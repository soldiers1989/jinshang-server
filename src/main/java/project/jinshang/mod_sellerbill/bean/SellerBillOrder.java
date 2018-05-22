package project.jinshang.mod_sellerbill.bean;

import java.math.BigDecimal;
import java.util.Date;

public class SellerBillOrder {
    private Long id;

    private Long sellerbillid;

    private Long orderid;

    private String orderno;

    private BigDecimal ordermoney;

    private String buyername;

    private Date fishtime;

    private BigDecimal breakpaymoney;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSellerbillid() {
        return sellerbillid;
    }

    public void setSellerbillid(Long sellerbillid) {
        this.sellerbillid = sellerbillid;
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

    public BigDecimal getOrdermoney() {
        return ordermoney;
    }

    public void setOrdermoney(BigDecimal ordermoney) {
        this.ordermoney = ordermoney;
    }

    public String getBuyername() {
        return buyername;
    }

    public void setBuyername(String buyername) {
        this.buyername = buyername == null ? null : buyername.trim();
    }

    public Date getFishtime() {
        return fishtime;
    }

    public void setFishtime(Date fishtime) {
        this.fishtime = fishtime;
    }

    public BigDecimal getBreakpaymoney() {
        return breakpaymoney;
    }

    public void setBreakpaymoney(BigDecimal breakpaymoney) {
        this.breakpaymoney = breakpaymoney;
    }
}