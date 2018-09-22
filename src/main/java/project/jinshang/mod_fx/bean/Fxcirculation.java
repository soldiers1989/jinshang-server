package project.jinshang.mod_fx.bean;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * null
 * 
 * @author zzy
 * 
 * @date 2018-05-05
 */
public class Fxcirculation {
    @ApiModelProperty("ID自增")
    private Long id;

    @ApiModelProperty("订单/单据ID")
    private Long commisionid;

    @ApiModelProperty("订单id")
    private Long orderid;

    @ApiModelProperty("订单单号")
    private String orderno;

    @ApiModelProperty("买家ID")
    private Long memberid;

    @ApiModelProperty("卖家id")
    private Long saleid;

    @ApiModelProperty("调整内容")
    private String circulationtext;

    @ApiModelProperty("原状态")
    private Long circulationold;

    @ApiModelProperty("调整后状态")
    private Long circulationnew;

    @ApiModelProperty("操作内容备份")
    private String other;

    @ApiModelProperty("调整时间")
    private Date createtime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCommisionid() {
        return commisionid;
    }

    public void setCommisionid(Long commisionid) {
        this.commisionid = commisionid;
    }

    public Long getOrderid() {
        return orderid;
    }

    public void setOrderid(Long orderid) {
        this.orderid = orderid;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno == null ? null : orderno.trim();
    }

    public Long getMemberid() {
        return memberid;
    }

    public void setMemberid(Long memberid) {
        this.memberid = memberid;
    }

    public Long getSaleid() {
        return saleid;
    }

    public void setSaleid(Long saleid) {
        this.saleid = saleid;
    }

    public String getCirculationtext() {
        return circulationtext;
    }

    public void setCirculationtext(String circulationtext) {
        this.circulationtext = circulationtext == null ? null : circulationtext.trim();
    }

    public Long getCirculationold() {
        return circulationold;
    }

    public void setCirculationold(Long circulationold) {
        this.circulationold = circulationold;
    }

    public Long getCirculationnew() {
        return circulationnew;
    }

    public void setCirculationnew(Long circulationnew) {
        this.circulationnew = circulationnew;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other == null ? null : other.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}