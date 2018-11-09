package project.jinshang.scheduled;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import project.jinshang.common.bean.PageRet;
import project.jinshang.common.constant.DistributeTaskConst;
import project.jinshang.common.constant.Quantity;
import project.jinshang.common.utils.CommonUtils;
import project.jinshang.mod_product.bean.Orders;
import project.jinshang.mod_product.service.OrderStoreStateLogService;
import project.jinshang.scheduled.Bean.DistributeTaskLog;
import project.jinshang.scheduled.mapper.DistributeTaskMapper;
import project.jinshang.scheduled.mapper.OrderStoreStateLogTaskMapper;
import project.jinshang.scheduled.service.DistributeTaskLogService;

import java.io.IOException;
import java.util.List;

@Component
@Profile("pro")
public class OrderStoreStateLogTask {

    @Autowired
    private OrderStoreStateLogTaskMapper orderStoreStateLogTaskMapper;

    @Autowired
    private OrderStoreStateLogService orderStoreStateLogService;

    @Autowired
    private DistributeTaskMapper distributeTaskMapper;
    @Autowired
    private DistributeTaskLogService distributeTaskLogService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Scheduled(cron = "0 0/5 8-20 * * ?")
    public void  getOrderStoreStates(){
        if(distributeTaskMapper.start(DistributeTaskConst.ORDER_SEND_GOODS_STATES) !=1){
            return;
        }

        DistributeTaskLog taskLog  = new DistributeTaskLog();
        taskLog.setHostip(CommonUtils.getServerIP());
        taskLog.setHostname(CommonUtils.getServerHost());
        taskLog.setTaskcode(DistributeTaskConst.ORDER_SEND_GOODS_STATES);

        try {
            List<Orders> list =  orderStoreStateLogTaskMapper.getOrders();

            list.forEach(orders -> {
                try {
                    orderStoreStateLogService.getStoreState(orders);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("同步订单发货状态",e);
            taskLog.setState(Quantity.STATE_2);
            taskLog.setError(e.toString());
        } finally {
            taskLog.setState(Quantity.STATE_1);
            distributeTaskMapper.end(DistributeTaskConst.ORDER_SEND_GOODS_STATES);
        }
        distributeTaskLogService.insert(taskLog);
    }

}



