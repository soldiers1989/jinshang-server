package project.jinshang.mod_product;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import project.jinshang.mod_batchprice.bean.ProductQueryParam;
import project.jinshang.mod_product.bean.Products;
import project.jinshang.mod_product.bean.ProductsExample;

public interface ProductsMapper {
    int countByExample(ProductsExample example);

    int deleteByExample(ProductsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Products record);

    int insertSelective(Products record);

    List<Products> selectByExample(ProductsExample example);

    Products selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Products record, @Param("example") ProductsExample example);

    int updateByExample(@Param("record") Products record, @Param("example") ProductsExample example);

    int updateByPrimaryKeySelective(Products record);

    int updateByPrimaryKey(Products record);

    @Select("select * from products where productno=#{prodNo} limit 1 ")
    Products getProdByPdno(@Param("prodNo") String prodNo);

    @Select("<script>select * from products where 1=1 " +
            "<if test=\"productQueryParam.level1 !=null and productQueryParam.level1 &gt; 0\"> and level1id=#{productQueryParam.level1} </if>" +
            "<if test=\"productQueryParam.level2 !=null and productQueryParam.level2 &gt; 0\"> and level2id=#{productQueryParam.level2} </if>" +
            "<if test=\"productQueryParam.level3 !=null and productQueryParam.level3 &gt; 0\"> and level3id=#{productQueryParam.level3} </if>" +
            "<if test=\"productQueryParam.materialid !=null and productQueryParam.materialid &gt; 0\"> and materialid=#{productQueryParam.materialid} </if>" +
            "<if test=\"productQueryParam.cardnumid !=null and productQueryParam.cardnumid &gt; 0\"> and cardnumid=#{productQueryParam.cardnumid} </if>" +
            "<if test=\"productQueryParam.brand !=null and productQueryParam.brand != ''\"> and brand=#{productQueryParam.brand} </if>" +
            "<if test=\"productQueryParam.mark !=null and productQueryParam.mark != ''\"> and mark=#{productQueryParam.mark} </if>" +
            "<if test=\"productQueryParam.productName != null and productQueryParam.productName != ''\"> and (productname like '%${productQueryParam.productName}%' or level3 like '%${productQueryParam.productName}%')</if>" +
            "<if test=\"productQueryParam.productno != null and productQueryParam.productno != ''\"> and productno like '%${productQueryParam.productno}%' </if>" +
            "<if test=\"productQueryParam.stand != null and productQueryParam.stand != ''\"> and standard like '%${productQueryParam.stand}%' </if>" +
            "order by id desc"+
            "</script>")
    List<Products> selectProductList(@Param("productQueryParam") ProductQueryParam productQueryParam);

    @Select("<script>select p.* from products p  where p.id in <foreach collection=\"ids\" item=\"item\" index=\"index\" \n" +
            "open=\"(\" separator=\",\" close=\")\">#{item}</foreach></script>")
    List<Products> getProductsByIds(@Param("ids") Long[] ids);
}