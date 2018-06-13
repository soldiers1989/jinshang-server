package project.jinshang.mod_product;

import com.alipay.api.AlipayApiException;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import mizuki.project.core.restserver.config.BasicRet;
import mizuki.project.core.restserver.util.JsonUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.jinshang.common.bean.MemberLogOperator;
import project.jinshang.common.bean.PageRet;
import project.jinshang.common.constant.AgentDeliveryAddressConst;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.common.constant.Quantity;
import project.jinshang.common.constant.TradeConstant;
import project.jinshang.common.exception.CashException;
import project.jinshang.common.utils.*;
import project.jinshang.mod_activity.bean.LimitTimeProd;
import project.jinshang.mod_activity.bean.LimitTimeStore;
import project.jinshang.mod_activity.service.LimitTimeProdService;
import project.jinshang.mod_activity.service.LimitTimeStoreService;
import project.jinshang.mod_admin.mod_commondata.service.CommonDataValueService;
import project.jinshang.mod_admin.mod_inte.bean.IntegralRecord;
import project.jinshang.mod_admin.mod_inte.bean.IntegralSet;
import project.jinshang.mod_admin.mod_inte.service.IntegralService;
import project.jinshang.mod_admin.mod_transet.bean.TransactionSetting;
import project.jinshang.mod_admin.mod_transet.service.TransactionSettingService;
import project.jinshang.mod_cash.bean.BuyerCapital;
import project.jinshang.mod_cash.bean.SalerCapital;
import project.jinshang.mod_common.bean.BasicExtRet;
import project.jinshang.mod_company.bean.AgentDeliveryAddress;
import project.jinshang.mod_company.bean.SellerCompanyInfo;
import project.jinshang.mod_company.service.SellerCompanyInfoService;
import project.jinshang.mod_invoice.bean.InvoiceInfo;
import project.jinshang.mod_member.bean.*;
import project.jinshang.mod_member.service.AdminService;
import project.jinshang.mod_member.service.AdminUserService;
import project.jinshang.mod_member.service.MemberRateSettingService;
import project.jinshang.mod_member.service.MemberService;
import project.jinshang.mod_pay.bean.Refund;
import project.jinshang.mod_pay.mod_alipay.AlipayService;
import project.jinshang.mod_pay.mod_bankpay.AbcService;
import project.jinshang.mod_pay.mod_wxpay.MyWxPayService;
import project.jinshang.mod_product.bean.*;
import project.jinshang.mod_product.service.*;
import project.jinshang.mod_shippingaddress.bean.ShippingAddress;
import project.jinshang.mod_shippingaddress.service.ShippingAddressService;
import project.jinshang.mod_wms_middleware.WMSService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

/**
 * 买家订单模块
 */
@RestController
@RequestMapping("/rest/buyer/orders")
@SessionAttributes({AppConstant.MEMBER_SESSION_NAME})
@Api(tags = "买家订单模块", description = "买家订单模块")
@Transactional(rollbackFor = Exception.class)
public class OrdersAction {

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
    private IntegralService integralService;

    @Autowired
    private MemberOperateLogService memberOperateLogService;

    @Autowired
    private SellerCompanyInfoService sellerCompanyInfoService;

    @Autowired
    private ProductAttrService productAttrService;

    @Autowired
    private MyWxPayService wxPayService;
    @Autowired
    private AlipayService alipayService;

    @Autowired
    private AbcService abcService;

    @Autowired
    private CommonDataValueService commonDataValueService;

    @Autowired
    private BillingRecordService billingRecordService;


    @Autowired
    private OrderProductBackService orderProductBackService;


    @Autowired
    private ProductStoreService productStoreService;

    @Autowired
    private OrderProductServices orderProductServices;


    @Autowired
    private LimitTimeStoreService limitTimeStoreService;

    @Autowired
    private LimitTimeProdService limitTimeProdService;

    @Autowired
    private CategoriesService categoriesService;

    @Autowired
    private OrderProductLogService orderProductLogService;

    @Autowired
    private OrderProductBackInfoService OrderProductBackInfoService;





    MemberLogOperator memberLogOperator = new MemberLogOperator();

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    private String lock = new String("lock");

    //2018年6月1日14:09:57
    //添加业务员信息
    @Autowired
    private AdminUserService adminUserService;
    //添加业务员信息
    @Autowired
    private AdminService adminService;

    /**
     * 买家获取收货地址
     *
     * @param model redis缓存 获取用户信息
     * @return 买家收货地址
     */
    @RequestMapping(value = "/getShippingAddress", method = RequestMethod.POST)
    @ApiOperation(value = "买家获取收货地址")
    public OrderCarRet getShippingAddress(Model model, HttpServletRequest request) {
        OrderCarRet orderCarRet = new OrderCarRet();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        List<ShippingAddress> list = ordersService.getShippingAddress(member.getId());
        orderCarRet.setResult(BasicRet.SUCCESS);
        orderCarRet.setMessage("返回成功");
        orderCarRet.data.shippingAddresses = list;

        return orderCarRet;
    }


    /**
     * 单件商品买家提交订单
     *
     * @param model
     * @param billingRecord
     * @param orders
     * @param orderProduct
     * @return
     */
    @RequestMapping(value = "/submitProductToOrder", method = RequestMethod.POST)
    @ApiOperation(value = "单件商品买家提交订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "invoiceid", value = "发票id", required = true, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "shipto", value = "收货人", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "phone", value = "收货人电话", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "province", value = "省", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "city", value = "市", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "area", value = "区", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "receivingaddress", value = "详细地址", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "isbilling", value = "是否开票0=不开票1=开票", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "billingtype", value = "发票类型0=纸质1=电子", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pdid", value = "商品id", required = true, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "pdno", value = "商品编号", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "protype", value = "远期类型0=不是远期1=全款2=定金", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "sellerid", value = "卖家id", required = true, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "unit", value = "单位", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "num", value = "数量", required = true, paramType = "query", dataType = "double"),
            @ApiImplicitParam(name = "storeid", value = "仓库id", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "storename", value = "仓库名称", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "deliverytime", value = "发货时间", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "ismailornot", value = "是否自提", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "isonline", value = "订单类型0=普通订单1=线下2=限时购", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "limitid", value = "活动id", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "deliverybill", value = "是否需要发货单，1-需要，0-不需要", required = false, paramType = "query", dataType = "int"),
    })
    public BasicRet submitProductToOrder(Model model, BillingRecord billingRecord, Orders orders, OrderProduct orderProduct, HttpServletRequest request) throws Exception {
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        OrderCarRet basicRet = new OrderCarRet();

        //判断收货地址
        if (!StringUtils.hasText(orders.getProvince()) || !StringUtils.hasText(orders.getCity()) || !StringUtils.hasText(orders.getArea()) || !StringUtils.hasText(orders.getReceivingaddress())) {
            basicRet.setMessage("请填写收货地址");
            basicRet.setResult(BasicRet.ERR);
            return basicRet;
        }

        OrderProductLog orderProductLog = new OrderProductLog();


        if (orders.getIsonline() == null) {
            orders.setIsonline(Quantity.STATE_0);
        }
        List<Orders> ordersList = new ArrayList<Orders>();
        List<ProductAttr> productAttrs = productAttrService.getListByPidAndPdno(orderProduct.getPdid(), orderProduct.getPdno());
        StringBuffer sb = new StringBuffer();
        for (ProductAttr attr : productAttrs) {
            sb.append(attr.getValue()).append("*");
        }
        if (productAttrs.size() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }

        orderProductLog.setProductattrjson(GsonUtils.toJson(productAttrs));


        if (orders.getIsonline() == Quantity.STATE_0) {  //普通订单
            ProductInfo productInfo = ordersService.getProductInfoByPrimeKey(orderProduct.getPdid());

            if (productInfo == null) {
                return new BasicRet(BasicRet.ERR, "商品不存在");
            }

            if (productInfo.getPdstate() != Quantity.STATE_4) {
                return new BasicRet(BasicRet.ERR, "商品处于下架状态");
            }

            //判断买家卖家是否是同一家
            if (productInfo.getMemberid() == member.getId()) {
                basicRet.setResult(BasicRet.ERR);
                basicRet.setMessage("不能购买自己的商品");
                return basicRet;
            }

            ProductStore productStore = shopCarService.getProductStore(orderProduct.getPdid(), orderProduct.getPdno(), orderProduct.getStoreid());
            Brand brand = shopCarService.getBrandById(productInfo.getBrandid());

            if (productInfo != null && productStore != null && brand != null) {

                //商品基本属性
                orderProduct.setPdname(productInfo.getProductname());

                orderProduct.setAttrjson(sb.toString());
                if (StringUtils.hasText(productInfo.getBrand())) {
                    orderProduct.setBrand(productInfo.getBrand());
                }
                if (StringUtils.hasText(productInfo.getMark())) {
                    orderProduct.setMark(productInfo.getMark());
                }
                if (StringUtils.hasText(productInfo.getStand())) {
                    orderProduct.setStandard(productInfo.getStand());
                }
                if (StringUtils.hasText(productInfo.getPackagetype())) {
                    orderProduct.setPddesc(productInfo.getPackagetype());
                }
                if (productInfo.getProducttype().equals("紧固件")) {
                    orderProduct.setProducttype(Quantity.STATE_1);
                } else {
                    orderProduct.setProducttype(Quantity.STATE_2);
                }
                String[] strpic = (String[]) productInfo.getPdpicture();
                if (strpic != null) {
                    if (strpic.length > 0) {
                        orderProduct.setPdpic(strpic[0]);
                    }
                }
                if (brand.getPic() != null) {
                    orderProduct.setPic(brand.getPic());
                }

                if (StringUtils.hasText(productInfo.getLevel3())) {
                    orderProduct.setClassify(productInfo.getLevel3());
                    orderProduct.setClassifyid(productInfo.getLevel3id());
                } else if (StringUtils.hasText(productInfo.getLevel2())) {
                    orderProduct.setClassify(productInfo.getLevel2());
                    orderProduct.setClassifyid(productInfo.getLevel2id());
                } else {
                    orderProduct.setClassify(productInfo.getLevel1());
                    orderProduct.setClassifyid(productInfo.getLevel1id());
                }


                if (StringUtils.hasText(productInfo.getMaterial())) {
                    orderProduct.setMaterial(productInfo.getMaterial());
                }
                if (StringUtils.hasText(productInfo.getCardnum())) {
                    orderProduct.setGradeno(productInfo.getCardnum());
                }

                BigDecimal convertNum = orderProduct.getNum();
                String convertUnit = orderProduct.getUnit();
                if (AppConstant.FASTENER_PRO_TYPE.equals(productInfo.getProducttype())) {
                    Map<String, Object> map = JinshangUtils.toLowerUnit(productInfo.getPackagetype(), orderProduct.getNum(), orderProduct.getUnit());
                    convertNum = (BigDecimal) map.get("num");
                    convertUnit = (String) map.get("unit");
                }

                orderProduct.setUnit(convertUnit);
                orderProduct.setNum(convertNum);
                //判断库存
                BigDecimal storeNum = productStore.getPdstorenum();
                if (convertNum.compareTo(storeNum) == Quantity.STATE_1) {
                    basicRet.setResult(BasicRet.ERR);
                    basicRet.setMessage("库存不足");
                    return basicRet;
                }
                //减库存操作
                productStore.setPdstorenum(productStore.getPdstorenum().subtract(convertNum));
                ordersService.updateProductStore(productStore);

                //是否包邮
                if (productStore.getFreightmode() == Quantity.STATE_) {
                    orderProduct.setMailornot(true);
                } else {
                    orderProduct.setMailornot(false);
                }


                //计算运费
                BigDecimal figtht = ordersService.countSinglePdFight(productInfo, productStore, orders.getProvince(), orders.getCity(), convertNum);
                orderProduct.setFreight(figtht);
                //计算价格
                BigDecimal salePrice = updatePriceByNum(convertNum, productStore, orderProduct.getDeliverytime(), productInfo.getMemberid(), productInfo.getLevel3id(), member.getGradleid());
                orderProduct.setPrice(salePrice);

                //订单总价
                BigDecimal totalPrice = BigDecimal.valueOf(0);
                BigDecimal appPap = salePrice.multiply(convertNum);
                //商品总价

                if (orderProduct.getProtype() == Quantity.STATE_0) {
                    totalPrice = figtht.add(appPap);
                    orders.setOrdertype(Quantity.STATE_0);
                    orderProduct.setActualpayment(totalPrice);
                }
                //全款
                if (orderProduct.getProtype() == Quantity.STATE_1) {
                    BigDecimal allPay = appPap.multiply(TradeConstant.allPayRate);
                    orderProduct.setAllpay(allPay);
                    totalPrice = figtht.add(allPay);
                    orderProduct.setActualpayment(totalPrice);
                    orders.setAllpay(allPay);
                    orders.setOrdertype(Quantity.STATE_1);
                }
                //定金
                if (orderProduct.getProtype() == Quantity.STATE_2) {
                    BigDecimal partpay = appPap.multiply(transactionSettingService.getTransactionSetting().getRemotepurchasingmargin()).multiply(new BigDecimal(0.01));
                    BigDecimal yupay = appPap.subtract(partpay);
                    orderProduct.setPartpay(partpay);
                    orderProduct.setYupay(yupay);
                    totalPrice = figtht.add(appPap);
                    orderProduct.setActualpayment(totalPrice);
                    orders.setDeposit(partpay);
                    orders.setBalance(yupay);
                    orders.setOrdertype(Quantity.STATE_2);
                }
                //订单总价
                orders.setTotalprice(totalPrice);
                //实付款
                orders.setActualpayment(totalPrice);
                //订单总运费
                orders.setFreight(figtht);

                String orderNo = GenerateNo.getOrderNo();
                orderProduct.setOrderno(orderNo);

                //提交订单
                /*String uuid = GenerateNo.getUUID();
                orders.setUuid(uuid);*/

                //买家id
                orders.setMemberid(member.getId());
                orders.setMembername(member.getUsername());
                //卖家id
                orders.setSaleid(orderProduct.getSellerid());
                Member member1 = memberService.getMemberById(orderProduct.getSellerid());
                if (member1 != null) {
                    orders.setSellername(member1.getUsername());
                }
                //获取公司名称
                SellerCompanyInfo sellerCompanyInfo = sellerCompanyInfoService.getSellerCompanyByMemberid(orderProduct.getSellerid());
                if (sellerCompanyInfo == null) {
                    throw new RuntimeException("卖家不存在");
                }

                orders.setMembercompany(sellerCompanyInfo.getCompanyname());
                orders.setShopname(sellerCompanyInfo.getShopname());
                //发货模式为平台代发
                if (sellerCompanyInfo.getDeliverymode() == Quantity.STATE_1) {
                    orders.setDeliverytype(Quantity.STATE_1);
                }

                //计算远期发货时间
                Date futureTime = getFutureTime(orderProduct);
                if (futureTime != null) {
                    orders.setFuturetime(futureTime);
                }

                //订单编号
                orders.setOrderno(orderNo);
                orders.setTransactionnumber(GenerateNo.getTransactionNo());
                //合同编号
                orders.setCode(GenerateNo.getContractNo());

                //仓库id
                orders.setStoreid(orderProduct.getStoreid());
                //仓库名称
                orders.setStorename(orderProduct.getStorename());
                orders.setCreatetime(new Date());

                //查询是否有业务员
                AdminUser AdminUser = adminUserService.getAdminUserByUserid(member.getId());
                if (AdminUser != null) {
                    //添加业务员/业务员联系方式
                    Admin admin = adminService.getById(AdminUser.getAdminid());
                    if(admin !=null){
                        orders.setClerkname(admin.getRealname());
                        orders.setClerknamephone(admin.getMobile());
                    }
                }

                ordersService.insertOrders(orders);
                orderProduct.setOrderid(orders.getId());
                ordersService.insertOrderProduct(orderProduct);

                //如果选择了开票
                if (orders.getIsbilling() != Quantity.STATE_0) {
                    if (billingRecord.getInvoiceid() != null) {
                        InvoiceInfo invoiceInfo = ordersService.getInvoiceInfo(billingRecord.getInvoiceid());
                        if (invoiceInfo != null) {
                            //创建开票记录
                            billingRecord.setInvoiceheadup(invoiceInfo.getInvoiceheadup());
                            billingRecord.setTexno(invoiceInfo.getTexno());
                            billingRecord.setBankofaccounts(invoiceInfo.getBankofaccounts());
                            billingRecord.setAccount(invoiceInfo.getAccount());
                            billingRecord.setPhone(invoiceInfo.getPhone());
                            billingRecord.setAddress(invoiceInfo.getAddress());
                            billingRecord.setMemberid(member.getId());
                            billingRecord.setMembername(member.getUsername());
                            billingRecord.setRemark(invoiceInfo.getAvailable());
                            billingRecord.setOrderno(orders.getId().toString());
                            billingRecord.setBillcash(totalPrice);
                            billingRecord.setBillingrecordtype(orders.getBillingtype());
                            billingRecord.setReceiveaddress(invoiceInfo.getReceiveaddress());


                            //提前创建出发票信息，等该笔订单交易成功时设置为0
                            billingRecord.setState(Quantity.STATE_);

                            ordersService.insertBillingRecord(billingRecord);

                            BillOrder billOrder = new BillOrder();

                            billOrder.setBillrecordid(billingRecord.getId());
                            billOrder.setOrderid(orders.getId());
                            ordersService.saveBillOrder(billOrder);
                        }
                    }
                }
                ordersList.add(orders);
                basicRet.setResult(BasicRet.SUCCESS);
                basicRet.setMessage("提交成功");
                basicRet.data.ordersList = ordersList;


                List<String> ordernoList = new ArrayList<>();
                ordersList.forEach(o -> ordernoList.add(o.getOrderno()));
                //提前计算出该订单及该订单商品的佣金
                ordersService.jisuanOrdersBreakPay(ordernoList);


                orderProductLog.setProductinfojson(GsonUtils.toJson(productInfo));
                orderProductLog.setProductstorejson(GsonUtils.toJson(productStore));
                orderProductLog.setOrderproductid(orderProduct.getId());
                orderProductLogService.add(orderProductLog);

                //操作日志
                OperateLog operateLog = new OperateLog();
                operateLog.setContent("提交订单");
                operateLog.setOpid(member.getId());
                operateLog.setOpname(member.getUsername());
                operateLog.setOptime(new Date());
                operateLog.setOptype(Quantity.STATE_0);
                operateLog.setOrderid(orders.getId());
                operateLog.setOrderno(orders.getOrderno());
                ordersService.saveOperatelog(operateLog);
                //用户日志
                memberLogOperator.saveMemberLog(member, null, "提交订单", "/rest/buyer/orders/submitProductToOrder", request, memberOperateLogService);

                return basicRet;
            } else {
                basicRet.setResult(BasicRet.ERR);
                basicRet.setMessage("没有该商品");
                return basicRet;
            }
        } else if (orders.getIsonline() == Quantity.STATE_2) {  ////限时购
            LimitTimeProd prod = shopCarService.getLimitTimeProd(orderProduct.getPdid(), orderProduct.getLimitid());
            //判断活动是否开始
            Date startTime = prod.getBegintime();
            Date endTime = prod.getEndtime();
            Date now = new Date();
            if (prod.getState() == Quantity.STATE_4) {
                if (startTime.compareTo(now) == 1) {
                    basicRet.setResult(BasicRet.ERR);
                    basicRet.setMessage("活动还未开始");
                    return basicRet;
                }
                if (endTime.compareTo(now) == -1) {
                    basicRet.setResult(BasicRet.ERR);
                    basicRet.setMessage("活动已结束");
                    return basicRet;
                }
                Long sellerId = prod.getMemberid();
                if (sellerId == member.getId()) {
                    basicRet.setMessage("不能购买自己的商品");
                    basicRet.setResult(BasicRet.ERR);
                    return basicRet;
                }

                if (prod.getBuylimit().compareTo(orderProduct.getNum()) == -1) {
                    basicRet.setMessage("同一账号超过购买数量");
                    basicRet.setResult(BasicRet.ERR);
                    return basicRet;
                }


                //查询订单商品，查看是否之前有买过该限时购的商品
                BigDecimal buyTotalNum = orderProductServices.getTotalNumByLimitid(member.getId(), orderProduct.getLimitid());
                buyTotalNum = buyTotalNum == null ? new BigDecimal(0) : buyTotalNum;

                if (prod.getBuylimit().compareTo(buyTotalNum.add(orderProduct.getNum())) == -1) {
                    basicRet.setMessage("您本次最多可购买" + prod.getBuylimit().subtract(buyTotalNum) + orderProduct.getUnit());
                    basicRet.setResult(BasicRet.ERR);
                    return basicRet;
                }


                LimitTimeStore limitTimeStore = shopCarService.getLimitTimeStore(orderProduct.getLimitid(), orderProduct.getPdid(), orderProduct.getPdno());
                if (limitTimeStore.getStorenum().compareTo(orderProduct.getNum()) == -1) {
                    basicRet.setMessage("超过库存数量");
                    basicRet.setResult(BasicRet.ERR);
                    return basicRet;
                }
                ProductInfo productInfo = ordersService.getProductInfoByPrimeKey(orderProduct.getPdid());
                Brand brand = shopCarService.getBrandById(productInfo.getBrandid());


                //更新库存
                //limitTimeStore.setStorenum(limitTimeStore.getStorenum().subtract(orderProduct.getNum()));
                //limitTimeStore.setSalesnum(limitTimeStore.getSalesnum().add(orderProduct.getNum()));
                //shopCarService.updateLimitTimeStore(limitTimeStore);
                //替换上面的代码块
                limitTimeStoreService.updateLimitStoreNum(limitTimeStore.getId(), orderProduct.getNum().multiply(new BigDecimal(-1)), orderProduct.getNum());

                LimitTimeStore newLimitStore = limitTimeStoreService.selectByPrimaryKey(limitTimeStore.getId());
                if (newLimitStore == null || newLimitStore.getStorenum().compareTo(new BigDecimal(0)) == -1) {
                    throw new Exception("库存不足");
                }

                //
                //prod.setSalestotalnum(prod.getSalestotalnum().add(orderProduct.getNum()));
                //shopCarService.updateLimitTimeProd(prod);
                //更新总的销量
                limitTimeProdService.updateSalestotalnumInDB(prod.getId(), orderProduct.getNum());


                //商品基本属性
                orderProduct.setPdname(productInfo.getProductname());

                orderProduct.setAttrjson(sb.toString());
                if (StringUtils.hasText(productInfo.getBrand())) {
                    orderProduct.setBrand(productInfo.getBrand());
                }
                if (StringUtils.hasText(productInfo.getMark())) {
                    orderProduct.setMark(productInfo.getMark());
                }
                if (StringUtils.hasText(productInfo.getStand())) {
                    orderProduct.setStandard(productInfo.getStand());
                }
                if (StringUtils.hasText(productInfo.getPackagetype())) {
                    orderProduct.setPddesc(productInfo.getPackagetype());
                }
                if (productInfo.getProducttype().equals("紧固件")) {
                    orderProduct.setProducttype(Quantity.STATE_1);
                } else {
                    orderProduct.setProducttype(Quantity.STATE_2);
                }
                String[] strpic = (String[]) productInfo.getPdpicture();
                if (strpic != null) {
                    if (strpic.length > 0) {
                        orderProduct.setPdpic(strpic[0]);
                    }
                }

                if (brand.getPic() != null) {
                    orderProduct.setPic(brand.getPic());
                }
                if (StringUtils.hasText(productInfo.getLevel3())) {
                    orderProduct.setClassify(productInfo.getLevel3());
                    orderProduct.setClassifyid(productInfo.getLevel3id());
                }
                if (StringUtils.hasText(productInfo.getMaterial())) {
                    orderProduct.setMaterial(productInfo.getMaterial());
                }
                if (StringUtils.hasText(productInfo.getCardnum())) {
                    orderProduct.setGradeno(productInfo.getCardnum());
                }

                BigDecimal convertNum = orderProduct.getNum();

                orderProduct.setUnit(orderProduct.getUnit());
                orderProduct.setNum(orderProduct.getNum());

                //是否包邮
                orderProduct.setMailornot(true);

                //计算运费
                BigDecimal figtht = BigDecimal.valueOf(0);
                orderProduct.setFreight(figtht);
                //计算价格

                orderProduct.setPrice(limitTimeStore.getLimitprice());

                //订单总价
                BigDecimal totalPrice = BigDecimal.valueOf(0);
                BigDecimal appPap = limitTimeStore.getLimitprice().multiply(orderProduct.getNum());
                //商品总价

                if (orderProduct.getProtype() == Quantity.STATE_0) {
                    totalPrice = figtht.add(appPap);
                    orders.setOrdertype(Quantity.STATE_0);
                    orderProduct.setActualpayment(totalPrice);
                }

                //订单总价
                orders.setTotalprice(totalPrice);
                //实付款
                orders.setActualpayment(totalPrice);
                //订单总运费
                orders.setFreight(figtht);

                String orderNo = GenerateNo.getOrderNo();
                orderProduct.setOrderno(orderNo);

                //提交订单
                /*String uuid = GenerateNo.getUUID();
                orders.setUuid(uuid);*/

                //买家id
                orders.setMemberid(member.getId());
                orders.setMembername(member.getUsername());
                //卖家id
                orders.setSaleid(orderProduct.getSellerid());
                Member member1 = memberService.getMemberById(orderProduct.getSellerid());
                if (member1 != null) {
                    orders.setSellername(member1.getUsername());
                }
                //获取公司名称
                SellerCompanyInfo sellerCompanyInfo = sellerCompanyInfoService.getSellerCompanyByMemberid(orderProduct.getSellerid());
                if (sellerCompanyInfo == null) {
                    throw new RuntimeException("卖家不存在");
                }

                orders.setMembercompany(sellerCompanyInfo.getCompanyname());
                orders.setShopname(sellerCompanyInfo.getShopname());
                if (sellerCompanyInfo.getDeliverymode() == Quantity.STATE_1) {
                    orders.setDeliverytype(Quantity.STATE_1);
                }

                //计算远期发货时间
                Date futureTime = getFutureTime(orderProduct);
                if (futureTime != null) {
                    orders.setFuturetime(futureTime);
                }

                //订单编号
                orders.setOrderno(orderNo);
                orders.setTransactionnumber(GenerateNo.getTransactionNo());
                //合同编号
                orders.setCode(GenerateNo.getContractNo());
//                ProductStore productStore = shopCarService.getProductStore(orderProduct.getPdid(), orderProduct.getPdno(),orderProduct.getStoreid());
                ProductStore productStore = productStoreService.getByPdidAndPdno(orderProduct.getPdid(), orderProduct.getPdno());
                //仓库id
                orders.setStoreid(productStore.getStoreid());
                //仓库名称
                orders.setStorename(productStore.getStorename());
                orders.setCreatetime(new Date());
                ordersService.insertOrders(orders);
                orderProduct.setOrderid(orders.getId());
                ordersService.insertOrderProduct(orderProduct);

                //如果选择了开票
                if (orders.getIsbilling() != Quantity.STATE_0) {
                    if (billingRecord.getInvoiceid() != null) {
                        InvoiceInfo invoiceInfo = ordersService.getInvoiceInfo(billingRecord.getInvoiceid());
                        if (invoiceInfo != null) {
                            //创建开票记录
                            billingRecord.setInvoiceheadup(invoiceInfo.getInvoiceheadup());
                            billingRecord.setTexno(invoiceInfo.getTexno());
                            billingRecord.setBankofaccounts(invoiceInfo.getBankofaccounts());
                            billingRecord.setAccount(invoiceInfo.getAccount());
                            billingRecord.setPhone(invoiceInfo.getPhone());
                            billingRecord.setAddress(invoiceInfo.getAddress());
                            billingRecord.setMemberid(member.getId());
                            billingRecord.setMembername(member.getUsername());
                            billingRecord.setRemark(invoiceInfo.getAvailable());
                            billingRecord.setOrderno(orders.getId().toString());
                            billingRecord.setBillcash(totalPrice);
                            billingRecord.setBillingrecordtype(orders.getBillingtype());
                            billingRecord.setReceiveaddress(invoiceInfo.getReceiveaddress());

                            //提前创建出发票信息，等该笔订单交易成功时设置为0
                            billingRecord.setState(Quantity.STATE_);

                            ordersService.insertBillingRecord(billingRecord);

                            BillOrder billOrder = new BillOrder();

                            billOrder.setBillrecordid(billingRecord.getId());
                            billOrder.setOrderid(orders.getId());
                            ordersService.saveBillOrder(billOrder);
                        }
                    }
                }
                ordersList.add(orders);
                basicRet.setResult(BasicRet.SUCCESS);
                basicRet.setMessage("提交成功");
                basicRet.data.ordersList = ordersList;


                List<String> ordernoList = new ArrayList<>();
                ordersList.forEach(o -> ordernoList.add(o.getOrderno()));
                //提前计算出该订单及该订单商品的佣金
                ordersService.jisuanOrdersBreakPay(ordernoList);


                orderProductLog.setOrderproductid(orderProduct.getId());
                orderProductLog.setProductstorejson(GsonUtils.toJson(productStore));
                orderProductLog.setProductinfojson(GsonUtils.toJson(productInfo));
                orderProductLogService.add(orderProductLog);


                //操作日志
                OperateLog operateLog = new OperateLog();
                operateLog.setContent("提交订单");
                operateLog.setOpid(member.getId());
                operateLog.setOpname(member.getUsername());
                operateLog.setOptime(new Date());
                operateLog.setOptype(Quantity.STATE_0);
                operateLog.setOrderid(orders.getId());
                operateLog.setOrderno(orders.getOrderno());
                ordersService.saveOperatelog(operateLog);
                //用户日志
                memberLogOperator.saveMemberLog(member, null, "提交订单", "/rest/buyer/orders/submitProductToOrder", request, memberOperateLogService);

            } else {
                basicRet.setMessage("商品不在活动中");
                basicRet.setResult(BasicRet.ERR);
                return basicRet;
            }
        }

        return basicRet;
    }

    /**
     * 计算远期发货时间
     *
     * @param orderProduct
     * @return
     */
    private Date getFutureTime(@NotNull OrderProduct orderProduct) {
        Date futuretime = null;
        //计算远期发货时间
        if (!orderProduct.getDeliverytime().equals("立即发货")) {
            Calendar now = Calendar.getInstance();
            now.setTime(new Date());
            if (orderProduct.getDeliverytime().equals("3天后发货")) {
                now.set(Calendar.DATE, now.get(Calendar.DATE) + 3);
                futuretime = now.getTime();
            }
            if (orderProduct.getDeliverytime().equals("15天后发货")) {
                now.set(Calendar.DATE, now.get(Calendar.DATE) + 15);
                futuretime = now.getTime();
            }
            if (orderProduct.getDeliverytime().equals("30天后发货")) {
                now.set(Calendar.DATE, now.get(Calendar.DATE) + 30);
                futuretime = now.getTime();
            }
            if (orderProduct.getDeliverytime().equals("60天后发货")) {
                now.set(Calendar.DATE, now.get(Calendar.DATE) + 60);
                futuretime = now.getTime();
            }
        }
        return futuretime;
    }

    /**
     * 计算价格
     *
     * @param num
     * @param productStore
     * @param diliverTime
     * @return
     */
    private BigDecimal updatePriceByNum(BigDecimal num, ProductStore productStore, @NotNull String diliverTime, long sellerid, long levelid, long gradeid) {

        //计算阶梯价格
        BigDecimal basicPrice = BigDecimal.valueOf(0);

        if (diliverTime.equals(Quantity.SANTIANFAHUO)) {
            basicPrice = productStore.getThreeprice();
        } else if (diliverTime.equals(Quantity.JIUSHITIANFAHUO)) {
            basicPrice = productStore.getNinetyprice();
        } else if (diliverTime.equals(Quantity.SANSHITIANFAHUO)) {
            basicPrice = productStore.getThirtyprice();
        } else if (diliverTime.equals(Quantity.LIUSHITIANFAHUO)) {
            basicPrice = productStore.getSixtyprice();
        } else {
            basicPrice = productStore.getProdprice();
        }

        MemberRateSetting memberRateSetting = memberRateSettingService.getSetting(sellerid, levelid, gradeid);
        basicPrice = basicPrice.multiply(memberRateSetting.getRate());
        BigDecimal saleprice = BigDecimal.valueOf(0);
        //判断是否开启阶梯价格
        if (productStore.getStepwiseprice() == true) {
            Gson gson = new Gson();
            List<StepWisePrice> list = gson.fromJson(productStore.getIntervalprice(), new TypeToken<ArrayList<StepWisePrice>>() {
            }.getType());
            //是否匹配价格区间
            boolean flag = false;
            //最大价格区间百分比
            BigDecimal lastPercent = BigDecimal.valueOf(0);
            BigDecimal maxstart = BigDecimal.valueOf(0);
            //匹配价格区间
            for (StepWisePrice stepWisePrice : list) {
                BigDecimal start = stepWisePrice.getStart();
                BigDecimal end = stepWisePrice.getEnd();
                BigDecimal percent = stepWisePrice.getRate();
                //end为0是最大价格区间
                if (end.compareTo(BigDecimal.valueOf(0)) == 0) {
                    lastPercent = percent;
                    maxstart = start;
                }
                if ((num.compareTo(start) == 1) && (num.compareTo(end) == -1 || num.compareTo(end) == 0)) {
                    saleprice = basicPrice.multiply(percent.multiply(new BigDecimal(0.01)));
                    flag = true;
                    break;
                }
            }
            //没有任何价格区间匹配，取最大的价格区间
            if (!flag) {
                if (num.compareTo(maxstart) == 1) {
                    saleprice = basicPrice.multiply(lastPercent.multiply(new BigDecimal(0.01)));
                } else {
                    saleprice = basicPrice;
                }
            }
        } else {
            saleprice = basicPrice;
        }

        return saleprice;
    }


    private boolean checkBuyNum(BigDecimal buynum, BigDecimal minplus) {
        try {
            BigDecimal a = buynum.divide(minplus);

            //System.out.println(a.intValue());

            if (new BigDecimal(a.intValue()).compareTo(a) != 0) {
                return false;
            }

            return true;
        } catch (Exception e) {
            return false;
        }
    }


    /**
     * 从购物车中买家提交订单
     *
     * @param model
     * @param billingRecord
     * @param orders
     * @param ids
     * @return
     */
    @RequestMapping(value = "/submitOrders", method = RequestMethod.POST)
    @ApiOperation(value = "从购物车中买家提交订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "invoiceid", value = "发票id", required = true, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "shipto", value = "收货人", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "phone", value = "收货人电话", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "province", value = "省", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "city", value = "市", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "area", value = "区", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "receivingaddress", value = "详细地址", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "isbilling", value = "是否开票0=不开票1=开票", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "billingtype", value = "开票类型0=纸质1=电子", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "ids", value = "选择的购物车id数组", required = true, paramType = "query", dataType = "array"),
            @ApiImplicitParam(name = "isonline", value = "订单类型0=普通订单1=线下2=限时购", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "deliverybill", value = "是否需要发货单，1-需要，0-不需要", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "mailornotPidArray", value = "需要自提的商品id json数组字符串，如[1,2]", required = true, paramType = "query", dataType = "string"),
    })
    public BasicRet submitOrders(
            Model model, BillingRecord billingRecord,
            Orders orders, String ids, String mailornotPidArray,
            HttpServletRequest request) throws Exception {

        OrderCarRet basicRet = new OrderCarRet();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        List<ShopCar> list = ordersService.loadSelectShopCar(ids);
        if (orders.getIsonline() == null) {
            orders.setIsonline(Quantity.STATE_0);
        }
        //删除购物车的商品
        String[] arrayIds = ids.split(",");
        List<Long> longIds = new ArrayList<Long>();
        for (String id : arrayIds) {
            longIds.add(Long.parseLong(id));
        }

        List<Long> mailornotPids = (List<Long>) JsonUtil.toObject(mailornotPidArray, List.class);

        //定义订单拆分map
        Map<String, List<ShopCar>> opMap = new TreeMap<String, List<ShopCar>>();
        List<Orders> ordersList = new ArrayList<Orders>();


        // final Map<String, List<ShopCar>> opMap1 =


        //---------------创建商品订单记录,按仓库按发货时间按远期付款类型拆分订单-------------------------------
        for (ShopCar shopCar : list) {
            long pdid = shopCar.getPdid();
            String pdno = shopCar.getPdno();
            long storeid = shopCar.getStoreid();
            BigDecimal convertNum = shopCar.getPdnumber();

            orders.setIsonline(shopCar.getIsonline());

            if (orders.getIsonline() == Quantity.STATE_0) {
                //更新库存
                ProductStore productStore = ordersService.getProductStore(pdid, pdno, storeid);
                if (productStore != null) {
                    BigDecimal storeNum = productStore.getPdstorenum();
                    if (convertNum.compareTo(storeNum) == Quantity.STATE_1) {
                        throw new RuntimeException("库存不足");
                    }


                    if (productStore.getMinplus() != null && productStore.getMinplus().compareTo(Quantity.BIG_DECIMAL_0) > 0) {
                        if (!this.checkBuyNum(convertNum, productStore.getMinplus())) {
                            throw new RuntimeException("购买量必须是加购量的倍数");
                        }
                    }


                    //减库存操作
                    productStore.setPdstorenum(productStore.getPdstorenum().subtract(convertNum));
                    ordersService.updateProductStore(productStore);
                    //发货时间
                    String deliveryTime = shopCar.getDelivertime();
                    int protype = shopCar.getProtype();
                    Short producttype = shopCar.getProducttype();
                    //根据仓库id和发货时间判断是否属于同一订单
                    String key = storeid + deliveryTime + protype + producttype + shopCar.getIsonline();
                    if (opMap.containsKey(key)) {
                        List<ShopCar> oplist = opMap.get(key);
                        oplist.add(shopCar);
                    } else {
                        List<ShopCar> optemplist = new ArrayList<ShopCar>();
                        optemplist.add(shopCar);
                        opMap.put(key, optemplist);
                    }
                } else {
//                    basicRet.setResult(BasicRet.ERR);
//                    basicRet.setMessage("没有该商品");
//                    return basicRet;
                    throw new RuntimeException("没有该商品");
                }
            }
            //限时购
            if (orders.getIsonline() == Quantity.STATE_2) {

                LimitTimeProd limitTimeProd = limitTimeProdService.getById(shopCar.getLimitid());

                //判断活动是否开始
                Date startTime = limitTimeProd.getBegintime();
                Date endTime = limitTimeProd.getEndtime();
                Date now = new Date();
                if (limitTimeProd.getState() == Quantity.STATE_4) {
                    if (startTime.compareTo(now) == 1) {
                        throw new Exception("活动还未开始");
                    }
                    if (endTime.compareTo(now) == -1) {
                        throw new Exception("活动已结束");
                    }
                    Long sellerId = limitTimeProd.getMemberid();
                    if (sellerId == member.getId()) {
                        throw new Exception("不能购买自己的商品");
                    }
                } else {
                    throw new Exception("活动未开始");
                }


                LimitTimeStore limitTimeStore = shopCarService.getLimitTimeStore(shopCar.getLimitid(), shopCar.getPdid(), shopCar.getPdno());
                //更新库存
                limitTimeStoreService.updateLimitStoreNum(limitTimeStore.getId(), shopCar.getPdnumber().multiply(new BigDecimal(-1)), shopCar.getPdnumber());
                LimitTimeStore newLimitStore = limitTimeStoreService.selectByPrimaryKey(limitTimeStore.getId());
                if (newLimitStore == null || newLimitStore.getStorenum().compareTo(new BigDecimal(0)) == -1) {
                    throw new Exception("库存不足");
                }
                limitTimeProdService.updateSalestotalnumInDB(shopCar.getLimitid(), shopCar.getPdnumber());


                //发货时间
                String deliveryTime = shopCar.getDelivertime();
                int protype = shopCar.getProtype();
                Short producttype = shopCar.getProducttype();
                //根据仓库id和发货时间判断是否属于同一订单
                String key = storeid + deliveryTime + protype + producttype + shopCar.getIsonline();
                if (opMap.containsKey(key)) {
                    List<ShopCar> oplist = opMap.get(key);
                    oplist.add(shopCar);
                } else {
                    List<ShopCar> optemplist = new ArrayList<ShopCar>();
                    optemplist.add(shopCar);
                    opMap.put(key, optemplist);
                }
            }
        }

        Short isonline = 0;
        //----------------------遍历拆分后的订单，生成不同的订单编号-------------------------------------
        if (opMap.size() > Quantity.INT_0) {
            //生成不同的订单
            for (String key : opMap.keySet()) {
                List<ShopCar> shopCarList = opMap.get(key);
                String orderNo = GenerateNo.getOrderNo();
                //订单总运费
                BigDecimal fight = BigDecimal.valueOf(0);
                //订单总价（包含运费）
                BigDecimal totalPrice = BigDecimal.valueOf(0);
                //订单总定金
                BigDecimal deposit = BigDecimal.valueOf(0);
                //订单总余款
                BigDecimal balance = BigDecimal.valueOf(0);
                //订单总全款
                BigDecimal orderAllpay = BigDecimal.valueOf(0);
                List<OrderProduct> orderProductsList = new ArrayList<OrderProduct>();
                for (ShopCar shop : shopCarList) {

                    isonline = shop.getIsonline();

                    OrderProduct op = new OrderProduct();
                    op.setOrderno(orderNo);
                    op.setPdid(shop.getPdid());
                    op.setPdno(shop.getPdno());
                    op.setAttrjson(shop.getAttrjson());
                    op.setSellerid(shop.getSaleid());
                    op.setDeliverytime(shop.getDelivertime());
                    ProductInfo productInfo = ordersService.getProductInfoByPrimeKey(shop.getPdid());

                    if (productInfo == null) {
                        throw new RuntimeException("商品id为" + shop.getPdid() + "的商品不存在");
                    }

                    if (productInfo.getPdstate() != Quantity.STATE_4) {
                        throw new RuntimeException(productInfo.getProductname() + "的商品现在处于下架状态，不可购买");
                    }

                    Brand brand = shopCarService.getBrandById(productInfo.getBrandid());
                    op.setPdname(productInfo.getProductname());
                    op.setPddesc(productInfo.getPackagetype());
                    if (productInfo.getProducttype().equals("紧固件")) {
                        op.setProducttype(Quantity.STATE_1);
                    } else {
                        op.setProducttype(Quantity.STATE_2);
                    }
                    String[] strpic = (String[]) productInfo.getPdpicture();
                    if (strpic != null) {
                        if (strpic.length > 0) {
                            op.setPdpic(strpic[0]);
                        } else {
                            op.setPdpic("");
                        }
                    } else {
                        op.setPdpic("");
                    }

                    if (brand != null) {
                        if (brand.getPic() != null) {
                            op.setPic(brand.getPic());
                        } else {
                            op.setPic("");
                        }
                    } else {
                        op.setPic("");
                    }
                    op.setPrice(shop.getPrice());
                    op.setUnit(shop.getUnit());
                    op.setNum(shop.getPdnumber());
                    op.setStoreid(shop.getStoreid());
                    op.setStorename(shop.getStorename());
                    //是否包邮
                    if (shop.getFrightmode() == Quantity.STATE_) {
                        op.setMailornot(true);
                    } else {
                        op.setMailornot(false);
                    }
                    if (StringUtils.hasText(productInfo.getStand())) {
                        op.setStandard(productInfo.getStand());
                    }
                    if (StringUtils.hasText(productInfo.getMark())) {
                        op.setMark(productInfo.getMark());
                    }
                    if (StringUtils.hasText(productInfo.getBrand())) {
                        op.setBrand(productInfo.getBrand());
                    }
                    if (StringUtils.hasText(productInfo.getMaterial())) {
                        op.setMaterial(productInfo.getMaterial());
                    }

                    if (StringUtils.hasText(productInfo.getLevel3())) {
                        op.setClassify(productInfo.getLevel3());
                        op.setClassifyid(productInfo.getLevel3id());
                    } else if (StringUtils.hasText(productInfo.getLevel2())) {
                        op.setClassify(productInfo.getLevel2());
                        op.setClassifyid(productInfo.getLevel2id());
                    } else {
                        op.setClassify(productInfo.getLevel1());
                        op.setClassifyid(productInfo.getLevel1id());
                    }


                    if (StringUtils.hasText(productInfo.getCardnum())) {
                        op.setGradeno(productInfo.getCardnum());
                    }
                    if (shop.getLimitid() != null) {
                        op.setLimitid(shop.getLimitid());
                    }
                    op.setIsmailornot((short) 0);
                    // 设置是否自提
                    if (mailornotPids != null && mailornotPids.contains(op.getPdid())) {
                        op.setIsmailornot((short) 1);
                    }
                    //设置远期支付方式
                    op.setProtype(shop.getProtype());
                    op.setAllpay(shop.getAllpay());
                    op.setPartpay(shop.getPartpay());
                    op.setYupay(shop.getYupay());
                    ProductStore productStore1 = ordersService.getProductStore(shop.getPdid(), shop.getPdno(), shop.getStoreid());
                    //计算运费
                    BigDecimal figtht = BigDecimal.valueOf(0);
                    if (orders.getIsonline() == Quantity.STATE_0) {
                        figtht = ordersService.countSinglePdFight(productInfo, productStore1, orders.getProvince(), orders.getCity(), shop.getPdnumber());
                        op.setFreight(figtht);
                    }
                    if (orders.getIsonline() == Quantity.STATE_2) {
                        op.setFreight(BigDecimal.valueOf(0));
                    }

                    if (shop.getProtype() != Quantity.STATE_1) {
                        op.setActualpayment(figtht.add(shop.getPrice().multiply(shop.getPdnumber())));
                    } else {
                        op.setActualpayment(figtht.add(shop.getAllpay()));
                    }
                    fight = fight.add(figtht);
                    orderProductsList.add(op);
                    //计算订单总价
                    BigDecimal tempTotalPrice = BigDecimal.valueOf(0);
                    //不是远期全款
                    if (shop.getProtype() == Quantity.STATE_0 || shop.getProtype() == Quantity.STATE_2) {
                        tempTotalPrice = shop.getPrice().multiply(shop.getPdnumber());
                        deposit = deposit.add(shop.getPartpay());
                        balance = balance.add(shop.getYupay());
                    }
                    //远期全款
                    if (shop.getProtype() == Quantity.STATE_1) {
                        tempTotalPrice = shop.getAllpay();
                        orderAllpay = orderAllpay.add(shop.getAllpay());
                    }
                    tempTotalPrice = tempTotalPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
                    totalPrice = totalPrice.add(tempTotalPrice).add(figtht);

                    //ordersService.insertOrderProduct(op);

                    List<ProductAttr> productAttrs = productAttrService.getListByPidAndPdno(productInfo.getId(), op.getPdno());
                    OrderProductLog orderProductLog = new OrderProductLog();
                    orderProductLog.setProductinfojson(GsonUtils.toJson(productInfo));
                    orderProductLog.setProductstorejson(GsonUtils.toJson(productStore1));
                    orderProductLog.setProductattrjson(GsonUtils.toJson(productAttrs));

                    op.getExtend().put("OrderProductLog", orderProductLog);

                }

                //----------------------------创建订单---------------------------------------
                OrderProduct orderProduct = orderProductsList.get(0);
                Orders order = new Orders();
                order.setIsonline(isonline);
                order.setProvince(orders.getProvince());
                order.setCity(orders.getCity());
                order.setArea(orders.getArea());
                order.setShipto(orders.getShipto());
                order.setPhone(orders.getPhone());
                order.setReceivingaddress(orders.getReceivingaddress());
                order.setIsbilling(orders.getIsbilling());
                order.setBillingtype(orders.getBillingtype());
                order.setDeliverybill(orders.getDeliverybill());
                //查询是否有业务员
                AdminUser AdminUser = adminUserService.getAdminUserByUserid(member.getId());
                if (AdminUser != null) {
                    //添加业务员/业务员联系方式
                    Admin admin = adminService.getById(AdminUser.getAdminid());
                    if(admin !=null){
                        order.setClerkname(admin.getRealname());
                        order.setClerknamephone(admin.getMobile());
                    }
                }

                //订单总运费
                order.setFreight(fight);
                //订单总价
                totalPrice = totalPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
                order.setTotalprice(totalPrice);
                //实付款
                order.setActualpayment(totalPrice);
                order.setDeposit(deposit);
                order.setBalance(balance);
                order.setAllpay(orderAllpay);
                //买家id
                order.setMemberid(member.getId());
                order.setMembername(member.getUsername());
                Member member1 = memberService.getMemberById(orderProduct.getSellerid());
                if (member1 != null) {
                    order.setSellername(member1.getUsername());
                }
                //卖家id
                order.setSaleid(orderProduct.getSellerid());
                // 获取公司名称
                SellerCompanyInfo sellerCompanyInfo = sellerCompanyInfoService.getSellerCompanyByMemberid(orderProduct.getSellerid());
                if (sellerCompanyInfo == null) {
                    throw new RuntimeException("卖家不存在");
                }

                order.setMembercompany(sellerCompanyInfo.getCompanyname());
                order.setShopname(sellerCompanyInfo.getShopname());
                //平台代发模式
                if (sellerCompanyInfo.getDeliverymode() == Quantity.STATE_1) {
                    order.setDeliverytype(Quantity.STATE_1);
                }


                //计算远期发货时间
                Date futuretime = getFutureTime(orderProduct);
                if (futuretime != null) {
                    order.setFuturetime(futuretime);
                }
                //订单编号
                order.setOrderno(orderNo);
                order.setTransactionnumber(GenerateNo.getTransactionNo());
                //合同编号
                order.setCode(GenerateNo.getContractNo());
                //仓库id
                order.setStoreid(orderProduct.getStoreid());
                //仓库名称
                order.setStorename(orderProduct.getStorename());
                if (orderProduct.getProtype() == Quantity.STATE_0) {
                    order.setOrdertype(Quantity.STATE_0);
                }
                if (orderProduct.getProtype() == Quantity.STATE_1) {
                    order.setOrdertype(Quantity.STATE_1);
                }
                if (orderProduct.getProtype() == Quantity.STATE_2) {
                    order.setOrdertype(Quantity.STATE_2);
                }
                order.setCreatetime(new Date());

                //保存订单
                ordersService.insertOrders(order);
                for (OrderProduct orderProduct1 : orderProductsList) {
                    orderProduct1.setOrderid(order.getId());

                    ordersService.insertOrderProduct(orderProduct1);

                    OrderProductLog orderProductLog = (OrderProductLog) orderProduct1.getExtend().get("OrderProductLog");
                    orderProductLog.setOrderproductid(orderProduct1.getId());
                    orderProductLogService.add(orderProductLog);

                }
                //批量保存产品订单表
                //ordersService.insertAllOrderProduct(orderProductsList);


                ordersList.add(order);

                //操作日志
                OperateLog operateLog = new OperateLog();
                operateLog.setContent("提交订单");
                operateLog.setOpid(member.getId());
                operateLog.setOpname(member.getUsername());
                operateLog.setOptime(new Date());
                operateLog.setOptype(Quantity.STATE_0);
                operateLog.setOrderid(order.getId());
                operateLog.setOrderno(order.getOrderno());
                ordersService.saveOperatelog(operateLog);
            }
            //如果选择了开票
            if (orders.getIsbilling() != Quantity.STATE_0) {
                if (billingRecord.getInvoiceid() != null) {
                    InvoiceInfo invoiceInfo = ordersService.getInvoiceInfo(billingRecord.getInvoiceid());
                    if (invoiceInfo != null) {
                        StringBuffer sb = new StringBuffer();
                        BigDecimal billCach = BigDecimal.valueOf(0);
                        List<BillOrder> billOrders = new ArrayList<>();
//                        for (Orders orders1 : ordersList) {
//                            sb.append(orders1.getId()).append(",");
//                            billCach = billCach.add((orders1.getTotalprice().subtract(orders1.getFreight())));
//
//                            BillOrder billOrder = new BillOrder();
//                            billOrder.setOrderid(orders1.getId());
//                            billOrders.add(billOrder);
//                        }
//                        //创建开票记录
//                        billingRecord.setInvoiceheadup(invoiceInfo.getInvoiceheadup());
//                        billingRecord.setTexno(invoiceInfo.getTexno());
//                        billingRecord.setBankofaccounts(invoiceInfo.getBankofaccounts());
//                        billingRecord.setAccount(invoiceInfo.getAccount());
//                        billingRecord.setPhone(invoiceInfo.getPhone());
//                        billingRecord.setAddress(invoiceInfo.getAddress());
//                        billingRecord.setMemberid(member.getId());
//                        billingRecord.setMembername(member.getUsername());
//                        billingRecord.setRemark(invoiceInfo.getAvailable());
//                        billingRecord.setOrderno(sb.toString());
//                        billingRecord.setBillcash(billCach);
//                        billingRecord.setBillingrecordtype(orders.getBillingtype());
//                        ordersService.insertBillingRecord(billingRecord);
//
//                        for (BillOrder billOrder : billOrders) {
//                            billOrder.setBillrecordid(billingRecord.getId());
//                        }
//                        ordersService.insertAll(billOrders);


                        for (Orders orders1 : ordersList) {
                            //创建开票记录
                            billingRecord.setInvoiceheadup(invoiceInfo.getInvoiceheadup());
                            billingRecord.setTexno(invoiceInfo.getTexno());
                            billingRecord.setBankofaccounts(invoiceInfo.getBankofaccounts());
                            billingRecord.setAccount(invoiceInfo.getAccount());
                            billingRecord.setPhone(invoiceInfo.getPhone());
                            billingRecord.setAddress(invoiceInfo.getAddress());
                            billingRecord.setMemberid(member.getId());
                            billingRecord.setMembername(member.getUsername());
                            billingRecord.setRemark(invoiceInfo.getAvailable());
                            billingRecord.setOrderno(orders1.getId().toString());
                            billingRecord.setBillcash(orders1.getTotalprice());
                            billingRecord.setBillingrecordtype(orders.getBillingtype());
                            billingRecord.setReceiveaddress(invoiceInfo.getReceiveaddress());

                            //提前创建出发票信息，等该笔订单交易成功时设置为0
                            billingRecord.setState(Quantity.STATE_);
                            billingRecord.setId(null);
                            ordersService.insertBillingRecord(billingRecord);

                            BillOrder billOrder = new BillOrder();
                            billOrder.setBillrecordid(billingRecord.getId());
                            billOrder.setOrderid(orders1.getId());
                            ordersService.saveBillOrder(billOrder);
                        }
                    }
                }
            }
        }


        //删除购物车
        shopCarService.deleteAll(longIds);

        List<String> ordernoList = new ArrayList<>();
        ordersList.forEach(o -> ordernoList.add(o.getOrderno()));
        ordersService.jisuanOrdersBreakPay(ordernoList);


        //批量保存订单
        //ordersService.insertAllOrders(ordersList);
        memberLogOperator.saveMemberLog(member, null, "提交订单", "/rest/buyer/orders/submitOrders", request, memberOperateLogService);
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("提交成功");
        basicRet.data.ordersList = ordersList;
        return basicRet;
    }

    private class OrderCarRet extends BasicRet {
        private class Data {
            private BuyerCenterModel buyerCenterModel;
            List<TransactionSetting> transactionSettings;
            private SellerBillRecord sellerBillRecord;
            private List<Orders> ordersList;
            private EvaPageModel evaPageModel;
            private OrderProductBack orderProductBack;

            private List<ShippingAddress> shippingAddresses;

            private BigDecimal bigDecimal;

            private Orders orders;

            private List<OrderProduct> orderProducts;

            private OrderProduct orderProduct;

            private OrderProduct orderProduct1;

            private BillingRecord billingRecord;

            private BillRecordComplex billRecordComplex;

            private Map<String, BigDecimal> map;

            private List<OperateLog> operateLogs;

            private BigDecimal integralRecord;

            private boolean selfsupport;

            private String expressurl;

            public String getExpressurl() {
                return expressurl;
            }

            public void setExpressurl(String expressurl) {
                this.expressurl = expressurl;
            }

            public boolean isSelfsupport() {
                return selfsupport;
            }

            public void setSelfsupport(boolean selfsupport) {
                this.selfsupport = selfsupport;
            }

            public OrderProduct getOrderProduct1() {
                return orderProduct1;
            }

            public void setOrderProduct1(OrderProduct orderProduct1) {
                this.orderProduct1 = orderProduct1;
            }

            public BigDecimal getIntegralRecord() {
                return integralRecord;
            }

            public void setIntegralRecord(BigDecimal integralRecord) {
                this.integralRecord = integralRecord;
            }

            public BuyerCenterModel getBuyerCenterModel() {
                return buyerCenterModel;
            }

            public void setBuyerCenterModel(BuyerCenterModel buyerCenterModel) {
                this.buyerCenterModel = buyerCenterModel;
            }

            public EvaPageModel getEvaPageModel() {
                return evaPageModel;
            }

            public void setEvaPageModel(EvaPageModel evaPageModel) {
                this.evaPageModel = evaPageModel;
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

    /**
     * 买家选择支付方式时获取用户余额和可用授信额度
     *
     * @param model
     * @param type
     * @return
     */
    @RequestMapping(value = "/getUserBanlance", method = RequestMethod.POST)
    @ApiOperation(value = "买家选择支付方式时获取用户余额和可用授信额度")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "type传0是获取用户余额，传1获取用户授信可用额度", required = true, paramType = "query", dataType = "int"),
    })
    public OrderCarRet getUserBanlance(Model model, short type) {

        OrderCarRet orderCarRet = new OrderCarRet();
        orderCarRet.setMessage("返回成功");
        orderCarRet.setResult(BasicRet.SUCCESS);
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        member = memberService.getMemberById(member.getId());
        //用户余额
        if (type == Quantity.STATE_0) {
            orderCarRet.data.bigDecimal = member.getBalance();
            return orderCarRet;
        } else {
            orderCarRet.data.bigDecimal = member.getAvailablelimit();
            return orderCarRet;
        }
    }

    /**
     * 买家支付接口（限余额和授信）
     *
     * @param model
     * @param type
     * @param payMoney
     * @param paySecret
     * @param orders
     * @return
     */
    @RequestMapping(value = "/payByBanlance", method = RequestMethod.POST)
    @ApiOperation(value = "买家支付接口（限余额和授信）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "type传0用余额支付，传1用授信支付", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "payMoney", value = "支付总金额", required = true, paramType = "query", dataType = "double"),
            @ApiImplicitParam(name = "paySecret", value = "支付密码", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "orders", value = "订单id：{1,2}", required = true, paramType = "query", dataType = "string"),
    })
    public BasicRet payByBanlance(Model model, short type, BigDecimal payMoney, String paySecret, String orders, HttpServletRequest request) throws CashException {

        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        member = memberService.getMemberById(member.getId());
        BasicRet basicRet = new BasicRet();
        paySecret = Base64Utils.decode(paySecret);
        String ps = CommonUtils.genMd5Password(paySecret, member.getPaypasswordsalt());

        List<OrderProduct> orderProducts = ordersService.getOrderProductByInOrderids(orders);
        BigDecimal sumpay = BigDecimal.valueOf(0);
        boolean flag = false;
        //计算总金额
        for (OrderProduct orderProduct : orderProducts) {

            //不是远期
            if (orderProduct.getProtype() == Quantity.STATE_0) {
                //没有支付
                if (orderProduct.getPaystate() == Quantity.STATE_0) {
                    orderProduct.setPaystate(Quantity.STATE_1);
                    sumpay = sumpay.add(orderProduct.getActualpayment());
                } else {
                    flag = true;
                }
            }
            //全款
            if (orderProduct.getProtype() == Quantity.STATE_1) {
                if (orderProduct.getPaystate() == Quantity.STATE_0) {
                    orderProduct.setPaystate(Quantity.STATE_1);
                    sumpay = sumpay.add(orderProduct.getActualpayment());
                } else {
                    flag = true;
                }
            }
            //定金
            if (orderProduct.getProtype() == Quantity.STATE_2) {
                //定金付
                if (orderProduct.getPaystate() == Quantity.STATE_0) {
                    orderProduct.setPaystate(Quantity.STATE_2);
                    // sumpay = sumpay.add(orderProduct.getPartpay()).add(orderProduct.getFreight());

                    sumpay = sumpay.add(orderProduct.getPartpay()); //订金不加运费


                    //余款支付
                } else if (orderProduct.getPaystate() == Quantity.STATE_2) {
                    orderProduct.setPaystate(Quantity.STATE_3);
                    //sumpay = sumpay.add(orderProduct.getYupay());
                    sumpay = sumpay.add(orderProduct.getYupay()).add(orderProduct.getFreight());
                } else {
                    flag = true;
                }
            }
        }
        if (flag) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("订单已支付");
            return basicRet;
        }

        //判断总金额是否正确
        if (sumpay.compareTo(payMoney) != Quantity.STATE_0) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("支付金额不正确");
            return basicRet;
        }
        //支付密码判断
        if (!ps.equals(member.getPaypassword())) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("支付密码不正确");
            return basicRet;
        } else {
            List<Orders> ordersList = ordersService.getOrdersByInIds(orders);
            //判断定金和余款是否是同一支付方式
            for (Orders order : ordersList) {

                if (!member.getId().equals(order.getMemberid())) {
                    basicRet.setResult(BasicRet.ERR);
                    basicRet.setMessage(order.getOrderno() + "的订单不属于你");
                    return basicRet;
                }

                //付余款时
                if (order.getOrdertype() == Quantity.STATE_3) {
                    if (order.getPaytype() == Quantity.STATE_3 && type == Quantity.STATE_1) {
                        basicRet.setResult(BasicRet.ERR);
                        basicRet.setMessage("定金为余额支付，余款也应余额支付");
                        return basicRet;
                    }
                    if (order.getPaytype() == Quantity.STATE_4 && type == Quantity.STATE_0) {
                        basicRet.setResult(BasicRet.ERR);
                        basicRet.setMessage("定金为授信支付，余款也应授信支付");
                        return basicRet;
                    }
                }
            }

            //余额支付
            if (type == Quantity.STATE_0) {
                BigDecimal banlance = member.getBalance();
                //余额小于支付的钱
                if (banlance.compareTo(payMoney) == Quantity.INT_) {
                    basicRet.setResult(BasicRet.ERR);
                    basicRet.setMessage("余额不足，请充值或更换支付方式");
                    return basicRet;
                } else {

//                    banlance = banlance.subtract(payMoney);
//                    member.setBalance(banlance);
//                    member.setIsbuy(Quantity.STATE_2);

                    //ordersService.saveMember(member);
                    memberService.updateBuyerMemberBalanceInDb(member.getId(), payMoney.multiply(Quantity.BIG_DECIMAL_MINUS_1));
                    Member newMember = memberService.getMemberById(member.getId());
                    if (newMember.getBalance().compareTo(Quantity.BIG_DECIMAL_0) < 0) {
                        throw new CashException("账户余额不足");
                    }

                    Member upateMember = new Member();
                    upateMember.setId(member.getId());
                    upateMember.setIsbuy(Quantity.STATE_2);
                    memberService.updateMember(upateMember);


                    payMethod(ordersList, member, type);
                    for (OrderProduct orderProduct : orderProducts) {
                        ordersService.updateOrderProduct(orderProduct);
                    }
                    List<Orders> ordersListWMS = ordersService.getWMSOrdersByInIds(orders);


                    ordersService.smsNotifySellerToOrders(ordersList);
                    logger.info("余额支付：" + JSONArray.fromObject(ordersListWMS).toString());
                    logger.error("余额支付：" + JSONArray.fromObject(ordersListWMS).toString());
                    wmsService.synOrders(ordersListWMS);
                    basicRet.setResult(BasicRet.SUCCESS);
                    basicRet.setMessage("支付成功");
                }
            } else if (type == Quantity.STATE_1) { //授信支付
                //判断可用授信金额是否足够
                BigDecimal availablelimit = member.getAvailablelimit();
                BigDecimal usedLimit = member.getUsedlimit();
                if (availablelimit.compareTo(payMoney) == Quantity.INT_) {
                    basicRet.setResult(BasicRet.ERR);
                    basicRet.setMessage("余额不足，请更换支付方式");
                    return basicRet;
                } else {
                    //availablelimit = availablelimit.subtract(payMoney);
                    //usedLimit = usedLimit.add(payMoney);
                    //member.setAvailablelimit(availablelimit);
                    //member.setUsedlimit(usedLimit);
                    //member.setIsbuy(Quantity.STATE_2);
                    //ordersService.saveMember(member);

                    memberService.updateBuyerMemberCreditBalanceInDb(member.getId(), payMoney, payMoney.multiply(Quantity.BIG_DECIMAL_MINUS_1));
                    Member newMember = memberService.getMemberById(member.getId());
                    if (member.getAvailablelimit().compareTo(Quantity.BIG_DECIMAL_0) < 0) {
                        throw new CashException("授信可用金额不足");
                    }


                    payMethod(ordersList, member, type);
                    for (OrderProduct orderProduct : orderProducts) {
                        ordersService.updateOrderProduct(orderProduct);
                    }

                    ordersService.smsNotifySellerToOrders(ordersList);

                    List<Orders> ordersListWMS = ordersService.getWMSOrdersByInIds(orders);
                    logger.info("授信支付：" + JSONArray.fromObject(ordersListWMS).toString());
                    logger.error("授信支付：" + JSONArray.fromObject(ordersListWMS).toString());
                    wmsService.synOrders(ordersListWMS);
                    basicRet.setResult(BasicRet.SUCCESS);
                    basicRet.setMessage("支付成功");
                }
            }
        }

        String[] orderids = orders.split(",");
        for (String id : orderids) {
            //操作日志
            OperateLog operateLog = new OperateLog();
            operateLog.setContent("订单已支付");
            operateLog.setOpid(member.getId());
            operateLog.setOpname(member.getUsername());
            operateLog.setOptime(new Date());
            operateLog.setOptype(Quantity.STATE_0);
            operateLog.setOrderid(Long.parseLong(id));
            //operateLog.setOrderno(orders.getOrderno());
            ordersService.saveOperatelog(operateLog);
        }
        memberLogOperator.saveMemberLog(member, null, "订单支付完成", "/rest/buyer/orders/payByBanlance", request, memberOperateLogService);


        return basicRet;
    }

    /**
     * 保存资金明细和修改订单状态
     *
     * @param ordersList 订单数组（orderno:price）
     * @param member     用户
     */
    private void payMethod(List<Orders> ordersList, Member member, short type) {
        //一个订单一条资金记录
        List<BuyerCapital> buyerCapitals = new ArrayList<BuyerCapital>();
        List<SalerCapital> salerCapitals = new ArrayList<SalerCapital>();
        for (Orders order : ordersList) {
            //余额支付
            if (type == Quantity.STATE_0) {
                order.setPaytype(Quantity.STATE_3);
            }
            //授信支付
            if (type == Quantity.STATE_1) {
                order.setPaytype(Quantity.STATE_4);
            }
            String transactionNo = GenerateNo.getTransactionNo();
            Date tranTime = new Date();
            //创建订单资金明细
            BuyerCapital buyerOrderCapital = createBuyerOrderCapital(order, type, member, tranTime, transactionNo);


            SalerCapital salerCapital = createSalerOrderCapital(order, tranTime, transactionNo);
            buyerCapitals.add(buyerOrderCapital);


            if (salerCapital.getRefundamount() == null) {
                salerCapital.setRefundamount(BigDecimal.valueOf(0));
            }

            salerCapitals.add(salerCapital);
            //立即发货
            if (order.getOrdertype() == Quantity.STATE_0) {
                if (order.getOrderstatus() == Quantity.STATE_0) {
                    order.setOrderstatus(Quantity.STATE_1);
                }
            }
            //远期全款
            if (order.getOrdertype() == Quantity.STATE_1) {
                if (order.getOrderstatus() == Quantity.STATE_0) {
                    order.setOrderstatus(Quantity.STATE_8);
                }
            }
            //定金
            if (order.getOrdertype() == Quantity.STATE_2) {
                if (order.getOrderstatus() == Quantity.STATE_0) {
                    order.setOrdertype(Quantity.STATE_3);
                    order.setOrderstatus(Quantity.STATE_8);
                }
            }
            //余款
            if (order.getOrdertype() == Quantity.STATE_3) {
                if (order.getOrderstatus() == Quantity.STATE_9) {
                    order.setOrderstatus(Quantity.STATE_1);
                }
            }
            order.setPaymenttime(new Date());
            ordersService.updateSingleOrder(order);
        }
        //批量保存买家卖家资金明细
        if (buyerCapitals.size() > Quantity.STATE_0) {
            ordersService.insertBuyerCapital(buyerCapitals);
        }
        if (salerCapitals.size() > Quantity.STATE_0) {
            ordersService.insertSallerCapital(salerCapitals);
        }
    }

    /**
     * 创建买家订单资金明细
     *
     * @param order
     * @param type
     * @param member
     * @param tranTime
     * @param tradeNo
     * @return
     */
    private BuyerCapital createBuyerOrderCapital(Orders order, Short type, Member member, Date tranTime, String tradeNo) {

        BuyerCapital buyerCapital = new BuyerCapital();
        buyerCapital.setTradetime(tranTime);
        buyerCapital.setOrderno(order.getOrderno());
        //余额支付
        if (type == Quantity.STATE_0) {
            buyerCapital.setPaytype(Quantity.STATE_3);
        }
        //授信支付
        if (type == Quantity.STATE_1) {
            buyerCapital.setPaytype(Quantity.STATE_4);
        }
        buyerCapital.setMemberid(member.getId());
        buyerCapital.setTradeno(tradeNo);
        buyerCapital.setRechargestate(Quantity.STATE_1);
        List<OrderProduct> orderProducts = ordersService.getOrderProductByOrderNo(order.getOrderno());
        BigDecimal capital = BigDecimal.valueOf(0);
        //立即发货
        if (order.getOrdertype() == Quantity.STATE_0) {
            if (order.getOrderstatus() == Quantity.STATE_0) {
                buyerCapital.setCapitaltype(Quantity.STATE_0);
                for (OrderProduct orderProduct : orderProducts) {
                    capital = capital.add(orderProduct.getActualpayment());
                }
                //买家订单资金明细
                buyerCapital.setCapital(capital);
                buyerCapital.setRemark("订单金额");
            }
        }
        //远期全款
        if (order.getOrdertype() == Quantity.STATE_1) {
            if (order.getOrderstatus() == Quantity.STATE_0) {
                buyerCapital.setCapitaltype(Quantity.STATE_9);
                for (OrderProduct orderProduct : orderProducts) {
                    capital = capital.add(orderProduct.getActualpayment());
                }
                buyerCapital.setCapital(capital);
                buyerCapital.setRemark("订单金额-远期全款");
            }
        }
        //定金
        if (order.getOrdertype() == Quantity.STATE_2) {
            if (order.getOrderstatus() == Quantity.STATE_0) {
                buyerCapital.setCapitaltype(Quantity.STATE_7);
                for (OrderProduct orderProduct : orderProducts) {
                    capital = capital.add(orderProduct.getPartpay());
                }
                buyerCapital.setCapital(capital);
                buyerCapital.setRemark("订单金额-远期订金");
            }
        }
        //余款
        if (order.getOrdertype() == Quantity.STATE_3) {
            if (order.getOrderstatus() == Quantity.STATE_9) {
                buyerCapital.setCapitaltype(Quantity.STATE_8);
                for (OrderProduct orderProduct : orderProducts) {
                    capital = capital.add(orderProduct.getYupay()).add(orderProduct.getFreight());
                }
                buyerCapital.setCapital(capital);
                buyerCapital.setRemark("订单金额-远期余款");
            }
        }
        return buyerCapital;
    }


    /**
     * 创建卖家订单资金
     *
     * @param order
     * @param TranNo
     * @param tranTime
     * @return
     */
    public SalerCapital createSalerOrderCapital(Orders order, Date tranTime, String TranNo) {
        SalerCapital salerCapital = new SalerCapital();
        salerCapital.setTradetime(tranTime);
        salerCapital.setOrderno(order.getOrderno());
        salerCapital.setTradeno(TranNo);
        salerCapital.setRechargestate(Quantity.STATE_1);
        salerCapital.setMemberid(order.getSaleid());
        Member seller = memberService.getMemberById(order.getSaleid());
        List<OrderProduct> orderProducts = ordersService.getOrderProductByOrderNo(order.getOrderno());
        BigDecimal capital = BigDecimal.valueOf(0);
        //立即发货
        if (order.getOrdertype() == Quantity.STATE_0) {
            if (order.getOrderstatus() == Quantity.STATE_0) {
                salerCapital.setCapitaltype(Quantity.STATE_0);
                for (OrderProduct orderProduct : orderProducts) {
                    capital = capital.add(orderProduct.getActualpayment());
                }
                //买家订单资金明细
                salerCapital.setOrdercapital(capital);
                salerCapital.setRemark("订单金额");
            }
        }
        //远期全款
        if (order.getOrdertype() == Quantity.STATE_1) {
            if (order.getOrderstatus() == Quantity.STATE_0) {
                salerCapital.setCapitaltype(Quantity.STATE_9);
                for (OrderProduct orderProduct : orderProducts) {
                    capital = capital.add(orderProduct.getActualpayment());
                }
                salerCapital.setOrdercapital(capital);
                salerCapital.setRemark("订单金额-远期全款");
            }
        }
        //定金
        if (order.getOrdertype() == Quantity.STATE_2) {
            if (order.getOrderstatus() == Quantity.STATE_0) {
                salerCapital.setCapitaltype(Quantity.STATE_10);
                for (OrderProduct orderProduct : orderProducts) {
                    capital = capital.add(orderProduct.getPartpay());
                }
                salerCapital.setOrdercapital(capital);
                salerCapital.setRemark("订单金额-远期定金");

            }
        }
        //余款
        if (order.getOrdertype() == Quantity.STATE_3) {
            if (order.getOrderstatus() == Quantity.STATE_9) {
                salerCapital.setCapitaltype(Quantity.STATE_8);
                for (OrderProduct orderProduct : orderProducts) {
                    capital = capital.add(orderProduct.getYupay().add(orderProduct.getFreight()));
                }
                salerCapital.setOrdercapital(capital);
                salerCapital.setRemark("订单金额-远期余款");
            }
        }

        return salerCapital;
    }


    /**
     * 买家获取订单列表
     *
     * @param model
     * @param param
     * @return
     */
    @RequestMapping(value = "/getMemberOrderList", method = RequestMethod.POST)
    @ApiOperation(value = "买家获取订单列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "memberName", value = "买家名称", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "sellerName", value = "卖家名称", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "pdName", value = "商品名称", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "orderNo", value = "订单号", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "code", value = "合同号", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "tranNo", value = "交易号", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "startTime", value = "开始时间", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "orderState", value = "订单状态0=待付款1=待发货3=待收货4=待验货5=已完成7=已关闭8=备货中9=备货完成", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "evaState", value = "评价状态0=未评价1=已评价", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "backstate", value = "退货状态0=正常1=退货中2=退货验收3=退货完成4=异议中", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "brand", value = "品牌", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "stand", value = "规格", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "mark", value = "印记", required = false, paramType = "query", dataType = "string"),
    })
    public PageRet getMemberOrderList(Model model, OrderQueryParam param) {
        PageRet pageRet = new PageRet();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        param.setMemberid(member.getId());
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
            memberOrders.setDelaydays(orders.getDelaydays());
            memberOrders.setIfdelay(orders.getIfdelay());
            memberOrders.setDelaypenalty(orders.getDelaypenalty());
            memberOrders.setTransactionnumber(orders.getTransactionnumber());
            memberOrders.setActualpayment(orders.getTotalprice());
            memberOrders.setReceiver(orders.getShipto());
            memberOrders.setReceiverPhone(orders.getPhone());
            //orders.totalprice,orders.freight,orders.deposit,orders.balance,orders.futuretime,orders.allpay,orders.ordertype
            memberOrders.setFreight(orders.getFreight());
            memberOrders.setDeposit(orders.getDeposit());
            memberOrders.setBalance(orders.getBalance());
            memberOrders.setCouriernumber(orders.getCouriernumber());
            memberOrders.setLogisticscompany(orders.getLogisticscompany());
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


    /**
     * 根据订单编号获取订单
     *
     * @param orderno
     * @return
     */
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

        IntegralRecord integralRecord = integralService.getIntegralRecordByOrderId(orders.getId());
        if (integralRecord != null) {
            orderCarRet.data.integralRecord = integralRecord.getScope();
        }
        String url = "";
        if (StringUtils.hasText(orders.getLogisticscompany()) && StringUtils.hasText(orders.getCouriernumber())) {
            List<String> list = commonDataValueService.getcommonDataValue("物流公司");
            for (String vl : list) {
                String[] vlStr = vl.split("-");
                if (orders.getLogisticscompany().equals(vlStr[0])) {
                    //物流查询
                    url = ExpressUtils.searchkuaiDiInfo(vlStr[1], orders.getCouriernumber());
                    break;
                }
            }
        }
        orderCarRet.data.expressurl = url;
        orderCarRet.data.orders = orders;
        return orderCarRet;
    }

    /**
     * 根据订单编号获取商品信息
     *
     * @param orderno
     * @return
     */
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

    /**
     * 根据商品订章id获取商品信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/getOrderProductById", method = RequestMethod.POST)
    @ApiOperation(value = "根据商品订章id获取商品信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "订单商品id", required = true, paramType = "query", dataType = "long"),
    })
    public OrderCarRet getOrderProductById(long id) {
        OrderCarRet orderCarRet = new OrderCarRet();
        orderCarRet.setMessage("返回成功");
        orderCarRet.setResult(BasicRet.SUCCESS);

        OrderProduct orderProduct = ordersService.getOrderProductById(id);

        ProductInfo info = ordersService.getProductInfoByPrimeKey(orderProduct.getPdid());

        orderCarRet.data.orderProduct = orderProduct;

        OrderProduct orderProduct1 = new OrderProduct();
        List<OrderProduct> list = ordersService.getOrderProductByOrderId(orderProduct.getOrderid(), orderProduct.getPdid(), orderProduct.getPdno());
        BigDecimal totalnum = BigDecimal.valueOf(0);
        BigDecimal totalPay = BigDecimal.valueOf(0);
        if (list.size() > 0) {
            BeanUtils.copyProperties(list.get(0), orderProduct1);
        }
        for (OrderProduct op : list) {
            totalnum = totalnum.add(op.getNum());
            totalPay = totalPay.add(op.getActualpayment());
        }
        orderProduct1.setNum(totalnum);
        orderProduct1.setActualpayment(totalPay);

        orderCarRet.data.orderProduct1 = orderProduct1;

        orderCarRet.data.selfsupport = info.getSelfsupport();

        return orderCarRet;
    }

    /**
     * 根据订单编号获取开票信息
     *
     * @param orderid
     * @return
     */
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


    /**
     * 买家卖家订单改变状态接口
     *
     * @param model
     * @param orderno
     * @param state
     * @return
     */
    @RequestMapping(value = "/updateOrderState", method = RequestMethod.POST)
    @ApiOperation(value = "买家卖家订单改变状态接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderno", value = "订单编号", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "state", value = "订单状态0=待付款1=待发货3=待收货4=待验货5=已完成7=已关闭8=备货中9=备货完成", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "paySecret", value = "支付密码", required = false, paramType = "query", dataType = "string"),
    })
    public BasicRet updateOrderState(Model model, String orderno, Short state, String paySecret, HttpServletRequest request) throws Exception {

        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        member = memberService.getMemberById(member.getId());

        Member oldMember = new Member();
        BeanUtils.copyProperties(member, oldMember);

        BasicRet basicRet = new BasicRet();
        Orders orders = ordersService.getOrdersByOrderNo(orderno);

        if (!member.getId().equals(orders.getMemberid())) {
            return new BasicRet(BasicRet.ERR, "该订单不属于你，不可操作");
        }

//        member = ordersService.getById(member.getId());

        if (orders != null) {

            if (state == Quantity.STATE_4) {
                if (state == orders.getOrderstatus()) {
                    basicRet.setResult(BasicRet.ERR);
                    basicRet.setMessage("订单已收货");
                    return basicRet;
                }
                orders.setBuyerdeliverytime(new Date());
                orders.setOrderstatus(state);
                ordersService.updateSingleOrder(orders);

            } else if (state == Quantity.STATE_5) {

                if (orders.getOrderstatus() != Quantity.STATE_4) {
                    basicRet.setResult(BasicRet.ERR);
                    basicRet.setMessage("订单状态不合法，不可确认验货");
                    return basicRet;
                }


                if (!StringUtils.hasText(paySecret)) {
                    basicRet.setResult(BasicRet.ERR);
                    basicRet.setMessage("支付密码不能为空");
                    return basicRet;
                }

                paySecret = Base64Utils.decode(paySecret);
                String ps = CommonUtils.genMd5Password(paySecret, member.getPaypasswordsalt());
                if (!ps.equals(member.getPaypassword())) {
                    basicRet.setResult(BasicRet.ERR);
                    basicRet.setMessage("支付密码不正确");
                    return basicRet;
                }

                orders.setBuyerinspectiontime(new Date());
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
                        basicRet.setResult(BasicRet.ERR);
                        basicRet.setMessage("有商品还在退货中，不能结束订单");
                        return basicRet;
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
                            BigDecimal brolerRate = rate.multiply(BigDecimal.valueOf(0.01));
                            if (brolerRate.compareTo(BigDecimal.valueOf(0)) < 0) {
                                brolerRate = categoriesService.getBrokerRate(classifyid).multiply(BigDecimal.valueOf(0.01));
                            }

                            //服务费比率 用的是category中设置的
                            BigDecimal serverRate = categoriesService.getServerRate(classifyid).multiply(BigDecimal.valueOf(0.01));


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

                if (scope.compareTo(Quantity.BIG_DECIMAL_0) > 0) {
                    integralValue = allPdPay.divideToIntegralValue(scope);
                }

                if (integralValue.compareTo(Quantity.BIG_DECIMAL_0) > 0) {
                    IntegralRecord integralRecord = new IntegralRecord();
                    integralRecord.setMemberid(member.getId());
                    integralRecord.setMembername(member.getUsername());
                    integralRecord.setOrderid(orders.getId());
                    integralRecord.setScope(integralValue);
                    integralRecord.setType(Quantity.STATE_0);
                    integralRecord.setCreatetime(new Date());
                    integralRecord.setRemark("订单积分");
                    integralService.saveIntegralRecord(integralRecord);
                }

                member.setIntegrals(member.getIntegrals().add(integralValue));
                member.setAvailableintegral(member.getAvailableintegral().add(integralValue));
                ordersService.saveMember(member, oldMember);


                if (orders.getIsbilling() == Quantity.STATE_1) {  //提交订单时选择开票  将billingrecord表中的state 字段由-1更改为0（待开票状态）
                    //并且要查询该订单中是否有退款的，如果有开票金额减去退款金额
                    //查询是否有退货或退款的，如果有退货开票金额要减去退货的钱
                    List<OrderProductBack> orderProductBackList = orderProductBackService.getByOrderNo(orderno);
                    BigDecimal subApply = new BigDecimal(0);
                    for (OrderProductBack opb : orderProductBackList) {
                        if (opb.getState() == 10) {
                            subApply = subApply.add(opb.getBackmoney());
                        }
                    }

                    billingRecordService.updateForwordBillingState(orders.getId().toString(), orders.getMemberid(), subApply.multiply(new BigDecimal(-1)));
                }


                /*
                if (member1 != null) {
                    member1.setSellerbanlance(member1.getSellerbanlance().add(sellerpay));
                    member1.setBillmoney(member1.getBillmoney().add(sellerpay));
                    ordersService.saveMember(member1);

                } else {
                    basicRet.setResult(BasicRet.ERR);
                    basicRet.setMessage("卖家不存在");
                    return basicRet;
                }*/

                Member seller = memberService.getMemberById(orders.getSaleid());
                //BigDecimal frozepay = sellerpay.subtract(totalBroke).setScale(2,BigDecimal.ROUND_HALF_UP);
                BigDecimal frozepay = sellerpay.setScale(2, BigDecimal.ROUND_HALF_UP);
                if (seller != null) {
                    //增加卖家冻结金额
                    memberService.updateSellerMemberBalanceInDb(seller.getId(), Quantity.BIG_DECIMAL_0, frozepay);
                }
                orders.setBrokepay(totalBroke);
                orders.setFrozepay(frozepay);
                orders.setServerpay(totalServerPay);

                //订单验货完成需要更新的数据
                int count = ordersService.updateOrdersConfirmgoods(orders);
                if (count != 1) {
                    throw new CashException("买家订单自动确认验货出现错误，可能出现并发更新问题，所有操作已回撤，订单id:" + orders.getId());
                }
            }

        } else {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("订单不存在");
            return basicRet;
        }


        //操作日志
        OperateLog operateLog = new OperateLog();
        if (state == Quantity.STATE_4) {
            operateLog.setContent("订单已收货");
        }
        if (state == Quantity.STATE_5) {
            operateLog.setContent("订单已验货");
        }
        //保存操作日志
        operateLog.setOpid(member.getId());
        operateLog.setOpname(member.getUsername());
        operateLog.setOptime(new Date());
        operateLog.setOptype(Quantity.STATE_0);
        operateLog.setOrderid(orders.getId());
        operateLog.setOrderno(orders.getOrderno());
        ordersService.saveOperatelog(operateLog);
        //保存用户日志
        memberLogOperator.saveMemberLog(member, null, "订单编号：" + orderno + "状态改变为：" + JinshangUtils.orderState(state), "/rest/buyer/orders/updateOrderState", request, memberOperateLogService);


        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("修改成功");
        return basicRet;
    }

//    private BigDecimal fetchServerOrBrokerRate(Categories categories, String type){
//        BigDecimal ret = BigDecimal.valueOf(0);
//        if("broker".equals(type)){
//            ret = categories.getBrokeragerate();
//        }else if("server".equals(type)){
//            ret = categories.getServicesrate();
//        }
//
//        //费率为-1,沿用上级分类
//        if(ret.compareTo(Quantity.BIG_DECIMAL_MINUS_1) ==0  && categories.getParentid()>0){
//            Categories categories1 = ordersService.getCategories(categories.getParentid());
//            if(categories1!=null){
//               return ret = fetchServerOrBrokerRate(categories1, type);
//            }
//
//        }
//        return ret;
//    }


    /*
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
    */


//    private  BigDecimal getBrokerRate(Long cateid){
//        Categories categories = categoriesService.getById(cateid);
//
//        if (categories == null){
//            return  Quantity.BIG_DECIMAL_0;
//        }
//
//        if(categories.getBrokeragerate() != null && categories.getBrokeragerate().compareTo(Quantity.BIG_DECIMAL_0) >=0){
//            return  categories.getServicesrate();
//        }else{
//            return  getBrokerRate(categories.getParentid());
//        }
//
//    }


    /**
     * 评价接口
     */
    @RequestMapping(value = "/evaProduct", method = RequestMethod.POST)
    @ApiOperation(value = "评价接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "订单商品表id", required = true, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "eva1", value = "宝贝与描述相符打分", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "eva2", value = "卖家服务", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "eva3", value = "物流服务", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "eva", value = "买家心得", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "isanonymous", value = "是否匿名1=不匿名2=匿名", required = true, paramType = "query", dataType = "int"),
    })
    public BasicRet evaProduct(Model model, long id, short eva1, short eva2, short eva3, String eva, Short isanonymous, HttpServletRequest request) {
        BasicRet basicRet = new BasicRet();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        OrderProduct orderProduct = ordersService.getOrderProductById(id);
        orderProduct.setEva1(eva1);
        orderProduct.setEva2(eva2);
        orderProduct.setEva3(eva3);
        orderProduct.setIsanonymous(isanonymous);
        orderProduct.setEvaluatestate(Quantity.STATE_1);
        orderProduct.setBuyersexperience(eva);
        orderProduct.setEvatime(new Date());
        orderProduct.setMembername(member.getUsername());
        orderProduct.setMemberid(member.getId());
        Member member1 = memberService.getMemberById(orderProduct.getSellerid());
        if (member1 != null) {
            orderProduct.setSellername(member1.getUsername());
            //计算评分保存到卖家
            ordersService.calculateSellerEvaScore(member1, eva1, eva2, eva3);
        }
        ordersService.updateOrderProduct(orderProduct);
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("评价成功");


        //保存操作日志
        OperateLog operateLog = new OperateLog();
        operateLog.setContent(orderProduct.getPdname() + "商品已评价");
        operateLog.setOpid(member.getId());
        operateLog.setOpname(member.getUsername());
        operateLog.setOptime(new Date());
        operateLog.setOptype(Quantity.STATE_0);
        operateLog.setOrderid(orderProduct.getOrderid());
        operateLog.setOrderno(orderProduct.getOrderno());
        operateLog.setOrderpdid(orderProduct.getId());
        ordersService.saveOperatelog(operateLog);

        //保存用户日志
        memberLogOperator.saveMemberLog(member, null, "提交评价", "/rest/buyer/orders/evaProduct", request, memberOperateLogService);
        return basicRet;
    }


    /**
     * 保存退货申请
     *
     * @param model
     * @param orderProductBack
     * @param pdbackNum
     * @return
     */
    @RequestMapping(value = "/saveOrderProductBack", method = RequestMethod.POST)
    @ApiOperation(value = "保存退货申请")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderpdid", value = "订单商品id", required = true, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "backtype", value = "退货类型0=仅退款1=退货退款2=部分退货", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "isreceivegoods", value = "是否收到货0=收到1=未收到", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "returnbackreason", value = "退货原因", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "pic", value = "买家上传的图片", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "backexplain", value = "退货说明", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "pdbackNum", value = "部分退货数量", required = false, paramType = "query", dataType = "double"),
    })
    public BasicRet saveOrderProductBack(Model model, OrderProductBack orderProductBack, BigDecimal pdbackNum, HttpServletRequest request) {
        OrderCarRet basicRet = new OrderCarRet();

        /*
        if(orderProductBack.getBacktype() == 2) {
            basicRet.setMessage("部分退货功能维护中，给您造成不便，敬请谅解，退货事宜请联系客服处理，如有问题您可致电4001002897");
            basicRet.setResult(BasicRet.ERR);
            return basicRet;
        }*/


        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

        OrderProduct orderProduct = ordersService.getOrderProductById(orderProductBack.getOrderpdid());
        if (orderProduct == null) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("没有找到此商品");
            return basicRet;
        }


        Member member1 = memberService.getMemberById(orderProduct.getSellerid());

        Orders orders = ordersService.getSingleOrder(orderProduct.getOrderid());
        if (orders == null) {
            return new BasicRet(BasicRet.ERR, "订单不存在");
        }

        if (orderProductBack.getBacktype() != 1 && orderProductBack.getBacktype() != 2) {
            return new BasicRet(BasicRet.ERR, "请选择退货类型");
        }

        if (orderProductBack.getBacktype() == 1 && orderProduct.getBackstate() != 0) {
            return new BasicRet(BasicRet.ERR, "退货状态不正常");
        }


        orderProductBack.setOrderid(orderProduct.getOrderid());
        orderProductBack.setOrderno(orderProduct.getOrderno());
        orderProductBack.setBackno(GenerateNo.getReturnNo());
        orderProductBack.setState(Quantity.STATE_0);
        orderProductBack.setProducttype(orderProduct.getProducttype());
        if (orderProduct.getLimitid() != null) {
            orderProductBack.setLimitid(orderProduct.getLimitid());
        }
        BigDecimal backMoney = BigDecimal.valueOf(0);
        //不是远期
        if (orderProduct.getProtype() == Quantity.STATE_0) {
            //是否支付
            if (orderProduct.getPaystate() == Quantity.STATE_1) {
                //部分退货
                if (orderProductBack.getBacktype() == Quantity.STATE_2) {
                    //如果部分退货数量大于货物总数量
                    if (pdbackNum.compareTo(orderProduct.getNum()) == Quantity.STATE_1) {
                        basicRet.setResult(BasicRet.SUCCESS);
                        basicRet.setMessage("退货数量大于购买数量");
                    } else {
                        orderProduct.setNum(orderProduct.getNum().subtract(pdbackNum));
                        orderProduct.setActualpayment(orderProduct.getNum().multiply(orderProduct.getPrice()).add(orderProduct.getFreight()));
                        OrderProduct orderProduct1 = new OrderProduct();
                        if (orderProduct.getNum().compareTo(BigDecimal.valueOf(0)) == Quantity.STATE_0) {
                            ordersService.deleteOrderProduct(orderProduct.getId());
                            orderProduct1.setFreight(orderProduct.getFreight());
                        } else {
                            ordersService.updateOrderProduct(orderProduct);
                            orderProduct1.setFreight(BigDecimal.valueOf(0));
                        }

                        orderProduct1.setAttrjson(orderProduct.getAttrjson());
                        orderProduct1.setEvatime(orderProduct.getEvatime());
                        orderProduct1.setBuyersexperience(orderProduct.getBuyersexperience());
                        orderProduct1.setEva3(orderProduct.getEva3());
                        orderProduct1.setEva2(orderProduct.getEva2());
                        orderProduct1.setEva1(orderProduct.getEva1());
                        orderProduct1.setEvaluatestate(orderProduct.getEvaluatestate());
                        orderProduct1.setIsanonymous(orderProduct.getIsanonymous());
                        orderProduct1.setProducttype(orderProduct.getProducttype());
                        orderProduct1.setSellerid(orderProduct.getSellerid());
                        orderProduct1.setMailornot(orderProduct.getMailornot());
                        orderProduct1.setSellername(orderProduct.getSellername());
                        orderProduct1.setMembername(orderProduct.getMembername());
                        orderProduct1.setBackstate(Quantity.STATE_1);
                        orderProduct1.setPic(orderProduct.getPic());
                        orderProduct1.setActualpayment(orderProduct.getPrice().multiply(pdbackNum));
                        orderProduct1.setPaystate(orderProduct.getPaystate());
                        orderProduct1.setAllpay(orderProduct.getAllpay());
                        orderProduct1.setBrand(orderProduct.getBrand());
                        orderProduct1.setClassify(orderProduct.getClassify());
                        orderProduct1.setGradeno(orderProduct.getGradeno());
                        orderProduct1.setMark(orderProduct.getMark());
                        orderProduct1.setMaterial(orderProduct.getMaterial());
                        orderProduct1.setNum(pdbackNum);
                        orderProduct1.setOrderid(orderProduct.getOrderid());
                        orderProduct1.setOrderno(orderProduct.getOrderno());
                        orderProduct1.setPartpay(orderProduct.getPartpay());
                        orderProduct1.setPddesc(orderProduct.getPddesc());
                        orderProduct1.setPdname(orderProduct.getPdname());
                        orderProduct1.setPdpic(orderProduct.getPdpic());
                        orderProduct1.setPrice(orderProduct.getPrice());
                        orderProduct1.setStandard(orderProduct.getStandard());
                        orderProduct1.setUnit(orderProduct.getUnit());
                        orderProduct1.setYupay(orderProduct.getYupay());
                        orderProduct1.setDeliverytime(orderProduct.getDeliverytime());
                        orderProduct1.setIsmailornot(orderProduct.getIsmailornot());
                        orderProduct1.setPdid(orderProduct.getPdid());
                        orderProduct1.setPdno(orderProduct.getPdno());
                        orderProduct1.setProtype(orderProduct.getProtype());
                        orderProduct1.setStoreid(orderProduct.getStoreid());
                        orderProduct1.setStorename(orderProduct.getStorename());

                        ordersService.insertOrderProduct(orderProduct1);


                        OrderProductLog orderProductLog = orderProductLogService.getByOrderproductid(orderProduct.getId());
                        if (orderProductLog != null) {
                            orderProductLog.setId(null);
                            orderProductLog.setOrderproductid(orderProduct1.getId());
                            orderProductLogService.add(orderProductLog);
                        }


                        orderProductBack.setOrderpdid(orderProduct1.getId());

                        backMoney = orderProduct.getPrice().multiply(pdbackNum);
                    }
                } else {
                    backMoney = orderProduct.getActualpayment().subtract(orderProduct.getFreight());
                }
            }
        }
        //远期全款
        if (orderProduct.getProtype() == Quantity.STATE_1) {
            //是否支付
            if (orderProduct.getPaystate() == Quantity.STATE_1) {
                backMoney = orderProduct.getAllpay();
            }
        }
        //定金支付
        if (orderProduct.getProtype() == Quantity.STATE_2) {
            //定金已支付
            if (orderProduct.getPaystate() == Quantity.STATE_2) {
                backMoney = orderProduct.getPartpay();
            }
            if (orderProduct.getPaystate() == Quantity.STATE_3) {
                backMoney = orderProduct.getYupay().add(orderProduct.getPartpay());
            }
        }
        orderProductBack.setBackmoney(backMoney);
        orderProductBack.setPdid(orderProduct.getPdid());
        orderProductBack.setPdno(orderProduct.getPdno());
        orderProductBack.setPdname(orderProduct.getPdname());
        if (orderProductBack.getBacktype() == Quantity.STATE_2) {
            orderProductBack.setPdnum(pdbackNum);
        } else {
            orderProductBack.setPdnum(orderProduct.getNum());
        }

        orderProductBack.setMemberid(member.getId());
        orderProductBack.setMembername(member.getUsername());

        if (orders != null) {
            orderProductBack.setCode(orders.getCode());
        }
        orderProductBack.setSellerid(orderProduct.getSellerid());

        orderProductBack.setSellname(member1.getUsername());
        orderProductBack.setStoreid(orderProduct.getStoreid());

        ordersService.insertOrderProductBack(orderProductBack);
        if (orderProductBack.getBacktype() != Quantity.STATE_2) {
            orderProduct.setBackstate(Quantity.STATE_1);
            ordersService.updateOrderProduct(orderProduct);
        }


        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("退货申请成功");
        basicRet.data.orderProductBack = orderProductBack;

        //短信通知卖家
        List<Orders> list = new ArrayList<>();
        list.add(orders);
        ordersService.smsNotifySellerToBackOrders(list);


        //保存操作日志
        OperateLog operateLog = new OperateLog();
        operateLog.setContent(orderProduct.getPdname() + "申请退货");
        operateLog.setOpid(member.getId());
        operateLog.setOpname(member.getUsername());
        operateLog.setOptime(new Date());
        operateLog.setOptype(Quantity.STATE_1);
        operateLog.setOrderid(orderProduct.getOrderid());
        operateLog.setOrderno(orderProduct.getOrderno());
        operateLog.setOrderpdid(orderProduct.getId());
        operateLog.setOrderpdid(orderProductBack.getOrderpdid());
        ordersService.saveOperatelog(operateLog);

        memberLogOperator.saveMemberLog(member, null, "提交退货申请", "/rest/buyer/orders/saveOrderProductBack", request, memberOperateLogService);

        return basicRet;


    }

    /**
     * 根据商品id获取退货详情
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/getOrderProductBackByOrderProductId", method = RequestMethod.POST)
    @ApiOperation(value = "根据商品id获取退货详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "订单商品id", required = true, paramType = "query", dataType = "long"),
    })
    public BasicRet getOrderProductBackByOrderProductId(Long id) {
        OrderCarRet orderCarRet = new OrderCarRet();
        OrderProductBack orderProductBack = ordersService.getOrderProductBackByOrderProductID(id);
        ProductInfo info = ordersService.getProductInfoByPrimeKey(orderProductBack.getPdid());
        OrderProduct orderProduct = new OrderProduct();
        List<OrderProduct> list = ordersService.getOrderProductByOrderId(orderProductBack.getOrderid(), orderProductBack.getPdid(), orderProductBack.getPdno());
        BigDecimal totalnum = BigDecimal.valueOf(0);
        BigDecimal totalPay = BigDecimal.valueOf(0);
        if (list.size() > 0) {
            BeanUtils.copyProperties(list.get(0), orderProduct);
        }
        for (OrderProduct op : list) {
            totalnum = totalnum.add(op.getNum());
            totalPay = totalPay.add(op.getActualpayment());
        }
        orderProduct.setNum(totalnum);
        orderProduct.setActualpayment(totalPay);
        orderProduct.setOrderno(orderProductBack.getOrderno());

        Orders orders = ordersService.getOrdersByOrderNo(orderProductBack.getOrderno());

        if (orders.getDeliverytype() == Quantity.STATE_1) {  //如果订单为代理发货模式，显示平台地址
            orderProductBack.setContact(AgentDeliveryAddressConst.linkman);
            orderProductBack.setContactphone(AgentDeliveryAddressConst.tel);
            orderProductBack.setProvince(AgentDeliveryAddressConst.province);
            orderProductBack.setCity(AgentDeliveryAddressConst.city);
            orderProductBack.setArea(AgentDeliveryAddressConst.citysmall);
            orderProductBack.setBackaddress(AgentDeliveryAddressConst.address);
        }


        orderCarRet.data.orderProduct = orderProduct;
        orderCarRet.data.orderProductBack = orderProductBack;
        orderCarRet.data.selfsupport = info.getSelfsupport();

        String url = "";
        if (StringUtils.hasText(orderProductBack.getLogisticscompany()) && StringUtils.hasText(orderProductBack.getLogisticsno())) {
            List<String> lists = commonDataValueService.getcommonDataValue("物流公司");
            for (String vl : lists) {
                String[] vlStr = vl.split("-");
                if (orderProductBack.getLogisticscompany().equals(vlStr[0])) {
                    //物流查询
                    url = ExpressUtils.searchkuaiDiInfo(vlStr[1], orderProductBack.getLogisticsno());
                    break;
                }
            }
        }
        orderCarRet.data.expressurl = url;
        orderCarRet.setMessage("返回成功");
        orderCarRet.setResult(BasicRet.SUCCESS);
        return orderCarRet;
    }


    /**
     * 根据商品id获取退货详情
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/getOrderProductBackById", method = RequestMethod.POST)
    @ApiOperation(value = "根据退货记录id获取退货详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "退货记录id", required = true, paramType = "query", dataType = "long"),
    })
    public BasicRet getOrderProductBackById(Long id) {
        OrderCarRet orderCarRet = new OrderCarRet();
        OrderProductBack orderProductBack = ordersService.getOrderProductBackById(id);
        ProductInfo info = ordersService.getProductInfoByPrimeKey(orderProductBack.getPdid());
        OrderProduct orderProduct = new OrderProduct();
        List<OrderProduct> list = ordersService.getOrderProductByOrderId(orderProductBack.getOrderid(), orderProductBack.getPdid(), orderProductBack.getPdno());
        BigDecimal totalnum = BigDecimal.valueOf(0);
        BigDecimal totalPay = BigDecimal.valueOf(0);
        if (list.size() > 0) {
            BeanUtils.copyProperties(list.get(0), orderProduct);
        }
        for (OrderProduct op : list) {
            totalnum = totalnum.add(op.getNum());
            totalPay = totalPay.add(op.getActualpayment());
        }
        orderProduct.setNum(totalnum);
        orderProduct.setActualpayment(totalPay);
        orderProduct.setOrderno(orderProductBack.getOrderno());
        orderCarRet.data.orderProduct = orderProduct;
        orderCarRet.data.orderProductBack = orderProductBack;
        orderCarRet.data.selfsupport = info.getSelfsupport();

        String url = "";
        if (StringUtils.hasText(orderProductBack.getLogisticscompany()) && StringUtils.hasText(orderProductBack.getLogisticsno())) {
            List<String> lists = commonDataValueService.getcommonDataValue("物流公司");
            for (String vl : lists) {
                String[] vlStr = vl.split("-");
                if (orderProductBack.getLogisticscompany().equals(vlStr[0])) {
                    //物流查询
                    url = ExpressUtils.searchkuaiDiInfo(vlStr[1], orderProductBack.getLogisticsno());
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
            @ApiImplicitParam(name = "state", value = "退货状态0=待卖家处理1=卖家同意待收货2=卖家同意直接退款3=卖家收到货同意退款4=卖家不同意5=买家同意验货6=买家申请异议7=平台同意退货不扣违约金8=平台同意退货扣违约金9=平台转入待验收10=退货成功11=撤消12=卖家待收货", required = false, paramType = "query", dataType = "int"),
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
            @ApiImplicitParam(name = "pic", value = "买家上传的图片", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "backexplain", value = "退货说明", required = false, paramType = "query", dataType = "String"),
    })
    public BasicRet updateOrderProductBack(Model model, ProductBackModel productBackModel, HttpServletRequest request) throws CashException {
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        BasicRet basicRet = new BasicRet();

        Short state = productBackModel.getState();

        if (state != Quantity.STATE_0 && state != Quantity.STATE_5 && state != Quantity.STATE_6 && state != Quantity.STATE_11 && state != 12) {
            basicRet.setMessage("操作状态不合法");
            basicRet.setResult(BasicRet.ERR);
            return basicRet;
        }

        productBackModel.setAdminstate(null);

//        OrderProductBack orderProductBack = ordersService.getOrderProductBackById(productBackModel.getId());
//        if (StringUtils.hasText(productBackModel.getPic())) {
//            orderProductBack.setPic(productBackModel.getPic());
//        }
//        if (StringUtils.hasText(productBackModel.getBackexplain())) {
//            orderProductBack.setBackexplain(productBackModel.getBackexplain());
//        }
//
//
//        if (orderProductBack != null) {
//            orderProductBack.setState(state);
//            if (adminstate != null) {
//                orderProductBack.setAdminstate(adminstate);
//            }
//            if (StringUtils.hasText(productBackModel.getLogisticsno())) {
//                orderProductBack.setLogisticsno(productBackModel.getLogisticsno());
//            }
//            if (StringUtils.hasText(productBackModel.getLogisticscompany())) {
//                orderProductBack.setLogisticscompany(productBackModel.getLogisticscompany());
//            }
//            if (StringUtils.hasText(productBackModel.getBackaddress())) {
//                orderProductBack.setBackaddress(productBackModel.getBackaddress());
//            }
//            if (StringUtils.hasText(productBackModel.getContact())) {
//                orderProductBack.setContact(productBackModel.getContact());
//            }
//            if (StringUtils.hasText(productBackModel.getContactphone())) {
//                orderProductBack.setContactphone(productBackModel.getContactphone());
//            }
//            if (StringUtils.hasText(productBackModel.getSellernotagree())) {
//                orderProductBack.setSellernotagree(productBackModel.getSellernotagree());
//            }
//            if (StringUtils.hasText(productBackModel.getAdminreason())) {
//                orderProductBack.setAdminreason(productBackModel.getAdminreason());
//            }
//            if (StringUtils.hasText(productBackModel.getProvince())) {
//                orderProductBack.setProvince(productBackModel.getProvince());
//            }
//            if (StringUtils.hasText(productBackModel.getCity())) {
//                orderProductBack.setCity(productBackModel.getCity());
//            }
//            if (StringUtils.hasText(productBackModel.getArea())) {
//                orderProductBack.setArea(productBackModel.getArea());
//            }
//        }

        OrderProductBack orderProductBack = ordersService.getBackgoodsOrderProductBack(productBackModel);

        if (orderProductBack != null) {
            OrderProduct orderProduct = ordersService.getOrderProductById(orderProductBack.getOrderpdid());
            Orders orders = ordersService.getOrdersById(orderProductBack.getOrderid());

            if (state == Quantity.STATE_5) {  //买家同意验货
                orderProduct.setBackstate(Quantity.STATE_0);
            } else if (state == Quantity.STATE_6) {//买家申请异议
                orderProduct.setBackstate(Quantity.STATE_4);
                orderProductBack.setDissidencetime(new Date());
            } else if (state == Quantity.STATE_11) {  //撤销
                //部分退货
                if (orderProductBack.getBacktype() == Quantity.STATE_2) {
                    List<OrderProduct> list = ordersService.getOrderProductByOrderId(orderProductBack.getOrderid());

                    for (OrderProduct op : list) {
                        //找出部分退货相同的商品，删除这个部分退货，并加数量和总价到原来的商品
                        if (op.getPdid().longValue() == orderProductBack.getPdid().longValue() &&
                                op.getPdno().equals(orderProductBack.getPdno()) &&
                                op.getBackstate() == Quantity.STATE_0) {
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
            } else if (state == 12) {  //买家发货给卖家，卖家待收货

            }

            if (orderProductBack.getBacktype() != Quantity.STATE_2) {
                ordersService.updateOrderProduct(orderProduct);
            }
            ordersService.updateOrderProductBack(orderProductBack);


            /*
            if (state != null) {
                //卖家同意待验货
                if (state == Quantity.STATE_1) {
                    orderProduct.setBackstate(Quantity.STATE_2);
                    //卖家同意直接退款或卖家收到货同意退款
                } else if (state == Quantity.STATE_2 || state == Quantity.STATE_3) {
                    orderProduct.setBackstate(Quantity.STATE_3);
                    if (list.size() == 1) {
                        orders.setOrderstatus(Quantity.STATE_7);
                        ordersService.updateSingleOrder(orders);
                    }
                    handleBackGoods(member, orderProductBack, orderProduct, orders);
                    orderProductBack.setState(Quantity.STATE_10);
                    //买家同意验货
                } else if (state == Quantity.STATE_5) {
                    orderProduct.setBackstate(Quantity.STATE_0);
                    //买家不同意申请异议
                } else if (state == Quantity.STATE_6) {
                    orderProduct.setBackstate(Quantity.STATE_4);
                    orderProductBack.setDissidencetime(new Date());
                    //平台同意退货不扣违约金
                } else if (state == Quantity.STATE_7) {   //平台同意退货不扣违约金
                    //订单只有一件商品，如果只退款的话，直接结束订单
                    if (list.size() == 1) {
                        //只退款
                        if (orderProductBack.getBacktype() == Quantity.STATE_0) { //仅退款
                            orders.setOrderstatus(Quantity.STATE_7);
                            ordersService.updateSingleOrder(orders);
                            handleBackGoods(member, orderProductBack, orderProduct, orders);
                            orderProduct.setBackstate(Quantity.STATE_3);
                            orderProductBack.setState(Quantity.STATE_10);
                        } else {   //1=退货退款2=部分退货
                            //退货验收
                            orderProduct.setBackstate(Quantity.STATE_2);
                        }
                    } else {
                        //只退款
                        if (orderProductBack.getBacktype() == Quantity.STATE_0) {
                            //退货完成
                            handleBackGoods(member, orderProductBack, orderProduct, orders);
                            orderProduct.setBackstate(Quantity.STATE_3);
                            orderProductBack.setState(Quantity.STATE_10);
                        } else {
                            orderProduct.setBackstate(Quantity.STATE_2);
                        }
                    }

                } else if (state == Quantity.STATE_8) {  //平台同意退货扣违约金
                    //订单只有一件商品，如果只退款的话，直接结束订单
                    if (list.size() == 1) {
                        //只退款
                        if (orderProductBack.getBacktype() == Quantity.STATE_0) {
                            orders.setOrderstatus(Quantity.STATE_7);
                            ordersService.updateSingleOrder(orders);
                            handleBackGoods(member, orderProductBack, orderProduct, orders);
                            orderProduct.setBackstate(Quantity.STATE_3);
                            orderProductBack.setState(Quantity.STATE_10);
                        } else {
                            //退货验收
                            orderProduct.setBackstate(Quantity.STATE_2);
                        }
                    } else {
                        //只退款
                        if (orderProductBack.getBacktype() == Quantity.STATE_0) {
                            //退货完成
                            handleBackGoods(member, orderProductBack, orderProduct, orders);
                            orderProduct.setBackstate(Quantity.STATE_3);
                            orderProductBack.setState(Quantity.STATE_10);
                        } else {
                            orderProduct.setBackstate(Quantity.STATE_2);
                        }
                    }
                    //平台转入待验收
                } else if (state == Quantity.STATE_9) {
                    orderProduct.setBackstate(Quantity.STATE_0);
                } else if (state == Quantity.STATE_11) {  //撤销退货
                    //部分退货
                    if (orderProductBack.getBacktype() == Quantity.STATE_2) {
                        for (OrderProduct op : list) {
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
                    //卖家待收货
                } else if (state == Quantity.STATE_12) {

                } else {
                    //卖家不同意
                }
                if (orderProductBack.getBacktype() != Quantity.STATE_2) {
                    ordersService.updateOrderProduct(orderProduct);
                }
                ordersService.updateOrderProductBack(orderProductBack);
            }*/
            // syn wms
//            if (state != null && state == Quantity.STATE_11) {
//                wmsService.cancelOrders(orders, WMSService.CANCEL_BACK_ORDER_TYPE);
//            }
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

//        if (orderProductBack.getState() == Quantity.STATE_1) {
//            operateLog.setContent("卖家同意待收货");
//            ordersService.saveOperatelog(operateLog);
//        }
//        if (orderProductBack.getState() == Quantity.STATE_2) {
//            operateLog.setContent("卖家同意直接退款");
//            ordersService.saveOperatelog(operateLog);
//        }
//        if (orderProductBack.getState() == Quantity.STATE_3) {
//            operateLog.setContent("卖家收到货同意退款");
//            ordersService.saveOperatelog(operateLog);
//        }
//        if (orderProductBack.getState() == Quantity.STATE_4) {
//            operateLog.setContent("卖家不同意");
//            ordersService.saveOperatelog(operateLog);
//        }
//        if (orderProductBack.getState() == Quantity.STATE_5) {
//            operateLog.setContent("买家同意验货");
//            ordersService.saveOperatelog(operateLog);
//        }
//        if (orderProductBack.getState() == Quantity.STATE_6) {
//            operateLog.setContent("买家申请异议");
//            ordersService.saveOperatelog(operateLog);
//        }
//        if (orderProductBack.getState() == Quantity.STATE_7) {
//            operateLog.setContent("平台同意退货不扣违约金");
//            ordersService.saveOperatelog(operateLog);
//        }
//        if (orderProductBack.getState() == Quantity.STATE_8) {
//            operateLog.setContent("平台同意退货扣违约金");
//            ordersService.saveOperatelog(operateLog);
//        }
//        if (orderProductBack.getState() == Quantity.STATE_9) {
//            operateLog.setContent("平台转入待验收");
//            ordersService.saveOperatelog(operateLog);
//        }
//        if (orderProductBack.getState() == Quantity.STATE_10) {
//            operateLog.setContent("退货成功");
//            ordersService.saveOperatelog(operateLog);
//        }
//        if (orderProductBack.getState() == Quantity.STATE_11) {
//            operateLog.setContent("退货撤消");
//            ordersService.saveOperatelog(operateLog);
//        }

        memberLogOperator.saveMemberLog(member, null, "修改退货申请", "/rest/buyer/orders/updateOrderProductBack", request, memberOperateLogService);
        basicRet.setMessage("修改成功");
        basicRet.setResult(BasicRet.SUCCESS);
        return basicRet;
    }

    /**
     * 退款操作
     *
     * @param operator         操作人
     * @param orderProductBack
     * @param orderProduct
     */

    /*
    public void handleBackGoods(Member operator, OrderProductBack orderProductBack, OrderProduct orderProduct, Orders order) throws CashException {
        //退款申请人
        Member member = memberService.getMemberById(orderProductBack.getMemberid());

        Member oldMember = new Member();
        BeanUtils.copyProperties(member,oldMember);

        TransactionSetting transactionSetting = transactionSettingService.getTransactionSetting();
        BigDecimal getLiquidated = transactionSetting.getGetliquidated().multiply(new BigDecimal(0.01));
        BigDecimal forwardsalesmargin = transactionSetting.getForwardsalesmargin().multiply(new BigDecimal(0.01));

        if (orderProductBack != null && orderProduct != null && order != null) {
            BigDecimal backPay = BigDecimal.valueOf(0);
            BigDecimal penaltyPay = BigDecimal.valueOf(0);

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
                        member.setBalance(member.getBalance().add(backPay));
                        buyerCapital1 = createBuyerBackPay(order, backPay, tranTime, Quantity.STATE_3);
                        salerCapital1 = createSalerBackPay(order, backPay, tranTime);
                        buyerCapitals.add(buyerCapital1);
                        salerCapitals.add(salerCapital1);
                    }
                    //退回到授信
                    if (buyerCapital.getPaytype() == Quantity.STATE_4) {
                        member.setAvailablelimit(member.getAvailablelimit().add(backPay));
                        member.setUsedlimit(member.getUsedlimit().subtract(backPay));
                        buyerCapital1 = createBuyerBackPay(order, backPay, tranTime, Quantity.STATE_4);
                        salerCapital1 = createSalerBackPay(order, backPay, tranTime);
                        buyerCapitals.add(buyerCapital1);
                        salerCapitals.add(salerCapital1);
                    }
                    //异议扣违约金,记录资金明细
                    if (orderProductBack.getAdminstate() == Quantity.STATE_2) {
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
                        member.setBalance(member.getBalance().add(backPay));
                        buyerCapital1 = createBuyerBackPay(order, backPay, tranTime, Quantity.STATE_3);
                        salerCapital1 = createSalerBackPay(order, backPay, tranTime);
                        buyerCapitals.add(buyerCapital1);
                        salerCapitals.add(salerCapital1);
                    }
                    //退回到授信
                    if (buyerCapital.getPaytype() == Quantity.STATE_4) {
                        member.setAvailablelimit(member.getAvailablelimit().add(backPay));
                        member.setUsedlimit(member.getUsedlimit().subtract(backPay));
                        buyerCapital1 = createBuyerBackPay(order, backPay, tranTime, Quantity.STATE_4);
                        salerCapital1 = createSalerBackPay(order, backPay, tranTime);
                        buyerCapitals.add(buyerCapital1);
                        salerCapitals.add(salerCapital1);
                    }
                    //异议扣违约金,记录资金明细
                    if (orderProductBack.getAdminstate() == Quantity.STATE_2) {
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
                //违约金
                penaltyPay = orderPay.multiply(getLiquidated);
                //定金支付明细
                BuyerCapital depositBuyerCapital = ordersService.getBuyerCapitalByNoType(order.getOrderno(), Quantity.STATE_7);

                if (depositBuyerCapital != null) {
                    //定金退回金额
                    BigDecimal depositBackPay = BigDecimal.valueOf(0);
                    //异议扣违约金
                    if (orderProductBack.getAdminstate() == Quantity.STATE_2) {
                        depositBackPay = depositBuyerCapital.getCapital().subtract(penaltyPay);
                    } else {
                        depositBackPay = depositBuyerCapital.getCapital();
                    }
                    //退回到余额
                    if (depositBuyerCapital.getPaytype() == Quantity.STATE_3) {
                        member.setBalance(member.getBalance().add(depositBackPay));
                        buyerCapital1 = createBuyerBackPay(order, depositBackPay, tranTime, Quantity.STATE_3);
                        salerCapital1 = createSalerBackPay(order, depositBackPay, tranTime);
                        buyerCapitals.add(buyerCapital1);
                        salerCapitals.add(salerCapital1);
                    }
                    //退回到授信
                    if (depositBuyerCapital.getPaytype() == Quantity.STATE_4) {
                        member.setAvailablelimit(member.getAvailablelimit().add(depositBackPay));
                        member.setUsedlimit(member.getUsedlimit().subtract(depositBackPay));
                        buyerCapital1 = createBuyerBackPay(order, depositBackPay, tranTime, Quantity.STATE_4);
                        salerCapital1 = createSalerBackPay(order, depositBackPay, tranTime);
                        buyerCapitals.add(buyerCapital1);
                        salerCapitals.add(salerCapital1);
                    }
                    //异议扣违约金,记录资金明细
                    if (orderProductBack.getAdminstate() == Quantity.STATE_2) {
                        //违约金
                        buyerCapital2 = createBuyerPenaltyPay(order, penaltyPay, tranTime, Quantity.STATE_6, orderPay, Quantity.BUYER_BACK_REASON);
                        salerCapital2 = createSalerPenaltyPay(order, penaltyPay, tranTime, Quantity.STATE_6, orderPay, Quantity.BUYER_BACK_REASON);
                        buyerCapitals.add(buyerCapital2);
                        salerCapitals.add(salerCapital2);
                    }
                }

                //余款支付明细
                BuyerCapital banlanceBuyerCapital = ordersService.getBuyerCapitalByNoType(order.getOrderno(), Quantity.STATE_8);
                if (banlanceBuyerCapital != null) {
                    BigDecimal banlanceBackPay = banlanceBuyerCapital.getCapital();
                    //退回到余额
                    if (banlanceBuyerCapital.getPaytype() == Quantity.STATE_3) {
                        member.setBalance(member.getBalance().add(banlanceBackPay));
                        buyerCapital1 = createBuyerBackPay(order, banlanceBackPay, tranTime, Quantity.STATE_3);
                        salerCapital1 = createSalerBackPay(order, banlanceBackPay, tranTime);
                        buyerCapitals.add(buyerCapital1);
                        salerCapitals.add(salerCapital1);
                    }
                    //退回到授信
                    if (banlanceBuyerCapital.getPaytype() == Quantity.STATE_4) {
                        member.setAvailablelimit(member.getAvailablelimit().add(banlanceBackPay));
                        member.setUsedlimit(member.getUsedlimit().subtract(banlanceBackPay));
                        buyerCapital1 = createBuyerBackPay(order, banlanceBackPay, tranTime, Quantity.STATE_4);
                        salerCapital1 = createSalerBackPay(order, banlanceBackPay, tranTime);
                        buyerCapitals.add(buyerCapital1);
                        salerCapitals.add(salerCapital1);
                    }
                }
            }
            //保存用户余额和授信
            ordersService.saveMember(member,oldMember);
            ordersService.insertBuyerCapital(buyerCapitals);
            ordersService.insertSallerCapital(salerCapitals);

        }

        //保存操作日志
        OperateLog operateLog = new OperateLog();
        operateLog.setContent("退货成功");
        operateLog.setOpid(operator.getId());
        operateLog.setOpname(operator.getUsername());
        operateLog.setOptime(new Date());
        operateLog.setOptype(Quantity.STATE_1);
        operateLog.setOrderid(orderProductBack.getOrderid());
        operateLog.setOrderno(orderProductBack.getOrderno());
        operateLog.setOrderpdid(orderProductBack.getOrderpdid());
        ordersService.saveOperatelog(operateLog);

    }
    */

    @Autowired
    private WMSService wmsService;

    @Autowired
    private OrderStoreStateLogService orderStoreStateLogService;

    @RequestMapping(value = "/cancelOrders", method = RequestMethod.POST)
    @ApiOperation(value = "取消订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "订单id", required = true, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "type", value = "0=买家取消订单1=卖家取消订单", required = true, paramType = "query", dataType = "int"),
    })
    public BasicRet cancelOrders(Model model, Long id, int type, HttpServletRequest request) throws CashException, WxPayException, AlipayApiException {
        BasicRet basicRet = new BasicRet();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        member = memberService.getMemberById(member.getId());

        Member oldMember = new Member();
        BeanUtils.copyProperties(member, oldMember);

        Orders orders = ordersService.getSingleOrder(id);
        Member seller = memberService.getMemberById(orders.getSaleid());
        Member oldSeller = new Member();
        BeanUtils.copyProperties(seller, oldSeller);


        try {
            OrderStoreStateLog orderStoreStateLog = orderStoreStateLogService.getStoreState(orders);
            if (orderStoreStateLog != null && (orderStoreStateLog.getLaststate() >= 2)) {
                return new BasicRet(BasicRet.ERR, "该订单已出库，不可取消");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        //退款金额
        BigDecimal backPay = BigDecimal.valueOf(0).setScale(2);
        //违约金金额
        BigDecimal penaltyPay = BigDecimal.valueOf(0).setScale(2);

        TransactionSetting transactionSetting = transactionSettingService.getTransactionSetting();

        BigDecimal getLiquidated = transactionSetting.getGetliquidated().multiply(new BigDecimal(0.01));
        BigDecimal forwardsalesmargin = transactionSetting.getForwardsalesmargin().multiply(new BigDecimal(0.01));

        List<OrderProduct> orderProducts = ordersService.getOrderProductByOrderId(id);

        Short productType = orderProducts.get(0).getProducttype();

        if (orders != null) {
            //没有支付
            if (orders.getOrderstatus() == Quantity.STATE_0) {
                //订单关闭
                orders.setOrderstatus(Quantity.STATE_7);
                ordersService.updateSingleOrder(orders);
                //删除开票申请记录
                ordersService.deleteBillRecord(orders.getOrderno());

            } else if (ordersService.canCancelOrders(orders)) {  //是否可以取消订单

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
                    //计算扣除违约金
                    BigDecimal orderPay = orders.getTotalprice().subtract(orders.getFreight());
                    //紧固件才扣违约金
                    if (productType == Quantity.STATE_1) {
                        //违约金
                        penaltyPay = orderPay.multiply(getLiquidated).setScale(2, BigDecimal.ROUND_HALF_UP);
                        System.out.println("订单金额：" + orderPay);
                        System.out.println("扣违约金：" + penaltyPay);
                        //退回的金额
                        backPay = orderPay.subtract(penaltyPay).setScale(2, BigDecimal.ROUND_HALF_UP);
                    } else {
                        backPay = orderPay;
                    }

                    //未发货，运费一块退回给买家
                    backPay = backPay.add(orders.getFreight()).setScale(2, BigDecimal.ROUND_HALF_UP);
                    System.out.println("退回金额：" + backPay);

                    //判断退回到余额还是授信
                    BuyerCapital buyerCapital = ordersService.getBuyerCapitalByNoType(orders.getOrderno(), Quantity.STATE_0);
                    if (buyerCapital != null) {
                        //退回到余额
                        if (buyerCapital.getPaytype() == Quantity.STATE_3) {
                            member.setBalance(member.getBalance().add(backPay));
                            buyerCapital1 = createBuyerBackPay(orders, backPay, tranTime, Quantity.STATE_3);
                            salerCapital1 = createSalerBackPay(orders, orders.getTotalprice(), tranTime);
                            buyerCapitals.add(buyerCapital1);
                            salerCapitals.add(salerCapital1);
                        }
                        //退回到授信
                        if (buyerCapital.getPaytype() == Quantity.STATE_4) {
                            member.setAvailablelimit(member.getAvailablelimit().add(backPay));
                            member.setUsedlimit(member.getUsedlimit().subtract(backPay));
                            buyerCapital1 = createBuyerBackPay(orders, backPay, tranTime, Quantity.STATE_4);
                            salerCapital1 = createSalerBackPay(orders, orders.getTotalprice(), tranTime);
                            buyerCapitals.add(buyerCapital1);
                            salerCapitals.add(salerCapital1);
                        }
                        //退回到支付宝或微信
                        if (buyerCapital.getPaytype() == Quantity.STATE_0
                                || buyerCapital.getPaytype() == Quantity.STATE_1
                                || buyerCapital.getPaytype() == Quantity.STATE_2) {
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
                                refund.setRefundAmount((backPay.multiply(new BigDecimal(100))).longValue());
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
                                        buyerCapital1 = createBuyerBackPay(orders, backPay, tranTime, Quantity.STATE_0);
                                    } else if (orders.getPaytype() == Quantity.STATE_1) {
                                        buyerCapital1 = createBuyerBackPay(orders, backPay, tranTime, Quantity.STATE_1);
                                    } else if (orders.getPaytype() == Quantity.STATE_2) {
                                        buyerCapital1 = createBuyerBackPay(orders, backPay, tranTime, Quantity.STATE_2);
                                    }
                                    salerCapital1 = createSalerBackPay(orders, orders.getTotalprice(), tranTime);
                                    buyerCapitals.add(buyerCapital1);
                                    salerCapitals.add(salerCapital1);
                                } else {
                                    throw new RuntimeException("退款失败");
                                }

                            }
                        }
                        if (productType == Quantity.STATE_1) {
                            //违约金
//                            seller.setSellerbanlance(seller.getSellerbanlance().add(penaltyPay.multiply(forwardsalesmargin)));
//                            buyerCapital2 = createBuyerPenaltyPay(orders, penaltyPay, tranTime, Quantity.STATE_6, orderPay, Quantity.BUYER_CANCEL_REASON);
//                            salerCapital2 = createSalerPenaltyPay(orders, penaltyPay, tranTime, Quantity.STATE_6, orderPay, Quantity.BUYER_CANCEL_REASON);
//                            buyerCapitals.add(buyerCapital2);
//                            salerCapitals.add(salerCapital2);


                            BigDecimal sellerPenaltyPay = penaltyPay.multiply(forwardsalesmargin).setScale(2, BigDecimal.ROUND_HALF_UP);
                            seller.setSellerbanlance(seller.getSellerbanlance().add(sellerPenaltyPay));

                            //买方扣除违约金
                            buyerCapital2 = createBuyerPenaltyPay(orders, penaltyPay, tranTime, Quantity.STATE_6, orderPay, Quantity.BUYER_CANCEL_REASON);

                            //卖方增加违约金额 = 违约金额*被违约方获取违约金占比；
                            if (sellerPenaltyPay.compareTo(Quantity.BIG_DECIMAL_0) > 0) {
                                salerCapital2 = createSalerPenaltyPay(orders, sellerPenaltyPay, tranTime, Quantity.STATE_6, orderPay, Quantity.BUYER_CANCEL_REASON);
                                salerCapitals.add(salerCapital2);
                            }
                            buyerCapitals.add(buyerCapital2);
                        }
                    }
                }
                //远期全款
                if (orders.getOrdertype() == Quantity.STATE_1) {
                    //计算扣除违约金
                    BigDecimal orderPay = orders.getAllpay();
                    if (productType == Quantity.STATE_1) {
                        //违约金
                        penaltyPay = orderPay.multiply(getLiquidated);
                        //退回的金额
                        backPay = orderPay.subtract(penaltyPay);
                    } else {
                        backPay = orderPay;
                    }
                    //未发货，运费退回
                    backPay = backPay.add(orders.getFreight()).setScale(2, BigDecimal.ROUND_HALF_UP);

                    //判断退回到余额还是授信
                    BuyerCapital buyerCapital = ordersService.getBuyerCapitalByNoType(orders.getOrderno(), Quantity.STATE_9);
                    if (buyerCapital != null) {
                        //退回到余额
                        if (buyerCapital.getPaytype() == Quantity.STATE_3) {
                            member.setBalance(member.getBalance().add(backPay));
                            buyerCapital1 = createBuyerBackPay(orders, backPay, tranTime, Quantity.STATE_3);
                            salerCapital1 = createSalerBackPay(orders, orders.getActualpayment(), tranTime);
                            buyerCapitals.add(buyerCapital1);
                            salerCapitals.add(salerCapital1);
                        }
                        //退回到授信
                        if (buyerCapital.getPaytype() == Quantity.STATE_4) {
                            member.setAvailablelimit(member.getAvailablelimit().add(backPay));
                            member.setUsedlimit(member.getUsedlimit().subtract(backPay));
                            buyerCapital1 = createBuyerBackPay(orders, backPay, tranTime, Quantity.STATE_4);
                            salerCapital1 = createSalerBackPay(orders, orders.getActualpayment(), tranTime);
                            buyerCapitals.add(buyerCapital1);
                            salerCapitals.add(salerCapital1);
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
                                refund.setRefundAmount((backPay.multiply(new BigDecimal(100))).longValue());
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
                                if (result) {
                                    if (orders.getPaytype() == Quantity.STATE_0) {
                                        buyerCapital1 = createBuyerBackPay(orders, backPay, tranTime, Quantity.STATE_0);
                                    } else if (orders.getPaytype() == Quantity.STATE_1) {
                                        buyerCapital1 = createBuyerBackPay(orders, backPay, tranTime, Quantity.STATE_1);
                                    } else if (orders.getPaytype() == Quantity.STATE_2) {
                                        buyerCapital1 = createBuyerBackPay(orders, backPay, tranTime, Quantity.STATE_2);
                                    }
                                    salerCapital1 = createSalerBackPay(orders, orders.getTotalprice(), tranTime);
                                    buyerCapitals.add(buyerCapital1);
                                    salerCapitals.add(salerCapital1);
                                } else {
                                    throw new RuntimeException("退款失败");
                                }

                            }
                        }
                        //违约金
                        if (productType == Quantity.STATE_1) {
                            BigDecimal sellerPenaltyPay = penaltyPay.multiply(forwardsalesmargin).setScale(2, BigDecimal.ROUND_HALF_UP);
                            seller.setSellerbanlance(seller.getSellerbanlance().add(sellerPenaltyPay));

                            //买方扣除违约金
                            buyerCapital2 = createBuyerPenaltyPay(orders, penaltyPay, tranTime, Quantity.STATE_6, orderPay, Quantity.BUYER_CANCEL_REASON);

                            //卖方增加违约金额 = 违约金额*被违约方获取违约金占比；
                            if (sellerPenaltyPay.compareTo(Quantity.BIG_DECIMAL_0) > 0) {
                                salerCapital2 = createSalerPenaltyPay(orders, sellerPenaltyPay, tranTime, Quantity.STATE_6, orderPay, Quantity.BUYER_CANCEL_REASON);
                                salerCapitals.add(salerCapital2);
                            }
                            buyerCapitals.add(buyerCapital2);
                        }

                    } else {
                        throw new CashException("买家订单付款明细未查询到，请联系网站客服");
                    }

                }


                //远期定金    已付定金，卖家处于备货中或备货完成状态   买家扣除定金
                if ((orders.getOrdertype() == Quantity.STATE_3 && orders.getOrderstatus() == Quantity.STATE_8) ||
                        (orders.getOrdertype() == Quantity.STATE_3 && orders.getOrderstatus() == Quantity.STATE_9)) {
                    //判断退回到余额还是授信
                    BuyerCapital buyerCapital = ordersService.getBuyerCapitalByNoType(orders.getOrderno(), Quantity.STATE_7);
                    //计算扣除违约金
                    BigDecimal orderPay = orders.getTotalprice().subtract(orders.getFreight());
//                    if (productType == Quantity.STATE_1) {
//                        //违约金
//                        penaltyPay = orders.getDeposit();//orderPay.multiply(getLiquidated);
//                        //退回的金额
//                        backPay = Quantity.BIG_DECIMAL_0;
//                    } else {
//                        backPay = orders.getDeposit();
//                    }

                    //违约金
                    penaltyPay = orders.getDeposit();//orderPay.multiply(getLiquidated);
                    //退回的金额
                    backPay = Quantity.BIG_DECIMAL_0;

                    //创建卖家退款明细
                    salerCapital1 = createSalerBackPay(orders, orders.getDeposit(), tranTime);
                    salerCapitals.add(salerCapital1);
                    /*
                    if (buyerCapital != null) {
                        if (backPay.compareTo(BigDecimal.valueOf(0)) != Quantity.STATE_0) {
                            //退回到余额
                            if (buyerCapital.getPaytype() == Quantity.STATE_3) {
                                member.setBalance(member.getBalance().add(backPay));
                                buyerCapital1 = createBuyerBackPay(orders, backPay, tranTime, Quantity.STATE_3);
                                salerCapital1 = createSalerBackPay(orders, orders.getTotalprice(), tranTime);
                                buyerCapitals.add(buyerCapital1);
                                salerCapitals.add(salerCapital1);
                            }
                            //退回到授信
                            if (buyerCapital.getPaytype() == Quantity.STATE_4) {
                                member.setAvailablelimit(member.getAvailablelimit().add(backPay));
                                member.setUsedlimit(member.getUsedlimit().subtract(backPay));
                                buyerCapital1 = createBuyerBackPay(orders, backPay, tranTime, Quantity.STATE_4);
                                salerCapital1 = createSalerBackPay(orders, orders.getTotalprice(), tranTime);
                                buyerCapitals.add(buyerCapital1);
                                salerCapitals.add(salerCapital1);
                            }
                            //退回到支付宝或微信
                            if (buyerCapital.getPaytype() == Quantity.STATE_0 || buyerCapital.getPaytype() == Quantity.STATE_1) {
                                String uuid = orders.getUuid();
                                if (uuid != null) {
                                    Refund refund = new Refund();
                                    refund.setOutTradeNo(uuid);
                                    if (orders.getPaytype() == Quantity.STATE_0) {
                                        refund.setChannel("alipay");
                                    } else {
                                        refund.setChannel("wxpay");
                                    }
                                    refund.setRefundAmount((backPay.multiply(new BigDecimal(100))).longValue());
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
                                        }
                                    } catch (Exception e) {

                                    }
                                    if (result) {
                                        if (orders.getPaytype() == Quantity.STATE_0) {
                                            buyerCapital1 = createBuyerBackPay(orders, backPay, tranTime, Quantity.STATE_0);
                                        } else {
                                            buyerCapital1 = createBuyerBackPay(orders, backPay, tranTime, Quantity.STATE_1);
                                        }
                                        salerCapital1 = createSalerBackPay(orders, orders.getTotalprice(), tranTime);
                                        buyerCapitals.add(buyerCapital1);
                                        salerCapitals.add(salerCapital1);
                                    }

                                }
                            }
                        }*/
                    //违约金
                    if (productType == Quantity.STATE_1) {
                        BigDecimal sellerPenaltyPay = penaltyPay.multiply(forwardsalesmargin).setScale(2, BigDecimal.ROUND_HALF_UP);

                        seller.setSellerbanlance(seller.getSellerbanlance().add(sellerPenaltyPay));
                        //buyerCapital2 = createBuyerPenaltyPay(orders, penaltyPay, tranTime, Quantity.STATE_6, orderPay, Quantity.BUYER_CANCEL_REASON);
                        salerCapital2 = createSalerPenaltyPay(orders, sellerPenaltyPay, tranTime, Quantity.STATE_6, orderPay, Quantity.BUYER_CANCEL_REASON);
                        //buyerCapitals.add(buyerCapital2);
                        salerCapitals.add(salerCapital2);
                    }
                }


                //远期余款
                if (orders.getOrdertype() == Quantity.STATE_3 && orders.getOrderstatus() == Quantity.STATE_1) {

                    //订单金额
                    BigDecimal orderPay = orders.getActualpayment().subtract(orders.getFreight());

                    //定金金额
                    BigDecimal partPay = orders.getDeposit();
                    //远期余款货款金额
                    BigDecimal yuPay = orders.getBalance();


                    BigDecimal backMoney1 = Quantity.BIG_DECIMAL_0;
                    BigDecimal backMoney2 = Quantity.BIG_DECIMAL_0;


                    //定金支付明细
                    BuyerCapital depositBuyerCapital = ordersService.getBuyerCapitalByNoType(orders.getOrderno(), Quantity.STATE_7);
                    if (productType == Quantity.STATE_1) {
                        //违约金
                        backMoney1 = partPay.subtract(partPay.multiply(getLiquidated)).setScale(2, BigDecimal.ROUND_HALF_UP);
                        backMoney2 = yuPay.subtract(yuPay.multiply(getLiquidated)).setScale(2, BigDecimal.ROUND_HALF_UP);
                    } else {
                        backMoney1 = partPay;
                        backMoney2 = yuPay;
                    }

                    //尾款包含运费，运费退给买家
                    backMoney2 = backMoney2.add(orders.getFreight());

                    //backPay = backMoney1.add(backMoney2);
                    penaltyPay = (partPay.add(yuPay)).multiply(getLiquidated).setScale(2, BigDecimal.ROUND_HALF_UP);


                    backPay = orders.getActualpayment().subtract(penaltyPay);

                    if (depositBuyerCapital != null) {

                        //退回到余额
                        if (depositBuyerCapital.getPaytype() == Quantity.STATE_3) {
                            member.setBalance(member.getBalance().add(backPay));
                            buyerCapital1 = createBuyerBackPay(orders, backPay, tranTime, Quantity.STATE_3);
                            salerCapital1 = createSalerBackPay(orders, orders.getActualpayment(), tranTime);
                            buyerCapitals.add(buyerCapital1);
                            salerCapitals.add(salerCapital1);
                        }
                        //退回到授信
                        if (depositBuyerCapital.getPaytype() == Quantity.STATE_4) {
                            member.setAvailablelimit(member.getAvailablelimit().add(backPay));
                            member.setUsedlimit(member.getUsedlimit().subtract(backPay));
                            buyerCapital1 = createBuyerBackPay(orders, backPay, tranTime, Quantity.STATE_4);
                            salerCapital1 = createSalerBackPay(orders, orders.getActualpayment(), tranTime);
                            buyerCapitals.add(buyerCapital1);
                            salerCapitals.add(salerCapital1);
                        }
                        //退回到支付宝或微信
                        if (depositBuyerCapital.getPaytype() == Quantity.STATE_0 ||
                                depositBuyerCapital.getPaytype() == Quantity.STATE_1 ||
                                depositBuyerCapital.getPaytype() == Quantity.STATE_2) {
                            String uuid = orders.getUuid();
                            String yuuuid = orders.getYuuuid();
                            if (uuid != null && yuuuid != null) {
                                //定金
                                Refund refund1 = new Refund();
                                //余款
                                Refund refund2 = new Refund();
                                refund1.setOutTradeNo(uuid);
                                refund2.setOutTradeNo(yuuuid);
                                if (orders.getPaytype() == Quantity.STATE_0) {
                                    refund1.setChannel("alipay");
                                    refund2.setChannel("alipay");
                                } else if (orders.getPaytype() == Quantity.STATE_1) {
                                    refund1.setChannel("wxpay");
                                    refund2.setChannel("wxpay");
                                } else if (orders.getPaytype() == Quantity.STATE_3) {
                                    refund1.setChannel("bank");
                                    refund2.setChannel("bank");
                                }
                                refund1.setRefundAmount((backMoney1.multiply(new BigDecimal(100))).longValue());
                                refund2.setRefundAmount((backMoney2.multiply(new BigDecimal(100))).longValue());

                                refund1.setRefundReason("订单定金退款");
                                refund2.setRefundReason("订单余款退款");
                                List<Orders> ordersList = ordersService.getOrdersByUUID(uuid);
                                BigDecimal totalPartPay = BigDecimal.valueOf(0);
                                BigDecimal totalYuPay = BigDecimal.valueOf(0);
                                for (Orders od : ordersList) {
                                    totalPartPay = totalPartPay.add(od.getDeposit());
                                    totalYuPay = totalYuPay.add(od.getBalance());
                                }
                                refund1.setTotalAmount((totalPartPay.multiply(new BigDecimal(100))).longValue());
                                refund2.setTotalAmount((totalYuPay.multiply(new BigDecimal(100))).longValue());

                                boolean result1 = false;
                                boolean result2 = false;
                                try {
                                    if ("alipay".equals(refund1.getChannel())) {
                                        result1 = alipayService.refund(refund1);
                                        result2 = alipayService.refund(refund2);
                                    } else if ("wxpay".equals(refund1.getChannel())) {
                                        result1 = wxPayService.refund(refund1);
                                        result2 = wxPayService.refund(refund2);
                                    } else if ("bank".equals(refund1.getChannel())) {
                                        result1 = abcService.refund(refund1);
                                        result2 = abcService.refund(refund2);
                                    }
                                } catch (Exception e) {
                                    throw e;
                                }
                                if (result1 && result2) {
                                    if (orders.getPaytype() == Quantity.STATE_0) {
                                        buyerCapital1 = createBuyerBackPay(orders, backPay, tranTime, Quantity.STATE_0);
                                    } else if (orders.getPaytype() == Quantity.STATE_1) {
                                        buyerCapital1 = createBuyerBackPay(orders, backPay, tranTime, Quantity.STATE_1);
                                    } else if (orders.getPaytype() == Quantity.STATE_2) {
                                        buyerCapital1 = createBuyerBackPay(orders, backPay, tranTime, Quantity.STATE_2);
                                    }
                                    salerCapital1 = createSalerBackPay(orders, orders.getActualpayment(), tranTime);
                                    buyerCapitals.add(buyerCapital1);
                                    salerCapitals.add(salerCapital1);
                                } else {
                                    throw new RuntimeException("退款失败");
                                }

                            }
                        }
                        //违约金
                        if (productType == Quantity.STATE_1) {
                            BigDecimal sellerPenaltyPay = penaltyPay.multiply(forwardsalesmargin).setScale(2, BigDecimal.ROUND_HALF_UP);
                            seller.setSellerbanlance(seller.getSellerbanlance().add(sellerPenaltyPay));

                            //买方扣除违约金
                            buyerCapital2 = createBuyerPenaltyPay(orders, penaltyPay, tranTime, Quantity.STATE_6, orderPay, Quantity.BUYER_CANCEL_REASON);

                            //卖方增加违约金额 = 违约金额*被违约方获取违约金占比；
                            if (sellerPenaltyPay.compareTo(Quantity.BIG_DECIMAL_0) > 0) {
                                salerCapital2 = createSalerPenaltyPay(orders, sellerPenaltyPay, tranTime, Quantity.STATE_6, orderPay, Quantity.BUYER_CANCEL_REASON);
                                salerCapitals.add(salerCapital2);
                            }
                            buyerCapitals.add(buyerCapital2);
                        }
                    }
                }

                //保存用户余额和授信
                ordersService.saveMember(member, oldMember);
                ordersService.saveMember(seller, oldSeller);


                if (buyerCapitals.size() > 0) {
                    ordersService.insertBuyerCapital(buyerCapitals);
                }

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


        //普通订单
        if (orders.getIsonline() == Quantity.STATE_0) {
            //加库存
            for (OrderProduct orderProduct : orderProducts) {
                //ProductStore productStore = ordersService.getProductStore(orderProduct.getPdid(), orderProduct.getPdno(),orderProduct.getStoreid());
                //productStore.setPdstorenum(productStore.getPdstorenum().add(orderProduct.getNum()))
                // pordersService.updateProductStore(productStore);
                productStoreService.addStoreNumByPdidAndPdno(orderProduct.getPdid(), orderProduct.getPdno(), orderProduct.getNum());

                //将退货的商品信息记录到orderproductbackinfo表中
                OrderProductBackInfo orderProductBackInfo = new OrderProductBackInfo();
                orderProductBackInfo.setOrderno(orderProduct.getOrderno());
                orderProductBackInfo.setPdid(orderProduct.getPdid());
                orderProductBackInfo.setBackno("TH" + UUID.randomUUID());
                orderProductBackInfo.setBacknum(orderProduct.getNum());
                orderProductBackInfo.setBacktype(Quantity.STATE_0);
                orderProductBackInfo.setBackstate(Quantity.STATE_0);
                orderProductBackInfo.setBacktime(new Date());
                OrderProductBackInfoService.addOrderProductBackInfo(orderProductBackInfo);
            }
        } else if (orders.getIsonline() == Quantity.STATE_2) {
            Map<String, BigDecimal> proMap = new HashMap<String, BigDecimal>();
            //加库存
            for (OrderProduct orderProduct : orderProducts) {
                LimitTimeStore store = shopCarService.getLimitTimeStore(orderProduct.getLimitid(), orderProduct.getPdid(), orderProduct.getPdno());
                LimitTimeProd prod = shopCarService.getLimitTimeProd(orderProduct.getPdid(), orderProduct.getLimitid());
                store.setStorenum(store.getStorenum().add(orderProduct.getNum()));
                store.setSalesnum(store.getSalesnum().subtract(orderProduct.getNum()));
                shopCarService.updateLimitTimeStore(store);
                //商品id进行分组
                BigDecimal num = proMap.get(orderProduct.getPdid() + "," + orderProduct.getLimitid());
                if (num == null) {
                    proMap.put(orderProduct.getPdid() + "," + orderProduct.getLimitid(), orderProduct.getNum());
                } else {
                    num = num.add(orderProduct.getNum());
                    proMap.put(orderProduct.getPdid() + "," + orderProduct.getLimitid(), num);
                }
            }
            //保存总销量
            for (String key : proMap.keySet()) {
                String[] ids = key.split(",");
                LimitTimeProd prod = shopCarService.getLimitTimeProd(Long.parseLong(ids[0]), Long.parseLong(ids[1]));
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
        ordersService.updateReason(orders, "买家取消订单");

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
        //保存用户日志
        memberLogOperator.saveMemberLog(member, null, "订单id：" + id + "取消", "/rest/buyer/orders/cancelOrders", request, memberOperateLogService);

        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("取消订单成功");
        return basicRet;
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
        buyerCapital.setPaytype(order.getPaytype());
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

        //订单资金会在订单完成后才进入冻结金额，所以不需要从卖家冻结金额中扣除
        //卖家从冻结金额中减去退回金额
//        Member seller = memberService.getMemberById(order.getSaleid());
//        Member oldSeller = new Member();
//        BeanUtils.copyProperties(seller,oldSeller);
//        seller.setSellerfreezebanlance(seller.getSellerfreezebanlance().subtract(backPay));
//
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
        return salerCapital;
    }

    /**
     * 获取退货列表
     *
     * @param model
     * @param backQueryParam
     * @param pageNo
     * @param pageSize
     * @return
     */
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
    public PageRet getOrderProductBack(Model model, BackQueryParam backQueryParam, int pageNo, int pageSize) {
        PageRet pageRet = new PageRet();
        pageRet.setMessage("返回成功");
        pageRet.setResult(BasicRet.SUCCESS);
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        backQueryParam.setMemberId(member.getId());
        pageRet.data.setPageInfo(ordersService.getOrderProductBackList(pageNo, pageSize, backQueryParam));
        return pageRet;
    }


    /**
     * 获取未开票订单
     *
     * @param model
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/getNotBillList", method = RequestMethod.POST)
    @ApiOperation(value = "获取未开票列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "页码", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "页数", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "orderNo", value = "订单号", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "startTime", value = "开始时间", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", required = false, paramType = "query", dataType = "string"),
    })
    public PageRet getNotBillList(Model model, int pageNo, int pageSize, BillQueryParam billQueryParam) {
        PageRet pageRet = new PageRet();
        pageRet.setMessage("返回成功");
        pageRet.setResult(BasicRet.SUCCESS);
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        pageRet.data.setPageInfo(ordersService.getNotBillOrders(billQueryParam, member.getId(), pageNo, pageSize));
        return pageRet;
    }

    /**
     * 获取开票列表
     *
     * @param model
     * @param billQueryParam
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/getBillRecordList", method = RequestMethod.POST)
    @ApiOperation(value = "获取开票列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "页码", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "页数", required = true, paramType = "query", dataType = "int"),
    })
    public PageRet getBillRecordList(Model model, BillQueryParam billQueryParam, int pageNo, int pageSize) {
        PageRet pageRet = new PageRet();
        pageRet.setMessage("返回成功");
        pageRet.setResult(BasicRet.SUCCESS);
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        if (member != null) {
            billQueryParam.setMemberid(member.getId());
            pageRet.data.setPageInfo(ordersService.getBillRecordList(billQueryParam, pageNo, pageSize));
            return pageRet;
        } else {
            pageRet.data.setPageInfo(ordersService.getBillRecordList(billQueryParam, pageNo, pageSize));
            return pageRet;
        }
    }

    /**
     * 更新开票申请记录
     *
     * @param billingRecord
     * @return
     */
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
        memberLogOperator.saveMemberLog(member, null, "更新开票申请记录", "/rest/buyer/orders/updateBillRecord", request, memberOperateLogService);
        return basicRet;
    }


    /**
     * 根据记录id获取开票信息
     *
     * @param id
     * @return
     */
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

            billRecordComplex.setReceiveaddress(billingRecord.getReceiveaddress());
            billRecordComplex.setAddress(billingRecord.getAddress());

            String[] ordernoArray = billingRecord.getOrderno().split(",");
            List<Orders> list = new ArrayList<Orders>();
            for (String orderid : ordernoArray) {
                Orders orders = ordersService.getOrdersById(Long.parseLong(orderid));

                //查询是否有退货或退款的，如果有退货开票金额要减去退货的钱
                List<OrderProductBack> orderProductBackList = orderProductBackService.getByOrderNo(orders.getOrderno());
                BigDecimal subApply = new BigDecimal(0);
                for (OrderProductBack opb : orderProductBackList) {
                    if (opb.getState() == 10) {
                        subApply = subApply.add(opb.getBackmoney());
                    }
                }
                if (subApply.compareTo(new BigDecimal(0)) > 0) {
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

    /**
     * 买家申请开票
     *
     * @param model
     * @param billingtype
     * @param orders
     * @param invoiceid
     * @return
     */
    @RequestMapping(value = "/applyBillRecord", method = RequestMethod.POST)
    @ApiOperation(value = "买家申请开票")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "billingtype", value = "发票类型0=纸质1=电子", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "orders", value = "订单id数组", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "invoiceid", value = "开票信息id", required = true, paramType = "query", dataType = "long"),
    })
    public BasicRet applyBillRecord(Model model, Short billingtype, String orders, Long invoiceid, HttpServletRequest request) {
        BasicRet basicRet = new BasicRet();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        List<Orders> list = ordersService.getOrdersByInIds(orders);
        List<BillOrder> billOrders = new ArrayList<>();
        BillingRecord billingRecord = new BillingRecord();
        InvoiceInfo invoiceInfo = ordersService.getInvoiceInfo(invoiceid);
        //创建开票记录
        billingRecord.setOrderuuid(GenerateNo.getUUID());
        billingRecord.setInvoiceheadup(invoiceInfo.getInvoiceheadup());
        billingRecord.setTexno(invoiceInfo.getTexno());
        billingRecord.setBankofaccounts(invoiceInfo.getBankofaccounts());
        billingRecord.setAccount(invoiceInfo.getAccount());
        billingRecord.setPhone(invoiceInfo.getPhone());
        billingRecord.setAddress(invoiceInfo.getAddress());
        billingRecord.setMemberid(member.getId());
        billingRecord.setMembername(member.getUsername());
        billingRecord.setRemark(invoiceInfo.getAvailable());
        billingRecord.setOrderno(orders);
        BigDecimal billCash = BigDecimal.valueOf(0);
        for (Orders orders1 : list) {
            billCash = billCash.add((orders1.getTotalprice().subtract(orders1.getFreight())));
            orders1.setBillingtype(billingtype);
            orders1.setIsbilling(Quantity.STATE_1);
            ordersService.updateSingleOrder(orders1);

            BillOrder billOrder = new BillOrder();
            billOrder.setOrderid(orders1.getId());
            billOrders.add(billOrder);
        }
        billingRecord.setBillcash(billCash);
        billingRecord.setBillingrecordtype(billingtype);
        ordersService.insertBillingRecord(billingRecord);


        for (BillOrder billOrder : billOrders) {
            billOrder.setBillrecordid(billingRecord.getId());
        }
        ordersService.insertAll(billOrders);

        basicRet.setMessage("申请成功");
        basicRet.setResult(BasicRet.SUCCESS);
        //保存用户日志
        memberLogOperator.saveMemberLog(member, null, "申请开票", "/rest/buyer/orders/applyBillRecord", request, memberOperateLogService);
        return basicRet;
    }


    /**
     * 买家个人中心订单各状态数量
     *
     * @param model
     * @param param
     * @return
     */
    @RequestMapping(value = "/getBuyerOrdersStateNum", method = RequestMethod.POST)
    @ApiOperation(value = "买家个人中心订单各状态数量")
    public BasicRet getBuyerOrdersStateNum(Model model, OrderQueryParam param) {
        OrderCarRet basicRet = new OrderCarRet();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        param.setMemberid(member.getId());
        BuyerCenterModel buyerCenterModel = ordersService.getMemberOrdersNum(param);
        basicRet.data.buyerCenterModel = buyerCenterModel;
        basicRet.setMessage("返回成功");
        basicRet.setResult(BasicRet.SUCCESS);
        return basicRet;
    }


    /**
     * 获取操作日志
     *
     * @param orderid
     * @param type
     * @return
     */
    @RequestMapping(value = "/getOrderOperateLog", method = RequestMethod.POST)
    @ApiOperation(value = "获取操作日志")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderid", value = "订单id", required = true, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "type", value = "操作类型0=订单操作1=退货操作", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "orderpdid", value = "订单商品id", required = false, paramType = "query", dataType = "long"),
    })
    public OrderCarRet getOrderOperateLog(Long orderid, Short type, Long orderpdid) {
        OrderCarRet orderCarRet = new OrderCarRet();

        List<OperateLog> list = ordersService.getOperatelog(orderid, type, orderpdid);

        orderCarRet.data.operateLogs = list;
        orderCarRet.setMessage("返回成功");
        orderCarRet.setResult(BasicRet.SUCCESS);
        return orderCarRet;
    }


    @RequestMapping(value = "/getOrderStoreLogAndDevLog", method = RequestMethod.POST)
    @ApiOperation(value = "获取订单仓库及物流跟踪信息")
    @ApiImplicitParams({
    })
    public OrderCarRet getOrderStoreLogAndDevLog(String orderno) {
        OrderCarRet orderCarRet = new OrderCarRet();

        Orders orders = ordersService.getOrdersByOrderNo(orderno);
        List<OperateLog> list = ordersService.getOperatelog(orders.getId(), Quantity.STATE_0, null);

        List<OperateLog> olList = new ArrayList<>();

        List<OperateLog> operateLogfinalList = new ArrayList<>();
        for (OperateLog ol : list) {
            if (!ol.getContent().equals("订单已收货") && !ol.getContent().equals("订单已验货")) {
                olList.add(ol);
            } else {
                operateLogfinalList.add(ol);
            }
        }


        if (orders.getOrderstatus() != 0) {
            try {
                OrderStoreStateLog orderStoreStateLog = orderStoreStateLogService.getStoreState(orders);

                if (orderStoreStateLog != null) {
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


    /**
     * 获取操作日志
     *
     * @param pdid
     * @return
     */
    @RequestMapping(value = "/getSingleProductEva", method = RequestMethod.POST)
    @ApiOperation(value = "获取单个商品评价")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pdid", value = "商品id", required = true, paramType = "query", dataType = "long"),
    })
    public OrderCarRet getSingleProductEva(Long pdid) {
        OrderCarRet orderCarRet = new OrderCarRet();
        EvaPageModel evaPageModel = ordersService.getSingleProductEva(pdid);
        orderCarRet.setMessage("返回成功");
        orderCarRet.setResult(BasicRet.SUCCESS);
        orderCarRet.data.evaPageModel = evaPageModel;
        return orderCarRet;
    }

    /**
     * 获取单个商品评价列表
     *
     * @param pdid
     * @return
     */
    @RequestMapping(value = "/getSingleProductEvaList", method = RequestMethod.POST)
    @ApiOperation(value = "获取单个商品评价列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "页码", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "页数", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pdid", value = "商品id", required = true, paramType = "query", dataType = "string"),
    })
    public PageRet getSingleProductEvaList(int pageNo, int pageSize, Long pdid) {
        PageRet pageRet = new PageRet();
        PageInfo pageInfo = ordersService.getOrderProductByPdNo(pageNo, pageSize, pdid);
        pageRet.data.setPageInfo(pageInfo);
        pageRet.setMessage("返回成功");
        pageRet.setResult(BasicRet.SUCCESS);
        return pageRet;
    }

    @RequestMapping(value = "/getSystemTime", method = RequestMethod.POST)
    @ApiOperation(value = "获取系统时间")
    public Long getSystemTime() {
        return System.currentTimeMillis();
    }


    @RequestMapping(value = "/validateOrderPayFinish", method = RequestMethod.POST)
    @ApiOperation(value = "判断支付是否完成")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orders", value = "订单id{1,2}", required = true, paramType = "query", dataType = "string"),
    })
    public BasicRet validateOrderPayFinish(String orders) {
        BasicRet basicRet = new BasicRet();
        List<Orders> ordersList = ordersService.getOrdersByInIds(orders);
        boolean flag = true;
        for (Orders order : ordersList) {
            //立即发货
            if (order.getOrdertype() == Quantity.STATE_0) {
                if (order.getOrderstatus() == Quantity.STATE_0) {
                    flag = false;
                }
            }
            //远期全款
            if (order.getOrdertype() == Quantity.STATE_1) {
                if (order.getOrderstatus() == Quantity.STATE_0) {
                    flag = false;
                }
            }
            //定金
            if (order.getOrdertype() == Quantity.STATE_2) {
                if (order.getOrderstatus() == Quantity.STATE_0) {
                    flag = false;
                }
            }
            //余款
            if (order.getOrdertype() == Quantity.STATE_3) {
                if (order.getOrderstatus() == Quantity.STATE_9) {
                    flag = false;
                }
            }
        }

        if (flag) {
            basicRet.setMessage("支付已完成");
            basicRet.setResult(BasicRet.SUCCESS);
        } else {
            basicRet.setMessage("支付未完成");
            basicRet.setResult(BasicRet.ERR);
        }
        return basicRet;
    }

    @RequestMapping(value = "/getOrderDetail", method = RequestMethod.POST)
    @ApiOperation(value = "app获取订单详情")
    @ApiImplicitParam(name = "orderNo", value = "订单编号", required = true, paramType = "query", dataType = "string")
    public OrderCarRet getOrderDetail(@RequestParam() String orderNo) {
        OrderCarRet orderCarRet = new OrderCarRet();
        Orders orders = ordersService.getOrdersByOrderNo(orderNo);
        if (orders != null) {
            orderCarRet.setMessage("返回成功");
            orderCarRet.setResult(BasicRet.SUCCESS);
            orderCarRet.data.orders = orders;
            orderCarRet.data.orderProducts = ordersService.getOrderProductByOrderNo(orders.getOrderno());
            if (1 == orders.getIsbilling()) {
                orderCarRet.data.billingRecord = ordersService.getBillingRecordByOrderNo(orders.getOrderno());
            }
            if (StringUtils.hasText(orders.getLogisticscompany()) && StringUtils.hasText(orders.getCouriernumber())) {
                List<String> list = commonDataValueService.getcommonDataValue("物流公司");
                for (String vl : list) {
                    String[] vlStr = vl.split("-");
                    if (orders.getLogisticscompany().equals(vlStr[0])) {
                        //物流查询
                        String url = ExpressUtils.searchkuaiDiInfo(vlStr[1], orders.getCouriernumber());
                        orderCarRet.data.setExpressurl(url);
                        break;
                    }
                }
            }
        } else {
            orderCarRet.setMessage("没有该订单");
            orderCarRet.setResult(BasicRet.ERR);
        }
        return orderCarRet;
    }

    /**
     * 评价接口(多个商品)
     */
    @RequestMapping(value = "/evaProduct/more", method = RequestMethod.POST)
    @ApiOperation(value = "评价接口(多个商品)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "eva2", value = "卖家服务", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "eva3", value = "物流服务", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "evaProductList", value = "商品评价列表Json", required = true, paramType = "query", dataType = "string"),
    })
    public BasicRet evaProductList(Model model,
                                   short eva2,
                                   short eva3,
                                   String evaProductList,
                                   HttpServletRequest request) {
        BasicRet basicRet = new BasicRet();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        List<OrderProductEva> orderProductEvas = new Gson().fromJson(evaProductList, new TypeToken<List<OrderProductEva>>() {
        }.getType());
        for (OrderProductEva orderProductEva : orderProductEvas) {
            OrderProduct orderProduct = ordersService.getOrderProductById(orderProductEva.getId());
            orderProduct.setEva1(orderProductEva.getEva1());
            orderProduct.setEva2(eva2);
            orderProduct.setEva3(eva3);
            orderProduct.setIsanonymous(orderProductEva.getIsanonymous());
            orderProduct.setEvaluatestate(Quantity.STATE_1);
            orderProduct.setBuyersexperience(orderProductEva.getEva());
            orderProduct.setEvatime(new Date());
            orderProduct.setMembername(member.getUsername());
            Member member1 = memberService.getMemberById(orderProduct.getSellerid());
            if (member1 != null) {
                orderProduct.setSellername(member1.getUsername());
                //计算评分保存到卖家
                ordersService.calculateSellerEvaScore(member1, orderProductEva.getEva1(), eva2, eva3);
            }
            ordersService.updateOrderProduct(orderProduct);


            //保存操作日志
            OperateLog operateLog = new OperateLog();
            operateLog.setContent(orderProduct.getPdname() + "商品已评价");
            operateLog.setOpid(member.getId());
            operateLog.setOpname(member.getUsername());
            operateLog.setOptime(new Date());
            operateLog.setOptype(Quantity.STATE_0);
            operateLog.setOrderid(orderProduct.getOrderid());
            operateLog.setOrderno(orderProduct.getOrderno());
            operateLog.setOrderpdid(orderProduct.getId());
            ordersService.saveOperatelog(operateLog);

            //保存用户日志
            memberLogOperator.saveMemberLog(member, null, "提交评价", "/rest/buyer/orders/evaProduct", request, memberOperateLogService);
        }
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("评价成功");
        return basicRet;
    }

    /**
     * 订单商品评价类
     */
    class OrderProductEva {
        long id;//订单商品表id
        short eva1;//宝贝与描述相符打分
        short isanonymous;//是否匿名1=不匿名2=匿名
        String eva;//买家心得

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public short getEva1() {
            return eva1;
        }

        public void setEva1(short eva1) {
            this.eva1 = eva1;
        }

        public short getIsanonymous() {
            return isanonymous;
        }

        public void setIsanonymous(short isanonymous) {
            this.isanonymous = isanonymous;
        }

        public String getEva() {
            return eva;
        }

        public void setEva(String eva) {
            this.eva = eva;
        }
    }

    /**
     * app获取用户余额和授信额度
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/getUserBalanceAndCredit", method = RequestMethod.POST)
    @ApiOperation(value = "app获取用户余额和授信额度")
    public BasicExtRet getUserBalance(Model model) {
        BasicExtRet orderCarRet = new BasicExtRet();
        orderCarRet.setMessage("返回成功");
        orderCarRet.setResult(BasicRet.SUCCESS);
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        member = memberService.getMemberById(member.getId());
        Map<String, String> map = new HashMap<>();
        if (member.getPaypassword() == null || "".equals(member.getPaypassword())) {
            map.put("hasPayPassword", "0");//没有设置支付密码
        } else {
            map.put("hasPayPassword", "1");//设置了支付密码
        }
        if (member.getCreditstate() == 1) {
            map.put("creditState", "1");//授信可用
            map.put("credit", member.getAvailablelimit().toPlainString());
        } else {
            map.put("creditState", "0");//授信不可用
            map.put("credit", "0.00");
        }
        map.put("balance", member.getBalance().toPlainString());//用户余额
        orderCarRet.setData(map);
        return orderCarRet;
    }

    @RequestMapping(value = "/saveBuyerIsDelayDays", method = RequestMethod.POST)
    @ApiOperation(value = "买家是否同意延期")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderid", value = "订单id", required = true, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "ifdelay", value = "是否同意2=同意3=不同意", required = true, paramType = "query", dataType = "int"),
    })
    public BasicRet buyerIsAgreeDalay(Model model, Long orderid, Short ifdelay, HttpServletRequest request) throws CashException {

        BasicRet basicRet = new BasicRet();

        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

        TransactionSetting transactionSetting = transactionSettingService.getTransactionSetting();

        BigDecimal forwardsalesmargin = transactionSetting.getForwardsalesmargin().multiply(new BigDecimal(0.01));

        Orders orders = ordersService.getOrdersById(orderid);

        BigDecimal delayPenalty = orders.getDelaypenalty();

        Member seller = memberService.getMemberById(orders.getSaleid());
        Member oldSeller = new Member();
        BeanUtils.copyProperties(seller, oldSeller);

        Member buyer = memberService.getMemberById(orders.getMemberid());
        Member oldBuyer = new Member();
        BeanUtils.copyProperties(buyer, oldBuyer);

        Integer delaydays = orders.getDelaydays();
        BigDecimal bigDecimalDays = new BigDecimal(delaydays);

        BigDecimal penaltyPay = orders.getDelaypenalty();
        //同意
        if (ifdelay == Quantity.STATE_2) {
            orders.setIfdelay(ifdelay);
            basicRet.setMessage("买家同意延期");

            if (seller.getSellerbanlance().compareTo(delayPenalty) == Quantity.STATE_) {
                basicRet.setMessage("余额小于违约金，不能延期");
                basicRet.setResult(BasicRet.ERR);
                return basicRet;
            }
            buyer.setBalance(buyer.getBalance().add(penaltyPay.multiply(forwardsalesmargin)));
            seller.setSellerbanlance(seller.getSellerbanlance().subtract(penaltyPay));
            ordersService.saveMember(buyer, oldBuyer);
            ordersService.saveMember(seller, oldSeller);

            BuyerCapital buyerCapital = createBuyerPenaltyPay(orders, penaltyPay, new Date(), Quantity.STATE_10, orders.getActualpayment().subtract(orders.getFreight()), "延期发货违约");
            SalerCapital salerCapital = createSalerPenaltyPay(orders, penaltyPay, new Date(), Quantity.STATE_7, orders.getActualpayment().subtract(orders.getFreight()), "延期发货违约");

            ordersService.saveBuyerCapital(buyerCapital);
            ordersService.saveSellerCapital(salerCapital);

            //保存用户日志
            memberLogOperator.saveMemberLog(member, null, "买家同意延期", "/rest/buyer/orders/saveBuyerIsDelayDays", request, memberOperateLogService);

        } else {
            orders.setIfdelay(ifdelay);
            basicRet.setMessage("买家不同意延期");
            //保存用户日志
            memberLogOperator.saveMemberLog(member, null, "买家不同意延期", "/rest/buyer/orders/saveBuyerIsDelayDays", request, memberOperateLogService);
        }

        ordersService.updateSingleOrder(orders);
        basicRet.setResult(BasicRet.SUCCESS);


        return basicRet;
    }

    /**
     * 根据订单编号获取订单
     */
    @RequestMapping(value = "/app/logisticsInfo", method = RequestMethod.POST)
    @ApiOperation(value = "app根据订单编号获取物流信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderNo", value = "订单编号", required = true, paramType = "query", dataType = "string"),
    })
    public BasicExtRet getAppOrdersByOrderNo(String orderNo) {
        BasicExtRet basicExtRet = new BasicExtRet();
        basicExtRet.setMessage("返回成功");
        basicExtRet.setResult(BasicRet.SUCCESS);
        Orders orders = ordersService.getOrdersByOrderNo(orderNo);
        Map<String, String> map = new HashMap<>();
        if (StringUtils.hasText(orders.getLogisticscompany()) && StringUtils.hasText(orders.getCouriernumber())) {
            map.put("hasLogisticsInfo", "true");//是否有物流
            List<String> list = commonDataValueService.getcommonDataValue("物流公司");
            for (String vl : list) {
                String[] vlStr = vl.split("-");
                if (orders.getLogisticscompany().equals(vlStr[0])) {
                    //物流查询
                    map.put("logisiticsCompany", vlStr[1]);//物流公司名
                    map.put("logisiticsNum", orders.getCouriernumber());//物流单号
                    String url = ExpressUtils.searchkuaiDiInfo(vlStr[1], orders.getCouriernumber());
                    map.put("logisticsInfoUrl", url);//物流url
                    break;
                }
            }
        } else {
            map.put("hasLogisticsInfo", "false");
        }
        basicExtRet.setData(map);
        return basicExtRet;
    }

  /*public static void main(String[] args) {

     String url =  ExpressUtils.searchkuaiDiInfo("zhongtong","478939321990");
     System.out.println(url);
  }*/

    /**
     * 买家再次购买
     */
    @RequestMapping(value = "/repurchase", method = RequestMethod.POST)
    @ApiOperation(value = "买家再次购买")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderno", value = "订单编号", required = true, paramType = "query", dataType = "string"),
    })
    public BasicExtRet repurchase(String orderno, HttpServletRequest request, Model model) {
        BasicExtRet basicExtRet = new BasicExtRet();
        basicExtRet.setMessage("返回成功");
        basicExtRet.setResult(BasicRet.SUCCESS);
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        Map<String, Object> repurchaseMap = ordersService.repurchase(orderno, member, request);
        List<String> list = new ArrayList<>();
        List<ShopCar> successfulList = (List<ShopCar>) repurchaseMap.get("successfulList");
        List<String> offErrorList = (List<String>) repurchaseMap.get("offErrorList");
        List<String> lackErrorList = (List<String>) repurchaseMap.get("lackErrorList");//记录库存不足
        basicExtRet.setData(repurchaseMap);
        return basicExtRet;
    }

}
