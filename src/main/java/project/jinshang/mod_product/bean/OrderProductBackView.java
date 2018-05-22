package project.jinshang.mod_product.bean;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

public class OrderProductBackView extends OrderProductBack{


    @ApiModelProperty(notes = "商品标准")
    private String level3;


    @ApiModelProperty(notes = "商品总价")
    private  BigDecimal actualpayment;


    private  Long classifyid;

    private  String unit;


    private String standard;


    public String getLevel3() {
        return level3;
    }


    public void setLevel3(String level3) {
        this.level3 = level3;
    }


    public BigDecimal getActualpayment() {
        return actualpayment;
    }

    public void setActualpayment(BigDecimal actualpayment) {
        this.actualpayment = actualpayment;
    }


    public Long getClassifyid() {
        return classifyid;
    }

    public void setClassifyid(Long classifyid) {
        this.classifyid = classifyid;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }
}