package project.jinshang.scheduled;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import project.jinshang.mod_product.bean.Orders;
import project.jinshang.mod_product.service.OrderStoreStateLogService;
import project.jinshang.scheduled.mapper.OrderStoreStateLogTaskMapper;

import java.io.IOException;
import java.util.List;

@Component
@Profile("pro")
public class OrderStoreStateLogTask {

    @Autowired
    private OrderStoreStateLogTaskMapper orderStoreStateLogTaskMapper;

    @Autowired
    private OrderStoreStateLogService orderStoreStateLogService;

    @Scheduled(cron = "0 0/5 8-20 * * ?")
    public void  getOrderStoreStates(){
        List<Orders> list =  orderStoreStateLogTaskMapper.getOrders();

        list.forEach(orders -> {
            try {
                orderStoreStateLogService.getStoreState(orders);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}



