package project.jinshang.mod_product.bean.dto;

import io.swagger.annotations.ApiModelProperty;
import project.jinshang.mod_product.bean.Orders;

/**
 * create : wyh
 * date : 2018/3/10
 */
public class OrdersView extends Orders {
    @ApiModelProperty(notes = "买家公司名称")
    private  String buyercompanyname;
    private String realname;

    public String getRealname() {
        return realname;
    }

    public OrdersView setRealname(String realname) {
        this.realname = realname;
        return this;
    }

    public String getBuyercompanyname() {
        return buyercompanyname;
    }

    public void setBuyercompanyname(String buyercompanyname) {
        this.buyercompanyname = buyercompanyname;
    }
}
