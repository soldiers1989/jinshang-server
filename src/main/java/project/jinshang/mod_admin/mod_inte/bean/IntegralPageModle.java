package project.jinshang.mod_admin.mod_inte.bean;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

public class IntegralPageModle {

    @ApiModelProperty(notes = "会员编号")
    private Long id;
    @ApiModelProperty(notes = "用户名")
    private String username;
    @ApiModelProperty(notes = "可用积分")
    private BigDecimal availableintegral;
    @ApiModelProperty(notes = "会员等级")
    private String gradename;
    @ApiModelProperty(notes = "历史积分")
    private BigDecimal integrals;
    @ApiModelProperty(notes = "创建时间")
    private Date createdate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public BigDecimal getAvailableintegral() {
        return availableintegral;
    }

    public void setAvailableintegral(BigDecimal availableintegral) {
        this.availableintegral = availableintegral;
    }

    public String getGradename() {
        return gradename;
    }

    public void setGradename(String gradename) {
        this.gradename = gradename;
    }

    public BigDecimal getIntegrals() {
        return integrals;
    }

    public void setIntegrals(BigDecimal integrals) {
        this.integrals = integrals;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }
}
