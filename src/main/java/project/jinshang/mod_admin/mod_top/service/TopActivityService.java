package project.jinshang.mod_admin.mod_top.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.common.utils.StringUtils;
import project.jinshang.mod_activity.LimitTimeProdMapper;
import project.jinshang.mod_activity.bean.LimitTimeProd;
import project.jinshang.mod_activity.service.LimitTimeProdService;
import project.jinshang.mod_admin.mod_top.ActivityTypeMapper;
import project.jinshang.mod_admin.mod_top.TopActivityMapper;
import project.jinshang.mod_admin.mod_top.TopActivityProductMapper;
import project.jinshang.mod_admin.mod_top.bean.*;
import project.jinshang.mod_product.ProductInfoMapper;
import project.jinshang.mod_product.bean.ProductAttr;
import project.jinshang.mod_product.service.ProductAttrService;

import java.util.List;

@Service
public class TopActivityService {

    @Autowired
    private TopActivityMapper topActivityMapper;

    @Autowired
    private TopActivityProductMapper topActivityProductMapper;

    @Autowired
    private ProductInfoMapper productInfoMapper;

    @Autowired
    private ActivityTypeMapper activityTypeMapper;

    @Autowired
    private ProductAttrService productAttrService;

    @Autowired
    private LimitTimeProdMapper limitTimeProdMapper;

    @Autowired
    private LimitTimeProdService limitTimeProdService;

    public LimitTimeProd getLimitTimeProdByID(Long id){
        return limitTimeProdMapper.selectByPrimaryKey(id);
    }

    /**
     * 保存活动
     * @param topActivity
     */
    public void saveTopActivity(TopActivity topActivity){
        topActivityMapper.insertSelective(topActivity);
    }

    /**
     * 更新活动
     * @param topActivity
     */
    public void updateTopActivity(TopActivity topActivity){
        topActivityMapper.updateByPrimaryKeySelective(topActivity);
    }

    /**
     * 获取活动列表
     * @param pageNo
     * @param pageSize
     * @param description
     * @return
     */
    public PageInfo getTopActivityList(int pageNo,int pageSize,String description){
        PageHelper.startPage(pageNo,pageSize);
        TopActivityExample topActivityExample = new TopActivityExample();
        TopActivityExample.Criteria criteria = topActivityExample.createCriteria();

        if(StringUtils.hasText(description)){
            description = "%"+description+"%";
            criteria.andDescriptionLike(description);
        }

        PageInfo pageInfo = new PageInfo(topActivityMapper.selectByExample(topActivityExample));
        return  pageInfo;
    }

    /**
     * 获取活动列表
     * @return
     */
    public List<TopActivity> getTopActivityList(){
        return topActivityMapper.selectByExample(new TopActivityExample());
    }

    /**
     * 删除活动
     * @param id
     */
    public void deleteTopActivity(Long id){
        topActivityMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据活动id删除商品
     * @param id
     */
    public void deleteByActivityId(Long id){
        TopActivityProductExample topActivityProductExample = new TopActivityProductExample();
        topActivityProductExample.createCriteria().andTopidEqualTo(id);
        topActivityProductMapper.deleteByExample(topActivityProductExample);
    }

    /**
     * 根据用户id从限时购中搜索商品
     * @param pageNo
     * @param pageSize
     * @param memberid
     * @return
     */
    public PageInfo getProductInfoByMemberId(int pageNo,int pageSize,Long memberid){
        PageHelper.startPage(pageNo,pageSize);
        List<ProductModel> list = topActivityMapper.getProductList(memberid);
        StringBuffer sb = new StringBuffer();
        for(ProductModel productModel:list){
            List<ProductAttr> productAttrs = productAttrService.getListByPidAndPdno(productModel.getPdid(), productModel.getPdno());
            for (ProductAttr attr : productAttrs) {
                sb.append(attr.getValue()).append("*");
            }
            if (productAttrs.size() > 0) {
                sb.deleteCharAt(sb.length() - 1);
            }
            productModel.setValue(sb.toString());
            sb.setLength(0);
        }
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    /**
     * 加载活动商家
     * @return
     */
    public List<ProductModel> getActivityMember(){
        return topActivityMapper.getActivityMember();
    }

    /**
     * 批量保存商品
     * @param list
     */
    public void batchInsertTopActivityProduct(List<TopActivityProduct> list){
        topActivityMapper.insertAll(list);
    }

    /**
     * 获取限时购商品列表
     * @return
     */
    public List<ProductModel> getLimitProduct(Short activitytype,Long topid,Integer befotime){
        List<ProductModel> list = topActivityMapper.getLimitProductList(activitytype,topid,befotime);
        // 如果获取到的商品时间上已经结束，那么 state置为5
        list.forEach(productModel -> {
            if(productModel.getEndtime().getTime()<=System.currentTimeMillis()){
                topActivityMapper.updateLimitTimeProdState(productModel.getLimitid());
                list.remove(productModel);
            }
        });

        list.forEach(productModel -> {
            if(productModel.getState() == 1 && productModel.getBegintime().getTime()>=System.currentTimeMillis()){
                limitTimeProdService.setToStart();
                return;
            }
        });
        return list;
    }


    public List<ProductModel> getLimitProduct(Short activitytype,Long topid){
        List<ProductModel> list = topActivityMapper.getLimitProductList2(activitytype,topid);
        // 如果获取到的商品时间上已经结束，那么 state置为5
        list.forEach(productModel -> {
            if(productModel.getEndtime().getTime()<=System.currentTimeMillis()){
                topActivityMapper.updateLimitTimeProdState(productModel.getLimitid());
            }
        });

        list.forEach(productModel -> {
            if(productModel.getState() == 1 && productModel.getBegintime().getTime()>=System.currentTimeMillis()){
                limitTimeProdService.setToStart();
                return;
            }
        });

        return list;
    }


    /**
     * 批量删除顶部活动商品
     * @param list
     */
    public void deleteAll(List<Long> list){
        topActivityMapper.deleteAll(list);
    }

    public void deleteTopActivityProduct(Long id){
        topActivityProductMapper.deleteByPrimaryKey(id);
    }

    /**
     * 保存活动分类
     * @param activityType
     */
    public void saveActivityType(ActivityType activityType){
        activityTypeMapper.insertSelective(activityType);
    }


    /**
     * 获取活动分类
     * @return
     */
    public List<ActivityType> getActivityType(){
        return  activityTypeMapper.selectByExample(new ActivityTypeExample());
    }

    /**
     * 修改分类
     * @param activityType
     */
    public void updateActivityType(ActivityType activityType){
        activityTypeMapper.updateByPrimaryKeySelective(activityType);
    }

    /**
     * 删除分类
     * @param id
     */
    public void deleteActivityType(Long id){
        activityTypeMapper.deleteByPrimaryKey(id);
    }


    /**
     * 该商品在推荐活动中是否已经存在
     * @param top
     * @return
     */
    public int exisCountTopActivityProduct(TopActivityProduct top){
        TopActivityProductExample example = new TopActivityProductExample();
        TopActivityProductExample.Criteria criteria = example.createCriteria();
        criteria.andActivityidEqualTo(top.getActivityid());
        criteria.andTopidEqualTo(top.getTopid());
        criteria.andActivitytypeEqualTo(top.getActivitytype());
        criteria.andPdidEqualTo(top.getPdid());
        criteria.andPdnoEqualTo(top.getPdno());
        return  topActivityProductMapper.countByExample(example);
    }
}
