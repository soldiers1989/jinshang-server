package project.jinshang.scheduled;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import project.jinshang.common.utils.DateUtils;
import project.jinshang.mod_admin.mod_count.bean.ProductView;
import project.jinshang.mod_admin.mod_count.service.ProductViewService;
import project.jinshang.scheduled.util.ProductViewStatistics;

import java.util.Date;
import java.util.Map;
import java.util.Set;

/**
 * create : wyh
 * date : 2018/1/30
 */
@Component
@Transactional(rollbackFor = Exception.class)
public class ProductViewStatisticsTask {

    @Autowired
    private ProductViewService productViewService;


    //每个应用单独统计
    @Scheduled(cron="*/60 * * * * *")
    public void  switchAndGetCollection(){
        Map<Long,Integer> map =  ProductViewStatistics.getandclear();
        Set<Long> keyset = map.keySet();
        int yyyymmdd = new Integer(DateUtils.format(new Date(),"yyyyMMdd"));

        for(Long pdid : keyset){
            ProductView productView = productViewService.selectByPdidAndYyyymmdd(pdid,yyyymmdd);
            if(productView == null){
                productView = new ProductView();
                productView.setPdid(pdid);
                productView.setCount(map.get(pdid));
                productView.setYyyymmdd(yyyymmdd);
                productViewService.insertSelective(productView);
            }else{
                productViewService.addCount(productView.getId(),map.get(pdid));
            }
        }
    }



}
