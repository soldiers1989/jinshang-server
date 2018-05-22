package project.jinshang.mod_product;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.mapping.StatementType;
import project.jinshang.mod_product.bean.ProductName;
import project.jinshang.mod_product.bean.ProductNameExample;

public interface ProductNameMapper {
    int countByExample(ProductNameExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ProductName record);

    int insertSelective(ProductName record);

    List<ProductName> selectByExample(ProductNameExample example);

    ProductName selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ProductName record, @Param("example") ProductNameExample example);

    int updateByExample(@Param("record") ProductName record, @Param("example") ProductNameExample example);

    int updateByPrimaryKeySelective(ProductName record);

    int updateByPrimaryKey(ProductName record);

    @Select("select * from productname where typeid=#{typeid} and name=#{name} limit 1")
    ProductName getByCateidAndName(@Param("typeid") long typeid,@Param("name") String name);

    @Select("<script>select pn.*,cg.name as categoryname from productname pn left join categories cg on pn.typeid=cg.id " +
            "<where> 1=1"+
            "<if test=\"productName.prodno != null and productName.prodno !='' \">and pn.prodno=#{productName.prodno} </if>" +
            "<if test=\"productName.name != null and productName.name !='' \">and pn.name like '%${productName.name}%' </if>" +
            "<if test=\"subCategoryids != null  and subCategoryids !='' \">and pn.typeid in (${subCategoryids}) </if>" +
            "</where>" +
            "order by pn.id desc </script>" )
    List<ProductName> listProductName(@Param("productName") ProductName productName,@Param("subCategoryids") String subCategoryids);


    @Select("select pn.*,ct.name as c1,ct.id as level3id,ct2.name as c2 ,ct2.id as level2id,ct3.name " +
            "as c3,ct3.id as level1id from productname pn,categories ct,categories ct2,categories ct3" +
            " where pn.typeid=ct.id  and ct2.id=ct.parentid and ct3.id=ct2.parentid " +
            "and pn.name=#{prodname} and ct.name=#{level3} and ct2.name=#{level2}")
    Map<String,Object> getProdnameAndCategor(@Param("prodname") String prodname, @Param("level2") String level2, @Param("level3") String level3);
}