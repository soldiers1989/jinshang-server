package project.jinshang.mod_fx;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import mizuki.project.core.restserver.config.BasicRet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.mod_common.bean.BasicExtRet;
import project.jinshang.mod_fx.bean.Fxcategories;
import project.jinshang.mod_fx.bean.FxcategoriesExample;
import project.jinshang.mod_fx.service.FxCategoriesService;
import project.jinshang.mod_product.bean.Categories;
import project.jinshang.mod_product.service.CategoriesService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = {"/rest/fxcategories"})
@Api(tags = "分类佣金比例管理", description = "分类佣金比例接口")
@SessionAttributes(AppConstant.MEMBER_SESSION_NAME)
public class FxCategoriesAction {

    @Autowired
    private FxCategoriesService fxCategoriesService;
    @Autowired
    private CategoriesService categoriesService;

    @PostMapping(value = "/updateOrInsert")
    @ApiOperation(value = "返佣比例设置")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "ID", required = false, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "cid", value = "商品分类ID(categories表的主键id)", required = true, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "ratio", value = "佣金比例", required = true, paramType = "query", dataType = "decimal"),
    })
    public BasicRet updateCategoriesInfo(Fxcategories fxcategories) {
        BasicRet basicRet = new BasicRet();
        //通过商品分类ID cid判断是否 存在则为更新 不存在则为insert
        Fxcategories oldfxcategories = null;
        oldfxcategories = fxCategoriesService.getCategoriesInfoById(fxcategories.getCid());
        if (oldfxcategories == null) {
          fxCategoriesService.insertFxCategories(fxcategories);
          basicRet.setResult(BasicRet.SUCCESS);
          basicRet.setMessage("插入成功");
          return basicRet;
        }
        fxCategoriesService.updateFxCategories(fxcategories.getCid(),fxcategories.getRatio());

        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("更新成功");
        return basicRet;
    }


    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ApiOperation(value = "返佣比例设置-列表")
    public BasicRet getList() {
        BasicExtRet basicRet = new BasicExtRet();
        List<Map<String,Object>> list = categoriesService.findCategories();

        basicRet.setData(list);
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("获取成功");
        return basicRet;
    }









}



