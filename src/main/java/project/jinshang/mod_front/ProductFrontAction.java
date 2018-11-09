package project.jinshang.mod_front;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hankcs.hanlp.collection.dartsclone.details.Keyset;
import io.swagger.annotations.*;
import mizuki.project.core.restserver.config.BasicRet;
import mizuki.project.core.restserver.util.JsonUtil;
import mizuki.project.core.restserver.util.StringUtil;
import org.apache.http.HttpResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.jinshang.common.bean.Page;
import project.jinshang.common.bean.PageRet;
import project.jinshang.common.bean.ProductGroup;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.common.constant.Quantity;
import project.jinshang.common.utils.*;
import project.jinshang.mod_admin.mod_count.bean.SearchKeyRecord;
import project.jinshang.mod_admin.mod_count.service.SearchKeyRecordService;
import project.jinshang.mod_common.bean.BasicExtRet;
import project.jinshang.mod_front.service.ProductFrontService;
import project.jinshang.mod_member.bean.Member;
import project.jinshang.mod_member.bean.MemberRateSetting;
import project.jinshang.mod_member.service.FavoriteService;
import project.jinshang.mod_member.service.MemberRateSettingService;
import project.jinshang.mod_member.service.MemberService;
import project.jinshang.mod_product.bean.*;
import project.jinshang.mod_product.bean.dto.OtherProdDetailViewDto;
import project.jinshang.mod_product.bean.dto.OtherProdStore;
import project.jinshang.mod_product.bean.dto.OtherProdStoreView;
import project.jinshang.mod_product.service.*;
import project.jinshang.scheduled.util.ProductViewStatistics;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.*;

/**
 * create : wyh
 * date : 2017/11/10
 */
@RestController
@Api(tags = "前台产品相关接口", description = "产品相关接口")
@RequestMapping("/rest/front/product")
@Transactional(rollbackFor = Exception.class)
@SessionAttributes({AppConstant.MEMBER_SESSION_NAME})
public class ProductFrontAction {

    @Autowired
    private ProductAttrService productAttrService;

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private ShippingTemplatesService shippingTemplatesService;

    @Autowired
    private ProductSearchService productSearchService;



    @Autowired
    private ProductFrontService productFrontService;


    @Autowired
    private MemberRateSettingService memberRateSettingService;

    @Autowired
    private OtherProdService otherProdService;

    @Autowired
    private FavoriteService favoriteService;

    @Autowired
    private  AttributetblService attributetblService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private SearchKeyRecordService searchKeyRecordService;


    @RequestMapping(value = "/getProductAttrByPid", method = RequestMethod.POST)
    @ApiOperation("根据产品id获取该产品的所有属性值")
    public List<ProductAttr> getProductAttrByPid(long productid) {
        List<ProductAttr> list = productAttrService.getByProductid(productid);
        return list;
    }


    @PostMapping(value = "/viewCookie")
    @ApiOperation("浏览记录")
    public ProductListRet2 viewCookie(HttpServletRequest request, HttpServletResponse response) {
        ProductListRet2 productListRet2 = new ProductListRet2();

        String productid = CookieUtils.getValue(request.getCookies(), AppConstant.PRODUCT_VIEW_COOKIE_ID);
        List<Map<String, Object>> list = new ArrayList<>();

        if (productid != null) {
            String[] pids = productid.split("#");
            if (pids != null && pids.length > 0) {
                for (String pid : pids) {
                    if (!StringUtils.isNumeric(pid)) continue;
                    Map<String, Object> productInfo = productInfoService.getProductInfoById(StringUtils.intValue(pid));
                    if (productInfo != null) {
                        String[] pdPics = (String[]) productInfo.get("pdpicture");
                        if (pdPics == null || pdPics.length == 0) {
                            productInfo.put("pdpicture", null);
                        } else {
                            productInfo.put("pdpicture", pdPics[0]);
                        }
                        BigDecimal price = (BigDecimal) productInfo.get("pdstorenum");
                        productInfo.put("pdstorenum", price.abs());
                        productInfo.put("level3",productInfo.get("level3"));
                        list.add(productInfo);
                    }
                }
            }
        }
        productListRet2.data = list;
        productListRet2.setResult(BasicRet.SUCCESS);
        return productListRet2;
    }


    @RequestMapping(value = "/getProductInfo", method = RequestMethod.POST)
    @ApiOperation("获取紧固件商品详情接口")
    public ProductRet getProductInfo(@RequestParam(required = true) long id, Model model, HttpServletRequest request, HttpServletResponse response) {
        ProductRet productRet = new ProductRet();

        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

        if(member != null){
            member =  memberService.getMemberById(member.getId());
        }


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

        // 添加浏览记录
        JinshangUtils.addViewCookie(request, response, productInfo.getId());

        //转换包装方式
        productInfo.setPackingList(JinshangUtils.toCovertPacking(productInfo.getPackagetype()));


        Member sellerMember =  memberService.getMemberById(productInfo.getMemberid());


        MemberRateSetting memberRateSetting = null;
        if (member != null && member.getGradleid() !=null && sellerMember != null && sellerMember.getMembersettingstate() == Quantity.STATE_1) {
            memberRateSetting = memberRateSettingService.getRecursiveSetting(productInfo.getMemberid(), productInfo.getLevel3id(), member.getGradleid());
        }

        if (memberRateSetting == null) {
            memberRateSetting = new MemberRateSetting();
            memberRateSetting.setRate(new BigDecimal(1));
        }


        //封装价格
        List list1 = new ArrayList();
        Map<String, Object> prodpriceMap = new HashMap<>();
        Map<String, Object> threepriceMap = new HashMap<>();
        Map<String, Object> ninetypriceMap = new HashMap<>();
        Map<String, Object> thirtypriceMap = new HashMap<>();
        Map<String, Object> sixtypriceMap = new HashMap<>();

        //前台marketprice 原价
        BigDecimal originalPrice = store.getProdprice();
        productInfo.getProductStore().setMarketprice(originalPrice);


        if (store.getProdprice() != null && store.getProdprice().compareTo(Quantity.BIG_DECIMAL_0)>0) {
            prodpriceMap.put("type", 0);
            prodpriceMap.put("name", Quantity.LIJIFAHUO);
            if(member != null) {
                prodpriceMap.put("price", store.getProdprice().multiply(memberRateSetting.getRate()).setScale(5, BigDecimal.ROUND_HALF_UP));
            }else{
                prodpriceMap.put("price",null);
            }
            list1.add(prodpriceMap);
            store.setProdprice(store.getProdprice().multiply(memberRateSetting.getRate()).setScale(5,BigDecimal.ROUND_HALF_UP));
        }

        /*
        if (store.getThreeprice() != null) {
            threepriceMap.put("type", 3);
            threepriceMap.put("name", Quantity.SANTIANFAHUO);
            if(member != null) {
                threepriceMap.put("price", store.getThreeprice().multiply(memberRateSetting.getRate()).setScale(5, BigDecimal.ROUND_HALF_UP));
            }else{
                threepriceMap.put("price",null);
            }
            list1.add(threepriceMap);
            store.setThreeprice(store.getThreeprice().multiply(memberRateSetting.getRate()).setScale(5,BigDecimal.ROUND_HALF_UP));
        }*/

        if (store.getThirtyprice() != null && store.getThirtyprice().compareTo(Quantity.BIG_DECIMAL_0)>0) {
            thirtypriceMap.put("type", 30);
            thirtypriceMap.put("name", Quantity.SANSHITIANFAHUO);
            if(member != null) {
                thirtypriceMap.put("price", store.getThirtyprice().multiply(memberRateSetting.getRate()).setScale(5, BigDecimal.ROUND_HALF_UP));
            }else{
                thirtypriceMap.put("price",null);
            }
            list1.add(thirtypriceMap);
            store.setThirtyprice(store.getThirtyprice().multiply(memberRateSetting.getRate()).setScale(5,BigDecimal.ROUND_HALF_UP));
        }

        if (store.getSixtyprice() != null && store.getSixtyprice().compareTo(Quantity.BIG_DECIMAL_0)>0) {
            sixtypriceMap.put("type", 60);
            sixtypriceMap.put("name", Quantity.LIUSHITIANFAHUO);
            if(member != null) {
                sixtypriceMap.put("price", store.getSixtyprice().multiply(memberRateSetting.getRate()).setScale(5, BigDecimal.ROUND_HALF_UP));
            }else{
                sixtypriceMap.put("price",null);
            }
            list1.add(sixtypriceMap);
            store.setSixtyprice(store.getSixtyprice().multiply(memberRateSetting.getRate()).setScale(5,BigDecimal.ROUND_HALF_UP));
        }


        if (store.getNinetyprice() != null && store.getNinetyprice().compareTo(Quantity.BIG_DECIMAL_0)>0) {
            ninetypriceMap.put("type", 90);
            ninetypriceMap.put("name", Quantity.JIUSHITIANFAHUO);
            if(member != null) {
                ninetypriceMap.put("price", store.getNinetyprice().multiply(memberRateSetting.getRate()).setScale(5, BigDecimal.ROUND_HALF_UP));
            }else{
                ninetypriceMap.put("price",null);
            }
            list1.add(ninetypriceMap);
            store.setNinetyprice(store.getNinetyprice().multiply(memberRateSetting.getRate()).setScale(5,BigDecimal.ROUND_HALF_UP));
        }

        if(member != null) {
            productInfo.setPrices(list1);
            productInfo.setShowprice(true);
        }else{
            productInfo.setPrices(list1);
            productInfo.setShowprice(false);
        }

        ShippingTemplates shippingTemplates = null;

//        if (productInfo.getProductStore().getFreightmode() > 0) {
//            shippingTemplates = shippingTemplatesService.getFullTemplatesById(productInfo.getProductStore().getFreightmode());
//        }


        productRet.data.productInfo = productInfo;
        productRet.data.shippingTemplates = shippingTemplates;


        //添加访问次数
        ProductViewStatistics.addCount(productInfo.getId());


        productRet.setMessage("查询成功");
        productRet.setResult(BasicRet.SUCCESS);
        return productRet;
    }


    @PostMapping("/othereProduct/detail")
    @ApiOperation("其他类商品详情")
    public  OtherProductDetailRet detailOtherProduct(@RequestParam(required = true) long id,Model model,
                                                     HttpServletRequest request,
                                                     HttpServletResponse response){
        OtherProductDetailRet detailRet =  new OtherProductDetailRet();

        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

        ProductInfo productInfo =  productInfoService.getById(id);
        if(productInfo == null){
            detailRet.setMessage("该商品不存在");
            detailRet.setResult(BasicRet.ERR);
            return  detailRet;
        }

        if(!productInfo.getProducttype().equals(AppConstant.OTHER_PRO_TYPE)){
            detailRet.setMessage("该商品不属于非紧固件类商品");
            detailRet.setResult(BasicRet.ERR);
            return  detailRet;
        }


        // 添加浏览记录
        JinshangUtils.addViewCookie(request, response, productInfo.getId());


        Member sellerMember = memberService.getMemberById(productInfo.getMemberid());


        //减价率
        MemberRateSetting memberRateSetting = null;
        if (member != null && member.getGradleid() !=null && sellerMember != null && sellerMember.getMembersettingstate() == Quantity.STATE_1) {
            memberRateSetting = memberRateSettingService.getRecursiveSetting(productInfo.getMemberid(), productInfo.getLevel3id(), member.getGradleid());
        }

        if (memberRateSetting == null) {
            memberRateSetting = new MemberRateSetting();
            memberRateSetting.setRate(new BigDecimal(1));
        }


        OtherProdDetailViewDto viewDto = new OtherProdDetailViewDto();
        BeanUtils.copyProperties(productInfo,viewDto);

        List<OtherProdStore> prodStoreList = otherProdService.getOtherProdStore(productInfo.getId());

        List<OtherProdStoreView> prodStoreViewList = new ArrayList<>();

        String intervalprice = "";


        //
        List<ProdAttrName> publishAttrs = new ArrayList<>();

        for(OtherProdStore pdStore : prodStoreList){
            viewDto.setStoreid(pdStore.getStoreid());
            viewDto.setStorename(pdStore.getStorename());
           // viewDto.setFreightmode(pdStore.getFreightmode());
            viewDto.setCostprice(pdStore.getCostprice());
            viewDto.setStepwiseprice(pdStore.isStepwiseprice());
            viewDto.setStartnum(pdStore.getStartnum());
            viewDto.setMinplus(pdStore.getMinplus());


            List<ProductAttr> attrList = productAttrService.getListByPidAndPdno(productInfo.getId(),pdStore.getPdno());
            pdStore.setProductAttrList(attrList);

            StringBuilder attrStr = new StringBuilder();
            for(ProductAttr attr : attrList){
                attrStr.append(attr.getAttribute()).append(":").append(attr.getValue()).append(";");
                ProdAttrName prodAttrName =  conKey(publishAttrs,attr.getAttribute());
                if(prodAttrName == null){
                    prodAttrName = new ProdAttrName();
                    prodAttrName.setName(attr.getAttribute());
                    Set<String> set = new HashSet<>();
                    set.add(attr.getValue());
                    prodAttrName.setValue(set);
                    publishAttrs.add(prodAttrName);
                }else{
                    Set<String> set = prodAttrName.getValue();
                    if(!set.contains(attr.getValue())){
                        set.add(attr.getValue());
                    }
                }
            }

            if(pdStore.getNinetyprice() != null) {
                pdStore.setNinetyprice(pdStore.getNinetyprice().multiply(memberRateSetting.getRate()).setScale(5, BigDecimal.ROUND_HALF_UP));
            }


            if(pdStore.getThirtyprice() != null) {
                pdStore.setThirtyprice(pdStore.getThirtyprice().multiply(memberRateSetting.getRate()).setScale(5, BigDecimal.ROUND_HALF_UP));
            }

            if(pdStore.getProdprice() != null) {
                pdStore.setProdprice(pdStore.getProdprice().multiply(memberRateSetting.getRate()).setScale(5, BigDecimal.ROUND_HALF_UP));
            }

            if(pdStore.getSixtyprice() != null) {
                pdStore.setSixtyprice(pdStore.getSixtyprice().multiply(memberRateSetting.getRate()).setScale(5, BigDecimal.ROUND_HALF_UP));
            }

            intervalprice =  pdStore.getIntervalprice();
            OtherProdStoreView otherProdStoreView = new OtherProdStoreView();
            BeanUtils.copyProperties(pdStore,otherProdStoreView);
            otherProdStoreView.setAttrStr(attrStr.toString());

            prodStoreViewList.add(otherProdStoreView);
        }

        //运费模版
//        ShippingTemplates shippingTemplates = null;
//        if (viewDto.getFreightmode() > 0) {
//            shippingTemplates = shippingTemplatesService.getFullTemplatesById(viewDto.getFreightmode());
//        }

        List<Attributetbl> attributetblList =  attributetblService.getAttributeWithValue(productInfo.getProductnameid());


        detailRet.data.viewDto =  viewDto;
        detailRet.data.prodStoreList =  prodStoreViewList;
        detailRet.data.attributetblList = attributetblList;
        detailRet.data.intervalprice = intervalprice;
       // detailRet.data.shippingTemplates = shippingTemplates;
        detailRet.data.publishAttrs = publishAttrs;


        //添加访问次数
        ProductViewStatistics.addCount(productInfo.getId());

        detailRet.setResult(BasicRet.SUCCESS);
        return  detailRet;
    }

    private  ProdAttrName conKey(List<ProdAttrName> list,String key){
        for(ProdAttrName attrName : list){
            if(attrName.getName().equals(key)){
                return  attrName;
            }
        }
        return  null;
    }



    private  class  ProdAttrName {
        private  String name;
        private  Set<String> value;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Set<String> getValue() {
            return value;
        }

        public void setValue(Set<String> value) {
            this.value = value;
        }
    }



    private  class OtherProductDetailRet extends  BasicRet{
        private  class  OtherProductDetailData{
            private OtherProdDetailViewDto viewDto;

            private List<OtherProdStoreView> prodStoreList;

            private List<Attributetbl> attributetblList;

            private  String intervalprice;

            private  ShippingTemplates shippingTemplates;


            private  List<ProdAttrName> publishAttrs;


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


            public List<Attributetbl> getAttributetblList() {
                return attributetblList;
            }

            public void setAttributetblList(List<Attributetbl> attributetblList) {
                this.attributetblList = attributetblList;
            }

            public List<ProdAttrName> getPublishAttrs() {
                return publishAttrs;
            }

            public void setPublishAttrs(List<ProdAttrName> publishAttrs) {
                this.publishAttrs = publishAttrs;
            }
        }

        private   OtherProductDetailData data = new OtherProductDetailData();

        public OtherProductDetailData getData() {
            return data;
        }

        public void setData(OtherProductDetailData data) {
            this.data = data;
        }
    }


    @ApiOperation("获取商品价格")
   @RequestMapping(value = "/prodRatePrice",method = RequestMethod.POST)
   public ProdRatePriceRet prodPrice(Model model,@RequestParam(required = true) @NotNull Long[] pids){
       Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

       ProdRatePriceRet prodRatePriceRet = new ProdRatePriceRet();

       if(member == null){
           prodRatePriceRet.data.list = null;
           prodRatePriceRet.setMessage("用户未登陆");
           prodRatePriceRet.setResult(BasicRet.ERR);
           return  prodRatePriceRet;
       }




       List<Map<String,Object>> list = productInfoService.getProdRatePriceByPids(pids);


       for (Map<String, Object> map : list) {
           MemberRateSetting memberRateSetting = null;
           if (member != null && map.get("membersettingstate") != null && map.get("membersettingstate").equals(1) && member.getGradleid() != null) {
               memberRateSetting = memberRateSettingService.getRecursiveSetting((Long) map.get("memberid"), (Long) map.get("level3id"), member.getGradleid());
           }

           if (memberRateSetting == null) {
               memberRateSetting = new MemberRateSetting();
               memberRateSetting.setRate(new BigDecimal(1));
           }


           List list1 = new ArrayList();
           Map<String, Object> prodpriceMap = new HashMap<>();
           Map<String, Object> ninetypriceMap = new HashMap<>();
           Map<String, Object> thirtypriceMap = new HashMap<>();
           Map<String, Object> sixtypriceMap = new HashMap<>();

           if (map.get("prodprice") != null) {
               prodpriceMap.put("type", 0);
               prodpriceMap.put("name", Quantity.LIJIFAHUO);
               if (member != null) {
                   prodpriceMap.put("price", ((BigDecimal) map.get("prodprice")).multiply(memberRateSetting.getRate()).setScale(5, BigDecimal.ROUND_HALF_UP));
               } else {
                   prodpriceMap.put("price", ((BigDecimal) map.get("prodprice")).multiply(memberRateSetting.getRate()).setScale(5, BigDecimal.ROUND_HALF_UP));
               }
               list1.add(prodpriceMap);
           }


           if (map.get("thirtyprice") != null) {
               thirtypriceMap.put("type", 30);
               thirtypriceMap.put("name", Quantity.SANSHITIANFAHUO);
               if (member != null) {
                   thirtypriceMap.put("price", ((BigDecimal) map.get("thirtyprice")).multiply(memberRateSetting.getRate()).setScale(5, BigDecimal.ROUND_HALF_UP));
               } else {
                   thirtypriceMap.put("price", ((BigDecimal) map.get("thirtyprice")).multiply(memberRateSetting.getRate()).setScale(5, BigDecimal.ROUND_HALF_UP));
               }
               list1.add(thirtypriceMap);
           }


           if (map.get("sixtyprice") != null) {
               sixtypriceMap.put("type", 60);
               sixtypriceMap.put("name", Quantity.LIUSHITIANFAHUO);
               if (member != null) {
                   sixtypriceMap.put("price", ((BigDecimal) map.get("sixtyprice")).multiply(memberRateSetting.getRate()).setScale(5, BigDecimal.ROUND_HALF_UP));
               } else {
                   sixtypriceMap.put("price", ((BigDecimal) map.get("sixtyprice")).multiply(memberRateSetting.getRate()).setScale(5, BigDecimal.ROUND_HALF_UP));
               }

               list1.add(sixtypriceMap);
           }

           if (map.get("ninetyprice") != null) {
               ninetypriceMap.put("type", 90);
               ninetypriceMap.put("name", Quantity.JIUSHITIANFAHUO);
               if (member != null) {
                   ninetypriceMap.put("price", ((BigDecimal) map.get("ninetyprice")).multiply(memberRateSetting.getRate()).setScale(5, BigDecimal.ROUND_HALF_UP));
               } else {
                   ninetypriceMap.put("price", ((BigDecimal) map.get("ninetyprice")).multiply(memberRateSetting.getRate()).setScale(5, BigDecimal.ROUND_HALF_UP));
               }
               list1.add(ninetypriceMap);
           }

           map.remove("prodprice");
           map.remove("threeprice");
           map.remove("thirtyprice");
           map.remove("sixtyprice");
           map.remove("ninetyprice");

           map.put("prices", list1);
       }


       List<Map<String,Object>> resList = new ArrayList<>();
       for(Long pid : pids){
           for(Map<String,Object> map : list){
               if(map.get("id").equals(pid)){
                   resList.add(map);
               }
           }
       }


       prodRatePriceRet.data.list = resList;
       prodRatePriceRet.setResult(BasicRet.SUCCESS);

       return  prodRatePriceRet;
   }



   private  class  ProdRatePriceRet extends  BasicRet{
        private class ProdRatePriceData{
            private  List<Map<String,Object>> list;

            public List<Map<String, Object>> getList() {
                return list;
            }

            public void setList(List<Map<String, Object>> list) {
                this.list = list;
            }
        }

        private  ProdRatePriceData data = new ProdRatePriceData();

       public ProdRatePriceData getData() {
           return data;
       }

       public void setData(ProdRatePriceData data) {
           this.data = data;
       }
   }






    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ApiOperation("紧固件产品搜索")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "搜索词", name = "searchKey", paramType = "query", dataType = "string"),
            @ApiImplicitParam(value = "1级分类", name = "level1", paramType = "query", dataType = "string"),
            @ApiImplicitParam(value = "2级分类", name = "level2", paramType = "query", dataType = "string"),
            @ApiImplicitParam(value = "3级分类", name = "level3", paramType = "query", dataType = "string"),
            @ApiImplicitParam(value = "品名", name = "productname", paramType = "query", dataType = "string"),
            @ApiImplicitParam(value = "品牌", name = "brand", paramType = "query", dataType = "string"),
            @ApiImplicitParam(value = "牌号", name = "cardnum", paramType = "query", dataType = "string"),
            @ApiImplicitParam(value = "材质", name = "material", paramType = "query", dataType = "string"),
            @ApiImplicitParam(value = "表面处理", name = "surfacetreatment", paramType = "query", dataType = "string"),
            @ApiImplicitParam(value = "属性搜索json [{\"attr\":\"长度\",\"value\":\"10\"}]", name = "searchJson", paramType = "query", dataType = "string"),
            @ApiImplicitParam(value = "排序方式 默认按规格降序 0=按规格升序，1=按规格降序", name = "sorttype", paramType = "query", dataType = "int"),
            @ApiImplicitParam(value = "是否自营 0=全部，1=自营", name = "selfsupport", paramType = "query", dataType = "int"),
            @ApiImplicitParam(value = "有库存 0=全部，1=有库存", name = "havestore", paramType = "query", dataType = "int"),
            @ApiImplicitParam(value = "是否有远期 0=全部，1=有远期", name = "forwardtime", paramType = "query", dataType = "int"),
            @ApiImplicitParam(value = "仓库", name = "storename", paramType = "query", dataType = "string"),
    })
    public ProductListRet list(
            @RequestParam(required = true, defaultValue = "1") int pageNo,
            @RequestParam(required = true, defaultValue = "20") int pageSize,
            @RequestParam(required = false,defaultValue = "") String searchKey,
            @RequestParam(required = false) String level1,
            @RequestParam(required = false) String level2,
            @RequestParam(required = false) String level3,
            @RequestParam(required = false) String productname,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String cardnum,
            @RequestParam(required = false) String material,
            @RequestParam(required = false) String searchJson,
//            @RequestParam(required = false) String diameter,
            @RequestParam(required = false) String surfacetreatment,
            @RequestParam(required = false) Integer sorttype,
            @RequestParam(required = false,defaultValue = "0") Integer selfsupport,
            @RequestParam(required = false,defaultValue = "0") Integer havestore,
            @RequestParam(required = false,defaultValue = "0") Integer forwardtime,
            @RequestParam(required = false,defaultValue = "") String storename,
            Model model) {

        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

        //存搜索记录
        if(StringUtils.hasText(searchKey)){
            SearchKeyRecord searchKeyRecord = new SearchKeyRecord();
            searchKeyRecord.setSearchkey(searchKey);
            searchKeyRecord.setCreatetime(new Date());
            searchKeyRecord.setType(3);
            searchKeyRecordService.insertSearchKeyRecord(searchKeyRecord);
        }

        String store = storename;

        searchKey = searchKey.toLowerCase();

        searchKey = searchKey.replaceAll("\\*"," ");

        if(member != null){
            member =  memberService.getMemberById(member.getId());
        }

        ProductListRet productListRet = new ProductListRet();

        int start = (pageNo - 1) * pageSize;
//        if(start > (10000-pageSize)){
//            start = start - pageSize;
//        }

        /*
        if(!StringUtils.hasText(searchKey) && sorttype == null){
            sorttype = 0;
        }*/


        Map<String, Object> attrs = new HashMap<>();

        if (StringUtils.hasText(searchJson)) {
            Gson gson = new Gson();
            if (!CommonUtils.isGoodJson(searchJson)) {
                productListRet.setResult(BasicRet.ERR);
                productListRet.setMessage("searchJson格式不对");
                return productListRet;
            }

            List<Map<String, String>> attrsList = gson.fromJson(searchJson, new TypeToken<List<Map<String, String>>>() {
            }.getType());

            if (attrsList != null) {
                for (Map<String, String> jsonMap : attrsList) {
//                    String name = ProductSearchUtils.getName(jsonMap.get("attr"));
                    String name = jsonMap.get("attr");
                    if (name != null) {
                        attrs.put(name, jsonMap.get("value"));
                    }
                }
            }
        }

        //        int count = productSearchService.countSearchWithKeys(StringUtils.nvl(searchKey), level1, level2, level3, productname, brand, cardnum, material,surfacetreatment, attrs,selfsupport, havestore,forwardtime,store);
        List<KeyValue> keyValues = new ArrayList<>();
//        List<List<Map>> resultList = null;
//        Map<String, Set> resultGroupAttr = null;
//        Map<String, Set> resultGroupAttr2 = null;

        Map<String,Object> data = productSearchService.search(StringUtils.nvl(searchKey), level1, level2, level3, productname, brand, cardnum, material, surfacetreatment,attrs, start, pageSize,sorttype== null ? null :JinshangUtils.fastenSortType(sorttype),selfsupport, havestore,forwardtime,store,null,AppConstant.FASTENER_PRO_TYPE);

        int count = 0;
        Object total =  data.get("total");
        if(total instanceof Long){
            count = ((Long) total).intValue();
        }else{
            count = (int)total;
        }

//        if(count > 10000){ //最多10000条数据
//            count = 10000;
//        }

        List<Map<String,Object>> list = (List<Map<String,Object>>) data.get("list");
        if (list != null && list.size() > 0) {

            for (Map<String, Object> map : list) {
                if(map.get("mark") != null && map.get("mark") !=""){
                    String tempmark = map.get("mark").toString();
                    //String mark = tempmark.split("\\.")[0];
                    map.put("mark",tempmark);
                }
                MemberRateSetting memberRateSetting = null;
                if (member != null && map.get("memberid") != null && map.get("membersettingstate") != null && map.get("membersettingstate").equals(1) && member.getGradleid() != null && map.get("level3id") != null) {
                    memberRateSetting = memberRateSettingService.getRecursiveSetting(Long.parseLong(map.get("memberid").toString()), (Long) map.get("level3id"), member.getGradleid());
                }

                if (memberRateSetting == null) {
                    memberRateSetting = new MemberRateSetting();
                    memberRateSetting.setRate(new BigDecimal(1));
                }


                List list1 = new ArrayList();
                Map<String, Object> prodpriceMap = new HashMap<>();
                Map<String, Object> threepriceMap = new HashMap<>();
                Map<String, Object> ninetypriceMap = new HashMap<>();
                Map<String, Object> thirtypriceMap = new HashMap<>();
                Map<String, Object> sixtypriceMap = new HashMap<>();

                if (map.get("prodprice") != null && (BigDecimal.valueOf((double)map.get("prodprice"))).compareTo(Quantity.BIG_DECIMAL_0) >0 ) {
                    prodpriceMap.put("type", 0);
                    prodpriceMap.put("name", Quantity.LIJIFAHUO);
                    if(member != null) {
                        prodpriceMap.put("price", (BigDecimal.valueOf((double)map.get("prodprice"))).multiply(memberRateSetting.getRate()).setScale(5, BigDecimal.ROUND_HALF_UP));
                    }else{
                        prodpriceMap.put("price", (BigDecimal.valueOf((double)map.get("prodprice"))).multiply(memberRateSetting.getRate()).setScale(5, BigDecimal.ROUND_HALF_UP));
                    }
                    list1.add(prodpriceMap);
                }


                if (map.get("thirtyprice") != null && (BigDecimal.valueOf((double)map.get("thirtyprice"))).compareTo(Quantity.BIG_DECIMAL_0) >0) {
                    thirtypriceMap.put("type", 30);
                    thirtypriceMap.put("name", Quantity.SANSHITIANFAHUO);
                    if(member != null) {
                        thirtypriceMap.put("price", (BigDecimal.valueOf((double)map.get("thirtyprice"))).multiply(memberRateSetting.getRate()).setScale(5, BigDecimal.ROUND_HALF_UP));
                    }else{
                        thirtypriceMap.put("price", (BigDecimal.valueOf((double)map.get("thirtyprice"))).multiply(memberRateSetting.getRate()).setScale(5, BigDecimal.ROUND_HALF_UP));
                    }
                    list1.add(thirtypriceMap);
                }


                if (map.get("sixtyprice") != null && (BigDecimal.valueOf((double)map.get("sixtyprice"))).compareTo(Quantity.BIG_DECIMAL_0) >0) {
                    sixtypriceMap.put("type", 60);
                    sixtypriceMap.put("name", Quantity.LIUSHITIANFAHUO);
                    if(member != null) {
                        sixtypriceMap.put("price", (BigDecimal.valueOf((double)map.get("sixtyprice"))).multiply(memberRateSetting.getRate()).setScale(5, BigDecimal.ROUND_HALF_UP));
                    }else{
                        sixtypriceMap.put("price", (BigDecimal.valueOf((double)map.get("sixtyprice"))).multiply(memberRateSetting.getRate()).setScale(5, BigDecimal.ROUND_HALF_UP));
                    }

                    list1.add(sixtypriceMap);
                }

                if (map.get("ninetyprice") != null && (BigDecimal.valueOf((double)map.get("ninetyprice"))).compareTo(Quantity.BIG_DECIMAL_0) >0) {
                    ninetypriceMap.put("type", 90);
                    ninetypriceMap.put("name", Quantity.JIUSHITIANFAHUO);
                    if(member != null) {
                        ninetypriceMap.put("price", (BigDecimal.valueOf((double)map.get("ninetyprice"))).multiply(memberRateSetting.getRate()).setScale(5, BigDecimal.ROUND_HALF_UP));
                    }else{
                        ninetypriceMap.put("price", (BigDecimal.valueOf((double)map.get("ninetyprice"))).multiply(memberRateSetting.getRate()).setScale(5, BigDecimal.ROUND_HALF_UP));
                    }
                    list1.add(ninetypriceMap);
                }

                map.remove("threeprice");
                map.remove("thirtyprice");
                map.remove("sixtyprice");
                map.remove("ninetyprice");


                if(member != null) {
                    map.put("prices", list1);
                    map.put("showprice",true);
                }else{
                    map.put("prices", list1);
                    map.put("showprice",false);
                }

                map.put("brandpic", map.get("pic"));
                map.remove("pic");

                //转换包装方式
                List packageList = JinshangUtils.toCovertPacking(StringUtils.nvl(map.get("packagetype")));
                map.put("packages", packageList);

                map.put("packageStr",JinshangUtils.packageToString((String)map.get("packagetype"),BigDecimal.valueOf((double)map.get("startnum")),(String) map.get("unit")));            }
        }

        Map<String, List> resultGroupAttr = (Map<String, List>) data.get("aggs");
        Map<String, List> resultGroupAttr2 = (Map<String, List>) data.get("aggs_attr");
//            ProductGroup sort = new ProductGroup();
//            resultList = sort.group(list);
//            List<Map> groupAttr = productSearchService.fetchSearchKeys(StringUtils.nvl(searchKey), level1, level2, level3, productname, brand, cardnum, material, surfacetreatment,attrs,selfsupport, havestore,forwardtime,store);
//
//
//            List<Map> groupAttr1 = new ArrayList<>();
//            if(attrs == null || attrs.size()== 0) {
//                groupAttr1 = productSearchService.fetchSearchAttrKeys(StringUtils.nvl(searchKey), level1, level2, level3, productname, brand, cardnum, material, surfacetreatment,attrs,selfsupport, havestore,forwardtime,store);
//            }else{
//                groupAttr1 = productSearchService.fetchSearchAttrKeysHashAttr(StringUtils.nvl(searchKey), level1, level2, level3, productname, brand, cardnum, material,surfacetreatment, attrs,selfsupport, havestore,forwardtime,store);
//            }
//
//            resultGroupAttr = productFrontService.groupAttr(groupAttr);
//
//            //在选择品名后,需要返回对应品名的所有属性条件作为筛选项
//            if(!StringUtils.hasText(productname)){
//                resultGroupAttr2 = productFrontService.groupAttrInAttr(groupAttr1);
//            }else{
//                resultGroupAttr2 = productFrontService.groupAttrInAttrbyProductname(groupAttr1);
//            }


        resultGroupAttr.putAll(resultGroupAttr2);

        Set<String> keySet = resultGroupAttr.keySet();
        for (String key : keySet) {
            KeyValue keyValue = new KeyValue();
            keyValue.setKey(key);
            keyValue.setValue(new ArrayList(resultGroupAttr.get(key)));
            keyValue.setName(ProductSearchUtils.getName(key).replace("一级分类","大类")
                    .replace("二级分类","分类").replace("三级分类","标准"));
            keyValue.setSort(ProductSearchUtils.getSort(key));


//                if(key.equals("level1")){
//                    keyValue.value.sort((String v1, String v2) -> {return ProductSearchUtils.getBigSort(v1).compareTo(ProductSearchUtils.getBigSort(v2));
//                    });
//                }
            if(key.equals("level1")){
                keyValue.value.sort((String v1,String v2)->{
                    return ProductSearchSortUtils.getSortWeigth("大类",Quantity.STATE_0,v1).compareTo(ProductSearchSortUtils.getSortWeigth("大类",Quantity.STATE_0,v2));
                });
            }

            if(key.equals("level2")){
                keyValue.value.sort((String v1,String v2)->{
                    return ProductSearchSortUtils.getSortWeigth("分类",Quantity.STATE_0,v1).compareTo(ProductSearchSortUtils.getSortWeigth("分类",Quantity.STATE_0,v2));
                });
            }

            if(key.equals("level3")){
                keyValue.value.sort((String v1,String v2)->{
                    return ProductSearchSortUtils.getSortWeigth("标准",Quantity.STATE_0,v1).compareTo(ProductSearchSortUtils.getSortWeigth("标准",Quantity.STATE_0,v2));
                });
            }

            if(key.equals("productname")){
                keyValue.value.sort((String v1,String v2)->{
                    return ProductSearchSortUtils.getSortWeigth("品名",Quantity.STATE_0,v1).compareTo(ProductSearchSortUtils.getSortWeigth("品名",Quantity.STATE_0,v2));
                });
            }


            if(key.equals("brand")){
                keyValue.value.sort((String v1,String v2)->{
                    return ProductSearchSortUtils.getSortWeigth("品牌",Quantity.STATE_0,v1).compareTo(ProductSearchSortUtils.getSortWeigth("品牌",Quantity.STATE_0,v2));
                });
            }


            if(key.equals("material")){
                keyValue.value.sort((String v1,String v2)->{
                    return ProductSearchSortUtils.getSortWeigth("材质",Quantity.STATE_0,v1).compareTo(ProductSearchSortUtils.getSortWeigth("材质",Quantity.STATE_0,v2));
                });
            }


            if(key.equals("cardnum")){
                keyValue.value.sort((String v1,String v2)->{
                    return ProductSearchSortUtils.getSortWeigth("牌号",Quantity.STATE_0,v1).compareTo(ProductSearchSortUtils.getSortWeigth("牌号",Quantity.STATE_0,v2));
                });
            }

            if(key.equals("surfacetreatment")){
                keyValue.value.sort((String v1,String v2)->{
                    return ProductSearchSortUtils.getSortWeigth("表面处理",Quantity.STATE_0,v1).compareTo(ProductSearchSortUtils.getSortWeigth("表面处理",Quantity.STATE_0,v2));
                });
            }


            if(key.equals("storename")){
                keyValue.value.sort((String v1,String v2)->{
                    return ProductSearchSortUtils.getSortWeigth("仓库",Quantity.STATE_0,v1).compareTo(ProductSearchSortUtils.getSortWeigth("仓库",Quantity.STATE_0,v2));
                });
            }


            if(key.equals("长度")){
                keyValue.value.sort((String v1,String v2)->{
                    return ProductSearchSortUtils.getSortWeigth("长度",Quantity.STATE_0,v1).compareTo(ProductSearchSortUtils.getSortWeigth("长度",Quantity.STATE_0,v2));
                });
            }

            if(key.equals("公称直径")){
                keyValue.value.sort((String v1,String v2)->{
                    return ProductSearchSortUtils.getSortWeigth("公称直径",Quantity.STATE_0,v1).compareTo(ProductSearchSortUtils.getSortWeigth("公称直径",Quantity.STATE_0,v2));
                });
            }

            if(key.equals("牙数")){
                keyValue.value.sort((String v1,String v2)->
                        StringUtils.floatValue(v1) >= StringUtils.floatValue(v2) ? 1 : -1
                );
            }
            if(key.equals("牙距")){
                keyValue.value.sort((String v1,String v2)->
                        StringUtils.floatValue(v1) >= StringUtils.floatValue(v2) ? 1 : -1
                );
            }

            if(key.equals("外径")){
                keyValue.value.sort((String v1,String v2)->
                        StringUtils.floatValue(v1) >= StringUtils.floatValue(v2) ? 1 : -1
                );
            }

            if(key.equals("厚度")){
                keyValue.value.sort((String v1,String v2)->
                        StringUtils.floatValue(v1) >= StringUtils.floatValue(v2) ? 1 : -1
                );
            }

            keyValues.add(keyValue);
        }


        keyValues.sort((KeyValue kv1,KeyValue kv2)->kv1.getSort().compareTo(kv2.getSort()));
        Page page = new Page();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setTotalRows(count);
        page.setList(list);
        page.setTotalPages(count > 0 ? (count - 1) / pageSize + 1 : 0);

        productListRet.data.pageInfo = page;
        productListRet.data.keyValues = keyValues;

        productListRet.setResult(BasicRet.SUCCESS);

        return productListRet;
    }



    @RequestMapping(value = "/otherProdList", method = RequestMethod.POST)
    @ApiOperation("非紧固件类商品搜索")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "搜索词", name = "searchKey", paramType = "query", dataType = "string"),
            @ApiImplicitParam(value = "1级分类", name = "level1", paramType = "query", dataType = "string"),
            @ApiImplicitParam(value = "2级分类", name = "level2", paramType = "query", dataType = "string"),
            @ApiImplicitParam(value = "3级分类", name = "level3", paramType = "query", dataType = "string"),
            @ApiImplicitParam(value = "品名", name = "productname", paramType = "query", dataType = "string"),
            @ApiImplicitParam(value = "品牌", name = "brand", paramType = "query", dataType = "string"),
            @ApiImplicitParam(value = "属性搜索json [{\"attr\":\"length\",\"value\":\"10\"}]", name = "searchJson", paramType = "query", dataType = "string"),
            @ApiImplicitParam(value = "排序方式  1=价格升序，2=价格降序", name = "sorttype", paramType = "query", dataType = "int"),
            @ApiImplicitParam(value = "搜索类型 0=生活类和工业品、1=工业品、2=生活类", name = "type", paramType = "query", dataType = "int"),
    })
    public ProductListRet otherProdList(@RequestParam(required = true, defaultValue = "1") int pageNo,
                                        @RequestParam(required = true, defaultValue = "20") int pageSize,
                                        @RequestParam(required = false) String searchKey,
                                        @RequestParam(required = false) String level1,
                                        @RequestParam(required = false) String level2,
                                        @RequestParam(required = false) String level3,
                                        @RequestParam(required = false) String productname,
                                        @RequestParam(required = false) String brand,
                                        @RequestParam(required = false) String searchJson,
                                        @RequestParam(required = false,defaultValue = "0") int sorttype,
                                        @RequestParam(required = false,defaultValue = "0") int type,
                                        Model model) {
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

        if(member != null){
            member =  memberService.getMemberById(member.getId());
        }


        //存搜索记录
        if(StringUtils.hasText(searchKey)){
            SearchKeyRecord searchKeyRecord = new SearchKeyRecord();
            searchKeyRecord.setSearchkey(searchKey);
            searchKeyRecord.setCreatetime(new Date());
            searchKeyRecord.setType(type);
            searchKeyRecordService.insertSearchKeyRecord(searchKeyRecord);
        }

        ProductListRet productListRet = new ProductListRet();
        int start = (pageNo - 1) * pageSize;


        Map<String, Object> attrs = new HashMap<>();
        if (StringUtils.hasText(searchJson)) {
            Gson gson = new Gson();
            if (!CommonUtils.isGoodJson(searchJson)) {
                productListRet.setResult(BasicRet.ERR);
                productListRet.setMessage("searchJson格式不对");
                return productListRet;
            }

            List<Map<String, String>> attrsList = gson.fromJson(searchJson, new TypeToken<List<Map<String, String>>>() {
            }.getType());

            if (attrsList != null) {
                for (Map<String, String> jsonMap : attrsList) {
                    String name = ProductSearchUtils.getName(jsonMap.get("attr"));
                    if (name != null) {
                        attrs.put(name, jsonMap.get("value"));
                    }
                }
            }
        }

//        int count = productSearchService.otherProdCountSearchWithKeys(StringUtils.nvl(searchKey), level1, level2, level3, productname, brand, attrs,type);
        List<KeyValue> keyValues = new ArrayList<>();
        Map<String,Object> data = productSearchService.search(StringUtils.nvl(searchKey), level1, level2, level3, productname, brand, null, null, null,attrs, start, pageSize,null,null, null,null,null,type,AppConstant.OTHER_PRO_TYPE);
        int count = ((Long)data.get("total")).intValue();
        List<Map<String,Object>> list = (List<Map<String,Object>>) data.get("list");
//        List<Map> list = null;
//        List<List<Map>> resultList = null;
//        Map<String, Set> resultGroupAttr = null;
//        Map<String, Set> resultGroupAttr2 = null;
//
//        List<KeyValue> keyValues = new ArrayList<>();
        if (count > 0) {
//            list = productSearchService.otherProdSearchWithKeys(StringUtils.nvl(searchKey), level1, level2, level3, productname, brand, attrs, pageNo, pageSize,JinshangUtils.otherProdSortType(sorttype),type);
//            List<Map> groupAttr = productSearchService.otherProdFetchSearchKeys(StringUtils.nvl(searchKey), level1, level2, level3, productname, brand, attrs,type);
//            List<Map> groupAttr1 = productSearchService.otherProdFetchSearchAttrKeys(StringUtils.nvl(searchKey), level1, level2, level3, productname, brand, attrs,type);

//            resultGroupAttr = productFrontService.groupAttr(groupAttr);
//            resultGroupAttr2 = productFrontService.groupAttrInAttr(groupAttr1);
            Map<String, List> resultGroupAttr = (Map<String, List>) data.get("aggs");
            Map<String, List> resultGroupAttr2 = (Map<String, List>) data.get("aggs_attr");
            resultGroupAttr.putAll(resultGroupAttr2);

            Set<String> keySet = resultGroupAttr.keySet();

            for (String key : keySet) {
                KeyValue keyValue = new KeyValue();
                keyValue.setKey(key);
                keyValue.setValue(new ArrayList(resultGroupAttr.get(key)));
                keyValue.setName(ProductSearchUtils.getName(key));
                keyValue.setSort(ProductSearchUtils.getSort(key));

                keyValues.add(keyValue);
            }

        }

        keyValues.sort((KeyValue kv1,KeyValue kv2)->kv1.getSort().compareTo(kv2.getSort()));




        Page page = new Page();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setTotalRows(count);
        page.setList(list);
        page.setTotalPages(count > 0 ? (count - 1) / pageSize + 1 : 0);

        productListRet.data.pageInfo = page;
        productListRet.data.keyValues = keyValues;


        productListRet.setResult(BasicRet.SUCCESS);


        return productListRet;
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


    private class ProductListRet2 extends BasicRet {
        private List<Map<String, Object>> data;

        public List<Map<String, Object>> getData() {
            return data;
        }

        public void setData(List<Map<String, Object>> data) {
            this.data = data;
        }
    }


    private class ProductListRet extends BasicRet {
        private class ProductListRetData {
            private Page pageInfo;
            private Map<String, Set> resultAttrGroup;

            public Page getPageInfo() {
                return pageInfo;
            }

//            public Map<String,Set> getResultAttrGroup() {
//                return resultAttrGroup;
//            }

            public Map<String,Object> searchMap;


            public List<KeyValue> keyValues;

            public void setPageInfo(Page pageInfo) {
                this.pageInfo = pageInfo;
            }

            public Map<String, Set> getResultAttrGroup() {
                return resultAttrGroup;
            }

            public void setResultAttrGroup(Map<String, Set> resultAttrGroup) {
                this.resultAttrGroup = resultAttrGroup;
            }

            public Map<String, Object> getSearchMap() {
                return searchMap;
            }

            public void setSearchMap(Map<String, Object> searchMap) {
                this.searchMap = searchMap;
            }

            public List<KeyValue> getKeyValues() {
                return keyValues;
            }

            public void setKeyValues(List<KeyValue> keyValues) {
                this.keyValues = keyValues;
            }
        }


        private ProductListRetData data = new ProductListRetData();

        public ProductListRetData getData() {
            return data;
        }

        public void setData(ProductListRetData data) {
            this.data = data;
        }
    }


    public class KeyValue {
        private String key;
        private List<String> value;
        private String name;
        private  Integer sort;

        public Integer getSort() {
            return sort;
        }

        public void setSort(Integer sort) {
            this.sort = sort;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public List getValue() {
            return value;
        }

        public void setValue(List value) {
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    @PostMapping("/favorite/state")
    @ApiOperation("获取某个商品用户收藏状态")
    @ApiImplicitParam(value = "商品id", name = "pid", required = true, paramType = "query", dataType = "int")
    public BasicExtRet getGoodsFavoriteState(@RequestParam(required = true) Long pid,
                                             HttpServletRequest request) {
        BasicExtRet basicExtRet = new BasicExtRet();
        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute(AppConstant.MEMBER_SESSION_NAME);
        basicExtRet.setMessage("查询成功");
        basicExtRet.setResult(BasicRet.SUCCESS);
        Map<String, Boolean> result = new HashMap<>(1);
        if (member != null) {
            if (favoriteService.getGoodsFavoriteByMemberId(member.getId(), pid)) {
                result.put("state", true);
                basicExtRet.setData(result);
            } else {
                result.put("state", false);
                basicExtRet.setData(result);
            }
        } else {
            result.put("state", false);
            basicExtRet.setData(result);
        }
        return basicExtRet;
    }







    /*
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ApiOperation("紧固件产品搜索")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "搜索词", name = "searchKey", paramType = "query", dataType = "string"),
            @ApiImplicitParam(value = "1级分类", name = "level1", paramType = "query", dataType = "string"),
            @ApiImplicitParam(value = "2级分类", name = "level2", paramType = "query", dataType = "string"),
            @ApiImplicitParam(value = "3级分类", name = "level3", paramType = "query", dataType = "string"),
            @ApiImplicitParam(value = "品名", name = "productname", paramType = "query", dataType = "string"),
            @ApiImplicitParam(value = "品牌", name = "brand", paramType = "query", dataType = "string"),
            @ApiImplicitParam(value = "牌号", name = "cardnum", paramType = "query", dataType = "string"),
            @ApiImplicitParam(value = "材质", name = "material", paramType = "query", dataType = "string"),
            @ApiImplicitParam(value = "属性搜索json [{\"attr\":\"length\",\"value\":\"10\"}]", name = "searchJson", paramType = "query", dataType = "string"),
            @ApiImplicitParam(value = "排序方式 默认按规格降序 0=按规格升序，1=按规格降序", name = "sorttype", paramType = "query", dataType = "int"),
    })
    public ProductListRet list(
            @RequestParam(required = true, defaultValue = "1") int pageNo,
            @RequestParam(required = true, defaultValue = "20") int pageSize,
            @RequestParam(required = false) String searchKey,
            @RequestParam(required = false) String level1,
            @RequestParam(required = false) String level2,
            @RequestParam(required = false) String level3,
            @RequestParam(required = false) String productname,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String cardnum,
            @RequestParam(required = false) String material,
            @RequestParam(required = false) String searchJson,
//            @RequestParam(required = false) String diameter,
            @RequestParam(required = false) Integer sorttype,
            Model model) {
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);


        if(member != null){
            member =  memberService.getMemberById(member.getId());
        }

        ProductListRet productListRet = new ProductListRet();

        int start = (pageNo - 1) * pageSize;


        if(!StringUtils.hasText(searchKey) && sorttype == null){
            sorttype = 0;
        }


        Map<String, Object> attrs = new HashMap<>();
//        if(StringUtils.hasText(length)){
//             attrs.put("长度",length);
//        }
//        if(StringUtils.hasText(diameter)){
//            attrs.put("直径",diameter);
//        }

        if (StringUtils.hasText(searchJson)) {
            Gson gson = new Gson();
            if (!CommonUtils.isGoodJson(searchJson)) {
                productListRet.setResult(BasicRet.ERR);
                productListRet.setMessage("searchJson格式不对");
                return productListRet;
            }

            List<Map<String, String>> attrsList = gson.fromJson(searchJson, new TypeToken<List<Map<String, String>>>() {
            }.getType());

            if (attrsList != null) {
                for (Map<String, String> jsonMap : attrsList) {
                    String name = ProductSearchUtils.getName(jsonMap.get("attr"));
                    if (name != null) {
                        attrs.put(name, jsonMap.get("value"));
                    }
                }
            }
        }

        int count = productSearchService.countSearchWithKeys(StringUtils.nvl(searchKey), level1, level2, level3, productname, brand, cardnum, material, attrs);

        List<Map> list = null;
        List<List<Map>> resultList = null;
        Map<String, Set> resultGroupAttr = null;
        Map<String, Set> resultGroupAttr2 = null;

        List<KeyValue> keyValues = new ArrayList<>();
        if (count > 0) {
            list = productSearchService.searchWithKeys(StringUtils.nvl(searchKey), level1, level2, level3, productname, brand, cardnum, material, attrs, start, pageSize,sorttype== null ? null :JinshangUtils.fastenSortType(sorttype));

            if (list != null && list.size() > 0) {

                for (Map<String, Object> map : list) {
                    MemberRateSetting memberRateSetting = null;
                    if (member != null && map.get("membersettingstate") != null && map.get("membersettingstate").equals(1)) {
                        memberRateSetting = memberRateSettingService.getRecursiveSetting((Long) map.get("memberid"), (Long) map.get("level3id"), member.getGradleid());
                    }

                    if (memberRateSetting == null) {
                        memberRateSetting = new MemberRateSetting();
                        memberRateSetting.setRate(new BigDecimal(1));
                    }


                    List list1 = new ArrayList();
                    Map<String, Object> prodpriceMap = new HashMap<>();
                    Map<String, Object> threepriceMap = new HashMap<>();
                    Map<String, Object> ninetypriceMap = new HashMap<>();
                    Map<String, Object> thirtypriceMap = new HashMap<>();
                    Map<String, Object> sixtypriceMap = new HashMap<>();

                    if (map.get("prodprice") != null) {
                        prodpriceMap.put("type", 0);
                        prodpriceMap.put("name", Quantity.LIJIFAHUO);
                        if(member != null) {
                            prodpriceMap.put("price", ((BigDecimal) map.get("prodprice")).multiply(memberRateSetting.getRate()).setScale(5, BigDecimal.ROUND_HALF_UP));
                        }else{
                            prodpriceMap.put("price", ((BigDecimal) map.get("prodprice")).multiply(memberRateSetting.getRate()).setScale(5, BigDecimal.ROUND_HALF_UP));
                        }
                        list1.add(prodpriceMap);
                    }



//                    if (map.get("threeprice") != null) {
//                        threepriceMap.put("type", 3);
//                        threepriceMap.put("name",Quantity.SANTIANFAHUO);
//                        if(member != null) {
//                            threepriceMap.put("price", ((BigDecimal) map.get("threeprice")).multiply(memberRateSetting.getRate()).setScale(5, BigDecimal.ROUND_HALF_UP));
//                        }else{
//                            threepriceMap.put("price", ((BigDecimal) map.get("threeprice")).multiply(memberRateSetting.getRate()).setScale(5, BigDecimal.ROUND_HALF_UP));
//                        }
//                        list1.add(threepriceMap);
//                    }



                    if (map.get("thirtyprice") != null) {
                        thirtypriceMap.put("type", 30);
                        thirtypriceMap.put("name", Quantity.SANSHITIANFAHUO);
                        if(member != null) {
                            thirtypriceMap.put("price", ((BigDecimal) map.get("thirtyprice")).multiply(memberRateSetting.getRate()).setScale(5, BigDecimal.ROUND_HALF_UP));
                        }else{
                            thirtypriceMap.put("price", ((BigDecimal) map.get("thirtyprice")).multiply(memberRateSetting.getRate()).setScale(5, BigDecimal.ROUND_HALF_UP));
                        }
                        list1.add(thirtypriceMap);
                    }


                    if (map.get("sixtyprice") != null) {
                        sixtypriceMap.put("type", 60);
                        sixtypriceMap.put("name", Quantity.LIUSHITIANFAHUO);
                        if(member != null) {
                            sixtypriceMap.put("price", ((BigDecimal) map.get("sixtyprice")).multiply(memberRateSetting.getRate()).setScale(5, BigDecimal.ROUND_HALF_UP));
                        }else{
                            sixtypriceMap.put("price", ((BigDecimal) map.get("sixtyprice")).multiply(memberRateSetting.getRate()).setScale(5, BigDecimal.ROUND_HALF_UP));
                        }

                        list1.add(sixtypriceMap);
                    }



                    if (map.get("ninetyprice") != null) {
                        ninetypriceMap.put("type", 90);
                        ninetypriceMap.put("name", Quantity.JIUSHITIANFAHUO);
                        if(member != null) {
                            ninetypriceMap.put("price", ((BigDecimal) map.get("ninetyprice")).multiply(memberRateSetting.getRate()).setScale(5, BigDecimal.ROUND_HALF_UP));
                        }else{
                            ninetypriceMap.put("price", ((BigDecimal) map.get("ninetyprice")).multiply(memberRateSetting.getRate()).setScale(5, BigDecimal.ROUND_HALF_UP));
                        }
                        list1.add(ninetypriceMap);
                    }



                    map.remove("threeprice");
                    map.remove("thirtyprice");
                    map.remove("sixtyprice");
                    map.remove("ninetyprice");


                   if(member != null) {
                       map.put("prices", list1);
                       map.put("showprice",true);
                   }else{
                       map.put("prices", list1);
                       map.put("showprice",false);
                   }

                    map.put("brandpic", map.get("pic"));
                    map.remove("pic");

                    //转换包装方式
                    List packageList = JinshangUtils.toCovertPacking(StringUtils.nvl(map.get("packagetype")));
                    map.put("packages", packageList);

                }
            }

            ProductGroup sort = new ProductGroup();
            resultList = sort.group(list);
            List<Map> groupAttr = productSearchService.fetchSearchKeys(StringUtils.nvl(searchKey), level1, level2, level3, productname, brand, cardnum, material, attrs);

            List<Map> groupAttr1 = productSearchService.fetchSearchAttrKeys(StringUtils.nvl(searchKey), level1, level2, level3, productname, brand, cardnum, material, attrs);


            resultGroupAttr = productFrontService.groupAttr(groupAttr);
            resultGroupAttr2 = productFrontService.groupAttrInAttr(groupAttr1);

            resultGroupAttr.putAll(resultGroupAttr2);

            Set<String> keySet = resultGroupAttr.keySet();
            for (String key : keySet) {
                KeyValue keyValue = new KeyValue();
                keyValue.setKey(key);
                keyValue.setValue(resultGroupAttr.get(key));
                keyValue.setName(ProductSearchUtils.getName(key));
                keyValue.setSort(ProductSearchUtils.getSort(key));
                keyValues.add(keyValue);
            }
        }




        keyValues.sort((KeyValue kv1,KeyValue kv2)->kv1.getSort().compareTo(kv2.getSort()));
        Page page = new Page();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setTotalRows(count);
        page.setList(resultList);
        page.setTotalPages(count > 0 ? (count - 1) / pageSize + 1 : 0);

        productListRet.data.pageInfo = page;
        productListRet.data.keyValues = keyValues;

        productListRet.setResult(BasicRet.SUCCESS);

        return productListRet;
    }

*/



/*
    @RequestMapping(value = "/list2", method = RequestMethod.POST)
    @ApiOperation("紧固件产品搜索(数据结构重新封装了)")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "搜索词", name = "searchKey", paramType = "query", dataType = "string"),
            @ApiImplicitParam(value = "1级分类", name = "level1", paramType = "query", dataType = "string"),
            @ApiImplicitParam(value = "2级分类", name = "level2", paramType = "query", dataType = "string"),
            @ApiImplicitParam(value = "3级分类", name = "level3", paramType = "query", dataType = "string"),
            @ApiImplicitParam(value = "品名", name = "productname", paramType = "query", dataType = "string"),
            @ApiImplicitParam(value = "品牌", name = "brand", paramType = "query", dataType = "string"),
            @ApiImplicitParam(value = "牌号", name = "cardnum", paramType = "query", dataType = "string"),
            @ApiImplicitParam(value = "材质", name = "material", paramType = "query", dataType = "string"),
            @ApiImplicitParam(value = "属性搜索json [{\"attr\":\"length\",\"value\":\"10\"}]", name = "searchJson", paramType = "query", dataType = "string"),
            @ApiImplicitParam(value = "排序方式 默认按规格降序 0=按规格降序，1=按规格升序", name = "sorttype", paramType = "query", dataType = "int"),
    })
    public ProductListRet list2(
            @RequestParam(required = true, defaultValue = "1") int pageNo,
            @RequestParam(required = true, defaultValue = "20") int pageSize,
            @RequestParam(required = false) String searchKey,
            @RequestParam(required = false) String level1,
            @RequestParam(required = false) String level2,
            @RequestParam(required = false) String level3,
            @RequestParam(required = false) String productname,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String cardnum,
            @RequestParam(required = false) String material,
            @RequestParam(required = false) String searchJson,
            @RequestParam(required = false) Integer sorttype,
            Model model) {
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

        if(member != null){
            member =  memberService.getMemberById(member.getId());
        }


        ProductListRet productListRet = new ProductListRet();
        int start = (pageNo - 1) * pageSize;


        if(!StringUtils.hasText(searchKey) && sorttype == null){
            sorttype = 0;
        }



        Map<String, Object> attrs = new HashMap<>();
        if (StringUtils.hasText(searchJson)) {
            Gson gson = new Gson();
            if (!CommonUtils.isGoodJson(searchJson)) {
                productListRet.setResult(BasicRet.ERR);
                productListRet.setMessage("searchJson格式不对");
                return productListRet;
            }

            List<Map<String, String>> attrsList = gson.fromJson(searchJson, new TypeToken<List<Map<String, String>>>() {
            }.getType());

            if (attrsList != null) {
                for (Map<String, String> jsonMap : attrsList) {
                    String name = ProductSearchUtils.getName(jsonMap.get("attr"));
                    if (name != null) {
                        attrs.put(name, jsonMap.get("value"));
                    }
                }
            }
        }


        int count = productSearchService.countSearchWithKeys(StringUtils.nvl(searchKey), level1, level2, level3, productname, brand, cardnum, material, attrs);

        List<Map> list = null;
        List<List<Map>> resultList = null;
        Map<String, Set> resultGroupAttr = null;
        Map<String, Set> resultGroupAttr2 = null;

        List<KeyValue> keyValues = new ArrayList<>();
        if (count > 0) {
            list = productSearchService.searchWithKeys(StringUtils.nvl(searchKey), level1, level2, level3, productname, brand, cardnum, material, attrs, start, pageSize,sorttype== null ? null :JinshangUtils.fastenSortType(sorttype));

            if (list != null && list.size() > 0) {

                for (Map<String, Object> map : list) {

                    MemberRateSetting memberRateSetting = null;
                    if (member != null && map.get("membersettingstate") != null && map.get("membersettingstate").equals(1)) {
                        memberRateSetting = memberRateSettingService.getRecursiveSetting((Long) map.get("memberid"), (Long) map.get("level3id"), member.getGradleid());
                    }

                    if (memberRateSetting == null) {
                        memberRateSetting = new MemberRateSetting();
                        memberRateSetting.setRate(new BigDecimal(1));
                    }


                    List list1 = new ArrayList();
                    Map<String, Object> prodpriceMap = new HashMap<>();
                    Map<String, Object> threepriceMap = new HashMap<>();
                    Map<String, Object> ninetypriceMap = new HashMap<>();
                    Map<String, Object> thirtypriceMap = new HashMap<>();
                    Map<String, Object> sixtypriceMap = new HashMap<>();

                    if (map.get("prodprice") != null) {
                        prodpriceMap.put("type", 0);
                        prodpriceMap.put("name", Quantity.LIJIFAHUO);
                        if(member != null) {
                            prodpriceMap.put("price", ((BigDecimal) map.get("prodprice")).multiply(memberRateSetting.getRate()).setScale(5, BigDecimal.ROUND_HALF_UP));
                        }else{
                            prodpriceMap.put("price", ((BigDecimal) map.get("prodprice")).multiply(memberRateSetting.getRate()).setScale(5, BigDecimal.ROUND_HALF_UP));
                        }
                        list1.add(prodpriceMap);
                    }


                    if (map.get("ninetyprice") != null) {
                        ninetypriceMap.put("type", 90);
                        ninetypriceMap.put("name", Quantity.JIUSHITIANFAHUO);
                        if(member != null) {
                            ninetypriceMap.put("price", ((BigDecimal) map.get("ninetyprice")).multiply(memberRateSetting.getRate()).setScale(5, BigDecimal.ROUND_HALF_UP));
                        }else{
                            ninetypriceMap.put("price", ((BigDecimal) map.get("ninetyprice")).multiply(memberRateSetting.getRate()).setScale(5, BigDecimal.ROUND_HALF_UP));
                        }
                        list1.add(ninetypriceMap);
                    }


                    if (map.get("thirtyprice") != null) {
                        thirtypriceMap.put("type", 30);
                        thirtypriceMap.put("name", Quantity.SANSHITIANFAHUO);
                        if(member != null) {
                            thirtypriceMap.put("price", ((BigDecimal) map.get("thirtyprice")).multiply(memberRateSetting.getRate()).setScale(5, BigDecimal.ROUND_HALF_UP));
                        }else{
                            thirtypriceMap.put("price", ((BigDecimal) map.get("thirtyprice")).multiply(memberRateSetting.getRate()).setScale(5, BigDecimal.ROUND_HALF_UP));
                        }
                        list1.add(thirtypriceMap);
                    }


                    if (map.get("sixtyprice") != null) {
                        sixtypriceMap.put("type", 60);
                        sixtypriceMap.put("name", Quantity.LIUSHITIANFAHUO);
                        if(member != null) {
                            sixtypriceMap.put("price", ((BigDecimal) map.get("sixtyprice")).multiply(memberRateSetting.getRate()).setScale(5, BigDecimal.ROUND_HALF_UP));
                        }else{
                            sixtypriceMap.put("price", ((BigDecimal) map.get("sixtyprice")).multiply(memberRateSetting.getRate()).setScale(5, BigDecimal.ROUND_HALF_UP));
                        }
                        list1.add(sixtypriceMap);
                    }


                    map.remove("threeprice");
                    map.remove("thirtyprice");
                    map.remove("sixtyprice");
                    map.remove("ninetyprice");

                    if(member != null) {
                        map.put("prices", list1);
                        map.put("showprice",true);
                    }else{
                        map.put("prices", list1);
                        map.put("showprice",false);
                    }

                    map.put("brandpic", map.get("pic"));
                    map.remove("pic");

                    //转换包装方式
                    List packageList = JinshangUtils.toCovertPacking(StringUtils.nvl(map.get("packagetype")));
                    map.put("packages", packageList);
                }
            }

            ProductGroup sort = new ProductGroup();
            resultList = sort.group(list);
            List<Map> groupAttr = productSearchService.fetchSearchKeys(StringUtils.nvl(searchKey), level1, level2, level3, productname, brand, cardnum, material, attrs);
            List<Map> groupAttr1 = productSearchService.fetchSearchAttrKeys(StringUtils.nvl(searchKey), level1, level2, level3, productname, brand, cardnum, material, attrs);

            resultGroupAttr = productFrontService.groupAttr(groupAttr);
            resultGroupAttr2 = productFrontService.groupAttrInAttr(groupAttr1);
            resultGroupAttr.putAll(resultGroupAttr2);

            Set<String> keySet = resultGroupAttr.keySet();
            for (String key : keySet) {
                KeyValue keyValue = new KeyValue();
                keyValue.setKey(key);
                keyValue.setValue(resultGroupAttr.get(key));
                keyValue.setName(ProductSearchUtils.getName(key));
                keyValues.add(keyValue);
            }
        }

        Page page = new Page();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setTotalRows(count);
        page.setList(aggregationProd(resultList));
        page.setTotalPages(count > 0 ? (count - 1) / pageSize + 1 : 0);

        productListRet.data.pageInfo = page;
        productListRet.data.keyValues = keyValues;



        Map<String,Object> searchMap = new HashMap<>();
        searchMap.put("searchKey",searchKey);
        searchMap.put("level1",level1);
        searchMap.put("level2",level2);
        searchMap.put("level3",level3);
        searchMap.put("productname",productname);
        searchMap.put("brand",brand);
        searchMap.put("cardnum",cardnum);
        searchMap.put("material",material);
        searchMap.put("searchJson",searchJson);
        searchMap.put("sorttype",sorttype);
        productListRet.data.searchMap = searchMap;


        productListRet.setResult(BasicRet.SUCCESS);

        return productListRet;
    }

*/

    /**
     * 对聚合后的结果重新封装
     * @param resultList
     * @return
     */

    /*
    private List<Map<String, Object>> aggregationProd(List<List<Map>> resultList) {
        List<Map<String, Object>> returnList = new ArrayList<>();
        if(resultList == null) return  returnList;
        for (List<Map> group : resultList) {
            Map<String, Object> commonDataMap = new HashMap<>();
            if (group != null) {
                Map<String, Object> aa = (Map<String, Object>) group.get(0);
                commonDataMap.put("pdno", aa.get("pdno"));
                commonDataMap.put("brand", aa.get("brand"));
                commonDataMap.put("mark", aa.get("mark"));
                commonDataMap.put("productname", aa.get("productname"));
                commonDataMap.put("brandpic", aa.get("brandpic"));
                commonDataMap.put("pdpicture", aa.get("pdpicture"));
                commonDataMap.put("unit", aa.get("unit"));
                commonDataMap.put("stand", aa.get("stand"));
                commonDataMap.put("packages", aa.get("packages"));
                commonDataMap.put("level1id", aa.get("level1id"));
                commonDataMap.put("level2id", aa.get("level2id"));
                commonDataMap.put("level3id", aa.get("level3id"));
                commonDataMap.put("level1", aa.get("level1"));
                commonDataMap.put("level2", aa.get("level2"));
                commonDataMap.put("level3", aa.get("level3"));
                commonDataMap.put("cardnum", aa.get("cardnum"));
                commonDataMap.put("material", aa.get("material"));
                commonDataMap.put("packagetype",aa.get("packagetype"));
                commonDataMap.put("surfacetreatment",aa.get("surfacetreatment"));

            }

            for (Map prod : group) {
                prod.remove("pdno");
                prod.remove("brand");
                prod.remove("productname");
                prod.remove("brandpic");
                prod.remove("reason");
                prod.remove("productid");
                prod.remove("level1id");
                prod.remove("level2id");
                prod.remove("level3id");
                prod.remove("level1");
                prod.remove("level2");
                prod.remove("level3");
                prod.remove("stand");
                prod.remove("mark");
                prod.remove("cardnum");
                prod.remove("cardnumid");
                prod.remove("materialid");
                prod.remove("material");
            }

            commonDataMap.put("prods", group);
            returnList.add(commonDataMap);
        }

        return returnList;
    }

*/












}
