package project.jinshang.mod_product.bean;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 
 * @author zzy
 * 
 * @date 2018-08-16
 */
public class LogisticsInfo {
    private Long id;

    @ApiModelProperty(notes = "订单id")
    private Long orderid;

    @ApiModelProperty(notes = "订单单号")
    private String orderno;

    @ApiModelProperty(notes = "快递单号")
    private String couriernumber;

    @ApiModelProperty(notes = "物流公司")
    private String logisticscompany;

    @ApiModelProperty(notes = "发货时间")
    private Date time;

    @ApiModelProperty(notes = "发货号")
    private String deliveryno;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderid() {
        return orderid;
    }

    public void setOrderid(Long orderid) {
        this.orderid = orderid;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno == null ? null : orderno.trim();
    }

    public String getCouriernumber() {
        return couriernumber;
    }

    public void setCouriernumber(String couriernumber) {
        this.couriernumber = couriernumber == null ? null : couriernumber.trim();
    }

    public String getLogisticscompany() {
        return logisticscompany;
    }

    public void setLogisticscompany(String logisticscompany) {
        this.logisticscompany = logisticscompany == null ? null : logisticscompany.trim();
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getDeliveryno() {
        return deliveryno;
    }

    public void setDeliveryno(String deliveryno) {
        this.deliveryno = deliveryno == null ? null : deliveryno.trim();
    }
}