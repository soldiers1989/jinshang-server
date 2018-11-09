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
public class YhqTicketpacks {

    @ApiModelProperty("ID自增")
    private Long id;

    @ApiModelProperty("编码")
    private String no;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("简介")
    private String about;

    @ApiModelProperty("发放标识")
    private String tag;

    @ApiModelProperty("发放方案")
    private String ticketlist;

    @ApiModelProperty("限定发放数量")
    private Long packscount;

    @ApiModelProperty("剩余数量")
    private Long surpluscount;

    @ApiModelProperty("发放开始时间")
    private Date starttime;

    @ApiModelProperty("发放截止时间")
    private Date endtime;

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

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about == null ? null : about.trim();
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag == null ? null : tag.trim();
    }

    public String getTicketlist() {
        return ticketlist;
    }

    public void setTicketlist(String ticketlist) {
        this.ticketlist = ticketlist == null ? null : ticketlist.trim();
    }

    public Long getPackscount() {
        return packscount;
    }

    public void setPackscount(Long packscount) {
        this.packscount = packscount;
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