package project.jinshang.mod_admin.mod_transet.bean;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

public class TransactionSetting {
    @ApiModelProperty(notes = "未付款超时")
    private BigDecimal unpaidtimeout;

    @ApiModelProperty(notes = "确认收货超时")
    private BigDecimal confirmreceipttimeout;

    @ApiModelProperty(notes = "订单退货期限")
    private BigDecimal orderreturnperiod;

    @ApiModelProperty(notes = "现货销售保证金")
    private BigDecimal spotsalesmargin;

    @ApiModelProperty(notes = "被违约方获取违约金占比")
    private BigDecimal forwardsalesmargin;

    @ApiModelProperty(notes = "违约金占比")
    private BigDecimal getliquidated;;

    @ApiModelProperty(notes = "远程采购保证金")
    private BigDecimal remotepurchasingmargin;

    @ApiModelProperty(notes = "充值金额上限")
    private BigDecimal upperlimitamount;

    @ApiModelProperty(notes = "延期发货1-10天日罚金")
    private BigDecimal delivery1of10;

    @ApiModelProperty(notes = "延期发货11-20天日罚金")
    private BigDecimal delivery11of20;

    @ApiModelProperty(notes = "延期发货21-30天日罚金")
    private BigDecimal delivery21of30;

    @ApiModelProperty(notes = "远程交货日")
    private BigDecimal remotedeliveryday;

    @ApiModelProperty(notes = "限时购提前购买时间")
    private BigDecimal buytimeaheadtime;

    @ApiModelProperty(notes = "限时购未付款超时")
    private BigDecimal timedoutofpayment;

    @ApiModelProperty(notes = "验货期")
    private BigDecimal inspectionperiod;

    @ApiModelProperty(notes = "收货后资金冻结期")
    private BigDecimal periodoffreezing;
    @ApiModelProperty(notes = "冻结金额到货款的天数")
    private BigDecimal freezetotogoodspay;

    @ApiModelProperty(notes = "授信违约金比率")
    private  BigDecimal creditbreakrate;



    public BigDecimal getFreezetotogoodspay() {
        return freezetotogoodspay;
    }

    public void setFreezetotogoodspay(BigDecimal freezetotogoodspay) {
        this.freezetotogoodspay = freezetotogoodspay;
    }

    public BigDecimal getUnpaidtimeout() {
        return unpaidtimeout;
    }

    public void setUnpaidtimeout(BigDecimal unpaidtimeout) {
        this.unpaidtimeout = unpaidtimeout;
    }

    public BigDecimal getConfirmreceipttimeout() {
        return confirmreceipttimeout;
    }

    public void setConfirmreceipttimeout(BigDecimal confirmreceipttimeout) {
        this.confirmreceipttimeout = confirmreceipttimeout;
    }

    public BigDecimal getOrderreturnperiod() {
        return orderreturnperiod;
    }

    public void setOrderreturnperiod(BigDecimal orderreturnperiod) {
        this.orderreturnperiod = orderreturnperiod;
    }

    public BigDecimal getSpotsalesmargin() {
        return spotsalesmargin;
    }

    public void setSpotsalesmargin(BigDecimal spotsalesmargin) {
        this.spotsalesmargin = spotsalesmargin;
    }

    public BigDecimal getForwardsalesmargin() {
        return forwardsalesmargin;
    }

    public void setForwardsalesmargin(BigDecimal forwardsalesmargin) {
        this.forwardsalesmargin = forwardsalesmargin;
    }

    public BigDecimal getGetliquidated() {
        return getliquidated;
    }

    public void setGetliquidated(BigDecimal getliquidated) {
        this.getliquidated = getliquidated;
    }

    public BigDecimal getRemotepurchasingmargin() {
        return remotepurchasingmargin;
    }

    public void setRemotepurchasingmargin(BigDecimal remotepurchasingmargin) {
        this.remotepurchasingmargin = remotepurchasingmargin;
    }

    public BigDecimal getUpperlimitamount() {
        return upperlimitamount;
    }

    public void setUpperlimitamount(BigDecimal upperlimitamount) {
        this.upperlimitamount = upperlimitamount;
    }

    public BigDecimal getDelivery1of10() {
        return delivery1of10;
    }

    public void setDelivery1of10(BigDecimal delivery1of10) {
        this.delivery1of10 = delivery1of10;
    }

    public BigDecimal getDelivery11of20() {
        return delivery11of20;
    }

    public void setDelivery11of20(BigDecimal delivery11of20) {
        this.delivery11of20 = delivery11of20;
    }

    public BigDecimal getDelivery21of30() {
        return delivery21of30;
    }

    public void setDelivery21of30(BigDecimal delivery21of30) {
        this.delivery21of30 = delivery21of30;
    }

    public BigDecimal getRemotedeliveryday() {
        return remotedeliveryday;
    }

    public void setRemotedeliveryday(BigDecimal remotedeliveryday) {
        this.remotedeliveryday = remotedeliveryday;
    }

    public BigDecimal getBuytimeaheadtime() {
        return buytimeaheadtime;
    }

    public void setBuytimeaheadtime(BigDecimal buytimeaheadtime) {
        this.buytimeaheadtime = buytimeaheadtime;
    }

    public BigDecimal getTimedoutofpayment() {
        return timedoutofpayment;
    }

    public void setTimedoutofpayment(BigDecimal timedoutofpayment) {
        this.timedoutofpayment = timedoutofpayment;
    }

    public BigDecimal getInspectionperiod() {
        return inspectionperiod;
    }

    public void setInspectionperiod(BigDecimal inspectionperiod) {
        this.inspectionperiod = inspectionperiod;
    }

    public BigDecimal getPeriodoffreezing() {
        return periodoffreezing;
    }

    public void setPeriodoffreezing(BigDecimal periodoffreezing) {
        this.periodoffreezing = periodoffreezing;
    }

    public BigDecimal getCreditbreakrate() {
        return creditbreakrate;
    }

    public void setCreditbreakrate(BigDecimal creditbreakrate) {
        this.creditbreakrate = creditbreakrate;
    }
}