package project.jinshang.scheduled.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import project.jinshang.mod_product.bean.Orders;

import java.util.List;

@Mapper
public interface OrderStoreStateLogTaskMapper {

    @Select("select  o.* from orders o left join orderstorestatelog ossl on \n" +
            "o.id=ossl.orderid where o.orderstatus=1 and (ossl.laststate is null or ossl.laststate !=99) ")
    public List<Orders>  getOrders();

}
