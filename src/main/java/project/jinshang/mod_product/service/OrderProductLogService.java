package project.jinshang.mod_product.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.mod_product.bean.OrderProductLog;
import project.jinshang.mod_product.mapper.OrderProductLogMapper;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderProductLogService {

    @Autowired
    private OrderProductLogMapper orderProductLogMapper;

    public void add(OrderProductLog log){
        orderProductLogMapper.insertSelective(log);
    }

    public void deleteByPrimaryKey(Long id){
        orderProductLogMapper.deleteByPrimaryKey(id);
    }


    public OrderProductLog getByOrderproductid(Long orderproductid){
        return orderProductLogMapper.getByOrderproductid(orderproductid);
    }

    /**
     * 根据orderproductid 查询一组
     * @param orderproductids
     * @return
     */
    public List<OrderProductLog> getProductinfoByOrderproductids(List<Long> orderproductids){
        if(orderproductids == null || orderproductids.size()==0) return  new ArrayList<>();
        return  orderProductLogMapper.getProductinfoByOrderproductids(orderproductids);
    }

}
