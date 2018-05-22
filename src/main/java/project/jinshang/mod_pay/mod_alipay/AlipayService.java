package project.jinshang.mod_pay.mod_alipay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeRefundRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.mod_pay.bean.Refund;

import java.math.BigDecimal;
import java.util.Random;

@Service
public class AlipayService {
    @Autowired
    private AlipayConfig alipayConfig;

    public boolean refund(Refund refund) throws AlipayApiException {
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(alipayConfig.gatewayUrl, alipayConfig.app_id, alipayConfig.merchant_private_key, "json", alipayConfig.charset, alipayConfig.alipay_public_key, alipayConfig.sign_type);

        //设置请求参数
        AlipayTradeRefundRequest alipayRequest = new AlipayTradeRefundRequest();

        //商户订单号，商户网站订单系统中唯一订单号
        String out_trade_no = refund.getOutTradeNo();

        //需要退款的金额，该金额不能大于订单金额，必填
        String refund_amount = new BigDecimal(refund.getRefundAmount()).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP).toString();
        //退款的原因说明
        String refund_reason = refund.getRefundReason();
        //标识一次退款请求，同一笔交易多次退款需要保证唯一，如需部分退款，则此参数必传
        //String out_request_no = String.valueOf(System.currentTimeMillis());
        String out_request_no = refund.getOutTradeNo()+"-"+ new Random().nextInt(1000)+ new Random().nextInt(1000);

        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
//                    + "\"trade_no\":\""+ trade_no +"\","
                + "\"refund_amount\":\""+ refund_amount +"\","
                + "\"refund_reason\":\""+ refund_reason +"\","
                + "\"out_request_no\":\""+ out_request_no +"\"}");

        //请求
        String result = alipayClient.execute(alipayRequest).getBody();
        //System.out.println(result);
        return (result.contains("Success"));
    }

}
