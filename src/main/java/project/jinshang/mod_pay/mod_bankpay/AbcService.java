package project.jinshang.mod_pay.mod_bankpay;

import com.abc.pay.client.JSON;
import com.abc.pay.client.ebus.RefundRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import project.jinshang.mod_pay.bean.Refund;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 农行接口
 */
@Service
public class AbcService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy/MM/dd");
    private SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("HH:mm:ss");

    public boolean refund(Refund refund){
        //1、生成退款请求对象
        RefundRequest tRequest = new RefundRequest();
        Date date = new Date(refund.getDatetime());
        String ymd = simpleDateFormat1.format(date);
        String hms = simpleDateFormat2.format(date);
        tRequest.dicRequest.put("OrderDate", ymd);  //订单日期（必要信息）
        tRequest.dicRequest.put("OrderTime", hms); //订单时间（必要信息）
        tRequest.dicRequest.put("OrderNo", refund.getOutTradeNo()); //原交易编号（必要信息）
        //tRequest.dicRequest.put("NewOrderNo", String.valueOf(System.currentTimeMillis())); //交易编号（必要信息）
        tRequest.dicRequest.put("NewOrderNo",refund.getOutTradeNo()+"-"+ new Random().nextInt(1000)+ new Random().nextInt(1000));

        tRequest.dicRequest.put("CurrencyCode", "156"); //交易币种（必要信息）
        tRequest.dicRequest.put("TrxAmount", new BigDecimal(refund.getRefundAmount()).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP).toString()); //退货金额 （必要信息）
        //如果需要专线地址，调用此方法：
        //tRequest.setConnectionFlag(true);

        //2、传送退款请求并取得退货结果
        JSON json = tRequest.postRequest();

        //3、判断退款结果状态，进行后续操作
        StringBuilder strMessage = new StringBuilder("");
        String ReturnCode = json.GetKeyValue("ReturnCode");
        String ErrorMessage = json.GetKeyValue("ErrorMessage");
        if (ReturnCode.equals("0000")) {
            //4、退款成功/退款受理成功
//            out.println("ReturnCode   = [" + ReturnCode + "]<br/>");
//            out.println("ErrorMessage = [" + ErrorMessage + "]<br/>");
//            out.println("OrderNo   = [" + json.GetKeyValue("OrderNo") + "]<br/>");
//            out.println("NewOrderNo   = [" + json.GetKeyValue("NewOrderNo") + "]<br/>");
//            out.println("TrxAmount = [" + json.GetKeyValue("TrxAmount") + "]<br/>");
//            out.println("BatchNo   = [" + json.GetKeyValue("BatchNo") + "]<br/>");
//            out.println("VoucherNo = [" + json.GetKeyValue("VoucherNo") + "]<br/>");
//            out.println("HostDate  = [" + json.GetKeyValue("HostDate") + "]<br/>");
//            out.println("HostTime  = [" + json.GetKeyValue("HostTime") + "]<br/>");
//            out.println("iRspRef  = [" + json.GetKeyValue("iRspRef") + "]<br/>");
            return true;
        } else {
            //5、退款失败
//            out.println("ReturnCode   = [" + ReturnCode + "]<br>");
//            out.println("ErrorMessage = [" + ErrorMessage + "]<br>");
            logger.error("bank pay refund err: "+ErrorMessage);
            return false;
        }
    }

}
