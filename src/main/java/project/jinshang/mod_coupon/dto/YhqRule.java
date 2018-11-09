package project.jinshang.mod_coupon.dto;

import java.math.BigDecimal;

/**
 * 优惠券优惠规则
 *
 * @author xiazy
 * @create 2018-08-16 23:31
 **/
public class YhqRule {
    
    public int type;
    public BigDecimal rule1;
    public BigDecimal rule2;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public BigDecimal getRule1() {
        return rule1;
    }

    public void setRule1(BigDecimal rule1) {
        this.rule1 = rule1;
    }

    public BigDecimal getRule2() {
        return rule2;
    }

    public void setRule2(BigDecimal rule2) {
        this.rule2 = rule2;
    }
}
