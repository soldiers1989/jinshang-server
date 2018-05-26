package project.jinshang.mod_product.bean;


import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/5/24.
 */
public class OrderProductBackInfo {
    private Long id;
    @ApiModelProperty(notes = "订单编号")
    private String orderno;
    @ApiModelProperty(notes = "商品id")
    private Long pdid;
    @ApiModelProperty(notes = "退货单号")
    private String backno;
    @ApiModelProperty(notes = "退货量")
    private BigDecimal backnum;
    @ApiModelProperty(notes = "退货方式 0=买家发起 1=商家编辑 2=平台编辑")
    private Short backtype;
    @ApiModelProperty(notes = "退货状态 0=退货完成 1=退货失败 2=退货中")
    private Short backstate;
    @ApiModelProperty(notes = "退货时间")
    private Date backtime;

    private List<OrderProduct> orderProducts;

    @Transient
    private Map<String,Object> extend;

    private OrderProduct orderProduct;

    public OrderProduct getOrderProduct() {
        return orderProduct;
    }

    public void setOrderProduct(OrderProduct orderProduct) {
        this.orderProduct = orderProduct;
    }

    public Map<String, Object> getExtend() {
        if(extend==null) extend=new HashMap<>();
        return extend;
    }

    public OrderProductBackInfo setExtend(Map<String, Object> extend) {
        this.extend = extend;
        return this;
    }

    public List<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(List<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
    }

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
        this.orderno = orderno;
    }

    public Long getPdid() {
        return pdid;
    }

    public void setPdid(Long pdid) {
        this.pdid = pdid;
    }

    public String getBackno() {
        return backno;
    }

    public void setBackno(String backno) {
        this.backno = backno;
    }

    public BigDecimal getBacknum() {
        return backnum;
    }

    public void setBacknum(BigDecimal backnum) {
        this.backnum = backnum;
    }

    public Short getBacktype() {
        return backtype;
    }

    public void setBacktype(Short backtype) {
        this.backtype = backtype;
    }

    public Short getBackstate() {
        return backstate;
    }

    public void setBackstate(Short backstate) {
        this.backstate = backstate;
    }

    public Date getBacktime() {
        return backtime;
    }

    public void setBacktime(Date backtime) {
        this.backtime = backtime;
    }
}
