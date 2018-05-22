package project.jinshang.common.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import project.jinshang.common.constant.AppConstant;

import javax.annotation.PostConstruct;
import java.util.Map;


@Component
@ConfigurationProperties("mod.sms_dayu")
public class  JinShangSms{

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final String product = "Dysmsapi";
    private static final String domain = "dysmsapi.aliyuncs.com";


    private String accessKey;
    private String accessKeySecret;
    private String signName;
    private String templateCode;
    private IAcsClient acsClient;

    public JinShangSms() {
    }

    @PostConstruct
    private void init() throws ClientException {
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", this.accessKey, this.accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", "Dysmsapi", "dysmsapi.aliyuncs.com");
        this.acsClient = new DefaultAcsClient(profile);
    }

//    public String send(String phone) {
//        String code = this.genCode();
//
//        try {
//            SendSmsRequest request = new SendSmsRequest();
//            request.setPhoneNumbers(phone);
//            request.setSignName(this.signName);
//            request.setTemplateCode(this.templateCode);
//            request.setTemplateParam("{\"code\":\"" + code + "\"}");
//            SendSmsResponse sendSmsResponse = (SendSmsResponse)this.acsClient.getAcsResponse(request);
//            if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
//                return code;
//            } else {
//                this.logger.error("短信发送 error: " + sendSmsResponse.getCode() + " ; " + sendSmsResponse.getMessage());
//                return null;
//            }
//        } catch (Exception var5) {
//            this.logger.error("err：", var5);
//            return null;
//        }
//    }


    public boolean send(String phone, String templateCode, Map<String,String> maps){

        if(AppConstant.MOCK_MOBILE.equals(phone)){
            return  false;
        }


        try {
            SendSmsRequest request = new SendSmsRequest();
            request.setPhoneNumbers(phone);
            request.setSignName(this.signName);
            request.setTemplateCode(templateCode);
            //request.setTemplateParam("{\"code\":\"" + code + "\"}");
            request.setTemplateParam(GsonUtils.toJson(maps));
           // System.out.println(request);
            SendSmsResponse sendSmsResponse = (SendSmsResponse)this.acsClient.getAcsResponse(request);
            //System.out.println(sendSmsResponse);
            if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
                return true;
            } else {
                System.out.println(sendSmsResponse.getCode()+"--"+sendSmsResponse.getMessage());
                this.logger.error("短信发送 error: " + sendSmsResponse.getCode() + " ; " + sendSmsResponse.getMessage());
                return false;
            }
        } catch (Exception var5) {
            var5.printStackTrace();
            this.logger.error("err：", var5);
            return false;
        }

    }



    public String getAccessKey() {
        return this.accessKey;
    }

    public JinShangSms setAccessKey(String accessKey) {
        this.accessKey = accessKey;
        return this;
    }

    public String getAccessKeySecret() {
        return this.accessKeySecret;
    }

    public JinShangSms setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
        return this;
    }

    public String getSignName() {
        return this.signName;
    }

    public JinShangSms setSignName(String signName) {
        this.signName = signName;
        return this;
    }

    public String getTemplateCode() {
        return this.templateCode;
    }

    public JinShangSms setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
        return this;
    }



}
