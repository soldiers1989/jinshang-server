package project.jinshang.mod_cash.bean.dto;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

public class BuyerCapitalAdminExcel {
    private Long id;

    @ApiModelProperty(notes = "订单号")
    private String orderno;

    @ApiModelProperty(notes = "交易编号")
    private String tradeno;

    @ApiModelProperty(notes = "类别0=消费1=充值2=退款3=提现4=授信5=授信还款6=违约金7=远期定金8=远期余款9=远期全款")
    private Short capitaltype;

    @ApiModelProperty(notes = "金额")
    private BigDecimal capital;

    @ApiModelProperty(notes = "支付方式0=支付宝1=微信2=银行卡3=余额4=授信")
    private Short paytype;

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

    @ApiModelProperty(notes = "交易对象")
    private String transactionobj;

    @ApiModelProperty(notes = "已出账单标记0=未出账单1=已出账单")
    private Short outbillstate;


    @ApiModelProperty(notes = "交易成功时间")
    private Date successtime;


    @ApiModelProperty(notes = "开始时间")
    private  Date starttime;

    @ApiModelProperty(notes = "结束时间")
    private  Date endtime;


    @ApiModelProperty(notes = "授信账单id")
    private Long billcreateid;

    @ApiModelProperty(notes = "卖方用户id")
    private Long sellerid;

    @ApiModelProperty(notes = "合同金额")
    private BigDecimal allpay;

    @ApiModelProperty(notes = "用户备注")
    private String membermark;

    @ApiModelProperty(notes = "审核备注")
    private String validatemark;

    @ApiModelProperty(notes = "操作时间")
    private Date operatetime;

    @ApiModelProperty(notes = "第三方支付交易号")
    private  String transactionid;

    private  Integer isbackcredit;

    //*******************EXTEND***************************************************//

    private  String companyname;

    @ApiModelProperty(notes = "用户名")
    private  String username;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

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
        this.orderno = orderno == null ? null : orderno.trim();
    }

    public String getTradeno() {
        return tradeno;
    }

    public void setTradeno(String tradeno) {
        this.tradeno = tradeno == null ? null : tradeno.trim();
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
        this.remark = remark == null ? null : remark.trim();
    }

    public String getRechargenumber() {
        return rechargenumber;
    }

    public void setRechargenumber(String rechargenumber) {
        this.rechargenumber = rechargenumber == null ? null : rechargenumber.trim();
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
        this.presentationnumber = presentationnumber == null ? null : presentationnumber.trim();
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
        this.account = account == null ? null : account.trim();
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation == null ? null : operation.trim();
    }

    public String getVerify() {
        return verify;
    }

    public void setVerify(String verify) {
        this.verify = verify == null ? null : verify.trim();
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias == null ? null : alias.trim();
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname == null ? null : bankname.trim();
    }

    public String getBanknamealias() {
        return banknamealias;
    }

    public void setBanknamealias(String banknamealias) {
        this.banknamealias = banknamealias == null ? null : banknamealias.trim();
    }

    public String getBankaccountname() {
        return bankaccountname;
    }

    public void setBankaccountname(String bankaccountname) {
        this.bankaccountname = bankaccountname == null ? null : bankaccountname.trim();
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
        this.transactionobj = transactionobj == null ? null : transactionobj.trim();
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

    public Long getBillcreateid() {
        return billcreateid;
    }

    public void setBillcreateid(Long billcreateid) {
        this.billcreateid = billcreateid;
    }

    public Integer getIsbackcredit() {
        return isbackcredit;
    }

    public void setIsbackcredit(Integer isbackcredit) {
        this.isbackcredit = isbackcredit;
    }


    public Long getSellerid() {
        return sellerid;
    }

    public void setSellerid(Long sellerid) {
        this.sellerid = sellerid;
    }

    public BigDecimal getAllpay() {
        return allpay;
    }

    public void setAllpay(BigDecimal allpay) {
        this.allpay = allpay;
    }

    public String getMembermark() {
        return membermark;
    }

    public void setMembermark(String membermark) {
        this.membermark = membermark;
    }

    public String getValidatemark() {
        return validatemark;
    }

    public void setValidatemark(String validatemark) {
        this.validatemark = validatemark;
    }

    public Date getOperatetime() {
        return operatetime;
    }

    public void setOperatetime(Date operatetime) {
        this.operatetime = operatetime;
    }

    public String getTransactionid() {
        return transactionid;
    }

    public void setTransactionid(String transactionid) {
        this.transactionid = transactionid;
    }
}