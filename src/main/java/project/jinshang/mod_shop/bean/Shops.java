package project.jinshang.mod_shop.bean;

import java.math.BigDecimal;

public class Shops {
    private Long id;

    private Long memberid;

    private String shopname;

    private Object businesscategory;

    private BigDecimal balance;

    private BigDecimal freezingamount;

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

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname == null ? null : shopname.trim();
    }

    public Object getBusinesscategory() {
        return businesscategory;
    }

    public void setBusinesscategory(Object businesscategory) {
        this.businesscategory = businesscategory;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getFreezingamount() {
        return freezingamount;
    }

    public void setFreezingamount(BigDecimal freezingamount) {
        this.freezingamount = freezingamount;
    }
}