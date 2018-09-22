package project.jinshang.mod_product.bean.dto;

import io.swagger.annotations.ApiModelProperty;
import project.jinshang.mod_product.bean.Orders;

import java.util.Date;

public class OrdersLogisticsInfoDto extends Orders {
    /**
     * 这里写1 主要是为了兼容orders字段和这里名称一样
     */
    private Long id1;

    @ApiModelProperty(notes = "订单id")
    private Long orderid1;

    @ApiModelProperty(notes = "订单单号")
    private String orderno1;

    @ApiModelProperty(notes = "快递单号")
    private String couriernumber1;

    @ApiModelProperty(notes = "物流公司")
    private String logisticscompany1;

    @ApiModelProperty(notes = "发货时间")
    private Date time;

    @ApiModelProperty(notes = "发货号")
    private String deliveryno1;

    public Long getId1() {
        return id1;
    }

    public void setId1(Long id1) {
        this.id1 = id1;
    }

    public Long getOrderid1() {
        return orderid1;
    }

    public void setOrderid1(Long orderid1) {
        this.orderid1 = orderid1;
    }

    public String getOrderno1() {
        return orderno1;
    }

    public void setOrderno1(String orderno1) {
        this.orderno1 = orderno1;
    }

    public String getCouriernumber1() {
        return couriernumber1;
    }

    public void setCouriernumber1(String couriernumber1) {
        this.couriernumber1 = couriernumber1;
    }

    public String getLogisticscompany1() {
        return logisticscompany1;
    }

    public void setLogisticscompany1(String logisticscompany1) {
        this.logisticscompany1 = logisticscompany1;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getDeliveryno1() {
        return deliveryno1;
    }

    public void setDeliveryno1(String deliveryno1) {
        this.deliveryno1 = deliveryno1;
    }
}
