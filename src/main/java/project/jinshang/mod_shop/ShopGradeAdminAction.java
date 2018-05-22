package project.jinshang.mod_shop;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import mizuki.project.core.restserver.config.BasicRet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.jinshang.common.bean.PageRet;
import project.jinshang.common.utils.StringUtils;
import project.jinshang.mod_company.bean.SellerCompanyInfoExample;
import project.jinshang.mod_company.service.SellerCompanyInfoService;
import project.jinshang.mod_shop.bean.ShopGrade;
import project.jinshang.mod_shop.bean.ShopGradeExample;
import project.jinshang.mod_shop.service.ShopGradeService;

import java.util.List;

/**
 * create : wyh
 * date : 2017/11/4
 */

@RestController
@RequestMapping("/rest/admin/shopGrade")
@Api(tags = "后台店铺等级管理",description = "后台店铺等级相关接口")
public class ShopGradeAdminAction {
    @Autowired
    private ShopGradeService shopGradeService;


    @Autowired
    private SellerCompanyInfoService sellerCompanyInfoService;

    @RequestMapping(value = "/listAllShopGroup",method = RequestMethod.POST)
    @ApiOperation("所有店铺等级列表")
    public PageRet list (@RequestParam(required = false,defaultValue = "") String gradename,
                         @RequestParam(required = true,defaultValue = "1") int pageNo,@RequestParam(required = true,defaultValue = "20") int pageSize ){
        PageRet pageRet = new PageRet();
        ShopGradeExample example =  new ShopGradeExample();
        ShopGradeExample.Criteria criteria = example.createCriteria();

        if(StringUtils.hasText(gradename)){
            criteria.andGradenameEqualTo("%"+gradename+"%");
        }

        PageInfo pageInfo = shopGradeService.listShopGroupBuyPage(example,pageNo,pageSize);

        pageRet.data.setPageInfo(pageInfo);
        pageRet.setResult(BasicRet.SUCCESS);
        return pageRet;
    }



    @RequestMapping(value = "/addShop",method = RequestMethod.POST)
    @ApiOperation(value = "添加店铺等级")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "grade",value = "组别",required = true,paramType = "query",dataType = "int"),
        @ApiImplicitParam(name = "gradename",value = "名称",required = true,paramType = "query"  ,dataType = "string"),
        @ApiImplicitParam(name="productlimit",value = "可发布产品数",required = true,paramType = "query",dataType = "int"),
        @ApiImplicitParam(name="chargestandard",value = "定金",required = true,paramType = "query",dataType = "BigDecimal"),
        @ApiImplicitParam(name="rate",value = "佣金比率",required = true,paramType = "query",dataType = "BigDecimal"),
        @ApiImplicitParam(name="remark",value = "备注",required = false,paramType = "query",dataType = "string"),
        @ApiImplicitParam(name="idefault",value = "是否是默认的等级{1:默认,0:否}",required = true,paramType = "query",dataType ="int" )
    })
    public BasicRet add(ShopGrade shopGrade){
        BasicRet basicRet = new BasicRet();
        if (shopGrade.getIdefault()==1){
            ShopGrade sg=new ShopGrade();
            sg.setIdefault(0);
            ShopGradeExample example = new ShopGradeExample();
            example.createCriteria().andIdefaultEqualTo(1);
            shopGradeService.updateByExampleSelective(sg,example);
        }
        shopGradeService.addGrade(shopGrade);
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("添加成功");
            return  basicRet;
    }



    @RequestMapping(value = "/deleteShopGroup",method = RequestMethod.POST)
    @ApiOperation("删除店铺级别")
    public  BasicRet deleteShouGroup(@RequestParam(required = true) long id){
        BasicRet basicRet=new BasicRet();
        ShopGrade shopGrade=new ShopGrade();
        int getidefault=shopGradeService.selectByPrimaryKey(id).getIdefault();
        if (getidefault==1){
          basicRet.setResult(BasicRet.ERR);
          basicRet.setMessage("该等级属于默认级别，请修改后再删除");
          return  basicRet;
        }else {

            SellerCompanyInfoExample example = new SellerCompanyInfoExample();
            SellerCompanyInfoExample.Criteria criteria = example.createCriteria();
            criteria.andShopgradeidEqualTo(new Long(id).intValue());
            int c= sellerCompanyInfoService.countByExample(example);
            if(c>0){
                basicRet.setResult(BasicRet.ERR);
                basicRet.setMessage("有卖家属于该等级，不可删除");
                return  basicRet;
            }


            shopGradeService.deleteShopGroup(id);
            basicRet.setResult(BasicRet.SUCCESS);
            basicRet.setMessage("删除成功");
        }
            return basicRet;
    }



    @RequestMapping(value = "/updateShopGroup",method = RequestMethod.POST)
    @ApiOperation("修改店铺等级信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "ID",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "grade",value = "组别",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "gradename",value = "名称",required = true,paramType = "query"  ,dataType = "string"),
            @ApiImplicitParam(name="productlimit",value = "可发布产品数",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name="chargestandard",value = "定金",required = true,paramType = "query",dataType = "BigDecimal"),
            @ApiImplicitParam(name="rate",value = "佣金比率",required = true,paramType = "query",dataType = "BigDecimal"),
            @ApiImplicitParam(name="remark",value = "备注",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name="idefault",value = "是否是默认的等级{1:默认,0:否}",required = true,paramType = "query",dataType ="int" )
    })
    public  BasicRet updateShopGroup(ShopGrade shopGrade){
       BasicRet basicRet =new BasicRet();
       if (shopGrade.getIdefault()==1){
           ShopGrade sg=new ShopGrade();
           sg.setIdefault(0);
           ShopGradeExample shopGradeExample =new ShopGradeExample();
           shopGradeExample.createCriteria().andIdefaultEqualTo(1);
           shopGradeService.updateByExampleSelective(sg,shopGradeExample);
       }else {
           ShopGrade DeshopGrade =shopGradeService.getDefaultShopGroup();
           if (DeshopGrade.getId()==shopGrade.getId()){
               basicRet.setResult(BasicRet.ERR);
               basicRet.setMessage("不可修改，至少有一个默认等级");
               return  basicRet;
           }

       }
       shopGradeService.updateShopGroup(shopGrade);
       basicRet.setResult(BasicRet.SUCCESS);
       basicRet.setMessage("修改成功");
        return  basicRet;
    }


    @RequestMapping(value = "/selectShopGroupbyID",method = RequestMethod.POST)
    @ApiOperation("根据ID查询该等级的店铺是否存在")
    public  BasicRet exitshougroup(@RequestParam(required = true) long id){
        BasicRet basicRet=new BasicRet();
        ShopGrade shopGrade =new ShopGrade();
        shopGrade=shopGradeService.selectByPrimaryKey(id);
        if(id!=shopGrade.getId()){
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("该ID的店铺等级不存在");
            return  basicRet;
        }else {
            basicRet.setResult(BasicRet.SUCCESS);
            basicRet.setMessage("ID为"+id+"的店铺等级为"+shopGrade.getGrade());
        }
        return basicRet;
    }



}
