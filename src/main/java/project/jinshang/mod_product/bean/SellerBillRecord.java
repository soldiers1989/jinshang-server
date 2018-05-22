package project.jinshang.mod_product.bean;

import java.math.BigDecimal;
import java.util.Date;

public class SellerBillRecord {
    private Long id;

    private BigDecimal money;

    private String billtype;

    private String billtitle;

    private String texno;

    private String billno;

    private String diliverycompany;

    private String diliveryno;

    private Long sellerid;

    private String sellername;

    private Date applytime;

    private Short state;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getBilltype() {
        return billtype;
    }

    public void setBilltype(String billtype) {
        this.billtype = billtype == null ? null : billtype.trim();
    }

    public String getBilltitle() {
        return billtitle;
    }

    public void setBilltitle(String billtitle) {
        this.billtitle = billtitle == null ? null : billtitle.trim();
    }

    public String getTexno() {
        return texno;
    }

    public void setTexno(String texno) {
        this.texno = texno == null ? null : texno.trim();
    }

    public String getBillno() {
        return billno;
    }

    public void setBillno(String billno) {
        this.billno = billno == null ? null : billno.trim();
    }

    public String getDiliverycompany() {
        return diliverycompany;
    }

    public void setDiliverycompany(String diliverycompany) {
        this.diliverycompany = diliverycompany == null ? null : diliverycompany.trim();
    }

    public String getDiliveryno() {
        return diliveryno;
    }

    public void setDiliveryno(String diliveryno) {
        this.diliveryno = diliveryno == null ? null : diliveryno.trim();
    }

    public Long getSellerid() {
        return sellerid;
    }

    public void setSellerid(Long sellerid) {
        this.sellerid = sellerid;
    }

    public String getSellername() {
        return sellername;
    }

    public void setSellername(String sellername) {
        this.sellername = sellername == null ? null : sellername.trim();
    }

    public Date getApplytime() {
        return applytime;
    }

    public void setApplytime(Date applytime) {
        this.applytime = applytime;
    }

    public Short getState() {
        return state;
    }

    public void setState(Short state) {
        this.state = state;
    }
}