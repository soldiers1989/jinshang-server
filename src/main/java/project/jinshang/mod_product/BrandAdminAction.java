package project.jinshang.mod_product;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import mizuki.project.core.restserver.config.BasicRet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.jinshang.common.bean.Page;
import project.jinshang.common.constant.AdminAuthorityConst;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.mod_common.bean.BasicExtRet;
import project.jinshang.mod_member.bean.Admin;
import project.jinshang.mod_product.bean.Brand;
import project.jinshang.mod_product.bean.Categories;
import project.jinshang.mod_product.bean.ProductInfoExample;
import project.jinshang.mod_product.service.BrandService;
import project.jinshang.mod_product.service.CategoriesService;
import project.jinshang.mod_product.service.ProductInfoService;

import java.util.Date;

/**
 * create : wyh
 * date : 2017/11/8
 */
@RestController
@RequestMapping("/rest/admin/brand")
@Api(tags = "后台品牌管理",description = "后台品牌管理相关接口")
@SessionAttributes({AppConstant.ADMIN_SESSION_NAME})
public class BrandAdminAction {

    @Autowired
    private BrandService brandService;

    @Autowired
    private CategoriesService categoriesService;

    @Autowired
    private ProductInfoService productInfoService;

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ApiOperation(value = "添加品牌")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "名称",name = "name",required = true,dataType = "string",paramType = "query"),
            @ApiImplicitParam(value = "产品分类id",name = "categoryid",required = true,dataType = "int",paramType = "query"),
            @ApiImplicitParam(value = "图片",name = "pic",required = false,dataType = "string",paramType = "query"),
    })
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.BRANDMANAGEMENT + "')")
    public BasicRet add(@RequestParam(required = true) String name,
                        @RequestParam(required = true) Long categoryid,
                        @RequestParam(required = false,defaultValue = "") String pic, Model model) {

        BasicRet basicRet = new BasicRet();

        Admin admin = (Admin) model.asMap().get(AppConstant.ADMIN_SESSION_NAME);
        Categories categories = null;

        if(categoryid != -1) {
            categories = categoriesService.getById(categoryid);

            if (categories == null) {
                basicRet.setMessage("该分类不存在");
                basicRet.setResult(BasicRet.ERR);
                return basicRet;
            }


            if (categories.getParentid() != 0) {
                basicRet.setMessage("分类为一级分类");
                basicRet.setResult(BasicRet.ERR);
                return basicRet;
            }
        }
        Brand dbBrand = brandService.getByNameAndCateid(name,categoryid);

        if (dbBrand != null) {
            basicRet.setMessage("已经存在该品牌了，不可重复添加");
            basicRet.setResult(BasicRet.ERR);
            return basicRet;
        }


        Brand brand = new Brand();
        brand.setName(name);
        brand.setCategory(categories == null ? "": categories.getName());
        brand.setCategoryid(categoryid);
        brand.setPic(pic);

        brand.setMemberid((long)0);
        brand.setAuditname(admin.getId());
        brand.setAudittime(new Date());
        brand.setReason("");
        brand.setAuditstate((short)1);
        brand.setCeatetime(new Date());


        brandService.addBrand(brand);

        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("添加成功");
        return basicRet;
    }



    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ApiOperation(value = "修改品牌")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "ID",name = "id",required = true,dataType = "int",paramType = "query"),
            @ApiImplicitParam(value = "名称",name = "name",required = true,dataType = "string",paramType = "query"),
            @ApiImplicitParam(value = "产品分类id",name = "categoryid",required = true,dataType = "int",paramType = "query"),
            @ApiImplicitParam(value = "图片",name = "pic",required = false,dataType = "string",paramType = "query"),
    })
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.BRANDMANAGEMENT + "')")
    public BasicRet update(@RequestParam(required = true) Long id,
                        @RequestParam(required = true) String name,
                        @RequestParam(required = true) Long categoryid,
                        @RequestParam(required = false,defaultValue = "") String pic, Model model) {

        BasicRet basicRet = new BasicRet();

        Admin admin = (Admin) model.asMap().get(AppConstant.ADMIN_SESSION_NAME);

        Categories categories = null;
        if(categoryid != -1) {
            categories =  categoriesService.getById(categoryid);

            if (categories == null) {
                basicRet.setMessage("该分类不存在");
                basicRet.setResult(BasicRet.ERR);
                return basicRet;
            }


            if (categories.getParentid() != 0) {
                basicRet.setMessage("分类为一级分类");
                basicRet.setResult(BasicRet.ERR);
                return basicRet;
            }

        }

        Brand dbBrand = brandService.getByNameAndCateid(name,categoryid);
        if (dbBrand != null && !dbBrand.getId().equals(id)) {
            basicRet.setMessage("已经存在该品牌了，不可使用重复品牌名");
            basicRet.setResult(BasicRet.ERR);
            return basicRet;
        }

        Brand brand = brandService.getById(id);
        if(brand == null){
            basicRet.setMessage("要修改的品牌不存在");
            basicRet.setResult(BasicRet.ERR);
            return basicRet;
        }

        brand.setName(name);
        brand.setCategoryid(categoryid);
        brand.setCategory(categories == null ? "" : categories.getName());
        brand.setPic(pic);

        brandService.updateByPrimaryKey(brand);

        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("修改成功");
        return basicRet;
    }



    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ApiOperation(value = "删除品牌")
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.BRANDMANAGEMENT + "')")
    public BasicRet delete(@RequestParam(required = true) Long id) {
        BasicRet basicRet = new BasicRet();

        ProductInfoExample example =new ProductInfoExample();
        ProductInfoExample.Criteria criteria = example.createCriteria();
        criteria.andBrandidEqualTo(id);
        int count = productInfoService.countByExample(example);
        if(count>0){
            basicRet.setMessage("有其他商品关联此品牌");
            basicRet.setResult(BasicRet.ERR);
            return  basicRet;
        }

        brandService.deleteById(id);
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("删除成功");
        return basicRet;
    }

    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ApiOperation(value = "品牌列表")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "品牌名称",name = "name",required = false,dataType = "string",paramType = "query"),
            @ApiImplicitParam(value = "审核状态{0:待审核， 1:通过，2：不通过}",name = "auditstate",required = false,dataType = "int",paramType = "query",defaultValue = "-1"),
            @ApiImplicitParam(value = "分类id",name = "categoryid",required = false,dataType = "int",paramType = "query",defaultValue = "0")
    })
    public BasicRet list(@RequestParam(required = true,defaultValue = "1") int pageNo,
                     @RequestParam(defaultValue = "20") int pageSize,
                      @RequestParam(defaultValue = "",required = false) String name,
                      @RequestParam(defaultValue = "-1",required = false) short auditstate,
                        @RequestParam(required = false,defaultValue = "0") long categoryid ) {
        BasicExtRet basicRet =  new BasicExtRet();

        Brand brand = new Brand();
        brand.setName(name);
        brand.setAuditstate(auditstate);
        brand.setCategoryid(categoryid);

        PageInfo pageInfo = brandService.getListByPage(pageNo,pageSize,brand);

        basicRet.setData(pageInfo);
        basicRet.setMessage("查询成功");
        basicRet.setResult(BasicRet.SUCCESS);
        return  basicRet;
    }



    @RequestMapping(value = "/checkValidate",method = RequestMethod.POST)
    @ApiOperation(value = "审核品牌")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "ID",name = "id",required = true,dataType = "short",paramType = "query"),
            @ApiImplicitParam(value = "审核状态id{1-通过，2-不通过}",name = "auditstate",required = true,dataType = "int",paramType = "query"),
            @ApiImplicitParam(value = "不通过原因",name = "reason",required = false,dataType = "string",paramType = "query"),
    })
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.BRANDMANAGEMENT + "')")
    public BasicRet checkValidate(@RequestParam(required = true) Long id,
                           @RequestParam(required = true) short auditstate,
                           @RequestParam(required = false,defaultValue = "")String reason, Model model) {

        BasicRet basicRet = new BasicRet();

        Admin admin = (Admin) model.asMap().get(AppConstant.ADMIN_SESSION_NAME);


        Brand brand = brandService.getById(id);
        if(brand == null){
            basicRet.setMessage("要修改的品牌不存在");
            basicRet.setResult(BasicRet.ERR);
            return basicRet;
        }

       brand.setAuditstate(auditstate);
       brand.setReason(reason);
       brand.setAuditname(admin.getId());

        brandService.updateByPrimaryKey(brand);

        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("审核成功");
        return basicRet;
    }
}
