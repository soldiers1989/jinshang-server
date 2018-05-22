package project.jinshang.mod_product.bean.dto;

import mizuki.project.core.restserver.config.BasicRet;
import project.jinshang.mod_product.bean.Orders;

import java.util.List;

public class OrdersRet extends BasicRet{

    public class  OrdersData {
        private List<Orders> ordersList;

        public List<Orders> getOrdersList() {
            return ordersList;
        }

        public void setOrdersList(List<Orders> ordersList) {
            this.ordersList = ordersList;
        }
    }


    private  OrdersData data = new OrdersData();

    public OrdersData getData() {
        return data;
    }

    public void setData(OrdersData data) {
        this.data = data;
    }
}
