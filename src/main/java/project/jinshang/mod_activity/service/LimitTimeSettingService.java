package project.jinshang.mod_activity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.mod_activity.LimitTimeSettingMapper;
import project.jinshang.mod_activity.bean.LimitTimeSetting;
import project.jinshang.mod_activity.bean.LimitTimeSettingExample;

import java.util.List;

/**
 * create : wyh
 * date : 2018/1/10
 */

@Service
public class LimitTimeSettingService {

    @Autowired
    private LimitTimeSettingMapper limitTimeSettingMapper;


    public  void insertOrReplace(LimitTimeSetting setting){
        LimitTimeSettingExample example = new LimitTimeSettingExample();
        int count = limitTimeSettingMapper.countByExample(example);
        if(count>0){
            limitTimeSettingMapper.update(setting);
            return;
        }

        limitTimeSettingMapper.insert(setting);
    }


    public  LimitTimeSetting getLimitTimeSetting(){
        LimitTimeSettingExample example = new LimitTimeSettingExample();
        List<LimitTimeSetting> list = limitTimeSettingMapper.selectByExample(example);
        if(list != null){
            return  list.get(0);
        }

        return  null;
    }


}
