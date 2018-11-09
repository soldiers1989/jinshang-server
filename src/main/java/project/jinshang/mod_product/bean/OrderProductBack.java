package project.jinshang.mod_product.bean;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class OrderProductBack {
    private Long id;

    @ApiModelProperty(notes = "订单编号")
    private String orderno;
    @ApiModelProperty(notes = "订单商品id")
    private Long orderpdid;

    @ApiModelProperty(notes = "订单id")
    private Long orderid;

    @ApiModelProperty(notes = "退货类型1=退货退款2=部分退货")
    private Short backtype;

    @ApiModelProperty(notes = "退货编号")
    private String backno;

    @ApiModelProperty(notes = "退货金额")
    private BigDecimal backmoney;

    @ApiModelProperty(notes = "商品编号")
    private String pdno;

    @ApiModelProperty(notes = "商品id")
    private Long pdid;

    @ApiModelProperty(notes = "商品名称")
    private String pdname;

    @ApiModelProperty(notes = "退货状态0=待卖家处理1=卖家同意待收货2=卖家同意直接退款3=卖家收到货同意退款4=卖家不同意5=买家同意验货6=买家申请异议7=平台同意退货不扣违约金8=平台同意退货扣违约金9=平台转入待验收10=退货成功11=撤消12=卖家待收货")
    private Short state;

    @ApiModelProperty(notes = "退货物流单号")
    private String logisticsno;

    @ApiModelProperty(notes = "退货物流公司")
    private String logisticscompany;

    @ApiModelProperty(notes = "退货原因")
    private String returnbackreason;

    @ApiModelProperty(notes = "图片")
    private String pic;

    @ApiModelProperty(notes = "退货地址")
    private String backaddress;

    @ApiModelProperty(notes = "退货说明")
    private String backexplain;

    @ApiModelProperty(notes = "省")
    private String province;

    @ApiModelProperty(notes = "市")
    private String city;

    @ApiModelProperty(notes = "区")
    private String area;

    @ApiModelProperty(notes = "买家id")
    private Long memberid;

    @ApiModelProperty(notes = "卖家id")
    private Long sellerid;

    @ApiModelProperty(notes = "仓库id")
    private Long storeid;

    @ApiModelProperty(notes = "退货时间")
    private Date createtime;

    @ApiModelProperty(notes = "退货数量")
    private BigDecimal pdnum;

    @ApiModelProperty(notes = "交易号")
    private String code;

    @ApiModelProperty(notes = "买家名称")
    private String membername;

    @ApiModelProperty(notes = "是否收到货0=收到1=未收到")
    private Short isreceivegoods;

    @ApiModelProperty(notes = "卖家名称")
    private String sellname;

    @ApiModelProperty(notes = "卖家不同意原因")
    private String sellernotagree;

    @ApiModelProperty(notes = "平台处理说明")
    private String adminreason;

    @ApiModelProperty(notes = "联系人")
    private String contact;

    @ApiModelProperty(notes = "联系人电话")
    private String contactphone;

    @ApiModelProperty(notes = "申请异议的时间")
    private Date dissidencetime;
    @ApiModelProperty(notes = "平台处理意见0=正常1=不扣违约金2=扣违约金3=转入待验收")
    private Short adminstate;
    @ApiModelProperty(notes = "商品类型1=紧固件2=其它")
    private Short producttype;
    @ApiModelProperty(notes = "活动id")
    private Long limitid;


    //===============================================================================
    private Object pdPic;//商品图片


    @ApiModelProperty(notes = "商品总价")
    private  BigDecimal actualpayment;


    private BigDecimal discountpay;



    public Long getLimitid() {
        return limitid;
    }

    public void setLimitid(Long limitid) {
        this.limitid = limitid;
    }

    public Short getProducttype() {
        return producttype;
    }

    public void setProducttype(Short producttype) {
        this.producttype = producttype;
    }

    public Short getAdminstate() {
        return adminstate;
    }

    public void setAdminstate(Short adminstate) {
        this.adminstate = adminstate;
    }

    public Date getDissidencetime() {
        return dissidencetime;
    }

    public void setDissidencetime(Date dissidencetime) {
        this.dissidencetime = dissidencetime;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMembername() {
        return membername;
    }

    public void setMembername(String membername) {
        this.membername = membername;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public BigDecimal getPdnum() {
        return pdnum;
    }

    public void setPdnum(BigDecimal pdnum) {
        this.pdnum = pdnum;
    }

    public Long getMemberid() {
        return memberid;
    }

    public void setMemberid(Long memberid) {
        this.memberid = memberid;
    }

    public Long getSellerid() {
        return sellerid;
    }

    public void setSellerid(Long sellerid) {
        this.sellerid = sellerid;
    }

    public Long getStoreid() {
        return storeid;
    }

    public void setStoreid(Long storeid) {
        this.storeid = storeid;
    }

    public Long getOrderid() {
        return orderid;
    }

    public void setOrderid(Long orderid) {
        this.orderid = orderid;
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

    public Long getOrderpdid() {
        return orderpdid;
    }

    public void setOrderpdid(Long orderpdid) {
        this.orderpdid = orderpdid;
    }

    public Short getBacktype() {
        return backtype;
    }

    public void setBacktype(Short backtype) {
        this.backtype = backtype;
    }

    public String getBackno() {
        return backno;
    }

    public void setBackno(String backno) {
        this.backno = backno;
    }

    public BigDecimal getBackmoney() {
        return backmoney;
    }

    public void setBackmoney(BigDecimal backmoney) {
        this.backmoney = backmoney;
    }

    public String getPdno() {
        return pdno;
    }

    public void setPdno(String pdno) {
        this.pdno = pdno == null ? null : pdno.trim();
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

    public Short getState() {
        return state;
    }

    public void setState(Short state) {
        this.state = state;
    }

    public String getLogisticsno() {
        return logisticsno;
    }

    public void setLogisticsno(String logisticsno) {
        this.logisticsno = logisticsno == null ? null : logisticsno.trim();
    }

    public String getLogisticscompany() {
        return logisticscompany;
    }

    public void setLogisticscompany(String logisticscompany) {
        this.logisticscompany = logisticscompany == null ? null : logisticscompany.trim();
    }

    public String getReturnbackreason() {
        return returnbackreason;
    }

    public void setReturnbackreason(String returnbackreason) {
        this.returnbackreason = returnbackreason == null ? null : returnbackreason.trim();
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic == null ? null : pic.trim();
    }

    public String getBackaddress() {
        return backaddress;
    }

    public void setBackaddress(String backaddress) {
        this.backaddress = backaddress;
    }

    public String getBackexplain() {
        return backexplain;
    }

    public void setBackexplain(String backexplain) {
        this.backexplain = backexplain;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Short getIsreceivegoods() {
        return isreceivegoods;
    }

    public void setIsreceivegoods(Short isreceivegoods) {
        this.isreceivegoods = isreceivegoods;
    }

    public String getSellname() {
        return sellname;
    }

    public void setSellname(String sellname) {
        this.sellname = sellname;
    }

    public String getSellernotagree() {
        return sellernotagree;
    }

    public void setSellernotagree(String sellernotagree) {
        this.sellernotagree = sellernotagree;
    }

    public String getAdminreason() {
        return adminreason;
    }

    public void setAdminreason(String adminreason) {
        this.adminreason = adminreason;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getContactphone() {
        return contactphone;
    }

    public void setContactphone(String contactphone) {
        this.contactphone = contactphone;
    }

    public Object getPdPic() {
        return pdPic;
    }

    public void setPdPic(Object pdPic) {
        this.pdPic = pdPic;
    }

    public BigDecimal getActualpayment() {
        return actualpayment;
    }

    public void setActualpayment(BigDecimal actualpayment) {
        this.actualpayment = actualpayment;
    }

    public BigDecimal getDiscountpay() {
        return discountpay;
    }

    public void setDiscountpay(BigDecimal discountpay) {
        this.discountpay = discountpay;
    }
}