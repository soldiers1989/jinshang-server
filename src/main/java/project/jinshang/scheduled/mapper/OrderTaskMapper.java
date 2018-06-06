package project.jinshang.scheduled.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import project.jinshang.mod_product.bean.Orders;

import java.util.List;

@Mapper
public interface OrderTaskMapper {

    /**
     * 获取买家超时未收货的订单
     * @return
     */
    @Select("select id,memberid,saleid,orderno,createtime,paymenttime,sellerdeliverytime from orders where orderstatus=3 " +
            "and sellerdeliverytime + INTERVAL ${intervalday} < now()")
    List<Orders> getTimeOutNotReviceOrders(@Param("intervalday") String intervalday);


    /**
     * 将买家超时未收货的订单更新为已收货状态
     * @param intervalday
     * @return
     */
    @Update("update  orders set orderstatus=4,buyerdeliverytime=now() where orderstatus=3 " +
            "and sellerdeliverytime + INTERVAL ${intervalday} < now()")
    int updateTimeOutNotReviceOrdersToRevice(@Param("intervalday") String intervalday);


    /**
     * 获取买家超时未确认验货的订单 （去除正在退货的订单）
     * @param intervalday
     * @return
     */
//    @Select("select O.* from orders O where orderstatus=4 " +
//            "and O.buyerdeliverytime + INTERVAL ${intervalday} < now() and O.id not in " +
//            "(select orderid from orderproduct P where O.id=P.orderid and (P.backstate != 0 and P.backstate != 3))")
//    List<Orders> getTimeOutNotConfirmOrders(@Param("intervalday") String intervalday);


    //暂时使用，过一段时间后使用上面的代码
    @Select("select O.* from orders O where orderstatus=4 " +
            "and O.sellerdeliverytime + INTERVAL '25 day' < now() and O.id not in " +
            "(select orderid from orderproduct P where O.id=P.orderid and (P.backstate != 0 and P.backstate != 3)) limit 100")
    List<Orders> getTimeOutNotConfirmOrders(@Param("intervalday") String intervalday);



    /**
     * 将订单标记为已结算状态
     * @param id
     * @return
     */
    @Update("update orders set paymentmethod=1 where id=#{id} and paymentmethod=0 and billtoserver=1 and orderstatus=5")
    int settleOrders(@Param("id") long id);

}
