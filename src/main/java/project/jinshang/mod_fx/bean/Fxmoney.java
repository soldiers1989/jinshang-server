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
public class Fxmoney {

    @ApiModelProperty("ID自增")
    private Long id;

    @ApiModelProperty("买家ID")
    private Long memberid;

    @ApiModelProperty("佣金余额")
    private BigDecimal moneytotal;

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

    public BigDecimal getMoneytotal() {
        return moneytotal;
    }

    public void setMoneytotal(BigDecimal moneytotal) {
        this.moneytotal = moneytotal;
    }
}