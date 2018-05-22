package project.jinshang.mod_bankaccount.bean;

import io.swagger.annotations.ApiModelProperty;

public class BankAccount {
    private Long id;
    @ApiModelProperty(notes = "会员ID")
    private Long memberid;

    @ApiModelProperty(notes = "开户银行")
    private String bankaccountname;

    @ApiModelProperty(notes = "开户帐号")
    private String bankaccountnumber;

    @ApiModelProperty(notes = "开户行")
    private String bankname;

    @ApiModelProperty(notes = "开户名")
    private String bankusername;

    @ApiModelProperty(notes = "税号")
    private String taxregistrationcertificate;

    @ApiModelProperty(notes = "是否默认使用该地址{0:不默认1:默认}")
    private Short isdefault;

    @ApiModelProperty(notes = "")
    private Short batype;

    @ApiModelProperty(notes = "身份证号")
    private String idCard;

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

    public String getBankaccountname() {
        return bankaccountname;
    }

    public void setBankaccountname(String bankaccountname) {
        this.bankaccountname = bankaccountname == null ? null : bankaccountname.trim();
    }

    public String getBankaccountnumber() {
        return bankaccountnumber;
    }

    public void setBankaccountnumber(String bankaccountnumber) {
        this.bankaccountnumber = bankaccountnumber == null ? null : bankaccountnumber.trim();
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname == null ? null : bankname.trim();
    }

    public String getBankusername() {
        return bankusername;
    }

    public void setBankusername(String bankusername) {
        this.bankusername = bankusername == null ? null : bankusername.trim();
    }

    public String getTaxregistrationcertificate() {
        return taxregistrationcertificate;
    }

    public void setTaxregistrationcertificate(String taxregistrationcertificate) {
        this.taxregistrationcertificate = taxregistrationcertificate == null ? null : taxregistrationcertificate.trim();
    }

    public Short getIsdefault() {
        return isdefault;
    }

    public void setIsdefault(Short isdefault) {
        this.isdefault = isdefault;
    }

    public Short getBatype() {
        return batype;
    }

    public void setBatype(Short batype) {
        this.batype = batype;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }
}