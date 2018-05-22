package project.jinshang.mod_product.bean;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

public class AreaCost {
    private Long id;

    @ApiModelProperty(notes = "模板主键")
    private Long temid;

    @ApiModelProperty(notes = "省")
    private String province;

    @ApiModelProperty(notes = "市")
    private String city;

    @ApiModelProperty(notes = "增加运费元")
    private BigDecimal perkilogramcost;

    @ApiModelProperty(notes = "默认运费公斤")
    private BigDecimal defaultfreight;

    @ApiModelProperty(notes = "默认运费元")
    private BigDecimal defaultcost;

    @ApiModelProperty(notes = "每增加公斤")
    private BigDecimal perkilogramadded;


    private String selectarea;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTemid() {
        return temid;
    }

    public void setTemid(Long temid) {
        this.temid = temid;
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

    public BigDecimal getPerkilogramcost() {
        return perkilogramcost;
    }

    public void setPerkilogramcost(BigDecimal perkilogramcost) {
        this.perkilogramcost = perkilogramcost;
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

    public String getSelectarea() {
        return selectarea;
    }

    public void setSelectarea(String selectarea) {
        this.selectarea = selectarea;
    }
}