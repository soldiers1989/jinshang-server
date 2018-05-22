package project.jinshang.mod_company.bean;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

public class BuyerCompanyInfo implements Serializable {
    private Long id;

    @ApiModelProperty(notes = "用户id")
    private Long memberid;

    @ApiModelProperty(notes = "shortname")
    private String shortname;

    @ApiModelProperty(notes = "companyname")
    private String companyname;

    @ApiModelProperty(notes = "详细地址")
    private String address;

    @ApiModelProperty(notes = "法人代表")
    private String legalperson;

    @ApiModelProperty(notes = "联系手机")
    private String mobile;

    @ApiModelProperty(notes = "单位电话")
    private String worktelephone;


    @ApiModelProperty(notes = "开户银行")
    private String bankname;

    @ApiModelProperty(notes = "银行帐号")
    private String bankaccount;

    @ApiModelProperty(notes = "纳税号")
    private String taxregistrationcertificate;


    @ApiModelProperty(notes = "结算方式")
    private String methodsettingaccount;

    @ApiModelProperty(notes = "创建时间")
    private Date createdate;

    @ApiModelProperty(notes = "更新时间")
    private Date updatedate;

    @ApiModelProperty(notes = "省")
    private String province;

    @ApiModelProperty(notes = "市")
    private String city;

    @ApiModelProperty(notes = "区")
    private String citysmall;

    @ApiModelProperty(notes = "营业执照")
    private String businesslicencenumberphoto;


    private String bankdeposit;

    private String bankuser;



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

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname == null ? null : shortname.trim();
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname == null ? null : companyname.trim();
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getLegalperson() {
        return legalperson;
    }

    public void setLegalperson(String legalperson) {
        this.legalperson = legalperson == null ? null : legalperson.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getWorktelephone() {
        return worktelephone;
    }

    public void setWorktelephone(String worktelephone) {
        this.worktelephone = worktelephone == null ? null : worktelephone.trim();
    }


    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname == null ? null : bankname.trim();
    }

    public String getBankaccount() {
        return bankaccount;
    }

    public void setBankaccount(String bankaccount) {
        this.bankaccount = bankaccount == null ? null : bankaccount.trim();
    }

    public String getTaxregistrationcertificate() {
        return taxregistrationcertificate;
    }

    public void setTaxregistrationcertificate(String taxregistrationcertificate) {
        this.taxregistrationcertificate = taxregistrationcertificate == null ? null : taxregistrationcertificate.trim();
    }

    public String getMethodsettingaccount() {
        return methodsettingaccount;
    }

    public void setMethodsettingaccount(String methodsettingaccount) {
        this.methodsettingaccount = methodsettingaccount == null ? null : methodsettingaccount.trim();
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

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getCitysmall() {
        return citysmall;
    }

    public void setCitysmall(String citysmall) {
        this.citysmall = citysmall == null ? null : citysmall.trim();
    }

    public String getBusinesslicencenumberphoto() {
        return businesslicencenumberphoto;
    }

    public void setBusinesslicencenumberphoto(String businesslicencenumberphoto) {
        this.businesslicencenumberphoto = businesslicencenumberphoto;
    }

    public String getBankdeposit() {
        return bankdeposit;
    }

    public void setBankdeposit(String bankdeposit) {
        this.bankdeposit = bankdeposit;
    }

    public String getBankuser() {
        return bankuser;
    }

    public void setBankuser(String bankuser) {
        this.bankuser = bankuser;
    }
}