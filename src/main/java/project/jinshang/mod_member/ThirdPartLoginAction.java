package project.jinshang.mod_member;

import com.github.binarywang.wxpay.config.WxPayConfig;
import com.google.code.kaptcha.Constants;
import io.swagger.annotations.*;
import mizuki.project.core.restserver.config.BasicRet;
import org.apache.http.client.HttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.common.constant.Quantity;
import project.jinshang.common.constant.SmsType;
import project.jinshang.common.constant.ThirdPartLoginType;
import project.jinshang.common.utils.*;
import project.jinshang.mod_common.bean.SmsLog;
import project.jinshang.mod_common.service.MobileService;
import project.jinshang.mod_member.bean.*;
import project.jinshang.mod_member.service.MemberService;
import project.jinshang.mod_member.service.ThirdPartLoginService;
import project.jinshang.mod_member.service.UserBindService;
import project.jinshang.mod_product.service.OrdersService;

import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * create : wyh
 * date : 2018/2/24
 */
@RestController
@Api(tags = "第三方登录")
@SessionAttributes({AppConstant.MEMBER_SESSION_NAME})
@Transactional(rollbackFor = Exception.class)
@RequestMapping("/rest/thirdpart/login")
public class ThirdPartLoginAction {


    @Autowired
    private  WxLoginConfig wxLoginConfig;

    @Autowired
    private ThirdPartLoginService thirdPartLoginService;

    @Autowired
    private UserBindService userBindService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private MobileService mobileService;





    @PostMapping("/getWxAccessToken")
    @ApiOperation(value = "微信登录")
    public WxAccessTokenRet getWxAccessToken(@RequestParam(required = true) String code,String type, Model model,HttpSession session) {
        WxAccessTokenRet wxAccessTokenRet = new WxAccessTokenRet();

        WxAccessToken wxAccessToken = thirdPartLoginService.getWxAccssToken(code,type);
        if(wxAccessToken == null || wxAccessToken.getAccess_token() == null ||wxAccessToken.getAccess_token().equals("")){
            wxAccessTokenRet.setResult(BasicRet.ERR);
            wxAccessTokenRet.setMessage("获取登录token失败，请稍后再试");
            return  wxAccessTokenRet;
        }

        wxAccessTokenRet.data.wxAccessToken = wxAccessToken;
        wxAccessTokenRet.setResult(BasicRet.SUCCESS);
        return  wxAccessTokenRet;
    }


    @PostMapping("/getWxAccTokForTinyProg")
    @ApiOperation(value = "微信小程序登录")
    public WxAccessTokenRet getWxAccTokForTinyProg(@RequestParam(required = true) String code, Model model,HttpSession session) {
        WxAccessTokenRet wxAccessTokenRet = new WxAccessTokenRet();

        WxAccessToken wxAccessToken = thirdPartLoginService.getWxAccTokForTinyProg(code);
        if(wxAccessToken == null || wxAccessToken.getUnionid() == null || "".equals(wxAccessToken.getUnionid())){
            wxAccessTokenRet.setResult(BasicRet.ERR);
            wxAccessTokenRet.setMessage("获取登录token失败，请稍后再试");
            return  wxAccessTokenRet;
        }



        wxAccessTokenRet.data.wxAccessToken = wxAccessToken;
        wxAccessTokenRet.setResult(BasicRet.SUCCESS);
        return  wxAccessTokenRet;
    }


    private class WxAccessTokenRet extends  BasicRet{
        private class WxAccessTokenData implements Serializable {
            private  WxAccessToken wxAccessToken;

            public WxAccessToken getWxAccessToken() {
                return wxAccessToken;
            }

            public void setWxAccessToken(WxAccessToken wxAccessToken) {
                this.wxAccessToken = wxAccessToken;
            }
        }

        private  WxAccessTokenData data = new WxAccessTokenData();

        public WxAccessTokenData getData() {
            return data;
        }

        public void setData(WxAccessTokenData data) {
            this.data = data;
        }
    }



    @PostMapping("/wxLogin")
    @ApiOperation(value = "微信登录")
    public ThirdPartLoginRet wxLogin(@RequestParam(required = true) String code,String type, Model model,HttpSession session){
        ThirdPartLoginRet thirdPartLoginRet = new ThirdPartLoginRet();

        WxAccessToken wxAccessToken = thirdPartLoginService.getWxAccssToken(code,type);
        if(wxAccessToken == null || wxAccessToken.getAccess_token() == null ||wxAccessToken.getAccess_token().equals("") ||
                wxAccessToken.getUnionid() == null ||wxAccessToken.getUnionid().equals("")){
            thirdPartLoginRet.setResult(BasicRet.ERR);
            thirdPartLoginRet.setMessage("获取登录token失败，请稍后再试");
            return  thirdPartLoginRet;
        }

        thirdPartLoginRet.data.setRealopenid(wxAccessToken.getOpenid());

        UserBinding userBinding = userBindService.getByOpenid(wxAccessToken.getUnionid(), ThirdPartLoginType.wxLogin);

        if(userBinding != null && userBinding.getMemberid() != null){ //已经绑定了
            Member member = memberService.getMemberById(userBinding.getMemberid());
            if(member != null) {
                member.setFrom("buyer");
                member.setLoginType("main");
                if (member.getDisabled() == true) {
                    thirdPartLoginRet.setResult(BasicRet.ERR);
                    thirdPartLoginRet.setMessage("帐号被禁用");
                    return thirdPartLoginRet;
                }

                memberService.fillMember(member);

                model.addAttribute(AppConstant.MEMBER_SESSION_NAME, member);
                thirdPartLoginRet.setMessage("登陆成功");
                thirdPartLoginRet.setResult(BasicRet.SUCCESS);

                Member updateDateMember = new Member();
                updateDateMember.setId(member.getId());
                updateDateMember.setLastlogindate(new Date());
                memberService.updateByPrimaryKeySelective(updateDateMember);


                //将未付款的订单改变成关闭
                //ordersService.updateNotPayOrdersForFinish(member.getId());


                String uuid = session.getId();
                thirdPartLoginRet.data.webToken = uuid;

                return  thirdPartLoginRet;
            }else {
                thirdPartLoginRet.setResult(BasicRet.ERR);
                thirdPartLoginRet.setMessage("未查询到匹配的帐号信息");
                return  thirdPartLoginRet;
            }
        }else{//没有绑定

            //获取用户微信信息
            WeixinInfo weixinInfo = thirdPartLoginService.getWeixinInfo(wxAccessToken.getOpenid(),wxAccessToken.getAccess_token());
            if(weixinInfo == null){
                thirdPartLoginRet.setResult(BasicRet.ERR);
                thirdPartLoginRet.setMessage("获取用户微信信息失败！请稍后重试");
                return  thirdPartLoginRet;
            }
            thirdPartLoginRet.setResult(BasicRet.SUCCESS);
            thirdPartLoginRet.setMessage("没有绑定帐号");
            thirdPartLoginRet.data.setNickname(weixinInfo.getNickname());
            thirdPartLoginRet.data.setType(ThirdPartLoginType.wxLogin);
            thirdPartLoginRet.data.setOpenid(wxAccessToken.getUnionid());
            return  thirdPartLoginRet;
        }
    }



    @PostMapping("/wxLoginForTinyProg")
    @ApiOperation(value = "微信小程序登录")
    public ThirdPartLoginRet wxLoginForTinyProg(@RequestParam(required = true) String code, Model model,HttpSession session){
        ThirdPartLoginRet thirdPartLoginRet = new ThirdPartLoginRet();

        WxAccessToken wxAccessToken = thirdPartLoginService.getWxAccTokForTinyProg(code);
        if(wxAccessToken == null || wxAccessToken.getUnionid() == null || "".equals(wxAccessToken.getUnionid())){
            thirdPartLoginRet.setResult(BasicRet.ERR);
            thirdPartLoginRet.setMessage("获取登录token失败，请稍后再试");
            return  thirdPartLoginRet;
        }

        thirdPartLoginRet.data.setRealopenid(wxAccessToken.getOpenid());

        UserBinding userBinding = userBindService.getByOpenid(wxAccessToken.getUnionid(), ThirdPartLoginType.wxLogin);

        if(userBinding != null && userBinding.getMemberid() != null){ //已经绑定了
            Member member = memberService.getMemberById(userBinding.getMemberid());
            if(member != null) {
                member.setFrom("buyer");
                member.setLoginType("main");
                if (member.getDisabled() == true) {
                    thirdPartLoginRet.setResult(BasicRet.ERR);
                    thirdPartLoginRet.setMessage("帐号被禁用");
                    return thirdPartLoginRet;
                }

                memberService.fillMember(member);

                model.addAttribute(AppConstant.MEMBER_SESSION_NAME, member);
                thirdPartLoginRet.setMessage("登陆成功");
                thirdPartLoginRet.setResult(BasicRet.SUCCESS);

                Member updateDateMember = new Member();
                updateDateMember.setId(member.getId());
                updateDateMember.setLastlogindate(new Date());
                memberService.updateByPrimaryKeySelective(updateDateMember);


                //将未付款的订单改变成关闭
                //ordersService.updateNotPayOrdersForFinish(member.getId());


                String uuid = session.getId();
                thirdPartLoginRet.data.webToken = uuid;

                return  thirdPartLoginRet;
            }else {
                thirdPartLoginRet.setResult(BasicRet.ERR);
                thirdPartLoginRet.setMessage("未查询到匹配的帐号信息");
                return  thirdPartLoginRet;
            }
        }else{//没有绑定

            //获取用户微信信息
//            WeixinInfo weixinInfo = thirdPartLoginService.getWeixinInfo(wxAccessToken.getOpenid(),wxAccessToken.getAccess_token());
//            if(weixinInfo == null){
//                thirdPartLoginRet.setResult(BasicRet.ERR);
//                thirdPartLoginRet.setMessage("获取用户微信信息失败！请稍后重试");
//                return  thirdPartLoginRet;
//            }
            thirdPartLoginRet.setResult(BasicRet.SUCCESS);
            thirdPartLoginRet.setMessage("没有绑定帐号");
//            thirdPartLoginRet.data.setNickname(weixinInfo.getNickname());
            thirdPartLoginRet.data.setType(ThirdPartLoginType.wxLogin);
            thirdPartLoginRet.data.setOpenid(wxAccessToken.getUnionid());
            return  thirdPartLoginRet;
        }
    }



    @PostMapping("/bind")
    @ApiOperation("绑定帐号")
    public  BasicRet bind(
                          @RequestParam(required = true) String openid,
                          @RequestParam(required = true) String type,
                          @RequestParam(required = true) String nickname,
                          @RequestParam(required = true) String username,
                          @RequestParam(required = true) String password,
                          @RequestParam(required = true) String yanzheng, Model model, HttpSession session
                          ){
        BasicRet basicRet = new BasicRet();

        String sessimg = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if (sessimg == null) {
            return new BasicRet(BasicRet.ERR, "请获取图片验证码");
        }

        if (!sessimg.equalsIgnoreCase(yanzheng)) {
            return new BasicRet(BasicRet.ERR, "图片验证码不正确");
        }

        session.removeAttribute(Constants.KAPTCHA_SESSION_KEY);

        password = Base64Utils.decode(password);

        Member member = memberService.getMemberByUsername(username);
        if (member == null) {
            basicRet.setMessage("用户名密码不正确");
            basicRet.setResult(BasicRet.ERR);
            return basicRet;
        } else {

            if (!CommonUtils.genMd5Password(password, member.getPasswordsalt()).equals(member.getPassword())) {  //密码不正确
                basicRet.setResult(BasicRet.ERR);
                basicRet.setMessage("用户名密码不正确");
                return basicRet;
            } else { //用户密码正确

                //判断该微信号是否已经绑定了其他的帐号
                int bindingCount = userBindService.getBindingCount(openid,ThirdPartLoginType.wxLogin);
                if(bindingCount>0){
                    basicRet.setResult(BasicRet.ERR);
                    basicRet.setMessage("该微信帐号已经绑定其他帐号了");
                    return  basicRet;
                }

                //判断该帐号是否已经绑定了
                UserBinding binding = userBindService.getByMemberid(member.getId(),ThirdPartLoginType.wxLogin);
                if(binding != null){
                    basicRet.setResult(BasicRet.ERR);
                    basicRet.setMessage("该帐号已经被绑定了");
                    return  basicRet;
                }

                binding = new UserBinding();
                binding.setMemberid(member.getId());
                binding.setOpenid(openid);
                binding.setName(nickname);
                binding.setState(Quantity.STATE_1);
                binding.setType(ThirdPartLoginType.wxLogin);
                binding.setBindtime(new Date());
                userBindService.insertSelective(binding);


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
            }
        }

    }


    @PostMapping("/wap/bind")
    @ApiOperation("手机端绑定帐号")
    public  BasicRet wapBbind(
                          @RequestParam(required = true) String openid,
                          @RequestParam(required = true) String type,
                          @RequestParam(required = true) String nickname,
                          @RequestParam(required = true) String username,
                          @RequestParam(required = true) String password,
                          Model model, HttpSession session
    ){
        BasicRet basicRet = new BasicRet();

        password = Base64Utils.decode(password);

        Member member = memberService.getMemberByUsername(username);
        if (member == null) {
            basicRet.setMessage("用户名密码不正确");
            basicRet.setResult(BasicRet.ERR);
            return basicRet;
        } else {

            if (!CommonUtils.genMd5Password(password, member.getPasswordsalt()).equals(member.getPassword())) {  //密码不正确
                basicRet.setResult(BasicRet.ERR);
                basicRet.setMessage("用户名密码不正确");
                return basicRet;
            } else { //用户密码正确


                //判断该微信号是否已经绑定了其他的帐号
                int bindingCount = userBindService.getBindingCount(openid,ThirdPartLoginType.wxLogin);
                if(bindingCount>0){
                    basicRet.setResult(BasicRet.ERR);
                    basicRet.setMessage("该微信帐号已经绑定其他帐号了");
                    return  basicRet;
                }

                //判断该帐号是否已经绑定了
                UserBinding binding = userBindService.getByMemberid(member.getId(),ThirdPartLoginType.wxLogin);
                if(binding != null){
                    basicRet.setResult(BasicRet.ERR);
                    basicRet.setMessage("该帐号已经被绑定了");
                    return  basicRet;
                }

                binding = new UserBinding();
                binding.setMemberid(member.getId());
                binding.setOpenid(openid);
                binding.setName(nickname);
                binding.setState(Quantity.STATE_1);
                binding.setType(ThirdPartLoginType.wxLogin);
                binding.setBindtime(new Date());
                userBindService.insertSelective(binding);


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
            }
        }

    }


    @PostMapping("/wap/bindByMobile")
    @ApiOperation("手机端绑定帐号")
    public  BasicRet wapBbindByMobile(
            @RequestParam(required = true) String code,
            @RequestParam(required = true) String nickname,
            @RequestParam(required = true) String mobile,
            @RequestParam(required = true) String mobileCode,
            Model model, HttpSession session
    ){
        BasicRet basicRet = new BasicRet();

        MemberExample example=new MemberExample();
        MemberExample.Criteria criteria=example.createCriteria();
        criteria.andMobileEqualTo(mobile);
        java.util.List<Member> memberList =memberService.selectByExample(example);
//        Member member = memberService.getMemberByUsername(username);
        if (memberList == null || memberList.size()==0) {
            basicRet.setMessage("用户名密码不正确");
            basicRet.setResult(BasicRet.ERR);
            return basicRet;
        } else {
            Member member=memberList.get(0);
            //判断手机验证码是否正确
            SmsLog smsLog = mobileService.getLastLog(mobile, SmsType.verification, 5);
            if (smsLog == null || !mobileCode.equalsIgnoreCase(smsLog.getVerifycode())) {
                basicRet.setResult(BasicRet.ERR);
                basicRet.setMessage("手机验证码不正确");
                return basicRet;
            } else { //手机验证码正确

                WxAccessToken wxAccessToken = thirdPartLoginService.getWxAccTokForTinyProg(code);
                if(wxAccessToken == null || wxAccessToken.getUnionid() == null || "".equals(wxAccessToken.getUnionid())){
                    basicRet.setResult(BasicRet.ERR);
                    basicRet.setMessage("获取登录token失败，请稍后再试");
                    return  basicRet;
                }else{
                    String unionid=wxAccessToken.getUnionid();
                    //判断该微信号是否已经绑定了其他的帐号
                    int bindingCount = userBindService.getBindingCount(unionid,ThirdPartLoginType.wxLogin);
                    if(bindingCount>0){
                        basicRet.setResult(BasicRet.ERR);
                        basicRet.setMessage("该微信帐号已经绑定其他帐号了");
                        return  basicRet;
                    }

                    //判断该帐号是否已经绑定了
                    UserBinding binding = userBindService.getByMemberid(member.getId(),ThirdPartLoginType.wxLogin);
                    if(binding != null){
                        basicRet.setResult(BasicRet.ERR);
                        basicRet.setMessage("该帐号已经被绑定了");
                        return  basicRet;
                    }

                    binding = new UserBinding();
                    binding.setMemberid(member.getId());
                    binding.setOpenid(unionid);
                    binding.setName(nickname);
                    binding.setState(Quantity.STATE_1);
                    binding.setType(ThirdPartLoginType.wxLogin);
                    binding.setBindtime(new Date());
                    userBindService.insertSelective(binding);


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
                }
            }
        }

    }



    @RequestMapping(value = "/registerMember", method = RequestMethod.POST)
    @ApiOperation(value = "注册用户并绑定")
    @ApiImplicitParams({

            @ApiImplicitParam(name = "username", value = "用户名", required = true, paramType = "query"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, paramType = "query"),
            @ApiImplicitParam(name = "mobile", value = "手机号", required = true, paramType = "query"),
            @ApiImplicitParam(name = "invitecode", value = "邀请码", required = false, paramType = "query"),
            @ApiImplicitParam(name = "clerkname", value = "业务员", required = false, paramType = "query"),
            @ApiImplicitParam(name = "mobileCode", value = "手机验证码", required = true, paramType = "query"),
            @ApiImplicitParam(name = "registersourcelabel", value = "注册来源", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "registertypelabel", value = "注册类型", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "registerchannellabel", value = "注册渠道", required = false, paramType = "query", dataType = "string"),
    })
    public MemberRet registerMember( @RequestParam(required = true) String openid,
                                     @RequestParam(required = true) String type,
                                     @RequestParam(required = true) String nickname,
                                     Member member, String mobileCode,Model model) {
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


        //判断该微信号是否已经绑定了其他的帐号
        int bindingCount = userBindService.getBindingCount(openid,ThirdPartLoginType.wxLogin);
        if(bindingCount>0){
            memberRet.setResult(BasicRet.ERR);
            memberRet.setMessage("该微信帐号已经绑定其他帐号了");
            return  memberRet;
        }

        errorMes = memberService.registerMember(member);

        if (errorMes.getSize() != 0) {
            memberRet.errs = errorMes;
            memberRet.setMessage("注册失败");
            return (MemberRet) memberRet.setResult(BasicRet.ERR);
        }


        member = memberService.getMemberByUsername(member.getUsername());
        member.setFrom("buyer");
        member.setLoginType("main");

        memberService.fillMember(member);
        model.addAttribute(AppConstant.MEMBER_SESSION_NAME, member);

        memberRet.setMessage("注册成功");

        return (MemberRet) memberRet.setResult(BasicRet.SUCCESS);
    }


    /**
     *微信端通过微信code换取用户的基本信息
     * @author xiazy
     * @date  2018/6/15 14:03
     * @param code
     * @param type
     * @param model
     * @param session
     * @return void
     */
    @RequestMapping(value  = "getUserInfoByCode",method = RequestMethod.POST)
    public WxUserInfoRet getUserInfoByCode(@RequestParam(required = true) String code,String type, Model model,HttpSession session) {
        WxUserInfoRet wxUserInfoRet=new WxUserInfoRet();
        WxAccessToken wxAccessToken = thirdPartLoginService.getWxAccssToken(code,type);
        if(wxAccessToken == null || wxAccessToken.getAccess_token() == null ||wxAccessToken.getAccess_token().equals("")){
            wxUserInfoRet.setResult(BasicRet.ERR);
            wxUserInfoRet.setMessage("获取登录token失败，请稍后再试");
            return  wxUserInfoRet;
        }
        WeixinInfo weixinInfo=thirdPartLoginService.getWeixinInfo(wxAccessToken.getOpenid(),wxAccessToken.getAccess_token());
        wxUserInfoRet.setData(weixinInfo);
        wxUserInfoRet.setMessage("获取用户信息成功");
        wxUserInfoRet.setResult(BasicRet.SUCCESS);
        return wxUserInfoRet;
    }


    @RequestMapping(value = "getSignature",method = RequestMethod.POST)
    public SignRet getSignature(@RequestParam(required = true) String url,Model model,HttpSession session){
        SignRet signRet=new SignRet();
        signRet.setMessage("获取签名成功");
        signRet.setResult(BasicRet.SUCCESS);
        JsapiTicket jsapiTicket=thirdPartLoginService.getJsApiTicket();
        Map map=SignUtil.sign(jsapiTicket.getTicket(),url);
        signRet.setJsapi_ticket(map.get("jsapi_ticket").toString());
        signRet.setNonceStr(map.get("nonceStr").toString());
        signRet.setSignature(map.get("signature").toString());
        signRet.setTimestamp(map.get("timestamp").toString());
        signRet.setUrl(map.get("url").toString());
        signRet.setAppId(wxLoginConfig.getWapAppId());
        return signRet;
    }

    private  class  ThirdPartLoginRet extends  BasicRet{
        private class ThirdPartLoginData {
            private  String nickname;
            private  Short type;
            private  String openid;
            private  String webToken;

            private  String realopenid;

            public String getWebToken() {
                return webToken;
            }

            public void setWebToken(String webToken) {
                this.webToken = webToken;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public Short getType() {
                return type;
            }

            public void setType(Short type) {
                this.type = type;
            }

            public String getOpenid() {
                return openid;
            }

            public void setOpenid(String openid) {
                this.openid = openid;
            }

            public String getRealopenid() {
                return realopenid;
            }

            public void setRealopenid(String realopenid) {
                this.realopenid = realopenid;
            }
        }

        private  ThirdPartLoginData data = new ThirdPartLoginData();

        public ThirdPartLoginData getData() {
            return data;
        }

        public void setData(ThirdPartLoginData data) {
            this.data = data;
        }
    }



    private class WxUserInfoRet extends BasicRet{
        private WeixinInfo data=new WeixinInfo();

        public WeixinInfo getData() {
            return data;
        }

        public void setData(WeixinInfo data) {
            this.data = data;
        }
    }


    @ApiModel(description = "返回错误对象")
    private class MemberRet extends BasicRet {
        @ApiModelProperty(notes = "注册出错后名称与错误的对应关系")
        private ErrorMes errs;

        public ErrorMes getErrs() {
            return errs;
        }
    }


    private class SignRet extends BasicRet{
        private String url;
        private String jsapi_ticket;
        private String nonceStr;
        private String timestamp;
        private String signature;
        private String appId;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getJsapi_ticket() {
            return jsapi_ticket;
        }

        public void setJsapi_ticket(String jsapi_ticket) {
            this.jsapi_ticket = jsapi_ticket;
        }

        public String getNonceStr() {
            return nonceStr;
        }

        public void setNonceStr(String nonceStr) {
            this.nonceStr = nonceStr;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public String getAppId() {
            return appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }
    }

}
