package project.jinshang.mod_purchase.bean;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

public class PurchaseRecord {
    private Long id;

    @ApiModelProperty(notes = "采购编号")
    private String purchaseno;

    @ApiModelProperty(notes = "供应商名称")
    private String supplyname;

    @ApiModelProperty(notes = "供应商编号")
    private String supplyid;

    @ApiModelProperty(notes = "发票类型")
    private String billtype;

    @ApiModelProperty(notes = "结算单位")
    private String settleunit;

    @ApiModelProperty(notes = "币总")
    private String currency;

    @ApiModelProperty(notes = "付款时间")
    private String payytime;

    @ApiModelProperty(notes = "部门")
    private String depart;

    @ApiModelProperty(notes = "业务员")
    private String business;

    @ApiModelProperty(notes = "制单员")
    private String operator;

    @ApiModelProperty(notes = "采购类型")
    private String purchasetype;

    @ApiModelProperty(notes = "结算方式")
    private String settletype;

    @ApiModelProperty(notes = "创建时间")
    private Date createtime;

    @ApiModelProperty(notes = "状态")
    private Short state;

    @ApiModelProperty(notes = "货款")
    private BigDecimal goodpay;

    @ApiModelProperty(notes = "审核人")
    private String audit;

    @ApiModelProperty(notes = "备注")
    private String remark;

    @ApiModelProperty(notes = "审核时间")
    private Date audittime;

    @ApiModelProperty(notes = "卖家id")
    private Long memberid;

    @ApiModelProperty(notes = "审核人id")
    private Long auditid;

    public Long getMemberid() {
        return memberid;
    }

    public void setMemberid(Long memberid) {
        this.memberid = memberid;
    }

    public Long getAuditid() {
        return auditid;
    }

    public void setAuditid(Long auditid) {
        this.auditid = auditid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPurchaseno() {
        return purchaseno;
    }

    public void setPurchaseno(String purchaseno) {
        this.purchaseno = purchaseno == null ? null : purchaseno.trim();
    }

    public String getSupplyname() {
        return supplyname;
    }

    public void setSupplyname(String supplyname) {
        this.supplyname = supplyname == null ? null : supplyname.trim();
    }

    public String getSupplyid() {
        return supplyid;
    }

    public void setSupplyid(String supplyid) {
        this.supplyid = supplyid;
    }

    public String getBilltype() {
        return billtype;
    }

    public void setBilltype(String billtype) {
        this.billtype = billtype == null ? null : billtype.trim();
    }

    public String getSettleunit() {
        return settleunit;
    }

    public void setSettleunit(String settleunit) {
        this.settleunit = settleunit == null ? null : settleunit.trim();
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency == null ? null : currency.trim();
    }

    public String getPayytime() {
        return payytime;
    }

    public void setPayytime(String payytime) {
        this.payytime = payytime == null ? null : payytime.trim();
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart == null ? null : depart.trim();
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business == null ? null : business.trim();
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public String getPurchasetype() {
        return purchasetype;
    }

    public void setPurchasetype(String purchasetype) {
        this.purchasetype = purchasetype == null ? null : purchasetype.trim();
    }

    public String getSettletype() {
        return settletype;
    }

    public void setSettletype(String settletype) {
        this.settletype = settletype == null ? null : settletype.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Short getState() {
        return state;
    }

    public void setState(Short state) {
        this.state = state;
    }

    public BigDecimal getGoodpay() {
        return goodpay;
    }

    public void setGoodpay(BigDecimal goodpay) {
        this.goodpay = goodpay;
    }

    public String getAudit() {
        return audit;
    }

    public void setAudit(String audit) {
        this.audit = audit == null ? null : audit.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getAudittime() {
        return audittime;
    }

    public void setAudittime(Date audittime) {
        this.audittime = audittime;
    }
}