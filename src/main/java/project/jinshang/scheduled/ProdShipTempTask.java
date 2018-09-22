package project.jinshang.scheduled;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import project.jinshang.common.exception.CashException;
import project.jinshang.mod_product.ShippingTemplateGroupMapper;
import project.jinshang.mod_product.ShippingTemplatesSellerAction;
import project.jinshang.mod_product.bean.ShippingTemplateGroup;
import project.jinshang.scheduled.Bean.CheckBean;
import project.jinshang.scheduled.Bean.ProductStoreInfo;
import project.jinshang.scheduled.mapper.ProdShipTempMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程同步修复产品的运费模板改为运费合集
 *
 * @author xiazy
 * @create 2018-07-19 17:41
 **/
@Component
@Transactional(rollbackFor = Exception.class)
@Profile({"test","pro","dev"})
public class ProdShipTempTask {
    @Autowired
    private ProdShipTempMapper prodShipTempMapper;
    @Autowired
    private ShippingTemplateGroupMapper shippingTemplateGroupMapper;
    private ExecutorService cachedThreadPool = Executors.newCachedThreadPool();



    //本月的21号(周六)的执行一次这个代码
    //@Scheduled(cron = "0 0 4 21 7 ?")
    public void fixProdShipTemp() throws CashException {
        prodShipTempMapper.updateProductInfo();
        List<ProductStoreInfo> pidList=prodShipTempMapper.getPdidByGroup();
        List<CheckBean> checkBeanList=new ArrayList<>();
        Map<String,Long> map=new HashMap<>();
        CheckBean checkBean=null;
        long id=0;
        if (pidList!=null&&pidList.size()>0){
            for (ProductStoreInfo ps:pidList){
                long memberId=ps.getMemberid();
                long freightmode=ps.getFreightmode();
                checkBean=new CheckBean(memberId,freightmode);
                String str=String.valueOf(memberId)+String.valueOf(freightmode);
                if (checkBeanList.contains(checkBean)){
                    id=map.get(str);
                }else{
                    ShippingTemplateGroup sts=new ShippingTemplateGroup();
                    sts.setMemberid(memberId);
                    sts.setSelflifting(false);
                    sts.setSfarrivepay(false);
                    sts.setExpresspay(true);
                    sts.setExpreselflifting(false);
                    sts.setExprehousehoid(false);
                    sts.setExpretemp(freightmode);
                    sts.setExpreselftemp(null);
                    sts.setExprehousetemp(null);
                    sts.setName("运费"+freightmode);
                    shippingTemplateGroupMapper.insertSelective(sts);
                    id=sts.getId();
                    map.put(str,id);
                    checkBeanList.add(checkBean);
                }
                ps.setShippingtemplatesgroup(id);
            }
        }

//        cachedThreadPool.execute(() -> {
            for (ProductStoreInfo sh1:pidList){
                prodShipTempMapper.updateSingle(sh1.getId(),sh1.getShippingtemplatesgroup());
            }
//        });

    }

}
