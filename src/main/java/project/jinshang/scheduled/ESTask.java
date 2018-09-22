package project.jinshang.scheduled;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import project.jinshang.mod_product.service.ProductSearchService;

@Component
@Profile({"pro"})
public class ESTask {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ProductSearchService productSearchService;

    @Scheduled(cron = "00 00 02 * * ?")
    public void rebuild() throws InterruptedException {
        productSearchService.rebuildIndex();
    }


}
