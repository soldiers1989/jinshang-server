package project.jinshang.mod_admin.mod_count.bean;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class SearchKeyQueryParam {

    @ApiModelProperty(notes = "开始时间")
    private Date startTime;
    @ApiModelProperty(notes = "结束时间")
    private Date endTime;

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
