package project.jinshang.mod_product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.mod_product.AgentLogisticsMapper;
import project.jinshang.mod_product.bean.AgentLogistics;

/**
 * create : wyh
 * date : 2018/3/12
 */

@Service
public class AgentLogisticsService {

    @Autowired
    private AgentLogisticsMapper agentLogisticsMapper;

    public  void insertSelective(AgentLogistics agentLogistics){
        agentLogisticsMapper.insertSelective(agentLogistics);
    }


}
