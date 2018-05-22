package project.jinshang.mod_activity.bean;

import java.math.BigDecimal;

public class LimitTimeStore {
    private Long id;

    private Long limittimeid;

    private Long pdid;

    private String pdno;

    private BigDecimal originalprice;

    private BigDecimal limitprice;

    private BigDecimal storenum;

    private BigDecimal salesnum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLimittimeid() {
        return limittimeid;
    }

    public void setLimittimeid(Long limittimeid) {
        this.limittimeid = limittimeid;
    }

    public Long getPdid() {
        return pdid;
    }

    public void setPdid(Long pdid) {
        this.pdid = pdid;
    }

    public String getPdno() {
        return pdno;
    }

    public void setPdno(String pdno) {
        this.pdno = pdno == null ? null : pdno.trim();
    }

    public BigDecimal getOriginalprice() {
        return originalprice;
    }

    public void setOriginalprice(BigDecimal originalprice) {
        this.originalprice = originalprice;
    }

    public BigDecimal getLimitprice() {
        return limitprice;
    }

    public void setLimitprice(BigDecimal limitprice) {
        this.limitprice = limitprice;
    }

    public BigDecimal getStorenum() {
        return storenum;
    }

    public void setStorenum(BigDecimal storenum) {
        this.storenum = storenum;
    }

    public BigDecimal getSalesnum() {
        return salesnum;
    }

    public void setSalesnum(BigDecimal salesnum) {
        this.salesnum = salesnum;
    }
}