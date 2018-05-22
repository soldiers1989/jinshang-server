package project.jinshang.mod_product;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import project.jinshang.mod_product.bean.AreaCost;
import project.jinshang.mod_product.bean.AreaCostExample;

public interface AreaCostMapper {
    int countByExample(AreaCostExample example);

    int deleteByExample(AreaCostExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AreaCost record);

    int insertSelective(AreaCost record);

    List<AreaCost> selectByExample(AreaCostExample example);

    AreaCost selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AreaCost record, @Param("example") AreaCostExample example);

    int updateByExample(@Param("record") AreaCost record, @Param("example") AreaCostExample example);

    int updateByPrimaryKeySelective(AreaCost record);

    int updateByPrimaryKey(AreaCost record);


    @Select("select * from areacost where temid=#{temid}")
    List<AreaCost> getAreaCostByTemid(@Param("temid") long temid);
}