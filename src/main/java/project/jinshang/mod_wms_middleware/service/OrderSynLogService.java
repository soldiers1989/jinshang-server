package project.jinshang.mod_wms_middleware.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.mod_wms_middleware.bean.OrderSynLog;
import project.jinshang.mod_wms_middleware.mapper.OrderSynLogMapper;

import java.util.List;

@Service
public class OrderSynLogService {

    @Autowired
    private OrderSynLogMapper orderSynLogMapper;


    public void batchAdd(List<OrderSynLog> list){
        orderSynLogMapper.batchAdd(list);
    }

}
