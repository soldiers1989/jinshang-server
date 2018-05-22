package project.jinshang.mod_product.bean;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class Brand {
    private Long id;

    @ApiModelProperty(notes = "品牌名称")
    private String name;

    @ApiModelProperty(notes = "品牌分类")
    private String category;

    @ApiModelProperty(notes = "分类id")
    private Long categoryid;

    @ApiModelProperty(notes = "品牌图片")
    private String pic;

    @ApiModelProperty(notes = "提交人id")
    private Long memberid;

    @ApiModelProperty(notes = "审核人id")
    private Long auditname;

    @ApiModelProperty(notes = "审核时间")
    private Date audittime;

    @ApiModelProperty(notes = "不通过原因")
    private String reason;

    @ApiModelProperty(notes = "审核状态")
    private Short auditstate;

    @ApiModelProperty(notes = "ceatetime")
    private Date ceatetime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    public Long getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(Long categoryid) {
        this.categoryid = categoryid;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic == null ? null : pic.trim();
    }

    public Long getMemberid() {
        return memberid;
    }

    public void setMemberid(Long memberid) {
        this.memberid = memberid;
    }

    public Long getAuditname() {
        return auditname;
    }

    public void setAuditname(Long auditname) {
        this.auditname = auditname;
    }

    public Date getAudittime() {
        return audittime;
    }

    public void setAudittime(Date audittime) {
        this.audittime = audittime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public Short getAuditstate() {
        return auditstate;
    }

    public void setAuditstate(Short auditstate) {
        this.auditstate = auditstate;
    }

    public Date getCeatetime() {
        return ceatetime;
    }

    public void setCeatetime(Date ceatetime) {
        this.ceatetime = ceatetime;
    }
}