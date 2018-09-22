package project.jinshang.mod_product.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import project.jinshang.mod_product.bean.OrderProductLog;
import project.jinshang.mod_product.bean.OrderProductLogExample;

public interface OrderProductLogMapper {
    int countByExample(OrderProductLogExample example);

    int deleteByExample(OrderProductLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(OrderProductLog record);

    int insertSelective(OrderProductLog record);

    List<OrderProductLog> selectByExample(OrderProductLogExample example);

    OrderProductLog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OrderProductLog record, @Param("example") OrderProductLogExample example);

    int updateByExample(@Param("record") OrderProductLog record, @Param("example") OrderProductLogExample example);

    int updateByPrimaryKeySelective(OrderProductLog record);

    int updateByPrimaryKey(OrderProductLog record);

    @Select("select * from orderproductlog where orderproductid=#{orderproductid} limit 1")
    OrderProductLog getByOrderproductid(@Param("orderproductid") Long orderproductid);


//    @Select("<script>select * from orders where id in <foreach collection=\"ids\" item=\"item\" index=\"index\" \n" +
//            "open=\"(\" separator=\",\" close=\")\">#{item}</foreach></script>")

    @Select("<script>select id,orderproductid,productinfojson from orderproductlog where orderproductid in <foreach collection=\"orderproductids\" item=\"item\" index=\"index\" " +
            "open=\"(\" separator=\",\" close=\")\">#{item}</foreach> </script>")
    List<OrderProductLog> getProductinfoByOrderproductids(@Param("orderproductids") List<Long> orderproductids);


    @Select("<script>select * from orderproductlog where orderproductid in <foreach collection=\"orderproductids\" item=\"item\" index=\"index\" " +
            "open=\"(\" separator=\",\" close=\")\">#{item}</foreach> </script>")
    List<OrderProductLog> getAllColumnByOrderproductids(@Param("orderproductids") List<Long> orderproductids);

}