package project.jinshang.mod_product.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.mod_product.OrderProductMapper;
import project.jinshang.mod_product.bean.OrderProduct;
import project.jinshang.mod_product.bean.OrderProductExample;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderProductServices {

    @Autowired
    private OrderProductMapper orderProductMapper;

    /**
     * 查询买家已经购买该限时购的数量
     * @param buyerid
     * @param limitid
     * @return
     */
    public BigDecimal getTotalNumByLimitid(Long buyerid,Long limitid){
        return  orderProductMapper.getTotalNumByLimitid(buyerid,limitid);
    }


    /**
     * 根据订单id查询
     * @param orderid
     * @return
     */
    public List<OrderProduct> getByOrderid(Long orderid){
       return  orderProductMapper.getByOrderid(orderid);
    }

    public List<OrderProduct> selectByExample(OrderProductExample example){
        return  orderProductMapper.selectByExample(example);
    }


    public OrderProduct getOrderProductById(long id) {
        return orderProductMapper.selectByPrimaryKey(id);
    }

    public List<OrderProduct> getOrderProductByOrderId(long orderid) {
        return  this.getByOrderid(orderid);
    }

    public List<OrderProduct> getOrderProductByOrderId(long orderid, Long pdid, String pdno) {
        OrderProductExample orderProductExample = new OrderProductExample();
        orderProductExample.createCriteria().andOrderidEqualTo(orderid).andPdidEqualTo(pdid).andPdnoEqualTo(pdno);
        return orderProductMapper.selectByExample(orderProductExample);
    }

    public List<OrderProduct> getOrderProductByOrderId(long orderid, long orderproductid) {
        OrderProductExample orderProductExample = new OrderProductExample();
        orderProductExample.createCriteria().andOrderidEqualTo(orderid).andIdNotEqualTo(orderproductid);
        return orderProductMapper.selectByExample(orderProductExample);
    }

    public List<OrderProduct> getByOrderNo(String orderno) {

        return orderProductMapper.getByOrderNo(orderno);
    }

    public List<OrderProduct> getByOrderNoAndBackStatus(String orderno) {
        return orderProductMapper.getByOrderNoAndBackStatus(orderno);
    }

    public void updateByPrimaryKeySelective(OrderProduct orderProduct){
        orderProductMapper.updateByPrimaryKeySelective(orderProduct);
    }
}
