package project.jinshang.mod_product;

import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import io.swagger.annotations.*;
import mizuki.project.core.restserver.config.BasicRet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import project.jinshang.common.bean.MemberLogOperator;
import project.jinshang.common.bean.Packing;
import project.jinshang.common.bean.PageRet;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.common.constant.Quantity;
import project.jinshang.common.constant.SellerAuthorityConst;
import project.jinshang.common.exception.MyException;
import project.jinshang.common.utils.*;
import project.jinshang.mod_admin.mod_transet.bean.TransactionSetting;
import project.jinshang.mod_admin.mod_transet.service.TransactionSettingService;
import project.jinshang.mod_admin.mod_upload.ProductBatchImport;
import project.jinshang.mod_admin.mod_upload.ProductImportModel;
import project.jinshang.mod_cash.bean.SalerCapital;
import project.jinshang.mod_cash.service.SalerCapitalService;
import project.jinshang.mod_common.bean.BasicExtRet;
import project.jinshang.mod_company.bean.SellerCompanyInfo;
import project.jinshang.mod_company.bean.Store;
import project.jinshang.mod_company.service.SellerCompanyCacheService;
import project.jinshang.mod_company.service.SellerCompanyInfoService;
import project.jinshang.mod_company.service.StoreService;
import project.jinshang.mod_member.bean.Member;
import project.jinshang.mod_member.service.MemberService;
import project.jinshang.mod_product.bean.*;
import project.jinshang.mod_product.bean.dto.*;
import project.jinshang.mod_product.service.*;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * create : wyh
 * date : 2017/11/10
 */

@RestController
@RequestMapping("/rest/seller/product")
@Api(tags = "卖家产品相关接口", description = "卖家操作产品相关的接口，发布产品、修改产品、上架产品、下架产品、删除产品等操作")
@Transactional(rollbackFor = Exception.class)
@SessionAttributes({AppConstant.MEMBER_SESSION_NAME})
public class ProductSellerAction {

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
    private StoreService storeService;

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private ProductStoreService productStoreService;

    @Autowired
    private ProductAttrService productAttrService;

    @Autowired
    private AttributetblService attributetblService;

    @Autowired
    private AttvalueService attvalueService;

    @Autowired
    private ShippingTemplatesService shippingTemplatesService;

    @Autowired
    private ProductSearchService productSearchService;

    @Autowired
    private TransactionSettingService transactionSettingService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private SalerCapitalService salerCapitalService;

    @Autowired
    private PdbailLogService pdbailLogService;

    @Autowired
    private ProductsService productsService;

    @Autowired
    private OtherProdService otherProdService;

    @Autowired
    private MemberLogOperator memberLogOperator;

    @Autowired
    private MemberOperateLogService memberOperateLogService;

    @Autowired
    private ProductBatchImport productBatchImport;

    @Autowired
    private SellerCompanyInfoService sellerCompanyInfoService;

    @Autowired
    private SellerCompanyCacheService sellerCompanyCacheService;

    @Autowired
    private ShippingTemplateGroupService shippingTemplateGroupService;

    @Autowired
    private  Gson gson;


    @Value("${shop.self-support.id}")
    private String shopSelfSupportid;

    @Value("${upload.dir.moduleIcon}")
    private  String uploadPath;



    @RequestMapping(value = "/getProdNotPassReason", method = RequestMethod.POST)
    @ApiOperation("查询商品未通过原因")
    public NotPassReasonRet getProdNotPassReason(
            @RequestParam(required = true) long id, Model model
    ) {
        NotPassReasonRet ret = new NotPassReasonRet();

        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        ProductInfo productInfo = productInfoService.getById(id);

        if (productInfo == null) {
            ret.setMessage("要查询的商品不存在");
            ret.setResult(BasicRet.ERR);
            return ret;
        }
        if (!productInfo.getMemberid().equals(member.getId())) {
            ret.setMessage("要查询的商品不属于你");
            ret.setResult(BasicRet.ERR);
            return ret;
        }

        if (productInfo.getPdstate() != 3 && productInfo.getPdstate() != Quantity.STATE_7) {
            ret.setMessage("商品状态不是未审核通过状态");
            ret.setResult(BasicRet.ERR);
            return ret;
        }
        ret.setResult(BasicRet.SUCCESS);
        ret.setMessage("查询成功");
        ret.data.reason = productInfo.getReason();
        return ret;
    }


    private class NotPassReasonRet extends BasicRet {
        private class NotPassReasonData {
            private String reason;

            public String getReason() {
                return reason;
            }

            public void setReason(String reason) {
                this.reason = reason;
            }
        }

        private NotPassReasonData data = new NotPassReasonData();

        public NotPassReasonData getData() {
            return data;
        }

        public void setData(NotPassReasonData data) {
            this.data = data;
        }
    }


    @RequestMapping(value = "/delProduct", method = RequestMethod.POST)
    @ApiOperation("删除商品")
    public BasicRet delProduct(@RequestParam(required = true) long productid, Model model, HttpServletRequest request) {
        BasicRet basicRet = new BasicRet();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        ProductInfo productInfo = productInfoService.getById(productid);

        if (productInfo == null) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("要删除的商品不存在");
            return basicRet;
        }

        if (!productInfo.getMemberid().equals(member.getId())) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("要删除的商品不属于你");
            return basicRet;
        }

        if (productInfo.getPdstate() == 4) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("已上架商品不可删除");
            return basicRet;
        }

        ProductInfo info = new ProductInfo();
        info.setPdstate((short) 6);
        info.setId(productInfo.getId());
        productInfoService.updateByPrimaryKeySelective(info);


        //保存日志
        memberLogOperator.saveMemberLog(member, null, "删除商品：" + productInfo.getProductname(), request, memberOperateLogService);


        basicRet.setMessage("删除成功");
        basicRet.setResult(BasicRet.SUCCESS);
        return basicRet;
    }


    @RequestMapping(value = "/FastenerProduct/update", method = RequestMethod.POST)
    @ApiOperation("修改紧固件商品")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "紧固件商品库id", name = "productsid", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(value = "商品副标题", name = "subtitle", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "商品价格", name = "prodprice", required = true, dataType = "float", paramType = "query"),
            @ApiImplicitParam(value = "起订量", name = "startnum", required = true, dataType = "double", paramType = "query"),
            @ApiImplicitParam(value = "加购量", name = "minplus", required = false, dataType = "double",  paramType = "query"),
            @ApiImplicitParam(value = "是否开启阶梯价格", name = "stepwiseprice", required = true, dataType = "boolean", defaultValue = "false", paramType = "query"),
            @ApiImplicitParam(value = "3天发货价格", name = "threeprice", required = false, dataType = "float", paramType = "query"),
            @ApiImplicitParam(value = "90天发货价格", name = "ninetyprice", required = false, dataType = "float", paramType = "query"),
            @ApiImplicitParam(value = "30天发货价格", name = "thirtyprice", required = false, dataType = "float", paramType = "query"),
            @ApiImplicitParam(value = "60天发货价格", name = "sixtyprice", required = false, dataType = "float", paramType = "query"),
            @ApiImplicitParam(value = "区间价格json,例如：[{\"start\": 1,\"end\": 10,\"rate\": 90},{\"start\": 11,\"end\": 20,\"rate\": 80},{\"start\": 21,\"end\": 0,\"rate\": 70}]", name = "intervalprice", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "市场价", name = "marketprice", required = true, dataType = "float", paramType = "query"),
            @ApiImplicitParam(value = "成本价", name = "costprice", required = false, dataType = "float", paramType = "query"),
            @ApiImplicitParam(value = "库存", name = "pdstorenum", required = true, dataType = "float", defaultValue = "1", paramType = "query"),
            @ApiImplicitParam(value = "仓库id", name = "storeid", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(value = "运费方式", name = "shippingtemplatesgroup", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(value = "是否推荐", name = "recommended", required = false, dataType = "boolean", paramType = "query"),
            @ApiImplicitParam(value = "seo标题", name = "seotitle", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "seo关键字", name = "seokey", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "seo描述", name = "seovalue", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "商品状态 0=放入仓库 1=待审核(立即发布) 2=审核通过 3=未通过 4=已上架(上架) 5=下架 6=删除 7-违规下架", name = "pdstate", required = true, dataType = "int", paramType = "query", defaultValue = "0"),
            @ApiImplicitParam(value = "商品编码", name = "pdno", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "折扣比例", name = "discountratio", required = true, dataType = "float", paramType = "query"),
    })
    @PreAuthorize("hasAuthority('" + SellerAuthorityConst.POSTGOODS + "') || hasAuthority('" + SellerAuthorityConst.ALL + "')")
    public AddProdRet updateFastenerProduct(
            @RequestParam(required = true) long id,
            @RequestParam(required = true) long productsid,
            @RequestParam(required = false,defaultValue = "") String subtitle,
            @RequestParam(required = true) BigDecimal prodprice,
            @RequestParam(required = true, defaultValue = "1") BigDecimal startnum,
            @RequestParam(required = true, defaultValue = "false") boolean stepwiseprice,
            @RequestParam(required = false) BigDecimal threeprice,
            @RequestParam(required = false) BigDecimal ninetyprice,
            @RequestParam(required = false) BigDecimal thirtyprice,
            @RequestParam(required = false) BigDecimal sixtyprice,
            @RequestParam(required = false) String intervalprice,
            @RequestParam(required = true) BigDecimal marketprice,
            @RequestParam(required = false, defaultValue = "0") BigDecimal costprice,
            @RequestParam(required = true) BigDecimal pdstorenum,
            @RequestParam(required = true) long storeid,
           // @RequestParam(required = true) long freightmode,
            @RequestParam(required = true) long shippingtemplatesgroup,
            @RequestParam(required = false) String[] tag,
            @RequestParam(required = true, defaultValue = "false") boolean recommended,
            @RequestParam(required = false, defaultValue = "") String seotitle,
            @RequestParam(required = false, defaultValue = "") String seokey,
            @RequestParam(required = false, defaultValue = "") String seovalue,
            @RequestParam(required = true, defaultValue = "0") short pdstate,
            @RequestParam(required = true) String pdno,
            @RequestParam(required = false) BigDecimal minplus,
            @RequestParam(required = true) BigDecimal discountratio,
            Model model, HttpServletRequest request
    ) {
        AddProdRet addProdRet = new AddProdRet();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

        ProductInfo productInfo = productInfoService.getById(id);
        Products products = productsService.getById(productInfo.getProductid());
        //Products products = productsService.getById(productsid);

        String mes = this.checkFastenerProductNum(products.getPackagetype(),startnum,minplus);
        if(StringUtils.hasText(mes)){
            addProdRet.setResult(BasicRet.ERR);
            addProdRet.setMessage(mes);
            return  addProdRet;
        }


        ErrorMes errorMes = new ErrorMes();

        if (productInfo == null) {
            addProdRet.setMessage("要修改的产品不存在");
            addProdRet.setResult(BasicRet.ERR);
            return addProdRet;
        }

        if (!member.getId().equals(productInfo.getMemberid())) {
            addProdRet.setMessage("要修改的不属于该你");
            addProdRet.setResult(BasicRet.ERR);
            return addProdRet;
        }

        ProductStore productStore = productStoreService.selectByProductidForFastener(id);
        if (productStore == null) {
            addProdRet.setMessage("产品仓库信息没有找到，请联系网站管理员");
            addProdRet.setResult(BasicRet.ERR);
            return addProdRet;
        }


        if (productInfo.getPdstate() == 4) {
            addProdRet.setResult(BasicRet.ERR);
            addProdRet.setMessage("已上架商品请先下架后再做修改");
            return addProdRet;
        }


        if (products == null) {
            errorMes.addError("productsid", "商品库不存在");
        }


        ProductStore dbStore = productStoreService.getByStoreidAndPdNo(member.getId(), storeid, pdno);
        if (dbStore != null && !dbStore.getPdid().equals(productInfo.getId())) {
            addProdRet.errorMes = errorMes;
            addProdRet.setMessage("已经发布过该商品了");
            addProdRet.setResult(BasicRet.ERR);
            return addProdRet;
        }


        if (errorMes != null && errorMes.getSize() > 0) {
            addProdRet.errorMes = errorMes;
            addProdRet.setMessage("有错误信息");
            addProdRet.setResult(BasicRet.ERR);
            return addProdRet;
        }


        if(shippingtemplatesgroup != -1){
            ShippingTemplateGroup shippingTemplateGroup = shippingTemplateGroupService.getShippingTemplateGroup(shippingtemplatesgroup);
            if(shippingTemplateGroup == null || !shippingTemplateGroup.getMemberid().equals( member.getId())){
                addProdRet.setMessage("运费合集不存在");
                addProdRet.setResult(BasicRet.ERR);
                return  addProdRet;
            }
            productInfo.setShippingtemplatesgroup(shippingTemplateGroup.getId());
        }else{
            productInfo.setShippingtemplatesgroup((long)-1);
        }


        productInfo.setProductid(productsid);
        productInfo.setMemberid(member.getId());
        productInfo.setLevel1id(products.getLevel1id());
        productInfo.setLevel1(products.getLevel1());
        productInfo.setLevel2id(products.getLevel2id());
        productInfo.setLevel2(products.getLevel2());
        productInfo.setLevel3id(products.getLevel3id());
        productInfo.setLevel3(products.getLevel3());
        productInfo.setCardnumid(products.getCardnumid());
        productInfo.setCardnum(products.getCardnum());
        productInfo.setProductnameid(products.getProductnameid());
        productInfo.setProductname(products.getProductname());
        productInfo.setProductalias(products.getProductalias());
        productInfo.setBrandid(products.getBrandid());
        productInfo.setBrand(products.getBrand());
        productInfo.setMark(products.getMark());
        productInfo.setProducttype("紧固件");
        productInfo.setSurfacetreatment(products.getSurfacetreatment());
        productInfo.setWeight(products.getWeight());
        productInfo.setProductsno(products.getProductno());
        productInfo.setPdpicture(products.getPdpicture());
        productInfo.setPddrawing(products.getPddrawing());
        productInfo.setPddes(products.getPddes());
        productInfo.setPackagetype(products.getPackagetype());
        productInfo.setTag(tag);
        productInfo.setRecommended(recommended);
        productInfo.setSeokey(StringUtils.nvl(seokey));
        productInfo.setSeotitle(StringUtils.nvl(seotitle));
        productInfo.setSeovalue(StringUtils.nvl(seovalue));
        productInfo.setSpecificationparam("");
        productInfo.setUpdatetime(new Date());
        productInfo.setPdstate(pdstate);
        productInfo.setSubtitle(subtitle);
        productInfo.setMaterialid(products.getMaterialid());
        productInfo.setMaterial(products.getMaterial());
        productInfo.setUnit(products.getUnit());
        productInfo.setStand(products.getStandard());
        productInfo.setType(Quantity.STATE_0);


        productStore.setStoreunit(products.getUnit());
        productStore.setStartnum(startnum);
        productStore.setStepwiseprice(stepwiseprice);

        productStore.setProdprice(prodprice);
        productStore.setThreeprice(threeprice);
        productStore.setNinetyprice(ninetyprice);
        productStore.setThirtyprice(thirtyprice);
        productStore.setSixtyprice(sixtyprice);
        BigDecimal zeroBigDecimal = new BigDecimal(0);
        if (prodprice.compareTo(zeroBigDecimal) == 0) productStore.setProdprice(null);
        if (threeprice != null && threeprice.compareTo(zeroBigDecimal) == 0) productStore.setThreeprice(null);
        if (ninetyprice != null && ninetyprice.compareTo(zeroBigDecimal) == 0) productStore.setNinetyprice(null);
        if (thirtyprice != null && thirtyprice.compareTo(zeroBigDecimal) == 0) productStore.setThirtyprice(null);
        if (sixtyprice != null && sixtyprice.compareTo(zeroBigDecimal) == 0) productStore.setSixtyprice(null);

        Gson gson = new Gson();
        List<Map<String, String>> intervalpriceList = gson.fromJson(intervalprice, new TypeToken<List<Map<String, String>>>() {
        }.getType());
        List<Map<String, String>> saveList = new ArrayList<>();
        for (Map<String, String> map : intervalpriceList) {
            if (map.containsKey("start") && StringUtils.hasText(map.get("start"))) {
                saveList.add(map);
            }
        }

        productStore.setIntervalprice(gson.toJson(saveList));
        productStore.setMarketprice(marketprice);
        productStore.setCostprice(costprice);
        productStore.setPdstorenum(pdstorenum);
        productStore.setAftersale(""); //售后包装
        productStore.setPdno(pdno);
        productStore.setMinplus(minplus);
        productStore.setDiscountratio(discountratio);

        Store store = storeService.getByIdAndMemberId(storeid, member.getId());
        productStore.setStoreid(storeid);
        if (store != null) {
            productStore.setStorename(store.getName());
            productStore.setLocation(store.getAddress());
        }

//        if (freightmode > 0) {
//            ShippingTemplates shippingTemplates = shippingTemplatesService.getById(freightmode);
//            if (shippingTemplates != null) {
//                productStore.setFreightmode(freightmode);
//            }
//        } else {
//            productStore.setFreightmode((long) -1);
//        }

        errorMes = productInfoService.checkProductInfo(productInfo, productStore);

        //判断商品发布状态
        if (pdstate != 0 && pdstate != 1) {
            errorMes.addError("pdstate", "商品发布状态不合法");
        }


        if (errorMes != null && errorMes.getSize() > 0) {
            addProdRet.errorMes = errorMes;
            addProdRet.setMessage("有错误信息");
            addProdRet.setResult(BasicRet.ERR);
            return addProdRet;
        }

        productInfoService.updateById(productInfo);
        productStore.setPdid(productInfo.getId());
        productStoreService.updateById(productStore);


        //删除属性重新添加
        productAttrService.deleteByProductid(productInfo.getId());
        ProductAttr productAttr = new ProductAttr();
        productAttr.setProductid(productInfo.getId());

        String attrJson = products.getAttribute();
        if (CommonUtils.isGoodJson(attrJson)) {
            List<Attribute> retList = gson.fromJson(attrJson,
                    new TypeToken<List<Attribute>>() {
                    }.getType());

            if (retList != null && retList.size() > 0) {
                for (Attribute attr : retList) {
                    productAttr.setValue(attr.getValue());
                    productAttr.setAttribute(attr.getAttribute());
                    productAttr.setAttributeid(attr.getAttributeid());
                    productAttr.setPdno(productStore.getPdno());
                    productAttrService.add(productAttr);
                }
            }
        }

        //保存日志
        memberLogOperator.saveMemberLog(member, null, "修改紧固件商品：" + productInfo.getProductname(), request, memberOperateLogService);

        addProdRet.setResult(BasicRet.SUCCESS);
        addProdRet.setMessage("修改成功");
        return addProdRet;
    }

    /**
     * 紧固件：
     * 商家，发布商品加限制，若有加购量，加购量必须是盒数量的整数倍，起订量必须是加购量的整数倍，如0.6千/盒，加购量必须是0.6的倍数，起订量必须是加购量的倍数；
     * @param packagetype
     * @param startnum
     * @param minplus
     * @return
     */
    private  String checkFastenerProductNum(String packagetype,BigDecimal startnum,BigDecimal minplus) {

        BigDecimal a = Quantity.BIG_DECIMAL_0;
        BigDecimal b = Quantity.BIG_DECIMAL_0;

        if(minplus != null && minplus.compareTo(Quantity.BIG_DECIMAL_0) >0){
            try {
                a = startnum.divide(minplus);

                if(new BigDecimal(a.intValue()).compareTo(a) != 0){
                    return  "起订量必须是加购量的整数倍";
                }

            } catch (ArithmeticException e) {
                return  "起订量必须是加购量的整数倍";
            }

            if(StringUtils.hasText(packagetype)) {
                List<Packing> list = JinshangUtils.toCovertPacking(packagetype);
                if (list != null && list.size() > 0) {
                    Packing p1 = list.get(0);

                    BigDecimal minnum = p1.getNum();
                    try {
                        b = minplus.divide(minnum);
                        if (new BigDecimal(b.intValue()).compareTo(b) != 0) {
                            return "加购量必须是盒数量的整数倍";
                        }
                    } catch (ArithmeticException e) {
                        return "加购量必须是盒数量的整数倍";
                    }
                }
            }
        }

        return  null;
    }


    private String checkOtherProdNum(BigDecimal startnum,BigDecimal minplus){
        if(minplus.compareTo(Quantity.BIG_DECIMAL_0)>0) {
            BigDecimal a = Quantity.BIG_DECIMAL_0;
            try {
                a = startnum.divide(minplus);
                if(new BigDecimal(a.intValue()).compareTo(a) != 0){
                    return "起订量必须是加购量的倍数";
                }
            } catch (Exception e) {
                return "起订量必须是加购量的倍数";
            }
        }

        return  null;
    }




    @RequestMapping(value = "/FastenerProduct/add", method = RequestMethod.POST)
    @ApiOperation("发布紧固件类商品")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "紧固件商品库id", name = "productsid", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(value = "商品副标题", name = "subtitle", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "商品价格", name = "prodprice", required = true, dataType = "float", paramType = "query"),
            @ApiImplicitParam(value = "起订量", name = "startnum", required = true, dataType = "float", paramType = "query"),
            @ApiImplicitParam(value = "是否开启阶梯价格", name = "stepwiseprice", required = true, dataType = "boolean", defaultValue = "false", paramType = "query"),
            @ApiImplicitParam(value = "3天发货价格", name = "threeprice", required = false, dataType = "float", paramType = "query"),
            @ApiImplicitParam(value = "90天发货价格", name = "ninetyprice", required = false, dataType = "float", paramType = "query"),
            @ApiImplicitParam(value = "30天发货价格", name = "thirtyprice", required = false, dataType = "float", paramType = "query"),
            @ApiImplicitParam(value = "60天发货价格", name = "sixtyprice", required = false, dataType = "float", paramType = "query"),
            @ApiImplicitParam(value = "区间价格json,例如：[{\"start\": 1,\"end\": 10,\"rate\": 90},{\"start\": 11,\"end\": 20,\"rate\": 80},{\"start\": 21,\"end\": 0,\"rate\": 70}]", name = "intervalprice", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "市场价", name = "marketprice", required = true, dataType = "float", paramType = "query"),
            @ApiImplicitParam(value = "成本价", name = "costprice", required = false, dataType = "float", paramType = "query"),
            @ApiImplicitParam(value = "库存", name = "pdstorenum", required = true, dataType = "float", defaultValue = "1", paramType = "query"),
            @ApiImplicitParam(value = "加购量", name = "minplus", required = false, dataType = "double", defaultValue = "0", paramType = "query"),
            @ApiImplicitParam(value = "仓库id", name = "storeid", required = true, dataType = "int", paramType = "query"),
            //@ApiImplicitParam(value = "运费方式", name = "freightmode", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(value = "运费方式", name = "shippingtemplatesgroup", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(value = "是否推荐", name = "recommended", required = false, dataType = "boolean", paramType = "query"),
            @ApiImplicitParam(value = "seo标题", name = "seotitle", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "seo关键字", name = "seokey", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "seo描述", name = "seovalue", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "商品编码", name = "pdno", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "商品状态 0=放入仓库 1=待审核(立即发布) 2=审核通过 3=未通过 4=已上架(上架) 5=下架 6=删除 7-违规下架", name = "pdstate", required = true, dataType = "int", paramType = "query", defaultValue = "0"),
            @ApiImplicitParam(value = "折扣比例", name = "discountratio", required = true, dataType = "float", paramType = "query"),
    })
    @PreAuthorize("hasAuthority('" + SellerAuthorityConst.POSTGOODS + "') || hasAuthority('" + SellerAuthorityConst.ALL + "') ")
    public AddProdRet addFastenerProduct(
            @RequestParam(required = true) long productsid,
            @RequestParam(required = false,defaultValue = "") String subtitle,
            @RequestParam(required = true) BigDecimal prodprice,
            @RequestParam(required = true, defaultValue = "1") BigDecimal startnum,
            @RequestParam(required = true, defaultValue = "false") boolean stepwiseprice,
            @RequestParam(required = false) BigDecimal threeprice,
            @RequestParam(required = false) BigDecimal ninetyprice,
            @RequestParam(required = false) BigDecimal thirtyprice,
            @RequestParam(required = false) BigDecimal sixtyprice,
            @RequestParam(required = false) String intervalprice,
            @RequestParam(required = true) BigDecimal marketprice,
            @RequestParam(required = false, defaultValue = "0") BigDecimal costprice,
            @RequestParam(required = true) BigDecimal pdstorenum,
            @RequestParam(required = true) long storeid,
            //@RequestParam(required = true) long freightmode,
            @RequestParam(required = true) long shippingtemplatesgroup,
            @RequestParam(required = false) String[] tag,
            @RequestParam(required = true, defaultValue = "false") boolean recommended,
            @RequestParam(required = false, defaultValue = "") String seotitle,
            @RequestParam(required = false, defaultValue = "") String seokey,
            @RequestParam(required = false, defaultValue = "") String seovalue,
            @RequestParam(required = true, defaultValue = "0") short pdstate,
            @RequestParam(required = true) String pdno,
            @RequestParam(required = false) BigDecimal minplus,
            @RequestParam(required = true) BigDecimal discountratio,
            Model model, HttpServletRequest request
    ) {
        AddProdRet addProdRet = new AddProdRet();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

        ProductInfo productInfo = new ProductInfo();
        ProductStore productStore = new ProductStore();

        ErrorMes errorMes = new ErrorMes();
        Products products = productsService.getById(productsid);

        if (products == null) {
            errorMes.addError("productsid", "商品库不存在");
        }

        if (errorMes != null && errorMes.getSize() > 0) {
            addProdRet.errorMes = errorMes;
            addProdRet.setMessage("有错误信息");
            addProdRet.setResult(BasicRet.ERR);
            return addProdRet;
        }


        String mes = this.checkFastenerProductNum(products.getPackagetype(),startnum,minplus);
        if(StringUtils.hasText(mes)){
            addProdRet.setMessage(mes);
            addProdRet.setResult(BasicRet.ERR);
            return addProdRet;
        }






        ProductStore dbStore = productStoreService.getByStoreidAndPdNo(member.getId(), storeid, pdno);
        if (dbStore != null) {
            addProdRet.setMessage("此商品已经发布过了，不可重复发布");
            addProdRet.setResult(BasicRet.ERR);
            return addProdRet;
        }

        productInfo.setProductid(productsid);
        productInfo.setMemberid(member.getId());
        productInfo.setLevel1id(products.getLevel1id());
        productInfo.setLevel1(products.getLevel1());
        productInfo.setLevel2id(products.getLevel2id());
        productInfo.setLevel2(products.getLevel2());
        productInfo.setLevel3id(products.getLevel3id());
        productInfo.setLevel3(products.getLevel3());
        productInfo.setCardnumid(products.getCardnumid());
        productInfo.setCardnum(products.getCardnum());
        productInfo.setProductnameid(products.getProductnameid());
        productInfo.setProductname(products.getProductname());
        productInfo.setProductalias(products.getProductalias());
        productInfo.setBrandid(products.getBrandid());
        productInfo.setBrand(products.getBrand());
        productInfo.setMark(products.getMark());
        productInfo.setProducttype("紧固件");
        productInfo.setSurfacetreatment(products.getSurfacetreatment());
        productInfo.setWeight(products.getWeight());
        productInfo.setProductsno(products.getProductno());
        productInfo.setPdpicture(products.getPdpicture());
        productInfo.setPddrawing(products.getPddrawing());
        productInfo.setPddes(products.getPddes());
        productInfo.setPackagetype(products.getPackagetype());
        productInfo.setTag(tag);
        productInfo.setRecommended(recommended);
        productInfo.setSeokey(StringUtils.nvl(seokey));
        productInfo.setSeotitle(StringUtils.nvl(seotitle));
        productInfo.setSeovalue(StringUtils.nvl(seovalue));
        productInfo.setSpecificationparam("");
        productInfo.setCreatetime(new Date());
        productInfo.setPdstate(pdstate);
        productInfo.setSubtitle(subtitle);
        productInfo.setMaterialid(products.getMaterialid());
        productInfo.setMaterial(products.getMaterial());
        productInfo.setUnit(products.getUnit());
        productInfo.setStand(products.getStandard());
        productInfo.setType(Quantity.STATE_0);

        productInfo.setMinprice(prodprice);
        productInfo.setHeightprice(prodprice);


        productInfo.setSelfsupport(Arrays.asList(shopSelfSupportid.split("\\|")).contains(String.valueOf(member.getId())));

        productStore.setStoreunit(products.getUnit());
        productStore.setStartnum(startnum);
        productStore.setStepwiseprice(stepwiseprice);
        productStore.setProdprice(prodprice);
        productStore.setThreeprice(threeprice);
        productStore.setNinetyprice(ninetyprice);
        productStore.setThirtyprice(thirtyprice);
        productStore.setSixtyprice(sixtyprice);
        productStore.setMinplus(minplus);
        productStore.setDiscountratio(discountratio);

        BigDecimal zeroBigDecimal = new BigDecimal(0);
        if (prodprice.compareTo(zeroBigDecimal) == 0) productStore.setProdprice(null);
        if (threeprice != null && threeprice.compareTo(zeroBigDecimal) == 0) productStore.setThreeprice(null);
        if (ninetyprice != null && ninetyprice.compareTo(zeroBigDecimal) == 0) productStore.setNinetyprice(null);
        if (thirtyprice != null && thirtyprice.compareTo(zeroBigDecimal) == 0) productStore.setThirtyprice(null);
        if (sixtyprice != null && sixtyprice.compareTo(zeroBigDecimal) == 0) productStore.setSixtyprice(null);
        productStore.setWeight(products.getWeight());


        Gson gson = new Gson();
        List<Map<String, String>> intervalpriceList = gson.fromJson(intervalprice, new TypeToken<List<Map<String, String>>>() {
        }.getType());
        List<Map<String, String>> saveList = new ArrayList<>();
        if (intervalpriceList != null) {
            for (Map<String, String> map : intervalpriceList) {
                if (map.containsKey("start") && StringUtils.hasText(map.get("start"))) {
                    saveList.add(map);
                }
            }
        }

        productStore.setIntervalprice(gson.toJson(saveList));
        productStore.setMarketprice(marketprice);
        productStore.setCostprice(costprice);
        productStore.setPdstorenum(pdstorenum);
        productStore.setAftersale(""); //售后包装

        //商品编码
        productStore.setPdno(pdno);

//        if (freightmode != -1) {
//            ShippingTemplates shippingTemplates = shippingTemplatesService.getById(freightmode);
//            if (shippingTemplates != null) {
//                productStore.setFreightmode(freightmode);
//            }
//        } else {
//            productStore.setFreightmode((long) -1);
//        }

        if(shippingtemplatesgroup != -1){
            ShippingTemplateGroup shippingTemplateGroup = shippingTemplateGroupService.getShippingTemplateGroup(shippingtemplatesgroup);
            if(shippingTemplateGroup == null || !shippingTemplateGroup.getMemberid().equals( member.getId())){
                addProdRet.setMessage("运费合计不存在");
                addProdRet.setResult(BasicRet.ERR);
                return  addProdRet;
            }
            productInfo.setShippingtemplatesgroup(shippingTemplateGroup.getId());
        }else{
            productInfo.setShippingtemplatesgroup((long)-1);
        }


        //判断商品发布状态
        if (pdstate != 0 && pdstate != 1) {
            errorMes.addError("pdstate", "商品发布状态不合法");
        }


        Store store = storeService.getByIdAndMemberId(storeid, member.getId());
        productStore.setStoreid(storeid);
        if (store != null) {
            productStore.setStorename(store.getName());
            productStore.setLocation(store.getAddress());
        } else {
            errorMes.addError("storeid", "仓库选择不合法");
        }

        if(errorMes.getSize()>0){
            addProdRet.errorMes = errorMes;
            addProdRet.setMessage("有错误信息");
            addProdRet.setResult(BasicRet.ERR);
            return addProdRet;
        }

        errorMes = productInfoService.checkProductInfo(productInfo, productStore);

        //判断该用户是否有发布的权限s
//        if(!checkPublishProductPrivilege(productInfo.getLevel3id(),(Long[]) (member.getSellerCompanyInfo().getBusinesscategory()))){
//            errorMes.addError("level","该用户没有发布该类别的权限");
//        }

        if (errorMes != null && errorMes.getSize() > 0) {
            addProdRet.errorMes = errorMes;
            addProdRet.setMessage("有错误信息");
            addProdRet.setResult(BasicRet.ERR);
            return addProdRet;
        }

        productInfoService.insertSelective(productInfo);
        productStore.setPdid(productInfo.getId());
        productStoreService.add(productStore);

        ProductAttr productAttr = new ProductAttr();
        productAttr.setProductid(productInfo.getId());


        String attrJson = products.getAttribute();
        if (CommonUtils.isGoodJson(attrJson)) {
            List<Attribute> retList = gson.fromJson(attrJson,
                    new TypeToken<List<Attribute>>() {
                    }.getType());

            if (retList != null && retList.size() > 0) {
                for (Attribute attr : retList) {
                    productAttr.setValue(attr.getValue());
                    productAttr.setAttribute(attr.getAttribute());
                    productAttr.setAttributeid(attr.getAttributeid());
                    productAttr.setPdno(productStore.getPdno());
                    productAttrService.add(productAttr);
                }
            }
        }

        //保存日志
        memberLogOperator.saveMemberLog(member, null, "发布紧固件商品：" + productInfo.getProductname(), request, memberOperateLogService);

        addProdRet.setResult(BasicRet.SUCCESS);
        addProdRet.setMessage("添加成功");
        return addProdRet;
    }


    @PostMapping("/excel/addFastenerProduct")
    @ApiOperation("excel导入紧固件商品")
    public  BasicRet addFastenerProductByExcel(@RequestParam("file") CommonsMultipartFile file,Model model,HttpServletRequest request) throws Exception {

        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

        if(!file.getOriginalFilename().endsWith(".xlsx") && !file.getOriginalFilename().endsWith(".xls")){
            return  new BasicRet(BasicRet.ERR,"请上传Excel文件");
        }

        String fileName= GenerateNo.getUUID()+file.getOriginalFilename();
        File dir =  new File(uploadPath);
        if(!dir.exists()){
            dir.mkdirs();
        }

        File newFile= new File(dir,fileName);
        Gson gson = new Gson();

        List<ProductInfo> infoList = new ArrayList<>();
        try {
            file.transferTo(newFile);
            List<ProductImportModel> list = productBatchImport.excelToProductinfo(newFile.getAbsolutePath());

            if(list != null){
                for(ProductImportModel importModel : list){
                    ProductInfo productInfo = new ProductInfo();
                    ProductStore productStore = new ProductStore();

                    Products products = productsService.getProdByPdno(importModel.getPdno());
                    if(products == null){
                        return  new BasicRet(BasicRet.ERR,importModel.getPdno()+"编码的商品在商品库中不存在");
                    }

                    String mes = this.checkFastenerProductNum(products.getPackagetype(),importModel.getNum(),importModel.getMinplus());
                    if(StringUtils.hasText(mes)){
                        return  new BasicRet(BasicRet.ERR,"编号为"+importModel.getPdno()+"的商品"+mes);
                    }


                    Store store =  storeService.getByNameAndMemberId(importModel.getStoreName(),member.getId());
                    if(store == null){
                        return  new BasicRet(BasicRet.ERR,"名称为"+importModel.getStoreName()+"的仓库不存在");
                    }


                    ProductStore dbStore = productStoreService.getByStoreidAndPdNo(member.getId(), store.getId(), importModel.getGoodsNum());
                    if (dbStore != null) {
                        return new BasicRet(BasicRet.ERR,"货号为"+importModel.getGoodsNum()+"的商品已经在"+store.getName()+"发布过了");
                    }


//                    ShippingTemplates shippingTemplates = null;
//                    if("包邮".equals(importModel.getDeliveryType())){
//                        shippingTemplates =  new ShippingTemplates();
//                        shippingTemplates.setId((long)-1);
//                    }else {
//                        shippingTemplates = shippingTemplatesService.getByNameAndMemberid(importModel.getDeliveryType(), member.getId());
//                    }
//
//                    if(shippingTemplates == null){
//                        return  new BasicRet(BasicRet.ERR,"名称为"+importModel.getDeliveryType()+"的运费方式不存在");
//                    }

                    if("包邮".equals(importModel.getDeliveryType())){
                        productInfo.setShippingtemplatesgroup((long)-1);
                    }else{
                        Long groupId = shippingTemplateGroupService.getGroupIdByMemberidAndName(member.getId(),importModel.getDeliveryType());
                        if(groupId == null || groupId <=0){
                            return  new BasicRet(BasicRet.ERR,"名称为"+importModel.getDeliveryType()+"的运费集合不存在");
                        }
                        productInfo.setShippingtemplatesgroup(groupId);
                    }



                    List<StepWisePrice> stepWisePrices =  new ArrayList<>();
                    boolean  stepwiseprice = false;
                    if(StringUtils.hasText(importModel.getInterval1()) && StringUtils.hasText(importModel.getInterval2())
                            && StringUtils.hasText(importModel.getInterval3()) && StringUtils.hasText(importModel.getSale1()) &&
                            StringUtils.hasText(importModel.getSale2()) && StringUtils.hasText(importModel.getSale3())){
                        stepwiseprice =  true;

                        StepWisePrice step1 = createStepWisePrice(importModel.getInterval1(),importModel.getSale1());
                        StepWisePrice step2 = createStepWisePrice(importModel.getInterval2(),importModel.getSale2());
                        StepWisePrice step3 = createStepWisePrice2(importModel.getInterval3(),importModel.getSale3());

                        if(step1 == null || step2 == null || step3 == null){
                            return  new BasicRet(BasicRet.ERR,"商品编号为"+importModel.getPdno()+"的商品价格区间填写错误");
                        }

                        stepWisePrices.add(step1);
                        stepWisePrices.add(step2);
                        stepWisePrices.add(step3);
                    }


                    productInfo.setProductid(products.getId());
                    productInfo.setMemberid(member.getId());
                    productInfo.setLevel1id(products.getLevel1id());
                    productInfo.setLevel1(products.getLevel1());
                    productInfo.setLevel2id(products.getLevel2id());
                    productInfo.setLevel2(products.getLevel2());
                    productInfo.setLevel3id(products.getLevel3id());
                    productInfo.setLevel3(products.getLevel3());
                    productInfo.setCardnumid(products.getCardnumid());
                    productInfo.setCardnum(products.getCardnum());
                    productInfo.setProductnameid(products.getProductnameid());
                    productInfo.setProductname(products.getProductname());
                    productInfo.setProductalias(products.getProductalias());
                    productInfo.setBrandid(products.getBrandid());
                    productInfo.setBrand(products.getBrand());
                    productInfo.setMark(products.getMark());
                    productInfo.setProducttype("紧固件");
                    productInfo.setSurfacetreatment(products.getSurfacetreatment());
                    productInfo.setWeight(products.getWeight());
                    productInfo.setProductsno(products.getProductno());
                    productInfo.setPdpicture(products.getPdpicture());
                    productInfo.setPddrawing(products.getPddrawing());
                    productInfo.setPddes(products.getPddes());
                    productInfo.setPackagetype(products.getPackagetype());
//                    productInfo.setTag(importModel.getPdtag());
                    productInfo.setRecommended(false);
                    productInfo.setSeokey(StringUtils.nvl(importModel.getSeoKey()));
                    productInfo.setSeotitle(StringUtils.nvl(importModel.getSeoTitle()));
                    productInfo.setSeovalue(StringUtils.nvl(importModel.getSeoDescription()));
                    productInfo.setSpecificationparam("");
                    productInfo.setCreatetime(new Date());
                    productInfo.setPdstate(Quantity.STATE_1);
                    productInfo.setSubtitle(importModel.getPdname());
                    productInfo.setMaterialid(products.getMaterialid());
                    productInfo.setMaterial(products.getMaterial());
                    productInfo.setUnit(products.getUnit());
                    productInfo.setStand(products.getStandard());
                    productInfo.setType(Quantity.STATE_0);

                    productInfo.setMinprice(importModel.getPrice());
                    productInfo.setHeightprice(importModel.getPrice());

                    productInfo.setSelfsupport(Arrays.asList(shopSelfSupportid.split("\\|")).contains(String.valueOf(member.getId())));
                    productInfo.setAttrJson(products.getAttribute());

                    productStore.setStoreid(store.getId());
                    productStore.setStorename(store.getName());
                    productStore.setStoreunit(products.getUnit());

                    productStore.setStartnum(importModel.getNum());
                    productStore.setMinplus(importModel.getMinplus());
                    productStore.setStepwiseprice(stepwiseprice);

                    productStore.setProdprice(importModel.getPrice());
                    productStore.setThreeprice(importModel.getThreePrice());
                    productStore.setNinetyprice(importModel.getNintyPrice());
                    productStore.setThirtyprice(importModel.getThirtyPrice());
                    productStore.setSixtyprice(importModel.getSixtyPrice());


                    BigDecimal zeroBigDecimal = new BigDecimal(0);
                    if (productStore.getProdprice().compareTo(zeroBigDecimal) == 0) productStore.setProdprice(null);
                    if (productStore.getThreeprice() != null && productStore.getThreeprice().compareTo(zeroBigDecimal) == 0) productStore.setNinetyprice(null);
                    if (productStore.getNinetyprice() != null && productStore.getNinetyprice().compareTo(zeroBigDecimal) == 0) productStore.setNinetyprice(null);
                    if (productStore.getThirtyprice() != null && productStore.getThirtyprice().compareTo(zeroBigDecimal) == 0) productStore.setThirtyprice(null);
                    if (productStore.getSixtyprice() != null && productStore.getSixtyprice().compareTo(zeroBigDecimal) == 0) productStore.setSixtyprice(null);


                    productStore.setIntervalprice(gson.toJson(stepWisePrices));
                    //productStore.setFreightmode(shippingTemplates.getId());
                    productStore.setLocation(store.getAddress());
                    //商品货号
                    productStore.setPdno(importModel.getGoodsNum());
                    productStore.setPdstorenum(importModel.getStoreNum());
                    productStore.setCostprice(importModel.getCostPrice());
                    productStore.setMarketprice(importModel.getMarketPrice());

                    productStore.setWeight(products.getWeight());

                    productInfo.setProductStore(productStore);

                    for(ProductInfo saveInfo : infoList){
                        if(saveInfo.getProductStore().getPdno().equals(importModel.getPdno()) &&
                                saveInfo.getProductStore().getStorename().equals(importModel.getStoreName())){
                            return  new BasicRet(BasicRet.ERR,"上传的excel中存在多个相同的商品");
                        }
                    }



                    ProductStore dbStore1 = productStoreService.getByStoreidAndPdNo(member.getId(), productStore.getStoreid(), productStore.getPdno());
                    if (dbStore1 != null) {
                        return  new BasicRet(BasicRet.ERR,"上传的excel中商品编码为"+productStore.getPdno()+"的已经存在");
                    }


                    infoList.add(productInfo);
                }
            }

            for(ProductInfo info : infoList){
                productInfoService.insertSelective(info);
                ProductStore pstore = info.getProductStore();
                pstore.setPdid(info.getId());
                productStoreService.insertSelective(pstore);

                String attrJson = info.getAttrJson();
                ProductAttr productAttr = new ProductAttr();
                productAttr.setProductid(info.getId());
                if (CommonUtils.isGoodJson(attrJson)) {
                    List<Attribute> retList = gson.fromJson(attrJson,
                            new TypeToken<List<Attribute>>() {
                            }.getType());

                    if (retList != null && retList.size() > 0) {
                        for (Attribute attr : retList) {
                            productAttr.setValue(attr.getValue());
                            productAttr.setAttribute(attr.getAttribute());
                            productAttr.setAttributeid(attr.getAttributeid());
                            productAttr.setPdno(pstore.getPdno());
                            productAttrService.add(productAttr);
                        }
                    }
                }


                //保存日志
                memberLogOperator.saveMemberLog(member, null, "发布紧固件商品：" + info.getProductname(), request, memberOperateLogService);

            }
        } catch (Exception e) {
            throw  e;
        } finally {
            newFile.delete();
        }
        return  new BasicRet(BasicRet.SUCCESS,"导入成功");
    }




    private  StepWisePrice createStepWisePrice(String interval,String rate){
        String[] interArr = interval.split("-");
        if(interArr == null || interArr.length != 2){
            return  null;
        }

        if(!StringUtils.isNumeric(interArr[0]) || !StringUtils.isNumeric(interArr[1]) || !StringUtils.isNumeric(rate)){
            return  null;
        }

        StepWisePrice stepWisePrice = new StepWisePrice();
        stepWisePrice.setStart(new BigDecimal(interArr[0]));
        stepWisePrice.setEnd(new BigDecimal(interArr[1]));
        stepWisePrice.setRate(new BigDecimal(rate));

        return  stepWisePrice;
    }


    private  StepWisePrice createStepWisePrice2(String interval,String rate){

        if(!StringUtils.isNumeric(interval) || !StringUtils.isNumeric(rate)){
            return  null;
        }

        StepWisePrice stepWisePrice = new StepWisePrice();
        stepWisePrice.setStart(new BigDecimal(interval));
        stepWisePrice.setRate(new BigDecimal(rate));

        stepWisePrice.setEnd(new BigDecimal(0));

        return  stepWisePrice;
    }



    @RequestMapping(value = "/getFastenerProductInfo", method = RequestMethod.POST)
    @ApiOperation("获取紧固件商品详情接口")
    public ProductRet getFastenerProductInfo(@RequestParam(required = true) long id, Model model) {
        ProductRet productRet = new ProductRet();

        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

        ProductInfo productInfo = productInfoService.getProductInfoWithStore(id);
        if (productInfo == null) {
            productRet.setMessage("未查询到该商品");
            productRet.setResult(BasicRet.ERR);
            return productRet;
        }

        if (!productInfo.getProducttype().equals(AppConstant.FASTENER_PRO_TYPE)) {
            productRet.setResult(BasicRet.ERR);
            productRet.setMessage("非紧固件商品不可调用此接口");
            return productRet;
        }


        ProductStore store = productInfo.getProductStore();
        if (store == null) {
            productRet.setMessage("未查询到商品库存信息");
            productRet.setResult(BasicRet.ERR);
            return productRet;
        }


        //转换包装方式
        productInfo.setPackingList(JinshangUtils.toCovertPacking(productInfo.getPackagetype()));


        //封装价格
        List list1 = new ArrayList();
        Map<String, Object> prodpriceMap = new HashMap<>();
        Map<String, Object> threepriceMap = new HashMap<>();
        Map<String, Object> ninetypriceMap = new HashMap<>();
        Map<String, Object> thirtypriceMap = new HashMap<>();
        Map<String, Object> sixtypriceMap = new HashMap<>();

        if (store.getProdprice() != null) {
            prodpriceMap.put("type", 0);
            prodpriceMap.put("name", Quantity.LIJIFAHUO);
            prodpriceMap.put("price", store.getProdprice());
            list1.add(prodpriceMap);
            store.setProdprice(store.getProdprice());
        }

        if (store.getThreeprice() != null) {
            threepriceMap.put("type", 3);
            threepriceMap.put("name", Quantity.SANTIANFAHUO);
            threepriceMap.put("price", store.getThreeprice());
            list1.add(threepriceMap);
            store.setThreeprice(store.getThreeprice());
        }

        if (store.getNinetyprice() != null) {
            ninetypriceMap.put("type", 90);
            ninetypriceMap.put("name", Quantity.JIUSHITIANFAHUO);
            ninetypriceMap.put("price", store.getNinetyprice());
            list1.add(ninetypriceMap);
            store.setNinetyprice(store.getNinetyprice());
        }

        if (store.getThirtyprice() != null) {
            thirtypriceMap.put("type", 30);
            thirtypriceMap.put("name", Quantity.SANSHITIANFAHUO);
            thirtypriceMap.put("price", store.getThirtyprice());
            list1.add(thirtypriceMap);
            store.setThirtyprice(store.getThirtyprice());
        }

        if (store.getSixtyprice() != null) {
            sixtypriceMap.put("type", 60);
            sixtypriceMap.put("name", Quantity.LIUSHITIANFAHUO);
            sixtypriceMap.put("price", store.getSixtyprice());
            list1.add(sixtypriceMap);
            store.setSixtyprice(store.getSixtyprice());
        }
        productInfo.setPrices(list1);

//        ShippingTemplates shippingTemplates = null;
//
//        if (productInfo.getProductStore().getFreightmode() > 0) {
//            shippingTemplates = shippingTemplatesService.getFullTemplatesById(productInfo.getProductStore().getFreightmode());
//        }


        productRet.data.productInfo = productInfo;
//        productRet.data.shippingTemplates = shippingTemplates;

        productRet.setMessage("查询成功");
        productRet.setResult(BasicRet.SUCCESS);
        return productRet;
    }


    public class ProductRet extends BasicRet {
        public class ProductRetData {
            private ProductInfo productInfo;
            private ShippingTemplates shippingTemplates;

            public ShippingTemplates getShippingTemplates() {
                return shippingTemplates;
            }

            public ProductInfo getProductInfo() {
                return productInfo;
            }
        }

        private ProductRetData data = new ProductRetData();

        public ProductRetData getData() {
            return data;
        }

        public ProductRet setData(ProductRetData data) {
            this.data = data;
            return this;
        }
    }


    @RequestMapping(value = "/updatePdstate", method = RequestMethod.POST)
    @ApiOperation("产品状态修改:上架、下架、删除商品等操作")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "产品id", name = "id", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(value = "产品状态4=上架 5=下架", name = "pdstate", required = true, paramType = "query", dataType = "int")
    })
    //商品状态 0=放入仓库 1=待审核(立即发布) 2=审核通过 3=未通过 4=已上架(上架) 5=下架 6=删除 7-违规下架
    @PreAuthorize("hasAuthority('" + SellerAuthorityConst.POSTGOODS + "') || hasAuthority('" + SellerAuthorityConst.ALL + "')")
    public BasicRet updatePdstate(@RequestParam(required = true) long id, @RequestParam(required = true) short pdstate, Model model, HttpServletRequest request) throws MyException {
        BasicRet basicRet = new BasicRet();
        ProductInfo info = new ProductInfo();

        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        if (pdstate != 0 && pdstate != 1 && pdstate != 4 && pdstate != 5 && pdstate != 6) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("要修改的状态不合法");
            return basicRet;
        }

        ProductInfo productInfo = productInfoService.getProductInfoWithStore(id);
        if (productInfo == null) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("要修改的产品不存在");
            return basicRet;
        }

        if (!productInfo.getMemberid().equals(member.getId())) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("要修改产品不属于你");
            return basicRet;
        }
        boolean addIndex = false;
        boolean canUpdate = false;
        if (pdstate == 0 && (productInfo.getPdstate() == Quantity.STATE_1 || productInfo.getPdstate() == Quantity.STATE_5 || productInfo.getPdstate() == Quantity.STATE_7)) {
            canUpdate = true;
        }
        if (pdstate == 1 && (productInfo.getPdstate() == Quantity.STATE_0 || productInfo.getPdstate() == Quantity.STATE_5)) {
            canUpdate = true;
        }

        if (pdstate == 4 && (productInfo.getPdstate() == Quantity.STATE_2 || productInfo.getPdstate() == Quantity.STATE_5)) {
            // 扣除保证金
            canUpdate = true;

            if ("紧固件".equals(productInfo.getProducttype())) {
                TransactionSetting transactionSetting = transactionSettingService.getTransactionSetting();
                if (transactionSetting == null || transactionSetting.getSpotsalesmargin() == null) {
                    basicRet.setResult(BasicRet.ERR);
                    basicRet.setMessage("请网站管理员先设置交易设置");
                    return basicRet;
                }
                BigDecimal spotalesmargin = transactionSetting.getSpotsalesmargin();
                BigDecimal productPrice = null;

                List<BigDecimal> priceList = new ArrayList<>();
                productInfo.getProductStore().getProdprice();

                priceList.add(CommonUtils.nullToDefault(productInfo.getProductStore().getProdprice()));
                priceList.add(CommonUtils.nullToDefault(productInfo.getProductStore().getThreeprice()));
                priceList.add(CommonUtils.nullToDefault(productInfo.getProductStore().getNinetyprice()));
                priceList.add(CommonUtils.nullToDefault(productInfo.getProductStore().getThirtyprice()));
                priceList.add(CommonUtils.nullToDefault(productInfo.getProductStore().getSixtyprice()));
                productPrice = Collections.max(priceList);

                BigDecimal storeNum = productInfo.getProductStore().getPdstorenum();
                //  （最高单价 * 库存 * 保证金比率） / 100
                BigDecimal bail = productPrice.multiply(storeNum).multiply(spotalesmargin.divide(new BigDecimal(100)));

                member = memberService.getMemberById(member.getId());
                if (member.getSellerbanlance() == null || (member.getSellerbanlance().subtract(bail).doubleValue() < 0)) {
                    basicRet.setMessage("帐号金额不足");
                    basicRet.setResult(BasicRet.ERR);
                    return basicRet;
                }
                //1.帐号可用余额扣除保证金，冻结金额增加保证金
//                BigDecimal sellerBalance = member.getSellerbanlance().subtract(bail);
//                BigDecimal sellerfreezebanlance = member.getSellerfreezebanlance() == null ? bail : member.getSellerfreezebanlance().add(bail);
//                memberService.updateSellerMemberBalance(member.getId(), sellerBalance, sellerfreezebanlance);
                memberService.updateSellerMemberBalanceInDb(member.getId(),bail.multiply(Quantity.BIG_DECIMAL_MINUS_1),bail);



                //2.记录到卖家资金明细表
                SalerCapital salerCapital = new SalerCapital();
                salerCapital.setMemberid(member.getId());
                salerCapital.setTradeno("");
                salerCapital.setOrderno("");
                salerCapital.setTradetime(new Date());
                salerCapital.setOrdercapital(new BigDecimal(0));
                salerCapital.setBail(bail);
                salerCapital.setPenalty(new BigDecimal(0));
                salerCapital.setRefundamount(new BigDecimal(0));
                salerCapital.setRemark(productInfo.getId()+"."+productInfo.getProductname()+"产品上架缴纳保证金");
                salerCapital.setCapitaltype((short) 1);
                salerCapital.setRechargenumber("");
                salerCapital.setRechargeperform((short) -1);
                salerCapital.setPresentationnumber("");
                salerCapital.setRechargestate((short) 1);
                salerCapitalService.insertSelective(salerCapital);

                //3.添加记录到商品保证金流水表
                PdbailLog pdbailLog = new PdbailLog();
                pdbailLog.setPdid(productInfo.getId());
                pdbailLog.setPdname(productInfo.getProductname());
                pdbailLog.setCaptialid(salerCapital.getId());
                pdbailLog.setCash(bail);
                pdbailLog.setSellerid(member.getId());
                pdbailLog.setCreatetime(new Date());
                pdbailLog.setType((short) 0);

                pdbailLogService.add(pdbailLog);
            }


            List<ProductAttr> attrList = productAttrService.getByProductid(productInfo.getId());
            productInfo.setAttrList(attrList);



            //  加入搜索索引
           // productSearchService.saveIndex(productInfo);

            info.setUptime(new Date());

        }

        if (pdstate == 5 && productInfo.getPdstate() == Quantity.STATE_4) {
            canUpdate = true;
            if ("紧固件".equals(productInfo.getProducttype())) {
                //退回保证金
                //查询出上架交了多少保证金
                PdbailLog pdbailLog = pdbailLogService.getLastLogByPdidType(productInfo.getId(), (short) 0, member.getId());
                if (pdbailLog == null) {
                    basicRet.setResult(BasicRet.ERR);
                    basicRet.setMessage("未查询到商品缴纳的保证金额");
                    return basicRet;
                }

                member = memberService.getMemberById(member.getId());
                if (member.getSellerfreezebanlance().subtract(pdbailLog.getCash()).doubleValue() < 0) {
                    basicRet.setMessage("帐户出现错误，冻结金额小于退回金额");
                    basicRet.setResult(BasicRet.ERR);
                    return basicRet;
                }

                //1.帐号可用余额扣除保证金，冻结金额增加保证金
                //BigDecimal sellerBalance = member.getSellerbanlance() == null ? pdbailLog.getCash() : member.getSellerbanlance().add(pdbailLog.getCash());
                //BigDecimal sellerfreezebanlance = member.getSellerfreezebanlance().subtract(pdbailLog.getCash());
                //memberService.updateSellerMemberBalance(member.getId(), sellerBalance, sellerfreezebanlance);
                memberService.updateSellerMemberBalanceInDb(member.getId(),pdbailLog.getCash(),pdbailLog.getCash().multiply(Quantity.BIG_DECIMAL_MINUS_1));

                //2.记录到卖家资金明细表
                SalerCapital salerCapital = new SalerCapital();
                salerCapital.setMemberid(member.getId());
                salerCapital.setTradeno("");
                salerCapital.setOrderno("");
                salerCapital.setTradetime(new Date());
                salerCapital.setOrdercapital(Quantity.BIG_DECIMAL_0);
                salerCapital.setBail(pdbailLog.getCash());
                salerCapital.setPenalty(Quantity.BIG_DECIMAL_0);
                salerCapital.setRefundamount(Quantity.BIG_DECIMAL_0);
                salerCapital.setRemark(productInfo.getId()+"."+productInfo.getProductname()+"产品下架退回保证金");
                salerCapital.setCapitaltype((short) 2);
                salerCapital.setRechargenumber("");
                salerCapital.setRechargeperform((short) -1);
                salerCapital.setPresentationnumber("");
                salerCapital.setRechargestate((short) 1);
                salerCapitalService.insertSelective(salerCapital);

                //3.添加记录到商品保证金流水表
                pdbailLog.setPdid(productInfo.getId());
                pdbailLog.setPdname(productInfo.getProductname());
                pdbailLog.setCaptialid(salerCapital.getId());
                pdbailLog.setSellerid(member.getId());
                pdbailLog.setCreatetime(new Date());
                pdbailLog.setType((short) 1);
                pdbailLogService.add(pdbailLog);
            }
            //productSearchService.delIndex(productInfo.getId());
            info.setDowntime(new Date());
        }

        if (pdstate == 6 && (productInfo.getPdstate() == 0 || productInfo.getPdstate() == 1 ||
                productInfo.getPdstate() == 2 || productInfo.getPdstate() == 3 || productInfo.getPdstate() == 5 || productInfo.getPdstate() == 7)) {
            canUpdate = true;
            productSearchService.delIndex(productInfo.getId());
        }

        if (canUpdate) {
            info.setId(id);
            info.setPdstate(pdstate);
            productInfoService.updateByPrimaryKeySelective(info);

            productSearchService.saveIndex(info);
            //保存日志
            //0=放入仓库 1=待审核(立即发布) 2=审核通过 3=未通过 4=已上架(上架) 5=下架 6=删除 7-违规下架
            Map pdstatemap = new HashMap();
            pdstatemap.put(0,"放入仓库");
            pdstatemap.put(1,"待审核(立即发布)");
            pdstatemap.put(2,"审核通过");
            pdstatemap.put(3,"未通过");
            pdstatemap.put(4,"已上架(上架)");
            pdstatemap.put(5,"下架");
            pdstatemap.put(6,"删除");
            pdstatemap.put(7,"违规下架");
//            memberLogOperator.saveMemberLog(member, null, "修改商品" + productInfo.getProductname() + "状态为" + pdstatemap.get((int)pdstate), request, memberOperateLogService);


            basicRet.setMessage("修改成功");
            basicRet.setResult(BasicRet.SUCCESS);
        } else {
            throw new MyException("要修改的状态不对");
//            basicRet.setMessage("要修改的状态不对");
//            basicRet.setResult(BasicRet.ERR);
        }

        return basicRet;
    }


    @RequestMapping(value = "/batch/delete", method = RequestMethod.POST)
    @ApiOperation("批量修改产品状态:删除")
    @ApiImplicitParams({
    })
    //商品状态 0=放入仓库 1=待审核(立即发布) 2=审核通过 3=未通过 4=已上架(上架) 5=下架 6=删除 7-违规下架
    public BasicRet batchDelete(@RequestParam(required = true) Long[] ids, Model model, HttpServletRequest request) {
        BasicRet basicRet = new BasicRet();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

        if (ids == null) {
            basicRet.setMessage("ids不可为空");
            basicRet.setResult(BasicRet.ERR);
            return basicRet;
        }

        ProductInfoExample example = new ProductInfoExample();
        ProductInfoExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(Arrays.asList(ids));
        criteria.andMemberidEqualTo(member.getId());
        criteria.andPdstateNotEqualTo(Quantity.STATE_4);

        List<ProductInfo> productList = productInfoService.listProductByExample(example);

        if (productList == null) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("要删除的商品不存在");
            return basicRet;
        }

        for (ProductInfo info : productList) {
            ProductInfo updatInfo = new ProductInfo();
            updatInfo.setId(info.getId());
            updatInfo.setPdstate(Quantity.STATE_6);
            productInfoService.updateByPrimaryKeySelective(updatInfo);

            productSearchService.delIndex(info.getId());
        }


        //保存日志
        memberLogOperator.saveMemberLog(member, null, "批量删除商品：" + Arrays.asList(ids), request, memberOperateLogService);

        basicRet.setMessage("删除成功");
        basicRet.setResult(BasicRet.SUCCESS);
        return basicRet;
    }


    @RequestMapping(value = "/batch/enterValidate", method = RequestMethod.POST)
    @ApiOperation("批量修改产品状态:批量将放入仓库状态的商品修改为待审核状态")
    @ApiImplicitParams({
    })
    //商品状态 0=放入仓库 1=待审核(立即发布) 2=审核通过 3=未通过 4=已上架(上架) 5=下架 6=删除 7-违规下架
    public BasicRet batchEnterValidate(@RequestParam(required = true) Long[] ids, Model model, HttpServletRequest request) {
        BasicRet basicRet = new BasicRet();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

        if (ids == null) {
            basicRet.setMessage("ids不可为空");
            basicRet.setResult(BasicRet.ERR);
            return basicRet;
        }

        ProductInfoExample example = new ProductInfoExample();
        ProductInfoExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(Arrays.asList(ids));
        criteria.andMemberidEqualTo(member.getId());
        criteria.andPdstateEqualTo(Quantity.STATE_0);

        List<ProductInfo> productList = productInfoService.listProductByExample(example);

        if (productList == null) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("要上架的商品不存在");
            return basicRet;
        }

        for (ProductInfo info : productList) {
            ProductInfo updatInfo = new ProductInfo();
            updatInfo.setId(info.getId());
            updatInfo.setPdstate(Quantity.STATE_1);
            productInfoService.updateByPrimaryKeySelective(updatInfo);
        }

        //保存日志
        memberLogOperator.saveMemberLog(member, null, "批量将放入仓库状态的商品修改为待审核状态：" + Arrays.asList(ids), request, memberOperateLogService);


        basicRet.setMessage("上架成功，进入待审核状态");
        basicRet.setResult(BasicRet.SUCCESS);
        return basicRet;
    }


    @RequestMapping(value = "/batch/enterStore", method = RequestMethod.POST)
    @ApiOperation("批量修改产品状态:批量将待审核状态商品改为放入仓库状态")
    @ApiImplicitParams({
    })
    //商品状态 0=放入仓库 1=待审核(立即发布) 2=审核通过 3=未通过 4=已上架(上架) 5=下架 6=删除 7-违规下架
    public BasicRet batchEnterStore(@RequestParam(required = true) Long[] ids, Model model, HttpServletRequest request) {
        BasicRet basicRet = new BasicRet();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

        if (ids == null) {
            basicRet.setMessage("ids不可为空");
            basicRet.setResult(BasicRet.ERR);
            return basicRet;
        }

        ProductInfoExample example = new ProductInfoExample();
        ProductInfoExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(Arrays.asList(ids));
        criteria.andMemberidEqualTo(member.getId());
        criteria.andPdstateEqualTo(Quantity.STATE_1);

        List<ProductInfo> productList = productInfoService.listProductByExample(example);

        if (productList == null) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("要上架的商品不存在");
            return basicRet;
        }

        for (ProductInfo info : productList) {
            ProductInfo updatInfo = new ProductInfo();
            updatInfo.setId(info.getId());
            updatInfo.setPdstate(Quantity.STATE_0);
            productInfoService.updateByPrimaryKeySelective(updatInfo);
        }

        //保存日志
        memberLogOperator.saveMemberLog(member, null, "批量将待审核状态商品改为放入仓库状态：" + Arrays.asList(ids), request, memberOperateLogService);

        basicRet.setMessage("操作成功，商品进入放入仓库状态");
        basicRet.setResult(BasicRet.SUCCESS);
        return basicRet;
    }


    @RequestMapping(value = "/batch/undercarriage", method = RequestMethod.POST)
    @ApiOperation("批量修改产品状态:下架")
    @ApiImplicitParams({
    })
    //商品状态 0=放入仓库 1=待审核(立即发布) 2=审核通过 3=未通过 4=已上架(上架) 5=下架 6=删除 7-违规下架
    public BasicRet batchUndercarriage(@RequestParam(required = true) Long[] ids, Model model, HttpServletRequest request) {
        BasicRet basicRet = new BasicRet();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

        if (ids == null) {
            basicRet.setMessage("ids不可为空");
            basicRet.setResult(BasicRet.ERR);
            return basicRet;
        }

        List<Short> stateList = new ArrayList<>();
        stateList.add(Quantity.STATE_4);

        ProductInfoExample example = new ProductInfoExample();
        ProductInfoExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(Arrays.asList(ids));
        criteria.andMemberidEqualTo(member.getId());
        criteria.andPdstateIn(stateList);
        List<ProductInfo> productList = productInfoService.listProductByExample(example);

        if (productList == null) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("要下架的商品不存在");
            return basicRet;
        }

        BigDecimal totalBaill = new BigDecimal(0); //需要退回的保证金总额
        List<BigDecimal> cashList = new ArrayList<>();
        for (ProductInfo info : productList) {
            if ("紧固件".equals(info.getProducttype())) {

                //查询出上架交了多少保证金
                PdbailLog pdbailLog = pdbailLogService.getLastLogByPdidType(info.getId(), (short) 0, member.getId());
                if (pdbailLog == null) {
                    basicRet.setResult(BasicRet.ERR);
                    basicRet.setMessage("未查询到商品缴纳的保证金额");
                    return basicRet;
                }
                cashList.add(pdbailLog.getCash());
                totalBaill = totalBaill.add(pdbailLog.getCash());
            }
        }

        //记录到卖家资金明细表
        SalerCapital salerCapital = new SalerCapital();
        salerCapital.setMemberid(member.getId());
        salerCapital.setTradeno("");
        salerCapital.setOrderno("");
        salerCapital.setTradetime(new Date());
        salerCapital.setOrdercapital(new BigDecimal(0));
        salerCapital.setBail(totalBaill);
        salerCapital.setPenalty(new BigDecimal(0));
        salerCapital.setRefundamount(new BigDecimal(0));
        salerCapital.setRemark("批量下架商品"+Arrays.asList(ids)+"产品下架退回保证金");
        salerCapital.setCapitaltype((short) 2);
        salerCapital.setRechargenumber("");
        salerCapital.setRechargeperform((short) -1);
        salerCapital.setPresentationnumber("");
        salerCapital.setRechargestate((short) 1);
        salerCapitalService.insertSelective(salerCapital);

        for (int i = 0; i < productList.size(); i++) {
            ProductInfo info = productList.get(i);
            if ("紧固件".equals(info.getProducttype())) {
                PdbailLog pdbailLog = new PdbailLog();
                //添加记录到商品保证金流水表
                pdbailLog.setPdid(info.getId());
                pdbailLog.setPdname(info.getProductname());
                pdbailLog.setCaptialid(salerCapital.getId());
                pdbailLog.setSellerid(member.getId());
                pdbailLog.setCreatetime(new Date());
                pdbailLog.setType((short) 1);
                pdbailLog.setCash(cashList.get(i));
                pdbailLogService.add(pdbailLog);
            }

            productSearchService.delIndex(info.getId());

            ProductInfo updatInfo = new ProductInfo();
            updatInfo.setId(info.getId());
            updatInfo.setDowntime(new Date());
            updatInfo.setPdstate(Quantity.STATE_5);
            productInfoService.updateByPrimaryKeySelective(updatInfo);
        }

        //更新用户帐号资金
        memberService.updateSellerMemberBalanceInDb(member.getId(), totalBaill, totalBaill.multiply(new BigDecimal(-1)));

        //保存日志
        memberLogOperator.saveMemberLog(member, null, "批量下架商品：" + Arrays.asList(ids), request, memberOperateLogService);


        basicRet.setMessage("修改成功");
        basicRet.setResult(BasicRet.SUCCESS);
        return basicRet;
    }


    @RequestMapping(value = "/batch/grounding", method = RequestMethod.POST)
    @ApiOperation("批量修改产品状态:上架")
    @ApiImplicitParams({
    })
    //商品状态 0=放入仓库 1=待审核(立即发布) 2=审核通过 3=未通过 4=已上架(上架) 5=下架 6=删除 7-违规下架
    public BasicRet batchGrounding(@RequestParam(required = true) Long[] ids, Model model, HttpServletRequest request) {
        BasicRet basicRet = new BasicRet();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

        if (ids == null) {
            basicRet.setMessage("ids不可为空");
            basicRet.setResult(BasicRet.ERR);
            return basicRet;
        }

        List<Short> stateList = new ArrayList<>();
        stateList.add(Quantity.STATE_2);
        stateList.add(Quantity.STATE_5);

        ProductInfoExample example = new ProductInfoExample();
        ProductInfoExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(Arrays.asList(ids));
        criteria.andMemberidEqualTo(member.getId());
        criteria.andPdstateIn(stateList);
        List<ProductInfo> productList = productInfoService.listProductByExample(example);

        if (productList.size() == 0) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("要上架的商品不存在");
            return basicRet;
        }

        TransactionSetting transactionSetting = transactionSettingService.getTransactionSetting();
        if (transactionSetting == null || transactionSetting.getSpotsalesmargin() == null) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("请网站管理员先设置交易设置");
            return basicRet;
        }
        BigDecimal spotalesmargin = transactionSetting.getSpotsalesmargin();
        BigDecimal totalBaill = new BigDecimal(0); //需要缴纳的保证金总金额

        for (ProductInfo info : productList) {
            if ("紧固件".equals(info.getProducttype())) {
                ProductStore productStore = productStoreService.selectByProductidForFastener(info.getId());
                if (productStore == null) {
                    basicRet.setResult(BasicRet.ERR);
                    basicRet.setMessage(info.getProductname() + "库存信息有问题，请联系网站客服");
                    return basicRet;
                }
                info.setProductStore(productStore);
                BigDecimal productPrice = null;

                List<BigDecimal> priceList = new ArrayList<>();
                priceList.add(CommonUtils.nullToDefault(productStore.getProdprice()));
                priceList.add(CommonUtils.nullToDefault(productStore.getThreeprice()));
                priceList.add(CommonUtils.nullToDefault(productStore.getNinetyprice()));
                priceList.add(CommonUtils.nullToDefault(productStore.getThirtyprice()));
                priceList.add(CommonUtils.nullToDefault(productStore.getSixtyprice()));
                productPrice = Collections.max(priceList);

                BigDecimal storeNum = productStore.getPdstorenum();
                BigDecimal bail = JinshangUtils.productBill(productPrice, storeNum, spotalesmargin);
                totalBaill = totalBaill.add(bail);
            }
        }

        member = memberService.getMemberById(member.getId());

        if (member.getSellerbanlance().subtract(totalBaill).doubleValue() < 0) {
            basicRet.setMessage("卖家账户余额不足，无法缴纳保证金");
            basicRet.setResult(BasicRet.ERR);
            return basicRet;
        }

        memberService.updateSellerMemberBalanceInDb(member.getId(), totalBaill.multiply(new BigDecimal(-1)), totalBaill);


        //记录到卖家资金明细表
        SalerCapital salerCapital = new SalerCapital();
        salerCapital.setMemberid(member.getId());
        salerCapital.setTradeno("");
        salerCapital.setOrderno("");
        salerCapital.setTradetime(new Date());
        salerCapital.setOrdercapital(new BigDecimal(0));
        salerCapital.setBail(totalBaill);
        salerCapital.setPenalty(new BigDecimal(0));
        salerCapital.setRefundamount(new BigDecimal(0));
        salerCapital.setRemark("产品批量上架缴纳保证金");
        salerCapital.setCapitaltype((short) 1);
        salerCapital.setRechargenumber("");
        salerCapital.setRechargeperform((short) -1);
        salerCapital.setPresentationnumber("");
        salerCapital.setRechargestate((short) 1);
        salerCapitalService.insertSelective(salerCapital);


        for (ProductInfo info : productList) {
            if ("紧固件".equals(info.getProducttype())) {
                //添加记录到商品保证金流水表
                PdbailLog pdbailLog = new PdbailLog();
                pdbailLog.setPdid(info.getId());
                pdbailLog.setPdname(info.getProductname());
                pdbailLog.setCaptialid(salerCapital.getId());
                pdbailLog.setCash(JinshangUtils.productBill(info.getProductStore().getProdprice(), info.getProductStore().getPdstorenum(), spotalesmargin));
                pdbailLog.setSellerid(member.getId());
                pdbailLog.setCreatetime(new Date());
                pdbailLog.setType((short) 0);
                pdbailLogService.add(pdbailLog);
            }

            List<ProductAttr> attrList = productAttrService.getByProductid(info.getId());
            info.setAttrList(attrList);

            ProductInfo updatInfo = new ProductInfo();
            updatInfo.setId(info.getId());
            updatInfo.setPdstate(Quantity.STATE_4);
            updatInfo.setUptime(new Date());
            productInfoService.updateByPrimaryKeySelective(updatInfo);
            //  加入搜索索引
            productSearchService.saveIndex(info);
        }

        //保存日志
        memberLogOperator.saveMemberLog(member, null, "批量上架商品：" + Arrays.asList(ids), request, memberOperateLogService);
        basicRet.setMessage("修改成功");
        basicRet.setResult(BasicRet.SUCCESS);

        return basicRet;
    }


    @RequestMapping(value = "/otherProduct/add", method = RequestMethod.POST)
    @ApiOperation("发布其他类商品")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "品名id", name = "productnameid", required = true, dataType = "int", paramType = "query"),
            //@ApiImplicitParam(value = "材质id",name = "materialid",required = true,dataType = "int",paramType = "query"),
            //@ApiImplicitParam(value = "牌号id",name = "cardnumid",required = true,dataType = "int",paramType = "query"),
            @ApiImplicitParam(value = "品牌id", name = "brandid", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(value = "商品别名", name = "productalias", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "商品副标题", name = "subtitle", required = false, dataType = "string", paramType = "query"),
//            @ApiImplicitParam(value = "起订量", name = "startnum", required = true, dataType = "float", paramType = "query"),
            @ApiImplicitParam(value = "单位", name = "unit", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "市场价", name = "marketprice", required = true, dataType = "float", paramType = "query"),
            @ApiImplicitParam(value = "成本价", name = "costprice", required = false, dataType = "float", paramType = "query"),
//            @ApiImplicitParam(value = "重量", name = "weight", required = true, dataType = "float", paramType = "query"),
            @ApiImplicitParam(value = "仓库id", name = "storeid", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(value = "运费方式,包邮-1", name = "shippingtemplatesgroup", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(value = "是否推荐", name = "recommended", required = false, dataType = "boolean", paramType = "query"),
            @ApiImplicitParam(value = "seo标题", name = "seotitle", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "seo关键字", name = "seokey", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "seo描述", name = "seovalue", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "描述", name = "pddes", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "商品状态 0=放入仓库 1=待审核(立即发布)", name = "pdstate", required = true, dataType = "int", paramType = "query", defaultValue = "0"),
            @ApiImplicitParam(value = "商品json [{\"startnum\":1,\"minplus\":1,\"weight\":1.0,\"prodprice\":1,\"thirtyprice\":2,\"sixtyprice\":20,\"ninetyprice\":90,\"pdstorenum\":100,\"pdno\":\"11123\",\"productAttrs\":[{\"attributeid\":1,\"attribute\":\"颜色\",\"value\":\"红色\"},{\"attributeid\":2,\"attribute\":\"尺寸\",\"value\":\"180\"}]}]", name = "prodJson", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(value = "是否开启阶梯价格", name = "stepwiseprice", required = true, dataType = "boolean", defaultValue = "false", paramType = "query"),
            @ApiImplicitParam(value = "区间价格json,例如：[{\"start\": 1,\"end\": 10,\"rate\": 90},{\"start\": 11,\"end\": 20,\"rate\": 80},{\"start\": 21,\"end\": 0,\"rate\": 70}]", name = "intervalprice", required = false, dataType = "string", paramType = "query"),
    })
    @PreAuthorize("hasAuthority('" + SellerAuthorityConst.POSTGOODS + "') || hasAuthority('" + SellerAuthorityConst.ALL + "')")
    public BasicRet addOtherProduct(@RequestParam(required = true) long level1id,
                                    @RequestParam(required = true) long level2id,
                                    @RequestParam(required = false, defaultValue = "0") long level3id,
                                    @RequestParam(required = true) long productnameid,
                                    @RequestParam(required = true) long brandid,
                                    @RequestParam(required = false, defaultValue = "") String productalias,
                                    @RequestParam(required = false, defaultValue = "") String subtitle,
                                    //@RequestParam(required = true) BigDecimal startnum,
                                    @RequestParam(required = true) String unit,
                                    @RequestParam(required = false) BigDecimal costprice,
                                    //@RequestParam(required = true) BigDecimal weight,
                                    @RequestParam(required = true) long storeid,
                                    @RequestParam(required = true) long shippingtemplatesgroup,
                                    @RequestParam(required = false) boolean recommended,
                                    @RequestParam(required = false, defaultValue = "") String seotitle,
                                    @RequestParam(required = false, defaultValue = "") String seokey,
                                    @RequestParam(required = false, defaultValue = "") String seovalue,
                                    @RequestParam(required = true, defaultValue = "0") short pdstate,
                                    @RequestParam(required = true) String prodJson,
                                    @RequestParam(required = true) String[] pdpicture,
                                    @RequestParam(required = false) String pddes,
                                    @RequestParam(required = true, defaultValue = "false") Boolean stepwiseprice,
                                    @RequestParam(required = false) String intervalprice,
                                    @RequestParam(required = true) BigDecimal marketprice,
                                    Model model, HttpServletRequest request
    ) {
        BasicRet basicRet = new BasicRet();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

        ProductInfo productInfo = new ProductInfo();


        if(shippingtemplatesgroup != -1){
            ShippingTemplateGroup shippingTemplateGroup = shippingTemplateGroupService.getShippingTemplateGroup(shippingtemplatesgroup);
            if(shippingTemplateGroup == null || !shippingTemplateGroup.getMemberid() .equals(member.getId())){
                basicRet.setMessage("运费合计不存在");
                basicRet.setResult(BasicRet.ERR);
                return  basicRet;
            }
            productInfo.setShippingtemplatesgroup(shippingTemplateGroup.getId());
        }else{
            productInfo.setShippingtemplatesgroup((long)-1);
        }


        if (pdstate != Quantity.STATE_0 && pdstate != Quantity.STATE_1) {
            return new BasicRet(BasicRet.ERR, "商品状态不合法");
        }


        if (stepwiseprice && !CommonUtils.isGoodJson(intervalprice)) {
            return new BasicRet(BasicRet.ERR, "区间价格json格式不对");
        }


        Categories categories = categoriesService.getById(level1id);
        if (categories != null) {
            productInfo.setLevel1id(level1id);
            productInfo.setLevel1(categories.getName());
        } else {
            return new BasicRet(BasicRet.ERR, "一级分类错误");
        }


        if(!categories.getCatetype().equals("生活类") && !categories.getCatetype().equals("工业品")){
            return new BasicRet(BasicRet.ERR, "该接口只可发布生活类和工业品的商品");
        }
        productInfo.setType(JinshangUtils.productType(categories.getCatetype()));

        categories = categoriesService.getById(level2id);
        if (categories != null) {
            productInfo.setLevel2id(level2id);
            productInfo.setLevel2(categories.getName());
        } else {
            return new BasicRet(BasicRet.ERR, "二级分类错误");
        }

        if (level3id > 0) {
            categories = categoriesService.getById(level3id);
            if (categories != null) {
                productInfo.setLevel3id(level3id);
                productInfo.setLevel3(categories.getName());
            } else {
                return new BasicRet(BasicRet.ERR, "三级分类错误");
            }
        } else {
            productInfo.setLevel3id((long) 0);
            productInfo.setLevel3("");
        }

        ProductName productName = productNameService.getById(productnameid);
        if (productName == null) {
            return new BasicRet(BasicRet.ERR, "品名不正确");
        } else {
            productInfo.setProductname(productName.getName());
            productInfo.setProductnameid(productnameid);
        }


        Brand brand = brandService.getById(brandid);
        if (brand == null) {
            return new BasicRet(BasicRet.ERR, "品牌错误");
        } else {
            productInfo.setBrand(brand.getName());
            productInfo.setBrandid(brandid);
        }

        Store store = storeService.getByIdAndMemberId(storeid, member.getId());
        if (store == null) {
            return new BasicRet(BasicRet.ERR, "仓库错误");
        }

        if (!CommonUtils.isGoodJson(prodJson)) {
            return new BasicRet(BasicRet.ERR, "json格式不正确");
        }

        Gson gson = new GsonBuilder()//建造者模式设置不同的配置
                .serializeNulls()//序列化为null对象
                .setDateFormat("yyyy-MM-dd HH:mm:ss") //设置日期的格式
                .disableHtmlEscaping()//防止对网址乱码 忽略对特殊字符的转换
                .create();


        prodJson = prodJson.replaceAll("\"thirtyprice\":\"\"","\"thirtyprice\":\"0\"")
                .replaceAll("\"sixtyprice\":\"\"","\"sixtyprice\":\"0\"")
                .replaceAll("\"ninetyprice\":\"\"","\"ninetyprice\":\"0\"")
                .replaceAll("\"minplus\":\"\"","\"minplus\":\"0\"");
        List<OtherProductInfo> resultList = gson.fromJson(prodJson, new TypeToken<List<OtherProductInfo>>() {
        }.getType());


        for (OtherProductInfo info : resultList) {
            BigDecimal zeroBigDecimal = new BigDecimal(0);
            if(info.getStartnum()==null || info.getStartnum().compareTo(zeroBigDecimal) <=0){
                return new BasicRet(BasicRet.ERR, "起订量不可为空或0");
            }

            if(info.getWeight() == null || info.getWeight().compareTo(zeroBigDecimal) <=0){
                return new BasicRet(BasicRet.ERR, "重量不可为空或0");
            }

            if (info.getProdprice().compareTo(zeroBigDecimal) == 0) info.setProdprice(null);
            if (info.getNinetyprice() != null && info.getNinetyprice().compareTo(zeroBigDecimal) == 0) info.setNinetyprice(null);
            if (info.getSixtyprice() != null && info.getSixtyprice().compareTo(zeroBigDecimal) == 0) info.setSixtyprice(null);
            if (info.getThirtyprice() != null && info.getThirtyprice().compareTo(zeroBigDecimal) == 0) info.setThirtyprice(null);
        }


        if (resultList == null || resultList.size() == 0) {
            return new BasicRet(BasicRet.ERR, "请填写商品信息");
        }


        Set<String> pdnoSet = new HashSet<>();
        for (OtherProductInfo otherProductInfo : resultList) {
            pdnoSet.add(otherProductInfo.getPdno());
        }

        if(pdnoSet.size() != resultList.size()){
            return  new BasicRet(BasicRet.ERR,"商品编码不可重复");
        }



        for (OtherProductInfo otherProductInfo : resultList) {
//            int count = productStoreService.countByMemberidAndPdno(member.getId(), otherProductInfo.getPdno());
//            if (count > 0) {
//                return new BasicRet(BasicRet.ERR, otherProductInfo.getPdno() + "编号的商品已经发布过了，不可重复发布");
//            }

            if(otherProductInfo.getMinplus() != null && otherProductInfo.getMinplus().compareTo(Quantity.BIG_DECIMAL_0) >0){
                String mes = this.checkOtherProdNum(otherProductInfo.getStartnum(),otherProductInfo.getMinplus());
                if(StringUtils.hasText(mes)){
                    return  new BasicRet(BasicRet.ERR,otherProductInfo.getPdno() + "编号的商品"+mes);
                }
            }


            ProductStore dbStore = productStoreService.getByStoreidAndPdNo(member.getId(), storeid, otherProductInfo.getPdno());
            if (dbStore != null) {
                return new BasicRet(BasicRet.ERR, otherProductInfo.getPdno() + "编号的商品已经发布过了，不可重复发布");
            }

        }

        productInfo.setProductalias(productalias);
        productInfo.setSubtitle(subtitle);
        productInfo.setUnit(unit);
        productInfo.setCreatetime(new Date());
        productInfo.setUpdatetime(new Date());
        productInfo.setProducttype("其他");
        productInfo.setMemberid(member.getId());
        productInfo.setRecommended(recommended);
        productInfo.setSeovalue(seovalue);
        productInfo.setSeotitle(seotitle);
        productInfo.setSeokey(seokey);
        productInfo.setPddes(pddes);
        productInfo.setPdpicture(pdpicture);
//        productInfo.setWeight(weight);
        productInfo.setPdstate(pdstate);
        productInfo.setSelfsupport(Arrays.asList(shopSelfSupportid.split("\\|")).contains(String.valueOf(member.getId())));

        productInfoService.insertSelective(productInfo);


        //区间价格
        List<Map<String, String>> intervalpriceList = gson.fromJson(intervalprice, new TypeToken<List<Map<String, String>>>() {
        }.getType());
        List<Map<String, String>> saveList = new ArrayList<>();
        for (Map<String, String> map : intervalpriceList) {
            if (map.containsKey("start") && StringUtils.hasText(map.get("start"))) {
                saveList.add(map);
            }
        }

        BigDecimal minPrice = new BigDecimal(0);
        BigDecimal heightPrice = new BigDecimal(0);

        for (OtherProductInfo otherProductInfo : resultList) {
            ProductStore productStore = new ProductStore();
            productStore.setStartnum(otherProductInfo.getStartnum());
            productStore.setCostprice(costprice);
            productStore.setMarketprice(marketprice);
            productStore.setPdstorenum(otherProductInfo.getPdstorenum());
            productStore.setStoreunit(unit);
            productStore.setLocation(store.getAddress());
           // productStore.setFreightmode(freightmode);
            productStore.setStorename(store.getName());
            productStore.setStoreid(storeid);
            productStore.setPdno(otherProductInfo.getPdno());
            productStore.setPdid(productInfo.getId());
            productStore.setStepwiseprice(stepwiseprice); //是否开启阶梯价格
            productStore.setProdprice(otherProductInfo.getProdprice());
            productStore.setNinetyprice(otherProductInfo.getNinetyprice());
            productStore.setSixtyprice(otherProductInfo.getSixtyprice());
            productStore.setThirtyprice(otherProductInfo.getThirtyprice());
            productStore.setThreeprice(otherProductInfo.getProdprice());
            productStore.setMinplus(otherProductInfo.getMinplus());

            productStore.setWeight(otherProductInfo.getWeight());

            productStore.setIntervalprice(gson.toJson(saveList));
            productStoreService.insertSelective(productStore);

            List<ProductAttr> productAttrList = otherProductInfo.getProductAttrs();
            for (ProductAttr att : productAttrList) {
                att.setProductid(productInfo.getId());
                att.setPdno(otherProductInfo.getPdno());
                productAttrService.add(att);
            }


            if (minPrice.compareTo(new BigDecimal(0)) == Quantity.STATE_0) {
                minPrice = productStore.getProdprice();
            } else if (minPrice.compareTo(productStore.getProdprice()) == Quantity.STATE_1) {
                minPrice = productStore.getProdprice();
            }

            if (heightPrice.compareTo(new BigDecimal(0)) == Quantity.STATE_0) {
                heightPrice = productStore.getProdprice();
            } else if (heightPrice.compareTo(productStore.getProdprice()) == Quantity.STATE_) {
                heightPrice = productStore.getProdprice();
            }
        }


        //更新最低价和最高价
        ProductInfo updateInfo = new ProductInfo();
        updateInfo.setId(productInfo.getId());
        updateInfo.setMinprice(minPrice);
        updateInfo.setHeightprice(heightPrice);
        productInfoService.updateByPrimaryKeySelective(updateInfo);


        //保存日志
        memberLogOperator.saveMemberLog(member, null, "发布其他类商品：" + productInfo.getProductname(), request, memberOperateLogService);


        return new BasicRet(BasicRet.SUCCESS, "添加成功");
    }


//    public static void main(String[] args) {
//        String prodJson =  "[{\"prodprice\":\"11\",\"thirtyprice\":0\"\",\"sixtyprice\":\"0\",\"ninetyprice\":\"0\",\"pdstorenum\":\"11\",\"pdno\":\"A123\",\"productAttrs\":[{\"attributeid\":122,\"attribute\":\"颜色\",\"value\":\"黄色\"},{\"attributeid\":123,\"attribute\":\"大小\",\"value\":\"M\"},{\"attributeid\":202,\"attribute\":\"厚度\",\"value\":\"加绒\"},{\"attributeid\":209,\"attribute\":\"材质\",\"value\":\"羊毛\"}]}]";
//        Gson gson  =  new GsonBuilder().serializeNulls().create();
//        List<OtherProductInfo> resultList  =   gson.fromJson(prodJson,new TypeToken<List<OtherProductInfo>>() {}.getType());
//    }



    @PostMapping(value="/generatePNO")
    @ApiOperation("生成商品编码")
    public PnoRet generatePNO(){
        PnoRet pnoRet = new PnoRet();
        pnoRet.setData(GenerateNo.getPNO());
        pnoRet.setResult(BasicRet.SUCCESS);
        return  pnoRet;
    }


    private  class  PnoRet extends  BasicRet{
        private  String data;

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }
    }



    @RequestMapping(value = "/listOtherProduct", method = RequestMethod.POST)
    @ApiOperation("非紧固件商品分页列表查询")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "第几页", name = "pageNo", paramType = "query", dataType = "int", required = true, defaultValue = "1"),
            @ApiImplicitParam(value = "每页显示的条数", name = "pageSize", paramType = "query", dataType = "int", required = true, defaultValue = "20"),
            @ApiImplicitParam(value = "商品名", name = "productname", paramType = "query", dataType = "string", required = false),
            @ApiImplicitParam(value = "品牌", name = "brand", paramType = "query", dataType = "string", required = false),
            @ApiImplicitParam(value = "分类id", name = "levelid", paramType = "query", dataType = "int", required = false, defaultValue = "0"),
            @ApiImplicitParam(value = "商品状态 0=放入仓库 1=待审核(立即发布) 2=审核通过 3=未通过 4=已上架(上架) 5=下架 6=删除 7-违规下架", name = "pdstate", paramType = "query", dataType = "int", required = false, defaultValue = "-1"),
            @ApiImplicitParam(value = "卖家id", name = "memberid", paramType = "query", dataType = "int", required = false, defaultValue = "0"),
            @ApiImplicitParam(value = "用户名", name = "username", paramType = "query", dataType = "string", required = false, defaultValue = ""),
            @ApiImplicitParam(value = "商品id，多个商品id用英文逗号隔开", name = "pdids", paramType = "query", dataType = "String", required = false),
            @ApiImplicitParam(value = "商品编号", name = "pdno", paramType = "query", dataType = "int", required = false),
            @ApiImplicitParam(value = "下架时间", name = "downtimeStart", paramType = "query", dataType = "date", required = false),
            @ApiImplicitParam(value = "下架时间", name = "downtimeEnd", paramType = "query", dataType = "date", required = false),
            @ApiImplicitParam(value = "最后修改价格时间", name = "updatetimeStart", paramType = "query", dataType = "date", required = false),
            @ApiImplicitParam(value = "最后修改价格时间", name = "updatetimeEnd", paramType = "query", dataType = "date", required = false),
            @ApiImplicitParam(value = "上架时间", name = "uptimeStart", paramType = "query", dataType = "date", required = false),
            @ApiImplicitParam(value = "上架时间", name = "uptimeEnd", paramType = "query", dataType = "date", required = false),
            @ApiImplicitParam(value = "发布时间区间", name = "createStart", paramType = "query", dataType = "date", required = false),
            @ApiImplicitParam(value = "发布时间区间", name = "createEnd", paramType = "query", dataType = "date", required = false),
    })
    public PageRet listOtherProduct(OtherProductQueryDto queryDto, int pageNo, int pageSize, Model model) {
        PageRet pageRet = new PageRet();

        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        queryDto.setMemberid(member.getId());
        if (queryDto.getLevelid() != null && queryDto.getLevelid() > 0) {
            Categories productCategory = categoriesService.getCategoryLevel(queryDto.getLevelid());
            if (productCategory != null) {
                if (productCategory.getLevel() == 1) {
                    queryDto.setLevel1id(queryDto.getLevelid());
                } else if (productCategory.getLevel() == 2) {
                    queryDto.setLevel2id(queryDto.getLevelid());
                } else if (productCategory.getLevel() == 3) {
                    queryDto.setLevel3id(queryDto.getLevelid());
                }
            }
        }

        if (queryDto.getUptimeEnd() != null) {
            queryDto.setUptimeEnd(DateUtils.addDays(queryDto.getUptimeEnd(), 1));
        }

        if (queryDto.getCreateEnd() != null) {
            queryDto.setCreateEnd(DateUtils.addDays(queryDto.getCreateEnd(), 1));
        }

        if (queryDto.getDowntimeEnd() != null) {
            queryDto.setDowntimeEnd(DateUtils.addDays(queryDto.getDowntimeEnd(), 1));
        }

        if (queryDto.getUpdatetimeEnd() != null) {
            queryDto.setUpdatetimeEnd(DateUtils.addDays(queryDto.getUpdatetimeEnd(), 1));
        }

        if(queryDto.getPdids() != null && queryDto.getPdids()!="") {
            /*String[] str = pdids.split(",");
            int[] intTemp = new int[str.length];
            for (int i = 0; i <str.length; i++) {
                intTemp[i] = Integer.parseInt(str[i]);
            }*/
            try {
                List<Integer> PdidList = Arrays.asList(queryDto.getPdids().split(",")).stream().map(s -> Integer.parseInt(s.trim())).collect(Collectors.toList());
                queryDto.setPdid(PdidList);
                String pdidStr = org.apache.commons.lang3.StringUtils.join(PdidList, ",");
                queryDto.setPdids(pdidStr);
            }catch (NumberFormatException e){
                pageRet.setResult(BasicRet.ERR);
                pageRet.setMessage("输入商品id格式错误，多个商品id请用一个英文逗号隔开");
                return pageRet;
            }
        }

        PageInfo pageInfo = otherProdService.listOtherProd(queryDto, pageNo, pageSize);

        pageRet.data.setPageInfo(pageInfo);
        pageRet.setResult(BasicRet.SUCCESS);

        return pageRet;
    }

    @RequestMapping(value = "/listOtherProductForSellerCenter", method = RequestMethod.POST)
    @ApiOperation("非紧固件商品分页列表查询,商家中心商品管理专用")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "第几页", name = "pageNo", paramType = "query", dataType = "int", required = true, defaultValue = "1"),
            @ApiImplicitParam(value = "每页显示的条数", name = "pageSize", paramType = "query", dataType = "int", required = true, defaultValue = "20"),
            @ApiImplicitParam(value = "商品名", name = "productname", paramType = "query", dataType = "string", required = false),
            @ApiImplicitParam(value = "品牌", name = "brand", paramType = "query", dataType = "string", required = false),
            @ApiImplicitParam(value = "分类id", name = "levelid", paramType = "query", dataType = "int", required = false, defaultValue = "0"),
            @ApiImplicitParam(value = "商品状态 0=放入仓库 1=待审核(立即发布) 2=审核通过 3=未通过 4=已上架(上架) 5=下架 6=删除 7-违规下架", name = "pdstate", paramType = "query", dataType = "int", required = false, defaultValue = "-1"),
            @ApiImplicitParam(value = "卖家id", name = "memberid", paramType = "query", dataType = "int", required = false, defaultValue = "0"),
            @ApiImplicitParam(value = "用户名", name = "username", paramType = "query", dataType = "string", required = false, defaultValue = ""),
            @ApiImplicitParam(value = "商品id，多个商品id用英文逗号隔开", name = "pdids", paramType = "query", dataType = "String", required = false),
            @ApiImplicitParam(value = "商品编号", name = "pdno", paramType = "query", dataType = "int", required = false),
            @ApiImplicitParam(value = "下架时间", name = "downtimeStart", paramType = "query", dataType = "date", required = false),
            @ApiImplicitParam(value = "下架时间", name = "downtimeEnd", paramType = "query", dataType = "date", required = false),
            @ApiImplicitParam(value = "最后修改价格时间", name = "updatetimeStart", paramType = "query", dataType = "date", required = false),
            @ApiImplicitParam(value = "最后修改价格时间", name = "updatetimeEnd", paramType = "query", dataType = "date", required = false),
            @ApiImplicitParam(value = "上架时间", name = "uptimeStart", paramType = "query", dataType = "date", required = false),
            @ApiImplicitParam(value = "上架时间", name = "uptimeEnd", paramType = "query", dataType = "date", required = false),
            @ApiImplicitParam(value = "发布时间区间", name = "createStart", paramType = "query", dataType = "date", required = false),
            @ApiImplicitParam(value = "发布时间区间", name = "createEnd", paramType = "query", dataType = "date", required = false),
    })
    public PageRet listOtherProductForSellerCenter(OtherProductQueryDto queryDto, int pageNo, int pageSize, Model model) {
        PageRet pageRet = new PageRet();

        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        queryDto.setMemberid(member.getId());
        if (queryDto.getLevelid() != null && queryDto.getLevelid() > 0) {
            Categories productCategory = categoriesService.getCategoryLevel(queryDto.getLevelid());
            if (productCategory != null) {
                if (productCategory.getLevel() == 1) {
                    queryDto.setLevel1id(queryDto.getLevelid());
                } else if (productCategory.getLevel() == 2) {
                    queryDto.setLevel2id(queryDto.getLevelid());
                } else if (productCategory.getLevel() == 3) {
                    queryDto.setLevel3id(queryDto.getLevelid());
                }
            }
        }

        if (queryDto.getUptimeEnd() != null) {
            queryDto.setUptimeEnd(DateUtils.addDays(queryDto.getUptimeEnd(), 1));
        }

        if (queryDto.getCreateEnd() != null) {
            queryDto.setCreateEnd(DateUtils.addDays(queryDto.getCreateEnd(), 1));
        }

        if (queryDto.getDowntimeEnd() != null) {
            queryDto.setDowntimeEnd(DateUtils.addDays(queryDto.getDowntimeEnd(), 1));
        }

        if (queryDto.getUpdatetimeEnd() != null) {
            queryDto.setUpdatetimeEnd(DateUtils.addDays(queryDto.getUpdatetimeEnd(), 1));
        }

        if(queryDto.getPdids() != null && queryDto.getPdids()!="") {
            try {
                List<Integer> PdidList = Arrays.asList(queryDto.getPdids().split(",")).stream().map(s -> Integer.parseInt(s.trim())).collect(Collectors.toList());
                queryDto.setPdid(PdidList);
                String pdidStr = org.apache.commons.lang3.StringUtils.join(PdidList, ",");
                queryDto.setPdids(pdidStr);
            }catch (NumberFormatException e){
                pageRet.setResult(BasicRet.ERR);
                pageRet.setMessage("输入商品id格式错误，多个商品id请用一个英文逗号隔开");
                return pageRet;
            }
        }

        PageInfo pageInfo = otherProdService.listOtherProd(queryDto, pageNo, pageSize);

        pageRet.data.setPageInfo(pageInfo);
        pageRet.setResult(BasicRet.SUCCESS);

        return pageRet;
    }



    @RequestMapping(value = "/exprotExcel/listOtherProduct", method = RequestMethod.GET)
    @ApiOperation("非紧固件商品excel导出")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "第几页", name = "pageNo", paramType = "query", dataType = "int", required = true, defaultValue = "1"),
            @ApiImplicitParam(value = "每页显示的条数", name = "pageSize", paramType = "query", dataType = "int", required = true, defaultValue = "20"),
            @ApiImplicitParam(value = "商品名", name = "productname", paramType = "query", dataType = "string", required = false),
            @ApiImplicitParam(value = "品牌", name = "brand", paramType = "query", dataType = "string", required = false),
            @ApiImplicitParam(value = "分类id", name = "levelid", paramType = "query", dataType = "int", required = false, defaultValue = "0"),
            @ApiImplicitParam(value = "商品状态 0=放入仓库 1=待审核(立即发布) 2=审核通过 3=未通过 4=已上架(上架) 5=下架 6=删除 7-违规下架", name = "pdstate", paramType = "query", dataType = "int", required = false, defaultValue = "-1"),
            @ApiImplicitParam(value = "卖家id", name = "memberid", paramType = "query", dataType = "int", required = false, defaultValue = "0"),
            @ApiImplicitParam(value = "用户名", name = "username", paramType = "query", dataType = "string", required = false, defaultValue = ""),
            @ApiImplicitParam(value = "商品id，多个商品id用英文逗号隔开", name = "pdids", paramType = "query", dataType = "String", required = false),
            @ApiImplicitParam(value = "下架时间", name = "downtimeStart", paramType = "query", dataType = "date", required = false),
            @ApiImplicitParam(value = "下架时间", name = "downtimeEnd", paramType = "query", dataType = "date", required = false),
            @ApiImplicitParam(value = "最后修改价格时间", name = "updatetimeStart", paramType = "query", dataType = "date", required = false),
            @ApiImplicitParam(value = "最后修改价格时间", name = "updatetimeEnd", paramType = "query", dataType = "date", required = false),
            @ApiImplicitParam(value = "上架时间", name = "uptimeStart", paramType = "query", dataType = "date", required = false),
            @ApiImplicitParam(value = "上架时间", name = "uptimeEnd", paramType = "query", dataType = "date", required = false),
            @ApiImplicitParam(value = "发布时间区间", name = "createStart", paramType = "query", dataType = "date", required = false),
            @ApiImplicitParam(value = "发布时间区间", name = "createEnd", paramType = "query", dataType = "date", required = false),
    })
    public ResponseEntity<InputStreamResource> listOtherProductForExportExcel(OtherProductQueryDto queryDto, int pageNo, int pageSize, Model model) {
        PageRet pageRet = new PageRet();

        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        queryDto.setMemberid(member.getId());
        if (queryDto.getLevelid() != null && queryDto.getLevelid() > 0) {
            Categories productCategory = categoriesService.getCategoryLevel(queryDto.getLevelid());
            if (productCategory != null) {
                if (productCategory.getLevel() == 1) {
                    queryDto.setLevel1id(queryDto.getLevelid());
                } else if (productCategory.getLevel() == 2) {
                    queryDto.setLevel2id(queryDto.getLevelid());
                } else if (productCategory.getLevel() == 3) {
                    queryDto.setLevel3id(queryDto.getLevelid());
                }
            }
        }

        if (queryDto.getUptimeEnd() != null) {
            queryDto.setUptimeEnd(DateUtils.addDays(queryDto.getUptimeEnd(), 1));
        }

        if (queryDto.getCreateEnd() != null) {
            queryDto.setCreateEnd(DateUtils.addDays(queryDto.getCreateEnd(), 1));
        }

        if (queryDto.getDowntimeEnd() != null) {
            queryDto.setDowntimeEnd(DateUtils.addDays(queryDto.getDowntimeEnd(), 1));
        }

        if (queryDto.getUpdatetimeEnd() != null) {
            queryDto.setUpdatetimeEnd(DateUtils.addDays(queryDto.getUpdatetimeEnd(), 1));
        }

        if(queryDto.getPdids() != null && queryDto.getPdids()!="") {
            /*String[] str = pdids.split(",");
            int[] intTemp = new int[str.length];
            for (int i = 0; i <str.length; i++) {
                intTemp[i] = Integer.parseInt(str[i]);
            }*/
            try {
                List<Integer> PdidList = Arrays.asList(queryDto.getPdids().split(",")).stream().map(s -> Integer.parseInt(s.trim())).collect(Collectors.toList());
                queryDto.setPdid(PdidList);
                String pdidStr = org.apache.commons.lang3.StringUtils.join(PdidList, ",");
                queryDto.setPdids(pdidStr);
            }catch (NumberFormatException e){
               /* pageRet.setResult(BasicRet.ERR);
                pageRet.setMessage("输入商品id格式错误，多个商品id请用一个英文逗号隔开");
                return pageRet;*/
            }
        }

        List<Map<String,Object>> resList = otherProdService.listOtherProdForSellerExportExcel(queryDto);

        String[] rowTitles = new String[]{"卖家公司名称","商品名称","别名","规格","挂牌价(3天价)",	"30天价格",
                "60天价格","90天价格",	"市场价",	"库存",	"商品编码",
                "品牌","起订量",	"重量（KG）","订货区间一","折扣一","订货区间二","折扣二",
                "订货区间三","折扣三","仓库名称",	"仓库地址","运费方式","SEO标题","SEO关键字","SEO描述"};

        XSSFWorkbook workbook = null;
        try {
            workbook = ExcelGen.common("非紧固件商品列表",rowTitles,resList,null);
            if(workbook!=null){
                ByteArrayOutputStream baos=new   ByteArrayOutputStream();
                workbook.write(baos);
                System.out.println(baos.toByteArray().length);
                HttpHeaders headers = new HttpHeaders();
                headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
                headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", new String("非紧固件商品列表.xlsx".getBytes(),"iso-8859-1")));
                headers.add("Pragma", "no-cache");
                headers.add("Expires", "0");
                String contentType = "application/vnd.ms-excel";
                return ResponseEntity.ok()
                        .headers(headers).contentType(MediaType.parseMediaType(contentType))
                        .body(new InputStreamResource(new ByteArrayInputStream(baos.toByteArray())));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(workbook != null){
                try {
                    workbook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return  null;
    }





    @PostMapping("/othereProduct/detail")
    @ApiOperation("其他类商品详情")
    public OtherProductDetailRet detailOtherProduct(@RequestParam(required = true) long id, Model model) {
        OtherProductDetailRet detailRet = new OtherProductDetailRet();

        ProductInfo productInfo = productInfoService.getById(id);
        if (productInfo == null) {
            detailRet.setMessage("该商品不存在");
            detailRet.setResult(BasicRet.ERR);
            return detailRet;
        }

        if (!productInfo.getProducttype().equals(AppConstant.OTHER_PRO_TYPE)) {
            detailRet.setMessage("该商品不属于非紧固件类商品");
            detailRet.setResult(BasicRet.ERR);
            return detailRet;
        }

        OtherProdDetailViewDto viewDto = new OtherProdDetailViewDto();
        BeanUtils.copyProperties(productInfo, viewDto);

        List<OtherProdStore> prodStoreList = otherProdService.getOtherProdStore(productInfo.getId());

        List<OtherProdStoreView> prodStoreViewList = new ArrayList<>();

        String intervalprice = "";

        for (OtherProdStore pdStore : prodStoreList) {
            viewDto.setStoreid(pdStore.getStoreid());
            viewDto.setStorename(pdStore.getStorename());
            List<ProductAttr> attrList = productAttrService.getListByPidAndPdno(productInfo.getId(), pdStore.getPdno());
            pdStore.setProductAttrList(attrList);


            //viewDto.setFreightmode(pdStore.getFreightmode());
            viewDto.setCostprice(pdStore.getCostprice());
            viewDto.setStepwiseprice(pdStore.isStepwiseprice());

            viewDto.setMarketprice(pdStore.getMarketprice());

            intervalprice = pdStore.getIntervalprice();
            OtherProdStoreView otherProdStoreView = new OtherProdStoreView();
            BeanUtils.copyProperties(pdStore, otherProdStoreView);
            prodStoreViewList.add(otherProdStoreView);
        }

        //运费模版
    //    ShippingTemplates shippingTemplates = null;

//        if (viewDto.getFreightmode() > 0) {
//            shippingTemplates = shippingTemplatesService.getFullTemplatesById(viewDto.getFreightmode());
//        }

//        List<Attributetbl> attributetblList =  attributetblService.getAttributeWithValue(productInfo.getProductnameid());

        detailRet.viewDto = viewDto;
        detailRet.prodStoreList = prodStoreViewList;
//        detailRet.attributetblList = attributetblList;
        detailRet.intervalprice = intervalprice;
        //detailRet.shippingTemplates = shippingTemplates;

        detailRet.setResult(BasicRet.SUCCESS);
        return detailRet;
    }


    private class OtherProductDetailRet extends BasicRet {
        private OtherProdDetailViewDto viewDto;

        private List<OtherProdStoreView> prodStoreList;

        // private List<Attributetbl> attributetblList;

        private String intervalprice;

        private ShippingTemplates shippingTemplates;

        public String getIntervalprice() {
            return intervalprice;
        }

        public void setIntervalprice(String intervalprice) {
            this.intervalprice = intervalprice;
        }

        public OtherProdDetailViewDto getViewDto() {
            return viewDto;
        }

        public void setViewDto(OtherProdDetailViewDto viewDto) {
            this.viewDto = viewDto;
        }

        public List<OtherProdStoreView> getProdStoreList() {
            return prodStoreList;
        }

        public void setProdStoreList(List<OtherProdStoreView> prodStoreList) {
            this.prodStoreList = prodStoreList;
        }

        public ShippingTemplates getShippingTemplates() {
            return shippingTemplates;
        }

        public void setShippingTemplates(ShippingTemplates shippingTemplates) {
            this.shippingTemplates = shippingTemplates;
        }


        //        public List<Attributetbl> getAttributetblList() {
//            return attributetblList;
//        }
//
//        public void setAttributetblList(List<Attributetbl> attributetblList) {
//            this.attributetblList = attributetblList;
//        }
    }


    @RequestMapping(value = "/otherProduct/update", method = RequestMethod.POST)
    @ApiOperation("修改其他类商品")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "品名id", name = "productnameid", required = true, dataType = "int", paramType = "query"),
            // @ApiImplicitParam(value = "材质id",name = "materialid",required = true,dataType = "int",paramType = "query"),
            //@ApiImplicitParam(value = "牌号id",name = "cardnumid",required = true,dataType = "int",paramType = "query"),
            @ApiImplicitParam(value = "品牌id", name = "brandid", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(value = "商品别名", name = "productalias", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "商品副标题", name = "subtitle", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "起订量", name = "startnum", required = true, dataType = "float", paramType = "query"),
            @ApiImplicitParam(value = "单位", name = "unit", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "成本价", name = "costprice", required = false, dataType = "float", paramType = "query"),
            @ApiImplicitParam(value = "市场价", name = "marketprice", required = true, dataType = "float", paramType = "query"),
            @ApiImplicitParam(value = "重量", name = "weight", required = true, dataType = "float", paramType = "query"),
            @ApiImplicitParam(value = "仓库id", name = "storeid", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(value = "运费方式,包邮-1", name = "shippingtemplatesgroup", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(value = "是否推荐", name = "recommended", required = false, dataType = "boolean", paramType = "query"),
            @ApiImplicitParam(value = "seo标题", name = "seotitle", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "seo关键字", name = "seokey", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "seo描述", name = "seovalue", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "描述", name = "pddes", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "商品状态 0=放入仓库 1=待审核(立即发布)", name = "pdstate", required = true, dataType = "int", paramType = "query", defaultValue = "0"),
            @ApiImplicitParam(value = "商品json [{\"startnum\":1,\"minplus\":1,\"weight\":1.0,\"prodprice\":1,\"thirtyprice\":2,\"sixtyprice\":20,\"ninetyprice\":90,\"pdstorenum\":100,\"pdno\":\"11123\",\"productAttrs\":[{\"attributeid\":1,\"attribute\":\"颜色\",\"value\":\"红色\"},{\"attributeid\":2,\"attribute\":\"尺寸\",\"value\":\"180\"}]}]", name = "prodJson", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(value = "是否开启阶梯价格", name = "stepwiseprice", required = true, dataType = "boolean", defaultValue = "false", paramType = "query"),
            @ApiImplicitParam(value = "区间价格json,例如：[{\"start\": 1,\"end\": 10,\"rate\": 90},{\"start\": 11,\"end\": 20,\"rate\": 80},{\"start\": 21,\"end\": 0,\"rate\": 70}]", name = "intervalprice", required = false, dataType = "string", paramType = "query"),
    })
    @PreAuthorize("hasAuthority('" + SellerAuthorityConst.POSTGOODS + "') || hasAuthority('" + SellerAuthorityConst.ALL + "')")
    public BasicRet updateOtherProduct(
            @RequestParam(required = true) long id,
            @RequestParam(required = true) long level1id,
            @RequestParam(required = true) long level2id,
            @RequestParam(required = false, defaultValue = "0") long level3id,
            @RequestParam(required = true) long productnameid,
            //@RequestParam(required = true) long materialid,
            //@RequestParam(required = true) long cardnumid,
            @RequestParam(required = true) long brandid,
            @RequestParam(required = false, defaultValue = "") String productalias,
            @RequestParam(required = false, defaultValue = "") String subtitle,
//            @RequestParam(required = true) BigDecimal startnum,
            @RequestParam(required = true) String unit,
            @RequestParam(required = false) BigDecimal costprice,
//            @RequestParam(required = true) BigDecimal weight,
            @RequestParam(required = true) long storeid,
            @RequestParam(required = true) long shippingtemplatesgroup,
            @RequestParam(required = false) boolean recommended,
            @RequestParam(required = false, defaultValue = "") String seotitle,
            @RequestParam(required = false, defaultValue = "") String seokey,
            @RequestParam(required = false, defaultValue = "") String seovalue,
            @RequestParam(required = true, defaultValue = "0") short pdstate,
            @RequestParam(required = true) String prodJson,
            @RequestParam(required = true) String[] pdpicture,
            @RequestParam(required = false) String pddes,
            @RequestParam(required = true, defaultValue = "false") Boolean stepwiseprice,
            @RequestParam(required = false) String intervalprice,
            @RequestParam(required = true) BigDecimal marketprice,
            Model model, HttpServletRequest request
    ) {
        BasicRet basicRet = new BasicRet();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

        ProductInfo productInfo = productInfoService.getById(id);
        if (productInfo == null) {
            return new BasicRet(BasicRet.ERR, "该商品不存在");
        }


        if (stepwiseprice && !CommonUtils.isGoodJson(intervalprice)) {
            return new BasicRet(BasicRet.ERR, "区间价格json格式不对");
        }


        if (!productInfo.getMemberid().equals(member.getId())) {
            return new BasicRet(BasicRet.ERR, "该商品不属于你");
        }

        if (productInfo.getPdstate() == Quantity.STATE_4) {
            return new BasicRet(BasicRet.ERR, "已上架商品不可修改");
        }

        if (pdstate != Quantity.STATE_0 && pdstate != Quantity.STATE_1) {
            return new BasicRet(BasicRet.ERR, "商品状态不合法");
        }


        Categories categories = categoriesService.getById(level1id);
        if (categories != null) {
            productInfo.setLevel1id(level1id);
            productInfo.setLevel1(categories.getName());
        } else {
            return new BasicRet(BasicRet.ERR, "一级分类错误");
        }

        if(!categories.getCatetype().equals("生活类") && !categories.getCatetype().equals("工业品")){
            return new BasicRet(BasicRet.ERR, "该接口只可修改生活类和工业品的商品");
        }
        productInfo.setType(JinshangUtils.productType(categories.getCatetype()));


        categories = categoriesService.getById(level2id);
        if (categories != null) {
            productInfo.setLevel2id(level2id);
            productInfo.setLevel2(categories.getName());
        } else {
            return new BasicRet(BasicRet.ERR, "二级分类错误");
        }

        if (level3id > 0) {
            categories = categoriesService.getById(level3id);
            if (categories != null) {
                productInfo.setLevel3id(level3id);
                productInfo.setLevel3(categories.getName());
            } else {
                return new BasicRet(BasicRet.ERR, "三级分类错误");
            }
        } else {
            productInfo.setLevel3id((long) 0);
            productInfo.setLevel3("");
        }

        ProductName productName = productNameService.getById(productnameid);
        if (productName == null) {
            return new BasicRet(BasicRet.ERR, "品名错误");
        } else {
            productInfo.setProductnameid(productnameid);
            productInfo.setProductname(productName.getName());
        }

//        Material material = materialService.getById(materialid);
//        if(material == null){
//            return  new BasicRet(BasicRet.ERR,"材质错误");
//        }else {
//            productInfo.setMaterial(material.getName());
//            productInfo.setMaterialid(materialid);
//        }
//
//        CardNum cardNum =  cardNumService.getById(cardnumid);
//        if(cardNum == null){
//            return  new BasicRet(BasicRet.ERR,"牌号错误");
//        }else{
//            productInfo.setCardnum(cardNum.getName());
//            productInfo.setCardnumid(cardnumid);
//        }

        Brand brand = brandService.getById(brandid);
        if (brand == null) {
            return new BasicRet(BasicRet.ERR, "品牌错误");
        } else {
            productInfo.setBrand(brand.getName());
            productInfo.setBrandid(brandid);
        }

        Store store = storeService.getByIdAndMemberId(storeid, member.getId());
        if (store == null) {
            return new BasicRet(BasicRet.ERR, "仓库错误");
        }

        if (!CommonUtils.isGoodJson(prodJson)) {
            return new BasicRet(BasicRet.ERR, "json格式不正确");
        }
//[{"prodprice":6899,"thirtyprice":6699,"sixtyprice":6499,"ninetyprice":6299,"pdstorenum":234,"pdno":"122","productAttrs":[{"attributeid":213,"attribute":"颜色","value":"亮黑"},{"attributeid":214,"attribute":"内存","value":"32G"},{"attributeid":276,"attribute":"网络","value":"全网4G"}]},{"prodprice":"6899","thirtyprice":"","sixtyprice":"","ninetyprice":"","pdstorenum":"333","pdno":"123","productAttrs":[{"attributeid":213,"attribute":"颜色","value":"亮黑"},{"attributeid":214,"attribute":"内存","value":"64G"},{"attributeid":276,"attribute":"网络","value":"电信4G"}]},{"prodprice":"6899","thirtyprice":"","sixtyprice":"","ninetyprice":"","pdstorenum":"444","pdno":"124","productAttrs":[{"attributeid":213,"attribute":"颜色","value":"亮黑"},{"attributeid":214,"attribute":"内存","value":"128G"},{"attributeid":276,"attribute":"网络","value":"全网4G"}]},{"prodprice":"6899","thirtyprice":"","sixtyprice":"","ninetyprice":"","pdstorenum":"555","pdno":"124","productAttrs":[{"attributeid":213,"attribute":"颜色","value":"银色"},{"attributeid":214,"attribute":"内存","value":"128G"},{"attributeid":276,"attribute":"网络","value":"联通4G"}]},{"prodprice":"6899","thirtyprice":"","sixtyprice":"","ninetyprice":"","pdstorenum":"111","pdno":"126","productAttrs":[{"attributeid":213,"attribute":"颜色","value":"玫瑰金"},{"attributeid":214,"attribute":"内存","value":"256G"},{"attributeid":276,"attribute":"网络","value":"移动4G"}]}

        prodJson = prodJson.replaceAll("\"thirtyprice\":\"\"","\"thirtyprice\":\"0\"").
                replaceAll("\"sixtyprice\":\"\"","\"sixtyprice\":\"0\"")
                .replaceAll("\"ninetyprice\":\"\"","\"ninetyprice\":\"0\"")
                .replaceAll("\"minplus\":\"\"","\"minplus\":\"0\"");
        List<OtherProductInfo> resultList = gson.fromJson(prodJson, new TypeToken<List<OtherProductInfo>>() {
        }.getType());

        if (resultList == null || resultList.size() == 0) {
            return new BasicRet(BasicRet.ERR, "请填写商品信息");
        }


        Set<String> pdnoSet = new HashSet<>();
        for (OtherProductInfo otherProductInfo : resultList) {
            pdnoSet.add(otherProductInfo.getPdno());
        }

        if(pdnoSet.size() != resultList.size()){
            return  new BasicRet(BasicRet.ERR,"商品编码不可重复");
        }



        for (OtherProductInfo otherProductInfo : resultList) {
            BigDecimal zeroBigDecimal = new BigDecimal(0);
            if(otherProductInfo.getStartnum()==null || otherProductInfo.getStartnum().compareTo(zeroBigDecimal) <=0){
                return new BasicRet(BasicRet.ERR, "起订量不可为空或0");
            }

            if(otherProductInfo.getWeight() == null || otherProductInfo.getWeight().compareTo(zeroBigDecimal) <=0){
                return new BasicRet(BasicRet.ERR, "重量不可为空或0");
            }

            if (otherProductInfo.getProdprice().compareTo(zeroBigDecimal) == 0) otherProductInfo.setProdprice(null);
            if (otherProductInfo.getNinetyprice() != null && otherProductInfo.getNinetyprice().compareTo(zeroBigDecimal) == 0) otherProductInfo.setNinetyprice(null);
            if (otherProductInfo.getSixtyprice() != null && otherProductInfo.getSixtyprice().compareTo(zeroBigDecimal) == 0) otherProductInfo.setSixtyprice(null);
            if (otherProductInfo.getThirtyprice() != null && otherProductInfo.getThirtyprice().compareTo(zeroBigDecimal) == 0) otherProductInfo.setThirtyprice(null);


            if(otherProductInfo.getMinplus().compareTo(Quantity.BIG_DECIMAL_0) >0){
                String mes = this.checkOtherProdNum(otherProductInfo.getStartnum(),otherProductInfo.getMinplus());
                if(StringUtils.hasText(mes)){
                    return  new BasicRet(BasicRet.ERR,otherProductInfo.getPdno() + "编号的商品"+mes);
                }
            }




            ProductStore dbProductStore = productStoreService.getByStoreidAndPdNo(member.getId(), store.getId(),otherProductInfo.getPdno());
            if (dbProductStore != null && !dbProductStore.getPdid().equals(id)) {
                return new BasicRet(BasicRet.ERR, otherProductInfo.getPdno() + "编号的商品已经发布过了，不可重复发布");
            }
        }
        //删除商品仓库表，重新添加
        productStoreService.deleteByProductid(id);
        //删除商品属性表，重新添加
        productAttrService.deleteByProductid(id);


        //区间价格
        List<Map<String, String>> intervalpriceList = gson.fromJson(intervalprice, new TypeToken<List<Map<String, String>>>() {
        }.getType());
        List<Map<String, String>> saveList = new ArrayList<>();
        for (Map<String, String> map : intervalpriceList) {
            if (map.containsKey("start") && StringUtils.hasText(map.get("start"))) {
                saveList.add(map);
            }
        }

        productInfo.setProductalias(productalias);
        productInfo.setSubtitle(subtitle);
        productInfo.setUnit(unit);
        productInfo.setUpdatetime(new Date());
        productInfo.setProducttype("其他");
        productInfo.setMemberid(member.getId());
        productInfo.setRecommended(recommended);
        productInfo.setSeovalue(seovalue);
        productInfo.setSeotitle(seotitle);
        productInfo.setSeokey(seokey);
        productInfo.setPddes(pddes);
        productInfo.setPdpicture(pdpicture);
//        productInfo.setWeight(weight);
        productInfo.setId(id);
        productInfo.setPdstate(pdstate);



        if(shippingtemplatesgroup != -1){
            ShippingTemplateGroup shippingTemplateGroup = shippingTemplateGroupService.getShippingTemplateGroup(shippingtemplatesgroup);
            if(shippingTemplateGroup == null || !shippingTemplateGroup.getMemberid().equals(member.getId())){
                basicRet.setMessage("运费合计不存在");
                basicRet.setResult(BasicRet.ERR);
                return  basicRet;
            }
            productInfo.setShippingtemplatesgroup(shippingTemplateGroup.getId());
        }else{
            productInfo.setShippingtemplatesgroup((long)-1);
        }


        BigDecimal minPrice = new BigDecimal(0);
        BigDecimal heightPrice = new BigDecimal(0);

        for (OtherProductInfo otherProductInfo : resultList) {
            ProductStore productStore = new ProductStore();
            productStore.setStartnum(otherProductInfo.getStartnum());
            productStore.setProdprice(otherProductInfo.getProdprice());
            productStore.setCostprice(costprice);
            productStore.setMarketprice(marketprice);
            productStore.setPdstorenum(otherProductInfo.getPdstorenum());
            productStore.setStoreunit(unit);
            productStore.setLocation(store.getAddress());
            //productStore.setFreightmode(freightmode);
            productStore.setStorename(store.getName());
            productStore.setStoreid(storeid);
            productStore.setPdno(otherProductInfo.getPdno());
            productStore.setPdid(productInfo.getId());
            productStore.setStepwiseprice(stepwiseprice); //是否开启阶梯价格

            productStore.setNinetyprice(otherProductInfo.getNinetyprice());
            productStore.setSixtyprice(otherProductInfo.getSixtyprice());
            productStore.setThirtyprice(otherProductInfo.getThirtyprice());
            productStore.setThreeprice(otherProductInfo.getProdprice());
            productStore.setIntervalprice(gson.toJson(saveList));
            productStore.setWeight(otherProductInfo.getWeight());
            productStore.setMinplus(otherProductInfo.getMinplus());

            productStoreService.insertSelective(productStore);

            List<ProductAttr> productAttrList = otherProductInfo.getProductAttrs();
            for (ProductAttr att : productAttrList) {
                att.setProductid(productInfo.getId());
                att.setPdno(otherProductInfo.getPdno());
                productAttrService.add(att);
            }


            //最低价 最高价
            if (minPrice.compareTo(new BigDecimal(0)) == Quantity.STATE_0) {
                minPrice = productStore.getProdprice();
            } else if (minPrice.compareTo(productStore.getProdprice()) == Quantity.STATE_1) {
                minPrice = productStore.getProdprice();
            }

            if (heightPrice.compareTo(new BigDecimal(0)) == Quantity.STATE_0) {
                heightPrice = productStore.getProdprice();
            } else if (heightPrice.compareTo(productStore.getProdprice()) == Quantity.STATE_) {
                heightPrice = productStore.getProdprice();
            }
        }

        productInfo.setHeightprice(heightPrice);
        productInfo.setMinprice(minPrice);

        //更新
        productInfoService.updateById(productInfo);


        //保存日志
        memberLogOperator.saveMemberLog(member, null, "修改其他类商品：" + productInfo.getProductname(), request, memberOperateLogService);


        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("修改成功");
        return basicRet;
    }


    @RequestMapping(value = "/listFastenerProduct", method = RequestMethod.POST)
    @ApiOperation("紧固件商品分页列表查询")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "第几页", name = "pageNo", paramType = "query", dataType = "int", required = true, defaultValue = "1"),
            @ApiImplicitParam(value = "每页显示的条数", name = "pageSize", paramType = "query", dataType = "int", required = true, defaultValue = "20"),
            @ApiImplicitParam(value = "商品名", name = "productname", paramType = "query", dataType = "string", required = false),
            @ApiImplicitParam(value = "品牌", name = "brand", paramType = "query", dataType = "string", required = false),
            @ApiImplicitParam(value = "分类id", name = "levelid", paramType = "query", dataType = "int", required = false, defaultValue = "0"),
            @ApiImplicitParam(value = "规格", name = "stand", paramType = "query", dataType = "string", required = false),
            @ApiImplicitParam(value = "是否有库存,0-全部,1-有，2-没有", name = "haveStorenum", paramType = "query", dataType = "int", required = false, defaultValue = "0"),
            @ApiImplicitParam(value = "商品状态0=放入仓库1=待审核2=审核通过3=未通过4=已上架5=下架6=删除7=违规下架", name = "pdstate", paramType = "query", dataType = "int", required = false, defaultValue = "-1"),
            @ApiImplicitParam(value = "商品id，多个商品id用英文逗号隔开", name = "pdids", paramType = "query", dataType = "String", required = false),
            @ApiImplicitParam(value = "商品编号", name = "pdno", paramType = "query", dataType = "int", required = false),
            @ApiImplicitParam(value = "材质id", name = "materialid", paramType = "query", dataType = "int", required = false, defaultValue = "-1"),
            @ApiImplicitParam(value = "牌号id", name = "cardnumid", paramType = "query", dataType = "int", required = false, defaultValue = "-1"),
            @ApiImplicitParam(value = "上架时间", name = "uptimeStart", paramType = "query", dataType = "date", required = false),
            @ApiImplicitParam(value = "上架时间", name = "uptimeEnd", paramType = "query", dataType = "date", required = false),
            @ApiImplicitParam(value = "下架时间", name = "downtimeStart", paramType = "query", dataType = "date", required = false),
            @ApiImplicitParam(value = "下架时间", name = "downtimeEnd", paramType = "query", dataType = "date", required = false),
            @ApiImplicitParam(value = "最后修改价格时间", name = "updatetimeStart", paramType = "query", dataType = "date", required = false),
            @ApiImplicitParam(value = "最后修改价格时间", name = "updatetimeEnd", paramType = "query", dataType = "date", required = false),
            @ApiImplicitParam(value = "发布时间区间", name = "createStart", paramType = "query", dataType = "date", required = false),
            @ApiImplicitParam(value = "发布时间区间", name = "createEnd", paramType = "query", dataType = "date", required = false),
            @ApiImplicitParam(value = "多规格属性  {\"DIN933\":[\"M16\",\"M3\"],\"GB5783\":[\"M16\",\"M3\"]}", name = "standJson", paramType = "query", dataType = "string", required = false),
    })
    public PageRet listFastenerProduct(@RequestParam(required = true, defaultValue = "1") int pageNo,
                                       @RequestParam(required = true, defaultValue = "20") int pageSize,
                                       @RequestParam(required = false) String productname,
                                       @RequestParam(required = false) String brand,
                                       @RequestParam(required = false, defaultValue = "0") long levelid,
                                       @RequestParam(required = false) String stand,
                                       @RequestParam(required = false, defaultValue = "0") short haveStorenum,
                                       @RequestParam(required = false, defaultValue = "-1") short pdstate,
                                       @RequestParam(required = false) String pdids,
                                       @RequestParam(required = false) String pdno,
                                       @RequestParam(required = false, defaultValue = "-1") long materialid,
                                       @RequestParam(required = true, defaultValue = "-1") long cardnumid,
                                       @RequestParam(required = false) Date uptimeStart,
                                       @RequestParam(required = false) Date uptimeEnd,
                                       @RequestParam(required = false) Date downtimeStart,
                                       @RequestParam(required = false) Date downtimeEnd,
                                       @RequestParam(required = false) Date updatetimeStart,
                                       @RequestParam(required = false) Date updatetimeEnd,
                                       @RequestParam(required = false) Date createStart,
                                       @RequestParam(required = false) Date createEnd,
                                       @RequestParam(required = false) String standJson,
                                       Model model) {
        PageRet pageRet = new PageRet();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

        ProductInfoQuery productInfo = new ProductInfoQuery();
        ProductStore productStore = new ProductStore();

        productInfo.setMemberid(member.getId());

        if (StringUtils.hasText(productname)) {
            productInfo.setProductname(productname);
        }

        if (StringUtils.hasText(pdno)) {
            productInfo.setPdno(pdno);
        }

        if (StringUtils.hasText(brand)) {
            productInfo.setBrand(brand);
        }

        if (StringUtils.hasText(standJson)) {
            if (!CommonUtils.isGoodJson(standJson)) {
                pageRet.setResult(BasicRet.ERR);
                pageRet.setMessage("json格式错误");
                return pageRet;
            }
            Map<String,List> standMap = gson.fromJson(standJson, new TypeToken<Map<String,List>>() {
            }.getType());
            productInfo.setStandMap(standMap);
        }


        if (levelid > 0) {
            Categories productCategory = categoriesService.getCategoryLevel(levelid);
            if (productCategory != null) {
                if (productCategory.getLevel() == 1) {
                    productInfo.setLevel1id(levelid);
                } else if (productCategory.getLevel() == 2) {
                    productInfo.setLevel2id(levelid);
                } else if (productCategory.getLevel() == 3) {
                    productInfo.setLevel3id(levelid);
                }
            }
        }

        if (StringUtils.hasText(stand)) {
            productInfo.setStand(stand);
        }

        if (haveStorenum == 1 || haveStorenum == 2) {
            productStore.setHaveStorenum(haveStorenum);
        }

        productInfo.setCardnumid(cardnumid);
        productInfo.setMaterialid(materialid);
        productInfo.setPdstate(pdstate);

        if (uptimeStart != null) {
            productInfo.setUptimeStart(uptimeStart);
        }

        if (uptimeEnd != null) {
            productInfo.setUptimeEnd(DateUtils.addDays(uptimeEnd, 1));
        }

        if(pdids != null && pdids !="") {
            /*String[] str = pdids.split(",");
            int[] intTemp = new int[str.length];
            for (int i = 0; i <str.length; i++) {
                intTemp[i] = Integer.parseInt(str[i]);
            }*/
            try {
                List<Integer> PdidList = Arrays.asList(pdids.split(",")).stream().map(s -> Integer.parseInt(s.trim())).collect(Collectors.toList());
                productInfo.setPdids(PdidList);
            }catch (NumberFormatException e){
                pageRet.setResult(BasicRet.ERR);
                pageRet.setMessage("输入商品id格式错误，多个商品id请用一个英文逗号隔开");
                return pageRet;
            }
        }

        if (downtimeStart != null) {
            productInfo.setDowntimeStart(downtimeStart);
        }

        if (downtimeEnd != null) {
            productInfo.setDowntimeEnd(DateUtils.addDays(downtimeEnd, 1));
        }

        if (updatetimeStart != null) {
            productInfo.setUpdatetimeStart(updatetimeStart);
        }

        if (updatetimeEnd != null) {
            productInfo.setUpdatetimeEnd(DateUtils.addDays(updatetimeEnd, 1));
        }

        if (createStart != null) {
            productInfo.setCreateStart(createStart);
        }

        if (createEnd != null) {
            productInfo.setCreateEnd(DateUtils.addDays(createEnd, 1));
        }

        productInfo.setProductStore(productStore);
        PageInfo pageInfo = productInfoService.listFastenerProduct(pageNo, pageSize, productInfo);
        pageRet.data.setPageInfo(pageInfo);
        pageRet.setResult(BasicRet.SUCCESS);
        return pageRet;
    }


    @RequestMapping(value = "/listFastenerProductMulti", method = RequestMethod.POST)
    @ApiOperation("紧固件商品多规格查询")
    @ApiImplicitParams({
            /*@ApiImplicitParam(value = "第几页", name = "pageNo", paramType = "query", dataType = "int", required = true, defaultValue = "1"),
            @ApiImplicitParam(value = "每页显示的条数", name = "pageSize", paramType = "query", dataType = "int", required = true, defaultValue = "20"),
            */@ApiImplicitParam(value = "商品名", name = "productname", paramType = "query", dataType = "string", required = false),
            @ApiImplicitParam(value = "品牌", name = "brand", paramType = "query", dataType = "string", required = false),
            @ApiImplicitParam(value = "分类id", name = "levelid", paramType = "query", dataType = "int", required = false, defaultValue = "0"),
            @ApiImplicitParam(value = "规格", name = "stand", paramType = "query", dataType = "string", required = false),
            @ApiImplicitParam(value = "是否有库存,0-全部,1-有，2-没有", name = "haveStorenum", paramType = "query", dataType = "int", required = false, defaultValue = "0"),
            @ApiImplicitParam(value = "商品状态0=放入仓库1=待审核2=审核通过3=未通过4=已上架5=下架6=删除7=违规下架", name = "pdstate", paramType = "query", dataType = "int", required = false, defaultValue = "-1"),
            @ApiImplicitParam(value = "商品id，多个商品id用英文逗号隔开", name = "pdids", paramType = "query", dataType = "String", required = false),
            @ApiImplicitParam(value = "商品编号", name = "pdno", paramType = "query", dataType = "int", required = false),
            @ApiImplicitParam(value = "材质id", name = "materialid", paramType = "query", dataType = "int", required = false, defaultValue = "-1"),
            @ApiImplicitParam(value = "牌号id", name = "cardnumid", paramType = "query", dataType = "int", required = false, defaultValue = "-1"),
            @ApiImplicitParam(value = "上架时间", name = "uptimeStart", paramType = "query", dataType = "date", required = false),
            @ApiImplicitParam(value = "上架时间", name = "uptimeEnd", paramType = "query", dataType = "date", required = false),
            @ApiImplicitParam(value = "下架时间", name = "downtimeStart", paramType = "query", dataType = "date", required = false),
            @ApiImplicitParam(value = "下架时间", name = "downtimeEnd", paramType = "query", dataType = "date", required = false),
            @ApiImplicitParam(value = "最后修改价格时间", name = "updatetimeStart", paramType = "query", dataType = "date", required = false),
            @ApiImplicitParam(value = "最后修改价格时间", name = "updatetimeEnd", paramType = "query", dataType = "date", required = false),
            @ApiImplicitParam(value = "发布时间区间", name = "createStart", paramType = "query", dataType = "date", required = false),
            @ApiImplicitParam(value = "发布时间区间", name = "createEnd", paramType = "query", dataType = "date", required = false),
    })
    public MultiRet MultiSpecification(/*@RequestParam(required = true, defaultValue = "1") int pageNo,
                                       @RequestParam(required = true, defaultValue = "20") int pageSize,*/
            @RequestParam(required = false) String productname,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false, defaultValue = "0") long levelid,
            @RequestParam(required = false) String stand,
            @RequestParam(required = false, defaultValue = "0") short haveStorenum,
            @RequestParam(required = false, defaultValue = "-1") short pdstate,
            @RequestParam(required = false) String pdids,
            @RequestParam(required = false) String pdno,
            @RequestParam(required = false, defaultValue = "-1") long materialid,
            @RequestParam(required = true, defaultValue = "-1") long cardnumid,
            @RequestParam(required = false) Date uptimeStart,
            @RequestParam(required = false) Date uptimeEnd,
            @RequestParam(required = false) Date downtimeStart,
            @RequestParam(required = false) Date downtimeEnd,
            @RequestParam(required = false) Date updatetimeStart,
            @RequestParam(required = false) Date updatetimeEnd,
            @RequestParam(required = false) Date createStart,
            @RequestParam(required = false) Date createEnd,
            Model model){
        MultiRet multiRet = new MultiRet();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

        ProductInfoQuery productInfo = new ProductInfoQuery();
        ProductStore productStore = new ProductStore();

        productInfo.setMemberid(member.getId());

        if (StringUtils.hasText(productname)) {
            productInfo.setProductname(productname);
        }

        if (StringUtils.hasText(pdno)) {
            productInfo.setPdno(pdno);
        }

        if (StringUtils.hasText(brand)) {
            productInfo.setBrand(brand);
        }

        if (levelid > 0) {
            Categories productCategory = categoriesService.getCategoryLevel(levelid);
            if (productCategory != null) {
                if (productCategory.getLevel() == 1) {
                    productInfo.setLevel1id(levelid);
                } else if (productCategory.getLevel() == 2) {
                    productInfo.setLevel2id(levelid);
                } else if (productCategory.getLevel() == 3) {
                    productInfo.setLevel3id(levelid);
                }
            }
        }

        if (StringUtils.hasText(stand)) {
            productInfo.setStand(stand);
        }

        if (haveStorenum == 1 || haveStorenum == 2) {
            productStore.setHaveStorenum(haveStorenum);
        }

        productInfo.setCardnumid(cardnumid);
        productInfo.setMaterialid(materialid);
        productInfo.setPdstate(pdstate);

        if (uptimeStart != null) {
            productInfo.setUptimeStart(uptimeStart);
        }

        if (uptimeEnd != null) {
            productInfo.setUptimeEnd(DateUtils.addDays(uptimeEnd, 1));
        }

        if(pdids != null && pdids !="") {
            try {
                List<Integer> PdidList = Arrays.asList(pdids.split(",")).stream().map(s -> Integer.parseInt(s.trim())).collect(Collectors.toList());
                productInfo.setPdids(PdidList);
            }catch (NumberFormatException e){
                multiRet.setResult(BasicRet.ERR);
                multiRet.setMessage("输入商品id格式错误，多个商品id请用一个英文逗号隔开");
                return multiRet;
            }
        }

        if (downtimeStart != null) {
            productInfo.setDowntimeStart(downtimeStart);
        }

        if (downtimeEnd != null) {
            productInfo.setDowntimeEnd(DateUtils.addDays(downtimeEnd, 1));
        }

        if (updatetimeStart != null) {
            productInfo.setUpdatetimeStart(updatetimeStart);
        }

        if (updatetimeEnd != null) {
            productInfo.setUpdatetimeEnd(DateUtils.addDays(updatetimeEnd, 1));
        }

        if (createStart != null) {
            productInfo.setCreateStart(createStart);
        }

        if (createEnd != null) {
            productInfo.setCreateEnd(DateUtils.addDays(createEnd, 1));
        }

        productInfo.setProductStore(productStore);
        multiRet.setResult(BasicRet.SUCCESS);
        multiRet.setMapData( productInfoService.MultiSpecification(productInfo));

        return multiRet;

    }

    private class MultiRet extends BasicRet{
        private List mapData;

        public List getMapData() {
            return mapData;
        }

        public void setMapData(List mapData) {
            this.mapData = mapData;
        }
    }

    @RequestMapping(value = "/excelExport/fastenerProduct", method = RequestMethod.GET)
    @ApiOperation("紧固件商品excel导出")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "商品名", name = "productname", paramType = "query", dataType = "string", required = false),
            @ApiImplicitParam(value = "品牌", name = "brand", paramType = "query", dataType = "string", required = false),
            @ApiImplicitParam(value = "分类id", name = "levelid", paramType = "query", dataType = "int", required = false, defaultValue = "0"),
            @ApiImplicitParam(value = "规格", name = "stand", paramType = "query", dataType = "string", required = false),
            @ApiImplicitParam(value = "是否有库存,0-全部,1-有，2-没有", name = "haveStorenum", paramType = "query", dataType = "int", required = false, defaultValue = "0"),
            @ApiImplicitParam(value = "商品状态0=放入仓库1=待审核2=审核通过3=未通过4=已上架5=下架6=删除7=违规下架", name = "pdstate", paramType = "query", dataType = "int", required = false, defaultValue = "-1"),
            @ApiImplicitParam(value = "是否有库存,0-全部,1-有，2-没有", name = "haveStorenum", paramType = "query", dataType = "int", required = false, defaultValue = "0"),
            @ApiImplicitParam(value = "商品id，多个商品id用英文逗号隔开", name = "pdids", paramType = "query", dataType = "String", required = false),
            @ApiImplicitParam(value = "材质id", name = "materialid", paramType = "query", dataType = "int", required = false, defaultValue = "-1"),
            @ApiImplicitParam(value = "牌号id", name = "cardnumid", paramType = "query", dataType = "int", required = false, defaultValue = "-1"),
            @ApiImplicitParam(value = "上架时间", name = "uptimeStart", paramType = "query", dataType = "date", required = false),
            @ApiImplicitParam(value = "上架时间", name = "uptimeEnd", paramType = "query", dataType = "date", required = false),
            @ApiImplicitParam(value = "下架时间", name = "downtimeStart", paramType = "query", dataType = "date", required = false),
            @ApiImplicitParam(value = "下架时间", name = "downtimeEnd", paramType = "query", dataType = "date", required = false),
            @ApiImplicitParam(value = "最后修改价格时间", name = "updatetimeStart", paramType = "query", dataType = "date", required = false),
            @ApiImplicitParam(value = "最后修改价格时间", name = "updatetimeEnd", paramType = "query", dataType = "date", required = false),
            @ApiImplicitParam(value = "发布时间区间", name = "createStart", paramType = "query", dataType = "date", required = false),
            @ApiImplicitParam(value = "发布时间区间", name = "createEnd", paramType = "query", dataType = "date", required = false),
    })
    public ResponseEntity<InputStreamResource> excelExportFastenerProduct(
            @RequestParam(required = false) String productname,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false, defaultValue = "0") long levelid,
            @RequestParam(required = false) String stand,
            @RequestParam(required = false, defaultValue = "0") short haveStorenum,
            @RequestParam(required = false, defaultValue = "-1") short pdstate,
            @RequestParam(required = false) String pdids,
            @RequestParam(required = false, defaultValue = "-1") long materialid,
            @RequestParam(required = true, defaultValue = "-1") long cardnumid,
            @RequestParam(required = false) Date uptimeStart,
            @RequestParam(required = false) Date uptimeEnd,
            @RequestParam(required = false) Date downtimeStart,
            @RequestParam(required = false) Date downtimeEnd,
            @RequestParam(required = false) Date updatetimeStart,
            @RequestParam(required = false) Date updatetimeEnd,
            @RequestParam(required = false) Date createStart,
            @RequestParam(required = false) Date createEnd,
            Model model) {

        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

        ProductInfoQuery productInfo = new ProductInfoQuery();
        ProductStore productStore = new ProductStore();

        productInfo.setMemberid(member.getId());

        if (StringUtils.hasText(productname)) {
            productInfo.setProductname(productname);
        }

        if (StringUtils.hasText(brand)) {
            productInfo.setBrand(brand);
        }

        if (levelid > 0) {
            Categories productCategory = categoriesService.getCategoryLevel(levelid);
            if (productCategory != null) {
                if (productCategory.getLevel() == 1) {
                    productInfo.setLevel1id(levelid);
                } else if (productCategory.getLevel() == 2) {
                    productInfo.setLevel2id(levelid);
                } else if (productCategory.getLevel() == 3) {
                    productInfo.setLevel3id(levelid);
                }
            }
        }

        if (StringUtils.hasText(stand)) {
            productInfo.setStand(stand);
        }

        if (haveStorenum == 1 || haveStorenum == 2) {
            productStore.setHaveStorenum(haveStorenum);
        }

        productInfo.setCardnumid(cardnumid);
        productInfo.setMaterialid(materialid);
        productInfo.setPdstate(pdstate);

        if (uptimeStart != null) {
            productInfo.setUptimeStart(uptimeStart);
        }

        if (uptimeEnd != null) {
            productInfo.setUptimeEnd(DateUtils.addDays(uptimeEnd, 1));
        }

        if(pdids != null && pdids !="") {
            /*String[] str = pdids.split(",");
            int[] intTemp = new int[str.length];
            for (int i = 0; i <str.length; i++) {
                intTemp[i] = Integer.parseInt(str[i]);
            }*/
            try {
                List<Integer> PdidList = Arrays.asList(pdids.split(",")).stream().map(s -> Integer.parseInt(s.trim())).collect(Collectors.toList());
                productInfo.setPdids(PdidList);
            }catch (NumberFormatException e){
                /*pageRet.setResult(BasicRet.ERR);
                pageRet.setMessage("输入商品id格式错误，多个商品id请用一个英文逗号隔开");
                return pageRet;*/
            }
        }

        if (downtimeStart != null) {
            productInfo.setDowntimeStart(downtimeStart);
        }

        if (downtimeEnd != null) {
            productInfo.setDowntimeEnd(DateUtils.addDays(downtimeEnd, 1));
        }

        if (updatetimeStart != null) {
            productInfo.setUpdatetimeStart(updatetimeStart);
        }

        if (updatetimeEnd != null) {
            productInfo.setUpdatetimeEnd(DateUtils.addDays(updatetimeEnd, 1));
        }

        if (createStart != null) {
            productInfo.setCreateStart(createStart);
        }

        if (createEnd != null) {
            productInfo.setCreateEnd(DateUtils.addDays(createEnd, 1));
        }

        productInfo.setProductStore(productStore);

        List<Map<String,Object>> prodList =  productInfoService.listFastenerProductForExcelBySeller(productInfo);

        //用于存放到excel的数据
        List<Map<String,Object>> data = new ArrayList<>();
        for(Map<String,Object> map : prodList){
            Map<String,Object> m1 = new HashMap<>();

            String category = map.get("level1")+"/"+map.get("level2")+"/"+map.get("level3");
            m1.put("分类",category);
            m1.put("商品编号",StringUtils.nvl(map.get("pdno")));
            m1.put("商品名称",StringUtils.nvl(map.get("productname")));
            m1.put("商品别名",StringUtils.nvl(map.get("productalias")));
            m1.put("商品副标题",StringUtils.nvl(map.get("subtitle")));
            m1.put("品牌",StringUtils.nvl(map.get("brand")));
            m1.put("材质",StringUtils.nvl(map.get("material")));
            m1.put("牌号",StringUtils.nvl(map.get("cardnum")));
            m1.put("印记",StringUtils.nvl(map.get("mark")));
            m1.put("包装方式",StringUtils.nvl(map.get("packagetype")));
            m1.put("计量单位",StringUtils.nvl(map.get("unit")));
            m1.put("表面处理",StringUtils.nvl(map.get("surfacetreatment")));
            m1.put("规格",StringUtils.nvl(map.get("stand")));
            m1.put("所在仓库",StringUtils.nvl(map.get("storename")));
            m1.put("库存数量",map.get("pdstorenum"));
            m1.put("状态",JinshangUtils.prodState((Integer) map.get("pdstate")));


            data.add(m1);
        }

        String[] rowTitles = new String[]{"分类","商品编号","商品名称","商品别名","商品副标题","品牌","材质",
                "牌号","印记","计量单位","包装方式","表面处理","规格","所在仓库","库存数量","状态"};
        XSSFWorkbook workbook = null;
        try {
            workbook = ExcelGen.common("商品库",rowTitles,data,null);
            if(workbook!=null){
                ByteArrayOutputStream baos=new   ByteArrayOutputStream();
                workbook.write(baos);
                System.out.println(baos.toByteArray().length);
                HttpHeaders headers = new HttpHeaders();
                headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
                headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", new String("商品列表.xlsx".getBytes(),"iso-8859-1")));
                headers.add("Pragma", "no-cache");
                headers.add("Expires", "0");
                String contentType = "application/vnd.ms-excel";
                return ResponseEntity.ok()
                        .headers(headers).contentType(MediaType.parseMediaType(contentType))
                        .body(new InputStreamResource(new ByteArrayInputStream(baos.toByteArray())));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(workbook != null){
                try {
                    workbook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return  null;

    }



    @RequestMapping(value = "/change/fastenerProdStoreNum", method = RequestMethod.POST)
    @ApiOperation("紧固件商品更改库存")
    public BasicRet updateFastenerProdStoreNum(long id, BigDecimal storenum, Model model, HttpServletRequest request) {
        BasicRet basicRet = new BasicRet();

        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

        ProductInfo info = productInfoService.getProductInfoWithStore(id);
        if (info == null || info.getProductStore() == null) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("该商品存在问题，请联系网站管理员");
            return basicRet;
        }

        if (info.getPdstate() == Quantity.STATE_4) { //已上架商品不可以更改库存
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("已上架商品不可以更改库存");
            return basicRet;
        }
        productStoreService.updatePdstorenumByPid(id, storenum);

        //保存日志
        memberLogOperator.saveMemberLog(member, null, "修改" + info.getId() + info.getProductname() + "库存", request, memberOperateLogService);

        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("修改成功");
        return basicRet;
    }


    @PostMapping("/getProductCount")
    @ApiOperation("获取各个状态下的产品数量")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "商品状态0=放入仓库1=待审核2=审核通过3=未通过4=已上架5=下架6=删除7=违规下架", name = "state", paramType = "query", dataType = "int", required = true, defaultValue = "1"),
    })
    public ProductCountRet getProductCount(Short state, Model model) {
        ProductCountRet productCountRet = new ProductCountRet();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        ProductInfoExample example = new ProductInfoExample();
        ProductInfoExample.Criteria criteria = example.createCriteria();
        criteria.andMemberidEqualTo(member.getId()).andPdstateEqualTo(state);

        int count = productInfoService.countByExample(example);

        productCountRet.num = count;
        productCountRet.setMessage("查询成功");
        productCountRet.setResult(BasicRet.SUCCESS);
        return productCountRet;
    }


    @PostMapping("/getProductCounts")
    @ApiOperation("获取各个状态下的产品数量2")
    public ProductCountRet2 getProductCounts(Model model) {
        ProductCountRet2 productCountRet = new ProductCountRet2();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        ProductInfoExample example = new ProductInfoExample();
        ProductInfoExample.Criteria criteria = example.createCriteria();
        criteria.andMemberidEqualTo(member.getId()).andPdstateEqualTo(Quantity.STATE_2);
        int storeNum = productInfoService.countByExample(example);

        example.clear();
        criteria = example.createCriteria();
        criteria.andMemberidEqualTo(member.getId()).andPdstateEqualTo(Quantity.STATE_4);
        int salesNum = productInfoService.countByExample(example);

        example.clear();
        criteria = example.createCriteria();
        criteria.andMemberidEqualTo(member.getId()).andPdstateEqualTo(Quantity.STATE_7);
        int illegalNum = productInfoService.countByExample(example);


        productCountRet.data.salesNum = salesNum;
        productCountRet.data.illegalNum = illegalNum;
        productCountRet.data.storeNum = storeNum;
        productCountRet.setResult(BasicRet.SUCCESS);
        return productCountRet;
    }


    private class ProductCountRet2 extends BasicRet {
        private class ProductCountData2{
            @ApiModelProperty(notes = "仓库中待上架商品")
            private int storeNum;

            @ApiModelProperty(notes = "出售中的商品数量")
            private int salesNum;

            @ApiModelProperty(notes = "违规下架的商品数量")
            private int illegalNum;

            public int getStoreNum() {
                return storeNum;
            }

            public void setStoreNum(int storeNum) {
                this.storeNum = storeNum;
            }

            public int getSalesNum() {
                return salesNum;
            }

            public void setSalesNum(int salesNum) {
                this.salesNum = salesNum;
            }

            public int getIllegalNum() {
                return illegalNum;
            }

            public void setIllegalNum(int illegalNum) {
                this.illegalNum = illegalNum;
            }
        }

        private  ProductCountData2 data = new ProductCountData2();

        public ProductCountData2 getData() {
            return data;
        }

        public void setData(ProductCountData2 data) {
            this.data = data;
        }
    }


    private class ProductCountRet extends BasicRet {
        private int num;

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }
    }


    @RequestMapping(value = "/getAllMaterial", method = RequestMethod.POST)
    @ApiOperation("获取所有材质表")
    public MaterialListRet getAllMaterial() {
        MaterialListRet materialListRet = new MaterialListRet();
        List<Material> list = materialService.getAll();
        materialListRet.list = list;
        materialListRet.setResult(BasicRet.SUCCESS);
        materialListRet.setMessage("查询成功");
        return materialListRet;
    }

    private class MaterialListRet extends BasicRet {
        private List<Material> list;

        public List<Material> getList() {
            return list;
        }

        public void setList(List<Material> list) {
            this.list = list;
        }
    }


    @RequestMapping(value = "/cardnum/getByMaterialid", method = RequestMethod.POST)
    @ApiOperation("根据材质id获取牌号")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "牌号id", name = "materialid", required = true, paramType = "query", dataType = "int")
    })
    public CardNumListRet getCardnumByMaterialid(@RequestParam(required = true) long materialid) {
        CardNumListRet cardNumListRet = new CardNumListRet();
        cardNumListRet.list = cardNumService.getListByMaterialid(materialid);
        cardNumListRet.setResult(BasicRet.SUCCESS);
        return cardNumListRet;
    }

    private class CardNumListRet extends BasicRet {
        private List<CardNum> list;

        public List<CardNum> getList() {
            return list;
        }

        public void setList(List<CardNum> list) {
            this.list = list;
        }
    }


    @RequestMapping(value = "/productname/getByCategoryId", method = RequestMethod.POST)
    @ApiOperation("根据分类id获取品名")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "分类id", name = "categoryid", required = true, paramType = "query", dataType = "int")
    })
    public ProductNameListRet getProductnameByCategoryId(@RequestParam(required = true) long categoryid) {
        ProductNameListRet productNameListRet = new ProductNameListRet();
        productNameListRet.list = productNameService.getByCategoryId(categoryid);
        productNameListRet.setResult(BasicRet.SUCCESS);
        return productNameListRet;
    }

    private class ProductNameListRet extends BasicRet {
        private List<ProductName> list;

        public List<ProductName> getList() {
            return list;
        }

        public void setList(List<ProductName> list) {
            this.list = list;
        }
    }


    @RequestMapping(value = "/attributetbl/getByProductnameid", method = RequestMethod.POST)
    @ApiOperation("根据品名id获取属性")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "品名id", name = "productnameid", required = true, paramType = "query", dataType = "int")
    })
    public AttributetblListRet getAttrByProductnameid(@RequestParam(required = true) long productnameid) {
        AttributetblListRet attributetblListRet = new AttributetblListRet();
        attributetblListRet.list = attributetblService.getAttributeListByProductnameid(productnameid);
        attributetblListRet.setResult(BasicRet.SUCCESS);
        return attributetblListRet;
    }


    private class AttributetblListRet extends BasicRet {
        private List<Attributetbl> list;

        public List<Attributetbl> getList() {
            return list;
        }

        public void setList(List<Attributetbl> list) {
            this.list = list;
        }
    }


    @RequestMapping(value = "/productname/detailWithAttr", method = RequestMethod.POST)
    @ApiOperation("品名详细信息(包含该品名下的属性和属性值)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "品名id", required = true, dataType = "int", paramType = "query"),
    })
    public ProductNameWithAttvalRet detailProductNameWithAttr(@RequestParam(required = true) long id) {
        ProductNameWithAttvalRet productNameWithAttvalRet = new ProductNameWithAttvalRet();

        ProductName productName = productNameService.getById(id);

        if (productName == null) {
            productNameWithAttvalRet.setMessage("未查询到该品名信息");
            productNameWithAttvalRet.setResult(BasicRet.SUCCESS);
            return productNameWithAttvalRet;
        }


        List<Attributetbl> attributetblList = attributetblService.getAttributeWithValue(productName.getId());

        productNameWithAttvalRet.data.productName = productName;
        productNameWithAttvalRet.data.attributetblList = attributetblList;
        productNameWithAttvalRet.setMessage("查询成功");
        productNameWithAttvalRet.setResult(BasicRet.SUCCESS);
        return productNameWithAttvalRet;
    }

    private class ProductNameWithAttvalRet extends BasicRet {
        private class ProductNameData {

            @ApiModelProperty(value = "品名信息")
            private ProductName productName;
            @ApiModelProperty(value = "属性列表")
            private List<Attributetbl> attributetblList;

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


        private ProductNameData data = new ProductNameData();

        public ProductNameData getData() {
            return data;
        }

        public void setData(ProductNameData data) {
            this.data = data;
        }
    }


    @RequestMapping(value = "/attvalue/getByAttrId", method = RequestMethod.POST)
    @ApiOperation("根据属性id获取属性值")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "品名id", name = "attrid", required = true, paramType = "query", dataType = "int")
    })
    public AttValueListRet getAttrvalueByAttrId(@RequestParam(required = true) long attrid) {
        AttValueListRet attValueListRet = new AttValueListRet();
        attValueListRet.list = attvalueService.getListByAttid(attrid);
        attValueListRet.setResult(BasicRet.SUCCESS);
        return attValueListRet;
    }

    private class AttValueListRet extends BasicRet {
        private List<Attvalue> list;

        public List<Attvalue> getList() {
            return list;
        }

        public void setList(List<Attvalue> list) {
            this.list = list;
        }
    }


    @RequestMapping(value = "/getPushCategory", method = RequestMethod.POST)
    @ApiOperation("获取可发布的产品类别")
    public BasicRet getPushCategory(Model model) {
        BasicExtRet basicRet = new BasicExtRet();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

        SellerCompanyInfo sellerCompanyInfo = sellerCompanyInfoService.getSellerCompanyByMemberid(member.getId());
        if (sellerCompanyInfo == null) {
            basicRet.setResult(BasicRet.SUCCESS);
            basicRet.setMessage("出现错误，请退出后重新登录");
            return basicRet;
        }

        Long[] categoryidArr = (Long[]) sellerCompanyInfo.getBusinesscategory();
        if (categoryidArr == null || categoryidArr.length <= 0) {
            basicRet.setMessage("请选择店铺可经营的类别");
            basicRet.setResult(BasicRet.ERR);
            return basicRet;
        }

        //从redis 缓存中获取可发布分类
        List<Categories> publishList = sellerCompanyCacheService.getPushCategory(member.getId());

        basicRet.setData(publishList);

        return basicRet;
    }


    @PostMapping("/getUnitRate")
    @ApiOperation("获取计量单位转化比率")
    public UnitRateRet getUnitRate(@RequestParam(required = true) long pdid, Model model) {

        UnitRateRet unitRateRet = new UnitRateRet();

        ProdUnitRateViewDto prodUnit = productInfoService.getProdUnitRate(pdid);

        if (prodUnit == null) {
            unitRateRet.setMessage("该商品信息不存在");
            unitRateRet.setResult(BasicRet.ERR);
            return unitRateRet;
        }

        unitRateRet.unitRate = prodUnit;
        unitRateRet.setResult(BasicRet.SUCCESS);
        return unitRateRet;
    }


    @PostMapping("/setUnitRate")
    @ApiOperation("设置计量单位比率")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "仓库计量单位", name = "prodstoreunit", dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "基本单位与库存单位转化比率", name = "unitrate", dataType = "double", paramType = "query"),
    })
    public BasicRet setUnitRate(@RequestParam(required = true) long pdid,
                                @RequestParam(required = true) String prodstoreunit,
                                @RequestParam(required = true) BigDecimal unitrate, Model model) {
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

        ProductInfo productInfo = productInfoService.getById(pdid);
        if (productInfo == null) {
            return new BasicRet(BasicRet.ERR, "商品信息不存在");
        }

        if (!productInfo.getMemberid().equals(member.getId())) {
            return new BasicRet(BasicRet.ERR, "商品不属于你");
        }

        ProductInfo updateInfo = new ProductInfo();
        updateInfo.setId(pdid);
        updateInfo.setProdstoreunit(prodstoreunit);
        updateInfo.setUnitrate(unitrate);
        productInfoService.updateByPrimaryKeySelective(updateInfo);

        return new BasicRet(BasicRet.SUCCESS, "修改成功");
    }

    private class UnitRateRet extends BasicRet {
        private ProdUnitRateViewDto unitRate;

        public ProdUnitRateViewDto getUnitRate() {
            return unitRate;
        }

        public void setUnitRate(ProdUnitRateViewDto unitRate) {
            this.unitRate = unitRate;
        }
    }





    private class AddProdRet extends BasicRet {
        ErrorMes errorMes = new ErrorMes();

        public ErrorMes getErrorMes() {
            return errorMes;
        }
    }


    /**
     * 判断用户是否有发布该类产品的权利
     *
     * @return
     */
    private boolean checkPublishProductPrivilege(long categoryid, Long[] publishCategoryid) {

        List<Categories> allCategories = categoriesService.getAll();

        long parentId = ProductCategoryUtils.get_top_parent_id(allCategories, categoryid);

        for (long id : publishCategoryid) {
            if (id == categoryid) {
                return true;
            }
        }
        return false;
    }


}
