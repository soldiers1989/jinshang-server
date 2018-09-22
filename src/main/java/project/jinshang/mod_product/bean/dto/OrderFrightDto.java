package project.jinshang.mod_product.bean.dto;

import java.math.BigDecimal;

/**
 * create : wyh
 * date : 2018/7/13
 */
public class OrderFrightDto{
    //默认运费公斤
    BigDecimal defaultfreight;
    //默认运费
    BigDecimal defaultcost;
    //每增加公斤
    BigDecimal perkilogramadded;
    //每增加运费
    BigDecimal perkilogramcost;
    //满免金额
    BigDecimal defaultfreeprice;

    private String province;

    private String city;

    //计价方式 1=按重量，2=按金额
    private  Short counttype;



    public BigDecimal getDefaultcost() {
        return defaultcost;
    }

    public void setDefaultcost(BigDecimal defaultcost) {
        this.defaultcost = defaultcost;
    }

    public BigDecimal getPerkilogramadded() {
        return perkilogramadded;
    }

    public void setPerkilogramadded(BigDecimal perkilogramadded) {
        this.perkilogramadded = perkilogramadded;
    }

    public BigDecimal getPerkilogramcost() {
        return perkilogramcost;
    }

    public void setPerkilogramcost(BigDecimal perkilogramcost) {
        this.perkilogramcost = perkilogramcost;
    }

    public BigDecimal getDefaultfreeprice() {
        return defaultfreeprice;
    }

    public void setDefaultfreeprice(BigDecimal defaultfreeprice) {
        this.defaultfreeprice = defaultfreeprice;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Short getCounttype() {
        return counttype;
    }

    public void setCounttype(Short counttype) {
        this.counttype = counttype;
    }

    public BigDecimal getDefaultfreight() {

        return defaultfreight;
    }

    public void setDefaultfreight(BigDecimal defaultfreight) {
        this.defaultfreight = defaultfreight;
    }
}
