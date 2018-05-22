package project.jinshang.mod_product;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import project.jinshang.mod_product.bean.CardNum;
import project.jinshang.mod_product.bean.CardNumExample;

public interface CardNumMapper {
    int countByExample(CardNumExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CardNum record);

    int insertSelective(CardNum record);

    List<CardNum> selectByExample(CardNumExample example);

    CardNum selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CardNum record, @Param("example") CardNumExample example);

    int updateByExample(@Param("record") CardNum record, @Param("example") CardNumExample example);

    int updateByPrimaryKeySelective(CardNum record);

    int updateByPrimaryKey(CardNum record);


    @Select("select * from cardnum where matialid=#{materialid } and name=#{name} limit 1")
    CardNum getByMaterialIdAndName(@Param("materialid") long materialid,@Param("name") String name);


    @Select("select * from cardnum where matialid=#{materialid }")
    List<CardNum> getListByMaterialid(@Param("materialid") long materialid);

}