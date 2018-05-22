package project.jinshang.mod_product.bean;

import io.swagger.annotations.ApiModelProperty;

public class BuyerCenterModel {

    @ApiModelProperty(notes = "预定待发")
    private Long num1 = 0l;
    @ApiModelProperty(notes = "待付款")
    private Long num2 = 0l;
    @ApiModelProperty(notes = "待发货")
    private Long num3 = 0l;
    @ApiModelProperty(notes = "待收货")
    private Long num4 = 0l;
    @ApiModelProperty(notes = "待验货")
    private Long num5 = 0l;
    @ApiModelProperty(notes = "交易成功")
    private Long num6 = 0l;
    @ApiModelProperty(notes = "退货中")
    private Long num7 = 0l;
    @ApiModelProperty(notes = "退货验收")
    private Long num8 = 0l;
    @ApiModelProperty(notes = "异议中")
    private Long num9 = 0l;
    @ApiModelProperty(notes = "交易关闭")
    private Long num10 = 0l;
    @ApiModelProperty(notes = "所有订单")
    private Integer num11 = 0;

    public Long getNum1() {
        return num1;
    }

    public void setNum1(Long num1) {
        this.num1 = num1;
    }

    public Long getNum2() {
        return num2;
    }

    public void setNum2(Long num2) {
        this.num2 = num2;
    }

    public Long getNum3() {
        return num3;
    }

    public void setNum3(Long num3) {
        this.num3 = num3;
    }

    public Long getNum4() {
        return num4;
    }

    public void setNum4(Long num4) {
        this.num4 = num4;
    }

    public Long getNum5() {
        return num5;
    }

    public void setNum5(Long num5) {
        this.num5 = num5;
    }

    public Long getNum6() {
        return num6;
    }

    public void setNum6(Long num6) {
        this.num6 = num6;
    }

    public Long getNum7() {
        return num7;
    }

    public void setNum7(Long num7) {
        this.num7 = num7;
    }

    public Long getNum8() {
        return num8;
    }

    public void setNum8(Long num8) {
        this.num8 = num8;
    }

    public Long getNum9() {
        return num9;
    }

    public void setNum9(Long num9) {
        this.num9 = num9;
    }

    public Long getNum10() {
        return num10;
    }

    public void setNum10(Long num10) {
        this.num10 = num10;
    }

    public Integer getNum11() {
        return num11;
    }

    public void setNum11(Integer num11) {
        this.num11 = num11;
    }
}
