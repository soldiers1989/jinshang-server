package project.jinshang.mod_product.bean;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

public class ShopCar {
    private Long id;

    private Long memberid;

    @ApiModelProperty(notes = "卖家id")
    private Long saleid;

    @ApiModelProperty(notes = "createtime")
    private Date createtime;

    @ApiModelProperty(notes = "商品id")
    private Long pdid;

    @ApiModelProperty(notes = "商品编号")
    private String pdno;

    @ApiModelProperty(notes = "数量")
    private BigDecimal pdnumber;

    @ApiModelProperty(notes = "仓库id")
    private Long storeid;

    @ApiModelProperty(notes = "发货时间")
    private String delivertime;

    @ApiModelProperty(notes = "价格")
    private BigDecimal price;

    @ApiModelProperty(notes = "单位")
    private String unit;

    @ApiModelProperty(notes = "运费模板id")
    private Long frightmode;

    @ApiModelProperty(notes = "是否自提")
    private short ismailornot;

    @ApiModelProperty(notes = "仓库名称")
    private String storename;

    @ApiModelProperty(notes = "定金")
    private BigDecimal partpay;

    @ApiModelProperty(notes = "余款")
    private BigDecimal yupay;

    @ApiModelProperty(notes = "全款")
    private BigDecimal allpay;

    @ApiModelProperty(notes = "远期类型0=不是远期1=全款2=定金")
    private Short protype;

    @ApiModelProperty(notes = "产品类型1=紧固件2=其它")
    private Short producttype;


    @ApiModelProperty(notes = "属性json")
    private  String attrjson;

    @ApiModelProperty(notes = "用户保存的数量")
    private BigDecimal viewnum;

    @ApiModelProperty(notes = "用户保存的单位")
    private String viewunit;

    @ApiModelProperty(notes = "订单类型标识0=线上1=线下2=限时购")
    private Short isonline;

    @ApiModelProperty(notes = "活动id")
    private Long limitid;

    public Long getLimitid() {
        return limitid;
    }

    public void setLimitid(Long limitid) {
        this.limitid = limitid;
    }

    public Short getIsonline() {
        return isonline;
    }

    public void setIsonline(Short isonline) {
        this.isonline = isonline;
    }

    public BigDecimal getViewnum() {
        return viewnum;
    }

    public void setViewnum(BigDecimal viewnum) {
        this.viewnum = viewnum;
    }

    public String getViewunit() {
        return viewunit;
    }

    public void setViewunit(String viewunit) {
        this.viewunit = viewunit;
    }

    public String getAttrjson() {
        return attrjson;
    }

    public void setAttrjson(String attrjson) {
        this.attrjson = attrjson;
    }

    public Short getProducttype() {
        return producttype;
    }

    public void setProducttype(Short producttype) {
        this.producttype = producttype;
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

    public Long getSaleid() {
        return saleid;
    }

    public void setSaleid(Long saleid) {
        this.saleid = saleid;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Long getPdid() {
        return pdid;
    }

    public void setPdid(Long pdid) {
        this.pdid = pdid;
    }

    public String getPdno() {
        return pdno;
    }

    public void setPdno(String pdno) {
        this.pdno = pdno == null ? null : pdno.trim();
    }

    public BigDecimal getPdnumber() {
        return pdnumber;
    }

    public void setPdnumber(BigDecimal pdnumber) {
        this.pdnumber = pdnumber;
    }

    public Long getStoreid() {
        return storeid;
    }

    public void setStoreid(Long storeid) {
        this.storeid = storeid;
    }

    public String getDelivertime() {
        return delivertime;
    }

    public void setDelivertime(String delivertime) {
        this.delivertime = delivertime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Long getFrightmode() {
        return frightmode;
    }

    public void setFrightmode(Long frightmode) {
        this.frightmode = frightmode;
    }

    public short getIsmailornot() {
        return ismailornot;
    }

    public void setIsmailornot(short ismailornot) {
        this.ismailornot = ismailornot;
    }

    public String getStorename() {
        return storename;
    }

    public void setStorename(String storename) {
        this.storename = storename;
    }

    public BigDecimal getPartpay() {
        return partpay;
    }

    public void setPartpay(BigDecimal partpay) {
        this.partpay = partpay;
    }

    public BigDecimal getYupay() {
        return yupay;
    }

    public void setYupay(BigDecimal yupay) {
        this.yupay = yupay;
    }

    public BigDecimal getAllpay() {
        return allpay;
    }

    public void setAllpay(BigDecimal allpay) {
        this.allpay = allpay;
    }

    public Short getProtype() {
        return protype;
    }

    public void setProtype(Short protype) {
        this.protype = protype;
    }
}