package project.jinshang.mod_coupon.dto;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 优惠券的查询参数dto
 *
 * @author xiazy
 * @create 2018-08-16 21:52
 **/
public class YhqCheckParamDto {

    @ApiModelProperty("优惠券类型1：满减券2：直减券3：包邮券4：折扣券100：代金券")
    private Long type;

    @ApiModelProperty("订单的时间")
    private Date checkTime;


    @ApiModelProperty("订单的金额")
    private BigDecimal orderAmount;


    @ApiModelProperty("优惠券所属的用户id")
    private Long memberid;
    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Long getMemberid() {
        return memberid;
    }

    public void setMemberid(Long memberid) {
        this.memberid = memberid;
    }
}
