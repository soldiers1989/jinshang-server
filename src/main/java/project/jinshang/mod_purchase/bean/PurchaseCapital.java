package project.jinshang.mod_purchase.bean;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

public class PurchaseCapital {
    private Long id;

    @ApiModelProperty(notes = "结算类型0=预付款1=结算金额")
    private Short type;
    @ApiModelProperty(notes = "金额")
    private BigDecimal pay;
    @ApiModelProperty(notes = "时间")
    private Date createtime;
    @ApiModelProperty(notes = "供应商id")
    private Long supplyid;
    @ApiModelProperty(notes = "卖家id")
    private Long memberid;

    public Long getSupplyid() {
        return supplyid;
    }

    public void setSupplyid(Long supplyid) {
        this.supplyid = supplyid;
    }

    public Long getMemberid() {
        return memberid;
    }

    public void setMemberid(Long memberid) {
        this.memberid = memberid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public BigDecimal getPay() {
        return pay;
    }

    public void setPay(BigDecimal pay) {
        this.pay = pay;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}