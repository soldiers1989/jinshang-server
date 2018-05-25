package project.jinshang.mod_fx.bean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * null
 * 
 * @author wcyong
 * 
 * @date 2018-05-23
 */
public class Fxcommision {
    /**
     * ID自增
     */
    private Long id;

    /**
     * 订单ID
     */
    private Long orderid;

    /**
     * 订单单号
     */
    private String orderno;

    /**
     * 买家ID
     */
    private Long memberid;

    /**
     * 卖家ID
     */
    private Long saleid;

    /**
     * 返佣账号ID
     */
    private Long cmemberid;

    /**
     * 类型1：一级返佣2：二级返佣
     */
    private Long type;

    /**
     * 订单创建时间
     */
    private Date ordercreatetime;

    /**
     * 买家确认验货时间
     */
    private Date buyerinspectiontime;

    /**
     * 订单总金额
     */
    private BigDecimal totalprice;

    /**
     * 佣金总额
     */
    private BigDecimal commisionprice;

    /**
     * 进度状态 1：订单进行中2：佣金核算中97：订单关闭98：异常操作99：已完成
     */
    private Long progressnum;

    /**
     * 佣金计算备份
     */
    private String productslist;

    /**
     * 其他备份
     */
    private String other;

    /**
     * 添加时间
     */
    private Date createtime;

    /**
     * 返佣时间
     */
    private Date commisiontime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getCmemberid() {
        return cmemberid;
    }

    public void setCmemberid(Long cmemberid) {
        this.cmemberid = cmemberid;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public Date getOrdercreatetime() {
        return ordercreatetime;
    }

    public void setOrdercreatetime(Date ordercreatetime) {
        this.ordercreatetime = ordercreatetime;
    }

    public Date getBuyerinspectiontime() {
        return buyerinspectiontime;
    }

    public void setBuyerinspectiontime(Date buyerinspectiontime) {
        this.buyerinspectiontime = buyerinspectiontime;
    }

    public BigDecimal getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(BigDecimal totalprice) {
        this.totalprice = totalprice;
    }

    public BigDecimal getCommisionprice() {
        return commisionprice;
    }

    public void setCommisionprice(BigDecimal commisionprice) {
        this.commisionprice = commisionprice;
    }

    public Long getProgressnum() {
        return progressnum;
    }

    public void setProgressnum(Long progressnum) {
        this.progressnum = progressnum;
    }

    public String getProductslist() {
        return productslist;
    }

    public void setProductslist(String productslist) {
        this.productslist = productslist == null ? null : productslist.trim();
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

    public Date getCommisiontime() {
        return commisiontime;
    }

    public void setCommisiontime(Date commisiontime) {
        this.commisiontime = commisiontime;
    }
}