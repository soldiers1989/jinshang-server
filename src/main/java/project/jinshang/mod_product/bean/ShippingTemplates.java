package project.jinshang.mod_product.bean;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;

public class ShippingTemplates {
    private Long id;

    @ApiModelProperty(notes = "模板名称")
    private String temname;

    @ApiModelProperty(notes = "商品地址省")
    private String province;

    @ApiModelProperty(notes = "商品地址市")
    private String city;

    @ApiModelProperty(notes = "商品地址区")
    private String area;

    @ApiModelProperty(notes = "详细地址 ")
    private String address;

    @ApiModelProperty(notes = "是否包邮")
    private Short isfree;

    @ApiModelProperty(notes = "计价方式")
    private Short counttype;

    @ApiModelProperty(notes = "默认运费公斤")
    private BigDecimal defaultfreight;

    @ApiModelProperty(notes = "默认运费元")
    private BigDecimal defaultcost;

    @ApiModelProperty(notes = "每增加公斤")
    private BigDecimal perkilogramadded;

    @ApiModelProperty(notes = "增加运费元")
    private BigDecimal perkilogramcost;

    @ApiModelProperty(notes = "memberid")
    private Long memberid;

    List<AreaCost> areaCostList;

    private Short temtype;

    private BigDecimal defaultfreeprice;

    private Long shopgroupid;

    public List<AreaCost> getAreaCostList() {
        return areaCostList;
    }

    public void setAreaCostList(List<AreaCost> areaCostList) {
        this.areaCostList = areaCostList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTemname() {
        return temname;
    }

    public void setTemname(String temname) {
        this.temname = temname == null ? null : temname.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Short getIsfree() {
        return isfree;
    }

    public void setIsfree(Short isfree) {
        this.isfree = isfree;
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

    public Long getMemberid() {
        return memberid;
    }

    public void setMemberid(Long memberid) {
        this.memberid = memberid;
    }

    public Short getTemtype() {
        return temtype;
    }

    public void setTemtype(Short temtype) {
        this.temtype = temtype;
    }

    public BigDecimal getDefaultfreeprice() {
        return defaultfreeprice;
    }

    public void setDefaultfreeprice(BigDecimal defaultfreeprice) {
        this.defaultfreeprice = defaultfreeprice;
    }

    public Long getShopgroupid() {
        return shopgroupid;
    }

    public void setShopgroupid(Long shopgroupid) {
        this.shopgroupid = shopgroupid;
    }
}