package project.jinshang.mod_member.bean.dto;

import java.math.BigDecimal;

/**
 * create : wyh
 * date : 2017/12/11
 */
public class MemberRateSettingViewDto {



    private Long memberid;

    private Long membergradeid;

    private Long levelid;

    private Long parentlevelid;

    private BigDecimal rate;

    private  String categoryname;



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

    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }
}
