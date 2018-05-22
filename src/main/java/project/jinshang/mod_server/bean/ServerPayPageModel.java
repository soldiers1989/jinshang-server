package project.jinshang.mod_server.bean;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

public class ServerPayPageModel {

    private Long id;

    @ApiModelProperty(notes = "服务商")
    private String servername;

    @ApiModelProperty(notes = "地区")
    private String area;

    @ApiModelProperty(notes = "城市")
    private String city;

    @ApiModelProperty(notes = "省")
    private String province;

    @ApiModelProperty(notes = "服务费占比")
    private BigDecimal rate;

    @ApiModelProperty(notes = "服务费")
    private BigDecimal sum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getServername() {
        return servername;
    }

    public void setServername(String servername) {
        this.servername = servername;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }
}
