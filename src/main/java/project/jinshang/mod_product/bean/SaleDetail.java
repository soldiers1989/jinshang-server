package project.jinshang.mod_product.bean;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * 销售明细实体类
 *
 * @author xiazy
 * @create 2018-06-05 11:43
 **/
public class SaleDetail {
    @ApiModelProperty(notes = "商品唯一特征码")
    private String pdno;
    @ApiModelProperty(notes = "购买数量")
    private BigDecimal buynum;
    @ApiModelProperty(notes = "计价数量")
    private BigDecimal valuationnum;
    @ApiModelProperty(notes = "单价")
    private BigDecimal price;

    public String getPdno() {
        return pdno;
    }

    public void setPdno(String pdno) {
        this.pdno = pdno;
    }

    public BigDecimal getBuynum() {
        return buynum;
    }

    public void setBuynum(BigDecimal buynum) {
        this.buynum = buynum;
    }

    public BigDecimal getValuationnum() {
        return valuationnum;
    }

    public void setValuationnum(BigDecimal valuationnum) {
        this.valuationnum = valuationnum;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
