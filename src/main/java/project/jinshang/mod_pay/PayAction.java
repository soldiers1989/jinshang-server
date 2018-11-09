package project.jinshang.mod_pay;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import mizuki.project.core.restserver.config.BasicRet;
import mizuki.project.core.restserver.config.exception.RestMainException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.jinshang.mod_pay.bean.Refund;
import project.jinshang.mod_pay.mod_alipay.AlipayService;
import project.jinshang.mod_pay.mod_bankpay.AbcService;
import project.jinshang.mod_pay.mod_wxpay.MyWxPayService;
import project.jinshang.mod_pay.service.TradeService;

@RestController
@RequestMapping("/rest/pay")
@Api(tags = "第三方支付")
@Transactional(rollbackFor = Exception.class)
public class PayAction {
    @Autowired
    private TradeService tradeService;
    @Autowired
    private MyWxPayService wxPayService;
    @Autowired
    private AlipayService alipayService;
    @Autowired
    private AbcService abcService;

//    @RequestMapping(value="/refund",method= RequestMethod.POST)
//    @ApiOperation(value = "退款接口")
//    public BasicRet refund(
//            Model model,
//            @RequestParam String tradeNo,
//            @ApiParam(required = true,value = "退款金额")
//            @RequestParam double refundAmount
//    ) throws RestMainException {
//        try{
//            Refund refund = tradeService.buildRefund(tradeNo);
//            if(refund==null) return new BasicRet(BasicRet.ERR,"订单错误");
//            refund.setRefundAmount((long) (refundAmount*100));
//            boolean result = false;
//            if("alipay".equals(refund.getChannel())){
//                result = alipayService.refund(refund);
//            }else if("wxpay".equals(refund.getChannel())){
//                result = wxPayService.refund(refund);
//            }else if("bank".equals(refund.getChannel())){
//                result = abcService.refund(refund);
//            }
//            if(result)
//                return new BasicRet(BasicRet.SUCCESS);
//            else
//                return new BasicRet(BasicRet.ERR,"退款申请失败");
//        }catch (Exception e){
//            throw new RestMainException(e, model);
//        }
//    }
}
