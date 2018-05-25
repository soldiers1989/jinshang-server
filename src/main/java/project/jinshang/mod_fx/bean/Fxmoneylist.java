package project.jinshang.mod_fx.bean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * null
 * 
 * @author wcyong
 * 
 * @date 2018-05-05
 */
public class Fxmoneylist {
    /**
     * ID自增
     */
    private Long id;

    /**
     * 买家ID
     */
    private Long memberid;

    /**
     * 类型1：佣金入账2：佣金使用
     */
    private Long listtype;

    /**
     * 备注
     */
    private String listtext;

    /**
     * 变动数量
     */
    private BigDecimal moneynum;

    /**
     * 佣金余额
     */
    private BigDecimal moneytotal;

    /**
     * 已使用佣金总额
     */
    private BigDecimal moneyprocessed;

    /**
     * 记录时间
     */
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