package project.jinshang.mod_member.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Created by Administrator on 2018/5/10.
 */
@ApiModel(description = "会员活动")
public class MemberActivity {
    @ApiModelProperty(notes = "id")
    private long id;
    @ApiModelProperty(notes = "手机")
    private String mobile;
    @ApiModelProperty(notes = "参与时间")
    private Date jointime;
    @ApiModelProperty(notes = "会员id")
    private long memberid;
    @ApiModelProperty(notes = "用户名")
    private String username;
    @ApiModelProperty(notes = "公司名称")
    private String companyname;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Date getJointime() {
        return jointime;
    }

    public void setJointime(Date jointime) {
        this.jointime = jointime;
    }

    public long getMemberid() {
        return memberid;
    }

    public void setMemberid(long memberid) {
        this.memberid = memberid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }
}
