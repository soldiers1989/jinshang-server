package project.jinshang.mod_member.bean.dto;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 
 * @author zzy
 * 
 * @date 2018-07-13
 */
public class ReturnVisitDto {

    @ApiModelProperty(notes = "id自增")
    private Long id;

    @ApiModelProperty(notes = "回访会员")
    private Long memberid;

    @ApiModelProperty(notes = "回访类型")
    private String type;

    @ApiModelProperty(notes = "回访时间")
    private Date returntime;

    @ApiModelProperty(notes = "回访结果")
    private String result;

    @ApiModelProperty(notes = "回访详细内容")
    private String content;

    @ApiModelProperty(notes = "回访的管理员")
    private Long adminid;

    @ApiModelProperty(notes = "记录添加时间")
    private Date createtime;

    @ApiModelProperty(notes = "手机号")
    private String mobile;

    @ApiModelProperty(notes = "标签")
    private String labelname;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Date getReturntime() {
        return returntime;
    }

    public void setReturntime(Date returntime) {
        this.returntime = returntime;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result == null ? null : result.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Long getAdminid() {
        return adminid;
    }

    public void setAdminid(Long adminid) {
        this.adminid = adminid;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getLabelname() {
        return labelname;
    }

    public void setLabelname(String labelname) {
        this.labelname = labelname;
    }
}