package project.jinshang.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import project.jinshang.mod_product.ShippingTemplateGroupMapper;
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
 * productCategoryTest
 *
 * @author xiazy
 * @create 2018-07-20 18:22
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryTest {
    @Autowired
    private ProdShipTempMapper prodShipTempMapper;
    @Autowired
    private ShippingTemplateGroupMapper shippingTemplateGroupMapper;
    private ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

    @Test
    public void repairData(){
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
