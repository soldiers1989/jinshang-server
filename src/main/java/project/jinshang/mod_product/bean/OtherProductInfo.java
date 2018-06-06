package project.jinshang.mod_product.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * create : wyh
 * date : 2017/12/25
 */
public class OtherProductInfo implements Serializable{

    @ApiModelProperty(notes = "商品价格")
    private BigDecimal prodprice;

    @ApiModelProperty(notes = "库存")
    private  BigDecimal pdstorenum;

    @ApiModelProperty(notes = "商品编码")
    private  String pdno;

    @ApiModelProperty(notes = "90天价格")
    private BigDecimal ninetyprice;

    @ApiModelProperty(notes = "30天价格")
    private BigDecimal thirtyprice;

    @ApiModelProperty(notes = "60天价格")
    private BigDecimal sixtyprice;


    @ApiModelProperty(notes = "商品重量")
    private BigDecimal weight;

    @ApiModelProperty(notes = "起定量")
    private BigDecimal startnum;


    @ApiModelProperty(notes = "加购量")
    private  BigDecimal minplus;


    private List<ProductAttr> productAttrs;

    public BigDecimal getProdprice() {
        return prodprice;
    }

    public void setProdprice(BigDecimal prodprice) {
        this.prodprice = prodprice;
    }

    public BigDecimal getPdstorenum() {
        return pdstorenum;
    }

    public void setPdstorenum(BigDecimal pdstorenum) {
        this.pdstorenum = pdstorenum;
    }

    public String getPdno() {
        return pdno;
    }

    public void setPdno(String pdno) {
        this.pdno = pdno;
    }

    public List<ProductAttr> getProductAttrs() {
        return productAttrs;
    }

    public void setProductAttrs(List<ProductAttr> productAttrs) {
        this.productAttrs = productAttrs;
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

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public BigDecimal getStartnum() {
        return startnum;
    }

    public void setStartnum(BigDecimal startnum) {
        this.startnum = startnum;
    }


    public BigDecimal getMinplus() {
        return minplus;
    }

    public void setMinplus(BigDecimal minplus) {
        this.minplus = minplus;
    }

    public static void main(String[] args) {
        List<OtherProductInfo> listAll = new ArrayList<>();

        OtherProductInfo info = new OtherProductInfo();
        info.setPdno("123");
        info.setPdstorenum(new BigDecimal(100));
        info.setProdprice(new BigDecimal(1));
        List<ProductAttr> list =  new ArrayList<>();
        ProductAttr attt1 = new ProductAttr();
        attt1.setAttributeid((long)1);
        attt1.setAttribute("颜色");
        attt1.setValue("红色");

        ProductAttr attt2 = new ProductAttr();
        attt2.setAttributeid((long)2);
        attt2.setAttribute("尺寸");
        attt2.setValue("180");

        list.add(attt1);
        list.add(attt2);
        info.setProductAttrs(list);


        listAll.add(info);



        info = new OtherProductInfo();
        info.setPdno("456");
        info.setPdstorenum(new BigDecimal(100));
        info.setProdprice(new BigDecimal(1));

         list =  new ArrayList<>();
        attt1.setAttributeid((long)1);
        attt1.setAttribute("颜色");
        attt1.setValue("绿色");

        attt2.setAttributeid((long)2);
        attt2.setAttribute("尺寸");
        attt2.setValue("170");

        list.add(attt1);
        list.add(attt2);
        info.setProductAttrs(list);
        listAll.add(info);


        Gson gson =  new Gson();

        String json =  gson.toJson(listAll);
        System.out.println(json);
        List<OtherProductInfo> resultList  =   gson.fromJson(json,new TypeToken<List<OtherProductInfo>>() {}.getType());
        System.out.println(resultList);
    }


}
