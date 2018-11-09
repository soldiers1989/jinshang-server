package project.jinshang.mod_product;

import com.alipay.api.AlipayApiException;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import mizuki.project.core.restserver.config.BasicRet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.jinshang.common.bean.MemberLogOperator;
import project.jinshang.common.bean.PageRet;
import project.jinshang.common.constant.AgentDeliveryAddressConst;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.common.constant.Quantity;
import project.jinshang.common.constant.SellerAuthorityConst;
import project.jinshang.common.exception.CashException;
import project.jinshang.common.exception.MyException;
import project.jinshang.common.utils.*;
import project.jinshang.mod_activity.bean.LimitTimeProd;
import project.jinshang.mod_activity.bean.LimitTimeStore;
import project.jinshang.mod_activity.service.LimitTimeStoreService;
import project.jinshang.mod_admin.mod_commondata.service.CommonDataValueService;
import project.jinshang.mod_admin.mod_returnreason.bean.ReturnReason;
import project.jinshang.mod_admin.mod_returnreason.service.ReturnReasonService;
import project.jinshang.mod_admin.mod_statement.bean.BuyerStatement;
import project.jinshang.mod_admin.mod_statement.bean.BuyerStatementExample;
import project.jinshang.mod_admin.mod_statement.service.StatementService;
import project.jinshang.mod_admin.mod_transet.bean.TransactionSetting;
import project.jinshang.mod_admin.mod_transet.service.TransactionSettingService;
import project.jinshang.mod_cash.bean.BuyerCapital;
import project.jinshang.mod_cash.bean.SalerCapital;
import project.jinshang.mod_cash.bean.dto.BuyerCapitalViewDto;
import project.jinshang.mod_cash.service.BuyerCapitalService;
import project.jinshang.mod_cash.service.SalerCapitalService;
import project.jinshang.mod_common.bean.SmsLog;
import project.jinshang.mod_common.bean.SmsTemplate;
import project.jinshang.mod_company.bean.BuyerCompanyInfo;
import project.jinshang.mod_company.bean.SellerCompanyInfo;
import project.jinshang.mod_company.service.AgentDeliveryAddressService;
import project.jinshang.mod_company.service.BuyerCompanyService;
import project.jinshang.mod_member.bean.Member;
import project.jinshang.mod_member.service.AdminService;
import project.jinshang.mod_member.service.AdminUserService;
import project.jinshang.mod_member.service.MemberRateSettingService;
import project.jinshang.mod_member.service.MemberService;
import project.jinshang.mod_pay.bean.Refund;
import project.jinshang.mod_pay.mod_alipay.AlipayService;
import project.jinshang.mod_pay.mod_bankpay.AbcService;
import project.jinshang.mod_pay.mod_wxpay.MyWxPayService;
import project.jinshang.mod_pay.service.PayLogsService;
import project.jinshang.mod_pay.service.TradeService;
import project.jinshang.mod_product.bean.*;
import project.jinshang.mod_product.bean.dto.LogisticsInfoDto;
import project.jinshang.mod_product.bean.dto.OrderFrightDto;
import project.jinshang.mod_product.bean.dto.OrdersRet;
import project.jinshang.mod_product.service.*;
import project.jinshang.mod_shippingaddress.bean.ShippingAddress;
import project.jinshang.mod_shippingaddress.service.ShippingAddressService;
import project.jinshang.mod_wms_middleware.WMSService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Created by Administrator on 2017/11/9.
 */
@RestController
@RequestMapping("/rest/seller/orders")
@SessionAttributes({AppConstant.MEMBER_SESSION_NAME})
@Api(tags = "卖家订单模块", description = "卖家订单模块")
@Transactional(rollbackFor = Exception.class)
public class SellerOrdersAction {

    private Logger logger = LoggerFactory.getLogger(this.getClass());


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
    private PdbailLogService pdbailLogService;

    @Autowired
    private MemberOperateLogService memberOperateLogService;

    @Autowired
    private MyWxPayService wxPayService;
    @Autowired
    private AlipayService alipayService;
    @Autowired
    private AbcService abcService;

    @Autowired
    private CommonDataValueService commonDataValueService;

    @Autowired
    private AgentLogisticsService agentLogisticsService;

    @Autowired
    private WMSService wmsService;

    @Autowired
    private ProductStoreService productStoreService;


    @Autowired
    private BuyerCompanyService buyerCompanyService;

    @Autowired
    private  OrderProductLogService orderProductLogService;

    @Autowired
    private LimitTimeStoreService limitTimeStoreService;

    @Autowired
    private TradeService tradeService;
    @Autowired
    private PayLogsService payLogsService;
    @Autowired
    private OrderProductServices orderProductServices;



    @Autowired
    private  AgentDeliveryAddressService agentDeliveryAddressService;

    @Autowired
    private OrderProductBackInfoService orderProductBackInfoService;
    //2018年6月1日14:09:57
    //添加业务员信息
    @Autowired
    private AdminUserService adminUserService;
    //添加业务员信息
    @Autowired
    private AdminService adminService;

    @Autowired
    private OrderProductBackService orderProductBackService;

    @Autowired
    private ReturnReasonService returnReasonService;

    @Autowired
    private LogisticsInfoService logisticsInfoService;
    @Autowired
    private StatementService statementService;


    //远期全款打折率
    private static final BigDecimal allPayRate = new BigDecimal(0.99);

    @Value("${spring.profiles.active}")
    private String profile;

    MemberLogOperator memberLogOperator = new MemberLogOperator();

    private class OrderCarRet extends BasicRet {
        private class Data {
            private BuyerCenterModel buyerCenterModel;
            private SellerBillRecord sellerBillRecord;
            private List<Orders> ordersList;

            List<TransactionSetting> transactionSettings;

            List<LogisticsInfoDto> logisticsInfos;


            private OrderProductBack orderProductBack;

            private BigDecimal penalty;

            private BigDecimal price;

            private List<ShippingAddress> shippingAddresses;

            private BigDecimal bigDecimal;

            private Orders orders;

            private List<OrderProduct> orderProducts;

            private OrderProduct orderProduct;

            private BillingRecord billingRecord;

            private BillRecordComplex billRecordComplex;

            private Map<String, BigDecimal> map;

            List<OperateLog> operateLogs;


            private String expressurl;

            public String getExpressurl() {
                return expressurl;
            }

            public void setExpressurl(String expressurl) {
                this.expressurl = expressurl;
            }

            public BuyerCenterModel getBuyerCenterModel() {
                return buyerCenterModel;
            }

            public void setBuyerCenterModel(BuyerCenterModel buyerCenterModel) {
                this.buyerCenterModel = buyerCenterModel;
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

            public List<TransactionSetting> getTransactionSettings() {
                return transactionSettings;
            }

            public void setTransactionSettings(List<TransactionSetting> transactionSettings) {
                this.transactionSettings = transactionSettings;
            }

            public List<LogisticsInfoDto> getLogisticsInfos() {
                return logisticsInfos;
            }

            public void setLogisticsInfos(List<LogisticsInfoDto> logisticsInfos) {
                this.logisticsInfos = logisticsInfos;
            }

            public BigDecimal getPenalty() {
                return penalty;
            }

            public void setPenalty(BigDecimal penalty) {
                this.penalty = penalty;
            }

            public BigDecimal getPrice() {
                return price;
            }

            public void setPrice(BigDecimal price) {
                this.price = price;
            }
        }

        private Data data = new Data();

        public Data getData() {
            return data;
        }

        public void setData(Data data) {
            this.data = data;
        }
    }


    @RequestMapping(value = "/getMemberOrderList", method = RequestMethod.POST)
    @ApiOperation(value = "卖家获取订单列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "memberName",value = "买家名称",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "pdName", value = "商品名称", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "orderNo", value = "订单号", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "code",value = "合同号",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "tranNo",value = "交易号",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "startTime", value = "开始时间", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "orderState", value = "订单状态0=待付款1=待发货3=待收货4=待验货5=已完成7=已关闭8=备货中9=备货完成10部分发货", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "evaState", value = "评价状态0=未评价1=已评价", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "backstate", value = "退货状态0=正常1=退货中2=退货验收3=退货完成4=异议中", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "brand", value = "品牌", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "stand", value = "规格", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "mark", value = "印记", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "sendstatus", value = "发货状态 全部订单为不传 1为待发货（查询待发货和部分发货订单）3为待收货订单(查待收货和部分发货) 10为部分发货订单(只查10部分发货)", required = false, paramType = "query", dataType = "long"),
    })
    @PreAuthorize("hasAuthority('" + SellerAuthorityConst.ORDER + "') || hasAuthority('" + SellerAuthorityConst.ALL + "')")
    public PageRet getMemberOrderList(Model model, OrderQueryParam param) {
        PageRet pageRet = new PageRet();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        param.setSellerid(member.getId());

        PageInfo pageInfo = ordersService.getMemberOrdersListForUser(param);
        List<Orders> list = pageInfo.getList();

        //组装订单
        List<MemberOrders> memberOrderses = new ArrayList<MemberOrders>();

        for (Orders orders : list) {
            List<OrderProduct> orderProducts = ordersService.getOrderProductByOrderNo(orders.getOrderno(), param);
            MemberOrders memberOrders = new MemberOrders();
            memberOrders.setOrderno(orders.getOrderno());
            memberOrders.setOrderid(orders.getId());
            memberOrders.setCode(orders.getCode());
            memberOrders.setCreatetime(orders.getCreatetime());
            memberOrders.setSellercompany(orders.getMembercompany());
            memberOrders.setOrderstate(orders.getOrderstatus());
            memberOrders.setTotalprice(orders.getTotalprice());
            memberOrders.setBrokepay(orders.getBrokepay());
            //实付金额
//            BigDecimal actualpayment = new BigDecimal(0);
//            for (OrderProduct orderProduct : orderProducts) {
//                actualpayment = actualpayment.add(orderProduct.getActualpayment());
//            }
            memberOrders.setActualpayment(orders.getActualpayment());
            memberOrders.setReceiver(orders.getShipto());
            memberOrders.setReceiverPhone(orders.getPhone());
            //orders.totalprice,orders.freight,orders.deposit,orders.balance,orders.futuretime,orders.allpay,orders.ordertype
            memberOrders.setFreight(orders.getFreight());
            memberOrders.setDeposit(orders.getDeposit());
            memberOrders.setBalance(orders.getBalance());
            if (orders.getFuturetime() != null) {
                memberOrders.setFuturetime(orders.getFuturetime());
            }
            memberOrders.setAllpay(orders.getAllpay());
            memberOrders.setOrdertype(orders.getOrdertype());
            memberOrders.setOrderProducts(orderProducts);
            memberOrders.setDiscountprice(orders.getDiscountprice());
            memberOrderses.add(memberOrders);
        }
        pageInfo.setList(memberOrderses);
        pageRet.data.setPageInfo(pageInfo);
        pageRet.setResult(BasicRet.SUCCESS);
        pageRet.setMessage("返回成功");
        return pageRet;
    }

    @RequestMapping(value = "/getMemberFutureOrderList", method = RequestMethod.POST)
    @ApiOperation(value = "卖家获取远期订单列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "memberName",value = "买家名称",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "pdName", value = "商品名称", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "orderNo", value = "订单号", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "code",value = "合同号",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "tranNo",value = "交易号",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "startTime", value = "开始时间", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "orderState", value = "订单状态0=待付款1=待发货3=待收货4=待验货5=已完成7=已关闭8=备货中9=备货完成", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "evaState", value = "评价状态0=未评价1=已评价", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "backstate", value = "退货状态0=正常1=退货中2=退货验收3=退货完成4=异议中", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "brand", value = "品牌", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "stand", value = "规格", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "mark", value = "印记", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "presellconfim", value = "卖家确认远期预售：0=卖家未确认该远期订单，1=卖家已确认接收该远期订单，2=卖家已确认不接收该远期订单", required = false, paramType = "query", dataType = "int",defaultValue = "-1"),
            @ApiImplicitParam(name = "prestocktimeStart", value = "卖家预计备货完成时间-开始", required = false, paramType = "query", dataType = "date"),
            @ApiImplicitParam(name = "prestocktimeEnd", value = "卖家预计备货完成时间-结束", required = false, paramType = "query", dataType = "date"),
    })
    @PreAuthorize("hasAuthority('" + SellerAuthorityConst.ORDER + "') || hasAuthority('" + SellerAuthorityConst.ALL + "')")
    public PageRet getMemberFutureOrderList(Model model, OrderQueryParam param) {
        PageRet pageRet = new PageRet();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        param.setSellerid(member.getId());

        PageInfo pageInfo = ordersService.getMemberOrdersListForUser(param);
        List<Orders> list = pageInfo.getList();

        //组装订单
        List<MemberOrders> memberOrderses = new ArrayList<MemberOrders>();

        for (Orders orders : list) {
            List<OrderProduct> orderProducts = ordersService.getOrderProductByOrderNo(orders.getOrderno(), param);
            MemberOrders memberOrders = new MemberOrders();
            memberOrders.setOrderno(orders.getOrderno());
            memberOrders.setOrderid(orders.getId());
            memberOrders.setCode(orders.getCode());
            memberOrders.setCreatetime(orders.getCreatetime());
            memberOrders.setSellercompany(orders.getMembercompany());
            memberOrders.setOrderstate(orders.getOrderstatus());
            memberOrders.setTotalprice(orders.getTotalprice());
            memberOrders.setBrokepay(orders.getBrokepay());
            //实付金额
            BigDecimal actualpayment = new BigDecimal(0);
            for (OrderProduct orderProduct : orderProducts) {
                actualpayment = actualpayment.add(orderProduct.getActualpayment());
            }
            memberOrders.setActualpayment(actualpayment);
            memberOrders.setReceiver(orders.getShipto());
            memberOrders.setReceiverPhone(orders.getPhone());
            //orders.totalprice,orders.freight,orders.deposit,orders.balance,orders.futuretime,orders.allpay,orders.ordertype
            memberOrders.setFreight(orders.getFreight());
            memberOrders.setDeposit(orders.getDeposit());
            memberOrders.setBalance(orders.getBalance());
            if (orders.getFuturetime() != null) {
                memberOrders.setFuturetime(orders.getFuturetime());
            }
            memberOrders.setAllpay(orders.getAllpay());
            memberOrders.setOrdertype(orders.getOrdertype());
            memberOrders.setOrderProducts(orderProducts);
            memberOrders.setPresellconfim(orders.getPresellconfim());
            memberOrders.setPrestocktime(orders.getPrestocktime());
            memberOrderses.add(memberOrders);

        }
        pageInfo.setList(memberOrderses);
        pageRet.data.setPageInfo(pageInfo);
        pageRet.setResult(BasicRet.SUCCESS);
        pageRet.setMessage("返回成功");
        return pageRet;
    }

    /**
     *远期订单商家确认
     */
    @RequestMapping(value = "/futureOrdersOfSellerConfirm",method = RequestMethod.POST)
    @ApiOperation(value = "远期订单商家确认")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderno", value = "订单编号", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "presellconfim", value = "卖家确认远期预售：0=卖家未确认该远期订单，1=卖家已确认接收该远期订单，2=卖家已确认不接收该远期订单", required = true, paramType = "query", dataType = "int"),
    })
    public BasicRet futureOrdersOfSellerConfirm(Model model, String orderno, int presellconfim, HttpServletRequest request) throws AlipayApiException, WxPayException, CashException, InvocationTargetException, IllegalAccessException {
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        BasicRet basicRet = new BasicRet();
        Orders orders = ordersService.getOrdersByOrderNo(orderno);
        if (orders != null){
            Orders updateOrder = new Orders();
            updateOrder.setId(orders.getId());
            updateOrder.setOrderno(orderno);
            updateOrder.setActualpayment(orders.getActualpayment());
            updateOrder.setForwardnoticephone(orders.getForwardnoticephone());
            updateOrder.setBuyerRealname(orders.getBuyerRealname());
            updateOrder.setPresellconfim((short)presellconfim);
            updateOrder.setCreatetime(orders.getCreatetime());
            updateOrder.setFuturetime(orders.getFuturetime());
            updateOrder.setOrdertype(orders.getOrdertype());

            if (presellconfim == Quantity.STATE_0){
                //买家在卖家未确认该远期订单时取消订单
                updateOrder.setOrderstatus(Quantity.STATE_7);
                ordersService.updateOrders(updateOrder);
                ordersService.cancelOrdersBuyer(orderno, member, request);
                basicRet.setMessage("您已取消订单！");
                basicRet.setResult(BasicRet.ERR);
                return basicRet;
            }else if (presellconfim == Quantity.STATE_1){
                //卖家已确认接收该远期订单
                updateOrder.setOrderstatus(Quantity.STATE_8);
                ordersService.updateOrders(updateOrder);
                basicRet.setMessage("商家已接单，商品正在备货中！");
                basicRet.setResult(BasicRet.SUCCESS);
                return basicRet;
            }else if (presellconfim == Quantity.STATE_2){
                //卖家已确认不接收该远期订单
                updateOrder.setOrderstatus(Quantity.STATE_7);
                ordersService.updateOrders(updateOrder);
                ordersService.notConfirmFutureOrders(orderno,member,request);
                basicRet.setMessage("商家拒绝接单，订单已关闭！");
                basicRet.setResult(BasicRet.ERR);
                return basicRet;
            }
        }
        return basicRet;
    }

    /**
     * 远期订单备货情况提醒
     */
    @RequestMapping(value = "/futureOrderPrepareRemind",method = RequestMethod.POST)
    @ApiOperation(value = "远期订单备货情况提醒")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderno", value = "订单编号", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "forwardnoticephone", value = "卖家远期订单手机号提醒", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "prestocktime", value = "卖家预计备货完成时间", required = true, paramType = "query", dataType = "date"),
    })
    public BasicRet futureOrderPrepareRemind(Model model, String orderno, String forwardnoticephone, Date prestocktime, HttpServletRequest request){
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        BasicRet basicRet = new BasicRet();
        Orders futureOrders = ordersService.getOrdersByOrderNo(orderno);
        if (futureOrders != null){
            Orders updateFutureOrder = new Orders();
            updateFutureOrder.setId(futureOrders.getId());
            updateFutureOrder.setOrderno(orderno);
            if (prestocktime.after(new Date()) && prestocktime.before(futureOrders.getFuturetime())){
                updateFutureOrder.setPrestocktime(prestocktime);
            }else{
                basicRet.setResult(BasicRet.ERR);
                basicRet.setMessage("您输入的日期不合法，请重新输入！");
                return basicRet;
            }
            if (CommonUtils.isMobile(forwardnoticephone)){
                updateFutureOrder.setForwardnoticephone(forwardnoticephone);
            }else{
                basicRet.setResult(BasicRet.ERR);
                basicRet.setMessage("您输入的手机号有误，请重新输入！");
                return basicRet;
            }
            ordersService.updateOrders(updateFutureOrder);
            basicRet.setResult(BasicRet.SUCCESS);
            basicRet.setMessage("返回成功");
        }else{
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("该条订单不存在");
        }
        return basicRet;
    }

    /**
     * 远期订单备货完成
     */
    @RequestMapping(value = "futureOrderPrepareFinish", method = RequestMethod.POST)
    @ApiOperation(value = "远期订单备货完成")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderno", value = "订单编号", required = true, paramType = "query", dataType = "string"),
    })
    public BasicRet futureOrderPrepareFinish(Model model, String orderno, HttpServletRequest request) throws MyException {
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        BasicRet basicRet = new BasicRet();
        Orders futureOrders = ordersService.getOrdersByOrderNo(orderno);
        if(futureOrders != null){
            Orders updateFutureOrders = new Orders();
            updateFutureOrders.setId(futureOrders.getId());
            updateFutureOrders.setOrderno(orderno);
            //商家备货完成，订单状态从“备货中”变为“备货完成”
            if (futureOrders.getOrderstatus() == Quantity.STATE_8){
                if (futureOrders.getOrdertype() == Quantity.STATE_1){
                    updateFutureOrders.setOrderstatus(Quantity.STATE_1);
                    ordersService.updateOrders(updateFutureOrders);
                    //订单类型为远期全款则发货
                    ordersService.sendOutProduct(updateFutureOrders,null, null);
                }else if (futureOrders.getOrdertype() == Quantity.STATE_2){
                    updateFutureOrders.setOrderstatus(Quantity.STATE_9);
                    ordersService.updateOrders(updateFutureOrders);
                    List<Orders> list = new ArrayList<>();
                    list.add(updateFutureOrders);
                    //订单类型为远期定金则向客户发送补尾款短信
                    ordersService.smsNotifyBuyerPayBalanceToFutureOrders(list);
                    basicRet.setResult(BasicRet.SUCCESS);
                    basicRet.setMessage("发送短信成功");
                }
            }
        }else {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("该条订单不存在");
        }
        return basicRet;
    }



    @RequestMapping(value = "/exportSellerOrdersExcel",method = RequestMethod.GET)
    @ApiOperation(value = "导出卖家订单列表")
    public ResponseEntity<InputStreamResource> exportSellerOrdersExcel(HttpServletResponse response, Model model, OrderQueryParam param) {

        XSSFWorkbook workbook = null;
        try {
            String[] rowTitles = new String[]{"订单日期", "订单号", "商品", "品牌印记", "订货量", "单价", "运费", "订单总价", "交易状态", "佣金","结算价"};
            String[] sumCols = new String[]{"订货量","订单总价", "佣金","结算价"};

            Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
            param.setSellerid(member.getId());
            List<Map<String, Object>> list = ordersService.getSellerExcelOrders(param);
            workbook = ExcelGen.common("卖家订单列表", rowTitles, list, sumCols);
            if (workbook != null) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                workbook.write(baos);
                System.out.println(baos.toByteArray().length);
                HttpHeaders headers = new HttpHeaders();
                headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
                headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", new String("卖家订单列表.xlsx".getBytes(), "iso-8859-1")));
                headers.add("Pragma", "no-cache");
                headers.add("Expires", "0");
                String contentType = "application/vnd.ms-excel";
                return ResponseEntity.ok().headers(headers).contentType(MediaType.parseMediaType(contentType))
                        .body(new InputStreamResource(new ByteArrayInputStream(baos.toByteArray())));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (workbook != null) {
                try {
                    workbook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @RequestMapping(value = "/getMemberDeliveryOrderList", method = RequestMethod.POST)
    @ApiOperation(value = "卖家获取发货列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pdName", value = "商品名称", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "orderNo", value = "订单号", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "tranNo", value = "交易号", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "startPayTime", value = "支付开始时间", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "endPayTime", value = "支付结束时间", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "orderState", value = "订单状态0=待付款1=待发货3=待收货4=待验货5=已完成7=已关闭8=备货中9=备货完成10部分发货", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "evaState", value = "评价状态0=未评价1=已评价", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "backstate", value = "退货状态0=正常1=退货中2=退货验收3=退货完成4=异议中", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "brand", value = "品牌", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "stand", value = "规格", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "mark", value = "印记", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "sendstatus", value = "发货状态 全部订单为不传 1为待发货（查询待发货和部分发货订单）3为待收货订单(查待收货和部分发货) 10为部分发货订单(只查10部分发货) ", required = false, paramType = "query", dataType = "long"),
    })
    public PageRet getMemberDeliveryOrderList(Model model, OrderQueryParam param) {
        PageRet pageRet = new PageRet();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        param.setSellerid(member.getId());

        PageInfo pageInfo = ordersService.getMemberOrdersListForUser(param);
        List<Orders> list = pageInfo.getList();

        //组装订单
        List<MemberOrders> memberOrderses = new ArrayList<MemberOrders>();

        for (Orders orders : list) {
            List<OrderProduct> orderProducts = ordersService.getOrderProductByOrderNo((orders.getOrderno()));
            MemberOrders memberOrders = new MemberOrders();
            memberOrders.setOrderno(orders.getOrderno());
            memberOrders.setOrderid(orders.getId());
            memberOrders.setCode(orders.getCode());
            memberOrders.setCreatetime(orders.getCreatetime());
            memberOrders.setSellercompany(orders.getMembercompany());
            memberOrders.setOrderstate(orders.getOrderstatus());
            memberOrders.setTotalprice(orders.getTotalprice());
            //实付金额
            BigDecimal actualpayment = new BigDecimal(0);
            for (OrderProduct orderProduct : orderProducts) {
                actualpayment = actualpayment.add(orderProduct.getActualpayment());
            }
            memberOrders.setActualpayment(actualpayment);
            memberOrders.setReceiver(orders.getShipto());

            //如果是代理发货，显示平台联系人
            if(orders.getDeliverytype() == Quantity.STATE_1){
                memberOrders.setReceiver(AgentDeliveryAddressConst.linkman);
            }

            memberOrders.setReceiverPhone(orders.getPhone());
            //orders.totalprice,orders.freight,orders.deposit,orders.balance,orders.futuretime,orders.allpay,orders.ordertype
            memberOrders.setFreight(orders.getFreight());
            memberOrders.setDeposit(orders.getDeposit());
            memberOrders.setBalance(orders.getBalance());
            if (orders.getFuturetime() != null) {
                memberOrders.setFuturetime(orders.getFuturetime());
            }
            memberOrders.setAllpay(orders.getAllpay());
            memberOrders.setOrdertype(orders.getOrdertype());
            memberOrders.setOrderProducts(orderProducts);
            memberOrderses.add(memberOrders);
        }

        pageInfo.setList(memberOrderses);
        pageRet.data.setPageInfo(pageInfo);
        pageRet.setResult(BasicRet.SUCCESS);
        pageRet.setMessage("返回成功");
        return pageRet;
    }

    @RequestMapping(value = "/memberDeliveryOrderListExcel", method = RequestMethod.GET)
    @ApiOperation(value = "卖家发货列表Excel导出")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pdName", value = "商品名称", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "orderNo", value = "订单号", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "tranNo", value = "交易号", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "startPayTime", value = "支付开始时间", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "endPayTime", value = "支付结束时间", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "orderState", value = "订单状态0=待付款1=待发货3=待收货4=待验货5=已完成7=已关闭8=备货中9=备货完成", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "evaState", value = "评价状态0=未评价1=已评价", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "backstate", value = "退货状态0=正常1=退货中2=退货验收3=退货完成4=异议中", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "brand", value = "品牌", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "stand", value = "规格", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "mark", value = "印记", required = false, paramType = "query", dataType = "string"),
    })
    public ResponseEntity<InputStreamResource> memberDeliveryOrderListExcel(Model model, OrderQueryParam param) {
        XSSFWorkbook workbook = null;
        try {
            String[] rowTitles = new String[]{"收件人", "合同号/订单号","订单日期", "商品", "品牌印记", "订货量",  "发货截止日期", "交易状态"};
            String[] sumCols = new String[]{"订货量"};

            Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
            param.setSellerid(member.getId());
            List<Map<String, Object>> list = ordersService.memberDeliveryOrderListExcel(param);
            workbook = ExcelGen.common("卖家发货列表", rowTitles, list, sumCols);
            if (workbook != null) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                workbook.write(baos);
                System.out.println(baos.toByteArray().length);
                HttpHeaders headers = new HttpHeaders();
                headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
                headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", new String("卖家发货列表.xlsx".getBytes(), "iso-8859-1")));
                headers.add("Pragma", "no-cache");
                headers.add("Expires", "0");
                String contentType = "application/vnd.ms-excel";
                return ResponseEntity.ok().headers(headers).contentType(MediaType.parseMediaType(contentType))
                        .body(new InputStreamResource(new ByteArrayInputStream(baos.toByteArray())));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (workbook != null) {
                try {
                    workbook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @RequestMapping(value = "/getOrdersByOrderNoArr", method = RequestMethod.POST)
    @ApiOperation(value = "根据订单编号获取订单[一组订单编号]")
    public OrdersRet getOrdersByOrderNoArr(Long[] orderids){
        OrdersRet ordersRet = new OrdersRet();

        List<Orders> list = ordersService.getOrdersByIds(orderids);
        for(Orders orders : list){
            Short deliverymode = ordersService.getSellerCompanyInfoByMemberId(orders.getSaleid()).getDeliverymode();
            orders.setDeliverymode(deliverymode);
            if(orders.getDeliverytype() == 1){  //如果是代理发货，设置为代理发货地址
                orders.setProvince(AgentDeliveryAddressConst.province);
                orders.setCity(AgentDeliveryAddressConst.city);
                orders.setArea(AgentDeliveryAddressConst.province);
                orders.setReceivingaddress(AgentDeliveryAddressConst.address);
                orders.setShipto(AgentDeliveryAddressConst.linkman);
                orders.setPhone(AgentDeliveryAddressConst.tel);
            }

            BuyerCompanyInfo buyerCompanyInfo = buyerCompanyService.getBuyerCompanyInfoByMemberId(orders.getMemberid());
            Member member = memberService.getMemberById(orders.getMemberid());
            if(buyerCompanyInfo != null){
                orders.setBuyercompanyname(buyerCompanyInfo.getCompanyname());
            }

            orders.setBuyerRealname(member.getRealname());


            List<OrderProduct> orderProductList = orderProductServices.getOrderProductByOrderId(orders.getId(),new Short[]{0,3});

            List<Long> orderproductids = new ArrayList<>();
            orderProductList.stream().forEach(orderProduct ->orderproductids.add(orderProduct.getId()));
            List<OrderProductLog> orderProductLogList = orderProductLogService.getProductinfoByOrderproductids(orderproductids);

            List<OrderProduct> retOrderProdList = new ArrayList<>();
            for(OrderProduct orderProduct : orderProductList){
                if(orderProduct.getNum().compareTo(Quantity.BIG_DECIMAL_0) <=0) continue;
                List packageList = JinshangUtils.toCovertPacking(StringUtils.nvl(orderProduct.getPddesc()));
                orderProduct.setPackageList(packageList);

                for(OrderProductLog opl : orderProductLogList){
                    if(opl.getOrderproductid().equals(orderProduct.getId())){
                        orderProduct.getExtend().put("productinfo",opl.getProductinfojson());
                    }
                }
                retOrderProdList.add(orderProduct);
            }
            orders.setOrderProducts(retOrderProdList);
            //取消时间：2018年6月2日10:08:44 原因:直接添加到订单保存数据库了
//            AdminUser AdminUser = adminUserService.getAdminUserByUserid(orders.getMemberid());
//            if (AdminUser != null) {
//                Admin admin = adminService.getById(AdminUser.getAdminid());
//                orders.setClerkname(admin.getRealname());
//                orders.setClerknamePhone(admin.getMobile());
//            }
        }

        ordersRet.getData().setOrdersList(list);
        ordersRet.setResult(BasicRet.SUCCESS);
        ordersRet.setMessage("查询成功");

        return ordersRet;
    }



    @RequestMapping(value = "/printSendGoods", method = RequestMethod.POST)
    @ApiOperation(value = "批量打印发货单[一组订单编号]")
    public OrdersRet printSendGoods(Long[] orderids) {
        OrdersRet ordersRet = new OrdersRet();
        BigDecimal ordersPrice=Quantity.BIG_DECIMAL_0;
        List<Orders> list = ordersService.getOrdersByIds(orderids);
        for (Orders orders : list) {
            if (orders.getDeliverytype() == 1) {  //如果是代理发货，设置为代理发货地址
                orders.setProvince(AgentDeliveryAddressConst.province);
                orders.setCity(AgentDeliveryAddressConst.city);
                orders.setArea(AgentDeliveryAddressConst.province);
                orders.setReceivingaddress(AgentDeliveryAddressConst.address);
                orders.setShipto(AgentDeliveryAddressConst.linkman);
                orders.setPhone(AgentDeliveryAddressConst.tel);
            }

            List<OrderProduct> orderProductList = orderProductServices.getByOrderid(orders.getId());

            List<Long> orderproductids = new ArrayList<>();
            orderProductList.stream().forEach(orderProduct -> orderproductids.add(orderProduct.getId()));
            List<OrderProductLog> orderProductLogList = orderProductLogService.getProductinfoByOrderproductids(orderproductids);

            List<OrderProduct> retOrderProdList = new ArrayList<>();
            for (OrderProduct orderProduct : orderProductServices.margeOrderProduct(orderProductList)) {
                if(orderProduct.getNum().compareTo(Quantity.BIG_DECIMAL_0) <=0) continue;
                List packageList = JinshangUtils.toCovertPacking(StringUtils.nvl(orderProduct.getPddesc()));
                orderProduct.setPackageList(packageList);

                for (OrderProductLog opl : orderProductLogList) {
                    if (opl.getOrderproductid().equals(orderProduct.getId())) {
                        orderProduct.getExtend().put("productinfo", opl.getProductinfojson());
                    }
                }
                ordersPrice=ordersPrice.add(orderProduct.getActualpayment()).add(orderProduct.getDiscountpay());
                DecimalFormat decimalFormat=new DecimalFormat("#.00");
//                BigDecimal actualpayment=new BigDecimal(orderProduct.getNum().multiply(orderProduct.getPrice()).toString()).setScale(2,BigDecimal.ROUND_HALF_UP);
                BigDecimal actualpayment=orderProduct.getActualpayment().add(orderProduct.getDiscountpay());
                orderProduct.setActualpayment(new BigDecimal(decimalFormat.format(actualpayment)));
                retOrderProdList.add(orderProduct);
            }


            orders.setOrderProducts(retOrderProdList);
            orders.setTotalprice(ordersPrice);
            BuyerCompanyInfo buyerCompanyInfo = buyerCompanyService.getBuyerCompanyInfoByMemberId(orders.getMemberid());
            Member member = memberService.getMemberById(orders.getMemberid());
            if (buyerCompanyInfo != null) {
                orders.setBuyercompanyname(buyerCompanyInfo.getCompanyname());
            }

            orders.setBuyerRealname(member.getRealname());
        }

        ordersRet.getData().setOrdersList(list);
        ordersRet.setResult(BasicRet.SUCCESS);
        ordersRet.setMessage("查询成功");

        return ordersRet;
    }




    @RequestMapping(value = "/getOrdersByOrderNo", method = RequestMethod.POST)
    @ApiOperation(value = "根据订单编号获取订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderno", value = "订单编号", required = true, paramType = "query", dataType = "string"),
    })
    public OrderCarRet getOrdersByOrderNo(String orderno) {
        OrderCarRet orderCarRet = new OrderCarRet();
        orderCarRet.setMessage("返回成功");
        orderCarRet.setResult(BasicRet.SUCCESS);
        Orders orders = ordersService.getOrdersByOrderNo(orderno);

        //取消时间：2018年6月2日10:08:44 原因:直接添加到订单保存数据库了
//        AdminUser AdminUser = adminUserService.getAdminUserByUserid(orders.getMemberid());
//        if (AdminUser != null) {
//            Admin admin = adminService.getById(AdminUser.getAdminid());
//            orders.setClerkname(admin.getRealname());
//            orders.setClerknamePhone(admin.getMobile());
//        }
        if(orders.getDeliverytype() == 1){  //如果是代理发货，设置为代理发货地址
//            AgentDeliveryAddress agentDeliveryAddress = agentDeliveryAddressService.getBySellerid(orders.getSaleid());
//            if(agentDeliveryAddress != null){
//                orders.setProvince(agentDeliveryAddress.getProvince());
//                orders.setCity(agentDeliveryAddress.getCity());
//                orders.setArea(agentDeliveryAddress.getCitysmall());
//                orders.setReceivingaddress(agentDeliveryAddress.getAddress());
//                orders.setShipto(agentDeliveryAddress.getLinkman());
//                orders.setPhone(agentDeliveryAddress.getTel());
//            }
            orders.setProvince(AgentDeliveryAddressConst.province);
            orders.setCity(AgentDeliveryAddressConst.city);
            orders.setArea(AgentDeliveryAddressConst.province);
            orders.setReceivingaddress(AgentDeliveryAddressConst.address);
            orders.setShipto(AgentDeliveryAddressConst.linkman);
            orders.setPhone(AgentDeliveryAddressConst.tel);

        }

        List<OrderProduct> productList = orderProductServices.getByOrderNo(orderno);
        orderCarRet.data.orderProducts = productList;
        orderCarRet.data.orders = orders;

        //根据订单号去logisticsinfo表查询物流
        List<LogisticsInfoDto> logisticsInfoList = logisticsInfoService.selectByOrderNo(orderno);

        String url = "";
        if(logisticsInfoList!=null && logisticsInfoList.size()>0) {
            setExpressurl(logisticsInfoList);
        }
        //app端接口为了兼容是可以使用下面那条 只取最新的一条物流信息
        //orderCarRet.data.expressurl = url;
        orderCarRet.data.logisticsInfos = logisticsInfoList;
        return orderCarRet;
    }

    private void setExpressurl(List<LogisticsInfoDto> logisticsInfoList) {
        String url;
        for (LogisticsInfoDto logisticsInfoDto : logisticsInfoList) {
            if (StringUtils.hasText(logisticsInfoDto.getLogisticscompany()) && StringUtils.hasText(logisticsInfoDto.getCouriernumber())) {
                List<String> list = commonDataValueService.getcommonDataValue("物流公司");
                for (String vl : list) {
                    String[] vlStr = vl.split("-");
                    if(logisticsInfoDto != null) {
                        if (vlStr[0].equals(logisticsInfoDto.getLogisticscompany())) {
                            //物流查询
                            url = ExpressUtils.searchkuaiDiInfo(vlStr[1], logisticsInfoDto.getCouriernumber());
                            logisticsInfoDto.setExpressurl(url);
                            break;
                        }
                    }else{
                        logisticsInfoDto.setExpressurl("");
                    }
                }
            }
        }
    }


    @RequestMapping(value = "/getOrderProductByOrderNo", method = RequestMethod.POST)
    @ApiOperation(value = "根据订单编号获取商品信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderno", value = "订单编号", required = true, paramType = "query", dataType = "string"),
    })
    public OrderCarRet getOrderProductByOrderNo(String orderno) {
        OrderCarRet orderCarRet = new OrderCarRet();
        orderCarRet.setMessage("返回成功");
        orderCarRet.setResult(BasicRet.SUCCESS);
        List<OrderProduct> productList=ordersService.getOrderProductByOrderNo(orderno);;
        for (OrderProduct orderProduct:productList) {
            DecimalFormat decimalFormat=new DecimalFormat("#.00");
            BigDecimal actualpayment=new BigDecimal(orderProduct.getNum().multiply(orderProduct.getPrice()).toString()).setScale(2,BigDecimal.ROUND_HALF_UP);
            orderProduct.setActualpayforcontract(new BigDecimal(decimalFormat.format(actualpayment)));
        }
        orderCarRet.data.orderProducts = productList;
        return orderCarRet;
    }

    @RequestMapping(value = "/getOrderProductById", method = RequestMethod.POST)
    @ApiOperation(value = "根据商品订章id获取商品信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "订单商品id", required = true, paramType = "query", dataType = "long"),
    })
    public OrderCarRet getOrderProductById(long id) {
        OrderCarRet orderCarRet = new OrderCarRet();
        orderCarRet.setMessage("返回成功");
        orderCarRet.setResult(BasicRet.SUCCESS);
        orderCarRet.data.orderProduct = ordersService.getOrderProductById(id);
        return orderCarRet;
    }

    @RequestMapping(value = "/getBillingRecordByOrderNo", method = RequestMethod.POST)
    @ApiOperation(value = "根据订单编号获取开票信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderid", value = "订单id", required = true, paramType = "query", dataType = "string"),
    })
    public OrderCarRet getBillingRecordByOrderNo(String orderid) {
        OrderCarRet orderCarRet = new OrderCarRet();
        orderCarRet.setMessage("返回成功");
        orderCarRet.setResult(BasicRet.SUCCESS);
        orderCarRet.data.billingRecord = ordersService.getBillingRecordByOrderNo(orderid);
        return orderCarRet;
    }


    @RequestMapping(value = "/updateOrderState", method = RequestMethod.POST)
    @ApiOperation(value = "卖家订单改变状态接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderno", value = "订单编号", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "state", value = "订单状态0=待付款1=待发货3=待收货4=待验货5=已完成7=已关闭8=备货中9=备货完成10=部分发货", required = true, paramType = "query", dataType = "int"),
    })
    public BasicRet updateOrderState(Model model, String orderno, short state, HttpServletRequest request) throws CashException {
        BasicRet basicRet = new BasicRet();

        //对操作类型进行校验 wyh
        if(state!=1 && state != 3 && state != 8 && state != 9 && state != 10){
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("操作类型不合法");
            return basicRet;
        }


        Orders orders = ordersService.getOrdersByOrderNo(orderno);
        if (orders != null) {
            if(orders.getOrderstatus() == Quantity.STATE_10){
                basicRet.setResult(BasicRet.ERR);
                basicRet.setMessage("部分发货商品，不可以确认收货，请全部发货完成再确认收货");
                return basicRet;
            }


            //卖家会有收货 和验货的操作？   wyh
            /*
            if (state == Quantity.STATE_4) {
                orders.setBuyerdeliverytime(new Date());
            }
            if (state == Quantity.STATE_5) {
                orders.setBuyerinspectiontime(new Date());
                //计算订单金额和佣金，并充值到余额和开票金额
                List<OrderProduct> list = ordersService.getOrderProductByOrderNo(orders.getOrderno());
                //判断是否有退货商品
                BigDecimal sellerpay = new BigDecimal(0);
                for (OrderProduct orderProduct : list) {
                    //退货状态0=正常1=退货中2=退货验收3=退货完成4=异议中
                    if (orderProduct.getBackstate() == Quantity.STATE_1 || orderProduct.getBackstate() == Quantity.STATE_2 || orderProduct.getBackstate() == Quantity.STATE_4) {
                        basicRet.setResult(BasicRet.ERR);
                        basicRet.setMessage("有商品还在退货中，不能结束订单");
                        return basicRet;
                    }
                    if (orderProduct.getBackstate() == Quantity.STATE_0) {
                        sellerpay = sellerpay.add(orderProduct.getActualpayment());
                    }
                }

                Member member = memberService.getMemberById(orders.getSaleid());
                Member oldMember = new Member();
                BeanUtils.copyProperties(member,oldMember);
                member.setBalance(member.getBalance().add(sellerpay));
                member.setBillmoney(member.getBillmoney().add(sellerpay));
                ordersService.saveMember(member,oldMember);
            }*/


            orders.setOrderstatus(state);
            ordersService.updateSingleOrder(orders);
        } else {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("订单不存在");
            return basicRet;
        }
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        //用户日志
        memberLogOperator.saveMemberLog(member, null, "订单编号：" + orderno + "状态改变为：" + JinshangUtils.orderState(state), "/rest/seller/orders/getOrderProductByOrderNo",request, memberOperateLogService);

        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("修改成功");
        return basicRet;
    }




    @RequestMapping(value = "/updateOrderPrice", method = RequestMethod.POST)
    @ApiOperation(value = "订单修改价格")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderproductid", value = "订单商品id", required = true, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "updatePay", value = "修改的总价", required = true, paramType = "query", dataType = "double"),
    })
    public BasicRet updateOrderPrice(Model model, Long orderproductid, BigDecimal updatePay, HttpServletRequest request) {
        BasicRet basicRet = new BasicRet();
        OrderProduct orderProduct = ordersService.getOrderProductById(orderproductid);
        if (orderProduct != null) {
            if (orderProduct.getProtype() == Quantity.STATE_2) {
                orderProduct.setPartpay((updatePay.subtract(orderProduct.getFreight())).multiply(transactionSettingService.getTransactionSetting().getRemotepurchasingmargin().multiply(new BigDecimal(0.01))));
                orderProduct.setYupay(updatePay.subtract(orderProduct.getPartpay()));
                orderProduct.setActualpayment(updatePay);
            } else if (orderProduct.getProtype() == Quantity.STATE_1) {
                orderProduct.setActualpayment(updatePay);
                orderProduct.setAllpay(updatePay.subtract(orderProduct.getFreight()));
            } else {
                orderProduct.setActualpayment(updatePay);
            }
            ordersService.updateOrderProduct(orderProduct);
        } else {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("订单商品不存在");
            return basicRet;
        }

        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);


        //保存操作日志
        OperateLog operateLog = new OperateLog();
        operateLog.setContent(orderProduct.getPdname() + "价格修改");
        operateLog.setOpid(member.getId());
        operateLog.setOpname(member.getUsername());
        operateLog.setOptime(new Date());
        operateLog.setOptype(Quantity.STATE_0);
        operateLog.setOrderid(orderProduct.getOrderid());
        operateLog.setOrderno(orderProduct.getOrderno());
        ordersService.saveOperatelog(operateLog);
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("修改成功");
        //用户日志
        memberLogOperator.saveMemberLog(member, null, "订单价格修改为：" + updatePay, "/rest/seller/orders/updateOrderPrice",request, memberOperateLogService);
        return basicRet;
    }

    @RequestMapping(value = "/sendOutGoods", method = RequestMethod.POST)
    @ApiOperation(value = "发货")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderno", value = "订单编号", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "logisticscompany", value = "运输方式", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "couriernumber", value = "运输单号", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "type", value = "type 1包邮 2自提  3顺丰到付 4物流自提 5物流到家 6快递", required = false, paramType = "query", dataType = "long"),
    })
    public BasicRet sendOutGoods(Model model, String orderno, String logisticscompany, String couriernumber,long type, HttpServletRequest request) throws MyException {

        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        logisticscompany = StringUtils.nvl(logisticscompany);
        couriernumber = StringUtils.nvl(couriernumber);

        BasicRet basicRet = new BasicRet();
        Orders orders = ordersService.getOrdersByOrderNo(orderno);

        if(type !=2) {
            if (logisticscompany == null || logisticscompany.equals("")) {
                basicRet.setMessage("运输方式不允许为空");
                basicRet.setResult(BasicRet.ERR);
                return basicRet;
            }
            if ((couriernumber == null || couriernumber == "") && (!logisticscompany.equals("无需物流"))) {
                basicRet.setMessage("运输单号不允许为空");
                basicRet.setResult(BasicRet.ERR);
                return basicRet;
            }
        }
        if (orders != null) {
            if(orders.getOrderstatus() == Quantity.STATE_10){
                basicRet.setMessage("部分发货，不允许走全部发货");
                basicRet.setResult(BasicRet.ERR);
                return  basicRet;
            }
            if (orders.getOrderstatus() == Quantity.STATE_1) {

                Orders updateOrder = new Orders();
                updateOrder.setId(orders.getId());

                //判断是否是代发订单
                if(orders.getDeliverytype() == Quantity.STATE_1){
                    //保存物流信息到代发物流表中
                    AgentLogistics agentLogistics =  new AgentLogistics();
                    agentLogistics.setOrderno(orders.getOrderno());
                    agentLogistics.setLogisticscompany(logisticscompany);
                    agentLogistics.setCouriernumber(couriernumber);
                    agentLogistics.setType(Quantity.STATE_0);
                    agentLogisticsService.insertSelective(agentLogistics);

                   /* updateOrder.setLogisticscompany(logisticscompany);
                    updateOrder.setCouriernumber(couriernumber);
                    updateOrder.setDeliveryno(GenerateNo.getInvoiceNo());*/
                    //判断是旧订单发货还是新订单发货
                    checkOldAndNewOrders(orderno,logisticscompany,couriernumber, orders);
                }else{
                    /*updateOrder.setLogisticscompany(logisticscompany);
                    updateOrder.setCouriernumber(couriernumber);
                    updateOrder.setDeliveryno(GenerateNo.getInvoiceNo());*/
                    //判断是旧订单发货还是新订单发货
                    checkOldAndNewOrders(orderno,logisticscompany,couriernumber, orders);
                }


                updateOrder.setOrderstatus(Quantity.STATE_3);
                updateOrder.setSellerdeliverytime(new Date());

                OrdersExample updateOrdersExample = new OrdersExample();
                OrdersExample.Criteria criteria = updateOrdersExample.createCriteria();
                criteria.andIdEqualTo(updateOrder.getId()).andOrderstatusEqualTo(Quantity.STATE_1);

                 if(ordersService.sendOutGoodsUpdateOrder(updateOrder,updateOrdersExample) != 1){
                    throw new MyException("请勿重复发货");
                 }
                 //进行对账单的插入
                Member buyer=memberService.getMemberById(orders.getMemberid());
                BuyerStatement buyerStatement=ordersService.createBuyerStateForSend(orders,orders.getActualpayment(),new Date(),buyer);
                statementService.insertStatement(buyerStatement);
                //保存操作日志
                OperateLog operateLog = new OperateLog();
                operateLog.setContent("已发货");
                operateLog.setOpid(member.getId());
                operateLog.setOpname(member.getUsername());
                operateLog.setOptime(new Date());
                operateLog.setOptype(Quantity.STATE_0);
                operateLog.setOrderid(orders.getId());
                operateLog.setOrderno(orders.getOrderno());
                ordersService.saveOperatelog(operateLog);

                basicRet.setMessage("发货成功");
                basicRet.setResult(BasicRet.SUCCESS);


                //查询orders 上面orders缺少物流单号和发货公司
                Orders orders1 = ordersService.getOrdersByOrderNo(orderno);
                List<Orders> list = new ArrayList<>();
                list.add(orders1);

                //短信通知买家
                if (logisticscompany.equals("无需物流")) {
                    ordersService.smsNotifySellerDeliveryAndSelfDeliveryToOrders(list);
                } else if (logisticscompany.equals("物流")) {
                    ordersService.smsNotifyBuyerLogisticsDeliveryToOrders(list);
                } else { //其他
                    ordersService.smsNotifySellerDeliveryToOrders(list);
                }

                //用户日志
                memberLogOperator.saveMemberLog(member, null, "订单发货完成,订单编号为：" + orderno, "/rest/seller/orders/sendOutGoods",request, memberOperateLogService);
                return basicRet;
            }else if(orders.getOrderstatus() == Quantity.STATE_7) {
                basicRet.setMessage("买家已取消订单，发货失败");
                basicRet.setResult(BasicRet.ERR);
                return basicRet;
            }else {
                basicRet.setMessage("订单已发货");
                basicRet.setResult(BasicRet.ERR);
                return basicRet;
            }
        } else {
            basicRet.setMessage("没有此订单");
            basicRet.setResult(BasicRet.ERR);
            return basicRet;
        }
    }

    private void checkOldAndNewOrders(String orderno,String logisticscompany,String couriernumber, Orders orders) {
        List<LogisticsInfoDto> logisticsInfoList = logisticsInfoService.selectByOrderNo(orders.getOrderno());
        //只有一条发货记录且orderstatus是3待收货的 是数据迁移后的订单
        if(logisticsInfoList.size()==1 && orders.getOrderstatus() == 3) {
            for (LogisticsInfoDto logisticsInfoDto:logisticsInfoList) {
            LogisticsInfo newLogisticsInfo = new LogisticsInfo();
            newLogisticsInfo.setId(logisticsInfoDto.getId());
            newLogisticsInfo.setCouriernumber(couriernumber);
            newLogisticsInfo.setLogisticscompany(logisticscompany);
            newLogisticsInfo.setDeliveryno(GenerateNo.getInvoiceNo());
            newLogisticsInfo.setTime(new Date());
            logisticsInfoService.updateLogisticsInfo(newLogisticsInfo);
            //同时回写deliveryid
            List<OrderProduct> list = orderProductServices.getByOrderNo(orders.getOrderno());
            if(list!=null && list.size()>0) {
                for (OrderProduct orderProduct : list) {
                    OrderProduct newOrderProduct = new OrderProduct();
                    newOrderProduct.setId(orderProduct.getId());
                    newOrderProduct.setDeliveryid(logisticsInfoDto.getId());
                    orderProductServices.updateByPrimaryKeySelective(newOrderProduct);
                }
             }
            }
        }else {
                LogisticsInfo newLogisticsInfo = new LogisticsInfo();
                newLogisticsInfo.setOrderid(orders.getId());
                newLogisticsInfo.setOrderno(orderno);
                newLogisticsInfo.setCouriernumber(couriernumber);
                newLogisticsInfo.setLogisticscompany(logisticscompany);
                newLogisticsInfo.setDeliveryno(GenerateNo.getInvoiceNo());
                newLogisticsInfo.setTime(new Date());
                logisticsInfoService.insertLogisticsInfo(newLogisticsInfo);
                //同时回写deliveryid
                List<OrderProduct> list = orderProductServices.getByOrderNo(orders.getOrderno());
                if(list!=null && list.size()>0) {
                    for (OrderProduct orderProduct : list) {
                        OrderProduct newOrderProduct = new OrderProduct();
                        newOrderProduct.setId(orderProduct.getId());
                        newOrderProduct.setDeliveryid(newLogisticsInfo.getId());
                        orderProductServices.updateByPrimaryKeySelective(newOrderProduct);
                    }
                }
            }

    }


    @RequestMapping(value = "/splitSendOutGoods", method = RequestMethod.POST)
    @ApiOperation(value = "分批发货")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderno", value = "订单编号", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "orderproductids", value = "勾选的商品id", required = true, paramType = "query", dataType = "array"),
            @ApiImplicitParam(name = "logisticscompany", value = "运输方式", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "couriernumber", value = "运输单号", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "type", value = "type 1包邮 2自提  3顺丰到付 4物流自提 5物流到家 6快递", required = false, paramType = "query", dataType = "long"),
    })
    public BasicRet splitSendOutGoods(Model model, String orderno, String logisticscompany, String couriernumber,long type,String orderproductids, HttpServletRequest request) throws MyException {

        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

        BasicRet basicRet = new BasicRet();
        Orders orders = ordersService.getOrdersByOrderNo(orderno);
        BigDecimal sendAmount=Quantity.BIG_DECIMAL_0;
        List<OrderProduct> orderProductList1=new ArrayList<>();
        if(type !=2) {
            if (logisticscompany == null || logisticscompany.equals("")) {
                basicRet.setMessage("运输方式不允许为空");
                basicRet.setResult(BasicRet.ERR);
                return basicRet;
            }
            if ((couriernumber == null || couriernumber == "") && (!logisticscompany.equals("无需物流"))) {
                basicRet.setMessage("运输单号不允许为空");
                basicRet.setResult(BasicRet.ERR);
                return basicRet;
            }
        }

        String[] oldIds = orderproductids.split(",");
        Long[] ids = new Long[oldIds.length];
        for (int i = 0; i < oldIds.length; i++) {
            ids[i] = Long.valueOf(oldIds[i]);
        }

        List<Long> orderproductidsList = Arrays.asList(ids);

        if (orders != null) {
            //查询出这个订单所有的商品 包含部分发货(已发货的)和待发货
            //long[]  orderproductidsList= {12589,12590};
            List<OrderProduct> orderProductList = orderProductServices.getByOrderNo(orderno);
            if(orderProductList!=null && orderProductList.size()>0 && (orderproductidsList.size() ==orderProductList.size())){
                basicRet.setResult(BasicRet.ERR);
                basicRet.setMessage("全部发货，请不要使用分批发货按钮");
                return basicRet;
            }
            //再判断已经部分发货的 不允许再发
            if(ids!=null && orderproductidsList.size() >0) {
                for (Long orderproductid : orderproductidsList) {
                    OrderProduct newOrderProduct = orderProductServices.getOrderProductById(orderproductid);
                    if (newOrderProduct != null) {
                        if (newOrderProduct.getDeliveryid() != null) {
                            basicRet.setResult(BasicRet.ERR);
                            basicRet.setMessage("勾选的部分商品发货(未发货的)，里面有已经发货的！");
                            return basicRet;
                        }
                    } else {
                        basicRet.setResult(BasicRet.ERR);
                        basicRet.setMessage("勾选的部分商品发货，在订单商品表未找到");
                        return basicRet;
                    }
                    orderProductList1.add(newOrderProduct);
                }
            }else{
                basicRet.setResult(BasicRet.ERR);
                basicRet.setMessage("请勾选部分发货商品，传入勾选的商品id长度要大于0");
                return basicRet;
            }


            if (orders.getOrderstatus() == Quantity.STATE_1 || orders.getOrderstatus() == Quantity.STATE_10) {
                Orders updateOrder = new Orders();
                updateOrder.setId(orders.getId());
                //判断是否是代发订单
                if (orders.getDeliverytype() == Quantity.STATE_1) {
                    //代发到平台的订单 不允许走分批发货
                    basicRet.setResult(BasicRet.ERR);
                    basicRet.setMessage("代发订单到平台的，不允许分批发货");
                    return basicRet;
                } else {
                    LogisticsInfo logisticsInfo = new LogisticsInfo();
                    logisticsInfo.setOrderid(orders.getId());
                    logisticsInfo.setOrderno(orders.getOrderno());
                    logisticsInfo.setLogisticscompany(logisticscompany);
                    logisticsInfo.setCouriernumber(couriernumber);
                    logisticsInfo.setTime(new Date());
                    logisticsInfo.setDeliveryno(GenerateNo.getInvoiceNo());
                    //xml里insertSelective 有返回主键id 覆盖xml需要注意
                    logisticsInfoService.insertLogisticsInfo(logisticsInfo);
                    //同时需要去orderproduct表把deliveryid补全
                    for (Long orderproductid:orderproductidsList) {
                        OrderProduct updateOrderProduct = new OrderProduct();
                        updateOrderProduct.setId(orderproductid);
                        updateOrderProduct.setDeliveryid(logisticsInfo.getId());
                        //orderProductServices.updateByPrimaryKeySelective(updateOrderProduct);

                        OrderProductExample updateOrderProductExample = new OrderProductExample();
                        OrderProductExample.Criteria criteria = updateOrderProductExample.createCriteria();
                        criteria.andIdEqualTo(orderproductid).andDeliveryidIsNull();
                        if(orderProductServices.sendSplitOutGoodsUpdateOrderProduct(updateOrderProduct,updateOrderProductExample) != 1){
                            throw new MyException("分批发货,请勿重复发货");
                        }
                    }
                }

                //判断是否为最后一件商品或者剩余商品全部发出。整个订单全部发完 则orderstatus要改为3 待收货
                //查询出所有 部分发货里面（还没发货的，也就是deliveryid不为null的）如果与orderProductList size相等那就代表全部发了
                //List<OrderProduct> neworderProductList = orderProductServices.getByOrderNoAndDeliveryidIsNull(orders.getOrderno());
                List<OrderProduct> neworderProductList = orderProductServices.getByOrderNoAndDeliveryidIsNotNull(orders.getOrderno());
                if(neworderProductList!=null && neworderProductList.size()>0 && (neworderProductList.size()==orderProductList.size())) {
                    updateOrder.setOrderstatus(Quantity.STATE_3);
                    //只有部分发货 最后发完才会去存这个时间 平常这个字段都为空
                    updateOrder.setSellerdeliverytime(new Date());
                }else{
                    updateOrder.setOrderstatus(Quantity.STATE_10);
                }

                ordersService.updateSingleOrder(updateOrder);

                //进行对账单的插入
                for (OrderProduct orderProduct:orderProductList1) {
                    sendAmount=sendAmount.add(orderProduct.getActualpayment());
                }
                if (updateOrder.getOrderstatus()==Quantity.STATE_3){
                    sendAmount=sendAmount.add(orders.getFreight());
                }
                Member buyer=memberService.getMemberById(orders.getMemberid());
                BuyerStatement buyerStatement=ordersService.createBuyerStateForSend(orders,sendAmount,new Date(),buyer);
                statementService.insertStatement(buyerStatement);

                //保存操作日志
                OperateLog operateLog = new OperateLog();
                if(neworderProductList!=null && neworderProductList.size()>0 && (neworderProductList.size()==orderProductList.size())) {
                    operateLog.setContent("已全部发货");
                }else {
                    operateLog.setContent("已部分发货");
                }
                operateLog.setOpid(member.getId());
                operateLog.setOpname(member.getUsername());
                operateLog.setOptime(new Date());
                operateLog.setOptype(Quantity.STATE_0);
                operateLog.setOrderid(orders.getId());
                operateLog.setOrderno(orders.getOrderno());
                ordersService.saveOperatelog(operateLog);
                if(neworderProductList!=null && neworderProductList.size()>0 && (neworderProductList.size()==orderProductList.size())) {
                    basicRet.setMessage("部分发货，已全部发货成功");
                }else {
                    basicRet.setMessage("部分发货成功");
                }

                basicRet.setResult(BasicRet.SUCCESS);


                //查询orders 上面orders缺少物流单号和发货公司
                Orders orders1 = ordersService.getOrdersByOrderNo(orderno);


                //短信通知买家
                List<Orders> list = new ArrayList<>();
                list.add(orders1);
                ordersService.smsNotifySellerDeliverySplitToOrders(list);



              /*  //查询买家信息
                Member buyer = memberService.getMemberById(orders.getMemberid());
                //第三方推送 极光推送
                Map<String, String> params = new HashMap<String, String> ();
                //type 1.代表订单详情 2.退货详情页 3.APP限时购列表页 4.APP限时购详情页 5.文章资讯详情页 6.紧固件 已挂牌商品 7. 非紧固件 已挂牌商品 8.部分发货详情页
                params.put("type","8");
                params.put("id",orders1.getOrderno()+"");
                JiGuangPushUtil.sendByAlias("紧商网发货提醒","您的订单号"+orders1.getOrderno()+"已部分发货",params,buyer.getMobile());*/


                //用户日志
                memberLogOperator.saveMemberLog(member, null, "订单发货完成,订单编号为：" + orderno, "/rest/seller/orders/splitSendOutGoods",request, memberOperateLogService);
                return basicRet;
            }else if(orders.getOrderstatus() == Quantity.STATE_7) {
                basicRet.setMessage("买家已取消订单，发货失败");
                basicRet.setResult(BasicRet.ERR);
                return basicRet;
            }else {
                basicRet.setMessage("订单已发货");
                basicRet.setResult(BasicRet.ERR);
                return basicRet;
            }
        } else {
            basicRet.setMessage("没有此订单");
            basicRet.setResult(BasicRet.ERR);
            return basicRet;
        }

    }


    @RequestMapping(value = "/getOrderProductBackByOrderProductId", method = RequestMethod.POST)
    @ApiOperation(value = "根据商品id获取退货详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "订单商品id", required = true, paramType = "query", dataType = "long"),
    })
    public BasicRet getOrderProductBackByOrderProductId(Long id) {
        OrderCarRet orderCarRet = new OrderCarRet();
        OrderProductBack orderProductBack = ordersService.getOrderProductBackByOrderProductID(id);
        OrderProduct orderProduct=orderProductServices.getOrderProductById(id);
        orderProductBack.setDiscountpay(orderProduct.getDiscountpay());
        orderCarRet.data.orderProductBack = orderProductBack;

        //根据退货原因 查询违约金比例
        ReturnReason returnReason = returnReasonService.selectByReturnReason(orderProductBack.getReturnbackreason());
        if(returnReason !=null) {
            BigDecimal penalty = returnReason.getPenalty();
            orderCarRet.data.penalty = penalty;
        }
        OrderProduct orderProduct1 = orderProductServices.getOrderProductById(orderProductBack.getPdid());
        if(orderProduct1 !=null) {
            BigDecimal price = orderProduct1.getPrice();
            orderCarRet.data.price = price;
        }

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







    @RequestMapping(value = "/updateOrderProductBack", method = RequestMethod.POST)
    @ApiOperation(value = "修改退货申请")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "退货申请id", required = true, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "state", value = "退货状态0=待卖家处理退款1=卖家同意待收货2=卖家同意直接退款3=卖家收到货同意4=卖家不同意5=买家同意验货6=买家申请异议7=平台同意退货不扣违约金8=平台同意退货扣违约金9=平台转入待验收10=退货成功11=撤消", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "adminstate", value = "平台处理意见0=正常1=不扣违约金2=扣违约金3=转入待验收", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "logisticsno", value = "退货单号", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "logisticscompany", value = "退货物流公司", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "backaddress", value = "退货地址", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "contact", value = "退货联系人", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "contactphone", value = "退货联系人电话", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "sellernotagree", value = "卖家不同意原因", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "adminreason", value = "平台处理说明", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "province", value = "省", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "city", value = "市", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "area", value = "区", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "backtype", value = "退货类型0=仅退款1=退货退款2=部分退货", required = true, paramType = "query", dataType = "int"),
    })
    public BasicRet updateOrderProductBack(Model model, ProductBackModel productBackModel, HttpServletRequest request) throws CashException, MyException {
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        BasicRet basicRet = new BasicRet();

        Short state = productBackModel.getState();

        if(state != Quantity.STATE_1 && state != Quantity.STATE_2 && state != Quantity.STATE_3 && state != Quantity.STATE_4){
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("操作状态不合法");
            return  basicRet;
        }

//        Short adminstate = productBackModel.getAdminstate();
//        OrderProductBack orderProductBack = ordersService.getOrderProductBackById(productBackModel.getId());
        productBackModel.setAdminstate(null);
        productBackModel.setPic(null);
        productBackModel.setBackexplain(null);


        OrderProductBack orderProductBack = ordersService.getBackgoodsOrderProductBack(productBackModel);
        if(orderProductBack == null){
            return  new BasicRet(BasicRet.ERR,"未查询到退货商品信息");
        }


        OrderProduct orderProduct = ordersService.getOrderProductById(orderProductBack.getOrderpdid());
        if(orderProduct == null){
            return  new BasicRet(BasicRet.ERR,"未查询到订单商品");
        }

        if(orderProductBack.getState()==Quantity.STATE_11) {
            basicRet.setMessage("退货已撤消");
            basicRet.setResult(BasicRet.ERR);
            return basicRet;
        }else if(orderProductBack.getState()==Quantity.STATE_6){
            basicRet.setMessage("退货已申请异议");
            basicRet.setResult(BasicRet.ERR);
            return basicRet;
        }else if(orderProductBack.getState()==Quantity.STATE_10){
            basicRet.setMessage("退货已完成");
            basicRet.setResult(BasicRet.ERR);
            return basicRet;
        }else {
            ProductStore store = null;
            LimitTimeStore limitTimeStore = null;
            LimitTimeProd limitTimeProd = null;
            Orders orders = ordersService.getOrdersById(orderProductBack.getOrderid());
            if (orders.getIsonline() == Quantity.STATE_0) {
                store = ordersService.getProductStore(orderProductBack.getPdid(), orderProductBack.getPdno(),orderProductBack.getStoreid());
            }
            if (orders.getIsonline() == Quantity.STATE_2) {
                //limitTimeStore = shopCarService.getLimitTimeStore(orderProductBack.getStoreid());

                limitTimeStore = shopCarService.getLimitTimeStore(orderProduct.getLimitid(),orderProduct.getPdid(),orderProduct.getPdno());

                limitTimeProd = shopCarService.getLimitTimeProd(orderProductBack.getPdid(),orderProductBack.getLimitid());
            }

            if (orderProductBack != null) {

                if(state == Quantity.STATE_1){  //卖家同意退货，进入买家发货给卖家，卖家验货环节
                    orderProduct.setBackstate(Quantity.STATE_2);

                    ordersService.updateOrderProduct(orderProduct);
                    ordersService.updateOrderProductBack(orderProductBack);

                }else if(state == Quantity.STATE_2 || state == Quantity.STATE_3){ //卖家同意直接退款或卖家收到货同意退款
                    orderProduct.setBackstate(Quantity.STATE_3);
                    orderProductBack.setState(Quantity.STATE_10);

                    //增加库存
                    if (orders.getIsonline() == Quantity.STATE_0 && store != null) {
                        store.setPdstorenum(store.getPdstorenum().add(orderProductBack.getPdnum()));
                        ordersService.updateProductStore(store);
                    }

                    if (orders.getIsonline() == Quantity.STATE_2 ) {
                        if(limitTimeStore != null) {
                            limitTimeStore.setStorenum(limitTimeStore.getStorenum().add(orderProductBack.getPdnum()));
                            limitTimeStore.setSalesnum(limitTimeStore.getSalesnum().subtract(orderProductBack.getPdnum()));
                            shopCarService.updateLimitTimeStore(limitTimeStore);
                        }
                        if(limitTimeProd != null) {
                            limitTimeProd.setSalestotalnum(limitTimeProd.getSalestotalnum().subtract(orderProduct.getNum()));
                            shopCarService.updateLimitTimeProd(limitTimeProd);
                        }
                    }

                    //退款
                    OrderProductBackExample orderProductBackExample = new OrderProductBackExample();
                    OrderProductBackExample.Criteria criteria = orderProductBackExample.createCriteria();
                    criteria.andIdEqualTo(orderProductBack.getId());
                    criteria.andStateIn(Arrays.asList(new Short[]{2,5,12}));
                    int c =orderProductBackService.updateByExampleSelective(orderProductBack,orderProductBackExample);
                    if(c != 1){
                        throw  new MyException("出现错误，请联系网站客服");
                    }


                    ordersService.updateOrderProduct(orderProduct);
                    handleBackGoods(orderProductBack, orderProduct, orders, member.getId(), member.getUsername());
                }else if(state == Quantity.STATE_4){
                    OrderProduct upOP = new OrderProduct();
                    upOP.setId(orderProduct.getId());
                    upOP.setBackstate(Quantity.STATE_5);

                    OrderProductBack upOPB = new OrderProductBack();
                    upOPB.setId(orderProductBack.getId());
                    upOPB.setState(Quantity.STATE_4);

                    ordersService.updateOrderProduct(upOP);
                    ordersService.updateOrderProductBack(upOPB);
                }


                List<OrderProduct> list = ordersService.getOrderProductByOrderId(orderProductBack.getOrderid(), orderProductBack.getOrderpdid());

                boolean flag = true;
                for (OrderProduct op : list) {
                    if (op.getBackstate() != Quantity.STATE_3) {
                        flag = false;
                        break;
                    }
                }
                //判断订单中商品是否都退货完成，就结束订单
                if (orderProduct.getBackstate() == Quantity.STATE_3 && flag) {
                    //删除开票申请记录
                    ordersService.deleteBillRecord(orders.getOrderno());
                    orders.setOrderstatus(Quantity.STATE_7);
                    orders.setReason("订单已退货");
                    ordersService.updateSingleOrder(orders);
                }
            } else {
                basicRet.setMessage("没有退货申请记录");
                basicRet.setResult(BasicRet.ERR);
                return basicRet;
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

            operateLog.setContent(JinshangUtils.orderProductBackStateMess(orderProductBack.getState()));
            ordersService.saveOperatelog(operateLog);



            // syn wms
            if(state!=null && (state == Quantity.STATE_1||state == Quantity.STATE_2 || state == Quantity.STATE_3
                    ||state == Quantity.STATE_7 || state == Quantity.STATE_8)){
                wmsService.backOrders(orderProductBack);
            }

            //进行主动退货，将数据json推送到中间件管理平台
//            ordersService.initiativeOrderReturn(productBackModel.getId());

            if(state == Quantity.STATE_3) {
                //将退货的商品信息记录到orderproductbackinfo表中
                OrderProductBackInfo orderProductBackInfo = new OrderProductBackInfo();
                orderProductBackInfo.setOrderno(orderProductBack.getOrderno());
                orderProductBackInfo.setPdid(orderProductBack.getPdid());
                orderProductBackInfo.setBackno("TH" + UUID.randomUUID());
                orderProductBackInfo.setBacknum(orderProductBack.getPdnum());
                orderProductBackInfo.setBacktype(Quantity.STATE_0);
                orderProductBackInfo.setBackstate(Quantity.STATE_0);
                orderProductBackInfo.setBacktime(new Date());
                orderProductBackInfoService.addOrderProductBackInfo(orderProductBackInfo);
            }

            //用户日志
            memberLogOperator.saveMemberLog(member, null, "修改退货申请,退货申请id为：" + productBackModel.getId(), "/rest/seller/orders/updateOrderProductBack",request, memberOperateLogService);
            basicRet.setMessage("修改成功");
            basicRet.setResult(BasicRet.SUCCESS);
            return basicRet;
        }
    }



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
        //TransactionSetting transactionSetting = transactionSettingService.getTransactionSetting();
       // BigDecimal getLiquidated = transactionSetting.getGetliquidated().multiply(new BigDecimal(0.01));
       // BigDecimal forwardsalesmargin = transactionSetting.getForwardsalesmargin().multiply(new BigDecimal(0.01));

        //新的 违约金占比
       // 责任方为买方的，走设置的占比  责任方为卖方的，还是走原来老的违约金占比
        BigDecimal getLiquidated = new BigDecimal(0);
        TransactionSetting transactionSetting = transactionSettingService.getTransactionSetting();
        ReturnReason returnReason = returnReasonService.selectByReturnReason(orderProductBack.getReturnbackreason());
        if(returnReason !=null) {
            if(returnReason.getResponsibility()==2){
                //卖家责任
                getLiquidated = transactionSetting.getGetliquidated().multiply(new BigDecimal(0.01));
            }else{
                //买家责任
                getLiquidated = returnReason.getPenalty().multiply(new BigDecimal(0.01));
            }

        }
        BigDecimal forwardsalesmargin = transactionSetting.getForwardsalesmargin().multiply(new BigDecimal(0.01));

        if (orderProductBack != null && orderProduct != null && order != null) {
            BigDecimal backPay = new BigDecimal(0);
            BigDecimal penaltyPay = new BigDecimal(0);

            Date tranTime = new Date();
            List<BuyerCapital> buyerCapitals = new ArrayList<BuyerCapital>();
            List<SalerCapital> salerCapitals = new ArrayList<SalerCapital>();
            List<BuyerStatement> statementList=new ArrayList<>();
            //买家退款资金明细
            BuyerCapital buyerCapital1 = null;
            //买家违约资金明细
            BuyerCapital buyerCapital2 = null;
            //卖家退款资金明细
            SalerCapital salerCapital1 = null;
            //卖家违约资金明细
            SalerCapital salerCapital2 = null;
            //客户下单对账单
            BuyerStatement buyerStatement=null;
            //立即发货,只有立即发货有部分退款
            if (orderProduct.getProtype() == Quantity.STATE_0) {
                BigDecimal orderPay = orderProduct.getActualpayment().subtract(orderProduct.getFreight());
                //是否有部分退货的情况
                BigDecimal backPayMoney = orderProductBack.getBackmoney();

                //退回的金额,异议扣违约金
                if (orderProductBack.getAdminstate() == Quantity.STATE_2 ) {
                    //违约金
                    penaltyPay = orderPay.multiply(getLiquidated).setScale(2,BigDecimal.ROUND_HALF_UP);
                }else if(orderProductBack.getAdminstate() == Quantity.STATE_0){
                    //平台没有介入 违约金
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
                        //先插入一条退货对账单明细
//                        buyerStatement=ordersService.createBuyerStateForBack(order,backPay.setScale(2,BigDecimal.ROUND_HALF_UP),tranTime,Quantity.STATE_3, (short) StatementType.StType3.getTyep(),buyer,returnReason,true);
//                        statementList.add(buyerStatement);
                        //被扣得违约金 创建一条买家资金明细
                        if(orderProductBack.getAdminstate() != Quantity.STATE_2) {
                            createBuyerPenaltyPay(order, penaltyPay, tranTime, buyerCapitals, orderPay);
                            //插入一条违约金的对账单明细
                            buyerStatement=ordersService.createBuyerStateForBack(order,penaltyPay.setScale(2,BigDecimal.ROUND_HALF_UP),tranTime,Quantity.STATE_3, (short) StatementType.StType4.getTyep(),buyer,returnReason,true);
                            statementList.add(buyerStatement);
                        }
                        //授信、余额支付不插入退款对账单明细
                        salerCapital1 = createSalerBackPay(order, backPay.add(orderProduct.getDiscountpay()), tranTime);
                        buyerCapitals.add(buyerCapital1);
                        salerCapitals.add(salerCapital1);
                    }


                    //退回到授信
                    if (buyerCapital.getPaytype() == Quantity.STATE_4) {
                        buyer.setAvailablelimit(buyer.getAvailablelimit().add(backPay.subtract(penaltyPay)).setScale(2,BigDecimal.ROUND_HALF_UP));
                        buyer.setUsedlimit(buyer.getUsedlimit().subtract(backPay.subtract(penaltyPay)));
                        buyerCapital1 = createBuyerBackPay(order, backPay.subtract(penaltyPay).setScale(2,BigDecimal.ROUND_HALF_UP), tranTime, Quantity.STATE_4);
                        //先插入一条退货对账单明细
//                        buyerStatement=ordersService.createBuyerStateForBack(order,backPay.setScale(2,BigDecimal.ROUND_HALF_UP),tranTime,Quantity.STATE_4,(short) StatementType.StType3.getTyep(),buyer,returnReason,true);
//                        statementList.add(buyerStatement);
                        //被扣得违约金 创建一条买家资金明细
                        if(orderProductBack.getAdminstate() != Quantity.STATE_2) {
                            createBuyerPenaltyPay(order, penaltyPay, tranTime, buyerCapitals, orderPay);
                            //插入一条违约金的对账单明细
                            buyerStatement=ordersService.createBuyerStateForBack(order,penaltyPay.setScale(2,BigDecimal.ROUND_HALF_UP),tranTime,Quantity.STATE_4, (short) StatementType.StType4.getTyep(),buyer,returnReason,true);
                            statementList.add(buyerStatement);
                        }
                        //授信、余额支付不插入退款对账单明细
                        salerCapital1 = createSalerBackPay(order, backPay.add(orderProduct.getDiscountpay()), tranTime);
                        buyerCapitals.add(buyerCapital1);
                        salerCapitals.add(salerCapital1);
                    }


                    //退回到支付宝\微信\银行卡
                    if(buyerCapital.getPaytype()==Quantity.STATE_0||
                            buyerCapital.getPaytype()==Quantity.STATE_1||
                            buyerCapital.getPaytype()==Quantity.STATE_2){
                        String uuid = order.getUuid();
                        Long ordertime = order.getOrdertime();
                        if(uuid!=null){
                            Refund refund = new Refund();
                            refund.setDatetime(ordertime);
                            refund.setOutTradeNo(uuid);
                            refund.setChannel(tradeService.getPayChannel(order.getPaytype()));

                            refund.setRefundAmount((backPay.subtract(penaltyPay).multiply(new BigDecimal(100))).setScale(2,BigDecimal.ROUND_HALF_UP).longValue());
                            refund.setRefundReason("订单退款");
                            BigDecimal totalAmout = tradeService.getTotalAmout(uuid);

                            refund.setTotalAmount((totalAmout.multiply(new BigDecimal(100))).setScale(2,BigDecimal.ROUND_HALF_UP).longValue());
                            boolean result = tradeService.backMoney(refund);


                            //System.out.println("退货通道："+refund.getChannel()+"退货结果："+result);
                            if(result){
                                if(order.getPaytype()==Quantity.STATE_0){
                                    buyerCapital1 = createBuyerBackPay(order, backPay.subtract(penaltyPay).setScale(2,BigDecimal.ROUND_HALF_UP), tranTime, Quantity.STATE_0);
                                    //被扣得违约金 创建一条买家资金明细
                                    if(orderProductBack.getAdminstate() != Quantity.STATE_2) {
                                        createBuyerPenaltyPay(order, penaltyPay, tranTime, buyerCapitals, orderPay);
                                    }
                                }else if(order.getPaytype()==Quantity.STATE_1){
                                    buyerCapital1 = createBuyerBackPay(order, backPay.subtract(penaltyPay).setScale(2,BigDecimal.ROUND_HALF_UP), tranTime, Quantity.STATE_1);
                                    //被扣得违约金 创建一条买家资金明细
                                    if(orderProductBack.getAdminstate() != Quantity.STATE_2) {
                                        createBuyerPenaltyPay(order, penaltyPay, tranTime, buyerCapitals, orderPay);
                                    }
                                }else {
                                    buyerCapital1 = createBuyerBackPay(order, backPay.subtract(penaltyPay).setScale(2,BigDecimal.ROUND_HALF_UP), tranTime, Quantity.STATE_2);
                                    //被扣得违约金 创建一条买家资金明细
                                    if(orderProductBack.getAdminstate() != Quantity.STATE_2) {
                                        createBuyerPenaltyPay(order, penaltyPay, tranTime, buyerCapitals, orderPay);
                                    }
                                }
                                //先插入一条退货对账单明细
//                                buyerStatement=ordersService.createBuyerStateForBack(order,backPay.setScale(2,BigDecimal.ROUND_HALF_UP),tranTime,order.getPaytype(),(short) StatementType.StType3.getTyep(),buyer,returnReason,true);
//                                statementList.add(buyerStatement);
                                //插入一条违约金的对账单明细
                                buyerStatement=ordersService.createBuyerStateForBack(order,penaltyPay.setScale(2,BigDecimal.ROUND_HALF_UP),tranTime,order.getPaytype(), (short) StatementType.StType4.getTyep(),buyer,returnReason,true);
                                statementList.add(buyerStatement);
                                //非余额、授信支付，需要再插入一条退款记录
                                buyerStatement=ordersService.createBuyerStateForBack(order,backPay.subtract(penaltyPay).setScale(2,BigDecimal.ROUND_HALF_UP),tranTime,order.getPaytype(), (short) StatementType.StType5.getTyep(),buyer,returnReason,true);
                                statementList.add(buyerStatement);
                                salerCapital1 = createSalerBackPay(order, backPay.add(orderProduct.getDiscountpay()), tranTime);
                                buyerCapitals.add(buyerCapital1);
                                salerCapitals.add(salerCapital1);
                            }else{
                                throw  new RuntimeException("退款失败");
                            }
                        }
                    }


                    //异议扣违约金,记录资金明细 state =0不走这里 否则会多扣一次违约金
                    if (penaltyPay.compareTo(Quantity.BIG_DECIMAL_0) == 1 &&  orderProductBack.getAdminstate() == Quantity.STATE_2){
                        BigDecimal sellerPenaltyPay = penaltyPay.multiply(forwardsalesmargin).setScale(2,BigDecimal.ROUND_HALF_UP);
                        if(sellerPenaltyPay.compareTo(Quantity.BIG_DECIMAL_0)>0){
                            sellerMember.setSellerfreezebanlance(sellerMember.getSellerfreezebanlance().add(sellerPenaltyPay));
                            salerCapital2 = createSalerPenaltyPay(order, sellerPenaltyPay, tranTime, Quantity.STATE_6, orderPay, Quantity.BUYER_BACK_REASON);
                            salerCapitals.add(salerCapital2);
                        }

                        createBuyerPenaltyPay(order, penaltyPay, tranTime, buyerCapitals, orderPay);
                    }

                    //如果退货类型为全部退货   将运费加到卖家货款金额里
                    //如果退货类型为全部退货   将运费加到卖家货款金额里
                    //对一个订单里的某个商品判断 部分退货还是全部退货 都加到冻结余额进去
                    if(orderProductBack.getBacktype() == Quantity.STATE_1){

                        //兼容分单之前的数据
                        if((order.getOrderfright() == null || order.getOrderfright()<=0) && order.getFrighttemplate() == null) {
                            sellerMember.setGoodsbanlance(sellerMember.getGoodsbanlance().add(orderProduct.getActualpayment().subtract(backPay)));
                            if(penaltyPay.compareTo(Quantity.BIG_DECIMAL_0) == 1 && orderProductBack.getAdminstate() == Quantity.STATE_0) {
                                //全部退货 如果违约金大于0且是买家原因违约 加到卖家冻结余额去
                                returnAllAndAddPenaltyPay(orderProductBack, order, sellerMember, forwardsalesmargin, penaltyPay, tranTime, salerCapitals, orderPay);
                            }
                        }else{
                            //新数据
                            //查询订单中是否还有正常的商品，如果有运费不处理，如果没有则运费加入卖家余额
                            List<OrderProduct> orderProductList = orderProductServices.getOrderProductByOrderId(order.getId());
                            boolean to = true;
                            for(OrderProduct op : orderProductList){
                                if(!op.getId().equals(orderProduct.getId())){
                                    if(op.getBackstate() != Quantity.STATE_3){
                                        to = false;
                                        break;
                                    }
                                }
                            }

                            if(to){
                                sellerMember.setGoodsbanlance(sellerMember.getGoodsbanlance().add(order.getFreight()));
                                if(penaltyPay.compareTo(Quantity.BIG_DECIMAL_0) == 1 && orderProductBack.getAdminstate() == Quantity.STATE_0) {
                                    //全部退货 如果违约金大于0且是买家原因违约 加到卖家冻结余额去
                                    returnAllAndAddPenaltyPay(orderProductBack, order, sellerMember, forwardsalesmargin, penaltyPay, tranTime, salerCapitals, orderPay);
                                }
                            }else{
                                if(penaltyPay.compareTo(Quantity.BIG_DECIMAL_0) == 1 && orderProductBack.getAdminstate() == Quantity.STATE_0) {
                                    //全部退货 如果违约金大于0且是买家原因违约 加到卖家冻结余额去
                                    returnAllAndAddPenaltyPay(orderProductBack, order, sellerMember, forwardsalesmargin, penaltyPay, tranTime, salerCapitals, orderPay);
                                }
                            }

                        }
                    }
                    //部分退货  运费不退 不加入到卖家余额
                    partReturn(orderProductBack, orderProduct, order, sellerMember, forwardsalesmargin, penaltyPay, tranTime, salerCapitals, orderPay);
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
                }else if(orderProductBack.getAdminstate() == Quantity.STATE_0){
                    //平台没有介入 违约金
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
                        //先插入一条退货对账单明细
//                        buyerStatement=ordersService.createBuyerStateForBack(order,backPay.setScale(2,BigDecimal.ROUND_HALF_UP),tranTime,Quantity.STATE_3, (short) StatementType.StType3.getTyep(),buyer,returnReason,true);
//                        statementList.add(buyerStatement);
                        //被扣得违约金 创建一条买家资金明细
                        if(orderProductBack.getAdminstate() != Quantity.STATE_2) {
                            createBuyerPenaltyPay(order, penaltyPay, tranTime, buyerCapitals, orderPay);
                            //插入一条违约金的对账单明细
                            buyerStatement=ordersService.createBuyerStateForBack(order,penaltyPay.setScale(2,BigDecimal.ROUND_HALF_UP),tranTime,Quantity.STATE_3, (short) StatementType.StType4.getTyep(),buyer,returnReason,true);
                            statementList.add(buyerStatement);
                        }
                        //授信、余额支付不插入退款对账单明细
                        salerCapital1 = createSalerBackPay(order, backPay.add(orderProduct.getDiscountpay()), tranTime);
                        buyerCapitals.add(buyerCapital1);
                        salerCapitals.add(salerCapital1);
                    }
                    //退回到授信
                    if (buyerCapital.getPaytype() == Quantity.STATE_4) {
                        buyer.setAvailablelimit(buyer.getAvailablelimit().add(backPay.subtract(penaltyPay)).setScale(2,BigDecimal.ROUND_HALF_UP));
                        buyer.setUsedlimit(buyer.getUsedlimit().subtract(backPay.subtract(penaltyPay)).setScale(2,BigDecimal.ROUND_HALF_UP));
                        buyerCapital1 = createBuyerBackPay(order, backPay.subtract(penaltyPay).setScale(2,BigDecimal.ROUND_HALF_UP), tranTime, Quantity.STATE_4);
                        //先插入一条退货对账单明细
//                        buyerStatement=ordersService.createBuyerStateForBack(order,backPay.setScale(2,BigDecimal.ROUND_HALF_UP),tranTime,Quantity.STATE_4, (short) StatementType.StType3.getTyep(),buyer,returnReason,true);
//                        statementList.add(buyerStatement);
                        //被扣得违约金 创建一条买家资金明细
                        if(orderProductBack.getAdminstate() != Quantity.STATE_2) {
                            createBuyerPenaltyPay(order, penaltyPay, tranTime, buyerCapitals, orderPay);
                            //插入一条违约金的对账单明细
                            buyerStatement=ordersService.createBuyerStateForBack(order,penaltyPay.setScale(2,BigDecimal.ROUND_HALF_UP),tranTime,Quantity.STATE_4, (short) StatementType.StType4.getTyep(),buyer,returnReason,true);
                            statementList.add(buyerStatement);
                        }
                        //授信、余额支付不插入退款对账单明细
                        salerCapital1 = createSalerBackPay(order, backPay.add(orderProduct.getDiscountpay()), tranTime);
                        buyerCapitals.add(buyerCapital1);
                        salerCapitals.add(salerCapital1);
                    }


                    //退回到支付宝或微信
                    if(buyerCapital.getPaytype()==Quantity.STATE_0||
                            buyerCapital.getPaytype()==Quantity.STATE_1||
                            buyerCapital.getPaytype()==Quantity.STATE_2){
                        String uuid = order.getUuid();
                        Long ordertime = order.getOrdertime();
                        if(uuid!=null){
                            Refund refund = new Refund();
                            refund.setOutTradeNo(uuid);
                            refund.setDatetime(ordertime);
                            refund.setChannel(tradeService.getPayChannel(order.getPaytype()));
                            refund.setRefundAmount((backPay.subtract(penaltyPay).setScale(2,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100))).longValue());
                            refund.setRefundReason("订单退款");
                            BigDecimal totalAmout = tradeService.getTotalAmout(uuid);
                            refund.setTotalAmount((totalAmout.multiply(new BigDecimal(100))).longValue());
                            boolean result = tradeService.backMoney(refund);

                            if(result){
                                if(order.getPaytype()==Quantity.STATE_0){
                                    buyerCapital1 = createBuyerBackPay(order, backPay.subtract(penaltyPay).setScale(2,BigDecimal.ROUND_HALF_UP), tranTime, Quantity.STATE_0);
                                }else if(order.getPaytype()==Quantity.STATE_1){
                                    buyerCapital1 = createBuyerBackPay(order, backPay.subtract(penaltyPay).setScale(2,BigDecimal.ROUND_HALF_UP), tranTime, Quantity.STATE_1);
                                }else {
                                    buyerCapital1 = createBuyerBackPay(order, backPay.subtract(penaltyPay).setScale(2,BigDecimal.ROUND_HALF_UP), tranTime, Quantity.STATE_2);
                                }
                                //先插入一条退货对账单明细
//                                buyerStatement=ordersService.createBuyerStateForBack(order,backPay.setScale(2,BigDecimal.ROUND_HALF_UP),tranTime,order.getPaytype(),(short) StatementType.StType3.getTyep(),buyer,returnReason,true);
//                                statementList.add(buyerStatement);
                                //插入一条违约金的对账单明细
                                buyerStatement=ordersService.createBuyerStateForBack(order,penaltyPay.setScale(2,BigDecimal.ROUND_HALF_UP),tranTime,order.getPaytype(), (short) StatementType.StType4.getTyep(),buyer,returnReason,true);
                                statementList.add(buyerStatement);
                                //非余额、授信支付，需要再插入一条退款记录
                                buyerStatement=ordersService.createBuyerStateForBack(order,backPay.subtract(penaltyPay).setScale(2,BigDecimal.ROUND_HALF_UP),tranTime,order.getPaytype(), (short) StatementType.StType5.getTyep(),buyer,returnReason,true);
                                statementList.add(buyerStatement);
                                salerCapital1 = createSalerBackPay(order, backPay.add(orderProduct.getDiscountpay()), tranTime);
                                buyerCapitals.add(buyerCapital1);
                                salerCapitals.add(salerCapital1);
                            }else{
                                throw  new RuntimeException("退款失败");
                            }
                        }
                    }

                    //异议扣违约金,记录资金明细 state =0不走这里 否则会多扣一次违约金
                    if (penaltyPay.compareTo(Quantity.BIG_DECIMAL_0) == 1 && orderProductBack.getAdminstate() == Quantity.STATE_2) {
                        BigDecimal sellerPenaltyPay = penaltyPay.multiply(forwardsalesmargin).setScale(2,BigDecimal.ROUND_HALF_UP);
                        if(sellerPenaltyPay.compareTo(Quantity.BIG_DECIMAL_0)>0){
                            sellerMember.setSellerfreezebanlance(sellerMember.getSellerfreezebanlance().add(sellerPenaltyPay));
                            salerCapital2 = createSalerPenaltyPay(order, sellerPenaltyPay, tranTime, Quantity.STATE_6, orderPay, Quantity.BUYER_BACK_REASON);
                            salerCapitals.add(salerCapital2);
                        }

                        createBuyerPenaltyPay(order, penaltyPay, tranTime, buyerCapitals, orderPay);
                    }

                    //如果退货类型为全部退货   将运费加到卖家货款金额里
                    if(orderProductBack.getBacktype() == Quantity.STATE_1){

                        //兼容分单之前的数据
                        if((order.getOrderfright() == null || order.getOrderfright()<=0) && order.getFrighttemplate() == null) {
                            sellerMember.setGoodsbanlance(sellerMember.getGoodsbanlance().add(orderProduct.getActualpayment().subtract(backPay)));
                            if(penaltyPay.compareTo(Quantity.BIG_DECIMAL_0) == 1 && orderProductBack.getAdminstate() == Quantity.STATE_0) {
                                //全部退货 如果违约金大于0且是买家原因违约 加到卖家冻结余额去
                                returnAllAndAddPenaltyPay(orderProductBack, order, sellerMember, forwardsalesmargin, penaltyPay, tranTime, salerCapitals, orderPay);
                            }
                        }else{
                            //新数据
                            //查询订单中是否还有正常的商品，如果有运费不处理，如果没有则运费加入卖家余额
                            List<OrderProduct> orderProductList = orderProductServices.getOrderProductByOrderId(order.getId());
                            boolean to = true;
                            for(OrderProduct op : orderProductList){
                                if(!op.getId().equals(orderProduct.getId())){
                                    if(op.getBackstate() != Quantity.STATE_3){
                                        to = false;
                                        break;
                                    }
                                }
                            }

                            if(to){
                                sellerMember.setGoodsbanlance(sellerMember.getGoodsbanlance().add(order.getFreight()));
                                if(penaltyPay.compareTo(Quantity.BIG_DECIMAL_0) == 1 && orderProductBack.getAdminstate() == Quantity.STATE_0) {
                                    //全部退货 如果违约金大于0且是买家原因违约 加到卖家冻结余额去
                                    returnAllAndAddPenaltyPay(orderProductBack, order, sellerMember, forwardsalesmargin, penaltyPay, tranTime, salerCapitals, orderPay);
                                }
                            }else{
                                if(penaltyPay.compareTo(Quantity.BIG_DECIMAL_0) == 1 && orderProductBack.getAdminstate() == Quantity.STATE_0) {
                                    //全部退货 如果违约金大于0且是买家原因违约 加到卖家冻结余额去
                                    returnAllAndAddPenaltyPay(orderProductBack, order, sellerMember, forwardsalesmargin, penaltyPay, tranTime, salerCapitals, orderPay);
                                }
                            }

                        }
                    }
                    //远期全款 没有部分退货
                    if(orderProductBack.getBacktype() == Quantity.STATE_2){
                       logger.info("远期全款没有 部分退货");
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

                    //违约金总额
                    BigDecimal totalPenal = orderPay.multiply(getLiquidated).setScale(2,BigDecimal.ROUND_HALF_UP);
                    penaltyPay = totalPenal;
                    //买家退款金额
                    BigDecimal buyerBackPay = orderPay.subtract(totalPenal).setScale(2,BigDecimal.ROUND_HALF_UP);

                    //卖家退款金额
                    BigDecimal salerBackPay = orderPay.setScale(2,BigDecimal.ROUND_HALF_UP);

                    //退回到余额
                    if (depositBuyerCapital.getPaytype() == Quantity.STATE_3) {
                        buyer.setBalance(buyer.getBalance().add(buyerBackPay));
                        buyerCapital1 = createBuyerBackPay(order, buyerBackPay, tranTime, Quantity.STATE_3);
                        //先插入一条退货对账单明细
//                        buyerStatement=ordersService.createBuyerStateForBack(order,backPay.setScale(2,BigDecimal.ROUND_HALF_UP),tranTime,Quantity.STATE_3, (short) StatementType.StType3.getTyep(),buyer,returnReason,true);
//                        statementList.add(buyerStatement);
                        //被扣得违约金 创建一条买家资金明细
                        if(orderProductBack.getAdminstate() != Quantity.STATE_2) {
                            createBuyerPenaltyPay(order, penaltyPay, tranTime, buyerCapitals, orderPay);
                            //插入一条违约金的对账单明细
                            buyerStatement=ordersService.createBuyerStateForBack(order,penaltyPay.setScale(2,BigDecimal.ROUND_HALF_UP),tranTime,Quantity.STATE_3, (short) StatementType.StType4.getTyep(),buyer,returnReason,true);
                            statementList.add(buyerStatement);
                        }
                        //授信、余额支付不插入退款对账单明细
                        salerCapital1 = createSalerBackPay(order, salerBackPay.add(orderProduct.getDiscountpay()), tranTime);
                        buyerCapitals.add(buyerCapital1);
                        salerCapitals.add(salerCapital1);
                    }

                    //退回到授信
                    if (depositBuyerCapital.getPaytype() == Quantity.STATE_4) {
                        buyer.setAvailablelimit(buyer.getAvailablelimit().add(buyerBackPay));
                        buyer.setUsedlimit(buyer.getUsedlimit().subtract(buyerBackPay));
                        buyerCapital1 = createBuyerBackPay(order, buyerBackPay, tranTime, Quantity.STATE_4);
                        //先插入一条退货对账单明细
//                        buyerStatement=ordersService.createBuyerStateForBack(order,backPay.setScale(2,BigDecimal.ROUND_HALF_UP),tranTime,Quantity.STATE_4, (short) StatementType.StType3.getTyep(),buyer,returnReason,true);
//                        statementList.add(buyerStatement);
                        //被扣得违约金 创建一条买家资金明细
                        if(orderProductBack.getAdminstate() != Quantity.STATE_2) {
                            createBuyerPenaltyPay(order, penaltyPay, tranTime, buyerCapitals, orderPay);
                            //插入一条违约金的对账单明细
                            buyerStatement=ordersService.createBuyerStateForBack(order,penaltyPay.setScale(2,BigDecimal.ROUND_HALF_UP),tranTime,Quantity.STATE_4, (short) StatementType.StType4.getTyep(),buyer,returnReason,true);
                            statementList.add(buyerStatement);
                        }
                        //授信、余额支付不插入退款对账单明细
                        salerCapital1 = createSalerBackPay(order, salerBackPay.add(orderProduct.getDiscountpay()), tranTime);
                        buyerCapitals.add(buyerCapital1);
                        salerCapitals.add(salerCapital1);
                    }
                    //退回到支付宝或微信
                    if(depositBuyerCapital.getPaytype()==Quantity.STATE_0||
                            depositBuyerCapital.getPaytype()==Quantity.STATE_1||
                            depositBuyerCapital.getPaytype()==Quantity.STATE_2){
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

                            refund1.setChannel(tradeService.getPayChannel(order.getPaytype()));
                            refund2.setChannel(tradeService.getPayChannel(order.getPaytype()));


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

                            boolean result1 = tradeService.backMoney(refund1);
                            boolean result2 = tradeService.backMoney(refund2);
//                            try {
//                                if("alipay".equals(refund1.getChannel())){
//                                    result1 = tradeService.backMoney(refund1);
//                                    result2 = alipayService.refund(refund2);
//                                }else if("wxpay".equals(refund1.getChannel())){
//                                    result1 = wxPayService.refund(refund1);
//                                    result2 = wxPayService.refund(refund2);
//                                }else {
//                                    result1 = abcService.refund(refund1);
//                                    result2 = abcService.refund(refund2);
//                                }
//                            }catch (Exception e){
//
//                            }
                            if(result1&&result2){
                                if(order.getPaytype()==Quantity.STATE_0){
                                    buyerCapital1 = createBuyerBackPay(order,buyerBackPay , tranTime, Quantity.STATE_0);
                                    if(orderProductBack.getAdminstate() != Quantity.STATE_2) {
                                        createBuyerPenaltyPay(order, penaltyPay, tranTime, buyerCapitals, orderPay);
                                    }
                                }else if(order.getPaytype()==Quantity.STATE_1){
                                    buyerCapital1 = createBuyerBackPay(order, buyerBackPay, tranTime, Quantity.STATE_1);
                                    if(orderProductBack.getAdminstate() != Quantity.STATE_2) {
                                        createBuyerPenaltyPay(order, penaltyPay, tranTime, buyerCapitals, orderPay);
                                    }
                                }else {
                                    buyerCapital1 = createBuyerBackPay(order, buyerBackPay, tranTime, Quantity.STATE_2);
                                    if(orderProductBack.getAdminstate() != Quantity.STATE_2) {
                                        createBuyerPenaltyPay(order, penaltyPay, tranTime, buyerCapitals, orderPay);
                                    }
                                }
                                //先插入一条退货对账单明细
//                                buyerStatement=ordersService.createBuyerStateForBack(order,backPay.setScale(2,BigDecimal.ROUND_HALF_UP),tranTime,order.getPaytype(),(short) StatementType.StType3.getTyep(),buyer,returnReason,true);
//                                statementList.add(buyerStatement);
                                //插入一条违约金的对账单明细
                                buyerStatement=ordersService.createBuyerStateForBack(order,penaltyPay.setScale(2,BigDecimal.ROUND_HALF_UP),tranTime,order.getPaytype(), (short) StatementType.StType4.getTyep(),buyer,returnReason,true);
                                statementList.add(buyerStatement);
                                //非余额、授信支付，需要再插入一条退款记录
                                buyerStatement=ordersService.createBuyerStateForBack(order,backPay.subtract(penaltyPay).setScale(2,BigDecimal.ROUND_HALF_UP),tranTime,order.getPaytype(), (short) StatementType.StType5.getTyep(),buyer,returnReason,true);
                                statementList.add(buyerStatement);
                                salerCapital1 = createSalerBackPay(order, salerBackPay.add(orderProduct.getDiscountpay()), tranTime);
                                buyerCapitals.add(buyerCapital1);
                                salerCapitals.add(salerCapital1);
                            }else{
                                throw  new RuntimeException("退款失败");
                            }
                        }
                    }

                    //异议扣违约金,记录资金明细 state =0不走这里 否则会多扣一次违约金
                    if (penaltyPay.compareTo(Quantity.BIG_DECIMAL_0) == 1 &&  orderProductBack.getAdminstate() == Quantity.STATE_2) {
                        //penaltyPay = totalPenal;
                        BigDecimal sellerPenaltyPay = penaltyPay.multiply(forwardsalesmargin).setScale(2,BigDecimal.ROUND_HALF_UP);
                        if(sellerPenaltyPay.compareTo(Quantity.BIG_DECIMAL_0)>0){
                            sellerMember.setSellerfreezebanlance(sellerMember.getSellerfreezebanlance().add(sellerPenaltyPay));
                            salerCapital2 = createSalerPenaltyPay(order, sellerPenaltyPay, tranTime, Quantity.STATE_6, orderPay, Quantity.BUYER_BACK_REASON);
                            salerCapitals.add(salerCapital2);
                        }

                        createBuyerPenaltyPay(order, penaltyPay, tranTime, buyerCapitals, orderPay);
                    }

                    //如果退货类型为全部退货   将运费加到卖家货款金额里
                    if(orderProductBack.getBacktype() == Quantity.STATE_1){
                       // penaltyPay = totalPenal;
                        //兼容分单之前的数据
                        if((order.getOrderfright() == null || order.getOrderfright()<=0) && order.getFrighttemplate() == null) {
                            sellerMember.setGoodsbanlance(sellerMember.getGoodsbanlance().add(orderProduct.getActualpayment().subtract(backPay)));
                            if(penaltyPay.compareTo(Quantity.BIG_DECIMAL_0) == 1 && orderProductBack.getAdminstate() == Quantity.STATE_0) {
                                //部分退货 如果违约金大于0且是买家原因违约 加到卖家冻结余额去
                                returnAllAndAddPenaltyPay(orderProductBack, order, sellerMember, forwardsalesmargin, penaltyPay, tranTime, salerCapitals, orderPay);
                            }
                        }else{
                            //新数据
                            //查询订单中是否还有正常的商品，如果有运费不处理，如果没有则运费加入卖家余额
                            List<OrderProduct> orderProductList = orderProductServices.getOrderProductByOrderId(order.getId());
                            boolean to = true;
                            for(OrderProduct op : orderProductList){
                                if(!op.getId().equals(orderProduct.getId())){
                                    if(op.getBackstate() != Quantity.STATE_3){
                                        to = false;
                                        break;
                                    }
                                }
                            }

                            if(to){
                                sellerMember.setGoodsbanlance(sellerMember.getGoodsbanlance().add(order.getFreight()));
                                if(penaltyPay.compareTo(Quantity.BIG_DECIMAL_0) == 1 && orderProductBack.getAdminstate() == Quantity.STATE_0) {
                                    //部分退货 如果违约金大于0且是买家原因违约 加到卖家冻结余额去
                                    returnAllAndAddPenaltyPay(orderProductBack, order, sellerMember, forwardsalesmargin, penaltyPay, tranTime, salerCapitals, orderPay);
                                }
                            }else{
                                if(penaltyPay.compareTo(Quantity.BIG_DECIMAL_0) == 1 && orderProductBack.getAdminstate() == Quantity.STATE_0) {
                                    //部分退货 如果违约金大于0且是买家原因违约 加到卖家冻结余额去
                                    returnAllAndAddPenaltyPay(orderProductBack, order, sellerMember, forwardsalesmargin, penaltyPay, tranTime, salerCapitals, orderPay);
                                }
                            }

                        }
                    }
                    //远期余款 没有部分退货
                    if(orderProductBack.getBacktype() == Quantity.STATE_2){
                        logger.info("远期余款没有 部分退货");
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
                ordersService.insertSallerCapitalNew(salerCapitals);
            }

            if (statementList.size()>0){
                statementService.insertStatementAll(statementList);
            }
        }
    }

    private void createBuyerPenaltyPay(Orders order, BigDecimal penaltyPay, Date tranTime, List<BuyerCapital> buyerCapitals, BigDecimal orderPay) {
        BuyerCapital buyerCapital2;
        if(penaltyPay.compareTo(Quantity.BIG_DECIMAL_0)>0) {
            buyerCapital2 = createBuyerPenaltyPay(order, penaltyPay, tranTime, Quantity.STATE_6, orderPay, Quantity.BUYER_BACK_REASON);
            buyerCapital2.setPaytype(order.getPaytype());
            buyerCapitals.add(buyerCapital2);
        }
    }

    private void partReturn(OrderProductBack orderProductBack, OrderProduct orderProduct, Orders order, Member sellerMember, BigDecimal forwardsalesmargin, BigDecimal penaltyPay, Date tranTime, List<SalerCapital> salerCapitals, BigDecimal orderPay) {
        if(orderProductBack.getBacktype() == Quantity.STATE_2){
            //兼容分单之前的数据
            if(penaltyPay.compareTo(Quantity.BIG_DECIMAL_0) == 1 && orderProductBack.getAdminstate() == Quantity.STATE_0) {
                //部分退货 如果违约金大于0且是买家原因违约 加到卖家冻结余额去
                returnAllAndAddPenaltyPay(orderProductBack, order, sellerMember, forwardsalesmargin, penaltyPay, tranTime, salerCapitals, orderPay);
            }else{
                //新数据
                //查询订单中是否还有正常的商品，如果有运费不处理，如果没有则运费加入卖家余额
                List<OrderProduct> orderProductList = orderProductServices.getOrderProductByOrderId(order.getId());
                boolean to = true;
                for(OrderProduct op : orderProductList){
                    if(!op.getId().equals(orderProduct.getId())){
                        if(op.getBackstate() != Quantity.STATE_3){
                            to = false;
                            break;
                        }
                    }
                }

                if(to){
                    if(penaltyPay.compareTo(Quantity.BIG_DECIMAL_0) == 1 && orderProductBack.getAdminstate() == Quantity.STATE_0) {
                        //部分退货 如果违约金大于0且是买家原因违约 加到卖家冻结余额去
                        returnAllAndAddPenaltyPay(orderProductBack, order, sellerMember, forwardsalesmargin, penaltyPay, tranTime, salerCapitals, orderPay);
                    }
                }else{
                    if(penaltyPay.compareTo(Quantity.BIG_DECIMAL_0) == 1 && orderProductBack.getAdminstate() == Quantity.STATE_0) {
                        //部分退货 如果违约金大于0且是买家原因违约 加到卖家冻结余额去
                        returnAllAndAddPenaltyPay(orderProductBack, order, sellerMember, forwardsalesmargin, penaltyPay, tranTime, salerCapitals, orderPay);
                    }
                }

            }
        }
    }


    private void returnAllAndAddPenaltyPay(OrderProductBack orderProductBack, Orders order, Member sellerMember, BigDecimal forwardsalesmargin, BigDecimal penaltyPay, Date tranTime, List<SalerCapital> salerCapitals, BigDecimal orderPay) {
        SalerCapital salerCapital2;
        if(penaltyPay.compareTo(Quantity.BIG_DECIMAL_0) == 1 && (orderProductBack.getAdminstate() == Quantity.STATE_0)) {
            BigDecimal sellerPenaltyPayAllReturn = penaltyPay.multiply(forwardsalesmargin).setScale(2, BigDecimal.ROUND_HALF_UP);
            if (sellerPenaltyPayAllReturn.compareTo(Quantity.BIG_DECIMAL_0) > 0) {
                sellerMember.setSellerfreezebanlance(sellerMember.getSellerfreezebanlance().add(sellerPenaltyPayAllReturn));
                salerCapital2 = createSalerPenaltyPay(order, sellerPenaltyPayAllReturn, tranTime, Quantity.STATE_6, orderPay, Quantity.BUYER_BACK_REASON);
                salerCapitals.add(salerCapital2);
            }
        }
    }


    /**
     * 退款操作
     *
     * @param operator 操作人
     * @param orderProductBack
     * @param orderProduct
     */
//    public void handleBackGoods(Member operator, OrderProductBack orderProductBack, OrderProduct orderProduct, Orders order) throws CashException {
//
//        Member oldOperatro = new Member();
//        BeanUtils.copyProperties(operator,oldOperatro);
//
//        //违约金占比
//        TransactionSetting transactionSetting = transactionSettingService.getTransactionSetting();
//        BigDecimal getLiquidated = transactionSetting.getGetliquidated().multiply(new BigDecimal(0.01));
//        BigDecimal forwardsalesmargin = transactionSetting.getForwardsalesmargin().multiply(new BigDecimal(0.01));
//
//        if (orderProductBack != null && orderProduct != null && order != null) {
//            //退款申请人
//            Member buyer = memberService.getMemberById(orderProductBack.getMemberid());
//            Member oldBuyer = new Member();
//            BeanUtils.copyProperties(buyer,oldBuyer);
//
//
//            BigDecimal backPay = new BigDecimal(0);
//            BigDecimal penaltyPay = new BigDecimal(0);
//
//            Date tranTime = new Date();
//            List<BuyerCapital> buyerCapitals = new ArrayList<BuyerCapital>();
//            List<SalerCapital> salerCapitals = new ArrayList<SalerCapital>();
//            //买家退款资金明细
//            BuyerCapital buyerCapital1 = null;
//            //买家违约资金明细
//            BuyerCapital buyerCapital2 = null;
//            //卖家退款资金明细
//            SalerCapital salerCapital1 = null;
//            //卖家违约资金明细
//            SalerCapital salerCapital2 = null;
//            //立即发货,只有立即发货有部分退款
//            if (orderProduct.getProtype() == Quantity.STATE_0) {
//                BigDecimal orderPay = orderProduct.getActualpayment().subtract(orderProduct.getFreight());
//                //是否有部分退货的情况
//                BigDecimal backPayMoney = orderProductBack.getBackmoney();
//                //违约金
//                penaltyPay = orderPay.multiply(getLiquidated);
//                //退回的金额,异议扣违约金
//                if (orderProductBack.getAdminstate() == Quantity.STATE_2) {
//                    backPay = backPayMoney.subtract(penaltyPay);
//                } else {
//                    backPay = backPayMoney;
//                }
//                //判断退回到余额还是授信
//                BuyerCapital buyerCapital = ordersService.getBuyerCapitalByNoType(orderProduct.getOrderno(), Quantity.STATE_0);
//                if (buyerCapital != null) {
//                    //退回到余额
//                    if (buyerCapital.getPaytype() == Quantity.STATE_3) {
//                        buyer.setBalance(buyer.getBalance().add(backPay));
//                        buyerCapital1 = createBuyerBackPay(order, backPay, tranTime, Quantity.STATE_3);
//                        salerCapital1 = createSalerBackPay(order, backPay, tranTime);
//                        buyerCapitals.add(buyerCapital1);
//                        salerCapitals.add(salerCapital1);
//                    }
//                    //退回到授信
//                    if (buyerCapital.getPaytype() == Quantity.STATE_4) {
//                        buyer.setAvailablelimit(buyer.getAvailablelimit().add(backPay));
//                        buyer.setUsedlimit(buyer.getUsedlimit().subtract(backPay));
//                        buyerCapital1 = createBuyerBackPay(order, backPay, tranTime, Quantity.STATE_4);
//                        salerCapital1 = createSalerBackPay(order, backPay, tranTime);
//                        buyerCapitals.add(buyerCapital1);
//                        salerCapitals.add(salerCapital1);
//                    }
//                    //退回到支付宝\微信\银行卡
//                    if(buyerCapital.getPaytype()==Quantity.STATE_0||buyerCapital.getPaytype()==Quantity.STATE_1||buyerCapital.getPaytype()==Quantity.STATE_2){
//                        String uuid = order.getUuid();
//                        Long ordertime = order.getOrdertime();
//                        if(uuid!=null){
//                            Refund refund = new Refund();
//                            refund.setDatetime(ordertime);
//                            refund.setOutTradeNo(uuid);
//                            if(order.getPaytype()==Quantity.STATE_0){
//                                refund.setChannel("alipay");
//                            }else if(order.getPaytype()==Quantity.STATE_1){
//                                refund.setChannel("wxpay");
//                            }else {
//                                refund.setChannel("bank");
//                            }
//                            refund.setRefundAmount((backPay.multiply(new BigDecimal(100))).longValue());
//                            refund.setRefundReason("订单退款");
//                            List<Orders> ordersList = ordersService.getOrdersByUUID(uuid);
//                            BigDecimal totalAmout = new BigDecimal(0);
//                            for(Orders od : ordersList){
//                                totalAmout = totalAmout.add(od.getActualpayment());
//                            }
//                            refund.setTotalAmount((totalAmout.multiply(new BigDecimal(100))).longValue());
//                            boolean result = false;
//                            try {
//                                if("alipay".equals(refund.getChannel())){
//                                    result = alipayService.refund(refund);
//                                }else if("wxpay".equals(refund.getChannel())){
//                                    result = wxPayService.refund(refund);
//                                }else {
//                                    result = abcService.refund(refund);
//                                }
//                            }catch (Exception e){
//
//                            }
//
//                            System.out.println("退货通道："+refund.getChannel()+"退货结果："+result);
//                            if(result){
//                                if(order.getPaytype()==Quantity.STATE_0){
//                                    buyerCapital1 = createBuyerBackPay(order, backPay, tranTime, Quantity.STATE_0);
//                                }else if(order.getPaytype()==Quantity.STATE_1){
//                                    buyerCapital1 = createBuyerBackPay(order, backPay, tranTime, Quantity.STATE_1);
//                                }else {
//                                    buyerCapital1 = createBuyerBackPay(order, backPay, tranTime, Quantity.STATE_2);
//                                }
//                                salerCapital1 = createSalerBackPay(order, backPay, tranTime);
//                                buyerCapitals.add(buyerCapital1);
//                                salerCapitals.add(salerCapital1);
//                            }
//                        }
//                    }
//                    //异议扣违约金,记录资金明细
//                    if (orderProductBack.getAdminstate() == Quantity.STATE_2) {
//                        operator.setSellerbanlance(operator.getSellerbanlance().add(penaltyPay.multiply(forwardsalesmargin)));
//                        //违约金
//                        buyerCapital2 = createBuyerPenaltyPay(order, penaltyPay, tranTime, Quantity.STATE_6, orderPay, Quantity.BUYER_BACK_REASON);
//                        salerCapital2 = createSalerPenaltyPay(order, penaltyPay, tranTime, Quantity.STATE_6, orderPay, Quantity.BUYER_BACK_REASON);
//                        buyerCapitals.add(buyerCapital2);
//                        salerCapitals.add(salerCapital2);
//                    }
//                }
//            }
//            //远期全款
//            if (orderProduct.getProtype() == Quantity.STATE_1) {
//                //计算扣除违约金
//                BigDecimal orderPay = orderProduct.getAllpay();
//                //违约金
//                penaltyPay = orderPay.multiply(getLiquidated);
//                //退回的金额,异议扣违约金
//                if (orderProductBack.getAdminstate() == Quantity.STATE_2) {
//                    backPay = orderPay.subtract(penaltyPay);
//                } else {
//                    backPay = orderPay;
//                }
//
//                //判断退回到余额还是授信
//                BuyerCapital buyerCapital = ordersService.getBuyerCapitalByNoType(orderProduct.getOrderno(), Quantity.STATE_9);
//                if (buyerCapital != null) {
//                    //退回到余额
//                    if (buyerCapital.getPaytype() == Quantity.STATE_3) {
//                        buyer.setBalance(buyer.getBalance().add(backPay));
//                        buyerCapital1 = createBuyerBackPay(order, backPay, tranTime, Quantity.STATE_3);
//                        salerCapital1 = createSalerBackPay(order, backPay, tranTime);
//                        buyerCapitals.add(buyerCapital1);
//                        salerCapitals.add(salerCapital1);
//                    }
//                    //退回到授信
//                    if (buyerCapital.getPaytype() == Quantity.STATE_4) {
//                        buyer.setAvailablelimit(buyer.getAvailablelimit().add(backPay));
//                        buyer.setUsedlimit(buyer.getUsedlimit().subtract(backPay));
//                        buyerCapital1 = createBuyerBackPay(order, backPay, tranTime, Quantity.STATE_4);
//                        salerCapital1 = createSalerBackPay(order, backPay, tranTime);
//                        buyerCapitals.add(buyerCapital1);
//                        salerCapitals.add(salerCapital1);
//                    }
//                    //退回到支付宝或微信
//                    if(buyerCapital.getPaytype()==Quantity.STATE_0||buyerCapital.getPaytype()==Quantity.STATE_1||buyerCapital.getPaytype()==Quantity.STATE_2){
//                        String uuid = order.getUuid();
//                        Long ordertime = order.getOrdertime();
//                        if(uuid!=null){
//                            Refund refund = new Refund();
//                            refund.setOutTradeNo(uuid);
//                            refund.setDatetime(ordertime);
//                            if(order.getPaytype()==Quantity.STATE_0){
//                                refund.setChannel("alipay");
//                            }else if(order.getPaytype()==Quantity.STATE_1){
//                                refund.setChannel("wxpay");
//                            }else {
//                                refund.setChannel("bank");
//                            }
//                            refund.setRefundAmount((backPay.multiply(new BigDecimal(100))).longValue());
//                            refund.setRefundReason("订单退款");
//                            List<Orders> ordersList = ordersService.getOrdersByUUID(uuid);
//                            BigDecimal totalAmout = new BigDecimal(0);
//                            for(Orders od : ordersList){
//                                totalAmout = totalAmout.add(od.getActualpayment());
//                            }
//                            refund.setTotalAmount((totalAmout.multiply(new BigDecimal(100))).longValue());
//                            boolean result = false;
//                            try {
//                                if("alipay".equals(refund.getChannel())){
//                                    result = alipayService.refund(refund);
//                                }else if("wxpay".equals(refund.getChannel())){
//                                    result = wxPayService.refund(refund);
//                                }else {
//                                    result = abcService.refund(refund);
//                                }
//                            }catch (Exception e){
//
//                            }
//                            if(result){
//                                if(order.getPaytype()==Quantity.STATE_0){
//                                    buyerCapital1 = createBuyerBackPay(order, backPay, tranTime, Quantity.STATE_0);
//                                }else if(order.getPaytype()==Quantity.STATE_1){
//                                    buyerCapital1 = createBuyerBackPay(order, backPay, tranTime, Quantity.STATE_1);
//                                }else {
//                                    buyerCapital1 = createBuyerBackPay(order, backPay, tranTime, Quantity.STATE_2);
//                                }
//                                salerCapital1 = createSalerBackPay(order, backPay, tranTime);
//                                buyerCapitals.add(buyerCapital1);
//                                salerCapitals.add(salerCapital1);
//                            }
//
//                        }
//                    }
//                    //异议扣违约金,记录资金明细
//                    if (orderProductBack.getAdminstate() == Quantity.STATE_2) {
//                        operator.setSellerbanlance(operator.getSellerbanlance().add(penaltyPay.multiply(forwardsalesmargin)));
//                        //违约金
//                        buyerCapital2 = createBuyerPenaltyPay(order, penaltyPay, tranTime, Quantity.STATE_6, orderPay, Quantity.BUYER_BACK_REASON);
//                        salerCapital2 = createSalerPenaltyPay(order, penaltyPay, tranTime, Quantity.STATE_6, orderPay, Quantity.BUYER_BACK_REASON);
//                        buyerCapitals.add(buyerCapital2);
//                        salerCapitals.add(salerCapital2);
//                    }
//                }
//
//            }
//            //远期余款
//            if (orderProduct.getProtype() == Quantity.STATE_2) {
//                //计算扣除违约金
//                BigDecimal orderPay = orderProduct.getActualpayment().subtract(orderProduct.getFreight());
//                BigDecimal partPay = orderProduct.getPartpay().subtract(orderProduct.getFreight());
//                BigDecimal yuPay = orderProduct.getYupay();
//
//
//                //违约金
//                penaltyPay = orderPay.multiply(getLiquidated);
//
//                //定金支付明细
//                BuyerCapital depositBuyerCapital = ordersService.getBuyerCapitalByNoType(order.getOrderno(), Quantity.STATE_7);
//
//                if (depositBuyerCapital != null) {
//
//                    //异议扣违约金
//                    //退回的金额,异议扣违约金
//                    if (orderProductBack.getAdminstate() == Quantity.STATE_2) {
//                        backPay = orderPay.subtract(penaltyPay);
//                        yuPay = yuPay.subtract(penaltyPay);
//                    } else {
//                        backPay = orderPay;
//                    }
//                    //退回到余额
//                    if (depositBuyerCapital.getPaytype() == Quantity.STATE_3) {
//                        buyer.setBalance(buyer.getBalance().add(backPay));
//                        buyerCapital1 = createBuyerBackPay(order, backPay, tranTime, Quantity.STATE_3);
//                        salerCapital1 = createSalerBackPay(order, backPay, tranTime);
//                        buyerCapitals.add(buyerCapital1);
//                        salerCapitals.add(salerCapital1);
//                    }
//                    //退回到授信
//                    if (depositBuyerCapital.getPaytype() == Quantity.STATE_4) {
//                        buyer.setAvailablelimit(buyer.getAvailablelimit().add(backPay));
//                        buyer.setUsedlimit(buyer.getUsedlimit().subtract(backPay));
//                        buyerCapital1 = createBuyerBackPay(order, backPay, tranTime, Quantity.STATE_4);
//                        salerCapital1 = createSalerBackPay(order, backPay, tranTime);
//                        buyerCapitals.add(buyerCapital1);
//                        salerCapitals.add(salerCapital1);
//                    }
//                    //退回到支付宝或微信
//                    if(depositBuyerCapital.getPaytype()==Quantity.STATE_0||depositBuyerCapital.getPaytype()==Quantity.STATE_1||depositBuyerCapital.getPaytype()==Quantity.STATE_2){
//                        String uuid = order.getUuid();
//                        String yuuuid = order.getYuuuid();
//                        Long ordertime = order.getOrdertime();
//                        Long yuordertime = order.getYuordertime();
//                        if(uuid!=null&&yuuuid!=null&&ordertime!=null&&yuordertime!=null){
//                            //定金
//                            Refund refund1 = new Refund();
//                            //余款
//                            Refund refund2 = new Refund();
//                            refund1.setOutTradeNo(uuid);
//                            refund1.setDatetime(ordertime);
//                            refund2.setOutTradeNo(yuuuid);
//                            refund2.setDatetime(yuordertime);
//
//                            if(order.getPaytype()==Quantity.STATE_0){
//                                refund1.setChannel("alipay");
//                                refund2.setChannel("alipay");
//                            }else if(order.getPaytype()==Quantity.STATE_1){
//                                refund1.setChannel("wxpay");
//                                refund2.setChannel("wxpay");
//                            }else {
//                                refund1.setChannel("bank");
//                                refund2.setChannel("bank");
//                            }
//                            refund1.setRefundAmount((partPay.multiply(new BigDecimal(100))).longValue());
//                            refund2.setRefundAmount((yuPay.multiply(new BigDecimal(100))).longValue());
//
//                            refund1.setRefundReason("订单定金退款");
//                            refund2.setRefundReason("订单余款退款");
//                            List<Orders> ordersList = ordersService.getOrdersByUUID(uuid);
//                            BigDecimal totalPartPay = new BigDecimal(0);
//                            BigDecimal totalYuPay = new BigDecimal(0);
//                            for(Orders od : ordersList){
//                                totalPartPay = totalPartPay.add(od.getDeposit());
//                                totalYuPay = totalYuPay.add(od.getBalance());
//                            }
//                            refund1.setTotalAmount((totalPartPay.multiply(new BigDecimal(100))).longValue());
//                            refund2.setTotalAmount((totalYuPay.multiply(new BigDecimal(100))).longValue());
//
//                            boolean result1 = false;
//                            boolean result2 = false;
//                            try {
//                                if("alipay".equals(refund1.getChannel())){
//                                    result1 = alipayService.refund(refund1);
//                                    result2 = alipayService.refund(refund2);
//                                }else if("wxpay".equals(refund1.getChannel())){
//                                    result1 = wxPayService.refund(refund1);
//                                    result2 = wxPayService.refund(refund2);
//                                }else {
//                                    result1 = abcService.refund(refund1);
//                                    result2 = abcService.refund(refund2);
//                                }
//                            }catch (Exception e){
//
//                            }
//                            if(result1&&result2){
//                                if(order.getPaytype()==Quantity.STATE_0){
//                                    buyerCapital1 = createBuyerBackPay(order, backPay, tranTime, Quantity.STATE_0);
//                                }else if(order.getPaytype()==Quantity.STATE_1){
//                                    buyerCapital1 = createBuyerBackPay(order, backPay, tranTime, Quantity.STATE_1);
//                                }else {
//                                    buyerCapital1 = createBuyerBackPay(order, backPay, tranTime, Quantity.STATE_2);
//                                }
//                                salerCapital1 = createSalerBackPay(order, backPay, tranTime);
//                                buyerCapitals.add(buyerCapital1);
//                                salerCapitals.add(salerCapital1);
//                            }
//
//                        }
//                    }
//                    //异议扣违约金,记录资金明细
//                    if (orderProductBack.getAdminstate() == Quantity.STATE_2) {
//                        operator.setSellerbanlance(operator.getSellerbanlance().add(penaltyPay.multiply(forwardsalesmargin)));
//                        //违约金
//                        buyerCapital2 = createBuyerPenaltyPay(order, penaltyPay, tranTime, Quantity.STATE_6, orderPay, Quantity.BUYER_BACK_REASON);
//                        salerCapital2 = createSalerPenaltyPay(order, penaltyPay, tranTime, Quantity.STATE_6, orderPay, Quantity.BUYER_BACK_REASON);
//                        buyerCapitals.add(buyerCapital2);
//                        salerCapitals.add(salerCapital2);
//                    }
//                }
//            }
//
//
//            //保存用户余额和授信
//            ordersService.saveMember(buyer,oldBuyer);
//            ordersService.saveMember(operator,oldOperatro);
//
//
//            if(buyerCapitals.size()>0){
//                ordersService.insertBuyerCapital(buyerCapitals);
//                //保存操作日志
//                OperateLog operateLog = new OperateLog();
//                operateLog.setContent("退款成功");
//                operateLog.setOpid(operator.getId());
//                operateLog.setOpname(operator.getUsername());
//                operateLog.setOptime(new Date());
//                operateLog.setOptype(Quantity.STATE_1);
//                operateLog.setOrderid(orderProductBack.getOrderid());
//                operateLog.setOrderno(orderProductBack.getOrderno());
//                operateLog.setOrderpdid(orderProduct.getId());
//                ordersService.saveOperatelog(operateLog);
//            }
//            if(salerCapitals.size()>0){
//                ordersService.insertSallerCapital(salerCapitals);
//            }
//        }
//
//
//
//    }

    /**
     * 取消订单
     *
     * @param model
     * @param id
     * @param type
     * @return
     */
    @RequestMapping(value = "/cancelOrders", method = RequestMethod.POST)
    @ApiOperation(value = "取消订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "订单id", required = true, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "type", value = "0=买家取消订单1=卖家取消订单", required = true, paramType = "query", dataType = "int"),
    })
    public BasicRet cancelOrders(Model model, Long id, int type, HttpServletRequest request) throws CashException, WxPayException, AlipayApiException {
        TransactionSetting transactionSetting = transactionSettingService.getTransactionSetting();
        BasicRet basicRet = new BasicRet();
        Member memberSession = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        Member member = memberService.getMemberById(memberSession.getId());
        Member oldMember = new Member();
        BeanUtils.copyProperties(member,oldMember);

        Orders orders = ordersService.getSingleOrder(id);
        Member buyer = memberService.getMemberById(orders.getMemberid());
        Member oldBuyer = new Member();
        BeanUtils.copyProperties(buyer,oldBuyer);

        //买家获取到退款违约金金额
        BigDecimal buyerPenaltyPay = Quantity.BIG_DECIMAL_0;

        //退款金额
        BigDecimal backPay = orders.getActualpayment();

        //违约金
        BigDecimal penaltyPay = (orders.getActualpayment().subtract(orders.getFreight())).multiply(transactionSetting.getGetliquidated().multiply(new BigDecimal(0.01))).setScale(2,BigDecimal.ROUND_HALF_UP);

        //被违约方获取违约金占比
        BigDecimal forwardsalesmargin = transactionSetting.getForwardsalesmargin().multiply(new BigDecimal(0.01));
        List<OrderProduct> orderProducts = ordersService.getOrderProductByOrderId(id);
        Short productType = orderProducts.get(0).getProducttype();
        //不是紧固件，违约金为0
        if(productType==Quantity.STATE_2){
            penaltyPay = new BigDecimal(0);
        }

        //买家应得到的违约金金额
        buyerPenaltyPay = penaltyPay.multiply(forwardsalesmargin).setScale(2, BigDecimal.ROUND_HALF_UP);


        if (orders != null) {
            //没有支付
            if (orders.getOrderstatus() == Quantity.STATE_0) {
                //订单关闭
                orders.setOrderstatus(Quantity.STATE_7);
                ordersService.updateSingleOrder(orders);
                //删除开票申请记录
                ordersService.deleteBillRecord(orders.getOrderno());
                //已支付,未发货
            } else if (ordersService.canCancelOrdersSeller(orders)) {

                Date tranTime = new Date();
                List<BuyerCapital> buyerCapitals = new ArrayList<BuyerCapital>();
                List<SalerCapital> salerCapitals = new ArrayList<SalerCapital>();
                List<BuyerStatement> statementList=new ArrayList<>();
                //买家退款资金明细
                BuyerCapital buyerCapital1 = null;
                //买家违约资金明细
                BuyerCapital buyerCapital2 = null;
                //卖家退款资金明细
                SalerCapital salerCapital1 = null;
                //卖家违约资金明细
                SalerCapital salerCapital2 = null;
                //客户下单对账单
                BuyerStatement buyerStatement=null;
                //订单的支付明细
                BuyerCapital buyerCapital=null;
                boolean isDeposit=false;
                //立即发货
                if (orders.getOrdertype() == Quantity.STATE_0) {

                    //判断退回到余额还是授信
                    buyerCapital = ordersService.getBuyerCapitalByNoType(orders.getOrderno(), Quantity.STATE_0);
                    if (buyerCapital != null) {


                        if(productType==Quantity.STATE_1){
                            BigDecimal sellerBanlance = member.getSellerbanlance();
                            if (sellerBanlance.compareTo(penaltyPay) == -1) {
                                basicRet.setResult(BasicRet.ERR);
                                basicRet.setMessage("余额不够，不能取消");
                                return basicRet;
                            }

                            if(buyerPenaltyPay.compareTo(Quantity.BIG_DECIMAL_0) >0){
                                buyerCapital2 = createBuyerPenaltyPay(orders, buyerPenaltyPay, tranTime, Quantity.STATE_10, backPay, Quantity.SELLER_CANCEL_REASON);
                                buyerCapitals.add(buyerCapital2);
                            }

                            if(penaltyPay.compareTo(Quantity.BIG_DECIMAL_0) >0) {
                                salerCapital2 = createSalerPenaltyPay(orders, penaltyPay, tranTime, Quantity.STATE_7, backPay, Quantity.SELLER_CANCEL_REASON);
                                salerCapitals.add(salerCapital2);
                            }
                        }


                        //从卖家账号扣除违约金
                        member.setSellerbanlance(member.getSellerbanlance().subtract(penaltyPay).setScale(2,BigDecimal.ROUND_HALF_UP));

                        //退回到余额
                        if (buyerCapital.getPaytype() == Quantity.STATE_3) {
                            buyer.setBalance(buyer.getBalance().add(backPay).add(buyerPenaltyPay).setScale(2,BigDecimal.ROUND_HALF_UP));

                            buyerCapital1 = createBuyerBackPay(orders, backPay, tranTime, Quantity.STATE_3);
                            salerCapital1 = createSalerBackPay(orders, backPay, tranTime);
                            buyerCapitals.add(buyerCapital1);
                            salerCapitals.add(salerCapital1);
                        }



                        //退回到授信
                        if (buyerCapital.getPaytype() == Quantity.STATE_4) {
                            buyer.setBalance(buyer.getBalance().add(buyerPenaltyPay).setScale(2, BigDecimal.ROUND_HALF_UP));
//                            if(buyerPenaltyPay.compareTo(Quantity.BIG_DECIMAL_0)>0) {
//                                buyer.setBalance(buyer.getBalance().add(buyerPenaltyPay).setScale(2, BigDecimal.ROUND_HALF_UP));
//                                buyerCapital2 = createBuyerBackPay(orders, buyerPenaltyPay, tranTime, Quantity.STATE_3);
//                                buyerCapitals.add(buyerCapital2);
//                            }

                            buyer.setAvailablelimit(buyer.getAvailablelimit().add(backPay));
                            buyer.setUsedlimit(buyer.getUsedlimit().subtract(backPay));
                            buyerCapital1 = createBuyerBackPay(orders, backPay, tranTime, Quantity.STATE_4);
                            salerCapital1 = createSalerBackPay(orders, backPay, tranTime);
                            buyerCapitals.add(buyerCapital1);
                            salerCapitals.add(salerCapital1);
                        }


                        //退回到支付宝或微信
                        if(buyerCapital.getPaytype()==Quantity.STATE_0||buyerCapital.getPaytype()==Quantity.STATE_1){
                            buyer.setBalance(buyer.getBalance().add(buyerPenaltyPay));
//                            if(buyerPenaltyPay.compareTo(Quantity.BIG_DECIMAL_0)>0) {
//                                buyer.setBalance(buyer.getBalance().add(buyerPenaltyPay));
//                                buyerCapital2 = createBuyerBackPay(orders, buyerPenaltyPay, tranTime, Quantity.STATE_3);
//                                buyerCapitals.add(buyerCapital2);
//                            }

                            String uuid = orders.getUuid();
                            if(uuid!=null){
                                Refund refund = new Refund();
                                refund.setOutTradeNo(uuid);
                                refund.setChannel(tradeService.getPayChannel(orders.getPaytype()));
                                refund.setRefundAmount((backPay.multiply(new BigDecimal(100))).longValue());
                                refund.setRefundReason("订单退款");
                                BigDecimal totalAmout = tradeService.getTotalAmout(uuid);
                                refund.setTotalAmount((totalAmout.multiply(new BigDecimal(100))).longValue());
                                boolean result = tradeService.backMoney(refund);

                                if(result){
                                    if(orders.getPaytype()==Quantity.STATE_0){
                                        buyerCapital1 = createBuyerBackPay(orders, backPay, tranTime, Quantity.STATE_0);
                                    }else if(orders.getPaytype()== Quantity.STATE_1){
                                        buyerCapital1 = createBuyerBackPay(orders, backPay, tranTime, Quantity.STATE_1);
                                    }else if(orders.getPaytype() == Quantity.STATE_2){
                                        buyerCapital1 = createBuyerBackPay(orders, backPay, tranTime, Quantity.STATE_2);
                                    }
                                    salerCapital1 = createSalerBackPay(orders, backPay, tranTime);
                                    buyerCapitals.add(buyerCapital1);
                                    salerCapitals.add(salerCapital1);
                                }else {
                                    throw  new RuntimeException("退款失败");
                                }
                            }
                        }
                    }
                }



                //远期全款
                if (orders.getOrdertype() == Quantity.STATE_1) {
                    if(productType==Quantity.STATE_1){
                        BigDecimal sellerBanlance = member.getSellerbanlance();
                        if (sellerBanlance.compareTo(penaltyPay) == -1) {
                            basicRet.setResult(BasicRet.ERR);
                            basicRet.setMessage("余额不够，不能取消");
                            return basicRet;
                        }


                        if(buyerPenaltyPay.compareTo(Quantity.BIG_DECIMAL_0)>0) {
                            buyerCapital2 = createBuyerPenaltyPay(orders, buyerPenaltyPay, tranTime, Quantity.STATE_10, backPay, Quantity.SELLER_CANCEL_REASON);
                            buyerCapitals.add(buyerCapital2);
                        }

                        if(penaltyPay.compareTo(Quantity.BIG_DECIMAL_0) >0) {
                            salerCapital2 = createSalerPenaltyPay(orders, penaltyPay, tranTime, Quantity.STATE_7, backPay, Quantity.SELLER_CANCEL_REASON);
                            salerCapitals.add(salerCapital2);
                        }
                    }

                    //从卖家账号扣除违约金
                    member.setSellerbanlance(member.getSellerbanlance().subtract(penaltyPay).setScale(2,BigDecimal.ROUND_HALF_UP));

                    //判断退回到余额还是授信
                    buyerCapital = ordersService.getBuyerCapitalByNoType(orders.getOrderno(), Quantity.STATE_9);
                    if (buyerCapital != null) {
                        //退回到余额
                        if (buyerCapital.getPaytype() == Quantity.STATE_3) {
                            buyer.setBalance(buyer.getBalance().add(backPay).add(buyerPenaltyPay).setScale(2,BigDecimal.ROUND_HALF_UP));
                            buyerCapital1 = createBuyerBackPay(orders, backPay, tranTime, Quantity.STATE_3);
                            salerCapital1 = createSalerBackPay(orders, backPay, tranTime);
                            buyerCapitals.add(buyerCapital1);
                            salerCapitals.add(salerCapital1);
                        }


                        //退回到授信
                        if (buyerCapital.getPaytype() == Quantity.STATE_4) {
//                            if(buyerPenaltyPay.compareTo(Quantity.BIG_DECIMAL_0)>0) {
//                                buyer.setBalance(buyer.getBalance().add(buyerPenaltyPay).setScale(2, BigDecimal.ROUND_HALF_UP));
//                                buyerCapital2 = createBuyerBackPay(orders, buyerPenaltyPay, tranTime, Quantity.STATE_3);
//                                buyerCapitals.add(buyerCapital2);
//                            }
                            buyer.setBalance(buyer.getBalance().add(buyerPenaltyPay).setScale(2,BigDecimal.ROUND_HALF_UP));
                            buyer.setAvailablelimit(buyer.getAvailablelimit().add(backPay));
                            buyer.setUsedlimit(buyer.getUsedlimit().subtract(backPay));
                            buyerCapital1 = createBuyerBackPay(orders, backPay, tranTime, Quantity.STATE_4);
                            salerCapital1 = createSalerBackPay(orders, backPay, tranTime);
                            buyerCapitals.add(buyerCapital1);
                            salerCapitals.add(salerCapital1);
                        }


                        //退回到支付宝或微信
                        if(buyerCapital.getPaytype()==Quantity.STATE_0||
                                buyerCapital.getPaytype()==Quantity.STATE_1 ||
                                buyerCapital.getPaytype()== Quantity.STATE_2){
//                            if(buyerPenaltyPay.compareTo(Quantity.BIG_DECIMAL_0)>0) {
//                                buyer.setBalance(buyer.getBalance().add(buyerPenaltyPay.setScale(2, BigDecimal.ROUND_HALF_UP)));
//                                buyerCapital2 = createBuyerBackPay(orders, buyerPenaltyPay, tranTime, Quantity.STATE_3);
//                                buyerCapitals.add(buyerCapital2);
//                            }

                            buyer.setBalance(buyer.getBalance().add(buyerPenaltyPay).setScale(2,BigDecimal.ROUND_HALF_UP));

                            String uuid = orders.getUuid();
                            if(uuid!=null){
                                Refund refund = new Refund();
                                refund.setOutTradeNo(uuid);
                                refund.setChannel(tradeService.getPayChannel(orders.getPaytype()));
                                refund.setRefundAmount((backPay.multiply(new BigDecimal(100))).longValue());
                                refund.setRefundReason("订单退款");
                                BigDecimal totalAmout = tradeService.getTotalAmout(uuid);
                                refund.setTotalAmount((totalAmout.multiply(new BigDecimal(100))).longValue());
                                boolean result = tradeService.backMoney(refund);

                                if(result){
                                    if(orders.getPaytype()==Quantity.STATE_0){
                                        buyerCapital1 = createBuyerBackPay(orders, backPay, tranTime, Quantity.STATE_0);
                                    }else if(orders.getPaytype()==Quantity.STATE_1) {
                                        buyerCapital1 = createBuyerBackPay(orders, backPay, tranTime, Quantity.STATE_1);
                                    }else if(orders.getPaytype() == Quantity.STATE_2){
                                        buyerCapital1 = createBuyerBackPay(orders, backPay, tranTime, Quantity.STATE_2);
                                    }
                                    salerCapital1 = createSalerBackPay(orders, backPay, tranTime);

                                    buyerCapitals.add(buyerCapital1);
                                    salerCapitals.add(salerCapital1);
                                }

                            }
                        }
                    }
                }

                /*
                //远期定金
                if (orders.getOrdertype() == Quantity.STATE_2) {
                    backPay = orders.getDeposit().add(orders.getFreight());
                    if(productType==Quantity.STATE_1){
                        BigDecimal sellerBanlance = member.getSellerbanlance();
                        if (sellerBanlance.compareTo(penaltyPay) == -1) {
                            basicRet.setResult(BasicRet.ERR);
                            basicRet.setMessage("余额不够，不能取消");
                            return basicRet;
                        } else {
                            member.setSellerbanlance(sellerBanlance.subtract(penaltyPay));
                        }
                        buyerCapital2 = createBuyerPenaltyPay(orders, penaltyPay, tranTime, Quantity.STATE_10, backPay, Quantity.SELLER_CANCEL_REASON);
                        salerCapital2 = createSalerPenaltyPay(orders, penaltyPay, tranTime, Quantity.STATE_7, backPay, Quantity.SELLER_CANCEL_REASON);
                        buyerCapitals.add(buyerCapital2);
                        salerCapitals.add(salerCapital2);
                    }
                    //判断退回到余额还是授信
                    BuyerCapital depositBuyerCapital = ordersService.getBuyerCapitalByNoType(orders.getOrderno(), Quantity.STATE_7);
                    if (depositBuyerCapital != null) {
                        //退回到余额
                        if (depositBuyerCapital.getPaytype() == Quantity.STATE_3) {
                            buyer.setBalance(buyer.getBalance().add(backPay).add(penaltyPay.multiply(forwardsalesmargin)));
                            buyerCapital1 = createBuyerBackPay(orders, backPay.add(penaltyPay.multiply(forwardsalesmargin)), tranTime, Quantity.STATE_3);
                            salerCapital1 = createSalerBackPay(orders, backPay.add(penaltyPay.multiply(forwardsalesmargin)), tranTime);
                            buyerCapitals.add(buyerCapital1);
                            salerCapitals.add(salerCapital1);
                        }
                        //退回到授信
                        if (depositBuyerCapital.getPaytype() == Quantity.STATE_4) {
                            buyer.setBalance(buyer.getBalance().add(penaltyPay.multiply(forwardsalesmargin)));
                            buyerCapital2 = createBuyerBackPay(orders, penaltyPay.multiply(forwardsalesmargin), tranTime, Quantity.STATE_3);
                            buyer.setAvailablelimit(buyer.getAvailablelimit().add(backPay));
                            buyer.setUsedlimit(buyer.getUsedlimit().subtract(backPay));
                            buyerCapital1 = createBuyerBackPay(orders, backPay, tranTime, Quantity.STATE_4);
                            salerCapital1 = createSalerBackPay(orders, backPay, tranTime);
                            buyerCapitals.add(buyerCapital2);
                            buyerCapitals.add(buyerCapital1);
                            salerCapitals.add(salerCapital1);
                        }
                        //退回到支付宝或微信
                        if(depositBuyerCapital.getPaytype()==Quantity.STATE_0||depositBuyerCapital.getPaytype()==Quantity.STATE_1){
                            String uuid = orders.getUuid();
                            buyer.setBalance(buyer.getBalance().add(penaltyPay.multiply(forwardsalesmargin)));
                            buyerCapital2 = createBuyerBackPay(orders, penaltyPay.multiply(forwardsalesmargin), tranTime, Quantity.STATE_3);
                            if(uuid!=null){
                                Refund refund = new Refund();
                                refund.setOutTradeNo(uuid);
                                if(orders.getPaytype()==Quantity.STATE_0){
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
                                    throw  e;
                                }
                                if(result){
                                    if(orders.getPaytype()==Quantity.STATE_0){
                                        buyerCapital1 = createBuyerBackPay(orders, backPay, tranTime, Quantity.STATE_0);
                                    }else {
                                        buyerCapital1 = createBuyerBackPay(orders, backPay, tranTime, Quantity.STATE_1);
                                    }
                                    salerCapital1 = createSalerBackPay(orders, backPay, tranTime);
                                    buyerCapitals.add(buyerCapital2);
                                    buyerCapitals.add(buyerCapital1);
                                    salerCapitals.add(salerCapital1);
                                }

                            }
                        }
                    }

                }
               */


                //远期订单已付尾款，等待卖家发货
                if (orders.getOrdertype() == Quantity.STATE_3 && orders.getOrderstatus() == Quantity.STATE_1) {
                    //定金金额
                    BigDecimal partPay = orders.getDeposit();

                    //远期余款
                    BigDecimal yuPay = orders.getBalance();

                    if(productType==Quantity.STATE_1){
                        BigDecimal sellerBanlance = member.getSellerbanlance();
                        if (sellerBanlance.compareTo(penaltyPay) == -1) {
                            basicRet.setResult(BasicRet.ERR);
                            basicRet.setMessage("余额不够，不能取消");
                            return basicRet;
                        }

                        if(buyerPenaltyPay.compareTo(Quantity.BIG_DECIMAL_0)>0) {
                            buyerCapital2 = createBuyerPenaltyPay(orders, buyerPenaltyPay, tranTime, Quantity.STATE_10, backPay, Quantity.SELLER_CANCEL_REASON);
                            buyerCapitals.add(buyerCapital2);
                        }

                        if(penaltyPay.compareTo(Quantity.BIG_DECIMAL_0)>0) {
                            salerCapital2 = createSalerPenaltyPay(orders, penaltyPay, tranTime, Quantity.STATE_7, backPay, Quantity.SELLER_CANCEL_REASON);
                            salerCapitals.add(salerCapital2);
                        }
                    }

                    //从卖家账号扣除违约金
                    member.setSellerbanlance(member.getSellerbanlance().subtract(penaltyPay).setScale(2,BigDecimal.ROUND_HALF_UP));



                    //定金支付明细
                    buyerCapital = ordersService.getBuyerCapitalByNoType(orders.getOrderno(), Quantity.STATE_7);
                    if (buyerCapital != null) {
                        //退回到余额
                        if (buyerCapital.getPaytype() == Quantity.STATE_3) {
                            buyer.setBalance(buyer.getBalance().add(backPay).add(buyerPenaltyPay));
                            buyerCapital1 = createBuyerBackPay(orders, backPay, tranTime, Quantity.STATE_3);
                            salerCapital1 = createSalerBackPay(orders, backPay, tranTime);
                            buyerCapitals.add(buyerCapital1);
                            salerCapitals.add(salerCapital1);
                        }

                        //退回到授信
                        if (buyerCapital.getPaytype() == Quantity.STATE_4) {
                            buyer.setBalance(buyer.getBalance().add(buyerPenaltyPay));

//                            if(buyerPenaltyPay.compareTo(Quantity.BIG_DECIMAL_0)>0) {
//                                buyerCapital2 = createBuyerBackPay(orders, buyerPenaltyPay, tranTime, Quantity.STATE_3);
//                                buyerCapitals.add(buyerCapital2);
//                            }

                            buyer.setAvailablelimit(buyer.getAvailablelimit().add(backPay));
                            buyer.setUsedlimit(buyer.getUsedlimit().subtract(backPay));

                            buyerCapital1 = createBuyerBackPay(orders, backPay, tranTime, Quantity.STATE_4);
                            salerCapital1 = createSalerBackPay(orders, backPay, tranTime);

                            buyerCapitals.add(buyerCapital1);
                            salerCapitals.add(salerCapital1);
                        }
                        //退回到支付宝或微信
                        if(buyerCapital.getPaytype()==Quantity.STATE_0||
                                buyerCapital.getPaytype()==Quantity.STATE_1 ||
                                buyerCapital.getPaytype()== Quantity.STATE_2
                                ){
                            String uuid = orders.getUuid();
                            String yuuuid = orders.getYuuuid();
                            buyer.setBalance(buyer.getBalance().add(buyerPenaltyPay));
                            // buyerCapital2 = createBuyerBackPay(orders, penaltyPay.multiply(forwardsalesmargin), tranTime, Quantity.STATE_3);
                            if(uuid!=null&&yuuuid!=null){
                                //定金
                                Refund refund1 = new Refund();
                                //余款
                                Refund refund2 = new Refund();
                                refund1.setOutTradeNo(uuid);
                                refund2.setOutTradeNo(yuuuid);
//                                if(orders.getPaytype()==Quantity.STATE_0){
//                                    refund1.setChannel("alipay");
//                                    refund2.setChannel("alipay");
//                                }else if(orders.getPaytype() == Quantity.STATE_1){
//                                    refund1.setChannel("wxpay");
//                                    refund2.setChannel("wxpay");
//                                }else if(orders.getPaytype() == Quantity.STATE_2){
//                                    refund1.setChannel("bank");
//                                    refund2.setChannel("bank");
//                                }
                                refund1.setChannel(tradeService.getPayChannel(orders.getPaytype()));
                                refund2.setChannel(tradeService.getPayChannel(orders.getPaytype()));

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

                                boolean result1 = tradeService.backMoney(refund1);
                                boolean result2 = tradeService.backMoney(refund2);
//                                try {
//                                    if("alipay".equals(refund1.getChannel())){
//                                        result1 = alipayService.refund(refund1);
//                                        result2 = alipayService.refund(refund2);
//                                    }else if("wxpay".equals(refund1.getChannel())){
//                                        result1 = wxPayService.refund(refund1);
//                                        result2 = wxPayService.refund(refund2);
//                                    }else if("bank".equals(refund1.getChannel())){
//                                        result1 = abcService.refund(refund1);
//                                        result2 = abcService.refund(refund2);
//                                    }
//                                }catch (Exception e){
//                                    throw  e;
//                                }
                                if(result1&&result2){
                                    if(orders.getPaytype()==Quantity.STATE_0){
                                        buyerCapital1 = createBuyerBackPay(orders, backPay, tranTime, Quantity.STATE_0);
                                    }else if(orders.getPaytype() == Quantity.STATE_1){
                                        buyerCapital1 = createBuyerBackPay(orders, backPay, tranTime, Quantity.STATE_1);
                                    }else if(orders.getPaytype()== Quantity.STATE_2){
                                        buyerCapital1 = createBuyerBackPay(orders, backPay, tranTime, Quantity.STATE_2);
                                    }
                                    salerCapital1 = createSalerBackPay(orders, backPay, tranTime);
                                    buyerCapitals.add(buyerCapital1);
                                    salerCapitals.add(salerCapital1);
                                }else {
                                    throw new  RuntimeException("退款失败");
                                }
                            }
                        }
                    }
                }
                Member member1=memberService.getMemberById(orders.getMemberid());
                if (buyerCapital.getPaytype() == Quantity.STATE_3&&productType==Quantity.STATE_1){
                    //插入一条违约金的对账单明细
                    buyerStatement=ordersService.createBuyerStateForBack(orders,buyerPenaltyPay,new Date(),buyerCapital.getPaytype(), (short) StatementType.StType4.getTyep(),member1,null,false);
                    statementList.add(buyerStatement);
                    //余额与授信不插入退款记录
                }
                if (buyerCapital.getPaytype() == Quantity.STATE_4&&productType==Quantity.STATE_1){
                    //插入一条违约金的对账单明细
                    buyerStatement=ordersService.createBuyerStateForBack(orders,buyerPenaltyPay,new Date(),buyerCapital.getPaytype(), (short) StatementType.StType4.getTyep(),member1,null,false);
                    statementList.add(buyerStatement);
                    //余额与授信不插入退款记录
                }
                if (buyerCapital.getPaytype() == Quantity.STATE_0
                        || buyerCapital.getPaytype() == Quantity.STATE_1
                        || buyerCapital.getPaytype() == Quantity.STATE_2){
                    if(productType==Quantity.STATE_1){
                        //插入一条违约金的对账单明细
                        buyerStatement=ordersService.createBuyerStateForBack(orders,buyerPenaltyPay,new Date(),buyerCapital.getPaytype(), (short) StatementType.StType4.getTyep(),member1,null,false);
                        statementList.add(buyerStatement);
                    }
                    //插入一条退款对账单明细
                    buyerStatement=ordersService.createBuyerStateForBack(orders,backPay,new Date(),buyerCapital.getPaytype(), (short) StatementType.StType5.getTyep(),member1,null,false);
                    statementList.add(buyerStatement);
                }
                //保存用户余额和授信
                ordersService.saveMember(buyer,oldBuyer);
                ordersService.saveMember(member,oldMember);
                ordersService.insertBuyerCapital(buyerCapitals);
                ordersService.insertSallerCapital(salerCapitals);
                if (statementList.size()>0){
                    statementService.insertStatementAll(statementList);
                }
            } else {
                basicRet.setResult(BasicRet.ERR);
                basicRet.setMessage("不能取消订单");
                return basicRet;
            }
        } else {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("没有此订单");
            return basicRet;
        }


        if (orders.getIsonline() == Quantity.STATE_0) {
            //加库存
            for (OrderProduct orderProduct : orderProducts) {
//                ProductStore productStore = ordersService.getProductStore(orderProduct.getPdid(),orderProduct.getPdno(), orderProduct.getStoreid());
//                productStore.setPdstorenum(productStore.getPdstorenum().add(orderProduct.getNum()));
//                ordersService.updateProductStore(productStore);
                productStoreService.addStoreNumByPdidAndPdno(orderProduct.getPdid(),orderProduct.getPdno(),orderProduct.getNum());
            }
        }


        if (orders.getIsonline() == Quantity.STATE_2) {
            Map<String, BigDecimal> proMap = new HashMap<String, BigDecimal>();
            //加库存
            for (OrderProduct orderProduct : orderProducts) {
                LimitTimeStore store = shopCarService.getLimitTimeStore(orderProduct.getLimitid(), orderProduct.getPdid(), orderProduct.getPdno());
                LimitTimeProd prod = shopCarService.getLimitTimeProd(orderProduct.getPdid(),orderProduct.getLimitid());
                store.setStorenum(store.getStorenum().add(orderProduct.getNum()));
                store.setSalesnum(store.getSalesnum().subtract(orderProduct.getNum()));
                shopCarService.updateLimitTimeStore(store);
                //商品id进行分组
                BigDecimal num = proMap.get(orderProduct.getPdid()+","+orderProduct.getLimitid());
                if (num == null) {
                    proMap.put(orderProduct.getPdid()+","+orderProduct.getLimitid(), orderProduct.getNum());
                } else {
                    num = num.add(orderProduct.getNum());
                    proMap.put(orderProduct.getPdid()+","+orderProduct.getLimitid(), num);
                }
            }
            //保存总销量
            for (String key : proMap.keySet()) {
                String[] ids = key.split(",");
                LimitTimeProd prod = shopCarService.getLimitTimeProd(Long.parseLong(ids[0]),Long.parseLong(ids[1]));
                prod.setSalestotalnum(prod.getSalestotalnum().subtract(proMap.get(key)));
                shopCarService.updateLimitTimeProd(prod);
            }
        }

        for (OrderProduct orderProduct : orderProducts) {
            //将退货的商品信息记录到orderproductbackinfo表中
            OrderProductBackInfo orderProductBackInfo = new OrderProductBackInfo();
            orderProductBackInfo.setOrderno(orderProduct.getOrderno());
            orderProductBackInfo.setPdid(orderProduct.getPdid());
            orderProductBackInfo.setBackno("TH" + UUID.randomUUID());
            orderProductBackInfo.setBacknum(orderProduct.getNum());
            orderProductBackInfo.setBacktype(Quantity.STATE_1);
            orderProductBackInfo.setBackstate(Quantity.STATE_0);
            orderProductBackInfo.setBacktime(new Date());
            orderProductBackInfoService.addOrderProductBackInfo(orderProductBackInfo);
        }

        //订单关闭
        orders.setOrderstatus(Quantity.STATE_7);
        ordersService.updateSingleOrder(orders);
        //删除开票申请记录
        ordersService.deleteBillRecord(orders.getOrderno());





        // syn wms
        wmsService.cancelOrders(orders, WMSService.CANCEL_ORDER_TYPE);
        ordersService.updateReason(orders,"卖家取消订单");
        //执行订单主动取消，post数据到中间件中间平台
//        ordersService.initiativeOrderCancel(id);

        //保存操作日志
        OperateLog operateLog = new OperateLog();
        operateLog.setContent("取消订单");
        operateLog.setOpid(member.getId());
        operateLog.setOpname(member.getUsername());
        operateLog.setOptime(new Date());
        operateLog.setOptype(Quantity.STATE_0);
        operateLog.setOrderid(orders.getId());
        operateLog.setOrderno(orders.getOrderno());
        ordersService.saveOperatelog(operateLog);


//        memberSession.setBalance(member.getBalance());
//        memberSession.setSellerbanlance(member.getSellerbanlance());
//        memberSession.setSellerfreezebanlance(member.getSellerfreezebanlance());
//        memberSession.setGoodsbanlance(member.getGoodsbanlance());
//        memberSession.setAvailablelimit(member.getAvailablelimit());
//        memberSession.setUsedlimit(member.getUsedlimit());
//        memberSession.setCreditlimit(member.getCreditlimit());
//        model.addAttribute(AppConstant.MEMBER_SESSION_NAME,memberSession);


        //保存用户日志
        memberLogOperator.saveMemberLog(member, null, "订单id：" + id + "取消", "/rest/seller/orders/cancelOrders",request, memberOperateLogService);

        //正式环境启用
        if(profile.equals("prod") || profile.equals("pro")) {
            //卖家取消订单 短信通知买家
            List<Orders> list = new ArrayList<>();
            list.add(orders);
            ordersService.smsNotifySellerCancelOrder(list);
        }

        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("取消订单成功");
        return basicRet;
    }



    @Autowired
    private BuyerCapitalService buyerCapitalService;

    @Autowired
    private BillingRecordService billingRecordService;

    @Autowired
    private SalerCapitalService salerCapitalService;

    @RequestMapping(value = "/updateOrderProductNum",method = RequestMethod.POST)
    @ApiOperation(value = "修改订单商品数量")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderno",value = "退货单号",required = true,paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "orderProductJson",value = "订单产品json [{\"id\":1909,\"num\":0.10}]",required = true,paramType = "query",dataType = "String"),
    })
    public BasicRet updateOrderProductNum(@RequestParam(required = true) String orderno,
                                          @RequestParam(required = true) String orderProductJson,Model model) throws WxPayException, AlipayApiException, CashException, MyException {

        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        TransactionSetting transactionSetting = transactionSettingService.getTransactionSetting();

        Orders orders = ordersService.getOrdersByOrderNo(orderno);


        //运费
        BigDecimal orderFreight = orders.getFreight();
        //订单总价
        BigDecimal orderTotalprice = orders.getActualpayment();
        //实付款
        //BigDecimal orderActualpayment = orders.getActualpayment();

        BigDecimal discountpay=Quantity.BIG_DECIMAL_0;
        BigDecimal totalDiscountpay=Quantity.BIG_DECIMAL_0;


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

        if(!orders.getSaleid().equals(member.getId())){
            return new BasicRet(BasicRet.ERR,"该订单卖家不是你，不可修改");
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

        BigDecimal totalProductMoney = Quantity.BIG_DECIMAL_0;  //所有商品总金额
        BigDecimal totalWeight = Quantity.BIG_DECIMAL_0;  //所有商品总重量
        BigDecimal totalNum = Quantity.BIG_DECIMAL_0; //所有数量

        BigDecimal oldTotalProductMoney = Quantity.BIG_DECIMAL_0;  //所有商品总金额 (没有修改数量和运费前)
        BigDecimal oldTotalWeight = Quantity.BIG_DECIMAL_0;  //所有商品总重量(没有修改数量和运费前)

        List<OrderProductModel> saveOrderProductList = new ArrayList<>();

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

                    ProductStore productStore1 = productStoreService.getProductStore(orderProduct.getPdid(), orderProduct.getPdno(), orders.getStoreid());

                    if (productStore1 == null) {
                        throw new RuntimeException("商品id为" + orderProduct.getPdid() + "的库存信息不存在");
                    }
                    discountpay=orderProduct.getDiscountpay()==null?Quantity.BIG_DECIMAL_0:orderProduct.getDiscountpay();
                    OrderProductModel saveOrderProduct = new OrderProductModel();
                    saveOrderProduct.setId(updateP.getId());
                    saveOrderProduct.setOldProductNum(orderProduct.getNum());

                    if(updateP.getNum().compareTo(Quantity.BIG_DECIMAL_0) ==0){ //全部不发了
                        saveOrderProduct.setNum(updateP.getNum());
                        saveOrderProduct.setPrice(orderProduct.getPrice());
                        saveOrderProduct.setFreight(Quantity.BIG_DECIMAL_0);
                        saveOrderProduct.setActualpayment(Quantity.BIG_DECIMAL_0);
                        saveOrderProduct.setDiscountpay(discountpay);
                        totalNum = totalNum.add(updateP.getNum());
                    }else {
                        saveOrderProduct.setNum(updateP.getNum());
                        saveOrderProduct.setPrice(orderProduct.getPrice());

                        BigDecimal oldActualpayment = orderProduct.getActualpayment();
                        //退货金额计算公式:退货数量x单价-(退货数量/总数量)X产品的优惠金额
                        BigDecimal pdbackNum=orderProduct.getNum().subtract(updateP.getNum());
                        //subtractMoney是退款金额
//                        BigDecimal subtractMoney=new BigDecimal(pdbackNum.multiply(orderProduct.getPrice()).subtract(pdbackNum.divide(orderProduct.getNum(),5,BigDecimal.ROUND_HALF_UP).multiply(discountpay)).toString()).setScale(2,BigDecimal.ROUND_HALF_UP);
                        BigDecimal subtractMoney=new BigDecimal(oldActualpayment.multiply(pdbackNum).divide(orderProduct.getNum(),5,BigDecimal.ROUND_HALF_UP).toString()).setScale(2,BigDecimal.ROUND_HALF_UP);
                        saveOrderProduct.setActualpayment(new BigDecimal(orderProduct.getActualpayment().subtract(subtractMoney).toString()).setScale(2,BigDecimal.ROUND_HALF_UP));
                        //重新核算优惠金额,担心采用 (优惠金额/订单数量)*剩余数量 的方式会导致精度丢失，故直接相减
//                        discountpay=new BigDecimal(saveOrderProduct.getNum().multiply(orderProduct.getPrice()).subtract(saveOrderProduct.getActualpayment()).toString()).setScale(2,BigDecimal.ROUND_HALF_UP);
                        discountpay=new BigDecimal(saveOrderProduct.getNum().multiply(discountpay).divide(orderProduct.getNum(),5,BigDecimal.ROUND_HALF_UP).toString()).setScale(2,BigDecimal.ROUND_HALF_UP);
                        saveOrderProduct.setDiscountpay(discountpay);

//                        saveOrderProduct.setActualpayment(new BigDecimal(saveOrderProduct.getPrice().multiply(saveOrderProduct.getNum()).toString()).setScale(2,BigDecimal.ROUND_HALF_UP));
                        totalProductMoney = totalProductMoney.add(saveOrderProduct.getActualpayment());
                        totalWeight = totalWeight.add(updateP.getNum().multiply(productStore1.getWeight()));
                        totalNum = totalNum.add(updateP.getNum());

                        //计算运费
//                        BigDecimal figtht = BigDecimal.valueOf(0);
//                        if (orders.getIsonline() == Quantity.STATE_0) {
//                            BigDecimal totalWeight = productStore1.getWeight().multiply(saveOrderProduct.getNum());
//                            //figtht = ordersService.countSinglePdFight(productInfo, productStore1, orders.getProvince(), orders.getCity(), saveOrderProduct.getNum());
//                            figtht = freightService.getFreight(productStore1.getFreightmode(),totalWeight,orders.getProvince(),orders.getCity());
//                            saveOrderProduct.setFreight(figtht);
//                        } else if (orders.getIsonline() == Quantity.STATE_2) {
//                            saveOrderProduct.setFreight(BigDecimal.valueOf(0));
//                        }

                        //TODO  兼容老数据
                        if(orders.getOrderfright() == null) {
                            //计算运费
                            BigDecimal figtht = BigDecimal.valueOf(0);
                            if (orders.getIsonline() == Quantity.STATE_0) {
                                figtht = freightService.getFreight(productStore1.getFreightmode(), productStore1.getWeight().multiply(saveOrderProduct.getNum()), orders.getProvince(), orders.getCity());
                                saveOrderProduct.setFreight(figtht);
                            } else if (orders.getIsonline() == Quantity.STATE_2) {
                                saveOrderProduct.setFreight(BigDecimal.valueOf(0));
                            }
                        }

                    }
                    totalDiscountpay=totalDiscountpay.add(discountpay);
                    saveOrderProductList.add(saveOrderProduct);

                    if (ordersService.updateOrderProductForModifyProductnum(saveOrderProduct) != 1) {
                        throw new RuntimeException("修改订单商品id:" + saveOrderProduct.getId() + "失败，请联系网站管理员");
                }

                    //将退货的商品信息记录到orderproductbackinfo表中
                    int a = orderProduct.getNum().compareTo(saveOrderProduct.getNum());
                    if(a!=0) {
                        OrderProductBackInfo orderProductBackInfo = new OrderProductBackInfo();
                        orderProductBackInfo.setOrderno(orderProduct.getOrderno());
                        orderProductBackInfo.setPdid(orderProduct.getPdid());
                        orderProductBackInfo.setBackno("TH" + UUID.randomUUID());
                        orderProductBackInfo.setBacknum(orderProduct.getNum().subtract(saveOrderProduct.getNum()));
                        orderProductBackInfo.setBacktype(Quantity.STATE_1);
                        orderProductBackInfo.setBackstate(Quantity.STATE_0);
                        orderProductBackInfo.setBacktime(new Date());
                        orderProductBackInfoService.addOrderProductBackInfo(orderProductBackInfo);
                    }
                }
            }

            //计算商家没有修改数量和运费之前的 用于退回运费差额的计算。
            if(orders.getIsmodifyfreight() == Quantity.STATE_1){
                ProductStore oldProductStore = productStoreService.getProductStore(orderProduct.getPdid(), orderProduct.getPdno(), orders.getStoreid());
                oldTotalProductMoney = oldTotalProductMoney.add(orderProduct.getActualpayment());
                oldTotalWeight = oldTotalWeight.add(orderProduct.getNum().multiply(oldProductStore.getWeight()));
            }
        }


        //计算修改后需要交纳的运费金额
        BigDecimal updateFreightMoney = Quantity.BIG_DECIMAL_0;

        //定义一个变量 用于存放修改商品订购数量后的运费（根据运费模板算出来的） 用于退回运费差额的计算。
        BigDecimal updateNumBeforeFreightMoney = Quantity.BIG_DECIMAL_0;


        if(orders.getOrderfright() != null) {
            if (orders.getOrderfright() != 1 && orders.getOrderfright() != 2 && orders.getOrderfright() != 3 && totalWeight.compareTo(Quantity.BIG_DECIMAL_0) == 1) {
                OrderFrightDto orderFrightDto = GsonUtils.toBean(orders.getFrighttemplate(), OrderFrightDto.class);
                updateFreightMoney = freightService.getFreightByOrderFrightDto(orderFrightDto, totalProductMoney.add(totalDiscountpay), totalWeight, orders.getProvince(), orders.getCity());
                updateNumBeforeFreightMoney = updateFreightMoney;
            } else {
                    updateFreightMoney = Quantity.BIG_DECIMAL_0;
                    updateNumBeforeFreightMoney = updateFreightMoney;
            }
        }else{
            //TODO 兼容老数据
            for (OrderProduct op : saveOrderProductList) {
                if(orders.getFreight().compareTo(new BigDecimal(0)) ==0) {
                    updateFreightMoney = updateFreightMoney.add(op.getFreight());
                    updateNumBeforeFreightMoney = updateFreightMoney;
                }
//               totalPrice = totalPrice.add(op.getActualpayment());
            }
        }

        //未修改运费的退运费金额 = 修改商品订购数量前的运费 - 修改商品订购数量后的运费
        //退回的运费 = （未修改运费的退运费金额 / 未修改的运费金额） * 修改后的运费金额。
        BigDecimal oldFreightMoney  = Quantity.BIG_DECIMAL_0; // 修改商品订购数量前的根据模板计算的运费
        BigDecimal compareFreightMoney = Quantity.BIG_DECIMAL_0; //未修改运费的退运费金额
        BigDecimal backFreightMoney = Quantity.BIG_DECIMAL_0; //退回的运费

        if(orders.getOrderfright() != 1 && orders.getOrderfright() != 2 && orders.getOrderfright() != 3) {
            oldFreightMoney = getOldFreightMoney(orders, oldTotalProductMoney, oldTotalWeight);
        }


        //如果有改过运费 都以改过运费的为准,但不包含修改数量为0的
        if(orders.getIsmodifyfreight() == 1 && totalNum.compareTo(new BigDecimal(0))>0){
            updateFreightMoney = orders.getFreight();
        }


        BigDecimal updateTotalMoney = BigDecimal.ZERO;

        //修改过运费的 商品总价+(修改后总运费-退回的运费)
        if(orders.getIsmodifyfreight() == 1 && orders.getFreight().compareTo(new BigDecimal(0))>0 && oldFreightMoney.compareTo(new BigDecimal(0))>0 && orders.getOrderfright() != 1 && orders.getOrderfright() != 2 && orders.getOrderfright() != 3) {
            //计算出来的运费差额
            backFreightMoney = getBackFreightMoney(orders, oldTotalProductMoney, oldTotalWeight, updateFreightMoney, updateNumBeforeFreightMoney);
            updateTotalMoney = new BigDecimal(totalProductMoney.add(updateFreightMoney.subtract(backFreightMoney)).toString()).setScale(2, BigDecimal.ROUND_HALF_UP);
        }else{
            //没有修改过运费的 商品总价+运费
            updateTotalMoney = new BigDecimal(totalProductMoney.add(updateFreightMoney).toString()).setScale(2, BigDecimal.ROUND_HALF_UP);  //修改后的订单的总金额（货款+运费）
        }

        //单个或者多个商品全部数量修改为0的情况
        if(orders.getIsmodifyfreight() == 1 && totalNum.compareTo(new BigDecimal(0))==0){
            updateTotalMoney = new BigDecimal(totalProductMoney.add(new BigDecimal(0)).toString()).setScale(2,BigDecimal.ROUND_HALF_UP);  //修改后的订单的总金额（货款+运费）
        }

//        BigDecimal totalFreight = Quantity.BIG_DECIMAL_0;
//        BigDecimal totalPrice = Quantity.BIG_DECIMAL_0;
//        for(OrderProduct op : saveOrderProductList){
//            totalFreight = totalFreight.add(op.getFreight());
//            totalPrice =  totalPrice.add(op.getActualpayment());
//        }
//
//        totalFreight.setScale(2,BigDecimal.ROUND_HALF_UP);
//        totalPrice.setScale(2,BigDecimal.ROUND_HALF_UP);



        //退款金额
        BigDecimal backMoney = new BigDecimal(orderTotalprice.subtract(updateTotalMoney).toString()).setScale(2,BigDecimal.ROUND_HALF_UP);

//          卖方修改订单数量，算卖方违约，卖方需要缴纳一定违约金
//          违约金 = 货品退货金额*违约金比例
//          缴纳违约金部分 = 订单总金额-修改后订单总金额 - （总运费-修改后总运费）

       // BigDecimal sellerWyMoney = orderTotalprice.subtract().subtract(orderFreight.subtract(totalFreight));
        BigDecimal sellerWyMoney = orderTotalprice.subtract(orderFreight).subtract(totalProductMoney);


        //买家获取到退款违约金金额
        BigDecimal buyerPenaltyPay = Quantity.BIG_DECIMAL_0;

        //违约金
        BigDecimal penaltyPay = sellerWyMoney.multiply(transactionSetting.getGetliquidated().multiply(new BigDecimal("0.01"))).setScale(2,BigDecimal.ROUND_HALF_UP);

        //被违约方获取违约金占比
        BigDecimal forwardsalesmargin = transactionSetting.getForwardsalesmargin().multiply(new BigDecimal("0.01"));


        //买家应得到的违约金金额
        buyerPenaltyPay = penaltyPay.multiply(forwardsalesmargin).setScale(2, BigDecimal.ROUND_HALF_UP);

        member = memberService.getMemberById(member.getId());
        if(member.getSellerbanlance().compareTo(penaltyPay)<0){
            throw  new CashException("需要缴纳"+penaltyPay+"的违约金，卖家余额账户金额不足");
        }
        memberService.updateSellerMemberBalanceInDb(member.getId(),penaltyPay.multiply(Quantity.BIG_DECIMAL_MINUS_1),Quantity.BIG_DECIMAL_0);

        member = memberService.getMemberById(member.getId());
        if(member.getSellerbanlance().compareTo(Quantity.BIG_DECIMAL_0) <0){
            throw  new CashException("需要缴纳"+buyerPenaltyPay+"的违约金，卖家余额账户金额不足");
        }

        //买方获取的违约金进入买家余额
        memberService.updateBuyerMemberBalanceInDb(orders.getMemberid(),buyerPenaltyPay);


        if(buyerPenaltyPay.compareTo(Quantity.BIG_DECIMAL_0)>0) {
            BuyerCapital buyerCapital2 = createBuyerPenaltyPay(orders, buyerPenaltyPay, new Date(), Quantity.STATE_10, buyerPenaltyPay, Quantity.SELLER_UPDATE_ORDERNUM_REASON);
            buyerCapitalService.insertSelective(buyerCapital2);
        }

        if(penaltyPay.compareTo(Quantity.BIG_DECIMAL_0)>0) {
            SalerCapital salerCapital2 = createSalerPenaltyPay(orders, penaltyPay, new Date(), Quantity.STATE_7, penaltyPay, Quantity.SELLER_UPDATE_ORDERNUM_REASON);
            salerCapitalService.insertSelective(salerCapital2);
        }


        if(backMoney.compareTo(Quantity.BIG_DECIMAL_0) <0){
            throw  new RuntimeException("退款金额不可少于0");
        }


        Orders updateOrders = new Orders();
        updateOrders.setId(orders.getId());



        //如果退款金额与订单总额相等
        if(backMoney.compareTo(orderTotalprice) ==0){
            updateOrders.setOrderstatus(Quantity.STATE_7);
            updateOrders.setReason("卖家取消订单");
            updateOrders.setTotalprice(Quantity.BIG_DECIMAL_0);
            updateOrders.setActualpayment(Quantity.BIG_DECIMAL_0);
            updateOrders.setFreight(Quantity.BIG_DECIMAL_0);
            ordersService.deleteBillRecord(orders.getId().toString());
        }else if(backMoney.compareTo(Quantity.BIG_DECIMAL_0) >=0){
            if(orders.getIsmodifyfreight() == 1){
                updateOrders.setFreight(updateFreightMoney.subtract(backFreightMoney));
            }else {
                updateOrders.setFreight(updateFreightMoney);
            }
            updateOrders.setTotalprice(updateTotalMoney.add(totalDiscountpay));
            updateOrders.setActualpayment(updateTotalMoney);

            //修改开票金额
            if(orders.getIsbilling() == Quantity.STATE_1){
                billingRecordService.updateAdminDecOrderProductnum(orders.getId().toString(),orders.getMemberid(),backMoney.multiply(new BigDecimal(-1)));
            }
        }
        updateOrders.setDiscountprice(totalDiscountpay);
        ordersService.updateSingleOrder(updateOrders);


        //操作日志
        OperateLog operateLog = new OperateLog();
        operateLog.setContent("卖家修改订单商品数量退款"+backMoney);
        operateLog.setOpid(member.getId());
        operateLog.setOpname(member.getUsername());
        operateLog.setOptime(new Date());
        operateLog.setOptype(Quantity.STATE_0);
        operateLog.setOrderid(orders.getId());
        operateLog.setOrderno(orders.getOrderno());
        ordersService.saveOperatelog(operateLog);



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
                        refund.setChannel(tradeService.getPayChannel(orders.getPaytype()));
                        refund.setRefundAmount((backMoney.multiply(new BigDecimal(100))).longValue());
                        refund.setRefundReason("订单退款");
                        BigDecimal totalAmout = tradeService.getTotalAmout(uuid);
                        refund.setTotalAmount((totalAmout.multiply(new BigDecimal(100))).longValue());
                        boolean result = tradeService.backMoney(refund);

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

                List<BuyerStatement> statementList=new ArrayList<>();
                BuyerStatement buyerStatement=null;
                Member buyer=memberService.getMemberById(orders.getMemberid());
                if (buyerCapital.getPaytype() == Quantity.STATE_3){
                    //插入一条违约金的对账单明细
                    buyerStatement=ordersService.createBuyerStateForBack(orders,buyerPenaltyPay,new Date(),buyerCapital.getPaytype(), (short) StatementType.StType4.getTyep(),buyer,null,false);
                    statementList.add(buyerStatement);
                    //余额与授信不插入退款记录
                }
                if (buyerCapital.getPaytype() == Quantity.STATE_4){
                    //插入一条违约金的对账单明细
                    buyerStatement=ordersService.createBuyerStateForBack(orders,buyerPenaltyPay,new Date(),buyerCapital.getPaytype(), (short) StatementType.StType4.getTyep(),buyer,null,false);
                    statementList.add(buyerStatement);
                    //余额与授信不插入退款记录
                }
                if (buyerCapital.getPaytype() == Quantity.STATE_0
                        || buyerCapital.getPaytype() == Quantity.STATE_1
                        || buyerCapital.getPaytype() == Quantity.STATE_2){
                    //插入一条违约金的对账单明细
                    buyerStatement=ordersService.createBuyerStateForBack(orders,buyerPenaltyPay,new Date(),buyerCapital.getPaytype(), (short) StatementType.StType4.getTyep(),buyer,null,false);
                    statementList.add(buyerStatement);
                    //插入一条退款对账单明细
                    buyerStatement=ordersService.createBuyerStateForBack(orders,backMoney,new Date(),buyerCapital.getPaytype(), (short) StatementType.StType5.getTyep(),buyer,null,false);
                    statementList.add(buyerStatement);
                }


                SalerCapital salerCapital =  createSalerBackPay(orders,backMoney,tranTime);

                if (buyerCapital1 != null) {
                    buyerCapitalService.insertSelective(buyerCapital1);
                }

                salerCapitalService.insertSelective(salerCapital);
                if (statementList.size()>0){
                    statementService.insertStatementAll(statementList);
                }

            } else {
                throw new RuntimeException("未查询到该订单的付款信息");
            }

        }


            //商品数量被卖家端修改 短信通知买家
            List<Orders> list = new ArrayList<>();
            list.add(orders);
            ordersService.smsNotifySellerProductNum(list);




        return  new BasicRet(BasicRet.SUCCESS,"修改成功");
    }
    /**
     * 算出需要退的运费
     * @param orders
     * @param oldTotalProductMoney
     * @param oldTotalWeight
     * @param updateFreightMoney
     * @param updateNumBeforeFreightMoney
     * @return
     * @throws MyException
     */
    private BigDecimal getBackFreightMoney(Orders orders, BigDecimal oldTotalProductMoney, BigDecimal oldTotalWeight, BigDecimal updateFreightMoney, BigDecimal updateNumBeforeFreightMoney) throws MyException {
        BigDecimal oldFreightMoney;
        BigDecimal compareFreightMoney;
        BigDecimal backFreightMoney;
        OrderFrightDto oldOrderFrightDto = GsonUtils.toBean(orders.getFrighttemplate(), OrderFrightDto.class);
        oldFreightMoney = freightService.getFreightByOrderFrightDto(oldOrderFrightDto, oldTotalProductMoney, oldTotalWeight, orders.getProvince(), orders.getCity());
        compareFreightMoney = oldFreightMoney.subtract(updateNumBeforeFreightMoney);
        //这里divide加2是 BigDecimal的divide方法进行除法时当不整除，出现无限循环小数时，就会抛异常
        backFreightMoney = (compareFreightMoney.divide(oldFreightMoney,5,RoundingMode.HALF_UP)).multiply(updateFreightMoney).setScale(2,BigDecimal.ROUND_HALF_UP);
        return backFreightMoney;
    }

    /**
     * 算出修改数量后 将这个数量传入根据运费模板算出运费
     * 例如数量为1千支 12元运费 修改为0.1千支还是12元
     * @param orders
     * @param oldTotalProductMoney
     * @param oldTotalWeight
     * @return
     * @throws MyException
     */
    private BigDecimal getOldFreightMoney(Orders orders, BigDecimal oldTotalProductMoney, BigDecimal oldTotalWeight) throws MyException {
        BigDecimal oldFreightMoney;
        OrderFrightDto oldOrderFrightDto = GsonUtils.toBean(orders.getFrighttemplate(), OrderFrightDto.class);
        oldFreightMoney = freightService.getFreightByOrderFrightDto(oldOrderFrightDto, oldTotalProductMoney, oldTotalWeight, orders.getProvince(), orders.getCity());
        return oldFreightMoney;
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
        //设置发票为未开
        Short zero = 0;
        buyerCapital.setBilltoserver(zero);
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

        ////订单资金会在订单完成后才进入冻结金额，所以不需要从卖家冻结金额中扣除
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
        //设置发票为未开
        Short zero = 0;
        salerCapital.setBilltoserver(zero);
        if (type == Quantity.STATE_6) {
            salerCapital.setRemark("买家违约金额");
        } else {
            salerCapital.setRemark("卖家违约金额");
        }
        return salerCapital;
    }


    @RequestMapping(value = "/getOrderProductBack", method = RequestMethod.POST)
    @ApiOperation(value = "获取退货列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startTime", value = "开始时间", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "code", value = "交易号", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "pdName", value = "商品名称", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "memberName", value = "买家", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "sellerName", value = "卖家", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "orderNo", value = "订单号", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "state", value = "退货状态0=待卖家处理1=卖家同意待收货2=卖家同意直接退款3=卖家收到货同意退款4=卖家不同意5=买家同意验货6=买家申请异议7=平台同意退货不扣违约金8=平台同意退货扣违约金9=平台转入待验收10=退货成功", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "pageNo", value = "开始页", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "页数", required = true, paramType = "query", dataType = "int"),
    })
    @PreAuthorize("hasAuthority('" + SellerAuthorityConst.BACKGOODS + "') || hasAuthority('" + SellerAuthorityConst.YIYI + "') || hasAuthority('" + SellerAuthorityConst.ALL + "')")
    public PageRet getOrderProductBack(Model model, BackQueryParam backQueryParam, int pageNo, int pageSize) {
        PageRet pageRet = new PageRet();
        pageRet.setMessage("返回成功");
        pageRet.setResult(BasicRet.SUCCESS);
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        backQueryParam.setSellerid(member.getId());
        pageRet.data.setPageInfo(ordersService.getOrderProductBackList(pageNo, pageSize, backQueryParam));
        return pageRet;
    }

    @RequestMapping(value = "/updateBillRecord", method = RequestMethod.POST)
    @ApiOperation(value = "更新开票申请记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "开票申请记录id", required = true, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "state", value = "开票状态0=待开票1=未收到2=确认", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "expressno", value = "物流单号", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "expresscom", value = "物流公司", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "billtype", value = "开票类型", required = false, paramType = "query", dataType = "string"),
    })
    public BasicRet updateBillRecord(Model model, BillingRecord billingRecord, HttpServletRequest request) {
        BasicRet basicRet = new BasicRet();
        ordersService.updateBillRecord(billingRecord);
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("修改成功");
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        //保存用户日志
        memberLogOperator.saveMemberLog(member, null, "更新开票申请记录", "/rest/seller/orders/updateBillRecord",request, memberOperateLogService);
        return basicRet;
    }




    @RequestMapping(value = "/getOrdersByBillRecordId", method = RequestMethod.POST)
    @ApiOperation(value = "根据记录id获取开票信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "开票申请记录id", required = true, paramType = "query", dataType = "long"),
    })
    public OrderCarRet getOrdersByBillRecordId(Long id) {
        OrderCarRet orderCarRet = new OrderCarRet();
        orderCarRet.setResult(BasicRet.SUCCESS);
        orderCarRet.setMessage("返回成功");
        BillingRecord billingRecord = ordersService.getBillRecordByID(id);
        BillRecordComplex billRecordComplex = new BillRecordComplex();
        if (billingRecord != null) {
            if (billingRecord.getBillno() != null) {
                billRecordComplex.setBillNo(billingRecord.getBillno());
            }
            if (billingRecord.getBilltime() != null) {
                billRecordComplex.setBillTime(billingRecord.getCreatetime());
            }
            if (billingRecord.getBilltype() != null) {
                billRecordComplex.setBillType(billingRecord.getBilltype());
            }
            if (billingRecord.getExpressno() != null) {
                billRecordComplex.setExpressNo(billingRecord.getExpressno());
            }
            if (billingRecord.getBillingrecordtype() != null) {
                billRecordComplex.setBillingrecordtype(billingRecord.getBillingrecordtype());
            }
            billRecordComplex.setMemberId(billingRecord.getMemberid());
            billRecordComplex.setMemberName(billingRecord.getMembername());
            String[] ordernoArray = billingRecord.getOrderno().split(",");
            List<Orders> list = new ArrayList<Orders>();
            for (String orderid : ordernoArray) {
                Orders orders = ordersService.getOrdersById(Long.parseLong(orderid));
                if (orders != null) {
                    list.add(orders);
                }

            }
            billRecordComplex.setList(list);
        } else {
            orderCarRet.setResult(BasicRet.ERR);
            orderCarRet.setMessage("没有开票记录");
            return orderCarRet;
        }
        orderCarRet.data.billRecordComplex = billRecordComplex;
        return orderCarRet;
    }

    @Autowired
    private FreightService freightService;

    /**
     * 计算运费
     *
     * @param productInfo
     * @param productStore
     * @param uprovince
     * @param ucity
     * @param pdnum
     * @return
     */
    /*
    private BigDecimal countSinglePdFight(ProductInfo productInfo, ProductStore productStore, String uprovince, String ucity, BigDecimal pdnum) {

        BigDecimal weight = productInfo.getWeight();
        long freightmode = productStore.getFreightmode();
        if (freightmode != Quantity.STATE_) {
            //获取商品运费模板
            ShippingTemplates shippingTemplates = ordersService.getShippingTemp(freightmode);
            //默认运费公斤
            BigDecimal defaultfreight = shippingTemplates.getDefaultfreight();
            //默认运费
            BigDecimal defaultcost = shippingTemplates.getDefaultcost();
            //每增加公斤
            BigDecimal perkilogramadded = shippingTemplates.getPerkilogramadded();
            //每增加运费
            BigDecimal perkilogramcost = shippingTemplates.getPerkilogramcost();
            //商品总重量
            BigDecimal totalweight = weight.multiply(pdnum);
            //匹配地区运费
            AreaCost areaCost = ordersService.getAreaCost(freightmode);

            if (uprovince == null || ucity == null) {
                if (shippingTemplates != null) {
                    //计算默认运费
                    return null;//freightService.getTotalCost(defaultfreight, defaultcost, perkilogramadded, perkilogramcost, totalweight);
                }
            } else {
                //如果有地区运费模板，就用地区运费模板，否则用默认运费模板
                if (areaCost != null) {
                    String province = areaCost.getProvince();
                    String city = areaCost.getCity();
                    //判断省市是否匹配
                    if (province.contains(uprovince) && city.contains(ucity)) {
                        BigDecimal udefaultfreight = areaCost.getDefaultfreight();
                        //默认运费
                        BigDecimal udefaultcost = areaCost.getDefaultcost();
                        //每增加公斤
                        BigDecimal uperkilogramadded = areaCost.getPerkilogramadded();
                        //每增加运费
                        BigDecimal uperkilogramcost = areaCost.getPerkilogramcost();
                        //计算地区运费
                        return null;//freightService.getTotalCost(udefaultfreight, udefaultcost, uperkilogramadded, uperkilogramcost, totalweight);

                    } else {
                        //计算默认运费
                        return null;//freightService.getTotalCost(defaultfreight, defaultcost, perkilogramadded, perkilogramcost, totalweight);
                    }

                } else {
                    //计算默认运费
                    return null;//freightService.getTotalCost(defaultfreight, defaultcost, perkilogramadded, perkilogramcost, totalweight);
                }
            }
        } else {
            return new BigDecimal(0);
        }
        return new BigDecimal(0);
    }
    */

    @RequestMapping(value = "/listShippingAddress", method = RequestMethod.POST)
    @ApiOperation("列出卖家收货地址列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sellerid", value = "卖家id", required = true, paramType = "query", dataType = "int"),
    })
    public PageRet listSellerShippingAddress(@RequestParam(required = true, defaultValue = "1") int pageNo,
                                             @RequestParam(required = true, defaultValue = "10") int pageSize, Model model, Long sellerid) {
        PageRet pageRet = new PageRet();
        PageInfo pageInfo = shippingAddressService.listAllShippingAddress(pageNo, pageSize, sellerid, Quantity.STATE_1);
        pageRet.data.setPageInfo(pageInfo);
        pageRet.setResult(BasicRet.SUCCESS);
        return pageRet;
    }

    @RequestMapping(value = "/saveSellerBillRecord", method = RequestMethod.POST)
    @ApiOperation("保存卖家开票记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "money", value = "开票金额", required = true, paramType = "query", dataType = "double"),
            @ApiImplicitParam(name = "billtype", value = "开票类型", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "billtitle", value = "抬头", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "texno", value = "税号", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "billno", value = "发票号", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "diliverycompany", value = "物流公司", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "diliveryno", value = "物流单号", required = true, paramType = "query", dataType = "string"),
    })
    public OrderCarRet saveSellerBillRecord(Model model, SellerBillRecord sellerBillRecord, HttpServletRequest request) {
        OrderCarRet orderCarRet = new OrderCarRet();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        sellerBillRecord.setSellerid(member.getId());
        sellerBillRecord.setSellername(member.getUsername());
        sellerBillRecord.setApplytime(new Date());
        sellerBillRecord.setState(Quantity.STATE_0);
        ordersService.saveSellerBillRecord(sellerBillRecord);
        orderCarRet.data.sellerBillRecord = sellerBillRecord;
        orderCarRet.setMessage("保存成功");
        orderCarRet.setResult(BasicRet.SUCCESS);
        //保存用户日志
        memberLogOperator.saveMemberLog(member, null, "卖家申请开票", "/rest/seller/orders/saveSellerBillRecord",request, memberOperateLogService);
        return orderCarRet;
    }

    @RequestMapping(value = "/loadSellerBillRecordList", method = RequestMethod.POST)
    @ApiOperation("获取卖家开票列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startTime", value = "开始时间", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "billno", value = "发票号", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "sellerid", value = "卖家编号", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "sellername", value = "卖家名称", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "diliveryno", value = "物流单号", required = false, paramType = "query", dataType = "string"),
    })
    @PreAuthorize("hasAuthority('" + SellerAuthorityConst.MYINVOICE + "') || hasAuthority('" + SellerAuthorityConst.ALL + "')")
    public PageRet loadSellerBillRecordList(Model model, int pageNo, int pageSize, SellerBillRecordQueryParam param) {
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        PageRet pageRet = new PageRet();
        PageInfo pageInfo = ordersService.getSellerBillRecord(pageNo, pageSize, param, member);
        pageRet.data.setPageInfo(pageInfo);
        pageRet.setResult(BasicRet.SUCCESS);
        return pageRet;
    }


    @RequestMapping(value = "/updateSellerBillRecord", method = RequestMethod.POST)
    @ApiOperation("修改卖家开票")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "开票申请id", required = false, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "state", value = "状态0=未确认1=确认", required = false, paramType = "query", dataType = "int"),

    })
    public BasicRet updateSellerBillRecord(Model model, SellerBillRecord sellerBillRecord, HttpServletRequest request) {
        BasicRet basicRet = new BasicRet();
        ordersService.updateSellerBillRecord(sellerBillRecord);
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("修改成功");
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        //保存用户日志
        memberLogOperator.saveMemberLog(member, null, "卖家修改开票状态为：" + sellerBillRecord.getState(), "/rest/seller/orders/updateSellerBillRecord",request, memberOperateLogService);
        return basicRet;
    }

    @RequestMapping(value = "/deleteSellerBillRecord", method = RequestMethod.POST)
    @ApiOperation("删除卖家开票记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "开票记录id", required = true, paramType = "query", dataType = "long"),
    })
    public BasicRet deleteSellerBillRecord(Model model, Long id, HttpServletRequest request) {
        BasicRet basicRet = new BasicRet();
        ordersService.deleteSellerBillRecord(id);
        basicRet.setMessage("删除成功");
        basicRet.setResult(BasicRet.SUCCESS);
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        //保存用户日志
        memberLogOperator.saveMemberLog(member, null, "卖家删除开票", "/rest/seller/orders/deleteSellerBillRecord",request, memberOperateLogService);
        return basicRet;

    }


    @RequestMapping(value = "/getSellerOrdersStateNum", method = RequestMethod.POST)
    @ApiOperation(value = "卖家个人中心订单各状态数量")
    public BasicRet getSellerOrdersStateNum(Model model, OrderQueryParam param) {
        OrderCarRet basicRet = new OrderCarRet();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        param.setSellerid(member.getId());
        BuyerCenterModel buyerCenterModel = ordersService.getMemberOrdersNum(param);
        basicRet.data.buyerCenterModel = buyerCenterModel;
        basicRet.setMessage("返回成功");
        basicRet.setResult(BasicRet.SUCCESS);
        return basicRet;
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

        orderCarRet.data.operateLogs = list;
        orderCarRet.setMessage("返回成功");
        orderCarRet.setResult(BasicRet.SUCCESS);
        return orderCarRet;
    }


    @RequestMapping(value = "/getProductEvaList", method = RequestMethod.POST)
    @ApiOperation(value = "获取评价列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "页码", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "页数", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "startTime", value = "开始时间", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "orderNo", value = "订单编号", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "sellerName", value = "所属商家", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "pdName", value = "商品名称", required = false, paramType = "query", dataType = "string"),
    })
    @PreAuthorize("hasAuthority('" + SellerAuthorityConst.EVALUATION + "') || hasAuthority('" + SellerAuthorityConst.ALL + "')")
    public PageRet getProductEvaList(Model model, OrderQueryParam param) {

        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

        if (member != null) {
            param.setSellerid(member.getId());
        }

        PageRet orderCarRet = new PageRet();

        PageInfo pageInfo = ordersService.getOrderProductEva(param);

        orderCarRet.data.setPageInfo(pageInfo);

        orderCarRet.setMessage("返回成功");
        orderCarRet.setResult(BasicRet.SUCCESS);
        return orderCarRet;
    }

    @RequestMapping(value = "/getDeliveryRecordList", method = RequestMethod.POST)
    @ApiOperation(value = "获取发货记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "页码", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "页数", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "startTime", value = "开始时间", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "deliveryno", value = "发货号", required = false, paramType = "query", dataType = "string"),
    })
    public PageRet getDeliveryRecordList(Model model, OrderQueryParam param) {

        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

        if (member != null) {
            param.setSellerid(member.getId());
        }

        PageRet orderCarRet = new PageRet();

        //PageInfo pageInfo = ordersService.getDeliveryRecord(param);
        PageInfo pageInfo = ordersService.getDeliveryNewRecord(param);

        orderCarRet.data.setPageInfo(pageInfo);

        orderCarRet.setMessage("返回成功");
        orderCarRet.setResult(BasicRet.SUCCESS);
        return orderCarRet;
    }

    /**
     * 保存操作日志
     *
     * @param memberOperateLog
     * @return
     */
    @RequestMapping(value = "/saveMemberLog", method = RequestMethod.POST)
    @ApiOperation(value = "保存操作日志")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ipaddress", value = "ip地址", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "content", value = "操作内容", required = true, paramType = "query", dataType = "string"),
    })
    public BasicRet saveMemberLog(Model model, MemberOperateLog memberOperateLog) {
        BasicRet basicRet = new BasicRet();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        memberOperateLog.setMemberid(member.getId());
        memberOperateLog.setMembername(member.getUsername());
        memberOperateLog.setCreatetime(new Date());
        memberOperateLogService.saveMemberOperateLog(memberOperateLog);
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("保存成功");
        return basicRet;
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
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        param.setMemberid(member.getId());
        PageRet pageRet = new PageRet();
        PageInfo pageInfo = ordersService.getMemberOperateLogList(pageNo,pageSize,param);
        pageRet.data.setPageInfo(pageInfo);
        pageRet.setResult(BasicRet.SUCCESS);
        pageRet.setMessage("返回成功");
        return pageRet;
    }


    @RequestMapping(value = "/saveDelayDays",method = RequestMethod.POST)
    @ApiOperation(value = "卖家保存延期发货")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderid",value = "订单id",required = true,paramType = "query",dataType = "long"),
            @ApiImplicitParam(name = "delaydays",value = "延期天数",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "paySecret", value = "支付密码", required = true, paramType = "query", dataType = "string"),
    })
    public BasicRet saveDelayDays(Model model,Long orderid,Integer delaydays,String paySecret,HttpServletRequest request){
        BasicRet basicRet =new BasicRet();

        Member seller = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        seller = memberService.getMemberById(seller.getId());

        String ps = CommonUtils.genMd5Password(paySecret, seller.getPaypasswordsalt());

        Orders orders = ordersService.getSingleOrder(orderid);

        if(orders.getOrderstatus()!=Quantity.STATE_1){
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("订单不是待发货状态 ，不能延期");
            return basicRet;
        }

        if(!ps.equals(seller.getPaypassword())){
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("支付密码不正确");
            return basicRet;
        }
        TransactionSetting transactionSetting = transactionSettingService.getTransactionSetting();

        BigDecimal pay1_10 = transactionSetting.getDelivery1of10();

        BigDecimal pay11_20 = transactionSetting.getDelivery11of20();

        BigDecimal pay21_30 = transactionSetting.getDelivery21of30();


        BigDecimal bigDecimalDays = new BigDecimal(delaydays);

        BigDecimal penaltyPay = new BigDecimal(0);


        //计算违约金
        if(delaydays>=1&&delaydays<=10){
            penaltyPay =  bigDecimalDays.multiply(pay1_10).setScale(2, RoundingMode.HALF_UP);
        }else if(delaydays>=11&&delaydays<=20){

            BigDecimal remaindays = new BigDecimal(delaydays-10);
            penaltyPay = (new BigDecimal(10).multiply(pay1_10).add(remaindays.multiply(pay11_20))).setScale(2, RoundingMode.HALF_UP);

        }else if(delaydays>=21&&delaydays<=30){
            BigDecimal remaindays = new BigDecimal(delaydays-20);
            penaltyPay = ((new BigDecimal(10).multiply(pay1_10).add(new BigDecimal(10).multiply(pay11_20)).add(remaindays.multiply(pay21_30)))).setScale(2,RoundingMode.HALF_UP);
        }
        //如果余额小于违约金，则不能延期
        if(seller.getSellerbanlance().compareTo(penaltyPay)==Quantity.STATE_){
            basicRet.setMessage("余额小于违约金，不能延期");
            basicRet.setResult(BasicRet.ERR);
            return basicRet;
        }



        orders.setDelaydays(delaydays);
        orders.setIfdelay(Quantity.STATE_1);
        orders.setDelaypenalty(penaltyPay);

        ordersService.updateSingleOrder(orders);

        basicRet.setMessage("延期发货申请成功，等待买家同意");
        basicRet.setResult(BasicRet.SUCCESS);

        //保存用户日志
        memberLogOperator.saveMemberLog(seller, null, "卖家申请发货延期天数："+delaydays, "/rest/seller/orders/saveDelayDays", request, memberOperateLogService);
        return basicRet;
    }

    @RequestMapping(value = "/updateOrderFreight", method = RequestMethod.POST)
    @ApiOperation(value = "订单修改运费")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ordersid", value = "订单id", required = true, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "freight", value = "修改后的运费", required = true, paramType = "query", dataType = "double"),
    })
    public BasicRet updateOrderFreight(Model model, Long ordersid, BigDecimal freight, HttpServletRequest request) {
        BasicRet basicRet = new BasicRet();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        Orders orders = ordersService.getOrdersById(ordersid);
        if (orders != null) {
            //判断订单状态 只有待付款才允许修改
            if(orders.getOrderstatus() != Quantity.STATE_0){
                basicRet.setResult(BasicRet.ERR);
                basicRet.setMessage("不是待付款状态，不允许修改运费");
                return basicRet;
            }
            if(orders.getIsticket() == Quantity.STATE_1){
                basicRet.setResult(BasicRet.ERR);
                basicRet.setMessage("使用优惠券的订单不允许修改运费");
                return basicRet;
            }
            if(!orders.getSaleid().equals(member.getId())){
                basicRet.setResult(BasicRet.ERR);
                basicRet.setMessage("该订单卖家不是你，不可修改");
                return basicRet;
            }
            if(!StringUtils.isNumeric(freight.toString())){
                basicRet.setResult(BasicRet.ERR);
                basicRet.setMessage("运费请填写数字");
                return basicRet;
            }
            if(freight.compareTo(new BigDecimal(0)) < 0){
                basicRet.setResult(BasicRet.ERR);
                basicRet.setMessage("运费不能为负数");
                return basicRet;
            }
            if(orders.getFreight().compareTo(freight) == 0){
                basicRet.setResult(BasicRet.ERR);
                basicRet.setMessage("修改后的运费价格和之前的运费相同");
                return basicRet;
            }



            List<OrderProduct> orderProductList = orderProductServices.getByOrderNo(orders.getOrderno());

            BigDecimal totalProductMoney = Quantity.BIG_DECIMAL_0;  //所有商品总金额(不包含运费)
            BigDecimal orderTotalPrice = Quantity.BIG_DECIMAL_0; //订单总价(含运费)
            BigDecimal productMoney = Quantity.BIG_DECIMAL_0;  //一个商品的金额


            BigDecimal deposit = Quantity.BIG_DECIMAL_0;  //远期定金
            BigDecimal balance = Quantity.BIG_DECIMAL_0;  //远期余款
            BigDecimal allPay = Quantity.BIG_DECIMAL_0;  //远期全款


            //重新计算总价和加入运费
           if(orderProductList!=null && orderProductList.size()>0) {
               for (OrderProduct orderProduct : orderProductList) {
                    productMoney = orderProduct.getPrice().multiply(orderProduct.getNum()).setScale(2,BigDecimal.ROUND_HALF_UP);
                   if(orders.getOrdertype() == Quantity.STATE_1) {
                       //远期全款
                       allPay = allPay.add(productMoney.setScale(2,BigDecimal.ROUND_HALF_UP));
                   }
                   if(orders.getOrdertype() == Quantity.STATE_2) {
                       //远期定金
                       deposit = deposit.add(orderProduct.getPartpay());
                       //修改运费的话 远期定金的 余款要加上运费
                       balance = (balance.add(orderProduct.getYupay())).add(freight);
                   }
                   totalProductMoney =  totalProductMoney.add(productMoney);
               }
               orderTotalPrice = totalProductMoney.add(freight);

               //将重新计算后的总价和运费存入
               Orders updateOrders = new Orders();
               updateOrders.setId(orders.getId());
               updateOrders.setFreight(freight);
               //actualpayment是已经减去优惠金额的 totalprice是没有减去优惠券金额的
                   //没有使用优惠券的时候
               if (orders.getDiscountprice().compareTo(Quantity.BIG_DECIMAL_0) == 0) {
                       updateOrders.setActualpayment(orderTotalPrice);
                       if(orders.getOrdertype() == Quantity.STATE_1) {
                           updateOrders.setAllpay(allPay.add(freight));
                       }
               } else {
                   //使用优惠券的时候
                       BigDecimal newActualpayment = orderTotalPrice.subtract(orders.getDiscountprice());
                       BigDecimal newAllpay = (allPay.add(freight)).subtract(orders.getDiscountprice());
                       updateOrders.setActualpayment(newActualpayment);
                       if(orders.getOrdertype() == Quantity.STATE_1) {
                           updateOrders.setAllpay(newAllpay);
                       }
               }
               updateOrders.setTotalprice(orderTotalPrice);
               updateOrders.setBalance(balance);
               updateOrders.setDeposit(deposit);
               //设置为已修改过运费
               updateOrders.setIsmodifyfreight(Quantity.STATE_1);
               ordersService.updateOrders(updateOrders);

               //修改开票金额
               if(orders.getIsbilling() == Quantity.STATE_1){
                   //直接用修改过后的运费减去修改前的运费 差额就是开票要加的
                   BigDecimal comparefreight = freight.subtract(orders.getFreight());
                   billingRecordService.updateAdminDecOrderProductnum(orders.getId().toString(),orders.getMemberid(),comparefreight);
               }
           }
        } else {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("订单不存在");
            return basicRet;
        }

        //保存操作日志
        OperateLog operateLog = new OperateLog();
        operateLog.setContent("卖家将原运费"+orders.getFreight()+"改成"+freight);
        operateLog.setOpid(member.getId());
        operateLog.setOpname(member.getUsername());
        operateLog.setOptime(new Date());
        operateLog.setOptype(Quantity.STATE_0);
        operateLog.setOrderid(orders.getId());
        operateLog.setOrderno(orders.getOrderno());
        ordersService.saveOperatelog(operateLog);
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("修改成功");
        //用户日志
        memberLogOperator.saveMemberLog(member, null, "订单运费修改为：" + freight, "/rest/seller/orders/updateOrderFreight",request, memberOperateLogService);
        return basicRet;
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
