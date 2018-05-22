package project.jinshang.mod_sale_rank.bean;

import java.math.BigDecimal;

public class SaleRankModel {
    private String pdname;

    private String attrjson;

    private BigDecimal price;

    private Long salenum;

    public String getPdname() {
        return pdname;
    }

    public void setPdname(String pdname) {
        this.pdname = pdname;
    }

    public String getAttrjson() {
        return attrjson;
    }

    public void setAttrjson(String attrjson) {
        this.attrjson = attrjson;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getSalenum() {
        return salenum;
    }

    public void setSalenum(Long salenum) {
        this.salenum = salenum;
    }
}
