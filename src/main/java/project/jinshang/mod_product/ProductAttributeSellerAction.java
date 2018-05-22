package project.jinshang.mod_product;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.mod_product.bean.Attvalue;
import project.jinshang.mod_product.service.*;

import java.util.List;

/**
 * create : wyh
 * date : 2017/11/10
 */

@RestController
@RequestMapping("/rest/seller/productAttribute")
@Api(tags = "卖家商品属性相关接口",description = "产品品名、产品属性、产品牌号、产品材质相关接口")
@SessionAttributes({AppConstant.MEMBER_SESSION_NAME})
@Transactional(rollbackFor = Exception.class)
public class ProductAttributeSellerAction {


    @Autowired
    private MaterialService materialService;

    @Autowired
    private CardNumService cardNumService;


    @Autowired
    private ProductNameService productNameService;

    @Autowired
    private AttributetblService attributetblService;

    @Autowired
    private AttvalueService attvalueService;


    @Autowired
    private  CategoriesService categoriesService;



    @RequestMapping(value = "/attrvalue/getValueByAttId",method = RequestMethod.POST)
    @ApiOperation("根据属性id获取所有属性值")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "attid",value = "属性值id",required = true,dataType = "int",paramType = "query"),
    })
    public List<Attvalue> getAttrvalueByAttId(
            @RequestParam(required = true) long attid ){
        return attvalueService.getListByAttid(attid);
    }



}
