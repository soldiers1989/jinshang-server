package project.jinshang.mod_product.bean.dto;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * create : wyh
 * date : 2018/7/18
 */
public class SplitOrderQuery {

    @ApiModelProperty(notes = "购物车id")
    private Long shopCarId;
    @ApiModelProperty(notes = "订单商品id")
    private Long pdid;
    @ApiModelProperty(notes = "仓库id")
    private Long storeid;
    @ApiModelProperty(notes = "限时购订单商品仓库id")
    private Long limitStoreid;
    @ApiModelProperty(notes = "限时购商品id")
    private Long limitid;
    @ApiModelProperty(notes = "商品编码")
    private String pdno;
    @ApiModelProperty(notes = "商品数量")
    private BigDecimal pdnumber;
    @ApiModelProperty(notes = "商品单价")
    private BigDecimal price;
    @ApiModelProperty(notes = "发货时间")
    private String delivertime;
    @ApiModelProperty(notes = "计量单位")
    private String unit;
    @ApiModelProperty(notes = "远期类型0=不是远期1=全款2=定金")
    private Short protype;
    @ApiModelProperty(notes = "属性json")
    private String attrJson;




    public Long getShopCarId() {
        return shopCarId;
    }

    public void setShopCarId(Long shopCarId) {
        this.shopCarId = shopCarId;
    }

    public Long getPdid() {
        return pdid;
    }

    public void setPdid(Long pdid) {
        this.pdid = pdid;
    }

    public Long getLimitid() {
        return limitid;
    }

    public void setLimitid(Long limitid) {
        this.limitid = limitid;
    }

    public String getPdno() {
        return pdno;
    }

    public void setPdno(String pdno) {
        this.pdno = pdno;
    }

    public BigDecimal getPdnumber() {
        return pdnumber;
    }

    public void setPdnumber(BigDecimal pdnumber) {
        this.pdnumber = pdnumber;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDelivertime() {
        return delivertime;
    }

    public void setDelivertime(String delivertime) {
        this.delivertime = delivertime;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Short getProtype() {
        return protype;
    }

    public void setProtype(Short protype) {
        this.protype = protype;
    }

    public String getAttrJson() {
        return attrJson;
    }

    public void setAttrJson(String attrJson) {
        this.attrJson = attrJson;
    }

    public Long getStoreid() {
        return storeid;
    }

    public void setStoreid(Long storeid) {
        this.storeid = storeid;
    }

    public Long getLimitStoreid() {
        return limitStoreid;
    }

    public void setLimitStoreid(Long limitStoreid) {
        this.limitStoreid = limitStoreid;
    }
}
