package project.jinshang.mod_admin.mod_inte.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.mod_admin.mod_inte.IntegralRecordMapper;
import project.jinshang.mod_admin.mod_inte.bean.IntegralRecordExample;

/**
 * create : wyh
 * date : 2018/3/6
 */
@Service
public class IntegralRecordService {

    @Autowired
    private IntegralRecordMapper integralRecordMapper;

    public  int countByExample(IntegralRecordExample example){
        return  integralRecordMapper.countByExample(example);
    }

}
