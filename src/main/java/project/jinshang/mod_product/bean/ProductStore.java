package project.jinshang.mod_product.bean;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;

public class ProductStore {
    private Long id;

    @ApiModelProperty(notes = "商品id")
    private Long pdid;

    @ApiModelProperty(notes = "仓库id")
    private Long storeid;

    @ApiModelProperty(notes = "仓库名称")
    private String storename;

    @ApiModelProperty(notes = "是否开启阶梯价格")
    private Boolean stepwiseprice;

    @ApiModelProperty(notes = "起定量")
    private BigDecimal startnum;

    @ApiModelProperty(notes = "商品价格")
    private BigDecimal prodprice;

    @ApiModelProperty(notes = "3天价格")
    private BigDecimal threeprice;

    @ApiModelProperty(notes = "90天价格")
    private BigDecimal ninetyprice;

    @ApiModelProperty(notes = "30天价格")
    private BigDecimal thirtyprice;

    @ApiModelProperty(notes = "60天价格")
    private BigDecimal sixtyprice;

    @ApiModelProperty(notes = "区间价格和折扣")
    private String intervalprice;

    @ApiModelProperty(notes = "市场价")
    private BigDecimal marketprice;

    @ApiModelProperty(notes = "成本价")
    private BigDecimal costprice;

    @ApiModelProperty(notes = "商品库存")
    private BigDecimal pdstorenum;


    @ApiModelProperty(notes = "商品库存单位")
    private String storeunit;

    @ApiModelProperty(notes = "包装售后")
    private String aftersale;

    @ApiModelProperty(notes = "所在地")
    private String location;

    @ApiModelProperty(notes = "运费方式")
    private Long freightmode;

    @ApiModelProperty(notes = "商品编号")
    private String pdno;

    @ApiModelProperty(notes = "商品重量")
    private BigDecimal weight;

    @ApiModelProperty(notes = "最少加购量")
    private BigDecimal minplus;

    @ApiModelProperty(notes = "折扣比例")
    private BigDecimal discountratio;



    //********************EXTEND**************************************************//
    @ApiModelProperty(notes = "是否存在库存")
    private  short haveStorenum;

    private List<ProductAttr> attrList;

    public List<ProductAttr> getAttrList() {
        return attrList;
    }

    public ProductStore setAttrList(List<ProductAttr> attrList) {
        this.attrList = attrList;
        return this;
    }

    public short getHaveStorenum() {
        return haveStorenum;
    }

    public void setHaveStorenum(short haveStorenum) {
        this.haveStorenum = haveStorenum;
    }






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

    public Long getStoreid() {
        return storeid;
    }

    public void setStoreid(Long storeid) {
        this.storeid = storeid;
    }

    public String getStorename() {
        return storename;
    }

    public void setStorename(String storename) {
        this.storename = storename == null ? null : storename.trim();
    }

    public Boolean getStepwiseprice() {
        return stepwiseprice;
    }

    public void setStepwiseprice(Boolean stepwiseprice) {
        this.stepwiseprice = stepwiseprice;
    }

    public BigDecimal getStartnum() {
        return startnum;
    }

    public void setStartnum(BigDecimal startnum) {
        this.startnum = startnum;
    }

    public BigDecimal getProdprice() {
        return prodprice;
    }

    public void setProdprice(BigDecimal prodprice) {
        this.prodprice = prodprice;
    }

    public BigDecimal getThreeprice() {
        return threeprice;
    }

    public void setThreeprice(BigDecimal threeprice) {
        this.threeprice = threeprice;
    }

    public BigDecimal getNinetyprice() {
        return ninetyprice;
    }

    public void setNinetyprice(BigDecimal ninetyprice) {
        this.ninetyprice = ninetyprice;
    }

    public BigDecimal getThirtyprice() {
        return thirtyprice;
    }

    public void setThirtyprice(BigDecimal thirtyprice) {
        this.thirtyprice = thirtyprice;
    }

    public BigDecimal getSixtyprice() {
        return sixtyprice;
    }

    public void setSixtyprice(BigDecimal sixtyprice) {
        this.sixtyprice = sixtyprice;
    }

    public String getIntervalprice() {
        return intervalprice;
    }

    public void setIntervalprice(String intervalprice) {
        this.intervalprice = intervalprice == null ? null : intervalprice.trim();
    }

    public BigDecimal getMarketprice() {
        return marketprice;
    }

    public void setMarketprice(BigDecimal marketprice) {
        this.marketprice = marketprice;
    }

    public BigDecimal getCostprice() {
        return costprice;
    }

    public void setCostprice(BigDecimal costprice) {
        this.costprice = costprice;
    }

    public BigDecimal getPdstorenum() {
        return pdstorenum;
    }

    public void setPdstorenum(BigDecimal pdstorenum) {
        this.pdstorenum = pdstorenum;
    }

    public String getStoreunit() {
        return storeunit;
    }

    public void setStoreunit(String storeunit) {
        this.storeunit = storeunit == null ? null : storeunit.trim();
    }

    public String getAftersale() {
        return aftersale;
    }

    public void setAftersale(String aftersale) {
        this.aftersale = aftersale == null ? null : aftersale.trim();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public Long getFreightmode() {
        return freightmode;
    }

    public void setFreightmode(Long freightmode) {
        this.freightmode = freightmode;
    }

    public String getPdno() {
        return pdno;
    }

    public void setPdno(String pdno) {
        this.pdno = pdno;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }


    public BigDecimal getMinplus() {
        return minplus;
    }

    public void setMinplus(BigDecimal minplus) {
        this.minplus = minplus;
    }

    public BigDecimal getDiscountratio() {
        return discountratio;
    }

    public void setDiscountratio(BigDecimal discountratio) {
        this.discountratio = discountratio;
    }
}
