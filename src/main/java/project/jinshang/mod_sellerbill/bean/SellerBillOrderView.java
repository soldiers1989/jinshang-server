package project.jinshang.mod_sellerbill.bean;

import project.jinshang.mod_product.bean.OrderProduct;

import java.util.List;

public class SellerBillOrderView extends SellerBillOrder{

    private List<OrderProduct> orderProductList;

    public List<OrderProduct> getOrderProductList() {
        return orderProductList;
    }

    public void setOrderProductList(List<OrderProduct> orderProductList) {
        this.orderProductList = orderProductList;
    }
}
