package project.jinshang.mod_admin.mod_prod;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import mizuki.project.core.restserver.config.BasicRet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.common.constant.Quantity;
import project.jinshang.common.utils.CommonUtils;
import project.jinshang.common.utils.GenerateNo;
import project.jinshang.common.utils.ProductCategoryUtils;
import project.jinshang.common.utils.StringUtils;
import project.jinshang.mod_admin.mod_upload.ProductBatchImport;
import project.jinshang.mod_admin.mod_upload.ProductStoreModel;
import project.jinshang.mod_product.bean.*;
import project.jinshang.mod_product.bean.dto.AttributetblDto1;
import project.jinshang.mod_product.service.*;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

/**
 * create : wyh
 * date : 2017/11/23
 */

@RestController
@RequestMapping("/rest/admin/products/manage")
@SessionAttributes({AppConstant.ADMIN_SESSION_NAME})
@Api(tags = "后台产品库管理")
public class ProductsManageAction {


    @Autowired
    private CategoriesService categoriesService;

    @Autowired
    private CardNumService cardNumService;

    @Autowired
    private BrandService brandService;

    @Autowired
    private MaterialService materialService;

    @Autowired
    private ProductNameService productNameService;

    @Autowired
    private  ProductsService productsService;


    @Autowired
    private  ProductInfoService productInfoService;


    @Autowired
    private ProductBatchImport productBatchImport;


    @Autowired
    private  AttributetblService attributetblService;

    @Autowired
    private  Gson gson;


    @Value("${upload.dir.moduleIcon}")
    private  String uploadPath;

    @RequestMapping(value = "/get",method = RequestMethod.POST)
    @ApiOperation("获取产品库详情")
    public  ProductsRet get(long id){

        ProductsRet productsRet =  new ProductsRet();

        Products products = productsService.getById(id);

        if(products == null){
            productsRet.setMessage("产品库不存在");
            productsRet.setResult(BasicRet.ERR);
        }else{
            productsRet.setResult(BasicRet.SUCCESS);
            productsRet.setMessage("查询成功");
            productsRet.data.products =  products;
        }

        return productsRet;
    }



    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ApiOperation("删除紧固件产品库")
    public  BasicRet delete(long id){
        BasicRet basicRet =  new BasicRet();

        ProductInfoExample example =new ProductInfoExample();
        ProductInfoExample.Criteria criteria = example.createCriteria();
        criteria.andProductidEqualTo(id).andPdstateNotEqualTo(Quantity.STATE_6);
        int count = productInfoService.countByExample(example);
        if(count>0){
            basicRet.setMessage("有其他商品关联此产品库");
            basicRet.setResult(BasicRet.ERR);
            return  basicRet;
        }

        productsService.deleteById(id);
        basicRet.setMessage("删除成功");
        basicRet.setResult(BasicRet.SUCCESS);
        return  basicRet;
    }




    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ApiOperation("修改紧固件产品库")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "一级分类id",name ="level1id",required = true,dataType = "int",paramType = "query"),
            @ApiImplicitParam(value = "二级分类id",name ="level2id",required = true,dataType = "int",paramType = "query"),
            @ApiImplicitParam(value = "三级分类id",name ="level3id",required = true,dataType = "int",paramType = "query"),
            @ApiImplicitParam(value = "品名id",name ="productnameid",required = true,dataType = "int",paramType = "query"),
            @ApiImplicitParam(value = "商品别名",name ="productalias",required = true,dataType = "string",paramType = "query"),
            @ApiImplicitParam(value = "品牌id",name ="brandid",required = true,dataType = "int",paramType = "query"),
            @ApiImplicitParam(value = "材质id",name ="materialid",required = true,dataType = "int",paramType = "query"),
            @ApiImplicitParam(value = "牌号id",name ="cardnumid",required = true,dataType = "int",paramType = "query"),
            @ApiImplicitParam(value = "印记",name ="mark",required = false,dataType = "string",paramType = "query"),
            @ApiImplicitParam(value = "计量单位",name ="unit",required = true,dataType = "string",paramType = "query"),
            @ApiImplicitParam(value = "表面处理",name ="surfacetreatment",required = false,dataType = "string",paramType = "query"),
            @ApiImplicitParam(value = "包装方式",name ="packagetype",required = true,dataType = "string",paramType = "query"),
            @ApiImplicitParam(value = "重量",name ="weight",required = true,dataType = "float",paramType = "query"),
            @ApiImplicitParam(value = "商品描述",name ="pddes",required = false,dataType = "string",paramType = "query"),
            @ApiImplicitParam(value = "属性json，例如：[ { \"attributeid\": 1, \"attribute\": \"尺寸\",\"value\": \"20cm*30cm\"}, { \"attributeid\": 8, \"attribute\": \"牙距\", \"value\": \"20\" }]",name ="attribute",required = true,dataType = "string",paramType = "query"),
    })
    public BasicRet updateProducts(
            @RequestParam(required = true) long id,
            @RequestParam(required = true) long level1id,
            @RequestParam(required = true) long level2id,
            @RequestParam(required = true) long level3id,
            @RequestParam(required = true) long productnameid,
            @RequestParam(required = true) String productalias,
            @RequestParam(required = true) Long brandid,
            @RequestParam(required = true) long materialid,
            @RequestParam(required = true) long cardnumid,
            @RequestParam(required = false,defaultValue = "") String mark,
            @RequestParam(required = true) String  unit,
            @RequestParam(required = false,defaultValue = "") String surfacetreatment,
            @RequestParam(required = true) String packagetype,
            @RequestParam(required = true,defaultValue = "0")BigDecimal weight,
            @RequestParam(required = false,defaultValue = "") String pddes,
            @RequestParam(required = true) String[] pdpicture,  //商品图片
            @RequestParam(required = false) String[] pddrawing,  //商品图纸
            @RequestParam(required = true) String attribute

    ){
        BasicRet basicRet =  new BasicRet();

        Products products =  productsService.getById(id);

        if(products == null){
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("商品库不存在");
            return  basicRet;
        }


        Categories categories =  categoriesService.getById(level1id);
        if(categories != null) {
            products.setLevel1(categories.getName());
            products.setLevel1id(level1id);
        }else{
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("分类不存在");
            return  basicRet;
        }

        categories =  categoriesService.getById(level2id);
        if(categories != null) {
            products.setLevel2(categories.getName());
            products.setLevel2id(level2id);
        }else{
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("分类不存在");
            return  basicRet;
        }

        categories = categoriesService.getById(level3id);
        if(categories != null){
            products.setLevel3(categories.getName());
            products.setLevel3id(level3id);
        }else{
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("分类不存在");
            return  basicRet;
        }

        ProductName productName = productNameService.getById(productnameid);
        if(productName != null){
            products.setProductname(productName.getName());
            products.setProductnameid(productnameid);
        }else{
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("品名不存在");
            return  basicRet;
        }

        products.setProductalias(productalias);

        Brand brand = brandService.getById(brandid);
        if(brand != null){
            products.setBrand(brand.getName());
            products.setBrandid((long)brandid);
        }else{
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("品牌不存在");
            return  basicRet;
        }



        Material material = materialService.getById(materialid);
        if(material != null){
            products.setMaterial(material.getName());
            products.setMaterialid(materialid);
        }else{
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("材质不存在");
            return  basicRet;
        }


        CardNum cardNum =  cardNumService.getById(cardnumid);
        if(cardNum != null){
            products.setCardnum(cardNum.getName());
            products.setCardnumid(cardnumid);
        }else{
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("牌号不存在");
            return  basicRet;
        }


        //规格
        StringBuilder standBuilder = new StringBuilder();
        if(CommonUtils.isGoodJson(attribute)){
            Gson gson = new Gson();
            List<Attribute> retList = gson.fromJson(attribute,
                    new TypeToken<List<Attribute>>() {
                    }.getType());

            /*
            if(retList != null && retList.size()>0){
                retList.sort((Attribute a1,Attribute a2)->a1.getAttributeid().compareTo(a2.getAttributeid()));
                for(int i=0;i<retList.size();i++){
                    Attribute att = retList.get(i);
                    if (att.getValue() != null && !att.getValue().equals("")){
                        standBuilder.append(att.getValue()).append("*");
                    }
                }
            }
            */

            List<AttributetblDto1> attributetblList = attributetblService.getAttributeByProdnameid(productName.getId());

            if(retList.size() != attributetblList.size()){
                basicRet.setResult(BasicRet.ERR);
                basicRet.setMessage("属性json不正确，请联系网站管理员");
                return  basicRet;
            }


//            attributetblList.forEach(attributetblDto1 -> {
//                retList.forEach(attribute1->{
//                    if(attributetblDto1.getId().equals(attribute1.getAttributeid())){
//                        if (attribute1.getValue() != null && !attribute1.getValue().equals("")){
//                            standBuilder.append(attribute1.getValue()).append("*");
//                        }
//                    }
//                });
//            });

            int size= retList.size();
            int i=1;
            for(AttributetblDto1 attributetblDto1 : attributetblList){
                for(Attribute attribute1 : retList){
                    if(attributetblDto1.getId().equals(attribute1.getAttributeid())){
                        if (attribute1.getValue() != null && !attribute1.getValue().equals("")){
                            standBuilder.append(attribute1.getValue());
                        }

                        if(i != retList.size()){
                            standBuilder.append(attributetblDto1.getConnector());
                        }

                        i++;
                    }
                }
            }
        }

        String stand = standBuilder.toString();
//        if(stand.endsWith("*")){
//            stand = stand.substring(0,stand.length()-1);
//        }


        products.setMark(mark);
        products.setUnit(unit);
        products.setSurfacetreatment(surfacetreatment);
        products.setPackagetype(packagetype);
        products.setWeight(weight);
        products.setPddes(pddes);
        products.setPddrawing(pddrawing);
        products.setPdpicture(pdpicture);
        products.setCreatetime(new Date());
        products.setStandard(stand);
        products.setAttribute(attribute);

        //        紧固件|内六角圆柱头螺钉|DIN912|M5*0.8*17|不锈钢304|奥展|A2-70|本色|1.2千/盒、6盒/箱
        products.setProdstr(products.getLevel1()+"|"+products.getLevel2()+"|"+products.getLevel3()+"|"+products.getProductname()+"|"+products.getStandard()
                +"|"+products.getMaterial()+products.getCardnum()+"|"+products.getMark()+"|"+products.getSurfacetreatment()+"|"+products.getPackagetype());


        productsService.update(products);


        //修改图片
        productInfoService.updateImgByProductsno(products);


        basicRet.setMessage("更新成功");
        basicRet.setResult(BasicRet.SUCCESS);
        return basicRet;
    }




    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ApiOperation("添加紧固件产品库")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "一级分类id",name ="level1id",required = true,dataType = "int",paramType = "query"),
            @ApiImplicitParam(value = "二级分类id",name ="level2id",required = true,dataType = "int",paramType = "query"),
            @ApiImplicitParam(value = "三级分类id",name ="level3id",required = true,dataType = "int",paramType = "query"),
            @ApiImplicitParam(value = "品名id",name ="productnameid",required = true,dataType = "int",paramType = "query"),
            @ApiImplicitParam(value = "商品别名",name ="productalias",required = true,dataType = "string",paramType = "query"),
            @ApiImplicitParam(value = "品牌id",name ="brandid",required = true,dataType = "int",paramType = "query"),
            @ApiImplicitParam(value = "材质id",name ="materialid",required = true,dataType = "int",paramType = "query"),
            @ApiImplicitParam(value = "牌号id",name ="cardnumid",required = true,dataType = "int",paramType = "query"),
            @ApiImplicitParam(value = "印记",name ="mark",required = false,dataType = "string",paramType = "query"),
            @ApiImplicitParam(value = "计量单位",name ="unit",required = true,dataType = "string",paramType = "query"),
            @ApiImplicitParam(value = "表面处理",name ="surfacetreatment",required = false,dataType = "string",paramType = "query"),
            @ApiImplicitParam(value = "包装方式",name ="packagetype",required = true,dataType = "string",paramType = "query"),
            @ApiImplicitParam(value = "重量",name ="weight",required = true,dataType = "float",paramType = "query"),
            @ApiImplicitParam(value = "商品描述",name ="pddes",required = false,dataType = "string",paramType = "query"),
            @ApiImplicitParam(value = "属性json，例如：[ { \"attributeid\": 1, \"attribute\": \"尺寸\",\"value\": \"20cm*30cm\"}, { \"attributeid\": 8, \"attribute\": \"牙距\", \"value\": \"20\" }]",name ="attribute",required = true,dataType = "string",paramType = "query"),
    })
    public BasicRet addProducts(
            @RequestParam(required = true) long level1id,
            @RequestParam(required = true) long level2id,
            @RequestParam(required = true) long level3id,
            @RequestParam(required = true) long productnameid,
            @RequestParam(required = true) String productalias,
            @RequestParam(required = true) Long brandid,
            @RequestParam(required = true) long materialid,
            @RequestParam(required = true) long cardnumid,
            @RequestParam(required = false,defaultValue = "") String mark,
            @RequestParam(required = true) String  unit,
            @RequestParam(required = false,defaultValue = "") String surfacetreatment,
            @RequestParam(required = true) String packagetype,
            @RequestParam(required = true,defaultValue = "0")BigDecimal weight,
            @RequestParam(required = false,defaultValue = "") String pddes,
            @RequestParam(required = true) String[] pdpicture,  //商品图片
            @RequestParam(required = false) String[] pddrawing,  //商品图纸
            @RequestParam(required = true) String attribute
            ){
        BasicRet basicRet =  new BasicRet();

        Products products =  new Products();
        Categories categories =  categoriesService.getById(level1id);
        if(categories != null) {
            products.setLevel1(categories.getName());
            products.setLevel1id(level1id);
        }else{
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("分类不存在");
            return  basicRet;
        }

        categories =  categoriesService.getById(level2id);
        if(categories != null) {
            products.setLevel2(categories.getName());
            products.setLevel2id(level2id);
        }else{
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("分类不存在");
            return  basicRet;
        }

        categories = categoriesService.getById(level3id);
        if(categories != null){
            products.setLevel3(categories.getName());
            products.setLevel3id(level3id);
        }else{
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("分类不存在");
            return  basicRet;
        }


        //商品编码
        String productno = GenerateNo.getProductsNo();
        products.setProductno(productno);

        ProductName productName = productNameService.getById(productnameid);
        if(productName != null){
            products.setProductname(productName.getName());
            products.setProductnameid(productnameid);
        }else{
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("品名不存在");
            return  basicRet;
        }

        products.setProductalias(productalias);

        Brand brand = brandService.getById(brandid);
        if(brand != null){
            products.setBrand(brand.getName());
            products.setBrandid((long)brandid);
        }else{
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("品牌不存在");
            return  basicRet;
        }

        Material material = materialService.getById(materialid);
        if(material != null){
            products.setMaterial(material.getName());
            products.setMaterialid(materialid);
        }else{
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("材质不存在");
            return  basicRet;
        }

        CardNum cardNum =  cardNumService.getById(cardnumid);
        if(cardNum != null){
            products.setCardnum(cardNum.getName());
            products.setCardnumid(cardnumid);
        }else{
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("牌号不存在");
            return  basicRet;
        }

        //规格
        StringBuilder standBuilder = new StringBuilder();
        if(CommonUtils.isGoodJson(attribute)){
            Gson gson = new Gson();

            List<Attribute> retList = gson.fromJson(attribute,
                    new TypeToken<List<Attribute>>() {
                    }.getType());

            /*
            if(retList != null && retList.size()>0){
                retList.sort((Attribute a1,Attribute a2)->a1.getAttributeid().compareTo(a2.getAttributeid()));
                for(int i=0;i<retList.size();i++){
                    Attribute att = retList.get(i);
                    if (att.getValue() != null && !att.getValue().equals("")){
                        standBuilder.append(att.getValue()).append("*");
                    }
                }
            }*/


            List<AttributetblDto1> attributetblList = attributetblService.getAttributeByProdnameid(productName.getId());

            if(retList.size() != attributetblList.size()){
                basicRet.setResult(BasicRet.ERR);
                basicRet.setMessage("属性json不正确，请联系网站管理员");
                return  basicRet;
            }


//            attributetblList.forEach(attributetblDto1 -> {
//                retList.forEach(attribute1->{
//                    if(attributetblDto1.getId().equals(attribute1.getAttributeid())){
//                        if (attribute1.getValue() != null && !attribute1.getValue().equals("")){
//                            standBuilder.append(attribute1.getValue()).append("*");
//                        }
//                    }
//                });
//            });

            int size= retList.size();
            int i=1;
            for(AttributetblDto1 attributetblDto1 : attributetblList){
                for(Attribute attribute1 : retList){
                    if(attributetblDto1.getId().equals(attribute1.getAttributeid())){
                        if (attribute1.getValue() != null && !attribute1.getValue().equals("")){
                            standBuilder.append(attribute1.getValue());
                        }

                        if(i != retList.size()){
                            standBuilder.append(attributetblDto1.getConnector());
                        }

                        i++;
                    }
                }
            }
        }

        String stand = standBuilder.toString();
//        if(stand.endsWith("*")){
//            stand = stand.substring(0,stand.length()-1);
//        }

        products.setMark(mark);
        products.setUnit(unit);
        products.setSurfacetreatment(surfacetreatment);
        products.setPackagetype(packagetype);
        products.setWeight(weight);
        products.setPddes(pddes);
        products.setPddrawing(pddrawing);
        products.setPdpicture(pdpicture);
        products.setCreatetime(new Date());
        products.setStandard(stand);
        products.setAttribute(attribute);

//        紧固件|内六角圆柱头螺钉|DIN912|M5*0.8*17|不锈钢304|奥展|A2-70|本色|1.2千/盒、6盒/箱
        products.setProdstr(products.getLevel1()+"|"+products.getLevel2()+"|"+products.getLevel3()+"|"+products.getProductname()+"|"+products.getStandard()
        +"|"+products.getMaterial()+products.getCardnum()+"|"+products.getMark()+"|"+products.getSurfacetreatment()+"|"+products.getPackagetype());



        ProductsExample example = new ProductsExample();
        ProductsExample.Criteria  criteria= example.createCriteria();
        criteria.andLevel1idEqualTo(products.getLevel1id()).andLevel2idEqualTo(products.getLevel2id()).andLevel3idEqualTo(products.getLevel3id());
        criteria.andProductnameidEqualTo(products.getProductnameid()).andBrandidEqualTo(products.getBrandid()).andCardnumidEqualTo(products.getCardnumid());
        criteria.andMaterialidEqualTo(products.getMaterialid()).andMarkEqualTo(products.getMark()).andUnitEqualTo(products.getUnit());
        criteria.andSurfacetreatmentEqualTo(products.getSurfacetreatment()).andPackagetypeEqualTo(products.getPackagetype());
        criteria.andStandardEqualTo(products.getStandard());

        int c =  productsService.countByExample(example);
        if(c>0){
            return  new BasicRet(BasicRet.ERR,"数据库中已存在该数据");
        }



        productsService.insertSelective(products);
        basicRet.setMessage("添加成功");
        basicRet.setResult(BasicRet.SUCCESS);
        return basicRet;
    }




    private  class  ProductsRet extends  BasicRet{
        private class Data {
            private Products products;

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




    @PostMapping("/mould/addProducts")
    @ApiOperation("excel导入紧固件商品库")
    public synchronized BasicRet addProductsByMould(@RequestParam("file") CommonsMultipartFile file) throws Exception {
        if(!file.getOriginalFilename().endsWith(".xlsx") && !file.getOriginalFilename().endsWith(".xls")){
            return  new BasicRet(BasicRet.ERR,"请上传Excel文件");
        }

        String fileName= GenerateNo.getUUID()+file.getOriginalFilename();

        File dir =  new File(uploadPath);
        if(!dir.exists()){
            dir.mkdirs();
        }

        File newFile= new File(dir,fileName);
        //通过CommonsMultipartFile的方法直接写文件（注意这个时候）

        List<Products> productsList = new ArrayList<>();

        try {
            file.transferTo(newFile);
            List<ProductStoreModel> list =  productBatchImport.excelProductTo(newFile.getAbsolutePath());

            int i=2;
            for(ProductStoreModel psm : list){

                Products products = new Products();
                String level[] =  psm.getLevel().trim().split("/");
                if(level.length <3) continue;

                String level1 = level[0].trim();
                String level2 = level[1].trim();
                String level3 =  level[2].trim();
                if(level.length==4){
                    level3 = level3+"/"+level[3].trim();
                }

                Map<String,Object> prodnameCate = productNameService.getProdnameAndCategor(psm.getProductname().trim(),level2,level3);

                if(prodnameCate == null){
                   //return  new BasicRet(BasicRet.ERR,psm.getProductname()+"信息错误");
                    throw  new Exception("第"+i+"行，品名为"+psm.getProductname()+"的品名不存在");
                }

                products.setLevel1(prodnameCate.get("c3").toString());
                products.setLevel1id((Long) prodnameCate.get("level1id"));
                products.setLevel2(prodnameCate.get("c2").toString());
                products.setLevel2id((Long) prodnameCate.get("level2id"));
                products.setLevel3(prodnameCate.get("c1").toString());
                products.setLevel3id((Long) prodnameCate.get("level3id"));
                products.setProductname(prodnameCate.get("name").toString());
                products.setProductnameid((Long) prodnameCate.get("id"));


                //商品编码
                String productno = GenerateNo.getProductsNo();
                products.setProductno(productno);

                ProductName productName = productNameService.getByCateidAndName(products.getLevel3id(),  psm.getProductname().trim());
                if(productName == null){
                   // return  new BasicRet(BasicRet.ERR,psm.getProductname()+"的商品名称不存在");

                    throw  new Exception("第"+i+"行，品名为"+psm.getProductname()+"的商品名称不存在");
                }

                products.setProductnameid(productName.getId());
                products.setProductname(productName.getName());
                products.setProductalias(psm.getAlias());


                List<Categories> categoryList  =  categoriesService.getAll();


                Brand brand =  brandService.getByName(psm.getBrand());
                if(brand == null){
                    throw  new Exception("第"+i+"行，品名为"+psm.getProductname()+"的商品品牌不存在");
                }

                products.setBrandid(brand.getId());
                products.setBrand(brand.getName());


                Material material =  materialService.getByName(psm.getMaterial());
                if(material == null){
                    throw  new Exception("第"+i+"行，品名为"+psm.getProductname()+"的材质不存在");
                }
                products.setMaterialid(material.getId());
                products.setMaterial(material.getName());

                CardNum cardNum = cardNumService.getByMaterialIdAndName(material.getId(),psm.getCardnum());
                if(cardNum == null){
                    throw  new Exception("第"+i+"行，品名为"+psm.getProductname()+"的牌号不存在");
                }

                products.setCardnumid(cardNum.getId());
                products.setCardnum(cardNum.getName());

                products.setMark(psm.getMark());
                products.setUnit(psm.getUnit());
                products.setSurfacetreatment(psm.getSuffer());
                products.setPackagetype(psm.getPackagetype());
                products.setWeight(new BigDecimal(psm.getWeight()));
                products.setCreatetime(new Date());

                //              公称直径:M10;长度:16;牙距:1.5;
                String attrStr = psm.getAttr().replaceAll(" "," ").replaceAll("；",";").trim();
                List<Attr> prodAttrList = new ArrayList<>();
                String[] attrArr = attrStr.split(";");
                for(String prodAttr : attrArr){
                    if(prodAttr.trim().equals("")) continue;
                    String[] aArr = prodAttr.replaceAll("：",":").split(":");
                    if(aArr.length != 2) continue;
                    Attr attr =  new Attr();
                    attr.setAttribute(aArr[0]);
                    attr.setValue(aArr[1]);
                    prodAttrList.add(attr);
                }

//                [{"attributeid":191,"attribute":"尺寸","value":"M6"},{"attributeid":192,"attribute":"长度","value":"60"}]

                List<AttributetblDto1> attributetblList = attributetblService.getAttributeByProdnameid(products.getProductnameid());

                for(AttributetblDto1 attributetbl : attributetblList){
                    for(Attr attr : prodAttrList){
                        if(attributetbl.getName().equals(attr.getAttribute())){
                            attributetbl.setValue(attr.getValue());
                        }
                    }
                }

                boolean qu = false;
                for(AttributetblDto1 attributetbl : attributetblList){
                    if(attributetbl.getValue() == null){
                        qu = true;
                        break;
                    }
                }

                if(qu){
                    System.out.println("有属性缺失");

                    throw  new Exception(products.getProductname()+"有属性缺失");
//                    continue;
                }


                StringBuilder stand = new StringBuilder();

                List<Attr> attrList = new ArrayList<>();
//                for(AttributetblDto1 attributetbl : attributetblList){
//                    Attr attr = new Attr();
//                    attr.setValue(attributetbl.getValue());
//                    attr.setAttribute(attributetbl.getName());
//                    attr.setAttributeid(attributetbl.getId());
//                    attrList.add(attr);
//                    stand.append(attributetbl.getValue()).append("*");
//                }

                int size = attributetblList.size();
                int j=1;
                for(AttributetblDto1 attributetbl : attributetblList){
                    Attr attr = new Attr();
                    attr.setValue(attributetbl.getValue());
                    attr.setAttribute(attributetbl.getName());
                    attr.setAttributeid(attributetbl.getId());
                    attrList.add(attr);

                    stand.append(attributetbl.getValue());
                    if(j != size){
                        stand.append(attributetbl.getConnector());
                    }

                    j++;
                }


                String standStr = stand.toString();
                //products.setStandard(standStr.substring(0,standStr.length()-1));
                products.setStandard(stand.toString());


                products.setAttribute(gson.toJson(attrList));

                products.setProdstr(products.getLevel1()+"|"+products.getLevel2()+"|"+products.getLevel3()+"|"+products.getProductname()+"|"+products.getStandard()
                        +"|"+products.getMaterial()+products.getCardnum()+"|"+products.getMark()+"|"+products.getSurfacetreatment()+"|"+products.getPackagetype());

                productsList.add(products);
            }
        } catch (Exception e) {
            throw  e;
        } finally {
            newFile.delete();
        }


        Set<String> keySet = new HashSet<>();
        for(Products p : productsList){
            keySet.add(p.getProdstr());
        }

        if(keySet.size() != productsList.size()){
            return  new BasicRet(BasicRet.ERR,"存在重复数据");
        }


        for(Products p : productsList){
            ProductsExample example = new ProductsExample();
            ProductsExample.Criteria  criteria= example.createCriteria();
            criteria.andLevel1idEqualTo(p.getLevel1id()).andLevel2idEqualTo(p.getLevel2id()).andLevel3idEqualTo(p.getLevel3id());
            criteria.andProductnameidEqualTo(p.getProductnameid()).andBrandidEqualTo(p.getBrandid()).andCardnumidEqualTo(p.getCardnumid());
            criteria.andMaterialidEqualTo(p.getMaterialid()).andMarkEqualTo(p.getMark()).andUnitEqualTo(p.getUnit());
            criteria.andSurfacetreatmentEqualTo(p.getSurfacetreatment()).andPackagetypeEqualTo(p.getPackagetype());
            criteria.andStandardEqualTo(p.getStandard());

            int c =  productsService.countByExample(example);
            if(c>0){
                return  new BasicRet(BasicRet.ERR,"数据库中已存在该数据");
            }

        }



        if(productsList.size()>0){
            for(Products products : productsList){
                productsService.insertSelective(products);
            }
        }

        return  new BasicRet(BasicRet.SUCCESS,"导入成功");
    }


    private  class  Attr{
        private  Long attributeid;

        private  String attribute;

        private  String value;

        public Long getAttributeid() {
            return attributeid;
        }

        public void setAttributeid(Long attributeid) {
            this.attributeid = attributeid;
        }

        public String getAttribute() {
            return attribute;
        }

        public void setAttribute(String attribute) {
            this.attribute = attribute;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }


    private  class  AttrValue{
        private  long attributeid;
        private  String attribute;
        private  String value;

        public long getAttributeid() {
            return attributeid;
        }

        public void setAttributeid(long attributeid) {
            this.attributeid = attributeid;
        }

        public String getAttribute() {
            return attribute;
        }

        public void setAttribute(String attribute) {
            this.attribute = attribute;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }


//    public static void main(String[] args) {
//        System.out.println(System.currentTimeMillis());
//    }

}
