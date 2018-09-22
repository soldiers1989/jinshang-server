package project.jinshang.mod_sellerbill.bean;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;


public class SellerBillBreak {
    private Long id;

    @ApiModelProperty(notes = "关联sellerbill.id")
    private Long sellerbillid;

    @ApiModelProperty(notes = "关联salercapital.id")
    private Long salercapitalid;

    @ApiModelProperty(notes = "交易号")
    private String tradeno;

    @ApiModelProperty(notes = "违约金额")
    private BigDecimal breakmoney;

    @ApiModelProperty(notes = "买家名称")
    private String buyername;

    @ApiModelProperty(notes = "违约时间")
    private Date breaktime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSellerbillid() {
        return sellerbillid;
    }

    public void setSellerbillid(Long sellerbillid) {
        this.sellerbillid = sellerbillid;
    }

    public Long getSalercapitalid() {
        return salercapitalid;
    }

    public void setSalercapitalid(Long salercapitalid) {
        this.salercapitalid = salercapitalid;
    }

    public String getTradeno() {
        return tradeno;
    }

    public void setTradeno(String tradeno) {
        this.tradeno = tradeno == null ? null : tradeno.trim();
    }

    public BigDecimal getBreakmoney() {
        return breakmoney;
    }

    public void setBreakmoney(BigDecimal breakmoney) {
        this.breakmoney = breakmoney;
    }

    public String getBuyername() {
        return buyername;
    }

    public void setBuyername(String buyername) {
        this.buyername = buyername == null ? null : buyername.trim();
    }

    public Date getBreaktime() {
        return breaktime;
    }

    public void setBreaktime(Date breaktime) {
        this.breaktime = breaktime;
    }
}