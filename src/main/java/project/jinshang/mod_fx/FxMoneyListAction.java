package project.jinshang.mod_fx;


import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import mizuki.project.core.restserver.config.BasicRet;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.annotation.*;

import project.jinshang.common.bean.PageRet;
import project.jinshang.common.constant.AdminAuthorityConst;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.common.utils.ExcelGen;
import project.jinshang.mod_common.bean.BasicExtRet;
import project.jinshang.mod_fx.bean.Fxcommision;
import project.jinshang.mod_fx.dto.FxcommisionDto;
import project.jinshang.mod_fx.service.FxCommisionService;
import project.jinshang.mod_fx.service.FxMoneyListService;
import project.jinshang.mod_member.bean.Member;
import project.jinshang.mod_member.bean.dto.MemberDto;
import project.jinshang.mod_member.service.MemberService;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value = {"/rest/admin/fxmoneylist"})
@Api(tags = "后台佣金汇总管理", description = "佣金汇总接口")
@SessionAttributes(AppConstant.ADMIN_SESSION_NAME)
public class FxMoneyListAction {

    @Autowired
    private MemberService memberService;
    @Autowired
    private FxMoneyListService fxMoneyListService;
    @Autowired
    private FxCommisionService fxCommisionService;

    @PostMapping("/list")
    @ApiOperation(value = "显示会员列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "页码(值为-1不分页)", required = true, paramType = "query", dataType = "int", defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页数", required = false, paramType = "query", dataType = "int", defaultValue = "20"),
    })
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.COMMISSIONSUMMATY + "')")
    public PageRet list(int pageNo, int pageSize) {

        PageInfo pageInfo = memberService.findAllMemberList(pageNo, pageSize);
        PageRet pageRet = new PageRet();
        pageRet.data.setPageInfo(pageInfo);
        pageRet.setMessage("返回成功");
        pageRet.setResult(BasicRet.SUCCESS);
        return pageRet;
    }


    @PostMapping("/list1")
    @ApiOperation(value = "查看佣金汇总")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "id",name = "memberid",required = true,dataType = "long",paramType = "query"),
            @ApiImplicitParam(name = "pageNo", value = "页码(值为-1不分页)", required = true, paramType = "query", dataType = "int", defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页数", required = false, paramType = "query", dataType = "int", defaultValue = "20"),
    })

    public PageRet list1(long memberid,int pageNo, int pageSize) {
        PageInfo pageInfo = fxMoneyListService.findCommisionList(memberid,pageNo, pageSize);
        PageRet pageRet = new PageRet();
        pageRet.data.setPageInfo(pageInfo);
        pageRet.setMessage("返回成功");
        pageRet.setResult(BasicRet.SUCCESS);
        return pageRet;
    }



    @RequestMapping(value = "/inverterlist",method = RequestMethod.POST)
    @ApiOperation(value = "分销邀请人列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "会员id",required = false,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "username",value = "用户名",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "realname",value = "真实姓名",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "clerkname",value = "客服人员",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "mobile",value = "手机",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "registDateStart",value = "开始时间",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "registDateEnd",value = "结束时间",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "loginDateStart",value = "最后登录时间-开始",required = false,paramType = "query",dataType = "date"),
            @ApiImplicitParam(name = "loginDateEnd",value = "最后登录时间-结束",required = false,paramType = "query",dataType = "date"),
            @ApiImplicitParam(name = "labelid",value = "标签id",required = false,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "gradleid",value = "组别id",required = false,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "isbuy",value = "是否消费0=全部1=无消费2=消费",required = false,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "invitecode",value = "邀请码",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "companyType", value = "是否是公司 0-全部，1-是，2-否", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageNo", value = "页码(值为-1不分页)", required = true, paramType = "query", dataType = "int", defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页数", required = false, paramType = "query", dataType = "int", defaultValue = "20"),
    })
    public PageRet selectmemberinfo(MemberDto memberDto, int pageNo, int pageSize){

        PageInfo pageInfo = memberService.selectInverterList(memberDto, pageNo, pageSize);
        PageRet pageRet = new PageRet();
        pageRet.data.setPageInfo(pageInfo);
        pageRet.setMessage("返回成功");
        pageRet.setResult(BasicRet.SUCCESS);
        return pageRet;
    }

    @GetMapping("/excelExport/inverterlist")
    @ApiOperation("excel导出三级分销邀请人列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "会员id",required = false,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "realname",value = "真实姓名",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "username",value = "用户名",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "clerkname",value = "客服人员",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "mobile",value = "手机",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "registDateStart",value = "开始时间",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "registDateEnd",value = "结束时间",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "loginDateStart",value = "最后登录时间-开始",required = false,paramType = "query",dataType = "date"),
            @ApiImplicitParam(name = "loginDateEnd",value = "最后登录时间-结束",required = false,paramType = "query",dataType = "date"),
            @ApiImplicitParam(name = "labelid",value = "标签id",required = false,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "gradleid",value = "组别id",required = false,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "isbuy",value = "是否消费0=全部1=无消费2=消费",required = false,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "invitecode",value = "邀请码",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "companyType", value = "是否是公司 0-全部，1-是，2-否", required = true, paramType = "query", dataType = "int"),
    })
    public ResponseEntity<InputStreamResource> inverterlistForExprotExcel(MemberDto memberDto){



        String[] rowTitles = new String[]{"邀请码","会员ID","真实姓名","会员全称","会员等级"
                ,"一级邀请人数量","二级邀请人数量"};


       List<Map<String,Object>> resList  = memberService.selectInverterList1(memberDto);
        XSSFWorkbook workbook = null;
        try {
            workbook = ExcelGen.common("三级分销邀请人列表",rowTitles, resList,null);
            if(workbook!=null){
                ByteArrayOutputStream baos=new   ByteArrayOutputStream();
                workbook.write(baos);
                System.out.println(baos.toByteArray().length);
                HttpHeaders headers = new HttpHeaders();
                headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
                headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", new String("三级分销邀请人列表.xlsx".getBytes(),"iso-8859-1")));
                headers.add("Pragma", "no-cache");
                headers.add("Expires", "0");
                String contentType = "application/vnd.ms-excel";
                return ResponseEntity.ok()
                        .headers(headers).contentType(MediaType.parseMediaType(contentType))
                        .body(new InputStreamResource(new ByteArrayInputStream(baos.toByteArray())));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(workbook != null){
                try {
                    workbook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return  null;
    }




    @PostMapping(value = "/get")
    @ApiOperation(value = "一级/二级邀请人按钮")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "inviterid", value = "邀请者id", required = true, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "type", value = "1.为一级邀请者id 2位二级邀请者id", required = true, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "pageNo", value = "页码(值为-1不分页)", required = true, paramType = "query", dataType = "int", defaultValue = "-1"),
            @ApiImplicitParam(name = "pageSize", value = "页数", required = false, paramType = "query", dataType = "int", defaultValue = "20"),
    })
    public PageRet getInviteridDetail(int pageNo, int pageSize,long inviterid,long type) {


        PageInfo pageInfo = memberService.getMemberByInviterid(inviterid,type,pageNo,pageSize);
        PageRet pageRet = new PageRet();
        if(pageInfo.getList().size()==0){
            pageRet.data.setPageInfo(pageInfo);
            pageRet.setMessage("不存在邀请人哦,该用户还没邀请过别人");
            pageRet.setResult(BasicRet.SUCCESS);
        }else {
            pageRet.data.setPageInfo(pageInfo);
            pageRet.setMessage("获取成功");
            pageRet.setResult(BasicRet.SUCCESS);
        }
        return pageRet;
    }

    @GetMapping("/excelExport/get")
    @ApiOperation("excel导出一级邀请人/二级邀请人")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "inviterid", value = "邀请者id", required = true, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "type", value = "1.为一级邀请者id 2位二级邀请者id", required = true, paramType = "query", dataType = "long"),
    })
    public ResponseEntity<InputStreamResource> getFirstAndSecondInviterForExprotExcel(long inviterid,long type){


        if(type == 1) {
            String[] rowTitles = new String[]{"会员名","真实姓名", "手机号", "会员等级", "邀请时间"};


            List<Map<String, Object>> resList = memberService.getMemberByInviterid1(inviterid, type);

            XSSFWorkbook workbook = null;
            try {
                workbook = ExcelGen.common("一级邀请人列表", rowTitles, resList, null);
                if (workbook != null) {
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    workbook.write(baos);
                    System.out.println(baos.toByteArray().length);
                    HttpHeaders headers = new HttpHeaders();
                    headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
                    headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", new String("一级邀请人列表.xlsx".getBytes(), "iso-8859-1")));
                    headers.add("Pragma", "no-cache");
                    headers.add("Expires", "0");
                    String contentType = "application/vnd.ms-excel";
                    return ResponseEntity.ok()
                            .headers(headers).contentType(MediaType.parseMediaType(contentType))
                            .body(new InputStreamResource(new ByteArrayInputStream(baos.toByteArray())));
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (workbook != null) {
                    try {
                        workbook.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }else{
            String[] rowTitles = new String[]{"会员名","真实姓名","手机号","会员等级","邀请时间"};


            List<Map<String,Object>> resList =  memberService.getMemberByInviterid1(inviterid,type);

            XSSFWorkbook workbook = null;
            try {
                workbook = ExcelGen.common("二级邀请人列表",rowTitles,resList,null);
                if(workbook!=null){
                    ByteArrayOutputStream baos=new   ByteArrayOutputStream();
                    workbook.write(baos);
                    System.out.println(baos.toByteArray().length);
                    HttpHeaders headers = new HttpHeaders();
                    headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
                    headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", new String("二级邀请人列表.xlsx".getBytes(),"iso-8859-1")));
                    headers.add("Pragma", "no-cache");
                    headers.add("Expires", "0");
                    String contentType = "application/vnd.ms-excel";
                    return ResponseEntity.ok()
                            .headers(headers).contentType(MediaType.parseMediaType(contentType))
                            .body(new InputStreamResource(new ByteArrayInputStream(baos.toByteArray())));
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if(workbook != null){
                    try {
                        workbook.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }

        return  null;
    }



    @ApiOperation(value = "后台-佣金记录列表/后台-佣金记录搜索列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderno", value = "订单单号", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "cmemberid", value = "返佣人", required = false, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "memberid", value = "买家", required = false, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "startime", value = "到账开始时间", required = false, paramType = "query", dataType = "date"),
            @ApiImplicitParam(name = "endtime", value = "到账结束时间", required = false, paramType = "query", dataType = "date"),
            @ApiImplicitParam(name = "progressnum", value = "进度状态 1：订单进行中2：佣金核算中97：订单关闭98：异常操作99：已完成", required = false, paramType = "query", dataType = "int", defaultValue = "-1"),
            @ApiImplicitParam(name = "pageNo", value = "页码(值为-1不分页)", required = true, paramType = "query", dataType = "int", defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页数", required = false, paramType = "query", dataType = "int", defaultValue = "20"),
    })
    @PostMapping("/list2")
    public PageRet getList2(Date startime, Date endtime, Fxcommision fxcommision, int pageNo, int pageSize) {
        PageInfo pageInfo = fxCommisionService.getListByPage1(startime,endtime, fxcommision,null,pageNo, pageSize);

        PageRet pageRet = new PageRet();
        pageRet.data.setPageInfo(pageInfo);
        pageRet.setMessage("返回成功");
        pageRet.setResult(BasicRet.SUCCESS);
        return pageRet;
    }


    @ApiOperation(value = "后台-累计返佣/后台-待到账佣金")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "progressnum", value = "progressNum进度状态（默认99为累计返佣，如果0为待到帐佣金）", required = true, paramType = "query", dataType = "long",defaultValue = "99"),
    })
    @PostMapping("/get1")
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.ORDERCOMMISSIONLOGS + "')")
    public BasicRet getCountCommisionPrice(Long progressnum) {
        BasicExtRet basicRet = new BasicExtRet();
        FxcommisionDto fxcommision = null;
        System.out.println("progressNum~~~~~~~~~~~~~"+progressnum);
        if(99 == progressnum) {
            //累计返佣
            fxcommision = fxCommisionService.getAllCountCommisionPrice();
        }else{
            //待到账佣金
            fxcommision = fxCommisionService.getAllWaitCommisionPrice();
        }
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("获取成功");
        basicRet.setData(fxcommision);

        return basicRet;
    }



    //--------------------------------------临时使用接口  填充二级邀请人id
    @GetMapping("/addinviterid2")
    @ApiOperation(value = "填充二级邀请人id")
    public BasicRet addinviterid2() {
        BasicExtRet basicRet = new BasicExtRet();
        List<Member> list = memberService.getAllMember();
        //下面用于调试的或者测试某个账号 为某个账号添加
      /*  long id = 17955l;
        MemberExample memberExample = new MemberExample();
        MemberExample.Criteria criteria = memberExample.createCriteria();
        criteria.andIdEqualTo(id);
        List<Member> list = memberService.selectByExample(memberExample);*/
        for (Member member:list) {
            if(member.getInviterid()!= null && member.getInviterid2() == null){
                //取到一级邀请人的信息
                Member member1 = memberService.getInviterIdByMemberId(member.getInviterid());
                //设置inviterid2
                if(member1.getInviterid()!=null) {
                    memberService.updateMemberInviterid2ById(member.getId(),member1.getInviterid());
                }
            }
        }
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("更新成功");
        basicRet.setData(list);

        return basicRet;
    }




}
