package project.jinshang.mod_product.provider;

import org.apache.ibatis.jdbc.SQL;
import project.jinshang.common.constant.Quantity;
import project.jinshang.common.utils.StringUtils;
import project.jinshang.mod_product.bean.OrderQueryParam;

import java.lang.reflect.Array;
import java.sql.ParameterMetaData;
import java.util.*;

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
        sql.SELECT("o.*,bc.companyname as buyercompanyname, mm.realname as realname ");
        sql.FROM(" orders o ");
        sql.LEFT_OUTER_JOIN("member m on o.saleid=m.id ");
        sql.LEFT_OUTER_JOIN("member mm on o.memberid=mm.id ");
        sql.LEFT_OUTER_JOIN("buyercompanyinfo bc on o.memberid=bc.memberid ");

        if (StringUtils.hasText(param.getMemberName())) {
            String memberName = "%" + param.getMemberName() + "%";
            param.setMemberName(memberName);
            sql.WHERE("(mm.realname like #{memberName} or mm.username like #{memberName} or bc.companyname LIKE #{memberName})");
        }

        if (StringUtils.hasText(param.getClerkname())) {
            String clerkName = "%" + param.getClerkname() + "%";
            param.setClerkname(clerkName);
            sql.WHERE(" o.clerkname like #{clerkname} ");
        }
        if (StringUtils.hasText(param.getWaysalesman())) {
            String waySalesMan = "%" + param.getWaysalesman() + "%";
            param.setWaysalesman(waySalesMan);
            sql.WHERE(" o.waysalesman like #{waysalesman} ");
        }

        if (StringUtils.hasText(param.getSellerName())) {
            String sellerName = "%" + param.getSellerName() + "%";
            param.setSellerName(sellerName);
            sql.WHERE("  (o.membercompany like #{sellerName} or m.username like #{sellerName}) ");
        }
        if (param.getIsonline() != null) {
            sql.WHERE(" Isonline=#{isonline} ");
        }
        if (param.getPayType() != null) {
            sql.WHERE("PayType = #{payType}");
        }
        if(StringUtils.hasText(param.getTransactionid())){
            sql.WHERE("transactionid = #{transactionid}");
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
      Short sendstatus = param.getSendstatus();
        if (param.getOrderState() != null) {
            if(param.getOrderState().toString().equals("11")){
                sql.WHERE(" (o.orderstatus='7' and  o.reason='卖家取消订单')");
            }else if(param.getOrderState()== 3 && sendstatus== 3){
                //后台管理--订单管理--待发货 传3待收货  但是要查状态为3待收货和10部分发货的
                sql.WHERE(" (o.orderstatus='3' or o.orderstatus='10')");
            }else if(param.getOrderState()== 1 && sendstatus== 10){
                //后台管理--待发货  orderstate只传1 但是要查状态为1待发货和10部分发货的
                sql.WHERE(" (o.orderstatus='1' or o.orderstatus='10')");
            }else {
                sql.WHERE(" o.orderstatus=#{orderState}");
            }
        }
        //后台订单状态筛选
        if (StringUtils.hasText(param.getOrderStates())) {
            List<String> list = new ArrayList<>(Arrays.asList(param.getOrderStates().split(",")));
            List<String> removeList = new ArrayList<>();
            StringBuffer sb = new StringBuffer("(");
            int count = 0;
            for (String i:list){
                if (i.equals("11")) {
                    if(count>0){sb.append(" or ");}
                    sb.append(" (o.orderstatus='7' and  o.reason='卖家取消订单') ");
                    removeList.add("11");
                    count++;
                }
                if (i.equals("3")) {
                    if(count>0){sb.append(" or ");}
                    //后台管理--订单管理--待发货 传3待收货  但是要查状态为3待收货和10部分发货的
                    sb.append("  (o.orderstatus='3' or o.orderstatus='10') ");
                    removeList.add("3");
                    count++;
                }
                if(i.equals("1")){
                    if(count>0){sb.append(" or ");}
                    //后台管理--待发货  orderstate只传1 但是要查状态为1待发货和10部分发货的
                    sb.append("  (o.orderstatus='1' or o.orderstatus='10')  ");
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
                sb.append(" o.orderstatus in ("+str+") ");
            }
            sb.append(")");
            sql.WHERE(sb.toString());
        }
        /*else{
            sql.WHERE(" (o.orderstatus!='7' and  o.orderstatus!='0')");
        }*/
        if (StringUtils.hasText(param.getShopName())) {
            String shopName = "%" + param.getShopName() + "%";
            param.setShopName(shopName);
            sql.WHERE("shopName like #{shopname}");
        }

        if (StringUtils.hasText(param.getShipto())) {
            String shipto = "%" + param.getShipto() + "%";
            param.setShipto(shipto);
            sql.WHERE("shipto like #{shipto}");
        }

        if (StringUtils.hasText(param.getStand())) {
            String stand = "%" + param.getStand() + "%";
            param.setStand(stand);
            sql.WHERE("o.orderno in (select orderno from orderproduct where standard like #{stand})");
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
            sql.WHERE(" Createtime <= #{endTime} ");
        }

        if (param.getRegisterTimeStart() != null) {
            sql.WHERE(" mm.createdate >= #{registerTimeStart} ");
        }
        if (param.getRegisterTimeEnd() != null) {
            Calendar c = Calendar.getInstance();
            c.setTime(param.getRegisterTimeEnd());
            c.add(Calendar.DAY_OF_MONTH, 1);// 今天+1天
            Date tomorrow = c.getTime();
            param.setRegisterTimeEnd(tomorrow);
            sql.WHERE(" mm.createdate <= #{registerTimeEnd} ");
        }

        //商家是否接收订单
        if(param.getPresellconfim() != null && param.getPresellconfim()!= -1 ){
            sql.WHERE("presellconfim =#{presellconfim}");
        }
        //预计备货开始时间
        if (param.getPrestocktimeStart() != null) {
            sql.WHERE("prestocktime >=#{prestocktimeStart}");
        }
        //预计备货结束时间
        if (param.getPrestocktimeEnd() != null) {
            Calendar c = Calendar.getInstance();
            c.setTime(param.getPrestocktimeEnd());
            c.add(Calendar.DAY_OF_MONTH, 1);// 今天+1天
            Date tomorrow = c.getTime();
            param.setPrestocktimeEnd(tomorrow);
            sql.WHERE("prestocktime <=#{prestocktimeEnd}");
        }
        sql.ORDER_BY( "o.id desc" );
        return  sql.toString();
    }

    /**
     * 后台获取订单统计列表
     */
    public  String  getAllMemberOrdersCountList(OrderQueryParam param){
        SQL sql =  new SQL();
        sql.SELECT("o.memberid,coalesce(case when bci.companyname ='' then null else bci.companyname end,case when m.realname ='' then null else m.realname end," +
                "case when m.username ='' then null else m.username end)as buyername,count(1) num");
        sql.FROM(" orders o ");
        sql.LEFT_OUTER_JOIN("member m on o.memberid=m.id ");
        sql.LEFT_OUTER_JOIN("buyercompanyinfo bci on o.memberid=bci.memberid ");
        sql.LEFT_OUTER_JOIN("member mm on o.saleid=mm.id ");
        sql.WHERE("(o.orderstatus = 1 or o.orderstatus = 3 or o.orderstatus = 8 or o.orderstatus = 10) ");
        Long memberid = param.getMemberid();
        if (memberid != null) {
            sql.WHERE("o.memberid = #{memberid}");
        }

        /*if (StringUtils.hasText(param.getClerkname())) {
            String clerkName = "%" + param.getClerkname() + "%";
            param.setClerkname(clerkName);
            sql.WHERE(" o.clerkname like #{clerkname} ");
        }

        if (StringUtils.hasText(param.getWaysalesman())) {
            String waySalesMan = "%" + param.getWaysalesman() + "%";
            param.setWaysalesman(waySalesMan);
            sql.WHERE(" o.waysalesman like #{waysalesman} ");
        }*/

        if (StringUtils.hasText(param.getSellerName())) {
            String sellerName = "%" + param.getSellerName() + "%";
            param.setSellerName(sellerName);
            sql.WHERE("  (mm.username like #{sellerName} or mm.realname like #{sellerName} or o.membercompany like #{sellerName}) ");
        }
        /*if (param.getIsonline() != null) {
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
        }*/
        /*if (StringUtils.hasText(param.getOrderNo())) {
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
        }*//*else{
            sql.WHERE(" (o.orderstatus!='7' and  o.orderstatus!='0')");
        }*//*


        if (StringUtils.hasText(param.getShopName())) {
            String shopName = "%" + param.getShopName() + "%";
            param.setShopName(shopName);
            sql.WHERE("shopName like #{shopname}");
        }

        if (StringUtils.hasText(param.getShipto())) {
            String shipto = "%" + param.getShipto() + "%";
            param.setShipto(shipto);
            sql.WHERE("shipto like #{shipto}");
        }

        if (StringUtils.hasText(param.getStand())) {
            String stand = "%" + param.getStand() + "%";
            param.setStand(stand);
            sql.WHERE("o.orderno in (select orderno from orderproduct where standard like #{stand})");
        }*/

        if (param.getStartTime() != null) {
            sql.WHERE(" Createtime >= #{startTime} ");
        }
        if (param.getEndTime() != null) {
            Calendar c = Calendar.getInstance();
            c.setTime(param.getEndTime());
            c.add(Calendar.DAY_OF_MONTH, 1);// 今天+1天
            Date tomorrow = c.getTime();
            param.setEndTime(tomorrow);
            sql.WHERE(" Createtime <= #{endTime} ");
        }

        /*if (param.getRegisterTimeStart() != null) {
            sql.WHERE(" mm.createdate >= #{registerTimeStart} ");
        }
        if (param.getRegisterTimeEnd() != null) {
            Calendar c = Calendar.getInstance();
            c.setTime(param.getRegisterTimeEnd());
            c.add(Calendar.DAY_OF_MONTH, 1);// 今天+1天
            Date tomorrow = c.getTime();
            param.setRegisterTimeEnd(tomorrow);
            sql.WHERE(" mm.createdate <= #{registerTimeEnd} ");
        }

        //商家是否接收订单
        if(param.getPresellconfim() != null && param.getPresellconfim()!= -1 ){
            sql.WHERE("presellconfim =#{presellconfim}");
        }
        //预计备货开始时间
        if (param.getPrestocktimeStart() != null) {
            sql.WHERE("prestocktime >=#{prestocktimeStart}");
        }
        //预计备货结束时间
        if (param.getPrestocktimeEnd() != null) {
            Calendar c = Calendar.getInstance();
            c.setTime(param.getPrestocktimeEnd());
            c.add(Calendar.DAY_OF_MONTH, 1);// 今天+1天
            Date tomorrow = c.getTime();
            param.setPrestocktimeEnd(tomorrow);
            sql.WHERE("prestocktime <=#{prestocktimeEnd}");
        }*/

        sql.GROUP_BY("o.memberid,m.username,m.realname,bci.companyname");
        sql.ORDER_BY( "num desc" );

        return  sql.toString();
    }

    /**
     * 后台获取订单统计详情
     */
    public  String  getAllMemberOrderCountDetail(OrderQueryParam param){
        SQL sql =  new SQL();
        sql.SELECT("o.id,o.orderno,m.username buyerusername,m.realname buyerrealname,bci.companyname buyercompanyname,mm.username sellerusername,mm.realname sellerrealname," +
                "o.membercompany sellercompanyname,o.phone,o.createtime,o.orderstatus,o.shipto,o.province,o.city,o.area,o.receivingaddress," +
                "case when o.orderstatus = 3 then 1 when o.orderstatus = 8 then 2 when o.orderstatus = 1 then 3 when o.orderstatus = 10 then 4 end as dataorder");
        sql.FROM(" orders o ");
        sql.LEFT_OUTER_JOIN("member m on o.memberid=m.id ");
        sql.LEFT_OUTER_JOIN("buyercompanyinfo bci on o.memberid=bci.memberid ");
        sql.LEFT_OUTER_JOIN("member mm on o.saleid=mm.id ");
        sql.WHERE("(o.orderstatus = 1 or o.orderstatus = 3 or o.orderstatus = 8 or o.orderstatus = 10) ");
        Long memberid = param.getMemberid();
        if (memberid != null) {
            sql.WHERE("o.memberid = #{memberid}");
        }

        /*if (StringUtils.hasText(param.getClerkname())) {
            String clerkName = "%" + param.getClerkname() + "%";
            param.setClerkname(clerkName);
            sql.WHERE(" o.clerkname like #{clerkname} ");
        }

        if (StringUtils.hasText(param.getWaysalesman())) {
            String waySalesMan = "%" + param.getWaysalesman() + "%";
            param.setWaysalesman(waySalesMan);
            sql.WHERE(" o.waysalesman like #{waysalesman} ");
        }*/

        if (StringUtils.hasText(param.getSellerName())) {
            String sellerName = "%" + param.getSellerName() + "%";
            param.setSellerName(sellerName);
            sql.WHERE("  (mm.username like #{sellerName} or mm.realname like #{sellerName} or o.membercompany like #{sellerName}) ");
        }
        /*if (param.getIsonline() != null) {
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
        }*/
        /*if (StringUtils.hasText(param.getOrderNo())) {
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
        }*//*else{
            sql.WHERE(" (o.orderstatus!='7' and  o.orderstatus!='0')");
        }*//*


        if (StringUtils.hasText(param.getShopName())) {
            String shopName = "%" + param.getShopName() + "%";
            param.setShopName(shopName);
            sql.WHERE("shopName like #{shopname}");
        }

        if (StringUtils.hasText(param.getShipto())) {
            String shipto = "%" + param.getShipto() + "%";
            param.setShipto(shipto);
            sql.WHERE("shipto like #{shipto}");
        }

        if (StringUtils.hasText(param.getStand())) {
            String stand = "%" + param.getStand() + "%";
            param.setStand(stand);
            sql.WHERE("o.orderno in (select orderno from orderproduct where standard like #{stand})");
        }*/

        if (param.getStartTime() != null) {
            sql.WHERE(" Createtime >= #{startTime} ");
        }
        if (param.getEndTime() != null) {
            Calendar c = Calendar.getInstance();
            c.setTime(param.getEndTime());
            c.add(Calendar.DAY_OF_MONTH, 1);// 今天+1天
            Date tomorrow = c.getTime();
            param.setEndTime(tomorrow);
            sql.WHERE(" Createtime <= #{endTime} ");
        }

        /*if (param.getRegisterTimeStart() != null) {
            sql.WHERE(" mm.createdate >= #{registerTimeStart} ");
        }
        if (param.getRegisterTimeEnd() != null) {
            Calendar c = Calendar.getInstance();
            c.setTime(param.getRegisterTimeEnd());
            c.add(Calendar.DAY_OF_MONTH, 1);// 今天+1天
            Date tomorrow = c.getTime();
            param.setRegisterTimeEnd(tomorrow);
            sql.WHERE(" mm.createdate <= #{registerTimeEnd} ");
        }

        //商家是否接收订单
        if(param.getPresellconfim() != null && param.getPresellconfim()!= -1 ){
            sql.WHERE("presellconfim =#{presellconfim}");
        }
        //预计备货开始时间
        if (param.getPrestocktimeStart() != null) {
            sql.WHERE("prestocktime >=#{prestocktimeStart}");
        }
        //预计备货结束时间
        if (param.getPrestocktimeEnd() != null) {
            Calendar c = Calendar.getInstance();
            c.setTime(param.getPrestocktimeEnd());
            c.add(Calendar.DAY_OF_MONTH, 1);// 今天+1天
            Date tomorrow = c.getTime();
            param.setPrestocktimeEnd(tomorrow);
            sql.WHERE("prestocktime <=#{prestocktimeEnd}");
        }*/
        sql.ORDER_BY(" dataorder,o.createtime desc ");
        return  sql.toString();
    }

    private final String TBL_ORDER = "orders orders on op.orderno=orders.orderno";
    private final String TBL_ORDER_PRODUCT = "orderproduct op";
    private final String TBL_MEMBER = "member m on orders.memberid=m.id";

    public String queryOrderByParam(OrderQueryParam param) {
        SQL sql = new SQL().SELECT("distinct orders.orderno,orders.shipto,orders.phone,orders.\"id\",orders.totalprice,orders.freight," +
                "orders.deposit,orders.balance,orders.futuretime,orders.allpay,orders.ordertype,orders.\"membercompany\"," +
                "orders.code,orders.transactionnumber,orders.orderstatus,orders.createtime,orders.brokepay,orders.delaydays," +
                "orders.ifdelay,orders.delaypenalty,orders.deliverytype,orders.couriernumber,orders.logisticscompany,op.price," +
                "op.num,op.singlebrokepay ").FROM(TBL_ORDER_PRODUCT);
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

        //商家是否接收订单
        if(param.getPresellconfim() != null && param.getPresellconfim()!= -1 ){
            sql.WHERE("orders.presellconfim=#{presellconfim}");
        }
        //预计备货开始时间
        Date prestocktimeStart = param.getPrestocktimeStart();
        if (prestocktimeStart != null) {
            sql.WHERE("orders.prestocktime >=#{prestocktimeStart}");
        }
        //预计备货结束时间
        Date prestocktimeEnd = param.getPrestocktimeEnd();
        if (prestocktimeEnd != null) {
            Calendar c = Calendar.getInstance();
            c.setTime(prestocktimeEnd);
            c.add(Calendar.DAY_OF_MONTH, 1);// 今天+1天
            Date tomorrow = c.getTime();
            param.setPrestocktimeEnd(tomorrow);
            sql.WHERE("orders.prestocktime <=#{prestocktimeEnd}");
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
        sql.GROUP_BY("orders.id,op.price,op.num,op.singlebrokepay");
        sql.ORDER_BY("orders.id desc");
        return sql.toString();
    }




    public String queryOrderByParamForUser(OrderQueryParam param) {
        SQL sql = new SQL().SELECT(" DISTINCT orders.* ").FROM( "orders" );

        String brand = param.getBrand();
        String pdName = param.getPdName();
        String mark = param.getMark();
        Short backstate = param.getBackstate();
        Short evaState = param.getEvaState();
        String stand = param.getStand();
        Integer isbilling=param.getIsbilling();

        if(StringUtils.hasText(brand) || StringUtils.hasText(pdName) || StringUtils.hasText(mark) || backstate != null || evaState != null || StringUtils.hasText(stand)){
            sql.LEFT_OUTER_JOIN(" orderproduct op on orders.id=op.orderid ");
        }


        //sql.WHERE("orders.orderno is not null");
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

        //商家是否接收订单
        if(param.getPresellconfim() != null && param.getPresellconfim()!= -1 ){
            sql.WHERE("orders.presellconfim=#{presellconfim}");
        }
        //预计备货开始时间
        Date prestocktimeStart = param.getPrestocktimeStart();
        if (prestocktimeStart != null) {
            sql.WHERE("orders.prestocktime >=#{prestocktimeStart}");
        }
        //预计备货结束时间
        Date prestocktimeEnd = param.getPrestocktimeEnd();
        if (prestocktimeEnd != null) {
            Calendar c = Calendar.getInstance();
            c.setTime(prestocktimeEnd);
            c.add(Calendar.DAY_OF_MONTH, 1);// 今天+1天
            Date tomorrow = c.getTime();
            param.setPrestocktimeEnd(tomorrow);
            sql.WHERE("orders.prestocktime <=#{prestocktimeEnd}");
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
        if (StringUtils.hasText(brand)) {
            brand = "%" + brand + "%";
            param.setBrand(brand);
            sql.WHERE("op.brand LIKE #{brand}");
        }
        //印记

        if (StringUtils.hasText(mark)) {
            mark = "%" + mark + "%";
            param.setMark(mark);
            sql.WHERE("op.mark LIKE #{mark}");
        }
        //规格

        if (StringUtils.hasText(stand)) {
            stand = "%" + stand + "%";
            param.setStand(stand);
            sql.WHERE("op.standard LIKE #{stand}");
        }
        //订单状态 待收货传state3 但是状态3和10 都要查
        Short orderState = param.getOrderState();
        Short sendstatus = param.getSendstatus();
        if (orderState != null) {
            //sql.WHERE("orders.orderstatus=#{orderState}");
            if(orderState.toString().equals("11")){
                sql.WHERE(" (orders.orderstatus='7' and  orders.reason='卖家取消订单')");
            }else if(orderState== 3 && sendstatus ==null){
                //兼容app没传sendstatus情况
                sql.WHERE(" orders.orderstatus=#{orderState}");
            }else if(orderState== 3 && sendstatus== 3){
                //买家端/商家端--交易管理---订单管理--待收货 搜索3待收货  但是要查状态为3待收货和10部分发货的
                sql.WHERE(" (orders.orderstatus='3' or orders.orderstatus='10')");
            } else if(orderState== 3 && sendstatus== 10){
                //商家端--交易管理---订单管理--待发货 搜索10部分发货
                sql.WHERE("  orders.orderstatus='10'");
            }else if(orderState== 1 && sendstatus ==null){
                //兼容app没传sendstatus情况
                sql.WHERE(" orders.orderstatus=#{orderState}");
            }else if(orderState== 1 && sendstatus== 10){
                //买家端/商家端--交易管理--发货管理--待发货  orderstate只传1 但是要查状态为1待发货和10部分发货的
                sql.WHERE(" (orders.orderstatus='1' or orders.orderstatus='10')");
            }else {
                sql.WHERE(" orders.orderstatus=#{orderState}");
            }
        }

        if(orderState == null && sendstatus !=null){
            //所有订单 发货状态 搜索部分发货10 条件
            if(orderState ==null && sendstatus ==10){
                sql.WHERE(" orders.orderstatus=#{sendstatus}");
            }
            if(orderState == null && sendstatus ==3) {
                //所有订单 发货状态 搜索全部发货 传3  要求查出3和10条件
                sql.WHERE(" (orders.orderstatus='3' or orders.orderstatus='10')");
            }

        }
        //评价状态
        if (evaState != null) {
            sql.WHERE("op.evaluatestate=#{evaState}");
        }
        //退货状态
        if (backstate != null) {
            if(backstate == Quantity.STATE_1) {
                sql.WHERE("op.backstate in (1,5)");
            }else {
                sql.WHERE("op.backstate=#{backstate}");
            }
        }

        if (isbilling!=null&&(isbilling==0||isbilling==1)){
            sql.WHERE("orders.isbilling =#{isbilling}");
        }
       // sql.GROUP_BY("orders.id,op.price,op.num,op.singlebrokepay");
        sql.ORDER_BY("orders.id desc");
        return sql.toString();
    }


}
