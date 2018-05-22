package project.jinshang.mod_purchase.bean;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

public class PurchaseBill {
    private Long id;
    @ApiModelProperty(notes = "供应商编号")
    private Long supplyid;
    @ApiModelProperty(notes = "供应商")
    private String supplier;
    @ApiModelProperty(notes = "账期开始时间")
    private Date billstart;
    @ApiModelProperty(notes = "账期结束时间")
    private Date billend;
    @ApiModelProperty(notes = "应开发票金额")
    private BigDecimal spay;
    @ApiModelProperty(notes = "开票类型")
    private String billtype;
    @ApiModelProperty(notes = "开票金额")
    private BigDecimal pay;
    @ApiModelProperty(notes = "发票结余")
    private BigDecimal ypay;
    @ApiModelProperty(notes = "发票号")
    private String billno;
    @ApiModelProperty(notes = "快递公司")
    private String express;
    @ApiModelProperty(notes = "快递单号")
    private String expressno;
    @ApiModelProperty(notes = "卖家id")
    private Long memberid;
    @ApiModelProperty(notes = "创建时间")
    private Date createtime;

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Long getMemberid() {
        return memberid;
    }

    public void setMemberid(Long memberid) {
        this.memberid = memberid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSupplyid() {
        return supplyid;
    }

    public void setSupplyid(Long supplyid) {
        this.supplyid = supplyid;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier == null ? null : supplier.trim();
    }

    public Date getBillstart() {
        return billstart;
    }

    public void setBillstart(Date billstart) {
        this.billstart = billstart;
    }

    public Date getBillend() {
        return billend;
    }

    public void setBillend(Date billend) {
        this.billend = billend;
    }

    public BigDecimal getSpay() {
        return spay;
    }

    public void setSpay(BigDecimal spay) {
        this.spay = spay;
    }

    public String getBilltype() {
        return billtype;
    }

    public void setBilltype(String billtype) {
        this.billtype = billtype == null ? null : billtype.trim();
    }

    public BigDecimal getPay() {
        return pay;
    }

    public void setPay(BigDecimal pay) {
        this.pay = pay;
    }

    public BigDecimal getYpay() {
        return ypay;
    }

    public void setYpay(BigDecimal ypay) {
        this.ypay = ypay;
    }

    public String getBillno() {
        return billno;
    }

    public void setBillno(String billno) {
        this.billno = billno == null ? null : billno.trim();
    }

    public String getExpress() {
        return express;
    }

    public void setExpress(String express) {
        this.express = express == null ? null : express.trim();
    }

    public String getExpressno() {
        return expressno;
    }

    public void setExpressno(String expressno) {
        this.expressno = expressno == null ? null : expressno.trim();
    }
}