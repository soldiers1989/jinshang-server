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
public class OtherProdStoreView {

    private  Long pdid;

    private  String pdno;

    private BigDecimal pdstorenum;

    private  BigDecimal prodprice;

    private  BigDecimal thirtyprice;

    private  BigDecimal sixtyprice;

    private  BigDecimal ninetyprice;



    private  Long storeid;

    private  String storename;

    private  BigDecimal storenum;

    private BigDecimal weight;


    private  BigDecimal startnum;

    private List<ProductAttr>  productAttrList;


    private  String attrStr;


    private  BigDecimal minplus;



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


    public BigDecimal getStartnum() {
        return startnum;
    }

    public void setStartnum(BigDecimal startnum) {
        this.startnum = startnum;
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


    public String getAttrStr() {
        return attrStr;
    }

    public void setAttrStr(String attrStr) {
        this.attrStr = attrStr;
    }

    public BigDecimal getStorenum() {
        return storenum;
    }

    public void setStorenum(BigDecimal storenum) {
        this.storenum = storenum;
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
}
