package project.jinshang.mod_product.bean;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

public class PdbailLog {
    private Long id;

    @ApiModelProperty(notes = "商品id")
    private Long pdid;

    @ApiModelProperty(notes = "商品名称")
    private String pdname;

    @ApiModelProperty(notes = "卖家资金明细表id")
    private Long captialid;

    @ApiModelProperty(notes = "保证金金额")
    private BigDecimal cash;

    @ApiModelProperty(notes = "卖家id")
    private Long sellerid;

    @ApiModelProperty(notes = "交易时间")
    private Date createtime;

    @ApiModelProperty(notes = "保证金类别0=上架1=下架")
    private Short type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPdid() {
        return pdid;
    }

    public void setPdid(Long pdid) {
        this.pdid = pdid;
    }

    public String getPdname() {
        return pdname;
    }

    public void setPdname(String pdname) {
        this.pdname = pdname == null ? null : pdname.trim();
    }

    public Long getCaptialid() {
        return captialid;
    }

    public void setCaptialid(Long captialid) {
        this.captialid = captialid;
    }

    public BigDecimal getCash() {
        return cash;
    }

    public void setCash(BigDecimal cash) {
        this.cash = cash;
    }

    public Long getSellerid() {
        return sellerid;
    }

    public void setSellerid(Long sellerid) {
        this.sellerid = sellerid;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }
}