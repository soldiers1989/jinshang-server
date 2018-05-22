package project.jinshang.mod_product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.mod_product.OrderProductBackMapper;
import project.jinshang.mod_product.bean.OrderProductBack;

import java.util.List;

/**
 * create : wyh
 * date : 2018/3/15
 */
@Service
public class OrderProductBackService {

    @Autowired
    private OrderProductBackMapper orderProductBackMapper;


    /**
     * 查询该订单中所有的退货商品
     * @param orderno
     * @return
     */
    public List<OrderProductBack> getByOrderNo(String orderno ){
        return  orderProductBackMapper.getByOrderNo(orderno);
    }


}
