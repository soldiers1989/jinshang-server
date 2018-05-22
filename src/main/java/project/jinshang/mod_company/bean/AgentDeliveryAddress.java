package project.jinshang.mod_company.bean;

import io.swagger.annotations.ApiModelProperty;

/**
 * 代理发货地址
 */
public class AgentDeliveryAddress {
    private Long id;

    @ApiModelProperty(notes = "卖家id")
    private Long sellerid;

    @ApiModelProperty(notes = "联系人")
    private String linkman;

    @ApiModelProperty(notes = "联系电话")
    private String tel;

    @ApiModelProperty(notes ="邮编" )
    private String zipcode;

    @ApiModelProperty(notes = "省")
    private String province;

    @ApiModelProperty(notes = "市")
    private String city;

    @ApiModelProperty(notes = "区")
    private String citysmall;

    @ApiModelProperty(notes = "详细地址")
    private String address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSellerid() {
        return sellerid;
    }

    public void setSellerid(Long sellerid) {
        this.sellerid = sellerid;
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman == null ? null : linkman.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode == null ? null : zipcode.trim();
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
}