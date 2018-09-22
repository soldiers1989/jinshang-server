package project.jinshang.mod_member;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import mizuki.project.core.restserver.config.BasicRet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import project.jinshang.common.bean.PageRet;
import project.jinshang.common.constant.AdminAuthorityConst;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.common.utils.GenerateNo;
import project.jinshang.mod_admin.mod_prod.ProductsManageAction;
import project.jinshang.mod_admin.mod_upload.ProductStoreModel;
import project.jinshang.mod_admin.mod_upload.ReturnVisitImport;
import project.jinshang.mod_common.bean.BasicExtRet;
import project.jinshang.mod_member.bean.*;
import project.jinshang.mod_member.bean.dto.ReturnVisitDto;
import project.jinshang.mod_member.service.*;
import project.jinshang.mod_product.bean.*;
import project.jinshang.mod_product.bean.dto.AttributetblDto1;
import project.jinshang.mod_product.service.OrdersService;

import java.io.File;
import java.math.BigDecimal;
import java.util.*;


@RestController
@RequestMapping("/rest/admin/returnvisit")
@SessionAttributes({AppConstant.ADMIN_SESSION_NAME})
@Api(tags = "后台回访记录",description = "后台回访记录相关接口")
@Transactional(rollbackFor = Exception.class)
public class ReturnVisitAction {

    @Value("${upload.dir.moduleIcon}")
    private  String uploadPath1;

    @Autowired
    private ReturnVisitService returnVisitService;

    @Autowired
    private ReturnVisitImport returnVisitImport;

    @Autowired
    private AdminService adminService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private AdminWaysalesmanService adminWaysalesmanService;

    @PostMapping(value = "/insert")
    @ApiOperation(value = "新增回访记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "memberid", value = "回访会员", required = true, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "type", value = "回访类型", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "returntime", value = "回访时间", required = true, paramType = "query", dataType = "date"),
            @ApiImplicitParam(name = "result", value = "回访结果", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "content", value = "回访详细内容", required = true, paramType = "query", dataType = "string"),

    })
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.RETURNVISIT + "')")
    public BasicRet insertReturnVisitInfo(ReturnVisit returnVisit,Model model) {
        BasicRet basicRet = new BasicRet();
        Admin admin = (Admin) model.asMap().get(AppConstant.ADMIN_SESSION_NAME);
        returnVisit.setAdminid(admin.getId());
        returnVisit.setCreatetime(new Date());
        returnVisitService.insertReturnVisitInfo(returnVisit);
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("新增成功");
        return basicRet;
    }

    @PostMapping("/list")
    @ApiOperation(value = "回访记录汇总/查看某个会员下面回访记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "会员名", required = false, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "memberid", value = "会员id", required = false, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "adminid", value = "业务员id", required = false, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "mobile", value = "手机号", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "startime", value = "回访开始时间", required = false, paramType = "query", dataType = "date"),
            @ApiImplicitParam(name = "endtime", value = "回访结束时间", required = false, paramType = "query", dataType = "date"),
            @ApiImplicitParam(name = "type", value = "回访类型", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "result", value = "回访结果", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "content", value = "回访详细内容", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "labelname", value = "标签名称", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "waysalesman", value = "业务员", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "pageNo", value = "页码(值为-1不分页)", required = true, paramType = "query", dataType = "int", defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页数", required = false, paramType = "query", dataType = "int", defaultValue = "20"),
    })
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.RETURNVISIT + "')")
    public PageRet getList(String mobile,String waysalesman,String username,String labelname,Date startime, Date endtime,ReturnVisit returnVisit, int pageNo, int pageSize) {
        PageInfo pageInfo = returnVisitService.getList(mobile,waysalesman,username,labelname,startime,endtime,returnVisit,pageNo,pageSize);
        PageRet pageRet = new PageRet();
        pageRet.data.setPageInfo(pageInfo);
        pageRet.setMessage("返回成功");
        pageRet.setResult(BasicRet.SUCCESS);
        return pageRet;
    }


    @PostMapping("/getListByMemeberid")
    @ApiOperation(value = "查看某个会员下所有的回访记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "memberid", value = "会员id", required = false, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "pageNo", value = "页码(值为-1不分页)", required = true, paramType = "query", dataType = "int", defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页数", required = false, paramType = "query", dataType = "int", defaultValue = "20"),
    })
    public PageRet getListByMemeberid(ReturnVisit returnVisit, int pageNo, int pageSize) {
        PageInfo pageInfo = returnVisitService.getListByMemeberid(returnVisit.getMemberid(),pageNo,pageSize);
        PageRet pageRet = new PageRet();
        pageRet.data.setPageInfo(pageInfo);
        pageRet.setMessage("返回成功");
        pageRet.setResult(BasicRet.SUCCESS);
        return pageRet;
    }


    @PostMapping(value = "/update")
    @ApiOperation(value = "修改回访记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "ID", required = true, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "memberid", value = "回访会员", required = true, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "type", value = "回访类型", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "returntime", value = "回访时间", required = true, paramType = "query", dataType = "date"),
            @ApiImplicitParam(name = "result", value = "回访结果", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "content", value = "回访详细内容", required = true, paramType = "query", dataType = "string"),
    })
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.RETURNVISIT + "')")
    public BasicRet updateReturnVisitInfo(ReturnVisit returnVisit,Model model) {
        BasicRet basicRet = new BasicRet();
        ReturnVisit oldReturnVisit = returnVisitService.selectById(returnVisit.getId());
        Date nowdate = new Date();
        Date createtime = oldReturnVisit.getCreatetime();
        long between = nowdate.getTime() - createtime.getTime();
        if (oldReturnVisit == null) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("不存在该回访记录");
        } else if(between > (24*3600000)){//大于一天不允许修改
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("距离创建时间超过24小时不允许编辑了");
        }else {
            Admin admin = (Admin) model.asMap().get(AppConstant.ADMIN_SESSION_NAME);
            returnVisit.setAdminid(admin.getId());
            returnVisitService.updateReturnVisitInfo(returnVisit);
            basicRet.setResult(BasicRet.SUCCESS);
            basicRet.setMessage("修改成功");
        }
        return basicRet;
    }


    @PostMapping(value = "/get")
    @ApiOperation(value = "获取回访记录详情")
    @ApiImplicitParam(name = "id", value = "回访记录id", required = true, paramType = "query", dataType = "int")
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.RETURNVISIT + "')")
    public BasicRet get(Long id) {
        BasicExtRet basicRet = new BasicExtRet();
        ReturnVisit returnVisit = returnVisitService.selectById(id);
        if (returnVisit == null) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("不存在该回访记录");
        } else {
            basicRet.setResult(BasicRet.SUCCESS);
            basicRet.setMessage("获取成功");
            basicRet.setData(returnVisit);
        }
        return basicRet;
    }


    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ApiOperation(value = "删除回访记录")
    @ApiImplicitParam(name = "id", value = "回访记录id", required = true, paramType = "query", dataType = "int")
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.RETURNVISIT + "')")
    public BasicRet delete( Long id) {
        BasicRet basicRet = new BasicRet();
        ReturnVisit returnVisit = returnVisitService.selectById(id);
        Date nowdate = new Date();
        Date createtime = returnVisit.getCreatetime();
        long between = nowdate.getTime() - createtime.getTime();
        if (returnVisit == null) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("不存在该回访记录");
        } else if(between > (24*3600000)){
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("距离创建时间超过24小时不允许删除了");
        }else {
            returnVisitService.deleteById(id);
            basicRet.setResult(BasicRet.SUCCESS);
            basicRet.setMessage("删除成功");
        }
        return basicRet;
    }


    @PostMapping("/import/addrecord")
    @ApiOperation("excel导入回访记录")
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.RETURNVISITEXCEL + "')")
    public synchronized BasicRet addProductsByMould(@RequestParam("file") CommonsMultipartFile file,@RequestParam("id") long id) throws Exception {
        if(!file.getOriginalFilename().endsWith(".xlsx") && !file.getOriginalFilename().endsWith(".xls")){
            return  new BasicRet(BasicRet.ERR,"请上传Excel文件");
        }
        Admin admin = adminService.getById(id);
        if(admin == null){
            return  new BasicRet(BasicRet.ERR,"业务员不存在");
        }

        String fileName= GenerateNo.getUUID()+file.getOriginalFilename();

        File dir =  new File(uploadPath1);
        if(!dir.exists()){
            dir.mkdirs();
        }

        File newFile= new File(dir,fileName);
        //通过CommonsMultipartFile的方法直接写文件（注意这个时候）

        List<ReturnVisitDto> returnVisitList;

        try {
            file.transferTo(newFile);
            returnVisitList =  returnVisitImport.excelReturnVisitTo(newFile.getAbsolutePath());
        } catch (Exception e) {
            throw  e;
        } finally {
            newFile.delete();
        }

        if(returnVisitList.size()>0){
            for(ReturnVisitDto returnVisitDto : returnVisitList){
                ReturnVisit returnVisit = new ReturnVisit();
                returnVisit.setMemberid(returnVisitDto.getMemberid());
                returnVisit.setType(returnVisitDto.getType());
                returnVisit.setReturntime(returnVisitDto.getReturntime());
                returnVisit.setResult(returnVisitDto.getResult());
                returnVisit.setContent(returnVisitDto.getContent());
                returnVisit.setAdminid(id);
                returnVisit.setCreatetime(new Date());
                returnVisitService.insertSelective(returnVisit);

                //先去查询下这个底下是否有业务员
                Member member = memberService.selectById(returnVisitDto.getMemberid());
                if(member!=null){
                    if(member.getWaysalesman()!=null){
                        //先去删除 权限管理 里面填写的业务员 拿memberid去adminwaysalesman表删除这个关联
                        adminWaysalesmanService.deleteByUserid(member.getId());
                    }
                }

                //修改标签 //设置业务员
               memberService.updateMemberLabelnameAndWaysalesman(returnVisitDto.getLabelname(),admin.getRealname(),returnVisitDto.getMemberid());

               //还得去 权限管理---业务员管理里面增加
                AdminWaysalesman adminWaysalesman = new AdminWaysalesman();
                adminWaysalesman.setAdminid(id);
                adminWaysalesman.setUserid(returnVisitDto.getMemberid());
                adminWaysalesmanService.addManageUsersBylist(adminWaysalesman);
                //同时更新订单里的业务员
                ordersService.updateOrdersWaysalesmanBymemberid(admin.getRealname(),id);
            }
        }

        return  new BasicRet(BasicRet.SUCCESS,"导入成功");
    }
}
