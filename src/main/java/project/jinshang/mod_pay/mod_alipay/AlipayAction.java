package project.jinshang.mod_pay.mod_alipay;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import io.swagger.annotations.*;
import mizuki.project.core.restserver.config.BasicRet;
import mizuki.project.core.restserver.config.exception.RestMainException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.jinshang.common.constant.Quantity;
import project.jinshang.mod_pay.bean.Trade;
import project.jinshang.mod_pay.service.TradeService;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@RestController
@RequestMapping("/rest/alipay")
@Api(tags = "第三方支付")
@Transactional(rollbackFor = Exception.class)
public class AlipayAction {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TradeService tradeService;
    @Autowired
    private AlipayConfig alipayConfig;

    @RequestMapping(value="/wap/toPay",method= {RequestMethod.POST,RequestMethod.GET})
    @ApiImplicitParams({
            @ApiImplicitParam(value = "类型 1=订单，2=买家余额充值，3=卖家余额充值",name = "type",paramType = "query")
    })
    @ApiOperation(value = "去支付（手机网站）. 直接作为链接使用。")
    public String toPayWap(
            Model model,
            @ApiParam(required = true,value = "订单id：{1,2}")
            @RequestParam String orders,
            Short type
    ) throws RestMainException{
        try{
            Trade trade = null;
            if(type== Quantity.STATE_1){ //订单
                trade = tradeService.buildFromOrderId(orders,Quantity.STATE_0);
            }else if (type == Quantity.STATE_2 ){
                trade = tradeService.buildFromBuyerRecharge(orders,Quantity.STATE_1);
            }else if(type == Quantity.STATE_3){
                trade = tradeService.buildFromSellerRecharge(orders,Quantity.STATE_1);
            }

            if(trade==null){
                return "订单状态错误";
            }
            if(trade.getPayUrlRet().getResult()== BasicRet.ERR){
                return trade.getPayUrlRet().getMessage();
            }
            //获得初始化的AlipayClient
            AlipayClient alipayClient = new DefaultAlipayClient(alipayConfig.gatewayUrl, alipayConfig.app_id, alipayConfig.merchant_private_key, "json", alipayConfig.charset, alipayConfig.alipay_public_key, alipayConfig.sign_type);
            //设置请求参数
            AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();
//            alipayRequest.setReturnUrl(alipayConfig.return_url);
            alipayRequest.setNotifyUrl(alipayConfig.getNotify_url());
            //商户订单号，商户网站订单系统中唯一订单号，必填
            String out_trade_no = trade.getOutTradeNo();
            // 付款金额，必填。两位数
            String total_amount = new BigDecimal(trade.getAmount()).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP).toString();
            // 订单名称，必填
            String subject = trade.getSubject();
            // 商品描述，可空
            String body = trade.getBody();

            alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                    + "\"total_amount\":\""+ total_amount +"\","
                    + "\"subject\":\""+ subject +"\","
                    + "\"body\":\""+ body +"\","
                    + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
            //请求
            return alipayClient.pageExecute(alipayRequest).getBody();
        }catch (Exception e){
            throw new RestMainException(e);
        }
    }

    @RequestMapping(value="/toPay",method= {RequestMethod.POST,RequestMethod.GET})
    @ApiImplicitParams({
            @ApiImplicitParam(value = "类型 1=订单，2=买家余额充值，3=卖家余额充值",name = "type",paramType = "query")
    })
    @ApiOperation(value = "去支付. 直接作为链接使用。")
    public String toPay(
            Model model,
            @ApiParam(required = true,value = "订单id：{1,2}")
            @RequestParam String orders,
            Short type
    ) throws RestMainException{
        try{
            Trade trade = null;
            if(type== Quantity.STATE_1){ //订单
                trade = tradeService.buildFromOrderId(orders,Quantity.STATE_0);
            }else if (type == Quantity.STATE_2 ){
                trade = tradeService.buildFromBuyerRecharge(orders,Quantity.STATE_1);
            }else if(type == Quantity.STATE_3){
                trade = tradeService.buildFromSellerRecharge(orders,Quantity.STATE_1);
            }

            if(trade==null){
                return "订单状态错误";
            }
            if(trade.getPayUrlRet()!=null && trade.getPayUrlRet().getResult()== BasicRet.ERR){
                return trade.getPayUrlRet().getMessage();
            }
            //获得初始化的AlipayClient
            AlipayClient alipayClient = new DefaultAlipayClient(alipayConfig.gatewayUrl, alipayConfig.app_id, alipayConfig.merchant_private_key, "json", alipayConfig.charset, alipayConfig.alipay_public_key, alipayConfig.sign_type);
            //设置请求参数
            AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
//            alipayRequest.setReturnUrl(alipayConfig.return_url);
            alipayRequest.setNotifyUrl(alipayConfig.getNotify_url());
            //商户订单号，商户网站订单系统中唯一订单号，必填
            String out_trade_no = trade.getOutTradeNo();
            // 付款金额，必填。两位数
            String total_amount = new BigDecimal(trade.getAmount()).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP).toString();
            // 订单名称，必填
            String subject = trade.getSubject();
            // 商品描述，可空
            String body = trade.getBody();

            alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                    + "\"total_amount\":\""+ total_amount +"\","
                    + "\"subject\":\""+ subject +"\","
                    + "\"body\":\""+ body +"\","
                    + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
            //请求
            return alipayClient.pageExecute(alipayRequest).getBody();
        }catch (Exception e){
            throw new RestMainException(e);
        }
    }

    @RequestMapping(value="/notify",method= {RequestMethod.POST, RequestMethod.GET})
    @ApiOperation(value = "")
    public String notify2(HttpServletRequest request) {
        try{
            //获取支付宝POST过来反馈信息
            Map<String,String> params = new HashMap<String,String>();
            Map<String,String[]> requestParams = request.getParameterMap();
            for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
                String name = (String) iter.next();
                String[] values = (String[]) requestParams.get(name);
                String valueStr = "";
                for (int i = 0; i < values.length; i++) {
                    valueStr = (i == values.length - 1) ? valueStr + values[i]
                            : valueStr + values[i] + ",";
                }
                //乱码解决，这段代码在出现乱码时使用
//                valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
                params.put(name, valueStr);
            }

            boolean signVerified = AlipaySignature.rsaCheckV1(params, alipayConfig.alipay_public_key, alipayConfig.charset, alipayConfig.sign_type); //调用SDK验证签名

            if(signVerified) {//验证成功
                //商户订单号
                String out_trade_no = request.getParameter("out_trade_no");

                String[] out_trade_no_array = out_trade_no.split("-");

                //支付宝交易号
                String trade_no = request.getParameter("trade_no");

                //交易状态
                String trade_status = request.getParameter("trade_status");

//                if(trade_status.equals("TRADE_FINISHED")){
                // 这里的订单支付完成关闭后
//                }else
                if (trade_status.equals("TRADE_SUCCESS")){
                    //判断该笔订单是否在商户网站中已经做过处理
                    //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                    //如果有做过处理，不执行商户的业务程序
                    boolean res = false;
                    //注意：
                    //付款完成后，支付宝系统发送该交易状态通知
                    if(out_trade_no_array.length==2){
                        if(out_trade_no_array[0].equals("order")){
                            res = tradeService.notify(out_trade_no,"alipay",trade_no);
                        }else if(out_trade_no_array[0].equals("buy")) {
                            res = tradeService.notifyBuyerRecharge(out_trade_no_array[1],trade_no);
                        }else if(out_trade_no_array[0].equals("sell")){
                            res = tradeService.notifySellerRecharge(out_trade_no_array[1],trade_no);
                        }
                    }

                    if(res) logger.info("trade success :"+out_trade_no);
                    // todo
                    else return "fail";
                }
                return  "success";

            }else {//验证失败
                //调试用，写文本函数记录程序运行情况是否正常
//                String sWord = AlipaySignature.getSignCheckContentV1(params);
                logger.error("验证失败");
                return "fail";
            }
        }catch (Exception e){
            e.printStackTrace();
            return "fail";
        }
    }
}
