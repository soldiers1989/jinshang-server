package project.jinshang.mod_member.bean;

import java.math.BigDecimal;

public class BuyerCollect {
    private Long id;

    private Long memberid;

    private String productname;

    private BigDecimal prodprice;

    private Long pdid;

    private Integer pdstorenum;

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

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname == null ? null : productname.trim();
    }

    public BigDecimal getProdprice() {
        return prodprice;
    }

    public void setProdprice(BigDecimal prodprice) {
        this.prodprice = prodprice;
    }

    public Long getPdid() {
        return pdid;
    }

    public void setPdid(Long pdid) {
        this.pdid = pdid;
    }

    public Integer getPdstorenum() {
        return pdstorenum;
    }

    public void setPdstorenum(Integer pdstorenum) {
        this.pdstorenum = pdstorenum;
    }
}