package project.jinshang.mod_member.bean.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import project.jinshang.mod_member.bean.Member;

import java.io.Serializable;
import java.util.Date;

/**
 * create : zzy
 * date : 2018/06/13
 */

public class MemberDto  implements Serializable{
    @ApiModelProperty(notes = "会员编号")
    private Long id;

    @ApiModelProperty(notes = "真实姓名")
    private String realname;

    @ApiModelProperty(notes = "用户名")
    private String username;

    @ApiModelProperty(notes = "手机号")
    private String mobile;

    @ApiModelProperty(notes = "业务员")
    private String clerkname;

    @ApiModelProperty(notes = "注册时间-开始")
    private Date registDateStart;

    @ApiModelProperty(notes = "注册时间-结束")
    private  Date registDateEnd;

    @ApiModelProperty(notes = "会员标签id")
    private Long labelid;

    @ApiModelProperty(notes = "会员等级id")
    private Long gradleid;

    @ApiModelProperty(notes = "会员等级")
    private String gradename;

    @ApiModelProperty(notes = "最后登录时间-开始")
    private Date loginDateStart;

    @ApiModelProperty(notes = "最后登录时间-结束")
    private  Date loginDateEnd;

    @ApiModelProperty(notes = "是否是公司 0-全部，1-是，2-否")
    private  short companyType;

    @ApiModelProperty(notes = "会员状态 是否停用")
    private  Boolean disabled;

    @ApiModelProperty(notes = "有没消费")
    private Short isbuy;

    @ApiModelProperty(notes = "一级邀请人数")
    private Long firstinviternum;

    @ApiModelProperty(notes = "二级邀请人数")
    private Long secondinviternum;

    @ApiModelProperty(notes = "三级分销 邀请人id")
    private Long inviterid;

    @ApiModelProperty(notes = "邀请码")
    private String invitecode;



    public Short getIsbuy() {
        return isbuy;
    }

    public void setIsbuy(Short isbuy) {
        this.isbuy = isbuy;
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
        this.username = username;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getClerkname() {
        return clerkname;
    }

    public void setClerkname(String clerkname) {
        this.clerkname = clerkname;
    }

    public Date getRegistDateStart() {
        return registDateStart;
    }

    public void setRegistDateStart(Date registDateStart) {
        this.registDateStart = registDateStart;
    }

    public Date getRegistDateEnd() {
        return registDateEnd;
    }

    public void setRegistDateEnd(Date registDateEnd) {
        this.registDateEnd = registDateEnd;
    }

    public Long getLabelid() {
        return labelid;
    }

    public void setLabelid(Long labelid) {
        this.labelid = labelid;
    }

    public Long getGradleid() {
        return gradleid;
    }

    public void setGradleid(Long gradleid) {
        this.gradleid = gradleid;
    }

    public Date getLoginDateStart() {
        return loginDateStart;
    }

    public void setLoginDateStart(Date loginDateStart) {
        this.loginDateStart = loginDateStart;
    }

    public Date getLoginDateEnd() {
        return loginDateEnd;
    }

    public void setLoginDateEnd(Date loginDateEnd) {
        this.loginDateEnd = loginDateEnd;
    }

    public short getCompanyType() {
        return companyType;
    }

    public void setCompanyType(short companyType) {
        this.companyType = companyType;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public Long getFirstinviternum() {
        return firstinviternum;
    }

    public void setFirstinviternum(Long firstinviternum) {
        this.firstinviternum = firstinviternum;
    }

    public Long getSecondinviternum() {
        return secondinviternum;
    }

    public void setSecondinviternum(Long secondinviternum) {
        this.secondinviternum = secondinviternum;
    }

    public String getGradename() {
        return gradename;
    }

    public void setGradename(String gradename) {
        this.gradename = gradename;
    }

    public Long getInviterid() {
        return inviterid;
    }

    public void setInviterid(Long inviterid) {
        this.inviterid = inviterid;
    }

    public String getInvitecode() {
        return invitecode;
    }

    public void setInvitecode(String invitecode) {
        this.invitecode = invitecode;
    }
}
