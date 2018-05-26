package project.jinshang.mod_member.bean.dto;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 对接记录表的查询Dto
 *
 * @author xiazy
 * @create 2018-05-25 11:30
 **/
public class ApiRecordQueryDto {

    @ApiModelProperty(notes = "对接ID")
    public String appId;
    @ApiModelProperty(notes = "对接接口")
    public String apiCode;
    @ApiModelProperty(notes = "对接查询开始时间")
    public Date startCreateTime;
    @ApiModelProperty(notes = "对接查询结束时间")
    public Date endCreateTime;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getApiCode() {
        return apiCode;
    }

    public void setApiCode(String apiCode) {
        this.apiCode = apiCode;
    }
    public Date getStartCreateTime() {
        return startCreateTime;
    }

    public void setStartCreateTime(Date startCreateTime) {
        this.startCreateTime = startCreateTime;
    }

    public Date getEndCreateTime() {
        return endCreateTime;
    }

    public void setEndCreateTime(Date endCreateTime) {
        this.endCreateTime = endCreateTime;
    }
}
