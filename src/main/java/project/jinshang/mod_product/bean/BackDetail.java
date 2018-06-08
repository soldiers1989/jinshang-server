package project.jinshang.mod_product.bean;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * 退货明细对应的实体类
 *
 * @author xiazy
 * @create 2018-06-05 16:00
 **/
public class BackDetail {
    @ApiModelProperty(notes = "商品唯一特征码")
    private String pdno;
    @ApiModelProperty(notes = "退货数量")
    private BigDecimal backnum;
    @ApiModelProperty(notes = "计价数量")
    private BigDecimal valuationnum;
    @ApiModelProperty(notes = "单价")
    private BigDecimal price;
    @ApiModelProperty(notes = "退货原因")
    private String backreason;
    @ApiModelProperty(notes = "备用参数JSON")
    private ExtendParam extendjson;
    @ApiModelProperty(notes = "时间戳")
    private Long timestamp;

    public String getPdno() {
        return pdno;
    }

    public void setPdno(String pdno) {
        this.pdno = pdno;
    }

    public BigDecimal getBacknum() {
        return backnum;
    }

    public void setBacknum(BigDecimal backnum) {
        this.backnum = backnum;
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

    public String getBackreason() {
        return backreason;
    }

    public void setBackreason(String backreason) {
        this.backreason = backreason;
    }

    public ExtendParam getExtendjson() {
        return extendjson;
    }

    public void setExtendjson(ExtendParam extendjson) {
        this.extendjson = extendjson;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
