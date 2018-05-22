package project.jinshang.mod_shop;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import project.jinshang.mod_shop.bean.Shops;
import project.jinshang.mod_shop.bean.ShopsExample;

public interface ShopsMapper {
    int countByExample(ShopsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Shops record);

    int insertSelective(Shops record);

    List<Shops> selectByExample(ShopsExample example);

    Shops selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Shops record, @Param("example") ShopsExample example);

    int updateByExample(@Param("record") Shops record, @Param("example") ShopsExample example);

    int updateByPrimaryKeySelective(Shops record);

    int updateByPrimaryKey(Shops record);
}