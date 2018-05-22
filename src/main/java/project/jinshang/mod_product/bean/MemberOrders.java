package project.jinshang.mod_product.bean;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/11/24.
 */
public class MemberOrders {

    @ApiModelProperty(notes = "创建时间")
    private Date createtime;
    @ApiModelProperty(notes = "合同号")
    private String code;
    @ApiModelProperty(notes = "订单号")
    private String orderno;
    @ApiModelProperty(notes = "交易号")
    private String transactionnumber;
    @ApiModelProperty(notes = "卖家公司名称")
    private String sellercompany;
    @ApiModelProperty(notes = "订单状态")
    private int orderstate;
    @ApiModelProperty(notes = "总价")
    private BigDecimal totalprice;
    @ApiModelProperty(notes = "实付款")
    private BigDecimal actualpayment;
    @ApiModelProperty(notes = "运费")
    private BigDecimal freight;
    @ApiModelProperty(notes = "定金")
    private BigDecimal deposit;
    @ApiModelProperty(notes = "余款")
    private BigDecimal balance;
    @ApiModelProperty(notes = "全款")
    private BigDecimal allpay;
    @ApiModelProperty(notes = "远期发货时间")
    private Date futuretime;
    @ApiModelProperty(notes = "订单类型0=立即发货1=远期全款2=远期定金3=远期余款")
    private short ordertype;
    @ApiModelProperty(notes = "收货人")
    private String receiver;
    @ApiModelProperty(notes = "收货人电话")
    private String receiverPhone;
    @ApiModelProperty(notes = "收货人地址")
    private String receiverAddress;
    @ApiModelProperty(notes = "订单id")
    private Long orderid;
    @ApiModelProperty(notes = "佣金")
    private BigDecimal brokepay;

    @ApiModelProperty(notes = "订单包含的商品列表")
    List<OrderProduct> orderProducts;

    @ApiModelProperty(notes = "延期发货天数")
    private Integer delaydays;

    @ApiModelProperty(notes = "延期发货审核状态")
    private Short  ifdelay;

    @ApiModelProperty(notes = "延期发货违约金")
    private BigDecimal delaypenalty;

    @ApiModelProperty(notes = "快递单号")
    private String couriernumber;

    @ApiModelProperty(notes = "物流公司")
    private String logisticscompany;

    public Integer getDelaydays() {
        return delaydays;
    }

    public void setDelaydays(Integer delaydays) {
        this.delaydays = delaydays;
    }

    public Short getIfdelay() {
        return ifdelay;
    }

    public void setIfdelay(Short ifdelay) {
        this.ifdelay = ifdelay;
    }

    public BigDecimal getDelaypenalty() {
        return delaypenalty;
    }

    public void setDelaypenalty(BigDecimal delaypenalty) {
        this.delaypenalty = delaypenalty;
    }

    public BigDecimal getBrokepay() {
        return brokepay;
    }

    public void setBrokepay(BigDecimal brokepay) {
        this.brokepay = brokepay;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSellercompany() {
        return sellercompany;
    }

    public void setSellercompany(String sellercompany) {
        this.sellercompany = sellercompany;
    }

    public int getOrderstate() {
        return orderstate;
    }

    public void setOrderstate(int orderstate) {
        this.orderstate = orderstate;
    }

    public List<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(List<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
    }

    public BigDecimal getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(BigDecimal totalprice) {
        this.totalprice = totalprice;
    }

    public BigDecimal getFreight() {
        return freight;
    }

    public void setFreight(BigDecimal freight) {
        this.freight = freight;
    }

    public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getAllpay() {
        return allpay;
    }

    public void setAllpay(BigDecimal allpay) {
        this.allpay = allpay;
    }

    public Date getFuturetime() {
        return futuretime;
    }

    public void setFuturetime(Date futuretime) {
        this.futuretime = futuretime;
    }

    public short getOrdertype() {
        return ordertype;
    }

    public void setOrdertype(short ordertype) {
        this.ordertype = ordertype;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public BigDecimal getActualpayment() {
        return actualpayment;
    }

    public void setActualpayment(BigDecimal actualpayment) {
        this.actualpayment = actualpayment;
    }

    public Long getOrderid() {
        return orderid;
    }

    public void setOrderid(Long orderid) {
        this.orderid = orderid;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public String getCouriernumber() {
        return couriernumber;
    }

    public void setCouriernumber(String couriernumber) {
        this.couriernumber = couriernumber;
    }

    public String getLogisticscompany() {
        return logisticscompany;
    }

    public void setLogisticscompany(String logisticscompany) {
        this.logisticscompany = logisticscompany;
    }

    public String getTransactionnumber() {
        return transactionnumber;
    }

    public void setTransactionnumber(String transactionnumber) {
        this.transactionnumber = transactionnumber;
    }
}
