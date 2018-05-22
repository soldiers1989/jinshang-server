package project.jinshang.mod_admin.mod_upload;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

public class ProductImportModel{

    @ApiModelProperty(notes = "紧固件编码")
    private String pdno;

    @ApiModelProperty(notes = "商品副标题")
    private String pdname;

    @ApiModelProperty(notes = "商品价格")
    private BigDecimal price;

    @ApiModelProperty(notes = "起订量")
    private BigDecimal num;


    @ApiModelProperty(notes = "加购量")
    private  BigDecimal minplus;

    @ApiModelProperty(notes = "3天发货价格")
    private BigDecimal threePrice;

    @ApiModelProperty(notes = "30天发货价格")
    private BigDecimal thirtyPrice;

    @ApiModelProperty(notes = "60天发货价格")
    private BigDecimal sixtyPrice;

    @ApiModelProperty(notes = "90天发货价格")
    private BigDecimal nintyPrice;

    @ApiModelProperty(notes = "价格数量区间一")
    private String interval1;

    @ApiModelProperty(notes = "价格数量区间二")
    private String interval2;

    @ApiModelProperty(notes = "价格数量区间三")
    private String interval3;

    @ApiModelProperty(notes = "折扣一")
    private String sale1;

    @ApiModelProperty(notes = "折扣二")
    private String sale2;

    @ApiModelProperty(notes = "折扣三")
    private String sale3;

    @ApiModelProperty(notes = "市场价")
    private BigDecimal marketPrice;

    @ApiModelProperty(notes = "成本价")
    private BigDecimal costPrice;

    @ApiModelProperty(notes = "商品库存")
    private BigDecimal storeNum;

    @ApiModelProperty(notes = "商品货号")
    private String goodsNum;

    @ApiModelProperty(notes = "仓库名称")
    private String storeName;

    @ApiModelProperty(notes = "运费方式")
    private String deliveryType;

    @ApiModelProperty(notes = "商品标签")
    private String pdtag;

    @ApiModelProperty(notes = "SEO标题")
    private String seoTitle;

    @ApiModelProperty(notes = "SEO关键字")
    private String seoKey;

    @ApiModelProperty(notes = "SEO描述")
    private String seoDescription;

    public String getPdno() {
        return pdno;
    }

    public void setPdno(String pdno) {
        this.pdno = pdno;
    }

    public String getPdname() {
        return pdname;
    }

    public void setPdname(String pdname) {
        this.pdname = pdname;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getNum() {
        return num;
    }

    public void setNum(BigDecimal num) {
        this.num = num;
    }

    public BigDecimal getThreePrice() {
        return threePrice;
    }

    public void setThreePrice(BigDecimal threePrice) {
        this.threePrice = threePrice;
    }

    public BigDecimal getThirtyPrice() {
        return thirtyPrice;
    }

    public void setThirtyPrice(BigDecimal thirtyPrice) {
        this.thirtyPrice = thirtyPrice;
    }

    public BigDecimal getSixtyPrice() {
        return sixtyPrice;
    }

    public void setSixtyPrice(BigDecimal sixtyPrice) {
        this.sixtyPrice = sixtyPrice;
    }

    public BigDecimal getNintyPrice() {
        return nintyPrice;
    }

    public void setNintyPrice(BigDecimal nintyPrice) {
        this.nintyPrice = nintyPrice;
    }

    public String getInterval1() {
        return interval1;
    }

    public void setInterval1(String interval1) {
        this.interval1 = interval1;
    }

    public String getInterval2() {
        return interval2;
    }

    public void setInterval2(String interval2) {
        this.interval2 = interval2;
    }

    public String getInterval3() {
        return interval3;
    }

    public void setInterval3(String interval3) {
        this.interval3 = interval3;
    }

    public String getSale1() {
        return sale1;
    }

    public void setSale1(String sale1) {
        this.sale1 = sale1;
    }

    public String getSale2() {
        return sale2;
    }

    public void setSale2(String sale2) {
        this.sale2 = sale2;
    }

    public String getSale3() {
        return sale3;
    }

    public void setSale3(String sale3) {
        this.sale3 = sale3;
    }

    public BigDecimal getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }

    public BigDecimal getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(BigDecimal costPrice) {
        this.costPrice = costPrice;
    }

    public BigDecimal getStoreNum() {
        return storeNum;
    }

    public void setStoreNum(BigDecimal storeNum) {
        this.storeNum = storeNum;
    }

    public String getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(String goodsNum) {
        this.goodsNum = goodsNum;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    public String getPdtag() {
        return pdtag;
    }

    public void setPdtag(String pdtag) {
        this.pdtag = pdtag;
    }

    public String getSeoTitle() {
        return seoTitle;
    }

    public void setSeoTitle(String seoTitle) {
        this.seoTitle = seoTitle;
    }

    public String getSeoKey() {
        return seoKey;
    }

    public void setSeoKey(String seoKey) {
        this.seoKey = seoKey;
    }

    public String getSeoDescription() {
        return seoDescription;
    }

    public void setSeoDescription(String seoDescription) {
        this.seoDescription = seoDescription;
    }

    public BigDecimal getMinplus() {
        return minplus;
    }

    public void setMinplus(BigDecimal minplus) {
        this.minplus = minplus;
    }
}
