package project.jinshang.mod_activity.bean;

import io.swagger.annotations.ApiModelProperty;

public class LimitTimeSetting {

    @ApiModelProperty(value = "预热时间")
    private Integer preheattime;

    @ApiModelProperty(value = "预热阶段是否可以购买 0=不可以购买，1=可以购买")
    private Short canbuy;

    public Integer getPreheattime() {
        return preheattime;
    }

    public void setPreheattime(Integer preheattime) {
        this.preheattime = preheattime;
    }

    public Short getCanbuy() {
        return canbuy;
    }

    public void setCanbuy(Short canbuy) {
        this.canbuy = canbuy;
    }
}