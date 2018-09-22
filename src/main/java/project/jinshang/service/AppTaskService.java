package project.jinshang.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.scheduled.mapper.AppTaskMapper;

import java.util.List;
import java.util.Map;

@Service
public class AppTaskService {

    @Autowired
    private AppTaskMapper appTaskMapper;

    public List<Map<String,Object>> getProductinfoList(){
        return appTaskMapper.getProductinfoList();
    }


    public void updateProductStore(long psid,String jsonList){
        appTaskMapper.updateProductStore(psid,jsonList);
    }
}
