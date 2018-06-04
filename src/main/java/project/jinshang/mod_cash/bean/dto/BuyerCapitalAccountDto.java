package project.jinshang.mod_cash.bean.dto;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 买家对账单对应的Dto
 * @author xiazy
 * @create 2018-05-14 14:31
 **/
public class BuyerCapitalAccountDto {

    @ApiModelProperty(notes = "交易时间")
    private Date tradetime;
    @ApiModelProperty(notes = "单号")
    private String oddnumber;
    @ApiModelProperty(notes = "类别{0=消费1=充值2=退款3=提现4=授信5=授信还款6=违约金7=远期定金8=远期余款9=远期全款10=卖家违约金11=授信未出账单还款}")
    private short capitaltype;
    @ApiModelProperty(notes = "类别(中文)")
    private String capitaltypename;
    @ApiModelProperty(notes = "发货金额")
    private BigDecimal deliveryamount;
    @ApiModelProperty(notes = "收款金额")
    private BigDecimal receiptamount;
    @ApiModelProperty(notes = "其他")
    private BigDecimal otheramount;
    @ApiModelProperty(notes = "应收账款")
    private BigDecimal receivableaccount;
    @ApiModelProperty(notes = "开票金额")
    private BigDecimal invoiceamount;
    @ApiModelProperty(notes = "发票结余")
    private BigDecimal invoicebalance;
    @ApiModelProperty(notes = "支付方式{0=支付宝1=微信2=银行卡3=余额4=授信}")
    private short  paytype;
    @ApiModelProperty(notes = "支付单号")
    private String payno;
    @ApiModelProperty(notes = "备注")
    private String remark;
    @ApiModelProperty(notes = "状态0=待处理1=成功2=失败3=待审核4=审核通过5=审核不通过")
    private Short rechargestate;
    @ApiModelProperty(notes = "会员名称")
    private String username;
    @ApiModelProperty(notes = "充值平台0=微信1=支付宝2=线下平台3=银行卡")
    private Short rechargeperform;
    public Date getTradetime() {
        return tradetime;
    }

    public void setTradetime(Date tradetime) {
        this.tradetime = tradetime;
    }

    public String getOddnumber() {
        return oddnumber;
    }

    public void setOddnumber(String oddnumber) {
        this.oddnumber = oddnumber;
    }

    public short getCapitaltype() {
        return capitaltype;
    }

    public void setCapitaltype(short capitaltype) {
        this.capitaltype = capitaltype;
    }

    public BigDecimal getDeliveryamount() {
        return deliveryamount;
    }

    public void setDeliveryamount(BigDecimal deliveryamount) {
        this.deliveryamount = deliveryamount;
    }

    public BigDecimal getReceiptamount() {
        return receiptamount;
    }

    public void setReceiptamount(BigDecimal receiptamount) {
        this.receiptamount = receiptamount;
    }

    public BigDecimal getOtheramount() {
        return otheramount;
    }

    public void setOtheramount(BigDecimal otheramount) {
        this.otheramount = otheramount;
    }

    public BigDecimal getReceivableaccount() {
        return receivableaccount;
    }

    public void setReceivableaccount(BigDecimal receivableaccount) {
        this.receivableaccount = receivableaccount;
    }

    public BigDecimal getInvoiceamount() {
        return invoiceamount;
    }

    public void setInvoiceamount(BigDecimal invoiceamount) {
        this.invoiceamount = invoiceamount;
    }

    public BigDecimal getInvoicebalance() {
        return invoicebalance;
    }

    public void setInvoicebalance(BigDecimal invoicebalance) {
        this.invoicebalance = invoicebalance;
    }

    public short getPaytype() {
        return paytype;
    }

    public void setPaytype(short paytype) {
        this.paytype = paytype;
    }

    public String getPayno() {
        return payno;
    }

    public void setPayno(String payno) {
        this.payno = payno;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCapitaltypename() {
        return capitaltypename;
    }

    public void setCapitaltypename(String capitaltypename) {
        this.capitaltypename = capitaltypename;
    }

    public Short getRechargestate() {
        return rechargestate;
    }

    public void setRechargestate(Short rechargestate) {
        this.rechargestate = rechargestate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Short getRechargeperform() {
        return rechargeperform;
    }

    public void setRechargeperform(Short rechargeperform) {
        this.rechargeperform = rechargeperform;
    }
}
