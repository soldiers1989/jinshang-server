package project.jinshang.mod_product.bean.dto;

import io.swagger.annotations.ApiModelProperty;
import project.jinshang.mod_product.bean.ProductStore;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class ProductInfoEsDTO {
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

    @ApiModelProperty(notes = "紧固件商品库编码")
    private String productsno;

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


    private Date updatetime;


    private Long productid;

    @ApiModelProperty(notes = "仓库单位")
    private String prodstoreunit;

    @ApiModelProperty(notes = "基本单位与库存单位转化比率")
    private BigDecimal unitrate;

    @ApiModelProperty(notes = "最高价格")
    private BigDecimal minprice;

    @ApiModelProperty(notes = "最低价格")
    private BigDecimal heightprice;

    @ApiModelProperty(notes = "自营")
    private Boolean selfsupport;

    @ApiModelProperty(notes = "类型 0-紧固件 1-工业品 2-生活类")
    private Short type;

    private Short membersettingstate;

    private String brandPic;

    private List<ProductStore> stores;

    public Long getId() {
        return id;
    }

    public ProductInfoEsDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getMemberid() {
        return memberid;
    }

    public ProductInfoEsDTO setMemberid(Long memberid) {
        this.memberid = memberid;
        return this;
    }

    public String getLevel1() {
        return level1;
    }

    public ProductInfoEsDTO setLevel1(String level1) {
        this.level1 = level1;
        return this;
    }

    public Long getLevel1id() {
        return level1id;
    }

    public ProductInfoEsDTO setLevel1id(Long level1id) {
        this.level1id = level1id;
        return this;
    }

    public String getLevel2() {
        return level2;
    }

    public ProductInfoEsDTO setLevel2(String level2) {
        this.level2 = level2;
        return this;
    }

    public Long getLevel2id() {
        return level2id;
    }

    public ProductInfoEsDTO setLevel2id(Long level2id) {
        this.level2id = level2id;
        return this;
    }

    public String getLevel3() {
        return level3;
    }

    public ProductInfoEsDTO setLevel3(String level3) {
        this.level3 = level3;
        return this;
    }

    public Long getLevel3id() {
        return level3id;
    }

    public ProductInfoEsDTO setLevel3id(Long level3id) {
        this.level3id = level3id;
        return this;
    }

    public String getProductsno() {
        return productsno;
    }

    public ProductInfoEsDTO setProductsno(String productsno) {
        this.productsno = productsno;
        return this;
    }

    public Long getProductnameid() {
        return productnameid;
    }

    public ProductInfoEsDTO setProductnameid(Long productnameid) {
        this.productnameid = productnameid;
        return this;
    }

    public String getProductname() {
        return productname;
    }

    public ProductInfoEsDTO setProductname(String productname) {
        this.productname = productname;
        return this;
    }

    public String getProductalias() {
        return productalias;
    }

    public ProductInfoEsDTO setProductalias(String productalias) {
        this.productalias = productalias;
        return this;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public ProductInfoEsDTO setSubtitle(String subtitle) {
        this.subtitle = subtitle;
        return this;
    }

    public String getBrand() {
        return brand;
    }

    public ProductInfoEsDTO setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public Long getBrandid() {
        return brandid;
    }

    public ProductInfoEsDTO setBrandid(Long brandid) {
        this.brandid = brandid;
        return this;
    }

    public Long getMaterialid() {
        return materialid;
    }

    public ProductInfoEsDTO setMaterialid(Long materialid) {
        this.materialid = materialid;
        return this;
    }

    public String getMaterial() {
        return material;
    }

    public ProductInfoEsDTO setMaterial(String material) {
        this.material = material;
        return this;
    }

    public String getMark() {
        return mark;
    }

    public ProductInfoEsDTO setMark(String mark) {
        this.mark = mark;
        return this;
    }

    public String getProducttype() {
        return producttype;
    }

    public ProductInfoEsDTO setProducttype(String producttype) {
        this.producttype = producttype;
        return this;
    }

    public String getUnit() {
        return unit;
    }

    public ProductInfoEsDTO setUnit(String unit) {
        this.unit = unit;
        return this;
    }

    public String getSurfacetreatment() {
        return surfacetreatment;
    }

    public ProductInfoEsDTO setSurfacetreatment(String surfacetreatment) {
        this.surfacetreatment = surfacetreatment;
        return this;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public ProductInfoEsDTO setWeight(BigDecimal weight) {
        this.weight = weight;
        return this;
    }

    public String getPackagetype() {
        return packagetype;
    }

    public ProductInfoEsDTO setPackagetype(String packagetype) {
        this.packagetype = packagetype;
        return this;
    }

    public Boolean getRecommended() {
        return recommended;
    }

    public ProductInfoEsDTO setRecommended(Boolean recommended) {
        this.recommended = recommended;
        return this;
    }

    public Short getPdstate() {
        return pdstate;
    }

    public ProductInfoEsDTO setPdstate(Short pdstate) {
        this.pdstate = pdstate;
        return this;
    }

    public Object getPddrawing() {
        return pddrawing;
    }

    public ProductInfoEsDTO setPddrawing(Object pddrawing) {
        this.pddrawing = pddrawing;
        return this;
    }

    public Object getPdpicture() {
        return pdpicture;
    }

    public ProductInfoEsDTO setPdpicture(Object pdpicture) {
        this.pdpicture = pdpicture;
        return this;
    }

    public String getPddes() {
        return pddes;
    }

    public ProductInfoEsDTO setPddes(String pddes) {
        this.pddes = pddes;
        return this;
    }

    public String getSpecificationparam() {
        return specificationparam;
    }

    public ProductInfoEsDTO setSpecificationparam(String specificationparam) {
        this.specificationparam = specificationparam;
        return this;
    }

    public String getSeokey() {
        return seokey;
    }

    public ProductInfoEsDTO setSeokey(String seokey) {
        this.seokey = seokey;
        return this;
    }

    public String getSeovalue() {
        return seovalue;
    }

    public ProductInfoEsDTO setSeovalue(String seovalue) {
        this.seovalue = seovalue;
        return this;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public ProductInfoEsDTO setCreatetime(Date createtime) {
        this.createtime = createtime;
        return this;
    }

    public Date getAudittime() {
        return audittime;
    }

    public ProductInfoEsDTO setAudittime(Date audittime) {
        this.audittime = audittime;
        return this;
    }

    public String getAuditname() {
        return auditname;
    }

    public ProductInfoEsDTO setAuditname(String auditname) {
        this.auditname = auditname;
        return this;
    }

    public String getReason() {
        return reason;
    }

    public ProductInfoEsDTO setReason(String reason) {
        this.reason = reason;
        return this;
    }

    public Date getUptime() {
        return uptime;
    }

    public ProductInfoEsDTO setUptime(Date uptime) {
        this.uptime = uptime;
        return this;
    }

    public Date getDowntime() {
        return downtime;
    }

    public ProductInfoEsDTO setDowntime(Date downtime) {
        this.downtime = downtime;
        return this;
    }

    public Object getTag() {
        return tag;
    }

    public ProductInfoEsDTO setTag(Object tag) {
        this.tag = tag;
        return this;
    }

    public Long getSalesnum() {
        return salesnum;
    }

    public ProductInfoEsDTO setSalesnum(Long salesnum) {
        this.salesnum = salesnum;
        return this;
    }

    public Long getCardnumid() {
        return cardnumid;
    }

    public ProductInfoEsDTO setCardnumid(Long cardnumid) {
        this.cardnumid = cardnumid;
        return this;
    }

    public String getCardnum() {
        return cardnum;
    }

    public ProductInfoEsDTO setCardnum(String cardnum) {
        this.cardnum = cardnum;
        return this;
    }

    public String getStand() {
        return stand;
    }

    public ProductInfoEsDTO setStand(String stand) {
        this.stand = stand;
        return this;
    }

    public String getSeotitle() {
        return seotitle;
    }

    public ProductInfoEsDTO setSeotitle(String seotitle) {
        this.seotitle = seotitle;
        return this;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public ProductInfoEsDTO setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
        return this;
    }

    public Long getProductid() {
        return productid;
    }

    public ProductInfoEsDTO setProductid(Long productid) {
        this.productid = productid;
        return this;
    }

    public String getProdstoreunit() {
        return prodstoreunit;
    }

    public ProductInfoEsDTO setProdstoreunit(String prodstoreunit) {
        this.prodstoreunit = prodstoreunit;
        return this;
    }

    public BigDecimal getUnitrate() {
        return unitrate;
    }

    public ProductInfoEsDTO setUnitrate(BigDecimal unitrate) {
        this.unitrate = unitrate;
        return this;
    }

    public BigDecimal getMinprice() {
        return minprice;
    }

    public ProductInfoEsDTO setMinprice(BigDecimal minprice) {
        this.minprice = minprice;
        return this;
    }

    public BigDecimal getHeightprice() {
        return heightprice;
    }

    public ProductInfoEsDTO setHeightprice(BigDecimal heightprice) {
        this.heightprice = heightprice;
        return this;
    }

    public Boolean getSelfsupport() {
        return selfsupport;
    }

    public ProductInfoEsDTO setSelfsupport(Boolean selfsupport) {
        this.selfsupport = selfsupport;
        return this;
    }

    public Short getType() {
        return type;
    }

    public ProductInfoEsDTO setType(Short type) {
        this.type = type;
        return this;
    }

    public Short getMembersettingstate() {
        return membersettingstate;
    }

    public ProductInfoEsDTO setMembersettingstate(Short membersettingstate) {
        this.membersettingstate = membersettingstate;
        return this;
    }

    public String getBrandPic() {
        return brandPic;
    }

    public ProductInfoEsDTO setBrandPic(String brandPic) {
        this.brandPic = brandPic;
        return this;
    }

    public List<ProductStore> getStores() {
        return stores;
    }

    public ProductInfoEsDTO setStores(List<ProductStore> stores) {
        this.stores = stores;
        return this;
    }
}
