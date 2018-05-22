package project.jinshang.mod_purchase.bean;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

public class PurchaseSupply {
    private Long id;

    @ApiModelProperty(notes = "供应商名称")
    private String username;

    @ApiModelProperty(notes = "地区")
    private String area;

    @ApiModelProperty(notes = "详细地址")
    private String address;

    @ApiModelProperty(notes = "供应商全称")
    private String supplyname;

    @ApiModelProperty(notes = "法人")
    private String legal;

    @ApiModelProperty(notes = "运输方式")
    private String transport;

    @ApiModelProperty(notes = "手机")
    private String mobilephone;

    @ApiModelProperty(notes = "电话")
    private String phone;

    @ApiModelProperty(notes = "邮编")
    private String code;

    @ApiModelProperty(notes = "开户银行")
    private String bank;

    @ApiModelProperty(notes = "账号")
    private String bankno;

    @ApiModelProperty(notes = "税号")
    private String texno;

    @ApiModelProperty(notes = "开票类型")
    private String billtype;

    @ApiModelProperty(notes = "结算类型")
    private String settletype;

    @ApiModelProperty(notes = "币总")
    private String currency;

    @ApiModelProperty(notes = "预付款")
    private BigDecimal prepay;

    @ApiModelProperty(notes = "应付款")
    private BigDecimal spay;

    @ApiModelProperty(notes = "往来金额")
    private BigDecimal allpay;

    @ApiModelProperty(notes = "结算时间")
    private String settletime;

    @ApiModelProperty(notes = "业务员")
    private String business;
    @ApiModelProperty(notes = "卖家id")
    private Long memberid;

    public Long getMemberid() {
        return memberid;
    }

    public void setMemberid(Long memberid) {
        this.memberid = memberid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getSupplyname() {
        return supplyname;
    }

    public void setSupplyname(String supplyname) {
        this.supplyname = supplyname == null ? null : supplyname.trim();
    }

    public String getLegal() {
        return legal;
    }

    public void setLegal(String legal) {
        this.legal = legal == null ? null : legal.trim();
    }

    public String getTransport() {
        return transport;
    }

    public void setTransport(String transport) {
        this.transport = transport == null ? null : transport.trim();
    }

    public String getMobilephone() {
        return mobilephone;
    }

    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone == null ? null : mobilephone.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank == null ? null : bank.trim();
    }

    public String getBankno() {
        return bankno;
    }

    public void setBankno(String bankno) {
        this.bankno = bankno == null ? null : bankno.trim();
    }

    public String getTexno() {
        return texno;
    }

    public void setTexno(String texno) {
        this.texno = texno == null ? null : texno.trim();
    }

    public String getBilltype() {
        return billtype;
    }

    public void setBilltype(String billtype) {
        this.billtype = billtype == null ? null : billtype.trim();
    }

    public String getSettletype() {
        return settletype;
    }

    public void setSettletype(String settletype) {
        this.settletype = settletype == null ? null : settletype.trim();
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency == null ? null : currency.trim();
    }

    public BigDecimal getPrepay() {
        return prepay;
    }

    public void setPrepay(BigDecimal prepay) {
        this.prepay = prepay;
    }

    public BigDecimal getSpay() {
        return spay;
    }

    public void setSpay(BigDecimal spay) {
        this.spay = spay;
    }

    public BigDecimal getAllpay() {
        return allpay;
    }

    public void setAllpay(BigDecimal allpay) {
        this.allpay = allpay;
    }

    public String getSettletime() {
        return settletime;
    }

    public void setSettletime(String settletime) {
        this.settletime = settletime == null ? null : settletime.trim();
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business == null ? null : business.trim();
    }
}