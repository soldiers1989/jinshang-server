package project.jinshang;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import project.jinshang.common.utils.NlpUtils;
import project.jinshang.mod_admin.mod_top.TopActivityMapper;
import project.jinshang.mod_product.OrderProductMapper;
import project.jinshang.mod_product.OrdersMapper;
import project.jinshang.mod_product.ProductInfoMapper;
import project.jinshang.mod_product.service.ProductSearchService;


/**
 * Created by ycj on 16/5/19.
 * 初始化 配置
 */
//@Profile("dev")
@Component
@Transactional(rollbackFor = Exception.class)
public class InitRunner implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private NlpUtils nlpUtils;
    @Autowired
    private ProductInfoMapper productInfoMapper;
    @Autowired
    private ProductSearchService productSearchService;

    @Autowired
    private OrdersMapper ordersMapper;

    @Override
    public void run(String... args) throws Exception {
        logger.info("init");
//        System.out.println(ordersMapper.selectByPrimaryKey(1127L));
//        ordersMapper.updateLimitTimeProdState(45);
//        System.out.println(orderProductMapper.getOrderProductByInOrderids("D201802271113555875"));
//        ProductInfo info = productInfoMapper.getGroundingById(68,238);
//        productSearchService.saveIndex(info.getId(),info.getProductname(),info.getMark(),
//                info.getBrand(),info.getLevel1(),info.getLevel2(),info.getLevel3(),info.getStand(),info.getMaterial(),info.getCardnum());

//        System.out.println(alipayConfig.getNotify_url());
    }
}
