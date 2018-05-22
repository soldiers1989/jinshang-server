package project.jinshang.scheduled;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.httpclient.NameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import project.jinshang.common.constant.Quantity;
import project.jinshang.common.utils.HttpClientUtils;
import project.jinshang.mod_product.bean.ProductInfo;
import project.jinshang.scheduled.mapper.BaiduSeoMapper;
import project.jinshang.scheduled.util.PushSiteMapUtil;

import java.util.*;


@Component
@Profile("pro")
public class BaiduSeoPost {

    @Autowired
    private BaiduSeoMapper baiduSeoMapper;

    //每天15：00推送
    @Scheduled(cron = "0 0 15 * * ?")
    public void  postTobaidu(){

        int count = 0;
        int has = 50000;
        int totalPages = 1;
        int pageNo = 1;

        while (has>1 && pageNo<=totalPages){
            List<String> urls = new ArrayList<>();

            PageInfo<ProductInfo> pageInfo = getPushSeoProdByPage(pageNo,200);

            for(ProductInfo productInfo : pageInfo.getList()){
                if(productInfo.getType() == Quantity.STATE_0){
                    urls.add("http://www.jinshang9.com/IndustrialDetail/"+productInfo.getId());
                }
            }

            pageNo++;
            totalPages = pageInfo.getPages();

            Map<String,Object> resMap =  PushSiteMapUtil.push(urls);

            if(resMap != null){
              has = ((Double)resMap.get("remain")).intValue();
            }
        }







    }



    private PageInfo<ProductInfo> getPushSeoProdByPage(int pageNo,int pageSize){
        PageHelper.startPage(pageNo,pageSize);
        return  new PageInfo(baiduSeoMapper.getPushSeoProd());
    }


}
