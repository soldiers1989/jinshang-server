package project.jinshang.mod_server.bean;

import java.math.BigDecimal;
import java.util.Date;

public class ServerOrderModel {

    private Date createtime;

    private String membername;

    private Long memberid;

    private String transactionnumber;

    private Date buyerinspectiontime;

    private BigDecimal serverpay;

    private Long id;

    private String orderno;

    private String area;

    private BigDecimal actualpayment;

    private BigDecimal freight;

    public BigDecimal getFreight() {
        return freight;
    }

    public void setFreight(BigDecimal freight) {
        this.freight = freight;
    }

    public BigDecimal getActualpayment() {
        return actualpayment;
    }

    public void setActualpayment(BigDecimal actualpayment) {
        this.actualpayment = actualpayment;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getMembername() {
        return membername;
    }

    public void setMembername(String membername) {
        this.membername = membername;
    }

    public Long getMemberid() {
        return memberid;
    }

    public void setMemberid(Long memberid) {
        this.memberid = memberid;
    }

    public String getTransactionnumber() {
        return transactionnumber;
    }

    public void setTransactionnumber(String transactionnumber) {
        this.transactionnumber = transactionnumber;
    }

    public Date getBuyerinspectiontime() {
        return buyerinspectiontime;
    }

    public void setBuyerinspectiontime(Date buyerinspectiontime) {
        this.buyerinspectiontime = buyerinspectiontime;
    }

    public BigDecimal getServerpay() {
        return serverpay;
    }

    public void setServerpay(BigDecimal serverpay) {
        this.serverpay = serverpay;
    }
}
