package project.jinshang.mod_product;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import project.jinshang.mod_product.bean.ShippingTemplateGroup;
import project.jinshang.mod_product.bean.ShippingTemplateGroupExample;

public interface ShippingTemplateGroupMapper {
    int countByExample(ShippingTemplateGroupExample example);

    int deleteByExample(ShippingTemplateGroupExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ShippingTemplateGroup record);

    int insertSelective(ShippingTemplateGroup record);

    List<ShippingTemplateGroup> selectByExample(ShippingTemplateGroupExample example);

    ShippingTemplateGroup selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ShippingTemplateGroup record, @Param("example") ShippingTemplateGroupExample example);

    int updateByExample(@Param("record") ShippingTemplateGroup record, @Param("example") ShippingTemplateGroupExample example);

    int updateByPrimaryKeySelective(ShippingTemplateGroup record);

    int updateByPrimaryKey(ShippingTemplateGroup record);

    @Select("select id from shippingtemplategroup where memberid=#{memberid} and name=#{name}")
    Long getGroupIdByMemberidAndName(@Param("memberid") long memberid,@Param("name") String name);

    @Select("select count(id) from shippingtemplategroup where expretemp=#{templatesId} or expreselftemp=#{templatesId} or exprehousetemp=#{templatesId}")
    int getCountUsedShippingTemplates(@Param("templatesId") Long templatesId);
}