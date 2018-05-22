package project.jinshang.mod_invoice.bean;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;


public class InvoiceInfo {

    private Long id;

    @ApiModelProperty(notes = "用户id")
    private Long memberid;

    @ApiModelProperty(notes = "发票开头")
    private String invoiceheadup;

    @ApiModelProperty(notes = "开户行")
    private String bankofaccounts;

    @ApiModelProperty(notes = "税号")
    private String texno;

    @ApiModelProperty(notes = "账号")
    private String account;

    @ApiModelProperty(notes = "发票地址")
    private String address;


    @ApiModelProperty(notes = "收票地址")
    private String receiveaddress;


    @ApiModelProperty(notes = "联系人")
    private String linkman;

    @ApiModelProperty(notes = "电话")
    private String phone;

    @ApiModelProperty(notes = "是否是默认信息 ， 0：不是  1：是")
    private Short defaultbill;

    @ApiModelProperty(notes = "创建日期")
    private Date createdate;

    @ApiModelProperty(notes = "更新日期")
    private Date updatedate;

    @ApiModelProperty(notes = "是否可用 0： 不可用 1：可用 （买方升级企业后 需要判断）")
    private Short available;

    @ApiModelProperty(notes = "是否公司账户")
    private Boolean company;

    public Boolean getCompany() {
        return company;
    }

    public void setCompany(Boolean company) {
        this.company = company;
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

    public String getInvoiceheadup() {
        return invoiceheadup;
    }

    public void setInvoiceheadup(String invoiceheadup) {
        this.invoiceheadup = invoiceheadup;
    }

    public String getBankofaccounts() {
        return bankofaccounts;
    }

    public void setBankofaccounts(String bankofaccounts) {
        this.bankofaccounts = bankofaccounts;
    }

    public String getTexno() {
        return texno;
    }

    public void setTexno(String texno) {
        this.texno = texno;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Short getDefaultbill() {
        return defaultbill;
    }

    public void setDefaultbill(Short defaultbill) {
        this.defaultbill = defaultbill;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Date getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
    }

    public Short getAvailable() {
        return available;
    }

    public void setAvailable(Short available) {
        this.available = available;
    }

    public String getReceiveaddress() {
        return receiveaddress;
    }

    public void setReceiveaddress(String receiveaddress) {
        this.receiveaddress = receiveaddress;
    }
}
