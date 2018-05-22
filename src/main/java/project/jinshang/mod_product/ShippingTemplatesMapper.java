package project.jinshang.mod_product;

import java.util.List;

import org.apache.ibatis.annotations.*;
import project.jinshang.mod_product.bean.ShippingTemplates;
import project.jinshang.mod_product.bean.ShippingTemplatesExample;

public interface ShippingTemplatesMapper {
    int countByExample(ShippingTemplatesExample example);

    int deleteByExample(ShippingTemplatesExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ShippingTemplates record);

    int insertSelective(ShippingTemplates record);

    List<ShippingTemplates> selectByExample(ShippingTemplatesExample example);

    ShippingTemplates selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ShippingTemplates record, @Param("example") ShippingTemplatesExample example);

    int updateByExample(@Param("record") ShippingTemplates record, @Param("example") ShippingTemplatesExample example);

    int updateByPrimaryKeySelective(ShippingTemplates record);

    int updateByPrimaryKey(ShippingTemplates record);



    @Select("select * from shippingTemplates where id=#{id}")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "id",property ="areaCostList",many = @Many(select = "project.jinshang.mod_product.AreaCostMapper.getAreaCostByTemid"))
    })
    ShippingTemplates getFullTemplatesById(long id);


    @Select("select * from ShippingTemplates where memberid=#{memberid}  and temname=#{name} limit 1")
    ShippingTemplates getByNameAndMemberid(@Param("name") String name,@Param("memberid") long memberid);

}