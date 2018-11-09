package project.jinshang.mod_coupon.bean;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * null
 * 
 * @author zzy
 * 
 * @date 2018-05-26
 */
public class YhqTicketset {

    @ApiModelProperty("ID自增")
    private Long id;

    @ApiModelProperty("编码")
    private String no;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("优惠券类型1：满减券2：直减券3：包邮券4：折扣券100：代金券")
    private Long type;

    @ApiModelProperty("发放类型1：提前生成2：领用时生成")
    private Long createtype;

    @ApiModelProperty("方案ID")
    private Long planid;

    @ApiModelProperty("简介")
    private String about;

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

    @ApiModelProperty("限定发放数量")
    private Long ticketcount;

    @ApiModelProperty("剩余数量")
    private Long surpluscount;

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

    @ApiModelProperty("状态1：待审核97：禁用98：审核不通过99：审核通过")
    private Long status;

    @ApiModelProperty("审核人ID")
    private Long auditingid;

    @ApiModelProperty("审核意见")
    private String auditingstr;

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

    public Long getCreatetype() {
        return createtype;
    }

    public void setCreatetype(Long createtype) {
        this.createtype = createtype;
    }

    public Long getPlanid() {
        return planid;
    }

    public void setPlanid(Long planid) {
        this.planid = planid;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about == null ? null : about.trim();
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

    public Long getTicketcount() {
        return ticketcount;
    }

    public void setTicketcount(Long ticketcount) {
        this.ticketcount = ticketcount;
    }

    public Long getSurpluscount() {
        return surpluscount;
    }

    public void setSurpluscount(Long surpluscount) {
        this.surpluscount = surpluscount;
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

    public Long getAuditingid() {
        return auditingid;
    }

    public void setAuditingid(Long auditingid) {
        this.auditingid = auditingid;
    }

    public String getAuditingstr() {
        return auditingstr;
    }

    public void setAuditingstr(String auditingstr) {
        this.auditingstr = auditingstr == null ? null : auditingstr.trim();
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