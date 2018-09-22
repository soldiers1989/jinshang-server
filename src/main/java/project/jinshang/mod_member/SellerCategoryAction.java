package project.jinshang.mod_member;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import mizuki.project.core.restserver.config.BasicRet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.jinshang.common.constant.AdminAuthorityConst;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.common.constant.Quantity;
import project.jinshang.common.utils.SellerCategoryUtils;
import project.jinshang.mod_common.bean.BasicExtRet;
import project.jinshang.mod_company.bean.SellerCompanyInfo;
import project.jinshang.mod_company.service.SellerCompanyInfoService;
import project.jinshang.mod_member.bean.SellerCategory;
import project.jinshang.mod_member.bean.SellerProductCategory;
import project.jinshang.mod_member.service.SellerCategoryService;
import project.jinshang.mod_product.bean.Categories;
import project.jinshang.mod_product.service.CategoriesService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rest/admin")
@Api(tags = "后台卖家分类佣金模块",description = "后台卖家分类佣金模块")
@SessionAttributes(AppConstant.ADMIN_SESSION_NAME)
public class SellerCategoryAction {


    @Autowired
    private SellerCategoryService sellerCategoryService;

    @Autowired
    private SellerCompanyInfoService sellerCompanyInfoService;
    @Autowired
    private CategoriesService categoriesService;

    @RequestMapping(value = "/getSubCategoriesTree",method = RequestMethod.POST)
    @ApiOperation(value = "获取子分类树")
    @ResponseBody
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.SELLERBROKEMANAGEMENT + "')")
    public BasicRet getSubCategoriesTree(@RequestParam(required = true) Long sellerid){
        BasicExtRet basicRet = new BasicExtRet();
        List<SellerCategory> list =  sellerCategoryService.getAll(sellerid);

        SellerCompanyInfo info = sellerCompanyInfoService.getSellerCompanyByMemberid(sellerid);
        if(info!=null){
            //获取卖家选择的分类ID来显示
            Long[] categoryids =( Long[])info.getBusinesscategory();
            List<SellerProductCategory> categoryList =  sellerCategoryService.toProdCate(list);

            //分类树
            categoryList = SellerCategoryUtils.getChildsManyGroup(categoryList, Quantity.STATE_0);
            if(categoryids[0]==0){
                basicRet.setData(categoryList);
            }else {
                List<SellerProductCategory> sellerProductCategories = new ArrayList<>();
                for(Long categoryid:categoryids){
                    for(SellerProductCategory sellerProductCategory:categoryList){
                        if(categoryid.longValue()==sellerProductCategory.getCategoryid()){
                            sellerProductCategories.add(sellerProductCategory);
                            break;
                        }
                    }
                }
                basicRet.setData(sellerProductCategories);
            }
        }
        basicRet.setResult(BasicRet.SUCCESS);
        return  basicRet;
    }



    /**
     *功能描述
     * @author xiazy
     * @date  2018/6/29 17:13
     * @param model
     * @return mizuki.project.core.restserver.config.BasicRet
     */
    @RequestMapping(value = "/scanDiff",method = RequestMethod.GET)
    @ApiOperation(value = "比较商家中的佣金比例与总的产品分类中不一致的地方")
    public BasicRet scanDiff(Model model){
        List<SellerCategory> dissSellerCategoryList=new ArrayList<>();
        BasicRet basicRet=new BasicRet();
        basicRet.setMessage("同步佣金完成");
        basicRet.setResult(BasicRet.SUCCESS);
        List<Categories> originCategoryList =  categoriesService.getAll();
        List<Long> sellerIdList=sellerCategoryService.getSellerIdGroupBy();
        for (Long sellerId:sellerIdList) {
            List<SellerCategory> sellerCategoryList =  sellerCategoryService.getAll(sellerId);
            for (Categories category:originCategoryList){
                Long id=category.getId();
                BigDecimal brokeragerate=category.getBrokeragerate();
                boolean ixExit=false;
                for (SellerCategory sellerCategory:sellerCategoryList){
                    if(sellerCategory.getCategoryid().longValue()!=id){

                    }else{
                        ixExit=true;
                    }
                }
                if(!ixExit){
                    SellerCategory sellerCategory=tranCategory2SellerCategory(category);
                    sellerCategory.setSellerid(sellerId);
                    dissSellerCategoryList.add(sellerCategory);
                }
            }


        }
        sellerCategoryService.insertAllOf(dissSellerCategoryList);
        return basicRet;
    }




    public SellerCategory tranCategory2SellerCategory(Categories categories){
        SellerCategory sellerCategory=new SellerCategory();
        if (categories!=null){
            sellerCategory.setSort(categories.getSort());
            sellerCategory.setCategoryid(categories.getId());
            sellerCategory.setBrokeragerate(categories.getBrokeragerate());
            sellerCategory.setName(categories.getName());
            sellerCategory.setParentid(categories.getParentid());
            sellerCategory.setTitle(categories.getTitle());
            sellerCategory.setKeywords(categories.getKeywords());
            sellerCategory.setDescription(categories.getDescription());
            sellerCategory.setImg(categories.getImg());
        }
        return sellerCategory;
    }

    @RequestMapping(value = "/updateCategoryBrokeragerate",method = RequestMethod.POST)
    @ApiOperation(value = "修改分类佣金")
    @ResponseBody
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.SELLERBROKEMANAGEMENT + "')")
    public BasicRet updateCategoryBrokeragerate(@RequestParam(required = true) Long id,@RequestParam(required = true) BigDecimal brokerRate){
        BasicExtRet basicRet = new BasicExtRet();
        SellerCategory sellerCategory = sellerCategoryService.getSellerCategory(id);

        if(sellerCategory == null){
            return  new BasicRet(BasicRet.ERR,"卖家佣金分类不存在");
        }

        SellerCategory updateSellerCategory  = new SellerCategory();
        updateSellerCategory.setId(sellerCategory.getId());
        updateSellerCategory.setBrokeragerate(brokerRate);
        sellerCategoryService.updateSellerCategory(updateSellerCategory);
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("修改成功");
        return  basicRet;
    }

    private  static  class  CateRet extends BasicRet{
        public List<SellerCategory> data=new ArrayList<>();

        public List<SellerCategory> getData() {
            return data;
        }

        public void setData(List<SellerCategory> data) {
            this.data = data;
        }
    }

}
