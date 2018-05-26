package project.jinshang.mod_member.bean.dto;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 对接记录的展示Dto
 *
 * @author xiazy
 * @create 2018-05-25 11:35
 **/
public class ApiRecordViewDto {
    @ApiModelProperty(notes = "主键ID")
    private Long id;
    @ApiModelProperty(notes = "对接ID")
    private String appId;
    @ApiModelProperty(notes = "对接地址")
    private String appUrl;
    @ApiModelProperty(notes = "调用接口")
    private String apiCode;
    @ApiModelProperty(notes = "传值")
    private String content;
    @ApiModelProperty(notes = "返回值")
    private String returnJson;
    @ApiModelProperty(notes = "对接时间")
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }

    public String getApiCode() {
        return apiCode;
    }

    public void setApiCode(String apiCode) {
        this.apiCode = apiCode;
    }

    public String getReturnJson() {
        return returnJson;
    }

    public void setReturnJson(String returnJson) {
        this.returnJson = returnJson;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
