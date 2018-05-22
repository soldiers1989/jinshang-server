package project.jinshang.mod_common.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.mod_common.bean.SmsTemplate;
import project.jinshang.mod_common.mapper.SmsTemplateMapper;

@Service
public class SmsTemplateService {

    @Autowired
    private SmsTemplateMapper smsTemplateMapper;

    public SmsTemplate getBySendCode(String sendcode){
        return  smsTemplateMapper.getBySendCode(sendcode);
    }

}
