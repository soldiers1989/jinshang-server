package project.jinshang.mod_product.bean;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * 分单 母单
 */
public class OrderMain {

    public static final int TYPE_NORMAL = 1;
    public static final int TYPE_SHOPGROUP = 2;

    private long id ;
    @ApiModelProperty(notes = "订单总金额")
    private BigDecimal ordermoney;
    @ApiModelProperty(notes = "运费")
    private BigDecimal freight;
    @ApiModelProperty(notes = "优惠券id")
    private Integer couponid;
    @ApiModelProperty(notes = "优惠金额")
    private BigDecimal couponmoney;
    @ApiModelProperty(notes = "1-普通，2-商家合单")
    private int type;
    private Timestamp createDt;

    private List<Orders> orders = new ArrayList<>();

    public List<Orders> getOrders() {
        return orders;
    }

    public OrderMain setOrders(List<Orders> orders) {
        this.orders = orders;
        return this;
    }

    public long getId() {
        return id;
    }

    public OrderMain setId(long id) {
        this.id = id;
        return this;
    }

    public BigDecimal getOrdermoney() {
        return ordermoney;
    }

    public OrderMain setOrdermoney(BigDecimal ordermoney) {
        this.ordermoney = ordermoney;
        return this;
    }

    public BigDecimal getFreight() {
        return freight;
    }

    public OrderMain setFreight(BigDecimal freight) {
        this.freight = freight;
        return this;
    }

    public Integer getCouponid() {
        return couponid;
    }

    public OrderMain setCouponid(Integer couponid) {
        this.couponid = couponid;
        return this;
    }

    public BigDecimal getCouponmoney() {
        return couponmoney;
    }

    public OrderMain setCouponmoney(BigDecimal couponmoney) {
        this.couponmoney = couponmoney;
        return this;
    }

    public int getType() {
        return type;
    }

    public OrderMain setType(int type) {
        this.type = type;
        return this;
    }

    public Timestamp getCreateDt() {
        return createDt;
    }

    public OrderMain setCreateDt(Timestamp createDt) {
        this.createDt = createDt;
        return this;
    }
}
