package project.jinshang.mod_admin.mod_creditapplyrecord;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import mizuki.project.core.restserver.config.BasicRet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.jinshang.common.bean.PageRet;
import project.jinshang.common.constant.AdminAuthorityConst;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.common.constant.Quantity;
import project.jinshang.common.constant.TradeConstant;
import project.jinshang.common.utils.DateUtils;
import project.jinshang.common.utils.ExcelGen;
import project.jinshang.common.utils.StringUtils;
import project.jinshang.mod_admin.mod_creditapplyrecord.bean.AccountDetailQuery;
import project.jinshang.mod_admin.mod_creditapplyrecord.bean.CreatingAccount;
import project.jinshang.mod_admin.mod_creditapplyrecord.bean.OutAccount;
import project.jinshang.mod_admin.mod_creditapplyrecord.service.AdminCreditapplyService;
import project.jinshang.mod_cash.bean.BuyerCapital;
import project.jinshang.mod_cash.bean.dto.BuyerCapitalQueryDto;
import project.jinshang.mod_cash.service.BuyerCapitalService;
import project.jinshang.mod_company.bean.BuyerCompanyInfo;
import project.jinshang.mod_company.bean.SellerCompanyInfo;
import project.jinshang.mod_company.service.BuyerCompanyService;
import project.jinshang.mod_credit.bean.BillCreate;
import project.jinshang.mod_credit.bean.CreditApplyRecord;
import project.jinshang.mod_credit.service.BillCreateService;
import project.jinshang.mod_member.bean.Admin;
import project.jinshang.mod_member.bean.Member;
import project.jinshang.mod_member.service.AdminService;
import project.jinshang.mod_member.service.MemberService;
import project.jinshang.scheduled.mapper.BillcreateTaskMapper;

import javax.security.auth.login.AccountException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;


/**
 * create : wyh
 * date : 2017/12/1
 */
@RestController
@RequestMapping("/rest/admin/creditapply")
@Api(tags = "后台授信管理")
@SessionAttributes({AppConstant.ADMIN_SESSION_NAME})
@Transactional(rollbackFor = Exception.class)
public class CreditapplyManageAction {

    @Autowired
    private AdminCreditapplyService adminCreditapplyService;

    @Autowired
    private AdminService adminService;


    @Autowired
    private MemberService memberService;

    @Autowired
    private BuyerCompanyService buyerCompanyService;

    @Autowired
    private  BillCreateService billCreateService;

    @Autowired
    private BuyerCapitalService buyerCapitalService;


    @Autowired
    private BillcreateTaskMapper billcreateTaskMapper;



    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ApiOperation("授信列表")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "状态0=待审核1=待受理2=受理中3=待复核4=已开通5=已拒绝6=已撤消",name = "state",required = false,dataType = "int",paramType = "query"),
            @ApiImplicitParam(value = "会员",name = "memberid",required = false,dataType = "int",paramType = "query"),
            @ApiImplicitParam(value = "联系人",name = "contract",required = false,dataType = "string",paramType = "query"),
            @ApiImplicitParam(value = "手机号",name = "phone",required = false,dataType = "string",paramType = "query"),
            @ApiImplicitParam(value = "单位名",name = "company",required = false,dataType = "string",paramType = "query"),
            @ApiImplicitParam(value = "申请编号",name = "id",required = false,dataType = "string",paramType = "query"),
    })
    public PageRet list(@RequestParam(required = true,defaultValue = "1") int pageNo,
                        @RequestParam(required = true,defaultValue = "10") int pageSize,
                        @RequestParam(required = false,defaultValue = "-1") short state,
                        @RequestParam(required = false,defaultValue = "-1") long memberid,
                        @RequestParam(required = false,defaultValue = "") String contract,
                        @RequestParam(required = false,defaultValue = "") String phone,
                        @RequestParam(required = false,defaultValue = "") String company,
                        @RequestParam(required = false,defaultValue = "-1") long id,
                        @RequestParam(required = false,defaultValue = "")Date startDate,
                        @RequestParam(required = false,defaultValue = "") Date endDate){
        PageRet pageRet = new PageRet();

        Map<String,Object> conditionMap = new HashMap<>();
        conditionMap.put("state",state);
        conditionMap.put("memberid",memberid);
        conditionMap.put("contract",contract);
        conditionMap.put("phone",phone);
        conditionMap.put("company",company);
        conditionMap.put("id",id);
        conditionMap.put("startDate",startDate);
        conditionMap.put("endDate",endDate);


        PageInfo pageInfo = adminCreditapplyService.listForAdmin(pageNo,pageSize,conditionMap);

        pageRet.data.setPageInfo(pageInfo);
        pageRet.setResult(BasicRet.SUCCESS);
        return  pageRet;
    }



    @RequestMapping(value = "/listForExcel",method = RequestMethod.GET)
    @ApiOperation("授信列表导出excel")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "状态0=待审核1=待受理2=受理中3=待复核4=已开通5=已拒绝6=已撤消",name = "state",required = false,dataType = "int",paramType = "query"),
            @ApiImplicitParam(value = "会员",name = "memberid",required = false,dataType = "int",paramType = "query"),
            @ApiImplicitParam(value = "联系人",name = "contract",required = false,dataType = "string",paramType = "query"),
            @ApiImplicitParam(value = "手机号",name = "phone",required = false,dataType = "string",paramType = "query"),
            @ApiImplicitParam(value = "单位名",name = "company",required = false,dataType = "string",paramType = "query"),
            @ApiImplicitParam(value = "申请编号",name = "id",required = false,dataType = "string",paramType = "query"),
    })
    public ResponseEntity<InputStreamResource> listForExcel(
                        @RequestParam(required = false,defaultValue = "-1") short state,
                        @RequestParam(required = false,defaultValue = "-1") long memberid,
                        @RequestParam(required = false,defaultValue = "") String contract,
                        @RequestParam(required = false,defaultValue = "") String phone,
                        @RequestParam(required = false,defaultValue = "") String company,
                        @RequestParam(required = false,defaultValue = "-1") long id,
                        @RequestParam(required = false,defaultValue = "")Date startDate,
                        @RequestParam(required = false,defaultValue = "") Date endDate){
        PageRet pageRet = new PageRet();

        Map<String,Object> conditionMap = new HashMap<>();
        conditionMap.put("state",state);
        conditionMap.put("memberid",memberid);
        conditionMap.put("contract",contract);
        conditionMap.put("phone",phone);
        conditionMap.put("company",company);
        conditionMap.put("id",id);
        conditionMap.put("startDate",startDate);
        conditionMap.put("endDate",endDate);


        List<Map<String,Object>> data = adminCreditapplyService.listForAdminExcel(conditionMap);

        String[] rowTitles = new String[]{"状态","申请时间","会员id","联系人","手机号","单位名","申请信用额度"};
        XSSFWorkbook workbook = null;
        try {
            workbook = ExcelGen.common("授信会员列表",rowTitles,data,null);
            if(workbook!=null){
                ByteArrayOutputStream baos=new   ByteArrayOutputStream();
                workbook.write(baos);
                System.out.println(baos.toByteArray().length);
                HttpHeaders headers = new HttpHeaders();
                headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
                headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", new String("授信会员列表.xlsx".getBytes(),"iso-8859-1")));
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





    @RequestMapping(value = "/validate",method = RequestMethod.POST)
    @ApiOperation("授信审核")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "ID",name = "id",required = false,dataType = "int",paramType = "query"),
            @ApiImplicitParam(value = "审核状态｛1=审核通过 5=审核不通过｝",name = "state",required = false,dataType = "int",paramType = "query"),
            @ApiImplicitParam(value = "备注",name = "description",required = false,dataType = "string",paramType = "query"),
    })
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.CREDITVALIDATE + "')")
    public BasicRet validate(@RequestParam(required =  true) long id,
                             @RequestParam(required = true) short state,
                             @RequestParam(required = false,defaultValue = "") String description,
                             Model model){

        Admin admin = (Admin) model.asMap().get(AppConstant.ADMIN_SESSION_NAME);

        BasicRet basicRet = new BasicRet();

        if(state != Quantity.STATE_1 && state != Quantity.STATE_5){
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("状态不合法");
            return basicRet;
        }

        CreditApplyRecord creditApplyRecord = adminCreditapplyService.getById(id);

        if(creditApplyRecord == null){
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("要审核的信息不存在");
            return basicRet;
        }

        if(creditApplyRecord.getState() != Quantity.STATE_0){
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("已经审核过了，不可重复审核");
            return basicRet;
        }

        CreditApplyRecord updateRecord =  new CreditApplyRecord();
        updateRecord.setId(creditApplyRecord.getId());
        updateRecord.setState(state);
        updateRecord.setAuditid(admin.getId());
        updateRecord.setAuditname(admin.getUsername());
        updateRecord.setAudittime(new Date());
        updateRecord.setDescription(StringUtils.nvl(description));

        adminCreditapplyService.updateByPrimaryKeySelective(updateRecord);


        basicRet.setMessage("审核成功");
        basicRet.setResult(BasicRet.SUCCESS);

        return  basicRet;
    }




    @PostMapping("/getServierid")
    @ApiOperation("业务员选择")
    public AdminListRet getServiers(){
        AdminListRet adminListRet =  new AdminListRet();

        List<Admin> list =  adminService.getAdminByRoles(AdminAuthorityConst.CREDITSALES);

        adminListRet.data = list;
        adminListRet.setResult(BasicRet.SUCCESS);
        return  adminListRet;
    }

    private  class  AdminListRet extends  BasicRet{
        private  List<Admin> data;

        public List<Admin> getData() {
            return data;
        }

        public void setData(List<Admin> data) {
            this.data = data;
        }
    }

    @RequestMapping(value = "/accept",method = RequestMethod.POST)
    @ApiOperation("授信受理")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "ID",name = "id",required = false,dataType = "int",paramType = "query"),
            @ApiImplicitParam(value = "主业务员id",name = "mainserverid",required = false,dataType = "int",paramType = "query"),
            @ApiImplicitParam(value = "辅业务员id",name = "assistserverid",required = false,dataType = "int",paramType = "query"),
            @ApiImplicitParam(value = "预计抵达时间",name = "expectarrivaltime",required = false,dataType = "int",paramType = "query"),
            @ApiImplicitParam(value = "受理时长",name = "acceptdays",required = false,dataType = "int",paramType = "query"),
    })
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.CREDITACCEPT + "')")
    public  BasicRet accept(@RequestParam(required = true) long id,
                            @RequestParam(required = true) long mainserverid,
                            @RequestParam(required = true) long assistserverid,
                            @RequestParam(required =  true) Date expectarrivaltime,
                            @RequestParam(required = true) int acceptdays){
        BasicRet basicRet =  new BasicRet();


        CreditApplyRecord creditApplyRecord = adminCreditapplyService.getById(id);
        if(creditApplyRecord == null){
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("信息不存在");
            return  basicRet;
        }


        if(creditApplyRecord.getState() != Quantity.STATE_1){
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("状态不正确");
            return  basicRet;
        }


        Admin mainServer = adminService.getById(mainserverid);
        Admin assistServer =  adminService.getById(assistserverid);

        if(mainServer == null){
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("主业务员不存在");
            return  basicRet;
        }



        String[] roles = (String[]) mainServer.getRoles();
        boolean havePer = false;
        if(roles != null){
            for(String rol : roles){
                if(rol.equalsIgnoreCase(AdminAuthorityConst.CREDITSALES)){
                    havePer = true;
                    break;
                }
            }
        }


        if(!havePer){
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("所选的主业务员没有该权限");
            return  basicRet;
        }




        if(assistServer == null){
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("辅业务员不存在");
            return  basicRet;
        }

        roles = (String[]) assistServer.getRoles();
        havePer = false;
        if(roles != null){
            for(String rol : roles){
                if(rol.equalsIgnoreCase(AdminAuthorityConst.CREDITSALES)){
                    havePer = true;
                    break;
                }
            }
        }

        if(!havePer){
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("所选的辅业务员没有该权限");
            return  basicRet;
        }


        CreditApplyRecord updateRecord = new CreditApplyRecord();
        updateRecord.setId(id);
        updateRecord.setMainserver(mainServer.getUsername());
        updateRecord.setAssistserver(assistServer.getUsername());
        updateRecord.setMainserverid(mainserverid);
        updateRecord.setAssistserverid(assistserverid);
        updateRecord.setExpectarrivaltime(expectarrivaltime);
        updateRecord.setAcceptdays(acceptdays);
        updateRecord.setState(Quantity.STATE_2);
        adminCreditapplyService.updateByPrimaryKeySelective(updateRecord);

        basicRet.setMessage("提交成功");
        basicRet.setResult(BasicRet.SUCCESS);
        return  basicRet;
    }




    @RequestMapping(value = "/submitApplyData",method = RequestMethod.POST)
    @ApiOperation("提交授信受理资料，进入待复核状态")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "ID",name = "id",required = false,dataType = "int",paramType = "query"),
            @ApiImplicitParam(value = "合同编号",name = "agreementno",required = true,dataType = "string",paramType = "query"),
            @ApiImplicitParam(value = "合同签订时间",name = "signtime",required = true,dataType = "string",paramType = "query"),
            @ApiImplicitParam(value = "合同签订地点",name = "signaddr",required = true,dataType = "string",paramType = "query"),
            @ApiImplicitParam(value = "合同文件",name = "agreementfile",required = true,dataType = "string",paramType = "query"),
            @ApiImplicitParam(value = "授信额度",name = "applymoney",required = true,dataType = "double",paramType = "query"),
    })
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.CREDITWRITEIN + "')")
    public  BasicRet submitApplyData(@RequestParam(required = true) long id,
                                     @RequestParam(required = true) String agreementno,
                                     @RequestParam(required = true) Date signtime,
                                     @RequestParam(required = true) String signaddr,
                                     @RequestParam(required = true) String agreementfile,
                                     @RequestParam(required = true)BigDecimal applymoney,
                                     Model model){
        BasicRet basicRet = new BasicRet();

        Admin admin = (Admin) model.asMap().get(AppConstant.ADMIN_SESSION_NAME);


        CreditApplyRecord creditApplyRecord = adminCreditapplyService.getById(id);
        if(creditApplyRecord == null){
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("信息不存在");
            return  basicRet;
        }


        if(creditApplyRecord.getState() != Quantity.STATE_2){
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("状态不正确");
            return  basicRet;
        }


        CreditApplyRecord updateRecord = new CreditApplyRecord();
        updateRecord.setId(id);
        updateRecord.setEntering(admin.getUsername());
        updateRecord.setEnteringid(admin.getId());
        updateRecord.setAgreementno(agreementno);
        updateRecord.setSigntime(signtime);
        updateRecord.setSignaddr(signaddr);
        updateRecord.setAgreementfile(agreementfile);
        updateRecord.setApplymoney(applymoney);
        updateRecord.setState(Quantity.STATE_3);

        adminCreditapplyService.updateByPrimaryKeySelective(updateRecord);

        basicRet.setMessage("提交成功");
        basicRet.setResult(BasicRet.SUCCESS);
        return  basicRet;
    }


    @RequestMapping(value = "/checkApplyData",method = RequestMethod.POST)
    @ApiOperation("授信复核")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "ID",name = "id",required = false,dataType = "int",paramType = "query"),
            @ApiImplicitParam(value = "状态4=已开通5=已拒绝",name = "state",required = true,dataType = "int",paramType = "query"),
    })
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.CREDITCHECK + "')")
    public  BasicRet checkApplyData(@RequestParam(required = true) long id,
                                    @RequestParam(required = true) short state,
                                    Model model) throws Exception {
        BasicRet basicRet = new BasicRet();
        Admin admin = (Admin) model.asMap().get(AppConstant.ADMIN_SESSION_NAME);

        CreditApplyRecord creditApplyRecord = adminCreditapplyService.getById(id);
        if(creditApplyRecord == null){
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("信息不存在");
            return  basicRet;
        }


        if(creditApplyRecord.getState() != Quantity.STATE_3){
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("状态不正确");
            return  basicRet;
        }


        Member member =  memberService.getMemberById(creditApplyRecord.getMemberid());
        if(member == null){
            basicRet.setMessage("申请人不存在");
            basicRet.setResult(BasicRet.ERR);
            return  basicRet;
        }

        if(state == Quantity.STATE_4){ //开通授信
            Member updateMember = new Member();
            updateMember.setId(creditApplyRecord.getMemberid());
            updateMember.setCreditlimit(creditApplyRecord.getApplymoney());
            updateMember.setAvailablelimit(creditApplyRecord.getApplymoney());

            //授信状态，已开通
            updateMember.setCreditstate(Quantity.STATE_1);

//            //授信账单日
//            Calendar calendar =  Calendar.getInstance();
//
//            //设置还款日
//            calendar.set(Calendar.DATE,creditApplyRecord.getRepaymentdate());
//            //推出账单日
//            calendar.add(Calendar.DATE,-TradeConstant.lastday_to_statementdate_interval);
//
//            //月份+1
//            calendar.add(Calendar.MONTH,1);
//
//            //如果当前时间已经过了账单日了，则设置账单日为下个月
//            if(calendar.getTime().getTime() < new Date().getTime()){
//                calendar.add(Calendar.MONTH,1);
//            }
//
//
//            updateMember.setCreditaccountday(DateUtils.format(calendar.getTime(),"yyyy-MM-dd"));
//            calendar.add(Calendar.DATE,-5);
//            calendar.add(Calendar.DATE,-1);
//            String endDays =  DateUtils.format(calendar.getTime(),"yyyy.MM.dd");
//            calendar.add(Calendar.DATE,1);
//            calendar.add(Calendar.MONTH,-1);
//            String startDays = DateUtils.format(calendar.getTime(),"yyyy.MM.dd");
//
//            updateMember.setCreditstarttoend(startDays+"-"+endDays);

            int count = memberService.updateByPrimaryKeySelective(updateMember);

            if(count != 1){
                throw  new Exception("系统出现故障");
            }
        }

        CreditApplyRecord updateRecord = new CreditApplyRecord();
        updateRecord.setId(id);
        updateRecord.setState(state);
        updateRecord.setReviewer(admin.getUsername());
        updateRecord.setReviewerid(admin.getId());
        updateRecord.setReviewtime(new Date());
        adminCreditapplyService.updateByPrimaryKeySelective(updateRecord);

        basicRet.setMessage("提交成功");
        basicRet.setResult(BasicRet.SUCCESS);
        return  basicRet;
    }



    @PostMapping("/detail")
    public  CreditDetailRet  detail(@RequestParam(required = true) long id){
        CreditDetailRet creditDetailRet = new CreditDetailRet();

        CreditApplyRecord creditApplyRecord =  adminCreditapplyService.getById(id);

        if(creditApplyRecord == null){
            creditDetailRet.setMessage("授信信息不存在");
            creditDetailRet.setResult(BasicRet.ERR);
            return  creditDetailRet;
        }

        Member member =  memberService.getMemberById(creditApplyRecord.getMemberid());
        if(member == null){
            creditDetailRet.setMessage("申请的用户不存在");
            creditDetailRet.setResult(BasicRet.ERR);
            return  creditDetailRet;
        }

//        BuyerCompanyInfo buyerCompanyInfo = buyerCompanyService.getBuyerCompanyInfoByMemberId(member.getId());
//        member.setBuyerCompanyInfo(buyerCompanyInfo);
        memberService.fillMember(member);

        creditDetailRet.setResult(BasicRet.SUCCESS);
        creditDetailRet.setMessage("查询成功");
        creditDetailRet.data.creditApplyRecord =  creditApplyRecord;
        creditDetailRet.data.member = member;

        return  creditDetailRet;
    }



    private  class  CreditDetailRet extends  BasicRet{
        private  class  CreditDetailData{
            private  CreditApplyRecord creditApplyRecord;
            private  Member member;

            public CreditApplyRecord getCreditApplyRecord() {
                return creditApplyRecord;
            }

            public void setCreditApplyRecord(CreditApplyRecord creditApplyRecord) {
                this.creditApplyRecord = creditApplyRecord;
            }

            public Member getMember() {
                return member;
            }

            public void setMember(Member member) {
                this.member = member;
            }
        }


        private  CreditDetailData data = new CreditDetailData();

        public CreditDetailData getData() {
            return data;
        }

        public void setData(CreditDetailData data) {
            this.data = data;
        }
    }


    @PostMapping("/listCreditUser")
    @ApiOperation("授信会员管理列表")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "买家授信状态 0=未开通，1=已开通，2=禁用授信",name = "creditstate",paramType = "query")
    })
    public  PageRet listCreditUser(@RequestParam(required = false,defaultValue = "0") long memberid,
                                   @RequestParam(required = false,defaultValue = "") String username,
                                   @RequestParam(required = false,defaultValue = "") String realname,
                                   @RequestParam(required = false,defaultValue = "") String companyname,
                                   @RequestParam(required = false,defaultValue = "") String mobile,
                                   @RequestParam(required = false,defaultValue = "-1") short creditstate,
                                   @RequestParam(required = true,defaultValue = "1") int pageNo,
                                   @RequestParam(required = true,defaultValue = "20") int pageSize){
        PageRet pageRet =  new PageRet();

        Map<String,Object> conditMap = new HashMap<>();
        conditMap.put("memberid",memberid);
        conditMap.put("username",username);
        conditMap.put("realname",realname);
        conditMap.put("companyname",companyname);
        conditMap.put("mobile",mobile);
        conditMap.put("creditstate",creditstate);

        PageInfo pageInfo =  adminCreditapplyService.listCreditUser(conditMap,pageNo,pageSize);

        pageRet.data.setPageInfo(pageInfo);
        pageRet.setResult(BasicRet.SUCCESS);
        return  pageRet;
    }




    @GetMapping("/exportExcel/listCreditUser")
    @ApiOperation("授信会员管理导出Excel")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "买家授信状态 0=未开通，1=已开通，2=禁用授信",name = "creditstate",paramType = "query")
    })
    public ResponseEntity<InputStreamResource> exportExcelCreditUser(@RequestParam(required = false,defaultValue = "0") long memberid,
                                                                    @RequestParam(required = false,defaultValue = "") String username,
                                                                    @RequestParam(required = false,defaultValue = "") String realname,
                                                                    @RequestParam(required = false,defaultValue = "") String companyname,
                                                                    @RequestParam(required = false,defaultValue = "") String mobile,
                                                                    @RequestParam(required = false,defaultValue = "-1") short creditstate){

        Map<String,Object> conditMap = new HashMap<>();
        conditMap.put("memberid",memberid);
        conditMap.put("username",username);
        conditMap.put("realname",realname);
        conditMap.put("companyname",companyname);
        conditMap.put("mobile",mobile);
        conditMap.put("creditstate",creditstate);
        List<Map<String,Object>> resList  =  adminCreditapplyService.listCreditUserForAdminExportExcel(conditMap);

        String[] rowTitles = new String[]{"会员全称","会员类型","真实姓名","会员等级",
                "联系手机","邮箱","单位全称","单位地址","授信额度","可用授信额度","最后还款日",
                "累计帐单数","累计违约数","最长违约天数", "累计违约金额"};

        XSSFWorkbook workbook = null;
        try {
            workbook = ExcelGen.common("授信管理列表",rowTitles,resList,null);
            if(workbook!=null){
                ByteArrayOutputStream baos=new ByteArrayOutputStream();
                workbook.write(baos);
                System.out.println(baos.toByteArray().length);
                HttpHeaders headers = new HttpHeaders();
                headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
                headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", new String("授信管理列表.xlsx".getBytes(),"iso-8859-1")));
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


    @PostMapping("/listUserBillCreate")
    @ApiOperation("用户授信账单列表")
    @ApiImplicitParams({
    })
   public  PageRet listUserBillCreate(@RequestParam(required = false,defaultValue = "0") long memberid,
                                      @RequestParam(required = false,defaultValue = "1") int pageNo,
                                      @RequestParam(required = false,defaultValue = "20") int pageSize){
       PageRet pageRet = new PageRet();
       PageInfo pageInfo = adminCreditapplyService.listUserBillCreate(memberid,pageNo,pageSize);

       pageRet.data.setPageInfo(pageInfo);
       pageRet.setResult(BasicRet.SUCCESS);
       return  pageRet;
   }



   @PostMapping("/creditUser")
   @ApiOperation("授信用户信息")
   public  CreditUserRet creditUser(@RequestParam(required = true) long memberid){
       CreditUserRet creditUserRet =  new CreditUserRet();

       Map<String,Object> map =  adminCreditapplyService.creditUser(memberid);

       creditUserRet.creditUser  =  map;
       creditUserRet.setResult(BasicRet.SUCCESS);
       return  creditUserRet;
   }

   private  class  CreditUserRet extends  BasicRet{
       private  Map<String,Object> creditUser;

       public Map<String, Object> getCreditUser() {
           return creditUser;
       }

       public void setCreditUser(Map<String, Object> creditUser) {
           this.creditUser = creditUser;
       }
   }


    @PostMapping("/creditCapitalList")
    @ApiOperation("授信账单资金明细")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "账单id",name = "billcreateid",paramType = "query")
    })
   public  CreditCapitalRet creditCapitalList(@RequestParam(required = true) int billcreateid,
                                              @RequestParam(required = true,defaultValue = "1") int pageNo,
                                              @RequestParam(required = true,defaultValue = "20") int pageSize){
        CreditCapitalRet creditCapitalRet = new CreditCapitalRet();

        BillCreate billCreate = billCreateService.getById(billcreateid);
        if(billCreate == null){
            creditCapitalRet.setMessage("该账单不存在");
            creditCapitalRet.setResult(BasicRet.ERR);
            return  creditCapitalRet;
        }

        BuyerCapitalQueryDto queryDto =  new BuyerCapitalQueryDto();
        queryDto.setBillcreateid(billcreateid);

        PageInfo pageInfo = buyerCapitalService.list(pageNo,pageSize,queryDto);

        creditCapitalRet.billCreate = billCreate;
        creditCapitalRet.pageInfo = pageInfo;
        creditCapitalRet.setResult(BasicRet.SUCCESS);
        return  creditCapitalRet;
   }


   private  class  CreditCapitalRet extends  BasicRet{
       private BillCreate billCreate;

       private PageInfo pageInfo;

       public BillCreate getBillCreate() {
           return billCreate;
       }

       public void setBillCreate(BillCreate billCreate) {
           this.billCreate = billCreate;
       }

       public PageInfo getPageInfo() {
           return pageInfo;
       }

       public void setPageInfo(PageInfo pageInfo) {
           this.pageInfo = pageInfo;
       }
   }



   @PostMapping("/currentCridetStatus")
   @ApiOperation("当前授信情况")
   public  CurrentCridetRet currentCridetStatus(){

       CurrentCridetRet currentCridetRet = new CurrentCridetRet();

       PageInfo pageInfo =  adminCreditapplyService.getGroupSettlementtime(1,1);
       OutAccount outAccount = new OutAccount();
       if(pageInfo.getList() != null && pageInfo.getList().size()==1){
           String settlement = (String) pageInfo.getList().get(0);
           outAccount.setAccountDate(settlement.substring(11,18));
           outAccount.setJiesuanDate(settlement);
           outAccount.setUserMember(adminCreditapplyService.getUserMeberBySettlement(settlement));

           outAccount.setOutMoney(adminCreditapplyService.getOutMoneyBySettlement(settlement));
           outAccount.setShouTotalMoney(adminCreditapplyService.getInMoneyBySettlement(settlement));
           outAccount.setJiaoqingUserCount(adminCreditapplyService.getCountBySettlementAndState(settlement,Quantity.STATE_1));
           outAccount.setYuqiUserCount(adminCreditapplyService.getCountBySettlementAndState(settlement,Quantity.STATE_2));

           if(outAccount.getOutMoney().equals(outAccount.getShouTotalMoney())){
               outAccount.setState("已缴清");
           }else{
               outAccount.setState("未缴清");
           }
       }



       //待审核
       int checkpending = adminCreditapplyService.getCountByStates(Quantity.STATE_0);

       //待受理
       int  waitaccept = adminCreditapplyService.getCountByStates(Quantity.STATE_1);

       //受理中
       int accepting =  adminCreditapplyService.getCountByStates(Quantity.STATE_2);

       currentCridetRet.data.outAccount = outAccount;
       currentCridetRet.data.checkpending = checkpending;
       currentCridetRet.data.waitaccept = waitaccept;
       currentCridetRet.data.accepting = accepting;

       currentCridetRet.setResult(BasicRet.SUCCESS);
       return currentCridetRet;
   }


   private  class  CurrentCridetRet extends  BasicRet{
       private class CurrentCridetData{
           private   OutAccount outAccount;

           @ApiModelProperty(notes = "待审核")
           private   int checkpending;

           @ApiModelProperty(notes = "待受理")
           private  int  waitaccept;

           @ApiModelProperty(notes = "受理中")
           private  int  accepting;

           public OutAccount getOutAccount() {
               return outAccount;
           }

           public void setOutAccount(OutAccount outAccount) {
               this.outAccount = outAccount;
           }

           public int getCheckpending() {
               return checkpending;
           }

           public void setCheckpending(int checkpending) {
               this.checkpending = checkpending;
           }

           public int getWaitaccept() {
               return waitaccept;
           }

           public void setWaitaccept(int waitaccept) {
               this.waitaccept = waitaccept;
           }

           public int getAccepting() {
               return accepting;
           }

           public void setAccepting(int accepting) {
               this.accepting = accepting;
           }
       }


       private  CurrentCridetData data=new CurrentCridetData();

       public CurrentCridetData getData() {
           return data;
       }

       public void setData(CurrentCridetData data) {
           this.data = data;
       }
   }





   @PostMapping("/accountPeriodMage")
   @ApiOperation("授信账期管理")
   public AccountReriodRet AccountPeriodManage(@RequestParam(required = true,defaultValue = "1") int pageNo,
                                       @RequestParam(required = true,defaultValue = "20") int pageSize){

       AccountReriodRet accountReriodRet = new AccountReriodRet();

       //生成中的账单
       CreatingAccount creatingAccount =  new CreatingAccount();
       creatingAccount.setAccountDate(DateUtils.format(new Date(),"yyyyMM"));


       Calendar calendar = Calendar.getInstance();
       calendar.set(Calendar.DAY_OF_MONTH,27);
       Date buyerinspectiontimeEndDate = calendar.getTime();
       calendar.add(Calendar.MONTH,-1);
       Date buyerinspectiontimeStartDate =  calendar.getTime();

       String buyerinspectiontimeStart = DateUtils.format(buyerinspectiontimeStartDate,"yyyy-MM-dd")+" 00:00:00";
       String buyerinspectiontimeEnd =  DateUtils.format(buyerinspectiontimeEndDate,"yyyy-MM-dd")+" 23:59:59";

       //结算期
       creatingAccount.setJiesuanDate(DateUtils.format(buyerinspectiontimeStartDate,"yyyy.MM.dd")+"-"+DateUtils.format(buyerinspectiontimeEndDate,"yyyy.MM.dd"));

       //已授出金额 （已用-退货）
       //已用授信
       BigDecimal usedMoney = billcreateTaskMapper.getAllUserPayTotalMoney(DateUtils.StrToDate(buyerinspectiontimeStart),DateUtils.StrToDate(buyerinspectiontimeEnd));
       if(usedMoney == null){
           usedMoney =  new BigDecimal(0);
       }

       //退款授信金额
       BigDecimal backMoney = billcreateTaskMapper.getAllUserDrawbackMoney(DateUtils.StrToDate(buyerinspectiontimeStart),DateUtils.StrToDate(buyerinspectiontimeEnd));
       if(backMoney == null){
           backMoney = new BigDecimal(0);
       }

       BigDecimal outMoney = usedMoney.subtract(backMoney);
       creatingAccount.setOutMoney(outMoney);

       //该账期内使用的授信用户的个数
       int userMemberCount =  billcreateTaskMapper.getUsedMemberCount(DateUtils.StrToDate(buyerinspectiontimeStart),DateUtils.StrToDate(buyerinspectiontimeEnd));
       creatingAccount.setUserMember(userMemberCount);


       accountReriodRet.setCreatingAccount(creatingAccount);


       PageInfo pageInfo =  adminCreditapplyService.getGroupSettlementtime(pageNo,pageSize);
       List<String> SettlementtimeList = pageInfo.getList();
       List<OutAccount> outAccounts = new ArrayList<>();
       for(String settlement : SettlementtimeList){

           OutAccount outAccount = new OutAccount();
           outAccount.setAccountDate(settlement.substring(11,18));
           outAccount.setJiesuanDate(settlement);
           outAccount.setUserMember(adminCreditapplyService.getUserMeberBySettlement(settlement));

           outAccount.setOutMoney(adminCreditapplyService.getOutMoneyBySettlement(settlement));
           outAccount.setShouTotalMoney(adminCreditapplyService.getInMoneyBySettlement(settlement));
           outAccount.setJiaoqingUserCount(adminCreditapplyService.getCountBySettlementAndState(settlement,Quantity.STATE_1));
           outAccount.setYuqiUserCount(adminCreditapplyService.getCountBySettlementAndState(settlement,Quantity.STATE_2));

           if(outAccount.getOutMoney().equals(outAccount.getShouTotalMoney())){
               outAccount.setState("已缴清");
           }else{
               outAccount.setState("未缴清");
           }

           outAccounts.add(outAccount);
       }

       pageInfo.setList(outAccounts);

       accountReriodRet.pageInfo = pageInfo;

       accountReriodRet.setResult(BasicRet.SUCCESS);
       return  accountReriodRet;

   }


   private class AccountReriodRet extends  BasicRet{
       @ApiModelProperty(notes = "生成中的账期")
       private CreatingAccount creatingAccount;

       @ApiModelProperty(notes = "已出授信账单")
       private  PageInfo pageInfo;



       public CreatingAccount getCreatingAccount() {
           return creatingAccount;
       }

       public void setCreatingAccount(CreatingAccount creatingAccount) {
           this.creatingAccount = creatingAccount;
       }


       public PageInfo getPageInfo() {
           return pageInfo;
       }

       public void setPageInfo(PageInfo pageInfo) {
           this.pageInfo = pageInfo;
       }
   }


   @PostMapping("/getAccountDetai")
   @ApiOperation("授信账期明细")
   @ApiImplicitParams({
           @ApiImplicitParam(value = "账期",name ="settlement",required = true,paramType = "query"),
           @ApiImplicitParam(value = "结算账单号",name ="billno",required = false,paramType = "query"),
           @ApiImplicitParam(value = "会员ID",name ="memberid",required = false,paramType = "query"),
           @ApiImplicitParam(value = "会员姓名",name ="membername",required = false,paramType = "query"),
           @ApiImplicitParam(value = "单位名称",name ="companyname",required = false,paramType = "query"),
           @ApiImplicitParam(value = "状态 状态0=未缴清1=已缴清2=已逾期 -1=所有",name ="state",required = false,paramType = "query"),
   })
   public  AccountDetailRet getAccountDetai(@RequestParam(required = true) String settlement,
                                            @RequestParam(required = false,defaultValue = "") String billno,
                                            @RequestParam(required = false,defaultValue = "0") long memberid,
                                            @RequestParam(required = false,defaultValue = "") String membername,
                                            @RequestParam(required = false,defaultValue = "") String companyname,
                                            @RequestParam(required = false,defaultValue = "-1") Short state,
                                            @RequestParam(required = true,defaultValue = "1") int pageNo,
                                            @RequestParam(required = true,defaultValue = "20") int pageSize){
       AccountDetailRet accountDetailRet = new AccountDetailRet();


       OutAccount outAccount = new OutAccount();
       outAccount.setAccountDate(settlement.substring(11,18));
       outAccount.setJiesuanDate(settlement);
       outAccount.setUserMember(adminCreditapplyService.getUserMeberBySettlement(settlement));

       outAccount.setOutMoney(adminCreditapplyService.getOutMoneyBySettlement(settlement));
       outAccount.setShouTotalMoney(adminCreditapplyService.getInMoneyBySettlement(settlement));
       outAccount.setJiaoqingUserCount(adminCreditapplyService.getCountBySettlementAndState(settlement,Quantity.STATE_1));
       outAccount.setYuqiUserCount(adminCreditapplyService.getCountBySettlementAndState(settlement,Quantity.STATE_2));
       if(outAccount.getOutMoney().equals(outAccount.getShouTotalMoney())){
           outAccount.setState("已缴清");
       }else{
           outAccount.setState("未缴清");
       }

       AccountDetailQuery query = new AccountDetailQuery();
       query.setBillno(billno);
       query.setCompanyname(companyname);
       query.setMemberid(memberid);
       query.setMembername(membername);
       query.setSettlement(settlement);
       query.setState(state);

       PageInfo pageInfo =  adminCreditapplyService.getAccountDetaiByPage(query, pageNo, pageSize);

       accountDetailRet.outAccount = outAccount;
       accountDetailRet.pageInfo =  pageInfo;

       accountDetailRet.setResult(BasicRet.SUCCESS);

       return  accountDetailRet;
   }



   private  class  AccountDetailRet extends  BasicRet{

       private  OutAccount outAccount;

       private  PageInfo pageInfo;

       public OutAccount getOutAccount() {
           return outAccount;
       }

       public void setOutAccount(OutAccount outAccount) {
           this.outAccount = outAccount;
       }

       public PageInfo getPageInfo() {
           return pageInfo;
       }

       public void setPageInfo(PageInfo pageInfo) {
           this.pageInfo = pageInfo;
       }
   }





}
