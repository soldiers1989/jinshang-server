package project.jinshang.mod_shop;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import project.jinshang.mod_shop.bean.ShopGrade;
import project.jinshang.mod_shop.bean.ShopGradeExample;

public interface ShopGradeMapper {
    int countByExample(ShopGradeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ShopGrade record);

    int insertSelective(ShopGrade record);

    List<ShopGrade> selectByExample(ShopGradeExample example);

    ShopGrade selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ShopGrade record, @Param("example") ShopGradeExample example);

    int updateByExample(@Param("record") ShopGrade record, @Param("example") ShopGradeExample example);

    int updateByPrimaryKeySelective(ShopGrade record);

    int updateByPrimaryKey(ShopGrade record);
}