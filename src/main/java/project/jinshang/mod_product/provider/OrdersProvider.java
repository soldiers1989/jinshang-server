package project.jinshang.mod_product.provider;

import org.apache.ibatis.jdbc.SQL;
import project.jinshang.common.utils.StringUtils;
import project.jinshang.mod_product.bean.OrderQueryParam;

import java.util.Calendar;
import java.util.Date;

/**
 * create : wyh
 * date : 2018/3/10
 */
public class OrdersProvider {

    /**
     * 后台获取订单列表
      */
    public  String  getAllMemberOrdersList(OrderQueryParam param){
        SQL sql =  new SQL();
        sql.SELECT("o.*,bc.companyname as buyercompanyname, m.realname as realname ");
        sql.FROM(" orders o ");
        sql.LEFT_OUTER_JOIN("member m on o.memberid=m.id ");
        sql.LEFT_OUTER_JOIN("buyercompanyinfo bc on o.memberid=bc.memberid ");

        if (StringUtils.hasText(param.getMemberName())) {
            String memberName = "%" + param.getMemberName() + "%";
            param.setMemberName(memberName);
            sql.WHERE(" (m.realname like #{memberName} or bc.companyname like #{memberName})");
        }
        if (StringUtils.hasText(param.getSellerName())) {
            String sellerName = "%" + param.getSellerName() + "%";
            param.setSellerName(sellerName);
            sql.WHERE(" (o.membercompany like #{sellerName} or o.shopname like #{sellerName}) ");
        }
        if (param.getIsonline() != null) {
            sql.WHERE(" Isonline=#{isonline} ");
        }
        if (param.getPayType() != null) {
            sql.WHERE("PayType = #{payType}");
        }


        if(param.getDeliverytype() != null){
            sql.WHERE(" deliverytype=#{deliverytype} ");
        }


        if (param.getOrderType() != null) {

            sql.WHERE("OrderType=#{orderType}");
        }
        if (StringUtils.hasText(param.getOrderNo())) {
            String orderNo = "%" + param.getOrderNo() + "%";
            param.setOrderNo(orderNo);
            sql.WHERE("orderno like #{orderNo}");
        }
        if (StringUtils.hasText(param.getCode())) {
            String code = "%" + param.getCode() + "%";
            param.setCode(code);
            sql.WHERE("code like #{code}");
        }
        if (StringUtils.hasText(param.getTranNo())) {
            String tranNo = "%" + param.getTranNo() + "%";
            param.setTranNo(tranNo);
            sql.WHERE(" Transactionnumber like #{tranNo} ");
        }


        if (param.getOrderState() != null) {
            if(param.getOrderState().toString().equals("11")){
                sql.WHERE(" (o.orderstatus='7' and  o.reason='卖家取消订单')");
            }else {
                sql.WHERE(" o.orderstatus=#{orderState} ");
            }
        }/*else{
            sql.WHERE(" (o.orderstatus!='7' and  o.orderstatus!='0')");
        }*/


        if (param.getShopName() != null) {
            String shopName = "%" + param.getShopName() + "%";
            param.setShopName(shopName);
            sql.WHERE("shopName like #{shopname}");
        }
        if (param.getStartTime() != null) {
            sql.WHERE(" Createtime >= #{startTime} ");
        }
        if (param.getEndTime() != null) {
            Calendar c = Calendar.getInstance();
            c.setTime(param.getEndTime());
            c.add(Calendar.DAY_OF_MONTH, 1);// 今天+1天
            Date tomorrow = c.getTime();
            param.setEndTime(tomorrow);
            sql.WHERE(" Createtime < #{endTime} ");
        }


        sql.ORDER_BY( "o.id desc" );

        return  sql.toString();
    }


    private final String TBL_ORDER = "orders orders on op.orderno=orders.orderno";
    private final String TBL_ORDER_PRODUCT = "orderproduct op";
    private final String TBL_MEMBER = "member m on orders.memberid=m.id";

    public String queryOrderByParam(OrderQueryParam param) {
        SQL sql = new SQL().SELECT("distinct orders.orderno,orders.shipto,orders.phone,orders.\"id\",orders.totalprice,orders.freight,orders.deposit,orders.balance,orders.futuretime,orders.allpay,orders.ordertype,orders.\"membercompany\",orders.code,orders.transactionnumber,orders.orderstatus,orders.createtime,orders.brokepay,orders.delaydays,orders.ifdelay,orders.delaypenalty,orders.deliverytype,orders.couriernumber,orders.logisticscompany ").FROM(TBL_ORDER_PRODUCT);
        sql.JOIN(TBL_ORDER);
        sql.JOIN(TBL_MEMBER);
        sql.WHERE("orders.orderno is not null");
        Long memberid = param.getMemberid();
        if (memberid != null) {
            sql.WHERE("orders.memberid=#{memberid}");
        }
        Long sellerid = param.getSellerid();
        if (sellerid != null) {
            sql.WHERE("orders.saleid=#{sellerid}");
        }

        if (StringUtils.hasText(param.getSellerName())) {
            String sellerName = "%" + param.getSellerName() + "%";
            param.setSellerName(sellerName);
            sql.WHERE(" (orders.membercompany like #{sellerName} or orders.shopname like #{sellerName}) ");
        }

        //买家真实姓名、公司名称
        if (StringUtils.hasText(param.getMemberName())) {
            String memberName = "%" + param.getMemberName() + "%";
            param.setMemberName(memberName);
            sql.WHERE(" (orders.membercompany like #{memberName} or m.realname like #{memberName}) ");
        }

        //商品名称
        String pdName = param.getPdName();
        if (StringUtils.hasText(pdName)) {
            pdName = "%" + pdName + "%";
            param.setPdName(pdName);
            sql.WHERE("op.pdname LIKE #{pdName}");
        }
        //订单编号
        String orderNo = param.getOrderNo();
        if (StringUtils.hasText(orderNo)) {
            orderNo = "%" + orderNo + "%";
            param.setOrderNo(orderNo);
            sql.WHERE("orders.orderno LIKE #{orderNo}");
        }
        String code = param.getCode();
        if (StringUtils.hasText(code)) {
            code = "%" + code + "%";
            param.setCode(code);
            sql.WHERE("orders.code like #{code}");
        }
        //订单编号
        String tranNo = param.getTranNo();
        if (StringUtils.hasText(tranNo)) {
            tranNo = "%" + tranNo + "%";
            param.setTranNo(tranNo);
            sql.WHERE("orders.transactionnumber LIKE #{tranNo}");
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

        //支付开始时间
        Date startPayTime = param.getStartPayTime();
        if (startPayTime != null) {
            sql.WHERE("orders.paymenttime >=#{startPayTime}");
        }
        //支付结束时间
        Date endPayTime = param.getEndPayTime();
        if (endPayTime != null) {
            Calendar c = Calendar.getInstance();
            c.setTime(endPayTime);
            c.add(Calendar.DAY_OF_MONTH, 1);// 今天+1天
            Date tomorrow = c.getTime();
            param.setEndPayTime(tomorrow);
            sql.WHERE("orders.paymenttime <=#{endPayTime}");
        }
        //品牌
        String brand = param.getBrand();
        if (StringUtils.hasText(brand)) {
            brand = "%" + brand + "%";
            param.setBrand(brand);
            sql.WHERE("op.brand LIKE #{brand}");
        }
        //印记
        String mark = param.getMark();
        if (StringUtils.hasText(mark)) {
            mark = "%" + mark + "%";
            param.setMark(mark);
            sql.WHERE("op.mark LIKE #{mark}");
        }
        //规格
        String stand = param.getStand();
        if (StringUtils.hasText(stand)) {
            stand = "%" + stand + "%";
            param.setStand(stand);
            sql.WHERE("op.standard LIKE #{stand}");
        }
        //订单状态
        Short orderState = param.getOrderState();
        if (orderState != null) {
            sql.WHERE("orders.orderstatus=#{orderState}");
        }
        //评价状态
        Short evaState = param.getEvaState();
        if (evaState != null) {
            sql.WHERE("op.evaluatestate=#{evaState}");
        }
        //退货状态
        Short backstate = param.getBackstate();
        if (backstate != null) {
            sql.WHERE("op.backstate=#{backstate}");
        }
        sql.GROUP_BY("orders.id");
        sql.ORDER_BY("orders.id desc");
        return sql.toString();
    }


}
