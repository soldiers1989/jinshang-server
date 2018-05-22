package project.jinshang.mod_product.bean;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

public class BillingRecord {
    private Long id;

    @ApiModelProperty(notes = "订单号")
    private String orderno;

    @ApiModelProperty(notes = "同一批次单订标识")
    private String orderuuid;

    @ApiModelProperty(notes = "发票抬头")
    private String invoiceheadup;

    @ApiModelProperty(notes = "税号")
    private String texno;

    @ApiModelProperty(notes = "开户行")
    private String bankofaccounts;

    @ApiModelProperty(notes = "账号")
    private String account;

    @ApiModelProperty(notes = "电话")
    private String phone;

    @ApiModelProperty(notes = "开票地址")
    private String address;


    @ApiModelProperty(notes = "收票地址")
    private String receiveaddress;

    @ApiModelProperty(notes = "企业级发票 0：不是 1：是")
    private Short remark;

    @ApiModelProperty(notes = "物流单号")
    private String expressno;

    @ApiModelProperty(notes = "买家id")
    private Long memberid;

    @ApiModelProperty(notes = "发票id")
    private Long invoiceid;

    @ApiModelProperty(notes = "创建时间")
    private Date createtime;

    @ApiModelProperty(notes = "会员名称")
    private String membername;

    @ApiModelProperty(notes = "开票金额")
    private BigDecimal billcash;

    @ApiModelProperty(notes = "物流公司")
    private String expresscom;

    @ApiModelProperty(notes = "开票号")
    private String billno;

    @ApiModelProperty(notes = "开票时间")
    private Date billtime;

    @ApiModelProperty(notes = "开票状态 -1=提交订单申请开票 0=待开票1=未收到2=确认")
    private Short state;

    @ApiModelProperty(notes = "开票类型")
    private String billtype;

    @ApiModelProperty(notes = "发票类型0=纸质1=电子")
    private Short billingrecordtype;

    public String getBilltype() {
        return billtype;
    }

    public void setBilltype(String billtype) {
        this.billtype = billtype;
    }

    public Short getBillingrecordtype() {
        return billingrecordtype;
    }

    public void setBillingrecordtype(Short billingrecordtype) {
        this.billingrecordtype = billingrecordtype;
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
        this.orderno = orderno;
    }

    public String getOrderuuid() {
        return orderuuid;
    }

    public void setOrderuuid(String orderuuid) {
        this.orderuuid = orderuuid;
    }

    public String getInvoiceheadup() {
        return invoiceheadup;
    }

    public void setInvoiceheadup(String invoiceheadup) {
        this.invoiceheadup = invoiceheadup == null ? null : invoiceheadup.trim();
    }

    public String getTexno() {
        return texno;
    }

    public void setTexno(String texno) {
        this.texno = texno == null ? null : texno.trim();
    }

    public String getBankofaccounts() {
        return bankofaccounts;
    }

    public void setBankofaccounts(String bankofaccounts) {
        this.bankofaccounts = bankofaccounts == null ? null : bankofaccounts.trim();
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Short getRemark() {
        return remark;
    }

    public void setRemark(Short remark) {
        this.remark = remark;
    }

    public String getExpressno() {
        return expressno;
    }

    public void setExpressno(String expressno) {
        this.expressno = expressno;
    }

    public Long getMemberid() {
        return memberid;
    }

    public void setMemberid(Long memberid) {
        this.memberid = memberid;
    }

    public Long getInvoiceid() {
        return invoiceid;
    }

    public void setInvoiceid(Long invoiceid) {
        this.invoiceid = invoiceid;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getMembername() {
        return membername;
    }

    public void setMembername(String membername) {
        this.membername = membername;
    }

    public BigDecimal getBillcash() {
        return billcash;
    }

    public void setBillcash(BigDecimal billcash) {
        this.billcash = billcash;
    }

    public String getExpresscom() {
        return expresscom;
    }

    public void setExpresscom(String expresscom) {
        this.expresscom = expresscom;
    }

    public String getBillno() {
        return billno;
    }

    public void setBillno(String billno) {
        this.billno = billno;
    }

    public Date getBilltime() {
        return billtime;
    }

    public void setBilltime(Date billtime) {
        this.billtime = billtime;
    }

    public Short getState() {
        return state;
    }

    public void setState(Short state) {
        this.state = state;
    }


    public String getReceiveaddress() {
        return receiveaddress;
    }

    public void setReceiveaddress(String receiveaddress) {
        this.receiveaddress = receiveaddress;
    }
}