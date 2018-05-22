package project.jinshang.mod_product.bean;

import io.swagger.annotations.ApiModelProperty;
import project.jinshang.common.bean.Packing;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class ProductInfoQuery {
    private Long id;

    private Long memberid;


    @ApiModelProperty(notes = "一级分类")
    private String level1;

    @ApiModelProperty(notes = "一级分类id")
    private Long level1id;

    @ApiModelProperty(notes = "二级分类")
    private String level2;

    @ApiModelProperty(notes = "二级分类id")
    private Long level2id;

    @ApiModelProperty(notes = "三级分类")
    private String level3;


    @ApiModelProperty(notes = "三级分类id")
    private Long level3id;

    @ApiModelProperty(notes = "商品货号")
    private String pdno;

    private Long productnameid;

    private String productname;

    private String productalias;

    private String subtitle;

    private String brand;

    private Long brandid;

    private Long materialid;

    private String material;

    private String mark;

    private String producttype;

    private String unit;

    private String surfacetreatment;

    private BigDecimal weight;

    private String packagetype;

    private Boolean recommended;

    @ApiModelProperty(notes = "商品状态0=放入仓库1=待审核2=审核通过3=未通过4=已上架5=下架6=删除7=违规下架")
    private Short pdstate;

    @ApiModelProperty(notes = "商品图纸")
    private Object pddrawing;

    @ApiModelProperty(notes = "商品图片")
    private Object pdpicture;

    private String pddes;

    private String specificationparam;

    private String seokey;

    private String seovalue;

    private Date createtime;

    private Date audittime;

    private String auditname;

    private String reason;

    private Date uptime;

    private Date downtime;

    private Object tag;

    private Long salesnum;


    private Long cardnumid;

    private String cardnum;

    private  String stand;


    private String seotitle;


    private  String shopname;


    private Date updatetime;


    private Long productid;

    private Date uptimeStart;

    private  Date uptimeEnd;

    private  Date createStart;
    private  Date createEnd;

    /******************************EXTEND********************************************/
    @ApiModelProperty(notes = "productStore")
    private  ProductStore productStore;


    private List prices;

    public List getPrices() {
        return prices;
    }

    public void setPrices(List prices) {
        this.prices = prices;
    }

    public ProductStore getProductStore() {
        return productStore;
    }

    public void setProductStore(ProductStore productStore) {
        this.productStore = productStore;
    }



    private List<Packing> packingList;

    public List<Packing> getPackingList() {
        return packingList;
    }

    public void setPackingList(List<Packing> packingList) {
        this.packingList = packingList;
    }


    private  String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMemberid() {
        return memberid;
    }

    public void setMemberid(Long memberid) {
        this.memberid = memberid;
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

    public String getPdno() {
        return pdno;
    }

    public void setPdno(String pdno) {
        this.pdno = pdno == null ? null : pdno.trim();
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

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle == null ? null : subtitle.trim();
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

    public String getProducttype() {
        return producttype;
    }

    public void setProducttype(String producttype) {
        this.producttype = producttype == null ? null : producttype.trim();
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

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public String getPackagetype() {
        return packagetype;
    }

    public void setPackagetype(String packagetype) {
        this.packagetype = packagetype == null ? null : packagetype.trim();
    }

    public Boolean getRecommended() {
        return recommended;
    }

    public void setRecommended(Boolean recommended) {
        this.recommended = recommended;
    }

    public Short getPdstate() {
        return pdstate;
    }

    public void setPdstate(Short pdstate) {
        this.pdstate = pdstate;
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

    public String getSpecificationparam() {
        return specificationparam;
    }

    public void setSpecificationparam(String specificationparam) {
        this.specificationparam = specificationparam == null ? null : specificationparam.trim();
    }

    public String getSeokey() {
        return seokey;
    }

    public void setSeokey(String seokey) {
        this.seokey = seokey == null ? null : seokey.trim();
    }

    public String getSeovalue() {
        return seovalue;
    }

    public void setSeovalue(String seovalue) {
        this.seovalue = seovalue == null ? null : seovalue.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getAudittime() {
        return audittime;
    }

    public void setAudittime(Date audittime) {
        this.audittime = audittime;
    }

    public String getAuditname() {
        return auditname;
    }

    public void setAuditname(String auditname) {
        this.auditname = auditname == null ? null : auditname.trim();
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public Date getUptime() {
        return uptime;
    }

    public void setUptime(Date uptime) {
        this.uptime = uptime;
    }

    public Date getDowntime() {
        return downtime;
    }

    public void setDowntime(Date downtime) {
        this.downtime = downtime;
    }

    public Object getTag() {
        return tag;
    }

    public void setTag(Object tag) {
        this.tag = tag;
    }

    public Long getSalesnum() {
        return salesnum;
    }

    public void setSalesnum(Long salesnum) {
        this.salesnum = salesnum;
    }

    public Long getCardnumid() {
        return cardnumid;
    }

    public void setCardnumid(Long cardnumid) {
        this.cardnumid = cardnumid;
    }

    public String getCardnum() {
        return cardnum;
    }

    public void setCardnum(String cardnum) {
        this.cardnum = cardnum;
    }

    public String getStand() {
        return stand;
    }

    public void setStand(String stand) {
        this.stand = stand;
    }


    public String getSeotitle() {
        return seotitle;
    }

    public void setSeotitle(String seotitle) {
        this.seotitle = seotitle;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Long getProductid() {
        return productid;
    }

    public void setProductid(Long productid) {
        this.productid = productid;
    }

    public Date getUptimeStart() {
        return uptimeStart;
    }

    public void setUptimeStart(Date uptimeStart) {
        this.uptimeStart = uptimeStart;
    }

    public Date getUptimeEnd() {
        return uptimeEnd;
    }

    public void setUptimeEnd(Date uptimeEnd) {
        this.uptimeEnd = uptimeEnd;
    }

    public Date getCreateStart() {
        return createStart;
    }

    public void setCreateStart(Date createStart) {
        this.createStart = createStart;
    }

    public Date getCreateEnd() {
        return createEnd;
    }

    public void setCreateEnd(Date createEnd) {
        this.createEnd = createEnd;
    }

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }
}