package project.jinshang.scheduled;

import mizuki.project.core.restserver.config.BasicRet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import project.jinshang.common.constant.Quantity;
import project.jinshang.common.exception.CashException;
import project.jinshang.common.utils.JinShangSms;
import project.jinshang.mod_activity.bean.LimitTimeProd;
import project.jinshang.mod_activity.bean.LimitTimeStore;
import project.jinshang.mod_activity.service.LimitTimeProdService;
import project.jinshang.mod_activity.service.LimitTimeStoreService;
import project.jinshang.mod_admin.mod_inte.bean.IntegralRecord;
import project.jinshang.mod_admin.mod_inte.bean.IntegralSet;
import project.jinshang.mod_admin.mod_inte.service.IntegralService;
import project.jinshang.mod_admin.mod_transet.bean.TransactionSetting;
import project.jinshang.mod_admin.mod_transet.service.TransactionSettingService;
import project.jinshang.mod_cash.bean.BuyerCapital;
import project.jinshang.mod_cash.bean.SalerCapital;
import project.jinshang.mod_member.bean.Member;
import project.jinshang.mod_member.bean.SellerCategory;
import project.jinshang.mod_member.service.MemberService;
import project.jinshang.mod_pay.bean.Refund;
import project.jinshang.mod_pay.mod_alipay.AlipayService;
import project.jinshang.mod_pay.mod_wxpay.MyWxPayService;
import project.jinshang.mod_pay.service.TradeService;
import project.jinshang.mod_product.bean.*;
import project.jinshang.mod_product.service.*;
import project.jinshang.mod_wms_middleware.WMSService;
import project.jinshang.scheduled.mapper.OrderTaskMapper;

import java.math.BigDecimal;
import java.util.*;

@Component
@Transactional(rollbackFor = Exception.class)
@Profile({"test","pro"})
public class QuartzTask {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private ShopCarService shopCarService;
    private static final BigDecimal penalty = new BigDecimal(0.02);

    @Autowired
    private TransactionSettingService transactionSettingService;

    @Autowired
    private MyWxPayService wxPayService;
    @Autowired
    private AlipayService alipayService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private OrderProductBackService orderProductBackService;

    @Autowired
    private BillingRecordService billingRecordService;

    @Autowired
    private OrderProductServices orderProductServices;

    @Autowired
    private LimitTimeProdService limitTimeProdService;

    @Autowired
    private LimitTimeStoreService limitTimeStoreService;

    @Autowired
    private  WMSService wmsService;

    @Autowired
    private OrderTaskMapper orderTaskMapper;

    @Autowired
    private CategoriesService categoriesService;

    @Autowired
    private IntegralService integralService;

    @Autowired
    private TradeService tradeService;

    @Autowired
    JinShangSms jinShangSms;

    @Autowired
    private ProductStoreService productStoreService;

    /**
     * 自动关闭超时未付款订单
     */
    @Scheduled(cron = "0 0/3 * * * ?")
    public void updateNotPayOrdersForFinish(){
        System.out.println("更新超时付款订单状态为已关闭");
        TransactionSetting transactionSetting = transactionSettingService.getTransactionSetting();

        BigDecimal unpaidtimeout = transactionSetting.getUnpaidtimeout();
        int unpaidtimeoutHour = unpaidtimeout.intValue();

        List<Orders> orders = orderTaskMapper.getOuttimeNotPayOrders(unpaidtimeoutHour+" hour");
        for (Orders order : orders) {
            if (order.getCreatetime() != null) {
                Orders updateOrder = new Orders();
                updateOrder.setId(order.getId());
                updateOrder.setOrderstatus(Quantity.STATE_7);
                updateOrder.setReason("订单超时取消");
                ordersService.updateSingleOrder(updateOrder);
                //将商品库存加回去
                List<OrderProduct> orderProductList = ordersService.getOrderProductByOrderId(order.getId());
                for (OrderProduct orderProduct : orderProductList) {
                    productStoreService.addStoreNumByPdidAndPdno(orderProduct.getPdid(), orderProduct.getPdno(), orderProduct.getNum());
                }
            }
        }
        System.out.println("更新超时付款订单完成");
    }



    /**
     * 冻结金额到货款自动确认
     * 之前应杰写的在最下面
     */
    /*
   // @Scheduled(cron = "0 0/3 * * * ?")
    public void autoFreezeToGoodsBanlance() throws CashException {
        System.out.println("自动执行冻结金额到货款---开始");
        Date date=new Date();

        TransactionSetting transactionSetting = transactionSettingService.getTransactionSetting();

        BigDecimal numday = transactionSetting.getFreezetotogoodspay();
        String intervalday = "'"+numday+" day'";

        //测试
//        BigDecimal numday = new BigDecimal(2);
//        String intervalday = "'"+numday+" min'";

        List<Orders> ordersList = ordersService.getSellerSettleOrdersList(intervalday,date);

        //更新冻结金额，货款金额和可开票金额
        for(Orders  orders:ordersList){
            Long sellerId = orders.getSaleid();
            Member seller = memberService.getMemberById(sellerId);
            BigDecimal differpay = orders.getFrozepay();

            memberService.updateSellerMemberBalanceInDb(seller.getId(),new BigDecimal(0),differpay.multiply(new BigDecimal(-1)),differpay,differpay);
            Member member = memberService.getMemberById(seller.getId());
            if(member.getSellerfreezebanlance().compareTo(Quantity.BIG_DECIMAL_0) <0 ){
                logger.error("冻结金额到货款自动确认 "+seller.getUsername()+"冻结金额不足");
                throw new CashException(seller.getUsername()+"冻结金额不足");
            }

            //将订单标记为结算状态
            Orders updateOrders = new Orders();
            updateOrders.setId(orders.getId());
            updateOrders.setPaymentmethod(Quantity.STATE_1);
            ordersService.updateSingleOrder(updateOrders);
        }

        //以下代码没有什么实际作用（先暂时保留，后期看卖家已结算字段是否有使用，没有使用的话拿掉该字段）
        List<SalerCapital> salerCapitals = ordersService.getSalerCapitalList(intervalday,date);
        //更新资金明细设为已结算
        for(SalerCapital salerCapital : salerCapitals){
            SalerCapital updateSalerCapital = new SalerCapital();
            updateSalerCapital.setId(salerCapital.getId());
            updateSalerCapital.setSettlement(Quantity.STATE_1);
            ordersService.updateSalerCapital(updateSalerCapital);
        }

        System.out.println("自动执行冻结金额到货款---结束");

    }
    */


     @Scheduled(cron = "0 0/3 * * * ?")
    public void autoFreezeToGoodsBanlance() throws CashException {
        System.out.println("自动执行冻结金额到货款---开始");
        //Date date=new Date();

        List<Orders> ordersList = ordersService.getSellerSettleOrdersList();

        //更新冻结金额，货款金额和可开票金额
        for(Orders  orders:ordersList){
            Long sellerId = orders.getSaleid();
            Member seller = memberService.getMemberById(sellerId);
            BigDecimal differpay = orders.getFrozepay();


            memberService.updateSellerMemberBalanceInDb(seller.getId(),new BigDecimal(0),differpay.multiply(new BigDecimal(-1)),differpay.subtract(orders.getBrokepay()),differpay);
            Member member = memberService.getMemberById(seller.getId());
            if(member.getSellerfreezebanlance().compareTo(Quantity.BIG_DECIMAL_0) <0 ){
                logger.error("冻结金额到货款自动确认 "+seller.getUsername()+"冻结金额不足");
                throw new CashException(seller.getUsername()+"冻结金额不足");
            }

            //将订单标记为已结算状态
//            Orders updateOrders = new Orders();
//            updateOrders.setId(orders.getId());
//            updateOrders.setPaymentmethod(Quantity.STATE_1);
//            ordersService.updateSingleOrder(updateOrders);
            //将订单标记为已结算状态
            if(orderTaskMapper.settleOrders(orders.getId()) != 1){
                throw  new CashException(orders.getOrderno()+"订单状态不正确,无法结算");
            }
        }


        /*
        //以下代码没有什么实际作用（先暂时保留，后期看卖家已结算字段是否有使用，没有使用的话拿掉该字段）
        List<SalerCapital> salerCapitals = ordersService.getSalerCapitalList(intervalday,date);
        //更新资金明细设为已结算
        for(SalerCapital salerCapital : salerCapitals){
            SalerCapital updateSalerCapital = new SalerCapital();
            updateSalerCapital.setId(salerCapital.getId());
            updateSalerCapital.setSettlement(Quantity.STATE_1);
            ordersService.updateSalerCapital(updateSalerCapital);
        }*/

        System.out.println("自动执行冻结金额到货款---结束");

    }






    /**
     * 买家订单自动确认收货
      */
    @Scheduled(cron = "0 0 0/2 * * ?")
   public void  autoDeliveryGoods(){
       System.out.println("买家订单自动确认收货---开始");

       TransactionSetting transactionSetting = transactionSettingService.getTransactionSetting();
       BigDecimal confirmreceipttimeout = transactionSetting.getConfirmreceipttimeout();

       //正式环境使用
       String intervalday = "'"+confirmreceipttimeout+" day'";

       //测试使用
       //confirmreceipttimeout = new BigDecimal(2);
       //String intervalday = "'"+confirmreceipttimeout+" min'";

       orderTaskMapper.updateTimeOutNotReviceOrdersToRevice(intervalday);

       System.out.println("买家订单自动确认收货---结束");
   }


    /**
     * 买家订单自动确认验货
     */
    @Scheduled(cron = "0 0 0/3 * * ?")
    public void  autoConfirmGoods() throws CashException {
        try {
            System.out.println("买家订单自动确认验货---开始");

            TransactionSetting transactionSetting = transactionSettingService.getTransactionSetting();
            BigDecimal inspectionperiod = transactionSetting.getInspectionperiod();

            //正式环境使用
            String intervalday = "'"+inspectionperiod+" day'";

            //测试使用
//        inspectionperiod = new BigDecimal(2);
//        String intervalday = "'"+inspectionperiod+" min'";


            List<Orders> ordersList = orderTaskMapper.getTimeOutNotConfirmOrders(intervalday);

            for(Orders orders : ordersList){

                Member member = memberService.getMemberById(orders.getMemberid());

                //计算订单金额和佣金，并充值到余额和开票金额
                List<OrderProduct> list = ordersService.getOrderProductByOrderNo(orders.getOrderno());
                //判断是否有退货商品
                //实际支付的总额
                BigDecimal sellerpay = BigDecimal.valueOf(0);

                //运费
                BigDecimal frigthpay = BigDecimal.valueOf(0);

                //总的佣金
                BigDecimal totalBroke = BigDecimal.valueOf(0);

                //服务费
                BigDecimal totalServerPay = BigDecimal.valueOf(0);

                //获取紧固件积分规则
                IntegralSet integralSet1 = integralService.getIntegralSetByType(Quantity.STATE_0);
                //获取其它积分规则
                IntegralSet integralSet2 = integralService.getIntegralSetByType(Quantity.STATE_1);
                //销量统计
                Map<Long, BigDecimal> pdsaleNumMap = new HashMap<>();
                for (OrderProduct orderProduct : list) {
                    //退货状态0=正常1=退货中2=退货验收3=退货完成4=异议中
                    if (orderProduct.getBackstate() == Quantity.STATE_1 || orderProduct.getBackstate() == Quantity.STATE_2 || orderProduct.getBackstate() == Quantity.STATE_4) {
                        continue;
                    }

                    if (orderProduct.getBackstate() == Quantity.STATE_0) {

                        BigDecimal saleNum = pdsaleNumMap.get(orderProduct.getPdid());

                        if (saleNum == null) {
                            pdsaleNumMap.put(orderProduct.getPdid(), orderProduct.getNum());
                        } else {
                            pdsaleNumMap.put(orderProduct.getPdid(), orderProduct.getNum().add(saleNum));
                        }

                        //计算佣金
                        Long classifyid = orderProduct.getClassifyid();
                        BigDecimal rate = BigDecimal.valueOf(0);

                        //商家分类
                        SellerCategory sellerCategory = ordersService.getSellerCategory(classifyid, orderProduct.getSellerid());
                        if (sellerCategory != null) {
                            if (sellerCategory.getBrokeragerate().compareTo(new BigDecimal(-1)) == 0) {
                                SellerCategory sellerCategory1 = ordersService.getSellerCategory(sellerCategory.getParentid(), orderProduct.getSellerid());
                                if (sellerCategory1 != null) {
                                    if (sellerCategory1.getBrokeragerate().compareTo(new BigDecimal(-1)) == 0) {
                                        SellerCategory sellerCategory2 = ordersService.getSellerCategory(sellerCategory1.getParentid(), orderProduct.getSellerid());
                                        if (sellerCategory2 != null) {
                                            rate = sellerCategory2.getBrokeragerate();
                                        }
                                    } else {
                                        rate = sellerCategory1.getBrokeragerate();
                                    }
                                }
                            } else {
                                rate = sellerCategory.getBrokeragerate();
                            }
                        }

                        Categories categories = ordersService.getCategories(classifyid);
                        if (categories != null) {
                            //佣金比率
                            BigDecimal brolerRate =rate.multiply(BigDecimal.valueOf(0.01));
                            if(brolerRate.compareTo(BigDecimal.valueOf(0))<0){
                                brolerRate = getBrokerRate(classifyid).multiply(BigDecimal.valueOf(0.01));
                            }

                            //服务费比率 用的是category中设置的
                            BigDecimal serverRate = getServerRate(classifyid).multiply(BigDecimal.valueOf(0.01));

                            //商品单位佣金=销售单价*商品佣金比例
                            BigDecimal singlebrokepay = orderProduct.getPrice().multiply(brolerRate);

                            //商品佣金=商品单位佣金*商品数量
                            //BigDecimal broker = (orderProduct.getActualpayment().subtract(orderProduct.getFreight())).multiply(brolerRate);
                            BigDecimal broker = singlebrokepay.multiply(orderProduct.getNum());

                            OrderProduct updateOrderProduct = new OrderProduct();
                            updateOrderProduct.setId(orderProduct.getId());
                            updateOrderProduct.setSinglebrokepay(singlebrokepay);
                            updateOrderProduct.setBrokepay(broker);
                            ordersService.updateOrderProduct(updateOrderProduct);


                            BigDecimal servepay = (orderProduct.getActualpayment().subtract(orderProduct.getFreight())).multiply(serverRate);
                            totalBroke = totalBroke.add(broker);
                            totalServerPay = totalServerPay.add(servepay);
                        }

                        sellerpay = sellerpay.add(orderProduct.getActualpayment());
                        frigthpay = frigthpay.add(orderProduct.getFreight());
                    }
                }
                //保存销量
                for (Long pdid : pdsaleNumMap.keySet()) {
                    ProductInfo info = ordersService.getProductInfoByPrimeKey(pdid);
                    if (info != null) {
                        BigDecimal salenum = pdsaleNumMap.get(pdid);
                        info.setSalesnum(info.getSalesnum() + salenum.longValue());
                        ordersService.saveProductInfo(info);
                    }
                }

                BigDecimal scope = BigDecimal.valueOf(0);
                //紧固件
                if (list.get(0).getProducttype() == Quantity.STATE_1) {
                    scope = integralSet1.getScope();
                } else {
                    scope = integralSet2.getScope();
                }

                BigDecimal allPdPay = sellerpay.subtract(frigthpay);


                BigDecimal integralValue = Quantity.BIG_DECIMAL_0;

                if(scope.compareTo(Quantity.BIG_DECIMAL_0) >0){
                    integralValue = allPdPay.divideToIntegralValue(scope);
                }

                if(integralValue.compareTo(Quantity.BIG_DECIMAL_0)>0 && member != null) {
                    IntegralRecord integralRecord = new IntegralRecord();
                    integralRecord.setMemberid(member.getId());
                    integralRecord.setMembername(member.getUsername());
                    integralRecord.setOrderid(orders.getId());
                    integralRecord.setScope(integralValue);
                    integralRecord.setType(Quantity.STATE_0);
                    integralRecord.setCreatetime(new Date());
                    integralRecord.setRemark("订单积分");
                    integralService.saveIntegralRecord(integralRecord);

                    memberService.updateIntegralInDb(member.getId(),integralValue,integralValue);
                }

                if(orders.getBillingtype() == Quantity.STATE_1){  //提交订单时选择开票  将billingrecord表中的state 字段由-1更改为0（待开票状态）
                    //并且要查询该订单中是否有退款的，如果有开票金额减去退款金额

                    //查询是否有退货或退款的，如果有退货开票金额要减去退货的钱
                    List<OrderProductBack> orderProductBackList =  orderProductBackService.getByOrderNo(orders.getOrderno());
                    BigDecimal subApply =  new BigDecimal(0);
                    for(OrderProductBack opb : orderProductBackList){
                        subApply =  subApply.add(opb.getBackmoney());
                    }

                    billingRecordService.updateForwordBillingState(orders.getId().toString(),orders.getMemberid(),subApply.multiply(new BigDecimal(-1)));
                }


                Member seller = memberService.getMemberById(orders.getSaleid());
                //BigDecimal frozepay = sellerpay.subtract(totalBroke).setScale(2,BigDecimal.ROUND_HALF_UP);\
                BigDecimal frozepay = sellerpay.setScale(2,BigDecimal.ROUND_HALF_UP);
                if (seller != null) {
                    //增加卖家冻结金额
                    memberService.updateSellerMemberBalanceInDb(seller.getId(),Quantity.BIG_DECIMAL_0,frozepay);
                }

                orders.setBrokepay(totalBroke);
                orders.setFrozepay(frozepay);
                orders.setServerpay(totalServerPay);

                int count = ordersService.updateOrdersConfirmgoods(orders);
                if(count != 1){
                    throw new CashException("买家订单自动确认验货出现错误，可能出现并发更新问题，所有操作已回撤，订单id:"+orders.getId());
                }

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("买家订单自动确认验货---结束");
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }







    /**
     * 订单自动确认
     */
    /*
    //@Scheduled(cron = "0 0/5 * * * ?")
    public void autoFinishOrder() throws CashException {
        System.out.println("订单自动确认开始");
        TransactionSetting transactionSetting = transactionSettingService.getTransactionSetting();
        BigDecimal confirmreceipttimeout = transactionSetting.getConfirmreceipttimeout();
        BigDecimal inspectionperiod = transactionSetting.getInspectionperiod();
        int confirmDay = confirmreceipttimeout.intValue();
        int inspectionperiodDay = inspectionperiod.intValue();
        List<Orders> list = ordersService.getNotOverOrders();
        List<Orders> orders = new ArrayList<>();
        Calendar c = Calendar.getInstance();
        //c.setTime(endTime);
        //c.add(Calendar.DAY_OF_MONTH, 1);// 今天+1天
        //Date tomorrow = c.getTime();
        for(Orders order:list){
            boolean flag = true;
            //总的佣金
            BigDecimal totalBroke = new BigDecimal(0);
            //服务费
            BigDecimal totalServerPay = new BigDecimal(0);

            BigDecimal sellerpay = new BigDecimal(0);

            BigDecimal frigthpay = new BigDecimal(0);

            List<OrderProduct> orderProducts = ordersService.getOrderProductByOrderId(order.getId());
            //验证是否有退货
            for(OrderProduct orderProduct:orderProducts){
                //退货还未完成
                if(orderProduct.getBackstate()==Quantity.STATE_1||orderProduct.getBackstate()==Quantity.STATE_2||orderProduct.getBackstate()==Quantity.STATE_4){
                    flag = false;
                    break;
                }
            }
            if(flag){
                Short status = order.getOrderstatus();
                //15天收货期
                if(status== Quantity.STATE_3){
                    Date deliverTime = order.getSellerdeliverytime();
                    if (deliverTime!=null){
                        c.setTime(deliverTime);
                        c.add(Calendar.DAY_OF_MONTH, confirmDay);
                        //c.add(Calendar.MINUTE, 5);
                        Date tomorrow = c.getTime();
                        Date today = new Date();
                        //如果大于
                        if(today.compareTo(tomorrow)==1){
                            order.setOrderstatus(Quantity.STATE_4);
                            order.setBuyerdeliverytime(new Date());
                            orders.add(order);
                        }

                    }
                }
                //10天验货期
                if(status==Quantity.STATE_4){
                    //销量统计
                    Map<Long, BigDecimal> pdsaleNumMap = new HashMap<>();

                    for(OrderProduct orderProduct:orderProducts){
//                        if (orderProduct.getBackstate() == Quantity.STATE_0) {
//                            //计算佣金
//                            Long classifyid = orderProduct.getClassifyid();
//                            //分类
//                            Categories categories = ordersService.getCategories(classifyid);
//                            if (categories != null) {
//                                //佣金比率
//                                BigDecimal brolerRate = categories.getBrokeragerate().multiply(new BigDecimal(0.01));
//
//                                //服务费比率
//                                BigDecimal serverRate = categories.getServicesrate().multiply(new BigDecimal(0.01));
//
//
//                                //佣金
//                                BigDecimal broker = (orderProduct.getActualpayment().subtract(orderProduct.getFreight())).multiply(brolerRate);
//                                BigDecimal servepay = (orderProduct.getActualpayment().subtract(orderProduct.getFreight())).multiply(serverRate);
//                                totalBroke = totalBroke.add(broker);
//                                totalServerPay = totalServerPay.add(servepay);
//                            }
//
//                            sellerpay = sellerpay.add(orderProduct.getActualpayment());
//                        }



                        if(orderProduct.getBackstate() == Quantity.STATE_0){
                            {

                                BigDecimal saleNum = pdsaleNumMap.get(orderProduct.getPdid());

                                if (saleNum == null) {
                                    pdsaleNumMap.put(orderProduct.getPdid(), orderProduct.getNum());
                                } else {
                                    pdsaleNumMap.put(orderProduct.getPdid(), orderProduct.getNum().add(saleNum));
                                }

                                //计算佣金
                                Long classifyid = orderProduct.getClassifyid();
                                BigDecimal rate = BigDecimal.valueOf(0);
                                //商家分类
                                SellerCategory sellerCategory = ordersService.getSellerCategory(classifyid, orderProduct.getSellerid());
                                if (sellerCategory != null) {
                                    if (sellerCategory.getBrokeragerate().compareTo(new BigDecimal(-1)) == 0) {
                                        SellerCategory sellerCategory1 = ordersService.getSellerCategory(sellerCategory.getParentid(), orderProduct.getSellerid());
                                        if (sellerCategory1 != null) {
                                            if (sellerCategory1.getBrokeragerate().compareTo(new BigDecimal(-1)) == 0) {
                                                SellerCategory sellerCategory2 = ordersService.getSellerCategory(sellerCategory1.getParentid(), orderProduct.getSellerid());
                                                if (sellerCategory2 != null) {
                                                    rate = sellerCategory2.getBrokeragerate();
                                                }
                                            } else {
                                                rate = sellerCategory1.getBrokeragerate();
                                            }
                                        }
                                    } else {
                                        rate = sellerCategory.getBrokeragerate();
                                    }
                                }

                                Categories categories = ordersService.getCategories(classifyid);
                                if (sellerCategory != null && categories != null) {
                                    //佣金比率
                                    BigDecimal brolerRate =rate.multiply(BigDecimal.valueOf(0.01));
                                    if(brolerRate.compareTo(BigDecimal.valueOf(0))<0){
                                        brolerRate = fetchServerOrBrokerRate(categories,"broker").multiply(BigDecimal.valueOf(0.01));
                                    }
                                    //服务费比率 用的是category中设置的
                                    BigDecimal serverRate = categories.getServicesrate().multiply(BigDecimal.valueOf(0.01));
                                    if(serverRate.compareTo(BigDecimal.valueOf(0))<0){
                                        serverRate = fetchServerOrBrokerRate(categories,"server").multiply(BigDecimal.valueOf(0.01));
                                    }

                                    //佣金
                                    BigDecimal broker = (orderProduct.getActualpayment().subtract(orderProduct.getFreight())).multiply(brolerRate);
                                    BigDecimal servepay = (orderProduct.getActualpayment().subtract(orderProduct.getFreight())).multiply(serverRate);
                                    totalBroke = totalBroke.add(broker);
                                    totalServerPay = totalServerPay.add(servepay);
                                }


                                sellerpay = sellerpay.add(orderProduct.getActualpayment());
                                frigthpay = frigthpay.add(orderProduct.getFreight());
                            }
                        }



                        //保存销量
                        for (Long pdid : pdsaleNumMap.keySet()) {
                            ProductInfo info = ordersService.getProductInfoByPrimeKey(pdid);
                            if (info != null) {
                                BigDecimal salenum = pdsaleNumMap.get(pdid);
                                info.setSalesnum(info.getSalesnum() + salenum.longValue());
                                ordersService.saveProductInfo(info);
                            }
                        }
                    }


                    Date deliverTime = order.getBuyerdeliverytime();
                    if (deliverTime!=null){
                         c.setTime(deliverTime);
                         c.add(Calendar.DAY_OF_MONTH, inspectionperiodDay);
                        //c.add(Calendar.MINUTE, 5);
                        Date tomorrow = c.getTime();
                        Date today = new Date();
                        //如果大于
                        if(today.compareTo(tomorrow)==1){
                            order.setOrderstatus(Quantity.STATE_5);
                            order.setBuyerinspectiontime(new Date());
                            orders.add(order);


                            if(order.getBillingtype() == Quantity.STATE_1){  //提交订单时选择开票  将billingrecord表中的state 字段由-1更改为0（待开票状态）
                                //并且要查询该订单中是否有退款的，如果有开票金额减去退款金额

                                //查询是否有退货或退款的，如果有退货开票金额要减去退货的钱
                                List<OrderProductBack> orderProductBackList =  orderProductBackService.getByOrderNo(order.getOrderno());
                                BigDecimal subApply =  new BigDecimal(0);
                                for(OrderProductBack opb : orderProductBackList){
                                    subApply =  subApply.add(opb.getBackmoney());
                                }

                                billingRecordService.updateForwordBillingState(order.getId().toString(),order.getMemberid(),subApply.multiply(new BigDecimal(-1)));
                            }



                            //计算订单金额和佣金，并充值到卖家余额和开票金额
                            Member seller = memberService.getMemberById(order.getSaleid());
                            Member oldSeller = new Member();
                            BeanUtils.copyProperties(seller,oldSeller);
                            BigDecimal frozepay = sellerpay.subtract(totalBroke);
                            System.out.println("订单号："+order.getOrderno()+"卖家冻结金额为："+frozepay);
                            if(seller!=null){
                                //增加卖家冻结金额
                                seller.setSellerfreezebanlance(seller.getSellerfreezebanlance().add(frozepay));
                                ordersService.saveMember(seller,oldSeller);
                            }
                            order.setBrokepay(totalBroke);
                            order.setFrozepay(frozepay);
                            order.setServerpay(totalServerPay);
                            orders.add(order);
                        }

                    }
                }
            }else {
                continue;
            }

        }
        //保存订单
        for(Orders orders1:orders){
            ordersService.updateSingleOrder(orders1);
        }
        System.out.println("订单完成");
    }

*/

    /*
    private BigDecimal fetchServerOrBrokerRate(Categories categories, String type){
        BigDecimal ret = BigDecimal.valueOf(0);
        if("broker".equals(type)){
            ret = categories.getBrokeragerate();
        }else if("server".equals(type)){
            ret = categories.getServicesrate();
        }
        if(ret.compareTo(BigDecimal.valueOf(0))<0 && categories.getParentid()>0){
            Categories categories1 = ordersService.getCategories(categories.getParentid());
            if(categories1!=null){
                ret = fetchServerOrBrokerRate(categories1, type);
            }
        }
        return ret;

    }*/




    /**
     * 退货自动执行
     */

    //@Scheduled(cron = "0 15 3 ? * *")
    //@Scheduled(cron = "0 0/2 * * * ?")
    /*
    public void autoHandleOrderBack() throws CashException {
        System.out.println("开始执行退货");
        TransactionSetting transactionSetting = transactionSettingService.getTransactionSetting();
        BigDecimal orderreturnperiod = transactionSetting.getOrderreturnperiod();
        int orderreturnperiodDay = orderreturnperiod.intValue();
        List<OrderProductBack> list = ordersService.getNotSellerHandle();
        Calendar c = Calendar.getInstance();
        for(OrderProductBack orderProductBack : list){
            OrderProduct orderProduct = ordersService.getOrderProductById(orderProductBack.getOrderpdid());
            Orders orders = ordersService.getOrdersById(orderProductBack.getOrderid());
            Member seller = memberService.getMemberById(orderProductBack.getSellerid());
            //计算时间
            Date createTime = orderProductBack.getCreatetime();
            if (createTime!=null && orderProduct!=null){
                c.setTime(createTime);
                //c.add(Calendar.DAY_OF_MONTH, orderreturnperiodDay);
                c.add(Calendar.MINUTE, 5);
                Date tomorrow = c.getTime();
                Date today = new Date();
                //如果大于
                if(today.compareTo(tomorrow)==1){
                    //只退款
                    if(orderProductBack.getBacktype()==Quantity.STATE_0){
                        System.out.println("退货订单号："+orderProductBack.getOrderno()+"退货商品id："+orderProductBack.getOrderpdid()+"退货号："+orderProductBack.getBackno());
                        orderProduct.setBackstate(Quantity.STATE_3);
                        handleBackGoods(seller, orderProductBack, orderProduct, orders);
                        orderProductBack.setState(Quantity.STATE_10);
                        //增加库存
                        if (orders.getIsonline() == Quantity.STATE_0) {
                            ProductStore store = ordersService.getProductStore(orderProductBack.getPdid(),orderProductBack.getPdno() ,orderProductBack.getStoreid());
                            if(store!=null){
                                store.setPdstorenum(store.getPdstorenum().add(orderProductBack.getPdnum()));
                                ordersService.updateProductStore(store);
                            }
                        }
                        if (orders.getIsonline() == Quantity.STATE_2) {
                            LimitTimeStore limitTimeStore = shopCarService.getLimitTimeStore(orderProductBack.getStoreid());
                            LimitTimeProd limitTimeProd = shopCarService.getLimitTimeProd(orderProductBack.getPdid(),orderProductBack.getLimitid());
                            if(limitTimeProd!=null&&limitTimeStore!=null){
                                limitTimeStore.setStorenum(limitTimeStore.getStorenum().add(orderProductBack.getPdnum()));
                                limitTimeStore.setSalesnum(limitTimeStore.getSalesnum().subtract(orderProductBack.getPdnum()));
                                shopCarService.updateLimitTimeStore(limitTimeStore);
                                limitTimeProd.setSalestotalnum(limitTimeProd.getSalestotalnum().subtract(orderProduct.getNum()));
                                shopCarService.updateLimitTimeProd(limitTimeProd);
                            }
                        }
                    }
                    ordersService.updateOrderProduct(orderProduct);
                    ordersService.updateOrderProductBack(orderProductBack);
                }

            }
        }
        System.out.println("退货完成");
    }
    */





    /**
     * 退款操作
     *
     * @param operator 操作人
     * @param orderProductBack
     * @param orderProduct
     */
    public void handleBackGoods(Member operator, OrderProductBack orderProductBack, OrderProduct orderProduct, Orders order) throws CashException {

        Member oldOperator = new Member();
        BeanUtils.copyProperties(operator,oldOperator);

        //违约金占比
        BigDecimal getLiquidated = transactionSettingService.getTransactionSetting().getGetliquidated().multiply(new BigDecimal(0.01));
        BigDecimal forwardsalesmargin = transactionSettingService.getTransactionSetting().getForwardsalesmargin().multiply(new BigDecimal(0.01));
        if (orderProductBack != null && orderProduct != null && order != null) {
            //退款申请人
            Member buyer = memberService.getMemberById(orderProductBack.getMemberid());
            Member oldBuyer = new Member();
            BeanUtils.copyProperties(buyer,oldBuyer);

            BigDecimal backPay = new BigDecimal(0);
            BigDecimal penaltyPay = new BigDecimal(0);

            Date tranTime = new Date();
            List<BuyerCapital> buyerCapitals = new ArrayList<BuyerCapital>();
            List<SalerCapital> salerCapitals = new ArrayList<SalerCapital>();
            //买家退款资金明细
            BuyerCapital buyerCapital1 = null;
            //买家违约资金明细
            BuyerCapital buyerCapital2 = null;
            //卖家退款资金明细
            SalerCapital salerCapital1 = null;
            //卖家违约资金明细
            SalerCapital salerCapital2 = null;
            //立即发货,只有立即发货有部分退款
            if (orderProduct.getProtype() == Quantity.STATE_0) {
                BigDecimal orderPay = orderProduct.getActualpayment().subtract(orderProduct.getFreight());
                //是否有部分退货的情况
                BigDecimal backPayMoney = orderProductBack.getBackmoney();
                //违约金
                penaltyPay = orderPay.multiply(getLiquidated);
                //退回的金额,异议扣违约金
                if (orderProductBack.getAdminstate() == Quantity.STATE_2) {
                    backPay = backPayMoney.subtract(penaltyPay);
                } else {
                    backPay = backPayMoney;
                }
                //判断退回到余额还是授信
                BuyerCapital buyerCapital = ordersService.getBuyerCapitalByNoType(orderProduct.getOrderno(), Quantity.STATE_0);
                if (buyerCapital != null) {
                    //退回到余额
                    if (buyerCapital.getPaytype() == Quantity.STATE_3) {
                        buyer.setBalance(buyer.getBalance().add(backPay));
                        buyerCapital1 = createBuyerBackPay(order, backPay, tranTime, Quantity.STATE_3);
                        salerCapital1 = createSalerBackPay(order, backPay, tranTime);
                        buyerCapitals.add(buyerCapital1);
                        salerCapitals.add(salerCapital1);
                    }
                    //退回到授信
                    if (buyerCapital.getPaytype() == Quantity.STATE_4) {
                        buyer.setAvailablelimit(buyer.getAvailablelimit().add(backPay));
                        buyer.setUsedlimit(buyer.getUsedlimit().subtract(backPay));
                        buyerCapital1 = createBuyerBackPay(order, backPay, tranTime, Quantity.STATE_4);
                        salerCapital1 = createSalerBackPay(order, backPay, tranTime);
                        buyerCapitals.add(buyerCapital1);
                        salerCapitals.add(salerCapital1);
                    }
                    //退回到支付宝或微信
                    if(buyerCapital.getPaytype()==Quantity.STATE_0||buyerCapital.getPaytype()==Quantity.STATE_1){
                        String uuid = order.getUuid();
                        if(uuid!=null){
                            Refund refund = new Refund();
                            refund.setOutTradeNo(uuid);
                            if(order.getPaytype()==Quantity.STATE_0){
                                refund.setChannel("alipay");
                            }else {
                                refund.setChannel("wxpay");
                            }
                            refund.setRefundAmount((backPay.multiply(new BigDecimal(100))).longValue());
                            refund.setRefundReason("订单退款");
                            List<Orders> ordersList = ordersService.getOrdersByUUID(uuid);
                            BigDecimal totalAmout = new BigDecimal(0);
                            for(Orders od : ordersList){
                                totalAmout = totalAmout.add(od.getActualpayment());
                            }
                            refund.setTotalAmount((totalAmout.multiply(new BigDecimal(100))).longValue());
                            boolean result = false;
                            try {
                                if("alipay".equals(refund.getChannel())){
                                    result = alipayService.refund(refund);
                                }else if("wxpay".equals(refund.getChannel())){
                                    result = wxPayService.refund(refund);
                                }
                            }catch (Exception e){

                            }

                            System.out.println("退货通道："+refund.getChannel()+"退货结果："+result);
                            if(result){
                                if(order.getPaytype()==Quantity.STATE_0){
                                    buyerCapital1 = createBuyerBackPay(order, backPay, tranTime, Quantity.STATE_0);
                                }else {
                                    buyerCapital1 = createBuyerBackPay(order, backPay, tranTime, Quantity.STATE_1);
                                }
                                salerCapital1 = createSalerBackPay(order, backPay, tranTime);
                                buyerCapitals.add(buyerCapital1);
                                salerCapitals.add(salerCapital1);
                            }

                        }
                    }
                    //异议扣违约金,记录资金明细
                    if (orderProductBack.getAdminstate() == Quantity.STATE_2) {
                        operator.setSellerbanlance(operator.getSellerbanlance().add(penaltyPay.multiply(forwardsalesmargin)));
                        //违约金
                        buyerCapital2 = createBuyerPenaltyPay(order, penaltyPay, tranTime, Quantity.STATE_6, orderPay, Quantity.BUYER_BACK_REASON);
                        salerCapital2 = createSalerPenaltyPay(order, penaltyPay, tranTime, Quantity.STATE_6, orderPay, Quantity.BUYER_BACK_REASON);
                        buyerCapitals.add(buyerCapital2);
                        salerCapitals.add(salerCapital2);
                    }
                }
            }
            //远期全款
            if (orderProduct.getProtype() == Quantity.STATE_1) {
                //计算扣除违约金
                BigDecimal orderPay = orderProduct.getAllpay();
                //违约金
                penaltyPay = orderPay.multiply(getLiquidated);
                //退回的金额,异议扣违约金
                if (orderProductBack.getAdminstate() == Quantity.STATE_2) {
                    backPay = orderPay.subtract(penaltyPay);
                } else {
                    backPay = orderPay;
                }

                //判断退回到余额还是授信
                BuyerCapital buyerCapital = ordersService.getBuyerCapitalByNoType(orderProduct.getOrderno(), Quantity.STATE_9);
                if (buyerCapital != null) {
                    //退回到余额
                    if (buyerCapital.getPaytype() == Quantity.STATE_3) {
                        buyer.setBalance(buyer.getBalance().add(backPay));
                        buyerCapital1 = createBuyerBackPay(order, backPay, tranTime, Quantity.STATE_3);
                        salerCapital1 = createSalerBackPay(order, backPay, tranTime);
                        buyerCapitals.add(buyerCapital1);
                        salerCapitals.add(salerCapital1);
                    }
                    //退回到授信
                    if (buyerCapital.getPaytype() == Quantity.STATE_4) {
                        buyer.setAvailablelimit(buyer.getAvailablelimit().add(backPay));
                        buyer.setUsedlimit(buyer.getUsedlimit().subtract(backPay));
                        buyerCapital1 = createBuyerBackPay(order, backPay, tranTime, Quantity.STATE_4);
                        salerCapital1 = createSalerBackPay(order, backPay, tranTime);
                        buyerCapitals.add(buyerCapital1);
                        salerCapitals.add(salerCapital1);
                    }
                    //退回到支付宝或微信
                    if(buyerCapital.getPaytype()==Quantity.STATE_0||buyerCapital.getPaytype()==Quantity.STATE_1){
                        String uuid = order.getUuid();
                        if(uuid!=null){
                            Refund refund = new Refund();
                            refund.setOutTradeNo(uuid);
                            if(order.getPaytype()==Quantity.STATE_0){
                                refund.setChannel("alipay");
                            }else {
                                refund.setChannel("wxpay");
                            }
                            refund.setRefundAmount((backPay.multiply(new BigDecimal(100))).longValue());
                            refund.setRefundReason("订单退款");
                            List<Orders> ordersList = ordersService.getOrdersByUUID(uuid);
                            BigDecimal totalAmout = new BigDecimal(0);
                            for(Orders od : ordersList){
                                totalAmout = totalAmout.add(od.getActualpayment());
                            }
                            refund.setTotalAmount((totalAmout.multiply(new BigDecimal(100))).longValue());
                            boolean result = false;
                            try {
                                if("alipay".equals(refund.getChannel())){
                                    result = alipayService.refund(refund);
                                }else if("wxpay".equals(refund.getChannel())){
                                    result = wxPayService.refund(refund);
                                }
                            }catch (Exception e){

                            }
                            System.out.println("退货通道："+refund.getChannel()+"退货结果："+result);
                            if(result){
                                if(order.getPaytype()==Quantity.STATE_0){
                                    buyerCapital1 = createBuyerBackPay(order, backPay, tranTime, Quantity.STATE_0);
                                }else {
                                    buyerCapital1 = createBuyerBackPay(order, backPay, tranTime, Quantity.STATE_1);
                                }
                                salerCapital1 = createSalerBackPay(order, backPay, tranTime);
                                buyerCapitals.add(buyerCapital1);
                                salerCapitals.add(salerCapital1);
                            }

                        }
                    }
                    //异议扣违约金,记录资金明细
                    if (orderProductBack.getAdminstate() == Quantity.STATE_2) {
                        operator.setSellerbanlance(operator.getSellerbanlance().add(penaltyPay.multiply(forwardsalesmargin)));
                        //违约金
                        buyerCapital2 = createBuyerPenaltyPay(order, penaltyPay, tranTime, Quantity.STATE_6, orderPay, Quantity.BUYER_BACK_REASON);
                        salerCapital2 = createSalerPenaltyPay(order, penaltyPay, tranTime, Quantity.STATE_6, orderPay, Quantity.BUYER_BACK_REASON);
                        buyerCapitals.add(buyerCapital2);
                        salerCapitals.add(salerCapital2);
                    }
                }

            }
            //远期余款
            if (orderProduct.getProtype() == Quantity.STATE_2) {
                //计算扣除违约金
                BigDecimal orderPay = orderProduct.getActualpayment().subtract(orderProduct.getFreight());
                BigDecimal partPay = orderProduct.getPartpay().subtract(orderProduct.getFreight());
                BigDecimal yuPay = orderProduct.getYupay();


                //违约金
                penaltyPay = orderPay.multiply(getLiquidated);

                //定金支付明细
                BuyerCapital depositBuyerCapital = ordersService.getBuyerCapitalByNoType(order.getOrderno(), Quantity.STATE_7);

                if (depositBuyerCapital != null) {

                    //异议扣违约金
                    //退回的金额,异议扣违约金
                    if (orderProductBack.getAdminstate() == Quantity.STATE_2) {
                        backPay = orderPay.subtract(penaltyPay);
                        yuPay = yuPay.subtract(penaltyPay);
                    } else {
                        backPay = orderPay;
                    }
                    //退回到余额
                    if (depositBuyerCapital.getPaytype() == Quantity.STATE_3) {
                        buyer.setBalance(buyer.getBalance().add(backPay));
                        buyerCapital1 = createBuyerBackPay(order, backPay, tranTime, Quantity.STATE_3);
                        salerCapital1 = createSalerBackPay(order, backPay, tranTime);
                        buyerCapitals.add(buyerCapital1);
                        salerCapitals.add(salerCapital1);
                    }
                    //退回到授信
                    if (depositBuyerCapital.getPaytype() == Quantity.STATE_4) {
                        buyer.setAvailablelimit(buyer.getAvailablelimit().add(backPay));
                        buyer.setUsedlimit(buyer.getUsedlimit().subtract(backPay));
                        buyerCapital1 = createBuyerBackPay(order, backPay, tranTime, Quantity.STATE_4);
                        salerCapital1 = createSalerBackPay(order, backPay, tranTime);
                        buyerCapitals.add(buyerCapital1);
                        salerCapitals.add(salerCapital1);
                    }
                    //退回到支付宝或微信
                    if(depositBuyerCapital.getPaytype()==Quantity.STATE_0||depositBuyerCapital.getPaytype()==Quantity.STATE_1){
                        String uuid = order.getUuid();
                        String yuuuid = order.getYuuuid();
                        if(uuid!=null&&yuuuid!=null){
                            //定金
                            Refund refund1 = new Refund();
                            //余款
                            Refund refund2 = new Refund();
                            refund1.setOutTradeNo(uuid);
                            refund2.setOutTradeNo(yuuuid);
                            if(order.getPaytype()==Quantity.STATE_0){
                                refund1.setChannel("alipay");
                                refund2.setChannel("alipay");
                            }else {
                                refund1.setChannel("wxpay");
                                refund2.setChannel("wxpay");
                            }
                            refund1.setRefundAmount((partPay.multiply(new BigDecimal(100))).longValue());
                            refund2.setRefundAmount((yuPay.multiply(new BigDecimal(100))).longValue());

                            refund1.setRefundReason("订单定金退款");
                            refund2.setRefundReason("订单余款退款");
                            List<Orders> ordersList = ordersService.getOrdersByUUID(uuid);
                            BigDecimal totalPartPay = new BigDecimal(0);
                            BigDecimal totalYuPay = new BigDecimal(0);
                            for(Orders od : ordersList){
                                totalPartPay = totalPartPay.add(od.getDeposit());
                                totalYuPay = totalYuPay.add(od.getBalance());
                            }
                            refund1.setTotalAmount((totalPartPay.multiply(new BigDecimal(100))).longValue());
                            refund2.setTotalAmount((totalYuPay.multiply(new BigDecimal(100))).longValue());

                            boolean result1 = false;
                            boolean result2 = false;
                            try {
                                if("alipay".equals(refund1.getChannel())){
                                    result1 = alipayService.refund(refund1);
                                    result2 = alipayService.refund(refund2);
                                }else if("wxpay".equals(refund1.getChannel())){
                                    result1 = wxPayService.refund(refund1);
                                    result2 = wxPayService.refund(refund2);
                                }
                            }catch (Exception e){

                            }
                            System.out.println("退货通道："+refund1.getChannel()+"退货结果："+result1+"-"+result2);
                            if(result1&&result2){
                                if(order.getPaytype()==Quantity.STATE_0){
                                    buyerCapital1 = createBuyerBackPay(order, backPay, tranTime, Quantity.STATE_0);
                                }else {
                                    buyerCapital1 = createBuyerBackPay(order, backPay, tranTime, Quantity.STATE_1);
                                }
                                salerCapital1 = createSalerBackPay(order, backPay, tranTime);
                                buyerCapitals.add(buyerCapital1);
                                salerCapitals.add(salerCapital1);
                            }

                        }
                    }
                    //异议扣违约金,记录资金明细
                    if (orderProductBack.getAdminstate() == Quantity.STATE_2) {
                        operator.setSellerbanlance(operator.getSellerbanlance().add(penaltyPay.multiply(forwardsalesmargin)));
                        //违约金
                        buyerCapital2 = createBuyerPenaltyPay(order, penaltyPay, tranTime, Quantity.STATE_6, orderPay, Quantity.BUYER_BACK_REASON);
                        salerCapital2 = createSalerPenaltyPay(order, penaltyPay, tranTime, Quantity.STATE_6, orderPay, Quantity.BUYER_BACK_REASON);
                        buyerCapitals.add(buyerCapital2);
                        salerCapitals.add(salerCapital2);
                    }
                }
            }
            //保存用户余额和授信
            ordersService.saveMember(buyer,oldBuyer);

            ordersService.saveMember(operator,oldOperator);


            if(buyerCapitals.size()>0){
                ordersService.insertBuyerCapital(buyerCapitals);
                //保存操作日志
                OperateLog operateLog = new OperateLog();
                operateLog.setContent("退款成功");
                operateLog.setOpid(operator.getId());
                operateLog.setOpname(operator.getUsername());
                operateLog.setOptime(new Date());
                operateLog.setOptype(Quantity.STATE_1);
                operateLog.setOrderid(orderProductBack.getOrderid());
                operateLog.setOrderno(orderProductBack.getOrderno());
                operateLog.setOrderpdid(orderProduct.getId());
                ordersService.saveOperatelog(operateLog);
            }
            if(salerCapitals.size()>0){
                ordersService.insertSallerCapital(salerCapitals);
            }
        }

    }





    @Scheduled(cron = "0 0/2 * * * ?")
    public void updateNotPayLimitOrdersForFinish(){
        System.out.println("更新限时购超时付款订单状态为已关闭");

        TransactionSetting transactionSetting = transactionSettingService.getTransactionSetting();
        BigDecimal timedoutofpayment = transactionSetting.getTimedoutofpayment();

        List<Orders> ordersList = ordersService.getNotPayMoneyLimitOrders(2);

        for(Orders orders : ordersList){
            //找到该订单中的商品
            List<OrderProduct> orderProductList = orderProductServices.getByOrderid(orders.getId());

            for(OrderProduct orderProduct : orderProductList){
                LimitTimeProd limitTimeProd = limitTimeProdService.getById(orderProduct.getLimitid());
                LimitTimeStore limitTimeStore = shopCarService.getLimitTimeStore(orderProduct.getLimitid(),orderProduct.getPdid(),orderProduct.getPdno());
                limitTimeProdService.updateSalestotalnumInDB(orderProduct.getLimitid(),orderProduct.getNum().multiply(new BigDecimal(-1)));
                limitTimeStoreService.updateLimitStoreNum(limitTimeStore.getId(),orderProduct.getNum(),orderProduct.getNum().multiply(new BigDecimal(-1)));
            }

            //更新订单为关闭状态
            Orders updateOrders = new Orders();
            updateOrders.setId(orders.getId());
            updateOrders.setOrderstatus(Quantity.STATE_7);
            updateOrders.setReason("订单超时取消");
            ordersService.updateSingleOrder(updateOrders);



            wmsService.cancelOrders(orders, WMSService.CANCEL_ORDER_TYPE);


        }
        System.out.println("更新限时购超时付款订单完成");
    }


    /**
     * 创建买家退款资金明细
     *
     * @param order
     * @param backPay
     * @param tranTime
     * @param type     3=退到余额4=退到授信
     * @return
     */
    private BuyerCapital createBuyerBackPay(Orders order, BigDecimal backPay, Date tranTime, Short type) {

        BuyerCapital buyerCapital = new BuyerCapital();
        buyerCapital.setOrderno(order.getOrderno());
        buyerCapital.setTradeno(order.getTransactionnumber());
        buyerCapital.setCapitaltype(Quantity.STATE_2);
        buyerCapital.setCapital(backPay);
        buyerCapital.setPaytype(type);
        buyerCapital.setMemberid(order.getMemberid());
        buyerCapital.setTradetime(tranTime);
        buyerCapital.setRemark("退款金额");

        return buyerCapital;
    }

    /**
     * 创建买家违约金资金明细
     *
     * @param order
     * @param penaltyPay
     * @param tranTime
     * @param type       6=买家违约10=卖家违约
     * @return
     */
    private BuyerCapital createBuyerPenaltyPay(Orders order, BigDecimal penaltyPay, Date tranTime, Short type, BigDecimal orderPay, String reason) {

        BuyerCapital buyerCapital = new BuyerCapital();
        buyerCapital.setOrderno(order.getOrderno());
        buyerCapital.setTradeno(order.getTransactionnumber());
        buyerCapital.setCapitaltype(type);
        buyerCapital.setCapital(penaltyPay);
        buyerCapital.setMemberid(order.getMemberid());
        buyerCapital.setTradetime(tranTime);
        buyerCapital.setAllpay(orderPay);
        buyerCapital.setRemark(reason);
        buyerCapital.setSellerid(order.getSaleid());
        if (type == Quantity.STATE_6) {
            buyerCapital.setRemark("买家违约金额");
        } else {
            buyerCapital.setRemark("卖家违约金额");
        }
        return buyerCapital;
    }

    /**
     * 创建卖家退款资金明细
     *
     * @param order
     * @param backPay
     * @param tranTime
     * @return
     */
    private SalerCapital createSalerBackPay(Orders order, BigDecimal backPay, Date tranTime) throws CashException {
        SalerCapital salerCapital = new SalerCapital();
        salerCapital.setMemberid(order.getSaleid());
        salerCapital.setTradeno(order.getTransactionnumber());
        salerCapital.setOrderno(order.getOrderno());
        salerCapital.setTradetime(tranTime);
        salerCapital.setRefundamount(backPay);
        salerCapital.setCapitaltype(Quantity.STATE_3);
        salerCapital.setRemark("退款金额");

        //卖家从冻结金额中减去退回金额
//        Member seller = memberService.getMemberById(order.getSaleid());
//        Member oldSeller = new Member();
//        BeanUtils.copyProperties(seller,oldSeller);
//        seller.setSellerfreezebanlance(seller.getSellerfreezebanlance().subtract(backPay));
//        ordersService.saveMember(seller,oldSeller);

        return salerCapital;
    }

    /**
     * 创建卖家违约金资金明细
     *
     * @param order
     * @param penaltyPay
     * @param tranTime
     * @param type       6=买家违约7=卖家违约
     * @return
     */
    private SalerCapital createSalerPenaltyPay(Orders order, BigDecimal penaltyPay, Date tranTime, Short type, BigDecimal orderPay, String reason) {
        SalerCapital salerCapital = new SalerCapital();
        salerCapital.setMemberid(order.getSaleid());
        salerCapital.setTradeno(order.getTransactionnumber());
        salerCapital.setOrderno(order.getOrderno());
        salerCapital.setTradetime(tranTime);
        salerCapital.setPenalty(penaltyPay);
        salerCapital.setCapitaltype(type);
        salerCapital.setAllpay(orderPay);
        salerCapital.setRemark(reason);
        salerCapital.setBuyerid(order.getMemberid());
        if (type == Quantity.STATE_6) {
            salerCapital.setRemark("买家违约金额");
        } else {
            salerCapital.setRemark("卖家违约金额");
        }
        return salerCapital;
    }



    private  BigDecimal getServerRate(Long cateid){
        Categories categories = categoriesService.getById(cateid);

        if (categories == null){
            return  Quantity.BIG_DECIMAL_0;
        }

        if(categories.getServicesrate() != null && categories.getServicesrate().compareTo(Quantity.BIG_DECIMAL_0) >=0){
            return  categories.getServicesrate();
        }else{
            return  getServerRate(categories.getParentid());
        }
    }

    private  BigDecimal getBrokerRate(Long cateid){
        Categories categories = categoriesService.getById(cateid);

        if (categories == null){
            return  Quantity.BIG_DECIMAL_0;
        }

        if(categories.getBrokeragerate() != null && categories.getBrokeragerate().compareTo(Quantity.BIG_DECIMAL_0) >=0){
            return  categories.getServicesrate();
        }else{
            return  getBrokerRate(categories.getParentid());
        }

    }


    /**
     * 冻结金额到货款自动确认
     */

/*
    @Scheduled(cron = "0 0/5 * * * ?")
    public void autoFreezeToGoodsBanlance() throws CashException {
        Date date=new Date();

        TransactionSetting transactionSetting = transactionSettingService.getTransactionSetting();


//        BigDecimal numday = transactionSetting.getFreezetotogoodspay();
//        String intervalday = "'"+numday+" day'";

        //测试
        BigDecimal numday = new BigDecimal(2);
        String intervalday = "'"+numday+" min'";


        List<SellerSettleModel> list = ordersService.getSellerSettleList(intervalday,date);
        List<Orders> orders = ordersService.getSellerSettleOrdersList(intervalday,date);

        for(Orders order :orders){
            order.setPaymentmethod(Quantity.STATE_1);
            ordersService.updateSingleOrder(order);
        }

        System.out.println("更新的list:"+list.size());
        //更新冻结金额，货款金额和可开票金额
        for(SellerSettleModel sellerSettleModel:list){
            Long sellerId = sellerSettleModel.getSaleid();
            Member seller = memberService.getMemberById(sellerId);
            BigDecimal differpay = sellerSettleModel.getFrozepay();
//            System.out.println("更新的冻结金额:"+differpay);
//            System.out.println("卖家冻结金额:"+seller.getSellerfreezebanlance());
//            System.out.println("卖家货款金额:"+seller.getGoodsbanlance());
//
//            seller.setSellerfreezebanlance(seller.getSellerfreezebanlance().subtract(differpay));
//            seller.setGoodsbanlance(seller.getGoodsbanlance().add(differpay));
//
//
//            System.out.println("更新后卖家冻结金额:"+seller.getSellerfreezebanlance());
//            System.out.println("更新后卖家货款金额:"+seller.getGoodsbanlance());
//            seller.setBillmoney(seller.getBillmoney().add(differpay));
            //ordersService.saveMember(seller);   //之前是这样写的，会有数据脏读问题
            //新改
            memberService.updateSellerMemberBalanceInDb(seller.getId(),new BigDecimal(0),differpay.multiply(new BigDecimal(-1)),differpay,differpay);
            Member member = memberService.getMemberById(seller.getId());
            if(member.getSellerfreezebanlance().compareTo(Quantity.BIG_DECIMAL_0) <0 ){
                throw new CashException(seller.getUsername()+"冻结金额不足");
            }

        }

        List<SalerCapital> salerCapitals = ordersService.getSalerCapitalList(intervalday,date);
        //更新资金明细设为已结算
        for(SalerCapital salerCapital : salerCapitals){
            SalerCapital updateSalerCapital = new SalerCapital();
            updateSalerCapital.setId(salerCapital.getId());
            updateSalerCapital.setSettlement(Quantity.STATE_1);
            ordersService.updateSalerCapital(updateSalerCapital);
        }

    }

*/





}

