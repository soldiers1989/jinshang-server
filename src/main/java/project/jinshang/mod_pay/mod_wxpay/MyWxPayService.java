package project.jinshang.mod_pay.mod_wxpay;

import com.github.binarywang.wxpay.bean.request.WxPayRefundRequest;
import com.github.binarywang.wxpay.bean.result.WxPayRefundResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import org.springframework.stereotype.Service;
import project.jinshang.mod_pay.bean.Refund;

import javax.annotation.Resource;
import java.util.Random;

@Service
public class MyWxPayService {
    @Resource(name = "wxPayService")
    private com.github.binarywang.wxpay.service.WxPayService wxService;

    public boolean refund(Refund refund) throws WxPayException {
        WxPayRefundRequest refundRequest = new WxPayRefundRequest();
        refundRequest.setOutTradeNo(refund.getOutTradeNo());
        //refundRequest.setOutRefundNo(String.valueOf(System.currentTimeMillis()));
        refundRequest.setOutRefundNo(refund.getOutTradeNo()+"-"+ new Random().nextInt(1000)+ new Random().nextInt(1000));
        refundRequest.setTotalFee((int)refund.getTotalAmount());
        refundRequest.setRefundFee((int)refund.getRefundAmount());
        WxPayRefundResult refundResult = wxService.refund(refundRequest);
        return "SUCCESS".equals(refundResult.getResultCode()) && "SUCCESS".equals(refundResult.getReturnCode());
    }

}
