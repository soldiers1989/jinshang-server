package project.jinshang.mod_sellerbill.bean;

import io.swagger.annotations.ApiModelProperty;

public class SellerBillQuery {

    private  String orderno;

    private  String sellercompanyname;

    private  Long sellerid;

    @ApiModelProperty(notes = "1= 订单发票   2=违约金发票")
    private Short type;

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

    public String getSellercompanyname() {
        return sellercompanyname;
    }

    public void setSellercompanyname(String sellercompanyname) {
        this.sellercompanyname = sellercompanyname;
    }

    public Long getSellerid() {
        return sellerid;
    }

    public void setSellerid(Long sellerid) {
        this.sellerid = sellerid;
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }
}
