package project.jinshang.mod_member.bean;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class MemberRateSetting {
    private Long id;

    private Long memberid;

    private Long membergradeid;

    private Long levelid;

    private Long parentlevelid;

    private BigDecimal rate;

    private String remark;

    private Integer sort;

    private Date updatetime;


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

    public Long getMembergradeid() {
        return membergradeid;
    }

    public void setMembergradeid(Long membergradeid) {
        this.membergradeid = membergradeid;
    }

    public Long getLevelid() {
        return levelid;
    }

    public void setLevelid(Long levelid) {
        this.levelid = levelid;
    }

    public Long getParentlevelid() {
        return parentlevelid;
    }

    public void setParentlevelid(Long parentlevelid) {
        this.parentlevelid = parentlevelid;
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

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

}