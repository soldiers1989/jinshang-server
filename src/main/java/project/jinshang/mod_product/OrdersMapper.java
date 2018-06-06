package project.jinshang.mod_product;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;
import project.jinshang.common.constant.Quantity;
import project.jinshang.common.utils.StringUtils;
import project.jinshang.mod_admin.mod_count.bean.OrderStatisticModel;
import project.jinshang.mod_cash.bean.SalerCapital;
import project.jinshang.mod_product.bean.*;
import project.jinshang.mod_product.bean.dto.OrdersView;
import project.jinshang.mod_product.provider.OrdersProvider;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

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


    @Select("select * from orders where orderno=#{orderno} order by id desc limit 1")
    Orders getOrdersByOrderNo(@Param("orderno") String orderno);

    /**
     * 获取订单总的佣金
     *
     * @param param
     * @return
     */
    @SelectProvider(type = OrdersSumBrokerProvider.class, method = "queryByParam")
    public BigDecimal getOrdersSumBroker(OrderQueryParam param);


    public class OrdersSumBrokerProvider {

        private final String TBL_ORDER = "orders od";
        private final String TBL_MEMBER = "member m on od.memberid=m.id";
        private final String TBL_BUYERCOMPANYINFO = "buyercompanyinfo bci on m.id=bci.memberid";
        private final String TBL_MEMBER2 = "member mm on od.saleid=mm.id";

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

            if (StringUtils.hasText(param.getMemberName())) {
                String memberName = "%" + param.getMemberName() + "%";
                param.setMemberName(memberName);
                sql.WHERE("(m.realname LIKE #{memberName} or m.username LIKE #{memberName} or bci.companyname LIKE #{memberName})");
            }
            if (StringUtils.hasText(param.getSellerName())) {
                String sellerName = "%" + param.getSellerName() + "%";
                param.setSellerName(sellerName);
                sql.WHERE("(mm.realname LIKE #{sellerName} or mm.username LIKE #{sellerName} or od.membercompany LIKE #{sellerName})");
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
            if (param.getOrderState() != null) {
                if(param.getOrderState().toString().equals("11")){
                    sql.WHERE(" (od.orderstatus=7 and  od.reason='卖家取消订单')");
                }else {
                    sql.WHERE(" od.orderstatus=#{orderState} ");
                }
            }else{
                sql.WHERE(" (od.orderstatus!=7 and  od.orderstatus!=0)");
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
        private final String TBL_MEMBER = "member m on od.memberid=m.id";
        private final String TBL_BUYERCOMPANYINFO = "buyercompanyinfo bci on m.id=bci.memberid";
        private final String TBL_MEMBER2 = "member mm on od.saleid=mm.id";
        //private final String TBL_BUYERCOMPANYINFO2 = "buyercompanyinfo bcii on mm.id=bcii.memberid";

        public String queryByParam(OrderQueryParam param) {

            SQL sql = new SQL().SELECT("sum(od.actualpayment-od.freight)").FROM(TBL_ORDER);
            sql.LEFT_OUTER_JOIN(TBL_MEMBER);
            sql.LEFT_OUTER_JOIN(TBL_BUYERCOMPANYINFO);
            sql.LEFT_OUTER_JOIN(TBL_MEMBER2);

            if (StringUtils.hasText(param.getClerkname())) {
                String clerkName = "%" + param.getClerkname() + "%";
                param.setClerkname(clerkName);
                sql.WHERE(" od.clerkname like #{clerkname} ");
            }

            if (StringUtils.hasText(param.getMemberName())) {
                String memberName = "%" + param.getMemberName() + "%";
                param.setMemberName(memberName);
                sql.WHERE("(m.realname LIKE #{memberName} or m.username LIKE #{memberName} or bci.companyname LIKE #{memberName})");
            }
            if (StringUtils.hasText(param.getSellerName())) {
                String sellerName = "%" + param.getSellerName() + "%";
                param.setSellerName(sellerName);
                sql.WHERE("(mm.realname LIKE #{sellerName} or mm.username LIKE #{sellerName} or od.membercompany LIKE #{sellerName})");
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
            if (param.getOrderState() != null) {
                if(param.getOrderState().toString().equals("11")){
                    sql.WHERE(" (od.orderstatus=7 and  od.reason='卖家取消订单')");
                }else {
                    sql.WHERE(" od.orderstatus=#{orderState} ");
                }
            }else{
                sql.WHERE(" (od.orderstatus!=7 and  od.orderstatus!=0)");
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

            return sql.toString();
        }
    }


    /**
     * 获取销售总金额
     *
     * @return
     */
    @SelectProvider(type = OrderSellSumProvider.class, method = "queryByParam")
    public BigDecimal getOrderSellSum(OrderQueryParam param);

    public class OrderSellSumProvider {

        private final String TBL_ORDER_PRODUCT = "orderproduct op";
        private final String TBL_ORDER = "orders od on op.orderid=od.id";
        private final String TBL_MEMBER = "member m on od.memberid=m.id";
        private final String TBL_BUYERCOMPANYINFO = "buyercompanyinfo bci on m.id=bci.memberid";
        private final String TBL_MEMBER2 = "member mm on od.saleid=mm.id";


        public String queryByParam(OrderQueryParam param) {

            SQL sql = new SQL().SELECT("sum(op.actualpayment-op.freight)").FROM(TBL_ORDER_PRODUCT);

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

            if (StringUtils.hasText(param.getMemberName())) {
                String memberName = "%" + param.getMemberName() + "%";
                param.setMemberName(memberName);
                sql.WHERE("(m.realname LIKE #{memberName} or m.username LIKE #{memberName} or bci.companyname LIKE #{memberName})");
            }
            if (StringUtils.hasText(param.getSellerName())) {
                String sellerName = "%" + param.getSellerName() + "%";
                param.setSellerName(sellerName);
                sql.WHERE("(mm.realname LIKE #{sellerName} or mm.username LIKE #{sellerName} or od.membercompany LIKE #{sellerName})");
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
            if (param.getOrderState() != null) {
                if(param.getOrderState().toString().equals("11")){
                    sql.WHERE(" (od.orderstatus=7 and  od.reason='卖家取消订单')");
                }else {
                    sql.WHERE(" od.orderstatus=#{orderState} ");
                }
            }else{
                sql.WHERE(" (od.orderstatus!=7 and  od.orderstatus!='0')");
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
        private final String TBL_MEMBER = "member m on od.memberid=m.id";
        private final String TBL_BUYERCOMPANYINFO = "buyercompanyinfo bci on m.id=bci.memberid";
        private final String TBL_MEMBER2 = "member mm on od.saleid=mm.id";


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

            if (StringUtils.hasText(param.getMemberName())) {
                String memberName = "%" + param.getMemberName() + "%";
                param.setMemberName(memberName);
                sql.WHERE("(m.realname LIKE #{memberName} or m.username LIKE #{memberName} or bci.companyname LIKE #{memberName})");
            }
            if (StringUtils.hasText(param.getSellerName())) {
                String sellerName = "%" + param.getSellerName() + "%";
                param.setSellerName(sellerName);
                sql.WHERE("(mm.realname LIKE #{sellerName} or mm.username LIKE #{sellerName} or od.membercompany LIKE #{sellerName})");
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
            if (param.getOrderState() != null) {
                if(param.getOrderState().toString().equals("11")){
                    sql.WHERE(" (od.orderstatus=7 and  od.reason='卖家取消订单')");
                }else {
                    sql.WHERE(" od.orderstatus=#{orderState} ");
                }
            }else{
                sql.WHERE(" (od.orderstatus!=7 and  od.orderstatus!=0)");
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
        private final String TBL_MEMBER = "member m on od.memberid=m.id";
        private final String TBL_BUYERCOMPANYINFO = "buyercompanyinfo bci on m.id=bci.memberid";
        private final String TBL_MEMBER2 = "member mm on od.saleid=mm.id";

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

            if (StringUtils.hasText(param.getMemberName())) {
                String memberName = "%" + param.getMemberName() + "%";
                param.setMemberName(memberName);
                sql.WHERE("(m.realname LIKE #{memberName} or m.username LIKE #{memberName} or bci.companyname LIKE #{memberName})");
            }
            if (StringUtils.hasText(param.getSellerName())) {
                String sellerName = "%" + param.getSellerName() + "%";
                param.setSellerName(sellerName);
                sql.WHERE("(mm.realname LIKE #{sellerName} or mm.username LIKE #{sellerName} or od.membercompany LIKE #{sellerName})");
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
            if (param.getOrderState() != null) {
                if(param.getOrderState() == 11) {
                    sql.WHERE(" (od.orderstatus=7 and  od.reason='卖家取消订单')");
                }else {
                    sql.WHERE(" od.orderstatus=#{orderState} ");
                }
            }else{
                sql.WHERE(" (od.orderstatus!=7 and  od.orderstatus!=0)");
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

            return sql.toString();
        }
    }


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
    @Select("SELECT id,orderno,totalprice,actualpayment,freight,allpay,deposit,balance,ordertype,orderstatus,saleid,paytype,memberid,uuid,yuuuid from orders where id in (${ids})")
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
            sb.append("op.pdname,op.attrjson,op.material,op.gradeno,op.brand,info.mark,");
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
            sql.ORDER_BY("od.createtime desc");

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


    @Select("SELECT " +
            "od.id, " +
            "CASE " +
            "WHEN (od.orderstatus = 1 " +
            "OR od.orderstatus = 3 or od.orderstatus=4 or od.orderstatus=5) " +
            "AND od.ordertype = 0 THEN " +
            "SUM (od.actualpayment) " +
            "WHEN ( " +
            "od.orderstatus = 1 OR od.orderstatus = 3 or od.orderstatus=4 or od.orderstatus=5 " +
            "OR od.orderstatus = 8 " +
            "OR od.orderstatus = 9 " +
            ") " +
            "AND od.ordertype = 1 THEN " +
            "SUM (od.allpay) " +
            "WHEN (od.orderstatus = 1 OR od.orderstatus = 3 or od.orderstatus=4 or od.orderstatus=5) " +
            "AND od.ordertype = 3 THEN " +
            "SUM (od.actualpayment) " +
            "when (od.orderstatus = 8 or od.orderstatus=9) and od.ordertype=3 THEN " +
            "    SUM(od.deposit)  " +
            "END as actualpayment " +
            "FROM " +
            "orders od " +
            "WHERE " +
            "( " +
            "od.orderstatus = 1 " +
            "OR od.orderstatus = 8 " +
            "OR od.orderstatus = 9 " +
            "OR od.orderstatus = 3 or od.orderstatus=4 or od.orderstatus=5 " +
            " " +
            ") and  " +
            "to_char(od.createtime,'yyyy-MM-DD HH24:MI:SS')>= to_char(current_date,'yyyy-MM-DD HH24:MI:SS') AND od.createtime<=now() " +
            "GROUP BY od.id ")
    List<Orders> getCurrentOrdersSumPay();


    @SelectProvider(type = OrdersProvider.class,method = "getAllMemberOrdersList")
    List<OrdersView> getAllMemberOrdersList(OrderQueryParam param);

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
    @Update("update orders set brokepay=#{brokepay},frozepay=#{frozepay},serverpay=#{serverpay},orderstatus=5,buyerinspectiontime=now() where id=#{id} and orderstatus=4 ")
    int updateOrdersConfirmgoods(Orders orders);

    /**
     * 查询卖家需要向平台开票的订单
     * @param saleid
     * @return
     */
    @Select("select id,orderno,membername,frozepay,brokepay,buyerinspectiontime as overtime from orders where saleid=#{saleid} " +
            "and orderstatus=5 and billtoserver=0 order by buyerinspectiontime asc ")
    List<Map<String,Object>> getWaitOpenBillList(@Param("saleid") long saleid);


    @Select("<script>select * from orders where id in <foreach collection=\"ids\" item=\"item\" index=\"index\" \n" +
            "open=\"(\" separator=\",\" close=\")\">#{item}</foreach></script>")
    List<Orders> getOrdersByIds(@Param("ids") Long[] ids);

    /**
     * 获取昨天0点到24点之间付款，且状态正常的订单；
     * @return
     */
    @Select("select o.*  from orders o where o.paymenttime between #{starttime} and #{endtime} and o.orderstatus not in (0,7) order by o.id asc")
    List<Orders> getRegularOrders(@Param("starttime") Date starttime, @Param("endtime") Date endtime);
}