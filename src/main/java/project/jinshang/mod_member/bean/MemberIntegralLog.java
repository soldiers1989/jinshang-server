package project.jinshang.mod_member.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class MemberIntegralLog {
    private Long id;

    private Long memberid;

    @ApiModelProperty(notes = "积分来源")
    private String source;

    @ApiModelProperty(notes = "积分值")
    private Integer integrals;

    @ApiModelProperty(notes = "总积分")
    private Integer totalscore;

    private Date creatdate;

    private String remake;

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

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    public Integer getIntegrals() {
        return integrals;
    }

    public void setIntegrals(Integer integrals) {
        this.integrals = integrals;
    }

    public Integer getTotalscore() {
        return totalscore;
    }

    public void setTotalscore(Integer totalscore) {
        this.totalscore = totalscore;
    }

    public Date getCreatdate() {
        return creatdate;
    }

    public void setCreatdate(Date creatdate) {
        this.creatdate = creatdate;
    }

    public String getRemake() {
        return remake;
    }

    public void setRemake(String remake) {
        this.remake = remake == null ? null : remake.trim();
    }
}