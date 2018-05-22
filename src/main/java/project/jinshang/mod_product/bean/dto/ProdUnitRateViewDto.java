package project.jinshang.mod_product.bean.dto;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * create : wyh
 * date : 2018/1/9
 */
public class ProdUnitRateViewDto {

    private  long id;

    @ApiModelProperty(notes = "基础单位")
    private  String unit;

    @ApiModelProperty(notes = "仓库存储单位")
    private String prodstoreunit;

    @ApiModelProperty(notes = "基本单位与库存单位转化比率")
    private BigDecimal unitrate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getProdstoreunit() {
        return prodstoreunit;
    }

    public void setProdstoreunit(String prodstoreunit) {
        this.prodstoreunit = prodstoreunit;
    }

    public BigDecimal getUnitrate() {
        return unitrate;
    }

    public void setUnitrate(BigDecimal unitrate) {
        this.unitrate = unitrate;
    }
}
