package project.jinshang.mod_coupon.bean;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * null
 * 
 * @author zzy
 * 
 * @date 2018-05-26
 */
public class YhqRecord {
    @ApiModelProperty("ID自增")
    private Long id;

    @ApiModelProperty("优惠券ID")
    private Long ticketid;

    @ApiModelProperty("优惠券编码")
    private String ticketno;

    @ApiModelProperty("优惠券类型1：满减券2：直减券3：包邮券4：折扣券100：代金券")
    private Long tickettype;

    @ApiModelProperty("具体折扣金额")
    private BigDecimal discountmoney;

    @ApiModelProperty("换算具体折扣百分比")
    private BigDecimal discountpercent;

    @ApiModelProperty("用户")
    private Long memberid;

    @ApiModelProperty("订单ID")
    private Long ordersid;

    @ApiModelProperty("订单编码")
    private String ordersno;

    @ApiModelProperty("使用时间")
    private Date usetime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTicketid() {
        return ticketid;
    }

    public void setTicketid(Long ticketid) {
        this.ticketid = ticketid;
    }

    public String getTicketno() {
        return ticketno;
    }

    public void setTicketno(String ticketno) {
        this.ticketno = ticketno == null ? null : ticketno.trim();
    }

    public Long getTickettype() {
        return tickettype;
    }

    public void setTickettype(Long tickettype) {
        this.tickettype = tickettype;
    }

    public BigDecimal getDiscountmoney() {
        return discountmoney;
    }

    public void setDiscountmoney(BigDecimal discountmoney) {
        this.discountmoney = discountmoney;
    }

    public BigDecimal getDiscountpercent() {
        return discountpercent;
    }

    public void setDiscountpercent(BigDecimal discountpercent) {
        this.discountpercent = discountpercent;
    }

    public Long getMemberid() {
        return memberid;
    }

    public void setMemberid(Long memberid) {
        this.memberid = memberid;
    }

    public Long getOrdersid() {
        return ordersid;
    }

    public void setOrdersid(Long ordersid) {
        this.ordersid = ordersid;
    }

    public String getOrdersno() {
        return ordersno;
    }

    public void setOrdersno(String ordersno) {
        this.ordersno = ordersno == null ? null : ordersno.trim();
    }

    public Date getUsetime() {
        return usetime;
    }

    public void setUsetime(Date usetime) {
        this.usetime = usetime;
    }
}