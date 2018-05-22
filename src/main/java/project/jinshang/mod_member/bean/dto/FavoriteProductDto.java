package project.jinshang.mod_member.bean.dto;

import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;
import java.util.Date;

/**
 * create : wyh
 * date : 2017/12/5
 */
public class FavoriteProductDto {

    private Long id;

    private Date createtime;

    private Long pid;

    private String productname;

    private BigDecimal prodprice;

    private String pic;

    private String storename;

    private BigDecimal pdstorenum;

    private String startnum;

    private String material;

    private String cardnum;

    private String brand;

    private String packagetype;

    private String tag;

    private Object pdpicture;

    private String stand;


    private  String producttype;
    private Boolean selfsupport;

    private  String level3;


    public String getProducttype() {
        return producttype;
    }

    public void setProducttype(String producttype) {
        this.producttype = producttype;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getCardnum() {
        return cardnum;
    }

    public void setCardnum(String cardnum) {
        this.cardnum = cardnum;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getPackagetype() {
        return packagetype;
    }

    public void setPackagetype(String packagetype) {
        this.packagetype = packagetype;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Object getPdpicture() {
        return pdpicture;
    }

    public void setPdpicture(Object pdpicture) {
        this.pdpicture = pdpicture;
    }

    public String getStand() {
        return stand;
    }

    public void setStand(String stand) {
        this.stand = stand;
    }

    public BigDecimal getProdprice() {
        return prodprice;
    }

    public void setProdprice(BigDecimal prodprice) {
        this.prodprice = prodprice;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getStorename() {
        return storename;
    }

    public void setStorename(String storename) {
        this.storename = storename;
    }

    public BigDecimal getPdstorenum() {
        return pdstorenum;
    }

    public void setPdstorenum(BigDecimal pdstorenum) {
        this.pdstorenum = pdstorenum;
    }

    public String getStartnum() {
        return startnum;
    }

    public void setStartnum(String startnum) {
        this.startnum = startnum;
    }

    public Boolean getSelfsupport() {
        return selfsupport;
    }

    public void setSelfsupport(Boolean selfsupport) {
        this.selfsupport = selfsupport;
    }

    public String getLevel3() {
        return level3;
    }

    public void setLevel3(String level3) {
        this.level3 = level3;
    }
}
