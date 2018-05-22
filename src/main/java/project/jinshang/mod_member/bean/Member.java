package project.jinshang.mod_member.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import project.jinshang.mod_company.bean.BuyerCompanyInfo;
import project.jinshang.mod_company.bean.SellerCompanyInfo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@ApiModel(description = "会员")
public class Member implements Serializable{
    private Long id;

    @ApiModelProperty(notes = "用户名")
    private String username;


    private String password;


    private String passwordsalt;

    @ApiModelProperty(notes = "手机号")
    private String mobile;

    @ApiModelProperty(notes = "邮箱")
    private String email;

    @ApiModelProperty(notes = "邮编")
    private String postcode;

    @ApiModelProperty(notes = "昵称")
    private String nick;

    @ApiModelProperty(notes = "真实姓名")
    private String realname;

    @ApiModelProperty(notes = "地址")
    private String address;

    @ApiModelProperty(notes = "是否停用")
    private Boolean disabled;

    @ApiModelProperty(notes = "创建时间")
    private Date createdate;

    @ApiModelProperty(notes = "最后登陆时间")
    private Date lastlogindate;

    @ApiModelProperty(notes = "头像")
    private String photo;

    @ApiModelProperty(notes = "类型：0-买家 1-卖家")
    private Short type;


    private String paypassword;

    private String paypasswordsalt;

    @ApiModelProperty(notes = "是否公司")
    private Boolean company;

    @ApiModelProperty(notes = "是否需要审核")
    private Boolean reviewed;

    @ApiModelProperty(notes = "会员等级")
    private Long gradleid;

    private String deliveryregionid;

    @ApiModelProperty(notes = "地址")
    private String deliveryaddress;

    @ApiModelProperty(notes = "会员积分")
    private BigDecimal integrals;

    @ApiModelProperty(notes = "买家余额")
    private BigDecimal balance;

    @ApiModelProperty(notes = "支付宝账户")
    private String alipay;

    @ApiModelProperty(notes = "微信账户")
    private String wxpay;

    @ApiModelProperty(notes = "注册途径")
    private String way;

    @ApiModelProperty(notes = "介绍人")
    private String waysalesman;

    @ApiModelProperty(notes = "邀请码")
    private String invitecode;

    @ApiModelProperty(notes = "业务员")
    private String clerkname;

    @ApiModelProperty(notes = "会员标签")
    private String labelid;

    @ApiModelProperty(notes = "父ID")
    private Long parentid;

    @ApiModelProperty(notes = "主账户名")
    private String parentname;

    @ApiModelProperty(notes = "权限")
    private Object menu;

    @ApiModelProperty(notes = "是否主账户")
    private Boolean flag;

    @ApiModelProperty(notes = "备注")
    private String remark;

    private String qq;

    private String sex;

    @ApiModelProperty(notes = "传真")
    private String faxes;

    @ApiModelProperty(notes = "固定电话")
    private String telephone;

    @ApiModelProperty(notes = "爱好")
    private String hobby;

    @ApiModelProperty(notes = "头像照片地址")
    private String favicon;

    @ApiModelProperty(notes = "卖家账户余额")
    private BigDecimal sellerbanlance;

    @ApiModelProperty(notes = "卖家账户冻结余额")
    private BigDecimal sellerfreezebanlance;


    @ApiModelProperty(notes = "买家授信额度")
    private BigDecimal creditlimit;


    @ApiModelProperty(notes = "买家授信状态 0=未开通，1=已开通，2=禁用授信")
    private Short creditstate;


    @ApiModelProperty(notes = "买家已用授信额度")
    private BigDecimal usedlimit;

    @ApiModelProperty(notes = "买家可用授信额度")
    private BigDecimal availablelimit;

    @ApiModelProperty(notes = "买家最后还款日")
    private Integer lastday;

    @ApiModelProperty(notes = "卖家账单日")
    private Integer billday;


    @ApiModelProperty(notes = "登录地址源 seller：卖家 buyer:买家")
    private  String from;

    @ApiModelProperty(notes = "登录方式 main:主帐号登录，sub:子帐号登录")
    private  String loginType;


    @ApiModelProperty(notes = "卖家公司信息")
    private SellerCompanyInfo sellerCompanyInfo;

    @ApiModelProperty(notes = "买家公司信息")
    private BuyerCompanyInfo buyerCompanyInfo;


    @ApiModelProperty(notes = "卖家减价率状态")
    private Short membersettingstate;

    @ApiModelProperty(notes = "开票金额")
    private BigDecimal billmoney;



    @ApiModelProperty(notes = "省")
    private  String province;

    @ApiModelProperty(notes = "市")
    private  String city;

    @ApiModelProperty(notes = "区")
    private  String citysmall;



    @ApiModelProperty(notes = "会员等级")
    private  String gradename;

    @ApiModelProperty(notes = "宝贝与描述相符")
    private BigDecimal eva1;

    @ApiModelProperty(notes = "卖家服务")
    private BigDecimal eva2;

    @ApiModelProperty(notes = "物流服务")
    private BigDecimal eva3;

    @ApiModelProperty(notes = "卖家组id")
    private Long sellergroupid;


    private  String groupname;

    @ApiModelProperty(notes = "可用积分")
    private BigDecimal availableintegral;

    @ApiModelProperty(notes = "货款金额")
    private BigDecimal goodsbanlance;
    @ApiModelProperty(notes = "是否是服务商")
    private Boolean services;

    @ApiModelProperty(notes = "是否购买")
    private Short isbuy;


    public Short getIsbuy() {
        return isbuy;
    }

    public void setIsbuy(Short isbuy) {
        this.isbuy = isbuy;
    }

    public Boolean getServices() {
        return services;
    }

    public void setServices(Boolean services) {
        this.services = services;
    }

    public BigDecimal getGoodsbanlance() {
        return goodsbanlance;
    }

    public void setGoodsbanlance(BigDecimal goodsbanlance) {
        this.goodsbanlance = goodsbanlance;
    }

    /*
        后台会员搜索
         */
    private Date starttime;
    private Date endtime;
    private Date begainlastlogindate;
    private Date overlastlogindate;




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
    public Date getBegainlastlogindate() {
        return begainlastlogindate;
    }
    public void setBegainlastlogindate(Date begainlastlogindate) {
        this.begainlastlogindate = begainlastlogindate;
    }
    public Date getOverlastlogindate() {
        return overlastlogindate;
    }
    public void setOverlastlogindate(Date overlastlogindate) {
        this.overlastlogindate = overlastlogindate;
    }

    public SellerCompanyInfo getSellerCompanyInfo() {
        return sellerCompanyInfo;
    }

    public void setSellerCompanyInfo(SellerCompanyInfo sellerCompanyInfo) {
        this.sellerCompanyInfo = sellerCompanyInfo;
    }

    public BuyerCompanyInfo getBuyerCompanyInfo() {
        return buyerCompanyInfo;
    }

    public void setBuyerCompanyInfo(BuyerCompanyInfo buyerCompanyInfo) {
        this.buyerCompanyInfo = buyerCompanyInfo;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getPasswordsalt() {
        return passwordsalt;
    }

    public void setPasswordsalt(String passwordsalt) {
        this.passwordsalt = passwordsalt == null ? null : passwordsalt.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode == null ? null : postcode.trim();
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick == null ? null : nick.trim();
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Date getLastlogindate() {
        return lastlogindate;
    }

    public void setLastlogindate(Date lastlogindate) {
        this.lastlogindate = lastlogindate;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo == null ? null : photo.trim();
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public String getPaypassword() {
        return paypassword;
    }

    public void setPaypassword(String paypassword) {
        this.paypassword = paypassword == null ? null : paypassword.trim();
    }

    public String getPaypasswordsalt() {
        return paypasswordsalt;
    }

    public void setPaypasswordsalt(String paypasswordsalt) {
        this.paypasswordsalt = paypasswordsalt == null ? null : paypasswordsalt.trim();
    }

    public Boolean getCompany() {
        return company;
    }

    public void setCompany(Boolean company) {
        this.company = company;
    }

    public Boolean getReviewed() {
        return reviewed;
    }

    public void setReviewed(Boolean reviewed) {
        this.reviewed = reviewed;
    }

    public Long getGradleid() {
        return gradleid;
    }

    public void setGradleid(Long gradleid) {
        this.gradleid = gradleid;
    }

    public String getDeliveryregionid() {
        return deliveryregionid;
    }

    public void setDeliveryregionid(String deliveryregionid) {
        this.deliveryregionid = deliveryregionid == null ? null : deliveryregionid.trim();
    }

    public String getDeliveryaddress() {
        return deliveryaddress;
    }

    public void setDeliveryaddress(String deliveryaddress) {
        this.deliveryaddress = deliveryaddress == null ? null : deliveryaddress.trim();
    }

    public BigDecimal getIntegrals() {
        return integrals;
    }

    public void setIntegrals(BigDecimal integrals) {
        this.integrals = integrals;
    }

    public BigDecimal getAvailableintegral() {
        return availableintegral;
    }

    public void setAvailableintegral(BigDecimal availableintegral) {
        this.availableintegral = availableintegral;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getAlipay() {
        return alipay;
    }

    public void setAlipay(String alipay) {
        this.alipay = alipay == null ? null : alipay.trim();
    }

    public String getWxpay() {
        return wxpay;
    }

    public void setWxpay(String wxpay) {
        this.wxpay = wxpay == null ? null : wxpay.trim();
    }

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way == null ? null : way.trim();
    }

    public String getWaysalesman() {
        return waysalesman;
    }

    public void setWaysalesman(String waysalesman) {
        this.waysalesman = waysalesman == null ? null : waysalesman.trim();
    }

    public String getInvitecode() {
        return invitecode;
    }

    public void setInvitecode(String invitecode) {
        this.invitecode = invitecode == null ? null : invitecode.trim();
    }

    public String getClerkname() {
        return clerkname;
    }

    public void setClerkname(String clerkname) {
        this.clerkname = clerkname == null ? null : clerkname.trim();
    }

    public String getLabelid() {
        return labelid;
    }

    public void setLabelid(String labelid) {
        this.labelid = labelid;
    }

    public Long getParentid() {
        return parentid;
    }

    public void setParentid(Long parentid) {
        this.parentid = parentid;
    }

    public String getParentname() {
        return parentname;
    }

    public void setParentname(String parentname) {
        this.parentname = parentname == null ? null : parentname.trim();
    }

    public Object getMenu() {
        return menu;
    }

    public void setMenu(Object menu) {
        this.menu = menu;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq == null ? null : qq.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getFaxes() {
        return faxes;
    }

    public void setFaxes(String faxes) {
        this.faxes = faxes == null ? null : faxes.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby == null ? null : hobby.trim();
    }

    public String getFavicon() {
        return favicon;
    }

    public void setFavicon(String favicon) {
        this.favicon = favicon == null ? null : favicon.trim();
    }

    public BigDecimal getSellerbanlance() {
        return sellerbanlance;
    }

    public void setSellerbanlance(BigDecimal sellerbanlance) {
        this.sellerbanlance = sellerbanlance;
    }

    public BigDecimal getSellerfreezebanlance() {
        return sellerfreezebanlance;
    }

    public void setSellerfreezebanlance(BigDecimal sellerfreezebanlance) {
        this.sellerfreezebanlance = sellerfreezebanlance;
    }


    public BigDecimal getCreditlimit() {
        return creditlimit;
    }

    public void setCreditlimit(BigDecimal creditlimit) {
        this.creditlimit = creditlimit;
    }

    public BigDecimal getUsedlimit() {
        return usedlimit;
    }

    public void setUsedlimit(BigDecimal usedlimit) {
        this.usedlimit = usedlimit;
    }

    public BigDecimal getAvailablelimit() {
        return availablelimit;
    }

    public void setAvailablelimit(BigDecimal availablelimit) {
        this.availablelimit = availablelimit;
    }

    public Integer getLastday() {
        return lastday;
    }

    public void setLastday(Integer lastday) {
        this.lastday = lastday;
    }

    public Integer getBillday() {
        return billday;
    }

    public void setBillday(Integer billday) {
        this.billday = billday;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    public Short getMembersettingstate() {
        return membersettingstate;
    }

    public void setMembersettingstate(Short membersettingstate) {
        this.membersettingstate = membersettingstate;
    }

    public BigDecimal getBillmoney() {
        return billmoney;
    }

    public void setBillmoney(BigDecimal billmoney) {
        this.billmoney = billmoney;
    }

    public String getGradename() {
        return gradename;
    }

    public void setGradename(String gradename) {
        this.gradename = gradename;
    }

    public BigDecimal getEva1() {
        return eva1;
    }

    public void setEva1(BigDecimal eva1) {
        this.eva1 = eva1;
    }

    public BigDecimal getEva2() {
        return eva2;
    }

    public void setEva2(BigDecimal eva2) {
        this.eva2 = eva2;
    }

    public BigDecimal getEva3() {
        return eva3;
    }

    public void setEva3(BigDecimal eva3) {
        this.eva3 = eva3;
    }

    public Long getSellergroupid() {
        return sellergroupid;
    }

    public void setSellergroupid(Long sellergroupid) {
        this.sellergroupid = sellergroupid;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCitysmall() {
        return citysmall;
    }

    public void setCitysmall(String citysmall) {
        this.citysmall = citysmall;
    }

    public Short getCreditstate() {
        return creditstate;
    }

    public void setCreditstate(Short creditstate) {
        this.creditstate = creditstate;
    }
}