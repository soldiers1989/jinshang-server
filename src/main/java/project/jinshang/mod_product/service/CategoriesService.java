package project.jinshang.mod_product.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;
import project.jinshang.common.constant.Quantity;
import project.jinshang.common.utils.ProductCategoryUtils;
import project.jinshang.common.utils.StringUtils;
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

    /**
     * 添加产品分类
     * @param categories
     */
    public void addCategory(Categories categories) {
        categoriesMapper.insert(categories);
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


}
