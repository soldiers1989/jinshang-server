package project.jinshang.mod_product.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.netflix.discovery.converters.Auto;
import mizuki.project.core.restserver.config.BasicRet;
import mizuki.project.core.restserver.util.JsonUtil;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import project.jinshang.common.bean.MemberLogOperator;
import project.jinshang.common.constant.AgentDeliveryAddressConst;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.common.constant.Quantity;
import project.jinshang.common.exception.CashException;
import project.jinshang.common.utils.*;
import project.jinshang.mod_activity.bean.LimitTimeProd;
import project.jinshang.mod_activity.bean.LimitTimeStore;
import project.jinshang.mod_admin.mod_count.bean.OrderComplex;
import project.jinshang.mod_admin.mod_count.bean.OrderStatisticModel;
import project.jinshang.mod_admin.mod_inte.IntegralRecordMapper;
import project.jinshang.mod_admin.mod_inte.IntegralSetMapper;
import project.jinshang.mod_admin.mod_inte.bean.IntegralRecord;
import project.jinshang.mod_admin.mod_inte.bean.IntegralSet;
import project.jinshang.mod_admin.mod_inte.bean.IntegralSetExample;
import project.jinshang.mod_admin.mod_transet.bean.TransactionSetting;
import project.jinshang.mod_admin.mod_transet.service.TransactionSettingService;
import project.jinshang.mod_cash.BuyerCapitalMapper;
import project.jinshang.mod_cash.SalerCapitalMapper;
import project.jinshang.mod_cash.bean.BuyerCapital;
import project.jinshang.mod_cash.bean.BuyerCapitalExample;
import project.jinshang.mod_cash.bean.SalerCapital;
import project.jinshang.mod_common.SmsLogMapper;
import project.jinshang.mod_common.bean.SmsLog;
import project.jinshang.mod_common.bean.SmsTemplate;
import project.jinshang.mod_common.service.SmsTemplateService;
import project.jinshang.mod_company.SellerCompanyInfoMapper;
import project.jinshang.mod_company.StoreMapper;
import project.jinshang.mod_company.bean.SellerCompanyInfo;
import project.jinshang.mod_company.bean.SellerCompanyInfoExample;
import project.jinshang.mod_company.bean.Store;
import project.jinshang.mod_company.service.SellerCompanyCacheService;
import project.jinshang.mod_invoice.InvoiceInfoMapper;
import project.jinshang.mod_invoice.bean.InvoiceInfo;
import project.jinshang.mod_member.MemberMapper;
import project.jinshang.mod_member.SellerCategoryMapper;
import project.jinshang.mod_member.bean.*;
import project.jinshang.mod_member.service.ApiRecordService;
import project.jinshang.mod_member.service.MemberRateSettingService;
import project.jinshang.mod_member.service.MemberService;
import project.jinshang.mod_pay.bean.Refund;
import project.jinshang.mod_product.*;
import project.jinshang.mod_product.bean.*;
import project.jinshang.mod_shippingaddress.ShippingAddressMapper;
import project.jinshang.mod_shippingaddress.bean.ShippingAddress;
import project.jinshang.mod_shippingaddress.bean.ShippingAddressExample;
import project.jinshang.mod_wms_middleware.WMSService;

import javax.persistence.Basic;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 2017/11/15.
 */
@Service
public class OrdersService {

    private ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private ShippingAddressMapper shippingAddressMapper;

    @Autowired
    private BillingRecordMapper billingRecordMapper;

    @Autowired
    private OrderProductMapper orderProductMapper;

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private BuyerCapitalMapper buyerCapitalMapper;

    @Autowired
    private SellerCompanyInfoMapper sellerCompanyInfoMapper;

    @Autowired
    private ProductStoreMapper productStoreMapper;

    @Autowired
    private ShopCarMapper shopCarMapper;

    @Autowired
    private ProductInfoMapper productInfoMapper;

    @Autowired
    private StoreMapper storeMapper;

    @Autowired
    private ShippingTemplatesMapper shippingTemplatesMapper;

    @Autowired
    private AreaCostMapper areaCostMapper;

    @Autowired
    private OrderProductBackMapper orderProductBackMapper;

    @Autowired
    private InvoiceInfoMapper invoiceInfoMapper;

    @Autowired
    private SalerCapitalMapper salerCapitalMapper;

    @Autowired
    private SellerBillRecordMapper sellerBillRecordMapper;

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Autowired
    private BillTypeMapper billTypeMapper;

    @Autowired
    private IntegralRecordMapper integralRecordMapper;

    @Autowired
    private IntegralSetMapper integralSetMapper;


    @Autowired
    private MemberOperateLogMapper memberOperateLogMapper;

    @Autowired
    private BillOrderMapper billOrderMapper;

    @Autowired
    private CategoriesMapper categoriesMapper;

    @Autowired
    private TransactionSettingService transactionSettingService;

    @Autowired
    private SellerCategoryMapper sellerCategoryMapper;
    @Autowired
    private  ProductAttrMapper productAttrMapper;
    @Autowired
    private WMSService wmsService;

    @Autowired
    private MemberService memberService;


    @Autowired
    private ProductStoreService productStoreService;


    @Autowired
    private CategoriesService categoriesService;


    @Autowired
    private OrderProductServices orderProductServices;
    @Autowired
    private ShopCarService shopCarService;
    @Autowired
    private MemberOperateLogService memberOperateLogService;
    @Autowired
    private MemberRateSettingService memberRateSettingService;
    @Autowired
    public ApiRecordService apiRecordService;
    //远期全款打折率
    private static final BigDecimal allPayRate = new BigDecimal(0.99);
    MemberLogOperator memberLogOperator = new MemberLogOperator();
    public void saveBuyerCapital(BuyerCapital buyerCapital) {
        buyerCapitalMapper.insertSelective(buyerCapital);
    }

    public void saveSellerCapital(SalerCapital salerCapital) {
        salerCapitalMapper.insertSelective(salerCapital);
    }


    public List<Orders> getOrdersByUUID(String outTradeNo) {
        OrdersExample ordersExample = new OrdersExample();
        ordersExample.createCriteria().andUuidEqualTo(outTradeNo);
        return ordersMapper.selectByExample(ordersExample);
    }


    public Categories getCategories(Long id) {
        return categoriesMapper.selectByPrimaryKey(id);
    }

    public SellerCategory getSellerCategory(Long categoryId, Long sellerid) {
        SellerCategoryExample sellerCategoryExample = new SellerCategoryExample();
        sellerCategoryExample.createCriteria().andCategoryidEqualTo(categoryId).andSelleridEqualTo(sellerid);
        List<SellerCategory> list = sellerCategoryMapper.selectByExample(sellerCategoryExample);
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    /**
     * 批量保存
     *
     * @param list
     */
    public void insertAll(List<BillOrder> list) {
        billOrderMapper.insertAll(list);
    }

    public void saveBillOrder(BillOrder billOrder) {
        billOrderMapper.insertSelective(billOrder);
    }

    /**
     * 获取卖家冻结金额到期资金
     *
     * @param intervalday 订单验收时间加多少天
     * @param nowtime     当前时间
     * @return
     */
    public List<SellerSettleModel> getSellerSettleList(String intervalday, Date nowtime) {
        return ordersMapper.getSellerSettleList(intervalday, nowtime);
    }


    /**
     * 获取可以结算的订单
     *
     * @param intervalday
     * @param nowtime
     * @return
     */
//    public List<Orders> getSellerSettleOrdersList(String intervalday, Date nowtime) {
//        return ordersMapper.getSellerSettleOrdersList(intervalday, nowtime);
//    }


    /**
     * 获取可以结算的订单
     * @return
     */
    public List<Orders> getSellerSettleOrdersList() {
        return ordersMapper.getSellerSettleOrdersList();
    }




    /**
     * 获取卖家冻结金额到期资金列表
     *
     * @param intervalday 订单验收时间加多少天
     * @param nowtime     当前时间
     * @return
     */
    public List<SalerCapital> getSalerCapitalList(String intervalday, Date nowtime) {
        return ordersMapper.getSalerCapitalList(intervalday, nowtime);
    }

    public void updateSalerCapital(SalerCapital salerCapital) {
        salerCapitalMapper.updateByPrimaryKeySelective(salerCapital);
    }


    public void deleteOrderProduct(Long id) {
        orderProductMapper.deleteByPrimaryKey(id);
    }

    /**
     * 获取操作日志
     *
     * @param pageNo
     * @param pageSize
     * @param param
     * @return
     */
    public PageInfo getMemberOperateLogList(int pageNo, int pageSize, MemberQueryParam param) {
        PageHelper.startPage(pageNo, pageSize).setOrderBy("createtime desc");
        MemberOperateLogExample memberOperateLogExample = new MemberOperateLogExample();

        MemberOperateLogExample.Criteria criteria = memberOperateLogExample.createCriteria();
        if (StringUtils.hasText(param.getIpaddress())) {
            String ipaddress = "%" + param.getIpaddress() + "%";
            criteria.andIpaddressLike(ipaddress);
        }
        if (StringUtils.hasText(param.getContent())) {
            String content = "%" + param.getContent() + "%";
            criteria.andContentLike(content);
        }
        if (StringUtils.hasText(param.getMembername())) {
            String memberName = "%" + param.getMembername() + "%";
            criteria.andMembernameLike(memberName);
        }
        if (param.getMemberid() != null) {
            criteria.andMemberidEqualTo(param.getMemberid());
        }
        if (param.getStartTime() != null) {
            criteria.andCreatetimeGreaterThanOrEqualTo(param.getStartTime());
        }
        if (param.getEndTime() != null) {
            Date endTime = param.getEndTime();
            Calendar c = Calendar.getInstance();
            c.setTime(endTime);
            c.add(Calendar.DAY_OF_MONTH, 1);// 今天+1天
            Date tomorrow = c.getTime();
            criteria.andCreatetimeLessThanOrEqualTo(tomorrow);
        }

        PageInfo pageInfo = new PageInfo(memberOperateLogMapper.selectByExample(memberOperateLogExample));
        return pageInfo;
    }

    /**
     * 保存积分
     *
     * @param record
     */
    public void saveIntegralRecord(IntegralRecord record) {
        integralRecordMapper.insertSelective(record);
    }

    /**
     * 获取紧固件积分规则
     *
     * @return
     */
    public IntegralSet getIntegralSet1() {
        IntegralSetExample integralSetExample = new IntegralSetExample();
        integralSetExample.createCriteria().andTypeEqualTo(Quantity.STATE_0);
        List<IntegralSet> list = integralSetMapper.selectByExample(integralSetExample);
        if (list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    /**
     * 获取其它积分规则
     *
     * @return
     */
    public IntegralSet getIntegralSet2() {
        IntegralSetExample integralSetExample = new IntegralSetExample();
        integralSetExample.createCriteria().andTypeEqualTo(Quantity.STATE_1);
        List<IntegralSet> list = integralSetMapper.selectByExample(integralSetExample);
        if (list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    /**
     * 保存发票类型
     *
     * @param billType
     */
    public void saveBillType(BillType billType) {
        billTypeMapper.insertSelective(billType);
    }

    /**
     * 获取所有发票类型
     *
     * @return
     */
    public List<BillType> getBillTypeList() {
        return billTypeMapper.selectByExample(new BillTypeExample());
    }

    /**
     * 删除发票类型
     *
     * @param id
     */
    public void deleteBillTyep(Integer id) {
        billTypeMapper.deleteByPrimaryKey(id);
    }

    /**
     * 保存操作日志
     *
     * @param operateLog
     */
    public void saveOperatelog(OperateLog operateLog) {
        operateLogMapper.insertSelective(operateLog);
    }

    /**
     * 获限操作日志
     *
     * @param orderid
     * @param type
     * @return
     */
    public List<OperateLog> getOperatelog(Long orderid, Short type, Long orderpdid) {
        OperateLogExample operateLogExample = new OperateLogExample();
        OperateLogExample.Criteria criteria = operateLogExample.createCriteria();
        criteria.andOptypeEqualTo(type);
        if (orderid != null) {
            criteria.andOrderidEqualTo(orderid);
        }
        if (orderpdid != null) {
            criteria.andOrderpdidEqualTo(orderpdid);
        }
        operateLogExample.setOrderByClause(" id asc ");
        return operateLogMapper.selectByExample(operateLogExample);
    }


    /**
     * 修改卖家开票记录
     *
     * @param sellerBillRecord
     */
    public void updateSellerBillRecord(SellerBillRecord sellerBillRecord) {
        sellerBillRecordMapper.updateByPrimaryKeySelective(sellerBillRecord);
    }


    /**
     * 删除卖家开票记录
     *
     * @param id
     */
    public void deleteSellerBillRecord(Long id) {
        sellerBillRecordMapper.deleteByPrimaryKey(id);
    }


    /**
     * 保存卖家开票记录
     *
     * @param sellerBillRecord
     */
    public void saveSellerBillRecord(SellerBillRecord sellerBillRecord) {
        sellerBillRecordMapper.insertSelective(sellerBillRecord);
    }

    /**
     * 获取卖家开票记录列表
     *
     * @param pageNo
     * @param pageSize
     * @param param
     * @param member
     * @return
     */
    public PageInfo getSellerBillRecord(int pageNo, int pageSize, SellerBillRecordQueryParam param, Member member) {

        PageHelper.startPage(pageNo, pageSize).setOrderBy("id desc");

        SellerBillRecordExample sellerBillRecordExample = new SellerBillRecordExample();
        SellerBillRecordExample.Criteria criteria = sellerBillRecordExample.createCriteria();
        if (member != null) {
            criteria.andSelleridEqualTo(member.getId());
        }
        if (StringUtils.hasText(param.getDiliveryno())) {
            String deliverNo = "%" + param.getDiliveryno() + "%";
            criteria.andDiliverynoLike(deliverNo);
        }

        if (StringUtils.hasText(param.getBillno())) {
            String billNo = "%" + param.getBillno() + "%";
            criteria.andBillnoLike(billNo);

        }
        if (StringUtils.hasText(param.getSellername())) {
            String sellerName = "%" + param.getSellername() + "%";
            criteria.andSellernameLike(sellerName);
        }
        if (param.getSellerid() != null) {
            criteria.andSelleridEqualTo(param.getSellerid());
        }
        if (param.getStartTime() != null) {
            criteria.andApplytimeGreaterThanOrEqualTo(param.getStartTime());
        }
        if (param.getEndTime() != null) {
            Date endTime = param.getEndTime();
            Calendar c = Calendar.getInstance();
            c.setTime(endTime);
            c.add(Calendar.DAY_OF_MONTH, 1);// 今天+1天
            Date tomorrow = c.getTime();
            criteria.andApplytimeLessThanOrEqualTo(tomorrow);
        }


        PageInfo pageInfo = new PageInfo(sellerBillRecordMapper.selectByExample(sellerBillRecordExample));
        return pageInfo;
    }


    /**
     * 获取卖家开票记录列表
     *
     * @param param
     * @return
     */
    public List<Map<String, Object>> getSellerBillRecordExcel(SellerBillRecordQueryParam param) {

        SellerBillRecordExample sellerBillRecordExample = new SellerBillRecordExample();
        SellerBillRecordExample.Criteria criteria = sellerBillRecordExample.createCriteria();

        if (StringUtils.hasText(param.getDiliveryno())) {
            String deliverNo = "%" + param.getDiliveryno() + "%";
            criteria.andDiliverynoLike(deliverNo);
        }

        if (StringUtils.hasText(param.getBillno())) {
            String billNo = "%" + param.getBillno() + "%";
            criteria.andBillnoLike(billNo);

        }
        if (StringUtils.hasText(param.getSellername())) {
            String sellerName = "%" + param.getSellername() + "%";
            criteria.andSellernameLike(sellerName);
        }
        if (param.getSellerid() != null) {
            criteria.andSelleridEqualTo(param.getSellerid());
        }
        if (param.getStartTime() != null) {
            criteria.andApplytimeGreaterThanOrEqualTo(param.getStartTime());
        }
        if (param.getEndTime() != null) {
            Date endTime = param.getEndTime();
            Calendar c = Calendar.getInstance();
            c.setTime(endTime);
            c.add(Calendar.DAY_OF_MONTH, 1);// 今天+1天
            Date tomorrow = c.getTime();
            criteria.andApplytimeLessThanOrEqualTo(tomorrow);
        }

        List<Map<String, Object>> list = new ArrayList<>();
        List<SellerBillRecord> sellerBillRecords = sellerBillRecordMapper.selectByExample(sellerBillRecordExample);

        for (SellerBillRecord sellerBillRecord : sellerBillRecords) {
            Map<String, Object> map = new HashMap<>();
            map.put("开票日期", sellerBillRecord.getApplytime());
            map.put("商家编号", sellerBillRecord.getSellerid());
            map.put("店铺名称", sellerBillRecord.getSellername());
            map.put("开票金额", sellerBillRecord.getMoney());
            map.put("物流公司", sellerBillRecord.getDiliverycompany());
            map.put("快递单号", sellerBillRecord.getDiliveryno());
            map.put("发票类型", sellerBillRecord.getBilltype());
            map.put("发票号", sellerBillRecord.getBillno());
            if (sellerBillRecord.getState() == Quantity.STATE_0) {
                map.put("状态", "待确认");
            } else {
                map.put("状态", "确认");
            }
            list.add(map);
        }

        return list;
    }


    /**
     * 获取未结束的订单
     *
     * @return
     */
    public List<Orders> getNotOverOrders() {

        OrdersExample ordersExample = new OrdersExample();
        ordersExample.createCriteria().andOrderstatusNotEqualTo(Quantity.STATE_5).andOrderstatusNotEqualTo(Quantity.STATE_7);
        return ordersMapper.selectByExample(ordersExample);

    }

    /**
     * 将未付款的订单改变成关闭
     *
     * @param memberid
     */
    public void updateNotPayOrdersForFinish(Long memberid) {

        TransactionSetting transactionSetting = transactionSettingService.getTransactionSetting();

        BigDecimal unpaidtimeout = transactionSetting.getUnpaidtimeout();

        int unpaidtimeoutHour = unpaidtimeout.intValue();

        Calendar c = Calendar.getInstance();
        OrdersExample ordersExample = new OrdersExample();
        OrdersExample.Criteria criteria = ordersExample.createCriteria();
        criteria.andOrderstatusEqualTo(Quantity.STATE_0);
        if (memberid != null) {
            criteria.andMemberidEqualTo(memberid);
        }

        List<Orders> list = ordersMapper.selectByExample(ordersExample);
        List<Orders> orders = new ArrayList<>();
        for (Orders order : list) {
            if (order.getCreatetime() != null) {
                c.setTime(order.getCreatetime());
                c.add(Calendar.HOUR, unpaidtimeoutHour);
                //c.add(Calendar.MINUTE, 5);
                Date tomorrow = c.getTime();
                Date today = new Date();

                //关闭订单
                if (today.compareTo(tomorrow) == 1) {
                    Orders updateOrder = new Orders();
                    updateOrder.setId(order.getId());
                    updateOrder.setOrderstatus(Quantity.STATE_7);
                    updateOrder.setReason("订单超时取消");
                    updateSingleOrder(updateOrder);


                    //将商品库存加回去
                    List<OrderProduct> orderProductList = this.getOrderProductByOrderId(order.getId());
                    for(OrderProduct orderProduct : orderProductList){
                      productStoreService.addStoreNumByPdidAndPdno(orderProduct.getPdid(),orderProduct.getPdno(),orderProduct.getNum());
                    }
                }
            }
        }
    }


    /**
     * 获取卖家没有处理的退货订单
     *
     * @return
     */
    public List<OrderProductBack> getNotSellerHandle() {
        OrderProductBackExample orderProductBackExample = new OrderProductBackExample();
        OrderProductBackExample.Criteria criteria1 = orderProductBackExample.createCriteria();
        criteria1.andStateEqualTo(Quantity.STATE_0);

        /*OrderProductBackExample.Criteria criteria2 = orderProductBackExample.createCriteria();
        criteria2.andStateEqualTo(Quantity.STATE_1);

        orderProductBackExample.or(criteria2);*/

        return orderProductBackMapper.selectByExample(orderProductBackExample);
    }


    /**
     * 根据id获取退货申请
     *
     * @param id
     * @return
     */
    public OrderProductBack getOrderProductBackById(long id) {
        return orderProductBackMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据id获取订单
     *
     * @param id
     * @return
     */
    public Orders getOrdersById(long id) {
        return ordersMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据id获取开票记录
     *
     * @param id
     * @return
     */
    public BillingRecord getBillRecordByID(Long id) {
        return billingRecordMapper.selectByPrimaryKey(id);
    }

    public List<BillingRecord> getBillRecordList(String ids) {
        return billingRecordMapper.getBillingRecordListByIds(ids);
    }

    /**
     * 修改开票申请记录
     *
     * @param billingRecord
     */
    public void updateBillRecord(BillingRecord billingRecord) {
        billingRecordMapper.updateByPrimaryKeySelective(billingRecord);
    }

    /**
     * 根据订单编号删除开票申请记录
     *
     * @param orderno
     */
    public void deleteBillRecord(String orderno) {
        BillingRecordExample billingRecordExample = new BillingRecordExample();
        billingRecordExample.createCriteria().andOrdernoEqualTo(orderno);
        billingRecordMapper.deleteByExample(billingRecordExample);
    }

    /**
     * 根据搜索条件获取开票列表
     *
     * @param billQueryParam
     * @param pageNo
     * @param pageSize
     * @return
     */
    public PageInfo getBillRecordList(BillQueryParam billQueryParam, int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize).setOrderBy("id desc");
        BillingRecordExample billingRecordExample = new BillingRecordExample();
        BillingRecordExample.Criteria criteria = billingRecordExample.createCriteria();
        Long memberId = billQueryParam.getMemberid();
        Date startTime = billQueryParam.getStartTime();
        Date endTime = billQueryParam.getEndTime();
        Short state = billQueryParam.getState();
        String memberName = billQueryParam.getMemberName();
        String orderNo = billQueryParam.getOrderNo();
        BigDecimal billStartCash = billQueryParam.getBillStartCash();
        BigDecimal billEndCash = billQueryParam.getBillEndCash();
        String expressNo = billQueryParam.getExpressNo();
        Date bStartTime = billQueryParam.getbStartTime();
        Date bEndTime = billQueryParam.getbEndTime();
        String billNo = billQueryParam.getBillNo();
        String invoiceheadup = billQueryParam.getInvoiceheadup();

        if (StringUtils.hasText(invoiceheadup)) {
            invoiceheadup = "%" + invoiceheadup + "%";
            criteria.andInvoiceheadupLike(invoiceheadup);
        }
        if (memberId != null) {
            criteria.andMemberidEqualTo(memberId);
        }
        if (startTime != null) {
            criteria.andCreatetimeGreaterThanOrEqualTo(startTime);
        }
        if (endTime != null) {
            Calendar c = Calendar.getInstance();
            c.setTime(endTime);
            c.add(Calendar.DAY_OF_MONTH, 1);// 今天+1天
            Date tomorrow = c.getTime();
            criteria.andCreatetimeLessThanOrEqualTo(tomorrow);
        }

        if (state != null && state != Quantity.STATE_) {
            criteria.andStateEqualTo(state);
        }

        if (StringUtils.hasText(memberName)) {
            memberName = "%" + memberName + "%";
            criteria.andMembernameLike(memberName);
        }
        if (StringUtils.hasText(orderNo)) {
            orderNo = "%" + orderNo + "%";
            criteria.andOrdernoLike(orderNo);
        }
        if (billStartCash != null) {
            criteria.andBillcashGreaterThanOrEqualTo(billStartCash);
        }
        if (billEndCash != null) {
            criteria.andBillcashLessThanOrEqualTo(billEndCash);
        }
        if (StringUtils.hasText(expressNo)) {
            expressNo = "%" + expressNo + "%";
            criteria.andExpressnoLike(expressNo);
        }
        if (bStartTime != null) {
            criteria.andBilltimeGreaterThanOrEqualTo(bStartTime);
        }
        if (bEndTime != null) {
            Calendar c = Calendar.getInstance();
            c.setTime(bEndTime);
            c.add(Calendar.DAY_OF_MONTH, 1);// 今天+1天
            Date tomorrow = c.getTime();
            criteria.andBilltimeLessThanOrEqualTo(tomorrow);
        }
        if (billNo != null) {
            criteria.andBillnoLike(billNo);
        }


        criteria.andStateNotEqualTo(Quantity.STATE_);


        PageInfo pageInfo = new PageInfo(billingRecordMapper.selectByExample(billingRecordExample));
        return pageInfo;
    }

    /**
     * 根据搜索条件导出开票列表
     *
     * @param billQueryParam
     * @return
     */
    public List<Map<String, Object>> getBillRecordList(BillQueryParam billQueryParam) {
        BillingRecordExample billingRecordExample = new BillingRecordExample();
        BillingRecordExample.Criteria criteria = billingRecordExample.createCriteria();
        Long memberId = billQueryParam.getMemberid();
        Date startTime = billQueryParam.getStartTime();
        Date endTime = billQueryParam.getEndTime();
        Short state = billQueryParam.getState();
        String memberName = billQueryParam.getMemberName();
        String orderNo = billQueryParam.getOrderNo();
        BigDecimal billStartCash = billQueryParam.getBillStartCash();
        BigDecimal billEndCash = billQueryParam.getBillEndCash();
        String expressNo = billQueryParam.getExpressNo();
        Date bStartTime = billQueryParam.getbStartTime();
        Date bEndTime = billQueryParam.getbEndTime();
        String billNo = billQueryParam.getBillNo();
        String invoiceheadup = billQueryParam.getInvoiceheadup();

        if (StringUtils.hasText(invoiceheadup)) {
            invoiceheadup = "%" + invoiceheadup + "%";
            criteria.andInvoiceheadupLike(invoiceheadup);
        }
        if (memberId != null) {
            criteria.andMemberidEqualTo(memberId);
        }
        if (startTime != null) {
            criteria.andCreatetimeGreaterThanOrEqualTo(startTime);
        }
        if (endTime != null) {
            Calendar c = Calendar.getInstance();
            c.setTime(endTime);
            c.add(Calendar.DAY_OF_MONTH, 1);// 今天+1天
            Date tomorrow = c.getTime();
            criteria.andCreatetimeLessThanOrEqualTo(tomorrow);
        }

        if (state != null && state != Quantity.STATE_) {
            criteria.andStateEqualTo(state);
        }

        if (StringUtils.hasText(memberName)) {
            memberName = "%" + memberName + "%";
            criteria.andMembernameLike(memberName);
        }
        if (StringUtils.hasText(orderNo)) {
            orderNo = "%" + orderNo + "%";
            criteria.andOrdernoLike(orderNo);
        }
        if (billStartCash != null) {
            criteria.andBillcashGreaterThanOrEqualTo(billStartCash);
        }
        if (billEndCash != null) {
            criteria.andBillcashLessThanOrEqualTo(billEndCash);
        }
        if (StringUtils.hasText(expressNo)) {
            expressNo = "%" + expressNo + "%";
            criteria.andExpressnoLike(expressNo);
        }
        if (bStartTime != null) {
            criteria.andBilltimeGreaterThanOrEqualTo(bStartTime);
        }
        if (bEndTime != null) {
            Calendar c = Calendar.getInstance();
            c.setTime(bEndTime);
            c.add(Calendar.DAY_OF_MONTH, 1);// 今天+1天
            Date tomorrow = c.getTime();
            criteria.andBilltimeLessThanOrEqualTo(tomorrow);
        }
        if (billNo != null) {
            criteria.andBillnoLike(billNo);
        }
        List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
        List<BillingRecord> billingRecords = billingRecordMapper.selectByExample(billingRecordExample);

        for (BillingRecord billingRecord : billingRecords) {
            String billingrecordtype = "";
            if (billingRecord.getBillingrecordtype() == Quantity.STATE_0) {
                billingrecordtype = "纸质";
            } else {
                billingrecordtype = "电子";
            }
            String billstate = "";
            if (billingRecord.getState() == Quantity.STATE_0) {
                billstate = "待开票";
            } else {
                billstate = "已开票";
            }

            String orderStr = billingRecord.getOrderno();

            String[] ordernos = orderStr.split(",");

            for (String id : ordernos) {
                Orders orders = getOrdersById(Long.parseLong(id));
                if (orders != null) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("开票日期", billingRecord.getCreatetime());
                    map.put("会员编码", billingRecord.getMemberid());
                    map.put("会员名称", billingRecord.getMembername());
                    map.put("订单号", billingRecord.getOrderno());
                    map.put("订单金额", orders.getTotalprice().subtract(orders.getFreight()));
                    map.put("开票金额", orders.getTotalprice().subtract(orders.getFreight()));
                    map.put("开票类型", billingrecordtype);
                    if (StringUtils.hasText(billingRecord.getBilltype())) {
                        map.put("发票类型", billingRecord.getBilltype());
                    } else {
                        map.put("发票类型", "");
                    }
                    map.put("发票抬头", billingRecord.getInvoiceid());
                    map.put("税号", billingRecord.getTexno());
                    if (StringUtils.hasText(billingRecord.getBillno())) {
                        map.put("发票号", billingRecord.getBillno());
                    } else {
                        map.put("发票号", "");
                    }
                    if (StringUtils.hasText(billingRecord.getExpresscom())) {
                        map.put("物流公司", billingRecord.getExpresscom());
                    } else {
                        map.put("物流公司", "");
                    }
                    if (StringUtils.hasText(billingRecord.getExpressno())) {
                        map.put("远输单号", billingRecord.getExpressno());
                    } else {
                        map.put("远输单号", "");
                    }

                    map.put("开票状态", billstate);
                    maps.add(map);
                }

            }

        }
        return maps;
    }


    /**
     * 获取未开票的订单
     *
     * @param memberid
     * @param pageNo
     * @param pageSize
     * @return
     */
    public PageInfo getNotBillOrders(BillQueryParam billQueryParam, long memberid, int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize).setOrderBy("id desc");
        OrdersExample ordersExample = new OrdersExample();
        OrdersExample.Criteria criteria = ordersExample.createCriteria();
        criteria.andMemberidEqualTo(memberid).andIsbillingEqualTo(Quantity.STATE_0).andOrderstatusEqualTo(Quantity.STATE_5);
        if (StringUtils.hasText(billQueryParam.getOrderNo())) {
            String orderNo = "%" + billQueryParam.getOrderNo() + "%";
            criteria.andOrdernoLike(orderNo);
        }
        if (billQueryParam.getStartTime() != null) {
            criteria.andCreatetimeGreaterThanOrEqualTo(billQueryParam.getStartTime());
        }
        if (billQueryParam.getEndTime() != null) {
            Calendar c = Calendar.getInstance();
            c.setTime(billQueryParam.getEndTime());
            c.add(Calendar.DAY_OF_MONTH, 1);// 今天+1天
            Date tomorrow = c.getTime();
            criteria.andCreatetimeLessThanOrEqualTo(tomorrow);
        }
        PageInfo pageInfo = new PageInfo(ordersMapper.selectByExample(ordersExample));
        return pageInfo;
    }

    /**
     * 获取用户开票信息
     *
     * @param id
     * @return
     */
    public InvoiceInfo getInvoiceInfo(long id) {
        return invoiceInfoMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取退货列表
     *
     * @param pageNo
     * @param pageSize
     * @param backQueryParam
     * @return
     */
    public PageInfo getOrderProductBackList(int pageNo, int pageSize, BackQueryParam backQueryParam) {
        PageHelper.startPage(pageNo, pageSize).setOrderBy("id desc");
//        OrderProductBackExample orderProductBackExample = new OrderProductBackExample();
//        OrderProductBackExample.Criteria criteria = orderProductBackExample.createCriteria();
//        if (backQueryParam.getSellerid() != null) {
//            criteria.andSelleridEqualTo(backQueryParam.getSellerid());
//        }
//
//        if (backQueryParam.getMemberId() != null) {
//            criteria.andMemberidEqualTo(backQueryParam.getMemberId());
//        }
//        if (StringUtils.hasText(backQueryParam.getCode())) {
//            String code = "%" + backQueryParam.getCode() + "%";
//            criteria.andCodeLike(code);
//        }
//        if (StringUtils.hasText(backQueryParam.getOrderNo())) {
//            String orderNo = "%" + backQueryParam.getOrderNo() + "%";
//            criteria.andOrdernoLike(orderNo);
//        }
//        if (StringUtils.hasText(backQueryParam.getBackNo())) {
//            String backNo = "%" + backQueryParam.getBackNo() + "%";
//            criteria.andBacknoLike(backNo);
//        }
//        if (StringUtils.hasText(backQueryParam.getPdName())) {
//            String pdName = "%" + backQueryParam.getPdName() + "%";
//            criteria.andPdnameLike(pdName);
//        }
//        if (StringUtils.hasText(backQueryParam.getMemberName())) {
//            String memberName = "%" + backQueryParam.getMemberName() + "%";
//            criteria.andMembernameLike(memberName);
//        }
//        if (StringUtils.hasText(backQueryParam.getSellerName())) {
//            String sellerName = "%" + backQueryParam.getSellerName() + "%";
//            criteria.andSellnameLike(sellerName);
//        }
//        if (backQueryParam.getState() != Quantity.STATE_) {
//            criteria.andStateEqualTo(backQueryParam.getState());
//        }
//        if (backQueryParam.getStartTime() != null) {
//            criteria.andCreatetimeGreaterThanOrEqualTo(backQueryParam.getStartTime());
//        }
//        if (backQueryParam.getEndTime() != null) {
//            Calendar c = Calendar.getInstance();
//            c.setTime(backQueryParam.getEndTime());
//            c.add(Calendar.DAY_OF_MONTH, 1);// 今天+1天
//            Date tomorrow = c.getTime();
//            criteria.andCreatetimeLessThanOrEqualTo(tomorrow);
//        }

        List<OrderProductBackView> list = orderProductBackMapper.getOrderProductBackList(backQueryParam);
        list.forEach(orderProductBackView -> {
            if(orderProductBackView.getClassifyid() != null) {
                Categories categories = this.getCategories(orderProductBackView.getClassifyid());
                orderProductBackView.setLevel3(categories.getName());
            }
        });

        PageInfo<OrderProductBackView> pageInfo = new PageInfo(list);
//        if (pageInfo.getList().size() != 0) {
//            List<Long> orderProductIds = pageInfo.getList().stream().map(o -> o.getPdid()).collect(Collectors.toList());
//            ProductInfoExample example = new ProductInfoExample();
//            ProductInfoExample.Criteria criteria1 = example.createCriteria();
//            criteria1.andIdIn(orderProductIds);
//            List<ProductInfo> productInfoList = productInfoMapper.selectByExample(example);
//            Map<Long, ProductInfo> productInfoListMap = productInfoList.stream().collect(Collectors.toMap(ProductInfo::getId, ProductInfo -> ProductInfo));
//            pageInfo.getList().forEach(orderProductBack -> {
//                String[] strpic = (String[]) productInfoListMap.get(orderProductBack.getPdid()).getPdpicture();
//                if (strpic != null && strpic.length > 0) {
//                    orderProductBack.setPdPic(strpic[0]);
//                }
//            });
//        }
        return pageInfo;
    }


    /**
     * 获取excel退货列表
     *
     * @param backQueryParam
     * @return
     */
    public List<Map<String, Object>> getOrderProductBackExcelList(BackQueryParam backQueryParam) {
        List<Map<String, Object>> list = ordersMapper.getExcelProductBack(backQueryParam);
        List<Map<String, Object>> list2 = new ArrayList<>();
        for (Map<String, Object> map : list) {
            Map<String, Object> maptemp = new HashMap<>();
            maptemp.put("下单时间", map.get("createtime"));
            maptemp.put("合同号", map.get("code"));
            maptemp.put("订单号", map.get("orderno"));
            maptemp.put("交易号", map.get("transactionnumber"));
            maptemp.put("买家", map.get("membername"));
            maptemp.put("卖家", map.get("sellername"));
            maptemp.put("退货数量", map.get("pdnum"));
            maptemp.put("退货金额", map.get("backmoney"));
            maptemp.put("退货单号", map.get("backno"));
            maptemp.put("订单类型", map.get("ordertype"));
            maptemp.put("订单来源", map.get("inonline"));
            maptemp.put("商品名称", map.get("pdname"));
            maptemp.put("规格", map.get("attrjson"));
            maptemp.put("材质", map.get("material"));
            maptemp.put("牌号", map.get("gradeno"));
            maptemp.put("品牌", map.get("brand"));
            maptemp.put("印记", map.get("mark"));
            maptemp.put("表面处理", map.get("surfacetreatment"));
            maptemp.put("包装方式", map.get("packagetype"));
            maptemp.put("单位", map.get("unit"));
            maptemp.put("单价", map.get("price"));
            maptemp.put("订购量", map.get("num"));
            maptemp.put("货款金额", map.get("actualpayment"));
            list2.add(maptemp);
        }
        return list2;
    }


    /**
     * 根据订单商品id获取最新的退货申请
     *
     * @param id
     * @return
     */
    public OrderProductBack getOrderProductBackByOrderProductID(Long id) {
//        OrderProductBackExample orderProductBackExample = new OrderProductBackExample();
//        orderProductBackExample.createCriteria().andOrderpdidEqualTo(id);
//        orderProductBackExample.setOrderByClause("id desc");
//        List<OrderProductBack> list = orderProductBackMapper.selectByExample(orderProductBackExample);
//        if (list.size() > 0) {
//            return list.get(0);
//        } else {
//            return null;
//        }

        return orderProductBackMapper.getOrderProductBackByOrderProductID(id);
    }

    /**
     * 保存退货申请
     *
     * @param orderProductBack
     */
    public void insertOrderProductBack(OrderProductBack orderProductBack) {
        orderProductBackMapper.insertSelective(orderProductBack);
    }

    /**
     * 修改退货申请
     *
     * @param orderProductBack
     */
    public void updateOrderProductBack(OrderProductBack orderProductBack) {
        orderProductBackMapper.updateByPrimaryKeySelective(orderProductBack);
    }

    /**
     * 根据ids数组计算总金额
     *
     * @param ids
     * @return
     */
    public BigDecimal getOrdersSumByInIds(String ids) {
        return ordersMapper.getOrdersSumByInIds(ids);
    }

    /**
     * 根据ids数组获取订单
     *
     * @param ids
     * @return
     */
    public List<Orders> getOrdersByInIds(String ids) {
        return ordersMapper.getOrdersByInIds(ids);
    }

    /**
     * 根据ids数组获取订单
     *
     * @param ids
     * @return
     */
    public List<Orders> getWMSOrdersByInIds(String ids) {
        return ordersMapper.getWMSOrdersByInIds(ids);
    }


    /**
     * 获取选中的购物车信息
     *
     * @param ids
     * @return
     */
    public List<ShopCar> loadSelectShopCar(String ids) {
        return shopCarMapper.loadSelectShopCar(ids);
    }

    /**
     * 获取运费模板
     *
     * @param id 模板id
     * @return
     */
    public ShippingTemplates getShippingTemp(long id) {
        return shippingTemplatesMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取地区运费模板
     *
     * @param id 模板id
     * @return
     */
    public AreaCost getAreaCost(long id) {
        AreaCostExample areaCostExample = new AreaCostExample();
        areaCostExample.createCriteria().andTemidEqualTo(id);
        List<AreaCost> list = areaCostMapper.selectByExample(areaCostExample);
        if (list.size() > Quantity.INT_0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    /**
     * 根据订单编号数组查询订单商品
     *
     * @param orderids
     * @return
     */
    public List<OrderProduct> getOrderProductByInOrderids(String orderids) {
        return orderProductMapper.getOrderProductByInOrderids(orderids);
    }

    /**
     * 根据id获取产品信息
     *
     * @param id
     * @return
     */
    public ProductInfo getProductInfoByPrimeKey(long id) {
        return productInfoMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据id获取仓库
     *
     * @param id
     * @return
     */
    public Store getStoreByPrimeKey(long id) {
        return storeMapper.selectByPrimaryKey(id);
    }


    /**
     * 根据用户id查询购物车
     *
     * @param memberid
     * @return
     */
    public List<ShopCar> getShopCarByMemberId(long memberid) {
        ShopCarExample shopCarExample = new ShopCarExample();
        shopCarExample.createCriteria().andMemberidEqualTo(memberid);
        return shopCarMapper.selectByExample(shopCarExample);
    }


    public void saveProductInfo(ProductInfo info) {
        productInfoMapper.updateByPrimaryKeySelective(info);
    }

    /**
     * 更新库存
     *
     * @param productStore
     */
    public void updateProductStore(ProductStore productStore) {
        productStoreMapper.updateByPrimaryKeySelective(productStore);
    }

    /**
     * 根据pdid和storeid查询
     *
     * @param pdid
     * @param storeid
     * @return
     */
    public ProductStore getProductStore(long pdid, String pdno, long storeid) {
//        ProductStoreExample productStoreExample = new ProductStoreExample();
//        productStoreExample.createCriteria().andPdidEqualTo(pdid).andStoreidEqualTo(storeid).andPdnoEqualTo(pdno);
//        List<ProductStore> list = productStoreMapper.selectByExample(productStoreExample);
//        if (list.size() > Quantity.INT_0) {
//            return list.get(0);
//        } else {
//            return null;
//        }

        return  productStoreService.getProductStore(pdid,pdno,storeid);

    }


    /**
     * 更新商品
     *
     * @param orderProduct
     */
    public int updateOrderProduct(OrderProduct orderProduct) {
        return  orderProductMapper.updateByPrimaryKeySelective(orderProduct);
    }

    /**
     * 根据id获取商品
     *
     * @param id
     * @return
     */
    public OrderProduct getOrderProductById(long id) {
        //return orderProductMapper.selectByPrimaryKey(id);
        return  orderProductServices.getOrderProductById(id);
    }

    public List<OrderProduct> getOrderProductByOrderId(long orderid) {
//        OrderProductExample orderProductExample = new OrderProductExample();
//        orderProductExample.createCriteria().andOrderidEqualTo(orderid);
//        return orderProductMapper.selectByExample(orderProductExample);

        return  orderProductServices.getOrderProductByOrderId(orderid);
    }

    public List<OrderProduct> getOrderProductByOrderId(long orderid, Long pdid, String pdno) {
//        OrderProductExample orderProductExample = new OrderProductExample();
//        orderProductExample.createCriteria().andOrderidEqualTo(orderid).andPdidEqualTo(pdid).andPdnoEqualTo(pdno);
//        return orderProductMapper.selectByExample(orderProductExample);
        return  orderProductServices.getOrderProductByOrderId(orderid,pdid,pdno);
    }

    public List<OrderProduct> getOrderProductByOrderId(long orderid, long orderproductid) {
//        OrderProductExample orderProductExample = new OrderProductExample();
//        orderProductExample.createCriteria().andOrderidEqualTo(orderid).andIdNotEqualTo(orderproductid);
//        return orderProductMapper.selectByExample(orderProductExample);

        return  orderProductServices.getOrderProductByOrderId(orderid,orderproductid);
    }

    /**
     * 根据订单编号获取订单
     *
     * @param orderno
     * @return
     */
    public Orders getOrdersByOrderNo(String orderno) {
//        OrdersExample ordersExample = new OrdersExample();
//        ordersExample.createCriteria().andOrdernoEqualTo(orderno);
//        List<Orders> list = ordersMapper.selectByExample(ordersExample);
//        if (list.size() > Quantity.INT_0) {
//            return list.get(0);
//        } else {
//            return null;
//        }
        return  ordersMapper.getOrdersByOrderNo(orderno);
    }

    /**
     * 根据订单编号获取商品信息
     *
     * @param orderno
     * @return
     */
    public List<OrderProduct> getOrderProductByOrderNo(String orderno) {
        OrderProductExample orderProductExample = new OrderProductExample();
        orderProductExample.createCriteria().andOrdernoEqualTo(orderno);
        List<OrderProduct> list = orderProductMapper.selectByExample(orderProductExample);

        for (OrderProduct op : list) {
            if (op.getProducttype() == Quantity.STATE_1 && StringUtils.hasText(op.getPddesc())) {
                op.setPackageStr(JinshangUtils.packageToString(op.getPddesc(), op.getNum(), op.getUnit()));
            }
        }

        return list;
    }

    /**
     * 根据订单编号获取商品信息
     *
     * @param orderno
     * @return
     */
    public List<OrderProduct> getOrderProductByOrderNo(String orderno, OrderQueryParam param) {
        OrderProductExample orderProductExample = new OrderProductExample();
        OrderProductExample.Criteria criteria = orderProductExample.createCriteria();
        criteria.andOrdernoEqualTo(orderno);
        if (StringUtils.hasText(param.getPdName())) {
            String pdName = "%" + param.getPdName() + "%";
            criteria.andPdnameLike(pdName);
        }
        if (param.getEvaState() != null) {
            criteria.andEvaluatestateEqualTo(param.getEvaState());
        }
        if (param.getBackstate() != null) {
            criteria.andBackstateEqualTo(param.getBackstate());
        }
        if (StringUtils.hasText(param.getBrand())) {
            String brand = "%" + param.getBrand() + "%";
            criteria.andBrandLike(brand);
        }
        if (StringUtils.hasText(param.getStand())) {
            String stand = "%" + param.getStand() + "%";
            criteria.andStandardLike(stand);
        }
        if (StringUtils.hasText(param.getMark())) {
            String mark = "%" + param.getMark() + "%";
            criteria.andMarkLike(mark);
        }
        return orderProductMapper.selectByExample(orderProductExample);
    }

    /**
     * 根据订单编号获取开票信息
     *
     * @param orderid
     * @return
     */
    public BillingRecord getBillingRecordByOrderNo(String orderid) {
//        BillingRecordExample billingRecordExample = new BillingRecordExample();
//        orderid = "%" + orderid + "%";
//        billingRecordExample.createCriteria().andOrdernoLike(orderid);
//        List<BillingRecord> list = billingRecordMapper.selectByExample(billingRecordExample);
//        if (list.size() > Quantity.INT_0) {
//            return list.get(0);
//        } else {
//            return null;
//        }

        if(!StringUtils.hasText(orderid)){
            return  null;
        }

        BillingRecord billingRecord = billingRecordMapper.getBillingRecordByOrderno(orderid);

        return  billingRecord;
    }

    /**
     * 获取用户订单列表
     *
     * @param param
     * @return
     */
    public PageInfo getMemberOrdersList(OrderQueryParam param) {
        PageHelper.startPage(param.getPageNo(), param.getPageSize()).setOrderBy("id desc");
        PageInfo info = new PageInfo(ordersMapper.getMemberOrdersList(param));
        return info;
    }

    /**
     * 商家待发货列表导出Excel
     * @param param
     * @return
     */
    public List<Map<String,Object>> memberDeliveryOrderListExcel(OrderQueryParam param) {
        PageHelper.startPage(param.getPageNo(), param.getPageSize()).setOrderBy("id desc");
        List<Orders> list = ordersMapper.getMemberOrdersList(param);
        List<Map<String,Object>> newList = new ArrayList<Map<String,Object>>();
        Map orderstatus = new HashMap();
        orderstatus.put("0","待付款");
        orderstatus.put("1","待发货");
        orderstatus.put("3","待收货");
        orderstatus.put("4","待验货");
        orderstatus.put("5","已完成");
        orderstatus.put("7","已关闭");
        orderstatus.put("8","备货中");
        orderstatus.put("9","备货完成");
        for(Orders o:list){
            Map<String,Object> map = new HashMap<String,Object>();
            List<OrderProduct> orderProducts =getOrderProductByOrderNo((o.getOrderno()));
            map.put("收件人",o.getShipto());
            map.put("合同号/订单号",o.getCode()+"/"+o.getOrderno());
            map.put("订单日期",o.getCreatetime());
            map.put("发货截止日期",o.getCreatetime());
            if(o.getOrderstatus()!=null) {
                map.put("交易状态", orderstatus.get(o.getOrderstatus().toString()));
            }else{
                map.put("交易状态","");
            }
            for (OrderProduct orderProduct : orderProducts) {
                map.put("商品",orderProduct.getPdname()+""+orderProduct.getClassify()+""+orderProduct.getStandard()+""+orderProduct.getMaterial()+"/"+orderProduct.getGradeno());
                map.put("品牌印记",orderProduct.getBrand());
                map.put("订货量",orderProduct.getNum());
                newList.add(map);
            }
        }

        List<Map<String,Object>> newList1 = new ArrayList<Map<String,Object>>();
        for(int i=0;i<newList.size();i++){
            Map<String,Object> newMap = new HashMap<String,Object>();
            String flag=newList.get(i).get("合同号/订单号").toString();
            if(newList1.size()<1){
                newMap.put("合同号/订单号",newList.get(i).get("合同号/订单号"));
                newMap.put("订单日期",newList.get(i).get("订单日期"));
            }else if(!newList1.get(i-1).get("合同号/订单号").equals(newList.get(i).get("合同号/订单号")) && !newList.get(i-1).get("合同号/订单号").equals(flag)){
                newMap.put("合同号/订单号",newList.get(i).get("合同号/订单号"));
                newMap.put("订单日期",newList.get(i).get("订单日期"));
            }else if(newList.get(i).get("合同号/订单号").equals(flag)){
                newMap.put("合同号/订单号","");
                newMap.put("订单日期","");

            }
            newMap.put("收件人",newList.get(i).get("收件人"));
            newMap.put("发货截止日期",newList.get(i).get("发货截止日期"));
            newMap.put("交易状态",newList.get(i).get("交易状态"));
            newMap.put("商品",newList.get(i).get("商品"));
            newMap.put("品牌印记",newList.get(i).get("品牌印记"));
            newMap.put("订货量",newList.get(i).get("订货量"));
            newList1.add(newMap);
        }

        return newList1;
    }

    /**
     * 导出卖家订单列表
     *
     * @param param
     * @return
     */
    public List<Map<String, Object>> getSellerExcelOrders(OrderQueryParam param) {
        //PageHelper.startPage(param.getPageNo(), param.getPageSize()).setOrderBy("id desc");
        List<Orders> list =ordersMapper.getMemberOrdersList(param);
        List<Map<String, Object>> memberOrderses = new ArrayList<Map<String, Object>>();
        Map orderStatus = new HashMap();
        orderStatus.put("0","待付款");
        orderStatus.put("1","待发货");
        orderStatus.put("3","待收货");
        orderStatus.put("4","待验货");
        orderStatus.put("5","已完成");
        orderStatus.put("7","已关闭");
        orderStatus.put("8","备货中");
        orderStatus.put("9","备货完成");
        for (Orders order : list) {
            List<OrderProduct> orderProducts =getOrderProductByOrderNo(order.getOrderno(), param);
            if(orderProducts.size()>0) {
                for (OrderProduct op : orderProducts) {
                    Map map = new HashMap();
                    map.put("订单日期", order.getCreatetime());
                    map.put("订单号", order.getOrderno());
                    map.put("商品", op.getPdname() + "" + op.getClassify() + "" + op.getStandard() + "" + op.getMaterial() + "/" + op.getGradeno());
                    map.put("品牌印记", op.getBrand() + "" + op.getMark());
                    map.put("订货量", op.getNum().toString());
                    map.put("单价", op.getPrice());
                    map.put("运费", op.getFreight());
                    map.put("订单总价", order.getTotalprice());
                    map.put("交易状态", orderStatus.get(order.getOrderstatus().toString()));
                    map.put("佣金", order.getBrokepay());
                    map.put("结算价", order.getTotalprice().subtract(order.getBrokepay()).doubleValue());
                    memberOrderses.add(map);
                }
            }

        }

        return memberOrderses;
    }

    /**
     * 获取excel订单列表
     *
     * @param param
     * @return
     */
    public List<Map<String, Object>> getExcelOrders(OrderQueryParam param) {

        List<Map<String, Object>> list = ordersMapper.getExcelOrders(param);

        List<Map<String, Object>> list2 = new ArrayList<Map<String, Object>>();

        for (Map<String, Object> map : list) {
            Map<String, Object> maptemp = new HashMap<String, Object>();
            maptemp.put("订单号", map.get("orderno"));
            maptemp.put("下单时间", map.get("createtime"));
            maptemp.put("合同号", map.get("code"));
            maptemp.put("交易号", map.get("transactionnumber"));

            String buyercompanyname = (String) map.get("buyercompanyname");
            if(!StringUtils.hasText(buyercompanyname)){
                buyercompanyname = (String) map.get("buyerrealname");
            }
            if (!StringUtils.hasText(buyercompanyname)) {
                buyercompanyname = (String) map.get("membername");
            }

            maptemp.put("买家", buyercompanyname);
            maptemp.put("卖家", map.get("membercompany"));
            maptemp.put("订单类型", map.get("ordertype"));
            maptemp.put("订单来源", map.get("inonline"));
            maptemp.put("商品名称", map.get("pdname")+"/"+map.get("level3"));
            maptemp.put("规格", map.get("attrjson"));
            maptemp.put("商品分类", map.get("level1")+" "+map.get("level2")+" "+map.get("level3"));
            maptemp.put("材质", map.get("material"));
            maptemp.put("牌号", map.get("gradeno"));
            maptemp.put("品牌", map.get("brand"));
            maptemp.put("印记", map.get("mark"));
            maptemp.put("表面处理", map.get("surfacetreatment"));
            maptemp.put("包装方式", map.get("packagetype"));
            maptemp.put("单位", map.get("unit"));
            maptemp.put("单价", map.get("price"));
            maptemp.put("订购量", map.get("num").toString());
            maptemp.put("货款金额", map.get("actualpayment"));

            maptemp.put("开票抬头", map.get("invoiceheadup"));
            maptemp.put("税号", map.get("texno"));
            maptemp.put("开户行", map.get("bankofaccounts"));
            maptemp.put("开户账号", map.get("account"));
            maptemp.put("开票地址", map.get("address"));
            maptemp.put("电话", map.get("phone"));
            maptemp.put("是否开票", map.get("brstate"));
            maptemp.put("订单状态", map.get("orderstatus"));
            maptemp.put("项目", map.get("odtype"));
            maptemp.put("收件人",map.get("shipto"));
            maptemp.put("收货地址", map.get("receivingaddress"));
            maptemp.put("付款方式", map.get("paytype"));
            maptemp.put("物流公司", map.get("logisticscompany"));
            maptemp.put("快递单号", map.get("couriernumber"));
            maptemp.put("业务员", map.get("clerkname"));
            maptemp.put("第三方支付单号", map.get("transactionid"));
            maptemp.put("业务单号",map.get("uuid"));

            list2.add(maptemp);
        }

  /* List<Map<String,Object>> newList = new ArrayList<Map<String,Object>>();
        for(int i=0;i<list2.size();i++){
            Map<String,Object> newMap = new HashMap<String,Object>();
            String flag=list2.get(i).get("订单号").toString();
            if(newList.size()<1){
                newMap.put("订单号",list2.get(i).get("订单号"));
                newMap.put("下单时间",list2.get(i).get("下单时间"));
                newMap.put("合同号",list2.get(i).get("合同号"));
                newMap.put("交易号",list2.get(i).get("交易号"));
                newMap.put("买家",list2.get(i).get("买家"));
                newMap.put("卖家",list2.get(i).get("卖家"));
                newMap.put("订单类型",list2.get(i).get("订单类型"));
                newMap.put("订单来源",list2.get(i).get("订单来源"));

            }else if(!newList.get(i-1).get("订单号").equals(list2.get(i).get("订单号")) && !list2.get(i-1).get("订单号").equals(flag)){
                newMap.put("订单号",list2.get(i).get("订单号"));
                newMap.put("下单时间",list2.get(i).get("下单时间"));
                newMap.put("合同号",list2.get(i).get("合同号"));
                newMap.put("交易号",list2.get(i).get("交易号"));
                newMap.put("买家",list2.get(i).get("买家"));
                newMap.put("卖家",list2.get(i).get("卖家"));
                newMap.put("订单类型",list2.get(i).get("订单类型"));
                newMap.put("订单来源",list2.get(i).get("订单来源"));
            }else if(list2.get(i).get("订单号").equals(flag)){
                newMap.put("订单号","");
                newMap.put("下单时间","");
                newMap.put("合同号","");
                newMap.put("交易号","");
                newMap.put("买家","");
                newMap.put("卖家","");
                newMap.put("订单类型","");
                newMap.put("订单来源","");

            }
            newMap.put("商品名称",list2.get(i).get("商品名称"));
            newMap.put("规格",list2.get(i).get("规格"));
            newMap.put("材质",list2.get(i).get("材质"));
            newMap.put("牌号",list2.get(i).get("牌号"));
            newMap.put("品牌",list2.get(i).get("品牌"));
            newMap.put("印记",list2.get(i).get("印记"));
            newMap.put("表面处理",list2.get(i).get("表面处理"));
            newMap.put("包装方式",list2.get(i).get("包装方式"));
            newMap.put("单位",list2.get(i).get("单位"));
            newMap.put("单价",list2.get(i).get("单价"));
            newMap.put("订购量",list2.get(i).get("订购量"));
            newMap.put("货款金额",list2.get(i).get("货款金额"));
            newMap.put("开票抬头",list2.get(i).get("开票抬头"));
            newMap.put("税号",list2.get(i).get("税号"));
            newMap.put("开户行",list2.get(i).get("开户行"));
            newMap.put("开户账号",list2.get(i).get("开户账号"));
            newMap.put("开票地址",list2.get(i).get("开票地址"));
            newMap.put("电话",list2.get(i).get("电话"));
            newMap.put("是否开票",list2.get(i).get("是否开票"));
            newMap.put("订单状态",list2.get(i).get("订单状态"));
            newMap.put("项目",list2.get(i).get("项目"));
            newMap.put("收件人",list2.get(i).get("收件人"));
            newMap.put("收货地址",list2.get(i).get("收货地址"));
            newMap.put("付款方式",list2.get(i).get("付款方式"));
            newMap.put("物流公司",list2.get(i).get("物流公司"));
            newMap.put("快递单号",list2.get(i).get("快递单号"));
            newMap.put("业务员",list2.get(i).get("业务员"));
            newMap.put("第三方支付单号",list2.get(i).get("第三方支付单号"));
            newMap.put("业务单号",list2.get(i).get("业务单号"));

            newList.add(newMap);
        }*/

        return list2;
    }

    /**
     * 后台获取订单列表
     *
     * @param param
     * @return
     */
    public PageInfo getAllMemberOrdersList(OrderQueryParam param) {
        PageHelper.startPage(param.getPageNo(), param.getPageSize());

        PageInfo info = new PageInfo(ordersMapper.getAllMemberOrdersList(param));
        return info;
    }


    /**
     * 统计获取订单列表
     *
     * @param param
     * @return
     */
    public PageInfo getAllOrdersList(OrderQueryParam param) {
        PageHelper.startPage(param.getPageNo(), param.getPageSize()).setOrderBy("id desc");

        OrdersExample ordersExample = new OrdersExample();
        OrdersExample.Criteria criteria = ordersExample.createCriteria();

        if (param.getOrderState() != null) {
            criteria.andOrderstatusEqualTo(param.getOrderState());
        } else {
            criteria.andOrderstatusEqualTo(Quantity.STATE_0);
            OrdersExample.Criteria criteria1 = ordersExample.createCriteria();
            criteria1.andOrderstatusEqualTo(Quantity.STATE_1);
            OrdersExample.Criteria criteria2 = ordersExample.createCriteria();
            criteria2.andOrderstatusEqualTo(Quantity.STATE_5);
            OrdersExample.Criteria criteria3 = ordersExample.createCriteria();
            criteria3.andOrderstatusEqualTo(Quantity.STATE_7);
            ordersExample.or(criteria1);
            ordersExample.or(criteria2);
            ordersExample.or(criteria3);
        }
        if (param.getShopName() != null) {
            String shopName = "%" + param.getShopName() + "%";
            criteria.andShopnameLike(shopName);
        }
        if (param.getStartTime() != null) {
            criteria.andCreatetimeGreaterThanOrEqualTo(param.getStartTime());
        }
        if (param.getEndTime() != null) {
            Calendar c = Calendar.getInstance();
            c.setTime(param.getEndTime());
            c.add(Calendar.DAY_OF_MONTH, 1);// 今天+1天
            Date tomorrow = c.getTime();
            criteria.andCreatetimeLessThanOrEqualTo(tomorrow);
        }


        PageInfo info = new PageInfo(ordersMapper.selectByExample(ordersExample));
        return info;
    }


    /**
     * 获取用户订单各状态数量
     *
     * @param param
     * @return
     */
    public BuyerCenterModel getMemberOrdersNum(OrderQueryParam param) {
        BuyerCenterModel buyerCenterModel = new BuyerCenterModel();
        List<Map<String, Object>> list = null;
        if (param.getMemberid() != null) {
            list = ordersMapper.getBuyerCenterOrdersNum(param);
        } else {
            list = ordersMapper.getSellerCenterOrdersNum(param);
            OrdersExample ordersExample = new OrdersExample();
            ordersExample.createCriteria().andSaleidEqualTo(param.getSellerid());
            Integer allNum = ordersMapper.countByExample(ordersExample);
            buyerCenterModel.setNum11(allNum);
        }
        for (Map<String, Object> map : list) {
            String numname = (String) map.get("numname");
            Long count = (Long) map.get("count");
            if (numname != null) {
                if (numname.equals("预定待发")) {
                    buyerCenterModel.setNum1(count);
                } else if (numname.equals("待付款")) {
                    buyerCenterModel.setNum2(count);
                } else if (numname.equals("待发货")) {
                    buyerCenterModel.setNum3(count);
                } else if (numname.equals("待收货")) {
                    buyerCenterModel.setNum4(count);
                } else if (numname.equals("待验货")) {
                    buyerCenterModel.setNum5(count);
                } else if (numname.equals("交易成功")) {
                    buyerCenterModel.setNum6(count);
                } else if (numname.equals("退货中")) {
                    buyerCenterModel.setNum7(count);
                } else if (numname.equals("退货验收")) {
                    buyerCenterModel.setNum8(count);
                } else if (numname.equals("异议中")) {
                    buyerCenterModel.setNum9(count);
                } else if (numname.equals("交易关闭")) {
                    buyerCenterModel.setNum10(count);
                } else {

                }
            }

        }
        return buyerCenterModel;
    }



    /**
     * 保存用户信息     此方法仅为暂时解决之前应杰写的数据脏读问题，为了应付网站上线，后期还需全部改掉
     * @param member
     */
    public void saveMember(Member member,Member oldMember) throws CashException {

        if(member.getBalance().compareTo(oldMember.getBalance()) != 0){
            memberService.updateBuyerMemberBalanceInDb(member.getId(),member.getBalance().subtract(oldMember.getBalance()));
        }

        if(member.getAvailablelimit().compareTo(oldMember.getAvailablelimit()) != 0 ||
                member.getUsedlimit().compareTo(oldMember.getUsedlimit()) !=0){
            memberService.updateBuyerMemberCreditBalanceInDb(member.getId(),member.getUsedlimit().subtract(oldMember.getUsedlimit()),
                    member.getAvailablelimit().subtract(oldMember.getAvailablelimit()));
        }


        if(member.getSellerbanlance().compareTo(oldMember.getSellerbanlance()) != 0 ||
                member.getSellerfreezebanlance().compareTo(oldMember.getSellerfreezebanlance()) != 0 ||
                member.getGoodsbanlance().compareTo(oldMember.getGoodsbanlance()) != 0 ||
                member.getBillmoney().compareTo(oldMember.getBillmoney()) != 0){
            memberService.updateSellerMemberBalanceInDb(member.getId(),member.getSellerbanlance().subtract(oldMember.getSellerbanlance()),
                    member.getSellerfreezebanlance().subtract(oldMember.getSellerfreezebanlance()),
                    member.getGoodsbanlance().subtract(oldMember.getGoodsbanlance()),member.getBillmoney().subtract(oldMember.getBillmoney()));
        }


        if(member.getIntegrals().compareTo(oldMember.getIntegrals()) != 0 ||
                member.getAvailableintegral().compareTo(oldMember.getAvailableintegral()) != 0){
            memberService.updateSellerMemberBalanceInDb(member.getId(),member.getIntegrals().subtract(member.getIntegrals()),
                    member.getAvailableintegral().subtract(member.getAvailableintegral()));
        }



        Member newMember = memberService.getMemberById(member.getId());
        if(newMember.getBalance().compareTo(Quantity.BIG_DECIMAL_0) <0){
            throw  new CashException("买家余额不足");
        }


        if(newMember.getSellerbanlance().compareTo(Quantity.BIG_DECIMAL_0) <0){
            throw  new CashException("卖家余额不足");
        }

        if(newMember.getSellerfreezebanlance().compareTo(Quantity.BIG_DECIMAL_0) <0){
            throw  new CashException("卖家冻结金额不足");
        }

        if(newMember.getGoodsbanlance().compareTo(Quantity.BIG_DECIMAL_0) <0){
            throw  new CashException("卖家货款金额不足");
        }

        if(newMember.getIntegrals().compareTo(Quantity.BIG_DECIMAL_0) <0){
            throw  new CashException("买家积分不足");
        }

        if(newMember.getAvailableintegral().compareTo(Quantity.BIG_DECIMAL_0) <0){
            throw  new CashException("买家可用积分不足");
        }
    }


//    public void saveMember(Member member) {
//
//        memberMapper.updateByPrimaryKeySelective(member);
//    }




    /**
     * 获取买家收货地址列表
     *
     * @param id 买家id
     * @return
     */
    public List<ShippingAddress> getShippingAddress(Long id) {
        ShippingAddressExample shippingAddressExample = new ShippingAddressExample();
        shippingAddressExample.createCriteria().andMemberidEqualTo(id);
        List<ShippingAddress> list = shippingAddressMapper.selectByExample(shippingAddressExample);
        return list;
    }

    /**
     * 添加开票记录
     *
     * @param billingRecord
     */
    public void insertBillingRecord(BillingRecord billingRecord) {
        billingRecordMapper.insertSelective(billingRecord);
    }

    /**
     * 添加订单
     *
     * @param orders
     */
    public void insertOrders(Orders orders) {
        ordersMapper.insertSelective(orders);
    }


    /**
     * 批量插入订单商品
     *
     * @param list
     */
    public void insertAllOrderProduct(List<OrderProduct> list) {
        orderProductMapper.insertAll(list);
    }

    /**
     * 保存订单商品
     *
     * @param product
     */
    public void insertOrderProduct(OrderProduct product) {
        orderProductMapper.insertSelective(product);
    }


    /**
     * 批量插入订单
     *
     * @param list
     */
    public void insertAllOrders(List<Orders> list) {
        ordersMapper.insertAll(list);
    }

    /**
     * 批量插入买家资金表
     *
     * @param list
     */
    public void insertBuyerCapital(List<BuyerCapital> list) {
        buyerCapitalMapper.insertAll(list);
    }

    /**
     * 批量插入卖家资金表
     *
     * @param list
     */
    public void insertSallerCapital(List<SalerCapital> list) {
        salerCapitalMapper.insertAll(list);
    }

    /**
     * 获取订单
     *
     * @param id
     * @return
     */
    public Orders getSingleOrder(long id) {
        return ordersMapper.selectByPrimaryKey(id);
    }

    /**
     * 更新订单
     *
     * @param orders
     */
    public int updateSingleOrder(Orders orders) {
       return ordersMapper.updateByPrimaryKeySelective(orders);
    }

    /**
     * 根据订单编号和类型获取买家资金明细
     *
     * @param orderNo
     * @param type
     * @return
     */
    public BuyerCapital getBuyerCapitalByNoType(String orderNo, Short type) {
        BuyerCapitalExample buyerCapitalExample = new BuyerCapitalExample();
        buyerCapitalExample.createCriteria().andOrdernoEqualTo(orderNo).andCapitaltypeEqualTo(type);
        List<BuyerCapital> buyerCapitals = buyerCapitalMapper.selectByExample(buyerCapitalExample);
        if (buyerCapitals.size() > 0) {
            return buyerCapitals.get(0);
        } else {
            return null;
        }
    }


    /**
     * 修改订单中授信还款的状态
     *
     * @param memberid
     * @param orderIds
     * @param isbackcredit
     * @return
     */
    public int updateOrdersIsbackcredit(long memberid, String orderIds, Short isbackcredit) {
        return ordersMapper.updateOrdersIsbackcredit(memberid, orderIds, isbackcredit);
    }

    /**
     * 获取当前所有已支付订单的销售额
     *
     * @return
     */
    public BigDecimal getCurrentOrdersSumPay() {
        BigDecimal sumPay = new BigDecimal(0);
        List<Orders> list = ordersMapper.getCurrentOrdersSumPay();
        for (Orders od : list) {
            sumPay = sumPay.add(od.getActualpayment());
        }
        return sumPay;
    }

    /**
     * 所有订单金额
     *
     * @return
     */
    public BigDecimal getOrdersSum(OrderQueryParam param) {
        return ordersMapper.getOrdersSum(param);
    }

    /**
     * 获取销售总金额
     *
     * @return
     */
    public BigDecimal getOrderSellSum(OrderQueryParam param) {
        return ordersMapper.getOrderSellSum(param);
    }

    /**
     * 获取总订货量
     *
     * @return
     */
    public BigDecimal getOrdersTotalNum(OrderQueryParam param) {
        return ordersMapper.getOrdersTotalNum(param);
    }

    /**
     * 获取总发货量
     *
     * @return
     */
    public BigDecimal getOrdersTotalDeliveryNum(OrderQueryParam param) {
        return ordersMapper.getOrdersTotalDeliveryNum(param);
    }

    /**
     * 获取总的佣金
     *
     * @param param
     * @return
     */
    public BigDecimal getOrdersSumBroker(OrderQueryParam param) {
        return ordersMapper.getOrdersSumBroker(param);
    }

    /**
     * 计算卖家评分
     *
     * @param member
     */
    public void calculateSellerEvaScore(Member member, Short ev1, Short ev2, Short ev3) {

        EvaModel evaModel = ordersMapper.getProductEvaSum(member.getId());

        int number = ordersMapper.getProductEvaNum(member.getId());

        BigDecimal bigDecimalNum = new BigDecimal(number + 1);


        if (evaModel != null) {
            BigDecimal eva1 = new BigDecimal(evaModel.getEva1sum() + ev1).divide(bigDecimalNum, 2, RoundingMode.HALF_UP);

            BigDecimal eva2 = new BigDecimal(evaModel.getEva2sum() + ev2).divide(bigDecimalNum, 2, RoundingMode.HALF_UP);

            BigDecimal eva3 = new BigDecimal(evaModel.getEva3sum() + ev3).divide(bigDecimalNum, 2, RoundingMode.HALF_UP);

            member.setEva1(eva1);
            member.setEva2(eva2);
            member.setEva3(eva3);
        } else {
            member.setEva1(new BigDecimal(ev1));
            member.setEva2(new BigDecimal(ev2));
            member.setEva3(new BigDecimal(ev3));
        }

        memberMapper.updateByPrimaryKeySelective(member);

    }

    /**
     * 获取单个商口评分
     *
     * @param pdid
     * @return
     */
    public EvaPageModel getSingleProductEva(Long pdid) {
        EvaModel evaModel = ordersMapper.getSingleProductEvaSum(pdid);

        Long number = ordersMapper.getSingleProductEvaNum(pdid);

        BigDecimal bigDecimalNum = new BigDecimal(number);
        BigDecimal eva1 = new BigDecimal(0);
        BigDecimal eva2 = new BigDecimal(0);
        BigDecimal eva3 = new BigDecimal(0);
        if (evaModel != null && number != 0) {
            eva1 = new BigDecimal(evaModel.getEva1sum()).divide(bigDecimalNum, 1, RoundingMode.HALF_UP);
            eva2 = new BigDecimal(evaModel.getEva2sum()).divide(bigDecimalNum, 1, RoundingMode.HALF_UP);
            eva3 = new BigDecimal(evaModel.getEva3sum()).divide(bigDecimalNum, 1, RoundingMode.HALF_UP);
        }

        EvaPageModel evaPageModel = new EvaPageModel();
        evaPageModel.setEva1(eva1);
        evaPageModel.setEva2(eva2);
        evaPageModel.setEva3(eva3);
        return evaPageModel;
    }

    /**
     * 获取单个商口评价数
     *
     * @param pdid
     * @return
     */
    public Long getSingleProductEvaNum(Long pdid) {
        Long number = ordersMapper.getSingleProductEvaNum(pdid);
        return number;
    }

    /**
     * 获取单个商品的评价列表
     *
     * @param pageNo
     * @param pageSize
     * @param pdid
     * @return
     */
    public PageInfo getOrderProductByPdNo(int pageNo, int pageSize, Long pdid) {
        PageHelper.startPage(pageNo, pageSize);
        OrderProductExample orderProductExample = new OrderProductExample();
        orderProductExample.createCriteria().andPdidEqualTo(pdid).andEvaluatestateEqualTo(Quantity.STATE_1);
        PageInfo pageInfo = new PageInfo(orderProductMapper.selectByExample(orderProductExample));
        return pageInfo;
    }

    public PageInfo getProductEvaListByPdId(int pageNo, int pageSize, Long pdid) {
        PageHelper.startPage(pageNo, pageSize);
        PageInfo pageInfo = new PageInfo(orderProductMapper.getProductEvaListByPdId(pdid));
        return pageInfo;
    }

    /**
     * 后台获取商品评价
     *
     * @param orderQueryParam
     * @return
     */
    public PageInfo getOrderProductEva(OrderQueryParam orderQueryParam) {

        PageHelper.startPage(orderQueryParam.getPageNo(), orderQueryParam.getPageSize()).setOrderBy("evatime desc");
        OrderProductExample orderProductExample = new OrderProductExample();
        OrderProductExample.Criteria criteria = orderProductExample.createCriteria();

        criteria.andEvaluatestateEqualTo(Quantity.STATE_1);

        if (orderQueryParam.getSellerid() != null) {
            criteria.andSelleridEqualTo(orderQueryParam.getSellerid());
        }

        if (orderQueryParam.getStartTime() != null) {
            criteria.andEvatimeGreaterThanOrEqualTo(orderQueryParam.getStartTime());
        }
        if (orderQueryParam.getEndTime() != null) {
            Calendar c = Calendar.getInstance();
            c.setTime(orderQueryParam.getEndTime());
            c.add(Calendar.DAY_OF_MONTH, 1);// 今天+1天
            Date tomorrow = c.getTime();
            criteria.andEvatimeLessThanOrEqualTo(tomorrow);
        }
        if (StringUtils.hasText(orderQueryParam.getOrderNo())) {
            String orderNo = "%" + orderQueryParam.getOrderNo() + "%";
            criteria.andOrdernoLike(orderNo);
        }
        if (StringUtils.hasText(orderQueryParam.getSellerName())) {
            String sellerName = "%" + orderQueryParam.getSellerName() + "%";
            criteria.andSellernameLike(sellerName);
        }

        if (StringUtils.hasText(orderQueryParam.getMemberName())) {
            String memberName = "%" + orderQueryParam.getMemberName() + "%";
            criteria.andMembernameLike(memberName);
        }
        if (StringUtils.hasText(orderQueryParam.getPdName())) {
            String pdName = "%" + orderQueryParam.getPdName() + "%";
            criteria.andPdnameLike(pdName);
        }
        PageInfo pageInfo = new PageInfo(orderProductMapper.selectByExample(orderProductExample));
        return pageInfo;

    }



    /**
     * 获取发货记录
     *
     * @param param
     * @return
     */
    public PageInfo getDeliveryRecord(OrderQueryParam param) {
        PageHelper.startPage(param.getPageNo(), param.getPageSize()).setOrderBy("sellerdeliverytime desc");
        OrdersExample ordersExample = new OrdersExample();
        OrdersExample.Criteria criteria = ordersExample.createCriteria();
        criteria.andDeliverynoIsNotNull();
        if (param.getSellerid() != null) {
            criteria.andSaleidEqualTo(param.getSellerid());
        }
        if (param.getStartTime() != null) {
            criteria.andSellerdeliverytimeGreaterThanOrEqualTo(param.getStartTime());
        }
        if (param.getEndTime() != null) {
            Calendar c = Calendar.getInstance();
            c.setTime(param.getEndTime());
            c.add(Calendar.DAY_OF_MONTH, 1);// 今天+1天
            Date tomorrow = c.getTime();
            criteria.andSellerdeliverytimeLessThanOrEqualTo(tomorrow);
        }

        if (StringUtils.hasText(param.getDeliveryno())) {
            String no = "%" + param.getDeliveryno() + "%";
            criteria.andDeliverynoLike(no);
        }

        PageInfo pageInfo = new PageInfo(ordersMapper.selectByExample(ordersExample));
        return pageInfo;
    }

    /**
     * 清空评论
     *
     * @param orderProductId
     */
    public void deleteMemberEva(Long orderProductId) {
        OrderProduct orderProduct = orderProductMapper.selectByPrimaryKey(orderProductId);
        if (orderProduct != null) {
            orderProduct.setEvaluatestate(Quantity.STATE_0);
            orderProduct.setEva1(Quantity.STATE_0);
            orderProduct.setEva2(Quantity.STATE_0);
            orderProduct.setEva3(Quantity.STATE_0);
            orderProduct.setBuyersexperience(null);
            orderProduct.setEvatime(null);
            orderProductMapper.updateByPrimaryKeySelective(orderProduct);
        }
    }

    public OrderComplex getOrderComplex(OrderQueryParam param) {

        OrderComplex orderComplex = new OrderComplex();
        Date startTime = param.getStartTime();
        Date endTime = param.getEndTime();

        List<Date> datalist = getBetweenDates(startTime, endTime);
        //列表
        PageInfo pageInfo = getAllOrdersList(param);
        orderComplex.setPageInfo(pageInfo);

        //@ApiModelProperty(notes = "待付款")
        List<OrderStatisticModel> list1 = null;
        //@ApiModelProperty(notes = "待发货")
        List<OrderStatisticModel> list2 = null;
        //@ApiModelProperty(notes = "已完成")
        List<OrderStatisticModel> list3 = null;
        //@ApiModelProperty(notes = "已取消")
        List<OrderStatisticModel> list4 = null;
        //全部状态
        if (param.getOrderState() == null) {
            param.setOrderState(Quantity.STATE_0);
            list1 = ordersMapper.getStatisticOrdersNum(param);

            param.setEndTime(endTime);
            param.setOrderState(Quantity.STATE_1);
            list2 = ordersMapper.getStatisticOrdersNum(param);

            param.setEndTime(endTime);
            param.setOrderState(Quantity.STATE_5);
            list3 = ordersMapper.getStatisticOrdersNum(param);

            param.setEndTime(endTime);
            param.setOrderState(Quantity.STATE_7);
            list4 = ordersMapper.getStatisticOrdersNum(param);
        } else {
            if (param.getOrderState() == Quantity.STATE_0) {
                list1 = ordersMapper.getStatisticOrdersNum(param);
            } else if (param.getOrderState() == Quantity.STATE_1) {
                list2 = ordersMapper.getStatisticOrdersNum(param);
            } else if (param.getOrderState() == Quantity.STATE_5) {
                list3 = ordersMapper.getStatisticOrdersNum(param);
            } else if (param.getOrderState() == Quantity.STATE_7) {
                list4 = ordersMapper.getStatisticOrdersNum(param);
            }
        }

        for (Date date : datalist) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String formatDate = simpleDateFormat.format(date);

            if (list1 != null) {
                boolean flag = false;
                for (OrderStatisticModel orderStatisticModel : list1) {
                    if (orderStatisticModel.getOrdertime().equals(formatDate)) {
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    OrderStatisticModel orderStatisticModel = new OrderStatisticModel();
                    orderStatisticModel.setOrdernum("0");
                    orderStatisticModel.setOrdertime(formatDate);
                    list1.add(orderStatisticModel);
                }
            }
            if (list2 != null) {
                boolean flag = false;
                for (OrderStatisticModel orderStatisticModel : list2) {
                    if (orderStatisticModel.getOrdertime().equals(formatDate)) {
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    OrderStatisticModel orderStatisticModel = new OrderStatisticModel();
                    orderStatisticModel.setOrdernum("0");
                    orderStatisticModel.setOrdertime(formatDate);
                    list2.add(orderStatisticModel);
                }
            }
            if (list3 != null) {
                boolean flag = false;
                for (OrderStatisticModel orderStatisticModel : list3) {
                    if (orderStatisticModel.getOrdertime().equals(formatDate)) {
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    OrderStatisticModel orderStatisticModel = new OrderStatisticModel();
                    orderStatisticModel.setOrdernum("0");
                    orderStatisticModel.setOrdertime(formatDate);
                    list3.add(orderStatisticModel);
                }
            }
            if (list4 != null) {
                boolean flag = false;
                for (OrderStatisticModel orderStatisticModel : list4) {
                    if (orderStatisticModel.getOrdertime().equals(formatDate)) {
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    OrderStatisticModel orderStatisticModel = new OrderStatisticModel();
                    orderStatisticModel.setOrdernum("0");
                    orderStatisticModel.setOrdertime(formatDate);
                    list4.add(orderStatisticModel);
                }
            }
        }

        if (list1 != null) {
            Collections.sort(list1, new Comparator<OrderStatisticModel>() {
                public int compare(OrderStatisticModel arg0, OrderStatisticModel arg1) {
                    return arg0.getOrdertime().compareTo(arg1.getOrdertime());
                }
            });
        }
        if (list2 != null) {
            Collections.sort(list2, new Comparator<OrderStatisticModel>() {
                public int compare(OrderStatisticModel arg0, OrderStatisticModel arg1) {
                    return arg0.getOrdertime().compareTo(arg1.getOrdertime());
                }
            });
        }
        if (list3 != null) {
            Collections.sort(list3, new Comparator<OrderStatisticModel>() {
                public int compare(OrderStatisticModel arg0, OrderStatisticModel arg1) {
                    return arg0.getOrdertime().compareTo(arg1.getOrdertime());
                }
            });
        }
        if (list4 != null) {
            Collections.sort(list4, new Comparator<OrderStatisticModel>() {
                public int compare(OrderStatisticModel arg0, OrderStatisticModel arg1) {
                    return arg0.getOrdertime().compareTo(arg1.getOrdertime());
                }
            });
        }

        orderComplex.setList1(list1);
        orderComplex.setList2(list2);
        orderComplex.setList3(list3);
        orderComplex.setList4(list4);

        return orderComplex;
    }

    /**
     * 获取两个日期之间的日期
     *
     * @param start 开始日期
     * @param end   结束日期
     * @return 日期集合
     */
    private List<Date> getBetweenDates(Date start, Date end) {
        List<Date> result = new ArrayList<Date>();
        Calendar tempStart = Calendar.getInstance();
        tempStart.setTime(start);


        Calendar tempEnd = Calendar.getInstance();
        tempEnd.setTime(end);
        while (tempStart.before(tempEnd)) {
            result.add(tempStart.getTime());
            tempStart.add(Calendar.DAY_OF_YEAR, 1);
        }
        result.add(tempEnd.getTime());
        return result;
    }

    public void updateReason(Orders orders, String reason) {
        ordersMapper.updateReason(orders.getId(), reason);
    }


    /**
     * 获取某个商品在订单中全款的金额
     *
     * @param orderno
     * @param pdid
     * @return
     */
    public BigDecimal getAllpayByOrderNoAndPdidAndPdNo(String orderno, Long pdid, String pdno) {
        return orderProductMapper.getAllpayByOrderNoAndPdidAndPdNo(orderno, pdid, pdno);
    }

    /**
     * @param orderno
     * @param pdid
     * @param pdno
     * @return
     */
    public List<OrderProduct> getOrderProdByOrderNoAndPdidAndPdNo(String orderno, Long pdid, String pdno) {
        return orderProductMapper.getOrderProdByOrderNoAndPdidAndPdNo(orderno, pdid, pdno);
    }


    /**
     * 获取超时未付款的限时购订单
     * @param timedoutofpayment
     * @return
     */
    public List<Orders> getNotPayMoneyLimitOrders(int timedoutofpayment){
        return  ordersMapper.getNotPayMoneyLimitOrders(timedoutofpayment);
    }


    /**
     * 订单验货完成需要更新的数据
     * @param orders
     * @return
     */
    public int updateOrdersConfirmgoods(Orders orders){
        return  ordersMapper.updateOrdersConfirmgoods(orders);
    }


    /**
     * 取消订单的几种类型：
     * 1. 立即发货、待发货状态
     * 2. 远期全款  备货
     * 3. 远期定金  备货
     * 4. 预订代发  备货完成
     */
    public  boolean canCancelOrders(Orders orders){
        boolean b = false;

        if(orders.getOrdertype() == Quantity.STATE_0 && orders.getOrderstatus() == Quantity.STATE_1){  //立即发货、待发货状态
            b = true;
        }else if(orders.getOrdertype() == Quantity.STATE_1 && orders.getOrderstatus() == Quantity.STATE_8){//远期全款  备货状态
            b = true;
        }else if(orders.getOrdertype() == Quantity.STATE_3 && orders.getOrderstatus() == Quantity.STATE_8){//远期定金  备货状态
            b = true;
        }else if(orders.getOrdertype() == Quantity.STATE_3 && orders.getOrderstatus() == Quantity.STATE_9){//远期定金  备货完成
            b = true;
        }else if(orders.getOrdertype() == Quantity.STATE_3 && orders.getOrderstatus() == Quantity.STATE_1){ //远期订单已付尾款，等待卖家发货
            b = true;
        }

        return  b;
    }

    /**
     * 发货
     */
    public int sendOutProduct(Orders orders, String logisticscompany, String couriernumber) {
        if (!StringUtils.isEmpty(logisticscompany)) {
            orders.setLogisticscompany(logisticscompany);
        }
        if (!StringUtils.isEmpty(couriernumber)) {
            orders.setCouriernumber(couriernumber);
        }
        orders.setDeliveryno(GenerateNo.getInvoiceNo());
        orders.setOrderstatus(Quantity.STATE_3);

        orders.setSellerdeliverytime(new Date());
        return ordersMapper.updateByPrimaryKeySelective(orders);
    }


    /**
     * 取消订单的几种类型：
     * 1. 立即发货、待发货状态
     * 2. 远期全款  备货完成、代发货状态
     * 3. 远期定金  备货
     * 4. 预订代发  备货完成
     */
    public  boolean canCancelOrdersSeller(Orders orders){
        boolean b = false;

        if(orders.getOrdertype() == Quantity.STATE_0 && orders.getOrderstatus() == Quantity.STATE_1){  //立即发货、待发货状态
            b = true;
        }else if(orders.getOrdertype() == Quantity.STATE_1 && (orders.getOrderstatus()==Quantity.STATE_8 || orders.getOrderstatus() == Quantity.STATE_1)){//远期全款  备货状态
            b = true;
        }else if(orders.getOrdertype() == Quantity.STATE_3 && orders.getOrderstatus() == Quantity.STATE_1){ //远期订单已付尾款，等待卖家发货
            b = true;
        }

        return  b;
    }




    public OrderProductBack getBackgoodsOrderProductBack(ProductBackModel productBackModel){

        OrderProductBack orderProductBack = this.getOrderProductBackById(productBackModel.getId());
        if(orderProductBack == null) return null;


        if (orderProductBack != null) {
            if(productBackModel.getState() != null) {
                orderProductBack.setState(productBackModel.getState());
            }

            if (productBackModel.getAdminstate() != null) {
                orderProductBack.setAdminstate(productBackModel.getAdminstate());
            }
            if (StringUtils.hasText(productBackModel.getLogisticsno())) {
                orderProductBack.setLogisticsno(productBackModel.getLogisticsno());
            }
            if (StringUtils.hasText(productBackModel.getLogisticscompany())) {
                orderProductBack.setLogisticscompany(productBackModel.getLogisticscompany());
            }
            if (StringUtils.hasText(productBackModel.getBackaddress())) {
                orderProductBack.setBackaddress(productBackModel.getBackaddress());
            }
            if (StringUtils.hasText(productBackModel.getContact())) {
                orderProductBack.setContact(productBackModel.getContact());
            }
            if (StringUtils.hasText(productBackModel.getContactphone())) {
                orderProductBack.setContactphone(productBackModel.getContactphone());
            }
            if (StringUtils.hasText(productBackModel.getSellernotagree())) {
                orderProductBack.setSellernotagree(productBackModel.getSellernotagree());
            }
            if (StringUtils.hasText(productBackModel.getAdminreason())) {
                orderProductBack.setAdminreason(productBackModel.getAdminreason());
            }
            if (StringUtils.hasText(productBackModel.getProvince())) {
                orderProductBack.setProvince(productBackModel.getProvince());
            }
            if (StringUtils.hasText(productBackModel.getCity())) {
                orderProductBack.setCity(productBackModel.getCity());
            }
            if (StringUtils.hasText(productBackModel.getArea())) {
                orderProductBack.setArea(productBackModel.getArea());
            }

            if (StringUtils.hasText(productBackModel.getPic())) {
                orderProductBack.setPic(productBackModel.getPic());
            }
            if (StringUtils.hasText(productBackModel.getBackexplain())) {
                orderProductBack.setBackexplain(productBackModel.getBackexplain());
            }
        }

        return orderProductBack;
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
    public BigDecimal countSinglePdFight(ProductInfo productInfo, ProductStore productStore, String uprovince, String ucity, BigDecimal pdnum) {

        BigDecimal weight = productStore.getWeight();
        long freightmode = productStore.getFreightmode();
        if (freightmode != Quantity.STATE_) {
            //获取商品运费模板
            ShippingTemplates shippingTemplates = this.getShippingTemp(freightmode);
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
            AreaCost areaCost = this.getAreaCost(freightmode);

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
            return BigDecimal.valueOf(0);
        }
        return BigDecimal.valueOf(0);
    }



    /**
     * 计算运费
     * @param defaultfreight   默认运费公斤
     * @param defaultcost      默认运费
     * @param perkilogramadded 每增加公斤
     * @param perkilogramcost  每增加运费
     * @param totalweight      商品总重量
     * @return 每个商品的运费
     */
    private BigDecimal getTotalCost(BigDecimal defaultfreight, BigDecimal defaultcost, BigDecimal perkilogramadded, BigDecimal perkilogramcost, BigDecimal totalweight) {

        BigDecimal totalCost = BigDecimal.valueOf(0);
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
            if (results[0].compareTo(BigDecimal.valueOf(0)) == Quantity.INT_0) {
                totalCost = defaultcost.add(perkilogramcost);
            } else {
                BigDecimal multiplycost = results[0].multiply(perkilogramcost);
                //如果没有余数
                if (results[1].compareTo(BigDecimal.valueOf(0)) == Quantity.INT_0) {
                    totalCost = multiplycost.add(defaultcost);
                } else {
                    //如果有余数
                    totalCost = multiplycost.add(perkilogramcost).add(defaultcost);
                }
            }
        }
        return totalCost;
    }


    /**
     * 分页查询卖家已完成需要向平台开票的订单
     * @param salerid
     * @param pageNo
     * @param pageSize
     * @return
     */
    public PageInfo getWaitOpenBillListByPage(Long salerid,int pageNo,int pageSize){
        PageHelper.startPage(pageNo,pageSize);
        return  new PageInfo(ordersMapper.getWaitOpenBillList(salerid));
    }


    /**
     * 查询订单
     * @param ids
     * @return
     */
    public List<Orders> getOrdersByIds(Long[] ids){
        return  ordersMapper.getOrdersByIds(ids);
    }


    public int updateByExampleSelective( Orders record,  OrdersExample example){
        return  ordersMapper.updateByExampleSelective(record,example);
    }

    /**
     * 获取昨天0点到24点之间付款，且状态正常的订单；
     * @return
     */
    public List<Orders> getRegularOrders(Date starttime,Date endtime) {
        return  ordersMapper.getRegularOrders(starttime,endtime);
    }


    @Autowired
    private JinShangSms jinShangSms;

    @Autowired
    private SmsTemplateService smsTemplateService;

    @Autowired
    private SmsLogMapper smsLogMapper;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 买家下单后短信通知卖家
     * @param list
     */
    @Profile({"pro"})
    public  void smsNotifySellerToOrders(List<Orders> list){
        SmsTemplate smsTemplate = smsTemplateService.getBySendCode("NotifySellerToOrders");
        if(smsTemplate == null){
            logger.error("NotifySellerToOrders短信模板不存在");
            return;
        }

        list.forEach(orders  -> {
            cachedThreadPool.execute(()->{
                Member member = memberService.getMemberById(orders.getSaleid());

                SellerCompanyInfo sellerCompanyInfo = sellerCompanyInfoMapper.getSellerCompanyByMemberid(member.getId());
                String mobile = member.getMobile();
                // mobile = "18663868251";
                if(sellerCompanyInfo.getSmsnotify() == Quantity.STATE_1 && StringUtils.hasText(mobile)){

                    Map<String,String> map = new HashMap<>();
                    map.put("orderno",orders.getOrderno());
                    SmsLog smsLog = new SmsLog();
                    smsLog.setCreatedate(new Date());
                    smsLog.setMobile(mobile);
                    smsLog.setType("卖家新订单通知");
                    smsLog.setMemberid(member.getId());
                    String content = smsTemplate.getContent();
                    smsLog.setDescription(content.replaceAll("\\$\\{orderno\\}",orders.getOrderno()));

                    if(jinShangSms.send(mobile,smsTemplate.getTemplatecode(),map)) {
                        smsLog.setStates(Quantity.STATE_1);
                    }else{
                        smsLog.setStates(Quantity.STATE_2);
                    }

                    smsLogMapper.insert(smsLog);
                }
            });
        });
    }


    @Profile({"pro"})
    public  void smsNotifySellerToBackOrders(List<Orders> list){
        SmsTemplate smsTemplate = smsTemplateService.getBySendCode("NotifySellerToBackOrders");
        if(smsTemplate == null){
            logger.error("NotifySellerToBackOrders短信模板不存在");
            return;
        }

        list.forEach(orders  -> {
            cachedThreadPool.execute(()->{
                Member member = memberService.getMemberById(orders.getSaleid());

                SellerCompanyInfo sellerCompanyInfo = sellerCompanyInfoMapper.getSellerCompanyByMemberid(member.getId());

                String mobile = member.getMobile();
                // mobile = "18663868251";
                if(sellerCompanyInfo.getSmsnotify() == Quantity.STATE_1 && StringUtils.hasText(mobile)){

                    Map<String,String> map = new HashMap<>();
                    map.put("orderno",orders.getOrderno());

                    SmsLog smsLog = new SmsLog();
                    smsLog.setCreatedate(new Date());
                    smsLog.setMobile(mobile);
                    smsLog.setType("买家退货通知");
                    smsLog.setMemberid(member.getId());
                    String content = smsTemplate.getContent();
                    smsLog.setDescription(content.replaceAll("\\$\\{orderno\\}",orders.getOrderno()));

                    if(jinShangSms.send(mobile,smsTemplate.getTemplatecode(),map)) {
                        smsLog.setStates(Quantity.STATE_1);
                    }else{
                        smsLog.setStates(Quantity.STATE_2);
                    }

                    smsLogMapper.insert(smsLog);
                }
            });
        });
    }


    /**
     *向中间件管理平台post数据
     * @author xiazy
     * @date  2018/6/5 15:03
     * @param url
     * @param params
     * @return void
     */
    private void sendToMiddleManage(String url, Map<String, String> params) {
        cachedThreadPool.execute(()->{
            try {
                Map<String,Object> ret=HttpClientUtils.post(url,params);
                String returnjson=JsonUtil.toJson(ret);
                ApiRecord apiRecord=new ApiRecord();
                apiRecord.setAppid(params.get("appid"));
                apiRecord.setAppurl(url);
                apiRecord.setApicode(params.get("type"));
                apiRecord.setContent(JsonUtil.toJson(params).toString().replace("",""));
                apiRecord.setReturnjson(returnjson);
                apiRecord.setCreatetime(new Date());
                apiRecordService.insertSelective(apiRecord);
            } catch (Exception e) {
                logger.error(e.getMessage(),e);
            }
        });
    }



    /**
     * 计算订单的佣金
     * @param list
     */
    public void jisuanOrdersBreakPay(List<String> list){
        list.forEach(orderno->{
            Orders orders = this.getOrdersByOrderNo(orderno);
            List<OrderProduct> orderProductList = this.getOrderProductByOrderId(orders.getId());

            BigDecimal totalBroke = Quantity.BIG_DECIMAL_0;
            BigDecimal totalServerPay = Quantity.BIG_DECIMAL_0;

            Predicate<OrderProduct> orderProductPredicate = (n) -> n.getBackstate()==0;

            List<OrderProduct> orderProducts = orderProductList.stream().filter(orderProductPredicate).collect(Collectors.toList());
            for(OrderProduct orderProduct : orderProducts){
                Long classifyid = orderProduct.getClassifyid();
                BigDecimal brolerRate = this.getSellerBrokeragerate(orders.getSaleid(),orderProduct.getClassifyid());

                //服务费比率 用的是category中设置的
                BigDecimal serverRate = categoriesService.getServerRate(classifyid).multiply(BigDecimal.valueOf(0.01));

                //商品单位佣金=销售单价*商品佣金比例
                BigDecimal singlebrokepay = orderProduct.getPrice().multiply(brolerRate);

                //商品佣金=商品单位佣金*商品数量
                //BigDecimal broker = (orderProduct.getActualpayment().subtract(orderProduct.getFreight())).multiply(brolerRate);
                BigDecimal broker = singlebrokepay.multiply(orderProduct.getNum());

                OrderProduct op = new OrderProduct();
                op.setId(orderProduct.getId());
                op.setSinglebrokepay(singlebrokepay);
                op.setBrokepay(broker);
                this.updateOrderProduct(op);

                BigDecimal servepay = (orderProduct.getActualpayment().subtract(orderProduct.getFreight())).multiply(serverRate);
                totalBroke = totalBroke.add(broker);
                totalServerPay = totalServerPay.add(servepay);
            }

            Orders orders1 = new Orders();
            orders1.setId(orders.getId());
            orders1.setBrokepay(totalBroke);
            orders1.setServerpay(totalServerPay);
            this.updateSingleOrder(orders1);
        });
    }


    /**
     * 获取商家某个分类商品的佣金比率
     * @param sellerid
     * @param classifyid
     * @return
     */
    private  BigDecimal getSellerBrokeragerate(Long sellerid,Long classifyid){

        BigDecimal rate = Quantity.BIG_DECIMAL_0;
        BigDecimal brolerRate = Quantity.BIG_DECIMAL_0;

        //商家分类
        SellerCategory sellerCategory = this.getSellerCategory(classifyid, sellerid);
        if (sellerCategory != null) {
            if (sellerCategory.getBrokeragerate().compareTo(new BigDecimal(-1)) == 0) {
                SellerCategory sellerCategory1 = this.getSellerCategory(sellerCategory.getParentid(), sellerid);
                if (sellerCategory1 != null) {
                    if (sellerCategory1.getBrokeragerate().compareTo(new BigDecimal(-1)) == 0) {
                        SellerCategory sellerCategory2 = this.getSellerCategory(sellerCategory1.getParentid(), sellerid);
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

        Categories categories = categoriesService.getById(classifyid);
        if (categories != null) {
            //佣金比率
            brolerRate =rate.multiply(BigDecimal.valueOf(0.01));
            if(brolerRate.compareTo(BigDecimal.valueOf(0))<0){
                brolerRate = categoriesService.getBrokerRate(classifyid).multiply(BigDecimal.valueOf(0.01));
            }
        }

        return brolerRate;
    }

    /**
     * 买家再次购买
     */
    public Map<String, Object> repurchase(String orderNo, Member member,HttpServletRequest request) {

        Map<String, Object> retmap = new HashMap<>();
        List<OrderProduct> orderProductsList = orderProductMapper.getRepurchaseList(orderNo);
        List<ShopCar> successfulList = new ArrayList<>();//记录成功
        List<String> offErrorList = new ArrayList<>();//记录商品下架
        List<String> lackErrorList = new ArrayList<>();//记录库存不足

        for (OrderProduct orderProduct : orderProductsList) {
            ShopCar shopCar = new ShopCar();
            String alertMsg = "";
            ProductInfo productInfo = productInfoMapper.selectByPrimaryKey(orderProduct.getPdid());
            ProductStore productStore = productStoreMapper.selectByPrimaryKey(orderProduct.getPdid());
            Orders orders = ordersMapper.getOrdersByOrderNo(orderNo);
//            //如果商品已上架、有相应库存并且购物车为空则加入购物车
//            if (productInfo.getPdstate() == Quantity.STATE_4 && orderProduct.getNum().compareTo(productStore.getPdstorenum()) <= 0) {
//                if (shopCar == null) {
                    shopCar.setPdid(orderProduct.getPdid());
                    shopCar.setPdno(orderProduct.getPdno());
                    shopCar.setLimitid(orderProduct.getLimitid());
                    shopCar.setPdnumber(orderProduct.getNum());
                    shopCar.setStoreid(orderProduct.getStoreid());
                    shopCar.setDelivertime(orderProduct.getDeliverytime());
                    shopCar.setUnit(orderProduct.getUnit());
                    shopCar.setProtype(orderProduct.getProtype());
                    shopCar.setIsonline(orders.getIsonline());
//                    shopCarMapper.insertSelective(shopCar);
//                } else {
//                    shopCarMapper.updateByPrimaryKeySelective(shopCar);
//                }
//                successfulList.add(orderProduct);
//
//                //商品已下架
//            }else if (productInfo.getPdstate() != Quantity.STATE_4){
//
//                offErrorList.add(orderProduct);
//
//                //商品库存不足
//            }else if(orderProduct.getNum().compareTo(productStore.getPdstorenum()) > 0){
//                lackErrorList.add(orderProduct);
//
//            }
            if (shopCar.getIsonline() == null) {
                shopCar.setIsonline(Quantity.STATE_0);
            }
            BasicRet basicRet = new BasicRet();
            //正常订单
            if (shopCar.getIsonline() == Quantity.STATE_0) {
                //判断商品是否下架
                ProductInfo info = productInfoMapper.selectByPrimaryKey(shopCar.getPdid());
                //判断买家卖家是否是同一人
                Long sellerId = info.getMemberid();
                if (sellerId == member.getId()) {
                    continue;
                }
                if (info != null) {
                    shopCar.setSaleid(info.getMemberid());
                    if (info.getProducttype().equals("紧固件")) {
                        shopCar.setProducttype(Quantity.STATE_1);
                    } else {
                        shopCar.setProducttype(Quantity.STATE_2);
                    }


                    BigDecimal convertNum = shopCar.getPdnumber();
                    String convertUnit = shopCar.getUnit();
                    if (AppConstant.FASTENER_PRO_TYPE.equals(info.getProducttype())) {
                        Map<String, Object> map = JinshangUtils.toLowerUnit(info.getPackagetype(), shopCar.getPdnumber(), shopCar.getUnit());
                        convertNum = (BigDecimal) map.get("num");
                        convertUnit = (String) map.get("unit");
                    }
                    //商品没有上架A
                    if (info.getPdstate() != Quantity.STATE_4) {
                        alertMsg = "下架:" + info.getProductname() + info.getLevel3() + info.getStand() + info.getMaterial() + "/" + info.getCardnum() + "/" + info.getSurfacetreatment() + "已下架";
                        offErrorList.add(alertMsg);
                        continue;
                    }
                    ShopCar shopCar1 = shopCarService.getMemberShopCar(shopCar.getPdid(), member.getId(), shopCar.getDelivertime(), shopCar.getPdno(), shopCar.getProtype());
                    if (shopCar1 != null) {
                        convertNum = convertNum.add(shopCar1.getPdnumber());
                    }
                    //判断库存
                    productStore = shopCarService.getProductStore(shopCar.getPdid(), shopCar.getPdno(), shopCar.getStoreid());


                    if (productStore != null && productStore.getMinplus() != null && productStore.getMinplus().compareTo(Quantity.BIG_DECIMAL_0) > 0) {

                        if (!this.checkBuyNum(convertNum, productStore.getMinplus())) {
//                            basicRet.setMessage("购买量必须是加购量的倍数");
//                            basicRet.setResult(BasicRet.ERR);
//                            return basicRet;
                            continue;
                        }
                    }


                    shopCar.setStorename(productStore.getStorename());
                    shopCar.setStoreid(productStore.getStoreid());
                    if (productStore != null) {
                        //库存不足
                        if ((productStore.getPdstorenum().compareTo(convertNum)) == Quantity.STATE_) {
//                            basicRet.setResult(BasicRet.ERR);
//                            basicRet.setMessage("库存不足");
//                            return basicRet;
                            alertMsg = "库存不足:" + info.getProductname() + info.getLevel3() + info.getStand() + info.getMaterial() + "/" + info.getCardnum() + "/" + info.getSurfacetreatment() + "库存不足";
                            lackErrorList.add(alertMsg);
                            continue;
                        }
                        //小于起定量
                        if (productStore.getStartnum().compareTo(convertNum) == Quantity.STATE_1) {
//                            basicRet.setResult(BasicRet.ERR);
//                            basicRet.setMessage("小于起定量");
//                            return basicRet;
                            continue;
                        }
                    } else {
                        continue;
                    }
                    boolean flag = false;
                    //计算阶梯价格
                    BigDecimal salePrice = updatePriceByNum(convertNum, productStore, shopCar.getDelivertime(), info.getMemberid(), info.getLevel3id(), member.getGradleid());
                    //远期定金和全款计算
                    BigDecimal allpap = salePrice.multiply(convertNum);
                    //如果是远期，需计算远期定金，全款和余额
                    if (shopCar.getProtype() != Quantity.STATE_0) {
                        if (shopCar.getProtype() == Quantity.STATE_1) {
                            //全款
                            shopCar.setAllpay(allpap.multiply(allPayRate));
                        } else {
                            //定金
                            shopCar.setPartpay(allpap.multiply(transactionSettingService.getTransactionSetting().getRemotepurchasingmargin().multiply(new BigDecimal(0.01))));
                            //余款
                            shopCar.setYupay(allpap.subtract(shopCar.getPartpay()));
                        }
                    }
                    List<ProductAttr> productAttrs=productAttrMapper.getListByPidAndPdno(shopCar.getPdid(), shopCar.getPdno());
                    //判断购物车里是否有该商品
                    if (shopCar1 == null) {
                        shopCar.setMemberid(member.getId());
                        shopCar.setPrice(salePrice);
                        shopCar.setUnit(convertUnit);
                        shopCar.setPdnumber(convertNum);
                        shopCar.setFrightmode(productStore.getFreightmode());
                        StringBuffer sb = new StringBuffer();
                        for (ProductAttr attr : productAttrs) {
                            sb.append(attr.getValue()).append("*");
                        }
                        if (productAttrs.size() > 0) {
                            sb.deleteCharAt(sb.length() - 1);
                        }
                        shopCar.setAttrjson(sb.toString());
                        shopCarService.insertShopCar(shopCar);
                    } else {
                        shopCar1.setPdnumber(convertNum);
                        shopCar1.setUnit(convertUnit);
                        shopCar1.setPrice(salePrice);
                        shopCar1.setFrightmode(productStore.getFreightmode());
                        if (shopCar.getProtype() != Quantity.STATE_0) {
                            BigDecimal appPap = salePrice.multiply(shopCar1.getPdnumber());
                            shopCar1.setProtype(shopCar.getProtype());
                            //全款
                            shopCar1.setAllpay(appPap.multiply(allPayRate));
                            //定金
                            shopCar1.setPartpay(appPap.multiply(transactionSettingService.getTransactionSetting().getRemotepurchasingmargin().multiply(new BigDecimal(0.01))));
                            //余款
                            shopCar1.setYupay(appPap.subtract(shopCar1.getPartpay()));
                        }
                        shopCarService.updateShopCar(shopCar1);
                    }
                    successfulList.add(shopCar);
                    //保存用户日志
                    memberLogOperator.saveMemberLog(member, null, "新增商品到购物车", "/rest/buyer/shopcar/insertShopCar", request, memberOperateLogService);
                } else {
                    continue;
                }
            }
        }
        if (successfulList.size() > 0) {
            retmap.put("successfulList", successfulList);
        }
        if (offErrorList.size() > 0) {
            retmap.put("offErrorList", offErrorList);
        }
        if (lackErrorList.size() > 0) {
            retmap.put("lackErrorList", lackErrorList);
        }
        return retmap;
    }

    private boolean checkBuyNum(BigDecimal buynum,BigDecimal minplus){
        try {
            BigDecimal a = buynum.divide(minplus);

            //System.out.println(a.intValue());

            if(new BigDecimal(a.intValue()).compareTo(a) != 0){
                return false;
            }

            return  true;
        } catch (Exception e) {
            return  false;
        }
    }

    private BigDecimal updatePriceByNum(BigDecimal num, ProductStore productStore, @NotNull String diliverTime, long sellerid, long levelid, long gradeid) {

        //计算阶梯价格
        BigDecimal basicPrice = new BigDecimal(0);

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
        BigDecimal saleprice = new BigDecimal(0);
        //判断是否开启阶梯价格
        if (productStore.getStepwiseprice()) {
            Gson gson = new Gson();
            List<StepWisePrice> list = gson.fromJson(productStore.getIntervalprice(), new TypeToken<ArrayList<StepWisePrice>>() {
            }.getType());
            //是否匹配价格区间
            boolean flag = false;
            //最大价格区间百分比
            BigDecimal lastPercent = new BigDecimal(0);
            BigDecimal maxstart = new BigDecimal(0);
            //匹配价格区间
            for (StepWisePrice stepWisePrice : list) {
                BigDecimal start = stepWisePrice.getStart();
                BigDecimal end = stepWisePrice.getEnd();
                BigDecimal percent = stepWisePrice.getRate();
                //end为0是最大价格区间
                if (end.compareTo(new BigDecimal(0)) == 0) {
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

    /**
     *订单下达主动
     * @author xiazy
     * @date  2018/6/5 10:50
     * @param orderIds 订单id
     * @return void
     */
    public BasicRet initiativeOrderIssue(String orderIds){
        BasicRet basicRet=new BasicRet();
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("订单下达成功");
        List<Orders> ordersList=this.getWMSOrdersByInIds(orderIds);
        for (Orders order :ordersList) {
            Long sellerId=order.getSaleid();
            Map<String,Object> retmap=verification(sellerId);
            if (((BasicRet)retmap.get("basicRet")).getResult()!=1){
                return (BasicRet)retmap.get("basicRet");
            }
            SellerCompanyInfo sellerCompanyInfo= (SellerCompanyInfo) retmap.get("sellerCompanyInfo");
            String appId=sellerCompanyInfo.getAppid();
            String appSecret=sellerCompanyInfo.getAppsecret();
            String appUrl=sellerCompanyInfo.getAppurl();
            Long timestamp=System.currentTimeMillis();
            String notify=MD5Tools.MD5(appId+appSecret+timestamp);
            List<OrderProduct> orderProductList=this.getOrderProductByOrderId(order.getId());
            List<SaleDetail> saleDetailList=new ArrayList<>();
            Js001 js001=new Js001();
            js001.setAppid(appId);
            js001.setNotify(notify);
            js001.setType(DockType.JS001.getType());
            js001.setOrderno(order.getOrderno());
            js001.setShipto(order.getShipto());
            js001.setReceivingaddress(order.getReceivingaddress());
            js001.setPhone(order.getPhone());
            //备用参数json暂时为空
//            js001.setExtendjson(new ExtendParam());
            js001.setExtendjson(null);
            js001.setTimestamp(String.valueOf(timestamp));
            orderProductList.parallelStream().forEach(orderProduct ->{
                SaleDetail saleDetail=new SaleDetail();
                saleDetail.setPdno(orderProduct.getPdno());
                saleDetail.setBuynum(orderProduct.getNum());
                saleDetail.setValuationnum(orderProduct.getNum());
                saleDetail.setPrice(orderProduct.getPrice());
                saleDetailList.add(saleDetail);
            });
            js001.setSalesjson(JsonUtil.toJson(saleDetailList));
            String jsonParam=JsonUtil.toJson(js001);
            sendToMiddleManage(appUrl,JsonUtil.toMap(jsonParam));
        }
        return basicRet;
    }

    /**
     *订单取消主动
     * @author xiazy
     * @date  2018/6/4 17:48
     * @param orderId 订单编码
     * @return void
     */
    public BasicRet initiativeOrderCancel(Long orderId){
        BasicRet basicRet=new BasicRet();
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("订单取消成功");
        Orders order=this.getWMSOrdersByInIds(String.valueOf(orderId)).get(0);
        Long sellerId=order.getSaleid();
        Map<String,Object> retmap=verification(sellerId);
        if (((BasicRet)retmap.get("basicRet")).getResult()!=1){
            return (BasicRet)retmap.get("basicRet");
        }
        SellerCompanyInfo sellerCompanyInfo= (SellerCompanyInfo) retmap.get("sellerCompanyInfo");
        String appId=sellerCompanyInfo.getAppid();
        String appSecret=sellerCompanyInfo.getAppsecret();
        String appUrl=sellerCompanyInfo.getAppurl();
        Long timestamp=System.currentTimeMillis();
        String notify=MD5Tools.MD5(appId+appSecret+timestamp);
        Js003 js003=new Js003();
        js003.setAppid(appId);
        js003.setNotify(notify);
        js003.setType(DockType.JS003.getType());
        js003.setOrderno(order.getOrderno());
        js003.setCanceltype(DockType.QX001.getType());
        //备用参数json暂时为空
//        js003.setExtendjson(new ExtendParam());
        js003.setExtendjson(null);
        js003.setTimestamp(String.valueOf(timestamp));
        String jsonParam=JsonUtil.toJson(js003);
        sendToMiddleManage(appUrl,JsonUtil.toMap(jsonParam));
        return basicRet;
    }

    /**
     *退货(主动)
     * @author xiazy
     * @date  2018/6/5 16:31
     * @param backId 退货单id
     * @return mizuki.project.core.restserver.config.BasicRet
     */
    public  BasicRet initiativeOrderReturn(Long backId){
        BasicRet basicRet=new BasicRet();
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("退货成功");
        OrderProductBack orderProductBack =this.getOrderProductBackById(backId);
        Long sellerId=orderProductBack.getSellerid();
        Map<String,Object> retmap=verification(sellerId);
        if (((BasicRet)retmap.get("basicRet")).getResult()!=1){
            return (BasicRet)retmap.get("basicRet");
        }
        SellerCompanyInfo sellerCompanyInfo= (SellerCompanyInfo) retmap.get("sellerCompanyInfo");
        String appId=sellerCompanyInfo.getAppid();
        String appSecret=sellerCompanyInfo.getAppsecret();
        String appUrl=sellerCompanyInfo.getAppurl();
        Long timestamp=System.currentTimeMillis();
        String notify=MD5Tools.MD5(appId+appSecret+timestamp);
        Js004 js004=new Js004();
        js004.setAppid(appId);
        js004.setNotify(notify);
        js004.setType(DockType.JS004.getType());
        js004.setOrderno(orderProductBack.getOrderno());
        js004.setBackno(orderProductBack.getBackno());
        js004.setContact(orderProductBack.getContact());
        js004.setBackaddress(orderProductBack.getBackaddress());
        js004.setContactphone(orderProductBack.getContactphone());
        BackDetail backDetail=new BackDetail();
        backDetail.setPdno(orderProductBack.getPdno());
        backDetail.setBacknum(orderProductBack.getPdnum());
        backDetail.setValuationnum(orderProductBack.getPdnum());
        OrderProduct orderProduct=orderProductMapper.selectByPrimaryKey(orderProductBack.getOrderpdid());
        backDetail.setPrice(orderProduct.getPrice());
        backDetail.setBackreason(orderProductBack.getReturnbackreason());
        backDetail.setExtendjson(new ExtendParam());
        backDetail.setTimestamp(orderProductBack.getCreatetime().getTime());
        js004.setBackjson(JsonUtil.toJson(backDetail));
        //备用参数json暂时为空
//        js004.setExtendjson(new ExtendParam());
        js004.setExtendjson(null);
        js004.setTimestamp(String.valueOf(timestamp));
        String jsonParam=JsonUtil.toJson(js004);
        sendToMiddleManage(appUrl,JsonUtil.toMap(jsonParam));
        return basicRet;
    }

    /**
     *进行卖家对接的参数的有效性校验
     * @author xiazy
     * @date  2018/6/5 15:27
     * @param sellerId 卖家的id
     * @return mizuki.project.core.restserver.config.BasicRet
     */
    public Map<String,Object> verification(Long sellerId){
        BasicRet basicRet=new BasicRet();
        Map<String,Object> retMap=new HashMap<>();
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("卖家对接参数检验通过");
        SellerCompanyInfo sellerCompanyInfo = sellerCompanyInfoMapper.getSellerCompanyByMemberid(sellerId);
        String appUrl=sellerCompanyInfo.getAppurl();
        String appId=sellerCompanyInfo.getAppid();
        String appSecret=sellerCompanyInfo.getAppsecret();
        if(!StringUtils.hasText(appUrl)){
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("商家未配置对应的中间件管理平台的对接地址");
            retMap.put("basicRet",basicRet);
            return retMap;
        }
        if (!StringUtils.hasText(appId)){
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("商家尚未获取对应的中间件管理平台的对接id");
            retMap.put("basicRet",basicRet);
            return retMap;
        }
        if (!StringUtils.hasText(appSecret)){
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("商家尚未获取对应的中间件管理平台的对接密钥");
            retMap.put("basicRet",basicRet);
            return retMap;
        }
        if (!sellerCompanyInfo.getDisable()){
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("商家配置的对接连接处于不可用状态.");
            retMap.put("basicRet",basicRet);
            return retMap;
        }
        retMap.put("basicRet",basicRet);
        retMap.put("sellerCompanyInfo",sellerCompanyInfo);
        return retMap;
    }

}
