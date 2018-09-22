package project.jinshang.mod_member.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.common.constant.Quantity;
import project.jinshang.common.utils.RedisUtils;
import project.jinshang.mod_member.SellerCategoryMapper;
import project.jinshang.mod_member.bean.SellerCategory;
import project.jinshang.mod_member.bean.SellerCategoryExample;
import project.jinshang.mod_member.bean.SellerProductCategory;
import project.jinshang.mod_product.bean.Categories;
import project.jinshang.mod_product.service.CategoriesService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class SellerCategoryService {

    @Autowired
    private SellerCategoryMapper sellerCategoryMapper;
    private ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    @Autowired
    private RedisUtils redisUtils;
    private static final Logger logger= LoggerFactory.getLogger(CategoriesService.class);
    public   static  final  String SELLER_COMPANY_PUBSH_CATEGORY = "getPushCategory:";
    public SellerCategory getSellerCategory(Long id){
        return sellerCategoryMapper.selectByPrimaryKey(id);
    }

    public void updateSellerCategory(SellerCategory sellerCategory){
        sellerCategoryMapper.updateByPrimaryKeySelective(sellerCategory);
    }

    public void insertAll(List<SellerCategory> list){

        if(list.size()>1000){
            List newList = list.subList(0,1000);
            List newList2 = list.subList(1000,list.size());
            sellerCategoryMapper.insertAll(newList);
            sellerCategoryMapper.insertAll(newList2);
        }else {
            sellerCategoryMapper.insertAll(list);
        }
    }

    public List<SellerCategory> getAll(Long sellerid){
        SellerCategoryExample sellerCategoryExample = new SellerCategoryExample();
        sellerCategoryExample.createCriteria().andSelleridEqualTo(sellerid);
        return sellerCategoryMapper.selectByExample(sellerCategoryExample);
    }

    public List<SellerProductCategory>  toProdCate(List<SellerCategory> list){
        List<SellerProductCategory> categoryList = new ArrayList<>();
        for(SellerCategory cate : list){
            SellerProductCategory productCategory = new SellerProductCategory();
            BeanUtils.copyProperties(cate,productCategory);
            categoryList.add(productCategory);

        }
        return  categoryList;
    }


    public List<Long> getSellerIdGroupBy(){
        return sellerCategoryMapper.getSellerIdGroupBy();
    }


    public List<SellerCategory> getSellerCateGoriesByExample(SellerCategoryExample example){
        List<SellerCategory> sellerCategoryList=sellerCategoryMapper.selectByExample(example);
        return  sellerCategoryList;
    }


    public void deleteByCategoryIdAndSellerId(SellerCategoryExample example){
        sellerCategoryMapper.deleteByExample(example);
    }

    /**
     *后台修改产品分类后,同步修改所有的商家的产品分类
     * @author xiazy
     * @date  2018/6/13 11:10
     * @param type 修改的分类 0-新增 1-修改 2-删除
     * @param categories
     * @return void
     */
    public void synCategories(short type,Categories categories){
        List<Long> sellerIdList=this.getSellerIdGroupBy();
        for (Long sellerId:sellerIdList) {
            switch (type){
                case Quantity.STATE_0:
                    synCategoriesForAdd(categories,sellerId);
                    break;
                case Quantity.STATE_1:
                    synCategoriesForUpdate(categories,sellerId);
                    break;
                case Quantity.STATE_2:
                    synCategoriesForDelete(categories,sellerId);
                    break;
                default:;
            }
        }
    }

    /**
     *产品新增分类同步
     * @author xiazy
     * @date  2018/6/13 11:57
     * @param categories
     * @return void
     */
    public void synCategoriesForAdd(Categories categories,Long sellerId){
        long parentid=categories.getParentid();
        //判断新加入的分来在商家产品分类里面是否有上级分类
        SellerCategoryExample example=new SellerCategoryExample();
        SellerCategoryExample.Criteria criteria=example.createCriteria();
        criteria.andCategoryidEqualTo(parentid);
        criteria.andSelleridEqualTo(sellerId);
        List<SellerCategory> sellerCategoryList=this.getSellerCateGoriesByExample(example);
        if (sellerCategoryList!=null&&sellerCategoryList.size()>0){
            List<SellerCategory> list=new ArrayList<SellerCategory>();
            SellerCategory sellerCategory=this.transCategories2SellerCategories(categories,sellerId);
            list.add(sellerCategory);
            this.insertAll(list);
        //清除商家对应的可发布商品列表的redis缓存
        redisUtils.delete(SELLER_COMPANY_PUBSH_CATEGORY+sellerId);
        }else{
            logger.info("卖家编号为{}的商品库中没有{}产品的对应的上级分类，不能插入!",sellerId,categories.getName());
        }

    }

    /**
     *产品修改分类同步
     * @author xiazy
     * @date  2018/6/13 11:57
     * @param categories
     * @return void
     */
    public void synCategoriesForUpdate(Categories categories,Long sellerId){
        long id=categories.getId();
        SellerCategoryExample example=new SellerCategoryExample();
        SellerCategoryExample.Criteria criteria=example.createCriteria();
        criteria.andCategoryidEqualTo(id);
        criteria.andSelleridEqualTo(sellerId);
        List<SellerCategory> sellerCategoryList=this.getSellerCateGoriesByExample(example);
        if (sellerCategoryList!=null&&sellerCategoryList.size()>0){
            SellerCategory sellerCategory=this.transCategories2SellerCategories(categories,sellerId);
//            现阶段的产品分类更新只会更新产品的名称
//            SellerCategory sellerCategory=new SellerCategory();
//            sellerCategory.setName(categories.getName());
            sellerCategory.setId(sellerCategoryList.get(0).getId());
            this.updateSellerCategory(sellerCategory);
            //清除商家对应的可发布商品列表的redis缓存
            redisUtils.delete(SELLER_COMPANY_PUBSH_CATEGORY+sellerId);
        }else{
            logger.info("卖家编号为{}的商品库中没有id为{}对应的商品，无法进行更新",sellerId,id);
        }
    }

    /**
     *产品删除分类同步
     * @author xiazy
     * @date  2018/6/13 11:57
     * @param categories
     * @return void
     */
    public void synCategoriesForDelete(Categories categories,Long sellerId){
        SellerCategoryExample example=new SellerCategoryExample();
        SellerCategoryExample.Criteria criteria=example.createCriteria();
        criteria.andCategoryidEqualTo(categories.getId());
        criteria.andSelleridEqualTo(sellerId);
        this.deleteByCategoryIdAndSellerId(example);
        //清除商家对应的可发布商品列表的redis缓存
        redisUtils.delete(SELLER_COMPANY_PUBSH_CATEGORY+sellerId);
    }


    /**
     *将公共的产品分类转为商家的产品分类
     * @author xiazy
     * @date  2018/6/13 15:52
     * @param categories
     * @param sellerId
     * @return project.jinshang.mod_member.bean.SellerCategory
     */
    public SellerCategory transCategories2SellerCategories(Categories categories,long sellerId){
        SellerCategory sellerCategory=null;
        if (categories!=null){
            sellerCategory=new SellerCategory();
            sellerCategory.setCategoryid(categories.getId());
            sellerCategory.setBrokeragerate(categories.getBrokeragerate());
            sellerCategory.setName(categories.getName());
            sellerCategory.setParentid(categories.getParentid());
            sellerCategory.setTitle(categories.getTitle());
            sellerCategory.setKeywords(categories.getKeywords());
            sellerCategory.setDescription(categories.getDescription());
            sellerCategory.setImg(categories.getImg());
            sellerCategory.setSort(categories.getSort());
            sellerCategory.setSellerid(sellerId);
        }
        return sellerCategory;
    }


    public void insertAllOf(List<SellerCategory> list){
        cachedThreadPool.execute(() -> {
            List current=null;
            if(list.size()>1000){
                int totalSize=list.size();
                int no=totalSize/1000;
                int end=totalSize-no*1000;
//                List newList = list.subList(0,1000);
//                List newList2 = list.subList(1000,list.size());
//                sellerCategoryMapper.insertAll(newList);
//                sellerCategoryMapper.insertAll(newList2);
                for (int i=1;i<no+1;i++){
                    int cursorstart=(i-1)*1000;
                    int cursorend=cursorstart+1000;
                    current=list.subList(cursorstart,cursorend);
                    sellerCategoryMapper.insertAll(current);
                }
                current=list.subList(totalSize-end,totalSize);
                sellerCategoryMapper.insertAll(current);
            }else {
                sellerCategoryMapper.insertAll(list);
            }
        });
    }
}
