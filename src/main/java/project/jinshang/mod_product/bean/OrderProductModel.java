package project.jinshang.mod_product.bean;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderProductModel extends OrderProduct{

    @ApiModelProperty(notes = "修改之前的商品的数量")
    private BigDecimal oldProductNum;

    public BigDecimal getOldProductNum() {
        return oldProductNum;
    }

    public void setOldProductNum(BigDecimal oldProductNum) {
        this.oldProductNum = oldProductNum;
    }
}