package project.jinshang.mod_pay.bean;

import java.util.Date;

public class PayTradeLogs {
    private Long id;

    private String outtradeno;

    private String orderno;

    private Date createtime;

    private Long orderid;

    private Short ordertype;

    private Short paystates;

    private Short paytype;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOuttradeno() {
        return outtradeno;
    }

    public void setOuttradeno(String outtradeno) {
        this.outtradeno = outtradeno == null ? null : outtradeno.trim();
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno == null ? null : orderno.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Long getOrderid() {
        return orderid;
    }

    public void setOrderid(Long orderid) {
        this.orderid = orderid;
    }

    public Short getOrdertype() {
        return ordertype;
    }

    public void setOrdertype(Short ordertype) {
        this.ordertype = ordertype;
    }

    public Short getPaystates() {
        return paystates;
    }

    public void setPaystates(Short paystates) {
        this.paystates = paystates;
    }

    public Short getPaytype() {
        return paytype;
    }

    public void setPaytype(Short paytype) {
        this.paytype = paytype;
    }
}