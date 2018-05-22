package project.jinshang.mod_shop.bean;

import java.math.BigDecimal;

public class ShopGrade {
    private Long id;

    private Integer grade;

    private String gradename;

    private Integer productlimit;

    private BigDecimal chargestandard;

    private BigDecimal rate;

    private String remark;

    private Integer idefault;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getGradename() {
        return gradename;
    }

    public void setGradename(String gradename) {
        this.gradename = gradename == null ? null : gradename.trim();
    }

    public Integer getProductlimit() {
        return productlimit;
    }

    public void setProductlimit(Integer productlimit) {
        this.productlimit = productlimit;
    }

    public BigDecimal getChargestandard() {
        return chargestandard;
    }

    public void setChargestandard(BigDecimal chargestandard) {
        this.chargestandard = chargestandard;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getIdefault() {
        return idefault;
    }

    public void setIdefault(Integer idefault) {
        this.idefault = idefault;
    }
}