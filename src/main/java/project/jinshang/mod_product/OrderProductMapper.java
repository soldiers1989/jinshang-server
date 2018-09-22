package project.jinshang.mod_product;

import org.apache.ibatis.annotations.*;
import project.jinshang.mod_product.bean.OrderProduct;
import project.jinshang.mod_product.bean.OrderProductExample;
import project.jinshang.mod_product.bean.OrderProductModel;
import project.jinshang.mod_product.bean.ProductEvaModel;
import project.jinshang.mod_product.provider.OrderProductProvider;
import project.jinshang.mod_sale_rank.bean.SaleRankModel;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

public interface OrderProductMapper {
    int countByExample(OrderProductExample example);

    int deleteByExample(OrderProductExample example);

    int deleteByPrimaryKey(Long id);

    int insert(OrderProduct record);

    int insertSelective(OrderProduct record);

    List<OrderProduct> selectByExample(OrderProductExample example);

    OrderProduct selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OrderProduct record, @Param("example") OrderProductExample example);

    int updateByExample(@Param("record") OrderProduct record, @Param("example") OrderProductExample example);

    int updateByPrimaryKeySelective(OrderProduct record);

    int updateByPrimaryKey(OrderProduct record);

    @Select("select * from orderproduct where orderno=#{param1}")
    List<OrderProduct> listByOrderNo(String no);

    @Select("select * from orderproduct where orderno=#{param1} and pdno=#{param2}")
    OrderProduct findByOrderNoAndPdno(String no,String pdno);

    @Update("update orderproduct set backstate=#{param2} where id=#{param1}")
    void updateBackStatusById(long id,int status);


    @Select("select sum(actualpayment) from orderproduct where orderno=#{orderno} and pdid=#{pdid} and pdno=#{pdno}")
    BigDecimal getAllpayByOrderNoAndPdidAndPdNo(@Param("orderno") String orderno,@Param("pdid") Long pdid,@Param("pdno") String pdno);


    @Select("select * from orderproduct where orderno=#{orderno} and pdid=#{pdid} and pdno=#{pdno}")
    List<OrderProduct> getOrderProdByOrderNoAndPdidAndPdNo(@Param("orderno") String orderno,@Param("pdid") Long pdid,@Param("pdno") String pdno);


    /**
     * 批量插入
     * @param list
     */
    @InsertProvider(type = OrderProductProvider.class, method = "insertAll")
    void insertAll(List<OrderProduct> list);
    @Select("select * from orderproduct where orderno = #{orderno}")
    List<OrderProduct> getByOrderNo(@Param("orderno") String orderno);

    @UpdateProvider(type = OrderProductProvider.class,method = "updateOrderProductForModifyProductnum")
    int updateOrderProductForModifyProductnum(OrderProductModel orderProduct);




    /**
     * 根据订单id数组查询订单商品
     * @param orderids
     * @return
     */
    @Select("SELECT id,orderno,price,num,freight,protype,allpay,partpay,yupay,paystate,actualpayment from orderproduct where orderid in (${orderids})")
    public List<OrderProduct> getOrderProductByInOrderids(@Param("orderids") String orderids);

    @Select("select * from orderproduct where orderid=#{param1}")
    List<OrderProduct> listOrderProductByOrderId(long orderid);

    @Select("SELECT op.pdname,op.attrjson,op.price,count(*) as salenum from orderproduct op LEFT JOIN categories ca on op.classifyid=ca.id " +
            "LEFT JOIN categories cas on cas.id=ca.parentid " +
            "where cas.name='螺栓' " +
            "GROUP BY op.pdname,op.attrjson,op.price " +
            "ORDER BY salenum desc LIMIT 4")
    public List<SaleRankModel> getLuoSuanRank();


    @Select("SELECT op.pdname,op.attrjson,op.price,count(*) as salenum from orderproduct op LEFT JOIN categories ca on op.classifyid=ca.id " +
            "LEFT JOIN categories cas on cas.id=ca.parentid " +
            "where cas.name='螺母' " +
            "GROUP BY op.pdname,op.attrjson,op.price " +
            "ORDER BY salenum desc LIMIT 4")
    public List<SaleRankModel> getLuoMuRank();

    @Select("SELECT op.pdname,op.attrjson,op.price,count(*) as salenum from orderproduct op LEFT JOIN categories ca on op.classifyid=ca.id " +
            "LEFT JOIN categories cas on cas.id=ca.parentid " +
            "where cas.name='螺钉' " +
            "GROUP BY op.pdname,op.attrjson,op.price " +
            "ORDER BY salenum desc LIMIT 4")
    public List<SaleRankModel> getLuoDingRank();


    @Select("SELECT op.pdname,op.attrjson,op.price,count(*) as salenum from orderproduct op LEFT JOIN categories ca on op.classifyid=ca.id " +
            "LEFT JOIN categories cas on cas.id=ca.parentid " +
            "where cas.name='挡圈' " +
            "GROUP BY op.pdname,op.attrjson,op.price " +
            "ORDER BY salenum desc LIMIT 4")
    public List<SaleRankModel> getDangQuanRank();


    @Select("SELECT op.eva1,op.eva2,op.eva3,op.evatime,member.username,member.favicon,op.isanonymous,op.buyersexperience " +
            "from orderproduct op LEFT JOIN Member member on op.memberid=member.id " +
            "where op.evaluatestate=1 and op.pdid=#{pdid}")
    public List<ProductEvaModel> getProductEvaListByPdId(@Param("pdid") Long pdid);


    @Select("select sum(num) from orders o,orderproduct op where o.id=op.orderid and o.memberid=#{buyerid} and op.limitid=#{limitid} and op.backstate<>3 and o.orderstatus<>7")
    BigDecimal getTotalNumByLimitid(@Param("buyerid") Long buyerid,@Param("limitid") Long limitid);

    /**
     * 根据订单id查询
     * @param orderid
     * @return
     */
    @Select("select o.*,p.selfsupport from orderproduct o left join productinfo p on o.pdid = p.id where orderid=#{orderid}")
    List<OrderProduct> getByOrderid(@Param("orderid") Long orderid);

    @Select("select * from orderproduct where orderno = #{orderno} and backstate = 0 ")
    List<OrderProduct> getByOrderNoAndBackStatus(@Param("orderno") String orderno);




    @Select("select * from orderproduct where orderno = #{orderno}")
    List<OrderProduct> getRepurchaseList(@Param("orderno") String orderno);

    @Select("<script>select orderproduct  " +
            " from orderproduct o " +
            "<where> 1=1 " +
            "<if test=\"orderProduct.deliveryid != null \">and o.deliveryid = #{orderProduct.deliveryid} </if>" +
            "</where> order by o.id desc" +
            "</script>")
    List<Map<String,Object>> selectByObject(@Param("orderProduct") OrderProduct orderProduct);
}