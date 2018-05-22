package project.jinshang.mod_product.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import mizuki.project.core.restserver.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.jinshang.common.utils.StringUtils;
import project.jinshang.mod_product.ProductNameMapper;
import project.jinshang.mod_product.bean.Categories;
import project.jinshang.mod_product.bean.ProductInfoExample;
import project.jinshang.mod_product.bean.ProductName;
import project.jinshang.mod_product.bean.ProductNameExample;

import java.util.List;
import java.util.Map;

/**
 * create : wyh
 * date : 2017/11/10
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ProductNameService {

    @Autowired
    private ProductNameMapper productNameMapper;


    @Autowired
    private  AttributetblService attributetblService;


    @Autowired
    private  CategoriesService categoriesService;




    public  void add(ProductName productName){
            productNameMapper.insert(productName);
    }


    /**
     * 先删除该品名关联的属性和属性值，后删除品名
     * @param id
     */
    public  void  delById(long id){
        attributetblService.deleteByProductnameid(id);
        productNameMapper.deleteByPrimaryKey(id);
    }



    public  void  updateById(ProductName productName){
        productNameMapper.updateByPrimaryKey(productName);
    }


    public  void  updateByPrimaryKeySelective(ProductName productName){
        productNameMapper.updateByPrimaryKeySelective(productName);
    }


    public  void  deleteById(long id){
        attributetblService.deleteByProductnameid(id);
        productNameMapper.deleteByPrimaryKey(id);

    }


    public ProductName getById(long id){
        return  productNameMapper.selectByPrimaryKey(id);
    }


    /**
     * 根据分类id和名称查询
     * @param typeid
     * @param name
     * @return
     */
    public  ProductName getByCateidAndName(long typeid,String name){
        return  productNameMapper.getByCateidAndName(typeid,name);
    }


    /**
     * 分页查询
     * @param pageNo
     * @param pageSize
     * @param typeid
     * @param name
     * @param prodno
     * @return
     */
    public PageInfo getListByPage(int pageNo,int pageSize, long typeid,String name,String prodno){

        ProductName productName =new ProductName();
        if (StringUtils.hasText(name)){
            productName.setName(name);
        }else {
            productName.setName(null);
        }

        if (StringUtils.hasText(prodno)) {
            productName.setProdno(prodno);
        }else {
            productName.setProdno(null);
        }

        StringBuilder subCategoryids = new StringBuilder();
        if (typeid !=-1){
           List<Categories> productCategoryList =   categoriesService.getAllSubCategoriesIdByParentid(typeid);
           if(productCategoryList != null && productCategoryList.size()>0){

               for(int i=0;i<productCategoryList.size();i++){
                   Categories category = productCategoryList.get(i);
                   if(i != (productCategoryList.size()-1)){
                       subCategoryids.append(category.getId()).append(",");
                   }else{
                       subCategoryids.append(category.getId());
                   }
               }
           }else{
               subCategoryids.append(typeid);
           }
        }


        PageHelper.startPage(pageNo,pageSize);
        List<ProductName> list = productNameMapper.listProductName(productName, subCategoryids.toString());
        PageInfo pageInfo = new PageInfo(list);
        return  pageInfo;
    }


    /**
     * 按照分类获取品名
     * @param catid
     * @return
     */
    public  List<ProductName> getByCateid(long catid){
        ProductNameExample example =  new ProductNameExample();
        example.createCriteria().andTypeidEqualTo(catid);
        return  productNameMapper.selectByExample(example);
    }


    public  List<ProductName> getByCategoryId(long categoryid){
        ProductNameExample example = new ProductNameExample();
        ProductNameExample.Criteria criteria = example.createCriteria();
        criteria.andTypeidEqualTo(categoryid);
        return  productNameMapper.selectByExample(example);
    }


    public Map<String,Object> getProdnameAndCategor(String prodname, String level2, String level3){
        return  productNameMapper.getProdnameAndCategor(prodname,level2,level3);
    }

}
