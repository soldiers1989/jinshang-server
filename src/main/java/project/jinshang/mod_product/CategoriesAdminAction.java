package project.jinshang.mod_product;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import mizuki.project.core.restserver.config.BasicRet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import project.jinshang.common.constant.AdminAuthorityConst;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.common.constant.Quantity;
import project.jinshang.mod_product.bean.Categories;
import project.jinshang.mod_product.bean.ProductInfoExample;
import project.jinshang.mod_product.service.CategoriesRunnable;
import project.jinshang.mod_product.service.CategoriesService;
import project.jinshang.mod_product.service.ProductInfoService;

import java.math.BigDecimal;

/**
 * create : wyh
 * date : 2017/11/8
 */
@RestController
@RequestMapping("/rest/admin/categories")
@Api(tags = "后台产品分类",description = "后台产品分类接口、价格利率设置")
@SessionAttributes(value = AppConstant.ADMIN_SESSION_NAME)
@Transactional(rollbackFor = Exception.class)
public class CategoriesAdminAction {

    @Autowired
    private CategoriesService categoriesService;

    @Autowired
    private ProductInfoService productInfoService;

    @RequestMapping(value = "/get",method = RequestMethod.POST)
    @ApiOperation(value = "获取分类详细信息，包括价格利率信息")
    public  CategoryRet get(@RequestParam(required = true) int id){

        CategoryRet categoryRet =  new CategoryRet();

        Categories categories = categoriesService.getById(id);
        if(categories == null){
            categoryRet.setResult(BasicRet.ERR);
            categoryRet.setMessage("不存在");
            return  categoryRet;
        }

        categoryRet.data =  categories;
        categoryRet.setResult(BasicRet.SUCCESS);
        return  categoryRet;
    }



    private class CategoryRet extends  BasicRet{
        private  Categories data;

        public Categories getData() {
            return data;
        }

        public void setData(Categories data) {
            this.data = data;
        }
    }



    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ApiOperation(value = "添加产品分类")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "名称",name = "name",required = true,dataType = "string",paramType = "query"),
            @ApiImplicitParam(value = "父id",name = "parentid",required = true,dataType = "string",paramType = "query",defaultValue = "0"),
            @ApiImplicitParam(value = "排序",name = "sort",required = true,dataType = "int",paramType = "query"),
            @ApiImplicitParam(value = "标题",name = "title",required = false,dataType = "string",paramType = "query"),
            @ApiImplicitParam(value = "关键字",name = "keywords",required = false,dataType = "string",paramType = "query"),
            @ApiImplicitParam(value = "描述",name = "description",required = false,dataType = "string",paramType = "query"),
            @ApiImplicitParam(value = "图片",name = "img",required = false,dataType = "string",paramType = "query"),
            @ApiImplicitParam(value = "佣金比率",name = "brokeragerate",required = true,dataType = "double",paramType = "query",defaultValue = "0"),
            @ApiImplicitParam(value = "服务费比率",name = "servicesrate",required = true,dataType = "double",paramType = "query",defaultValue = "0"),
            @ApiImplicitParam(value = "类型 紧固件、工业品、生活类 ",name = "catetype",required = true,dataType = "string",paramType = "query",defaultValue = ""),
    })
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.CLASSIFICATIONMANAGEMENT + "')")
    public BasicRet add(@RequestParam(required = true) String name,
                        @RequestParam(defaultValue ="0",required = true) long parentid,
                        @RequestParam(required = true,defaultValue = "0") int sort,
                         String title, String keywords,
                         String description,
                        String img,
                        @RequestParam(required = true,defaultValue = "0") BigDecimal brokeragerate,
                        @RequestParam(required = true,defaultValue = "0") BigDecimal servicesrate,
                        @RequestParam(required = true) String catetype){
        BasicRet basicRet = new BasicRet();


        if(!"紧固件".equals(catetype) && !"工业品".equals(catetype) && !"生活类".equals(catetype)){
            basicRet.setMessage("分类请填写紧固件、工业品、生活类");
            basicRet.setResult(BasicRet.ERR);
            return  basicRet;
        }


        if(parentid == 0 && (brokeragerate.compareTo(new BigDecimal(-1)) == 0)){
            basicRet.setMessage("顶级分类佣金比率不可沿用上级分类的");
            basicRet.setResult(BasicRet.ERR);
            return  basicRet;
        }

        if(parentid == 0 && (servicesrate.compareTo(new BigDecimal(-1)) == 0)){
            basicRet.setMessage("顶级分类服务费比率不可沿用上级分类的");
            basicRet.setResult(BasicRet.ERR);
            return  basicRet;
        }


        if(categoriesService.exisName(name,parentid)){
            basicRet.setMessage("该分类名称已存在，不可重复添加");
            basicRet.setResult(BasicRet.ERR);
            return  basicRet;
        }

        if(parentid != 0) {
            Categories parentCategory = categoriesService.getById(parentid);
            if(parentCategory == null){
                return  new BasicRet(BasicRet.ERR,"父分类不存在");
            }

            if(!parentCategory.getCatetype().equals(catetype)){
                return  new BasicRet(BasicRet.ERR,"子类必须选择跟父类相同的类型");
            }
        }

        Categories categories =  new Categories();
        categories.setName(name);
        categories.setParentid(parentid);
        categories.setSort(sort);
        categories.setKeywords(keywords);
        categories.setTitle(title);
        categories.setDescription(description);
        categories.setImg(img);
        categories.setBrokeragerate(brokeragerate);
        categories.setServicesrate(servicesrate);
        categories.setCatetype(catetype);

        categoriesService.addCategory(categories);
        //进行商品新增的同步
//        CategoriesRunnable categoriesRunnable=new CategoriesRunnable(categoriesService,categories,Quantity.STATE_0);
//        Thread thread=new Thread(categoriesRunnable);
//        thread.start();

        basicRet.setMessage("添加成功");
        basicRet.setResult(BasicRet.SUCCESS);

        return  basicRet;
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ApiOperation(value = "修改产品分类")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "ID",name = "id",required = true,dataType = "int",paramType = "query"),
            @ApiImplicitParam(value = "名称",name = "name",required = true,dataType = "string",paramType = "query"),
            @ApiImplicitParam(value = "排序",name = "sort",required = true,dataType = "int",paramType = "query"),
            @ApiImplicitParam(value = "标题",name = "title",required = false,dataType = "string",paramType = "query"),
            @ApiImplicitParam(value = "关键字",name = "keywords",required = false,dataType = "string",paramType = "query"),
            @ApiImplicitParam(value = "描述",name = "description",required = false,dataType = "string",paramType = "query"),
            @ApiImplicitParam(value = "图片",name = "img",required = false,dataType = "string",paramType = "query"),
            @ApiImplicitParam(value = "佣金比率",name = "brokeragerate",required = true,dataType = "double",paramType = "query",defaultValue = "0"),
            @ApiImplicitParam(value = "服务费比率",name = "servicesrate",required = true,dataType = "double",paramType = "query",defaultValue = "0"),
            @ApiImplicitParam(value = "类型  紧固件、工业品、生活类  ",name = "catetype",required = true,dataType = "string",paramType = "query",defaultValue = ""),
    })
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.CLASSIFICATIONMANAGEMENT + "')")
    public  BasicRet update(@RequestParam(required = true) long id,
                            @RequestParam(required = true) String name,
                            @RequestParam(required = true) int sort,
                            String title, String keywords,
                            String description,
                            String img, @RequestParam(required = true,defaultValue = "0") BigDecimal brokeragerate,
                            @RequestParam(required = true,defaultValue = "0") BigDecimal servicesrate,
                            @RequestParam(required = true) String catetype){

        BasicRet basicRet = new BasicRet();

        if(!"紧固件".equals(catetype) && !"工业品".equals(catetype) && !"生活类".equals(catetype)){
            basicRet.setMessage("分类请填写紧固件、工业品、生活类");
            basicRet.setResult(BasicRet.ERR);
            return  basicRet;
        }

        Categories categories = categoriesService.getById(id);
        if(categories == null ){
            basicRet.setMessage("该分类不存在");
            basicRet.setResult(BasicRet.ERR);
            return  basicRet;
        }


        if(categories.getParentid() == 0 && (brokeragerate.compareTo(new BigDecimal(-1)) == 0)){
            basicRet.setMessage("顶级分类佣金比率不可沿用上级分类的");
            basicRet.setResult(BasicRet.ERR);
            return  basicRet;
        }

        if(categories.getParentid() == 0 && (servicesrate.compareTo(new BigDecimal(-1)) == 0)){
            basicRet.setMessage("顶级分类服务费比率不可沿用上级分类的");
            basicRet.setResult(BasicRet.ERR);
            return  basicRet;
        }

        if(categories.getParentid() != 0) {
            Categories parentCategory = categoriesService.getById(categories.getParentid());
            if(parentCategory == null){
                return  new BasicRet(BasicRet.ERR,"父分类不存在");
            }

            if(!parentCategory.getCatetype().equals(catetype)){
                return  new BasicRet(BasicRet.ERR,"子类必须选择跟父类相同的类型");
            }
        }


        Categories dbCategories = categoriesService.getCategoryByNameAndParentid(name,categories.getParentid());
        if(dbCategories != null && dbCategories.getId() != id){
            basicRet.setMessage("该分类名称已存在");
            basicRet.setResult(BasicRet.ERR);
            return  basicRet;
        }

        categories.setId(id);
        categories.setName(name);
        categories.setSort(sort);
        categories.setKeywords(keywords);
        categories.setTitle(title);
        categories.setDescription(description);
        categories.setImg(img);
        categories.setBrokeragerate(brokeragerate);
        categories.setServicesrate(servicesrate);
        categories.setCatetype(catetype);

        categoriesService.updateByPrimaryKey(categories);

        //进行商品修改的同步
//        CategoriesRunnable categoriesRunnable=new CategoriesRunnable(categoriesService,categories,Quantity.STATE_1);
//        Thread thread=new Thread(categoriesRunnable);
//        thread.start();

        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("修改成功");
        return  basicRet;
    }



    @RequestMapping(value =  "/delete",method = RequestMethod.POST)
    @ApiOperation(value = "删除分类")
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.CLASSIFICATIONMANAGEMENT + "')")
    public  BasicRet delete(@RequestParam(required = true) long id){
        BasicRet basicRet = new BasicRet();

        int sonCount =  categoriesService.hasSonCategoryCount(id);
        if(sonCount>0){
            basicRet.setMessage("该分类下有子类，不可删除");
            basicRet.setResult(BasicRet.ERR);
            return  basicRet;
        }


        //查询商品库，查询有没有该分类下的商品
        Categories category  = categoriesService.getCategoryLevel(id);
        if(category == null){
            basicRet.setMessage("不存在该分类");
            basicRet.setResult(BasicRet.ERR);
            return  basicRet;
        }


        ProductInfoExample example = new ProductInfoExample();
        ProductInfoExample.Criteria criteria = example.createCriteria();
        if(category.getLevel() == 1){
            criteria.andLevel1idEqualTo(id);
        }else if(category.getLevel() == 2){
            criteria.andLevel2idEqualTo(id);
        }else if(category.getLevel() == 3){
            criteria.andLevel3idEqualTo(id);
        }
        criteria.andPdstateNotEqualTo(Quantity.STATE_6);

        int count = productInfoService.countByExample(example);
        if(count >0){
            basicRet.setMessage("该分类下存在商品，不可删除");
            basicRet.setResult(BasicRet.ERR);
            return  basicRet;
        }



        categoriesService.delete(id);

        //进行商品删除的同步
//        Categories categories=new Categories();
//        categories.setId(id);
//        CategoriesRunnable categoriesRunnable=new CategoriesRunnable(categoriesService,categories,Quantity.STATE_2);
//        Thread thread=new Thread(categoriesRunnable);
//        thread.start();

        basicRet.setResult(BasicRet.ERR);
        basicRet.setMessage("删除成功");
        return  basicRet;

    }

/*
    @RequestMapping(value = "/businessRateSet",method = RequestMethod.POST)
    @ApiOperation("价格利率设置（运营）")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "分类id",name = "id",required = true,dataType = "int",paramType = "query"),
            @ApiImplicitParam(value = "加价率",name = "uprate",required = true,dataType = "double",paramType = "query"),
            @ApiImplicitParam(value = "金牌优惠率",name = "goldmemberrate",required = true,dataType = "double",paramType = "query"),
            @ApiImplicitParam(value = "服务商优惠率",name = "serverrate",required = true,dataType = "double",paramType = "query"),
            @ApiImplicitParam(value = "三级批发价优惠率",name = "thirdrate",required = true,dataType = "double",paramType = "query"),
            @ApiImplicitParam(value = "二级批发价优惠率",name = "secondrate",required = true,dataType = "double",paramType = "query"),
            @ApiImplicitParam(value = "一级批发价优惠率",name = "firstrate",required = true,dataType = "double",paramType = "query"),
    })
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.OPERATIONPRICERATESETTING + "')")
    public  BasicRet businessRateSet(@RequestParam(required = true) int id,
                                     @RequestParam(required = true)BigDecimal uprate,
                                     @RequestParam(required = true) BigDecimal goldmemberrate,
                                     @RequestParam(required = true) BigDecimal serverrate,
                                     @RequestParam(required = true) BigDecimal thirdrate,
                                     @RequestParam(required = true) BigDecimal secondrate,
                                     @RequestParam(required =true ) BigDecimal firstrate){

        BasicRet basicRet = new BasicRet();

        Categories categories = categoriesService.getById(id);
        if(categories == null){
            basicRet.setMessage("要设置的分类不存在");
            basicRet.setResult(BasicRet.ERR);
            return  basicRet;
        }


        categories.setUprate(uprate);
        categories.setGoldmemberrate(goldmemberrate);
        categories.setServerrate(serverrate);
        categories.setThirdrate(thirdrate);
        categories.setSecondrate(secondrate);
        categories.setFirstrate(firstrate);


        categoriesService.updateByPrimaryKey(categories);

        basicRet.setMessage("设置成功");
        basicRet.setResult(BasicRet.SUCCESS);
        return  basicRet;
    }



    @RequestMapping(value = "/list/businessRate",method = RequestMethod.POST)
    @ApiOperation("价格利率列表（运营）")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "父类分类id",name = "parentid",required = true,dataType = "int",paramType = "query",defaultValue = "0"),
    })
    public PageInfo listBusinessRate(@RequestParam(required = true,defaultValue = "1") int pageNo,
                                     @RequestParam(required = true,defaultValue = "20") int pageSize,
                                     @RequestParam(required = true,defaultValue = "0") int parentid
                                     ) {

        return  categoriesService.listBusinessRate(pageNo,pageSize,parentid);
    }




    @RequestMapping(value = "/list/financeRate",method = RequestMethod.POST)
    @ApiOperation("价格利率列表（财务）")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "父类分类id",name = "parentid",required = true,dataType = "int",paramType = "query",defaultValue = "0"),
    })
    public PageInfo listFinanceRate(@RequestParam(required = true,defaultValue = "1") int pageNo,
                                     @RequestParam(required = true,defaultValue = "20") int pageSize,
                                     @RequestParam(required = true,defaultValue = "0") int parentid
    ) {

        return  categoriesService.listFinanceRateSet(pageNo,pageSize,parentid);
    }






    @RequestMapping(value = "/financeRateSet",method = RequestMethod.POST)
    @ApiOperation("价格利率设置（财务）")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "分类id",name = "id",required = true,dataType = "int",paramType = "query"),
            @ApiImplicitParam(value = "计费率（财务）",name = "freerate",required = true,dataType = "double",paramType = "query"),
            @ApiImplicitParam(value = "运营费用（财务）",name = "businessrate",required = true,dataType = "double",paramType = "query"),
            @ApiImplicitParam(value = "支付费用率（财务）",name = "payrate",required = true,dataType = "double",paramType = "query"),
            @ApiImplicitParam(value = "服务商净利率（财务）",name = "servernetrate",required = true,dataType = "double",paramType = "query"),
    })
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.FINANCEPRICERATESETTING + "')")
    public  BasicRet financeRateSet(@RequestParam(required = true) int id,
                                     @RequestParam(required = true)BigDecimal freerate,
                                     @RequestParam(required = true) BigDecimal businessrate,
                                     @RequestParam(required = true) BigDecimal payrate,
                                     @RequestParam(required = true) BigDecimal servernetrate
                                    ) {

        BasicRet basicRet = new BasicRet();

        Categories categories = categoriesService.getById(id);
        if(categories == null){
            basicRet.setMessage("要设置的分类不存在");
            basicRet.setResult(BasicRet.ERR);
            return  basicRet;
        }

        categories.setFreerate(freerate);
        categories.setBusinessrate(businessrate);
        categories.setPayrate(payrate);
        categories.setServernetrate(servernetrate);


        categoriesService.updateByPrimaryKey(categories);

        basicRet.setMessage("设置成功");
        basicRet.setResult(BasicRet.SUCCESS);
        return  basicRet;
    }
*/
}
