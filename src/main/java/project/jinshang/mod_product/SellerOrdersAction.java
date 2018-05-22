package project.jinshang.mod_product;

import com.alipay.api.AlipayApiException;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import mizuki.project.core.restserver.config.BasicRet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
import project.jinshang.common.utils.*;
import project.jinshang.mod_activity.bean.LimitTimeProd;
import project.jinshang.mod_activity.bean.LimitTimeStore;
import project.jinshang.mod_activity.service.LimitTimeStoreService;
import project.jinshang.mod_admin.mod_commondata.service.CommonDataValueService;
import project.jinshang.mod_admin.mod_transet.bean.TransactionSetting;
import project.jinshang.mod_admin.mod_transet.service.TransactionSettingService;
import project.jinshang.mod_cash.bean.BuyerCapital;
import project.jinshang.mod_cash.bean.SalerCapital;
import project.jinshang.mod_cash.service.BuyerCapitalService;
import project.jinshang.mod_cash.service.SalerCapitalService;
import project.jinshang.mod_company.bean.AgentDeliveryAddress;
import project.jinshang.mod_company.bean.BuyerCompanyInfo;
import project.jinshang.mod_company.service.AgentDeliveryAddressService;
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
import project.jinshang.mod_wms_middleware.WMSService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
    private  AgentDeliveryAddressService agentDeliveryAddressService;


    //远期全款打折率
    private static final BigDecimal allPayRate = new BigDecimal(0.99);

    MemberLogOperator memberLogOperator = new MemberLogOperator();

    private class OrderCarRet extends BasicRet {
        private class Data {
            private BuyerCenterModel buyerCenterModel;
            private SellerBillRecord sellerBillRecord;
            private List<Orders> ordersList;

            List<TransactionSetting> transactionSettings;

            private OrderProductBack orderProductBack;

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
            @ApiImplicitParam(name = "orderState", value = "订单状态0=待付款1=待发货3=待收货4=待验货5=已完成7=已关闭8=备货中9=备货完成", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "evaState", value = "评价状态0=未评价1=已评价", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "backstate", value = "退货状态0=正常1=退货中2=退货验收3=退货完成4=异议中", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "brand", value = "品牌", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "stand", value = "规格", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "mark", value = "印记", required = false, paramType = "query", dataType = "string"),
    })
    @PreAuthorize("hasAuthority('" + SellerAuthorityConst.ORDER + "') || hasAuthority('" + SellerAuthorityConst.ALL + "')")
    public PageRet getMemberOrderList(Model model, OrderQueryParam param) {
        PageRet pageRet = new PageRet();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        param.setSellerid(member.getId());

        PageInfo pageInfo = ordersService.getMemberOrdersList(param);
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
            memberOrderses.add(memberOrders);
        }
        pageInfo.setList(memberOrderses);
        pageRet.data.setPageInfo(pageInfo);
        pageRet.setResult(BasicRet.SUCCESS);
        pageRet.setMessage("返回成功");
        return pageRet;
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
            @ApiImplicitParam(name = "orderState", value = "订单状态0=待付款1=待发货3=待收货4=待验货5=已完成7=已关闭8=备货中9=备货完成", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "evaState", value = "评价状态0=未评价1=已评价", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "backstate", value = "退货状态0=正常1=退货中2=退货验收3=退货完成4=异议中", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "brand", value = "品牌", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "stand", value = "规格", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "mark", value = "印记", required = false, paramType = "query", dataType = "string"),
    })
    public PageRet getMemberDeliveryOrderList(Model model, OrderQueryParam param) {
        PageRet pageRet = new PageRet();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        param.setSellerid(member.getId());

        PageInfo pageInfo = ordersService.getMemberOrdersList(param);
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

    @RequestMapping(value = "/getOrderProductByOrderNo", method = RequestMethod.POST)
    @ApiOperation(value = "根据订单编号获取商品信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderno", value = "订单编号", required = true, paramType = "query", dataType = "string"),
    })
    public OrderCarRet getOrderProductByOrderNo(String orderno) {
        OrderCarRet orderCarRet = new OrderCarRet();
        orderCarRet.setMessage("返回成功");
        orderCarRet.setResult(BasicRet.SUCCESS);
        orderCarRet.data.orderProducts = ordersService.getOrderProductByOrderNo(orderno);
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
            @ApiImplicitParam(name = "state", value = "订单状态0=待付款1=待发货3=待收货4=待验货5=已完成7=已关闭8=备货中9=备货完成", required = true, paramType = "query", dataType = "int"),
    })
    public BasicRet updateOrderState(Model model, String orderno, short state, HttpServletRequest request) throws CashException {
        BasicRet basicRet = new BasicRet();
        Orders orders = ordersService.getOrdersByOrderNo(orderno);
        if (orders != null) {
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

            }
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
    })
    public BasicRet sendOutGoods(Model model, String orderno, String logisticscompany, String couriernumber, HttpServletRequest request) {

        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

        BasicRet basicRet = new BasicRet();
        Orders orders = ordersService.getOrdersByOrderNo(orderno);
        if (orders != null) {
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


                    updateOrder.setLogisticscompany(logisticscompany);
                    updateOrder.setCouriernumber(couriernumber);
                    updateOrder.setDeliveryno(GenerateNo.getInvoiceNo());
                }else{
                    updateOrder.setLogisticscompany(logisticscompany);
                    updateOrder.setCouriernumber(couriernumber);
                    updateOrder.setDeliveryno(GenerateNo.getInvoiceNo());
                }


                updateOrder.setOrderstatus(Quantity.STATE_3);
                updateOrder.setSellerdeliverytime(new Date());
                ordersService.updateSingleOrder(updateOrder);


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


    @RequestMapping(value = "/getOrderProductBackByOrderProductId", method = RequestMethod.POST)
    @ApiOperation(value = "根据商品id获取退货详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "订单商品id", required = true, paramType = "query", dataType = "long"),
    })
    public BasicRet getOrderProductBackByOrderProductId(Long id) {
        OrderCarRet orderCarRet = new OrderCarRet();
        OrderProductBack orderProductBack = ordersService.getOrderProductBackByOrderProductID(id);
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
    public BasicRet updateOrderProductBack(Model model, ProductBackModel productBackModel, HttpServletRequest request) throws CashException {
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
        } else {
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


//            if (orderProductBack != null) {
//                orderProductBack.setState(state);
//                if (adminstate != null) {
//                    orderProductBack.setAdminstate(adminstate);
//                }
//                if (StringUtils.hasText(productBackModel.getLogisticsno())) {
//                    orderProductBack.setLogisticsno(productBackModel.getLogisticsno());
//                }
//                if (StringUtils.hasText(productBackModel.getLogisticscompany())) {
//                    orderProductBack.setLogisticscompany(productBackModel.getLogisticscompany());
//                }
//                if (StringUtils.hasText(productBackModel.getBackaddress())) {
//                    orderProductBack.setBackaddress(productBackModel.getBackaddress());
//                }
//                if (StringUtils.hasText(productBackModel.getContact())) {
//                    orderProductBack.setContact(productBackModel.getContact());
//                }
//                if (StringUtils.hasText(productBackModel.getContactphone())) {
//                    orderProductBack.setContactphone(productBackModel.getContactphone());
//                }
//                if (StringUtils.hasText(productBackModel.getSellernotagree())) {
//                    orderProductBack.setSellernotagree(productBackModel.getSellernotagree());
//                }
//                if (StringUtils.hasText(productBackModel.getAdminreason())) {
//                    orderProductBack.setAdminreason(productBackModel.getAdminreason());
//                }
//                if (StringUtils.hasText(productBackModel.getProvince())) {
//                    orderProductBack.setProvince(productBackModel.getProvince());
//                }
//                if (StringUtils.hasText(productBackModel.getCity())) {
//                    orderProductBack.setCity(productBackModel.getCity());
//                }
//                if (StringUtils.hasText(productBackModel.getArea())) {
//                    orderProductBack.setArea(productBackModel.getArea());
//                }
//                if (productBackModel.getBacktype() != null) {
//                    orderProductBack.setBacktype(productBackModel.getBacktype());
//                }
//            }
            if (orderProductBack != null) {

                if(state == Quantity.STATE_1){  //卖家同意退货，进入买家发货给卖家，卖家验货环节
                    orderProduct.setBackstate(Quantity.STATE_2);
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
                    handleBackGoods(orderProductBack, orderProduct, orders,member.getId(),member.getUsername());

                }else if(state == Quantity.STATE_4){

                }

                ordersService.updateOrderProduct(orderProduct);
                ordersService.updateOrderProductBack(orderProductBack);

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



                /*if (state != null) {
                    //卖家同意待验货
                    if (state == Quantity.STATE_1) {
                        orderProduct.setBackstate(Quantity.STATE_2);
                        //卖家同意直接退款或卖家收到货同意退款
                    } else if (state == Quantity.STATE_2 || state == Quantity.STATE_3) {
                        orderProduct.setBackstate(Quantity.STATE_3);
                        handleBackGoods(member, orderProductBack, orderProduct, orders);
                        orderProductBack.setState(Quantity.STATE_10);

                        //增加库存
                        if (orders.getIsonline() == Quantity.STATE_0) {
                            store.setPdstorenum(store.getPdstorenum().add(orderProductBack.getPdnum()));
                            ordersService.updateProductStore(store);
                        }
                        if (orders.getIsonline() == Quantity.STATE_2) {
                            limitTimeStore.setStorenum(limitTimeStore.getStorenum().add(orderProductBack.getPdnum()));
                            limitTimeStore.setSalesnum(limitTimeStore.getSalesnum().subtract(orderProductBack.getPdnum()));
                            shopCarService.updateLimitTimeStore(limitTimeStore);
                            limitTimeProd.setSalestotalnum(limitTimeProd.getSalestotalnum().subtract(orderProduct.getNum()));
                            shopCarService.updateLimitTimeProd(limitTimeProd);
                        }
                        //买家同意验货
                    } else if (state == Quantity.STATE_5) {
                        orderProduct.setBackstate(Quantity.STATE_0);
                        //买家不同意申请异议
                    } else if (state == Quantity.STATE_6) {
                        orderProduct.setBackstate(Quantity.STATE_4);
                        orderProductBack.setDissidencetime(new Date());
                        //平台同意退货不扣违约金
                    } else if (state == Quantity.STATE_7) {
                        //只退款
                        if (orderProductBack.getBacktype() == Quantity.STATE_0) {
                            handleBackGoods(member, orderProductBack, orderProduct, orders);
                            orderProduct.setBackstate(Quantity.STATE_3);
                            orderProductBack.setState(Quantity.STATE_10);
                            //增加库存
                            if (orders.getIsonline() == Quantity.STATE_0) {
                                store.setPdstorenum(store.getPdstorenum().add(orderProductBack.getPdnum()));
                                ordersService.updateProductStore(store);
                            }
                            if (orders.getIsonline() == Quantity.STATE_2) {
                                limitTimeStore.setStorenum(limitTimeStore.getStorenum().add(orderProductBack.getPdnum()));
                                limitTimeStore.setSalesnum(limitTimeStore.getSalesnum().subtract(orderProductBack.getPdnum()));
                                shopCarService.updateLimitTimeStore(limitTimeStore);
                                limitTimeProd.setSalestotalnum(limitTimeProd.getSalestotalnum().subtract(orderProduct.getNum()));
                                shopCarService.updateLimitTimeProd(limitTimeProd);
                            }
                        } else {
                            //退货验收
                            orderProduct.setBackstate(Quantity.STATE_2);
                        }
                        //平台同意退货扣违约金
                    } else if (state == Quantity.STATE_8) {
                        //只退款
                        if (orderProductBack.getBacktype() == Quantity.STATE_0) {
                            handleBackGoods(member, orderProductBack, orderProduct, orders);
                            orderProduct.setBackstate(Quantity.STATE_3);
                            orderProductBack.setState(Quantity.STATE_10);
                            //增加库存
                            if (orders.getIsonline() == Quantity.STATE_0) {
                                store.setPdstorenum(store.getPdstorenum().add(orderProductBack.getPdnum()));
                                ordersService.updateProductStore(store);
                            }
                            if (orders.getIsonline() == Quantity.STATE_2) {
                                limitTimeStore.setStorenum(limitTimeStore.getStorenum().add(orderProductBack.getPdnum()));
                                limitTimeStore.setSalesnum(limitTimeStore.getSalesnum().subtract(orderProductBack.getPdnum()));
                                shopCarService.updateLimitTimeStore(limitTimeStore);
                                limitTimeProd.setSalestotalnum(limitTimeProd.getSalestotalnum().subtract(orderProduct.getNum()));
                                shopCarService.updateLimitTimeProd(limitTimeProd);
                            }
                        } else {
                            //退货验收
                            orderProduct.setBackstate(Quantity.STATE_2);
                        }
                        //平台转入待验收
                    } else if (state == Quantity.STATE_9) {
                        orderProduct.setBackstate(Quantity.STATE_0);
                    } else if (state == Quantity.STATE_11) {
                        orderProduct.setBackstate(Quantity.STATE_0);
                    } else {
                        //卖家不同意
                    }
                    ordersService.updateOrderProduct(orderProduct);
                    ordersService.updateOrderProductBack(orderProductBack);

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
                }*/
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
                    if(buyerCapital.getPaytype()==Quantity.STATE_0||
                            buyerCapital.getPaytype()==Quantity.STATE_1||
                            buyerCapital.getPaytype()==Quantity.STATE_2){
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

                            //System.out.println("退货通道："+refund.getChannel()+"退货结果："+result);
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
                            }else{
                                throw  new RuntimeException("退款失败");
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
                    if(buyerCapital.getPaytype()==Quantity.STATE_0||
                            buyerCapital.getPaytype()==Quantity.STATE_1||
                            buyerCapital.getPaytype()==Quantity.STATE_2){
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
                            }else{
                                throw  new RuntimeException("退款失败");
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

                    //违约金总额
                    BigDecimal totalPenal = orderPay.multiply(getLiquidated).setScale(2,BigDecimal.ROUND_HALF_UP);
                    //买家退款金额
                    BigDecimal buyerBackPay = orderPay.subtract(totalPenal).setScale(2,BigDecimal.ROUND_HALF_UP);

                    //卖家退款金额
                    BigDecimal salerBackPay = orderPay.setScale(2,BigDecimal.ROUND_HALF_UP);

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
                            }else{
                                throw  new RuntimeException("退款失败");
                            }
                        }
                    }

                    //异议扣违约金,记录资金明细
                    if (orderProductBack.getAdminstate() == Quantity.STATE_2) {
                        penaltyPay = totalPenal;
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
                //买家退款资金明细
                BuyerCapital buyerCapital1 = null;
                //买家违约资金明细
                BuyerCapital buyerCapital2 = null;
                //卖家退款资金明细
                SalerCapital salerCapital1 = null;
                //卖家违约资金明细
                SalerCapital salerCapital2 = null;
                //立即发货
                if (orders.getOrdertype() == Quantity.STATE_0) {

                    //判断退回到余额还是授信
                    BuyerCapital buyerCapital = ordersService.getBuyerCapitalByNoType(orders.getOrderno(), Quantity.STATE_0);
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
                                if(orders.getPaytype()==Quantity.STATE_0){
                                    refund.setChannel("alipay");
                                }else if(orders.getPaytype() == Quantity.STATE_1){
                                    refund.setChannel("wxpay");
                                }else if(orders.getPaytype() == Quantity.STATE_2){
                                    refund.setChannel("bank");
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
                                    }else if("bank".equals(refund.getChannel())){
                                        result = abcService.refund(refund);
                                    }
                                }catch (Exception e){
                                    throw  e;
                                }

                                System.out.println("退货通道："+refund.getChannel()+"退货结果："+result);
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
                    BuyerCapital buyerCapital = ordersService.getBuyerCapitalByNoType(orders.getOrderno(), Quantity.STATE_9);
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
                                if(orders.getPaytype()==Quantity.STATE_0){
                                    refund.setChannel("alipay");
                                }else if(orders.getPaytype() == Quantity.STATE_1){
                                    refund.setChannel("wxpay");
                                }else if(orders.getPaytype() == Quantity.STATE_2){
                                    refund.setChannel("bank");
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
                                    }else if("bank".equals(refund.getChannel())){
                                        result = abcService.refund(refund);
                                    }
                                }catch (Exception e){
                                    throw e;
                                }

                                System.out.println("退货通道："+refund.getChannel()+"退货结果："+result);
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
                    BuyerCapital depositBuyerCapital = ordersService.getBuyerCapitalByNoType(orders.getOrderno(), Quantity.STATE_7);
                    if (depositBuyerCapital != null) {
                        //退回到余额
                        if (depositBuyerCapital.getPaytype() == Quantity.STATE_3) {
                            buyer.setBalance(buyer.getBalance().add(backPay).add(buyerPenaltyPay));
                            buyerCapital1 = createBuyerBackPay(orders, backPay, tranTime, Quantity.STATE_3);
                            salerCapital1 = createSalerBackPay(orders, backPay, tranTime);
                            buyerCapitals.add(buyerCapital1);
                            salerCapitals.add(salerCapital1);
                        }

                        //退回到授信
                        if (depositBuyerCapital.getPaytype() == Quantity.STATE_4) {
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
                        if(depositBuyerCapital.getPaytype()==Quantity.STATE_0||
                                depositBuyerCapital.getPaytype()==Quantity.STATE_1 ||
                                depositBuyerCapital.getPaytype()== Quantity.STATE_2
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
                                if(orders.getPaytype()==Quantity.STATE_0){
                                    refund1.setChannel("alipay");
                                    refund2.setChannel("alipay");
                                }else if(orders.getPaytype() == Quantity.STATE_1){
                                    refund1.setChannel("wxpay");
                                    refund2.setChannel("wxpay");
                                }else if(orders.getPaytype() == Quantity.STATE_2){
                                    refund1.setChannel("bank");
                                    refund2.setChannel("bank");
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
                                    }else if("bank".equals(refund1.getChannel())){
                                        result1 = abcService.refund(refund1);
                                        result2 = abcService.refund(refund2);
                                    }
                                }catch (Exception e){
                                   throw  e;
                                }
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
                //保存用户余额和授信
                ordersService.saveMember(buyer,oldBuyer);
                ordersService.saveMember(member,oldMember);
                ordersService.insertBuyerCapital(buyerCapitals);
                ordersService.insertSallerCapital(salerCapitals);
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
                LimitTimeStore store = shopCarService.getLimitTimeStore(orderProduct.getStoreid());
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

        //订单关闭
        orders.setOrderstatus(Quantity.STATE_7);
        ordersService.updateSingleOrder(orders);
        //删除开票申请记录
        ordersService.deleteBillRecord(orders.getOrderno());





        // syn wms
        wmsService.cancelOrders(orders, WMSService.CANCEL_ORDER_TYPE);
        ordersService.updateReason(orders,"卖家取消订单");

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
                                          @RequestParam(required = true) String orderProductJson,Model model) throws WxPayException, AlipayApiException, CashException {

        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        TransactionSetting transactionSetting = transactionSettingService.getTransactionSetting();

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


        /**
         * 卖方修改订单数量，算卖方违约，卖方需要缴纳一定违约金
         * 违约金 = 货品退货金额*违约金比例
         *
         * 缴纳违约金部分 = 订单总金额-修改后订单总金额 - （总运费-修改后总运费）
         */
        BigDecimal sellerWyMoney = orderActualpayment.subtract(totalPrice).subtract(orderFreight.subtract(totalFreight));


        //买家获取到退款违约金金额
        BigDecimal buyerPenaltyPay = Quantity.BIG_DECIMAL_0;

        //违约金
        BigDecimal penaltyPay = sellerWyMoney.multiply(transactionSetting.getGetliquidated().multiply(new BigDecimal(0.01))).setScale(2,BigDecimal.ROUND_HALF_UP);

        //被违约方获取违约金占比
        BigDecimal forwardsalesmargin = transactionSetting.getForwardsalesmargin().multiply(new BigDecimal(0.01));


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
            updateOrders.setReason("卖家取消订单");
            updateOrders.setTotalprice(Quantity.BIG_DECIMAL_0);
            updateOrders.setActualpayment(Quantity.BIG_DECIMAL_0);
            updateOrders.setFreight(Quantity.BIG_DECIMAL_0);
            ordersService.deleteBillRecord(orders.getId().toString());
        }else if(backMoney.compareTo(Quantity.BIG_DECIMAL_0) >=0){
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
        operateLog.setContent("卖家修改订单商品数量，退款"+backMoney);
        operateLog.setOpid(member.getId());
        operateLog.setOpname(member.getUsername());
        operateLog.setOptime(new Date());
        operateLog.setOptype(Quantity.STATE_0);
        operateLog.setOrderid(orders.getId());
        operateLog.setOrderno(orders.getOrderno());
        ordersService.saveOperatelog(operateLog);


        return  new BasicRet(BasicRet.SUCCESS,"修改成功");
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
                    return getTotalCost(defaultfreight, defaultcost, perkilogramadded, perkilogramcost, totalweight);
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
                        return getTotalCost(udefaultfreight, udefaultcost, uperkilogramadded, uperkilogramcost, totalweight);

                    } else {
                        //计算默认运费
                        return getTotalCost(defaultfreight, defaultcost, perkilogramadded, perkilogramcost, totalweight);
                    }

                } else {
                    //计算默认运费
                    return getTotalCost(defaultfreight, defaultcost, perkilogramadded, perkilogramcost, totalweight);
                }
            }
        } else {
            return new BigDecimal(0);
        }
        return new BigDecimal(0);
    }

    /**
     * 计算运费
     *
     * @param defaultfreight   默认运费公斤
     * @param defaultcost      默认运费
     * @param perkilogramadded 每增加公斤
     * @param perkilogramcost  每增加运费
     * @param totalweight      商品总重量
     * @return 每个商品的运费
     */
    private BigDecimal getTotalCost(BigDecimal defaultfreight, BigDecimal defaultcost, BigDecimal perkilogramadded, BigDecimal perkilogramcost, BigDecimal totalweight) {

        BigDecimal totalCost = new BigDecimal(0);
        //判断重量计算运费
        //如果小于或等于默认重量
        if (totalweight.compareTo(defaultfreight) == Quantity.STATE_ || totalweight.compareTo(defaultfreight) == Quantity.STATE_0) {
            totalCost = defaultcost;
        } else {
            //剩余的重量
            BigDecimal substractWeight = totalweight.subtract(defaultfreight);
            //倍数和余数
            BigDecimal[] results = substractWeight.divideAndRemainder(perkilogramadded);
            //如果剩下的重量小于每增加公斤数
            if (results[0].compareTo(new BigDecimal(0)) == Quantity.INT_0) {
                totalCost = defaultcost.add(perkilogramcost);
            } else {
                BigDecimal multiplycost = results[0].multiply(perkilogramcost);
                //如果没有余数
                if (results[1].compareTo(new BigDecimal(0)) == Quantity.INT_0) {
                    totalCost = multiplycost.add(defaultcost);
                } else {
                    //如果有余数
                    totalCost = multiplycost.add(perkilogramcost).add(defaultcost);
                }
            }
        }
        return totalCost;
    }

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

        PageInfo pageInfo = ordersService.getDeliveryRecord(param);

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
