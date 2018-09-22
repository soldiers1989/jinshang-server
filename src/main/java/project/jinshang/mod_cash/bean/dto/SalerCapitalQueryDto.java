package project.jinshang.mod_cash.bean.dto;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * create : wyh
 * date : 2017/12/11
 */
public class SalerCapitalQueryDto {

    //用户id
    private  Long memberid;

    //交易开始时间
    private Date tradetimeStart;

    //交易结束时间
    private Date tradetimeEnd;

    //交易类型
    private  Short capitaltype;

    //交易编号
    private  String tradeno;

    //交易编号
    private  String orderno;

    //支付方式
    private  Short paytype;


    //充值单号
    private  String rechargenumber;


    //充值平台
    private  Short rechargeperform;


    private  Short rechargestate;




    private  String username;

    @ApiModelProperty(notes = "会员姓名")
    private  String realname;

    private  String companyname;

    private  String shopname;

    //提现单号
    private  String presentationnumber;

    private BigDecimal capitalStart;

    private  BigDecimal capitalEnd;


    private  Short withdrawtype;

    @ApiModelProperty(notes = "卖家是否已向平台开票开违约金发票 0-未开，1-已开，-1= 老数据")
    private Short billtoserver;



    public String getPresentationnumber() {
        return presentationnumber;
    }

    public void setPresentationnumber(String presentationnumber) {
        this.presentationnumber = presentationnumber;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Short getRechargestate() {
        return rechargestate;
    }

    public void setRechargestate(Short rechargestate) {
        this.rechargestate = rechargestate;
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



    public String getTradeno() {
        return tradeno;
    }

    public void setTradeno(String tradeno) {
        this.tradeno = tradeno;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }


    public String getRechargenumber() {
        return rechargenumber;
    }

    public void setRechargenumber(String rechargenumber) {
        this.rechargenumber = rechargenumber;
    }

    public Short getCapitaltype() {
        return capitaltype;
    }

    public void setCapitaltype(Short capitaltype) {
        this.capitaltype = capitaltype;
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

    public Short getWithdrawtype() {
        return withdrawtype;
    }

    public void setWithdrawtype(Short withdrawtype) {
        this.withdrawtype = withdrawtype;
    }


    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public Short getBilltoserver() {
        return billtoserver;
    }

    public void setBilltoserver(Short billtoserver) {
        this.billtoserver = billtoserver;
    }
}
