package project.jinshang.mod_smallprog.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 微信小程序mapper层
 *
 * @author xiazy
 * @create 2018-08-02 13:53
 **/
@Mapper
public interface WxSmallProgMapper {


    @Select("select p.id,p.productname,p.productalias,p.subtitle,p.brand,p.stand,p.pdpicture,p.material,p.salesnum as salenum,p             .unit,p.producttype,p.level3,(select min(prodprice) from productstore where p.id=pdid ) as price from productinfo p             where pdstate<>6 and pdstate<>5 order by salesnum desc limit #{count}")
    List<Map<String,Object>>  getRankProductList(@Param("count") int coutn);
}
