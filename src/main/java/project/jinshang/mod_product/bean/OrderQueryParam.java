package project.jinshang.mod_product.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 订单搜索条件
 * Created by Administrator on 2017/11/29.
 */
@ApiModel(description = "订单搜索条件")
public class OrderQueryParam {
    @ApiModelProperty(notes = "买家id")
    private Long memberid;
    @ApiModelProperty(notes = "买家名称")
    private String memberName;
    @ApiModelProperty(notes = "卖家id")
    private Long sellerid;
    @ApiModelProperty(notes = "卖家名称")
    private String sellerName;
    @ApiModelProperty(notes = "线上线下")
    private Short isonline;
    @ApiModelProperty(notes = "支付方式0=支付宝1=微信2=银行卡3=余额4=授信")
    private Short payType;
    @ApiModelProperty(notes = "订单类型0=立即发货1=远期全款2=远期定金3=远期余款")
    private Short orderType;
    @ApiModelProperty(notes = "商品名称")
    private String pdName;
    @ApiModelProperty(notes = "订单号")
    private String orderNo;
    @ApiModelProperty(notes = "合同号")
    private String code;
    @ApiModelProperty(notes = "交易号")
    private String tranNo;
    @ApiModelProperty(notes = "开始时间")
    private Date startTime;
    @ApiModelProperty(notes = "结束时间")
    private Date endTime;
    @ApiModelProperty(notes = "订单状态")
    private Short orderState;

    @ApiModelProperty(notes = "评价状态")
    private Short evaState;

    @ApiModelProperty(notes = "退货状态")
    private Short backstate;
    @ApiModelProperty(notes = "规格")
    private String stand;
    @ApiModelProperty(notes = "品牌")
    private String brand;
    @ApiModelProperty(notes = "印记")
    private String mark;
    @ApiModelProperty(notes = "付款开始时间")
    private Date startPayTime;
    @ApiModelProperty(notes = "付款结束时间")
    private Date endPayTime;

    @ApiModelProperty(notes = "发货号")
    private String deliveryno;

    @ApiModelProperty(notes = "店铺名称")
    private String shopName;

    @ApiModelProperty(notes = "发货方式 0-卖家直发，1-平台代发")
    private Short deliverytype;

    //添加业务员
    @ApiModelProperty(notes = "业务员")
    private String clerkname;
    private int pageNo;

    private int pageSize;

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getDeliveryno() {
        return deliveryno;
    }

    public void setDeliveryno(String deliveryno) {
        this.deliveryno = deliveryno;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Long getMemberid() {
        return memberid;
    }

    public void setMemberid(Long memberid) {
        this.memberid = memberid;
    }

    public void setSellerid(Long sellerid) {
        this.sellerid = sellerid;
    }

    public String getPdName() {
        return pdName;
    }

    public void setPdName(String pdName) {
        this.pdName = pdName;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }


    public String getStand() {
        return stand;
    }

    public void setStand(String stand) {
        this.stand = stand;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public Long getSellerid() {
        return sellerid;
    }

    public Short getOrderState() {
        return orderState;
    }

    public void setOrderState(Short orderState) {
        this.orderState = orderState;
    }

    public Short getEvaState() {
        return evaState;
    }

    public void setEvaState(Short evaState) {
        this.evaState = evaState;
    }

    public Short getBackstate() {
        return backstate;
    }

    public void setBackstate(Short backstate) {
        this.backstate = backstate;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public Short getIsonline() {
        return isonline;
    }

    public void setIsonline(Short isonline) {
        this.isonline = isonline;
    }

    public Short getPayType() {
        return payType;
    }

    public void setPayType(Short payType) {
        this.payType = payType;
    }

    public Short getOrderType() {
        return orderType;
    }

    public void setOrderType(Short orderType) {
        this.orderType = orderType;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTranNo() {
        return tranNo;
    }

    public void setTranNo(String tranNo) {
        this.tranNo = tranNo;
    }

    public Date getStartPayTime() {
        return startPayTime;
    }

    public void setStartPayTime(Date startPayTime) {
        this.startPayTime = startPayTime;
    }

    public Date getEndPayTime() {
        return endPayTime;
    }

    public void setEndPayTime(Date endPayTime) {
        this.endPayTime = endPayTime;
    }

    public Short getDeliverytype() {
        return deliverytype;
    }

    public void setDeliverytype(Short deliverytype) {
        this.deliverytype = deliverytype;
    }

    public String getClerkname() {
        return clerkname;
    }

    public void setClerkname(String clerkname) {
        this.clerkname = clerkname;
    }
}
