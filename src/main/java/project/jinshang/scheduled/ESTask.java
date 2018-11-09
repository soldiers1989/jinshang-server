package project.jinshang.scheduled;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import project.jinshang.common.constant.DistributeTaskConst;
import project.jinshang.common.constant.Quantity;
import project.jinshang.common.utils.CommonUtils;
import project.jinshang.mod_product.service.ProductSearchService;
import project.jinshang.scheduled.Bean.DistributeTaskLog;
import project.jinshang.scheduled.mapper.DistributeTaskMapper;
import project.jinshang.scheduled.service.DistributeTaskLogService;

@Component
//@Profile({"pro"})
public class ESTask {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ProductSearchService productSearchService;

    @Autowired
    private DistributeTaskMapper distributeTaskMapper;
    @Autowired
    private DistributeTaskLogService distributeTaskLogService;

    @Scheduled(cron = "00 00 02 * * ?")
    public void rebuildProductinfo() throws InterruptedException {
        if(distributeTaskMapper.start(DistributeTaskConst.SEARCH_REBUILD_PRODUCTINFO)!=1){
            return;
        }

        DistributeTaskLog taskLog  = new DistributeTaskLog();
        taskLog.setHostip(CommonUtils.getServerIP());
        taskLog.setHostname(CommonUtils.getServerHost());
        taskLog.setTaskcode(DistributeTaskConst.SEARCH_REBUILD_PRODUCTINFO);


        try {
            productSearchService.rebuildIndex();
        } catch (InterruptedException e) {
            logger.error("重建商品索引",e);
            taskLog.setError(e.toString());
            taskLog.setState(Quantity.STATE_2);
        } finally {
            distributeTaskMapper.end(DistributeTaskConst.SEARCH_REBUILD_PRODUCTINFO);
            taskLog.setState(Quantity.STATE_1);
        }

        distributeTaskLogService.insert(taskLog);
    }


}
