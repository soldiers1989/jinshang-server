package project.jinshang.mod_product;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import project.jinshang.mod_product.bean.AgentLogistics;
import project.jinshang.mod_product.bean.AgentLogisticsExample;

public interface AgentLogisticsMapper {
    int countByExample(AgentLogisticsExample example);

    int deleteByExample(AgentLogisticsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AgentLogistics record);

    int insertSelective(AgentLogistics record);

    List<AgentLogistics> selectByExample(AgentLogisticsExample example);

    AgentLogistics selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AgentLogistics record, @Param("example") AgentLogisticsExample example);

    int updateByExample(@Param("record") AgentLogistics record, @Param("example") AgentLogisticsExample example);

    int updateByPrimaryKeySelective(AgentLogistics record);

    int updateByPrimaryKey(AgentLogistics record);
}