package project.jinshang.mod_member;

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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.jinshang.common.bean.MemberLogOperator;
import project.jinshang.common.bean.PageRet;
import project.jinshang.common.constant.AdminAuthorityConst;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.common.constant.Quantity;
import project.jinshang.common.utils.ExcelGen;
import project.jinshang.common.utils.StringUtils;
import project.jinshang.mod_company.bean.BuyerCompanyInfo;
import project.jinshang.mod_company.service.BuyerCompanyService;
import project.jinshang.mod_member.bean.Admin;
import project.jinshang.mod_member.bean.AdminUser;
import project.jinshang.mod_member.bean.Member;
import project.jinshang.mod_member.bean.MemberGrade;
import project.jinshang.mod_member.bean.dto.MemberAdminQueryDto;
import project.jinshang.mod_member.service.*;
import project.jinshang.mod_product.service.MemberOperateLogService;
import project.jinshang.mod_server.service.MemberServerService;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * create : wyh
 * date : 2017/11/3
 */

@RestController
@RequestMapping("/rest/admin/member")
@Api(tags = "后台会员管理", description = "会员信息搜索、编辑、重置密码、清除支付密码")
@Transactional(rollbackFor = Exception.class)
@SessionAttributes({AppConstant.ADMIN_SESSION_NAME})
public class MemberAdminRestAction {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberAdminService memberAdminService;

    @Autowired
    private BuyerCompanyService buyerCompanyService;

    @Autowired
    private MemberGradeService memberGradeService;

    @Autowired
    protected MemberLogOperator memberLogOperator;

    @Autowired
    private MemberOperateLogService memberOperateLogService;

    @Autowired
    private MemberServerService memberServerService;

    @Autowired
    private AdminUserService adminUserService;
    @Autowired
    private AdminService adminService;

    @PostMapping("/editMember")
    @ApiOperation("修改会员信息")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "姓名", name = "realname", required = true, paramType = "query"),
            @ApiImplicitParam(value = "会员等级", name = "gradeid", required = false, paramType = "query"),
            @ApiImplicitParam(value = "介绍人", name = "waysalesman", required = false, paramType = "query"),
            @ApiImplicitParam(value = "手机", name = "mobile", required = true, paramType = "query"),
            @ApiImplicitParam(value = "邮箱", name = "email", required = false, paramType = "query"),
            @ApiImplicitParam(value = "传真", name = "faxes", required = false, paramType = "query"),
            @ApiImplicitParam(value = "固定电话", name = "telephone", required = false, paramType = "query"),
            @ApiImplicitParam(value = "邮编", name = "postcode", required = false, paramType = "query"),
            @ApiImplicitParam(value = "QQ", name = "qq", required = false, paramType = "query"),
            @ApiImplicitParam(value = "微信", name = "wxpay", required = false, paramType = "query"),
            @ApiImplicitParam(value = "支付宝", name = "alipay", required = false, paramType = "query"),
            @ApiImplicitParam(value = "爱好", name = "hobby", required = false, paramType = "query"),
            @ApiImplicitParam(value = "省", name = "province", required = true, paramType = "query"),
            @ApiImplicitParam(value = "市", name = "city", required = true, paramType = "query"),
            @ApiImplicitParam(value = "区", name = "citysmall", required = true, paramType = "query"),
            @ApiImplicitParam(value = "地址", name = "address", required = false, paramType = "query"),
            @ApiImplicitParam(value = "业务员", name = "clerkname", required = false, paramType = "query"),
            @ApiImplicitParam(value = "公司简称", name = "shortname", required = false, paramType = "query"),
            @ApiImplicitParam(value = "公司名称", name = "companyname", required = false, paramType = "query"),
            @ApiImplicitParam(value = "公司省", name = "companyprovince", required = false, paramType = "query"),
            @ApiImplicitParam(value = "公司市", name = "companycity", required = false, paramType = "query"),
            @ApiImplicitParam(value = "公司区", name = "companycitysmall", required = false, paramType = "query"),
            @ApiImplicitParam(value = "公司所在地区", name = "companyaddress", required = false, paramType = "query"),
            @ApiImplicitParam(value = "单位电话", name = "companyworktelephone", required = false, paramType = "query"),
            @ApiImplicitParam(value = "公司联系手机", name = "companymobile", required = false, paramType = "query"),
            @ApiImplicitParam(value = "结算方式", name = "methodSettingAccount", required = false, paramType = "query"),
            @ApiImplicitParam(value = "开户行", name = "bankdeposit", required = false, paramType = "query"),
            @ApiImplicitParam(value = "开户人", name = "bankuser", required = false, paramType = "query"),
            @ApiImplicitParam(value = "开户帐号", name = "bankaccount", required = false, paramType = "query"),
            @ApiImplicitParam(value = "开户银行", name = "bankname", required = false, paramType = "query"),
            @ApiImplicitParam(value = "税号", name = "taxregistrationcertificate", required = false, paramType = "query"),
            @ApiImplicitParam(value = "营业执照图片", name = "businesslicencenumberphoto", required = false, paramType = "query"),
            @ApiImplicitParam(value = "备注", name = "remark", required = false, paramType = "query"),
            @ApiImplicitParam(name = "service", value = "是否是服务商", required = true, paramType = "query", dataType = "boolean"),
    })
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.MEMBERMANAGEMENT + "')")
    public BasicRet editMember(@RequestParam(required = true) long id,
                               @RequestParam(required = false) Long adminid,
                               @RequestParam(required = true) String realname,
                               @RequestParam(required = true) int gradeid,
                               @RequestParam(required = false, defaultValue = "") String waysalesman,
                               @RequestParam(required = true) String mobile,
                               @RequestParam(required = false, defaultValue = "") String email,
                               @RequestParam(required = false, defaultValue = "") String faxes,
                               @RequestParam(required = false, defaultValue = "") String telephone,
                               @RequestParam(required = false, defaultValue = "") String postcode,
                               @RequestParam(required = false, defaultValue = "") String qq,
                               @RequestParam(required = false, defaultValue = "") String wxpay,
                               @RequestParam(required = false, defaultValue = "") String alipay,
                               @RequestParam(required = false, defaultValue = "") String hobby,
                               @RequestParam(required = true, defaultValue = "") String province,
                               @RequestParam(required = true, defaultValue = "") String city,
                               @RequestParam(required = true, defaultValue = "") String citysmall,
                               @RequestParam(required = true, defaultValue = "") String address,
                               @RequestParam(required = false, defaultValue = "") String clerkname,
                               @RequestParam(required = false, defaultValue = "") String shortname,
                               @RequestParam(required = false, defaultValue = "") String companyname,
                               @RequestParam(required = false, defaultValue = "") String companyaddress,
                               @RequestParam(required = false, defaultValue = "") String companyprovince,
                               @RequestParam(required = false, defaultValue = "") String companycity,
                               @RequestParam(required = false, defaultValue = "") String companycitysmall,
                               @RequestParam(required = false, defaultValue = "") String companyworktelephone,
                               @RequestParam(required = false, defaultValue = "") String companymobile,
                               @RequestParam(required = false, defaultValue = "") String methodSettingAccount,
                               @RequestParam(required = false, defaultValue = "") String bankdeposit,
                               @RequestParam(required = false, defaultValue = "") String bankuser,
                               @RequestParam(required = false, defaultValue = "") String bankaccount,
                               @RequestParam(required = false, defaultValue = "") String bankname,
                               @RequestParam(required = false, defaultValue = "") String taxregistrationcertificate,
                               @RequestParam(required = false, defaultValue = "") String businesslicencenumberphoto,
                               @RequestParam(required = false, defaultValue = "") String remark,
                               @RequestParam(required = true) boolean services,
                               Model model, HttpServletRequest request
    ) {

        Admin admin = (Admin) model.asMap().get(AppConstant.ADMIN_SESSION_NAME);

        Member member = memberService.getMemberById(id);
        if (member == null) {
            return new BasicRet(BasicRet.ERR, "用户不存在");
        }

        if (gradeid != -1) {
            MemberGrade memberGrade = memberGradeService.getById(gradeid);
            if (memberGrade == null) {
                return new BasicRet(BasicRet.ERR, "用户级别不存在");
            }
            member.setGradleid((long) gradeid);
            member.setGradename(memberGrade.getGradename());
        }

        member.setRealname(realname);
        member.setWaysalesman(waysalesman);
        member.setMobile(mobile);
        member.setEmail(email);
        member.setFaxes(faxes);
        member.setTelephone(telephone);
        member.setPostcode(postcode);
        member.setQq(qq);
        member.setWxpay(wxpay);
        member.setAlipay(alipay);
        member.setHobby(hobby);
        member.setProvince(province);
        member.setCity(city);
        member.setCitysmall(citysmall);
        member.setAddress(address);
        member.setClerkname(clerkname);
        member.setRemark(remark);
        //更新业务员
        if (adminid != null && !"".equals(adminid)) {
            Admin adminuser = adminService.getById(adminid);
            if (adminuser != null) {
                member.setClerkname(adminuser.getRealname());
            }
        }
        //  memberService.updateMemberClerknameByid(id, adminuser.getRealname());//更新业务员
        if (services == false) {
            //删除关联的服务商地区
            memberServerService.deleteServerSetByMemberId(id);
            member.setServices(services);
        } else {
            member.setServices(services);
        }

        memberService.updateByPrimaryKeySelective(member);


        //修改公司信息
        BuyerCompanyInfo buyerCompanyInfo = buyerCompanyService.getBuyerCompanyInfoByMemberId(member.getId());
        if (buyerCompanyInfo != null) {
            buyerCompanyInfo.setShortname(shortname);
            buyerCompanyInfo.setCompanyname(companyname);
            buyerCompanyInfo.setAddress(companyaddress);
            buyerCompanyInfo.setProvince(companyprovince);
            buyerCompanyInfo.setCity(companycity);
            buyerCompanyInfo.setCitysmall(companycitysmall);

            buyerCompanyInfo.setWorktelephone(companyworktelephone);
            buyerCompanyInfo.setMobile(companymobile);
            buyerCompanyInfo.setMethodsettingaccount(methodSettingAccount);
            buyerCompanyInfo.setBankdeposit(bankdeposit);
            buyerCompanyInfo.setBankuser(bankuser);
            buyerCompanyInfo.setBankaccount(bankaccount);
            buyerCompanyInfo.setBankname(bankname);
            buyerCompanyInfo.setTaxregistrationcertificate(taxregistrationcertificate);
            buyerCompanyInfo.setBusinesslicencenumberphoto(businesslicencenumberphoto);

            buyerCompanyService.updateByPrimaryKeySelective(buyerCompanyInfo);
        }
        if(clerkname==null||"".equals(clerkname)){
            adminUserService.delAdminUserByuserid(id);
        }
        //2018年5月31日10:53:04更新业务员操作

        if (adminid != null && !"".equals(adminid)) {
            //查找是否存在之前的业务员
            AdminUser result = adminUserService.getAdminUserByUserid(id);
            if (result != null) {
                adminUserService.delAdminUserByid(result.getId());//刪除之前的業務員
                AdminUser adminUser = new AdminUser();
                adminUser.setUserid(id);
                adminUser.setAdminid(adminid);
                adminUserService.addManageUsersBylist(adminUser);//添加新业务员
                // Admin adminuser = adminService.getById(clerkname);
                //memberService.updateMemberClerknameByid(id, adminuser.getRealname());//更新业务员
            } else {
                AdminUser adminUser = new AdminUser();
                adminUser.setUserid(id);
                adminUser.setAdminid(adminid);
                adminUserService.addManageUsersBylist(adminUser);//添加新业务员
                //  Admin adminuser = adminService.getById(clerkname);
                //memberService.updateMemberClerknameByid(id, adminuser.getRealname());//更新业务员
            }

        }

        //保存日志
        memberLogOperator.saveMemberLog(null, admin, "修改会员信息：" + member.getUsername(), request, memberOperateLogService);

        return new BasicRet(BasicRet.SUCCESS, "修改成功");
    }


    @PostMapping("/getMemberDetail")
    @ApiOperation("获取用户信息")
    public MemberDetailRet getMemberDetail(long id) {
        MemberDetailRet memberDetailRet = new MemberDetailRet();

        Member member = memberService.getMemberById(id);
        if (member == null) {
            memberDetailRet.setResult(BasicRet.ERR);
            memberDetailRet.setMessage("用户不存在");
            return memberDetailRet;
        }

        BuyerCompanyInfo buyerCompanyInfo = buyerCompanyService.getBuyerCompanyInfoByMemberId(id);
        member.setBuyerCompanyInfo(buyerCompanyInfo);

        memberDetailRet.member = member;

        memberDetailRet.setResult(BasicRet.SUCCESS);
        return memberDetailRet;
    }


    private class MemberDetailRet extends BasicRet {
        private Member member;

        private BigDecimal percentConversion;

        public BigDecimal getPercentConversion() {
            return percentConversion;
        }

        public void setPercentConversion(BigDecimal percentConversion) {
            this.percentConversion = percentConversion;
        }

        public Member getMember() {
            return member;
        }

        public void setMember(Member member) {
            this.member = member;
        }
    }

    @RequestMapping(value = "/getPercentConversion", method = RequestMethod.POST)
    @ApiOperation(value = "获取消费转化率")
    public MemberDetailRet getPercentConversion(MemberAdminQueryDto dto) {
        MemberDetailRet memberDetailRet = new MemberDetailRet();
        memberDetailRet.percentConversion = memberAdminService.getPercentConversion(dto);
        memberDetailRet.setMessage("返回成功");
        memberDetailRet.setResult(BasicRet.SUCCESS);
        return memberDetailRet;
    }


    @RequestMapping(value = "/selectmemberinfo", method = RequestMethod.POST)
    @ApiOperation(value = "后台会员根据条件查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "clerkname", value = "业务员", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "id", value = "编号", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "mobile", value = "手机", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "registDateStart", value = "开始时间", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "registDateEnd", value = "结束时间", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "labelid", value = "标签id", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "gradleid", value = "组别id", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "loginDateStart", value = "最后登录时间-开始", required = false, paramType = "query", dataType = "date"),
            @ApiImplicitParam(name = "loginDateEnd", value = "最后登录时间-结束", required = false, paramType = "query", dataType = "date"),
            @ApiImplicitParam(name = "companyType", value = "是否是公司 0-全部，1-是，2-否", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "isbuy", value = "是否消费0=全部1=无消费2=消费", required = false, paramType = "query", dataType = "int"),
    })
    public SelectMemberInfoRet selectmemberinfo(MemberAdminQueryDto dto,
                                    @RequestParam(required = true) int pageNo,
                                    @RequestParam(required = true) int pageSize,Model model) {
        Admin admin = (Admin) model.asMap().get(AppConstant.ADMIN_SESSION_NAME);
        PageInfo pageInfo = memberAdminService.list(dto, pageNo, pageSize);
       List<AdminUser> list= adminUserService.findAdminUserByAdminid(admin.getId());
        SelectMemberInfoRet selectMemberInfoRet=new SelectMemberInfoRet();
        selectMemberInfoRet.data.setPageInfo(pageInfo);
        selectMemberInfoRet.data.setAdminUserList(list);
        selectMemberInfoRet.setResult(BasicRet.SUCCESS);
        return selectMemberInfoRet;
    }


    private class SelectMemberInfoRet extends BasicRet{
        private class SelectMemberInfoData{
            private  PageInfo pageInfo;
            private  List<AdminUser> adminUserList;
            public PageInfo getPageInfo() {
                return pageInfo;
            }

            public void setPageInfo(PageInfo pageInfo) {
                this.pageInfo = pageInfo;
            }

            public List<AdminUser> getAdminUserList() {
                return adminUserList;
            }

            public void setAdminUserList(List<AdminUser> adminUserList) {
                this.adminUserList = adminUserList;
            }
        }
        public   SelectMemberInfoData data = new SelectMemberInfoData();

        public SelectMemberInfoData getData() {
            return data;
        }

        public void setData(SelectMemberInfoData data) {
            this.data = data;
        }
    }


    @RequestMapping(value = "/excelExport/memberinfo", method = RequestMethod.GET)
    @ApiOperation(value = "Excel导出后台会员")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "clerkname", value = "业务员", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "id", value = "编号", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "mobile", value = "手机", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "registDateStart", value = "开始时间", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "registDateEnd", value = "结束时间", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "labelid", value = "标签id", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "gradleid", value = "组别id", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "loginDateStart", value = "最后登录时间-开始", required = false, paramType = "query", dataType = "date"),
            @ApiImplicitParam(name = "loginDateEnd", value = "最后登录时间-结束", required = false, paramType = "query", dataType = "date"),
            @ApiImplicitParam(name = "companyType", value = "是否是公司 0-全部，1-是，2-否", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "isbuy", value = "是否消费0=全部1=无消费2=消费", required = false, paramType = "query", dataType = "int")
    })
    public ResponseEntity<InputStreamResource> exportExcelmemberinfo(MemberAdminQueryDto dto) {


        List<Map<String, Object>> resList = memberAdminService.listForExportExcel(dto);


        String[] rowTitles = new String[]{"会员编号", "会员全称", "会员类型", "真实姓名", "会员等级", "注册时间", "介绍人",
                "会员账号", "手机号码", "邮箱", "传真", "固定电话", "邮编", "邀请码", "QQ", "微信", "支付宝",
                "爱好", "联系地址", "授信额度", "结算日（每月）", "业务员", "是否卖家", "单位简称", "单位全称",
                "单位编号", "所在地", "详细地址", "单位电话", "联系手机", "结算方式", "开户银行", "开户行",
                "银行账号", "纳税号"};

        XSSFWorkbook workbook = null;
        try {
            workbook = ExcelGen.common("会员管理列表", rowTitles, resList, null);
            if (workbook != null) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                workbook.write(baos);
                System.out.println(baos.toByteArray().length);
                HttpHeaders headers = new HttpHeaders();
                headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
                headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", new String("会员管理列表.xlsx".getBytes(), "iso-8859-1")));
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
        return null;
    }


    @RequestMapping(value = "/updateMemberPassword", method = RequestMethod.POST)
    @ApiOperation(value = "后台会员重置密码")
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.MEMBERMANAGEMENT + "')")
    public BasicRet updateMemberPassword(long id, Model model, HttpServletRequest request) {
        Admin admin = (Admin) model.asMap().get(AppConstant.ADMIN_SESSION_NAME);

        BasicRet basicRet = new BasicRet();
        String password = "123456";
        Member member = memberService.getMemberById(id);

        if (member == null) {
            basicRet.setMessage("用户不存在");
            basicRet.setResult(BasicRet.ERR);
            return basicRet;
        }

        memberService.updateMemberPassword(id, password, member.getPasswordsalt());

        //保存日志
        memberLogOperator.saveMemberLog(null, admin, "后台会员重置密码：" + member.getUsername(), request, memberOperateLogService);

        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("重置成功，初始密码为'123456'");
        return basicRet;
    }


    @RequestMapping(value = "/updateMemberState", method = RequestMethod.POST)
    @ApiOperation(value = "后台会员停用、启用")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "会员id", name = "id", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(value = "状态 1=禁用，2=启用", name = "state", required = true, paramType = "query", dataType = "int"),
    })
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.MEMBERMANAGEMENT + "')")
    public BasicRet updateMemberState(@RequestParam(required = true) long id, @RequestParam(required = true) short state, Model model, HttpServletRequest request) {
        Admin admin = (Admin) model.asMap().get(AppConstant.ADMIN_SESSION_NAME);
        BasicRet basicRet = new BasicRet();
        Member member = memberService.getMemberById(id);

        if (member == null) {
            basicRet.setMessage("用户不存在");
            basicRet.setResult(BasicRet.ERR);
            return basicRet;
        }


        if (state != Quantity.STATE_2 && state != Quantity.STATE_1) {
            basicRet.setMessage("状态参数不正确");
            basicRet.setResult(BasicRet.ERR);
            return basicRet;
        }


        Member updateMember = new Member();
        updateMember.setId(member.getId());
        updateMember.setDisabled(state == Quantity.STATE_2 ? false : true);
        memberService.updateByPrimaryKeySelective(updateMember);


        String tag = "启用";
        if (state == 1) {
            tag = "禁用";
        }
        //保存日志
        memberLogOperator.saveMemberLog(null, admin, "后台会员" + member.getUsername() + tag, request, memberOperateLogService);


        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("修改成功");
        return basicRet;
    }


    @PostMapping("/setLebel")
    @ApiOperation(value = "编辑标签")
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.MEMBERMANAGEMENT + "')")
    public BasicRet setLebel(@RequestParam(required = true) long id,
                             @RequestParam(required = true) String labelid,
                             @RequestParam(required = false, defaultValue = "") String clerkname) {

        if (StringUtils.hasText(labelid) && !labelid.endsWith(",")) {
            labelid += ",";
        }

        Member member = new Member();
        member.setId(id);
        member.setLabelid(labelid);
        member.setClerkname(clerkname);
        memberService.updateByPrimaryKeySelective(member);
        return new BasicRet(BasicRet.SUCCESS, "设置成功");
    }


    @RequestMapping(value = "/cleanPaypassword", method = RequestMethod.POST)
    @ApiOperation(value = "清除会员支付密码")
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.MEMBERMANAGEMENT + "')")
    public BasicRet cleanPaypassword(long id, Model model, HttpServletRequest request) {

        Admin admin = (Admin) model.asMap().get(AppConstant.ADMIN_SESSION_NAME);

        Member member = memberService.getMemberById(id);

        BasicRet basicRet = new BasicRet();
        memberService.cleanPaypassword(id, "");

        //保存日志
        memberLogOperator.saveMemberLog(null, admin, "清除会员支付密码:" + member.getUsername(), request, memberOperateLogService);

        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("会员支付密码清除成功");
        return basicRet;
    }


    @RequestMapping(value = "/applyForCompany", method = RequestMethod.POST)
    @ApiOperation(value = "个人帐号申请成为公司帐号")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "memberid", value = "买家ID", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "shortname", value = "单位简称", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "companyname", value = "单位全称", required = true, paramType = "query", dataType = "string"),
//            @ApiImplicitParam(name = "companyno", value = "单位编号", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "province", value = "省", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "city", value = "市", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "citysmall", value = "区", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "address", value = "详细地址", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "legalperson", value = "法人代表", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "mobile", value = "联系手机", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "worktelephone", value = "单位电话", required = true, paramType = "query", dataType = "string"),
//            @ApiImplicitParam(name = "postaladdress", value = "通讯地址", required = true, paramType = "query", dataType = "string"),
//            @ApiImplicitParam(name = "zipcode", value = "邮编", required = false, paramType = "query", dataType = "string"),


            @ApiImplicitParam(name = "bankdeposit", value = "开户行", required = true, paramType = "query", dataType = "string"), //新加
            @ApiImplicitParam(name = "bankuser", value = "开户人", required = true, paramType = "query", dataType = "string"),//新加


            @ApiImplicitParam(name = "bankname", value = "开户银行", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "bankaccount", value = "银行帐号", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "taxregistrationcertificate", value = "纳税号", required = true, paramType = "query", dataType = "string"),

//            @ApiImplicitParam(name = "invoicetype", value = "发票类型", required = false, paramType = "query", dataType = "string"),

            @ApiImplicitParam(name = "methodsettingaccount", value = "结算方式", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "businesslicencenumberphoto", value = "营业执照", required = true, paramType = "query", dataType = "string"),
    })
    public BasicRet applyForCompany(BuyerCompanyInfo info) {
        BasicRet basicRet = new BasicRet();

        BuyerCompanyInfo buyerCompanyInfo = buyerCompanyService.getBuyerCompanyInfoByMemberId(info.getMemberid());
        if (buyerCompanyInfo != null) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("该账户已经为公司帐号了");
            return basicRet;
        }

        buyerCompanyService.applyForCompany(info);
        basicRet.setMessage("升级为公司帐号");
        basicRet.setResult(BasicRet.SUCCESS);
        return basicRet;
    }

    /**
     * 添加沒有添加管理的会员
     *
     * @param id
     * @param companyname
     * @param realname
     * @param mobile
     * @return
     */

    @RequestMapping(value = "/findNotAddMembers", method = RequestMethod.POST)
    @ApiOperation(value = "查询未添加的管理会员列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "编号", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "companyname", value = "公司名称", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "username", value = "个人姓名", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "mobile", value = "联系手机", required = false, paramType = "query", dataType = "string"),
    })
    public PageRet findNotAddMembers(@RequestParam(required = false, defaultValue = "") Long id,
                                     @RequestParam(required = false, defaultValue = "") String companyname,
                                     @RequestParam(required = false, defaultValue = "") String realname,
                                     @RequestParam(required = false, defaultValue = "") String mobile,
                                     @RequestParam(required = true) int pageNo,
                                     @RequestParam(required = true) int pageSize) {
        PageRet pageRet = new PageRet();
        PageInfo pageInfo = memberAdminService.findNotAddMembers(id, companyname, realname, mobile, pageNo, pageSize);
        pageRet.data.setPageInfo(pageInfo);
        pageRet.setResult(BasicRet.SUCCESS);
        return pageRet;
    }

    @RequestMapping(value = "/findManageMemberList", method = RequestMethod.POST)
    @ApiOperation(value = "查询已添加的管理会员列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "编号", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "companyname", value = "公司名称", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "username", value = "个人姓名", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "mobile", value = "联系手机", required = false, paramType = "query", dataType = "string"),
    })
    public PageRet findManageMemberList(@RequestParam(required = true, defaultValue = "") Long adminid,
                                        @RequestParam(required = false, defaultValue = "") Long id,
                                        @RequestParam(required = false, defaultValue = "") String companyname,
                                        @RequestParam(required = false, defaultValue = "") String realname,
                                        @RequestParam(required = false, defaultValue = "") String mobile, Model model,
                                        @RequestParam(required = true) int pageNo,
                                        @RequestParam(required = true) int pageSize) {
        PageRet pageRet = new PageRet();
        PageInfo pageInfo = memberAdminService.findManageMemberList(id, companyname, realname, mobile, adminid, pageNo, pageSize);
        pageRet.data.setPageInfo(pageInfo);
        pageRet.setResult(BasicRet.SUCCESS);
        return pageRet;
    }

    @RequestMapping(value = "/addManageUsersBylist", method = RequestMethod.POST)
    @ApiOperation(value = "添加要管理的会员列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "array", value = "编号数组", required = true, paramType = "query", dataType = "Long")
    })
    public MemberAdminViewDtoRet addManageUsersBylist(@RequestParam(required = true, defaultValue = "") Long adminid,
                                                      @RequestParam(required = false) Long[] userid, Model model) {
        MemberAdminViewDtoRet memberAdminViewDtoRet = new MemberAdminViewDtoRet();
        Admin admin = adminService.getById(adminid);
        for (Long id : userid) {
            AdminUser result = adminUserService.getAdminUserByAdminAndUserid(admin.getId(), id);
            if (result == null) {
                AdminUser adminUser = new AdminUser();
                adminUser.setAdminid(admin.getId());
                adminUser.setUserid(id);
                adminUserService.addManageUsersBylist(adminUser);
                memberService.updateMemberClerknameByid(id, admin.getRealname());
            }
        }
        memberAdminViewDtoRet.setMessage("操作成功");
        memberAdminViewDtoRet.setResult(BasicRet.SUCCESS);
        return memberAdminViewDtoRet;
    }

    @RequestMapping(value = "/delManageUsersByUserid", method = RequestMethod.POST)
    @ApiOperation(value = "添加要管理的会员列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userid", value = "会员id", required = true, paramType = "query", dataType = "Long")
    })
    public MemberAdminViewDtoRet delManageUsersByid(@RequestParam(required = true, defaultValue = "") Long adminid,
                                                    @RequestParam(required = true) Long userid, Model model) {
        MemberAdminViewDtoRet memberAdminViewDtoRet = new MemberAdminViewDtoRet();
        AdminUser result = adminUserService.getAdminUserByAdminAndUserid(adminid, userid);
        if (result != null) {
            Member member=memberService.getMemberById(result.getUserid());
            if (member!=null){
                member.setClerkname("");
                memberService.updateByPrimaryKeySelective(member);
            }
            adminUserService.delAdminUserByid(result.getId());
        }
        memberAdminViewDtoRet.setMessage("操作成功");
        memberAdminViewDtoRet.setResult(BasicRet.SUCCESS);
        return memberAdminViewDtoRet;
    }

    @RequestMapping(value = "/findAdminList", method = RequestMethod.POST)
    @ApiOperation(value = "查找所有的业务员")
    @ApiImplicitParams({})
    public MemberAdminViewDtoRet findAdminList() {
        MemberAdminViewDtoRet memberAdminViewDtoRet = new MemberAdminViewDtoRet();
        List<Admin> list = adminService.findAdminList();
        memberAdminViewDtoRet.setList(list);
        memberAdminViewDtoRet.setMessage("操作成功");
        memberAdminViewDtoRet.setResult(BasicRet.SUCCESS);
        return memberAdminViewDtoRet;
    }

    public class MemberAdminViewDtoRet extends BasicRet {
        private List<Admin> list;

        public List<Admin> getList() {
            return list;
        }

        public void setList(List<Admin> list) {
            this.list = list;
        }
    }


}
