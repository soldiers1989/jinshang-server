package project.jinshang.mod_product.bean.dto;

import io.swagger.annotations.ApiModelProperty;
import project.jinshang.mod_product.bean.ProductAttr;
import springfox.documentation.annotations.ApiIgnore;

import java.math.BigDecimal;
import java.util.List;

/**
 * create : wyh
 * date : 2018/1/4
 */
public class OtherProdStoreForExcel {

    private  Long pdid;

    private  String pdno;

    private BigDecimal pdstorenum;

    private  BigDecimal prodprice;

    private  BigDecimal thirtyprice;

    private  BigDecimal sixtyprice;

    private  BigDecimal ninetyprice;

    @ApiModelProperty(notes = "成本价")
    private  BigDecimal costprice;

    @ApiModelProperty(notes = "市场价")
    private  BigDecimal marketprice;

    private  Long storeid;

    @ApiModelProperty(notes = "阶梯价格")
    private  String intervalprice;

    private  boolean stepwiseprice;

    private  BigDecimal startnum;

    private  long freightmode;


    private  BigDecimal limitprice;

    private  BigDecimal storenum;

    @ApiModelProperty(notes = "仓库名称")
    private  String storename;


    @ApiModelProperty(notes = "仓库地址")
    private  String storeaddress;

    @ApiModelProperty(notes = "运费模版名称")
    private  String temname;



    private List<ProductAttr>  productAttrList;

    public String getPdno() {
        return pdno;
    }

    public void setPdno(String pdno) {
        this.pdno = pdno;
    }

    public BigDecimal getPdstorenum() {
        return pdstorenum;
    }

    public void setPdstorenum(BigDecimal pdstorenum) {
        this.pdstorenum = pdstorenum;
    }

    public BigDecimal getProdprice() {
        return prodprice;
    }

    public void setProdprice(BigDecimal prodprice) {
        this.prodprice = prodprice;
    }

    public List<ProductAttr> getProductAttrList() {
        return productAttrList;
    }

    public void setProductAttrList(List<ProductAttr> productAttrList) {
        this.productAttrList = productAttrList;
    }

    public Long getPdid() {
        return pdid;
    }

    public void setPdid(Long pdid) {
        this.pdid = pdid;
    }


    public void setStoreid(Long storeid) {
        this.storeid = storeid;
    }

    public void setStorename(String storename) {
        this.storename = storename;
    }

    @ApiIgnore
    public Long getStoreid() {
        return storeid;
    }

    @ApiIgnore
    public String getStorename() {
        return storename;
    }

    @ApiIgnore
    public String getIntervalprice() {
        return intervalprice;
    }

    public void setIntervalprice(String intervalprice) {
        this.intervalprice = intervalprice;
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

    public BigDecimal getNinetyprice() {
        return ninetyprice;
    }

    public void setNinetyprice(BigDecimal ninetyprice) {
        this.ninetyprice = ninetyprice;
    }

    @ApiIgnore
    public BigDecimal getCostprice() {
        return costprice;
    }


    public void setCostprice(BigDecimal costprice) {
        this.costprice = costprice;
    }

    public boolean isStepwiseprice() {
        return stepwiseprice;
    }

    public void setStepwiseprice(boolean stepwiseprice) {
        this.stepwiseprice = stepwiseprice;
    }


    public BigDecimal getStartnum() {
        return startnum;
    }

    public void setStartnum(BigDecimal startnum) {
        this.startnum = startnum;
    }

    public long getFreightmode() {
        return freightmode;
    }

    public void setFreightmode(long freightmode) {
        this.freightmode = freightmode;
    }

    public BigDecimal getLimitprice() {
        return limitprice;
    }

    public void setLimitprice(BigDecimal limitprice) {
        this.limitprice = limitprice;
    }

    public BigDecimal getStorenum() {
        return storenum;
    }

    public void setStorenum(BigDecimal storenum) {
        this.storenum = storenum;
    }

    public BigDecimal getMarketprice() {
        return marketprice;
    }

    public void setMarketprice(BigDecimal marketprice) {
        this.marketprice = marketprice;
    }


    public String getStoreaddress() {
        return storeaddress;
    }

    public void setStoreaddress(String storeaddress) {
        this.storeaddress = storeaddress;
    }

    public String getTemname() {
        return temname;
    }

    public void setTemname(String temname) {
        this.temname = temname;
    }
}
