package project.jinshang.mod_admin.mod_app;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import mizuki.project.core.restserver.config.BasicRet;
import mizuki.project.core.restserver.modules.oss_ali.AliOSS;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import project.jinshang.common.bean.PageRet;
import project.jinshang.common.constant.AdminAuthorityConst;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.config.OssManageUtil;
import project.jinshang.mod_admin.mod_app.bean.AppVersion;
import project.jinshang.mod_admin.mod_app.service.AppVersionService;
import project.jinshang.mod_common.bean.BasicExtRet;
import project.jinshang.mod_member.bean.Admin;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;



@RestController
@RequestMapping(value = {"/rest/admin/appversion"})
@Api(tags = "后台app版本管理", description = "app版本接口")
@SessionAttributes(AppConstant.ADMIN_SESSION_NAME)
public class AppVersionAction {

    @Value("${upload.dir.moduleIcon}")
    private  String uploadPath1;

    @Autowired
    private AppVersionService appVersionService;


    @Autowired
    private AliOSS aliOSS;

    @Autowired
    private OssManageUtil ossManageUtil;


    @PostMapping(value = "/insert")
    @ApiOperation(value = "新增app版本")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "apptype", value = "app类型 ios android", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "version", value = "app版本号", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "versionname", value = "app版本名称", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "type", value = "1普通升级 2强制升级", required = true, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "url", value = "下载地址", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "downloadurl", value = "自己服务器app下载地址 安卓必填 ios不填", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "channel", value = "渠道例如应用宝 华为市场", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "createtime", value = "创建时间", required = true, paramType = "query", dataType = "date"),
            @ApiImplicitParam(name = "updatetime", value = "修改时间", required = true, paramType = "query", dataType = "date"),
            @ApiImplicitParam(name = "mobile", value = "客服电话", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "remark", value = "备注", required = false, paramType = "query", dataType = "string"),
    })
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.APPVERSIONCONTROL + "')")
    public BasicRet insertAppVersionInfo(AppVersion appVersion, @RequestParam(value = "file", required = false) CommonsMultipartFile file, Model model) throws IOException {
        Admin admin = (Admin) model.asMap().get(AppConstant.ADMIN_SESSION_NAME);
        BasicRet basicRet = new BasicRet();

        if(file !=null) {
            if(!file.getOriginalFilename().endsWith(".apk") && !file.getOriginalFilename().endsWith(".ipa")){
                return  new BasicRet(BasicRet.ERR,"请上传apk类型或者ipa类型的文件");
            }
            try {
                // 流转换 转换为oss所需的InputStream
                DiskFileItem fi = (DiskFileItem) file.getFileItem();
                InputStream fileContent = fi.getInputStream();
                String fileName = fi.getName();
                //传入文件名 进行重命名，返回oss url
                String apkUrl = ossManageUtil.uploadFile(fileContent, "app", fileName,appVersion.getVersionname(),appVersion.getChannel());
                appVersion.setDownloadurl(apkUrl);

            } catch (Exception e) {
                throw new RuntimeException("上传失败");
            }
        }
        appVersion.setAdminid(admin.getId());
        appVersion.setCreatetime(new Date());
        appVersion.setUpdatetime(new Date());
        appVersionService.insertAppVersionInfo(appVersion);
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("新增成功");
        return basicRet;
    }

    @PostMapping(value = "/getDetailById")
    @ApiOperation(value = "查看App版本详情")
    @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "query", dataType = "long")
    public BasicRet getDetailById(Long id) {
        BasicExtRet basicRet = new BasicExtRet();
        AppVersion appVersion = appVersionService.getDetailById(id);
        if (appVersion ==null) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("不存在该app版本详情");
        } else {
            basicRet.setResult(BasicRet.SUCCESS);
            basicRet.setMessage("获取成功");
            basicRet.setData(appVersion);
        }
        return basicRet;
    }


    @PostMapping(value = "/update")
    @ApiOperation(value = "修改app版本表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "apptype", value = "app类型 ios android", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "version", value = "app版本号", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "versionname", value = "app版本名称", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "type", value = "1普通升级 2强制升级", required = false, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "url", value = "下载地址", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "downloadurl", value = "自己服务器app下载地址 安卓必填 ios选填", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "channel", value = "渠道例如应用宝 华为市场", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "createtime", value = "创建时间", required = false, paramType = "query", dataType = "date"),
            @ApiImplicitParam(name = "updatetime", value = "修改时间", required = false, paramType = "query", dataType = "date"),
            @ApiImplicitParam(name = "mobile", value = "客服电话", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "remark", value = "备注", required = false, paramType = "query", dataType = "string"),
    })
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.APPVERSIONCONTROL + "')")
    public BasicRet updateAppVersionInfo(AppVersion appVersion, @RequestParam(value = "file", required = false) CommonsMultipartFile file, Model model) {
        BasicRet basicRet = new BasicRet();
        Admin admin = (Admin) model.asMap().get(AppConstant.ADMIN_SESSION_NAME);
        AppVersion oldAppVersion = appVersionService.getDetailById(appVersion.getId());
        if (oldAppVersion == null) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("不存在该app版本");
        } else {
            if(file !=null) {
                if(!file.getOriginalFilename().endsWith(".apk") && !file.getOriginalFilename().endsWith(".ipa")){
                    return  new BasicRet(BasicRet.ERR,"请上传apk类型或者ipa类型的文件");
                }
                try {
                    // 流转换 转换为oss所需的InputStream
                    DiskFileItem fi = (DiskFileItem) file.getFileItem();
                    InputStream fileContent = fi.getInputStream();
                    String fileName = fi.getName();
                    //传入文件名 进行重命名，返回oss url
                    String apkUrl = ossManageUtil.uploadFile(fileContent, "app", fileName,appVersion.getVersionname(),appVersion.getChannel());
                    appVersion.setDownloadurl(apkUrl);

                } catch (Exception e) {
                    throw new RuntimeException("上传失败");
                }
            }

            appVersion.setAdminid(admin.getId());
            appVersion.setUpdatetime(new Date());
            appVersionService.updateAppVersionInfo(appVersion);
            basicRet.setResult(BasicRet.SUCCESS);
            basicRet.setMessage("修改成功");
        }
        return basicRet;
    }


    @PostMapping(value = "/updateVersionOrVersionName")
    @ApiOperation(value = "修改app版本表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "apptype", value = "app类型ios anroid", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "version", value = "app版本号", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "versionname", value = "app版本名称", required = true, paramType = "query", dataType = "string"),
    })
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.APPVERSIONCONTROL + "')")
    public BasicRet updateVersionOrVersionName(AppVersion appVersion, Model model) {
        BasicRet basicRet = new BasicRet();
        Admin admin = (Admin) model.asMap().get(AppConstant.ADMIN_SESSION_NAME);
        appVersion.setAdminid(admin.getId());
        appVersion.setUpdatetime(new Date());
        appVersionService.updateVersionOrVersionName(appVersion);
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("修改成功");

        return basicRet;
    }



    @PostMapping(value = "/deleteById")
    @ApiOperation(value = "删除app版本")
    @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "query", dataType = "long")
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.APPVERSIONCONTROL + "')")
    public BasicRet deleteAppVersionById(Long id) {
        BasicRet basicRet = new BasicRet();
        AppVersion oldAppVersion = appVersionService.getDetailById(id);
        if (oldAppVersion == null) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("不存在该app版本");
        } else {
            appVersionService.deleteAppVersionById(id);
            basicRet.setResult(BasicRet.SUCCESS);
            basicRet.setMessage("删除成功");
        }
        return basicRet;
    }

    @PostMapping("/list")
    @ApiOperation(value = "app版本列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "apptype", value = "app类型 ios android", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "version", value = "app版本号", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "versionname", value = "app版本名称", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "type", value = "1普通升级 2强制升级", required = false, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "url", value = "下载地址", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "downloadurl", value = "自己服务器app下载地址 安卓必填 ios不填", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "channel", value = "渠道例如应用宝 华为市场", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "createtime", value = "创建时间", required = false, paramType = "query", dataType = "date"),
            @ApiImplicitParam(name = "updatetime", value = "修改时间", required = false, paramType = "query", dataType = "date"),
            @ApiImplicitParam(name = "mobile", value = "客服电话", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "remark", value = "备注", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "pageNo", value = "页码(值为-1不分页)", required = true, paramType = "query", dataType = "int", defaultValue = "-1"),
            @ApiImplicitParam(name = "pageSize", value = "页数", required = false, paramType = "query", dataType = "int", defaultValue = "20"),
    })
    public PageRet getList(int pageNo, int pageSize, AppVersion appVersion) {
        PageInfo pageInfo = appVersionService.getListByPage(pageNo, pageSize,appVersion);
        PageRet pageRet = new PageRet();
        pageRet.data.setPageInfo(pageInfo);
        pageRet.setMessage("返回成功");
        pageRet.setResult(BasicRet.SUCCESS);
        return pageRet;
    }

}
