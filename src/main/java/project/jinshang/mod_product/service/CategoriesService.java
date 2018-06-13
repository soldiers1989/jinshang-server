package project.jinshang.mod_product.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.common.constant.Quantity;
import project.jinshang.common.utils.ProductCategoryUtils;
import project.jinshang.common.utils.StringUtils;
import project.jinshang.mod_member.service.SellerCategoryService;
import project.jinshang.mod_product.CategoriesMapper;
import project.jinshang.mod_product.bean.Categories;
import project.jinshang.mod_product.bean.CategoriesExample;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * create : wyh
 * date : 2017/11/8
 */

@Service
public class CategoriesService {

    @Autowired
    private CategoriesMapper categoriesMapper;
    @Autowired
    private SellerCategoryService sellerCategoryService;

    private static final Logger logger= LoggerFactory.getLogger(CategoriesService.class);

    /**
     * 添加产品分类
     * @param categories
     */
    public void addCategory(Categories categories) {
        categoriesMapper.insert(categories);
        CategoriesRunnable categoriesRunnable=new CategoriesRunnable(sellerCategoryService,categories,Quantity.STATE_0);
        Thread thread=new Thread(categoriesRunnable);
        thread.start();
    }


    public Categories getCategoryByName(String name) {
        if (!StringUtils.hasText(name)) {
            return null;
        }
        return categoriesMapper.getCategoryByName(name);
    }


    public Categories getCategoryByNameAndParentid(String name, long parentid) {
        if (!StringUtils.hasText(name)) {
            return null;
        }
        return categoriesMapper.getCategoryByNameAndParentid(name, parentid);
    }


    public void updateByPrimaryKey(Categories categories) {
        categoriesMapper.updateByPrimaryKey(categories);
        CategoriesRunnable categoriesRunnable=new CategoriesRunnable(sellerCategoryService,categories,Quantity.STATE_1);
        Thread thread=new Thread(categoriesRunnable);
        thread.start();
    }


    /**
     * 查询是否已经存在分类名称
     *
     * @param name
     * @return
     */
    public boolean exisName(String name, long parentid) {
        CategoriesExample example = new CategoriesExample();
        example.createCriteria().andNameEqualTo(name).andParentidEqualTo(parentid);
        int count = categoriesMapper.countByExample(example);
        if (count > 0) {
            return true;
        }

        return false;
    }


    public Categories getById(long id) {
        return categoriesMapper.selectByPrimaryKey(id);
    }


    /**
     * 根据父类id 查询有几个子分类
     *
     * @param parentid
     * @return
     */
    public int hasSonCategoryCount(long parentid) {
        return categoriesMapper.hasSonCategoryCount(parentid);
    }

    public void delete(long id) {
        categoriesMapper.deleteByPrimaryKey(id);
        Categories categories=new Categories();
        categories.setId(id);
        CategoriesRunnable categoriesRunnable=new CategoriesRunnable(sellerCategoryService,categories,Quantity.STATE_2);
        Thread thread=new Thread(categoriesRunnable);
        thread.start();
    }


    /**
     * 获取全部
     *
     * @return
     */

    public List<Categories> getAll() {
        return categoriesMapper.getAll();
    }


    /**
     * 获取所有紧固件的分类
     *
     * @return
     */
    public List<Categories> getAllFastener() {
        return categoriesMapper.getAllFastener();
    }


    /**
     * 根据父类id 获取子类（只获取一级）
     *
     * @param parentid
     * @return
     */
    public List<Categories> getCategoryByParentid(long parentid) {
        CategoriesExample example = new CategoriesExample();
        CategoriesExample.Criteria criteria = example.createCriteria();
        example.setOrderByClause(" sort asc ");
        criteria.andParentidEqualTo(parentid);

        return categoriesMapper.selectByExample(example);
    }


    /**
     * 分页查询关于财务的价格利率的字段
     *
     * @param pageNo
     * @param pageSize
     * @param parentid
     * @return
     */
    public PageInfo listFinanceRateSet(int pageNo, int pageSize, int parentid) {
        PageHelper.startPage(pageNo, pageSize);
        List<Categories> list = categoriesMapper.listFinanceRateSet(parentid);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }


    public PageInfo listBusinessRate(int pageNo, int pageSize, int parentid) {
        PageHelper.startPage(pageNo, pageSize);
        List<Categories> list = categoriesMapper.listBusinessRate(parentid);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }


    /**
     * 查询分类的级别
     *
     * @param categoryid
     * @return
     */
    public Categories getCategoryLevel(long categoryid) {
        List<Categories> categoriesList = categoriesMapper.getAll();

        categoriesList = ProductCategoryUtils.getChildsManyGroup(categoriesList, 0, 1);

        SortToList sortToList = new SortToList();
        sortToList.toList(categoriesList);

        List<Categories> list = sortToList.getList();
        if (list != null && list.size() > 0) {
            for (Categories category : list) {
                if (category.getId() == categoryid) {
                    return category;
                }
            }
        }

        return null;
    }


    /**
     * 根据父类id获取所有子类的id
     *
     * @param parentid
     * @return
     */
    public List<Categories> getAllSubCategoriesIdByParentid(long parentid) {
        List<Categories> categoriesList = this.getAll();


        categoriesList = ProductCategoryUtils.getChildsManyGroup(categoriesList, parentid);

        SortToList sortToList = new SortToList();
        sortToList.toList(categoriesList);
        List<Categories> list = sortToList.getList();

        return list;
    }


    /**
     * 根据分类获取佣金比率
     *
     * @param levelid
     * @return
     */
    public BigDecimal getProductBrokeragerate(Long levelid) {
        List<Categories> allCategories = this.getAll();
        return ProductCategoryUtils.getBrokeragerate(allCategories, levelid);
    }


    /**
     * 获取佣金比例
     * @param cateid
     * @return
     */
    public  BigDecimal getBrokerRate(Long cateid){
        Categories categories = this.getById(cateid);

        if (categories == null){
            return  Quantity.BIG_DECIMAL_0;
        }

        if(categories.getBrokeragerate() != null && categories.getBrokeragerate().compareTo(Quantity.BIG_DECIMAL_0) >=0){
            return  categories.getServicesrate();
        }else{
            return  getBrokerRate(categories.getParentid());
        }

    }

    /**
     * 服务费比例
     * @param cateid
     * @return
     */
    public   BigDecimal getServerRate(Long cateid){
        Categories categories = this.getById(cateid);

        if (categories == null){
            return  Quantity.BIG_DECIMAL_0;
        }

        if(categories.getServicesrate() != null && categories.getServicesrate().compareTo(Quantity.BIG_DECIMAL_0) >=0){
            return  categories.getServicesrate();
        }else{
            return  getServerRate(categories.getParentid());
        }
    }



    /**
     * 根据分类获取服务费比率
     * @param levelid
     * @return
     */
    public  BigDecimal getProductServicesrate(Long levelid){
        List<Categories> allCategories = this.getAll();
        return ProductCategoryUtils.getServicesrate(allCategories, levelid);
    }





    private class SortToList {
        private List<Categories> list = new ArrayList<>();

        public void toList(List<Categories> sortList) {
            for (Categories category : sortList) {
                if (category.getList() != null && category.getList().size() > 0) {
                    toList(category.getList());
                }
                list.add(category);
            }
        }

        public List<Categories> getList() {
            return list;
        }
    }


    /**
     * 获取全部(root)
     *
     * @return
     */

    public List<Categories> getRootAll() {
        CategoriesExample example = new CategoriesExample();
        CategoriesExample.Criteria criteria = example.createCriteria();
        criteria.andParentidEqualTo((long) 0);
        return categoriesMapper.selectByExample(example);
    }


    public List<Map<String,Object>> findCategories(){
        return categoriesMapper.findCategories();

    }

//    /**
//     *后台修改产品分类后,同步修改所有的商家的产品分类
//     * @author xiazy
//     * @date  2018/6/13 11:10
//     * @param type 修改的分类 0-新增 1-修改 2-删除
//     * @param categories
//     * @return void
//     */
//    public void synCategories(short type,Categories categories){
//        List<Long> sellerIdList=sellerCategoryService.getSellerIdGroupBy();
//        for (Long sellerId:sellerIdList) {
//            switch (type){
//                case Quantity.STATE_0:
//                    synCategoriesForAdd(categories,sellerId);
//                case Quantity.STATE_1:
//                    synCategoriesForUpdate(categories,sellerId);
//                case Quantity.STATE_2:
//                    synCategoriesForDelete(categories,sellerId);
//            }
//        }
//    }
//
//    /**
//     *产品新增分类同步
//     * @author xiazy
//     * @date  2018/6/13 11:57
//     * @param categories
//     * @return void
//     */
//    public void synCategoriesForAdd(Categories categories,Long sellerId){
//        long parentid=categories.getParentid();
//        //判断新加入的分来在商家产品分类里面是否有上级分类
//        SellerCategoryExample example=new SellerCategoryExample();
//        SellerCategoryExample.Criteria criteria=example.createCriteria();
//        criteria.andCategoryidEqualTo(parentid);
//        criteria.andSelleridEqualTo(sellerId);
//        List<SellerCategory> sellerCategoryList=sellerCategoryService.getSellerCateGoriesByExample(example);
//        if (sellerCategoryList!=null&&sellerCategoryList.size()>0){
//            List<SellerCategory> list=new ArrayList<SellerCategory>();
//            SellerCategory sellerCategory=this.transCategories2SellerCategories(categories,sellerId);
//            list.add(sellerCategory);
//            sellerCategoryService.insertAll(list);
//
//        }else{
//            logger.info("卖家编号为{}的商品库中没有{}产品的对应的上级分类，不能插入!",sellerId,categories.getName());
//        }
//
//    }
//
//    /**
//     *产品修改分类同步
//     * @author xiazy
//     * @date  2018/6/13 11:57
//     * @param categories
//     * @return void
//     */
//    public void synCategoriesForUpdate(Categories categories,Long sellerId){
//        long id=categories.getId();
//        SellerCategoryExample example=new SellerCategoryExample();
//        SellerCategoryExample.Criteria criteria=example.createCriteria();
//        criteria.andCategoryidEqualTo(id);
//        criteria.andSelleridEqualTo(sellerId);
//        List<SellerCategory> sellerCategoryList=sellerCategoryService.getSellerCateGoriesByExample(example);
//        if (sellerCategoryList!=null&&sellerCategoryList.size()>0){
//            SellerCategory sellerCategory=this.transCategories2SellerCategories(categories,sellerId);
////            现阶段的产品分类更新只会更新产品的名称
////            SellerCategory sellerCategory=new SellerCategory();
////            sellerCategory.setName(categories.getName());
//            sellerCategory.setId(sellerCategoryList.get(0).getId());
//            sellerCategoryService.updateSellerCategory(sellerCategory);
//        }else{
//            logger.info("卖家编号为{}的商品库中没有id为{}对应的商品，无法进行更新",sellerId,id);
//        }
//    }
//
//    /**
//     *产品删除分类同步
//     * @author xiazy
//     * @date  2018/6/13 11:57
//     * @param categories
//     * @return void
//     */
//    public void synCategoriesForDelete(Categories categories,Long sellerId){
//        SellerCategoryExample example=new SellerCategoryExample();
//        SellerCategoryExample.Criteria criteria=example.createCriteria();
//        criteria.andCategoryidEqualTo(categories.getId());
//        criteria.andSelleridEqualTo(sellerId);
//        sellerCategoryService.deleteByCategoryIdAndSellerId(example);
//    }
//
//
//    /**
//     *将公共的产品分类转为商家的产品分类
//     * @author xiazy
//     * @date  2018/6/13 15:52
//     * @param categories
//     * @param sellerId
//     * @return project.jinshang.mod_member.bean.SellerCategory
//     */
//    public SellerCategory transCategories2SellerCategories(Categories categories,long sellerId){
//        SellerCategory sellerCategory=null;
//        if (categories!=null){
//            sellerCategory=new SellerCategory();
//            sellerCategory.setCategoryid(categories.getId());
//            sellerCategory.setBrokeragerate(categories.getBrokeragerate());
//            sellerCategory.setName(categories.getName());
//            sellerCategory.setParentid(categories.getParentid());
//            sellerCategory.setTitle(categories.getTitle());
//            sellerCategory.setKeywords(categories.getKeywords());
//            sellerCategory.setDescription(categories.getDescription());
//            sellerCategory.setImg(categories.getImg());
//            sellerCategory.setSort(categories.getSort());
//            sellerCategory.setSellerid(sellerId);
//        }
//        return sellerCategory;
//    }

}
