package project.jinshang.mod_product.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import mizuki.project.core.restserver.config.BasicRet;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.common.constant.Quantity;
import project.jinshang.common.utils.ProductCategoryUtils;
import project.jinshang.common.utils.StringUtils;
import project.jinshang.mod_common.UpdateProdCategory;
import project.jinshang.mod_member.service.SellerCategoryService;
import project.jinshang.mod_product.CategoriesMapper;
import project.jinshang.mod_product.bean.Categories;
import project.jinshang.mod_product.bean.CategoriesExample;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
    private ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    private static final Logger logger= LoggerFactory.getLogger(CategoriesService.class);

    /**
     * 添加产品分类
     * @param categories
     */
    public void addCategory(Categories categories) {
        categoriesMapper.insert(categories);
        //进行商品新增的同步
//        CategoriesRunnable categoriesRunnable=new CategoriesRunnable(sellerCategoryService,categories,Quantity.STATE_0);
//        Thread thread=new Thread(categoriesRunnable);
//        thread.start();
        cachedThreadPool.execute(() -> {
            sellerCategoryService.synCategories(Quantity.STATE_0,categories);
        });
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
        //进行商品修改的同步
//        CategoriesRunnable categoriesRunnable=new CategoriesRunnable(sellerCategoryService,categories,Quantity.STATE_1);
//        Thread thread=new Thread(categoriesRunnable);
//        thread.start();
        cachedThreadPool.execute(() -> {
            sellerCategoryService.synCategories(Quantity.STATE_1,categories);
        });
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
        //进行商品删除的同步
        Categories categories=new Categories();
        categories.setId(id);
//        CategoriesRunnable categoriesRunnable=new CategoriesRunnable(sellerCategoryService,categories,Quantity.STATE_2);
//        Thread thread=new Thread(categoriesRunnable);
//        thread.start();
        cachedThreadPool.execute(() -> {
            sellerCategoryService.synCategories(Quantity.STATE_2,categories);
        });

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
            return  categories.getBrokeragerate();
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

    public List<Map<String,Object>> processProductCategoryForExport(){
        List<Map<String,Object>> resList = new ArrayList<>();
        List<Categories> list = this.getAll();
        list = ProductCategoryUtils.getChildsManyGroup(list, 0, 1);
        for (Categories categories:list){
            //一级分类
            Map<String,Object> FirstCaterorymap=new HashMap<>();
            FirstCaterorymap.put("一级分类",categories.getName());
            FirstCaterorymap.put("二级分类","");
            FirstCaterorymap.put("三级分类","");
            FirstCaterorymap.put("排序",categories.getSort());
            FirstCaterorymap.put("类型",categories.getCatetype());
            FirstCaterorymap.put("数据库主键",categories.getId());
            resList.add(FirstCaterorymap);
            List<Categories>  list1=categories.getList();
            for (Categories categories1:list1){
                //二级分类
                Map<String,Object> SecondCaterorymap=new HashMap<>();
                SecondCaterorymap.put("一级分类",categories.getName());
                SecondCaterorymap.put("二级分类",categories1.getName());
                SecondCaterorymap.put("三级分类","");
                SecondCaterorymap.put("排序",categories1.getSort());
                SecondCaterorymap.put("类型",categories1.getCatetype());
                SecondCaterorymap.put("数据库主键",categories1.getId());
                resList.add(SecondCaterorymap);

                List<Categories>  list2=categories1.getList();
                for (Categories categories2:list2) {
                    //三级分类
                    Map<String, Object> ThirdCaterorymap = new HashMap<>();
                    ThirdCaterorymap.put("一级分类", categories.getName());
                    ThirdCaterorymap.put("二级分类", categories1.getName());
                    ThirdCaterorymap.put("三级分类", categories2.getName());
                    ThirdCaterorymap.put("排序", categories2.getSort());
                    ThirdCaterorymap.put("类型", categories2.getCatetype());
                    ThirdCaterorymap.put("数据库主键",categories2.getId());
                    resList.add(ThirdCaterorymap);
                }
            }
        }
        return resList;
    }

    public void executeBatchProdCateImport(File file){
        UpdateProdCategory updateProdCategory=new UpdateProdCategory(this,file);
        Thread thread1=new Thread(updateProdCategory);
        thread1.start();
    }
    public BasicRet processProductCategoryForImport(File file) throws Exception{
        BasicRet basicRet=new BasicRet();
        basicRet.setMessage("更新成功");
        basicRet.setResult(BasicRet.SUCCESS);
        Workbook workbook=null;
        List<Categories> categoriesArrayList=new ArrayList<>();
        List<Map<String,Object>> categoryForExport=this.processProductCategoryForExport();
        categoryForExport.stream().forEach(map->{
            map.remove("排序");
        });
        try{
            workbook = WorkbookFactory.create(file);
            //读取默认第一个工作表sheet
            Sheet sheet =  workbook.getSheetAt(0);
            //获取sheet中最后一行行号
            int lastRowNum = sheet.getLastRowNum();

            if(lastRowNum<3){
                throw  new RuntimeException("模版不正确");
            }

            Map<String,Integer> map = new HashMap<>();

            Row titleRow =  sheet.getRow(1);
            int cellNum = titleRow.getLastCellNum();
            for(int i=0;i<cellNum;i++){
                map.put(titleRow.getCell(i).getStringCellValue(),i);
            }
            for (int i=2;i<=lastRowNum;i++){
                Row row=sheet.getRow(i);
                if (row!=null){
                    Categories categories=new Categories();
                    Long id=(long) row.getCell(map.get("数据库主键")).getNumericCellValue();
                    Integer sort=(int) row.getCell(map.get("排序")).getNumericCellValue();
                    String firstgradename=row.getCell(map.get("一级分类")).getStringCellValue();
                    String secondgradename=row.getCell(map.get("二级分类")).getStringCellValue();
                    String thirdgradename=row.getCell(map.get("三级分类")).getStringCellValue();
                    String catetype=row.getCell(map.get("类型")).getStringCellValue();
                    Map<String,Object> catemap=new HashMap<>();
                    catemap.put("一级分类",firstgradename);
                    catemap.put("二级分类",secondgradename);
                    catemap.put("三级分类",thirdgradename);
                    catemap.put("类型",catetype);
                    catemap.put("数据库主键",id);
                    if (categoryForExport.contains(catemap)){
                        categories.setId(id);
                        categories.setSort(sort);
                        categoriesArrayList.add(categories);
                    }else{
                        basicRet.setResult(BasicRet.ERR);
                        String soutstr=String.format("存在不匹配的数据，一级分类为:%s，二级分类为:%s,三级分类为:%s,类型为:%s,主键为:%d,不能进行更新",firstgradename,secondgradename,thirdgradename,catetype,id);
                        System.out.println(soutstr);
                        basicRet.setMessage(soutstr);
                    }
                }
            }
//            if (categoriesArrayList.size()>1000){
//                int length=categoriesArrayList.size();
//                List<Categories> categoriesList1=null;
//                categoriesList1=categoriesArrayList.subList(0,2);
//                categoriesMapper.updateAll(categoriesList1);
////                categoriesList1=categoriesArrayList.subList(1000,length);
////                categoriesMapper.updateAll(categoriesList1);
//            }else {
//                categoriesMapper.updateAll(categoriesArrayList);
//            }
            for (Categories categories:categoriesArrayList){
                categoriesMapper.updateByPrimaryKeySelective(categories);
            }

        }catch (InvalidFormatException e){
            e.printStackTrace();
        }finally {
            if (workbook!=null){
                workbook.close();
            }
            file.delete();
        }
        return basicRet;
    }

}
