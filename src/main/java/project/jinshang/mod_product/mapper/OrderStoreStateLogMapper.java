package project.jinshang.mod_product.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import project.jinshang.mod_product.bean.OrderStoreStateLog;
import project.jinshang.mod_product.bean.OrderStoreStateLogExample;

public interface OrderStoreStateLogMapper {
    int countByExample(OrderStoreStateLogExample example);

    int deleteByExample(OrderStoreStateLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(OrderStoreStateLog record);

    int insertSelective(OrderStoreStateLog record);

    List<OrderStoreStateLog> selectByExample(OrderStoreStateLogExample example);

    OrderStoreStateLog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OrderStoreStateLog record, @Param("example") OrderStoreStateLogExample example);

    int updateByExample(@Param("record") OrderStoreStateLog record, @Param("example") OrderStoreStateLogExample example);

    int updateByPrimaryKeySelective(OrderStoreStateLog record);

    int updateByPrimaryKey(OrderStoreStateLog record);


    @Select("select * from orderstorestatelog where orderid=#{orderid} order by id desc limit 1")
    OrderStoreStateLog getByOrderid(@Param("orderid") Long orderid);


    @Select("select * from orderstorestatelog where orderno=#{orderno} order by id desc limit 1")
    OrderStoreStateLog getByOrderno(@Param("orderno") String orderno);
}