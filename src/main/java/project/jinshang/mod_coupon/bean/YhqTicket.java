package project.jinshang.mod_coupon.bean;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * null
 *
 * @author zzy
 *
 * @date 2018-05-26
 */
public class YhqTicket {

    @ApiModelProperty("ID自增")
    private Long id;

    @ApiModelProperty("编码")
    private String no;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("优惠券类型1：满减券2：直减券3：包邮券4：折扣券100：代金券")
    private Long type;

    @ApiModelProperty("方案ID")
    private Long planid;

    @ApiModelProperty("优惠券配置ID")
    private Long ticketsetid;

    @ApiModelProperty(" 优惠券分发方案")
    private Long projectid;

    @ApiModelProperty("简介")
    private String about;

    @ApiModelProperty("代金券金额")
    private BigDecimal capital;

    @ApiModelProperty("可用余额")
    private BigDecimal available;

    @ApiModelProperty("优惠券规则")
    private String rule;

    @ApiModelProperty("适用分类列表")
    private String categorieslist;

    @ApiModelProperty("适用仓库列表")
    private String storelist;

    @ApiModelProperty("适用商品列表")
    private String productstorelist;

    @ApiModelProperty("适用买家等级列表")
    private String membergradelist;

    @ApiModelProperty("发放开始时间")
    private Date starttime;

    @ApiModelProperty("发放截止时间")
    private Date endtime;

    @ApiModelProperty("有效规则")
    private String validityrule;

    @ApiModelProperty("有效开始时间")
    private Date validitystarttime;

    @ApiModelProperty("有效截止时间")
    private Date validityendtime;

    @ApiModelProperty("状态1：待领取2：已领取97：禁用98：已失效99：已使用")
    private Long status;

    @ApiModelProperty("领取用户")
    private Long memberid;

    @ApiModelProperty("领取时间")
    private Date gettime;

    @ApiModelProperty("使用时间")
    private Date usertime;

    @ApiModelProperty("添加人")
    private Long usersid;

    @ApiModelProperty("添加时间")
    private Date createtime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no == null ? null : no.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public Long getPlanid() {
        return planid;
    }

    public void setPlanid(Long planid) {
        this.planid = planid;
    }

    public Long getTicketsetid() {
        return ticketsetid;
    }

    public void setTicketsetid(Long ticketsetid) {
        this.ticketsetid = ticketsetid;
    }

    public Long getProjectid() {
        return projectid;
    }

    public void setProjectid(Long projectid) {
        this.projectid = projectid;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about == null ? null : about.trim();
    }

    public BigDecimal getCapital() {
        return capital;
    }

    public void setCapital(BigDecimal capital) {
        this.capital = capital;
    }

    public BigDecimal getAvailable() {
        return available;
    }

    public void setAvailable(BigDecimal available) {
        this.available = available;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule == null ? null : rule.trim();
    }

    public String getCategorieslist() {
        return categorieslist;
    }

    public void setCategorieslist(String categorieslist) {
        this.categorieslist = categorieslist == null ? null : categorieslist.trim();
    }

    public String getStorelist() {
        return storelist;
    }

    public void setStorelist(String storelist) {
        this.storelist = storelist == null ? null : storelist.trim();
    }

    public String getProductstorelist() {
        return productstorelist;
    }

    public void setProductstorelist(String productstorelist) {
        this.productstorelist = productstorelist == null ? null : productstorelist.trim();
    }

    public String getMembergradelist() {
        return membergradelist;
    }

    public void setMembergradelist(String membergradelist) {
        this.membergradelist = membergradelist == null ? null : membergradelist.trim();
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

    public String getValidityrule() {
        return validityrule;
    }

    public void setValidityrule(String validityrule) {
        this.validityrule = validityrule == null ? null : validityrule.trim();
    }

    public Date getValiditystarttime() {
        return validitystarttime;
    }

    public void setValiditystarttime(Date validitystarttime) {
        this.validitystarttime = validitystarttime;
    }

    public Date getValidityendtime() {
        return validityendtime;
    }

    public void setValidityendtime(Date validityendtime) {
        this.validityendtime = validityendtime;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getMemberid() {
        return memberid;
    }

    public void setMemberid(Long memberid) {
        this.memberid = memberid;
    }

    public Date getGettime() {
        return gettime;
    }

    public void setGettime(Date gettime) {
        this.gettime = gettime;
    }

    public Date getUsertime() {
        return usertime;
    }

    public void setUsertime(Date usertime) {
        this.usertime = usertime;
    }

    public Long getUsersid() {
        return usersid;
    }

    public void setUsersid(Long usersid) {
        this.usersid = usersid;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}