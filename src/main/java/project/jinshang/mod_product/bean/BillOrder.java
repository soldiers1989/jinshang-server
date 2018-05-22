package project.jinshang.mod_product.bean;

public class BillOrder {
    private Long id;

    private Long billrecordid;

    private Long orderid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBillrecordid() {
        return billrecordid;
    }

    public void setBillrecordid(Long billrecordid) {
        this.billrecordid = billrecordid;
    }

    public Long getOrderid() {
        return orderid;
    }

    public void setOrderid(Long orderid) {
        this.orderid = orderid;
    }
}