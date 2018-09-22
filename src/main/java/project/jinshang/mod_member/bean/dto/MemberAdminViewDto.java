package project.jinshang.mod_member.bean.dto;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * create : wyh
 * date : 2017/12/9
 */
public class MemberAdminViewDto implements Serializable {

    @ApiModelProperty(notes = "编号")
    private Long id;

    @ApiModelProperty(notes = "用户名")
    private String username;


    @ApiModelProperty(notes = "真实姓名")
    private String realname;


    @ApiModelProperty(notes = "会员等级")
    private String gradename;


    @ApiModelProperty(notes = "注册时间")
    private Date createdate;


    @ApiModelProperty(notes = "状态")
    private Boolean disabled;


    @ApiModelProperty(notes = "最后登陆时间")
    private Date lastlogindate;

    @ApiModelProperty(notes = "客服人员")
    private  String clerkname;

    @ApiModelProperty(notes="业务员")
    private String waysalesman;

    @ApiModelProperty(notes = "标签id")
    private  String labelid;

    @ApiModelProperty(notes = "标签名称")
    private  String labelname;

    @ApiModelProperty(notes = "公司名称")
    private  String companyname;


    private  String[] labelidArr;

    @ApiModelProperty(notes = "联系手机")
    private  String mobile;
    @ApiModelProperty(notes = "管理下單")
    private  Long manageState;

    @ApiModelProperty(notes = "注册来源标识")
    private String registersourcelabel;

    @ApiModelProperty(notes = "注册类型标识")
    private String registertypelabel;

    @ApiModelProperty(notes = "注册渠道标识")
    private String registerchannellabel;

    private  String[] labelnameArr;

    @ApiModelProperty(notes = "flag为1时候 当搜索“未分配”时结果为空，则改为搜索“已分配”")
    private Long flag;

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

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }


    public String getGradename() {
        return gradename;
    }

    public void setGradename(String gradename) {
        this.gradename = gradename;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public Date getLastlogindate() {
        return lastlogindate;
    }

    public void setLastlogindate(Date lastlogindate) {
        this.lastlogindate = lastlogindate;
    }

    public String getClerkname() {
        return clerkname;
    }

    public void setClerkname(String clerkname) {
        this.clerkname = clerkname;
    }

    public String getWaysalesman() {
        return waysalesman;
    }

    public void setWaysalesman(String waysalesman) {
        this.waysalesman = waysalesman;
    }

    public String getLabelid() {
        return labelid;
    }

    public void setLabelid(String labelid) {
        this.labelid = labelid;
    }

    public String[] getLabelidArr() {
        return labelidArr;
    }

    public void setLabelidArr(String[] labelidArr) {
        this.labelidArr = labelidArr;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public Long getManageState() {
        return manageState;
    }

    public void setManageState(Long manageState) {
        this.manageState = manageState;
    }


    public String getRegistersourcelabel() {
        return registersourcelabel;
    }

    public void setRegistersourcelabel(String registersourcelabel) {
        this.registersourcelabel = registersourcelabel;
    }

    public String getRegistertypelabel() {
        return registertypelabel;
    }

    public void setRegistertypelabel(String registertypelabel) {
        this.registertypelabel = registertypelabel;
    }

    public String getRegisterchannellabel() {
        return registerchannellabel;
    }

    public void setRegisterchannellabel(String registerchannellabel) {
        this.registerchannellabel = registerchannellabel;
    }

    public String getLabelname() {
        return labelname;
    }

    public void setLabelname(String labelname) {
        this.labelname = labelname;
    }

    public String[] getLabelnameArr() {
        return labelnameArr;
    }

    public void setLabelnameArr(String[] labelnameArr) {
        this.labelnameArr = labelnameArr;
    }

    public Long getFlag() {
        return flag;
    }

    public void setFlag(Long flag) {
        this.flag = flag;
    }
}
