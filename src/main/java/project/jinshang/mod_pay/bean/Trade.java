package project.jinshang.mod_pay.bean;

import project.jinshang.mod_pay.bean.ret.PayUrlRet;

public class Trade {
    // 订单号
    private String outTradeNo;
    // 金额-分
    private long amount;
    // 订单名称
    private String subject;
    // 商品描述
    private String body;
    // 商品id
    private String productId;
    //
    private long datetime;

    public long getDatetime() {
        return datetime;
    }

    public Trade setDatetime(long datetime) {
        this.datetime = datetime;
        return this;
    }

    private PayUrlRet payUrlRet;

    public PayUrlRet getPayUrlRet() {
        return payUrlRet;
    }

    public void setPayUrlRet(PayUrlRet payUrlRet) {
        this.payUrlRet = payUrlRet;
    }

    public String getProductId() {
        if(productId==null) productId=String.valueOf(System.currentTimeMillis());
        return productId;
    }

    public Trade setProductId(String productId) {
        this.productId = productId;
        return this;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public Trade setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
        return this;
    }

    public long getAmount() {
        return amount;
    }

    public Trade setAmount(long amount) {
        this.amount = amount;
        return this;
    }

    public String getSubject() {
        return subject;
    }

    public Trade setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public String getBody() {
        if(body==null) body=subject;
        return body;
    }

    public Trade setBody(String body) {
        this.body = body;
        return this;
    }

}
