package project.jinshang.mod_company.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.mod_company.AgentDeliveryAddressMapper;
import project.jinshang.mod_company.bean.AgentDeliveryAddress;

/**
 * create : wyh
 * date : 2018/3/12
 */

@Service
public class AgentDeliveryAddressService {

    @Autowired
    private AgentDeliveryAddressMapper agentDeliveryAddressMapper;

    /**
     * 根据卖家用户id查询
     * @param sellerid
     * @return
     */
    public AgentDeliveryAddress getBySellerid(Long sellerid){
        return  agentDeliveryAddressMapper.getBySellerid(sellerid);
    }


    public  AgentDeliveryAddress getById(Long id){
        return  agentDeliveryAddressMapper.selectByPrimaryKey(id);
    }


    public  void  updateByPrimaryKeySelective(AgentDeliveryAddress address){
        agentDeliveryAddressMapper.updateByPrimaryKeySelective(address);
    }


    public  void insertSelective(AgentDeliveryAddress address){
        agentDeliveryAddressMapper.insertSelective(address);
    }

}
