package project.jinshang.mod_wms_middleware;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import mizuki.project.core.restserver.util.JsonUtil;
import org.apache.commons.collections.map.HashedMap;
import net.sf.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import project.jinshang.common.constant.Quantity;
import project.jinshang.common.utils.GsonUtils;
import project.jinshang.common.utils.HttpClientUtils;
import project.jinshang.common.utils.StringUtils;
import project.jinshang.mod_company.BuyerCompanyInfoMapper;
import project.jinshang.mod_company.bean.BuyerCompanyInfo;
import project.jinshang.mod_company.bean.BuyerCompanyInfoExample;
import project.jinshang.mod_company.service.BuyerCompanyService;
import project.jinshang.mod_member.MemberMapper;
import project.jinshang.mod_member.bean.Member;
import project.jinshang.mod_member.service.MemberService;
import project.jinshang.mod_product.*;
import project.jinshang.mod_product.bean.*;
import project.jinshang.mod_wms_middleware.bean.OrderSynLog;
import project.jinshang.mod_wms_middleware.bean.WmsMidMsg;
import project.jinshang.mod_wms_middleware.service.OrderSynLogService;

import java.sql.Timestamp;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * wms 中间件 服务
 */
@Service
@EnableScheduling
public class WMSService {
    private ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private OrderProductMapper orderProductMapper;
    @Autowired
    private WmsMiddlewareMsgMapper wmsMiddlewareMsgMapper;

    @Autowired
    private OrderProductBackMapper orderProductBackMapper;

    @Value("${mod.middleware.serverurl}")
    private String middleware_url;

    @Value("${mod.middleware.aozhanurl}")
    private String aozhan_middleware_url;

    @Autowired
    private ProductInfoMapper productInfoMapper;

    @Autowired
    OrdersMapper ordersMapper;

    @Autowired
    BillingRecordMapper billingRecordMapper;

    @Autowired
    MemberMapper memberMapper;

    @Autowired
    BuyerCompanyInfoMapper buyerCompanyInfoMapper;

    @Autowired
    private BuyerCompanyService buyerCompanyService;

    @Autowired
    private OrderSynLogService orderSynLogService;


    @Autowired
    private MemberService memberService;

    @Value("${shop.orders-syn.id}")
    private String shopSelfSupportid;


    @Value("${shop.aozhan-syn.id}")
    private  String aozhanids;


    @Scheduled(cron = "0 0/1 * * * ?")
    @Profile("pro")
    public void checkUnSend() {
        List<WmsMidMsg> list = wmsMiddlewareMsgMapper.list(0);
        System.out.println(list.size());
        for (WmsMidMsg msg : list) {
            cachedThreadPool.execute(() -> {
                Map<String, Object> ret = HttpClientUtils.post(msg.getUrl(), JsonUtil.toMap(msg.getParams()));
                if (((int) ret.getOrDefault("result", -100)) >= 0) {
                    wmsMiddlewareMsgMapper.del(msg.getId());

                    String ordersJson = (String) GsonUtils.toMap(msg.getParams()).get("saleOrderJsonList");
                    List<HashedMap> orderMap = GsonUtils.toList(ordersJson,HashedMap.class);
                    List<OrderSynLog> synLogs = new ArrayList<>();
                    orderMap.forEach(orders -> {
                        OrderSynLog log = new OrderSynLog();
                        log.setOperatetime(new Date());
                        log.setOrderno(orders.get("orderno").toString());
                        log.setType(Quantity.STATE_1);
                        log.setState(Quantity.STATE_1);
                        synLogs.add(log);
                    });

                    orderSynLogService.batchAdd(synLogs);
                }
            });
        }
    }


    private void send(String url, Map<String, String> params) {
        cachedThreadPool.execute(() -> {

            WmsMidMsg wmsMidMsg = new WmsMidMsg()
                    .setCreateDt(new Timestamp(System.currentTimeMillis()))
                    .setParams(JsonUtil.toJson(params)).setUrl(url);

            String ordersJson =  params.get("saleOrderJsonList");
            List<HashedMap> list = GsonUtils.toList(ordersJson,HashedMap.class);
            List<OrderSynLog> synLogs = new ArrayList<>();
            list.forEach(orders -> {
                OrderSynLog log = new OrderSynLog();
                log.setOperatetime(new Date());
                log.setOrderno(orders.get("orderno").toString());
                log.setType(Quantity.STATE_1);
                synLogs.add(log);
            });


            try {
                Map<String, Object> ret = HttpClientUtils.post(url, params);
                //logger.warn("send middleware ret: " + url + " ; " + ret.toString());
                if (((int) ret.getOrDefault("result", -100)) < 0) {
                    wmsMidMsg.setStatus(0);
                    wmsMiddlewareMsgMapper.save(wmsMidMsg);
                    synLogs.forEach(logs->logs.setState(Quantity.STATE_2));
                }else{
                    synLogs.forEach(logs->logs.setState(Quantity.STATE_1));
                }
            } catch (Exception e) {
                wmsMidMsg.setStatus(0);
                wmsMiddlewareMsgMapper.save(wmsMidMsg);
                synLogs.forEach(logs->logs.setState(Quantity.STATE_2));
                logger.error(e.getMessage(),e);
            }

            orderSynLogService.batchAdd(synLogs);
        });
    }

    /**
     * 同步订单
     */
    public void synOrders(List<Orders> ordersList) {
        //System.out.println("执行订单同步");
        logger.error("执行订单同步");


        List<String> shopSelfSupportIds = Arrays.asList(shopSelfSupportid.split("\\|"));
        List<String> aoZhanidList = Arrays.asList(aozhanids.split("\\|"));
        List<Orders> list = new ArrayList<>();
        List<Orders> aoZhanList = new ArrayList<>();
        String productTypeName = null;
        Map<String, String> params = new HashMap<>();
        for (Orders orders : ordersList) {
            Member buyer = memberService.getMemberById(orders.getMemberid());
            BuyerCompanyInfo buyerCompanyInfo = buyerCompanyService.getBuyerCompanyInfoByMemberId(orders.getMemberid());
            if(buyerCompanyInfo != null){
                orders.setBuyercompanyname(buyerCompanyInfo.getCompanyname());
            }
            orders.setBuyerRealname(buyer.getRealname());
            List<OrderProduct> orderProducts = orderProductMapper.listOrderProductByOrderId(orders.getId());
            if (orderProducts.size() != 0) {
                ProductInfo productInfo = productInfoMapper.selectByPrimaryKey(orderProducts.get(0).getPdid());
                BuyerCompanyInfoExample example = new BuyerCompanyInfoExample();
                example.createCriteria().andMemberidEqualTo(orders.getMemberid());
                List<BuyerCompanyInfo> buyerCompanyInfos = buyerCompanyInfoMapper.selectByExample(example);


                if (shopSelfSupportIds.contains(String.valueOf(orders.getSaleid()))) {
                    params.put("productTypeName", productInfo.getLevel1());
                    orders.setOrderProducts(orderProducts);
                    if (orders.getIsbilling() == 1) {
                        BillingRecordExample billingRecordExample = new BillingRecordExample();
                        billingRecordExample.createCriteria().andOrdernoEqualTo(String.valueOf(orders.getId()));
                        List<BillingRecord> billingRecords = billingRecordMapper.selectByExample(billingRecordExample);
                        if (!billingRecords.isEmpty()) {
                            orders.setInvoiceName(billingRecords.get(0).getInvoiceheadup());
                        } else {
                            if (buyerCompanyInfos.isEmpty()) {
                                orders.setInvoiceName(orders.getShipto());
                            } else {
                                orders.setInvoiceName(buyerCompanyInfos.get(0).getCompanyname());
                            }
                        }
                    } else {
                        if (buyerCompanyInfos.isEmpty()) {
                            orders.setInvoiceName(orders.getShipto());
                        } else {
                            orders.setInvoiceName(buyerCompanyInfos.get(0).getCompanyname());
                        }
                    }
                    logger.error("紧商--："+ JSONArray.fromObject(list));
                    logger.info("紧商--："+JSONArray.fromObject(list));
                    list.add(orders);
                } else if (aoZhanidList.contains(String.valueOf(orders.getSaleid()))) {
                    if (orders.getIsbilling() == 1) {
                        BillingRecordExample billingRecordExample = new BillingRecordExample();
                        billingRecordExample.createCriteria().andOrdernoEqualTo(String.valueOf(orders.getId()));
                        List<BillingRecord> billingRecords = billingRecordMapper.selectByExample(billingRecordExample);
                        if (!billingRecords.isEmpty()) {
                            if(billingRecords.get(0).getInvoiceheadup()!=null||billingRecords.get(0).getInvoiceheadup()!=""){
                                orders.setInvoiceName(billingRecords.get(0).getInvoiceheadup());
                            }
                        } else {
                            if (buyerCompanyInfos.isEmpty()) {
                                orders.setInvoiceName(orders.getShipto());
                            } else {
                                orders.setInvoiceName(buyerCompanyInfos.get(0).getCompanyname());
                            }
                        }
                    } else {
                        if (buyerCompanyInfos.isEmpty()) {
                            orders.setInvoiceName(orders.getShipto());
                        } else {
                            orders.setInvoiceName(buyerCompanyInfos.get(0).getCompanyname());
                        }
                    }

                    logger.error("奥展--："+ JSONArray.fromObject(orderProducts));
                    logger.info("奥展--："+JSONArray.fromObject(orderProducts));
                    orders.setOrderProducts(orderProducts);
                    aoZhanList.add(orders);
                }
            }
        }


        if (!list.isEmpty() && !"".equals(middleware_url)) {

            List<OrderSynLog> synLogs = new ArrayList<>();
            list.forEach(orders -> {
                OrderSynLog log = new OrderSynLog();
                log.setOperatetime(new Date());
                log.setOrderno(orders.getOrderno());
                log.setState(Quantity.STATE_0);
                log.setType(Quantity.STATE_1);
                synLogs.add(log);
            });

            orderSynLogService.batchAdd(synLogs);

            logger.error("紧商中间件："+ JSONArray.fromObject(list));
            logger.info("紧商中间件："+JSONArray.fromObject(list));
            //System.out.println("执行订单jswms同步");
            logger.error("执行订单jswms同步");
            logger.error(JsonUtil.toJson(list));
            params.put("saleOrderJsonList", JsonUtil.toJson(list));
            send(middleware_url + "/jinshang/saleOrder", params);
        }

        //奥展中间件抛接
        if (!aoZhanList.isEmpty() && !"".equals(aozhan_middleware_url)) {
            logger.error("奥展中间件"+JSONArray.fromObject(aoZhanList));
            logger.info("奥展中间件"+JSONArray.fromObject(aoZhanList));

            List<OrderSynLog> synLogs = new ArrayList<>();
            aoZhanList.forEach(orders -> {
                OrderSynLog log = new OrderSynLog();
                log.setOperatetime(new Date());
                log.setOrderno(orders.getOrderno());
                log.setState(Quantity.STATE_0);
                log.setType(Quantity.STATE_1);
                synLogs.add(log);
            });

            orderSynLogService.batchAdd(synLogs);

            try {
                params.put("saleOrderJsonList", JsonUtil.toJson(aoZhanList));
                send(aozhan_middleware_url + "/aoZhan/saleOrder", params);
                logger.error(JsonUtil.toJson(aoZhanList));

            }catch (Exception e){
                logger.error("向奥展中间件抛转订单异常信息"+ e.getMessage()+"\n\r"+e.getStackTrace().toString());
            }

        }
    }

    public static final String CANCEL_ORDER_TYPE = "JYCK";//销售订单
    public static final String CANCEL_BACK_ORDER_TYPE = "XTRK";//销售退货

    /**
     * 取消订单 销售/退货订单
     */
    public void cancelOrders(Orders orders, String type) {
        List<String> shopSelfSupportIds = Arrays.asList(shopSelfSupportid.split("\\|"));
        List<String> aozhanIdsList = Arrays.asList(aozhanids.split("\\|"));
        if (shopSelfSupportIds.contains(String.valueOf(orders.getSaleid())) && !"".equals(middleware_url)) {
            Map<String, String> params = new HashMap<>();
            params.put("orderNo", orders.getOrderno());
            params.put("orderType", type);
            send(middleware_url + "/jinshang/cancel", params);
        }else if(aozhanIdsList.contains(String.valueOf(orders.getSaleid())) && !"".equals(aozhan_middleware_url)){
            logger.error("奥展订单取消地址指向："+aozhan_middleware_url + "/aoZhan/cancel");
            logger.info("奥展订单取消地址指向："+aozhan_middleware_url + "/aoZhan/cancel");
            logger.error("奥展订单取消传递参数信息：orderNo"+orders.getOrderno()+"******orderType"+type);
            logger.info("奥展订单取消传递参数信息：orderNo"+orders.getOrderno()+"******orderType"+type);
            Map<String, String> params = new HashMap<>();
            params.put("orderNo", orders.getOrderno());
            params.put("orderType", type);
            send(aozhan_middleware_url + "/aoZhan/cancel", params);

        }
    }

    /**
     * 退货
     */
    public void backOrders(OrderProductBack orderProductBack) {
        List<String> shopSelfSupportIds = Arrays.asList(shopSelfSupportid.split("\\|"));
        List<String> aozhanIdsList = Arrays.asList(aozhanids.split("\\|"));

        ProductInfo productInfo = productInfoMapper.selectByPrimaryKey(orderProductBack.getPdid());
        logger.error("中间件退货所传递的参数："+JsonUtil.toJson(orderProductBack).toString());
        Orders orders = ordersMapper.selectByPrimaryKey(orderProductBack.getOrderid());
        if (shopSelfSupportIds.contains(String.valueOf(orders.getSaleid()))  && !"".equals(middleware_url)) {
            Map<String, String> params = new HashMap<>();
            params.put("saleOrderBackJson", JsonUtil.toJson(orders));
            params.put("saleOrderBackProductJson", JsonUtil.toJson(orderProductBack));
            params.put("productTypeName", productInfo.getLevel1());
            send(middleware_url + "/jinshang/saleOrderBack", params);
        }else if(aozhanIdsList.contains(String.valueOf(orders.getSaleid())) && !"".equals(aozhan_middleware_url)){
            Map<String, String> params = new HashMap<>();
//            params.put("saleOrderBackJson", JsonUtil.toJson(orders));
            params.put("saleOrderBackJsonList", JsonUtil.toJson(orderProductBack));
          //  logger.error("平台奥展中间件退货传递参数："+JSONArray.fromObject(orderProductBack));
           // logger.error("平台奥展中间件退货地址指向："+aozhan_middleware_url + "/aoZhan/saleOrderBack");
           // logger.info("平台奥展中间件退货传递参数："+JSONArray.fromObject(orderProductBack));
           // logger.info("平台奥展中间件退货地址指向："+aozhan_middleware_url + "/aoZhan/saleOrderBack");
            send(aozhan_middleware_url + "/aoZhan/saleOrderBack", params);
        }
    }

    public Orders selectOrdersByOrderNo(String orderNo) {
        return ordersMapper.findByNo(orderNo);
    }

    public OrderProduct selectOrderProductByOrderNoAndSku(String orderNo, String sku) {
        return orderProductMapper.findByOrderNoAndPdno(orderNo, sku);
    }

    public List<OrderProductBack> selectOrderProductBackByOrderBackNo(String orderBackNo, String sku) {
        OrderProductBackExample example = new OrderProductBackExample();
        example.createCriteria().andBacknoEqualTo(orderBackNo).andPdnoEqualTo(sku);
        return orderProductBackMapper.selectByExample(example);
    }



    public PageInfo getProdStoreInfo(long memberid, int pageNo, int pageSize){
        PageHelper.startPage(pageNo,pageSize);
        List<Map<String,Object>> list = wmsMiddlewareMsgMapper.getProdStoreInfo(memberid);
        return  new PageInfo(list);
    }
}
