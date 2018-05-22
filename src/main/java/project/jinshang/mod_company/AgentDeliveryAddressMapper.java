package project.jinshang.mod_company;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import project.jinshang.mod_company.bean.AgentDeliveryAddress;
import project.jinshang.mod_company.bean.AgentDeliveryAddressExample;

public interface AgentDeliveryAddressMapper {
    int countByExample(AgentDeliveryAddressExample example);

    int deleteByExample(AgentDeliveryAddressExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AgentDeliveryAddress record);

    int insertSelective(AgentDeliveryAddress record);

    List<AgentDeliveryAddress> selectByExample(AgentDeliveryAddressExample example);

    AgentDeliveryAddress selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AgentDeliveryAddress record, @Param("example") AgentDeliveryAddressExample example);

    int updateByExample(@Param("record") AgentDeliveryAddress record, @Param("example") AgentDeliveryAddressExample example);

    int updateByPrimaryKeySelective(AgentDeliveryAddress record);

    int updateByPrimaryKey(AgentDeliveryAddress record);

    @Select("select * from agentdeliveryaddress where sellerid=#{sellerid} order by id desc limit 1")
    AgentDeliveryAddress getBySellerid(@Param("sellerid") Long sellerid);
}