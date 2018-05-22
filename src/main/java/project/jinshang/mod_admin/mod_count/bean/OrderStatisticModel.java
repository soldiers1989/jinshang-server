package project.jinshang.mod_admin.mod_count.bean;

import io.swagger.annotations.ApiModelProperty;

public class OrderStatisticModel {

    @ApiModelProperty(notes = "时间")
    private String ordertime;
    @ApiModelProperty(notes = "数量")
    private String ordernum;

    public String getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(String ordertime) {
        this.ordertime = ordertime;
    }

    public String getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(String ordernum) {
        this.ordernum = ordernum;
    }
}
