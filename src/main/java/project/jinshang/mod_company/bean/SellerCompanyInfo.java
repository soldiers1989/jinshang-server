package project.jinshang.mod_company.bean;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;

import java.io.Serializable;
import java.util.Date;

public class SellerCompanyInfo implements Serializable{
    private Long id;

    private Long memberid;

    @ApiModelProperty(notes = "companyname")
    private String companyname;

    @ApiModelProperty(notes = "省")
    private String province;

    @ApiModelProperty(notes = "市")
    private String city;

    @ApiModelProperty(notes = "区")
    private String citysmall;

    @ApiModelProperty(notes = "公司详细地址")
    private String address;

    @ApiModelProperty(notes = "mail")
    private String email;

    @ApiModelProperty(notes = "公司电话")
    private String companytel;

    @ApiModelProperty(notes = "联系人")
    private String linkman;

    @ApiModelProperty(notes = "联系人电话")
    private String linkmantel;

    @ApiModelProperty(notes = "员工人数")
    private Integer employeenum;

    @ApiModelProperty(notes = "注册资本（万元）")
    private Integer regfound;

    @ApiModelProperty(notes = "营业执照")
    private String businesslicencenumber;

    @ApiModelProperty(notes = "营业执照图片地址")
    private String businesslicencenumberphoto;

    @ApiModelProperty(notes = "营业执照开始时间")
    private Date businesslicencestart;

    @ApiModelProperty(notes = "营业执照结束时间")
    private Date businesslicenceend;

    @ApiModelProperty(notes = "经营范围")
    private String businessscope;

    @ApiModelProperty(notes = "银行开户行")
    private String bankname;

    @ApiModelProperty(notes = "公司银行帐号")
    private String bankaccount;

    @ApiModelProperty(notes = "开户行支行名称")
    private String bankbrachname;

    @ApiModelProperty(notes = "支行联行号")
    private String bankbrachaccount;

    @ApiModelProperty(notes = "开会行所在地（省）")
    private String bankprovince;

    @ApiModelProperty(notes = "开会行所在地(市)")
    private String bankcity;

    @ApiModelProperty(notes = "开会行所在地(区)")
    private String bankcitysmall;

    @ApiModelProperty(notes = "开户银行许可证图片")
    private String bankorgnumpic;

    @ApiModelProperty(notes = "支付宝姓名")
    private String alipayname;

    @ApiModelProperty(notes = "支付宝帐号")
    private String alipayno;

    @ApiModelProperty(notes = "微信姓名")
    private String wxname;

    @ApiModelProperty(notes = "微信帐号")
    private String wxno;

    @ApiModelProperty(notes = "税务登记证")
    private String taxregistrationno;

    @ApiModelProperty(notes = "纳税人识别号")
    private String taxregistrationcertificate;

    @ApiModelProperty(notes = "税务登记证电子图片")
    private String taxregistrationnopic;

    @ApiModelProperty(notes = "创建时间")
    private Date createdate;

    @ApiModelProperty(notes = "更新时间")
    private Date updatedate;

    @ApiModelProperty(notes = "")
    private Short validate;

    @ApiModelProperty(notes = "店铺名称")
    private String shopname;

    @ApiModelProperty(notes = "经营分类")
    @ApiParam(hidden = true)
    private Object businesscategory;

    @ApiModelProperty(notes = "商铺等级关联id")
    private Integer shopgradeid;

    @ApiModelProperty(notes = "店铺有效期")
    private String shopperiod;

    @ApiModelProperty(notes = "店铺状态0=开店1=关店")
    private Short shopstate;

    @ApiModelProperty(notes = "是否推荐0=推存1=不推存")
    private Short isrecommend;

    @ApiModelProperty(notes = "发货模式 0-直发，1-代发")
    private Short deliverymode;
    //**************************EXTEND*********************************************//
    @ApiModelProperty(notes = "店铺等级")
    private  String shopgrade;

    @ApiModelProperty(notes = "短信通知，0=不通知，1=通知 （买家下单、买家退货等）")
    private Short smsnotify;

    @ApiModelProperty(notes = "对接ID")
    private String appid;

    @ApiModelProperty(notes = "对接密钥")
    private String appsecret;

    @ApiModelProperty(notes = "对接连接地址")
    private String appurl;

    @ApiModelProperty(notes = "接口状态")
    private Boolean disable;

    @ApiModelProperty(notes = "是否可以自提")
    private Boolean isselflifting;

    @ApiModelProperty(notes = "运费是否计入结算价 0-不计入，1-计入")
    private Short freightmode;

    public String getShopgrade() {
        return shopgrade;
    }

    public void setShopgrade(String shopgrade) {
        this.shopgrade = shopgrade;
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

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname == null ? null : companyname.trim();
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getCompanytel() {
        return companytel;
    }

    public void setCompanytel(String companytel) {
        this.companytel = companytel == null ? null : companytel.trim();
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman == null ? null : linkman.trim();
    }

    public String getLinkmantel() {
        return linkmantel;
    }

    public void setLinkmantel(String linkmantel) {
        this.linkmantel = linkmantel == null ? null : linkmantel.trim();
    }

    public Integer getEmployeenum() {
        return employeenum;
    }

    public void setEmployeenum(Integer employeenum) {
        this.employeenum = employeenum;
    }

    public Integer getRegfound() {
        return regfound;
    }

    public void setRegfound(Integer regfound) {
        this.regfound = regfound;
    }

    public String getBusinesslicencenumber() {
        return businesslicencenumber;
    }

    public void setBusinesslicencenumber(String businesslicencenumber) {
        this.businesslicencenumber = businesslicencenumber == null ? null : businesslicencenumber.trim();
    }

    public String getBusinesslicencenumberphoto() {
        return businesslicencenumberphoto;
    }

    public void setBusinesslicencenumberphoto(String businesslicencenumberphoto) {
        this.businesslicencenumberphoto = businesslicencenumberphoto == null ? null : businesslicencenumberphoto.trim();
    }

    public Date getBusinesslicencestart() {
        return businesslicencestart;
    }

    public void setBusinesslicencestart(Date businesslicencestart) {
        this.businesslicencestart = businesslicencestart;
    }

    public Date getBusinesslicenceend() {
        return businesslicenceend;
    }

    public void setBusinesslicenceend(Date businesslicenceend) {
        this.businesslicenceend = businesslicenceend;
    }

    public String getBusinessscope() {
        return businessscope;
    }

    public void setBusinessscope(String businessscope) {
        this.businessscope = businessscope == null ? null : businessscope.trim();
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

    public String getBankbrachname() {
        return bankbrachname;
    }

    public void setBankbrachname(String bankbrachname) {
        this.bankbrachname = bankbrachname == null ? null : bankbrachname.trim();
    }

    public String getBankbrachaccount() {
        return bankbrachaccount;
    }

    public void setBankbrachaccount(String bankbrachaccount) {
        this.bankbrachaccount = bankbrachaccount == null ? null : bankbrachaccount.trim();
    }

    public String getBankprovince() {
        return bankprovince;
    }

    public void setBankprovince(String bankprovince) {
        this.bankprovince = bankprovince == null ? null : bankprovince.trim();
    }

    public String getBankcity() {
        return bankcity;
    }

    public void setBankcity(String bankcity) {
        this.bankcity = bankcity == null ? null : bankcity.trim();
    }

    public String getBankcitysmall() {
        return bankcitysmall;
    }

    public void setBankcitysmall(String bankcitysmall) {
        this.bankcitysmall = bankcitysmall == null ? null : bankcitysmall.trim();
    }

    public String getBankorgnumpic() {
        return bankorgnumpic;
    }

    public void setBankorgnumpic(String bankorgnumpic) {
        this.bankorgnumpic = bankorgnumpic == null ? null : bankorgnumpic.trim();
    }

    public String getAlipayname() {
        return alipayname;
    }

    public void setAlipayname(String alipayname) {
        this.alipayname = alipayname == null ? null : alipayname.trim();
    }

    public String getAlipayno() {
        return alipayno;
    }

    public void setAlipayno(String alipayno) {
        this.alipayno = alipayno == null ? null : alipayno.trim();
    }

    public String getWxname() {
        return wxname;
    }

    public void setWxname(String wxname) {
        this.wxname = wxname == null ? null : wxname.trim();
    }

    public String getWxno() {
        return wxno;
    }

    public void setWxno(String wxno) {
        this.wxno = wxno == null ? null : wxno.trim();
    }

    public String getTaxregistrationno() {
        return taxregistrationno;
    }

    public void setTaxregistrationno(String taxregistrationno) {
        this.taxregistrationno = taxregistrationno == null ? null : taxregistrationno.trim();
    }

    public String getTaxregistrationcertificate() {
        return taxregistrationcertificate;
    }

    public void setTaxregistrationcertificate(String taxregistrationcertificate) {
        this.taxregistrationcertificate = taxregistrationcertificate == null ? null : taxregistrationcertificate.trim();
    }

    public String getTaxregistrationnopic() {
        return taxregistrationnopic;
    }

    public void setTaxregistrationnopic(String taxregistrationnopic) {
        this.taxregistrationnopic = taxregistrationnopic == null ? null : taxregistrationnopic.trim();
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

    public Short getValidate() {
        return validate;
    }

    public void setValidate(Short validate) {
        this.validate = validate;
    }

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname == null ? null : shopname.trim();
    }

    public Object getBusinesscategory() {
        return businesscategory;
    }

    public void setBusinesscategory(Object businesscategory) {
        this.businesscategory = businesscategory;
    }

    public Integer getShopgradeid() {
        return shopgradeid;
    }

    public void setShopgradeid(Integer shopgradeid) {
        this.shopgradeid = shopgradeid;
    }

    public String getShopperiod() {
        return shopperiod;
    }

    public void setShopperiod(String shopperiod) {
        this.shopperiod = shopperiod == null ? null : shopperiod.trim();
    }

    public Short getShopstate() {
        return shopstate;
    }

    public void setShopstate(Short shopstate) {
        this.shopstate = shopstate;
    }

    public Short getIsrecommend() {
        return isrecommend;
    }

    public void setIsrecommend(Short isrecommend) {
        this.isrecommend = isrecommend;
    }

    public Short getDeliverymode() {
        return deliverymode;
    }

    public void setDeliverymode(Short deliverymode) {
        this.deliverymode = deliverymode;
    }

    public Short getSmsnotify() {
        return smsnotify;
    }

    public void setSmsnotify(Short smsnotify) {
        this.smsnotify = smsnotify;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid == null ? null : appid.trim();
    }

    public String getAppsecret() {
        return appsecret;
    }

    public void setAppsecret(String appsecret) {
        this.appsecret = appsecret == null ? null : appsecret.trim();
    }

    public String getAppurl() {
        return appurl;
    }

    public void setAppurl(String appurl) {
        this.appurl = appurl == null ? null : appurl.trim();
    }

    public Boolean getDisable() {
        return disable;
    }

    public void setDisable(Boolean disable) {
        this.disable = disable;
    }

    public Boolean getIsselflifting() {
        return isselflifting;
    }

    public void setIsselflifting(Boolean isselflifting) {
        this.isselflifting = isselflifting;
    }

    public Short getFreightmode() {
        return freightmode;
    }

    public void setFreightmode(Short freightmode) {
        this.freightmode = freightmode;
    }
}