package project.jinshang.common.bean;

import java.math.BigDecimal;

/**
 * create : wyh
 * date : 2017/11/28
 */
public class Packing {

    private  String unit;
    private BigDecimal num;

    public Packing() {
    }

    public Packing(String unit, BigDecimal num) {
        this.unit = unit;
        this.num = num;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public BigDecimal getNum() {
        return num;
    }

    public void setNum(BigDecimal num) {
        this.num = num;
    }


    @Override
    public String toString() {
        return "Packing{" +
                "unit='" + unit + '\'' +
                ", num=" + num +
                '}';
    }
}
