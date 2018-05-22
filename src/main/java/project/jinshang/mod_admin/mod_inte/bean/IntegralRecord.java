package project.jinshang.mod_admin.mod_inte.bean;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

public class IntegralRecord {
    private Long id;
    @ApiModelProperty(notes = "会员id")
    private Long memberid;
    @ApiModelProperty(notes = "订单id")
    private Long orderid;
    @ApiModelProperty(notes = "会员名称")
    private String membername;
    @ApiModelProperty(notes = "积分值")
    private BigDecimal scope;
    @ApiModelProperty(notes = "积分类型0=消费1=注册2=邀请3=后台增加4=后台减少")
    private Short type;
    @ApiModelProperty(notes = "创建时间")
    private Date createtime;
    @ApiModelProperty(notes = "备注")
    private String remark;
    @ApiModelProperty(notes = "新会员注册id")
    private Long registerid;
    @ApiModelProperty(notes = "新会员注册名称")
    private String registername;
    @ApiModelProperty(notes = "注册时间")
    private Date registertime;
    @ApiModelProperty(notes = "新会员积分值")
    private BigDecimal registerscope;

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

    public Long getOrderid() {
        return orderid;
    }

    public void setOrderid(Long orderid) {
        this.orderid = orderid;
    }

    public String getMembername() {
        return membername;
    }

    public void setMembername(String membername) {
        this.membername = membername == null ? null : membername.trim();
    }

    public BigDecimal getScope() {
        return scope;
    }

    public void setScope(BigDecimal scope) {
        this.scope = scope;
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Long getRegisterid() {
        return registerid;
    }

    public void setRegisterid(Long registerid) {
        this.registerid = registerid;
    }

    public String getRegistername() {
        return registername;
    }

    public void setRegistername(String registername) {
        this.registername = registername == null ? null : registername.trim();
    }

    public Date getRegistertime() {
        return registertime;
    }

    public void setRegistertime(Date registertime) {
        this.registertime = registertime;
    }

    public BigDecimal getRegisterscope() {
        return registerscope;
    }

    public void setRegisterscope(BigDecimal registerscope) {
        this.registerscope = registerscope;
    }
}