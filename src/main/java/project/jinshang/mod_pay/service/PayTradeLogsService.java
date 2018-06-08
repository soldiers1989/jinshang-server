package project.jinshang.mod_pay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.mod_pay.bean.PayTradeLogs;
import project.jinshang.mod_pay.bean.PayTradeLogsExample;
import project.jinshang.mod_pay.mapper.PayTradeLogsMapper;

import java.util.List;

@Service
public class PayTradeLogsService {

    @Autowired
    private PayTradeLogsMapper payTradeLogsMapper;


    public void insertSelective(PayTradeLogs payTradeLogs){
        payTradeLogsMapper.insertSelective(payTradeLogs);
    }


    public List<PayTradeLogs> getByOuttradeno(String outtradeno){
        PayTradeLogsExample example = new PayTradeLogsExample();
        PayTradeLogsExample.Criteria criteria = example.createCriteria();
        criteria.andOuttradenoEqualTo(outtradeno);
        return payTradeLogsMapper.selectByExample(example);
    }

    public void batchInsert(List<PayTradeLogs> list){
        payTradeLogsMapper.batchInsert(list);
    }


    public void updateByPrimaryKeySelective(PayTradeLogs logs){
        payTradeLogsMapper.updateByPrimaryKeySelective(logs);
    }


}
