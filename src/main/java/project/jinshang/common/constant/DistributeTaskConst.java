package project.jinshang.common.constant;

/**
 * create : wyh
 * date : 2018/10/29
 */
public class DistributeTaskConst {

    //订单
    //超时未付款订单
    public static final String ORDER_OVERTIME_NOT_PAY_ORDERS = "updateNotPayOrdersForFinish";

    //冻结金额到货款
    public static final String ORDER_FREEZE_TO_GOODSBANLANCE = "autoFreezeToGoodsBanlance";

    //买家订单自动确认收货
    public static final String ORDER_AUTO_DELIVERY_GOODS = "autoDeliveryGoods";

    //买家订单自动确认验货
    public static final String ORDER_AUTO_CONFIRM_GOODS = "autoConfirmGoods";

    //关闭限时购超时付款订单
    public static final String ORDER_CLOSE_NOTPAY_LINIMTORDERS = "closeNotPayLinimtOrders";

    //同步订单仓储发货状态
    public static final String ORDER_SEND_GOODS_STATES = "getOrderStoreStates";


    //搜索引擎
    //商品数据重建索引
    public static final String SEARCH_REBUILD_PRODUCTINFO = "rebuildProductinfo";


    //授信
    // 生成授信账单
    public static final String BILL_GENETATE_CREDITBILL = "genetateCreditBill";

    //检测授信账单逾期
    public static final String BILL_CHECK_CREDITBILL_OVERDUE = "checkCreditBillOverdue";

    //首页
    //更新首页缓存
    public static final String INDEX_INDEX_FLOOR = "addIndexFloor";

    //更新展示类目缓存
    public static final String INDEX_SHOW_CATE = "addShowCate";


    //分销
    //查询邀请人
    public static final String FX_ORDERS = "FX_Order";

    //执行返佣
    public static final String FX_EXECUTE_RUTUNR = "FX_executeReturn";


    //推送失败信息至wms
    public static final String CHECK_UN_SEND = "checkUnSend";






}
