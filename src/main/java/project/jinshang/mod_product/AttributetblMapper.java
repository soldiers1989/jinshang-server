package project.jinshang.mod_product;

import java.util.List;

import org.apache.ibatis.annotations.*;
import project.jinshang.mod_product.bean.Attributetbl;
import project.jinshang.mod_product.bean.AttributetblExample;
import project.jinshang.mod_product.bean.dto.AttributetblDto1;

public interface AttributetblMapper {
    int countByExample(AttributetblExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Attributetbl record);

    int insertSelective(Attributetbl record);

    List<Attributetbl> selectByExample(AttributetblExample example);

    Attributetbl selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Attributetbl record, @Param("example") AttributetblExample example);

    int updateByExample(@Param("record") Attributetbl record, @Param("example") AttributetblExample example);

    int updateByPrimaryKeySelective(Attributetbl record);

    int updateByPrimaryKey(Attributetbl record);

    @Select("select * from attributetbl where productnameid=#{productnameid} and name=#{name} limit 1")
    Attributetbl getByProductnameidAndName(@Param("productnameid") long productnameid,@Param("name") String name);

    @Delete("delete from attributetbl where productnameid=#{productnameid}")
    void deleteByProductnameid(@Param("productnameid") long productnameid);



    @Select("select * from attributetbl where productnameid=#{productnameid} order by sort asc,id asc")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "id",property = "attvalues",many = @Many(
                    select = "project.jinshang.mod_product.AttvalueMapper.getAttvalueByAttid"
            ))
    })
    List<Attributetbl> getAttributeWithValue(@Param("productnameid") long productnameid);


    @Select("select * from attributetbl where productnameid=#{productnameid} order by sort asc,id asc")
    List<AttributetblDto1> getAttributeByProdnameid(@Param("productnameid") Long productnameid);

}