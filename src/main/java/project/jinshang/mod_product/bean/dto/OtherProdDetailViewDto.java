package project.jinshang.mod_product.bean.dto;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * create : wyh
 * date : 2018/1/4
 */
public class OtherProdDetailViewDto {

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

    @ApiModelProperty(notes = "成本价")
    private  BigDecimal costprice;

    @ApiModelProperty(notes = "市场价")
    private  BigDecimal marketprice;


    @ApiModelProperty(notes = "是否开启阶梯价格")
    private  boolean stepwiseprice;


    @ApiModelProperty(notes = "仓库id")
    private  Long storeid;

    @ApiModelProperty(notes = "仓库名称")
    private String storename;


    private  long freightmode;

    private  String username;


    private  String shopname;


    @ApiModelProperty(notes = "自营")
    private Boolean selfsupport;


    @ApiModelProperty(notes = "仓库单位")
    private String prodstoreunit;;

    @ApiModelProperty(notes = "基本单位与库存单位转化比率")
    private BigDecimal unitrate;

    @ApiModelProperty(notes = "起订量")
    private  BigDecimal startnum;


    @ApiModelProperty(notes = "最高价格")
    private BigDecimal minprice;

    @ApiModelProperty(notes = "最低价格")
    private BigDecimal heightprice;

    private Short type;



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
        this.level1 = level1;
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
        this.level2 = level2;
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
        this.level3 = level3;
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
        this.pdno = pdno;
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
        this.productname = productname;
    }

    public String getProductalias() {
        return productalias;
    }

    public void setProductalias(String productalias) {
        this.productalias = productalias;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
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
        this.material = material;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getProducttype() {
        return producttype;
    }

    public void setProducttype(String producttype) {
        this.producttype = producttype;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getSurfacetreatment() {
        return surfacetreatment;
    }

    public void setSurfacetreatment(String surfacetreatment) {
        this.surfacetreatment = surfacetreatment;
    }


    public String getPackagetype() {
        return packagetype;
    }

    public void setPackagetype(String packagetype) {
        this.packagetype = packagetype;
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
        this.pddes = pddes;
    }

    public String getSpecificationparam() {
        return specificationparam;
    }

    public void setSpecificationparam(String specificationparam) {
        this.specificationparam = specificationparam;
    }

    public String getSeokey() {
        return seokey;
    }

    public void setSeokey(String seokey) {
        this.seokey = seokey;
    }

    public String getSeovalue() {
        return seovalue;
    }

    public void setSeovalue(String seovalue) {
        this.seovalue = seovalue;
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
        this.auditname = auditname;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
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

    public BigDecimal getCostprice() {
        return costprice;
    }

    public void setCostprice(BigDecimal costprice) {
        this.costprice = costprice;
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
        this.storename = storename;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }



    public boolean isStepwiseprice() {
        return stepwiseprice;
    }

    public void setStepwiseprice(boolean stepwiseprice) {
        this.stepwiseprice = stepwiseprice;
    }

    public long getFreightmode() {
        return freightmode;
    }

    public void setFreightmode(long freightmode) {
        this.freightmode = freightmode;
    }

    public BigDecimal getMarketprice() {
        return marketprice;
    }

    public void setMarketprice(BigDecimal marketprice) {
        this.marketprice = marketprice;
    }

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }


    public Boolean getSelfsupport() {
        return selfsupport;
    }

    public void setSelfsupport(Boolean selfsupport) {
        this.selfsupport = selfsupport;
    }


    public String getProdstoreunit() {
        return prodstoreunit;
    }

    public void setProdstoreunit(String prodstoreunit) {
        this.prodstoreunit = prodstoreunit;
    }

    public BigDecimal getUnitrate() {
        return unitrate;
    }

    public void setUnitrate(BigDecimal unitrate) {
        this.unitrate = unitrate;
    }


    public BigDecimal getStartnum() {
        return startnum;
    }

    public void setStartnum(BigDecimal startnum) {
        this.startnum = startnum;
    }

    public BigDecimal getMinprice() {
        return minprice;
    }

    public void setMinprice(BigDecimal minprice) {
        this.minprice = minprice;
    }

    public BigDecimal getHeightprice() {
        return heightprice;
    }

    public void setHeightprice(BigDecimal heightprice) {
        this.heightprice = heightprice;
    }


    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }
}
