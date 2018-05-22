package project.jinshang.mod_common;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import mizuki.project.core.restserver.config.BasicRet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.jinshang.common.constant.SmsType;
import project.jinshang.common.utils.Base64Utils;
import project.jinshang.common.utils.CommonUtils;
import project.jinshang.common.utils.StringUtils;
import project.jinshang.mod_common.bean.SmsLog;
import project.jinshang.mod_common.service.MobileService;
import project.jinshang.mod_member.bean.Member;
import project.jinshang.mod_member.service.MemberService;

import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * create : wyh
 * date : 2017/11/3
 */
@RestController
@Api(tags = "用户密码",description = "用户密码相关接口")
public class PasswordAction {

    @Autowired
    private MemberService memberService;


    @Autowired
    private MobileService mobileService;


    @RequestMapping(value = "/rest/forgetPassword/getMobile",method = RequestMethod.POST)
    @ApiOperation(value = "获取用户手机号")
    public  MobileRet getMobile(@RequestParam(required = true) String username){
        MobileRet mobileRet = new MobileRet();
        Member member =  memberService.getMemberByUsername(username);
        if(member == null){
            mobileRet.setMessage("该用户不存在");
            mobileRet.setResult(BasicRet.ERR);
            return  mobileRet;
        }

        mobileRet.mobile =  member.getMobile();
        mobileRet.setResult(BasicRet.SUCCESS);
        return  mobileRet;
    }


    private  class  MobileRet extends  BasicRet{
        private  String mobile;

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }
    }


    @RequestMapping(value = "/rest/forgetPassword/step1",method = RequestMethod.POST)
    @ApiOperation(value = "找回密码第一步")
    public TokenRet forgetPasswordSep1(@RequestParam(required = true) String username,
                                    @RequestParam(required = true) String mobile,
                                    @RequestParam(required = true) String mobileCode,
                                    HttpSession session){
        TokenRet basicRet = new TokenRet();

        Member member =  memberService.getMemberByUsername(username);
        if(member == null){
            basicRet.setMessage("该用户不存在");
            basicRet.setResult(BasicRet.ERR);
            return  basicRet;
        }

        if(!member.getMobile().equalsIgnoreCase(mobile)){
            basicRet.setMessage("手机号和账户名不一致");
            basicRet.setResult(BasicRet.ERR);
            return  basicRet;
        }


        SmsLog smsLog = mobileService.getLastLog(mobile, SmsType.FINDPWD,5);
        if(smsLog == null || !mobileCode.equalsIgnoreCase(smsLog.getVerifycode())){
            basicRet.setMessage("手机验证码不正确");
            basicRet.setResult(BasicRet.ERR);
            return  basicRet;
        }

        String token = UUID.randomUUID().toString();
        session.setAttribute("token",token);
        session.setAttribute("updateUsername",username);


        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("请进行下一步");
        basicRet.token =  token;
        return  basicRet;
    }


    @RequestMapping(value =  "/rest/forgetPassword/step2",method = RequestMethod.POST)
    @ApiOperation(value = "找回密码第二步")
    public  BasicRet forgetPasswordSep2(@RequestParam(required = true) String username,
                                        @RequestParam(required = true) String token,
                                        @RequestParam(required = true) String password,
                                        HttpSession session){

            BasicRet basicRet =  new BasicRet();
            String sessionUsername = (String) session.getAttribute("updateUsername");
            String sessionToken = (String) session.getAttribute("token");

            password = Base64Utils.decode(password);

            if(!StringUtils.hasText(sessionUsername) || !StringUtils.hasText(sessionToken)){
                basicRet.setMessage("长时间未操作，请返回第一步重新操作");
                basicRet.setResult(BasicRet.ERR);
                return  basicRet;
            }


            if(!sessionUsername.equalsIgnoreCase(username) || !sessionToken.equalsIgnoreCase(sessionToken)){
                basicRet.setMessage("token不正确");
                basicRet.setResult(BasicRet.ERR);
                return  basicRet;
            }

            Member member =  memberService.getMemberByUsername(username);
            if(member == null){
                basicRet.setMessage("该用户不存在");
                basicRet.setResult(BasicRet.ERR);
                return  basicRet;
            }

            String newPwd = CommonUtils.genMd5Password(password,member.getPasswordsalt());
            Member m = new Member();
            m.setId(member.getId());
            m.setPassword(newPwd);
            memberService.updateMember(m);
            basicRet.setMessage("新密码设置成功！");
            basicRet.setResult(BasicRet.SUCCESS);


            session.removeAttribute("updateUsername");
            session.removeAttribute("token");

            return  basicRet;
    }



    private  class  TokenRet extends  BasicRet{
        @ApiModelProperty(notes = "token")
        private  String token;

        public String getToken() {
            return token;
        }
    }


}
