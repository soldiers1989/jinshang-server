package project.jinshang.mod_product;

import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.swagger.annotations.*;
import mizuki.project.core.restserver.config.BasicRet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.jinshang.common.bean.AdminLogOperator;
import project.jinshang.common.bean.PageRet;
import project.jinshang.common.constant.AdminAuthorityConst;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.common.constant.Quantity;
import project.jinshang.common.utils.CommonUtils;
import project.jinshang.common.utils.StringUtils;
import project.jinshang.mod_member.bean.Admin;
import project.jinshang.mod_product.bean.*;
import project.jinshang.mod_product.service.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * create : wyh
 * date : 2017/11/9
 */

@RestController
@RequestMapping("/rest/admin/productAttribute")
@Api(tags = "后台商品属性相关接口",description = "产品品名、产品属性、产品牌号、产品材质相关接口")
@SessionAttributes({AppConstant.ADMIN_SESSION_NAME})
@Transactional(rollbackFor = Exception.class)
public class ProductAttributeAdminAction {

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
    @Autowired
    private  ProductInfoService productInfoService;
    @Autowired
    private AdminOperateLogService adminOperateLogService;
    AdminLogOperator adminLogOperator=new AdminLogOperator();


    @PostMapping("/attr/add")
    @ApiOperation("添加品名下的属性")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "productnameid",value = "品名id",required = true,dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "name",value = "属性名",required = true,dataType = "string",paramType = "query"),
            @ApiImplicitParam(name = "sort",value = "排序",required = false,dataType = "string",paramType = "query"),
            @ApiImplicitParam(name = "connector",value = "连接符",required = false,dataType = "string",paramType = "query",defaultValue = "*"),
            @ApiImplicitParam(name = "remark",value = "备注",required = false,dataType = "string",paramType = "query"),
    })
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.PRODUCTATTRIBUTE + "')")
    public  BasicRet addAttr(@RequestParam(required = true) long productnameid,
                             @RequestParam(required = true) String name,
                             @RequestParam(required = false,defaultValue = "0") int sort,
                             @RequestParam(required = false,defaultValue = "*") String connector,
                             @RequestParam(required = false,defaultValue = "") String remark,
                             Model model,HttpServletRequest request){
        BasicRet basicRet =  new BasicRet();

        ProductName productName = productNameService.getById(productnameid);
        if(productName == null){
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("品名不存在");
            return  basicRet;
        }

        Attributetbl attributetbl = attributetblService.getByProductnameidAndName(productnameid,name);
        if(attributetbl != null){
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("已存在该属性");
            return  basicRet;
        }

        attributetbl = new Attributetbl();
        attributetbl.setProductnameid(productnameid);
        attributetbl.setName(name);
        attributetbl.setSort(sort);
        attributetbl.setRemark(remark);

        attributetbl.setConnector(connector);

        attributetblService.add(attributetbl);
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("添加成功");
        Admin admin = (Admin) model.asMap().get(AppConstant.ADMIN_SESSION_NAME);
        adminLogOperator.saveAdminLog(admin,"品名:"+productName.getName()+"新增属性:"+name,(short)1,"attributetbl",request,adminOperateLogService);
        return  basicRet;
    }





    @RequestMapping(value = "/attrvalue/getValueByAttId",method = RequestMethod.POST)
    @ApiOperation("根据属性id获取所有属性值")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "attid",value = "属性值id",required = true,dataType = "int",paramType = "query"),
    })
    public List<Attvalue> getAttrvalueByAttId(
            @RequestParam(required = true) long attid ){
       return attvalueService.getListByAttid(attid);
    }




    @RequestMapping(value = "/attrvalue/update",method = RequestMethod.POST)
    @ApiOperation("修改属性值")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "属性值id",required = true,dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "paramvalue",value = "属性值",required = true,dataType = "string",paramType = "query"),
            @ApiImplicitParam(name = "sort",value = "排序",required = true,dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "mark",value = "备注",required = false,dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "productName",value = "品名",required = true,dataType = "string",paramType = "query"),
            @ApiImplicitParam(name = "attrname",value = "属性名",required = true,dataType = "string",paramType = "query"),
    })
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.PRODUCTATTRIBUTE + "')")
    public BasicRet updateAttrvalue(
                                 @RequestParam(required = true) long id,
                                 @RequestParam(required = true) String paramvalue,
                                 @RequestParam(required = true) int sort,
                                 @RequestParam(required = false,defaultValue = "")String mark,
                                 Model model,HttpServletRequest request,String productName,String attrname){
        BasicRet basicRet = new BasicRet();

        Attvalue dbAttvalue = attvalueService.getByAttidAndValue(id,paramvalue);
        if(dbAttvalue != null && dbAttvalue.getId() != id){
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("该属性已经添加了该值了");
            return  basicRet;
        }

        Attvalue attvalue = attvalueService.getById(id);
        if(attvalue == null){
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("该属性值不存在");
            return  basicRet;
        }

        attvalue.setParamvalue(paramvalue);
        attvalue.setSort(sort);
        attvalue.setMark(mark);

        Attributetbl attributetbl = attributetblService.selectByPrimaryKey(attvalue.getAttid());
        if(attributetbl.getName().equalsIgnoreCase("公称直径") || attributetbl.getName().equalsIgnoreCase("长度")){
            this.checkValue(attvalue,attributetbl.getName());
        }else  if(attributetbl.getName().equalsIgnoreCase("牙距")){
            attvalue.setParamvalue(convertValue(attvalue.getParamvalue()));
        }else  if(attributetbl.getName().equalsIgnoreCase("厚度")) {
            attvalue.setParamvalue(convertValue(attvalue.getParamvalue()));
        }


        attvalueService.updateById(attvalue);

        basicRet.setMessage("修改成功");
        basicRet.setResult(BasicRet.SUCCESS);
        Admin admin = (Admin) model.asMap().get(AppConstant.ADMIN_SESSION_NAME);
        adminLogOperator.saveAdminLog(admin,"品名:"+productName+"的属性:"+attrname+"修改属性值:"+paramvalue,(short)3,"attvalue",request,adminOperateLogService);
        return basicRet;
    }



    /**
     * 处理属性数据
     * @param
     */
    private void checkValue(Attvalue attvalue,String attributeName){
        String attv = attvalue.getParamvalue();
        if(attributeName.equals("公称直径")) {
            if (attvalue.getParamvalue().startsWith("#")) {
                attv = attv.substring(1, attv.length()) + "#";
                attvalue.setParamvalue(attv);
            }else if(attv.endsWith("\"") || attv.endsWith("″")){
                attv = attv.substring(0, attv.length()-1);
                attvalue.setParamvalue(attv);
            }else if(attv.startsWith("φ") || attv.startsWith("∮")){
                attv = "Ф"+attv.substring(1, attv.length());
                attvalue.setParamvalue(attv);
            }
        }else if(attributeName.equals("长度")){
            if(attv.endsWith("\"") || attv.endsWith("″")) {
                attv = attv.substring(0, attv.length() - 1);
                attvalue.setParamvalue(attv);
            }
        }
    }


    @RequestMapping(value = "/attrvalue/del",method = RequestMethod.POST)
    @ApiOperation("删除属性值")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "属性值id",required = true,dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "productName",value = "品名",required = true,dataType = "string",paramType = "query"),
            @ApiImplicitParam(name = "attrname",value = "属性名",required = true,dataType = "string",paramType = "query"),
    })
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.PRODUCTATTRIBUTE + "')")
    public BasicRet delAttrvalue(@RequestParam(required = true) long id,
                                 Model model,HttpServletRequest request,String productName,String attrname){
        BasicRet basicRet = new BasicRet();
        Attvalue attvalue = attvalueService.getById(id);
        attvalueService.delById(id);

        basicRet.setMessage("删除成功");
        basicRet.setResult(BasicRet.SUCCESS);
        Admin admin = (Admin) model.asMap().get(AppConstant.ADMIN_SESSION_NAME);
        adminLogOperator.saveAdminLog(admin,"品名:"+productName+"的属性:"+attrname+"删除属性值:"+attvalue.getParamvalue(),(short)2,"attvalue",request,adminOperateLogService);
        return basicRet;
    }





    @RequestMapping(value = "/attrvalue/add",method = RequestMethod.POST)
    @ApiOperation("添加属性值")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "attid",value = "属性id",required = true,dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "paramvalue",value = "属性值",required = true,dataType = "string",paramType = "query"),
            @ApiImplicitParam(name = "sort",value = "排序",required = true,dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "mark",value = "备注",required = false,dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "productName",value = "品名",required = true,dataType = "string",paramType = "query"),
            @ApiImplicitParam(name = "attrname",value = "属性名",required = true,dataType = "string",paramType = "query"),
    })
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.PRODUCTATTRIBUTE + "')")
    public BasicRet addAttrvalue(@RequestParam(required = true) long attid,
                                 @RequestParam(required = true) String paramvalue,
                                 @RequestParam(required = true) int sort,
                                 @RequestParam(required = false,defaultValue = "")String mark,
                                 Model model,HttpServletRequest request,String productName,String attrname){
        BasicRet basicRet = new BasicRet();

        Attvalue attvalue = attvalueService.getByAttidAndValue(attid,paramvalue);
        if(attvalue != null){
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("该属性已经添加了该值了");
            return  basicRet;
        }

        attvalue = new Attvalue();
        attvalue.setAttid(attid);
        attvalue.setParamvalue(paramvalue);
        attvalue.setSort(sort);
        attvalue.setMark(mark);


        Attributetbl attributetbl = attributetblService.selectByPrimaryKey(attvalue.getAttid());
        if(attributetbl.getName().equalsIgnoreCase("公称直径") || attributetbl.getName().equalsIgnoreCase("长度")){
            this.checkValue(attvalue,attributetbl.getName());
        }else  if(attributetbl.getName().equalsIgnoreCase("牙距")){
            attvalue.setParamvalue(this.convertValue(attvalue.getParamvalue()));
        }else  if(attributetbl.getName().equalsIgnoreCase("厚度")){
            attvalue.setParamvalue(this.convertValue(attvalue.getParamvalue()));
        }


        attvalueService.add(attvalue);


        basicRet.setMessage("添加成功");
        basicRet.setResult(BasicRet.SUCCESS);
        Admin admin = (Admin) model.asMap().get(AppConstant.ADMIN_SESSION_NAME);
        adminLogOperator.saveAdminLog(admin,"品名:"+productName+"的属性:"+attrname+"新增属性值:"+paramvalue,(short)1,"attvalue",request,adminOperateLogService);
        return basicRet;
    }


    public static String convertValue(String value) {
        if(StringUtils.isNumeric(value)){
            //牙距值小于 10 的整数（不包含 10），自动为其小数点后补一位 0；
            if(Double.parseDouble(value) < 10 && StringUtils.isInteger(value)){
                return value+".0";
            }else if(Double.parseDouble(value) >=10){
                return BigDecimal.valueOf(Double.parseDouble(value))
                        .stripTrailingZeros().toPlainString();
            }else{
                Pattern pattern = Pattern.compile("(\\d+)\\.(0+)$");
                Matcher matcher = pattern.matcher(value);
                if(matcher.find()){
                    //System.out.println(St);
                    return BigDecimal.valueOf(Double.parseDouble(value))
                            .stripTrailingZeros().toPlainString()+".0";
                }else{
                    return BigDecimal.valueOf(Double.parseDouble(value))
                            .stripTrailingZeros().toPlainString();
                }
            }
        }
        return value;
    }




    @RequestMapping(value = "/attvalue/getByProdnameidAndName",method = RequestMethod.POST)
    @ApiOperation("根据品名id和属性名查询属性值")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "品名id",name = "productnameid",paramType = "query",dataType = "int",required = true),
            @ApiImplicitParam(value = "属性名",name = "name",paramType = "query",dataType = "int",required = true),
    })
    public AttrValueRet getAttvalueByProdNameidAndName(long productnameid,String name){
        AttrValueRet attrValueRet =  new AttrValueRet();

        List<Attvalue> list = attvalueService.listByProductnameidAndName(productnameid,name);

        attrValueRet.data.list =  list;
        attrValueRet.setMessage("查询成功");
        attrValueRet.setResult(BasicRet.SUCCESS);
        return  attrValueRet;
    }





    @RequestMapping(value = "/productname/getAttribute",method = RequestMethod.POST)
    @ApiOperation("根据品名id查询有哪些属性")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "品名id",required = true,dataType = "int",paramType = "query"),
    })
    public  ProdNameAttributeRet listAttributes(@RequestParam(required = true) long id){
        ProdNameAttributeRet prodNameAttributeRet = new ProdNameAttributeRet();

        List<Attributetbl> list =  attributetblService.getAttributeListByProductnameid(id);
        prodNameAttributeRet.list =  list;
        prodNameAttributeRet.setMessage("查询成功");
        prodNameAttributeRet.setResult(BasicRet.SUCCESS);
        return  prodNameAttributeRet;
    }



    @RequestMapping(value = "/productname/detail",method = RequestMethod.POST)
    @ApiOperation("品名详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "品名id",required = true,dataType = "int",paramType = "query"),
    })
    public  ProductNameRet detailProductName(@RequestParam(required = true) long id){
        ProductNameRet productNameRet = new ProductNameRet();

       ProductName productName =  productNameService.getById(id);

       if(productName == null){
           productNameRet.setMessage("未查询到该品名信息");
           productNameRet.setResult(BasicRet.SUCCESS);
           return  productNameRet;
       }

       productNameRet.productName = productName;
       productNameRet.setMessage("查询成功");
       productNameRet.setResult(BasicRet.SUCCESS);
       return  productNameRet;
    }



    @RequestMapping(value = "/productname/detailWithAttr",method = RequestMethod.POST)
    @ApiOperation("品名详细信息(包含该品名下的属性和属性值)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "品名id",required = true,dataType = "int",paramType = "query"),
    })
    public  ProductNameWithAttvalRet detailProductNameWithAttr(@RequestParam(required = true) long id){
        ProductNameWithAttvalRet productNameWithAttvalRet = new ProductNameWithAttvalRet();

        ProductName productName =  productNameService.getById(id);

        if(productName == null){
            productNameWithAttvalRet.setMessage("未查询到该品名信息");
            productNameWithAttvalRet.setResult(BasicRet.SUCCESS);
            return  productNameWithAttvalRet;
        }


        List<Attributetbl> attributetblList =  attributetblService.getAttributeWithValue(productName.getId());

        productNameWithAttvalRet.data.productName = productName;
        productNameWithAttvalRet.data.attributetblList =  attributetblList;
        productNameWithAttvalRet.setMessage("查询成功");
        productNameWithAttvalRet.setResult(BasicRet.SUCCESS);
        return  productNameWithAttvalRet;
    }




    @RequestMapping(value = "/productname/list",method = RequestMethod.POST)
    @ApiOperation("品名列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo",value = "第几页",required = true,dataType = "int",paramType = "query",defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize",value = "每页多少条",required = true,dataType = "int",paramType = "query",defaultValue = "20"),
            @ApiImplicitParam(name = "name",value = "名称",required = false,dataType = "string",paramType = "query"),
            @ApiImplicitParam(name = "typeid",value = "分类id",required = false,dataType = "int",paramType = "query",defaultValue = "-1"),
            @ApiImplicitParam(name = "prodno",value = "品名编号",required = false,dataType = "string",paramType = "query"),

    })
    public PageRet listProductName(@RequestParam(required = true,defaultValue = "1") int pageNo,
                                   @RequestParam(required = true,defaultValue = "20") int pageSize,
                                   @RequestParam(required = false,defaultValue = "-1") long typeid,
                                   @RequestParam(required = false,defaultValue = "") String prodno,
                                   @RequestParam(required = false,defaultValue = "") String name){
        PageRet pageRet =  new PageRet();
        PageInfo pageInfo = productNameService.getListByPage(pageNo,pageSize,typeid,name,prodno);

        pageRet.data.setPageInfo(pageInfo);
        pageRet.setResult(BasicRet.SUCCESS);
        pageRet.setMessage("查询成功");
        return  pageRet;
    }


    @RequestMapping(value = "/productname/listByCatid",method = RequestMethod.POST)
    @ApiOperation("按照分类查询品名")
    public  ProductNameListRet listProductNameByCatid(long catid){
        ProductNameListRet ret = new ProductNameListRet();

        ret.list =  productNameService.getByCategoryId(catid);
        ret.setResult(BasicRet.SUCCESS);
        return  ret;
    }



    @RequestMapping(value = "/productname/del",method = RequestMethod.POST)
    @ApiOperation("删除品名")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "品名id",name = "id",dataType = "int",paramType = "query",required = true)
    })
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.IDENTITYMANAGEMENT + "')")
    public  BasicRet delProductName(@RequestParam(required = true) long id,Model model,HttpServletRequest request){
        BasicRet basicRet = new BasicRet();

        ProductInfoExample example =new ProductInfoExample();
        ProductInfoExample.Criteria criteria = example.createCriteria();
        criteria.andProductnameidEqualTo(id).andPdstateNotEqualTo(Quantity.STATE_6);
        int count = productInfoService.countByExample(example);
        if(count>0){
            basicRet.setMessage("有其他商品关联此品名");
            basicRet.setResult(BasicRet.ERR);
            return  basicRet;
        }
        ProductName productName = productNameService.getById(id);
        productNameService.deleteById(id);

        basicRet.setMessage("删除成功");
        basicRet.setResult(BasicRet.SUCCESS);
        Admin admin = (Admin) model.asMap().get(AppConstant.ADMIN_SESSION_NAME);
        adminLogOperator.saveAdminLog(admin,"删除品名:"+productName.getName(),(short)2,"productname",request,adminOperateLogService);
        return  basicRet;
    }



    @RequestMapping(value = "/productname/update",method = RequestMethod.POST)
    @ApiOperation("修改品名")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "品名id",name = "id",dataType = "int",paramType = "query",required = true),
            @ApiImplicitParam(name = "name",value = "名称",required = true,dataType = "string",paramType = "query"),
            @ApiImplicitParam(name = "typeid",value = "分类id",required = true,dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "prodno",value = "品名编号",required = true,dataType = "string",paramType = "query"),
            @ApiImplicitParam(name = "unit",value = "计量单位",required = true,dataType = "string",paramType = "query"),
            @ApiImplicitParam(name = "mark",value = "备注",required = false,dataType = "string",paramType = "query"),
            @ApiImplicitParam(name = "pic",value = "图片",required = false,dataType = "string",paramType = "query"),
    })
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.IDENTITYMANAGEMENT + "')")
    public  BasicRet updateProductName(@RequestParam(required = true) long id,
                                       @RequestParam(required = true) String name,
                                       @RequestParam(required = true) long typeid,
                                       @RequestParam(required = true) String prodno,
                                       @RequestParam(required =  true) String unit,
                                       @RequestParam(defaultValue = "") String mark,
                                       @RequestParam(required = true)String pic,
                                       HttpServletRequest request,
                                       Model model){
        BasicRet basicRet = new BasicRet();

        Categories categories =  categoriesService.getById(typeid);
        if(categories == null){
            basicRet.setMessage("分类不存在");
            basicRet.setResult(BasicRet.ERR);
            return  basicRet;
        }

        ProductName productName = productNameService.getByCateidAndName((long) typeid,name);
        if(productName != null && !productName.getId().equals(id) ){
            basicRet.setMessage("该分类下已存在此品名");
            basicRet.setResult(BasicRet.ERR);
            return  basicRet;
        }

        productName = new ProductName();
        productName.setId(id);
        productName.setName(name);
        productName.setTypeid(typeid);
        productName.setUnit(unit);
        productName.setMark(mark);
        productName.setProdno(prodno);
        productName.setPic(pic);

        productNameService.updateByPrimaryKeySelective(productName);

        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("修改成功");
        Admin admin = (Admin) model.asMap().get(AppConstant.ADMIN_SESSION_NAME);
        //保存管理员修改品名日志
        adminLogOperator.saveAdminLog(admin,"修改品名:"+name,(short)3,"productname",request,adminOperateLogService);
        return  basicRet;
    }


    @RequestMapping(value = "/productname/add",method = RequestMethod.POST)
    @ApiOperation("添加品名")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name",value = "名称",required = true,dataType = "string",paramType = "query"),
            @ApiImplicitParam(name = "typeid",value = "分类id",required = true,dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "prodno",value = "品名编号",required = true,dataType = "string",paramType = "query"),
            @ApiImplicitParam(name = "unit",value = "计量单位",required = true,dataType = "string",paramType = "query"),
            @ApiImplicitParam(name = "attrJson",value = "显示名（需要拼装成json格式，例：[{\"name\": \"规格\",\"connector\": \"-\"},{\"name\": \"净含量\",\"connector\": \"-\"}]）",required = true,dataType = "string",paramType = "query"),
            @ApiImplicitParam(name = "mark",value = "备注",required = false,dataType = "string",paramType = "query"),
            @ApiImplicitParam(name = "pic",value = "图片",required = true,dataType = "string",paramType = "query"),
    })
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.IDENTITYMANAGEMENT + "')")
    public BasicRet addProductName(@RequestParam(required = true) String name,
                                   @RequestParam(required = true) int typeid,
                                   @RequestParam(required = true) String prodno,
                                   @RequestParam(required =  true) String unit,
                                   @RequestParam(required = true) String attrJson,
                                   @RequestParam(defaultValue = "") String mark,
                                   @RequestParam(required = true) String pic,
                                   Model model,HttpServletRequest request){
        BasicRet basicRet = new BasicRet();

        if(!CommonUtils.isGoodJson(attrJson)){
            basicRet.setMessage("显示名没有拼装成json");
            basicRet.setResult(BasicRet.ERR);
            return  basicRet;
        }

        Categories categories =  categoriesService.getById(typeid);
        if(categories == null){
            basicRet.setMessage("分类不存在");
            basicRet.setResult(BasicRet.ERR);
            return  basicRet;
        }

        ProductName productName = productNameService.getByCateidAndName((long) typeid,name);
        if(productName != null){
            basicRet.setMessage("该分类下已存在此品名");
            basicRet.setResult(BasicRet.ERR);
            return  basicRet;
        }

        productName = new ProductName();
        productName.setName(name);
        productName.setTypeid((long) typeid);
        productName.setUnit(unit);
        productName.setMark(mark);
        productName.setProdno(prodno);
        productName.setPic(pic);
        productNameService.add(productName);

        Gson gson = new Gson();
        List<Attributetbl> attributetblList = gson.fromJson(attrJson,new TypeToken<List<Attributetbl>>() {}.getType());
        for(Attributetbl attributetbl : attributetblList){
            attributetbl.setProductnameid(productName.getId());
            attributetblService.add(attributetbl);
        }

        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("添加成功");
        Admin admin = (Admin) model.asMap().get(AppConstant.ADMIN_SESSION_NAME);
        adminLogOperator.saveAdminLog(admin,"新增品名:"+name,(short)1,"productname",request,adminOperateLogService);
        return  basicRet;
    }


    @RequestMapping(value = "/materialManage",method = RequestMethod.POST)
    @ApiOperation(value = "根据id查询该材质下的所有牌号")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "materialId",value = "材质id",required = true,paramType = "query",dataType = "int")
    })
    public MaterialManageRet materialManage(@RequestParam(required = true) long materialId){
        MaterialManageRet materialManageRet = new MaterialManageRet();

        Material material =    materialService.getById(materialId);
        if(material == null){
            materialManageRet.setMessage("未查询到该材质信息");
            materialManageRet.setResult(BasicRet.ERR);
            return  materialManageRet;
        }

        List<CardNum> cardNumList = cardNumService.getListByMaterialid(materialId);

        materialManageRet.material = material;
        materialManageRet.cardNumList = cardNumList;
        materialManageRet.setResult(BasicRet.SUCCESS);
        materialManageRet.setMessage("查询成功");
        return  materialManageRet;
    }


    @RequestMapping(value = "/cardnum/del",method = RequestMethod.POST)
    @ApiOperation(value = "删除牌号")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "ID", name = "id", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(value = "材质名称", name = "materialName", required = true, paramType = "query", dataType = "string"),
    })
    public BasicRet delCardNum(@RequestParam(required = true) long id,Model model,HttpServletRequest request,@RequestParam(required = true)String materialName){
        BasicRet basicRet = new BasicRet();
        ProductInfoExample example =new ProductInfoExample();
        ProductInfoExample.Criteria criteria = example.createCriteria();
        criteria.andCardnumidEqualTo(id);
        int count = productInfoService.countByExample(example);
        if(count>0){
            basicRet.setMessage("有其他商品关联此牌号");
            basicRet.setResult(BasicRet.ERR);
            return  basicRet;
        }
        CardNum cardnum = cardNumService.getById(id);
        cardNumService.delete(id);

        basicRet.setMessage("删除成功");
        basicRet.setResult(BasicRet.SUCCESS);
        Admin admin = (Admin) model.asMap().get(AppConstant.ADMIN_SESSION_NAME);
        adminLogOperator.saveAdminLog(admin,"材质:"+materialName+"删除牌号:"+cardnum.getName(),(short)2,"cardnum",request,adminOperateLogService);
        return  basicRet;
    }



    @RequestMapping(value = "/cardnum/add",method = RequestMethod.POST)
    @ApiOperation(value = "添加牌号")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "牌号",name = "name",required = true,paramType = "query",dataType = "string"),
            @ApiImplicitParam(value = "材质id",name = "matialid",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(value = "序号",name = "number",required = true,paramType = "query",dataType = "int"),
    })
    public BasicRet addCardNum(@RequestParam(required =  true) String name,
                               @RequestParam(required = true) long matialid,
                               @RequestParam(required = true) int number,
                               Model model,HttpServletRequest request){
        BasicRet basicRet = new BasicRet();

        Material material =  materialService.getById(matialid);

        if(material == null){
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("材质不存在");
            return  basicRet;
        }

        //查询该材质下是否已经有了相同的品名了
        CardNum cardNum = cardNumService.getByMaterialIdAndName(matialid,name);

        if(cardNum != null){
            basicRet.setMessage("已经存在该牌名了");
            basicRet.setResult(BasicRet.ERR);
            return  basicRet;
        }

        cardNum = new CardNum();
        cardNum.setMatialid(matialid);
        cardNum.setName(name);
        cardNum.setNumber(number);

        cardNumService.add(cardNum);

        basicRet.setMessage("添加成功");
        basicRet.setResult(BasicRet.SUCCESS);
        Admin admin = (Admin) model.asMap().get(AppConstant.ADMIN_SESSION_NAME);
        adminLogOperator.saveAdminLog(admin,"材质:"+material.getName()+"新增牌号:"+name,(short)1,"cardnum",request,adminOperateLogService);
        return  basicRet;
    }


    @RequestMapping(value = "/cardnum/get",method = RequestMethod.POST)
    @ApiOperation(value = "获取牌号信息")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "id",name = "id",required = true,paramType = "query",dataType = "int"),
    })
    public  CardNumRet getCardNum(@RequestParam(required = true) long id){
        CardNumRet cardNumRet = new CardNumRet();

        CardNum cardNum = cardNumService.getById(id);
        if(cardNum == null){
            cardNumRet.setMessage("该牌号不存在");
            cardNumRet.setResult(BasicRet.ERR);
            return  cardNumRet;
        }

        cardNumRet.data.cardNum  =  cardNum;
        cardNumRet.setResult(BasicRet.SUCCESS);
        cardNumRet.setMessage("查询成功");
        return  cardNumRet;
    }



    @RequestMapping(value = "/cardnum/update",method = RequestMethod.POST)
    @ApiOperation(value = "更新牌号信息")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "id",name = "id",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(value = "牌号",name = "name",required = true,paramType = "query",dataType = "string"),
            @ApiImplicitParam(value = "材质id",name = "matialid",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(value = "序号",name = "number",required = true,paramType = "query",dataType = "int"),
    })
    public BasicRet updateCardNum(
                               @RequestParam(required =  true) long id,
                               @RequestParam(required =  true) String name,
                               @RequestParam(required = true) long matialid,
                               @RequestParam(required = true) int number,
                               Model model,HttpServletRequest request){
        BasicRet basicRet = new BasicRet();

        Material material =  materialService.getById(matialid);

        if(material == null){
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("材质不存在");
            return  basicRet;
        }

        //查询该材质下是否已经有了相同的品名了
        CardNum cardNum = cardNumService.getByMaterialIdAndName(matialid,name);

        if(cardNum != null && cardNum.getId() != id){
            basicRet.setMessage("已经存在该牌名了");
            basicRet.setResult(BasicRet.ERR);
            return  basicRet;
        }

        cardNum = new CardNum();
        cardNum.setId(id);
        cardNum.setMatialid(matialid);
        cardNum.setName(name);
        cardNum.setNumber(number);
        cardNumService.update(cardNum);

        basicRet.setMessage("修改成功");
        basicRet.setResult(BasicRet.SUCCESS);
        Admin admin = (Admin) model.asMap().get(AppConstant.ADMIN_SESSION_NAME);
        adminLogOperator.saveAdminLog(admin,"材质:"+material.getName()+"修改牌号:"+name,(short)3,"cardnum",request,adminOperateLogService);
        return  basicRet;
    }



    @RequestMapping(value = "/material/del",method = RequestMethod.POST)
    @ApiOperation(value = "根据id删除材质信息")
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.MATERIALMANAGEMENT + "')")
    public  BasicRet delMaterial(@RequestParam(required =  true)long id, Model model, HttpServletRequest request){
        BasicRet basicRet = new BasicRet();

        ProductInfoExample example =new ProductInfoExample();
        ProductInfoExample.Criteria criteria = example.createCriteria();
        criteria.andMaterialidEqualTo(id);
        int count = productInfoService.countByExample(example);
        if(count>0){
            basicRet.setMessage("有其他商品关联此材质");
            basicRet.setResult(BasicRet.ERR);
            return  basicRet;
        }
        Material material = materialService.getById(id);
        materialService.deleteById(id);

        basicRet.setMessage("删除成功");
        basicRet.setResult(BasicRet.SUCCESS);
        Admin admin = (Admin) model.asMap().get(AppConstant.ADMIN_SESSION_NAME);
        adminLogOperator.saveAdminLog(admin,"删除材质:"+material.getName(),(short)2,"material",request,adminOperateLogService);
        return  basicRet;
    }



    @RequestMapping(value = "/material/delete",method = RequestMethod.POST)
    @ApiOperation(value = "根据id获取材质")
    public  Material getMaterial(@RequestParam(required =  true)long id){
        Material material =  materialService.getById(id);
        return material;
    }





    @RequestMapping(value = "/material/add",method = RequestMethod.POST)
    @ApiOperation(value = "添加材质")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "材质名称",name = "name",required = true,paramType = "query",dataType = "string"),
            @ApiImplicitParam(value = "材质编号",name = "number",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(value = "图片",name = "img",required = false,paramType = "query",dataType = "string"),
    })
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.MATERIALMANAGEMENT + "')")
    public BasicRet addMaterial(@RequestParam(required =  true)String name,
                                @RequestParam(required = true) int number,
                                @RequestParam(required = false) String img,
                                Model model,HttpServletRequest request){
        BasicRet basicRet = new BasicRet();

        Material material = materialService.getByName(name);
        if(material != null){
            basicRet.setMessage("已经存在该材质，不可重复添加");
            basicRet.setResult(BasicRet.ERR);
            return  basicRet;
        }

        material = new Material();
        material.setName(name);
        material.setNumber(number);
        material.setImg(img);
        materialService.add(material);

        basicRet.setMessage("添加成功");
        basicRet.setResult(BasicRet.SUCCESS);
        Admin admin = (Admin) model.asMap().get(AppConstant.ADMIN_SESSION_NAME);
        adminLogOperator.saveAdminLog(admin,"新增材质:"+name,(short)1,"material",request,adminOperateLogService);
        return  basicRet;
    }



    @RequestMapping(value = "/material/update",method = RequestMethod.POST)
    @ApiOperation(value = "修改材质")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "id",name = "id",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(value = "材质名称",name = "name",required = true,paramType = "query",dataType = "string"),
            @ApiImplicitParam(value = "材质编号",name = "number",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(value = "图片",name = "img",required = false,paramType = "query",dataType = "string"),
    })
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.MATERIALMANAGEMENT + "')")
    public BasicRet updateMaterial(
                                @RequestParam(required = true) long id,
                                @RequestParam(required =  true)String name,
                                @RequestParam(required = true) int number,
                                @RequestParam(required = false) String img,
                                Model model , HttpServletRequest request){
        BasicRet basicRet = new BasicRet();


        Material dbMaterial = materialService.getByName(name);
        if(dbMaterial != null && !dbMaterial.getId().equals(id)){
            basicRet.setMessage("已经存在该材质，名称不可重复");
            basicRet.setResult(BasicRet.ERR);
            return  basicRet;
        }


        Material material =  new Material();
        material.setId(id);
        material.setName(name);
        material.setNumber(number);
        material.setImg(img);

        materialService.update(material);

        basicRet.setMessage("修改成功");
        basicRet.setResult(BasicRet.SUCCESS);
        Admin admin = (Admin) model.asMap().get(AppConstant.ADMIN_SESSION_NAME);
        adminLogOperator.saveAdminLog(admin,"修改材质:"+name,(short)3,"material",request,adminOperateLogService);
        return  basicRet;
    }




    @RequestMapping(value = "/material/list",method = RequestMethod.POST)
    @ApiOperation(value = "列表查询材质信息")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "第几页",name = "pageNo",required = true,paramType = "query",dataType = "int",defaultValue = "1"),
            @ApiImplicitParam(value = "每页多少条",name = "pageSize",required = true,paramType = "query",dataType = "int",defaultValue = "20"),
            @ApiImplicitParam(value = "材质名称",name = "name",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(value = "材质编号",name = "number",required = false,paramType = "query",dataType = "int"),
    })
    public PageRet listMaterial(
            @RequestParam(required = true,defaultValue = "1") int pageNo,
            @RequestParam(required =  true,defaultValue = "20")int  pageSize,
            @RequestParam(required =  false,defaultValue = "0")int  number,
            @RequestParam(required = false,defaultValue = "") String name){
         PageRet pageRet = new PageRet();
         PageInfo pageInfo =  materialService.getList(pageNo,pageSize,name,number);

         pageRet.data.setPageInfo(pageInfo);
         pageRet.setResult(BasicRet.SUCCESS);
         return  pageRet;

    }




    private  class CardNumRet extends  BasicRet{
        private class CardNumData{
            private  CardNum cardNum;

            public CardNum getCardNum() {
                return cardNum;
            }

            public void setCardNum(CardNum cardNum) {
                this.cardNum = cardNum;
            }
        }

        private  CardNumData data = new CardNumData();

        public CardNumData getData() {
            return data;
        }

        public void setData(CardNumData data) {
            this.data = data;
        }
    }



    private  class  AttrValueRet extends  BasicRet{
        private  class  Data{
            private List<Attvalue> list;

            public List<Attvalue> getList() {
                return list;
            }

            public void setList(List<Attvalue> list) {
                this.list = list;
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




    private class MaterialManageRet extends  BasicRet{
        private  List<CardNum> cardNumList;

        private  Material material;

        public Material getMaterial() {
            return material;
        }

        public List<CardNum> getCardNumList() {
            return cardNumList;
        }
    }



    private  class  ProductNameRet extends BasicRet{
        private  ProductName productName;

        public ProductName getProductName() {
            return productName;
        }

    }


    private  class  ProductNameListRet extends BasicRet{
       private  List<ProductName> list;

        public List<ProductName> getList() {
            return list;
        }

        public void setList(List<ProductName> list) {
            this.list = list;
        }
    }



    private  class  ProdNameAttributeRet extends  BasicRet{
        private  List<Attributetbl> list;

        public List<Attributetbl> getList() {
            return list;
        }
    }


    private  class  ProductNameWithAttvalRet extends  BasicRet{
          private class ProductNameData{

              @ApiModelProperty(value = "品名信息")
              private  ProductName productName;
              @ApiModelProperty(value = "属性列表")
              private  List<Attributetbl> attributetblList;

              public ProductName getProductName() {
                  return productName;
              }

              public void setProductName(ProductName productName) {
                  this.productName = productName;
              }

              public List<Attributetbl> getAttributetblList() {
                  return attributetblList;
              }

              public void setAttributetblList(List<Attributetbl> attributetblList) {
                  this.attributetblList = attributetblList;
              }
          }


          private  ProductNameData data = new ProductNameData();

        public ProductNameData getData() {
            return data;
        }

        public void setData(ProductNameData data) {
            this.data = data;
        }
    }



}
