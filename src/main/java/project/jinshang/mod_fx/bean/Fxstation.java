package project.jinshang.mod_fx.bean;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * null
 * 
 * @author zzy
 * 
 * @date 2018-05-05
 */
public class Fxstation {

    @ApiModelProperty("ID自增")
    private Long id;

    @ApiModelProperty("佣金返现周期")
    private Long cycle;

    @ApiModelProperty("二级返佣金比例")
    private BigDecimal ratio2;

    @ApiModelProperty("状态")
    private Boolean disable;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCycle() {
        return cycle;
    }

    public void setCycle(Long cycle) {
        this.cycle = cycle;
    }

    public BigDecimal getRatio2() {
        return ratio2;
    }

    public void setRatio2(BigDecimal ratio2) {
        this.ratio2 = ratio2;
    }

    public Boolean getDisable() {
        return disable;
    }

    public void setDisable(Boolean disable) {
        this.disable = disable;
    }
}