package project.jinshang.mod_product.bean.dto;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;

/**
 * create : wyh
 * date : 2018/7/17
 */
public class ShopCarView {

    private List<ShopCarProdView>  shopCarProdViewList;

    @ApiModelProperty(notes = "运费模板id")
    private Long shippingTemplateId;

    @ApiModelProperty(notes = "运费模板集合id")
    private Long shippingTemplateGroupId;

    @ApiModelProperty(notes = "默认发货方式")
    private String defaultmethod;

    //运费
    @ApiModelProperty(notes = "运费")
    private BigDecimal freight;

    //支持的物流方式
    @ApiModelProperty(notes = "支持的物流方式")
    private List<String> supportShippingMethod;

    //订单商品总价格
    @ApiModelProperty(notes = "订单商品总价格")
    private BigDecimal productTotalPrice;

    //订单商品定金总价(远期定金使用)
    @ApiModelProperty(notes = "订单商品定金总价(远期定金使用)")
    private BigDecimal productPartTotalPrice;

    @ApiModelProperty(notes = "订单商品余款总价(远期定金使用)")
    private BigDecimal productYuTotalPrice;

    @ApiModelProperty(notes = "订单商品总重量")
    private BigDecimal totalWeight;

    @ApiModelProperty(notes = "用户选择的发货方式")
    private Short orderfright;

    @ApiModelProperty(notes = "运费模板")
    private String frighttemplate;

    @ApiModelProperty(notes = "远期类型0=不是远期1=全款2=定金")
    private Short protype;




    public List<ShopCarProdView> getShopCarProdViewList() {
        return shopCarProdViewList;
    }

    public void setShopCarProdViewList(List<ShopCarProdView> shopCarProdViewList) {
        this.shopCarProdViewList = shopCarProdViewList;
    }

    public String getDefaultmethod() {
        return defaultmethod;
    }

    public void setDefaultmethod(String defaultmethod) {
        this.defaultmethod = defaultmethod;
    }

    public BigDecimal getFreight() {
        return freight;
    }

    public void setFreight(BigDecimal freight) {
        this.freight = freight;
    }

    public List<String> getSupportShippingMethod() {
        return supportShippingMethod;
    }

    public void setSupportShippingMethod(List<String> supportShippingMethod) {
        this.supportShippingMethod = supportShippingMethod;
    }

    public BigDecimal getProductTotalPrice() {
        return productTotalPrice;
    }

    public void setProductTotalPrice(BigDecimal productTotalPrice) {
        this.productTotalPrice = productTotalPrice;
    }

    public BigDecimal getProductPartTotalPrice() {
        return productPartTotalPrice;
    }

    public void setProductPartTotalPrice(BigDecimal productPartTotalPrice) {
        this.productPartTotalPrice = productPartTotalPrice;
    }

    public BigDecimal getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(BigDecimal totalWeight) {
        this.totalWeight = totalWeight;
    }

    public Long getShippingTemplateId() {
        return shippingTemplateId;
    }

    public void setShippingTemplateId(Long shippingTemplateId) {
        this.shippingTemplateId = shippingTemplateId;
    }

    public Long getShippingTemplateGroupId() {
        return shippingTemplateGroupId;
    }

    public void setShippingTemplateGroupId(Long shippingTemplateGroupId) {
        this.shippingTemplateGroupId = shippingTemplateGroupId;
    }

    public BigDecimal getProductYuTotalPrice() {
        return productYuTotalPrice;
    }

    public void setProductYuTotalPrice(BigDecimal productYuTotalPrice) {
        this.productYuTotalPrice = productYuTotalPrice;
    }

    public Short getOrderfright() {
        return orderfright;
    }


    public void setOrderfright(Short orderfright) {
        this.orderfright = orderfright;
    }

    public String getFrighttemplate() {
        return frighttemplate;
    }

    public void setFrighttemplate(String frighttemplate) {
        this.frighttemplate = frighttemplate;
    }

    public Short getProtype() {
        return protype;
    }

    public void setProtype(Short protype) {
        this.protype = protype;
    }
}
