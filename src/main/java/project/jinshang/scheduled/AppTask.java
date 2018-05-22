package project.jinshang.scheduled;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import project.jinshang.common.utils.GsonUtils;
import project.jinshang.mod_product.bean.*;
import project.jinshang.mod_product.service.*;

import java.util.List;

@Component
public class AppTask {

    @Autowired
    private OrderProductServices orderProductServices;

    @Autowired
    private OrderProductLogService orderProductLogService;

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private ProductStoreService productStoreService;

    @Autowired
    private ProductAttrService productAttrService;

    //@Scheduled(cron = "0 4 10 * * ?")
    public void run(){
        List<OrderProduct> list = orderProductServices.selectByExample(null);

        for(OrderProduct orderProduct : list){
            OrderProductLog orderProductLog = orderProductLogService.getByOrderproductid(orderProduct.getId());
            if(orderProductLog != null) continue;

            orderProductLog = new OrderProductLog();

            ProductInfo productInfo = productInfoService.getById(orderProduct.getPdid());
            orderProductLog.setProductinfojson(GsonUtils.toJson(productInfo));

            ProductStore productStore = productStoreService.getByPdidAndPdno(orderProduct.getPdid(),orderProduct.getPdno());
            orderProductLog.setProductstorejson(GsonUtils.toJson(productStore));

            List<ProductAttr> attrs = productAttrService.getListByPidAndPdno(orderProduct.getPdid(),orderProduct.getPdno());
            orderProductLog.setProductattrjson(GsonUtils.toJson(attrs));

            orderProductLog.setOrderproductid(orderProduct.getId());

            orderProductLogService.add(orderProductLog);

        }

    }

}
