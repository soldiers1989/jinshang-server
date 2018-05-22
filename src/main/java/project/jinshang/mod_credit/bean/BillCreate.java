package project.jinshang.mod_credit.bean;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

public class BillCreate {
    private Integer id;

    private Long buyerid;

    private String billno;

    private BigDecimal money;

    private BigDecimal amountpaid;

    private Date createtime;

    private String settlementtime;

    private Integer records;

    private Short state;

    private Date lastrepaymentday;

    @ApiModelProperty(notes = "是否违约")
    private Boolean illegal;

    @ApiModelProperty(notes = "违约天数")
    private Integer illegaldays;

    @ApiModelProperty(notes = "违约金额")
    private BigDecimal illegalmoney;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getBuyerid() {
        return buyerid;
    }

    public void setBuyerid(Long buyerid) {
        this.buyerid = buyerid;
    }

    public String getBillno() {
        return billno;
    }

    public void setBillno(String billno) {
        this.billno = billno == null ? null : billno.trim();
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public BigDecimal getAmountpaid() {
        return amountpaid;
    }

    public void setAmountpaid(BigDecimal amountpaid) {
        this.amountpaid = amountpaid;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getSettlementtime() {
        return settlementtime;
    }

    public void setSettlementtime(String settlementtime) {
        this.settlementtime = settlementtime == null ? null : settlementtime.trim();
    }

    public Integer getRecords() {
        return records;
    }

    public void setRecords(Integer records) {
        this.records = records;
    }

    public Short getState() {
        return state;
    }

    public void setState(Short state) {
        this.state = state;
    }

    public Date getLastrepaymentday() {
        return lastrepaymentday;
    }

    public void setLastrepaymentday(Date lastrepaymentday) {
        this.lastrepaymentday = lastrepaymentday;
    }

    public Boolean getIllegal() {
        return illegal;
    }

    public void setIllegal(Boolean illegal) {
        this.illegal = illegal;
    }

    public Integer getIllegaldays() {
        return illegaldays;
    }

    public void setIllegaldays(Integer illegaldays) {
        this.illegaldays = illegaldays;
    }

    public BigDecimal getIllegalmoney() {
        return illegalmoney;
    }

    public void setIllegalmoney(BigDecimal illegalmoney) {
        this.illegalmoney = illegalmoney;
    }
}