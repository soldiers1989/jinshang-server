package project.jinshang.mod_company.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.common.utils.RedisUtils;
import project.jinshang.mod_product.bean.Categories;

import java.util.List;

/**
 * 卖家公司缓存Service
 * create : wyh
 * date : 2018/3/13
 */

@Service
public class SellerCompanyCacheService {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private  SellerCompanyInfoService sellerCompanyInfoService;

    public   static  final  String SELLER_COMPANY_PUBSH_CATEGORY = "getPushCategory:";

    /**
     * 获取卖家的可发布商品分类
     * @param memberid
     * @return
     */
    public List<Categories> getPushCategory(Long memberid){
        List<Categories> list  = redisUtils.getList(SELLER_COMPANY_PUBSH_CATEGORY+memberid,Categories.class);
        if(list != null){
            return  list;
        }

        //redis 缓存中不存在，查数据库
        list = sellerCompanyInfoService.getPushCategory(memberid);
        redisUtils.setList(SELLER_COMPANY_PUBSH_CATEGORY+memberid,list);
        return  list;
    }


    /**
     * 重新加载卖家的可发布商品分类
     * @param memberid
     * @return
     */
    public  List<Categories> reloadPushCategory(Long memberid){
        //查数据库
        List<Categories>  list = sellerCompanyInfoService.getPushCategory(memberid);
        redisUtils.setList(SELLER_COMPANY_PUBSH_CATEGORY+memberid,list);
        return  list;
    }


}
