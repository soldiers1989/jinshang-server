package project.jinshang.common.constant;

import java.math.BigDecimal;

/**
 * create : wyh
 * date : 2017/12/18
 */
public class TradeConstant {

    //账单日到最后还款日间隔天数
    public  static  final int  lastday_to_statementdate_interval = 20;

    //结算日到账单日间隔天数
    public  static  final  int accountday_to_statementdate_interval = 5;

    //远期全款打折率
    public static final BigDecimal allPayRate = new BigDecimal(0.99);

}
