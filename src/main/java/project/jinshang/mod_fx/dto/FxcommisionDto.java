package project.jinshang.mod_fx.dto;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

public class FxcommisionDto {

    @ApiModelProperty("我的累计返佣")
    private BigDecimal countcommisionprice;

    @ApiModelProperty("我的待到账返佣")
    private BigDecimal waitcommisionprice;

    @ApiModelProperty("所有待到账返佣")
    private BigDecimal allwaitcommisionPrice;

    @ApiModelProperty("所有累计返佣")
    private BigDecimal allcountcommisionprice;


    public BigDecimal getCountcommisionprice() {
        return countcommisionprice;
    }

    public void setCountcommisionprice(BigDecimal countcommisionprice) {
        this.countcommisionprice = countcommisionprice;
    }

    public BigDecimal getWaitcommisionprice() {
        return waitcommisionprice;
    }

    public void setWaitcommisionprice(BigDecimal waitcommisionprice) {
        this.waitcommisionprice = waitcommisionprice;
    }

    public BigDecimal getAllwaitcommisionPrice() {
        return allwaitcommisionPrice;
    }

    public void setAllwaitcommisionPrice(BigDecimal allwaitcommisionPrice) {
        this.allwaitcommisionPrice = allwaitcommisionPrice;
    }

    public BigDecimal getAllcountcommisionprice() {
        return allcountcommisionprice;
    }

    public void setAllcountcommisionprice(BigDecimal allcountcommisionprice) {
        this.allcountcommisionprice = allcountcommisionprice;
    }
}
