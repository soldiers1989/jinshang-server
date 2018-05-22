package project.jinshang.mod_product.bean;

import java.math.BigDecimal;

/**
 * 价格区间实体类
 * Created by Administrator on 2017/11/28.
 */
public class StepWisePrice {

    private BigDecimal start;
    private BigDecimal end;
    private BigDecimal rate;

    public BigDecimal getStart() {
        return start;
    }

    public void setStart(BigDecimal start) {
        this.start = start;
    }

    public BigDecimal getEnd() {
        return end;
    }

    public void setEnd(BigDecimal end) {
        this.end = end;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }
}
