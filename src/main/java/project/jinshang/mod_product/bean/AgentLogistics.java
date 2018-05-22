package project.jinshang.mod_product.bean;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 代理发货物流表
 */
public class AgentLogistics {
    private Long id;

    @ApiModelProperty(notes = "订单编号")
   private String orderno;

    @ApiModelProperty(notes = "物流公司")
    private String logisticscompany;

    @ApiModelProperty(notes = "运输单号")
    private String couriernumber;

    private Date createdate;

    @ApiModelProperty(notes = "类型 0-商家发货到平台  1-平台发货到商家（退货）")
    private Short type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno == null ? null : orderno.trim();
    }

    public String getLogisticscompany() {
        return logisticscompany;
    }

    public void setLogisticscompany(String logisticscompany) {
        this.logisticscompany = logisticscompany == null ? null : logisticscompany.trim();
    }

    public String getCouriernumber() {
        return couriernumber;
    }

    public void setCouriernumber(String couriernumber) {
        this.couriernumber = couriernumber == null ? null : couriernumber.trim();
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }
}