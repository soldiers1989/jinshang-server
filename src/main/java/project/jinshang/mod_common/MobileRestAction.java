package project.jinshang.mod_common;

import com.google.code.kaptcha.Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import mizuki.project.core.restserver.config.BasicRet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.jinshang.common.utils.CommonUtils;
import project.jinshang.mod_common.service.MobileService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;

/**
 * create : wyh
 * date : 2017/10/28
 */
@RestController
@RequestMapping("/rest/front")
@Api(tags = "手机验证码模块",description = "获取手机验证码")
@Transactional(rollbackFor = Exception.class)
public class MobileRestAction {

    @Autowired
    private MobileService mobileService;

    @RequestMapping(value = "/genMobileCode",method = RequestMethod.POST)
    @ApiOperation(value = "发送手机验证码")
    @ApiImplicitParam(value = "短信类型{注册=register;验证手机号=verification;找回密码=findpwd}",name = "type",required = true,paramType = "query")
    public BasicRet genMobileCode(
            HttpServletRequest request,
            @RequestParam(required = true) String mobile,
            @RequestParam(required = true) String imgCode,
            String type,
            HttpSession session){
        BasicRet basicRet =  new BasicRet();
        String sessionCode = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        session.removeAttribute(Constants.KAPTCHA_SESSION_KEY);
        if(!CommonUtils.isMobile(mobile)){
            basicRet.setMessage("手机号不合法");
            basicRet.setResult(BasicRet.ERR);
            return basicRet;
        }


        if(!imgCode.equalsIgnoreCase(sessionCode)){
            basicRet.setMessage("验证码不正确");
            basicRet.setResult(BasicRet.ERR);
            return basicRet;
        }



        boolean b = false;


        b = mobileService.sendMobileCode(request,mobile,type);

        if(b) {
            basicRet.setMessage("验证码发送成功");
            basicRet.setResult(BasicRet.SUCCESS);
        }else{
            basicRet.setMessage("验证码发送失败，请稍候再试");
            basicRet.setResult(BasicRet.ERR);
        }
        return  basicRet;
    }



    @RequestMapping(value = "/mobile/genMobileCode",method = RequestMethod.POST)
    @ApiOperation(value = "发送手机验证码")
    @ApiImplicitParam(value = "短信类型{注册=register;验证手机号=verification;找回密码=findpwd}",name = "type",required = true,paramType = "query")
    public BasicRet genMobileCodeMobile(
            HttpServletRequest request,
            @RequestParam(required = true) String mobile,
            String type){
        BasicRet basicRet =  new BasicRet();
        if(!CommonUtils.isMobile(mobile)){
            basicRet.setMessage("手机号不合法");
            basicRet.setResult(BasicRet.ERR);
            return basicRet;
        }

        boolean b = false;
        b = mobileService.sendMobileCode(request,mobile,type);

        if(b) {
            basicRet.setMessage("验证码发送成功");
            basicRet.setResult(BasicRet.SUCCESS);
        }else{
            basicRet.setMessage("验证码发送失败，请稍候再试");
            basicRet.setResult(BasicRet.ERR);
        }
        return  basicRet;
    }

}
