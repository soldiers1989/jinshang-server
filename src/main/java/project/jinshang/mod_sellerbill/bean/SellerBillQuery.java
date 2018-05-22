package project.jinshang.mod_sellerbill.bean;

public class SellerBillQuery {

    private  String orderno;

    private  String sellercompanyname;

    private  Long sellerid;

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
}
