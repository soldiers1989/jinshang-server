package project.jinshang.mod_cash.bean.dto;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * create : wyh
 * date : 2017/12/11
 */
public class BuyerCapitalViewDto {

    private Long id;

    @ApiModelProperty(notes = "订单号")
    private String orderno;

    @ApiModelProperty(notes = "交易编号")
    private String tradeno;

    @ApiModelProperty(notes = "类别{0=消费1=充值2=退款3=提现4=授信5=授信还款}")
    private Short capitaltype;

    @ApiModelProperty(notes = "金额")
    private BigDecimal capital;

    @ApiModelProperty(notes = "支付方式0=支付宝1=微信2=银行卡3=余额4=授信")
    private Short paytype;

    @ApiModelProperty(notes = "开票金额")
    private BigDecimal invoiceamount;

    @ApiModelProperty(notes = "是否开票0=不开票1=开票")
    private Short isinvoice;

    @ApiModelProperty(notes = "会员id")
    private Long memberid;

    @ApiModelProperty(notes = "交易时间")
    private Date tradetime;

    @ApiModelProperty(notes = "备注")
    private String remark;

    @ApiModelProperty(notes = "充值单号")
    private String rechargenumber;

    @ApiModelProperty(notes = "充值平台0=微信1=支付宝2=线下平台3=银行卡")
    private Short rechargeperform;

    @ApiModelProperty(notes = "提现单号")
    private String presentationnumber;

    @ApiModelProperty(notes = "状态0=待处理1=成功2=失败3=待审核4=审核通过5=审核不通过")
    private Short rechargestate;

    @ApiModelProperty(notes = "账号（提现或充值）")
    private String account;

    @ApiModelProperty(notes = "操作人员")
    private String operation;

    @ApiModelProperty(notes = "审核人员")
    private String verify;

    @ApiModelProperty(notes = "昵称")
    private String alias;

    @ApiModelProperty(notes = "开户银行全称")
    private String bankname;

    @ApiModelProperty(notes = "开户银行")
    private String banknamealias;

    @ApiModelProperty(notes = "银行开户名")
    private String bankaccountname;

    @ApiModelProperty(notes = "提现方式1=微信2=支付宝3=银行卡")
    private Short withdrawtype;

    private String transactionobj;

    private Short outbillstate;


    private Date successtime;

    private Long recordid;

    @ApiModelProperty(notes = "第三方支付交易号")
    private String transactionid;

    @ApiModelProperty(notes = "发货号")
    private String deliveryno;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

    public String getTradeno() {
        return tradeno;
    }

    public void setTradeno(String tradeno) {
        this.tradeno = tradeno;
    }

    public Short getCapitaltype() {
        return capitaltype;
    }

    public void setCapitaltype(Short capitaltype) {
        this.capitaltype = capitaltype;
    }

    public BigDecimal getCapital() {
        return capital;
    }

    public void setCapital(BigDecimal capital) {
        this.capital = capital;
    }

    public Short getPaytype() {
        return paytype;
    }

    public void setPaytype(Short paytype) {
        this.paytype = paytype;
    }

    public BigDecimal getInvoiceamount() {
        return invoiceamount;
    }

    public void setInvoiceamount(BigDecimal invoiceamount) {
        this.invoiceamount = invoiceamount;
    }

    public Short getIsinvoice() {
        return isinvoice;
    }

    public void setIsinvoice(Short isinvoice) {
        this.isinvoice = isinvoice;
    }

    public Long getMemberid() {
        return memberid;
    }

    public void setMemberid(Long memberid) {
        this.memberid = memberid;
    }

    public Date getTradetime() {
        return tradetime;
    }

    public void setTradetime(Date tradetime) {
        this.tradetime = tradetime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRechargenumber() {
        return rechargenumber;
    }

    public void setRechargenumber(String rechargenumber) {
        this.rechargenumber = rechargenumber;
    }

    public Short getRechargeperform() {
        return rechargeperform;
    }

    public void setRechargeperform(Short rechargeperform) {
        this.rechargeperform = rechargeperform;
    }

    public String getPresentationnumber() {
        return presentationnumber;
    }

    public void setPresentationnumber(String presentationnumber) {
        this.presentationnumber = presentationnumber;
    }

    public Short getRechargestate() {
        return rechargestate;
    }

    public void setRechargestate(Short rechargestate) {
        this.rechargestate = rechargestate;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getVerify() {
        return verify;
    }

    public void setVerify(String verify) {
        this.verify = verify;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }

    public String getBanknamealias() {
        return banknamealias;
    }

    public void setBanknamealias(String banknamealias) {
        this.banknamealias = banknamealias;
    }

    public String getBankaccountname() {
        return bankaccountname;
    }

    public void setBankaccountname(String bankaccountname) {
        this.bankaccountname = bankaccountname;
    }

    public Short getWithdrawtype() {
        return withdrawtype;
    }

    public void setWithdrawtype(Short withdrawtype) {
        this.withdrawtype = withdrawtype;
    }

    public String getTransactionobj() {
        return transactionobj;
    }

    public void setTransactionobj(String transactionobj) {
        this.transactionobj = transactionobj;
    }

    public Short getOutbillstate() {
        return outbillstate;
    }

    public void setOutbillstate(Short outbillstate) {
        this.outbillstate = outbillstate;
    }

    public Date getSuccesstime() {
        return successtime;
    }

    public void setSuccesstime(Date successtime) {
        this.successtime = successtime;
    }

    public Long getRecordid() {
        return recordid;
    }

    public void setRecordid(Long recordid) {
        this.recordid = recordid;
    }

    public String getTransactionid() {
        return transactionid;
    }

    public void setTransactionid(String transactionid) {
        this.transactionid = transactionid;
    }

    public String getDeliveryno() {
        return deliveryno;
    }

    public void setDeliveryno(String deliveryno) {
        this.deliveryno = deliveryno;
    }
}
