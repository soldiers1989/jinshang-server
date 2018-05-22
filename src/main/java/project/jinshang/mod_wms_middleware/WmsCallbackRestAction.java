package project.jinshang.mod_wms_middleware;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.swagger.annotations.*;
import mizuki.project.core.restserver.config.BasicRet;
import mizuki.project.core.restserver.config.exception.RestMainException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import project.jinshang.common.bean.MemberLogOperator;
import project.jinshang.common.bean.PageRet;
import project.jinshang.common.constant.Quantity;
import project.jinshang.common.exception.CashException;
import project.jinshang.common.utils.GsonUtils;
import project.jinshang.common.utils.JinshangUtils;
import project.jinshang.mod_activity.bean.LimitTimeProd;
import project.jinshang.mod_activity.bean.LimitTimeStore;
import project.jinshang.mod_admin.mod_transet.bean.TransactionSetting;
import project.jinshang.mod_admin.mod_transet.service.TransactionSettingService;
import project.jinshang.mod_cash.bean.BuyerCapital;
import project.jinshang.mod_cash.bean.SalerCapital;
import project.jinshang.mod_member.bean.Member;
import project.jinshang.mod_member.service.MemberService;
import project.jinshang.mod_pay.bean.Refund;
import project.jinshang.mod_pay.mod_alipay.AlipayService;
import project.jinshang.mod_pay.mod_bankpay.AbcService;
import project.jinshang.mod_pay.mod_wxpay.MyWxPayService;
import project.jinshang.mod_product.bean.*;
import project.jinshang.mod_product.service.*;
import project.jinshang.mod_wms_middleware.bean.GoodsStock;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/rest/callback/wms")
@Api(tags = "wms中间件回调模块")
@Transactional(rollbackFor = Exception.class)
public class WmsCallbackRestAction {
    @Autowired
    private ShopCarService shopCarService;

    @Resource
    OrdersService ordersService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private TransactionSettingService transactionSettingService;

    @Autowired
    private MemberOperateLogService memberOperateLogService;

    @Autowired
    private MyWxPayService wxPayService;
    @Autowired
    private AlipayService alipayService;
    @Autowired
    private AbcService abcService;

    @Autowired
    private WMSService wmsService;

    @Autowired
    private ProductStoreService productStoreService;

    MemberLogOperator memberLogOperator = new MemberLogOperator();

    @RequestMapping(value = "sale", method = RequestMethod.POST)
    @ApiOperation(value = "")
    public BasicRet sale(
            @RequestParam String orderNo,
            @ApiParam(required = true, value = "XTRK:销售退货入库 JYCK:销售出库")
            @RequestParam String moduleCode,
            @ApiParam(value = "物流公司名称")
            @RequestParam(required = false) String logisticsCompanyName,
            @ApiParam(value = "物流单号")
            @RequestParam(required = false) String expressNo,
            @ApiParam(value = "商品编码")
            @RequestParam(required = false) String sku,
            @ApiParam(value = "数量")
            @RequestParam(required = false) String qty
    ) throws RestMainException {
        try {
            if ("XTRK".equals(moduleCode)) {
//                OrderProductBackExample example = new OrderProductBackExample();
//                example.createCriteria().andPdnoEqualTo(sku).andBacknoEqualTo(orderNo);
//                List<OrderProductBack> orderProductBackList = orderProductBackMapper.selectByExample(example);
//                if (orderProductBackList == null || orderProductBackList.size() == 0) {
//                    return new BasicRet(BasicRet.ERR, "退货商品不存在");
//                }
//                OrderProductBack orderProductBack = orderProductBackList.get(0);
//                if (orderProductBack.getState() == 10 || orderProductBack.getState() == 11) {
//                    return new BasicRet(BasicRet.ERR, "orderProductBack状态不对");
//                }
//                OrderProduct orderProduct = wmsService.selectOrderProductByOrderNoAndSku(orderProductBack.getOrderno(), sku);
//                if (orderProduct == null) return new BasicRet(BasicRet.ERR, "商品不存在");
//                if (orderProduct.getBackstate() == 0) return new BasicRet(BasicRet.ERR, "orderProduct状态不对");
//
//
//                orderProductBack.setState((short) 10);
//                orderProductBackMapper.updateByPrimaryKeySelective(orderProductBack);
//                example.clear();
//                example.createCriteria().andOrdernoEqualTo(orderProductBack.getOrderno());
//                List<OrderProductBack> orderProductBackListAll = orderProductBackMapper.selectByExample(example);
//                boolean orderBackSuccess = true;
//                for (OrderProductBack orderProductBack1 : orderProductBackListAll) {
//                    if (orderProductBack1.getState() != 10 && orderProductBack1.getState() != 11) {
//                        orderBackSuccess = false;
//                        break;
//                    }
//                }
//                if (orderBackSuccess) {
//                    orderProductMapper.updateBackStatusById(orderProduct.getId(), 3);
//                }
            } else if ("JYCK".equals(moduleCode)) {
                Orders orders = wmsService.selectOrdersByOrderNo(orderNo);
                if (orders.getOrderstatus() == 0 || orders.getOrderstatus() == 5
                        || orders.getOrderstatus() == 7) {
                    return new BasicRet(BasicRet.ERR, "order状态不对");
                }
                ordersService.sendOutProduct(orders, logisticsCompanyName, expressNo);
            } else {
                return new BasicRet(BasicRet.ERR, "参数错误");
            }
            return new BasicRet(BasicRet.SUCCESS);
        } catch (Exception e) {
            throw new RestMainException(e);
        }
    }

    class WMSOrderDetail {
        private String sku;
        private String qty;

        public String getSku() {
            return sku;
        }

        public void setSku(String sku) {
            this.sku = sku;
        }

        public String getQty() {
            return qty;
        }

        public void setQty(String qty) {
            this.qty = qty;
        }
    }

    @RequestMapping(value = "/orderProductBack/complete", method = RequestMethod.POST)
    @ApiOperation(value = "修改退货申请")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderBackNo", value = "退货订单no", required = true, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "sku", value = "退货商品编码", required = true, paramType = "query", dataType = "long"),
//            @ApiImplicitParam(name = "state", value = "退货状态0=待卖家处理退款1=卖家同意待收货2=卖家同意直接退款3=卖家收到货同意4=卖家不同意5=买家同意验货6=买家申请异议7=平台同意退货不扣违约金8=平台同意退货扣违约金9=平台转入待验收10=退货成功11=撤消", required = false, paramType = "query", dataType = "int"),
//            @ApiImplicitParam(name = "backtype", value = "退货类型0=仅退款1=退货退款2=部分退货", required = true, paramType = "query", dataType = "int"),
    })
    public BasicRet updateOrderProductBack(String orderBackNo, String sku, HttpServletRequest request) throws CashException {
        BasicRet basicRet = new BasicRet();
        ProductBackModel productBackModel = new ProductBackModel();
//        if(state != Quantity.STATE_1 && state != Quantity.STATE_2 && state != Quantity.STATE_3 && state != Quantity.STATE_4){
//            basicRet.setResult(BasicRet.ERR);
//            basicRet.setMessage("操作状态不合法");
//            return  basicRet;
//        }

        List<OrderProductBack> orderProductBackList = wmsService.selectOrderProductBackByOrderBackNo(orderBackNo, sku);
        if (orderProductBackList.isEmpty()) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("不存在该退货订单");
            return basicRet;
        }
        OrderProductBack orderProductBack = orderProductBackList.get(0);
//        orderProductBack.setState(productBackModel.getState());

//        OrderProductBack orderProductBack = ordersService.getBackgoodsOrderProductBack(productBackModel);
//        if(orderProductBack == null){
//            return  new BasicRet(BasicRet.ERR,"未查询到退货商品信息");
//        }


        if (orderProductBack.getState() == Quantity.STATE_11) {
            basicRet.setMessage("退货已撤消");
            basicRet.setResult(BasicRet.ERR);
            return basicRet;
        } else if (orderProductBack.getState() == Quantity.STATE_6) {
            basicRet.setMessage("退货已申请异议");
            basicRet.setResult(BasicRet.ERR);
            return basicRet;
        } else if (orderProductBack.getState() == Quantity.STATE_10) {
            basicRet.setMessage("退货已完成");
            basicRet.setResult(BasicRet.ERR);
            return basicRet;
        } else {
            ProductStore store = null;
            LimitTimeStore limitTimeStore = null;
            LimitTimeProd limitTimeProd = null;
            Orders orders = ordersService.getOrdersById(orderProductBack.getOrderid());
            if (orders.getIsonline() == Quantity.STATE_0) {
                store = ordersService.getProductStore(orderProductBack.getPdid(), orderProductBack.getPdno(), orderProductBack.getStoreid());
            }
            if (orders.getIsonline() == Quantity.STATE_2) {
                limitTimeStore = shopCarService.getLimitTimeStore(orderProductBack.getStoreid());
                limitTimeProd = shopCarService.getLimitTimeProd(orderProductBack.getPdid(), orderProductBack.getLimitid());
            }

            OrderProduct orderProduct = ordersService.getOrderProductById(orderProductBack.getOrderpdid());

            orderProduct.setBackstate(Quantity.STATE_3);
            handleBackGoods(orderProductBack, orderProduct, orders, null, null);
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

            //保存操作日志
            OperateLog operateLog = new OperateLog();
            //货状态0=待卖家处理1=卖家同意待收货2=卖家同意直接退款3=卖家收到货同意退款4=卖家不同意5=买家同意验货6=买家申请异议7=平台同意退货不扣违约金8=平台同意退货扣违约金9=平台转入待验收10=退货成功11=撤消
            operateLog.setOpid(-1L);
            operateLog.setOpname("");
            operateLog.setOptime(new Date());
            operateLog.setOptype(Quantity.STATE_1);
            operateLog.setOrderid(orderProductBack.getOrderid());
            operateLog.setOrderno(orderProductBack.getOrderno());
            operateLog.setOrderpdid(orderProductBack.getOrderpdid());

            operateLog.setContent(JinshangUtils.orderProductBackStateMess(orderProductBack.getState()));
            ordersService.saveOperatelog(operateLog);

            //用户日志
            memberLogOperator.saveMemberLog(null, null, "中间件修改退货申请,退货申请id为：" + "/rest/seller/orders/updateOrderProductBack", request, memberOperateLogService);
            basicRet.setMessage("修改成功");
            basicRet.setResult(BasicRet.SUCCESS);
            return basicRet;
        }
    }

    /*
    @RequestMapping(value = "/productStore/updateStorenum", method = RequestMethod.POST)
    @ApiOperation(value = "同步库存")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pdno", value = "商品编码", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "num", value = "数量", required = true, paramType = "query", dataType = "double"),
    })
    public BasicRet updateProductStoreNum(String pdno, BigDecimal num) {

        int c = productStoreService.updateNumByStoreidAndPdno(pdno, num);

        if (c > 0) {
            return new BasicRet(BasicRet.SUCCESS, "更新成功");
        }

        return new BasicRet(BasicRet.ERR, "未查询到该仓库商品信息");
    }
    */



    @RequestMapping(value = "/productStore/updateStorenum", method = RequestMethod.POST)
    @ApiOperation(value = "同步库存")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "memberid", value = "用户id", required = true, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "pdno", value = "商品编码", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "num", value = "数量", required = true, paramType = "query", dataType = "double"),
    })
    public BasicRet updateProductStoreNum(@RequestParam(required = true) Long memberid,
                                          @RequestParam(required = true) String pdno,
                                          @RequestParam(required = true) BigDecimal num) {

        int c = productStoreService.updateNumByStoreidAndPdno(memberid,pdno, num);

        if (c > 0) {
            return new BasicRet(BasicRet.SUCCESS, "更新成功");
        }

        return new BasicRet(BasicRet.ERR, "未查询到该仓库商品信息");
    }



    @RequestMapping(value = "/productStore/updateStorenum/list", method = RequestMethod.POST)
    @ApiOperation(value = "同步库存")
    @ApiImplicitParam(name = "goodsStockList", value = "商品库存列表", required = true, paramType = "query", dataType = "string")
    public BasicRet updateProductStoreNumList(String goodsStockList) {
        BasicRet basicRet = new BasicRet();
        List<GoodsStock> goodsStocks = new Gson().fromJson(goodsStockList, new TypeToken<List<GoodsStock>>() {
        }.getType());
        List<GoodsStock> errorGoodsStocks = new ArrayList<>();
        for (GoodsStock goodsStock : goodsStocks) {
            int c;
            if ("01".equals(goodsStock.getWarehouseCode())) {
                double num = Double.valueOf(goodsStock.getUqty());
                num = num / 1000;
                c = productStoreService.updateNumByStoreidAndPdno(goodsStock.getSku(), new BigDecimal(String.valueOf(num)));
            } else {
                c = productStoreService.updateNumByStoreidAndPdno(goodsStock.getSku(), new BigDecimal(goodsStock.getUqty()));
            }
            if (c <= 0) {
                errorGoodsStocks.add(goodsStock);
            }
        }
        if (errorGoodsStocks.isEmpty()) {
            basicRet.setResult(BasicRet.SUCCESS);
            basicRet.setMessage("成功");
        } else {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage(new Gson().toJson(errorGoodsStocks));
        }
        return basicRet;
    }


    @PostMapping("/getProdStoreInfo")
    @ApiOperation(value = "查询仓库编码")
    public PageRet getProdStoreInfo(long memberid, int pageNo, int pageSize) {
        PageRet pageRet = new PageRet();
        pageRet.data.setPageInfo(wmsService.getProdStoreInfo(memberid, pageNo, pageSize));
        pageRet.setResult(BasicRet.SUCCESS);
        return pageRet;
    }


    /**
     * 退款操作
     *
     * @param orderProductBack
     * @param orderProduct
     * @param order
     * @param operatorId       操作者id
     * @param operatorUsername 操作者用户名
     * @throws CashException
     */
    private void handleBackGoods(OrderProductBack orderProductBack, OrderProduct orderProduct, Orders order, Long operatorId, String operatorUsername) throws CashException {

        //卖家
        Member sellerMember = memberService.getMemberById(order.getSaleid());
        Member oldSellerMember = new Member();
        BeanUtils.copyProperties(sellerMember, oldSellerMember);

        //退款申请人
        Member buyer = memberService.getMemberById(order.getMemberid());
        Member oldBuyer = new Member();
        BeanUtils.copyProperties(buyer, oldBuyer);


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
                    penaltyPay = orderPay.multiply(getLiquidated).setScale(2, BigDecimal.ROUND_HALF_UP);
                }

                backPay = backPayMoney;

                //判断退回到余额还是授信
                BuyerCapital buyerCapital = ordersService.getBuyerCapitalByNoType(orderProduct.getOrderno(), Quantity.STATE_0);
                if (buyerCapital != null) {
                    //退回到余额
                    if (buyerCapital.getPaytype() == Quantity.STATE_3) {
                        buyer.setBalance(buyer.getBalance().add(backPay.subtract(penaltyPay)).setScale(2, BigDecimal.ROUND_HALF_UP));
                        buyerCapital1 = createBuyerBackPay(order, backPay.subtract(penaltyPay).setScale(2, BigDecimal.ROUND_HALF_UP), tranTime, Quantity.STATE_3);
                        salerCapital1 = createSalerBackPay(order, backPay, tranTime);
                        buyerCapitals.add(buyerCapital1);
                        salerCapitals.add(salerCapital1);
                    }


                    //退回到授信
                    if (buyerCapital.getPaytype() == Quantity.STATE_4) {
                        buyer.setAvailablelimit(buyer.getAvailablelimit().add(backPay.subtract(penaltyPay)).setScale(2, BigDecimal.ROUND_HALF_UP));
                        buyer.setUsedlimit(buyer.getUsedlimit().subtract(backPay.subtract(penaltyPay)));
                        buyerCapital1 = createBuyerBackPay(order, backPay.subtract(penaltyPay).setScale(2, BigDecimal.ROUND_HALF_UP), tranTime, Quantity.STATE_4);
                        salerCapital1 = createSalerBackPay(order, backPay, tranTime);
                        buyerCapitals.add(buyerCapital1);
                        salerCapitals.add(salerCapital1);
                    }


                    //退回到支付宝\微信\银行卡
                    if (buyerCapital.getPaytype() == Quantity.STATE_0 || buyerCapital.getPaytype() == Quantity.STATE_1 || buyerCapital.getPaytype() == Quantity.STATE_2) {
                        String uuid = order.getUuid();
                        Long ordertime = order.getOrdertime();
                        if (uuid != null) {
                            Refund refund = new Refund();
                            refund.setDatetime(ordertime);
                            refund.setOutTradeNo(uuid);
                            if (order.getPaytype() == Quantity.STATE_0) {
                                refund.setChannel("alipay");
                            } else if (order.getPaytype() == Quantity.STATE_1) {
                                refund.setChannel("wxpay");
                            } else {
                                refund.setChannel("bank");
                            }

                            refund.setRefundAmount((backPay.subtract(penaltyPay).multiply(new BigDecimal(100))).setScale(2, BigDecimal.ROUND_HALF_UP).longValue());
                            refund.setRefundReason("订单退款");
                            List<Orders> ordersList = ordersService.getOrdersByUUID(uuid);
                            BigDecimal totalAmout = new BigDecimal(0);
                            for (Orders od : ordersList) {
                                totalAmout = totalAmout.add(od.getActualpayment());
                            }
                            refund.setTotalAmount((totalAmout.multiply(new BigDecimal(100))).setScale(2, BigDecimal.ROUND_HALF_UP).longValue());
                            boolean result = false;
                            try {
                                if ("alipay".equals(refund.getChannel())) {
                                    result = alipayService.refund(refund);
                                } else if ("wxpay".equals(refund.getChannel())) {
                                    result = wxPayService.refund(refund);
                                } else {
                                    result = abcService.refund(refund);
                                }
                            } catch (Exception e) {

                            }

                            System.out.println("退货通道：" + refund.getChannel() + "退货结果：" + result);
                            if (result) {
                                if (order.getPaytype() == Quantity.STATE_0) {
                                    buyerCapital1 = createBuyerBackPay(order, backPay.subtract(penaltyPay).setScale(2, BigDecimal.ROUND_HALF_UP), tranTime, Quantity.STATE_0);
                                } else if (order.getPaytype() == Quantity.STATE_1) {
                                    buyerCapital1 = createBuyerBackPay(order, backPay.subtract(penaltyPay).setScale(2, BigDecimal.ROUND_HALF_UP), tranTime, Quantity.STATE_1);
                                } else {
                                    buyerCapital1 = createBuyerBackPay(order, backPay.subtract(penaltyPay).setScale(2, BigDecimal.ROUND_HALF_UP), tranTime, Quantity.STATE_2);
                                }
                                salerCapital1 = createSalerBackPay(order, backPay, tranTime);
                                buyerCapitals.add(buyerCapital1);
                                salerCapitals.add(salerCapital1);
                            }
                        }
                    }


                    //异议扣违约金,记录资金明细
                    if (orderProductBack.getAdminstate() == Quantity.STATE_2) {
                        BigDecimal sellerPenaltyPay = penaltyPay.multiply(forwardsalesmargin).setScale(2, BigDecimal.ROUND_HALF_UP);
                        if (sellerPenaltyPay.compareTo(Quantity.BIG_DECIMAL_0) > 0) {
                            sellerMember.setSellerbanlance(sellerMember.getSellerbanlance().add(sellerPenaltyPay));
                            salerCapital2 = createSalerPenaltyPay(order, sellerPenaltyPay, tranTime, Quantity.STATE_6, orderPay, Quantity.BUYER_BACK_REASON);
                            salerCapitals.add(salerCapital2);
                        }

                        if (penaltyPay.compareTo(Quantity.BIG_DECIMAL_0) > 0) {
                            buyerCapital2 = createBuyerPenaltyPay(order, penaltyPay, tranTime, Quantity.STATE_6, orderPay, Quantity.BUYER_BACK_REASON);
                            buyerCapital2.setPaytype(order.getPaytype());
                            buyerCapitals.add(buyerCapital2);
                        }
                    }

                    //如果退货类型为全部退货   将运费加到卖家货款金额里
                    if (orderProductBack.getBacktype() == Quantity.STATE_1) {
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
                    penaltyPay = orderPay.multiply(getLiquidated).setScale(2, BigDecimal.ROUND_HALF_UP);
                }

                backPay = orderPay;


                //判断退回到余额还是授信
                BuyerCapital buyerCapital = ordersService.getBuyerCapitalByNoType(orderProduct.getOrderno(), Quantity.STATE_9);
                if (buyerCapital != null) {
                    //退回到余额
                    if (buyerCapital.getPaytype() == Quantity.STATE_3) {
                        buyer.setBalance(buyer.getBalance().add(backPay).subtract(penaltyPay).setScale(2, BigDecimal.ROUND_HALF_UP));
                        buyerCapital1 = createBuyerBackPay(order, backPay.subtract(penaltyPay).setScale(2, BigDecimal.ROUND_HALF_UP), tranTime, Quantity.STATE_3);
                        salerCapital1 = createSalerBackPay(order, backPay, tranTime);
                        buyerCapitals.add(buyerCapital1);
                        salerCapitals.add(salerCapital1);
                    }
                    //退回到授信
                    if (buyerCapital.getPaytype() == Quantity.STATE_4) {
                        buyer.setAvailablelimit(buyer.getAvailablelimit().add(backPay.subtract(penaltyPay)).setScale(2, BigDecimal.ROUND_HALF_UP));
                        buyer.setUsedlimit(buyer.getUsedlimit().subtract(backPay.subtract(penaltyPay)).setScale(2, BigDecimal.ROUND_HALF_UP));
                        buyerCapital1 = createBuyerBackPay(order, backPay.subtract(penaltyPay).setScale(2, BigDecimal.ROUND_HALF_UP), tranTime, Quantity.STATE_4);
                        salerCapital1 = createSalerBackPay(order, backPay, tranTime);
                        buyerCapitals.add(buyerCapital1);
                        salerCapitals.add(salerCapital1);
                    }


                    //退回到支付宝或微信
                    if (buyerCapital.getPaytype() == Quantity.STATE_0 || buyerCapital.getPaytype() == Quantity.STATE_1 || buyerCapital.getPaytype() == Quantity.STATE_2) {
                        String uuid = order.getUuid();
                        Long ordertime = order.getOrdertime();
                        if (uuid != null) {
                            Refund refund = new Refund();
                            refund.setOutTradeNo(uuid);
                            refund.setDatetime(ordertime);
                            if (order.getPaytype() == Quantity.STATE_0) {
                                refund.setChannel("alipay");
                            } else if (order.getPaytype() == Quantity.STATE_1) {
                                refund.setChannel("wxpay");
                            } else {
                                refund.setChannel("bank");
                            }
                            refund.setRefundAmount((backPay.subtract(penaltyPay).setScale(2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100))).longValue());
                            refund.setRefundReason("订单退款");
                            List<Orders> ordersList = ordersService.getOrdersByUUID(uuid);
                            BigDecimal totalAmout = new BigDecimal(0);
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
                                } else {
                                    result = abcService.refund(refund);
                                }
                            } catch (Exception e) {

                            }
                            if (result) {
                                if (order.getPaytype() == Quantity.STATE_0) {
                                    buyerCapital1 = createBuyerBackPay(order, backPay.subtract(penaltyPay).setScale(2, BigDecimal.ROUND_HALF_UP), tranTime, Quantity.STATE_0);
                                } else if (order.getPaytype() == Quantity.STATE_1) {
                                    buyerCapital1 = createBuyerBackPay(order, backPay.subtract(penaltyPay).setScale(2, BigDecimal.ROUND_HALF_UP), tranTime, Quantity.STATE_1);
                                } else {
                                    buyerCapital1 = createBuyerBackPay(order, backPay.subtract(penaltyPay).setScale(2, BigDecimal.ROUND_HALF_UP), tranTime, Quantity.STATE_2);
                                }
                                salerCapital1 = createSalerBackPay(order, backPay, tranTime);
                                buyerCapitals.add(buyerCapital1);
                                salerCapitals.add(salerCapital1);
                            }

                        }
                    }

                    //异议扣违约金,记录资金明细
                    if (orderProductBack.getAdminstate() == Quantity.STATE_2) {
                        BigDecimal sellerPenaltyPay = penaltyPay.multiply(forwardsalesmargin).setScale(2, BigDecimal.ROUND_HALF_UP);
                        if (sellerPenaltyPay.compareTo(Quantity.BIG_DECIMAL_0) > 0) {
                            sellerMember.setSellerbanlance(sellerMember.getSellerbanlance().add(sellerPenaltyPay));
                            salerCapital2 = createSalerPenaltyPay(order, sellerPenaltyPay, tranTime, Quantity.STATE_6, orderPay, Quantity.BUYER_BACK_REASON);
                            salerCapitals.add(salerCapital2);
                        }

                        if (penaltyPay.compareTo(Quantity.BIG_DECIMAL_0) > 0) {
                            buyerCapital2 = createBuyerPenaltyPay(order, penaltyPay, tranTime, Quantity.STATE_6, orderPay, Quantity.BUYER_BACK_REASON);
                            buyerCapital2.setPaytype(order.getPaytype());
                            buyerCapitals.add(buyerCapital2);
                        }
                    }


                    //如果退货类型为全部退货   将运费加到卖家货款金额里
                    if (orderProductBack.getBacktype() == Quantity.STATE_1) {
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
                        partPaypenal = partPay.multiply(getLiquidated).setScale(2, BigDecimal.ROUND_HALF_UP);
                        yuPayPenal = yuPay.multiply(getLiquidated).setScale(2, BigDecimal.ROUND_HALF_UP);
                    }


                    //买家退款金额
                    BigDecimal buyerBackPay = partPay.subtract(partPaypenal).add(yuPay.subtract(yuPayPenal)).setScale(2, BigDecimal.ROUND_HALF_UP);

                    //卖家退款金额
                    BigDecimal salerBackPay = partPay.add(yuPay).setScale(2, BigDecimal.ROUND_HALF_UP);


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
                    if (depositBuyerCapital.getPaytype() == Quantity.STATE_0 || depositBuyerCapital.getPaytype() == Quantity.STATE_1 || depositBuyerCapital.getPaytype() == Quantity.STATE_2) {
                        String uuid = order.getUuid();
                        String yuuuid = order.getYuuuid();
                        Long ordertime = order.getOrdertime();
                        Long yuordertime = order.getYuordertime();
                        if (uuid != null && yuuuid != null && ordertime != null && yuordertime != null) {
                            //定金
                            Refund refund1 = new Refund();
                            //余款
                            Refund refund2 = new Refund();
                            refund1.setOutTradeNo(uuid);
                            refund1.setDatetime(ordertime);
                            refund2.setOutTradeNo(yuuuid);
                            refund2.setDatetime(yuordertime);

                            if (order.getPaytype() == Quantity.STATE_0) {
                                refund1.setChannel("alipay");
                                refund2.setChannel("alipay");
                            } else if (order.getPaytype() == Quantity.STATE_1) {
                                refund1.setChannel("wxpay");
                                refund2.setChannel("wxpay");
                            } else {
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
                                } else {
                                    result1 = abcService.refund(refund1);
                                    result2 = abcService.refund(refund2);
                                }
                            } catch (Exception e) {

                            }
                            if (result1 && result2) {
                                if (order.getPaytype() == Quantity.STATE_0) {
                                    buyerCapital1 = createBuyerBackPay(order, buyerBackPay, tranTime, Quantity.STATE_0);
                                } else if (order.getPaytype() == Quantity.STATE_1) {
                                    buyerCapital1 = createBuyerBackPay(order, buyerBackPay, tranTime, Quantity.STATE_1);
                                } else {
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
                        BigDecimal sellerPenaltyPay = penaltyPay.multiply(forwardsalesmargin).setScale(2, BigDecimal.ROUND_HALF_UP);
                        if (sellerPenaltyPay.compareTo(Quantity.BIG_DECIMAL_0) > 0) {
                            sellerMember.setSellerbanlance(sellerMember.getSellerbanlance().add(sellerPenaltyPay));
                            salerCapital2 = createSalerPenaltyPay(order, sellerPenaltyPay, tranTime, Quantity.STATE_6, orderPay, Quantity.BUYER_BACK_REASON);
                            salerCapitals.add(salerCapital2);
                        }

                        if (penaltyPay.compareTo(Quantity.BIG_DECIMAL_0) > 0) {
                            buyerCapital2 = createBuyerPenaltyPay(order, penaltyPay, tranTime, Quantity.STATE_6, orderPay, Quantity.BUYER_BACK_REASON);
                            buyerCapital2.setPaytype(order.getPaytype());
                            buyerCapitals.add(buyerCapital2);
                        }
                    }

                    //如果退货类型为全部退货   将运费加到卖家货款金额里
                    if (orderProductBack.getBacktype() == Quantity.STATE_1) {
                        sellerMember.setGoodsbanlance(sellerMember.getGoodsbanlance().add(orderProduct.getActualpayment().subtract(partPay).subtract(yuPay)));
                    }
                }
            }


            //保存用户余额和授信
            ordersService.saveMember(buyer, oldBuyer);
            ordersService.saveMember(sellerMember, oldSellerMember);

            if (buyerCapitals.size() > 0) {

                ordersService.insertBuyerCapital(buyerCapitals);
                //保存操作日志
                OperateLog operateLog = new OperateLog();
                operateLog.setContent("退款成功");

                operateLog.setOpid(-1L);
//
                operateLog.setOpname("");

                operateLog.setOptime(new Date());
                operateLog.setOptype(Quantity.STATE_1);
                operateLog.setOrderid(orderProductBack.getOrderid());
                operateLog.setOrderno(orderProductBack.getOrderno());
                operateLog.setOrderpdid(orderProduct.getId());
                ordersService.saveOperatelog(operateLog);
            }

            if (salerCapitals.size() > 0) {
                ordersService.insertSallerCapital(salerCapitals);
            }
        }
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

    @RequestMapping(value = "/reSynSaleOrder", method = RequestMethod.POST)
    public BasicRet reSynSaleOrder(@RequestParam("orderId") long orderId) {
        BasicRet basicRet = new BasicRet();
        Orders orders = ordersService.getOrdersById(orderId);
        if (orders == null) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("没有该订单");
            return basicRet;
        }
        List<Orders> ordersList = new ArrayList<>();
        ordersList.add(orders);
        wmsService.synOrders(ordersList);
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("同步成功");
        return basicRet;
    }
}
