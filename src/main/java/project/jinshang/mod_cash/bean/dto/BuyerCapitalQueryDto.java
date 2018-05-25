package project.jinshang.mod_cash.bean.dto;

/**
 * create : wyh
 * date : 2017/12/11
 */

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 买家资金明细查询DTO
 */
public class BuyerCapitalQueryDto {

    //用户id
    private  Long memberid;

    //交易开始时间
    private Date tradetimeStart;

    //交易结束时间
    private Date tradetimeEnd;


    //交易编号
    private  String tradeno;



    @ApiModelProperty(notes = "支付方式")
    private Short paytype;


    @ApiModelProperty(notes = "充值平台")
    private Short rechargeperform;

    @ApiModelProperty(notes = "状态0=待处理1=成功2=失败3=待审核4=审核通过5=审核不通过")
    private Short rechargestate;

    @ApiModelProperty(notes = "类别")
    private Short capitaltype;


    private  String username;

    @ApiModelProperty(notes = "会员姓名")
    private  String realname;

    private  String companyname;

    @ApiModelProperty(notes = "充值单号")
    private  String rechargenumber;

    @ApiModelProperty(notes = "提现单号")
    private  String presentationnumber;


    private BigDecimal capitalStart;

    private  BigDecimal capitalEnd;


    private  String shopname;


    @ApiModelProperty(notes = "订单号")
    private  String orderno;


    @ApiModelProperty(notes = "授信账单id")
    private  Integer billcreateid;

    @ApiModelProperty(notes = "提现方式1=微信2=支付宝3=银行卡")
    private Short withdrawtype;

    public Short getWithdrawtype() {
        return withdrawtype;
    }

    public void setWithdrawtype(Short withdrawtype) {
        this.withdrawtype = withdrawtype;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public BigDecimal getCapitalStart() {
        return capitalStart;
    }

    public void setCapitalStart(BigDecimal capitalStart) {
        this.capitalStart = capitalStart;
    }

    public BigDecimal getCapitalEnd() {
        return capitalEnd;
    }

    public void setCapitalEnd(BigDecimal capitalEnd) {
        this.capitalEnd = capitalEnd;
    }

    public String getRechargenumber() {
        return rechargenumber;
    }

    public void setRechargenumber(String rechargenumber) {
        this.rechargenumber = rechargenumber;
    }

    public String getPresentationnumber() {
        return presentationnumber;
    }

    public void setPresentationnumber(String presentationnumber) {
        this.presentationnumber = presentationnumber;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getMemberid() {
        return memberid;
    }

    public void setMemberid(Long memberid) {
        this.memberid = memberid;
    }

    public Date getTradetimeStart() {
        return tradetimeStart;
    }

    public void setTradetimeStart(Date tradetimeStart) {
        this.tradetimeStart = tradetimeStart;
    }

    public Date getTradetimeEnd() {
        return tradetimeEnd;
    }

    public void setTradetimeEnd(Date tradetimeEnd) {
        this.tradetimeEnd = tradetimeEnd;
    }

    public Short getCapitaltype() {
        return capitaltype;
    }

    public void setCapitaltype(Short capitaltype) {
        this.capitaltype = capitaltype;
    }

    public String getTradeno() {
        return tradeno;
    }

    public void setTradeno(String tradeno) {
        this.tradeno = tradeno;
    }

    public Short getPaytype() {
        return paytype;
    }

    public void setPaytype(Short paytype) {
        this.paytype = paytype;
    }

    public Short getRechargeperform() {
        return rechargeperform;
    }


    public void setRechargeperform(Short rechargeperform) {
        this.rechargeperform = rechargeperform;
    }



    public Short getRechargestate() {
        return rechargestate;
    }

    public void setRechargestate(Short rechargestate) {
        this.rechargestate = rechargestate;
    }


    public Integer getBillcreateid() {
        return billcreateid;
    }

    public void setBillcreateid(Integer billcreateid) {
        this.billcreateid = billcreateid;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }
}
