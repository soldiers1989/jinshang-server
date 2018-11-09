package project.jinshang.mod_admin.mod_statement.bean;

import java.math.BigDecimal;
import java.util.Date;

public class BuyerStatement {
    private Long id;

    private Long memberid;

    private Long billrecoid;

    private String contractno;

    private Short type;

    private BigDecimal deliveryamount;

    private BigDecimal receiptamount;

    private BigDecimal receivableamount;

    private BigDecimal invoiceamount;

    private BigDecimal invoicebalance;

    private Short paytype;

    private String payno;

    private String remark;

    private Date createtime;

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

    public Long getBillrecoid() {
        return billrecoid;
    }

    public void setBillrecoid(Long billrecoid) {
        this.billrecoid = billrecoid;
    }

    public String getContractno() {
        return contractno;
    }

    public void setContractno(String contractno) {
        this.contractno = contractno == null ? null : contractno.trim();
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public BigDecimal getDeliveryamount() {
        return deliveryamount;
    }

    public void setDeliveryamount(BigDecimal deliveryamount) {
        this.deliveryamount = deliveryamount;
    }

    public BigDecimal getReceiptamount() {
        return receiptamount;
    }

    public void setReceiptamount(BigDecimal receiptamount) {
        this.receiptamount = receiptamount;
    }

    public BigDecimal getReceivableamount() {
        return receivableamount;
    }

    public void setReceivableamount(BigDecimal receivableamount) {
        this.receivableamount = receivableamount;
    }

    public BigDecimal getInvoiceamount() {
        return invoiceamount;
    }

    public void setInvoiceamount(BigDecimal invoiceamount) {
        this.invoiceamount = invoiceamount;
    }

    public BigDecimal getInvoicebalance() {
        return invoicebalance;
    }

    public void setInvoicebalance(BigDecimal invoicebalance) {
        this.invoicebalance = invoicebalance;
    }

    public Short getPaytype() {
        return paytype;
    }

    public void setPaytype(Short paytype) {
        this.paytype = paytype;
    }

    public String getPayno() {
        return payno;
    }

    public void setPayno(String payno) {
        this.payno = payno == null ? null : payno.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}