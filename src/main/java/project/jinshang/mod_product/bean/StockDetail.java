package project.jinshang.mod_product.bean;

import io.swagger.annotations.ApiModelProperty;

/**
 * 库存明细所对应的实体类
 *
 * @author xiazy
 * @create 2018-06-07 15:27
 **/
public class StockDetail {
    @ApiModelProperty(notes = "商品唯一特征码")
    public String pdno;
    @ApiModelProperty(notes = "库存可用数量")
    public String storenum;
    @ApiModelProperty(notes = "单位")
    public String unit;

    public String getPdno() {
        return pdno;
    }

    public void setPdno(String pdno) {
        this.pdno = pdno;
    }

    public String getStorenum() {
        return storenum;
    }

    public void setStorenum(String storenum) {
        this.storenum = storenum;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
