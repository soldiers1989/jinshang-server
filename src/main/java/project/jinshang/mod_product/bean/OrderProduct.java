package project.jinshang.mod_product.bean;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderProduct {
    private Long id;

    @ApiModelProperty(notes = "订单编号")
    private String orderno;

    @ApiModelProperty(notes = "商品id")
    private Long pdid;

    @ApiModelProperty(notes = "商品编号")
    private String pdno;

    @ApiModelProperty(notes = "商品名")
    private String pdname;

    @ApiModelProperty(notes = "包装方式")
    private String pddesc;

    @ApiModelProperty(notes = "单价")
    private BigDecimal price;

    @ApiModelProperty(notes = "单位")
    private String unit;

    @ApiModelProperty(notes = "订单量")
    private BigDecimal num;

    @ApiModelProperty(notes = "仓库名称")
    private String storename;

    @ApiModelProperty(notes = "仓库id")
    private Long storeid;

    @ApiModelProperty(notes = "是否包邮")
    private Boolean mailornot;

    @ApiModelProperty(notes = "退货状态0=正常1=退货中2=退货验收3=退货完成4=异议中5=卖家不同意退货")
    private Short backstate;

    @ApiModelProperty(notes = "规格")
    private String standard;

    @ApiModelProperty(notes = "印记")
    private String mark;

    @ApiModelProperty(notes = "品牌")
    private String brand;

    @ApiModelProperty(notes = "发货时间")
    private String deliverytime;

    @ApiModelProperty(notes = "运费")
    private BigDecimal freight;

    @ApiModelProperty(notes = "卖家id")
    private Long sellerid;

    @ApiModelProperty(notes = "是否自提")
    private Short ismailornot;

    @ApiModelProperty(notes = "评价状态0=未评价1=已评价")
    private Short evaluatestate;

    @ApiModelProperty(notes = "宝贝与描述相符")
    private Short eva1;

    @ApiModelProperty(notes = "卖家服务")
    private Short eva2;

    @ApiModelProperty(notes = "物流服务")
    private Short eva3;

    @ApiModelProperty(notes = "买家心得")
    private String buyersexperience;

    @ApiModelProperty(notes = "远期类型0=不是远期1=全款2=定金")
    private Short protype;

    @ApiModelProperty(notes = "全款金额")
    private BigDecimal allpay;

    @ApiModelProperty(notes = "定金金额")
    private BigDecimal partpay;

    @ApiModelProperty(notes = "余款金额")
    private BigDecimal yupay;

    @ApiModelProperty(notes = "支付状态0=待支付1=全款支付完成2=定金支付完成3=余款支付完成")
    private Short paystate;

    @ApiModelProperty(notes = "订单id")
    private Long orderid;

    @ApiModelProperty(notes = "商品图片")
    private String pdpic;

    @ApiModelProperty(notes = "分类")
    private String classify;

    @ApiModelProperty(notes = "材质")
    private String material;

    @ApiModelProperty(notes = "牌号")
    private String gradeno;

    @ApiModelProperty(notes = "商品总价")
    private BigDecimal actualpayment;

    @ApiModelProperty(notes = "品牌图片")
    private String pic;

    @ApiModelProperty(notes = "评价时间")
    private Date evatime;
    @ApiModelProperty(notes = "所属商家")
    private String sellername;
    @ApiModelProperty(notes = "评价人")
    private String membername;
    @ApiModelProperty(notes = "产品类型1=紧固件2=其它")
    private Short producttype;
    @ApiModelProperty(notes = "是否匿名1=不匿名2=匿名")
    private Short isanonymous;

    @ApiModelProperty(notes = "属性json")
    private  String attrjson;
    @ApiModelProperty(notes = "用户保存的数量")
    private BigDecimal viewnum;
    @ApiModelProperty(notes = "用户保存的单位")
    private String viewunit;
    @ApiModelProperty(notes = "活动id")
    private Long limitid;
    @ApiModelProperty(notes = "分类id")
    private Long classifyid;
    @ApiModelProperty(notes = "评价人id")
    private Long memberid;

    @ApiModelProperty(notes = "商品佣金=商品单位佣金*商品数量")
    private BigDecimal brokepay;

    @ApiModelProperty(notes = "商品单位佣金=销售单价*商品佣金比例")
    private BigDecimal singlebrokepay;



    @ApiModelProperty(notes = "优惠金额")
    private BigDecimal discountpay;
    @ApiModelProperty(notes = "分批发货ID")
    private Long deliveryid;

    //=====================================//
    @ApiModelProperty(notes = "通俗易看的数量")
    private String packageStr;

    private List packageList;

    private boolean selfsupport;


    @ApiModelProperty(notes = "紧固件商品库编码")
    private String productsno;

    @ApiModelProperty(notes = "商品一级分类名称")
    private String productTypeName;


    @ApiModelProperty(notes = "用于销售合同打印时的产品总价计算")
    private BigDecimal actualpayforcontract;

    @Transient
    private Map<String,Object> extend;

    public Map<String, Object> getExtend() {
        if(extend==null) extend=new HashMap<>();
        return extend;
    }

    public OrderProduct setExtend(Map<String, Object> extend) {
        this.extend = extend;
        return this;
    }

    public Long getMemberid() {
        return memberid;
    }

    public void setMemberid(Long memberid) {
        this.memberid = memberid;
    }

    public Long getClassifyid() {
        return classifyid;
    }

    public void setClassifyid(Long classifyid) {
        this.classifyid = classifyid;
    }

    public Long getLimitid() {
        return limitid;
    }

    public void setLimitid(Long limitid) {
        this.limitid = limitid;
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

    public Short getIsanonymous() {
        return isanonymous;
    }

    public void setIsanonymous(Short isanonymous) {
        this.isanonymous = isanonymous;
    }

    public Date getEvatime() {
        return evatime;
    }

    public void setEvatime(Date evatime) {
        this.evatime = evatime;
    }

    public String getSellername() {
        return sellername;
    }

    public void setSellername(String sellername) {
        this.sellername = sellername;
    }

    public String getMembername() {
        return membername;
    }

    public void setMembername(String membername) {
        this.membername = membername;
    }

    public String getPdpic() {
        return pdpic;
    }

    public void setPdpic(String pdpic) {
        this.pdpic = pdpic;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno == null ? null : orderno.trim();
    }

    public Long getPdid() {
        return pdid;
    }

    public void setPdid(Long pdid) {
        this.pdid = pdid;
    }

    public String getPdname() {
        return pdname;
    }

    public void setPdname(String pdname) {
        this.pdname = pdname == null ? null : pdname.trim();
    }

    public String getPddesc() {
        return pddesc;
    }

    public void setPddesc(String pddesc) {
        this.pddesc = pddesc == null ? null : pddesc.trim();
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
        this.unit = unit == null ? null : unit.trim();
    }

    public BigDecimal getNum() {
        return num;
    }

    public void setNum(BigDecimal num) {
        this.num = num;
    }

    public String getStorename() {
        return storename;
    }

    public void setStorename(String storename) {
        this.storename = storename == null ? null : storename.trim();
    }

    public Long getStoreid() {
        return storeid;
    }

    public void setStoreid(Long storeid) {
        this.storeid = storeid;
    }

    public Boolean getMailornot() {
        return mailornot;
    }

    public void setMailornot(Boolean mailornot) {
        this.mailornot = mailornot;
    }

    public Short getBackstate() {
        return backstate;
    }

    public void setBackstate(Short backstate) {
        this.backstate = backstate;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard == null ? null : standard.trim();
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark == null ? null : mark.trim();
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand == null ? null : brand.trim();
    }

    public String getDeliverytime() {
        return deliverytime;
    }

    public void setDeliverytime(String deliverytime) {
        this.deliverytime = deliverytime == null ? null : deliverytime.trim();
    }

    public BigDecimal getFreight() {
        return freight;
    }

    public void setFreight(BigDecimal freight) {
        this.freight = freight;
    }

    public Long getSellerid() {
        return sellerid;
    }

    public void setSellerid(Long sellerid) {
        this.sellerid = sellerid;
    }

    public Short getIsmailornot() {
        return ismailornot;
    }

    public void setIsmailornot(Short ismailornot) {
        this.ismailornot = ismailornot;
    }

    public Short getEvaluatestate() {
        return evaluatestate;
    }

    public void setEvaluatestate(Short evaluatestate) {
        this.evaluatestate = evaluatestate;
    }

    public Short getEva1() {
        return eva1;
    }

    public void setEva1(Short eva1) {
        this.eva1 = eva1;
    }

    public Short getEva2() {
        return eva2;
    }

    public void setEva2(Short eva2) {
        this.eva2 = eva2;
    }

    public Short getEva3() {
        return eva3;
    }

    public void setEva3(Short eva3) {
        this.eva3 = eva3;
    }

    public String getBuyersexperience() {
        return buyersexperience;
    }

    public void setBuyersexperience(String buyersexperience) {
        this.buyersexperience = buyersexperience;
    }

    public String getPdno() {
        return pdno;
    }

    public void setPdno(String pdno) {
        this.pdno = pdno;
    }

    public Short getProtype() {
        return protype;
    }

    public void setProtype(Short protype) {
        this.protype = protype;
    }

    public BigDecimal getAllpay() {
        return allpay;
    }

    public void setAllpay(BigDecimal allpay) {
        this.allpay = allpay;
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

    public Short getPaystate() {
        return paystate;
    }

    public void setPaystate(Short paystate) {
        this.paystate = paystate;
    }

    public Long getOrderid() {
        return orderid;
    }

    public void setOrderid(Long orderid) {
        this.orderid = orderid;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getGradeno() {
        return gradeno;
    }

    public void setGradeno(String gradeno) {
        this.gradeno = gradeno;
    }

    public BigDecimal getActualpayment() {
        return actualpayment;
    }

    public void setActualpayment(BigDecimal actualpayment) {
        this.actualpayment = actualpayment;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Short getProducttype() {
        return producttype;
    }

    public void setProducttype(Short producttype) {
        this.producttype = producttype;
    }

    public String getAttrjson() {
        return attrjson;
    }

    public void setAttrjson(String attrjson) {
        this.attrjson = attrjson;
    }

    public String getPackageStr() {
        return packageStr;
    }

    public void setPackageStr(String packageStr) {
        this.packageStr = packageStr;
    }


    public List getPackageList() {
        return packageList;
    }

    public void setPackageList(List packageList) {
        this.packageList = packageList;
    }


    public BigDecimal getBrokepay() {
        return brokepay;
    }

    public void setBrokepay(BigDecimal brokepay) {
        this.brokepay = brokepay;
    }

    public BigDecimal getSinglebrokepay() {
        return singlebrokepay;
    }

    public void setSinglebrokepay(BigDecimal singlebrokepay) {
        this.singlebrokepay = singlebrokepay;
    }

    public boolean getSelfsupport() {
        return selfsupport;
    }

    public void setSelfsupport(boolean selfsupport) {
        this.selfsupport = selfsupport;
    }

    public String getProductsno() {
        return productsno;
    }

    public void setProductsno(String productsno) {
        this.productsno = productsno;
    }

    public String getProductTypeName() {
        return productTypeName;
    }

    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }

    public Long getDeliveryid() {
        return deliveryid;
    }

    public void setDeliveryid(Long deliveryid) {
        this.deliveryid = deliveryid;
    }


    public BigDecimal getDiscountpay() {
        return discountpay;
    }

    public void setDiscountpay(BigDecimal discountpay) {
        this.discountpay = discountpay;
    }

    public BigDecimal getActualpayforcontract() {
        return actualpayforcontract;
    }

    public void setActualpayforcontract(BigDecimal actualpayforcontract) {
        this.actualpayforcontract = actualpayforcontract;
    }
}