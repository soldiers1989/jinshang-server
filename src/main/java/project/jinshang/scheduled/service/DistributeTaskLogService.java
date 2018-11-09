package project.jinshang.scheduled.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.scheduled.Bean.DistributeTaskLog;
import project.jinshang.scheduled.mapper.DistributeTaskLogMapper;

/**
 * create : wyh
 * date : 2018/10/30
 */
@Service
public class DistributeTaskLogService {
    @Autowired
    DistributeTaskLogMapper distributeTaskLogMapper;

    public void insert(DistributeTaskLog log){
        distributeTaskLogMapper.insertSelective(log);
    }

}
