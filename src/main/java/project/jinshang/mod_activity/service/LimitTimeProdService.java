package project.jinshang.mod_activity.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.common.utils.DateUtils;
import project.jinshang.common.utils.JinshangUtils;
import project.jinshang.common.utils.StringUtils;
import project.jinshang.mod_activity.LimitTimeProdMapper;
import project.jinshang.mod_activity.bean.LimitTimeProd;
import project.jinshang.mod_activity.bean.LimitTimeProdExample;
import project.jinshang.mod_activity.bean.LimitTimeStore;
import project.jinshang.mod_activity.bean.LimitTimeStoreExample;
import project.jinshang.mod_activity.bean.dto.LimitTimeProdQuery;
import project.jinshang.mod_product.bean.ProductAttr;
import project.jinshang.mod_product.bean.ProductStore;
import project.jinshang.mod_product.service.ProductAttrService;
import project.jinshang.mod_product.service.ProductStoreService;

import java.math.BigDecimal;
import java.util.*;

/**
 * create : wyh
 * date : 2018/1/10
 */
@Service
public class LimitTimeProdService {

    @Autowired
    private LimitTimeProdMapper limitTimeProdMapper;


    @Autowired
    private  LimitTimeStoreService limitTimeStoreService;


    @Autowired
    private ProductStoreService productStoreService;

    @Autowired
    private ProductAttrService productAttrService;

    public  void insertSelective(LimitTimeProd prod){
        limitTimeProdMapper.insertSelective(prod);
    }

    public  void  updateByPrimaryKeySelective(LimitTimeProd prod){
        limitTimeProdMapper.updateByPrimaryKeySelective(prod);
    }

    public  LimitTimeProd getById(Long id){
        return  limitTimeProdMapper.selectByPrimaryKey(id);
    }


    public  int countByExample(LimitTimeProdExample example){
        return  limitTimeProdMapper.countByExample(example);
    }


    public void updateBySort(Long id,Integer sort){
        limitTimeProdMapper.updateBySort(id, sort);
    }



    /**
     * 卖家端列表查询
     * @param query
     * @param pageNo
     * @param pageSize
     * @return
     */
    public PageInfo listBuyPageForSeller(LimitTimeProdQuery query, int pageNo, int pageSize){
        PageHelper.startPage(pageNo,pageSize);

        if(StringUtils.hasText(query.getActivitytitle())){
            query.setActivitytitle("%"+query.getActivitytitle()+"%");
        }

        return  new PageInfo(limitTimeProdMapper.listBuyPageForSeller(query));
    }


    /**
     * 卖家端导出限时购活动excel
     * @param query
     * @return
     */
    public List<Map<String,Object>> listBuyPageForSellerExportExcel(LimitTimeProdQuery query){
        //存放结果
        List<Map<String,Object>> resList = new ArrayList<>();

        if(StringUtils.hasText(query.getActivitytitle())){
            query.setActivitytitle("%"+query.getActivitytitle()+"%");
        }

        List<Map<String,Object>> list = limitTimeProdMapper.listBuyPageForSellerExportExcel(query);

        for(Map<String,Object> beanMap : list){
            //查询活动库存信息
            List<LimitTimeStore> storeList = limitTimeStoreService.getStoreListByLimitid((Long) beanMap.get("id"));
            for(LimitTimeStore store : storeList){
                Map<String,Object> resMap = new HashMap<>();
                resMap.put("活动名称",beanMap.get("activitytitle"));
                resMap.put("商品名称",beanMap.get("productname"));
                resMap.put("品牌",beanMap.get("brand"));
                resMap.put("单位",beanMap.get("unit"));
                resMap.put("商品类目",beanMap.get("category"));
                resMap.put("开始时间", DateUtils.format((Date) beanMap.get("begintime"),"yyyy/MM/dd HH:mm:ss"));
                resMap.put("结束时间",DateUtils.format((Date) beanMap.get("endtime"),"yyyy/MM/dd HH:mm:ss"));
                resMap.put("单个ID购买限制",beanMap.get("buylimit"));
                resMap.put("状态", JinshangUtils.limitProdState((int)beanMap.get("state")));

                resMap.put("原价",store.getOriginalprice());
                resMap.put("限时价",store.getLimitprice());
                resMap.put("活动库存",store.getStorenum());
                resMap.put("销售量",store.getSalesnum());

                ProductStore productStore = productStoreService.getByPdidAndPdno(store.getPdid(),store.getPdno());
                if(productStore != null){
                    resMap.put("商品库存",productStore.getPdstorenum().add(store.getStorenum()));
                }

                StringBuilder attrSb = new StringBuilder();
                List<ProductAttr> productAttrList = productAttrService.getListByPidAndPdno(store.getPdid(),store.getPdno());
                if(productAttrList != null){
                    for(int i=0;i<productAttrList.size();i++){
                        ProductAttr attr = productAttrList.get(i);
                        attrSb.append(attr.getValue());
                        if(i != (productAttrList.size()-1)){
                            attrSb.append("/");
                        }
                    }
                }
                resMap.put("商品属性",attrSb.toString());

                resList.add(resMap);
            }
        }


       return  resList;
    }





    public PageInfo listBuyPageForAdmin(LimitTimeProdQuery query, int pageNo, int pageSize){
        PageHelper.startPage(pageNo,pageSize);

        if(StringUtils.hasText(query.getActivitytitle())){
            query.setActivitytitle("%"+query.getActivitytitle()+"%");
        }

        return  new PageInfo(limitTimeProdMapper.listBuyPageForAdmin(query));
    }


    /**
     * 后台导出限时购活动excel
     * @param query
     * @return
     */
    public List<Map<String,Object>> listBuyPageForAdminExportExcel(LimitTimeProdQuery query){
        //存放结果
        List<Map<String,Object>> resList = new ArrayList<>();


        if(StringUtils.hasText(query.getActivitytitle())){
            query.setActivitytitle("%"+query.getActivitytitle()+"%");
        }
        List<Map<String,Object>> list = limitTimeProdMapper.listBuyPageForAdminExportExcel(query);

        for(Map<String,Object> beanMap : list){
                Map<String,Object> resMap = new HashMap<>();
                resMap.put("活动名称",beanMap.get("activitytitle"));
                resMap.put("卖家",beanMap.get("username"));
                resMap.put("商品名称",beanMap.get("productname"));
                resMap.put("品牌",beanMap.get("brand"));
                resMap.put("单位",beanMap.get("unit"));
                resMap.put("商品类目",beanMap.get("category"));
                resMap.put("开始时间", DateUtils.format((Date) beanMap.get("begintime"),"yyyy/MM/dd HH:mm:ss"));
                resMap.put("结束时间",DateUtils.format((Date) beanMap.get("endtime"),"yyyy/MM/dd HH:mm:ss"));
                resMap.put("单个ID购买限制",beanMap.get("buylimit"));
                resMap.put("状态", JinshangUtils.limitProdState((int)beanMap.get("state")));
                resMap.put("销售量",beanMap.get("salestotalnum"));
                resMap.put("销售金额",beanMap.get("sumsalsmoney"));
                resList.add(resMap);

        }


        return  resList;
    }



    /**
     * 前台查询列表
     * @param query
     * @param befoBuyTime
     * @param pageNo
     * @param pageSize
     * @return
     */
    public  PageInfo listBuyPageForFront(LimitTimeProdQuery query,int befoBuyTime,int pageNo,int pageSize){
        PageHelper.startPage(pageNo,pageSize);

        List<Map> list = limitTimeProdMapper.listBuyPageForFront(query,befoBuyTime);
        long systemTime = System.currentTimeMillis();
        for(Map map : list){
            map.put("systemTime",systemTime);
        }

        return  new PageInfo(list);
    }


    public  void  setToStart(){
        limitTimeProdMapper.setToStart();
    }

    public  void  setToEnd(){
        limitTimeProdMapper.setToEnd();
    }



    public void updateSalestotalnumInDB(Long id, BigDecimal num){
        limitTimeProdMapper.updateSalestotalnumInDB(id,num);
    }


}
