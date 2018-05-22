package project.jinshang.mod_admin.mod_inte.bean;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

public class IntegralSet {
    private Integer id;

    @ApiModelProperty(notes = "类型0=紧固件1=其它2=邀请会员3=新会员注册4=积分兑换")
    private Short type;

    @ApiModelProperty(notes = "分值")
    private BigDecimal scope;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public BigDecimal getScope() {
        return scope;
    }

    public void setScope(BigDecimal scope) {
        this.scope = scope;
    }
}