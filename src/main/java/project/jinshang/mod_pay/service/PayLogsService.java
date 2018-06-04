package project.jinshang.mod_pay.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.mod_pay.bean.PayLogs;
import project.jinshang.mod_pay.mapper.PayLogsMapper;

@Service
public class PayLogsService {
    @Autowired
    private PayLogsMapper payLogsMapper;

    public void insertSelective(PayLogs payLogs){
        payLogsMapper.insertSelective(payLogs);
    }




}
