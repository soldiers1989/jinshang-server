package project.jinshang.mod_purchase.bean;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

public class PurchaseProduct {
    private Long id;

    @ApiModelProperty(notes = "采购记录id")
    private Long purchaseid;

    @ApiModelProperty(notes = "商品名称")
    private String pdname;

    @ApiModelProperty(notes = "商品编号")
    private String pdno;

    @ApiModelProperty(notes = "商品id")
    private String pdid;

    @ApiModelProperty(notes = "规格")
    private Long standard;

    @ApiModelProperty(notes = "单位")
    private String unit;

    @ApiModelProperty(notes = "数量")
    private BigDecimal num;

    @ApiModelProperty(notes = "价格")
    private BigDecimal price;

    @ApiModelProperty(notes = "货款")
    private BigDecimal allpay;

    @ApiModelProperty(notes = "品牌")
    private String brand;

    @ApiModelProperty(notes = "折扣")
    private BigDecimal sale;

    @ApiModelProperty(notes = "批次")
    private String batchno;

    @ApiModelProperty(notes = "备注")
    private String remark;

    @ApiModelProperty(notes = "仓库名称")
    private String storename;

    @ApiModelProperty(notes = "供应商id")
    private Long supplyid;

    @ApiModelProperty(notes = "卖家id")
    private Long memberid;

    public Long getSupplyid() {
        return supplyid;
    }

    public void setSupplyid(Long supplyid) {
        this.supplyid = supplyid;
    }

    public Long getMemberid() {
        return memberid;
    }

    public void setMemberid(Long memberid) {
        this.memberid = memberid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPurchaseid() {
        return purchaseid;
    }

    public void setPurchaseid(Long purchaseid) {
        this.purchaseid = purchaseid;
    }

    public String getPdname() {
        return pdname;
    }

    public void setPdname(String pdname) {
        this.pdname = pdname == null ? null : pdname.trim();
    }

    public String getPdno() {
        return pdno;
    }

    public void setPdno(String pdno) {
        this.pdno = pdno == null ? null : pdno.trim();
    }

    public String getPdid() {
        return pdid;
    }

    public void setPdid(String pdid) {
        this.pdid = pdid == null ? null : pdid.trim();
    }

    public Long getStandard() {
        return standard;
    }

    public void setStandard(Long standard) {
        this.standard = standard;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public BigDecimal getNum() {
        return num;
    }

    public void setNum(BigDecimal num) {
        this.num = num;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getAllpay() {
        return allpay;
    }

    public void setAllpay(BigDecimal allpay) {
        this.allpay = allpay;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand == null ? null : brand.trim();
    }

    public BigDecimal getSale() {
        return sale;
    }

    public void setSale(BigDecimal sale) {
        this.sale = sale;
    }

    public String getBatchno() {
        return batchno;
    }

    public void setBatchno(String batchno) {
        this.batchno = batchno == null ? null : batchno.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getStorename() {
        return storename;
    }

    public void setStorename(String storename) {
        this.storename = storename == null ? null : storename.trim();
    }
}