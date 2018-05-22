package project.jinshang.mod_admin.mod_transet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.mod_admin.mod_transet.TransactionSettingMapper;
import project.jinshang.mod_admin.mod_transet.bean.TransactionSetting;
import project.jinshang.mod_admin.mod_transet.bean.TransactionSettingExample;

import java.util.List;

/**
 * Created by Administrator on 2017/11/9.
 * 支付方式service
 */
@Service
public class TransactionSettingService {

    @Autowired
    TransactionSettingMapper transactionSettingMapper;

    public void updateTransactionSetting(TransactionSetting transactionSetting, TransactionSettingExample transactionSettingExample){
        transactionSettingMapper.updateByExampleSelective(transactionSetting,transactionSettingExample);
    }

    public TransactionSetting loadTransactionSetting(TransactionSettingExample example){
        List<TransactionSetting> list = transactionSettingMapper.selectByExample(example);
        if(list.size()>0){
            return list.get(0);
        }
        return null;
    }


    public  TransactionSetting getTransactionSetting(){
       return transactionSettingMapper.getTransactionSetting();
    }

}
