package project.jinshang.mod_product;

import com.alipay.api.AlipayApiException;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import mizuki.project.core.restserver.config.BasicRet;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.jinshang.common.bean.MemberLogOperator;
import project.jinshang.common.bean.PageRet;
import project.jinshang.common.constant.AdminAuthorityConst;
import project.jinshang.common.constant.AgentDeliveryAddressConst;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.common.constant.Quantity;
import project.jinshang.common.exception.CashException;
import project.jinshang.common.utils.*;
import project.jinshang.mod_activity.bean.LimitTimeProd;
import project.jinshang.mod_activity.bean.LimitTimeStore;
import project.jinshang.mod_admin.mod_commondata.service.CommonDataValueService;
import project.jinshang.mod_admin.mod_transet.bean.TransactionSetting;
import project.jinshang.mod_admin.mod_transet.service.TransactionSettingService;
import project.jinshang.mod_cash.bean.BuyerCapital;
import project.jinshang.mod_cash.bean.SalerCapital;
import project.jinshang.mod_cash.service.BuyerCapitalService;
import project.jinshang.mod_company.bean.BuyerCompanyInfo;
import project.jinshang.mod_company.service.BuyerCompanyService;
import project.jinshang.mod_member.bean.Admin;
import project.jinshang.mod_member.bean.Member;
import project.jinshang.mod_member.service.MemberRateSettingService;
import project.jinshang.mod_member.service.MemberService;
import project.jinshang.mod_pay.bean.Refund;
import project.jinshang.mod_pay.mod_alipay.AlipayService;
import project.jinshang.mod_pay.mod_bankpay.AbcService;
import project.jinshang.mod_pay.mod_wxpay.MyWxPayService;
import project.jinshang.mod_product.bean.*;
import project.jinshang.mod_product.bean.dto.OrdersRet;
import project.jinshang.mod_product.service.*;
import project.jinshang.mod_shippingaddress.bean.ShippingAddress;
import project.jinshang.mod_shippingaddress.service.ShippingAddressService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 2017/11/9.
 */
@RestController
@RequestMapping("/rest/admin/orders")
@SessionAttributes({AppConstant.ADMIN_SESSION_NAME})
@Api(tags = "后台订单模块",description = "后台订单模块")
@Transactional(rollbackFor =Exception.class)
public class AdminOrdersAction {

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private ShopCarService shopCarService;

    @Autowired
    private MemberRateSettingService memberRateSettingService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private ShippingAddressService shippingAddressService;

    @Autowired
    private TransactionSettingService transactionSettingService;

    @Autowired
    private MemberOperateLogService memberOperateLogService;

    @Autowired
    private MyWxPayService wxPayService;
    @Autowired
    private AlipayService alipayService;

    @Autowired
    private CommonDataValueService commonDataValueService;

    @Autowired
    private OrderProductBackService orderProductBackService;

    @Autowired
    private AbcService abcService;

    @Autowired
    private BuyerCapitalService buyerCapitalService;

    @Autowired
    private BillingRecordService billingRecordService;
    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private BuyerCompanyService buyerCompanyService;


    @Autowired
    private  OrderProductLogService orderProductLogService;


    @Autowired
    private OrderProductServices orderProductServices;

    @Autowired
    private  OrderStoreStateLogService orderStoreStateLogService;



    //远期全款打折率
    private static final BigDecimal allPayRate = new BigDecimal(0.99);

    MemberLogOperator memberLogOperator = new MemberLogOperator();


    private  class  OrderCarRet extends  BasicRet{
        private  class  Data{

            List<BillRecordComplex> listComplex;
            private List<BillType> billTypes;

            private SellerBillRecord sellerBillRecord;
            private  List<Orders> ordersList;

            private OrderProductBack orderProductBack;

            private List<ShippingAddress> shippingAddresses;

            private BigDecimal bigDecimal;

            private Orders orders;

            private List<OrderProduct> orderProducts;

            private  OrderProduct orderProduct;

            private BillingRecord billingRecord;

            private BillRecordComplex billRecordComplex;

            private Map<String,BigDecimal> map;

            List<OperateLog> operateLogs;

            private  String expressurl;

            public String getExpressurl() {
                return expressurl;
            }

            public void setExpressurl(String expressurl) {
                this.expressurl = expressurl;
            }

            public List<BillRecordComplex> getListComplex() {
                return listComplex;
            }

            public void setListComplex(List<BillRecordComplex> listComplex) {
                this.listComplex = listComplex;
            }

            public List<OperateLog> getOperateLogs() {
                return operateLogs;
            }

            public void setOperateLogs(List<OperateLog> operateLogs) {
                this.operateLogs = operateLogs;
            }

            public Map<String, BigDecimal> getMap() {
                return map;
            }

            public void setMap(Map<String, BigDecimal> map) {
                this.map = map;
            }

            public BillRecordComplex getBillRecordComplex() {
                return billRecordComplex;
            }

            public void setBillRecordComplex(BillRecordComplex billRecordComplex) {
                this.billRecordComplex = billRecordComplex;
            }

            public BillingRecord getBillingRecord() {
                return billingRecord;
            }

            public void setBillingRecord(BillingRecord billingRecord) {
                this.billingRecord = billingRecord;
            }

            public OrderProduct getOrderProduct() {
                return orderProduct;
            }

            public void setOrderProduct(OrderProduct orderProduct) {
                this.orderProduct = orderProduct;
            }

            public List<OrderProduct> getOrderProducts() {
                return orderProducts;
            }

            public void setOrderProducts(List<OrderProduct> orderProducts) {
                this.orderProducts = orderProducts;
            }

            public Orders getOrders() {
                return orders;
            }

            public void setOrders(Orders orders) {
                this.orders = orders;
            }

            public BigDecimal getBigDecimal() {
                return bigDecimal;
            }

            public void setBigDecimal(BigDecimal bigDecimal) {
                this.bigDecimal = bigDecimal;
            }

            public List<ShippingAddress> getShippingAddresses() {
                return shippingAddresses;
            }

            public void setShippingAddresses(List<ShippingAddress> shippingAddresses) {
                this.shippingAddresses = shippingAddresses;
            }

            private int orderNum;

            private boolean selfsupport;

            public boolean isSelfsupport() {
                return selfsupport;
            }

            public void setSelfsupport(boolean selfsupport) {
                this.selfsupport = selfsupport;
            }

            public int getOrderNum() {
                return orderNum;
            }

            public void setOrderNum(int orderNum) {
                this.orderNum = orderNum;
            }

            public List<Orders> getOrdersList() {
                return ordersList;
            }

            public void setOrdersList(List<Orders> ordersList) {
                this.ordersList = ordersList;
            }

            public OrderProductBack getOrderProductBack() {
                return orderProductBack;
            }

            public void setOrderProductBack(OrderProductBack orderProductBack) {
                this.orderProductBack = orderProductBack;
            }

            public SellerBillRecord getSellerBillRecord() {
                return sellerBillRecord;
            }

            public void setSellerBillRecord(SellerBillRecord sellerBillRecord) {
                this.sellerBillRecord = sellerBillRecord;
            }

            public List<BillType> getBillTypes() {
                return billTypes;
            }

            public void setBillTypes(List<BillType> billTypes) {
                this.billTypes = billTypes;
            }
        }
        private  Data data = new Data();

        public Data getData() {
            return data;
        }
        public void setData(Data data) {
            this.data = data;
        }
    }


    @RequestMapping(value = "/getMemberOperateLogList",method = RequestMethod.POST)
    @ApiOperation(value = "后台获取操作日志列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ipaddress",value = "买家名称",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "memberid",value = "卖家名称",required = false,paramType = "query",dataType = "long"),
            @ApiImplicitParam(name = "membername",value = "线上线下订单标识0=线上1=线下",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "content",value = "支付方式0=支付宝1=微信2=银行卡3=余额4=授信",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "startTime",value = "开始时间",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "endTime",value = "结束时间",required = false,paramType = "query",dataType = "string"),
    })
    public PageRet getMemberOperateLogList(Model model,int pageNo,int pageSize,MemberQueryParam param){
        PageRet pageRet = new PageRet();
        PageInfo pageInfo = ordersService.getMemberOperateLogList(pageNo,pageSize,param);
        pageRet.data.setPageInfo(pageInfo);
        pageRet.setResult(BasicRet.SUCCESS);
        pageRet.setMessage("返回成功");
        return pageRet;
    }

    @RequestMapping(value = "/getAllMemberOrderList",method = RequestMethod.POST)
    @ApiOperation(value = "后台获取订单列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "memberName",value = "买家名称",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "sellerName",value = "卖家名称",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "isonline",value = "线上线下订单标识0=线上1=线下",required = false,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "payType",value = "支付方式0=支付宝1=微信2=银行卡3=余额4=授信",required = false,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "orderType",value = "订单类型0=立即发货1=远期全款2=远期定金3=远期余款",required = false,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "orderNo",value = "订单号",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "code",value = "合同号",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "tranNo",value = "交易号",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "startTime",value = "开始时间",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "endTime",value = "结束时间",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "orderState",value = "订单状态0=待付款1=待发货3=待收货4=待验货5=已完成7=已关闭8=备货中9=备货完成11=卖家违约订单",required = false,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "deliverytype",value = "发货模式 0-卖家直发，1-平台代发",required = false,paramType = "query",dataType = "int"),
    })
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.ORDERMANAGEMENT + "')")
    public PageRet getAllMemberOrderList(Model model,OrderQueryParam param){
        PageRet pageRet = new PageRet();
        PageInfo pageInfo = ordersService.getAllMemberOrdersList(param);
        pageRet.data.setPageInfo(pageInfo);
        pageRet.setResult(BasicRet.SUCCESS);
        pageRet.setMessage("返回成功");
        return pageRet;
    }


    @RequestMapping(value = "/sendOutGoods", method = RequestMethod.POST)
    @ApiOperation(value = "发货")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderno", value = "订单编号", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "logisticscompany", value = "运输方式", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "couriernumber", value = "运输单号", required = true, paramType = "query", dataType = "String"),
    })
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.ORDERMANAGEMENT + "')")
    public BasicRet sendOutGoods(Model model, String orderno, String logisticscompany, String couriernumber, HttpServletRequest request) {

        Admin admin = (Admin) model.asMap().get(AppConstant.ADMIN_SESSION_NAME);

        BasicRet basicRet = new BasicRet();
        Orders orders = ordersService.getOrdersByOrderNo(orderno);
        if (orders != null && orders.getDeliverytype() == Quantity.STATE_1 ) {
            if (orders.getOrderstatus() == Quantity.STATE_3) {

                orders.setLogisticscompany(logisticscompany);
                orders.setCouriernumber(couriernumber);
                orders.setDeliveryno(GenerateNo.getInvoiceNo());

                orders.setSellerdeliverytime(new Date());
                ordersService.updateSingleOrder(orders);

                //保存操作日志
                OperateLog operateLog = new OperateLog();
                operateLog.setContent("平台将代发商品发货");
                operateLog.setOpid(admin.getId());
                operateLog.setOpname(admin.getUsername());
                operateLog.setOptime(new Date());
                operateLog.setOptype(Quantity.STATE_0);
                operateLog.setOrderid(orders.getId());
                operateLog.setOrderno(orders.getOrderno());
                ordersService.saveOperatelog(operateLog);

                basicRet.setMessage("发货成功");
                basicRet.setResult(BasicRet.SUCCESS);
                //用户日志
                memberLogOperator.saveMemberLog(null, admin, "代发货订单由平台发货完成,订单编号为：" + orderno, "",request, memberOperateLogService);
                return basicRet;
            }else if(orders.getOrderstatus() == Quantity.STATE_7) {
                basicRet.setMessage("买家已取消订单，发货失败");
                basicRet.setResult(BasicRet.ERR);
                return basicRet;
            } else {
                basicRet.setMessage("卖家还未将此订单发货");
                basicRet.setResult(BasicRet.ERR);
                return basicRet;
            }
        } else {
            basicRet.setMessage("没有此订单");
            basicRet.setResult(BasicRet.ERR);
            return basicRet;
        }
    }





    @RequestMapping(value = "/getOrderTotalNum",method = RequestMethod.POST)
    @ApiOperation(value = "后台获取订单总额货款，总销售额，总订货量，总发货量,总佣金数")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "memberName",value = "买家名称",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "sellerName",value = "卖家名称",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "isonline",value = "线上线下订单标识0=线上1=线下",required = false,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "payType",value = "支付方式0=支付宝1=微信2=银行卡3=余额4=授信",required = false,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "orderType",value = "订单类型0=立即发货1=远期全款2=远期定金3=远期余款",required = false,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "orderNo",value = "订单号",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "code",value = "合同号",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "tranNo",value = "交易号",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "startTime",value = "开始时间",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "endTime",value = "结束时间",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "orderState",value = "订单状态0=待付款1=待发货3=待收货4=待验货5=已完成7=已关闭8=备货中9=备货完成11=卖家违约订单",required = false,paramType = "query",dataType = "int"),
    })
    public OrderCarRet getOrderTotalNum(OrderQueryParam param){
        OrderCarRet orderCarRet = new OrderCarRet();
        Map<String,BigDecimal> map = new HashMap<String,BigDecimal>();

        OrderQueryParam q1= new OrderQueryParam();
        BeanUtils.copyProperties(param,q1);
        BigDecimal ordersSum = ordersService.getOrdersSum(q1);

        OrderQueryParam q2= new OrderQueryParam();
        BeanUtils.copyProperties(param,q2);
        BigDecimal orderSellSum = ordersService.getOrderSellSum(q2);

        OrderQueryParam q3= new OrderQueryParam();
        BeanUtils.copyProperties(param,q3);
        BigDecimal ordersTotalNum = ordersService.getOrdersTotalNum(q3);

        OrderQueryParam q4= new OrderQueryParam();
        BeanUtils.copyProperties(param,q4);
        BigDecimal ordersTotalDeliveryNum = ordersService.getOrdersTotalDeliveryNum(q4);

        BigDecimal orderSumBroker = ordersService.getOrdersSumBroker(param);

        map.put("ordersSum",ordersSum);
        map.put("orderSellSum",orderSellSum);
        map.put("ordersTotalNum",ordersTotalNum);
        map.put("ordersTotalDeliveryNum",ordersTotalDeliveryNum);
        map.put("orderSumBroker",orderSumBroker);

        orderCarRet.data.map = map;
        orderCarRet.setMessage("返回成功");
        orderCarRet.setResult(BasicRet.SUCCESS);
        return  orderCarRet;
    }



    @RequestMapping(value = "/getOrdersByOrderNoArr", method = RequestMethod.POST)
    @ApiOperation(value = "根据订单编号获取订单[一组订单编号]")
    public OrdersRet getOrdersByOrderNoArr(Long[] orderids){
        OrdersRet ordersRet = new OrdersRet();

        List<Orders> list = ordersService.getOrdersByIds(orderids);
        for(Orders orders : list){
            if(orders.getDeliverytype() == 1){  //如果是代理发货，设置为代理发货地址
                orders.setProvince(AgentDeliveryAddressConst.province);
                orders.setCity(AgentDeliveryAddressConst.city);
                orders.setArea(AgentDeliveryAddressConst.province);
                orders.setReceivingaddress(AgentDeliveryAddressConst.address);
                orders.setShipto(AgentDeliveryAddressConst.linkman);
                orders.setPhone(AgentDeliveryAddressConst.tel);
            }

            List<OrderProduct> orderProductList = ordersService.getOrderProductByOrderId(orders.getId());

            List<Long> orderproductids = new ArrayList<>();
            orderProductList.stream().forEach(orderProduct ->orderproductids.add(orderProduct.getId()));
            List<OrderProductLog> orderProductLogList = orderProductLogService.getProductinfoByOrderproductids(orderproductids);


            for(OrderProduct orderProduct : orderProductList){
                List packageList = JinshangUtils.toCovertPacking(StringUtils.nvl(orderProduct.getPddesc()));
                orderProduct.setPackageList(packageList);

                for(OrderProductLog opl : orderProductLogList){
                    if(opl.getOrderproductid().equals(orderProduct.getId())){
                        orderProduct.getExtend().put("productinfo",opl.getProductinfojson());
                    }
                }
            }
            orders.setOrderProducts(orderProductList);


            BuyerCompanyInfo buyerCompanyInfo = buyerCompanyService.getBuyerCompanyInfoByMemberId(orders.getMemberid());
            Member member = memberService.getMemberById(orders.getMemberid());
            if(buyerCompanyInfo != null){
                orders.setBuyercompanyname(buyerCompanyInfo.getCompanyname());
            }

            orders.setBuyerRealname(member.getRealname());

        }

        ordersRet.getData().setOrdersList(list);
        ordersRet.setResult(BasicRet.SUCCESS);
        ordersRet.setMessage("查询成功");

        return ordersRet;
    }




    @RequestMapping(value = "/getOrdersByOrderNo",method = RequestMethod.POST)
    @ApiOperation(value = "根据订单编号获取订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderno",value = "订单编号",required = true,paramType = "query",dataType = "string"),
    })
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.ORDERMANAGEMENT + "')")
    public OrderCarRet getOrdersByOrderNo(String orderno) throws IOException {
        OrderCarRet orderCarRet = new OrderCarRet();
        orderCarRet.setMessage("返回成功");
        orderCarRet.setResult(BasicRet.SUCCESS);
        Orders orders = ordersService.getOrdersByOrderNo(orderno);
        orderCarRet.data.orders = orders;
        String url = "";
        if(StringUtils.hasText(orders.getLogisticscompany())&&StringUtils.hasText(orders.getCouriernumber())){
            List<String> list = commonDataValueService.getcommonDataValue("物流公司");
            for(String vl:list){
                String[] vlStr = vl.split("-");
                if(orders.getLogisticscompany().equals(vlStr[0])){
                    //物流查询
                    url =  ExpressUtils.searchkuaiDiInfo(vlStr[1],orders.getCouriernumber());
                    break;
                }
            }
        }



        orderCarRet.data.expressurl = url;
        return orderCarRet;
    }


    @RequestMapping(value = "/getOrdersByOrderNoWithBuyerinfo",method = RequestMethod.POST)
    @ApiOperation(value = "根据订单编号获取订单-包含买家和买家公司信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderno",value = "订单编号",required = true,paramType = "query",dataType = "string"),
    })
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.ORDERMANAGEMENT + "')")
    public OrdersBuyerRet getOrdersByOrderNoWithBuyerinfo(String orderno){
        OrdersBuyerRet orderCarRet = new OrdersBuyerRet();

        Orders orders = ordersService.getOrdersByOrderNo(orderno);
        orderCarRet.data.orders = orders;

        if(orders != null){
            Member member = memberService.getMemberById(orders.getMemberid());
            BuyerCompanyInfo buyerCompanyInfo = buyerCompanyService.getBuyerCompanyInfoByMemberId(orders.getMemberid());

            orderCarRet.data.buyMember =member;
            orderCarRet.data.buyerCompanyInfo = buyerCompanyInfo;
        }

        orderCarRet.setMessage("返回成功");
        orderCarRet.setResult(BasicRet.SUCCESS);
        return orderCarRet;
    }


    private  class  OrdersBuyerRet extends  BasicRet{
        private class OrderData{
            private  Orders orders;
            private  Member buyMember;
            private  BuyerCompanyInfo buyerCompanyInfo;

            public Orders getOrders() {
                return orders;
            }

            public void setOrders(Orders orders) {
                this.orders = orders;
            }

            public Member getBuyMember() {
                return buyMember;
            }

            public void setBuyMember(Member buyMember) {
                this.buyMember = buyMember;
            }

            public BuyerCompanyInfo getBuyerCompanyInfo() {
                return buyerCompanyInfo;
            }

            public void setBuyerCompanyInfo(BuyerCompanyInfo buyerCompanyInfo) {
                this.buyerCompanyInfo = buyerCompanyInfo;
            }
        }


        private  OrderData data = new OrderData();

        public OrderData getData() {
            return data;
        }

        public void setData(OrderData data) {
            this.data = data;
        }
    }



    @RequestMapping(value = "/getOrderProductByOrderNo",method = RequestMethod.POST)
    @ApiOperation(value = "根据订单编号获取商品信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderno",value = "订单编号",required = true,paramType = "query",dataType = "string"),
    })
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.ORDERMANAGEMENT + "')")
    public OrderCarRet getOrderProductByOrderNo(String orderno){
        OrderCarRet orderCarRet = new OrderCarRet();
        orderCarRet.setMessage("返回成功");
        orderCarRet.setResult(BasicRet.SUCCESS);
        orderCarRet.data.orderProducts = ordersService.getOrderProductByOrderNo(orderno);
        orderCarRet.data.orderProducts.forEach(orderProduct -> orderProduct.getExtend().put("productInfo",productInfoService.getById(orderProduct.getPdid())));
        return orderCarRet;
    }

    @RequestMapping(value = "/getOrderProductById",method = RequestMethod.POST)
    @ApiOperation(value = "根据商品订章id获取商品信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "订单商品id",required = true,paramType = "query",dataType = "long"),
    })
    public OrderCarRet getOrderProductById(long id){
        OrderCarRet orderCarRet = new OrderCarRet();
        orderCarRet.setMessage("返回成功");
        orderCarRet.setResult(BasicRet.SUCCESS);
        OrderProduct orderProduct = ordersService.getOrderProductById(id);
        ProductInfo info = ordersService.getProductInfoByPrimeKey(orderProduct.getPdid());
        orderCarRet.data.orderProduct =  orderProduct;
        orderCarRet.data.selfsupport = info.getSelfsupport();
        return orderCarRet;
    }

    @RequestMapping(value = "/getBillingRecordByOrderNo",method = RequestMethod.POST)
    @ApiOperation(value = "根据订单编号获取开票信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderid",value = "订单id",required = true,paramType = "query",dataType = "string"),
    })
    public OrderCarRet getBillingRecordByOrderNo(String orderid){
        OrderCarRet orderCarRet = new OrderCarRet();
        orderCarRet.setMessage("返回成功");
        orderCarRet.setResult(BasicRet.SUCCESS);
        orderCarRet.data.billingRecord = ordersService.getBillingRecordByOrderNo(orderid);
        return orderCarRet;
    }

    @RequestMapping(value = "/getOrderProductBack",method = RequestMethod.POST)
    @ApiOperation(value = "获取退货列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startTime",value = "开始时间",required = false,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "endTime",value = "结束时间",required = false,paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "code",value = "交易号",required = false,paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "pdName",value = "商品名称",required = false,paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "memberName",value = "买家",required = false,paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "sellerName",value = "卖家",required = false,paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "orderNo",value = "订单号",required = false,paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "backNo",value = "退货号",required = false,paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "state",value = "退货状态0=待卖家处理1=卖家同意待收货2=卖家同意直接退款3=卖家收到货同意退款4=卖家不同意5=买家同意验货6=买家申请异议7=平台同意退货不扣违约金8=平台同意退货扣违约金9=平台转入待验收10=退货成功",required = true,paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "pageNo",value = "开始页",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "pageSize",value = "页数",required = true,paramType = "query",dataType = "int"),
    })
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.RETURNMANAGEMENT + "')")
    public PageRet getOrderProductBack(BackQueryParam backQueryParam,int pageNo,int pageSize){
        PageRet pageRet = new PageRet();
        pageRet.setMessage("返回成功");
        pageRet.setResult(BasicRet.SUCCESS);

        PageInfo<OrderProductBackView> pageInfo =ordersService.getOrderProductBackList(pageNo,pageSize,backQueryParam);


        pageRet.data.setPageInfo(pageInfo);
        return pageRet;
    }

    @RequestMapping(value = "/listShippingAddress",method = RequestMethod.POST)
    @ApiOperation("列出卖家收货地址列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sellerid",value = "卖家id",required = true,paramType = "query"  ,dataType = "int"),
    })
    public PageRet listSellerShippingAddress(@RequestParam(required = true,defaultValue = "1") int pageNo,
                                             @RequestParam(required = true,defaultValue = "10")  int pageSize, Model model,Long sellerid){
        PageRet pageRet = new PageRet();
        PageInfo pageInfo = shippingAddressService.listAllShippingAddress(pageNo,pageSize,sellerid,Quantity.STATE_1);
        pageRet.data.setPageInfo(pageInfo);
        pageRet.setResult(BasicRet.SUCCESS);
        return  pageRet;
    }


    @RequestMapping(value = "/getOrderProductBackByOrderProductId",method = RequestMethod.POST)
    @ApiOperation(value = "根据商品id获取退货详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "订单商品id",required = true,paramType = "query",dataType = "long"),
    })
    public BasicRet getOrderProductBackByOrderProductId(Long id){
          OrderCarRet orderCarRet = new OrderCarRet();
          OrderProductBack orderProductBack = ordersService.getOrderProductBackByOrderProductID(id);

          if(orderProductBack != null){
//              orderProductBack.setActualpayment( ordersService.getAllpayByOrderNoAndPdidAndPdNo(orderProductBack.getOrderno(),orderProductBack.getPdid(),orderProductBack.getPdno()));

            List<OrderProduct> orderProductList  = ordersService.getOrderProdByOrderNoAndPdidAndPdNo(orderProductBack.getOrderno(),orderProductBack.getPdid(),orderProductBack.getPdno());
            BigDecimal actualpayment = new BigDecimal(0);
            for(OrderProduct op : orderProductList){
                actualpayment = op.getActualpayment().subtract(op.getFreight()).add(actualpayment);
            }
            orderProductBack.setActualpayment(actualpayment);
          }

          orderCarRet.data.orderProductBack = orderProductBack;
          String url = "";
          if(StringUtils.hasText(orderProductBack.getLogisticscompany())&&StringUtils.hasText(orderProductBack.getLogisticsno())){
              List<String> lists = commonDataValueService.getcommonDataValue("物流公司");
              for(String vl:lists){
                  String[] vlStr = vl.split("-");
                  if(orderProductBack.getLogisticscompany().equals(vlStr[0])){
                      //物流查询
                      url =  ExpressUtils.searchkuaiDiInfo(vlStr[1],orderProductBack.getLogisticsno());
                      break;
                  }
              }
          }
          orderCarRet.data.expressurl = url;
          orderCarRet.setMessage("返回成功");
          orderCarRet.setResult(BasicRet.SUCCESS);
          return orderCarRet;
    }



    @RequestMapping(value = "/updateOrderProductBack",method = RequestMethod.POST)
    @ApiOperation(value = "修改退货申请")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "退货申请id",required = true,paramType = "query",dataType = "long"),
            @ApiImplicitParam(name = "state",value = "退货状态0=待卖家处理1=卖家同意待收货2=卖家同意直接退款3=卖家收到货同意退款4=卖家不同意5=买家同意验货6=买家申请异议7=平台同意退货不扣违约金8=平台同意退货扣违约金9=平台转入待验收10=退货成功11=撤消",required = false,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "logisticsno",value = "退货单号",required = false,paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "logisticscompany",value = "退货物流公司",required = false,paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "backaddress",value = "退货地址",required = false,paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "contact",value = "退货联系人",required = false,paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "contactphone",value = "退货联系人电话",required = false,paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "sellernotagree",value = "卖家不同意原因",required = false,paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "adminreason",value = "平台处理说明",required = false,paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "adminstate",value = "平台处理意见0=正常1=不扣违约金2=扣违约金3=转入待验收",required = false,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "province",value = "省",required = false,paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "city",value = "市",required = false,paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "area",value = "区",required = false,paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "backtype",value = "退货类型0=仅退款1=退货退款2=部分退货",required = true,paramType = "query",dataType = "int"),
    })
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.RETURNMANAGEMENT + "')")
    public BasicRet updateOrderProductBack(Model model, ProductBackModel productBackModel, HttpServletRequest request) throws CashException {

        Admin admin = (Admin) model.asMap().get(AppConstant.ADMIN_SESSION_NAME);

        BasicRet basicRet = new BasicRet();

        Short state = productBackModel.getState();

        if(state != Quantity.STATE_7 && state != Quantity.STATE_8 && state != Quantity.STATE_9){
            basicRet.setMessage("操作状态不合法");
            basicRet.setResult(BasicRet.ERR);
            return basicRet;
        }


        productBackModel.setState(null);
        productBackModel.setPic(null);
        productBackModel.setBackexplain(null);
        OrderProductBack orderProductBack = ordersService.getBackgoodsOrderProductBack(productBackModel);
        if(orderProductBack == null){
            return  new BasicRet(BasicRet.ERR,"未查询到退货商品信息");
        }


        if(orderProductBack.getState()==Quantity.STATE_11){
            basicRet.setMessage("退货已撤消");
            basicRet.setResult(BasicRet.ERR);
            return basicRet;
        }else if(orderProductBack.getState()==Quantity.STATE_10){
            basicRet.setMessage("退货已完成");
            basicRet.setResult(BasicRet.ERR);
            return basicRet;
        }else {
            orderProductBack.setState(state);
            ProductStore store = null;
            LimitTimeStore limitTimeStore = null;
            LimitTimeProd limitTimeProd = null;
            Orders orders = ordersService.getOrdersById(orderProductBack.getOrderid());
            if(orders.getIsonline()==Quantity.STATE_0){
                store = ordersService.getProductStore(orderProductBack.getPdid(),orderProductBack.getPdno(),orderProductBack.getStoreid());
            }
            if(orders.getIsonline()==Quantity.STATE_2){
                limitTimeStore = shopCarService.getLimitTimeStore(orderProductBack.getStoreid());
                limitTimeProd = shopCarService.getLimitTimeProd(orderProductBack.getPdid(),orderProductBack.getLimitid());
            }

            if(orderProductBack!=null){
                OrderProduct orderProduct = ordersService.getOrderProductById(orderProductBack.getOrderpdid());
                List<OrderProduct> list1 = ordersService.getOrderProductByOrderId(orderProductBack.getOrderid());
                if(state!=null){
                     if(state==Quantity.STATE_7){
                            //退货验收
                            orderProduct.setBackstate(Quantity.STATE_2);

                        //平台同意退货扣违约金
                    }else if(state==Quantity.STATE_8){
                            //退货验收
                            orderProduct.setBackstate(Quantity.STATE_2);

                        //平台转入待验收
                    }else if(state==Quantity.STATE_9){
                        //部分退货
                        if (orderProductBack.getBacktype() == Quantity.STATE_2) {
                            for (OrderProduct op : list1) {
                                //找出部分退货相同的商品，删除这个部分退货，并加数量和总价到原来的商品
                                if (op.getPdid().longValue() == orderProductBack.getPdid().longValue() && op.getPdno().equals(orderProductBack.getPdno()) && op.getBackstate() == Quantity.STATE_0) {
                                    op.setNum(op.getNum().add(orderProduct.getNum()));
                                    op.setActualpayment(op.getNum().multiply(op.getPrice()).add(op.getFreight()));
                                    ordersService.updateOrderProduct(op);
                                    ordersService.deleteOrderProduct(orderProduct.getId());
                                    break;
                                    //如果部分退货全部退掉，找不到原来的商品，就更新状态
                                } else {
                                    orderProduct.setBackstate(Quantity.STATE_0);
                                }
                            }
                        } else {
                            orderProduct.setBackstate(Quantity.STATE_0);
                        }
                    }

                    ordersService.updateOrderProduct(orderProduct);
                    ordersService.updateOrderProductBack(orderProductBack);

                    List<OrderProduct> list = ordersService.getOrderProductByOrderId(orderProductBack.getOrderid(),orderProductBack.getOrderpdid());

                    boolean flag = true;
                    for(OrderProduct op:list){
                        if(op.getBackstate()!=Quantity.STATE_3){
                            flag =false;
                            break;
                        }
                    }
                    //判断订单中商品是否都退货完成，就结束订单
                    if(orderProduct.getBackstate()==Quantity.STATE_3&&flag){
                        //删除开票申请记录
                        ordersService.deleteBillRecord(orders.getOrderno());
                        orders.setOrderstatus(Quantity.STATE_7);
                        ordersService.updateSingleOrder(orders);
                    }
                }
            }else{
                basicRet.setMessage("没有退货申请记录");
                basicRet.setResult(BasicRet.ERR);
                return  basicRet;
            }

            //保存操作日志
            OperateLog operateLog = new OperateLog();
            //货状态0=待卖家处理1=卖家同意待收货2=卖家同意直接退款3=卖家收到货同意退款4=卖家不同意5=买家同意验货6=买家申请异议7=平台同意退货不扣违约金8=平台同意退货扣违约金9=平台转入待验收10=退货成功11=撤消
            operateLog.setOpid(admin.getId());
            operateLog.setOpname(admin.getUsername());
            operateLog.setOptime(new Date());
            operateLog.setOptype(Quantity.STATE_1);
            operateLog.setOrderid(orderProductBack.getOrderid());
            operateLog.setOrderno(orderProductBack.getOrderno());
            operateLog.setOrderpdid(orderProductBack.getOrderpdid());



            operateLog.setContent(JinshangUtils.orderProductBackStateMess(orderProductBack.getState()));
            ordersService.saveOperatelog(operateLog);


            //保存用户日志
            memberLogOperator.saveMemberLog(null,admin,"修改退货申请，退货申请id为："+productBackModel.getId(),"/rest/admin/orders/updateOrderProductBack",request,memberOperateLogService);
            basicRet.setMessage("修改成功");
            basicRet.setResult(BasicRet.SUCCESS);
            return  basicRet;
        }
    }





    @RequestMapping(value = "/updateOrderProductNum",method = RequestMethod.POST)
    @ApiOperation(value = "修改订单商品数量")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderno",value = "退货单号",required = true,paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "orderProductJson",value = "订单产品json [{\"id\":1909,\"num\":0.10}]",required = true,paramType = "query",dataType = "String"),
    })
    public BasicRet updateOrderProductNum(@RequestParam(required = true) String orderno,
                                          @RequestParam(required = true) String orderProductJson,Model model) throws WxPayException, AlipayApiException {

        Admin admin = (Admin) model.asMap().get(AppConstant.ADMIN_SESSION_NAME);

        Orders orders = ordersService.getOrdersByOrderNo(orderno);

        //运费
        BigDecimal orderFreight = orders.getFreight();
        //订单总价
        BigDecimal orderTotalprice = orders.getTotalprice();
        //实付款
        BigDecimal orderActualpayment = orders.getActualpayment();


        if(orders == null){
            return  new BasicRet(BasicRet.ERR,"订单不存在");
        }

        if(orders.getOrderstatus() != Quantity.STATE_1){ //未发货订单可以修改商品数量
            return  new BasicRet(BasicRet.ERR,"只有未发货订单可以修改商品数量");
        }

        if(orders.getIsonline() == Quantity.STATE_2){
            return  new BasicRet(BasicRet.ERR,"限时购订单不可修改");
        }

        if(orders.getOrdertype() != Quantity.STATE_0){
            return  new BasicRet(BasicRet.ERR,"远期订单不可修改");
        }


        List<OrderProduct> updateOrderProductList = GsonUtils.toList(orderProductJson,OrderProduct.class);
        List<OrderProduct> orderProductList = ordersService.getOrderProductByOrderId(orders.getId());

        Set<Long> orderprodidSet = new HashSet<>();



        if(updateOrderProductList.size() != orderProductList.size()){
            return  new BasicRet(BasicRet.ERR,"提交订单商品的数量与实际数量不符");
        }

        for(OrderProduct orderProduct : orderProductList) {
            for (OrderProduct updateP : updateOrderProductList) {
                if(orderProduct.getId().equals(updateP.getId())){
                    orderprodidSet.add(orderProduct.getId());
                }
            }
        }

        if(orderProductList.size() != orderprodidSet.size()){
          return  new BasicRet(BasicRet.ERR,"提交订单商品与实际商品不对应");
        }


        List<OrderProduct> saveOrderProductList = new ArrayList<>();

            for (OrderProduct orderProduct : orderProductList) {
                for (OrderProduct updateP : updateOrderProductList) {
                    if (orderProduct.getId().equals(updateP.getId())) {

                        if (orderProduct.getNum().compareTo(updateP.getNum()) < 0) {
                            throw new RuntimeException(orderProduct.getPdname() + "要修改的数量不可比原订单数量多");
                        }

                        if (orderProduct.getProtype() != Quantity.STATE_0) {
                            throw new RuntimeException(orderProduct.getPdname() + "为远期订单商品，远期订单不可修改");
                        }


                        ProductInfo productInfo = ordersService.getProductInfoByPrimeKey(orderProduct.getPdid());

                        if (productInfo == null) {
                            throw new RuntimeException("商品id为" + orderProduct.getPdid() + "的商品不存在");
                        }

                        ProductStore productStore1 = ordersService.getProductStore(orderProduct.getPdid(), orderProduct.getPdno(), orderProduct.getStoreid());

                        if (productStore1 == null) {
                            throw new RuntimeException("商品id为" + orderProduct.getPdid() + "的库存信息不存在");
                        }

                        OrderProduct saveOrderProduct = new OrderProduct();
                        saveOrderProduct.setId(updateP.getId());

                        if(updateP.getNum().compareTo(Quantity.BIG_DECIMAL_0) ==0){ //全部不发了
                            saveOrderProduct.setNum(updateP.getNum());
                            saveOrderProduct.setPrice(orderProduct.getPrice());
                            saveOrderProduct.setFreight(Quantity.BIG_DECIMAL_0);
                            saveOrderProduct.setActualpayment(Quantity.BIG_DECIMAL_0);
                        }else {
                            saveOrderProduct.setNum(updateP.getNum());
                            saveOrderProduct.setPrice(orderProduct.getPrice());

                            //计算运费
                            BigDecimal figtht = BigDecimal.valueOf(0);
                            if (orders.getIsonline() == Quantity.STATE_0) {
                                figtht = ordersService.countSinglePdFight(productInfo, productStore1, orders.getProvince(), orders.getCity(), saveOrderProduct.getNum());
                                saveOrderProduct.setFreight(figtht);
                            } else if (orders.getIsonline() == Quantity.STATE_2) {
                                saveOrderProduct.setFreight(BigDecimal.valueOf(0));
                            }

                            saveOrderProduct.setActualpayment(figtht.add(saveOrderProduct.getPrice().multiply(saveOrderProduct.getNum())));
                        }

                        saveOrderProductList.add(saveOrderProduct);

                        if (ordersService.updateOrderProduct(saveOrderProduct) != 1) {
                            throw new RuntimeException("修改订单商品id:" + saveOrderProduct.getId() + "失败，请联系网站管理员");
                        }

                    }
                }
            }



        BigDecimal totalFreight = Quantity.BIG_DECIMAL_0;
        BigDecimal totalPrice = Quantity.BIG_DECIMAL_0;
        for(OrderProduct op : saveOrderProductList){
           totalFreight = totalFreight.add(op.getFreight());
           totalPrice =  totalPrice.add(op.getActualpayment());
        }

        totalFreight.setScale(2,BigDecimal.ROUND_HALF_UP);
        totalPrice.setScale(2,BigDecimal.ROUND_HALF_UP);

        //退款金额
        BigDecimal backMoney = orderTotalprice.subtract(totalPrice);

        if(backMoney.compareTo(Quantity.BIG_DECIMAL_0) <0){
            throw  new RuntimeException("退款金额不可少于0");
        }

        if(backMoney.compareTo(Quantity.BIG_DECIMAL_0)>0) {
            //买家退款资金明细
            BuyerCapital buyerCapital1 = null;

            //判断退回到余额还是授信
            Date tranTime = new Date();
            BuyerCapital buyerCapital = ordersService.getBuyerCapitalByNoType(orders.getOrderno(), Quantity.STATE_0);
            if (buyerCapital != null) {
                //退回到余额
                if (buyerCapital.getPaytype() == Quantity.STATE_3) {
                    memberService.updateBuyerMemberBalanceInDb(orders.getMemberid(), backMoney);
                    buyerCapital1 = createBuyerBackPay(orders, backMoney, tranTime, Quantity.STATE_3);
                }
                //退回到授信
                if (buyerCapital.getPaytype() == Quantity.STATE_4) {
                    buyerCapital1 = createBuyerBackPay(orders, backMoney, tranTime, Quantity.STATE_4);
                    memberService.updateBuyerMemberCreditBalanceInDb(orders.getMemberid(), backMoney.multiply(Quantity.BIG_DECIMAL_MINUS_1), backMoney);
                }


                //退回到支付宝或微信
                if (buyerCapital.getPaytype() == Quantity.STATE_0 ||
                        buyerCapital.getPaytype() == Quantity.STATE_1 ||
                        buyerCapital.getPaytype() == Quantity.STATE_2) {
                    String uuid = orders.getUuid();
                    if (uuid != null) {
                        Refund refund = new Refund();
                        refund.setOutTradeNo(uuid);
                        if (orders.getPaytype() == Quantity.STATE_0) {
                            refund.setChannel("alipay");
                        } else if (orders.getPaytype() == Quantity.STATE_1) {
                            refund.setChannel("wxpay");
                        } else if (orders.getPaytype() == Quantity.STATE_2) {
                            refund.setChannel("bank");
                        }
                        refund.setRefundAmount((backMoney.multiply(new BigDecimal(100))).longValue());
                        refund.setRefundReason("订单退款");
                        List<Orders> ordersList = ordersService.getOrdersByUUID(uuid);
                        BigDecimal totalAmout = BigDecimal.valueOf(0);
                        for (Orders od : ordersList) {
                            totalAmout = totalAmout.add(od.getActualpayment());
                        }
                        refund.setTotalAmount((totalAmout.multiply(new BigDecimal(100))).longValue());
                        boolean result = false;
                        try {
                            if ("alipay".equals(refund.getChannel())) {
                                result = alipayService.refund(refund);
                            } else if ("wxpay".equals(refund.getChannel())) {
                                result = wxPayService.refund(refund);
                            } else if ("bank".equals(refund.getChannel())) {
                                result = abcService.refund(refund);
                            }
                        } catch (Exception e) {
                            throw e;
                        }

                        System.out.println("退货通道：" + refund.getChannel() + "退货结果：" + result);

                        if (result) {
                            if (orders.getPaytype() == Quantity.STATE_0) {
                                buyerCapital1 = createBuyerBackPay(orders, backMoney, tranTime, Quantity.STATE_0);
                            } else if (orders.getPaytype() == Quantity.STATE_1) {
                                buyerCapital1 = createBuyerBackPay(orders, backMoney, tranTime, Quantity.STATE_1);
                            } else if (orders.getPaytype() == Quantity.STATE_2) {
                                buyerCapital1 = createBuyerBackPay(orders, backMoney, tranTime, Quantity.STATE_2);
                            }
                        } else {
                            throw new RuntimeException("退款失败");
                        }
                    }
                }

                if (buyerCapital1 != null) {
                    buyerCapitalService.insertSelective(buyerCapital1);
                }
            } else {
                throw new RuntimeException("未查询到该订单的付款信息");
            }
        }


        Orders updateOrders = new Orders();
        updateOrders.setId(orders.getId());

        //如果退款金额与订单总额相等
        if(backMoney.compareTo(orderTotalprice) ==0){
            updateOrders.setOrderstatus(Quantity.STATE_7);
            updateOrders.setReason("平台取消订单");
            updateOrders.setTotalprice(Quantity.BIG_DECIMAL_0);
            updateOrders.setActualpayment(Quantity.BIG_DECIMAL_0);
            updateOrders.setFreight(Quantity.BIG_DECIMAL_0);
            ordersService.deleteBillRecord(orders.getId().toString());
        }else{
            updateOrders.setFreight(totalFreight);
            updateOrders.setTotalprice(totalPrice);
            updateOrders.setActualpayment(totalPrice);

            //修改开票金额
            if(orders.getIsbilling() == Quantity.STATE_1){
                billingRecordService.updateAdminDecOrderProductnum(orders.getId().toString(),orders.getMemberid(),backMoney.multiply(new BigDecimal(-1)));
            }
        }


        ordersService.updateSingleOrder(updateOrders);


        //操作日志
        OperateLog operateLog = new OperateLog();
        operateLog.setContent("后台修改订单商品数量，退款"+backMoney);
        operateLog.setOpid(admin.getId());
        operateLog.setOpname(admin.getUsername());
        operateLog.setOptime(new Date());
        operateLog.setOptype(Quantity.STATE_0);
        operateLog.setOrderid(orders.getId());
        operateLog.setOrderno(orders.getOrderno());
        ordersService.saveOperatelog(operateLog);


        return  new BasicRet(BasicRet.SUCCESS,"修改成功");
    }






/*
    @RequestMapping(value = "/updateOrderProductBack",method = RequestMethod.POST)
    @ApiOperation(value = "修改退货申请")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "退货申请id",required = true,paramType = "query",dataType = "long"),
            @ApiImplicitParam(name = "state",value = "退货状态0=待卖家处理1=卖家同意待收货2=卖家同意直接退款3=卖家收到货同意退款4=卖家不同意5=买家同意验货6=买家申请异议7=平台同意退货不扣违约金8=平台同意退货扣违约金9=平台转入待验收10=退货成功11=撤消",required = false,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "logisticsno",value = "退货单号",required = false,paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "logisticscompany",value = "退货物流公司",required = false,paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "backaddress",value = "退货地址",required = false,paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "contact",value = "退货联系人",required = false,paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "contactphone",value = "退货联系人电话",required = false,paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "sellernotagree",value = "卖家不同意原因",required = false,paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "adminreason",value = "平台处理说明",required = false,paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "adminstate",value = "平台处理意见0=正常1=不扣违约金2=扣违约金3=转入待验收",required = false,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "province",value = "省",required = false,paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "city",value = "市",required = false,paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "area",value = "区",required = false,paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "backtype",value = "退货类型0=仅退款1=退货退款2=部分退货",required = true,paramType = "query",dataType = "int"),
    })
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.RETURNMANAGEMENT + "')")
    public BasicRet updateOrderProductBack(Model model, ProductBackModel productBackModel, HttpServletRequest request) throws CashException {

        Admin member = (Admin) model.asMap().get(AppConstant.ADMIN_SESSION_NAME);

        BasicRet basicRet = new BasicRet();

        Short state = productBackModel.getState();

        if(state != Quantity.STATE_7 && state != Quantity.STATE_8 && state != Quantity.STATE_9){
            basicRet.setMessage("操作状态不合法");
            basicRet.setResult(BasicRet.ERR);
            return basicRet;
        }


        Short adminstate = productBackModel.getAdminstate();
        OrderProductBack orderProductBack = ordersService.getOrderProductBackById(productBackModel.getId());

        if(orderProductBack.getState()==Quantity.STATE_11){
            basicRet.setMessage("退货已撤消");
            basicRet.setResult(BasicRet.ERR);
            return basicRet;
        }else if(orderProductBack.getState()==Quantity.STATE_10){
            basicRet.setMessage("退货已完成");
            basicRet.setResult(BasicRet.ERR);
            return basicRet;
        }else {
            orderProductBack.setState(state);
            ProductStore store = null;
            LimitTimeStore limitTimeStore = null;
            LimitTimeProd limitTimeProd = null;
            Orders orders = ordersService.getOrdersById(orderProductBack.getOrderid());
            if(orders.getIsonline()==Quantity.STATE_0){
                store = ordersService.getProductStore(orderProductBack.getPdid(),orderProductBack.getPdno(),orderProductBack.getStoreid());
            }
            if(orders.getIsonline()==Quantity.STATE_2){
                limitTimeStore = shopCarService.getLimitTimeStore(orderProductBack.getStoreid());
                limitTimeProd = shopCarService.getLimitTimeProd(orderProductBack.getPdid(),orderProductBack.getLimitid());
            }
            if(adminstate!=null){
                orderProductBack.setAdminstate(adminstate);
            }
            if(StringUtils.hasText(productBackModel.getLogisticsno())){
                orderProductBack.setLogisticsno(productBackModel.getLogisticsno());
            }
            if(StringUtils.hasText(productBackModel.getLogisticscompany())){
                orderProductBack.setLogisticscompany(productBackModel.getLogisticscompany());
            }
            if(StringUtils.hasText(productBackModel.getBackaddress())){
                orderProductBack.setBackaddress(productBackModel.getBackaddress());
            }
            if(StringUtils.hasText(productBackModel.getContact())){
                orderProductBack.setContact(productBackModel.getContact());
            }
            if(StringUtils.hasText(productBackModel.getContactphone())){
                orderProductBack.setContactphone(productBackModel.getContactphone());
            }
            if(StringUtils.hasText(productBackModel.getSellernotagree())){
                orderProductBack.setSellernotagree(productBackModel.getSellernotagree());
            }
            if(StringUtils.hasText(productBackModel.getAdminreason())){
                orderProductBack.setAdminreason(productBackModel.getAdminreason());
            }
            if(StringUtils.hasText(productBackModel.getProvince())){
                orderProductBack.setProvince(productBackModel.getProvince());
            }
            if(StringUtils.hasText(productBackModel.getCity())){
                orderProductBack.setCity(productBackModel.getCity());
            }
            if(StringUtils.hasText(productBackModel.getArea())){
                orderProductBack.setArea(productBackModel.getArea());
            }
            if(productBackModel.getBacktype()!=null){
                orderProductBack.setBacktype(productBackModel.getBacktype());
            }
            if(orderProductBack!=null){
                OrderProduct orderProduct = ordersService.getOrderProductById(orderProductBack.getOrderpdid());
                List<OrderProduct> list1 = ordersService.getOrderProductByOrderId(orderProductBack.getOrderid());
                if(state!=null){
                    //卖家同意待验货
                    if(state==Quantity.STATE_1){
                        orderProduct.setBackstate(Quantity.STATE_2);
                        //卖家同意直接退款或卖家收到货同意退款
                    } else if(state==Quantity.STATE_2||state==Quantity.STATE_3){
                        orderProduct.setBackstate(Quantity.STATE_3);
                        handleBackGoods(member,orderProductBack,orderProduct,orders);
                        orderProductBack.setState(Quantity.STATE_10);
                        //增加库存
                        if(orders.getIsonline()==Quantity.STATE_0){
                            store.setPdstorenum(store.getPdstorenum().add(orderProductBack.getPdnum()));
                            ordersService.updateProductStore(store);
                        }
                        if(orders.getIsonline()==Quantity.STATE_2){
                            limitTimeStore.setStorenum(limitTimeStore.getStorenum().add(orderProductBack.getPdnum()));
                            limitTimeStore.setSalesnum(limitTimeStore.getSalesnum().subtract(orderProductBack.getPdnum()));
                            shopCarService.updateLimitTimeStore(limitTimeStore);
                            limitTimeProd.setSalestotalnum(limitTimeProd.getSalestotalnum().subtract(orderProduct.getNum()));
                            shopCarService.updateLimitTimeProd(limitTimeProd);
                        }
                        //买家同意验货
                    }else if(state==Quantity.STATE_5){
                        orderProduct.setBackstate(Quantity.STATE_0);
                        //买家不同意申请异议
                    }else if(state==Quantity.STATE_6){
                        orderProduct.setBackstate(Quantity.STATE_4);
                        orderProductBack.setDissidencetime(new Date());
                        //平台同意退货不扣违约金
                    }else if(state==Quantity.STATE_7){
                        //只退款
                        if(orderProductBack.getBacktype()==Quantity.STATE_0){
                            handleBackGoods(member,orderProductBack,orderProduct,orders);
                            orderProduct.setBackstate(Quantity.STATE_3);
                            orderProductBack.setState(Quantity.STATE_10);
                            //增加库存
                            if(orders.getIsonline()==Quantity.STATE_0){
                                store.setPdstorenum(store.getPdstorenum().add(orderProductBack.getPdnum()));
                                ordersService.updateProductStore(store);
                            }
                            if(orders.getIsonline()==Quantity.STATE_2){
                                limitTimeStore.setStorenum(limitTimeStore.getStorenum().add(orderProductBack.getPdnum()));
                                limitTimeStore.setSalesnum(limitTimeStore.getSalesnum().subtract(orderProductBack.getPdnum()));
                                shopCarService.updateLimitTimeStore(limitTimeStore);
                                limitTimeProd.setSalestotalnum(limitTimeProd.getSalestotalnum().subtract(orderProduct.getNum()));
                                shopCarService.updateLimitTimeProd(limitTimeProd);
                            }
                        }else{
                            //退货验收
                            orderProduct.setBackstate(Quantity.STATE_2);
                        }
                        //平台同意退货扣违约金
                    }else if(state==Quantity.STATE_8){
                        //只退款
                        if(orderProductBack.getBacktype()==Quantity.STATE_0){
                            handleBackGoods(member,orderProductBack,orderProduct,orders);
                            orderProduct.setBackstate(Quantity.STATE_3);
                            orderProductBack.setState(Quantity.STATE_10);
                            //增加库存
                            if(orders.getIsonline()==Quantity.STATE_0){
                                store.setPdstorenum(store.getPdstorenum().add(orderProductBack.getPdnum()));
                                ordersService.updateProductStore(store);
                            }
                            if(orders.getIsonline()==Quantity.STATE_2){
                                limitTimeStore.setStorenum(limitTimeStore.getStorenum().add(orderProductBack.getPdnum()));
                                limitTimeStore.setSalesnum(limitTimeStore.getSalesnum().subtract(orderProductBack.getPdnum()));
                                shopCarService.updateLimitTimeStore(limitTimeStore);
                                limitTimeProd.setSalestotalnum(limitTimeProd.getSalestotalnum().subtract(orderProduct.getNum()));
                                shopCarService.updateLimitTimeProd(limitTimeProd);
                            }
                        }else{
                            //退货验收
                            orderProduct.setBackstate(Quantity.STATE_2);
                        }
                        //平台转入待验收
                    }else if(state==Quantity.STATE_9){
                        //部分退货
                        if (orderProductBack.getBacktype() == Quantity.STATE_2) {
                            for (OrderProduct op : list1) {
                                //找出部分退货相同的商品，删除这个部分退货，并加数量和总价到原来的商品
                                if (op.getPdid().longValue() == orderProductBack.getPdid().longValue() && op.getPdno().equals(orderProductBack.getPdno()) && op.getBackstate() == Quantity.STATE_0) {
                                    op.setNum(op.getNum().add(orderProduct.getNum()));
                                    op.setActualpayment(op.getNum().multiply(op.getPrice()).add(op.getFreight()));
                                    ordersService.updateOrderProduct(op);
                                    ordersService.deleteOrderProduct(orderProduct.getId());
                                    break;
                                    //如果部分退货全部退掉，找不到原来的商品，就更新状态
                                } else {
                                    orderProduct.setBackstate(Quantity.STATE_0);
                                }
                            }
                        } else {
                            orderProduct.setBackstate(Quantity.STATE_0);
                        }
                    }else if(state==Quantity.STATE_11){
                        orderProduct.setBackstate(Quantity.STATE_0);
                    } else{
                        //卖家不同意
                    }
                    ordersService.updateOrderProduct(orderProduct);
                    ordersService.updateOrderProductBack(orderProductBack);

                    List<OrderProduct> list = ordersService.getOrderProductByOrderId(orderProductBack.getOrderid(),orderProductBack.getOrderpdid());

                    boolean flag = true;
                    for(OrderProduct op:list){
                        if(op.getBackstate()!=Quantity.STATE_3){
                            flag =false;
                            break;
                        }
                    }
                    //判断订单中商品是否都退货完成，就结束订单
                    if(orderProduct.getBackstate()==Quantity.STATE_3&&flag){
                        //删除开票申请记录
                        ordersService.deleteBillRecord(orders.getOrderno());
                        orders.setOrderstatus(Quantity.STATE_7);
                        ordersService.updateSingleOrder(orders);
                    }
                }
            }else{
                basicRet.setMessage("没有退货申请记录");
                basicRet.setResult(BasicRet.ERR);
                return  basicRet;
            }

            //保存操作日志
            OperateLog operateLog = new OperateLog();
            //货状态0=待卖家处理1=卖家同意待收货2=卖家同意直接退款3=卖家收到货同意退款4=卖家不同意5=买家同意验货6=买家申请异议7=平台同意退货不扣违约金8=平台同意退货扣违约金9=平台转入待验收10=退货成功11=撤消
            operateLog.setOpid(member.getId());
            operateLog.setOpname(member.getUsername());
            operateLog.setOptime(new Date());
            operateLog.setOptype(Quantity.STATE_1);
            operateLog.setOrderid(orderProductBack.getOrderid());
            operateLog.setOrderno(orderProductBack.getOrderno());
            operateLog.setOrderpdid(orderProductBack.getOrderpdid());

//            if(orderProductBack.getState()==Quantity.STATE_1){
//                operateLog.setContent("卖家同意待收货");
//                ordersService.saveOperatelog(operateLog);
//            }
//            if(orderProductBack.getState()==Quantity.STATE_2){
//                operateLog.setContent("卖家同意直接退款");
//                ordersService.saveOperatelog(operateLog);
//            }
//            if(orderProductBack.getState()==Quantity.STATE_3){
//                operateLog.setContent("卖家收到货同意退款");
//                ordersService.saveOperatelog(operateLog);
//            }
//            if(orderProductBack.getState()==Quantity.STATE_4){
//                operateLog.setContent("卖家不同意");
//                ordersService.saveOperatelog(operateLog);
//            }
//            if(orderProductBack.getState()==Quantity.STATE_5){
//                operateLog.setContent("买家同意验货");
//                ordersService.saveOperatelog(operateLog);
//            }
//            if(orderProductBack.getState()==Quantity.STATE_6){
//                operateLog.setContent("买家申请异议");
//                ordersService.saveOperatelog(operateLog);
//            }
//            if(orderProductBack.getState()==Quantity.STATE_7){
//                operateLog.setContent("平台同意退货不扣违约金");
//                ordersService.saveOperatelog(operateLog);
//            }
//            if(orderProductBack.getState()==Quantity.STATE_8){
//                operateLog.setContent("平台同意退货扣违约金");
//                ordersService.saveOperatelog(operateLog);
//            }
//            if(orderProductBack.getState()==Quantity.STATE_9){
//                operateLog.setContent("平台转入待验收");
//                ordersService.saveOperatelog(operateLog);
//            }
//            if(orderProductBack.getState()==Quantity.STATE_10){
//                operateLog.setContent("退货成功");
//                ordersService.saveOperatelog(operateLog);
//            }
//            if(orderProductBack.getState()==Quantity.STATE_11){
//                operateLog.setContent("退货撤消");
//                ordersService.saveOperatelog(operateLog);
//            }

            operateLog.setContent(JinshangUtils.orderProductBackStateMess(orderProductBack.getState()));
            ordersService.saveOperatelog(operateLog);


            //保存用户日志
            memberLogOperator.saveMemberLog(null,member,"修改退货申请，退货申请id为："+productBackModel.getId(),"/rest/admin/orders/updateOrderProductBack",request,memberOperateLogService);
            basicRet.setMessage("修改成功");
            basicRet.setResult(BasicRet.SUCCESS);
            return  basicRet;
        }
    }

*/

    /**
     * 退款操作
     * @param operator 操作人
     * @param orderProductBack
     * @param orderProduct
     */
    /*
    public void handleBackGoods(Admin operator,OrderProductBack orderProductBack,OrderProduct orderProduct,Orders order) throws CashException {
        //退款申请人
        Member buyer = memberService.getMemberById(orderProductBack.getMemberid());
        Member seller = memberService.getMemberById(orderProductBack.getSellerid());
        

        Member oldBuyer = new Member();
        BeanUtils.copyProperties(buyer,oldBuyer);

        Member oldSeller = new Member();
        BeanUtils.copyProperties(seller,oldSeller);

        TransactionSetting transactionSetting = transactionSettingService.getTransactionSetting();
        BigDecimal getLiquidated = transactionSetting.getGetliquidated().multiply(new BigDecimal(0.01));
        BigDecimal forwardsalesmargin = transactionSetting.getForwardsalesmargin().multiply(new BigDecimal(0.01));

        if(orderProductBack!=null&&orderProduct!=null&&order!=null){
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
            if(orderProduct.getProtype()==Quantity.STATE_0){
                BigDecimal orderPay = orderProduct.getActualpayment().subtract(orderProduct.getFreight());
                //是否有部分退货的情况
                BigDecimal backPayMoney = orderProductBack.getBackmoney();
                //违约金
                penaltyPay = orderPay.multiply(getLiquidated);
                //退回的金额,异议扣违约金
                if(orderProductBack.getAdminstate()==Quantity.STATE_2){
                    backPay = backPayMoney.subtract(penaltyPay);
                }else{
                    backPay = backPayMoney;
                }
                //判断退回到余额还是授信
                BuyerCapital buyerCapital = ordersService.getBuyerCapitalByNoType(orderProduct.getOrderno(),Quantity.STATE_0);
                if(buyerCapital!=null){
                    //退回到余额
                    if(buyerCapital.getPaytype()==Quantity.STATE_3){
                        buyer.setBalance(buyer.getBalance().add(backPay));
                        buyerCapital1 = createBuyerBackPay(order,backPay,tranTime,Quantity.STATE_3);
                        salerCapital1 = createSalerBackPay(order,backPay,tranTime);
                        buyerCapitals.add(buyerCapital1);
                        salerCapitals.add(salerCapital1);
                    }
                    //退回到授信
                    if(buyerCapital.getPaytype()==Quantity.STATE_4){
                        buyer.setAvailablelimit(buyer.getAvailablelimit().add(backPay));
                        buyer.setUsedlimit(buyer.getUsedlimit().subtract(backPay));
                        buyerCapital1 = createBuyerBackPay(order,backPay,tranTime,Quantity.STATE_4);
                        salerCapital1 = createSalerBackPay(order,backPay,tranTime);
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
                    if(orderProductBack.getAdminstate()==Quantity.STATE_2){
                        seller.setSellerbanlance(seller.getSellerbanlance().add(penaltyPay.multiply(forwardsalesmargin)));
                        //违约金
                        buyerCapital2 = createBuyerPenaltyPay(order,penaltyPay,tranTime,Quantity.STATE_6,orderPay,Quantity.BUYER_BACK_REASON);
                        salerCapital2 = createSalerPenaltyPay(order,penaltyPay,tranTime,Quantity.STATE_6,orderPay,Quantity.BUYER_BACK_REASON);
                        buyerCapitals.add(buyerCapital2);
                        salerCapitals.add(salerCapital2);
                    }
                }
            }
            //远期全款
            if(orderProduct.getProtype()==Quantity.STATE_1){
                //计算扣除违约金
                BigDecimal orderPay = orderProduct.getAllpay();
                //违约金
                penaltyPay = orderPay.multiply(getLiquidated);
                //退回的金额,异议扣违约金
                if(orderProductBack.getAdminstate()==Quantity.STATE_2){
                    backPay = orderPay.subtract(penaltyPay);
                }else{
                    backPay = orderPay;
                }

                //判断退回到余额还是授信
                BuyerCapital buyerCapital = ordersService.getBuyerCapitalByNoType(orderProduct.getOrderno(),Quantity.STATE_9);
                if(buyerCapital!=null){
                    //退回到余额
                    if(buyerCapital.getPaytype()==Quantity.STATE_3){
                        buyer.setBalance(buyer.getBalance().add(backPay));
                        buyerCapital1 = createBuyerBackPay(order,backPay,tranTime,Quantity.STATE_3);
                        salerCapital1 = createSalerBackPay(order,backPay,tranTime);
                        buyerCapitals.add(buyerCapital1);
                        salerCapitals.add(salerCapital1);
                    }
                    //退回到授信
                    if(buyerCapital.getPaytype()==Quantity.STATE_4){
                        buyer.setAvailablelimit(buyer.getAvailablelimit().add(backPay));
                        buyer.setUsedlimit(buyer.getUsedlimit().subtract(backPay));
                        buyerCapital1 = createBuyerBackPay(order,backPay,tranTime,Quantity.STATE_4);
                        salerCapital1 = createSalerBackPay(order,backPay,tranTime);
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
                    if(orderProductBack.getAdminstate()==Quantity.STATE_2){
                        seller.setSellerbanlance(seller.getSellerbanlance().add(penaltyPay.multiply(forwardsalesmargin)));
                        //违约金
                        buyerCapital2 = createBuyerPenaltyPay(order,penaltyPay,tranTime,Quantity.STATE_6,orderPay,Quantity.BUYER_BACK_REASON);
                        salerCapital2 = createSalerPenaltyPay(order,penaltyPay,tranTime,Quantity.STATE_6,orderPay,Quantity.BUYER_BACK_REASON);
                        buyerCapitals.add(buyerCapital2);
                        salerCapitals.add(salerCapital2);
                    }
                }

            }
            //远期余款
            if(orderProduct.getProtype()==Quantity.STATE_2){
                //计算扣除违约金
                BigDecimal orderPay = orderProduct.getActualpayment().subtract(orderProduct.getFreight());
                BigDecimal partPay = orderProduct.getPartpay().subtract(orderProduct.getFreight());
                BigDecimal yuPay = orderProduct.getYupay();

                //违约金
                penaltyPay = orderPay.multiply(getLiquidated);
                //定金支付明细
                BuyerCapital depositBuyerCapital = ordersService.getBuyerCapitalByNoType(order.getOrderno(), Quantity.STATE_7);

                if(depositBuyerCapital!=null){
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
                    if(orderProductBack.getAdminstate()==Quantity.STATE_2){
                        seller.setSellerbanlance(seller.getSellerbanlance().add(penaltyPay.multiply(forwardsalesmargin)));
                        //违约金
                        buyerCapital2 = createBuyerPenaltyPay(order,penaltyPay,tranTime,Quantity.STATE_6,orderPay,Quantity.BUYER_BACK_REASON);
                        salerCapital2 = createSalerPenaltyPay(order,penaltyPay,tranTime,Quantity.STATE_6,orderPay,Quantity.BUYER_BACK_REASON);
                        buyerCapitals.add(buyerCapital2);
                        salerCapitals.add(salerCapital2);
                    }
                }
            }
            //保存用户余额和授信
            ordersService.saveMember(buyer,oldBuyer);
            ordersService.saveMember(seller,oldSeller);


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
                operateLog.setOrderpdid(orderProductBack.getOrderpdid());
                ordersService.saveOperatelog(operateLog);
            }
            if(salerCapitals.size()>0){
                ordersService.insertSallerCapital(salerCapitals);
            }
        }

    }

    */



    /**
     * 退款操作
     * @param orderProductBack
     * @param orderProduct
     * @param order
     * @param operatorId   操作者id
     * @param operatorUsername 操作者用户名
     * @throws CashException
     */
    private void handleBackGoods(OrderProductBack orderProductBack, OrderProduct orderProduct, Orders order,Long operatorId,String operatorUsername) throws CashException {

        //卖家
        Member sellerMember = memberService.getMemberById(order.getSaleid());
        Member oldSellerMember = new Member();
        BeanUtils.copyProperties(sellerMember,oldSellerMember);

        //退款申请人
        Member buyer = memberService.getMemberById(order.getMemberid());
        Member oldBuyer = new Member();
        BeanUtils.copyProperties(buyer,oldBuyer);


        //违约金占比
        TransactionSetting transactionSetting = transactionSettingService.getTransactionSetting();
        BigDecimal getLiquidated = transactionSetting.getGetliquidated().multiply(new BigDecimal(0.01));
        BigDecimal forwardsalesmargin = transactionSetting.getForwardsalesmargin().multiply(new BigDecimal(0.01));

        if (orderProductBack != null && orderProduct != null && order != null) {
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

                //退回的金额,异议扣违约金
                if (orderProductBack.getAdminstate() == Quantity.STATE_2) {
                    //违约金
                    penaltyPay = orderPay.multiply(getLiquidated).setScale(2,BigDecimal.ROUND_HALF_UP);
                }

                backPay = backPayMoney;

                //判断退回到余额还是授信
                BuyerCapital buyerCapital = ordersService.getBuyerCapitalByNoType(orderProduct.getOrderno(), Quantity.STATE_0);
                if (buyerCapital != null) {
                    //退回到余额
                    if (buyerCapital.getPaytype() == Quantity.STATE_3) {
                        buyer.setBalance(buyer.getBalance().add(backPay.subtract(penaltyPay)).setScale(2,BigDecimal.ROUND_HALF_UP));
                        buyerCapital1 = createBuyerBackPay(order, backPay.subtract(penaltyPay).setScale(2,BigDecimal.ROUND_HALF_UP), tranTime, Quantity.STATE_3);
                        salerCapital1 = createSalerBackPay(order, backPay, tranTime);
                        buyerCapitals.add(buyerCapital1);
                        salerCapitals.add(salerCapital1);
                    }


                    //退回到授信
                    if (buyerCapital.getPaytype() == Quantity.STATE_4) {
                        buyer.setAvailablelimit(buyer.getAvailablelimit().add(backPay.subtract(penaltyPay)).setScale(2,BigDecimal.ROUND_HALF_UP));
                        buyer.setUsedlimit(buyer.getUsedlimit().subtract(backPay.subtract(penaltyPay)));
                        buyerCapital1 = createBuyerBackPay(order, backPay.subtract(penaltyPay).setScale(2,BigDecimal.ROUND_HALF_UP), tranTime, Quantity.STATE_4);
                        salerCapital1 = createSalerBackPay(order, backPay, tranTime);
                        buyerCapitals.add(buyerCapital1);
                        salerCapitals.add(salerCapital1);
                    }


                    //退回到支付宝\微信\银行卡
                    if(buyerCapital.getPaytype()==Quantity.STATE_0||buyerCapital.getPaytype()==Quantity.STATE_1||buyerCapital.getPaytype()==Quantity.STATE_2){
                        String uuid = order.getUuid();
                        Long ordertime = order.getOrdertime();
                        if(uuid!=null){
                            Refund refund = new Refund();
                            refund.setDatetime(ordertime);
                            refund.setOutTradeNo(uuid);
                            if(order.getPaytype()==Quantity.STATE_0){
                                refund.setChannel("alipay");
                            }else if(order.getPaytype()==Quantity.STATE_1){
                                refund.setChannel("wxpay");
                            }else {
                                refund.setChannel("bank");
                            }

                            refund.setRefundAmount((backPay.subtract(penaltyPay).multiply(new BigDecimal(100))).setScale(2,BigDecimal.ROUND_HALF_UP).longValue());
                            refund.setRefundReason("订单退款");
                            List<Orders> ordersList = ordersService.getOrdersByUUID(uuid);
                            BigDecimal totalAmout = new BigDecimal(0);
                            for(Orders od : ordersList){
                                totalAmout = totalAmout.add(od.getActualpayment());
                            }
                            refund.setTotalAmount((totalAmout.multiply(new BigDecimal(100))).setScale(2,BigDecimal.ROUND_HALF_UP).longValue());
                            boolean result = false;
                            try {
                                if("alipay".equals(refund.getChannel())){
                                    result = alipayService.refund(refund);
                                }else if("wxpay".equals(refund.getChannel())){
                                    result = wxPayService.refund(refund);
                                }else {
                                    result = abcService.refund(refund);
                                }
                            }catch (Exception e){

                            }

                            System.out.println("退货通道："+refund.getChannel()+"退货结果："+result);
                            if(result){
                                if(order.getPaytype()==Quantity.STATE_0){
                                    buyerCapital1 = createBuyerBackPay(order, backPay.subtract(penaltyPay).setScale(2,BigDecimal.ROUND_HALF_UP), tranTime, Quantity.STATE_0);
                                }else if(order.getPaytype()==Quantity.STATE_1){
                                    buyerCapital1 = createBuyerBackPay(order, backPay.subtract(penaltyPay).setScale(2,BigDecimal.ROUND_HALF_UP), tranTime, Quantity.STATE_1);
                                }else {
                                    buyerCapital1 = createBuyerBackPay(order, backPay.subtract(penaltyPay).setScale(2,BigDecimal.ROUND_HALF_UP), tranTime, Quantity.STATE_2);
                                }
                                salerCapital1 = createSalerBackPay(order, backPay, tranTime);
                                buyerCapitals.add(buyerCapital1);
                                salerCapitals.add(salerCapital1);
                            }
                        }
                    }


                    //异议扣违约金,记录资金明细
                    if (orderProductBack.getAdminstate() == Quantity.STATE_2) {
                        BigDecimal sellerPenaltyPay = penaltyPay.multiply(forwardsalesmargin).setScale(2,BigDecimal.ROUND_HALF_UP);
                        if(sellerPenaltyPay.compareTo(Quantity.BIG_DECIMAL_0)>0){
                            sellerMember.setSellerbanlance(sellerMember.getSellerbanlance().add(sellerPenaltyPay));
                            salerCapital2 = createSalerPenaltyPay(order, sellerPenaltyPay, tranTime, Quantity.STATE_6, orderPay, Quantity.BUYER_BACK_REASON);
                            salerCapitals.add(salerCapital2);
                        }

                        if(penaltyPay.compareTo(Quantity.BIG_DECIMAL_0)>0) {
                            buyerCapital2 = createBuyerPenaltyPay(order, penaltyPay, tranTime, Quantity.STATE_6, orderPay, Quantity.BUYER_BACK_REASON);
                            buyerCapital2.setPaytype(order.getPaytype());
                            buyerCapitals.add(buyerCapital2);
                        }
                    }

                    //如果退货类型为全部退货   将运费加到卖家货款金额里
                    if(orderProductBack.getBacktype() == Quantity.STATE_1){
                        sellerMember.setGoodsbanlance(sellerMember.getGoodsbanlance().add(orderProduct.getActualpayment().subtract(backPay)));
                    }
                }
            }


            //远期全款
            if (orderProduct.getProtype() == Quantity.STATE_1) {

                backPay = Quantity.BIG_DECIMAL_0;
                penaltyPay = Quantity.BIG_DECIMAL_0;

                //计算扣除违约金
                BigDecimal orderPay = orderProduct.getAllpay();

                //退回的金额,异议扣违约金
                if (orderProductBack.getAdminstate() == Quantity.STATE_2) {
                    //违约金
                    penaltyPay = orderPay.multiply(getLiquidated).setScale(2,BigDecimal.ROUND_HALF_UP);
                }

                backPay = orderPay;


                //判断退回到余额还是授信
                BuyerCapital buyerCapital = ordersService.getBuyerCapitalByNoType(orderProduct.getOrderno(), Quantity.STATE_9);
                if (buyerCapital != null) {
                    //退回到余额
                    if (buyerCapital.getPaytype() == Quantity.STATE_3) {
                        buyer.setBalance(buyer.getBalance().add(backPay).subtract(penaltyPay).setScale(2,BigDecimal.ROUND_HALF_UP));
                        buyerCapital1 = createBuyerBackPay(order, backPay.subtract(penaltyPay).setScale(2,BigDecimal.ROUND_HALF_UP), tranTime, Quantity.STATE_3);
                        salerCapital1 = createSalerBackPay(order, backPay, tranTime);
                        buyerCapitals.add(buyerCapital1);
                        salerCapitals.add(salerCapital1);
                    }
                    //退回到授信
                    if (buyerCapital.getPaytype() == Quantity.STATE_4) {
                        buyer.setAvailablelimit(buyer.getAvailablelimit().add(backPay.subtract(penaltyPay)).setScale(2,BigDecimal.ROUND_HALF_UP));
                        buyer.setUsedlimit(buyer.getUsedlimit().subtract(backPay.subtract(penaltyPay)).setScale(2,BigDecimal.ROUND_HALF_UP));
                        buyerCapital1 = createBuyerBackPay(order, backPay.subtract(penaltyPay).setScale(2,BigDecimal.ROUND_HALF_UP), tranTime, Quantity.STATE_4);
                        salerCapital1 = createSalerBackPay(order, backPay, tranTime);
                        buyerCapitals.add(buyerCapital1);
                        salerCapitals.add(salerCapital1);
                    }


                    //退回到支付宝或微信
                    if(buyerCapital.getPaytype()==Quantity.STATE_0||buyerCapital.getPaytype()==Quantity.STATE_1||buyerCapital.getPaytype()==Quantity.STATE_2){
                        String uuid = order.getUuid();
                        Long ordertime = order.getOrdertime();
                        if(uuid!=null){
                            Refund refund = new Refund();
                            refund.setOutTradeNo(uuid);
                            refund.setDatetime(ordertime);
                            if(order.getPaytype()==Quantity.STATE_0){
                                refund.setChannel("alipay");
                            }else if(order.getPaytype()==Quantity.STATE_1){
                                refund.setChannel("wxpay");
                            }else {
                                refund.setChannel("bank");
                            }
                            refund.setRefundAmount((backPay.subtract(penaltyPay).setScale(2,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100))).longValue());
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
                                }else {
                                    result = abcService.refund(refund);
                                }
                            }catch (Exception e){

                            }
                            if(result){
                                if(order.getPaytype()==Quantity.STATE_0){
                                    buyerCapital1 = createBuyerBackPay(order, backPay.subtract(penaltyPay).setScale(2,BigDecimal.ROUND_HALF_UP), tranTime, Quantity.STATE_0);
                                }else if(order.getPaytype()==Quantity.STATE_1){
                                    buyerCapital1 = createBuyerBackPay(order, backPay.subtract(penaltyPay).setScale(2,BigDecimal.ROUND_HALF_UP), tranTime, Quantity.STATE_1);
                                }else {
                                    buyerCapital1 = createBuyerBackPay(order, backPay.subtract(penaltyPay).setScale(2,BigDecimal.ROUND_HALF_UP), tranTime, Quantity.STATE_2);
                                }
                                salerCapital1 = createSalerBackPay(order, backPay, tranTime);
                                buyerCapitals.add(buyerCapital1);
                                salerCapitals.add(salerCapital1);
                            }

                        }
                    }

                    //异议扣违约金,记录资金明细
                    if (orderProductBack.getAdminstate() == Quantity.STATE_2) {
                        BigDecimal sellerPenaltyPay = penaltyPay.multiply(forwardsalesmargin).setScale(2,BigDecimal.ROUND_HALF_UP);
                        if(sellerPenaltyPay.compareTo(Quantity.BIG_DECIMAL_0)>0){
                            sellerMember.setSellerbanlance(sellerMember.getSellerbanlance().add(sellerPenaltyPay));
                            salerCapital2 = createSalerPenaltyPay(order, sellerPenaltyPay, tranTime, Quantity.STATE_6, orderPay, Quantity.BUYER_BACK_REASON);
                            salerCapitals.add(salerCapital2);
                        }

                        if(penaltyPay.compareTo(Quantity.BIG_DECIMAL_0)>0) {
                            buyerCapital2 = createBuyerPenaltyPay(order, penaltyPay, tranTime, Quantity.STATE_6, orderPay, Quantity.BUYER_BACK_REASON);
                            buyerCapital2.setPaytype(order.getPaytype());
                            buyerCapitals.add(buyerCapital2);
                        }
                    }


                    //如果退货类型为全部退货   将运费加到卖家货款金额里
                    if(orderProductBack.getBacktype() == Quantity.STATE_1){
                        sellerMember.setGoodsbanlance(sellerMember.getGoodsbanlance().add(orderProduct.getActualpayment().subtract(backPay)));
                    }
                }
            }


            //远期余款
            if (orderProduct.getProtype() == Quantity.STATE_2) {
                //计算扣除违约金
                BigDecimal orderPay = orderProduct.getActualpayment().subtract(orderProduct.getFreight());


                BigDecimal partPay = orderProduct.getPartpay();
                BigDecimal yuPay = orderProduct.getYupay();


                //预付款违约金
                BigDecimal partPaypenal = Quantity.BIG_DECIMAL_0;
                //余款违约金
                BigDecimal yuPayPenal = Quantity.BIG_DECIMAL_0;

                //定金支付明细
                BuyerCapital depositBuyerCapital = ordersService.getBuyerCapitalByNoType(order.getOrderno(), Quantity.STATE_7);

                if (depositBuyerCapital != null) {

                    //异议扣违约金
                    //退回的金额,异议扣违约金
                    if (orderProductBack.getAdminstate() == Quantity.STATE_2) {
                        //违约金
                        partPaypenal = partPay.multiply(getLiquidated).setScale(2,BigDecimal.ROUND_HALF_UP);
                        yuPayPenal = yuPay.multiply(getLiquidated).setScale(2,BigDecimal.ROUND_HALF_UP);
                    }


                    //买家退款金额
                    BigDecimal buyerBackPay = partPay.subtract(partPaypenal).add(yuPay.subtract(yuPayPenal)).setScale(2,BigDecimal.ROUND_HALF_UP);

                    //卖家退款金额
                    BigDecimal salerBackPay = partPay.add(yuPay).setScale(2,BigDecimal.ROUND_HALF_UP);


                    //退回到余额
                    if (depositBuyerCapital.getPaytype() == Quantity.STATE_3) {
                        buyer.setBalance(buyer.getBalance().add(buyerBackPay));
                        buyerCapital1 = createBuyerBackPay(order, buyerBackPay, tranTime, Quantity.STATE_3);
                        salerCapital1 = createSalerBackPay(order, salerBackPay, tranTime);
                        buyerCapitals.add(buyerCapital1);
                        salerCapitals.add(salerCapital1);
                    }


                    //退回到授信
                    if (depositBuyerCapital.getPaytype() == Quantity.STATE_4) {
                        buyer.setAvailablelimit(buyer.getAvailablelimit().add(buyerBackPay));
                        buyer.setUsedlimit(buyer.getUsedlimit().subtract(buyerBackPay));
                        buyerCapital1 = createBuyerBackPay(order, buyerBackPay, tranTime, Quantity.STATE_4);
                        salerCapital1 = createSalerBackPay(order, salerBackPay, tranTime);
                        buyerCapitals.add(buyerCapital1);
                        salerCapitals.add(salerCapital1);
                    }
                    //退回到支付宝或微信
                    if(depositBuyerCapital.getPaytype()==Quantity.STATE_0||depositBuyerCapital.getPaytype()==Quantity.STATE_1||depositBuyerCapital.getPaytype()==Quantity.STATE_2){
                        String uuid = order.getUuid();
                        String yuuuid = order.getYuuuid();
                        Long ordertime = order.getOrdertime();
                        Long yuordertime = order.getYuordertime();
                        if(uuid!=null&&yuuuid!=null&&ordertime!=null&&yuordertime!=null){
                            //定金
                            Refund refund1 = new Refund();
                            //余款
                            Refund refund2 = new Refund();
                            refund1.setOutTradeNo(uuid);
                            refund1.setDatetime(ordertime);
                            refund2.setOutTradeNo(yuuuid);
                            refund2.setDatetime(yuordertime);

                            if(order.getPaytype()==Quantity.STATE_0){
                                refund1.setChannel("alipay");
                                refund2.setChannel("alipay");
                            }else if(order.getPaytype()==Quantity.STATE_1){
                                refund1.setChannel("wxpay");
                                refund2.setChannel("wxpay");
                            }else {
                                refund1.setChannel("bank");
                                refund2.setChannel("bank");
                            }
                            refund1.setRefundAmount((partPay.subtract(partPaypenal).multiply(new BigDecimal(100))).longValue());
                            refund2.setRefundAmount((yuPay.subtract(yuPayPenal).multiply(new BigDecimal(100))).longValue());

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
                                }else {
                                    result1 = abcService.refund(refund1);
                                    result2 = abcService.refund(refund2);
                                }
                            }catch (Exception e){

                            }
                            if(result1&&result2){
                                if(order.getPaytype()==Quantity.STATE_0){
                                    buyerCapital1 = createBuyerBackPay(order,buyerBackPay , tranTime, Quantity.STATE_0);
                                }else if(order.getPaytype()==Quantity.STATE_1){
                                    buyerCapital1 = createBuyerBackPay(order, buyerBackPay, tranTime, Quantity.STATE_1);
                                }else {
                                    buyerCapital1 = createBuyerBackPay(order, buyerBackPay, tranTime, Quantity.STATE_2);
                                }
                                salerCapital1 = createSalerBackPay(order, salerBackPay, tranTime);
                                buyerCapitals.add(buyerCapital1);
                                salerCapitals.add(salerCapital1);
                            }

                        }
                    }

                    //异议扣违约金,记录资金明细
                    if (orderProductBack.getAdminstate() == Quantity.STATE_2) {
                        penaltyPay = partPaypenal.add(yuPayPenal);
                        BigDecimal sellerPenaltyPay = penaltyPay.multiply(forwardsalesmargin).setScale(2,BigDecimal.ROUND_HALF_UP);
                        if(sellerPenaltyPay.compareTo(Quantity.BIG_DECIMAL_0)>0){
                            sellerMember.setSellerbanlance(sellerMember.getSellerbanlance().add(sellerPenaltyPay));
                            salerCapital2 = createSalerPenaltyPay(order, sellerPenaltyPay, tranTime, Quantity.STATE_6, orderPay, Quantity.BUYER_BACK_REASON);
                            salerCapitals.add(salerCapital2);
                        }

                        if(penaltyPay.compareTo(Quantity.BIG_DECIMAL_0)>0) {
                            buyerCapital2 = createBuyerPenaltyPay(order, penaltyPay, tranTime, Quantity.STATE_6, orderPay, Quantity.BUYER_BACK_REASON);
                            buyerCapital2.setPaytype(order.getPaytype());
                            buyerCapitals.add(buyerCapital2);
                        }
                    }

                    //如果退货类型为全部退货   将运费加到卖家货款金额里
                    if(orderProductBack.getBacktype() == Quantity.STATE_1){
                        sellerMember.setGoodsbanlance(sellerMember.getGoodsbanlance().add(orderProduct.getActualpayment().subtract(partPay).subtract(yuPay)));
                    }
                }
            }


            //保存用户余额和授信
            ordersService.saveMember(buyer,oldBuyer);
            ordersService.saveMember(sellerMember,oldSellerMember);

            if(buyerCapitals.size()>0){

                ordersService.insertBuyerCapital(buyerCapitals);
                //保存操作日志
                OperateLog operateLog = new OperateLog();
                operateLog.setContent("退款成功");

                operateLog.setOpid(operatorId);

                operateLog.setOpname(operatorUsername);

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




    /**
     * 创建买家退款资金明细
     * @param order
     * @param backPay
     * @param tranTime
     * @param type 3=退到余额4=退到授信
     * @return
     */
    private BuyerCapital createBuyerBackPay(Orders order,BigDecimal backPay,Date tranTime,Short type){

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
     * @param order
     * @param penaltyPay
     * @param tranTime
     * @param type 6=买家违约10=卖家违约
     * @return
     */
    private BuyerCapital createBuyerPenaltyPay(Orders order,BigDecimal penaltyPay,Date tranTime,Short type,BigDecimal orderPay,String reason){

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
        if(type==Quantity.STATE_6){
            buyerCapital.setRemark("买家违约金额");
        }else {
            buyerCapital.setRemark("卖家违约金额");
        }
        return buyerCapital;
    }

    /**
     * 创建卖家退款资金明细
     * @param order
     * @param backPay
     * @param tranTime
     * @return
     */
    private SalerCapital createSalerBackPay(Orders order,BigDecimal backPay,Date tranTime){
        SalerCapital salerCapital = new SalerCapital();
        salerCapital.setMemberid(order.getSaleid());
        salerCapital.setTradeno(order.getTransactionnumber());
        salerCapital.setOrderno(order.getOrderno());
        salerCapital.setTradetime(tranTime);
        salerCapital.setRefundamount(backPay);
        salerCapital.setCapitaltype(Quantity.STATE_3);
        salerCapital.setRemark("退款金额");

        //卖家从冻结金额中减去退回金额
//        Member seller = ordersService.getById(order.getSaleid());
//        seller.setSellerfreezebanlance(seller.getSellerfreezebanlance().subtract(backPay));
//        ordersService.saveMember(seller);
          memberService.updateSellerMemberBalanceInDb(order.getSaleid(),Quantity.BIG_DECIMAL_0,backPay.multiply(Quantity.BIG_DECIMAL_MINUS_1));



        return salerCapital;
    }

    /**
     * 创建卖家违约金资金明细
     * @param order
     * @param penaltyPay
     * @param tranTime
     * @param type 6=买家违约7=卖家违约
     * @return
     */
    private SalerCapital createSalerPenaltyPay(Orders order,BigDecimal penaltyPay,Date tranTime,Short type,BigDecimal orderPay,String reason){
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
        if(type==Quantity.STATE_6){
            salerCapital.setRemark("买家违约金额");
        }else {
            salerCapital.setRemark("卖家违约金额");
        }
        return salerCapital;
    }

    @RequestMapping(value = "/updateBillRecord",method = RequestMethod.POST)
    @ApiOperation(value = "更新开票申请记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "开票申请记录id",required = true,paramType = "query",dataType = "long"),
            @ApiImplicitParam(name = "state",value = "开票状态0=待开票1=未收到2=确认",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "expressno",value = "物流单号",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "expresscom",value = "物流公司",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "billtype",value = "开票类型",required = false,paramType = "query",dataType = "string"),
    })
    public BasicRet updateBillRecord(Model model,BillingRecord billingRecord,HttpServletRequest request){
        BasicRet basicRet = new BasicRet();
        ordersService.updateBillRecord(billingRecord);
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("修改成功");
        Admin admin = (Admin) model.asMap().get(AppConstant.ADMIN_SESSION_NAME);
        //保存用户日志
        memberLogOperator.saveMemberLog(null,admin,"更新开票申请记录，开票申请记录id:"+billingRecord.getId(),"/rest/admin/orders/updateBillRecord",request,memberOperateLogService);
        return  basicRet;
    }

    @RequestMapping(value = "/batchUpdateBillRecord",method = RequestMethod.POST)
    @ApiOperation(value = "批量更新开票申请记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids",value = "开票申请记录ids",required = true,paramType = "query",dataType = "long"),
            @ApiImplicitParam(name = "state",value = "开票状态0=待开票1=未收到2=确认",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "expressno",value = "物流单号",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "expresscom",value = "物流公司",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "billtype",value = "开票类型",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "billno",value = "开票号",required = false,paramType = "query",dataType = "string"),
    })
    public BasicRet batchUpdateBillRecord(Model model,HttpServletRequest request,String ids,Short state,String expressno,String expresscom,String billtype,String billno){
        BasicRet basicRet = new BasicRet();
        List<BillingRecord> list = ordersService.getBillRecordList(ids);
        for(BillingRecord billingRecord:list){
            billingRecord.setExpressno(expressno);
            billingRecord.setExpresscom(expresscom);
            billingRecord.setState(state);
            billingRecord.setBilltype(billtype);
            billingRecord.setBillno(billno);
            ordersService.updateBillRecord(billingRecord);
        }
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("修改成功");
        Admin admin = (Admin) model.asMap().get(AppConstant.ADMIN_SESSION_NAME);
        //保存用户日志
        memberLogOperator.saveMemberLog(null,admin,"批量更新开票申请记录，开票申请记录id:"+ids,"/rest/admin/orders/batchUpdateBillRecord",request,memberOperateLogService);
        return  basicRet;
    }

    /**
     * 获取开票列表
     * @param model
     * @param billQueryParam
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/getBillRecordList",method = RequestMethod.POST)
    @ApiOperation(value = "获取开票列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo",value = "页码",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "pageSize",value = "页数",required = true,paramType = "query",dataType = "int"),
    })
    public PageRet getBillRecordList(Model model,BillQueryParam billQueryParam,int pageNo,int pageSize){
        PageRet pageRet = new PageRet();
        pageRet.setMessage("返回成功");
        pageRet.setResult(BasicRet.SUCCESS);
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        if(member!=null){
            billQueryParam.setMemberid(member.getId());
            pageRet.data.setPageInfo(ordersService.getBillRecordList(billQueryParam,pageNo,pageSize));
            return pageRet;
        }else{
            pageRet.data.setPageInfo(ordersService.getBillRecordList(billQueryParam,pageNo,pageSize));
            return  pageRet;
        }
    }



    @RequestMapping(value = "/loadSellerBillRecordList",method = RequestMethod.POST)
    @ApiOperation("获取卖家开票列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startTime",value = "开始时间",required = false,paramType = "query"  ,dataType = "string"),
            @ApiImplicitParam(name = "endTime",value = "结束时间",required = false,paramType = "query"  ,dataType = "string"),
            @ApiImplicitParam(name = "billno",value = "发票号",required = false,paramType = "query"  ,dataType = "string"),
            @ApiImplicitParam(name = "sellerid",value = "卖家编号",required = false,paramType = "query"  ,dataType = "string"),
            @ApiImplicitParam(name = "sellername",value = "卖家名称",required = false,paramType = "query"  ,dataType = "string"),
            @ApiImplicitParam(name = "diliveryno",value = "物流单号",required = false,paramType = "query"  ,dataType = "string"),
    })
    public PageRet loadSellerBillRecordList(Model model,int pageNo,int pageSize,SellerBillRecordQueryParam param){
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        PageRet pageRet = new PageRet();
        PageInfo pageInfo = ordersService.getSellerBillRecord(pageNo,pageSize,param,member);
        pageRet.data.setPageInfo(pageInfo);
        pageRet.setResult(BasicRet.SUCCESS);
        return  pageRet;
    }

    @RequestMapping(value = "/updateSellerBillRecord",method = RequestMethod.POST)
    @ApiOperation("修改卖家开票")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "开票申请id",required = false,paramType = "query"  ,dataType = "long"),
            @ApiImplicitParam(name = "state",value = "状态0=未确认1=确认",required = false,paramType = "query"  ,dataType = "int"),

    })
    public BasicRet updateSellerBillRecord(Model model,SellerBillRecord sellerBillRecord,HttpServletRequest request){
        BasicRet basicRet = new BasicRet();
        ordersService.updateSellerBillRecord(sellerBillRecord);
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("修改成功");
        Admin admin = (Admin)model.asMap().get(AppConstant.ADMIN_SESSION_NAME);
        //保存用户日志
        memberLogOperator.saveMemberLog(null,admin,"修改卖家开票，开票申请记录id:"+sellerBillRecord.getId(),"/rest/admin/orders/updateSellerBillRecord",request,memberOperateLogService);
        return  basicRet;
    }

    @RequestMapping(value = "/getOrdersByBillRecordId",method = RequestMethod.POST)
    @ApiOperation(value = "根据记录id获取开票信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "开票申请记录id",required = true,paramType = "query",dataType = "long"),
    })
    public OrderCarRet getOrdersByBillRecordId(Long id){
        OrderCarRet orderCarRet = new OrderCarRet();
        orderCarRet.setResult(BasicRet.SUCCESS);
        orderCarRet.setMessage("返回成功");
        BillingRecord billingRecord = ordersService.getBillRecordByID(id);
        BillRecordComplex billRecordComplex = new BillRecordComplex();
        if(billingRecord!=null){
            if(billingRecord.getBillno()!=null){
                billRecordComplex.setBillNo(billingRecord.getBillno());
            }
           if(billingRecord.getBilltime()!=null){
               billRecordComplex.setBillTime(billingRecord.getCreatetime());
           }
           if(billingRecord.getBilltype()!=null){
               billRecordComplex.setBillType(billingRecord.getBilltype());
           }
           if(billingRecord.getExpressno()!=null){
               billRecordComplex.setExpressNo(billingRecord.getExpressno());
           }
           if(billingRecord.getBillingrecordtype()!=null){
               billRecordComplex.setBillingrecordtype(billingRecord.getBillingrecordtype());
           }
           if(billingRecord.getExpresscom()!=null){
                billRecordComplex.setExpressCompany(billingRecord.getExpresscom());
           }
            billRecordComplex.setMemberId(billingRecord.getMemberid());
            billRecordComplex.setMemberName(billingRecord.getMembername());

            billRecordComplex.setAddress(billingRecord.getAddress());
            billRecordComplex.setReceiveaddress(billingRecord.getReceiveaddress());


            String[] ordernoArray = billingRecord.getOrderno().split(",");
            List<Orders> list = new ArrayList<Orders>();
            for (String orderid : ordernoArray) {
                Orders orders = ordersService.getOrdersById(Long.parseLong(orderid));



                //查询是否有退货或退款的，如果有退货开票金额要减去退货的钱
                List<OrderProductBack> orderProductBackList =  orderProductBackService.getByOrderNo(orders.getOrderno());
                BigDecimal subApply =  new BigDecimal(0);
                for(OrderProductBack opb : orderProductBackList){
                    if(opb.getState() == 10) {
                        subApply = subApply.add(opb.getBackmoney());
                    }
                }

                if(subApply.compareTo(new BigDecimal(0)) >0){
                    orders.setTotalprice(orders.getTotalprice().subtract(subApply));
                }



                if (orders != null) {
                    list.add(orders);
                }
            }
            billRecordComplex.setList(list);
        }
        orderCarRet.data.billRecordComplex = billRecordComplex;
        return orderCarRet;
    }

    @RequestMapping(value = "/getOrdersByBillRecordIds",method = RequestMethod.POST)
    @ApiOperation(value = "根据记录ids获取开票信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids",value = "批量开票申请记录id集合",required = true,paramType = "query",dataType = "string"),
    })
    public OrderCarRet getOrdersByBillRecordIds(String ids){
        OrderCarRet orderCarRet = new OrderCarRet();
        orderCarRet.setResult(BasicRet.SUCCESS);
        orderCarRet.setMessage("返回成功");
        List<BillingRecord> billingRecords = ordersService.getBillRecordList(ids);
        List<BillRecordComplex> listComplex = new ArrayList<>();
        for(BillingRecord billingRecord:billingRecords){
            BillRecordComplex billRecordComplex = new BillRecordComplex();

            if(billingRecord.getBillno()!=null){
                billRecordComplex.setBillNo(billingRecord.getBillno());
            }
            if(billingRecord.getBilltime()!=null){
                billRecordComplex.setBillTime(billingRecord.getCreatetime());
            }
            if(billingRecord.getBilltype()!=null){
                billRecordComplex.setBillType(billingRecord.getBilltype());
            }
            if(billingRecord.getExpressno()!=null){
                billRecordComplex.setExpressNo(billingRecord.getExpressno());
            }
            if(billingRecord.getBillingrecordtype()!=null){
                billRecordComplex.setBillingrecordtype(billingRecord.getBillingrecordtype());
            }
            if(billingRecord.getExpresscom()!=null){
                billRecordComplex.setExpressCompany(billingRecord.getExpresscom());
            }
            billRecordComplex.setMemberId(billingRecord.getMemberid());
            billRecordComplex.setMemberName(billingRecord.getMembername());
            String[] ordernoArray = billingRecord.getOrderno().split(",");
            List<Orders> list = new ArrayList<Orders>();
            for(String orderid : ordernoArray){
                Orders orders =  ordersService.getOrdersById(Long.parseLong(orderid));
                if(orders!=null){
                    list.add(orders);
                }

            }
            billRecordComplex.setList(list);
            listComplex.add(billRecordComplex);
        }

        orderCarRet.data.listComplex = listComplex;
        return orderCarRet;
    }


    @RequestMapping(value = "/getProductEvaList",method = RequestMethod.POST)
    @ApiOperation(value = "获取评价列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo",value = "页码",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "pageSize",value = "页数",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "startTime",value = "开始时间",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "endTime",value = "结束时间",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "orderNo",value = "订单编号",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "sellerName",value = "所属商家",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "memberName",value = "评价人",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "pdName",value = "商品名称",required = false,paramType = "query",dataType = "string"),

    })
    public PageRet getProductEvaList(OrderQueryParam param){
        PageRet orderCarRet = new PageRet();

        PageInfo pageInfo = ordersService.getOrderProductEva(param);

        orderCarRet.data.setPageInfo(pageInfo);

        orderCarRet.setMessage("返回成功");
        orderCarRet.setResult(BasicRet.SUCCESS);
        return orderCarRet;
    }

    @RequestMapping(value = "/deleteProductEva",method = RequestMethod.POST)
    @ApiOperation(value = "删除评价")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderProductId",value = "评价id",required = true,paramType = "query",dataType = "long"),
    })
    public BasicRet deleteProductEva(Model model,Long orderProductId,HttpServletRequest request){
        BasicRet orderCarRet = new BasicRet();
        ordersService.deleteMemberEva(orderProductId);
        orderCarRet.setMessage("删除成功");
        orderCarRet.setResult(BasicRet.SUCCESS);
        Admin admin = (Admin)model.asMap().get(AppConstant.ADMIN_SESSION_NAME);
        //保存用户日志
        memberLogOperator.saveMemberLog(null,admin,"删除评价,id为："+orderProductId,"/rest/admin/orders/deleteProductEva",request,memberOperateLogService);
        return orderCarRet;
    }


    @RequestMapping(value = "/getOrderOperateLog", method = RequestMethod.POST)
    @ApiOperation(value = "获取操作日志")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderid", value = "订单id", required = true, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "type", value = "操作类型0=订单操作1=退货操作", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "orderpdid", value = "订单商品id", required = false, paramType = "query", dataType = "long"),
    })
    public OrderCarRet getOrderOperateLog(Long orderid, Short type,Long orderpdid) {
        OrderCarRet orderCarRet = new OrderCarRet();

        List<OperateLog> list = ordersService.getOperatelog(orderid, type,orderpdid);


        List<OperateLog> olList = new ArrayList<>();

        List<OperateLog> operateLogfinalList =new ArrayList<>();

        for(OperateLog ol : list){
           if(!ol.getContent().equals("订单已收货") && !ol.getContent().equals("订单已验货")){
               olList.add(ol);
            }else {
               operateLogfinalList.add(ol);
           }
        }


        Orders orders = ordersService.getOrdersById(orderid);
        if(type==0 && orders.getOrderstatus() != 0){

            try {
                OrderStoreStateLog orderStoreStateLog = orderStoreStateLogService.getStoreState(orders);

                if(orderStoreStateLog != null){
                        Collections.reverse(orderStoreStateLog.getList());
                        orderStoreStateLog.getList().forEach(storeState -> {
                        OperateLog operateLog = new OperateLog();
                        operateLog.setOpname(storeState.getOperateUser());
                        operateLog.setOrderid(orderStoreStateLog.getOrderid());
                        operateLog.setContent(storeState.getPointName());
                        operateLog.setOptime(DateUtils.StrToDate(storeState.getCreateTime()));
                        olList.add(operateLog);
                    });
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        olList.addAll(operateLogfinalList);

        orderCarRet.data.operateLogs = olList;
        orderCarRet.setMessage("返回成功");
        orderCarRet.setResult(BasicRet.SUCCESS);
        return orderCarRet;
    }




    @RequestMapping(value = "/saveBillType",method = RequestMethod.POST)
    @ApiOperation(value = "保存发票内容")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name",value = "发票内容",required = true,paramType = "query",dataType = "string"),
    })
    public BasicRet saveBillType(Model model,BillType billType,HttpServletRequest request){
        BasicRet basicRet = new BasicRet();

        ordersService.saveBillType(billType);

        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("保存成功");

        Admin admin = (Admin)model.asMap().get(AppConstant.ADMIN_SESSION_NAME);
        //保存用户日志
        memberLogOperator.saveMemberLog(null,admin,"保存发票内容","/rest/admin/orders/saveBillType",request,memberOperateLogService);

        return basicRet;
    }

    @RequestMapping(value = "/getBillTypeList",method = RequestMethod.POST)
    @ApiOperation(value = "获取发票类型")
    public OrderCarRet getBillTypeList(){
        OrderCarRet orderCarRet = new OrderCarRet();
        List<BillType> list = ordersService.getBillTypeList();
        orderCarRet.data.billTypes = list;
        orderCarRet.setResult(BasicRet.SUCCESS);
        orderCarRet.setMessage("返回成功");
        return orderCarRet;
    }

    @RequestMapping(value = "/deleteBillType",method = RequestMethod.POST)
    @ApiOperation(value = "删除发票类型")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "id",required = true,paramType = "query",dataType = "int"),
    })
    public BasicRet deleteBillType(Model model,Integer id,HttpServletRequest request){
        BasicRet basicRet = new BasicRet();
        ordersService.deleteBillTyep(id);
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("删除成功");
        Admin admin = (Admin)model.asMap().get(AppConstant.ADMIN_SESSION_NAME);
        //保存用户日志
        memberLogOperator.saveMemberLog(null,admin,"删除发票内容","/rest/admin/orders/deleteBillType",request,memberOperateLogService);
        return basicRet;
    }

    /**
     * 保存操作日志
     * @param memberOperateLog
     * @return
     */
    @RequestMapping(value = "/saveMemberLog",method = RequestMethod.POST)
    @ApiOperation(value = "保存操作日志")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ipaddress",value = "ip地址",required = true,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "content",value = "操作内容",required = true,paramType = "query",dataType = "string"),
    })
    public BasicRet saveMemberLog(Model model,MemberOperateLog memberOperateLog){
        BasicRet basicRet = new BasicRet();
        Admin member = (Admin) model.asMap().get(AppConstant.ADMIN_SESSION_NAME);
        memberOperateLog.setMemberid(member.getId());
        memberOperateLog.setMembername(member.getUsername());
        memberOperateLog.setCreatetime(new Date());
        memberOperateLogService.saveMemberOperateLog(memberOperateLog);
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("保存成功");
        return basicRet;
    }

    /**
     * 获取当前订单总的销售额
     * @return
     */
    @RequestMapping(value = "/getCurrentOrdersSumPay",method = RequestMethod.POST)
    @ApiOperation(value = "获取当前订单总的销售额")
    public OrderCarRet getCurrentOrdersSumPay(){
        OrderCarRet orderCarRet = new OrderCarRet();
        orderCarRet.data.bigDecimal =  ordersService.getCurrentOrdersSumPay();
        orderCarRet.setMessage("返回成功");
        orderCarRet.setResult(BasicRet.SUCCESS);
        return orderCarRet;
    }




    /*    public static void main(String[] args) {

        Gson gson = new Gson();
        OrderProduct op = new OrderProduct();
        op.setStoreid(1l);
        op.setStorename("杭州");
        op.setNum(13);
        op.setPddesc("奥展无/2千/盒|6盒/箱");
        op.setPdid(1l);
        op.setPdno("JS000000000000");
        op.setPdname("奥展外六角螺栓／不锈钢－304/螺钉GB819/M2*10/本色");
        op.setPrice(new BigDecimal(23));
        op.setDeliverytime("立即发货");
        op.setMailornot(false);
        op.setUnit("千支");
        op.setStandard("M2*10");
        op.setMark("印记");
        op.setBrand("奥展");
        op.setSellerid(1l);
        op.setFreight(new BigDecimal(30));
        op.setIsmailornot((short)0);

        OrderProduct op1 = new OrderProduct();
        op1.setStoreid(2l);
        op1.setStorename("宁波");
        op1.setNum(15);
        op1.setPddesc("紧商无/5千/盒|10盒/箱");
        op1.setPdid(1l);
        op1.setPdno("JS111111111111");
        op1.setPdname("线材／不锈钢－204/螺钉GB819/M2*10/本色");
        op1.setPrice(new BigDecimal(56));
        op1.setDeliverytime("三天后发货");
        op1.setMailornot(true);
        op1.setUnit("千克");
        op1.setStandard("M2*10");
        op1.setMark("印记");
        op1.setBrand("奥展");
        op1.setSellerid(2l);
        op1.setFreight(new BigDecimal(20));
        op1.setIsmailornot((short)0);

        List<OrderProduct> list = new ArrayList<OrderProduct>();
        list.add(op);
        list.add(op1);

        String str = gson.toJson(list);

        System.out.println(str);

        List<OrderProduct> list2 = gson.fromJson(str,new TypeToken<ArrayList<OrderProduct>>() {}.getType());


        System.out.println(str);

    }*/
}
