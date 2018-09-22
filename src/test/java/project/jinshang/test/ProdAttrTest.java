package project.jinshang.test;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import project.jinshang.common.utils.GsonUtils;
import project.jinshang.mod_product.ProductAttributeAdminAction;
import project.jinshang.mod_product.ProductsMapper;
import project.jinshang.mod_product.bean.Attribute;
import project.jinshang.mod_product.bean.Attributetbl;
import project.jinshang.mod_product.bean.Products;
import project.jinshang.mod_product.bean.ProductsExample;
import project.jinshang.mod_product.bean.dto.AttributetblDto1;
import project.jinshang.mod_product.service.AttributetblService;

import java.util.List;

/**
 * create : wyh
 * date : 2018/9/4
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProdAttrTest {

    @Autowired
    private ProductsMapper productsMapper;

    @Autowired
    private AttributetblService attributetblService;

    /**
     * 修复products 表中的attribute json 数据
     * 修复属性名不一致的问题
     */
    @Test
    public void repairProductsAttribute(){

        int pageNo = 1;
        int pageSize = 200;
        int totalCount = 10;

        while (pageNo < totalCount){
            PageHelper.startPage(pageNo,pageSize);
            ProductsExample example = new ProductsExample();
            example.setOrderByClause("id desc");
            List<Products> list = productsMapper.selectByExample(example);
            PageInfo pageInfo = new PageInfo(list);

            totalCount = pageInfo.getPages();
            pageNo++;

            for(Products products : list){
                String attr = products.getAttribute();
                List<Attribute>  attributes = GsonUtils.toList(attr,Attribute.class);

                List<AttributetblDto1> attributetblList = attributetblService.getAttributeByProdnameid(products.getProductnameid());

                boolean modify = false;

                for(Attribute attribute : attributes){
                    for(AttributetblDto1 attributetblDto1 : attributetblList){
                        if(attribute.getAttributeid().equals(attributetblDto1.getId())){
                            if(!attribute.getAttribute().equals(attributetblDto1.getName())){
                                modify = true;
                                attribute.setAttribute(attributetblDto1.getName());
                            }
                        }
                    }
                }
                if(modify) {
                    Products updateProducts = new Products();
                    updateProducts.setId(products.getId());
                    updateProducts.setAttribute(GsonUtils.toJson(attributes));
                    productsMapper.updateByPrimaryKeySelective(updateProducts);
                }
            }

        }


    }


    @Test
    public void repairProductsYJ(){

        int pageNo = 1;
        int pageSize = 200;
        int totalCount = 10;

        while (pageNo < totalCount){
            PageHelper.startPage(pageNo,pageSize);
            ProductsExample example = new ProductsExample();
            example.setOrderByClause("id desc");
            List<Products> list = productsMapper.selectByExample(example);
            PageInfo pageInfo = new PageInfo(list);

            totalCount = pageInfo.getPages();
            pageNo++;

            for(Products products : list){
                String attr = products.getAttribute();
                List<Attribute>  attributes = GsonUtils.toList(attr,Attribute.class);

                List<AttributetblDto1> attributetblList = attributetblService.getAttributeByProdnameid(products.getProductnameid());

                boolean modify = false;

                for(Attribute attribute : attributes){
                   if(attribute.getAttribute().equalsIgnoreCase("厚度")){
                       System.out.println(attribute.getValue());
                       attribute.setValue(ProductAttributeAdminAction.convertValue(attribute.getValue()));
                       System.out.println(attribute.getValue());
                       modify = true;
                   }
                }
                if(modify) {
                    Products updateProducts = new Products();
                    updateProducts.setId(products.getId());
                    updateProducts.setAttribute(GsonUtils.toJson(attributes));
                    productsMapper.updateByPrimaryKeySelective(updateProducts);
                }
            }

        }


    }




}
