package project.jinshang.mod_product.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel(description = "退货搜索条件")
public class BackQueryParam {

    @ApiModelProperty(notes = "买家id")
    private Long memberId;
    @ApiModelProperty(notes = "卖家id")
    private Long sellerid;
    @ApiModelProperty(notes = "开始时间")
    private Date startTime;
    @ApiModelProperty(notes = "结束时间")
    private Date endTime;
    @ApiModelProperty(notes = "交易号")
    private String code;
    @ApiModelProperty(notes = "商品名称")
    private String pdName;
    @ApiModelProperty(notes = "买家名称")
    private String memberName;
    @ApiModelProperty(notes = "卖家名称")
    private String sellerName;
    @ApiModelProperty(notes = "订单编号")
    private String orderNo;
    @ApiModelProperty(notes = "退货编号")
    private String backNo;
    @ApiModelProperty(notes = "退货状态")
    private Short state;
    @ApiModelProperty("可传入多个订单状态，适用于小程序的订单列表的获取")
    private String multiBackStates;
    @ApiModelProperty("小程序的订单搜索，可以代表商品名称以及订单号")
    private String prodNamAndOrderNo;

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getSellerid() {
        return sellerid;
    }

    public void setSellerid(Long sellerid) {
        this.sellerid = sellerid;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPdName() {
        return pdName;
    }

    public void setPdName(String pdName) {
        this.pdName = pdName;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Short getState() {
        return state;
    }

    public void setState(Short state) {
        this.state = state;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getBackNo() {
        return backNo;
    }

    public void setBackNo(String backNo) {
        this.backNo = backNo;
    }

    public String getMultiBackStates() {
        return multiBackStates;
    }

    public void setMultiBackStates(String multiBackStates) {
        this.multiBackStates = multiBackStates;
    }

    public String getProdNamAndOrderNo() {
        return prodNamAndOrderNo;
    }

    public void setProdNamAndOrderNo(String prodNamAndOrderNo) {
        this.prodNamAndOrderNo = prodNamAndOrderNo;
    }
}
