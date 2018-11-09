package project.jinshang.mod_product.bean.dto;

import io.swagger.annotations.ApiModelProperty;
import project.jinshang.mod_product.bean.ShopCar;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * create : wyh
 * date : 2018/7/17
 */
public class ShopCarProdView {

    //购物车id
    private Long shopcarid;
    private Long pdid;
    private Long limitid;
    private String level1;
    private String level2;
    private String level3;
    private String pdpicture;
    private String brandpic;
    private String brand;
    private String stand;
    private String unit;
    private BigDecimal storenum;
    private String packageStr;
    private String productname;


    private BigDecimal weight;
    private BigDecimal pdnumber;
    private String storename;
    private String mark;
    private boolean selfsupport;
    private String cardnum;
    private String material;
    private String delivertime;

    @ApiModelProperty(notes = "远期类型0=不是远期1=全款2=定金")
    private Short protype;


    @ApiModelProperty(notes = "订单类型标识0=线上1=线下2=限时购")
    private Short isonline;

    //总重量（pdnumber * weight）
    private BigDecimal totalWeight;

    //单价
    private BigDecimal price;

    //总价
    private BigDecimal allpay;

    //定金
    private BigDecimal partpay;

    //余款
    private BigDecimal yupay;

    //优惠金额
    private BigDecimal discountprice;

    private Map<String,Object> extend = new HashMap<>();


    public Object getExtend(String key) {
        return extend.get(key);
    }

    public void setExtend(String key,Object value) {
        this.extend.put(key,value);
    }

    public String getLevel1() {
        return level1;
    }

    public void setLevel1(String level1) {
        this.level1 = level1;
    }

    public String getLevel2() {
        return level2;
    }

    public void setLevel2(String level2) {
        this.level2 = level2;
    }

    public String getLevel3() {
        return level3;
    }

    public void setLevel3(String level3) {
        this.level3 = level3;
    }

    public String getPdpicture() {
        return pdpicture;
    }

    public void setPdpicture(String pdpicture) {
        this.pdpicture = pdpicture;
    }

    public String getBrandpic() {
        return brandpic;
    }

    public void setBrandpic(String brandpic) {
        this.brandpic = brandpic;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getStand() {
        return stand;
    }

    public void setStand(String stand) {
        this.stand = stand;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public BigDecimal getStorenum() {
        return storenum;
    }

    public void setStorenum(BigDecimal storenum) {
        this.storenum = storenum;
    }

    public String getPackageStr() {
        return packageStr;
    }

    public void setPackageStr(String packageStr) {
        this.packageStr = packageStr;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public String getStorename() {
        return storename;
    }

    public void setStorename(String storename) {
        this.storename = storename;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public boolean isSelfsupport() {
        return selfsupport;
    }

    public void setSelfsupport(boolean selfsupport) {
        this.selfsupport = selfsupport;
    }

    public String getCardnum() {
        return cardnum;
    }

    public void setCardnum(String cardnum) {
        this.cardnum = cardnum;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getDelivertime() {
        return delivertime;
    }

    public void setDelivertime(String delivertime) {
        this.delivertime = delivertime;
    }

    public Short getProtype() {
        return protype;
    }

    public void setProtype(Short protype) {
        this.protype = protype;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getAllpay() {
        return allpay;
    }

    public void setAllpay(BigDecimal allpay) {
        this.allpay = allpay;
    }

    public BigDecimal getPartpay() {
        return partpay;
    }

    public void setPartpay(BigDecimal partpay) {
        this.partpay = partpay;
    }

    public BigDecimal getYupay() {
        return yupay;
    }

    public void setYupay(BigDecimal yupay) {
        this.yupay = yupay;
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

    public BigDecimal getPdnumber() {
        return pdnumber;
    }

    public void setPdnumber(BigDecimal pdnumber) {
        this.pdnumber = pdnumber;
    }

    public BigDecimal getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(BigDecimal totalWeight) {
        this.totalWeight = totalWeight;
    }

    public Short getIsonline() {
        return isonline;
    }

    public void setIsonline(Short isonline) {
        this.isonline = isonline;
    }

    public Long getShopcarid() {
        return shopcarid;
    }

    public void setShopcarid(Long shopcarid) {
        this.shopcarid = shopcarid;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public BigDecimal getDiscountprice() {
        return discountprice;
    }

    public void setDiscountprice(BigDecimal discountprice) {
        this.discountprice = discountprice;
    }
}
