package project.jinshang.mod_product.bean;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

public class SellerSettleModel {

    @ApiModelProperty(notes = "总金额之差")
    private BigDecimal frozepay;

    @ApiModelProperty(notes = "卖家id")
    private Long saleid;

    public BigDecimal getFrozepay() {
        return frozepay;
    }

    public void setFrozepay(BigDecimal frozepay) {
        this.frozepay = frozepay;
    }

    public Long getSaleid() {
        return saleid;
    }

    public void setSaleid(Long saleid) {
        this.saleid = saleid;
    }
}
