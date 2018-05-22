package project.jinshang.mod_deliveryaddress.bean;

import io.swagger.annotations.ApiModelProperty;

public class DeliveryAddress {
    private Long id;

    private Long memberid;

    @ApiModelProperty(notes = "delivery")
    private String delivery;

    @ApiModelProperty(notes = "公司名")
    private String companyname;

    @ApiModelProperty(notes = "地区代码")
    private Long regionid;

    @ApiModelProperty(notes = "省")
    private String province;

    @ApiModelProperty(notes = "市")
    private String city;

    @ApiModelProperty(notes = "区")
    private String citysmall;

    @ApiModelProperty(notes = "详细地址")
    private String address;

    @ApiModelProperty(notes = "电话")
    private String phone;

    @ApiModelProperty(notes = "是否默认使用该地址{0:不是,1:是}")
    private Short isdefault;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMemberid() {
        return memberid;
    }

    public void setMemberid(Long memberid) {
        this.memberid = memberid;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery == null ? null : delivery.trim();
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname == null ? null : companyname.trim();
    }

    public Long getRegionid() {
        return regionid;
    }

    public void setRegionid(Long regionid) {
        this.regionid = regionid;
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

    public String getCitysmall() {
        return citysmall;
    }

    public void setCitysmall(String citysmall) {
        this.citysmall = citysmall == null ? null : citysmall.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Short getIsdefault() {
        return isdefault;
    }

    public void setIsdefault(Short isdefault) {
        this.isdefault = isdefault;
    }
}