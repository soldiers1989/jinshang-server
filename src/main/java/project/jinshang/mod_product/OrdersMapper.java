package project.jinshang.mod_product;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;
import project.jinshang.common.constant.Quantity;
import project.jinshang.common.utils.DateUtils;
import project.jinshang.common.utils.StringUtils;
import project.jinshang.mod_admin.mod_count.bean.OrderStatisticModel;
import project.jinshang.mod_cash.bean.SalerCapital;
import project.jinshang.mod_product.bean.*;
import project.jinshang.mod_product.bean.dto.OrdersLogisticsInfoDto;
import project.jinshang.mod_product.bean.dto.OrdersView;
import project.jinshang.mod_product.provider.OrdersProvider;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.MessageFormat;
import java.time.LocalTime;
import java.util.*;

public interface OrdersMapper {
    int countByExample(OrdersExample example);

    int deleteByExample(OrdersExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Orders record);

    int insertSelective(Orders record);

    List<Orders> selectByExample(OrdersExample example);

    Orders selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Orders record, @Param("example") OrdersExample example);

    int updateByExample(@Param("record") Orders record, @Param("example") OrdersExample example);

    int updateByPrimaryKeySelective(Orders record);

    int updateByPrimaryKey(Orders record);

    @Select("select * from orders where orderno=#{param1}")
    Orders findByNo(String no);

    @Update("update orders set orderstatus=#{param2},sellerdeliverytime=now()" +
            " where id=#{param1}")
    void updateOrderStatusById(long id, int status);

    @Update("update orders set orderstatus=#{param2},logisticscompany=#{param3},couriernumber=#{param4},sellerdeliverytime=now()" +
            " where id=#{param1}")
    void updateOrderStatusPlusById(long id, int status, String logisticsCompanyName, String expressNo);

    @Update("update orders set reason=#{param2} where id=#{param1}")
    void updateReason(long id, String reason);

    /**
     * 获取卖家冻结金额到期资金
     *
     * @param intervalday 订单验收时间加多少天
     * @param nowtime     当前时间
     * @return
     */
    @Select("SELECT " +
            "orders.saleid," +
            "SUM (orders.frozepay) as frozepay " +
            "FROM " +
            "orders orders " +
            "WHERE " +
            "orders.orderstatus=5 and " +
            "orders.paymentmethod=0 " +
            "AND ( " +
            "orders.buyerinspectiontime + INTERVAL ${intervalday}" +
            ") < #{nowtime}  " +
            "GROUP BY " +
            "orders.saleid")
    public List<SellerSettleModel> getSellerSettleList(@Param("intervalday") String intervalday, @Param("nowtime") Date nowtime);

    /*
    @Select("SELECT " +
            "orders.* " +
            "FROM " +
            "orders orders " +
            "WHERE " +
            "orders.orderstatus=5 and " +
            "orders.paymentmethod=0 " +
            "AND ( " +
            "orders.buyerinspectiontime + INTERVAL ${intervalday}" +
            ") < #{nowtime}")
    public List<Orders> getSellerSettleOrdersList(@Param("intervalday") String intervalday, @Param("nowtime") Date nowtime);
    */

    @Select("select o.* from orders o,(\n" +
            "select so.orderid,so,orderno from sellerbill sb,sellerbillorder so where sb.id=so.sellerbillid and sb.state=99) t \n" +
            "where o.id=t.orderid and o.paymentmethod=0 and o.billtoserver=1 and o.orderstatus=5")
    public List<Orders> getSellerSettleOrdersList();


    /**
     * 获取卖家冻结金额到期资金列表
     *
     * @param intervalday 订单验收时间加多少天
     * @param nowtime     当前时间
     * @return
     */
    @Select("SELECT " +
            "scap.id,scap.settlement " +
            "FROM " +
            "salercapital scap " +
            "LEFT JOIN orders orders ON scap.orderno = orders.orderno " +
            "WHERE " +
            "orders.orderstatus = 5 " +
            "AND scap.settlement=0 " +
            "AND ( " +
            "scap.capitaltype = 0 " +
            "OR scap.capitaltype = 3 " +
            ") " +
            "AND ( " +
            "orders.buyerinspectiontime + INTERVAL ${intervalday} " +
            ") < #{nowtime}")
    public List<SalerCapital> getSalerCapitalList(@Param("intervalday") String intervalday, @Param("nowtime") Date nowtime);

    /**
     * 获取卖家商品评分总分
     *
     * @param sellerid
     * @return
     */
    @Select("SELECT SUM (eva1) AS eva1sum,SUM (eva2) AS eva2sum,SUM (eva3) AS eva3sum FROM orderproduct WHERE evaluatestate = 1 AND sellerid=#{sellerid}")
    public EvaModel getProductEvaSum(Long sellerid);

    /**
     * 获取单个商品评分总分
     *
     * @param pdid
     * @return
     */
    @Select("SELECT SUM (eva1) AS eva1sum,SUM (eva2) AS eva2sum,SUM (eva3) AS eva3sum FROM orderproduct WHERE pdid=#{pdid}")
    public EvaModel getSingleProductEvaSum(Long pdid);

    /**
     * 获取卖家商品评分总数
     *
     * @param sellerid
     * @return
     */
    @Select("SELECT count(*) FROM orderproduct WHERE evaluatestate = 1 AND sellerid=#{sellerid}")
    public int getProductEvaNum(Long sellerid);


    /**
     * 获取单个商品评分总数
     *
     * @param pdid
     * @return
     */
    @Select("SELECT count(*) FROM orderproduct WHERE evaluatestate=1 and pdid=#{pdid}")
    public Long getSingleProductEvaNum(Long pdid);


    @Select("select o.*,pi.selfsupport from orders o left join orderproduct op on o.orderno = op.orderno \n" +
            "left join productinfo pi on op.pdid = pi.id where o.orderno=#{orderno} order by o.id desc limit 1")
    Orders getOrdersByOrderNo(@Param("orderno") String orderno);

    /**
     * 获取订单总的佣金
     *
     * @param param
     * @return
     */
    @SelectProvider(type = OrdersSumBrokerProvider.class, method = "queryByParam")
    public BigDecimal getOrdersSumBroker(OrderQueryParam param);




    /**
     * 某个卖家超时发货总数
     * @param endtime
     * @param saleid
     * @param orderstatus
     * @return
     */
    @Select("<script>SELECT count(0) " +
            " from orders o " +
            "<where> 1=1 " +
            "<if test=\"endtime != null\">and o.paymenttime &lt;= #{endtime} </if>" +
            "<if test=\"saleid != null and saleid!='' \">and o.saleid = #{saleid} </if>" +
            "<if test=\"orderstatus != null and orderstatus!='' \">and o.orderstatus = #{orderstatus} </if>" +
            "and o.sellerdeliverytime is null "+
            "</where>" +
            "</script>")
    int getOverTimeSendOrderNum(@Param("endtime")Date endtime,@Param("saleid") Long saleid,@Param("orderstatus")Short orderstatus);

    /**
     * 某个卖家超时1天订单数量
     * @param starttime
     * @param endtime
     * @param saleid
     * @param orderstatus
     * @return
     */
    @Select("<script>SELECT count(0) " +
            " from orders o " +
            "<where> 1=1 " +
            "<if test=\"starttime != null\">and o.paymenttime &gt;= #{starttime} </if>" +
            "<if test=\"endtime != null\">and o.paymenttime &lt;= #{endtime} </if>" +
            "<if test=\"saleid != null and saleid!='' \">and o.saleid = #{saleid} </if>" +
            "<if test=\"orderstatus != null and orderstatus!='' \">and o.orderstatus = #{orderstatus} </if>" +
            "and o.sellerdeliverytime is null "+
            "</where>" +
            "</script>")
    int getOverTime1DaySendOrderNum(@Param("starttime")Date starttime,@Param("endtime") Date endtime,@Param("saleid") Long saleid,@Param("orderstatus")Short orderstatus);

    /**
     * 某个卖家超时2天订单数量
     * @param starttime
     * @param endtime
     * @param saleid
     * @param orderstatus
     * @return
     */
    @Select("<script>SELECT count(0) " +
            " from orders o " +
            "<where> 1=1 " +
            "<if test=\"starttime != null\">and o.paymenttime &gt;= #{starttime} </if>" +
            "<if test=\"endtime != null\">and o.paymenttime &lt;= #{endtime} </if>" +
            "<if test=\"saleid != null and saleid!='' \">and o.saleid = #{saleid} </if>" +
            "<if test=\"orderstatus != null and orderstatus!='' \">and o.orderstatus = #{orderstatus} </if>" +
            "and o.sellerdeliverytime is null "+
            "</where>" +
            "</script>")
    int getOverTime2DaySendOrderNum(@Param("starttime")Date starttime,@Param("endtime") Date endtime,@Param("saleid") Long saleid,@Param("orderstatus")Short orderstatus);

    /**
     * 某个卖家超时3天订单数量
     * @param endtime
     * @param saleid
     * @param orderstatus
     * @return
     */
    @Select("<script>SELECT count(0) " +
            " from orders o " +
            "<where> 1=1 " +
            "<if test=\"endtime != null\">and o.paymenttime &lt;= #{endtime} </if>" +
            "<if test=\"saleid != null and saleid!='' \">and o.saleid = #{saleid} </if>" +
            "<if test=\"orderstatus != null and orderstatus!='' \">and o.orderstatus = #{orderstatus} </if>" +
            "and o.sellerdeliverytime is null "+
            "</where>" +
            "</script>")
    int getOverTime3DaySendOrderNum(@Param("endtime") Date endtime,@Param("saleid") Long saleid,@Param("orderstatus")Short orderstatus);


    public class OrdersSumBrokerProvider {

        private final String TBL_ORDER = "orders od";
        private final String TBL_MEMBER = "member m on od.saleid=m.id";
        private final String TBL_MEMBER2 = "member mm on od.memberid=mm.id";
        private final String TBL_BUYERCOMPANYINFO = "buyercompanyinfo bci on od.memberid=bci.memberid";

        public String queryByParam(OrderQueryParam param) {

            SQL sql = new SQL().SELECT("sum(od.brokepay)").FROM(TBL_ORDER);
            sql.LEFT_OUTER_JOIN(TBL_MEMBER);
            sql.LEFT_OUTER_JOIN(TBL_BUYERCOMPANYINFO);
            sql.LEFT_OUTER_JOIN(TBL_MEMBER2);

            if (StringUtils.hasText(param.getClerkname())) {
                String clerkName = "%" + param.getClerkname() + "%";
                param.setClerkname(clerkName);
                sql.WHERE(" od.clerkname like #{clerkname} ");
            }

            if (StringUtils.hasText(param.getWaysalesman())) {
                String waySalesMan = "%" + param.getWaysalesman() + "%";
                param.setWaysalesman(waySalesMan);
                sql.WHERE(" od.waysalesman like #{waysalesman} ");
            }

            if (StringUtils.hasText(param.getMemberName())) {
                String memberName = "%" + param.getMemberName() + "%";
                param.setMemberName(memberName);
                sql.WHERE("(mm.realname LIKE #{memberName} or mm.username LIKE #{memberName} or bci.companyname LIKE #{memberName})");
            }
            if (StringUtils.hasText(param.getSellerName())) {
                String sellerName = "%" + param.getSellerName() + "%";
                param.setSellerName(sellerName);
                sql.WHERE("(od.membercompany like #{sellerName} or m.username like #{sellerName})");
            }
            if (param.getIsonline() != null) {
                sql.WHERE("od.isonline=#{isonline}");
            }
            if (param.getPayType() != null) {
                sql.WHERE("od.paytype=#{payType}");
            }
            if (param.getOrderType() != null) {
                sql.WHERE("od.ordertype=#{orderType}");
            }
            if (StringUtils.hasText(param.getOrderNo())) {
                String orderNo = "%" + param.getOrderNo() + "%";
                param.setOrderNo(orderNo);
                sql.WHERE("od.orderno LIKE #{orderNo}");
            }
            if (StringUtils.hasText(param.getCode())) {
                String code = "%" + param.getCode() + "%";
                param.setCode(code);
                sql.WHERE("od.code LIKE #{code}");
            }
            if (StringUtils.hasText(param.getTranNo())) {
                String tranNo = "%" + param.getTranNo() + "%";
                param.setTranNo(tranNo);
                sql.WHERE("od.transactionnumber LIKE #{tranNo}");
            }

            if (StringUtils.hasText(param.getShipto())) {
                String shipto = "%" + param.getShipto() + "%";
                param.setShipto(shipto);
                sql.WHERE("od.shipto LIKE #{shipto}");
            }

            if(param.getDeliverytype() != null){
                sql.WHERE(" deliverytype=#{deliverytype} ");
            }


            if (StringUtils.hasText(param.getStand())) {
                String stand = "%" + param.getStand() + "%";
                param.setStand(stand);
                sql.WHERE("od.orderno in (select orderno from orderproduct where standard like #{stand})");
            }
            Short sendstatus = param.getSendstatus();
            if (param.getOrderState() != null) {
                if(param.getOrderState().toString().equals("11")){
                    sql.WHERE(" (od.orderstatus=7 and  od.reason='卖家取消订单')");
                }else if(param.getOrderState()== 3 && sendstatus== 3){
                    //后台管理--订单管理--待发货 传3待收货  但是要查状态为3待收货和10部分发货的
                    sql.WHERE(" (od.orderstatus='3' or od.orderstatus='10')");
                }else if(param.getOrderState()== 1 && sendstatus== 10){
                    //后台管理--待发货  orderstate只传1 但是要查状态为1待发货和10部分发货的
                    sql.WHERE(" (od.orderstatus='1' or od.orderstatus='10')");
                }else {
                    sql.WHERE(" od.orderstatus=#{orderState} ");
                }
            }else{
                sql.WHERE(" (od.orderstatus!=7 and  od.orderstatus!=0)");
            }
            if(StringUtils.hasText(param.getTransactionid())){
                sql.WHERE("transactionid = #{transactionid}");
            }
            if (StringUtils.hasText(param.getOrderStates())) {
                List<String> list = new ArrayList<>(Arrays.asList(param.getOrderStates().split(",")));
                List<String> removeList = new ArrayList<>();
                StringBuffer sb = new StringBuffer("(");
                int count = 0;
                for (String i:list){
                    if (i.equals("11")) {
                        if(count>0){sb.append(" or ");}
                        sb.append(" (od.orderstatus='7' and  od.reason='卖家取消订单') ");
                        removeList.add("11");
                        count++;
                    }
                    if (i.equals("3")) {
                        if(count>0){sb.append(" or ");}
                        //后台管理--订单管理--待发货 传3待收货  但是要查状态为3待收货和10部分发货的
                        sb.append("  (od.orderstatus='3' or od.orderstatus='10') ");
                        removeList.add("3");
                        count++;
                    }
                    if(i.equals("1")){
                        if(count>0){sb.append(" or ");}
                        //后台管理--待发货  orderstate只传1 但是要查状态为1待发货和10部分发货的
                        sb.append("  (od.orderstatus='1' or od.orderstatus='10')  ");
                        removeList.add("1");
                        count++;
                    }
                }
                if (removeList.size()>0){
                    list.removeAll(removeList);
                }
                String str = String.join(",", list);
                if(StringUtils.hasText(str)){
                    if(count>0){sb.append(" or ");}
                    sb.append(" od.orderstatus in ("+str+") ");
                }
                sb.append(")");
                sql.WHERE(sb.toString());
            }


            if (param.getShopName() != null) {
                String shopName = "%" + param.getShopName() + "%";
                param.setShopName(shopName);
                sql.WHERE("od.shopname LIKE #{shopName}");
            }
            if (param.getStartTime() != null) {
                sql.WHERE("od.createtime >=#{startTime}");
            }
            if (param.getEndTime() != null) {
                Calendar c = Calendar.getInstance();
                c.setTime(param.getEndTime());
                c.add(Calendar.DAY_OF_MONTH, 1);// 今天+1天
                Date tomorrow = c.getTime();
                param.setEndTime(tomorrow);
                sql.WHERE("od.createtime <=#{endTime}");
            }

            if (param.getRegisterTimeStart() != null) {
                sql.WHERE("mm.createdate >=#{registerTimeStart}");
            }
            if (param.getRegisterTimeEnd() != null) {
                Calendar c = Calendar.getInstance();
                c.setTime(param.getRegisterTimeEnd());
                c.add(Calendar.DAY_OF_MONTH, 1);// 今天+1天
                Date tomorrow = c.getTime();
                param.setRegisterTimeEnd(tomorrow);
                sql.WHERE("mm.createdate <=#{registerTimeEnd}");
            }

            return sql.toString();
        }
    }


    /**
     * 所有订单金额
     *
     * @return
     */
    @SelectProvider(type = OrderNumProvider.class, method = "queryByParam")
    public BigDecimal getOrdersSum(OrderQueryParam param);

    public class OrderNumProvider {

        private final String TBL_ORDER = "orders od";
        private final String TBL_BUYERCOMPANYINFO = "buyercompanyinfo bci on od.memberid=bci.memberid";
        private final String TBL_MEMBER = "member m on od.saleid=m.id";
        private final String TBL_MEMBER2 = "member mm on od.memberid=mm.id";
        //private final String TBL_BUYERCOMPANYINFO2 = "buyercompanyinfo bcii on mm.id=bcii.memberid";

        public String queryByParam(OrderQueryParam param) {

            SQL sql = new SQL().SELECT("sum(od.totalprice)").FROM(TBL_ORDER);
            sql.LEFT_OUTER_JOIN(TBL_MEMBER);
            sql.LEFT_OUTER_JOIN(TBL_BUYERCOMPANYINFO);
            sql.LEFT_OUTER_JOIN(TBL_MEMBER2);

            if (StringUtils.hasText(param.getClerkname())) {
                String clerkName = "%" + param.getClerkname() + "%";
                param.setClerkname(clerkName);
                sql.WHERE(" od.clerkname like #{clerkname} ");
            }

            if (StringUtils.hasText(param.getWaysalesman())) {
                String waySalesMan = "%" + param.getWaysalesman() + "%";
                param.setWaysalesman(waySalesMan);
                sql.WHERE(" od.waysalesman like #{waysalesman} ");
            }
            if(StringUtils.hasText(param.getTransactionid())){
                sql.WHERE("transactionid = #{transactionid}");
            }

            if (StringUtils.hasText(param.getMemberName())) {
                String memberName = "%" + param.getMemberName() + "%";
                param.setMemberName(memberName);
                sql.WHERE("(mm.realname LIKE #{memberName} or mm.username LIKE #{memberName} or bci.companyname LIKE #{memberName})");
            }
            if (StringUtils.hasText(param.getSellerName())) {
                String sellerName = "%" + param.getSellerName() + "%";
                param.setSellerName(sellerName);
                sql.WHERE("(od.membercompany like #{sellerName} or m.username like #{sellerName})");
            }
            if (param.getIsonline() != null) {
                sql.WHERE("od.isonline=#{isonline}");
            }
            if (param.getPayType() != null) {
                sql.WHERE("od.paytype=#{payType}");
            }
            if (param.getOrderType() != null) {
                sql.WHERE("od.ordertype=#{orderType}");
            }
            if (StringUtils.hasText(param.getOrderNo())) {
                String orderNo = "%" + param.getOrderNo() + "%";
                param.setOrderNo(orderNo);
                sql.WHERE("od.orderno LIKE #{orderNo}");
            }
            if (StringUtils.hasText(param.getCode())) {
                String code = "%" + param.getCode() + "%";
                param.setCode(code);
                sql.WHERE("od.code LIKE #{code}");
            }
            if (StringUtils.hasText(param.getTranNo())) {
                String tranNo = "%" + param.getTranNo() + "%";
                param.setTranNo(tranNo);
                sql.WHERE("od.transactionnumber LIKE #{tranNo}");
            }

            if (StringUtils.hasText(param.getShipto())) {
                String shipto = "%" + param.getShipto() + "%";
                param.setShipto(shipto);
                sql.WHERE("od.shipto LIKE #{shipto}");
            }

            if(param.getDeliverytype() != null){
                sql.WHERE(" deliverytype=#{deliverytype} ");
            }

            if (StringUtils.hasText(param.getStand())) {
                String stand = "%" + param.getStand() + "%";
                param.setStand(stand);
                sql.WHERE("od.orderno in (select orderno from orderproduct where standard like #{stand})");
            }
            Short sendstatus = param.getSendstatus();
            if (param.getOrderState() != null) {
                if(param.getOrderState().toString().equals("11")){
                    sql.WHERE(" (od.orderstatus=7 and  od.reason='卖家取消订单')");
                }else if(param.getOrderState()== 3 && sendstatus== 3){
                    //后台管理--订单管理--待发货 传3待收货  但是要查状态为3待收货和10部分发货的
                    sql.WHERE(" (od.orderstatus='3' or od.orderstatus='10')");
                }else if(param.getOrderState()== 1 && sendstatus== 10){
                    //后台管理--待发货  orderstate只传1 但是要查状态为1待发货和10部分发货的
                    sql.WHERE(" (od.orderstatus='1' or od.orderstatus='10')");
                }else {
                    sql.WHERE(" od.orderstatus=#{orderState} ");
                }
            }else{
                sql.WHERE(" (od.orderstatus!=7 and  od.orderstatus!=0)");
            }
            if(StringUtils.hasText(param.getTransactionid())){
                sql.WHERE("transactionid = #{transactionid}");
            }
            if (StringUtils.hasText(param.getOrderStates())) {
                List<String> list = new ArrayList<>(Arrays.asList(param.getOrderStates().split(",")));
                List<String> removeList = new ArrayList<>();
                StringBuffer sb = new StringBuffer("(");
                int count = 0;
                for (String i:list){
                    if (i.equals("11")) {
                        if(count>0){sb.append(" or ");}
                        sb.append(" (od.orderstatus='7' and  od.reason='卖家取消订单') ");
                        removeList.add("11");
                        count++;
                    }
                    if (i.equals("3")) {
                        if(count>0){sb.append(" or ");}
                        //后台管理--订单管理--待发货 传3待收货  但是要查状态为3待收货和10部分发货的
                        sb.append("  (od.orderstatus='3' or od.orderstatus='10') ");
                        removeList.add("3");
                        count++;
                    }
                    if(i.equals("1")){
                        if(count>0){sb.append(" or ");}
                        //后台管理--待发货  orderstate只传1 但是要查状态为1待发货和10部分发货的
                        sb.append("  (od.orderstatus='1' or od.orderstatus='10')  ");
                        removeList.add("1");
                        count++;
                    }
                }
                if (removeList.size()>0){
                    list.removeAll(removeList);
                }
                String str = String.join(",", list);
                if(StringUtils.hasText(str)){
                    if(count>0){sb.append(" or ");}
                    sb.append(" od.orderstatus in ("+str+") ");
                }
                sb.append(")");
                sql.WHERE(sb.toString());
            }
            if (param.getShopName() != null) {
                String shopName = "%" + param.getShopName() + "%";
                param.setShopName(shopName);
                sql.WHERE("od.shopname LIKE #{shopName}");
            }
            if (param.getStartTime() != null) {
                sql.WHERE("od.createtime >=#{startTime}");
            }
            if (param.getEndTime() != null) {
                Calendar c = Calendar.getInstance();
                c.setTime(param.getEndTime());
                c.add(Calendar.DAY_OF_MONTH, 1);// 今天+1天
                Date tomorrow = c.getTime();
                param.setEndTime(tomorrow);
                sql.WHERE("od.createtime <=#{endTime}");
            }

            if (param.getRegisterTimeStart() != null) {
                sql.WHERE("mm.createdate >=#{registerTimeStart}");
            }
            if (param.getRegisterTimeEnd() != null) {
                Calendar c = Calendar.getInstance();
                c.setTime(param.getRegisterTimeEnd());
                c.add(Calendar.DAY_OF_MONTH, 1);// 今天+1天
                Date tomorrow = c.getTime();
                param.setRegisterTimeEnd(tomorrow);
                sql.WHERE("mm.createdate <=#{registerTimeEnd}");
            }

            return sql.toString();
        }
    }


    /**
     * 获取订单总运费
     *
     * @return
     */
    @SelectProvider(type = OrderFreightProvider.class, method = "queryByParam")
    public BigDecimal getOrderFreight(OrderQueryParam param);

    public class OrderFreightProvider {

        private final String TBL_ORDER = "orders od";
        private final String TBL_BUYERCOMPANYINFO = "buyercompanyinfo bci on od.memberid=bci.memberid";
        private final String TBL_MEMBER = "member m on od.saleid=m.id";
        private final String TBL_MEMBER2 = "member mm on od.memberid=mm.id";


        public String queryByParam(OrderQueryParam param) {

            SQL sql = new SQL().SELECT("sum(od.freight)").FROM(TBL_ORDER);
            sql.LEFT_OUTER_JOIN(TBL_MEMBER);
            sql.LEFT_OUTER_JOIN(TBL_BUYERCOMPANYINFO);
            sql.LEFT_OUTER_JOIN(TBL_MEMBER2);

            if (StringUtils.hasText(param.getClerkname())) {
                String clerkName = "%" + param.getClerkname() + "%";
                param.setClerkname(clerkName);
                sql.WHERE(" od.clerkname like #{clerkname} ");
            }

            if (StringUtils.hasText(param.getWaysalesman())) {
                String waySalesMan = "%" + param.getWaysalesman() + "%";
                param.setWaysalesman(waySalesMan);
                sql.WHERE(" od.waysalesman like #{waysalesman} ");
            }

            if (StringUtils.hasText(param.getMemberName())) {
                String memberName = "%" + param.getMemberName() + "%";
                param.setMemberName(memberName);
                sql.WHERE("(mm.realname LIKE #{memberName} or mm.username LIKE #{memberName} or bci.companyname LIKE #{memberName})");
            }
            if (StringUtils.hasText(param.getSellerName())) {
                String sellerName = "%" + param.getSellerName() + "%";
                param.setSellerName(sellerName);
                sql.WHERE("(od.membercompany like #{sellerName} or m.username like #{sellerName})");
            }
            if (param.getIsonline() != null) {
                sql.WHERE("od.isonline=#{isonline}");
            }
            if (param.getPayType() != null) {
                sql.WHERE("od.paytype=#{payType}");
            }
            if (param.getOrderType() != null) {
                sql.WHERE("od.ordertype=#{orderType}");
            }
            if (StringUtils.hasText(param.getOrderNo())) {
                String orderNo = "%" + param.getOrderNo() + "%";
                param.setOrderNo(orderNo);
                sql.WHERE("od.orderno LIKE #{orderNo}");
            }
            if (StringUtils.hasText(param.getCode())) {
                String code = "%" + param.getCode() + "%";
                param.setCode(code);
                sql.WHERE("od.code LIKE #{code}");
            }
            if (StringUtils.hasText(param.getTranNo())) {
                String tranNo = "%" + param.getTranNo() + "%";
                param.setTranNo(tranNo);
                sql.WHERE("od.transactionnumber LIKE #{tranNo}");
            }

            if (StringUtils.hasText(param.getShipto())) {
                String shipto = "%" + param.getShipto() + "%";
                param.setShipto(shipto);
                sql.WHERE("od.shipto LIKE #{shipto}");
            }

            if(param.getDeliverytype() != null){
                sql.WHERE(" deliverytype=#{deliverytype} ");
            }

            if (StringUtils.hasText(param.getStand())) {
                String stand = "%" + param.getStand() + "%";
                param.setStand(stand);
                sql.WHERE("od.orderno in (select orderno from orderproduct where standard like #{stand})");
            }
            Short sendstatus = param.getSendstatus();
            if (param.getOrderState() != null) {
                if(param.getOrderState().toString().equals("11")){
                    sql.WHERE(" (od.orderstatus=7 and  od.reason='卖家取消订单')");
                }else if(param.getOrderState()== 3 && sendstatus== 3){
                    //后台管理--订单管理--待发货 传3待收货  但是要查状态为3待收货和10部分发货的
                    sql.WHERE(" (od.orderstatus='3' or od.orderstatus='10')");
                }else if(param.getOrderState()== 1 && sendstatus== 10){
                    //后台管理--待发货  orderstate只传1 但是要查状态为1待发货和10部分发货的
                    sql.WHERE(" (od.orderstatus='1' or od.orderstatus='10')");
                }else {
                    sql.WHERE(" od.orderstatus=#{orderState} ");
                }
            }else{
                sql.WHERE(" (od.orderstatus!=7 and  od.orderstatus!=0)");
            }
            if(StringUtils.hasText(param.getTransactionid())){
                sql.WHERE("transactionid = #{transactionid}");
            }
            if (StringUtils.hasText(param.getOrderStates())) {
                List<String> list = new ArrayList<>(Arrays.asList(param.getOrderStates().split(",")));
                List<String> removeList = new ArrayList<>();
                StringBuffer sb = new StringBuffer("(");
                int count = 0;
                for (String i:list){
                    if (i.equals("11")) {
                        if(count>0){sb.append(" or ");}
                        sb.append(" (od.orderstatus='7' and  od.reason='卖家取消订单') ");
                        removeList.add("11");
                        count++;
                    }
                    if (i.equals("3")) {
                        if(count>0){sb.append(" or ");}
                        //后台管理--订单管理--待发货 传3待收货  但是要查状态为3待收货和10部分发货的
                        sb.append("  (od.orderstatus='3' or od.orderstatus='10') ");
                        removeList.add("3");
                        count++;
                    }
                    if(i.equals("1")){
                        if(count>0){sb.append(" or ");}
                        //后台管理--待发货  orderstate只传1 但是要查状态为1待发货和10部分发货的
                        sb.append("  (od.orderstatus='1' or od.orderstatus='10')  ");
                        removeList.add("1");
                        count++;
                    }
                }
                if (removeList.size()>0){
                    list.removeAll(removeList);
                }
                String str = String.join(",", list);
                if(StringUtils.hasText(str)){
                    if(count>0){sb.append(" or ");}
                    sb.append(" od.orderstatus in ("+str+") ");
                }
                sb.append(")");
                sql.WHERE(sb.toString());
            }


            if (param.getShopName() != null) {
                String shopName = "%" + param.getShopName() + "%";
                param.setShopName(shopName);
                sql.WHERE("od.shopname LIKE #{shopName}");
            }
            if (param.getStartTime() != null) {
                sql.WHERE("od.createtime >=#{startTime}");
            }
            if (param.getEndTime() != null) {
                Calendar c = Calendar.getInstance();
                c.setTime(param.getEndTime());
                c.add(Calendar.DAY_OF_MONTH, 1);// 今天+1天
                Date tomorrow = c.getTime();
                param.setEndTime(tomorrow);
                sql.WHERE("od.createtime <=#{endTime}");
            }

            if (param.getRegisterTimeStart() != null) {
                sql.WHERE("mm.createdate >=#{registerTimeStart}");
            }
            if (param.getRegisterTimeEnd() != null) {
                Calendar c = Calendar.getInstance();
                c.setTime(param.getRegisterTimeEnd());
                c.add(Calendar.DAY_OF_MONTH, 1);// 今天+1天
                Date tomorrow = c.getTime();
                param.setRegisterTimeEnd(tomorrow);
                sql.WHERE("mm.createdate <=#{registerTimeEnd}");
            }

            return sql.toString();
        }
    }

    /**
     * 获取总订货量
     *
     * @return
     */
    @SelectProvider(type = OrderTotalNumProvider.class, method = "queryByParam")
    public BigDecimal getOrdersTotalNum(OrderQueryParam param);

    public class OrderTotalNumProvider {

        private final String TBL_ORDER_PRODUCT = "orderproduct op";
        private final String TBL_ORDER = "orders od on op.orderid=od.id";
        private final String TBL_MEMBER = "member m on od.saleid=m.id";
        private final String TBL_MEMBER2 = "member mm on od.memberid=mm.id";
        private final String TBL_BUYERCOMPANYINFO = "buyercompanyinfo bci on od.memberid=bci.memberid";


        public String queryByParam(OrderQueryParam param) {

            SQL sql = new SQL().SELECT("sum(op.num)").FROM(TBL_ORDER_PRODUCT);

            sql.LEFT_OUTER_JOIN(TBL_ORDER);
            sql.LEFT_OUTER_JOIN(TBL_MEMBER);
            sql.LEFT_OUTER_JOIN(TBL_BUYERCOMPANYINFO);
            sql.LEFT_OUTER_JOIN(TBL_MEMBER2);

            if (StringUtils.hasText(param.getClerkname())) {
                String clerkName = "%" + param.getClerkname() + "%";
                param.setClerkname(clerkName);
                sql.WHERE(" od.clerkname like #{clerkname} ");
            }

            if (StringUtils.hasText(param.getWaysalesman())) {
                String waySalesMan = "%" + param.getWaysalesman() + "%";
                param.setWaysalesman(waySalesMan);
                sql.WHERE(" od.waysalesman like #{waysalesman} ");
            }

            if (StringUtils.hasText(param.getMemberName())) {
                String memberName = "%" + param.getMemberName() + "%";
                param.setMemberName(memberName);
                sql.WHERE("(mm.realname LIKE #{memberName} or mm.username LIKE #{memberName} or bci.companyname LIKE #{memberName})");
            }
            if (StringUtils.hasText(param.getSellerName())) {
                String sellerName = "%" + param.getSellerName() + "%";
                param.setSellerName(sellerName);
                sql.WHERE("(od.membercompany like #{sellerName} or m.username like #{sellerName})");
            }
            if (param.getIsonline() != null) {
                sql.WHERE("od.isonline=#{isonline}");
            }
            if (param.getPayType() != null) {
                sql.WHERE("od.paytype=#{payType}");
            }
            if (param.getOrderType() != null) {
                sql.WHERE("od.ordertype=#{orderType}");
            }
            if (StringUtils.hasText(param.getOrderNo())) {
                String orderNo = "%" + param.getOrderNo() + "%";
                param.setOrderNo(orderNo);
                sql.WHERE("od.orderno LIKE #{orderNo}");
            }
            if (StringUtils.hasText(param.getCode())) {
                String code = "%" + param.getCode() + "%";
                param.setCode(code);
                sql.WHERE("od.code LIKE #{code}");
            }
            if (StringUtils.hasText(param.getTranNo())) {
                String tranNo = "%" + param.getTranNo() + "%";
                param.setTranNo(tranNo);
                sql.WHERE("od.transactionnumber LIKE #{tranNo}");
            }

            if (StringUtils.hasText(param.getShipto())) {
                String shipto = "%" + param.getShipto() + "%";
                param.setShipto(shipto);
                sql.WHERE("od.shipto LIKE #{shipto}");
            }

            if(param.getDeliverytype() != null){
                sql.WHERE(" deliverytype=#{deliverytype} ");
            }

            if (StringUtils.hasText(param.getStand())) {
                String stand = "%" + param.getStand() + "%";
                param.setStand(stand);
                sql.WHERE("od.orderno in (select orderno from orderproduct where standard like #{stand})");
            }
            Short sendstatus = param.getSendstatus();
            if (param.getOrderState() != null) {
                if(param.getOrderState().toString().equals("11")){
                    sql.WHERE(" (od.orderstatus=7 and  od.reason='卖家取消订单')");
                }else if(param.getOrderState()== 3 && sendstatus== 3){
                    //后台管理--订单管理--待发货 传3待收货  但是要查状态为3待收货和10部分发货的
                    sql.WHERE(" (od.orderstatus='3' or od.orderstatus='10')");
                }else if(param.getOrderState()== 1 && sendstatus== 10){
                    //后台管理--待发货  orderstate只传1 但是要查状态为1待发货和10部分发货的
                    sql.WHERE(" (od.orderstatus='1' or od.orderstatus='10')");
                }else {
                    sql.WHERE(" od.orderstatus=#{orderState} ");
                }
            }else{
                sql.WHERE(" (od.orderstatus!=7 and  od.orderstatus!=0)");
            }
            if(StringUtils.hasText(param.getTransactionid())){
                sql.WHERE("transactionid = #{transactionid}");
            }
            if (StringUtils.hasText(param.getOrderStates())) {
                List<String> list = new ArrayList<>(Arrays.asList(param.getOrderStates().split(",")));
                List<String> removeList = new ArrayList<>();
                StringBuffer sb = new StringBuffer("(");
                int count = 0;
                for (String i:list){
                    if (i.equals("11")) {
                        if(count>0){sb.append(" or ");}
                        sb.append(" (od.orderstatus='7' and  od.reason='卖家取消订单') ");
                        removeList.add("11");
                        count++;
                    }
                    if (i.equals("3")) {
                        if(count>0){sb.append(" or ");}
                        //后台管理--订单管理--待发货 传3待收货  但是要查状态为3待收货和10部分发货的
                        sb.append("  (od.orderstatus='3' or od.orderstatus='10') ");
                        removeList.add("3");
                        count++;
                    }
                    if(i.equals("1")){
                        if(count>0){sb.append(" or ");}
                        //后台管理--待发货  orderstate只传1 但是要查状态为1待发货和10部分发货的
                        sb.append("  (od.orderstatus='1' or od.orderstatus='10')  ");
                        removeList.add("1");
                        count++;
                    }
                }
                if (removeList.size()>0){
                    list.removeAll(removeList);
                }
                String str = String.join(",", list);
                if(StringUtils.hasText(str)){
                    if(count>0){sb.append(" or ");}
                    sb.append(" od.orderstatus in ("+str+") ");
                }
                sb.append(")");
                sql.WHERE(sb.toString());
            }


            if (param.getShopName() != null) {
                String shopName = "%" + param.getShopName() + "%";
                param.setShopName(shopName);
                sql.WHERE("od.shopname LIKE #{shopName}");
            }
            if (param.getStartTime() != null) {
                sql.WHERE("od.createtime >=#{startTime}");
            }
            if (param.getEndTime() != null) {
                Calendar c = Calendar.getInstance();
                c.setTime(param.getEndTime());
                c.add(Calendar.DAY_OF_MONTH, 1);// 今天+1天
                Date tomorrow = c.getTime();
                param.setEndTime(tomorrow);
                sql.WHERE("od.createtime <=#{endTime}");
            }

            if (param.getRegisterTimeStart() != null) {
                sql.WHERE("mm.createdate >=#{registerTimeStart}");
            }
            if (param.getRegisterTimeEnd() != null) {
                Calendar c = Calendar.getInstance();
                c.setTime(param.getRegisterTimeEnd());
                c.add(Calendar.DAY_OF_MONTH, 1);// 今天+1天
                Date tomorrow = c.getTime();
                param.setRegisterTimeEnd(tomorrow);
                sql.WHERE("mm.createdate <=#{registerTimeEnd}");
            }

            return sql.toString();
        }
    }

    /**
     * 获取总发货量
     *
     * @return
     */
    @SelectProvider(type = OrdersTotalDeliveryNumProvider.class, method = "queryByParam")
    public BigDecimal getOrdersTotalDeliveryNum(OrderQueryParam param);


    public class OrdersTotalDeliveryNumProvider {

        private final String TBL_ORDER_PRODUCT = "orderproduct op";
        private final String TBL_ORDER = "orders od on op.orderid=od.id";
        private final String TBL_MEMBER = "member m on od.saleid=m.id";
        private final String TBL_MEMBER2 = "member mm on od.memberid=mm.id";
        private final String TBL_BUYERCOMPANYINFO = "buyercompanyinfo bci on od.memberid=bci.memberid";

        public String queryByParam(OrderQueryParam param) {

            SQL sql = new SQL().SELECT("sum(op.num)").FROM(TBL_ORDER_PRODUCT);

            sql.LEFT_OUTER_JOIN(TBL_ORDER);
            sql.LEFT_OUTER_JOIN(TBL_MEMBER);
            sql.LEFT_OUTER_JOIN(TBL_BUYERCOMPANYINFO);
            sql.LEFT_OUTER_JOIN(TBL_MEMBER2);

            sql.WHERE("op.backstate=0");

            if (StringUtils.hasText(param.getClerkname())) {
                String clerkName = "%" + param.getClerkname() + "%";
                param.setClerkname(clerkName);
                sql.WHERE(" od.clerkname like #{clerkname} ");
            }

            if (StringUtils.hasText(param.getWaysalesman())) {
                String waySalesMan = "%" + param.getWaysalesman() + "%";
                param.setWaysalesman(waySalesMan);
                sql.WHERE(" od.waysalesman like #{waysalesman} ");
            }

            if (StringUtils.hasText(param.getMemberName())) {
                String memberName = "%" + param.getMemberName() + "%";
                param.setMemberName(memberName);
                sql.WHERE("(mm.realname LIKE #{memberName} or mm.username LIKE #{memberName} or bci.companyname LIKE #{memberName})");
            }
            if (StringUtils.hasText(param.getSellerName())) {
                String sellerName = "%" + param.getSellerName() + "%";
                param.setSellerName(sellerName);
                sql.WHERE("(od.membercompany like #{sellerName} or m.username like #{sellerName})");
            }
            if (param.getIsonline() != null) {
                sql.WHERE("od.isonline=#{isonline}");
            }
            if (param.getPayType() != null) {
                sql.WHERE("od.paytype=#{payType}");
            }
            if (param.getOrderType() != null) {
                sql.WHERE("od.ordertype=#{orderType}");
            }
            if (StringUtils.hasText(param.getOrderNo())) {
                String orderNo = "%" + param.getOrderNo() + "%";
                param.setOrderNo(orderNo);
                sql.WHERE("od.orderno LIKE #{orderNo}");
            }
            if (StringUtils.hasText(param.getCode())) {
                String code = "%" + param.getCode() + "%";
                param.setCode(code);
                sql.WHERE("od.code LIKE #{code}");
            }
            if (StringUtils.hasText(param.getTranNo())) {
                String tranNo = "%" + param.getTranNo() + "%";
                param.setTranNo(tranNo);
                sql.WHERE("od.transactionnumber LIKE #{tranNo}");
            }

            if (StringUtils.hasText(param.getShipto())) {
                String shipto = "%" + param.getShipto() + "%";
                param.setShipto(shipto);
                sql.WHERE("od.shipto LIKE #{shipto}");
            }

            if(param.getDeliverytype() != null){
                sql.WHERE(" deliverytype=#{deliverytype} ");
            }

            if (StringUtils.hasText(param.getStand())) {
                String stand = "%" + param.getStand() + "%";
                param.setStand(stand);
                sql.WHERE("od.orderno in (select orderno from orderproduct where standard like #{stand})");
            }
            Short sendstatus = param.getSendstatus();
            if (param.getOrderState() != null) {
                if(param.getOrderState() == 11) {
                    sql.WHERE(" (od.orderstatus=7 and  od.reason='卖家取消订单')");
                }else if(param.getOrderState()== 3 && sendstatus== 3){
                    //后台管理--订单管理--待发货 传3待收货  但是要查状态为3待收货和10部分发货的
                    sql.WHERE(" (od.orderstatus='3' or od.orderstatus='10')");
                }else if(param.getOrderState()== 1 && sendstatus== 10){
                    //后台管理--待发货  orderstate只传1 但是要查状态为1待发货和10部分发货的
                    sql.WHERE(" (od.orderstatus='1' or od.orderstatus='10')");
                }else {
                    sql.WHERE(" od.orderstatus=#{orderState} ");
                }
            }else{
                sql.WHERE(" (od.orderstatus!=7 and  od.orderstatus!=0)");
            }
            if(StringUtils.hasText(param.getTransactionid())){
                sql.WHERE("transactionid = #{transactionid}");
            }
            if (StringUtils.hasText(param.getOrderStates())) {
                List<String> list = new ArrayList<>(Arrays.asList(param.getOrderStates().split(",")));
                List<String> removeList = new ArrayList<>();
                StringBuffer sb = new StringBuffer("(");
                int count = 0;
                for (String i:list){
                    if (i.equals("11")) {
                        if(count>0){sb.append(" or ");}
                        sb.append(" (od.orderstatus='7' and  od.reason='卖家取消订单') ");
                        removeList.add("11");
                        count++;
                    }
                    if (i.equals("3")) {
                        if(count>0){sb.append(" or ");}
                        //后台管理--订单管理--待发货 传3待收货  但是要查状态为3待收货和10部分发货的
                        sb.append("  (od.orderstatus='3' or od.orderstatus='10') ");
                        removeList.add("3");
                        count++;
                    }
                    if(i.equals("1")){
                        if(count>0){sb.append(" or ");}
                        //后台管理--待发货  orderstate只传1 但是要查状态为1待发货和10部分发货的
                        sb.append("  (od.orderstatus='1' or od.orderstatus='10')  ");
                        removeList.add("1");
                        count++;
                    }
                }
                if (removeList.size()>0){
                    list.removeAll(removeList);
                }
                String str = String.join(",", list);
                if(StringUtils.hasText(str)){
                    if(count>0){sb.append(" or ");}
                    sb.append(" od.orderstatus in ("+str+") ");
                }
                sb.append(")");
                sql.WHERE(sb.toString());
            }



            if (param.getShopName() != null) {
                String shopName = "%" + param.getShopName() + "%";
                param.setShopName(shopName);
                sql.WHERE("od.shopname LIKE #{shopName}");
            }
            if (param.getStartTime() != null) {
                sql.WHERE("od.createtime >=#{startTime}");
            }
            if (param.getEndTime() != null) {
                Calendar c = Calendar.getInstance();
                c.setTime(param.getEndTime());
                c.add(Calendar.DAY_OF_MONTH, 1);// 今天+1天
                Date tomorrow = c.getTime();
                param.setEndTime(tomorrow);
                sql.WHERE("od.createtime <=#{endTime}");
            }

            if (param.getRegisterTimeStart() != null) {
                sql.WHERE("mm.createdate >=#{registerTimeStart}");
            }
            if (param.getRegisterTimeEnd() != null) {
                Calendar c = Calendar.getInstance();
                c.setTime(param.getRegisterTimeEnd());
                c.add(Calendar.DAY_OF_MONTH, 1);// 今天+1天
                Date tomorrow = c.getTime();
                param.setRegisterTimeEnd(tomorrow);
                sql.WHERE("mm.createdate <=#{registerTimeEnd}");
            }

            return sql.toString();
        }
    }

    /**
     * 获取当日卖家违约订单数
     */
    @Select("select count(1) from orders where orderstatus = 7 and reason='卖家取消订单' and \n" +
            "to_char(paymenttime,'yyyy-MM-DD HH24:MI:SS')>= to_char(current_date,'yyyy-MM-DD HH24:MI:SS') AND paymenttime<=now()")
    public BigDecimal getSellerBreachOrders();

    /**
     * 根据ids数组计算总金额
     *
     * @param ids
     * @return
     */
    @Select("SELECT sum(totalprice)  from orders where id in (${ids})")
    public BigDecimal getOrdersSumByInIds(@Param("ids") String ids);

    /**
     * 根据ids数组获取订单
     *
     * @param ids
     * @return
     */
    @Select("SELECT id,orderno,totalprice,actualpayment,freight,allpay,deposit,balance,ordertype,orderstatus,saleid,paytype,memberid,uuid,yuuuid,discountprice from orders where id in (${ids})")
    public List<Orders> getOrdersByInIds(@Param("ids") String ids);


    /**
     * 根据ids数组获取订单
     *
     * @param ids
     * @return
     */
    @Select("SELECT * from orders where id in (${ids})")
    public List<Orders> getWMSOrdersByInIds(@Param("ids") String ids);

    /**
     * 批量插入
     *
     * @param list
     */
    @InsertProvider(type = OrderProvider.class, method = "insertAll")
    void insertAll(List<Orders> list);

    public class OrderProvider {
        public String insertAll(Map map) {
            List<Orders> list = (List<Orders>) map.get("list");
            StringBuilder sb = new StringBuilder();
            sb.append("INSERT INTO orders ");
            sb.append("(memberid,saleid,orderno,province,city,area,receivingaddress,freight,totalprice,code,ordertype,storename,storeid,isbilling,billingtype,uuid,membercompany,futuretime) ");
            sb.append("VALUES ");
            MessageFormat mf = new MessageFormat("(#'{'list[{0}].memberid},#'{'list[{0}].saleid},#'{'list[{0}].orderno},#'{'list[{0}].province},#'{'list[{0}].city},#'{'list[{0}].area},#'{'list[{0}].receivingaddress},#'{'list[{0}].freight},#'{'list[{0}].totalprice},#'{'list[{0}].code},#'{'list[{0}].ordertype},#'{'list[{0}].storename},#'{'list[{0}].storeid},#'{'list[{0}].isbilling},#'{'list[{0}].billingtype},#'{'list[{0}].uuid},#'{'list[{0}].membercompany},#'{'list[{0}].futuretime})");
            for (int i = 0; i < list.size(); i++) {
                sb.append(mf.format(new Object[]{i}));
                if (i < list.size() - 1) {
                    sb.append(",");
                }
            }
            return sb.toString();
        }
    }


    @SelectProvider(type = OrderExcelProvider.class, method = "queryOrderByParam")
    List<Map<String, Object>> getExcelOrders(OrderQueryParam param);


    public class OrderExcelProvider {

        private final String TBL_ORDER = "orders od on op.orderid=od.id";
        private final String TBL_ORDER_PRODUCT = "orderproduct op";
        private final String TBL_PRODUCT_INFO = "productinfo info on op.pdid=info.id";
        private final String TBL_BILL_ORDER = "billorder bo on bo.orderid=od.id";
        private final String TBL_BILL_RECORD = "billingrecord br on bo.billrecordid=br.id";


        public String queryOrderByParam(OrderQueryParam param) {
            StringBuffer sb = new StringBuffer();
            sb.append("od.createtime,od.code,od.orderno,od.transactionnumber,od.logisticscompany,od.couriernumber,od.membercompany,");
            sb.append("od.membername,od.sellername,od.transactionid,od.shipto,od.uuid,");
            sb.append(" m.realname as buyerrealname,m.clerkname,");
            sb.append(" bci.companyname as buyercompanyname, ");
            sb.append("CONCAT(od.province,od.city,od.area,od.receivingaddress) as receivingaddress,");
            sb.append("case od.paytype when 0 then '支付宝' when 1 then '微信' when 2 then '银行卡' when 3 then '余额' when 4 then '授信' end as paytype, ");//支付方式0=支付宝1=微信2=银行卡3=余额4=授信
            sb.append("case when od.ordertype=0 then '现货' else '远期' END as ordertype,");
            sb.append("case when od.isonline=1 then '线下' ELSE '线上' end as inonline,");
            sb.append("op.pdname,op.attrjson,op.standard,op.material,op.gradeno,op.brand,info.mark,");
            sb.append("info.surfacetreatment,info.packagetype,op.unit,op.price,op.num,op.actualpayment,info.level1,info.level2,info.level3,");

            sb.append("br.invoiceheadup,br.texno,br.bankofaccounts,br.account,br.address,br.phone,");
            sb.append("case when br.state=0 then '是' ELSE '否' END as brstate,");
            sb.append("case od.orderstatus when 0 then '待付款' when 1 then '待发货'");
            sb.append("when 3 then '待收货' when 4 then '待验货' when 5 then '已完成'");
            sb.append("when 7 then '已关闭' when 8 then '备货中' when 9 then '备货完成'");
            sb.append("ELSE '其它' END as orderstatus,");
            sb.append("case when od.isonline=2 then '限时购' ELSE '其它' END as odtype");

            SQL sql = new SQL().SELECT(sb.toString()).FROM(TBL_ORDER_PRODUCT);

            sql.JOIN(TBL_ORDER);
            sql.LEFT_OUTER_JOIN(TBL_PRODUCT_INFO);
            sql.LEFT_OUTER_JOIN(TBL_BILL_ORDER);
            sql.LEFT_OUTER_JOIN(TBL_BILL_RECORD);
            sql.LEFT_OUTER_JOIN(" member m on od.memberid=m.id ");
            sql.LEFT_OUTER_JOIN(" buyercompanyinfo  bci on od.memberid=bci.memberid ");


            if (StringUtils.hasText(param.getMemberName())) {
                String memberName = "%" + param.getMemberName() + "%";
                param.setMemberName(memberName);
                sql.WHERE("(m.realname like #{memberName} or bci.companyname like #{memberName})");
            }
            if (StringUtils.hasText(param.getSellerName())) {
                String sellerName = "%" + param.getSellerName() + "%";
                param.setSellerName(sellerName);
                sql.WHERE("(od.membercompany like #{sellerName} or od.shopname like #{sellerName})");
            }
            if (param.getIsonline() != null) {
                sql.WHERE("od.isonline=#{isonline}");
            }
            if (param.getPayType() != null) {
                sql.WHERE("od.paytype=#{payType}");
            }
            if (param.getOrderType() != null) {
                sql.WHERE("od.ordertype=#{orderType}");
            }
            if (StringUtils.hasText(param.getOrderNo())) {
                String orderNo = "%" + param.getOrderNo() + "%";
                param.setOrderNo(orderNo);
                sql.WHERE("od.orderno LIKE #{orderNo}");
            }
            if (StringUtils.hasText(param.getClerkname())) {
                String clerkName = "%" + param.getClerkname() + "%";
                param.setClerkname(clerkName);
                sql.WHERE(" od.clerkname like #{clerkname} ");
            }
            if(StringUtils.hasText(param.getTransactionid())){
                sql.WHERE("od.transactionid = #{transactionid}");
            }
            if (StringUtils.hasText(param.getWaysalesman())) {
                String waySalesMan = "%" + param.getWaysalesman() + "%";
                param.setWaysalesman(waySalesMan);
                sql.WHERE(" od.waysalesman like #{waysalesman} ");
            }
            if (StringUtils.hasText(param.getStand())) {
                String stand = "%" + param.getStand() + "%";
                param.setStand(stand);
                sql.WHERE("od.orderno in (select orderno from orderproduct where standard like #{stand})");
            }

            if (StringUtils.hasText(param.getCode())) {
                String code = "%" + param.getCode() + "%";
                param.setCode(code);
                sql.WHERE("od.code LIKE #{code}");
            }
            if (StringUtils.hasText(param.getTranNo())) {
                String tranNo = "%" + param.getTranNo() + "%";
                param.setTranNo(tranNo);
                sql.WHERE("od.transactionnumber LIKE #{tranNo}");
            }
            if (param.getOrderState() != null) {
                if(param.getOrderState().toString().equals("11")){
                    sql.WHERE(" (od.orderstatus='7' and  od.reason='卖家取消订单')");
                }else {
                    sql.WHERE(" od.orderstatus=#{orderState} ");
                }
            }
            if (StringUtils.hasText(param.getOrderStates())) {
                List<String> list = new ArrayList<>(Arrays.asList(param.getOrderStates().split(",")));
                List<String> removeList = new ArrayList<>();
                StringBuffer sbb = new StringBuffer("(");
                int count = 0;
                for (String i:list){
                    if (i.equals("11")) {
                        if(count>0){sbb.append(" or ");}
                        sbb.append(" (od.orderstatus='7' and  od.reason='卖家取消订单') ");
                        removeList.add("11");
                        count++;
                    }
                }
                if (removeList.size()>0){
                    list.removeAll(removeList);
                }
                String str = String.join(",", list);
                if(StringUtils.hasText(str)){
                    if(count>0){sbb.append(" or ");}
                    sbb.append(" od.orderstatus in ("+str+") ");
                }
                sbb.append(")");
                sql.WHERE(sbb.toString());
            }

            if (param.getShopName() != null) {
                String shopName = "%" + param.getShopName() + "%";
                param.setShopName(shopName);
                sql.WHERE("od.shopname LIKE #{shopName}");
            }

            if (param.getShipto() != null) {
                String shipto = "%" + param.getShipto() + "%";
                param.setShipto(shipto);
                sql.WHERE("od.shipto LIKE #{shipto}");
            }

            if (param.getDeliverytype() != null) {
                sql.WHERE("od.deliverytype = #{deliverytype}");
            }

            if (param.getStartTime() != null) {
                sql.WHERE("od.createtime >=#{startTime}");
            }
            if (param.getEndTime() != null) {
                Calendar c = Calendar.getInstance();
                c.setTime(param.getEndTime());
                c.add(Calendar.DAY_OF_MONTH, 1);// 今天+1天
                Date tomorrow = c.getTime();
                param.setEndTime(tomorrow);
                sql.WHERE("od.createtime <=#{endTime}");
            }

            if (param.getRegisterTimeStart() != null) {
                sql.WHERE("m.createdate >=#{registerTimeStart}");
            }
            if (param.getRegisterTimeEnd() != null) {
                Calendar c = Calendar.getInstance();
                c.setTime(param.getRegisterTimeEnd());
                c.add(Calendar.DAY_OF_MONTH, 1);// 今天+1天
                Date tomorrow = c.getTime();
                param.setRegisterTimeEnd(tomorrow);
                sql.WHERE("m.createdate <=#{registerTimeEnd}");
            }
            //超时时间
            if(param.getOvertime() != null) {
                //获取当前系统时间
                LocalTime time = LocalTime.now();
                int h = time.getHour();
                //获取今天时间16点
                String todaytime = DateUtils.LastOrNextDate(0) + " 16:00:00";
                Timestamp todaytime1 = Timestamp.valueOf(todaytime);
                param.setTodaytime(todaytime1);
                //获取昨天时间16点
                String yesterday = DateUtils.LastOrNextDate(-1) + " 16:00:00";
                Timestamp yesterday1 = Timestamp.valueOf(yesterday);
                param.setYesterdaytime(yesterday1);
                //获取前天时间16点
                String beforeyesterday = DateUtils.LastOrNextDate(-2) + " 16:00:00";
                Timestamp beforeyesterday1 = Timestamp.valueOf(beforeyesterday);
                param.setBeforeyesterdaytime(beforeyesterday1);
                //获取大前天时间16点
                String threedaysago = DateUtils.LastOrNextDate(-3) + " 16:00:00";
                Timestamp threedaysago1 = Timestamp.valueOf(beforeyesterday);
                param.setThreedaysagotime(threedaysago1);
                if (param.getOvertime() == 0) {
                    //昨天16点之前生成的未发货订单。
                    sql.WHERE("od.paymenttime < #{yesterdaytime} AND od.orderstatus = '1' AND sellerdeliverytime IS NULL");
                }
                if (param.getOvertime() == 1) {
                    //昨天16点之前(00:00 - 15:59)生成的未发货订单 + 前天16点及以后(16:00 - 24:00)生成的未发货订单
                    sql.WHERE("od.paymenttime > #{beforeyesterdaytime}  AND od.paymenttime < #{yesterdaytime} AND od.orderstatus = '1' AND od.sellerdeliverytime IS NULL");
                }
                if (param.getOvertime() == 2) {
                    //前天16点之前(00:00 - 15:59)生成的未发货订单 + 大前天16点及以后(16:00 - 24:00)生成的未发货订单
                    sql.WHERE("od.paymenttime > #{threedaysagotime}  AND od.paymenttime < #{beforeyesterdaytime} AND od.orderstatus = '1' AND od.sellerdeliverytime IS NULL");
                }
                if (param.getOvertime() == 3) {
                    //大前天16点之前生成的所有未发货订单
                    sql.WHERE("od.paymenttime < #{threedaysagotime} AND od.orderstatus = '1' AND od.sellerdeliverytime IS NULL");
                }
            }

            sql.ORDER_BY("od.createtime desc");

            return sql.toString();
        }
    }


    @SelectProvider(type = SellerBillOrderExcelProvider.class, method = "queryOrderByParam")
    List<Map<String, Object>> getExcelSellerBillOrders(@Param("saleid") Long saleid,@Param("orderid") String orderid);


    public class SellerBillOrderExcelProvider {

        private final String TBL_ORDER = "orders od on op.orderid=od.id";
        private final String TBL_ORDER_PRODUCT = "orderproduct op";
        private final String TBL_PRODUCT_INFO = "productinfo info on op.pdid=info.id";

        public String queryOrderByParam(@Param("saleid") Long saleid,@Param("orderid") String orderid) {
            StringBuffer sb = new StringBuffer();
            sb.append("od.createtime,od.code,od.orderno,od.transactionnumber,od.frozepay,od.freight as newfreight,");
            sb.append("case od.paytype when 0 then '支付宝' when 1 then '微信' when 2 then '银行卡' when 3 then '余额' when 4 then '授信' end as paytype, ");//支付方式0=支付宝1=微信2=银行卡3=余额4=授信
            sb.append("case when od.ordertype=0 then '现货' else '远期' END as ordertype,");
            sb.append("case when od.isonline=1 then '线下' ELSE '线上' end as inonline,");
            sb.append("op.pdname,op.attrjson,op.standard,op.material,op.gradeno,op.brand,op.singlebrokepay,info.mark,");
            sb.append("info.surfacetreatment,info.packagetype,op.unit,op.price,op.num,op.actualpayment,op.discountpay,info.level1,info.level2,info.level3,");
            sb.append("case od.orderstatus when 0 then '待付款' when 1 then '待发货'");
            sb.append("when 3 then '待收货' when 4 then '待验货' when 5 then '已完成'");
            sb.append("when 7 then '已关闭' when 8 then '备货中' when 9 then '备货完成'");
            sb.append("ELSE '其它' END as orderstatus");

            SQL sql = new SQL().SELECT(sb.toString()).FROM(TBL_ORDER_PRODUCT);

            sql.JOIN(TBL_ORDER);
            sql.LEFT_OUTER_JOIN(TBL_PRODUCT_INFO);
            sql.WHERE("od.saleid = #{saleid}");
            if (orderid != null && orderid != "") {
                sql.WHERE("od.id in ("+orderid+")");
            }
            //过滤掉 退货的
            sql.WHERE("op.backstate = 0");
            sql.ORDER_BY("od.createtime desc");

            return sql.toString();
        }
    }


    @SelectProvider(type = getOrdersCount.class, method = "queryOrderCountByParam")
    Long getOrdersCount(OrderQueryParam param);


    public class getOrdersCount {

        private final String TBL_ORDER = "orders od on op.orderid=od.id";
        private final String TBL_ORDER_PRODUCT = "orderproduct op";
        private final String TBL_PRODUCT_INFO = "productinfo info on op.pdid=info.id";
        private final String TBL_BILL_ORDER = "billorder bo on bo.orderid=od.id";
        private final String TBL_BILL_RECORD = "billingrecord br on bo.billrecordid=br.id";


        public String queryOrderCountByParam(OrderQueryParam param) {

            SQL sql = new SQL().SELECT("count(1)").FROM(TBL_ORDER_PRODUCT);

            sql.JOIN(TBL_ORDER);
            sql.LEFT_OUTER_JOIN(TBL_PRODUCT_INFO);
            sql.LEFT_OUTER_JOIN(TBL_BILL_ORDER);
            sql.LEFT_OUTER_JOIN(TBL_BILL_RECORD);
            sql.LEFT_OUTER_JOIN(" member m on od.memberid=m.id ");
            sql.LEFT_OUTER_JOIN(" buyercompanyinfo  bci on od.memberid=bci.memberid ");


            if (StringUtils.hasText(param.getMemberName())) {
                String memberName = "%" + param.getMemberName() + "%";
                param.setMemberName(memberName);
                sql.WHERE("(m.realname like #{memberName} or bci.companyname like #{memberName})");
            }
            if (StringUtils.hasText(param.getSellerName())) {
                String sellerName = "%" + param.getSellerName() + "%";
                param.setSellerName(sellerName);
                sql.WHERE("(od.membercompany like #{sellerName} or od.shopname like #{sellerName})");
            }
            if (param.getIsonline() != null) {
                sql.WHERE("od.isonline=#{isonline}");
            }
            if (param.getPayType() != null) {
                sql.WHERE("od.paytype=#{payType}");
            }
            if (param.getOrderType() != null) {
                sql.WHERE("od.ordertype=#{orderType}");
            }
            if (StringUtils.hasText(param.getOrderNo())) {
                String orderNo = "%" + param.getOrderNo() + "%";
                param.setOrderNo(orderNo);
                sql.WHERE("od.orderno LIKE #{orderNo}");
            }
            if (StringUtils.hasText(param.getClerkname())) {
                String clerkName = "%" + param.getClerkname() + "%";
                param.setClerkname(clerkName);
                sql.WHERE(" od.clerkname like #{clerkname} ");
            }

            if (StringUtils.hasText(param.getWaysalesman())) {
                String waySalesMan = "%" + param.getWaysalesman() + "%";
                param.setWaysalesman(waySalesMan);
                sql.WHERE(" od.waysalesman like #{waysalesman} ");
            }

            if (StringUtils.hasText(param.getStand())) {
                String stand = "%" + param.getStand() + "%";
                param.setStand(stand);
                sql.WHERE("od.orderno in (select orderno from orderproduct where standard like #{stand})");
            }

            if (StringUtils.hasText(param.getCode())) {
                String code = "%" + param.getCode() + "%";
                param.setCode(code);
                sql.WHERE("od.code LIKE #{code}");
            }
            if (StringUtils.hasText(param.getTranNo())) {
                String tranNo = "%" + param.getTranNo() + "%";
                param.setTranNo(tranNo);
                sql.WHERE("od.transactionnumber LIKE #{tranNo}");
            }
            if (param.getOrderState() != null) {
                if(param.getOrderState().toString().equals("11")){
                    sql.WHERE(" (od.orderstatus='7' and  od.reason='卖家取消订单')");
                }else {
                    sql.WHERE(" od.orderstatus=#{orderState} ");
                }
            }
            if (param.getShopName() != null) {
                String shopName = "%" + param.getShopName() + "%";
                param.setShopName(shopName);
                sql.WHERE("od.shopname LIKE #{shopName}");
            }
            if (param.getStartTime() != null) {
                sql.WHERE("od.createtime >=#{startTime}");
            }
            if (param.getEndTime() != null) {
                Calendar c = Calendar.getInstance();
                c.setTime(param.getEndTime());
                c.add(Calendar.DAY_OF_MONTH, 1);// 今天+1天
                Date tomorrow = c.getTime();
                param.setEndTime(tomorrow);
                sql.WHERE("od.createtime <=#{endTime}");
            }
            //sql.ORDER_BY("od.createtime desc");

            return sql.toString();
        }
    }

    @SelectProvider(type = ProductBackProvider.class, method = "queryOrderByParam")
    List<Map<String, Object>> getExcelProductBack(BackQueryParam backQueryParam);

    public class ProductBackProvider {
        private final String TBL_ORDER_PRODUCT_BACK = "orderproductback opb";
        private final String TBL_ORDER = "orders od on opb.orderid=od.id";
        private final String TBL_ORDER_PRODUCT = "orderproduct op on opb.orderpdid=op.id";
        private final String TBL_PRODUCT_INFO = "productinfo info on opb.pdid=info.id";

        public String queryOrderByParam(BackQueryParam backQueryParam) {

            StringBuffer sb = new StringBuffer();
            sb.append("od.createtime,od.code,od.orderno,od.transactionnumber,");
            sb.append("od.membername,od.sellername,opb.pdnum,opb.backmoney,opb.backno,");
            sb.append("case when od.ordertype=0 then '现货' else '远期' END as ordertype,");
            sb.append("case when od.isonline=1 then '线下' ELSE '线上' end as inonline,");
            sb.append("opb.pdname,op.attrjson,op.material,op.gradeno,op.brand,info.mark,");
            sb.append("info.surfacetreatment,info.packagetype,op.unit,op.price,op.num,op.actualpayment");

            SQL sql = new SQL().SELECT(sb.toString()).FROM(TBL_ORDER_PRODUCT_BACK);

            sql.LEFT_OUTER_JOIN(TBL_ORDER);
            sql.LEFT_OUTER_JOIN(TBL_ORDER_PRODUCT);
            sql.LEFT_OUTER_JOIN(TBL_PRODUCT_INFO);

            if (backQueryParam.getSellerid() != null) {
                sql.WHERE("opb.sellerid=#{sellerid}");
            }

            if (backQueryParam.getMemberId() != null) {
                sql.WHERE("opb.memberid=#{memberId}");
            }
            if (StringUtils.hasText(backQueryParam.getCode())) {
                String code = "%" + backQueryParam.getCode() + "%";
                backQueryParam.setCode(code);
                sql.WHERE("opb.code LIKE #{code}");
            }
            if (StringUtils.hasText(backQueryParam.getOrderNo())) {
                String orderNo = "%" + backQueryParam.getOrderNo() + "%";
                backQueryParam.setOrderNo(orderNo);
                sql.WHERE("opb.orderno LIKE #{orderNo}");
            }
            if (StringUtils.hasText(backQueryParam.getBackNo())) {
                String backNo = "%" + backQueryParam.getBackNo() + "%";
                backQueryParam.setBackNo(backNo);
                sql.WHERE("opb.backno LIKE #{backNo}");
            }
            if (StringUtils.hasText(backQueryParam.getPdName())) {
                String pdName = "%" + backQueryParam.getPdName() + "%";
                backQueryParam.setPdName(pdName);
                sql.WHERE("opb.pdname LIKE #{pdName}");
            }
            if (StringUtils.hasText(backQueryParam.getMemberName())) {
                String memberName = "%" + backQueryParam.getMemberName() + "%";
                backQueryParam.setMemberName(memberName);
                sql.WHERE("opb.membername LIKE #{memberName}");
            }
            if (StringUtils.hasText(backQueryParam.getSellerName())) {
                String sellerName = "%" + backQueryParam.getSellerName() + "%";
                backQueryParam.setSellerName(sellerName);
                sql.WHERE("opb.sellname LIKE #{sellerName}");
            }
            if (backQueryParam.getState() != Quantity.STATE_) {
                sql.WHERE("opb.state=#{state}");
            }
            if (backQueryParam.getStartTime() != null) {
                sql.WHERE("opb.createtime >=#{startTime}");
            }
            if (backQueryParam.getEndTime() != null) {
                Calendar c = Calendar.getInstance();
                c.setTime(backQueryParam.getEndTime());
                c.add(Calendar.DAY_OF_MONTH, 1);// 今天+1天
                Date tomorrow = c.getTime();
                backQueryParam.setEndTime(tomorrow);
                sql.WHERE("orders.createtime <=#{endTime}");
            }

            return sql.toString();
        }
    }

    /**
     * 获取用户订单列表
     *
     * @param param
     * @return
     */
    @SelectProvider(type = OrdersProvider.class, method = "queryOrderByParam")
    List<Orders> getMemberOrdersList(OrderQueryParam param);


    @SelectProvider(type = OrdersProvider.class, method = "queryOrderByParamForUser")
    List<Orders> getMemberOrdersListForUser(OrderQueryParam param);

    @Select("<script> select DISTINCT orders.* from orders " +
            "<if test=\"param.brand !=null or param.pdName!=null or param.mark!=null or param.backstate!=null or param.evaState!=null or param.stand!=null or param.prodNamAndOrderNo !=null\">left join orderproduct op on orders.id=op.orderid </if>"+
            " where 1=1 "+
            "<if test=\"param.memberid!=null and param.memberid!=''\"> and orders.memberid=#{param.memberid} </if>"+
            "<if test=\"param.sellerid!=null and param.sellerid!=''\"> and orders.saleid=#{param.sellerid} </if>"+
            "<if test=\"param.sellerName!=null and param.sellerName!=''\"> and (orders.membercompany like CONCAT(\"%\",#{param.sellerName},\"%\") or orders.shopname like CONCAT(\"%\",#{param.sellerName},\"%\")) </if>"+
            "<if test=\"param.memberName!=null and param.memberName!=''\"> and (orders.membercompany like CONCAT(\"%\",#{param.memberName},\"%\") or m.realname like CONCAT(\"%\",#{param.memberName},\"%\")) </if>"+
            "<if test=\"param.pdName!=null and param.pdName!=''\"> and op.pdname LIKE CONCAT(\"%\",#{param.pdName},\"%\") </if>"+
            "<if test=\"param.orderNo!=null and param.orderNo!=''\"> and orders.orderno LIKE CONCAT(\"%\",#{param.orderNo},\"%\") </if>"+
            "<if test=\"param.code!=null and param.code!=''\"> and orders.code LIKE CONCAT(\"%\",#{param.code},\"%\") </if>"+
            "<if test=\"param.tranNo!=null and param.tranNo!=''\"> and orders.transactionnumber LIKE CONCAT(\"%\",#{param.tranNo},\"%\") </if>"+
            "<if test=\"param.startTime!=null and param.startTime!=''\"> and orders.createtime &gt; #{param.startTime} </if>"+
            "<if test=\"param.endTime!=null and param.endTime!=''\"> and orders.createtime &lt; #{param.endTime} </if>"+
            "<if test=\"param.presellconfim!=null and param.presellconfim!='' and param.presellconfim!=-1\"> and orders.presellconfim=#{param.presellconfim} </if>"+
            "<if test=\"param.prestocktimeStart!=null and param.prestocktimeStart!=''\"> and orders.prestocktime &gt; #{param.prestocktimeStart} </if>"+
            "<if test=\"param.prestocktimeEnd!=null and param.prestocktimeEnd!=''\"> and orders.prestocktime &lt; #{param.prestocktimeEnd} </if>"+
            "<if test=\"param.startPayTime!=null and param.startPayTime!=''\"> and orders.paymenttime &gt; #{param.startPayTime} </if>"+
            "<if test=\"param.endPayTime!=null and param.endPayTime!=''\"> and orders.paymenttime &lt; #{param.endPayTime} </if>"+
            "<if test=\"param.brand!=null and param.brand!=''\"> and op.brand LIKE CONCAT(\"%\",#{param.brand},\"%\") </if>"+
            "<if test=\"param.mark!=null and param.mark!=''\"> and op.mark LIKE CONCAT(\"%\",#{param.mark},\"%\") </if>"+
            "<if test=\"param.stand!=null and param.stand!=''\"> and op.standard LIKE CONCAT(\"%\",#{param.stand},\"%\") </if>"+
            "<if test=\"param.orderState!=null and param.orderState!=''\"> and orders.orderstatus=#{param.orderState} </if>"+
            "<if test=\"param.multiOrderStates!=null and param.multiOrderStates!=''\"> and orders.orderstatus in (${param.multiOrderStates}) </if>"+
            "<if test=\"param.evaState!=null and param.evaState!=''\"> and op.evaluatestate=#{param.evaState} </if>"+
            "<if test=\"param.backstate!=null and param.backstate!=''\"> and op.backstate=#{param.backstate} </if>"+
            "<if test=\"param.prodNamAndOrderNo!=null and param.prodNamAndOrderNo!=''\"> and (orders.orderno like '%${param.prodNamAndOrderNo}%' or op.pdname LIKE '%${param.prodNamAndOrderNo}%' ) </if>"+
            " order by orders.id desc "+
            "</script>")
    List<Orders> getMemberOrdersListForUserMp(@Param("param") OrderQueryParam param);



    @Update("update orders set isbackcredit=#{isbackcredit} where memberid=#{memberid} and orderstatus=6 and  id in (${orderIds})")
    int updateOrdersIsbackcredit(@Param("memberid") long memberid, @Param("orderIds") String orderIds, @Param("isbackcredit") Short isbackcredit);


    @Select("update buyercapital set rechargestate=1 where capitaltype in (0,7,8,9) and orderno in (${ordernos}) ")
    Integer updateBuyerCapitalByOrderNos(@Param("ordernos") String ordernos);

    @Select("update salercapital set rechargestate=1 where capitaltype in (0,8,9,10) and orderno in (${ordernos}) ")
    Integer updateSellerCapitalByOrderNos(@Param("ordernos") String ordernos);

    /**
     * 获取统计订单各状态数量
     *
     * @param param
     * @return
     */
    @SelectProvider(type = OrdersStatisticProvider.class, method = "queryOrderByParam")
    List<OrderStatisticModel> getStatisticOrdersNum(OrderQueryParam param);

    public class OrdersStatisticProvider {

        private final String TBL_ORDER = "orders orders";

        public String queryOrderByParam(OrderQueryParam param) {
            SQL sql = new SQL().SELECT("to_char(orders.createtime, 'yyyy-mm-dd') AS ordertime,COUNT (*) AS ordernum").FROM(TBL_ORDER);

            //店铺名称
            String shopName = param.getShopName();
            if (StringUtils.hasText(shopName)) {
                shopName = "%" + shopName + "%";
                param.setShopName(shopName);
                sql.WHERE("orders.shopname LIKE #{shopName}");
            }

            //开始时间
            Date startTime = param.getStartTime();
            if (startTime != null) {
                sql.WHERE("orders.createtime >=#{startTime}");
            }
            //结束时间
            Date endTime = param.getEndTime();
            if (endTime != null) {
                Calendar c = Calendar.getInstance();
                c.setTime(endTime);
                c.add(Calendar.DAY_OF_MONTH, 1);// 今天+1天
                Date tomorrow = c.getTime();
                param.setEndTime(tomorrow);
                sql.WHERE("orders.createtime <=#{endTime}");
            }

            //订单状态
            Short orderState = param.getOrderState();
            if (orderState != null) {
                sql.WHERE("orders.orderstatus=#{orderState}");
            }
            sql.GROUP_BY("to_char(orders.createtime, 'yyyy-mm-dd')");
            return sql.toString();
        }
    }


    /**
     * 获取用户订单各状态数量
     *
     * @param param
     * @return
     */
    @SelectProvider(type = OrdersNumProvider.class, method = "queryOrderByParamForNum")
    int getMemberOrdersNum(OrderQueryParam param);

    public class OrdersNumProvider {

        private final String TBL_ORDER = "orders orders on op.orderno=orders.orderno";
        private final String TBL_ORDER_PRODUCT = "orderproduct op";

        public String queryOrderByParamForNum(OrderQueryParam param) {
            SQL sql = new SQL().SELECT("count(*) from (SELECT orders.\"id\" ").FROM(TBL_ORDER_PRODUCT);
            sql.LEFT_OUTER_JOIN(TBL_ORDER);
            Long memberid = param.getMemberid();
            if (memberid != null) {
                sql.WHERE("orders.memberid=#{memberid}");
            }
            Long sellerid = param.getSellerid();
            if (sellerid != null) {
                sql.WHERE("orders.saleid=#{sellerid}");
            }
            //订单状态
            Short orderState = param.getOrderState();
            if (orderState != null) {
                sql.WHERE("orders.orderstatus=#{orderState}");
            }
            //退货状态
            Short backstate = param.getBackstate();
            if (backstate != null) {
                sql.WHERE("op.backstate=#{backstate}");
            }
            sql.GROUP_BY("orders.\"id\") as groupOrder");
            return sql.toString();
        }
    }

    @Select("SELECT " +
            "CASE WHEN orders.orderstatus = 0 AND op.backstate=0 THEN '待付款' " +
            "WHEN orders.orderstatus = 1 AND op.backstate=0 THEN '待发货' " +
            "WHEN orders.orderstatus = 3 AND op.backstate=0 THEN '待收货' " +
            "WHEN orders.orderstatus = 4 AND op.backstate=0 THEN '待验货' " +
            "WHEN orders.orderstatus = 5 AND op.backstate=0 THEN '交易成功' " +
            "WHEN op.backstate=1 THEN '退货中' " +
            "WHEN op.backstate=2 THEN '退货验收' " +
            "WHEN op.backstate=4 THEN '异议中' " +
            "WHEN orders.orderstatus=7 THEN '交易关闭' " +
            "WHEN orders.orderstatus=9 and op.backstate=0 THEN '预定待发' " +
            "ELSE NULL END numname," +
            "COUNT(DISTINCT(orders.id)) " +
            "FROM orders orders LEFT JOIN orderproduct op on orders.id=op.orderid " +
            "where orders.memberid=#{memberid} " +
            "GROUP BY " +
            "CASE WHEN orders.orderstatus = 0 AND op.backstate=0 THEN '待付款' " +
            "WHEN orders.orderstatus = 1 AND op.backstate=0 THEN '待发货' " +
            "WHEN orders.orderstatus = 3 AND op.backstate=0 THEN '待收货' " +
            "WHEN orders.orderstatus = 4 AND op.backstate=0 THEN '待验货' " +
            "WHEN orders.orderstatus = 5 AND op.backstate=0 THEN '交易成功' " +
            "WHEN op.backstate=1 THEN '退货中' " +
            "WHEN op.backstate=2 THEN '退货验收' " +
            "WHEN op.backstate=4 THEN '异议中' " +
            "WHEN orders.orderstatus=7 THEN '交易关闭' " +
            "WHEN orders.orderstatus=9 and op.backstate=0 THEN '预定待发' " +
            "ELSE NULL END")
    List<Map<String, Object>> getBuyerCenterOrdersNum(OrderQueryParam param);

    @Select("SELECT " +
            "CASE WHEN orders.orderstatus = 0 AND op.backstate=0 THEN '待付款' " +
            "WHEN orders.orderstatus = 1 AND op.backstate=0 THEN '待发货' " +
            "WHEN orders.orderstatus = 3 AND op.backstate=0 THEN '待收货' " +
            "WHEN orders.orderstatus = 4 AND op.backstate=0 THEN '待验货' " +
            "WHEN orders.orderstatus = 5 AND op.backstate=0 THEN '交易成功' " +
            "WHEN op.backstate=1 THEN '退货中' " +
            "WHEN op.backstate=2 THEN '退货验收' " +
            "WHEN op.backstate=4 THEN '异议中' " +
            "WHEN orders.orderstatus=7 THEN '交易关闭' " +
            "WHEN orders.orderstatus=9 and op.backstate=0 THEN '预定待发' " +
            "ELSE NULL END numname," +
            "COUNT(DISTINCT(orders.id)) " +
            "FROM orders orders LEFT JOIN orderproduct op on orders.id=op.orderid " +
            "where orders.saleid=#{sellerid} " +
            "GROUP BY " +
            "CASE WHEN orders.orderstatus = 0 AND op.backstate=0 THEN '待付款' " +
            "WHEN orders.orderstatus = 1 AND op.backstate=0 THEN '待发货' " +
            "WHEN orders.orderstatus = 3 AND op.backstate=0 THEN '待收货' " +
            "WHEN orders.orderstatus = 4 AND op.backstate=0 THEN '待验货' " +
            "WHEN orders.orderstatus = 5 AND op.backstate=0 THEN '交易成功' " +
            "WHEN op.backstate=1 THEN '退货中' " +
            "WHEN op.backstate=2 THEN '退货验收' " +
            "WHEN op.backstate=4 THEN '异议中' " +
            "WHEN orders.orderstatus=7 THEN '交易关闭' " +
            "WHEN orders.orderstatus=9 and op.backstate=0 THEN '预定待发' " +
            "ELSE NULL END")
    List<Map<String, Object>> getSellerCenterOrdersNum(OrderQueryParam param);


//    @Select("SELECT " +
//            "od.id, " +
//            "CASE " +
//            "WHEN (od.orderstatus = 1 " +
//            "OR od.orderstatus = 3 or od.orderstatus=4 or od.orderstatus=5) " +
//            "AND od.ordertype = 0 THEN " +
//            "SUM (od.actualpayment) " +
//            "WHEN ( " +
//            "od.orderstatus = 1 OR od.orderstatus = 3 or od.orderstatus=4 or od.orderstatus=5 " +
//            "OR od.orderstatus = 8 " +
//            "OR od.orderstatus = 9 " +
//            ") " +
//            "AND od.ordertype = 1 THEN " +
//            "SUM (od.allpay) " +
//            "WHEN (od.orderstatus = 1 OR od.orderstatus = 3 or od.orderstatus=4 or od.orderstatus=5) " +
//            "AND od.ordertype = 3 THEN " +
//            "SUM (od.actualpayment) " +
//            "when (od.orderstatus = 8 or od.orderstatus=9) and od.ordertype=3 THEN " +
//            "    SUM(od.deposit)  " +
//            "END as actualpayment " +
//            "FROM " +
//            "orders od " +
//            "WHERE " +
//            "( " +
//            "od.orderstatus = 1 " +
//            "OR od.orderstatus = 8 " +
//            "OR od.orderstatus = 9 " +
//            "OR od.orderstatus = 3 or od.orderstatus=4 or od.orderstatus=5 " +
//            " " +
//            ") and  " +
//            "to_char(od.createtime,'yyyy-MM-DD HH24:MI:SS')>= to_char(current_date,'yyyy-MM-DD HH24:MI:SS') AND od.createtime<=now() " +
//            "GROUP BY od.id ")
    @Select("select sum(od.totalprice) from orders od where od.createtime>= current_date AND od.createtime<=now() and od.orderstatus in (1,3,4,5,8,9,10)")
    BigDecimal getCurrentOrdersSumPay();


    @SelectProvider(type = OrdersProvider.class,method = "getAllMemberOrdersList")
    List<OrdersView> getAllMemberOrdersList(OrderQueryParam param);


    //后台统计订单列表
    @SelectProvider(type = OrdersProvider.class,method = "getAllMemberOrdersCountList")
    List<Map<String,Object>> getAllMemberOrdersCountList(OrderQueryParam param);

    //后台统计订单详情
    @SelectProvider(type = OrdersProvider.class,method = "getAllMemberOrderCountDetail")
    List<Map<String,Object>> getAllMemberOrderCountDetail(OrderQueryParam param);

    //后台统计订单详情
    @Select("<script> select o.id,o.orderno,m.username buyerusername,m.realname buyerrealname,bci.companyname buyercompanyname,mm.username sellerusername," +
            "mm.realname sellerrealname,o.membercompany sellercompanyname,o.phone,o.createtime,o.orderstatus,o.shipto,o.province,o.city,o.area," +
            "o.receivingaddress,case when o.orderstatus = 3 then 1 when o.orderstatus = 8 then 2 when o.orderstatus = 1 then 3 when o.orderstatus = 10 then 4 end as dataorder " +
            "from orders o left join member m on o.memberid=m.id left join buyercompanyinfo bci on o.memberid=bci.memberid left join " +
            "member mm on o.saleid=mm.id  where o.id in <foreach collection=\"orderids\" item=\"item\" index=\"index\" " +
            "open=\"(\" separator=\",\" close=\")\">#{item}</foreach>  order by dataorder,o.createtime desc </script>")
    List<Map<String,Object>> getAllMemberOrderCountPrint(@Param("orderids") Long[] orderids);

    /**
     * 获取超时未付款的限时购订单
     * @param timedoutofpayment
     * @return
     */
    @Select("select * from orders where isonline=2 and orderstatus=0 and now() - createtime >= interval '${timedoutofpayment} min' ")
    List<Orders> getNotPayMoneyLimitOrders(@Param("timedoutofpayment") int timedoutofpayment);

    /**
     * 订单验货完成需要更新的数据
     * @param orders
     * @return
     */
    @Update("update orders set brokepay=#{brokepay},frozepay=#{frozepay},serverpay=#{serverpay},orderstatus=5,totalprice=#{totalprice},actualpayment=#{actualpayment},discountprice=#{discountprice},buyerinspectiontime=now() where id=#{id} and orderstatus=4 ")
    int updateOrdersConfirmgoods(Orders orders);

    /**
     * 查询卖家需要向平台开票的订单
     * @param saleid
     * @return
     */
    @Select("select id,orderno,membername,frozepay,brokepay,buyerinspectiontime as overtime from orders where saleid=#{saleid} " +
            "and orderstatus=5 and billtoserver=0 order by buyerinspectiontime asc ")
    List<Map<String,Object>> getWaitOpenBillList(@Param("saleid") long saleid);


    @Select("<script>select os.*,br.invoiceheadup from orders os LEFT OUTER JOIN billingrecord br on \n" +
            "os.id ||'' = br.orderno where os.id in <foreach collection=\"ids\" item=\"item\" index=\"index\" \n" +
            "open=\"(\" separator=\",\" close=\")\">#{item}</foreach></script>")
    List<Orders> getOrdersByIds(@Param("ids") Long[] ids);

    /**
     * 获取昨天0点到24点之间付款，且状态正常的订单；
     * @return
     */
    @Select("select o.*  from orders o where o.paymenttime between #{starttime} and #{endtime} and o.orderstatus not in (0,7) order by o.id asc")
    List<Orders> getRegularOrders(@Param("starttime") Date starttime, @Param("endtime") Date endtime);


    /**
     * 2018年6月8日
     * 获取会员的所有订单
     *
     * @return
     */
    @Select("select o.*  from orders o WHERE o.memberid=#{memberid}")
    List<Orders> findOrdersByuserid(@Param("memberid") Long memberid);

    /**
     * 根据用户id获取用户所有订单 且为已支付 即orderstatus不等于0和7
     * @param memberid
     * @return
     */
    @Select("select o.*  from orders o WHERE o.memberid=#{memberid} and o.orderstatus !=0 and o.orderstatus !=7 ")
    List<Orders> findOrdersByuseridAndOrderStatus(@Param("memberid") Long memberid);


    /**
     *
     * 根据用户id修改业务员
     *
     * @return
     */
    @Update("update orders set clerkname=#{clerkname},clerknamephone=#{clerknamephone} where memberid=#{memberid}")
    int updateOrdersClerknameBymemberid(@Param("clerkname") String clerkname,@Param("clerknamephone") String clerknamephone,@Param("memberid") Long memberid);

    /**
     *
     * 根据用户id修改介绍人
     *
     * @return
     */
    @Update("update orders set waysalesman=#{waysalesman} where memberid=#{memberid}")
    int updateOrdersWaysalesmanBymemberid(@Param("waysalesman") String waysalesman,@Param("memberid") Long memberid);

    /**
     * 取第三方支付的总金额，该方法在paylogs表上线后会自动弃用，之后可以删除该方法
     * @param uuid
     * @return
     */
    @Select("select sum(actualpayment) from orders where uuid=#{uuid}")
    BigDecimal getSumActualpaymentByUUID(@Param("uuid") String uuid);

    /**
     * 分单部分
     */
    @Insert("insert into ordermain(ordermoney,freight,couponid,couponmoney,type,createDt) " +
            "values(#{ordermoney},#{freight},#{couponid},#{couponmoney},#{type},now())")
    @Options(useGeneratedKeys = true)
    void saveOrderMain(OrderMain orderMain);

    @Select("select shopgroupid from store where id=#{param1}")
    Long getShopgidFromStore(long storeid);


    /**
     * 查询出所有订单 且id 从小到大
     * @return
     */
    @Select("select o.* from orders o order by o.id asc")
    List<Orders> getAllOrders();

    @Select("select o.* from orders o where orderno =#{orderno}")
    List<Orders> getByOrderNo(@Param("orderno") String orderno);


    @Select("<script>select o.*,li.id as id1,li.orderid as orderid1,li.orderno as orderno1,li.couriernumber as couriernumber1,li.logisticscompany as logisticscompany1,li.time,li.deliveryno as deliveryno1  " +
            "from orders o " +
            "left join logisticsinfo li on li.orderid=o.id " +
            "<where> 1=1 " +
            "<if test=\"param.sellerid != null and param.sellerid!='' \">and o.saleid = #{param.sellerid} </if>" +
            "<if test=\"param.deliveryno != null and param.deliveryno !='' \">and (li.deliveryno is not null and  li.deliveryno = #{param.deliveryno}) </if>" +
            "<if test=\"param.deliveryno == null or param.deliveryno =='' \">and li.deliveryno is not null </if>" +
            "<if test=\"param.startTime != null \">and(li.time is not null and li.time &gt;= #{param.startTime})</if>" +
            "<if test=\"param.endTime != null \">and(li.time is not null and li.time &lt;= #{param.endTime})</if>" +
            "</where> order by li.time desc" +
            "</script>")
    List<OrdersLogisticsInfoDto> getDeliveryNewRecord(@Param("param") OrderQueryParam param);



    @Update("update orders set clerkname = null where memberid in (${ids})")
    void updateOrdersByIds(@Param("ids") StringBuffer ids);
}
