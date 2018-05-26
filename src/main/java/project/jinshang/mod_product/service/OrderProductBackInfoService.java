package project.jinshang.mod_product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.mod_product.bean.OrderProductBackInfo;
import project.jinshang.mod_product.mapper.OrderProductBackInfoMapper;

import java.util.List;

/**
 * Created by Administrator on 2018/5/24.
 */
@Service
public class OrderProductBackInfoService {

    @Autowired
    private OrderProductBackInfoMapper orderProductBackInfoMapper;

    public void addOrderProductBackInfo(OrderProductBackInfo orderProductBackInfo){
        orderProductBackInfoMapper.addOrderProductBackInfo(orderProductBackInfo);
    }

    public List<OrderProductBackInfo> getOrderProductBackByOrderNo(String orderno){
        return orderProductBackInfoMapper.getOrderProductBackByOrderNo(orderno);
    }
}
