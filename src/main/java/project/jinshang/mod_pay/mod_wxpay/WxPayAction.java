package project.jinshang.mod_pay.mod_wxpay;

import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.bean.order.WxPayMpOrderResult;
import com.github.binarywang.wxpay.bean.order.WxPayNativeOrderResult;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import io.swagger.annotations.*;
import me.chanjar.weixin.common.bean.WxJsapiSignature;
import mizuki.project.core.restserver.config.BasicMapDataRet;
import mizuki.project.core.restserver.config.BasicRet;
import mizuki.project.core.restserver.config.WebConfBean;
import mizuki.project.core.restserver.config.exception.RestMainException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.jinshang.common.constant.Quantity;
import project.jinshang.common.utils.GsonUtils;
import project.jinshang.mod_pay.bean.PayLogs;
import project.jinshang.mod_pay.bean.Trade;
import project.jinshang.mod_pay.bean.ret.PayUrlRet;
import project.jinshang.mod_pay.service.PayLogsService;
import project.jinshang.mod_pay.service.TradeService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.util.Date;

@RestController
@RequestMapping("/rest/wxpay")
@Transactional(rollbackFor = Exception.class)
@Api(tags = "第三方支付")
public class WxPayAction {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource(name = "wxPayService")
    private WxPayService wxService;
    @Autowired
    private TradeService tradeService;
    @Autowired
    private WebConfBean webConfBean;

    @Resource
    private PayLogsService payLogsService;

    @RequestMapping(value="/toPay",method= {RequestMethod.POST})
    @ApiOperation(value = "去支付. 返回的url需要调用url2qr生成支付二维码")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "类型 1=订单，2=买家余额充值，3=卖家余额充值",name = "type",paramType = "query")
    })
    public PayUrlRet toPay(
            Model model,
            @ApiParam(required = true,value = "订单id")
            @RequestParam String orders,
            HttpServletRequest request,Short type
    ) throws RestMainException {
        try {
            PayUrlRet ret = new PayUrlRet();
            Trade trade = null;
            if(type== Quantity.STATE_1){
                trade = tradeService.buildFromOrderId(orders,Quantity.STATE_1);
            }else if(type == Quantity.STATE_2){
                trade = tradeService.buildFromBuyerRecharge(orders,Quantity.STATE_0);
            }else if(type == Quantity.STATE_3){
                trade = tradeService.buildFromSellerRecharge(orders,Quantity.STATE_0);
            }


            if(trade==null){
                ret.setResult(BasicRet.ERR).setMessage("订单状态错误");
                return ret;
            }
            if(trade.getPayUrlRet()!=null && trade.getPayUrlRet().getResult()==BasicRet.ERR){
                return trade.getPayUrlRet();
            }
            WxPayUnifiedOrderRequest orderRequest = new WxPayUnifiedOrderRequest();
            // 商品描述
            orderRequest.setBody(trade.getBody());
            // 订单号
            orderRequest.setOutTradeNo(trade.getOutTradeNo());
            // 金额 分
            orderRequest.setTotalFee((int) trade.getAmount());
            // native时需要
            orderRequest.setProductId(trade.getProductId());
            logger.info(request.getRemoteAddr());
            orderRequest.setSpbillCreateIp(request.getRemoteAddr());
            orderRequest.setNotifyURL(webConfBean.getProjectDomain()+"/rest/wxpay/notify");
            orderRequest.setTradeType("NATIVE");
            WxPayNativeOrderResult result = wxService.createOrder(orderRequest);
            ret.setResult(BasicRet.SUCCESS);
            ret.getData().setUrl(result.getCodeUrl());
            return ret;
        }catch (Exception e){
            throw new RestMainException(e);
        }
    }
    
    @RequestMapping(value="/url2qr",method= {RequestMethod.POST,RequestMethod.GET})
    @ApiOperation(value = "url转二维码")
    public ResponseEntity<InputStreamResource> url2qr(
            @RequestParam String url
    ){
        byte[] data = wxService.createScanPayQrcodeMode2(url,null,null);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        return ResponseEntity
                .ok().headers(headers)
                .contentType(MediaType.IMAGE_JPEG)
                .body(new InputStreamResource(new ByteArrayInputStream(data)));
    }

    @RequestMapping(value="/toPay4Js",method= {RequestMethod.POST})
    @ApiOperation(value = "去支付. 用于jssdk")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "类型 1=订单，2=买家余额充值，3=卖家余额充值",name = "type",paramType = "query")
    })
    public BasicMapDataRet toPay4Js(
            Model model,
            @ApiParam(required = true,value = "订单id")
            @RequestParam String orders,
            HttpServletRequest request,Short type,
            @RequestParam String openid
    ) throws RestMainException {
        try {
            BasicMapDataRet ret = new BasicMapDataRet();
            Trade trade = null;
            if(type== Quantity.STATE_1){
                trade = tradeService.buildFromOrderId(orders,Quantity.STATE_1);
            }else if(type == Quantity.STATE_2){
                trade = tradeService.buildFromBuyerRecharge(orders,Quantity.STATE_0);
            }else if(type == Quantity.STATE_3){
                trade = tradeService.buildFromSellerRecharge(orders,Quantity.STATE_0);
            }
            if(trade==null){
                ret.setResult(BasicRet.ERR).setMessage("订单状态错误");
                return ret;
            }
            if(trade.getPayUrlRet()!=null && trade.getPayUrlRet().getResult()==BasicRet.ERR){
                ret.setResult(BasicRet.ERR).setMessage(trade.getPayUrlRet().getMessage());
                return ret;
            }
//            WxJsapiSignature js = wxService.createJsapiSignature(request.getRequestURL().toString());
//            data.put("jsapi",js);
            WxPayUnifiedOrderRequest orderRequest = new WxPayUnifiedOrderRequest();
            // 商品描述
            orderRequest.setBody(trade.getBody());
            // 订单号
            orderRequest.setOutTradeNo(trade.getOutTradeNo());
            // 金额 分
            orderRequest.setTotalFee((int) trade.getAmount());
            // native时需要
            orderRequest.setProductId(trade.getProductId());
            logger.info(request.getRemoteAddr());
            orderRequest.setSpbillCreateIp(request.getRemoteAddr());
            orderRequest.setNotifyURL(webConfBean.getProjectDomain()+"/rest/wxpay/notify");
            orderRequest.setTradeType("JSAPI");
            orderRequest.setOpenid(openid);
            WxPayMpOrderResult result = wxService.createOrder(orderRequest);
            ret.setResult(BasicRet.SUCCESS);
            ret.getData().put("result",result);

            logger.error("微信订单返回："+ret.getData());

            return ret;
        }catch (Exception e){
            throw new RestMainException(e);
        }
    }

//
//    public static void main(String[] args) {
//        BasicMapDataRet ret = new BasicMapDataRet();
//
//        WxPayMpOrderResult result = WxPayMpOrderResult.builder().appId("wx6eb2a1213254a1df").nonceStr("1525349799372").timeStamp("1525349799")
//                .packageValue("prepay_id=wx03201639298812ac69674b371505593977").signType("MD5").paySign("467B0999799EA32702B2A2F5CEE72DCA").build()
//                ;
//
//
//        ret.getData().put("result",result);
//
//        System.out.println(GsonUtils.toJson(ret));
//    }



    @RequestMapping(value="/notify",method= {RequestMethod.POST, RequestMethod.GET})
    public String notify2(@RequestBody String xmlData) {
        try {

            logger.error("微信回调："+xmlData);

            WxPayOrderNotifyResult result = wxService.parseOrderNotifyResult(xmlData);
            if("SUCCESS".equals(result.getReturnCode()) && "SUCCESS".equals(result.getResultCode())) {
                String orderNo = result.getOutTradeNo();
                String[] orderNo_array = orderNo.split("-");

                //支付金额
                Integer total_amount = result.getTotalFee();
                PayLogs payLogs = new PayLogs();
                payLogs.setTransactionid(result.getTransactionId());
                payLogs.setOuttradeno(orderNo);
                payLogs.setMoney(new BigDecimal(total_amount).divide(new BigDecimal(100)).setScale(2,BigDecimal.ROUND_HALF_UP));
                payLogs.setCreatetime(new Date());
                payLogs.setChannel("wxpay");
                payLogsService.insertSelective(payLogs);

                boolean res = false;
                if(orderNo_array.length==2){
                    if(orderNo_array[0].equals("order")){
                        res = tradeService.notify(orderNo, "wxpay",result.getTransactionId());
                    }else if(orderNo_array[0].equals("buy")) {
                        res = tradeService.notifyBuyerRecharge(orderNo_array[1],result.getTransactionId());
                    }else if(orderNo_array[0].equals("sell")){
                        res = tradeService.notifySellerRecharge(orderNo_array[1],result.getTransactionId());
                    }
                }

                if(res) logger.info("trade success :"+orderNo);
                // todo
                else return returnXml(false);
            }
            return returnXml(true);
        } catch (WxPayException e) {
            e.printStackTrace();
            return returnXml(false);
        }
    }

    private String returnXml(boolean success){
        if(success){
            return "<xml>" +
                    "<return_code><![CDATA[SUCCESS]]></return_code>" +
                    "<return_msg><![CDATA[OK]]></return_msg>" +
                    "</xml>";
        }else{
            return "<xml>" +
                    "<return_code><![CDATA[FAIL]]></return_code>" +
                    "<return_msg><![CDATA[error]]></return_msg>" +
                    "</xml>";
        }
    }
}
