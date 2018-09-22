package project.jinshang.mod_activity.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class LimitTimeProd implements Serializable{
    private Long id;

    private Long memberid;

    private String username;

    private Long pdid;

    private String activitytitle;

    private Date begintime;

    private Date endtime;

    private BigDecimal salestotalnum;

    private Short state;

    private BigDecimal buylimit;

    private String remark;

    private String category;

    private Long categoryid;

    private BigDecimal minprice;

    private BigDecimal normalprice;

    private Integer sort;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public Long getPdid() {
        return pdid;
    }

    public void setPdid(Long pdid) {
        this.pdid = pdid;
    }

    public String getActivitytitle() {
        return activitytitle;
    }

    public void setActivitytitle(String activitytitle) {
        this.activitytitle = activitytitle == null ? null : activitytitle.trim();
    }

    public Date getBegintime() {
        return begintime;
    }

    public void setBegintime(Date begintime) {
        this.begintime = begintime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public BigDecimal getSalestotalnum() {
        return salestotalnum;
    }

    public void setSalestotalnum(BigDecimal salestotalnum) {
        this.salestotalnum = salestotalnum;
    }

    public Short getState() {
        return state;
    }

    public void setState(Short state) {
        this.state = state;
    }

    public BigDecimal getBuylimit() {
        return buylimit;
    }

    public void setBuylimit(BigDecimal buylimit) {
        this.buylimit = buylimit;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    public Long getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(Long categoryid) {
        this.categoryid = categoryid;
    }

    public BigDecimal getMinprice() {
        return minprice;
    }

    public void setMinprice(BigDecimal minprice) {
        this.minprice = minprice;
    }

    public BigDecimal getNormalprice() {
        return normalprice;
    }

    public void setNormalprice(BigDecimal normalprice) {
        this.normalprice = normalprice;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}