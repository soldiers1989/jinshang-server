package project.jinshang.mod_admin.mod_app.bean;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 
 * @author zzy
 * 
 * @date 2018-08-07
 */
public class AppVersion {
    private Long id;

    @ApiModelProperty(notes = "app类型 ios android")
    private String apptype;

    @ApiModelProperty(notes = "app版本号")
    private String version;

    @ApiModelProperty(notes = "app版本名")
    private String versionname;

    @ApiModelProperty(notes = "0 普通升级 1 强制升级")
    private Short type;

    @ApiModelProperty(notes = "外网app下载地址")
    private String url;

    @ApiModelProperty(notes = "自己服务器app下载地址")
    private String downloadurl;

    @ApiModelProperty(notes = "app渠道例如 应用宝 华为市场")
    private String channel;

    @ApiModelProperty(notes = "创建时间")
    private Date createtime;

    @ApiModelProperty(notes = "修改时间")
    private Date updatetime;

    @ApiModelProperty(notes = "后台管理员的id")
    private Long adminid;

    @ApiModelProperty(notes = "客服电话")
    private String mobile;

    @ApiModelProperty(notes = "备注")
    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApptype() {
        return apptype;
    }

    public void setApptype(String apptype) {
        this.apptype = apptype == null ? null : apptype.trim();
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
    }

    public String getVersionname() {
        return versionname;
    }

    public void setVersionname(String versionname) {
        this.versionname = versionname == null ? null : versionname.trim();
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getDownloadurl() {
        return downloadurl;
    }

    public void setDownloadurl(String downloadurl) {
        this.downloadurl = downloadurl == null ? null : downloadurl.trim();
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel == null ? null : channel.trim();
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

    public Long getAdminid() {
        return adminid;
    }

    public void setAdminid(Long adminid) {
        this.adminid = adminid;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}