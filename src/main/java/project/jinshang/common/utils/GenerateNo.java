package project.jinshang.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * Created by Administrator on 2017/11/14.
 */
public class GenerateNo {

    private static String orderNo ;//订单号

    private static String invoiceNo;//发货单号

    private static String returnNo;//退货单号

    private static String contractNo;//合同号

    private static String tranNo;//交易号

    private static String billNo;//账单编号

    private static String rechargeNo;//充值单号

    private static String withdrawalsNo;//提现单号

    private static String repayment;//还款单号

    private static String settlementNo;//结算账单号（授信）

    private static final String format= "yyyyMMddHHmmss";

    private static final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" ;

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);

    /**
     * 订单号
     * @return
     */
    public static String getOrderNo(){
        orderNo = simpleDateFormat.format(new Date());
        orderNo = "D"+orderNo+(int)((Math.random()*9+1)*1000);
        return orderNo;
    }

    /**
     * 合同号
     * @return
     */
    public static String getContractNo(){
        contractNo = simpleDateFormat.format(new Date());
        contractNo = "JS"+contractNo+(int)((Math.random()*9+1)*1000)+getUpperLetter();
        return contractNo;
    }

    /**
     * 交易号
     * @return
     */
    public static String getTransactionNo(){
        tranNo = simpleDateFormat.format(new Date());
        tranNo = tranNo+(int)((Math.random()*9+1)*100000);
        return tranNo;
    }

    /**
     * 账单编号
     * @return
     */
    public static String getBillNo(){
        billNo = simpleDateFormat.format(new Date());
        billNo = "ZD"+billNo+(int)((Math.random()*9+1)*1000);
        return billNo;
    }

    /**
     * 充值单号
     * @return
     */
    public static String getRechargeNo(){
        rechargeNo = simpleDateFormat.format(new Date());
        rechargeNo = "CZ"+rechargeNo+(int)((Math.random()*9+1)*1000);
        return rechargeNo;
    }

    /**
     * 提现单号
     * @return
     */
    public static String getWithdrawalsNoNo(){
        withdrawalsNo = simpleDateFormat.format(new Date());
        withdrawalsNo = "TX"+withdrawalsNo+(int)((Math.random()*9+1)*1000);
        return withdrawalsNo;
    }


    /**
     * 退货单号
     * @return
     */
    public static String getReturnNo(){
        returnNo = simpleDateFormat.format(new Date());
        returnNo = "TH"+returnNo+(int)((Math.random()*9+1)*1000);
        return returnNo;
    }

    /**
     * 还款单号
     * @return
     */
    public static String getRepaymentNo(){
        repayment = simpleDateFormat.format(new Date());
        repayment = "HK"+repayment+(int)((Math.random()*9+1)*1000);
        return repayment;
    }


    /**
     * 发货单号
     * @return
     */
    public static String getInvoiceNo(){
        invoiceNo = simpleDateFormat.format(new Date());
        invoiceNo = "FH"+invoiceNo+(int)((Math.random()*9+1)*1000);
        return invoiceNo;
    }

    /**
     * 结算账单号（授信）
     * @return
     */
    public static String getSettlementNo(){
        settlementNo = simpleDateFormat.format(new Date());
        settlementNo = settlementNo+(int)((Math.random()*9+1)*1000);
        return settlementNo;
    }


    /**
     *紧固件商品库编码生成
     */
    public  static  String getProductsNo(){
        int max=999999;
        int min=99999;
        Random random = new Random();
        String pdno = "JS"+DateUtils.format(new Date(),"yyyyMMddHHmmss")+(int)(random.nextInt(max)%(max-min+1) + min);

        return  pdno;
    }


    /**
     * 自动生成商品编码
     * @return
     */
    public  static  String getPNO(){
        int max=999999;
        int min=99999;
        Random random = new Random();
        String pdno = DateUtils.format(new Date(),"yyyyMMddHHmmss")+(int)(random.nextInt(max)%(max-min+1) + min);

        return  pdno;
    }

    /**
     * 卖家发票号
     * @return
     */
    public static String getSellBillNo(){
        billNo = simpleDateFormat.format(new Date());
        billNo = "FP"+billNo+(int)((Math.random()*9+1)*1000);
        return billNo;
    }


    /**
     * 获取随机大写字母
     * @return
     */
    private static char getUpperLetter() {
        return (chars.charAt((int) (Math.random() * 26)));
    }

    /**
     * 32位uuid
     * @return
     */
    public static String getUUID(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 16位uuid
     * @return
     */
    public static String getOrderIdByUUId() {
        int machineId = 1;//最大支持1-9个集群机器部署
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        if(hashCodeV < 0) {//有可能是负数
            hashCodeV = - hashCodeV;
        }
        // 0 代表前面补充0
        // 4 代表长度为4
        // d 代表参数为正数型
        return machineId + String.format("%015d", hashCodeV);
    }

}
