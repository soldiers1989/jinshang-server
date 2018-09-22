package project.jinshang.mod_fx.bean;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * null
 * 
 * @author zzy
 * 
 * @date 2018-05-05
 */
public class Fxmoneylist {

    @ApiModelProperty("ID自增")
    private Long id;

    @ApiModelProperty("买家ID")
    private Long memberid;

    @ApiModelProperty("类型1：佣金入账2：佣金使用")
    private Long listtype;

    @ApiModelProperty("备注")
    private String listtext;

    @ApiModelProperty("变动数量")
    private BigDecimal moneynum;

    @ApiModelProperty("佣金余额")
    private BigDecimal moneytotal;

    @ApiModelProperty("已使用佣金总额")
    private BigDecimal moneyprocessed;

    @ApiModelProperty("记录时间")
    private Date createtime;

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

    public Long getListtype() {
        return listtype;
    }

    public void setListtype(Long listtype) {
        this.listtype = listtype;
    }

    public String getListtext() {
        return listtext;
    }

    public void setListtext(String listtext) {
        this.listtext = listtext == null ? null : listtext.trim();
    }

    public BigDecimal getMoneynum() {
        return moneynum;
    }

    public void setMoneynum(BigDecimal moneynum) {
        this.moneynum = moneynum;
    }

    public BigDecimal getMoneytotal() {
        return moneytotal;
    }

    public void setMoneytotal(BigDecimal moneytotal) {
        this.moneytotal = moneytotal;
    }

    public BigDecimal getMoneyprocessed() {
        return moneyprocessed;
    }

    public void setMoneyprocessed(BigDecimal moneyprocessed) {
        this.moneyprocessed = moneyprocessed;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}