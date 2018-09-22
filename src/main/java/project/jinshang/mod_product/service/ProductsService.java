package project.jinshang.mod_product.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.common.utils.StringUtils;
import project.jinshang.mod_batchprice.bean.ProductQueryParam;
import project.jinshang.mod_product.ProductsMapper;
import project.jinshang.mod_product.bean.Products;
import project.jinshang.mod_product.bean.ProductsExample;

import java.util.List;

/**
 * create : wyh
 * date : 2017/11/23
 */
@Service
public class ProductsService {

    @Autowired
    private ProductsMapper productsMapper;


    public  Products getById(long id){
        return  productsMapper.selectByPrimaryKey(id);
    }


    public  void  add(Products products){
        productsMapper.insert(products);
    }


    public  void  insertSelective(Products products){
        productsMapper.insertSelective(products);
    }

    public  void deleteById(long id){
        productsMapper.deleteByPrimaryKey(id);
    }


    public  void  update(Products products){
        productsMapper.updateByPrimaryKey(products);
    }


    public  void  updateByPrimaryKeySelective(Products products){
        productsMapper.updateByPrimaryKeySelective(products);
    }

    public  int countByExample(ProductsExample example){
        return  productsMapper.countByExample(example);
    }


    public List<Products> selectByExample(ProductsExample example){
        return  productsMapper.selectByExample(example);
    }

    /**
     * 按产品编号查询个数
     * @param prodNo
     * @return
     */
    public int getProdNoCount(String prodNo){
        ProductsExample example = new ProductsExample();
        example.createCriteria().andProductnoEqualTo(prodNo);
        return  productsMapper.countByExample(example);
    }


    public  Products getProdByPdno(String prodNo){
        return  productsMapper.getProdByPdno(prodNo);
    }



    /**
     * 列表搜索
     * @param productno
     * @param productname
     * @param stand
     * @param brand
     * @param mark
     * @param pageNo
     * @param pageSize
     * @return
     */
    public PageInfo list(String productno,String productname, String stand,String brand,String mark,long categoryid,int level,long materialid,long  cardnumid,String productalias,int pageNo,int pageSize){
        PageHelper.startPage(pageNo,pageSize);
        //ProductsExample example = new ProductsExample();
        ProductQueryParam productQueryParam = new ProductQueryParam();
        if(StringUtils.hasText("productname")){
            productQueryParam.setProductName(productname);
        }
        if(StringUtils.hasText("stand")){
            productQueryParam.setStand(stand);
        }
        if(level >0){
            if(level == 1){
                productQueryParam.setLevel1(categoryid);
            }else if(level == 2){
                productQueryParam.setLevel2(categoryid);
            }else if(level == 3){
                productQueryParam.setLevel3(categoryid);
            }
        }
        if(StringUtils.hasText(productno)){
            productQueryParam.setProductno(productno);
        }

        if(StringUtils.hasText(brand)){
            productQueryParam.setBrand(brand);
        }

        if(StringUtils.hasText(mark)){
            productQueryParam.setMark(mark);
        }

        if(materialid>0){
            productQueryParam.setMaterialid(materialid);
        }

        if(cardnumid >0){
            productQueryParam.setCardnumid(cardnumid);
        }

        //example.setOrderByClause(" id desc ");
        //ProductsExample.Criteria criteria =  example.createCriteria();

        /*if(level >0){
            if(level == 1){
                criteria.andLevel1idEqualTo(categoryid);
            }else if(level == 2){
                criteria.andLevel2idEqualTo(categoryid);
            }else if(level == 3){
                criteria.andLevel3idEqualTo(categoryid);
            }
        }

        if(materialid >0){
            criteria.andMaterialidEqualTo(materialid);
        }

        if(cardnumid>0){
            criteria.andCardnumidEqualTo(cardnumid);
        }


        if(StringUtils.hasText(productno)){
            criteria.andProductnoLike("%"+productno+"%");
        }*/

        /*if(StringUtils.hasText(productname)){
            criteria.andProductnameLike("%"+productname+"%");
        }

        if(StringUtils.hasText(stand)){
            criteria.andStandardLike("%"+stand+"%");
        }

        if(StringUtils.hasText(brand)){
            criteria.andBrandLike("%"+brand+"%");
        }

        if(StringUtils.hasText(mark)){
            criteria.andMarkLike("%"+mark+"%");
        }


        if(StringUtils.hasText(productalias)){
            criteria.andProductaliasLike("%"+productalias+"%");
        }
*/

        List<Products> list = productsMapper.selectProductList(productQueryParam);

        PageInfo pageInfo = new PageInfo(list);
        return  pageInfo;
    }






    public List<Products> listForExcel(String productno,String productname, String stand,String brand,String mark,long categoryid,int level,long materialid,long  cardnumid,String productalias){
        ProductsExample example = new ProductsExample();
        example.setOrderByClause(" id asc ");
        ProductsExample.Criteria criteria =  example.createCriteria();

        if(level >0){
            if(level == 1){
                criteria.andLevel1idEqualTo(categoryid);
            }else if(level == 2){
                criteria.andLevel2idEqualTo(categoryid);
            }else if(level == 3){
                criteria.andLevel3idEqualTo(categoryid);
            }
        }

        if(materialid >0){
            criteria.andMaterialidEqualTo(materialid);
        }

        if(cardnumid>0){
            criteria.andCardnumidEqualTo(cardnumid);
        }


        if(StringUtils.hasText(productno)){
            criteria.andProductnoLike("%"+productno+"%");
        }

        if(StringUtils.hasText(productname)){
            criteria.andProductnameLike("%"+productname+"%");
        }

        if(StringUtils.hasText(stand)){
            criteria.andStandardLike("%"+stand+"%");
        }

        if(StringUtils.hasText(brand)){
            criteria.andBrandLike("%"+brand+"%");
        }

        if(StringUtils.hasText(mark)){
            criteria.andMarkLike("%"+mark+"%");
        }

        if(StringUtils.hasText(productalias)){
            criteria.andProductaliasLike("%"+productalias+"%");
        }

        List<Products> list = productsMapper.selectByExample(example);

        return  list;
    }











}
