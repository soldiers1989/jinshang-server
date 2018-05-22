package project.jinshang.mod_product.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

@ApiModel(description = "开票搜索条件")
public class BillQueryParam {

    @ApiModelProperty(notes = "开始时间")
    private Date startTime;
    @ApiModelProperty(notes = "结束时间")
    private Date endTime;
    @ApiModelProperty(notes = "开票状态")
    private Short state;
    @ApiModelProperty(notes = "会员编号")
    private Long memberid;
    @ApiModelProperty(notes = "会员名称")
    private String memberName;
    @ApiModelProperty(notes = "订单号")
    private String orderNo;
    @ApiModelProperty(notes = "开票开始金额")
    private BigDecimal billStartCash;
    @ApiModelProperty(notes = "开票结束金额")
    private BigDecimal billEndCash;
    @ApiModelProperty(notes = "快递单号")
    private String expressNo;
    @ApiModelProperty(notes = "开票开始时间")
    private Date bStartTime;
    @ApiModelProperty(notes = "开票结束时间")
    private Date bEndTime;
    @ApiModelProperty(notes = "开票号")
    private String billNo;
    @ApiModelProperty(notes = "发票抬头")
    private String invoiceheadup;

    public String getInvoiceheadup() {
        return invoiceheadup;
    }

    public void setInvoiceheadup(String invoiceheadup) {
        this.invoiceheadup = invoiceheadup;
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

    public Short getState() {
        return state;
    }

    public void setState(Short state) {
        this.state = state;
    }

    public Long getMemberid() {
        return memberid;
    }

    public void setMemberid(Long memberid) {
        this.memberid = memberid;
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

    public BigDecimal getBillStartCash() {
        return billStartCash;
    }

    public void setBillStartCash(BigDecimal billStartCash) {
        this.billStartCash = billStartCash;
    }

    public BigDecimal getBillEndCash() {
        return billEndCash;
    }

    public void setBillEndCash(BigDecimal billEndCash) {
        this.billEndCash = billEndCash;
    }

    public String getExpressNo() {
        return expressNo;
    }

    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo;
    }

    public Date getbStartTime() {
        return bStartTime;
    }

    public void setbStartTime(Date bStartTime) {
        this.bStartTime = bStartTime;
    }

    public Date getbEndTime() {
        return bEndTime;
    }

    public void setbEndTime(Date bEndTime) {
        this.bEndTime = bEndTime;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }
}
