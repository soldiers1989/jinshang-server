package project.jinshang.mod_cash.bean.dto;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

public class SalerCapitalAdminExcel {
    private Long id;

    private Long memberid;

    @ApiModelProperty(notes = "交易号")
    private String tradeno;

    @ApiModelProperty(notes = "订单号")
    private String orderno;

    @ApiModelProperty(notes = "交易时间")
    private Date tradetime;

    @ApiModelProperty(notes = "订单金额")
    private BigDecimal ordercapital;

    @ApiModelProperty(notes = "保证金")
    private BigDecimal bail;

    @ApiModelProperty(notes = "违约金")
    private BigDecimal penalty;

    @ApiModelProperty(notes = "退款金额")
    private BigDecimal refundamount;

    @ApiModelProperty(notes = "备注")
    private String remark;

    @ApiModelProperty(notes = "资金类型0=订单金额1=上架保证金2=下架保证金3=退款金额4=充值5=提现6=买家违约金7=卖家违约金8=余款9=全款10=定金")
    private Short capitaltype;

    @ApiModelProperty(notes = "充值单号")
    private String rechargenumber;

    @ApiModelProperty(notes = "平台0=微信1=支付宝2=线下平台3=授信4=银行卡")
    private Short rechargeperform;

    @ApiModelProperty(notes = "提现单号")
    private String presentationnumber;

    @ApiModelProperty(notes = "状态0=待处理1=成功2=失败3=待审核4=审核通过5=审核不通过")
    private Short rechargestate;

    @ApiModelProperty(notes = "")
    private String account;

    @ApiModelProperty(notes = "操作人员")
    private String operation;

    @ApiModelProperty(notes = "审核人员")
    private String verify;

    @ApiModelProperty(notes = "开户银行全称")
    private String bankname;

    @ApiModelProperty(notes = "开户银行")
    private String banknamealias;

    @ApiModelProperty(notes = "银行开户名")
    private String bankaccountname;

    @ApiModelProperty(notes = "昵称")
    private String alias;

    @ApiModelProperty(notes = "提现方式1=微信2=支付宝3=银行卡")
    private Short withdrawtype;



    @ApiModelProperty(notes = "买方id")
    private Long buyerid;


    @ApiModelProperty(notes = "合同金额")
    private BigDecimal allpay;


    @ApiModelProperty(notes = "佣金")
    private  BigDecimal brokerage;

    @ApiModelProperty(notes = "0=未结算1=结算")
    private  Short settlement;


    private  Date successtime;

    @ApiModelProperty(notes = "用户备注")
    private String membermark;


    @ApiModelProperty(notes = "审核备注")
    private String validatemark;

    @ApiModelProperty(notes = "操作时间")
    private Date operatetime;


    @ApiModelProperty(notes = "第三方支付交易号")
    private  String transactionid;

    //********************EXTEND****************************************************//

    @ApiModelProperty(notes = "开始时间")
    private  Date starttime;

    @ApiModelProperty(notes = "结束时间")
    private  Date endtime;

    @ApiModelProperty(notes = "用户名")
    private  String username;

    @ApiModelProperty(notes = "公司名")
    private  String companyname;

    @ApiModelProperty(notes = "店铺名称")
    private  String shopname;


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

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
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

    public Long getMemberid() {
        return memberid;
    }

    public void setMemberid(Long memberid) {
        this.memberid = memberid;
    }

    public String getTradeno() {
        return tradeno;
    }

    public void setTradeno(String tradeno) {
        this.tradeno = tradeno == null ? null : tradeno.trim();
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno == null ? null : orderno.trim();
    }

    public Date getTradetime() {
        return tradetime;
    }

    public void setTradetime(Date tradetime) {
        this.tradetime = tradetime;
    }

    public BigDecimal getOrdercapital() {
        return ordercapital;
    }

    public void setOrdercapital(BigDecimal ordercapital) {
        this.ordercapital = ordercapital;
    }

    public BigDecimal getBail() {
        return bail;
    }

    public void setBail(BigDecimal bail) {
        this.bail = bail;
    }

    public BigDecimal getPenalty() {
        return penalty;
    }

    public void setPenalty(BigDecimal penalty) {
        this.penalty = penalty;
    }

    public BigDecimal getRefundamount() {
        return refundamount;
    }

    public void setRefundamount(BigDecimal refundamount) {
        this.refundamount = refundamount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Short getCapitaltype() {
        return capitaltype;
    }

    public void setCapitaltype(Short capitaltype) {
        this.capitaltype = capitaltype;
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

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Long getBuyerid() {
        return buyerid;
    }

    public void setBuyerid(Long buyerid) {
        this.buyerid = buyerid;
    }

    public BigDecimal getAllpay() {
        return allpay;
    }

    public void setAllpay(BigDecimal allpay) {
        this.allpay = allpay;
    }

    public Date getSuccesstime() {
        return successtime;
    }

    public void setSuccesstime(Date successtime) {
        this.successtime = successtime;
    }

    public BigDecimal getBrokerage() {
        return brokerage;
    }

    public void setBrokerage(BigDecimal brokerage) {
        this.brokerage = brokerage;
    }

    public Short getSettlement() {
        return settlement;
    }

    public void setSettlement(Short settlement) {
        this.settlement = settlement;
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