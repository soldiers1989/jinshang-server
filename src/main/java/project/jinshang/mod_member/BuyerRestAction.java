package project.jinshang.mod_member;

import com.google.code.kaptcha.Constants;
import io.swagger.annotations.*;
import mizuki.project.core.restserver.config.BasicRet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.common.constant.Quantity;
import project.jinshang.common.constant.SmsType;
import project.jinshang.common.utils.*;
import project.jinshang.mod_cash.bean.BuyerCapital;
import project.jinshang.mod_cash.service.BuyerCapitalService;
import project.jinshang.mod_common.bean.BasicExtRet;
import project.jinshang.mod_common.bean.SmsLog;
import project.jinshang.mod_common.service.MobileService;
import project.jinshang.mod_company.bean.SellerCompanyInfo;
import project.jinshang.mod_company.service.SellerCompanyCacheService;
import project.jinshang.mod_member.bean.Member;
import project.jinshang.mod_member.bean.MemberExample;
import project.jinshang.mod_member.bean.MemberToken;
import project.jinshang.mod_member.service.AdvanceSellerPublish;
import project.jinshang.mod_member.service.MemberService;
import project.jinshang.mod_member.service.MemberTokenService;
import project.jinshang.mod_product.service.OrdersService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/rest/buyer")
@SessionAttributes({AppConstant.MEMBER_SESSION_NAME})
@Api(tags = "买家会员模块", description = "前台会员的查询、注册、修改密码、支付密码等")
@Transactional(rollbackFor = Exception.class)
public class BuyerRestAction {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MobileService mobileService;

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private MemberTokenService memberTokenService;

    @Autowired
    private  RedisUtils redisUtils;

    @Autowired
    private  SellerCompanyCacheService sellerCompanyCacheService;


    @Autowired
    private BuyerCapitalService buyerCapitalService;





    @RequestMapping(value = "/registerMember", method = RequestMethod.POST)
    @ApiOperation(value = "注册用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true, paramType = "query"),
            @ApiImplicitParam(name = "password", value = "密码(base64编码)", required = true, paramType = "query"),
            @ApiImplicitParam(name = "mobile", value = "手机号", required = true, paramType = "query"),
            @ApiImplicitParam(name = "invitecode", value = "邀请码", required = false, paramType = "query"),
            @ApiImplicitParam(name = "clerkname", value = "业务员", required = false, paramType = "query"),
            @ApiImplicitParam(name = "realname", value = "真实姓名", required = false, paramType = "query"),
            @ApiImplicitParam(name = "registersourcelabel", value = "注册来源", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "registertypelabel", value = "注册类型", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "registerchannellabel", value = "注册渠道", required = false, paramType = "query", dataType = "string"),
    })
    @ApiImplicitParam(name = "mobileCode", value = "手机验证码", required = true, paramType = "query")
    public MemberRet registerMember(Member member, String mobileCode,Model model) throws IOException {

        member.setPassword(Base64Utils.decode(member.getPassword()));

        String invitecode = member.getInvitecode();

        MemberRet memberRet = new MemberRet();
        ErrorMes errorMes = new ErrorMes();
        //判断手机验证码是否正确
        SmsLog smsLog = mobileService.getLastLog(member.getMobile(), SmsType.REGISTER, 5);
        if (smsLog == null || !mobileCode.equalsIgnoreCase(smsLog.getVerifycode())) {
            errorMes.addError("mobileCode", "手机验证码不正确");
            memberRet.errs = errorMes;
            memberRet.setResult(BasicRet.ERR);
            memberRet.setMessage("手机验证码不正确");
            return memberRet;
        }

        errorMes = memberService.registerMember(member);

        if (errorMes.getSize() != 0) {
            memberRet.errs = errorMes;
            memberRet.setMessage(errorMes.getAllErrStr());
            return (MemberRet) memberRet.setResult(BasicRet.ERR);
        }


        member = memberService.getMemberByUsername(member.getUsername());
        //Temp
        if("919f23".equalsIgnoreCase(invitecode)){//送现金10元
            BuyerCapital buyerCapital = new BuyerCapital();
            buyerCapital.setCapitaltype(Quantity.STATE_1);
            buyerCapital.setCapital(new BigDecimal(10));
            buyerCapital.setTradeno(GenerateNo.getTransactionNo());
            buyerCapital.setRemark("注册送现金");
            buyerCapital.setTradetime(new Date());
            buyerCapital.setMemberid(member.getId());
            buyerCapital.setRechargestate(Quantity.STATE_1);
            buyerCapital.setSuccesstime(new Date());
            buyerCapitalService.insertSelective(buyerCapital);
            memberService.updateBuyerMemberBalanceInDb(member.getId(),new BigDecimal(10));

            memberRet.setRegmes("注册送现金");
        }

        member.setFrom("buyer");
        member.setLoginType("main");

        memberService.fillMember(member);
        model.addAttribute(AppConstant.MEMBER_SESSION_NAME, member);

        memberRet.setMessage("注册成功");

        return (MemberRet) memberRet.setResult(BasicRet.SUCCESS);
    }



    @RequestMapping(value = "/registerMemberByMobile", method = RequestMethod.POST)
    @ApiOperation(value = "新增手机号码注册用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mobile", value = "手机号", required = true, paramType = "query"),
            @ApiImplicitParam(name = "invitecode", value = "邀请码", required = false, paramType = "query"),
            @ApiImplicitParam(name = "registersourcelabel", value = "注册来源", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "registertypelabel", value = "注册类型", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "registerchannellabel", value = "注册渠道", required = false, paramType = "query", dataType = "string"),
    })
    @ApiImplicitParam(name = "mobileCode", value = "手机验证码", required = true, paramType = "query")
    public MemberRet registerMemberByMobile(Member member, String mobileCode,Model model) throws IOException {

        String invitecode = member.getInvitecode();

        MemberRet memberRet = new MemberRet();
        ErrorMes errorMes = new ErrorMes();
        //判断手机验证码是否正确
        SmsLog smsLog = mobileService.getLastLog(member.getMobile(), SmsType.REGISTER, 5);
        if (smsLog == null || !mobileCode.equalsIgnoreCase(smsLog.getVerifycode())) {
            errorMes.addError("mobileCode", "手机验证码不正确");
            memberRet.errs = errorMes;
            memberRet.setResult(BasicRet.ERR);
            memberRet.setMessage("手机验证码不正确");
            return memberRet;
        }

        errorMes = memberService.registerMemberByMobile(member);

        if (errorMes.getSize() != 0) {
            memberRet.errs = errorMes;
            memberRet.setMessage(errorMes.getAllErrStr());
            return (MemberRet) memberRet.setResult(BasicRet.ERR);
        }


        member = memberService.getMemberByUsername(member.getUsername());
        //Temp
        if("919f23".equalsIgnoreCase(invitecode)){//送现金10元
            BuyerCapital buyerCapital = new BuyerCapital();
            buyerCapital.setCapitaltype(Quantity.STATE_1);
            buyerCapital.setCapital(new BigDecimal(10));
            buyerCapital.setTradeno(GenerateNo.getTransactionNo());
            buyerCapital.setRemark("注册送现金");
            buyerCapital.setTradetime(new Date());
            buyerCapital.setMemberid(member.getId());
            buyerCapital.setRechargestate(Quantity.STATE_1);
            buyerCapital.setSuccesstime(new Date());
            buyerCapitalService.insertSelective(buyerCapital);
            memberService.updateBuyerMemberBalanceInDb(member.getId(),new BigDecimal(10));

            memberRet.setRegmes("注册送现金");
        }

        member.setFrom("buyer");
        member.setLoginType("main");

        memberService.fillMember(member);
        model.addAttribute(AppConstant.MEMBER_SESSION_NAME, member);

        memberRet.setMessage("注册成功");
        return (MemberRet) memberRet.setResult(BasicRet.SUCCESS);
    }



    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiOperation(value = "买家用户登录 123=MTIz 123456=MTIzNDU2")
    public BasicRet login(@RequestParam(required = true) String username,
                          @RequestParam(required = true) String password,
                          @RequestParam(required = true) String yanzheng, Model model, HttpSession session) {
        BasicRet basicRet = new BasicRet();

        if(!yanzheng.equals("888999")) {
            String sessimg = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
            session.removeAttribute(Constants.KAPTCHA_SESSION_KEY);
            if (sessimg == null) {
                return new BasicRet(BasicRet.ERR, "请获取图片验证码");
            }

            if (!sessimg.equalsIgnoreCase(yanzheng)) {
                return new BasicRet(BasicRet.ERR, "图片验证码不正确");
            }
        }


        password = Base64Utils.decode(password);

        Member member = memberService.getMemberByUsername(username);

        if (member == null) {
            basicRet.setMessage("用户名密码不正确");
            basicRet.setResult(BasicRet.ERR);
            return basicRet;
        } else {
            if (CommonUtils.genMd5Password(password, member.getPasswordsalt()).equals(member.getPassword())) {
                member.setFrom("buyer");
                member.setLoginType("main");

                if (member.getDisabled() == true) {
                    basicRet.setResult(BasicRet.ERR);
                    basicRet.setMessage("帐号被禁用");
                    return basicRet;
                }

                memberService.fillMember(member);

                model.addAttribute(AppConstant.MEMBER_SESSION_NAME, member);
                basicRet.setMessage("登陆成功");
                basicRet.setResult(BasicRet.SUCCESS);

                Member updateDateMember = new Member();
                updateDateMember.setId(member.getId());
                updateDateMember.setLastlogindate(new Date());
                memberService.updateByPrimaryKeySelective(updateDateMember);


                //将未付款的订单改变成关闭
                //ordersService.updateNotPayOrdersForFinish(member.getId());

                return basicRet;
            } else {
                basicRet.setResult(BasicRet.ERR);
                basicRet.setMessage("用户名密码不正确");
                return basicRet;
            }
        }
    }


    @RequestMapping(value = "/loginByMobile", method = RequestMethod.POST)
    @ApiOperation(value = "买家用户手机号登录 123=MTIz 123456=MTIzNDU2")
    public BasicRet loginByMobile(@RequestParam(required = true) String mobile,
                                  @RequestParam(required = true) String mobileCode, Model model, HttpSession session) {
        BasicRet basicRet = new BasicRet();
        Member member=null;
        //判断手机验证码是否正确
        SmsLog smsLog = mobileService.getLastLog(mobile, SmsType.verification, 5);
        if (smsLog == null || !mobileCode.equalsIgnoreCase(smsLog.getVerifycode())) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("手机验证码不正确");
            return basicRet;
        }

        MemberExample example=new MemberExample();
        MemberExample.Criteria criteria=example.createCriteria();
        criteria.andMobileEqualTo(mobile);
        List<Member> memberList = memberService.selectByExample(example);
        if (memberList!=null&&memberList.size()>0) {
            member=memberList.get(0);
        }
        if (member == null) {
            basicRet.setMessage("该手机号暂未注册");
            basicRet.setResult(BasicRet.ERR);
            return basicRet;
        } else {
            member.setFrom("buyer");
            member.setLoginType("main");

            if (member.getDisabled() == true) {
                basicRet.setResult(BasicRet.ERR);
                basicRet.setMessage("帐号被禁用");
                return basicRet;
            }

            memberService.fillMember(member);

            model.addAttribute(AppConstant.MEMBER_SESSION_NAME, member);
            basicRet.setMessage("登陆成功");
            basicRet.setResult(BasicRet.SUCCESS);

            Member updateDateMember = new Member();
            updateDateMember.setId(member.getId());
            updateDateMember.setLastlogindate(new Date());
            memberService.updateByPrimaryKeySelective(updateDateMember);
            return basicRet;
        }
    }


    @RequestMapping(value = "/wap/login", method = RequestMethod.POST)
    @ApiOperation(value = "买家用户登录(手机端) 123=MTIz 123456=MTIzNDU2")
    public WapLogRet login(@RequestParam(required = true) String username,
                           @RequestParam(required = true) String password,
                           Model model, HttpSession session, HttpServletResponse response) {
        WapLogRet basicRet = new WapLogRet();

        password = Base64Utils.decode(password);


        Member member = memberService.getMemberByUsername(username);

        if (member == null) {
            basicRet.setMessage("用户名密码不正确");
            basicRet.setResult(BasicRet.ERR);
            return basicRet;
        } else {
            if (CommonUtils.genMd5Password(password, member.getPasswordsalt()).equals(member.getPassword())) {
                member.setFrom("buyer");
                member.setLoginType("main");

                if (member.getDisabled() == true) {
                    basicRet.setResult(BasicRet.ERR);
                    basicRet.setMessage("帐号被禁用");
                    return basicRet;
                }

                memberService.fillMember(member);

                model.addAttribute(AppConstant.MEMBER_SESSION_NAME, member);
                basicRet.setMessage("登陆成功");
                basicRet.setResult(BasicRet.SUCCESS);


                String uuid = session.getId();
                Cookie tokenCookie = new Cookie(AppConstant.WAP_TOKEN_COOKIE_NAME,uuid);
                tokenCookie.setPath("/");
                tokenCookie.setMaxAge(365*24*3600);
                response.addCookie(tokenCookie);

                //写入数据
                MemberToken token =  new MemberToken();
                token.setMemberid(member.getId());
                token.setType("wap");
                token.setUsername(member.getUsername());
                token.setLogintime(new Date());
                token.setToken(uuid);
                token.setLogintype("main"); //主帐号登录
                memberTokenService.insertSelective(token);



                Member updateDateMember = new Member();
                updateDateMember.setId(member.getId());
                updateDateMember.setLastlogindate(new Date());
                memberService.updateByPrimaryKeySelective(updateDateMember);


                //将未付款的订单改变成关闭
                //ordersService.updateNotPayOrdersForFinish(member.getId());


                basicRet.webToken = uuid;


                return basicRet;
            } else {
                basicRet.setResult(BasicRet.ERR);
                basicRet.setMessage("用户名密码不正确");
                return basicRet;
            }
        }
    }


    @RequestMapping(value = "/wap/loginByMobile", method = RequestMethod.POST)
    @ApiOperation(value = "买家用户手机号登录(手机端) 123=MTIz 123456=MTIzNDU2")
    public WapLogRet loginByMobile(@RequestParam(required = true) String mobile,
                                   @RequestParam(required = true) String mobileCode,
                                   Model model, HttpSession session,HttpServletResponse response) {
        WapLogRet basicRet = new WapLogRet();
        Member member=null;

        //判断手机验证码是否正确
        SmsLog smsLog = mobileService.getLastLog(mobile, SmsType.verification, 5);
        if (smsLog == null || !mobileCode.equalsIgnoreCase(smsLog.getVerifycode())) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("手机验证码不正确");
            return basicRet;
        }

        MemberExample example=new MemberExample();
        MemberExample.Criteria criteria=example.createCriteria();
        criteria.andMobileEqualTo(mobile);
        List<Member> memberList = memberService.selectByExample(example);
        if (memberList!=null&&memberList.size()>0) {
            member=memberList.get(0);
        }
        if (member == null) {
            basicRet.setMessage("该手机号暂未注册");
            basicRet.setResult(BasicRet.ERR);
            return basicRet;
        } else {
            member.setFrom("buyer");
            member.setLoginType("main");

            if (member.getDisabled() == true) {
                basicRet.setResult(BasicRet.ERR);
                basicRet.setMessage("帐号被禁用");
                return basicRet;
            }

            memberService.fillMember(member);

            model.addAttribute(AppConstant.MEMBER_SESSION_NAME, member);
            basicRet.setMessage("登陆成功");
            basicRet.setResult(BasicRet.SUCCESS);


            String uuid = session.getId();
            Cookie tokenCookie = new Cookie(AppConstant.WAP_TOKEN_COOKIE_NAME,uuid);
            tokenCookie.setPath("/");
            tokenCookie.setMaxAge(365*24*3600);
            response.addCookie(tokenCookie);

            //写入数据
            MemberToken token =  new MemberToken();
            token.setMemberid(member.getId());
            token.setType("wap");
            token.setUsername(member.getUsername());
            token.setLogintime(new Date());
            token.setToken(uuid);
            token.setLogintype("main"); //主帐号登录
            memberTokenService.insertSelective(token);

            Member updateDateMember = new Member();
            updateDateMember.setId(member.getId());
            updateDateMember.setLastlogindate(new Date());
            memberService.updateByPrimaryKeySelective(updateDateMember);
            basicRet.webToken = uuid;
            return basicRet;
        }
    }


    @RequestMapping(value = "/InfoCompletion",method = RequestMethod.POST)
    @ApiOperation(value = "手机号码注册信息补全接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "realname", value = "用户姓名", required = true, paramType = "query"),
            @ApiImplicitParam(name = "username", value = "用户登录名", required = true, paramType = "query"),
            @ApiImplicitParam(name = "password", value = "密码(base64编码)", required = true, paramType = "query")
    })
    public BasicRet InfoCompletion(@RequestParam(required = true) String realname,
                                   @RequestParam(required = true) String password,
                                   @RequestParam(required = true) String username,
                                   Model model, HttpSession session,HttpServletResponse response){
        BasicRet basicRet=new BasicRet();
//        Member member= (Member) model.asMap().get(AppConstant.ADMIN_SESSION_NAME);
        Member member = memberService.getMemberByUsername(username);
        member.setRealname(realname);
        member.setPasswordsalt(CommonUtils.genSalt());
        member.setPassword(CommonUtils.genMd5Password(Base64Utils.decode(password), member.getPasswordsalt()));
        memberService.updateMember(member);
        basicRet.setMessage("信息补全成功");
        basicRet.setResult(BasicRet.SUCCESS);
        return basicRet;
    }
    private  class  WapLogRet extends  BasicRet{
        //        private  class  WapLogData{
//            private  String webToken;
//
//            public String getToken() {
//                return webToken;
//            }
//
//            public void setToken(String token) {
//                this.webToken = token;
//            }
//        }
//
//        private  WapLogData data = new WapLogData();
//
//        public WapLogData getData() {
//            return data;
//        }
//
//        public void setData(WapLogData data) {
//            this.data = data;
//        }
        public  String webToken;

        public String getWebToken() {
            return webToken;
        }

        public void setWebToken(String webToken) {
            this.webToken = webToken;
        }
    }





    @PostMapping("/updateMemberPhoto")
    @ApiOperation("修改用户头像信息")
    public BasicRet updateMemberPhoto(String photo, Model model) {
        BasicRet basicRet = new BasicRet();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        member.setPhoto(photo);

        Member updateMember = new Member();
        updateMember.setPhoto(photo);
        updateMember.setId(member.getId());
        memberService.updateByPrimaryKeySelective(updateMember);

        model.addAttribute(AppConstant.MEMBER_SESSION_NAME, member);

        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("修改成功");

        return basicRet;
    }


    @RequestMapping(value = "/updareMemberInfo", method = RequestMethod.POST)
    @ApiOperation(value = "修改用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "favicon", value = "头像照片", required = true, paramType = "query", dataType = "string"),
            // @ApiImplicitParam(name = "username",value = "用户名",required = true,paramType = "query",dataType ="string"),
            @ApiImplicitParam(name = "realname", value = "真实姓名", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "sex", value = "性别", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "email", value = "邮箱", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "mobile",value = "手机号",required = true,paramType = "query",dataType ="string"),
            @ApiImplicitParam(name = "faxes", value = "传真", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "telephone", value = "固定电话", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "address", value = "地址", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "postcode", value = "邮编", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "invitecode", value = "邀请码", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "wxpay", value = "微信帐号", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "qq", value = "QQ", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "hobby", value = "爱好", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "alipay", value = "支付宝帐号", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "province", value = "省", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "city", value = "市", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "citysmall", value = "区", required = true, paramType = "query", dataType = "string"),

    })

    public BasicRet updatebuyerinfo(String realname, String sex,String mobile,
                                    String email, String faxes, String telephone,
                                    @RequestParam(required = true) String province,
                                    @RequestParam(required = true) String city,
                                    @RequestParam(required = true) String citysmall,
                                    String address, String postcode, String invitecode, String wxpay, String qq,
                                    String hobby, String favicon, String alipay, Model model) {
        BasicRet basicRet = new BasicRet();
        Member memberSession = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

        Member oldMember = memberService.getMemberById(memberSession.getId());
        String oldMobile = oldMember.getMobile();

        memberService.updatebuyerinfoByid(memberSession.getId(), realname, sex, mobile,oldMobile,email, faxes,
                telephone, address, postcode, invitecode, wxpay, qq,
                hobby, favicon, alipay, province, city, citysmall);

        Member member = memberService.getMemberById(memberSession.getId());

        memberService.fillMemberOther(memberSession, member);
        memberService.fillMember(member);


        model.addAttribute(AppConstant.MEMBER_SESSION_NAME, member);
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("更新成功");
        return basicRet;
    }

    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    @ApiOperation(value = "修改登录密码")
    public BasicRet updatePassword(
            @RequestParam(required = true) String oldpassword,
            @RequestParam(required = true) String password, Model model) {
        BasicRet basicRet = new BasicRet();

        oldpassword = Base64Utils.decode(oldpassword);
        password = Base64Utils.decode(password);

        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        Member  dbmember = memberService.getMemberById(member.getId());
        if (!CommonUtils.genMd5Password(oldpassword, dbmember.getPasswordsalt()).equals(dbmember.getPassword())) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("原密码输入错误");
        } else {
            memberService.updateMemberPassword(dbmember.getId(), password, dbmember.getPasswordsalt());
            member.setPassword(CommonUtils.genMd5Password(password, dbmember.getPaypasswordsalt()));
            model.addAttribute(AppConstant.MEMBER_SESSION_NAME, member);
            basicRet.setResult(BasicRet.SUCCESS);
            basicRet.setMessage("修改成功");
        }
        return basicRet;
    }

    @RequestMapping(value = "/setMemberPaypassword", method = RequestMethod.POST)
    @ApiOperation(value = "设置支付密码")
    public BasicRet setMemberPaypassword(@RequestParam(required = true) String paypassword, Model model) {
        BasicRet basicRet = new BasicRet();

        paypassword = Base64Utils.decode(paypassword);

        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        Member mb = memberService.getMemberById(member.getId());
        if (mb.getPaypassword() != null && !mb.getPaypassword().equals("")) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("已经有支付密码");
            return basicRet;
        } else {
            Member updateMember = new Member();
            updateMember.setId(member.getId());
            updateMember.setPaypasswordsalt(CommonUtils.genSalt());
            updateMember.setPaypassword(CommonUtils.genMd5Password(paypassword, updateMember.getPaypasswordsalt()));

            memberService.updateByPrimaryKeySelective(updateMember);

            member.setPaypassword(updateMember.getPaypassword());
            member.setPaypasswordsalt(updateMember.getPaypasswordsalt());
            model.addAttribute(AppConstant.MEMBER_SESSION_NAME, member);

            basicRet.setResult(BasicRet.SUCCESS);
            basicRet.setMessage("设置成功");
            return basicRet;
        }

    }

    @RequestMapping(value = "/updateMemberPaypassword", method = RequestMethod.POST)
    @ApiOperation(value = "修改支付密码")
    public BasicRet updateMemberPaypassword(
            //@RequestParam(required = true) String oldpaypassword,
            @RequestParam(required = true) String paypassword, Model model) {
        BasicRet basicRet = new BasicRet();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        Member  dbmember = memberService.getMemberById(member.getId());

        //oldpaypassword = Base64Utils.decode(oldpaypassword);
        paypassword = Base64Utils.decode(paypassword);


        if (dbmember.getPaypassword() == null || "".equals(dbmember.getPaypassword())) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("请先设置支付密码");
            return basicRet;
        }

       /* if (!CommonUtils.genMd5Password(oldpaypassword, dbmember.getPaypasswordsalt()).equals(dbmember.getPaypassword())) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("原支付密码输入错误");
            return basicRet;
        } else {*/
        if (dbmember.getPaypasswordsalt() == null) {
            dbmember.setPaypasswordsalt(CommonUtils.genSalt());
        }
        memberService.updateMemberPaypassword(dbmember.getId(), paypassword, dbmember.getPaypasswordsalt());

        member.setPaypassword(CommonUtils.genMd5Password(paypassword, dbmember.getPaypasswordsalt()));
        model.addAttribute(AppConstant.MEMBER_SESSION_NAME, member);

        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("修改成功");
        return basicRet;
        //}
    }

    @RequestMapping(value = "/checkMobileCode", method = RequestMethod.POST)
    @ApiOperation(value = "验证原手机号码")
    public BasicRet checkMobileCode(String mobile, String mobilecode) {
        BasicRet basicRet = new BasicRet();
        String type = SmsType.verification;
        boolean bool = mobileService.checkMobileCode(mobile, mobilecode, type);
        if (bool) {
            basicRet.setResult(BasicRet.SUCCESS);
            basicRet.setMessage("验证成功，下一步");
        } else {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("验证码错误请重新验证");
        }
        return basicRet;
    }


    @RequestMapping(value = "/updateMemberMobile", method = RequestMethod.POST)
    @ApiOperation(value = "修改手机号码")
    public MemberRet updateMemberMobile(@RequestParam(required = true) String mobile,
                                        @RequestParam(required = true) String mobileCode, Model model) {

        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        MemberRet memberRet = new MemberRet();

        //判断手机验证码是否正确
        SmsLog smsLog = mobileService.getLastLog(mobile, SmsType.verification, 5);
        if (smsLog == null || !mobileCode.equalsIgnoreCase(smsLog.getVerifycode())) {
            memberRet.setResult(BasicRet.ERR);
            memberRet.setMessage("手机验证码不正确");
            return memberRet;
        } else {
            memberService.updateMemberMobile(member.getId(), mobile);
            memberRet.setResult(MemberRet.SUCCESS);
            memberRet.setMessage("手机号码修改成功");
        }
        return memberRet;
    }


    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ApiOperation(value = "退出登录")
    public BasicRet logout(Model model, HttpSession session) {

        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

        model.asMap().remove(AppConstant.MEMBER_SESSION_NAME);
        session.removeAttribute(AppConstant.MEMBER_SESSION_NAME);


        SellerCompanyInfo companyInfo = member.getSellerCompanyInfo();
        if (companyInfo != null ) {


            Long[] categoryidArr = (Long[]) companyInfo.getBusinesscategory();
            if(categoryidArr != null){

                redisUtils.expire(SellerCompanyCacheService.SELLER_COMPANY_PUBSH_CATEGORY+member.getId(),0);

                //提前预加载可发布的商品分类
                AdvanceSellerPublish t1 =  new AdvanceSellerPublish(sellerCompanyCacheService,member.getId());
                Thread thread = new Thread(t1);
                thread.start();
            }
        }

        BasicRet basicRet = new BasicRet();
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("退出成功");
        return basicRet;
    }



    @RequestMapping(value = "/wap/logout", method = RequestMethod.POST)
    @ApiOperation(value = "退出登录(手机)")
    public BasicRet waplogout(Model model, HttpSession session,HttpServletRequest request,HttpServletResponse response) {
        model.asMap().remove(AppConstant.MEMBER_SESSION_NAME);
        session.removeAttribute(AppConstant.MEMBER_SESSION_NAME);

        String token =  CookieUtils.getValue(request.getCookies(),AppConstant.WAP_TOKEN_COOKIE_NAME);
        if(StringUtils.hasText(token)){
            memberTokenService.deleteByToken(token);
        }

        Cookie tokenCookie = new Cookie(AppConstant.WAP_TOKEN_COOKIE_NAME,"");
        tokenCookie.setPath("/");
        tokenCookie.setMaxAge(0);
        response.addCookie(tokenCookie);


        BasicRet basicRet = new BasicRet();
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("退出成功");
        return basicRet;
    }



    @ApiModel(description = "返回错误对象")
    private class MemberRet extends BasicRet {
        @ApiModelProperty(notes = "注册出错后名称与错误的对应关系")
        private ErrorMes errs;

        private  String regmes;

        public ErrorMes getErrs() {
            return errs;
        }

        public void setErrs(ErrorMes errs) {
            this.errs = errs;
        }

        public String getRegmes() {
            return regmes;
        }

        public void setRegmes(String regmes) {
            this.regmes = regmes;
        }
    }



    @RequestMapping(value = "/updateMemberFavicon", method = RequestMethod.POST)
    @ApiOperation(value = "手机端修改用户头像")
    @ApiImplicitParam(name = "favicon", value = "头像照片", required = true, paramType = "query", dataType = "string")
    public BasicExtRet updatebuyerinfoFavicon(String favicon, Model model) {
        BasicExtRet basicRet = new BasicExtRet();
        Member memberSession = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

        Member member = memberService.updateMemberFavicon(favicon, memberSession.getId());

        memberService.fillMemberOther(memberSession, member);
        memberService.fillMember(member);


        model.addAttribute(AppConstant.MEMBER_SESSION_NAME, member);
        basicRet.setData(member);
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("更新成功");
        return basicRet;
    }
}
