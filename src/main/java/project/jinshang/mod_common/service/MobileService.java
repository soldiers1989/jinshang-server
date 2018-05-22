package project.jinshang.mod_common.service;

import mizuki.project.core.restserver.modules.sms.DayuSms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.common.utils.CommonUtils;
import project.jinshang.common.utils.DateUtils;
import project.jinshang.common.utils.SmsSend;
import project.jinshang.common.utils.StringUtils;
import project.jinshang.mod_common.SmsLogMapper;
import project.jinshang.mod_common.bean.SmsLog;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * create : wyh
 * date : 2017/10/28
 */
@Service
public class MobileService {

    @Autowired
    private SmsLogMapper smsLogMapper;
    @Autowired
    private DayuSms dayuSms;


    public boolean sendMobileCode(HttpServletRequest request, String mobile,String type) {

        //生成验证码
//        String verifyCode = CommonUtils.genVerificationCode(4);
//        String content = "尊敬的用户，您正在进行手机验证操作，您的验证码为:"+verifyCode+"验证码有效期为5分钟";
        //  调用手机发送验证码接口
//        boolean b = SmsSend.send(mobile,content);
        String code = null;

        if(mobile.equals(AppConstant.MOCK_MOBILE)){
            code = AppConstant.MOCK_VERIFY_CODE;
        }else{
            code = dayuSms.send(mobile);
        }

        if(code==null){
            return  false;
        }

        request.getSession(true).setAttribute("mobileCode",code);
        SmsLog smsLog =  new SmsLog();
        smsLog.setIp(StringUtils.getIp(request));
        smsLog.setMobile(mobile);
        smsLog.setType(type);
        smsLog.setVerifycode(code);
        smsLog.setDescription("");
        saveSmsLog(smsLog);
        return true;
    }

    public void saveSmsLog(SmsLog smsLog) {
        smsLog.setCreatedate(new Date());
        smsLogMapper.insert(smsLog);
    }


    public  SmsLog getLastLog(String mobile,String type,int minutes){
       SmsLog smsLog =  smsLogMapper.getLastLog(mobile,type);
       if(smsLog != null && DateUtils.diffSeconds(smsLog.getCreatedate(),new Date())<=minutes*60){
           return  smsLog;
       }
       return  null;
    }

    /**
     * 验证原手机号码
     */
    public boolean checkMobileCode(String mobile,String mobilecode,String type){
        boolean bool=false;
        SmsLog smsLog=smsLogMapper.getLastLog(mobile,type);

        if(smsLog==null){
            return  false;
        }

        if (!mobilecode.equals(smsLog.getVerifycode())){
            return false;
        }else {
            return true;
        }
    }

    }
