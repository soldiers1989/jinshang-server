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
public class YhqProject {
    @ApiModelProperty("ID自增")
    private Long id;

    @ApiModelProperty("编码")
    private String no;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("简介")
    private String about;

    @ApiModelProperty("发放方案")
    private String ticketlist;

    @ApiModelProperty("发放类型1：批量发放2：指定人发放")
    private Long type;

    @ApiModelProperty("发放规则")
    private String rule;

    @ApiModelProperty("状态1：待审核2：审核通过97：禁用98：审核不通过99：已执行")
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

    public String getTicketlist() {
        return ticketlist;
    }

    public void setTicketlist(String ticketlist) {
        this.ticketlist = ticketlist == null ? null : ticketlist.trim();
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule == null ? null : rule.trim();
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