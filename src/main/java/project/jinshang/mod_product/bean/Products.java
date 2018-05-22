package project.jinshang.mod_product.bean;

import java.math.BigDecimal;
import java.util.Date;

public class Products {
    private Long id;

    private String level1;

    private Long level1id;

    private String level2;

    private Long level2id;

    private String level3;

    private Long level3id;

    private String productno;

    private Long productnameid;

    private String productname;

    private String productalias;

    private String brand;

    private Long brandid;

    private Long cardnumid;

    private Date createtime;

    private Long materialid;

    private String material;

    private String mark;

    private String unit;

    private String surfacetreatment;

    private String packagetype;

    private BigDecimal weight;

    private Object pddrawing;

    private Object pdpicture;

    private String pddes;

    private String cardnum;

    private String standard;

    private String attribute;

    private String prodstr;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLevel1() {
        return level1;
    }

    public void setLevel1(String level1) {
        this.level1 = level1 == null ? null : level1.trim();
    }

    public Long getLevel1id() {
        return level1id;
    }

    public void setLevel1id(Long level1id) {
        this.level1id = level1id;
    }

    public String getLevel2() {
        return level2;
    }

    public void setLevel2(String level2) {
        this.level2 = level2 == null ? null : level2.trim();
    }

    public Long getLevel2id() {
        return level2id;
    }

    public void setLevel2id(Long level2id) {
        this.level2id = level2id;
    }

    public String getLevel3() {
        return level3;
    }

    public void setLevel3(String level3) {
        this.level3 = level3 == null ? null : level3.trim();
    }

    public Long getLevel3id() {
        return level3id;
    }

    public void setLevel3id(Long level3id) {
        this.level3id = level3id;
    }

    public String getProductno() {
        return productno;
    }

    public void setProductno(String productno) {
        this.productno = productno == null ? null : productno.trim();
    }

    public Long getProductnameid() {
        return productnameid;
    }

    public void setProductnameid(Long productnameid) {
        this.productnameid = productnameid;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname == null ? null : productname.trim();
    }

    public String getProductalias() {
        return productalias;
    }

    public void setProductalias(String productalias) {
        this.productalias = productalias == null ? null : productalias.trim();
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand == null ? null : brand.trim();
    }

    public Long getBrandid() {
        return brandid;
    }

    public void setBrandid(Long brandid) {
        this.brandid = brandid;
    }

    public Long getCardnumid() {
        return cardnumid;
    }

    public void setCardnumid(Long cardnumid) {
        this.cardnumid = cardnumid;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Long getMaterialid() {
        return materialid;
    }

    public void setMaterialid(Long materialid) {
        this.materialid = materialid;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material == null ? null : material.trim();
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark == null ? null : mark.trim();
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public String getSurfacetreatment() {
        return surfacetreatment;
    }

    public void setSurfacetreatment(String surfacetreatment) {
        this.surfacetreatment = surfacetreatment == null ? null : surfacetreatment.trim();
    }

    public String getPackagetype() {
        return packagetype;
    }

    public void setPackagetype(String packagetype) {
        this.packagetype = packagetype == null ? null : packagetype.trim();
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public Object getPddrawing() {
        return pddrawing;
    }

    public void setPddrawing(Object pddrawing) {
        this.pddrawing = pddrawing;
    }

    public Object getPdpicture() {
        return pdpicture;
    }

    public void setPdpicture(Object pdpicture) {
        this.pdpicture = pdpicture;
    }

    public String getPddes() {
        return pddes;
    }

    public void setPddes(String pddes) {
        this.pddes = pddes == null ? null : pddes.trim();
    }

    public String getCardnum() {
        return cardnum;
    }

    public void setCardnum(String cardnum) {
        this.cardnum = cardnum == null ? null : cardnum.trim();
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard == null ? null : standard.trim();
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute == null ? null : attribute.trim();
    }

    public String getProdstr() {
        return prodstr;
    }

    public void setProdstr(String prodstr) {
        this.prodstr = prodstr;
    }
}