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
import project.jinshang.common.bean.PageRet;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.common.constant.SellerAuthorityConst;
import project.jinshang.mod_member.bean.Member;
import project.jinshang.mod_product.bean.Brand;
import project.jinshang.mod_product.bean.Categories;
import project.jinshang.mod_product.service.BrandService;
import project.jinshang.mod_product.service.CategoriesService;

import java.util.Date;

/**
 * create : wyh
 * date : 2017/11/9
 */
@RestController
@SessionAttributes({AppConstant.MEMBER_SESSION_NAME})
@RequestMapping("/rest/seller/brand")
@Api(tags = "卖家品牌管理",description = "卖家品牌管理相关接口")
public class BrandSellerAction {

    @Autowired
    private BrandService brandService;

    @Autowired
    private CategoriesService categoriesService;

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ApiOperation(value = "添加品牌")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "名称",name = "name",required = true,dataType = "string",paramType = "query"),
            @ApiImplicitParam(value = "产品分类id",name = "categoryid",required = true,dataType = "int",paramType = "query"),
            @ApiImplicitParam(value = "图片",name = "pic",required = false,dataType = "string",paramType = "query"),
    })
    @PreAuthorize("hasAuthority('"+ SellerAuthorityConst.GRADELIST+"') || hasAuthority('"+ SellerAuthorityConst.ALL+"')")
    public BasicRet add(@RequestParam(required = true) String name,
                        @RequestParam(required = true) int categoryid,
                        @RequestParam(required = false,defaultValue = "") String pic, Model model) {

        BasicRet basicRet = new BasicRet();

        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

        Categories categories = categoriesService.getById(categoryid);

        if(categories == null){
            basicRet.setMessage("该分类不存在");
            basicRet.setResult(BasicRet.ERR);
            return basicRet;
        }

        if(!new Long(0).equals(categories.getParentid())){
            basicRet.setMessage("分类为一级分类");
            basicRet.setResult(BasicRet.ERR);
            return  basicRet;
        }


        Brand dbBrand = brandService.getByNameAndCateid(name,categories.getId());

        if (dbBrand != null) {
            basicRet.setMessage("已经存在该品牌了，不可重复添加");
            basicRet.setResult(BasicRet.ERR);
            return basicRet;
        }

        Brand brand = new Brand();
        brand.setName(name);
        brand.setCategory(categories.getName());
        brand.setCategoryid(new Long(categoryid));
        brand.setPic(pic);

        brand.setMemberid(member.getId());
        brand.setAuditname((long) 0);
        brand.setReason("");
        brand.setAuditstate((short)0);
        brand.setCeatetime(new Date());
        brand.setAudittime(new Date());

        brandService.addBrand(brand);

        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("添加成功");
        return basicRet;
    }




    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ApiOperation(value = "删除品牌",notes = "后台审核通过的品牌不可删除，只可删除正在审核和审核为通过的")
    @PreAuthorize("hasAuthority('"+ SellerAuthorityConst.GRADELIST+"') || hasAuthority('"+ SellerAuthorityConst.ALL+"')")
    public BasicRet delete(@RequestParam(required = true) Long id,Model model) {
        BasicRet basicRet = new BasicRet();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        Brand brand = brandService.getById(id);
        if(brand == null){
            basicRet.setMessage("要删除的品牌不存在");
            basicRet.setResult(BasicRet.ERR);
            return  basicRet;
        }


        if(brand.getAuditstate() == 1){
            basicRet.setMessage("已经审核通过的品牌不可删除");
            basicRet.setResult(BasicRet.ERR);
            return  basicRet;
        }

        if(!member.getId().equals(brand.getMemberid())){
            basicRet.setMessage("要删除的品牌不是你创建的，不可删除");
            basicRet.setResult(BasicRet.ERR);
            return  basicRet;
        }

        brandService.deleteById(id);
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("删除成功");
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
    @PreAuthorize("hasAuthority('"+ SellerAuthorityConst.GRADELIST+"') || hasAuthority('"+ SellerAuthorityConst.ALL+"')")
    public BasicRet update(@RequestParam(required = true) Long id,
                           @RequestParam(required = true) String name,
                           @RequestParam(required = true) int categoryid,
                           @RequestParam(required = false,defaultValue = "") String pic, Model model) {

        BasicRet basicRet = new BasicRet();

        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

        Categories categories = categoriesService.getById(categoryid);

        if(categories == null){
            basicRet.setMessage("该分类不存在");
            basicRet.setResult(BasicRet.ERR);
            return basicRet;
        }

        if(!new Long(0).equals(categories.getParentid())){
            basicRet.setMessage("分类为一级分类");
            basicRet.setResult(BasicRet.ERR);
            return  basicRet;
        }

        Brand dbBrand = brandService.getByNameAndCateid(name,categories.getId());

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


        if(brand.getAuditstate() == 1){
            basicRet.setMessage("已经审核通过的品牌不可修改");
            basicRet.setResult(BasicRet.ERR);
            return  basicRet;
        }

        if(!member.getId().equals(brand.getMemberid())){
            basicRet.setMessage("要修改的品牌不是你创建的，不可修改");
            basicRet.setResult(BasicRet.ERR);
            return  basicRet;
        }

        brand.setName(name);
        brand.setCategoryid((long) categoryid);
        brand.setCategory(categories.getName());
        brand.setPic(pic);
        brand.setAuditstate((short)0);

        brandService.updateByPrimaryKey(brand);

        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("修改成功");
        return basicRet;
    }


    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ApiOperation(value = "品牌列表")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "品牌名称",name = "name",required = false,dataType = "string",paramType = "query"),
            @ApiImplicitParam(value = "审核状态{1:通过，2：不通过}",name = "auditstate",required = false,dataType = "int",paramType = "query",defaultValue = "-1"),
            @ApiImplicitParam(value = "分类id",name = "categoryid",required = false,dataType = "int",paramType = "query",defaultValue = "0")
    })
    @PreAuthorize("hasAuthority('"+ SellerAuthorityConst.GRADELIST+"') || hasAuthority('"+ SellerAuthorityConst.ALL+"')")
    public PageRet list(@RequestParam(required = true,defaultValue = "1") int pageNo,
                        @RequestParam(defaultValue = "20") int pageSize,
                        @RequestParam(defaultValue = "",required = false) String name,
                        @RequestParam(required = false,defaultValue = "0") long categoryid,
                        @RequestParam(defaultValue = "-1",required = false) short auditstate, Model model) {
        PageRet pageRet =  new PageRet();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

        Brand brand = new Brand();
        brand.setName(name);
        brand.setAuditstate(auditstate);
        brand.setMemberid(member.getId());
        brand.setCategoryid(categoryid);

        PageInfo pageInfo = brandService.getListByPage(pageNo,pageSize,brand);
        pageRet.data.setPageInfo(pageInfo);
        pageRet.setResult(BasicRet.SUCCESS);
        return  pageRet;
    }



}
