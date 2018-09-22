package project.jinshang.mod_product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.common.constant.Quantity;
import project.jinshang.common.exception.CashException;
import project.jinshang.common.exception.MyException;
import project.jinshang.mod_activity.service.LimitTimeProdService;
import project.jinshang.mod_activity.service.LimitTimeStoreService;
import project.jinshang.mod_admin.mod_commondata.service.CommonDataValueService;
import project.jinshang.mod_admin.mod_inte.bean.IntegralRecord;
import project.jinshang.mod_admin.mod_inte.bean.IntegralSet;
import project.jinshang.mod_admin.mod_inte.service.IntegralService;
import project.jinshang.mod_admin.mod_transet.service.TransactionSettingService;
import project.jinshang.mod_company.service.SellerCompanyInfoService;
import project.jinshang.mod_member.bean.Member;
import project.jinshang.mod_member.bean.SellerCategory;
import project.jinshang.mod_member.service.MemberRateSettingService;
import project.jinshang.mod_member.service.MemberService;
import project.jinshang.mod_pay.mod_alipay.AlipayService;
import project.jinshang.mod_pay.mod_bankpay.AbcService;
import project.jinshang.mod_pay.mod_wxpay.MyWxPayService;
import project.jinshang.mod_pay.service.TradeService;
import project.jinshang.mod_product.bean.*;
import project.jinshang.mod_shippingaddress.service.ShippingAddressService;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 处理分单兼容老数据的service
 * create : wyh
 * date : 2018/7/26
 */

@Service
public class CompatibleFreightOrdersService {
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
    @Autowired
    private TradeService tradeService;
    @Autowired
    private OrdersSplitService ordersSplitService;
    @Autowired
    private ShippingTemplateGroupService shippingTemplateGroupService;
    @Autowired
    private ProductInfoService productInfoService;


    /**
     * 处理之前运费放在订单商品表中的数据
     * @param orders
     * @param list
     * @param member
     * @throws CashException
     * @throws MyException
     */
    public void clOldFinshOrders(Orders orders, List<OrderProduct> list, Member member) throws CashException, MyException {
        //实际支付的总额
        BigDecimal sellerpay = BigDecimal.valueOf(0);

        //运费
        BigDecimal frigthpay = BigDecimal.valueOf(0);

        //总的佣金
        BigDecimal totalBroke = BigDecimal.valueOf(0);

        //服务费
        BigDecimal totalServerPay = BigDecimal.valueOf(0);

        //所有商品总金额
        BigDecimal allPdPay = BigDecimal.valueOf(0);

        //获取紧固件积分规则
        IntegralSet integralSet1 = integralService.getIntegralSetByType(Quantity.STATE_0);
        //获取其它积分规则
        IntegralSet integralSet2 = integralService.getIntegralSetByType(Quantity.STATE_1);
        //销量统计
        Map<Long, BigDecimal> pdsaleNumMap = new HashMap<>();
        for (OrderProduct orderProduct : list) {
            //退货状态0=正常1=退货中2=退货验收3=退货完成4=异议中
            if (orderProduct.getBackstate() == Quantity.STATE_1 || orderProduct.getBackstate() == Quantity.STATE_2 || orderProduct.getBackstate() == Quantity.STATE_4) {
                throw  new MyException("有商品还在退货中，不能结束订单");
            }
            if (orderProduct.getBackstate() == Quantity.STATE_0) {

                BigDecimal saleNum = pdsaleNumMap.get(orderProduct.getPdid());

                if (saleNum == null) {
                    pdsaleNumMap.put(orderProduct.getPdid(), orderProduct.getNum());
                } else {
                    pdsaleNumMap.put(orderProduct.getPdid(), orderProduct.getNum().add(saleNum));
                }

                allPdPay = allPdPay.add(orderProduct.getActualpayment().subtract(orderProduct.getFreight()));

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
                    if(orders.getReceivingaddress().indexOf("党湾镇")==-1) {
                        totalServerPay = totalServerPay.add(servepay);
                    }
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

            memberService.updateIntegralInDb(member.getId(),integralValue,integralValue);
        }




        if (orders.getIsbilling() == Quantity.STATE_1) {  //提交订单时选择开票  将billingrecord表中的state 字段由-1更改为0（待开票状态）
            //并且要查询该订单中是否有退款的，如果有开票金额减去退款金额
            //查询是否有退货或退款的，如果有退货开票金额要减去退货的钱
            List<OrderProductBack> orderProductBackList = orderProductBackService.getByOrderNo(orders.getOrderno());
            BigDecimal subApply = new BigDecimal(0);
            for (OrderProductBack opb : orderProductBackList) {
                if (opb.getState() == 10) {
                    subApply = subApply.add(opb.getBackmoney());
                }
            }

            billingRecordService.updateForwordBillingState(orders.getId().toString(), orders.getMemberid(), subApply.multiply(new BigDecimal(-1)));
        }

        Member seller = memberService.getMemberById(orders.getSaleid());
        BigDecimal frozepay = sellerpay.setScale(2, BigDecimal.ROUND_HALF_UP);
        if (seller != null) {
            //增加卖家冻结金额
            memberService.updateSellerMemberBalanceInDb(seller.getId(), Quantity.BIG_DECIMAL_0, frozepay);
        }
        orders.setBrokepay(totalBroke.setScale(2,BigDecimal.ROUND_HALF_UP));
        orders.setFrozepay(frozepay);
        orders.setServerpay(totalServerPay.setScale(2,BigDecimal.ROUND_HALF_UP));

        //订单验货完成需要更新的数据
        int count = ordersService.updateOrdersConfirmgoods(orders);
        if (count != 1) {
            throw new CashException("买家订单自动确认验货出现错误，可能出现并发更新问题，所有操作已回撤，订单id:" + orders.getId());
        }
    }


    /**
     * 处理分单上线后的新订单数据
     * @param orders
     * @param list
     * @param member
     * @throws CashException
     * @throws MyException
     */
    public void clNewFinshOrders(Orders orders, List<OrderProduct> list,Member member) throws CashException, MyException {
        //订单总金额
        BigDecimal totalprice = Quantity.BIG_DECIMAL_0;

        //运费
        BigDecimal frigthpay = orders.getFreight();

        //总的佣金
        BigDecimal totalBroke = BigDecimal.valueOf(0);

        //服务费
        BigDecimal totalServerPay = BigDecimal.valueOf(0);

        //所有商品金额（不包含退货商品）
        BigDecimal allPdPay = BigDecimal.valueOf(0);

        //获取紧固件积分规则
        IntegralSet integralSet1 = integralService.getIntegralSetByType(Quantity.STATE_0);
        //获取其它积分规则
        IntegralSet integralSet2 = integralService.getIntegralSetByType(Quantity.STATE_1);
        //销量统计
        Map<Long, BigDecimal> pdsaleNumMap = new HashMap<>();
        for (OrderProduct orderProduct : list) {
            //退货状态0=正常1=退货中2=退货验收3=退货完成4=异议中
            if (orderProduct.getBackstate() == Quantity.STATE_1 || orderProduct.getBackstate() == Quantity.STATE_2 ||
                    orderProduct.getBackstate() == Quantity.STATE_4 || orderProduct.getBackstate() == Quantity.STATE_5) {
                throw  new MyException("有商品还在退货中，不能结束订单");
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


                    BigDecimal servepay = (orderProduct.getActualpayment()).multiply(serverRate);
                    totalBroke = totalBroke.add(broker);
                    if(orders.getReceivingaddress().indexOf("党湾镇")==-1) {
                        totalServerPay = totalServerPay.add(servepay);
                    }
                }

                //sellerpay = sellerpay.add(orderProduct.getActualpayment());

                allPdPay = allPdPay.add(orderProduct.getActualpayment());

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

            memberService.updateIntegralInDb(member.getId(),integralValue,integralValue);
        }




        if (orders.getIsbilling() == Quantity.STATE_1) {  //提交订单时选择开票  将billingrecord表中的state 字段由-1更改为0（待开票状态）
            //并且要查询该订单中是否有退款的，如果有开票金额减去退款金额
            //查询是否有退货或退款的，如果有退货开票金额要减去退货的钱
            List<OrderProductBack> orderProductBackList = orderProductBackService.getByOrderNo(orders.getOrderno());
            BigDecimal subApply = new BigDecimal(0);
            for (OrderProductBack opb : orderProductBackList) {
                if (opb.getState() == 10) {
                    subApply = subApply.add(opb.getBackmoney());
                }
            }
            billingRecordService.updateForwordBillingState(orders.getId().toString(), orders.getMemberid(), subApply.multiply(new BigDecimal(-1)));
        }

        Member seller = memberService.getMemberById(orders.getSaleid());
        totalBroke = totalBroke.setScale(2,BigDecimal.ROUND_HALF_UP);
        BigDecimal frozepay = allPdPay.add(orders.getFreight()).setScale(2, BigDecimal.ROUND_HALF_UP);
        if (seller != null) {
            //增加卖家冻结金额
            memberService.updateSellerMemberBalanceInDb(seller.getId(), Quantity.BIG_DECIMAL_0, frozepay);
        }
        orders.setBrokepay(totalBroke.setScale(2,BigDecimal.ROUND_HALF_UP));
        orders.setFrozepay(frozepay);
        orders.setServerpay(totalServerPay.setScale(2,BigDecimal.ROUND_HALF_UP));

        //订单验货完成需要更新的数据
        int count = ordersService.updateOrdersConfirmgoods(orders);
        if (count != 1) {
            throw new CashException("买家订单自动确认验货出现错误，可能出现并发更新问题，所有操作已回撤，订单id:" + orders.getId());
        }
    }



}
