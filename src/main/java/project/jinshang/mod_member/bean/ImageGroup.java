package project.jinshang.mod_member.bean;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 
 * @author zzy
 * 
 * @date 2018-09-10
 */
public class ImageGroup {
    private Long id;

    @ApiModelProperty(notes = "用户id 卖家和后台管理")
    private Long memberid;

    @ApiModelProperty(notes = "分组名")
    private String groupname;

    @ApiModelProperty(notes = "创建时间")
    private Date createtime;

    @ApiModelProperty(notes = "修改时间")
    private Date updatetime;

    @ApiModelProperty(notes = "分组状态 1=正常 2=已删除")
    private Short status;

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

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname == null ? null : groupname.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }
}