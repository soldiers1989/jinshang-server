package project.jinshang.mod_product;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import project.jinshang.mod_product.bean.ProductAttr;
import project.jinshang.mod_product.bean.ProductAttrExample;

public interface ProductAttrMapper {


    int insert(ProductAttr record);

    int insertSelective(ProductAttr record);

    //清除redis 缓存在service层操作
    @Delete("delete from productattr where productid=#{productid}")
    int deleteByPid(@Param("productid") Long productid);

    @Cacheable(value = "productattr",key = "'getListByPidAndPdno:'+#p0+':'+#p1")
    @Select("select * from productattr where productid=#{productid} and pdno=#{pdno} order by attributeid asc ")
    List<ProductAttr> getListByPidAndPdno(@Param("productid") Long productid,@Param("pdno") String pdno);

//    @Select("select * from productattr where productid=#{productid} and pdno=#{pdno} order by attributeid asc ")
    @Select("select p.*,a.sort from productattr p left join attvalue a on p.attributeid=a.attid and p.value=a.paramvalue" +
            " where p.productid=#{productid} and p.pdno=#{pdno} order by p.attributeid asc")
    List<ProductAttr> getListByPidAndPdno2(@Param("productid") Long productid,@Param("pdno") String pdno);

    @Cacheable(value = "productattr",key = "'getListByPid:'+#p0")
    @Select("select * from productattr where productid=#{productid}")
    List<ProductAttr> getListByPid(@Param("productid") Long productid);

}