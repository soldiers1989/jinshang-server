package project.jinshang.mod_product;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import mizuki.project.core.restserver.config.BasicRet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.jinshang.common.utils.ExcelGen;
import project.jinshang.common.utils.ProductCategoryUtils;
import project.jinshang.mod_product.bean.Categories;
import project.jinshang.mod_product.bean.Products;
import project.jinshang.mod_product.service.CategoriesService;
import project.jinshang.mod_product.service.ProductsService;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * create : wyh
 * date : 2017/11/23
 */

@RestController
@RequestMapping({"/rest/admin/products","/rest/seller/products"})
@Api(tags = "后台、卖家紧固件产品库")
public class ProductsAction {


    @Autowired
    private ProductsService productsService;


    @Autowired
    private CategoriesService categoriesService;

    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ApiOperation("商品库列表搜索")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "商品编号",name = "productno",required = false,dataType = "string",paramType = "query"),
            @ApiImplicitParam(value = "商品名称",name = "productname",required = false,dataType = "string",paramType = "query"),
            @ApiImplicitParam(value = "规格",name = "stand",required = false,dataType = "string",paramType = "query"),
            @ApiImplicitParam(value = "品牌",name = "brand",required = false,dataType = "string",paramType = "query"),
            @ApiImplicitParam(value = "印记",name = "mark",required = false,dataType = "string",paramType = "query"),
            @ApiImplicitParam(value = "分类id",name = "categoryid",required = false,dataType = "int",paramType = "query",defaultValue = "0"),
            @ApiImplicitParam(value = "材质",name = "materialid",required = false,dataType = "int",paramType = "query"),
            @ApiImplicitParam(value = "牌号",name = "cardnumid",required = false,dataType = "int",paramType = "query"),
            @ApiImplicitParam(value = "商品别名",name = "productalias",required = false,dataType = "int",paramType = "query"),

    })
    public ProductsListRet list(
            @RequestParam(required = true ,defaultValue = "1") int pageNo,
            @RequestParam(required = true ,defaultValue = "20") int pageSize,
            @RequestParam(required = false,defaultValue = "") String productno,
            @RequestParam(required = false,defaultValue = "") String productname,
            @RequestParam(required = false,defaultValue = "")String stand,
            @RequestParam(required = false,defaultValue = "")String brand,
            @RequestParam(required = false,defaultValue = "")String mark,
            @RequestParam(required = false,defaultValue = "0") long categoryid,
            @RequestParam(required = false,defaultValue = "0") long materialid,
            @RequestParam(required = false,defaultValue = "0") long cardnumid,
            @RequestParam(required = false,defaultValue = "")String productalias
            ){

        ProductsListRet productsListRet = new ProductsListRet();

        int level =  0;
        if(categoryid >0){
          Categories productCategory =  categoriesService.getCategoryLevel(categoryid);
          if(productCategory != null) {
              level = productCategory.getLevel();
          }else{
              productsListRet.setResult(BasicRet.ERR);
              productsListRet.setMessage("分类不存在");
              return  productsListRet;
          }
        }


        PageInfo pageInfo = productsService.list(productno,productname,stand,brand,mark,categoryid,level,materialid,cardnumid,productalias,pageNo,pageSize);

        productsListRet.setResult(BasicRet.SUCCESS);
        productsListRet.setMessage("查询成功");
        productsListRet.data.pageInfo =  pageInfo;

        return  productsListRet;
    }



    @RequestMapping(value = "/get",method = RequestMethod.POST)
    @ApiOperation("获取商品库信息")
    public  ProductRet  get(@RequestParam(required = true) long id){
        ProductRet productRet = new ProductRet();

        Products products = productsService.getById(id);

        if(products == null){
            productRet.setMessage("未查询到商品库信息");
            productRet.setResult(BasicRet.ERR);
            return  productRet;
        }


        productRet.setMessage("查询成功");
        productRet.setResult(BasicRet.SUCCESS);
        productRet.data.products = products;
        return productRet;
    }



    @RequestMapping(value = "/exportExcellist",method = RequestMethod.GET)
    @ApiOperation("商品库导出excel")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "商品编号",name = "productno",required = false,dataType = "string",paramType = "query"),
            @ApiImplicitParam(value = "商品名称",name = "productname",required = false,dataType = "string",paramType = "query"),
            @ApiImplicitParam(value = "规格",name = "stand",required = false,dataType = "string",paramType = "query"),
            @ApiImplicitParam(value = "品牌",name = "brand",required = false,dataType = "string",paramType = "query"),
            @ApiImplicitParam(value = "印记",name = "mark",required = false,dataType = "string",paramType = "query"),
            @ApiImplicitParam(value = "分类id",name = "categoryid",required = false,dataType = "int",paramType = "query",defaultValue = "0"),
            @ApiImplicitParam(value = "材质",name = "materialid",required = false,dataType = "int",paramType = "query"),
            @ApiImplicitParam(value = "牌号",name = "cardnumid",required = false,dataType = "int",paramType = "query"),
            @ApiImplicitParam(value = "商品别名",name = "productalias",required = false,dataType = "int",paramType = "query"),

    })
    public ResponseEntity<InputStreamResource> exportExcellist(
            @RequestParam(required = false,defaultValue = "") String productno,
            @RequestParam(required = false,defaultValue = "") String productname,
            @RequestParam(required = false,defaultValue = "")String stand,
            @RequestParam(required = false,defaultValue = "")String brand,
            @RequestParam(required = false,defaultValue = "")String mark,
            @RequestParam(required = false,defaultValue = "0") long categoryid,
            @RequestParam(required = false,defaultValue = "0") long materialid,
            @RequestParam(required = false,defaultValue = "0") long cardnumid,
            @RequestParam(required = false,defaultValue = "")String productalias
    ){

        int level =  0;
        if(categoryid >0){
            Categories productCategory =  categoriesService.getCategoryLevel(categoryid);
            if(productCategory != null) {
                level = productCategory.getLevel();
            }else{
               throw  new RuntimeException("分类不存在");
            }
        }


        List<Products> resList  = productsService.listForExcel(productno,productname,stand,brand,mark,categoryid,level,materialid,cardnumid,productalias);
        List<Map<String,Object>> data = new ArrayList<>();
        for(Products p : resList){
            Map<String,Object> map = new HashMap<>();
            map.put("商品编码",p.getProductno());
            map.put("分类",p.getLevel1()+"/"+p.getLevel2()+"/"+p.getLevel3());
            map.put("商品名称",p.getProductname());
            map.put("材质/牌号",p.getMaterial()+"/"+p.getCardnum());

            map.put("规格",p.getStandard());
            map.put("品牌",p.getBrand());
            map.put("印记",p.getMark());
            map.put("计量单位",p.getUnit());
            map.put("包装方式",p.getPackagetype());
            map.put("表面处理",p.getSurfacetreatment());
            map.put("重量(KG)",p.getWeight());

            data.add(map);
        }

        String[] rowTitles = new String[]{"商品编码","分类","商品名称","材质/牌号","规格","品牌","印记","计量单位","印记","包装方式","表面处理","重量(KG)"};
        XSSFWorkbook workbook = null;
        try {
            workbook = ExcelGen.common("紧固件商品库列表",rowTitles,data,null);
            if(workbook!=null){
                ByteArrayOutputStream baos=new   ByteArrayOutputStream();
                workbook.write(baos);
//                System.out.println(baos.toByteArray().length);
                HttpHeaders headers = new HttpHeaders();
                headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
                headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", new String("紧固件商品库列表.xlsx".getBytes(),"iso-8859-1")));
                headers.add("Pragma", "no-cache");
                headers.add("Expires", "0");
                String contentType = "application/vnd.ms-excel";
                return ResponseEntity.ok()
                        .headers(headers).contentType(MediaType.parseMediaType(contentType))
                        .body(new InputStreamResource(new ByteArrayInputStream(baos.toByteArray())));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(workbook != null){
                try {
                    workbook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return  null;
    }




    private  class  ProductRet extends  BasicRet{
        private  class  Data {
            private  Products products;

            public Products getProducts() {
                return products;
            }

            public void setProducts(Products products) {
                this.products = products;
            }
        }

        Data data = new Data();

        public Data getData() {
            return data;
        }

        public void setData(Data data) {
            this.data = data;
        }
    }



    private class ProductsListRet extends BasicRet{
        private  class  Data {
            private PageInfo pageInfo;

            public PageInfo getPageInfo() {
                return pageInfo;
            }

            public void setPageInfo(PageInfo pageInfo) {
                this.pageInfo = pageInfo;
            }
        }
        Data data =  new Data();

        public Data getData() {
            return data;
        }

        public void setData(Data data) {
            this.data = data;
        }
    }


}
