package project.jinshang.mod_admin.mod_app;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import mizuki.project.core.restserver.config.BasicRet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.jinshang.mod_admin.mod_app.bean.AppVersion;
import project.jinshang.mod_admin.mod_app.dto.AppVersionDto;
import project.jinshang.mod_admin.mod_app.service.AppVersionService;
import project.jinshang.mod_common.bean.BasicExtRet;


@RestController
@RequestMapping(value = {"/rest/front/appversion"})
@Api(tags = "前台获取app版本", description = "前台获取app版本接口")
public class AppVersionFrontAction {

    @Autowired
    private AppVersionService appVersionService;


    @PostMapping("/checkversion")
    @ApiOperation(value = "检查版本")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "apptype", value = "app类型 ios android", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "version", value = "app版本号", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "versionname", value = "app版本名称", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "type", value = "1 普通升级 2 强制升级", required = false, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "url", value = "下载地址", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "downloadurl", value = "自己服务器下载地址", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "channel", value = "渠道例如应用宝 华为市场", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "createtime", value = "创建时间", required = false, paramType = "query", dataType = "date"),
            @ApiImplicitParam(name = "updatetime", value = "修改时间", required = false, paramType = "query", dataType = "date"),
            @ApiImplicitParam(name = "mobile", value = "客服电话", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "remark", value = "备注", required = false, paramType = "query", dataType = "string"),
    })
    public BasicRet checkversionList(AppVersion appVersion) {
        AppVersion newappVersion = appVersionService.checkversionList(appVersion);
        AppVersionDto appVersionDto = new AppVersionDto();
        if(appVersion.getApptype().equals("android")|| appVersion.getApptype().equals("Android")){
            if(newappVersion.getVersion().equals(appVersion.getVersion())){
                appVersionDto.setFlag(false);
                appVersionDto.setType(newappVersion.getType());
                appVersionDto.setDownloadurl(newappVersion.getDownloadurl());
                appVersionDto.setVersionname(newappVersion.getVersionname());
            } else{
                appVersionDto.setFlag(true);
                appVersionDto.setType(newappVersion.getType());
                appVersionDto.setDownloadurl(newappVersion.getDownloadurl());
                appVersionDto.setVersionname(newappVersion.getVersionname());
            }
        }

        if(appVersion.getApptype().equals("ios")||appVersion.getApptype().equals("Ios")||appVersion.getApptype().equals("IOS")) {
            if(newappVersion.getVersion().equals(appVersion.getVersion())){
                appVersionDto.setFlag(false);
                appVersionDto.setType(newappVersion.getType());
                appVersionDto.setUrl(newappVersion.getUrl());
                appVersionDto.setVersionname(newappVersion.getVersionname());
            } else{
                appVersionDto.setFlag(true);
                appVersionDto.setType(newappVersion.getType());
                appVersionDto.setUrl(newappVersion.getUrl());
                appVersionDto.setVersionname(newappVersion.getVersionname());
            }
        }

        BasicExtRet basicRet = new BasicExtRet();
        basicRet.setData(appVersionDto);
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("获取成功");
        return basicRet;
    }

}
