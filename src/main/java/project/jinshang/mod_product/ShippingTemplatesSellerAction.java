package project.jinshang.mod_product;

import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.swagger.annotations.*;
import mizuki.project.core.restserver.config.BasicRet;
import org.elasticsearch.monitor.os.OsStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.jinshang.common.bean.MemberLogOperator;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.common.constant.Quantity;
import project.jinshang.common.constant.SellerAuthorityConst;
import project.jinshang.common.utils.CommonUtils;
import project.jinshang.common.utils.StringUtils;
import project.jinshang.mod_member.bean.Admin;
import project.jinshang.mod_member.bean.Member;
import project.jinshang.mod_product.bean.*;
import project.jinshang.mod_product.service.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * create : wyh
 * date : 2017/11/13
 */

@RestController
@RequestMapping("/rest/seller/shippingtemplates")
@Api(tags = "卖家运费模版设置")
@SessionAttributes({AppConstant.MEMBER_SESSION_NAME})
public class ShippingTemplatesSellerAction {

    @Autowired
    private ShippingTemplatesService shippingTemplatesService;

    @Autowired
    private AreaCostService areaCostService;

    @Autowired
    private ProductStoreService productStoreService;


    @Autowired
    protected MemberLogOperator memberLogOperator;

    @Autowired
    private MemberOperateLogService memberOperateLogService;

    @Autowired
    private ShippingTemplateGroupService shippingTemplateGroupService;


    @RequestMapping(value = "/addTemplate",method = RequestMethod.POST)
    @ApiOperation("添加模版")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "名称",name = "temname",required = true,dataType = "string",paramType = "query"),
//            @ApiImplicitParam(value = "商品地址省",name = "province",required = true,dataType = "string",paramType = "query"),
//            @ApiImplicitParam(value = "商品地址市",name = "city",required = true,dataType = "string",paramType = "query"),
//            @ApiImplicitParam(value = "商品地址区",name = "area",required = true,dataType = "string",paramType = "query"),
//            @ApiImplicitParam(value = "详细地址",name = "address",required = true,dataType = "string",paramType = "query"),
            @ApiImplicitParam(value = "是否包邮 0=不包邮，1=包邮",name = "isfree",required = true,dataType = "int",paramType = "query"),
            @ApiImplicitParam(value = "计价方式 1=按重量 2-按金额",name = "counttype",required = true,dataType = "int",paramType = "query"),
            @ApiImplicitParam(value = "默认运费公斤",name = "defaultfreight",required = true,dataType = "string",paramType = "query",defaultValue = "0"),
            @ApiImplicitParam(value = "默认运费元",name = "defaultcost",required = true,dataType = "string",paramType = "query",defaultValue = "0"),
            @ApiImplicitParam(value = "每增加公斤",name = "perkilogramadded",required = true,dataType = "string",paramType = "query",defaultValue = "0"),
            @ApiImplicitParam(value = "增加运费元",name = "perkilogramcost",required = true,dataType = "string",paramType = "query",defaultValue = "0"),
            @ApiImplicitParam(value = "其他指定运费Json,例如：[{\"province\":\"山东,浙江\",\"city\":\"烟台，青岛，杭州\"，\"perkilogramcost\":1,\"defaultfreight\":3,\"defaultcost\":4,\"perkilogramadded\":2,\"selectarea\":\"\"},{\"province\":\"北京,浙江\",\"city\":\"北京，绍兴\"，\"perkilogramcost\":2,\"defaultfreight\":5,\"defaultcost\":4,\"perkilogramadded\":2,\"selectarea\":\"\"}]",name = "templateJson",required = false,dataType = "string",paramType = "query"),
            @ApiImplicitParam(value = "类型，1商家端，2商家合单集合",name = "temtype",required = true,dataType = "int",paramType = "query"),
            @ApiImplicitParam(value = "满免金额",name = "defaultfreeprice",required = true,dataType = "string",paramType = "query",defaultValue = "0"),
            @ApiImplicitParam(value = "如果类型是1商家端，则为-1)",name = "shopgroupid",required = true,dataType = "string",paramType = "query"),
    })
    @PreAuthorize("hasAuthority('"+ SellerAuthorityConst.LOGISTICS+"') || hasAuthority('"+ SellerAuthorityConst.ALL+"')")
    public BasicRet addTemplate(
            @RequestParam(required = true) String temname,
//            @RequestParam(required =  true) String province,
//            @RequestParam(required = true) String city,
//            @RequestParam(required =true ) String area,
//            @RequestParam(required = true) String address,
            @RequestParam(required = true,defaultValue = "0") int isfree,
            @RequestParam(required = true,defaultValue = "0") int counttype,
            @RequestParam(required = true,defaultValue = "0")BigDecimal defaultfreight,
            @RequestParam(required = true,defaultValue = "0") BigDecimal defaultcost,
            @RequestParam(required = true,defaultValue = "0") BigDecimal perkilogramadded,
            @RequestParam(required = true,defaultValue = "0") BigDecimal perkilogramcost,
            @RequestParam(required = false,defaultValue = "") String templateJson,
            @RequestParam(required = true,defaultValue = "1") int temtype,
            @RequestParam(required = true,defaultValue = "0") BigDecimal defaultfreeprice,
            @RequestParam(required = true)  long shopgroupid,
            Model model, HttpServletRequest request){

        BasicRet basicRet = new BasicRet();

        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        Admin  admin  = (Admin) model.asMap().get(AppConstant.ADMIN_SESSION_NAME);

        ShippingTemplates templates = new ShippingTemplates();
        templates.setTemname(temname);
        templates.setProvince("");
        templates.setCity("");
        templates.setArea("");
        templates.setAddress("");

        templates.setIsfree((short)0);
        templates.setCounttype((short)counttype);
        templates.setDefaultfreight(defaultfreight);
        templates.setDefaultcost(defaultcost);
        templates.setPerkilogramadded(perkilogramadded);
        templates.setPerkilogramcost(perkilogramcost);
        templates.setMemberid(member.getId());
        templates.setTemtype((short)temtype);
        templates.setDefaultfreeprice(defaultfreeprice);
        if (temtype== Quantity.INT_1){
            templates.setShopgroupid(-1L);
            templates.setMemberid(member.getId());
        }else if(temtype==Quantity.INT_2){
            templates.setMemberid(-1L);
            templates.setShopgroupid(shopgroupid);
        }
        ShippingTemplatesExample example=new ShippingTemplatesExample();
        ShippingTemplatesExample.Criteria criteria=example.createCriteria();
        criteria.andMemberidEqualTo(member.getId());
        criteria.andTemnameEqualTo(temname);
        List<ShippingTemplates> shippingTemplatesList=shippingTemplatesService.listTemplates(example);
        if (shippingTemplatesList!=null&&shippingTemplatesList.size()>0){
            basicRet.setMessage("运费模版名称不能重复");
            basicRet.setResult(BasicRet.ERR);
            return basicRet;
        }
        shippingTemplatesService.add(templates);




//[{"province":"山东,浙江","city":"烟台，青岛，杭州"，"perkilogramcost":1,"defaultfreight":3,"defaultcost":4,"perkilogramadded":2},{"province":"北京,浙江","city":"北京，绍兴"，"perkilogramcost":2,"defaultfreight":5,"defaultcost":4,"perkilogramadded":2}]
        if(StringUtils.hasText(templateJson) ){
            if(!CommonUtils.isGoodJson(templateJson)){
                basicRet.setResult(BasicRet.ERR);
                basicRet.setMessage("指定城市运费模版json格式不对");
                return  basicRet;
            }


            Gson gson = new Gson();
            List<Map<String,Object>> list = gson.fromJson(templateJson,new TypeToken<ArrayList<Map<String,Object>>>() {}.getType());

            if(list != null && list.size()>0){
                for(Map<String,Object> map: list){
                    if(map.get("city") == null || map.get("city").equals("")) continue;

                    AreaCost areaCost = new AreaCost();
                    areaCost.setTemid(templates.getId());
                    areaCost.setCity((String) map.get("city"));
                    areaCost.setProvince((String) map.get("province"));
                    areaCost.setDefaultcost(new BigDecimal( map.get("defaultcost").toString()));
                    areaCost.setDefaultfreight(new BigDecimal( map.get("defaultfreight").toString()));
                    areaCost.setPerkilogramadded(new BigDecimal( map.get("perkilogramadded").toString()));
                    areaCost.setPerkilogramcost(new BigDecimal( map.get("perkilogramcost").toString()));
                    areaCost.setSelectarea(map.get("selectarea").toString());
                    areaCost.setDefaultfreeprice(new BigDecimal(map.get("defaultfreeprice").toString()));

                    areaCostService.add(areaCost);
                }
            }
        }




        //保存日志
        memberLogOperator.saveMemberLog(member,null,"添加运费模版："+temname,request,memberOperateLogService);

        basicRet.setMessage("添加成功");
        basicRet.setResult(BasicRet.SUCCESS);
        return  basicRet;
    }





    @RequestMapping(value = "/updateTemplate",method = RequestMethod.POST)
    @ApiOperation("修改模版")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "id",name = "id",required = true,dataType = "int",paramType = "query"),
            @ApiImplicitParam(value = "名称",name = "temname",required = true,dataType = "string",paramType = "query"),
//            @ApiImplicitParam(value = "商品地址省",name = "province",required = true,dataType = "string",paramType = "query"),
//            @ApiImplicitParam(value = "商品地址市",name = "city",required = true,dataType = "string",paramType = "query"),
//            @ApiImplicitParam(value = "商品地址区",name = "area",required = true,dataType = "string",paramType = "query"),
//            @ApiImplicitParam(value = "详细地址",name = "address",required = true,dataType = "string",paramType = "query"),
            @ApiImplicitParam(value = "是否包邮",name = "isfree",required = true,dataType = "int",paramType = "query"),
            @ApiImplicitParam(value = "计价方式",name = "counttype",required = true,dataType = "int",paramType = "query"),
            @ApiImplicitParam(value = "默认运费公斤",name = "defaultfreight",required = true,dataType = "double",paramType = "query"),
            @ApiImplicitParam(value = "默认运费元",name = "defaultcost",required = true,dataType = "double",paramType = "query"),
            @ApiImplicitParam(value = "每增加公斤",name = "perkilogramadded",required = true,dataType = "double",paramType = "query"),
            @ApiImplicitParam(value = "增加运费元",name = "perkilogramcost",required = true,dataType = "double",paramType = "query"),
            @ApiImplicitParam(value = "其他指定运费Json,例如：[{\"province\":\"山东,浙江\",\"city\":\"烟台，青岛，杭州\"，\"perkilogramcost\":1,\"defaultfreight\":3,\"defaultcost\":4,\"perkilogramadded\":2,\"selectarea\":\"\"},{\"province\":\"北京,浙江\",\"city\":\"北京，绍兴\"，\"perkilogramcost\":2,\"defaultfreight\":5,\"defaultcost\":4,\"perkilogramadded\":2,\"selectarea\":\"\"}]",name = "templateJson",required = false,dataType = "string",paramType = "query"),
            @ApiImplicitParam(value = "类型，1商家端，2商家合单集合",name = "temtype",required = true,dataType = "int",paramType = "query"),
            @ApiImplicitParam(value = "满免金额",name = "defaultfreeprice",required = true,dataType = "string",paramType = "query",defaultValue = "0"),
            @ApiImplicitParam(value = "如果类型是1商家端，则为-1)",name = "shopgroupid",required = true,dataType = "string",paramType = "query"),
    })
    @PreAuthorize("hasAuthority('"+ SellerAuthorityConst.LOGISTICS+"') || hasAuthority('"+ SellerAuthorityConst.ALL+"')")
    public BasicRet updateTemplate(
            @RequestParam(required = true) long id,
            @RequestParam(required = true) String temname,
//            @RequestParam(required =  true) String province,
//            @RequestParam(required = true) String city,
//            @RequestParam(required =true ) String area,
//            @RequestParam(required = true) String address,
            @RequestParam(required = true,defaultValue = "0") int isfree,
            @RequestParam(required = true,defaultValue = "0") int counttype,
            @RequestParam(required = true,defaultValue = "0")BigDecimal defaultfreight,
            @RequestParam(required = true,defaultValue = "0") BigDecimal defaultcost,
            @RequestParam(required = true,defaultValue = "0") BigDecimal perkilogramadded,
            @RequestParam(required = true,defaultValue = "0") BigDecimal perkilogramcost,
            @RequestParam(required = false,defaultValue = "") String templateJson,
            @RequestParam(required = true,defaultValue = "1") int temtype,
            @RequestParam(required = true,defaultValue = "0") BigDecimal defaultfreeprice,
            @RequestParam(required = true)  long shopgroupid,
            Model model,HttpServletRequest request ){

        BasicRet basicRet = new BasicRet();

        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

        ShippingTemplates templates = shippingTemplatesService.getById(id);

        if(templates == null){
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("该模版不存在");
            return  basicRet;
        }


        if(!member.getId().equals(templates.getMemberid())){
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("该模版不属于你");
            return  basicRet;
        }


        templates.setTemname(temname);
        templates.setProvince("");
        templates.setCity("");
        templates.setArea("");
        templates.setAddress("");
        templates.setIsfree((short)isfree);
        templates.setCounttype((short)counttype);
        templates.setDefaultfreight(defaultfreight);
        templates.setDefaultcost(defaultcost);
        templates.setPerkilogramadded(perkilogramadded);
        templates.setPerkilogramcost(perkilogramcost);
        templates.setTemtype((short) temtype);
        templates.setDefaultfreeprice(defaultfreeprice);

        ShippingTemplatesExample example=new ShippingTemplatesExample();
        ShippingTemplatesExample.Criteria criteria=example.createCriteria();
        criteria.andMemberidEqualTo(member.getId());
        criteria.andTemnameEqualTo(temname);
        List<ShippingTemplates> shippingTemplatesList=shippingTemplatesService.listTemplates(example);
        if (shippingTemplatesList!=null&&shippingTemplatesList.size()>0&&shippingTemplatesList.get(0).getId().compareTo(id)!=0){
            basicRet.setMessage("运费模版名称不能重复");
            basicRet.setResult(BasicRet.ERR);
            return basicRet;
        }

        shippingTemplatesService.update(templates);


        if(StringUtils.hasText(templateJson) ){
            if(!CommonUtils.isGoodJson(templateJson)){
                basicRet.setResult(BasicRet.ERR);
                basicRet.setMessage("指定城市运费模版json格式不对");
                return  basicRet;
            }
        }

        areaCostService.deleteByTemid(templates.getId());

        Gson gson = new Gson();
        List<AreaCost> list = gson.fromJson(templateJson,new TypeToken<ArrayList<AreaCost>>() {}.getType());

        if(list != null && list.size()>0){
            for(AreaCost areaCost : list){
                areaCost.setTemid(templates.getId());
                areaCostService.add(areaCost);
            }
        }

        //保存日志
        memberLogOperator.saveMemberLog(member,null,"修改运费模版："+temname,request,memberOperateLogService);

        basicRet.setMessage("修改成功");
        basicRet.setResult(BasicRet.SUCCESS);
        return  basicRet;
    }


    @RequestMapping(value =  "/delTemplate",method = RequestMethod.POST)
    @ApiOperation("删除运费模版")
    @PreAuthorize("hasAuthority('"+ SellerAuthorityConst.LOGISTICS+"') || hasAuthority('"+ SellerAuthorityConst.ALL+"')")
    public  BasicRet delTemplate(@RequestParam(required = true) long id,Model model,HttpServletRequest request){
        BasicRet basicRet = new BasicRet();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        ShippingTemplates templates = shippingTemplatesService.getById(id);

        if(templates == null){
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("该模版不存在");
            return  basicRet;
        }


        if(!member.getId().equals(templates.getMemberid())){
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("该模版不属于你");
            return  basicRet;
        }


//        ProductStoreExample example = new ProductStoreExample();
//        example.createCriteria().andFreightmodeEqualTo(id);
//        int count = productStoreService.countByExample(example);
//
//        if(count>0){
//            basicRet.setResult(BasicRet.ERR);
//            basicRet.setMessage("该模版还有产品在使用，不可删除");
//            return  basicRet;
//        }

        if(shippingTemplateGroupService.getCountUsedShippingTemplates(id) >0){
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("该模版还有运费集合在使用，不可删除");
            return  basicRet;
        }

        shippingTemplatesService.deleteById(id);
        areaCostService.deleteByTemid(id);

        //保存日志
        memberLogOperator.saveMemberLog(member,null,"删除运费模版："+templates.getTemname(),request,memberOperateLogService);

        basicRet.setMessage("删除成功");
        basicRet.setResult(BasicRet.SUCCESS);
        return  basicRet;

    }




    @PostMapping("/detail")
    @ApiOperation("模版详情")
    @PreAuthorize("hasAuthority('"+ SellerAuthorityConst.LOGISTICS+"') || hasAuthority('"+ SellerAuthorityConst.ALL+"')")
    public TemplateRet detail(@RequestParam(required = true) long id,Model model){
        TemplateRet templateRet = new TemplateRet();

        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

        ShippingTemplates templates = shippingTemplatesService.getById(id);
        if(templates == null){
            templateRet.setResult(BasicRet.ERR);
            templateRet.setMessage("模版不存在");
            return  templateRet;
        }

        if(!templates.getMemberid().equals(member.getId())){
            templateRet.setResult(BasicRet.ERR);
            templateRet.setMessage("模版不属于你");
            return  templateRet;
        }

        List<AreaCost> areaCosts =  areaCostService.getByTempid(templates.getId());
        templates.setAreaCostList(areaCosts);
        templateRet.setData(templates);
        templateRet.setResult(BasicRet.SUCCESS);

        return  templateRet;
    }



    @RequestMapping(value =  "/listTemplate",method = RequestMethod.POST)
    @ApiOperation("列出所有模版")
    @PreAuthorize("hasAuthority('"+ SellerAuthorityConst.LOGISTICS+"') || hasAuthority('"+ SellerAuthorityConst.ALL+"')")
    public TemplateListRet  listTemplate(Model model){
        TemplateListRet templateRet =  new TemplateListRet();

        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        List<ShippingTemplates> list =  shippingTemplatesService.listTemplatesByMemberid(member.getId());
        templateRet.setData(list);
        templateRet.setResult(BasicRet.SUCCESS);
        templateRet.setMessage("获取成功");
        return  templateRet;
    }



    private  class  TemplateListRet extends  BasicRet{
        private  List<ShippingTemplates> data;

        public List<ShippingTemplates> getData() {
            return data;
        }

        public void setData(List<ShippingTemplates> data) {
            this.data = data;
        }
    }

    private  class  TemplateRet extends  BasicRet{
        private  ShippingTemplates data;

        public ShippingTemplates getData() {
            return data;
        }

        public void setData(ShippingTemplates data) {
            this.data = data;
        }
    }

}
