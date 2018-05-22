package project.jinshang.mod_admin.mod_top.bean;

import project.jinshang.mod_product.bean.ProductInfo;

import java.math.BigDecimal;
import java.util.Date;

public class ProductModel extends ProductInfo{

    private Long tapid;
    private Long limitid;
    private BigDecimal prodprice;
    private BigDecimal limitprice;

    private BigDecimal normalprice;

    private String shopname;

    private Date begintime;

    private Date endtime;

    private String value;

    private Short state;

    private Long pdid;


    private  String pdno;

    public String getPdno() {
        return pdno;
    }

    public void setPdno(String pdno) {
        this.pdno = pdno;
    }

    public Long getPdid() {
        return pdid;
    }

    public void setPdid(Long pdid) {
        this.pdid = pdid;
    }

    public Short getState() {
        return state;
    }

    public void setState(Short state) {
        this.state = state;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getTapid() {
        return tapid;
    }

    public void setTapid(Long tapid) {
        this.tapid = tapid;
    }


    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public BigDecimal getNormalprice() {
        return normalprice;
    }

    public void setNormalprice(BigDecimal normalprice) {
        this.normalprice = normalprice;
    }

    public Long getLimitid() {
        return limitid;
    }

    public void setLimitid(Long limitid) {
        this.limitid = limitid;
    }

    public BigDecimal getProdprice() {
        return prodprice;
    }

    public void setProdprice(BigDecimal prodprice) {
        this.prodprice = prodprice;
    }

    public BigDecimal getLimitprice() {
        return limitprice;
    }

    public void setLimitprice(BigDecimal limitprice) {
        this.limitprice = limitprice;
    }

    public Date getBegintime() {
        return begintime;
    }

    public void setBegintime(Date begintime) {
        this.begintime = begintime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }
}
