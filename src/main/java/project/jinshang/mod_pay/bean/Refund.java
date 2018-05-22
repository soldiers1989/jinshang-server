package project.jinshang.mod_pay.bean;

/**
 * 退款信息
 */
public class Refund {
    private String outTradeNo;
    // 退款金额-分
    private long refundAmount;
    // 订单总金额
    private long totalAmount;
    private String refundReason;
    // 支付方式: alipay,wxpay
    private String channel;
    // 订单时间
    private long datetime;

    public long getDatetime() {
        return datetime;
    }

    public Refund setDatetime(long datetime) {
        this.datetime = datetime;
        return this;
    }

    public String getChannel() {
        return channel;
    }

    public Refund setChannel(String channel) {
        this.channel = channel;
        return this;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public Refund setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
        return this;
    }

    public long getRefundAmount() {
        return refundAmount;
    }

    public Refund setRefundAmount(long refundAmount) {
        this.refundAmount = refundAmount;
        return this;
    }

    public long getTotalAmount() {
        return totalAmount;
    }

    public Refund setTotalAmount(long totalAmount) {
        this.totalAmount = totalAmount;
        return this;
    }

    public String getRefundReason() {
        if(refundReason==null) refundReason="";
        return refundReason;
    }

    public Refund setRefundReason(String refundReason) {
        this.refundReason = refundReason;
        return this;
    }
}
