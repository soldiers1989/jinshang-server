package project.jinshang.test;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import project.jinshang.common.bean.Packing;
import project.jinshang.common.utils.CommonUtils;
import project.jinshang.common.utils.JinshangUtils;
import project.jinshang.common.utils.StringUtils;
import project.jinshang.mod_product.bean.*;
import project.jinshang.mod_product.service.ProductInfoService;
import project.jinshang.mod_product.service.ProductStoreService;
import project.jinshang.mod_product.service.ProductsService;

import java.util.List;

/**
 * create : wyh
 * date : 2018/8/14
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductUnit {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private ProductStoreService productStoreService;

    @Test
    public void ProductInfoUnit(){

        ProductInfoExample example = new ProductInfoExample();
        example.setOrderByClause("id desc ");
        int pageNo = 1;
        int pageSize = 800;
        int totalPages = 10;

        while (pageNo < totalPages) {

            PageInfo pageInfo = productInfoService.getByPage(example, pageNo, pageSize);

            List<ProductInfo> list = pageInfo.getList();

            for(ProductInfo info : list){
                //1.5千/盒|6盒/箱
                if(StringUtils.hasText(info.getPackagetype())){
                   List<Packing>  packingList =  JinshangUtils.toCovertPacking(info.getPackagetype());
                   if(packingList.size()>0){
                        String baseUtil = packingList.get(0).getUnit();
                        if(!baseUtil.equals(info.getUnit())){
                            System.out.println(info.getId()+"---"+baseUtil+"----"+info.getUnit());
                            System.out.println(packingList);
                            String packageType = enPackage(packingList,info.getUnit());
                            System.out.println(packageType);

                            ProductInfo uInfo = new ProductInfo();
                            uInfo.setId(info.getId());
                            uInfo.setPackagetype(packageType);
                            productInfoService.updateByPrimaryKeySelective(uInfo);
                        }

                   }
                }


//                List<ProductStore> psList = productStoreService.getByProductid(info.getId());
//                for(ProductStore ps : psList){
//                    if(!ps.getStoreunit().equals(info.getUnit())){
//                        System.out.println(info.getId()+"---"+ps.getStoreunit()+"---"+info.getUnit());
//                    }
//                }


            }

            totalPages = pageInfo.getPages();
            pageNo++;
        }



    }



    @Autowired
    ProductsService productsService;

    @Test
    public void productsUnit(){
        ProductsExample example = new ProductsExample();
        example.setOrderByClause("id desc ");
        int pageNo = 1;
        int pageSize = 800;
        int totalPages = 10;

        while (pageNo<totalPages){
            PageInfo pageInfo = productsService.list(null,null,null,null,null,-1,-1,-1,-1,null,pageNo,pageSize);

            List<Products> list = pageInfo.getList();
            for(Products products : list){
                if(StringUtils.hasText(products.getPackagetype())){
                    List<Packing>  packingList =  JinshangUtils.toCovertPacking(products.getPackagetype());
                    if(packingList.size()>0){
                        String baseUtil = packingList.get(0).getUnit();
                        if(!baseUtil.equals(products.getUnit())){

                            System.out.println(packingList);
                            String packageType = enPackage(packingList,products.getUnit());
                            System.out.println(packageType);

                            Products up = new Products();
                            up.setId(products.getId());
                            up.setPackagetype(packageType);
                            productsService.updateByPrimaryKeySelective(up);
                        }

                    }
                }
            }

            pageNo++;
            totalPages = pageInfo.getPages();
        }

    }


    //1.5千/盒|6盒/箱
    //[Packing{unit='千', num=1.5}, Packing{unit='盒', num=6}, Packing{unit='箱', num=0}]
    private static String enPackage(List<Packing> list,String baseutil){
        String packageStr = "";

        for(int i = 0;i< list.size()-1;i++){

            Packing packing = list.get(i);
            if(i==0) {
                packageStr = packing.getNum() + baseutil;
            }else{
                packageStr += packing.getNum() + packing.getUnit();
            }

            if(i< list.size()-1){
                Packing afterPacking = list.get(i+1);
                packageStr += "/" + afterPacking.getUnit() ;
                if(i != list.size()-2){
                  packageStr += "|";
                }
            }
        }
        //System.out.println(list);
        //System.out.println(packageStr);
        return  packageStr;
    }




    //1.5千/盒|6盒/箱
    public static void main(String[] args) {
        List<Packing> list = JinshangUtils.toCovertPacking("1.5千/盒");
        enPackage(list,"千支");
    }

}
