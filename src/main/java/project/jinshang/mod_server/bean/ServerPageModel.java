package project.jinshang.mod_server.bean;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ServerPageModel implements Serializable {


    @ApiModelProperty(notes = "编号")
    private Long id;

    @ApiModelProperty(notes = "真实姓名")
    private String realname;

    @ApiModelProperty(notes = "会员全称")
    private String companyname;

    @ApiModelProperty(notes = "会员等级")
    private String gradename;

    @ApiModelProperty(notes = "会员类别")
    private boolean company;

    @ApiModelProperty(notes = "手机")
    private String mobile;

    @ApiModelProperty(notes = "邮箱")
    private String email;

    @ApiModelProperty(notes = "注册时间")
    private Date createdate;

    @ApiModelProperty(notes = "状态")
    private boolean disabled;

    @ApiModelProperty(notes = "注册名")
    private String username;

    @ApiModelProperty(notes = "服务费设置列表")
    private List<ServerSet> list;

    public List<ServerSet> getList() {
        return list;
    }

    public void setList(List<ServerSet> list) {
        this.list = list;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getGradename() {
        return gradename;
    }

    public void setGradename(String gradename) {
        this.gradename = gradename;
    }

    public boolean isCompany() {
        return company;
    }

    public void setCompany(boolean company) {
        this.company = company;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }
}
