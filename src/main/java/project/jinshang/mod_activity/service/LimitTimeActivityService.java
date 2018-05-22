package project.jinshang.mod_activity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.jinshang.common.constant.Quantity;
import project.jinshang.mod_activity.bean.LimitTimeProd;
import project.jinshang.mod_activity.bean.LimitTimeStore;
import project.jinshang.mod_product.bean.ProductStore;
import project.jinshang.mod_product.service.ProductStoreService;

import java.util.List;

/**
 * create : wyh
 * date : 2018/1/11
 */

@Service
@Transactional(rollbackFor = Exception.class)
public class LimitTimeActivityService {

    @Autowired
    private  LimitTimeStoreService limitTimeStoreService;

    @Autowired
    private ProductStoreService productStoreService;


    /**
     * 扣库存(从商品库存中扣除库存)
     * @param limitTimeProd
     * @return
     */
    public  String decrLimitTimeStoreNum(LimitTimeProd limitTimeProd){
        List<LimitTimeStore> limitTimeStoreList =  limitTimeStoreService.getStoreListByLimitid(limitTimeProd.getId());
        for(LimitTimeStore limitTimeStore : limitTimeStoreList){
            ProductStore productStore =  productStoreService.getByPdidAndPdno(limitTimeProd.getPdid(),limitTimeStore.getPdno());
            if(productStore == null) return  "库存信息不正确";
            if(productStore.getPdstorenum().compareTo(limitTimeStore.getStorenum()) == Quantity.STATE_){
                return  "实际库存小于活动库存";
            }
        }

        for(LimitTimeStore limitTimeStore : limitTimeStoreList){
            productStoreService.decrStoreNumByPdidAndPdno(limitTimeProd.getPdid(),limitTimeStore.getPdno(),limitTimeStore.getStorenum());
        }

        return  null;
    }



    public  String addLimitTimeStoreNum(LimitTimeProd limitTimeProd){
        List<LimitTimeStore> limitTimeStoreList =  limitTimeStoreService.getStoreListByLimitid(limitTimeProd.getId());
        for(LimitTimeStore limitTimeStore : limitTimeStoreList){
            productStoreService.addStoreNumByPdidAndPdno(limitTimeProd.getPdid(),limitTimeStore.getPdno(),limitTimeStore.getStorenum());
        }
        return  null;
    }




}
