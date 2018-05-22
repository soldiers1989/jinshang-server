package project.jinshang.common.constant;

/**
 * 买家资金常量
 *
 * @author xiazy
 * @create 2018-05-15 11:14
 **/
public class BuyerCapitalConst {
    //消费
    public  static  final short CAPITALTYPE_CONSUM            =0;
    //充值
    public  static  final short CAPITALTYPE_RECHARGE          =1;
    //退款
    public  static  final short CAPITALTYPE_REFUND            =2;
    //提现
    public  static  final short CAPITALTYPE_WITHDRAWAL        =3;
    //授信
    public  static  final short CAPITALTYPE_CREDIT            =4;
    //授信还款
    public  static  final short CAPITALTYPE_CREDIT_REPAY      =5;
    //违约金(买家)
    public  static  final short CAPITALTYPE_PENALTY           =6;
    //远期定金
    public  static  final short CAPITALTYPE_DEPOSIT_LONG      =7;
    //远期余款
    public  static  final short CAPITALTYPE_SURPLUS_LONG      =8;
    //远期全款
    public  static  final short CAPITALTYPE_FULL_PAYMENT_LONG =9;
    //卖家违约金
    public  static  final short CAPITALTYPE_PENALTY_SELLER    =10;
    //授信未出账单还款
    public  static  final short CAPITALTYPE_CREDIT_NOT_PAID   =11;

    //支付宝支付
    public  static  final short PAYMETHOD_ALIPAY     =0;
    //微信支付
    public  static  final short PAYMETHOD_WEIXIN     =1;
    //银行卡支付
    public  static  final short PAYMETHOD_BANKCARD   =2;
    //余额支付
    public  static  final short PAYMETHOD_BALANCE    =3;
    //授信支付
    public  static  final short PAYMETHOD_CREDIT     =4;


}
