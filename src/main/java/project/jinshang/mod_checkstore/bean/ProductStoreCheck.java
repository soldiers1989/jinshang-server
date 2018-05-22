package project.jinshang.mod_checkstore.bean;

import java.math.BigDecimal;
import java.util.Date;

public class ProductStoreCheck {
    private Long id;

    private Long pdid;

    private String pdno;

    private String storesite;

    private String unit;

    private BigDecimal storenum;

    private String checkuser;

    private Date checktime;

    private String validateuser;

    private Date validatetime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getStoresite() {
        return storesite;
    }

    public void setStoresite(String storesite) {
        this.storesite = storesite == null ? null : storesite.trim();
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public BigDecimal getStorenum() {
        return storenum;
    }

    public void setStorenum(BigDecimal storenum) {
        this.storenum = storenum;
    }

    public String getCheckuser() {
        return checkuser;
    }

    public void setCheckuser(String checkuser) {
        this.checkuser = checkuser == null ? null : checkuser.trim();
    }

    public Date getChecktime() {
        return checktime;
    }

    public void setChecktime(Date checktime) {
        this.checktime = checktime;
    }

    public String getValidateuser() {
        return validateuser;
    }

    public void setValidateuser(String validateuser) {
        this.validateuser = validateuser == null ? null : validateuser.trim();
    }

    public Date getValidatetime() {
        return validatetime;
    }

    public void setValidatetime(Date validatetime) {
        this.validatetime = validatetime;
    }
}