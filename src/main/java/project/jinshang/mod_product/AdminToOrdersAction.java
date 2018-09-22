package project.jinshang.mod_product;

import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import mizuki.project.core.restserver.config.BasicRet;
import mizuki.project.core.restserver.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.jinshang.common.bean.MemberLogOperator;
import project.jinshang.common.bean.Page;
import project.jinshang.common.bean.PageRet;
import project.jinshang.common.constant.AdminAuthorityConst;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.common.constant.Quantity;
import project.jinshang.common.utils.*;
import project.jinshang.mod_activity.bean.LimitTimeProd;
import project.jinshang.mod_activity.bean.LimitTimeStore;
import project.jinshang.mod_activity.service.LimitTimeProdService;
import project.jinshang.mod_activity.service.LimitTimeStoreService;
import project.jinshang.mod_admin.mod_transet.bean.TransactionSetting;
import project.jinshang.mod_admin.mod_transet.service.TransactionSettingService;
import project.jinshang.mod_common.bean.BasicExtRet;
import project.jinshang.mod_company.StoreMapper;
import project.jinshang.mod_company.bean.SellerCompanyInfo;
import project.jinshang.mod_company.bean.Store;
import project.jinshang.mod_company.service.SellerCompanyInfoService;
import project.jinshang.mod_front.ProductFrontAction;
import project.jinshang.mod_front.service.ProductFrontService;
import project.jinshang.mod_invoice.bean.InvoiceInfo;
import project.jinshang.mod_invoice.service.InvoiceService;
import project.jinshang.mod_member.bean.Admin;
import project.jinshang.mod_member.bean.AdminUser;
import project.jinshang.mod_member.bean.Member;
import project.jinshang.mod_member.bean.MemberRateSetting;
import project.jinshang.mod_member.service.AdminService;
import project.jinshang.mod_member.service.AdminUserService;
import project.jinshang.mod_member.service.MemberRateSettingService;
import project.jinshang.mod_member.service.MemberService;
import project.jinshang.mod_product.bean.*;
import project.jinshang.mod_product.bean.dto.OtherProductQueryDto;
import project.jinshang.mod_product.service.*;
import project.jinshang.mod_shippingaddress.bean.ShippingAddress;
import project.jinshang.mod_shippingaddress.bean.ShippingAddressExample;
import project.jinshang.mod_shippingaddress.service.ShippingAddressService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/admin/toorders")
@SessionAttributes({AppConstant.ADMIN_SESSION_NAME})
@Api(tags = "后台代下单模块", description = "后台代下单接口")
@Transactional(rollbackFor = Exception.class)
public class AdminToOrdersAction {

    @Autowired
    ShopCarService shopCarService;

    @Autowired
    private MemberRateSettingService memberRateSettingService;

    @Autowired
    private TransactionSettingService transactionSettingService;

    @Autowired
    private MemberOperateLogService memberOperateLogService;

    @Autowired
    private ProductAttrService productAttrService;

    @Autowired
    private OrderProductServices orderProductServices;

    @Autowired
    private ProductStoreService productStoreService;

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private ShippingAddressService shippingAddressService;

    @Autowired
    private StoreMapper storeMapper;

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private LimitTimeProdService limitTimeProdService;

    @Autowired
    private LimitTimeStoreService limitTimeStoreService;

    @Autowired
    private AdminUserService adminUserService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private SellerCompanyInfoService sellerCompanyInfoService;

    @Autowired
    private OrderProductLogService orderProductLogService;

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private CategoriesService categoriesService;

    @Autowired
    private OtherProdService otherProdService;

    @Autowired
    private ProductFrontService productFrontService;

    @Autowired
    private ProductSearchService productSearchService;
    @Autowired
    private FreightService freightService;



    //远期全款打折率
    private static final BigDecimal allPayRate = new BigDecimal(0.99);

    MemberLogOperator memberLogOperator = new MemberLogOperator();

    @PostMapping("/insertShopCar")
    @ApiOperation(value = "新增商品到代下单列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "memberid", value = "会员id", required = true, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "pdid", value = "商品id", required = true, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "pdno", value = "商品编号", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "limitid", value = "限时购id", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "pdnumber", value = "商品数量", required = true, paramType = "query", dataType = "double"),
            @ApiImplicitParam(name = "storeid", value = "仓库id", required = false, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "delivertime", value = "发货时间", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "unit", value = "单位", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "protype", value = "远期类型0=不是远期1=全款2=定金", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "isonline", value = "订单类型标识0=线上1=线下2=限时购", required = false, paramType = "query", dataType = "int"),
    })
    public BasicRet insertShopCar(ShopCar shopCar, HttpServletRequest request, Long limitid,long memberid) throws Exception {
       // Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        Member member = memberService.selectById(memberid);
        if(shopCar.getIsonline()==null){
            shopCar.setIsonline(Quantity.STATE_0);
        }
        BasicRet basicRet = new BasicRet();
        //正常订单
        if(shopCar.getIsonline()==Quantity.STATE_0){
            //判断商品是否下架
            ProductInfo info = shopCarService.getProductInfo(shopCar.getPdid());
            //判断买家卖家是否是同一人
            Long sellerId = info.getMemberid();
            if (sellerId == member.getId()) {
                basicRet.setMessage("不能购买自己的商品");
                basicRet.setResult(BasicRet.ERR);
                return basicRet;
            }
            ProductStore productStore = null;
            if (info != null) {
                shopCar.setSaleid(info.getMemberid());
                if (info.getProducttype().equals("紧固件")) {
                    shopCar.setProducttype(Quantity.STATE_1);
                } else {
                    shopCar.setProducttype(Quantity.STATE_2);
                }


                BigDecimal convertNum = shopCar.getPdnumber();
                String convertUnit = shopCar.getUnit();
                if (AppConstant.FASTENER_PRO_TYPE.equals(info.getProducttype())) {
                    Map<String, Object> map = JinshangUtils.toLowerUnit(info.getPackagetype(), shopCar.getPdnumber(), shopCar.getUnit());
                    convertNum = (BigDecimal) map.get("num");
                    convertUnit = (String) map.get("unit");
                }
                //商品没有上架
                if (info.getPdstate() != Quantity.STATE_4) {
                    basicRet.setResult(BasicRet.ERR);
                    basicRet.setMessage("商品未上架");
                    return basicRet;
                }
                ShopCar shopCar1 = shopCarService.getMemberShopCar(shopCar.getPdid(), member.getId(), shopCar.getDelivertime(),shopCar.getPdno(),shopCar.getProtype());
                //ShopCar shopCar1 = shopCarService.getMemberShopCar1(shopCar.getPdid(), member.getId(),shopCar.getPdno());
                if(shopCar1!=null){
                    convertNum =convertNum.add(shopCar1.getPdnumber());
                }
                //判断库存
                productStore = shopCarService.getProductStore(shopCar.getPdid(), shopCar.getPdno(),shopCar.getStoreid());


                if(productStore != null && productStore.getMinplus()!=null && productStore.getMinplus().compareTo(Quantity.BIG_DECIMAL_0)>0){

                    if(!this.checkBuyNum(convertNum,productStore.getMinplus())){
                        basicRet.setMessage("购买量必须是加购量的倍数");
                        basicRet.setResult(BasicRet.ERR);
                        return basicRet;
                    }
                }


                shopCar.setStorename(productStore.getStorename());
                shopCar.setStoreid(productStore.getStoreid());
                if (productStore != null) {
                    //库存不足
                    if ((productStore.getPdstorenum().compareTo(convertNum)) == Quantity.STATE_) {
                        basicRet.setResult(BasicRet.ERR);
                        basicRet.setMessage("库存不足");
                        return basicRet;
                    }
                    //小于起定量
                    if (productStore.getStartnum().compareTo(convertNum) == Quantity.STATE_1) {
                        basicRet.setResult(BasicRet.ERR);
                        basicRet.setMessage("小于起定量");
                        return basicRet;
                    }
                } else {
                    basicRet.setResult(BasicRet.ERR);
                    basicRet.setMessage("找不到库存");
                    return basicRet;
                }
                boolean flag = false;
                //计算阶梯价格
                BigDecimal salePrice = ordersService.updatePriceByNum(convertNum, productStore, shopCar.getDelivertime(), info.getMemberid(), info.getLevel3id(), member.getGradleid());
                //远期定金和全款计算
                BigDecimal allpap = salePrice.multiply(convertNum);
                //如果是远期，需计算远期定金，全款和余额
                if (shopCar.getProtype() != Quantity.STATE_0) {
                    if (shopCar.getProtype() == Quantity.STATE_1) {
                        //全款
                        shopCar.setAllpay(allpap.multiply(allPayRate));
                        salePrice = salePrice.multiply(allPayRate);
                    } else {
                        //定金
                        shopCar.setPartpay(allpap.multiply(transactionSettingService.getTransactionSetting().getRemotepurchasingmargin().multiply(new BigDecimal(0.01))));
                        //余款
                        shopCar.setYupay(allpap.subtract(shopCar.getPartpay()));
                    }
                }
                List<ProductAttr> productAttrs = productAttrService.getListByPidAndPdno(shopCar.getPdid(),shopCar.getPdno());
                //判断购物车里是否有该商品
                if (shopCar1 == null) {
                    shopCar.setMemberid(member.getId());
                    shopCar.setPrice(salePrice);
                    shopCar.setUnit(convertUnit);
                    shopCar.setPdnumber(convertNum);
                    shopCar.setFrightmode(productStore.getFreightmode());
                    StringBuffer sb = new StringBuffer();
                    for(ProductAttr attr:productAttrs){
                        sb.append(attr.getValue()).append("*");
                    }
                    if(productAttrs.size()>0){
                        sb.deleteCharAt(sb.length()-1);
                    }
                    shopCar.setAttrjson(sb.toString());
                    shopCarService.insertShopCar(shopCar);
                } else {
                    shopCar1.setPdnumber(convertNum);
                    shopCar1.setUnit(convertUnit);
                    shopCar1.setPrice(salePrice);
                    shopCar1.setFrightmode(productStore.getFreightmode());
                    if (shopCar.getProtype() != Quantity.STATE_0) {
                        BigDecimal appPap = salePrice.multiply(shopCar1.getPdnumber());
                        shopCar1.setProtype(shopCar.getProtype());
                        //全款
                        shopCar1.setAllpay(appPap.multiply(allPayRate));
                        //定金
                        shopCar1.setPartpay(appPap.multiply(transactionSettingService.getTransactionSetting().getRemotepurchasingmargin().multiply(new BigDecimal(0.01))));
                        //余款
                        shopCar1.setYupay(appPap.subtract(shopCar1.getPartpay()));
                    }
                    shopCarService.updateShopCar(shopCar1);
                }
                basicRet.setResult(BasicRet.SUCCESS);
                basicRet.setMessage("添加成功");
                //保存用户日志
                memberLogOperator.saveMemberLog(member, null, "新增代客户下单", "/rest/admin/toorders/insert",request, memberOperateLogService);
                return basicRet;
            } else {
                basicRet.setResult(BasicRet.ERR);
                basicRet.setMessage("找不到此商品");
                return basicRet;
            }
        }

        if(shopCar.getIsonline()==Quantity.STATE_2){  //限时购
            LimitTimeProd prod = shopCarService.getLimitTimeProd(shopCar.getPdid(),limitid);
            //判断活动是否开始
            Date startTime = prod.getBegintime();
            Date endTime = prod.getEndtime();
            Date now = new Date();
            if(prod.getState()==Quantity.STATE_4){
                if(startTime.compareTo(now)==1){
                    basicRet.setResult(BasicRet.ERR);
                    basicRet.setMessage("活动还未开始");
                    return basicRet;
                }
                if(endTime.compareTo(now)==-1){
                    basicRet.setResult(BasicRet.ERR);
                    basicRet.setMessage("活动已结束");
                    return basicRet;
                }
                Long sellerId = prod.getMemberid();
                if (sellerId == member.getId()) {
                    basicRet.setMessage("不能购买自己的商品");
                    basicRet.setResult(BasicRet.ERR);
                    return basicRet;
                }
                List<ShopCar> list = shopCarService.getLimitShopCarNum(shopCar.getPdid(),member.getId());
                BigDecimal num = new BigDecimal(0);
                for (ShopCar shopCar1:list){
                    num = num.add(shopCar1.getPdnumber());
                }

                num = num.add(shopCar.getPdnumber());
                if(prod.getBuylimit().compareTo(num)==-1){
                    basicRet.setMessage("同一账号超过购买数量");
                    basicRet.setResult(BasicRet.ERR);
                    return basicRet;
                }


                //查询订单商品，查看是否之前有买过该限时购的商品
                BigDecimal buyTotalNum = orderProductServices.getTotalNumByLimitid(member.getId(),shopCar.getLimitid());
                buyTotalNum = buyTotalNum==null ? new BigDecimal(0) : buyTotalNum;

                if(prod.getBuylimit().compareTo(buyTotalNum.add(shopCar.getPdnumber())) == -1){
                    basicRet.setMessage("您本次最多可购买"+prod.getBuylimit().subtract(buyTotalNum)+shopCar.getUnit());
                    basicRet.setResult(BasicRet.ERR);
                    return  basicRet;
                }


//                ProductStore productStore = shopCarService.getProductStore(shopCar.getPdid(),shopCar.getPdno(),shopCar.getStoreid());
                ProductStore productStore = productStoreService.getByPdidAndPdno(shopCar.getPdid(),shopCar.getPdno());

                ShopCar shopCar1 = shopCarService.getLimitShopCarNum(shopCar.getPdid(),member.getId(),shopCar.getPdno());
                LimitTimeStore limitTimeStore = shopCarService.getLimitTimeStore(limitid,shopCar.getPdid(),shopCar.getPdno());
                if(limitTimeStore.getStorenum().compareTo(shopCar.getPdnumber())==-1){
                    basicRet.setMessage("超过库存数量");
                    basicRet.setResult(BasicRet.ERR);
                    return basicRet;
                }


                //更新库存
//                limitTimeStore.setStorenum(limitTimeStore.getStorenum().subtract(shopCar.getPdnumber()));
//                limitTimeStore.setSalesnum(limitTimeStore.getSalesnum().add(shopCar.getPdnumber()));
//                shopCarService.updateLimitTimeStore(limitTimeStore);
//                prod.setSalestotalnum(prod.getSalestotalnum().add(shopCar.getPdnumber()));
//                shopCarService.updateLimitTimeProd(prod);

                //更新库存放到提交订单方法中



                if(shopCar1==null){
                    shopCar.setStoreid(productStore.getStoreid());
                    shopCar.setStorename(productStore.getStorename());
                    shopCar.setMemberid(member.getId());
                    shopCar.setSaleid(prod.getMemberid());
                    shopCar.setPrice(limitTimeStore.getLimitprice());
                    shopCar.setUnit(shopCar.getUnit());
                    shopCar.setPdnumber(shopCar.getPdnumber());
                    shopCar.setFrightmode(-1l);
                    StringBuffer sb = new StringBuffer();
                    List<ProductAttr> productAttrs = productAttrService.getListByPidAndPdno(shopCar.getPdid(),shopCar.getPdno());
                    for(ProductAttr attr:productAttrs){
                        sb.append(attr.getValue()).append("*");
                    }
                    if(productAttrs.size()>0){
                        sb.deleteCharAt(sb.length()-1);
                    }
                    shopCar.setAttrjson(sb.toString());
                    shopCarService.insertShopCar(shopCar);


                }else {
                    shopCar1.setPdnumber(shopCar1.getPdnumber().add(shopCar.getPdnumber()));
                    shopCar1.setUnit(shopCar.getUnit());
                    shopCar1.setPrice(limitTimeStore.getLimitprice());
                    shopCar1.setFrightmode(-1l);
                    shopCarService.updateShopCar(shopCar1);
                }

                basicRet.setResult(BasicRet.SUCCESS);
                basicRet.setMessage("添加成功");
                //保存用户日志
                memberLogOperator.saveMemberLog(member, null, "新增商品到购物车", "/rest/buyer/shopcar/insertShopCar",request, memberOperateLogService);
                return basicRet;
            }else {
                basicRet.setMessage("商品不在活动中");
                basicRet.setResult(BasicRet.ERR);
                return basicRet;
            }

        }

        return basicRet;
    }


    @ApiOperation("获取商品价格")
    @RequestMapping(value = "/prodRatePrice",method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "memberid", value = "会员id", required = true, paramType = "query", dataType = "long"),
    })
    public ProdRatePriceRet prodPrice(Long[] pids,long memberid){
       Member member = memberService.selectById(memberid);
        ProdRatePriceRet prodRatePriceRet = new ProdRatePriceRet();

        if(member == null){
            prodRatePriceRet.data.list = null;
            prodRatePriceRet.setMessage("用户未登陆");
            prodRatePriceRet.setResult(BasicRet.SUCCESS);
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



    private boolean checkBuyNum(BigDecimal buynum,BigDecimal minplus){
        try {
            BigDecimal a = buynum.divide(minplus);

            //System.out.println(a.intValue());

            if(new BigDecimal(a.intValue()).compareTo(a) != 0){
                return false;
            }

            return  true;
        } catch (Exception e) {
            return  false;
        }
    }


//    /**
//     * 计算价格
//     *
//     * @param num
//     * @param productStore
//     * @param diliverTime
//     * @return
//     */
//    private BigDecimal updatePriceByNum(BigDecimal num, ProductStore productStore, @NotNull String diliverTime, long sellerid, long levelid, long gradeid) {
//
//        //计算阶梯价格
//        BigDecimal basicPrice = new BigDecimal(0);
//
//        if (diliverTime.equals(Quantity.SANTIANFAHUO)) {
//            basicPrice = productStore.getThreeprice();
//        } else if (diliverTime.equals(Quantity.JIUSHITIANFAHUO)) {
//            basicPrice = productStore.getNinetyprice();
//        } else if (diliverTime.equals(Quantity.SANSHITIANFAHUO)) {
//            basicPrice = productStore.getThirtyprice();
//        } else if (diliverTime.equals(Quantity.LIUSHITIANFAHUO)) {
//            basicPrice = productStore.getSixtyprice();
//        } else {
//            basicPrice = productStore.getProdprice();
//        }
//        MemberRateSetting memberRateSetting = memberRateSettingService.getSetting(sellerid, levelid, gradeid);
//        basicPrice = basicPrice.multiply(memberRateSetting.getRate());
//        BigDecimal saleprice = new BigDecimal(0);
//        //判断是否开启阶梯价格
//        if (productStore.getStepwiseprice()) {
//            Gson gson = new Gson();
//            List<StepWisePrice> list = gson.fromJson(productStore.getIntervalprice(), new TypeToken<ArrayList<StepWisePrice>>() {
//            }.getType());
//            //是否匹配价格区间
//            boolean flag = false;
//            //最大价格区间百分比
//            BigDecimal lastPercent = new BigDecimal(0);
//            BigDecimal maxstart = new BigDecimal(0);
//            //匹配价格区间
//            for (StepWisePrice stepWisePrice : list) {
//                BigDecimal start = stepWisePrice.getStart();
//                BigDecimal end = stepWisePrice.getEnd();
//                BigDecimal percent = stepWisePrice.getRate();
//                //end为0是最大价格区间
//                if (end.compareTo(new BigDecimal(0)) == 0) {
//                    lastPercent = percent;
//                    maxstart = start;
//                }
//                if ((num.compareTo(start) == 1 || num.compareTo(start) == 0) && (num.compareTo(end) == -1 )) {
//                    saleprice = basicPrice.multiply(percent.multiply(new BigDecimal(0.01)));
//                    flag = true;
//                    break;
//                }
//            }
//            //没有任何价格区间匹配，取最大的价格区间
//            if (!flag) {
//                if (num.compareTo(maxstart) == 1) {
//                    saleprice = basicPrice.multiply(lastPercent.multiply(new BigDecimal(0.01)));
//                } else {
//                    saleprice = basicPrice;
//                }
//            }
//        } else {
//            saleprice = basicPrice;
//        }
//
//        return saleprice;
//    }



    @PostMapping("/loadAllShopCar")
    @ApiOperation(value = "加载下单列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "memberid", value = "会员id", required = false, paramType = "query", dataType = "long"),
    })
    public ShopCarListRet loadList(Model model, @RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = "20") int pageSize,long memberid) {
        ShopCarListRet shopCarListRet = new ShopCarListRet();
        //Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        //Admin admin = (Admin) model.asMap().get(AppConstant.ADMIN_SESSION_NAME);
        Member member = memberService.selectById(memberid);
        PageInfo pageInfo = shopCarService.loadAllShipCar(member.getId(), pageNo, pageSize);

        shopCarListRet.data.pageInfo = pageInfo;
        shopCarListRet.setMessage("返回成功");
        shopCarListRet.setResult(BasicRet.SUCCESS);
        return shopCarListRet;
    }


    @RequestMapping(value = "/updateShopCar", method = RequestMethod.POST)
    @ApiOperation(value = "当变更数量和单位和远期付款方式时候用")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "memberid", value = "会员id", required = false, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "id", value = "购物车id", required = false, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "pdid", value = "针对详情页商品id", required = false, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "storeid", value = "针对详情页仓库id", required = false, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "diliveryTime", value = "针对详情页发货时间", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "pdnumber", value = "商品数量", required = true, paramType = "query", dataType = "float"),
            @ApiImplicitParam(name = "unit", value = "单位：箱或盒或千", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "protype", value = "远期类型0=不是远期1=全款2=定金", required = true, paramType = "query", dataType = "int"),
    })
    public ShopCarRet updateShopCar(Model model, Long id, BigDecimal pdnumber, String unit, long pdid, long storeid, String diliveryTime, short protype, String pdno,long memberid) {

        //Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        Member member = memberService.selectById(memberid);
        ShopCarRet shopCarRet = new ShopCarRet();
        BigDecimal saleprice = new BigDecimal(0);
        //有购物车的时候
        if (id != null) {
            ShopCar shopCar = shopCarService.getShopCarByPrimeKey(id);

            if(shopCar.getIsonline() ==Quantity.STATE_2){//限时购

                LimitTimeStore limitTimeStore = shopCarService.getLimitTimeStore(shopCar.getLimitid(),shopCar.getPdid(),shopCar.getPdno());
                ProductStore productStore = shopCarService.getProductStore(shopCar.getPdid(), shopCar.getPdno(),shopCar.getStoreid());
                ProductInfo info = shopCarService.getProductInfo(shopCar.getPdid());


                //库存
                BigDecimal storeNum = limitTimeStore.getStorenum();
                //起定量
                BigDecimal startNum = productStore.getStartnum();

                BigDecimal convertNum = pdnumber;
                String converUnit = unit;

                if (AppConstant.FASTENER_PRO_TYPE.equals(info.getProducttype())) {
                    Map<String, Object> map = JinshangUtils.toLowerUnit(info.getPackagetype(), pdnumber, unit);
                    convertNum = (BigDecimal) map.get("num");
                    converUnit = (String) map.get("unit");
                }


                //判断库存和起定量
                if (convertNum.compareTo(storeNum) == 1) {
                    shopCarRet.setResult(BasicRet.ERR);
                    shopCarRet.setMessage("库存不足");
                    return shopCarRet;
                }
                if (convertNum.compareTo(startNum) == -1) {
                    shopCarRet.setResult(BasicRet.ERR);
                    shopCarRet.setMessage("订购数量小于起定量");
                    return shopCarRet;
                }


                //阶梯价计算
                saleprice = limitTimeStore.getLimitprice();
                shopCar.setPrice(saleprice);
                shopCar.setPdnumber(convertNum);
                shopCar.setUnit(converUnit);




//                //计算远期定金和全款
//                if (!shopCar.getDelivertime().equals(Quantity.LIJIFAHUO)) {
//                    shopCar.setProtype(protype);
//                    //全款
//                    shopCar.setAllpay(saleprice.multiply(convertNum).multiply(allPayRate));
//                    //定金
//                    shopCar.setPartpay(saleprice.multiply(convertNum).multiply(transactionSettingService.getTransactionSetting().getRemotepurchasingmargin().multiply(new BigDecimal(0.01))));
//                    //余款
//                    shopCar.setYupay(saleprice.multiply(convertNum).multiply((new BigDecimal(1).subtract(transactionSettingService.getTransactionSetting().getRemotepurchasingmargin().multiply(new BigDecimal(0.01))))));
//                }
                shopCarService.updateShopCar(shopCar);



            }else{//其他
                ProductStore productStore = shopCarService.getProductStore(shopCar.getPdid(),shopCar.getPdno(), shopCar.getStoreid());
                ProductInfo info = shopCarService.getProductInfo(shopCar.getPdid());
                //库存
                BigDecimal storeNum = productStore.getPdstorenum();
                //起定量
                BigDecimal startNum = productStore.getStartnum();

                BigDecimal convertNum = pdnumber;
                String converUnit = unit;

                if (AppConstant.FASTENER_PRO_TYPE.equals(info.getProducttype())) {
                    Map<String, Object> map = JinshangUtils.toLowerUnit(info.getPackagetype(), pdnumber, unit);
                    convertNum = (BigDecimal) map.get("num");
                    converUnit = (String) map.get("unit");
                }

                //判断库存和起定量
                if (convertNum.compareTo(storeNum) == 1) {
                    shopCarRet.setResult(BasicRet.ERR);
                    shopCarRet.setMessage("库存不足");
                    return shopCarRet;
                }
                if (convertNum.compareTo(startNum) == -1) {
                    shopCarRet.setResult(BasicRet.ERR);
                    shopCarRet.setMessage("订购数量小于起定量");
                    return shopCarRet;
                }


                /*
                if(productStore.getMinplus() != null && productStore.getMinplus().compareTo(Quantity.BIG_DECIMAL_0)>0){
                    if(!this.checkBuyNum(convertNum,productStore.getMinplus())){
                        shopCarRet.setResult(BasicRet.ERR);
                        shopCarRet.setMessage("购买量必须是加购量的倍数");
                        return shopCarRet;
                    }
                }*/

                //阶梯价计算
                saleprice = ordersService.updatePriceByNum(convertNum, productStore, shopCar.getDelivertime(), info.getMemberid(), info.getLevel3id(), member.getGradleid());
                shopCar.setPrice(saleprice);
                shopCar.setPdnumber(convertNum);
                shopCar.setUnit(converUnit);
                //计算远期定金和全款
                if (!shopCar.getDelivertime().equals(Quantity.LIJIFAHUO)) {
                    shopCar.setProtype(protype);
                    //全款
                    shopCar.setAllpay(saleprice.multiply(convertNum).multiply(allPayRate));
                    //定金
                    shopCar.setPartpay(saleprice.multiply(convertNum).multiply(transactionSettingService.getTransactionSetting().getRemotepurchasingmargin().multiply(new BigDecimal(0.01))));
                    //余款
                    shopCar.setYupay(saleprice.multiply(convertNum).multiply((new BigDecimal(1).subtract(transactionSettingService.getTransactionSetting().getRemotepurchasingmargin().multiply(new BigDecimal(0.01))))));
                }
                shopCarService.updateShopCar(shopCar);
            }

            //商品详情页
        } else {
            ProductStore productStore = shopCarService.getProductStore(pdid, pdno,storeid);
            ProductInfo info = shopCarService.getProductInfo(pdid);
            BigDecimal storeNum = productStore.getPdstorenum();
            BigDecimal startNum = productStore.getStartnum();

            BigDecimal convertNum = pdnumber;
            if (AppConstant.FASTENER_PRO_TYPE.equals(info.getProducttype())) {
                convertNum = (BigDecimal) (JinshangUtils.toLowerUnit(info.getPackagetype(), pdnumber, unit)).get("num");
            }

            //判断库存和起定量
            if (convertNum.compareTo(storeNum) == 1) {
                shopCarRet.setResult(BasicRet.ERR);
                shopCarRet.setMessage("库存不足");
                return shopCarRet;
            }
            if (convertNum.compareTo(startNum) == -1) {
                shopCarRet.setResult(BasicRet.ERR);
                shopCarRet.setMessage("订购数量小于起定量");
                return shopCarRet;
            }
            //阶梯价计算
            saleprice = ordersService.updatePriceByNum(convertNum, productStore, diliveryTime, info.getMemberid(), info.getLevel3id(), member.getGradleid());
        }
        shopCarRet.setResult(BasicRet.SUCCESS);
        shopCarRet.setMessage("返回成功");
        shopCarRet.data.saleprice = saleprice;
        return shopCarRet;
    }

    private class ShopCarListRet extends BasicRet {
        private class Data {
            private PageInfo pageInfo;

            public PageInfo getPageInfo() {
                return pageInfo;
            }

            public void setPageInfo(PageInfo pageInfo) {
                this.pageInfo = pageInfo;
            }
        }

        private Data data = new Data();

        public Data getData() {
            return data;
        }

        public void setData(Data data) {
            this.data = data;
        }
    }


    @PostMapping("/default/detail")
    @ApiOperation(value = "获取默认发票信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "memberid", value = "代客户下单的会员id", required = true, paramType = "query", dataType = "string"),
    })
    public BasicRet getInvoiceInfoDefault(Model model,long memberid) {
        BasicExtRet basicRet = new BasicExtRet();
        //Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        InvoiceInfo invoiceInfo = invoiceService.getDefaultInvoiceInfoByMemberId(memberid);
        basicRet.setData(invoiceInfo);
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("获取成功");
        return basicRet;
    }


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiOperation(value = "新增发票信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "memberid", value = "代客户下单的会员id", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "invoiceheadup", value = "发票开头", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "bankofaccounts", value = "开户行", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "texno", value = "税号", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "account", value = "账号", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "address", value = "发票地址", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "receiveaddress", value = "收票地址", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "linkman", value = "联系人", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "phone", value = "电话", required = true, paramType = "query", dataType = "string"),
    })
    public BasicRet addInvoiceInfo(InvoiceInfo invoiceInfo,Model model,long memberid) {
        BasicRet basicRet = new BasicRet();
        //Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        List<InvoiceInfo> invoiceInfos = invoiceService.getInvoiceInfoListByMemberId(memberid);
        Member member = memberService.selectById(memberid);
        if (invoiceInfos == null || invoiceInfos.size() == 0) {
            invoiceInfo.setDefaultbill((short) 1);
        } else {
            invoiceInfo.setDefaultbill((short) 0);
        }

        /*if (invoiceInfos.size() != 0 && member.getCompany()) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("公司账户新增失败");
        }else {*/
            invoiceInfo.setMemberid(member.getId());
            invoiceInfo.setCreatedate(new Date());
            invoiceInfo.setUpdatedate(new Date());
            invoiceInfo.setAvailable((short) 0);//
            invoiceService.addInvoiceInfo(invoiceInfo);
            basicRet.setResult(BasicRet.SUCCESS);
            basicRet.setMessage("新增成功");
     /*   }*/
        return basicRet;
    }

    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    @ApiOperation(value = "发票信息详情")
    @ApiImplicitParam(name = "invoiceId", value = "发票信息id", required = true, paramType = "query", dataType = "int")
    public BasicRet getInvoiceInfoDetail(@RequestParam(value = "invoiceId") Long invoiceId) {
        BasicExtRet basicRet = new BasicExtRet();
        InvoiceInfo invoiceInfo = invoiceService.getInvoiceInfoById(invoiceId);
        if (invoiceInfo == null) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("不存在该发票信息");
        } else {
            basicRet.setResult(BasicRet.SUCCESS);
            basicRet.setMessage("获取成功");
            basicRet.setData(invoiceInfo);
        }
        return basicRet;
    }

    @RequestMapping(value = "/list2", method = RequestMethod.POST)
    @ApiOperation(value = "发票信息列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "memberid", value = "代客户下单的会员id", required = true, paramType = "query", dataType = "string"),
    })
    public BasicRet getInvoiceInfoList2(long memberid) {
        //Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        Member member = memberService.selectById(memberid);
        BasicExtRet basicRet = new BasicExtRet();
        List<InvoiceInfo> invoiceInfoList = invoiceService.getInvoiceInfoListByMemberId(member.getId());
        basicRet.setData(invoiceInfoList);
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("获取成功");
        return basicRet;
    }




/*
    @RequestMapping(value = "/loadSelectProduct", method = RequestMethod.POST)
    @ApiOperation(value = "订单确认页面商品列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "memberid", value = "代客户下单的会员id", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "pdids", value = "如果单个商品购买就传一个id", required = false, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "pdno", value = "如果单个商品购买就传一个pdno", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "uProvince", value = "收货地址省", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "uCity", value = "收货地址市", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "pdnum", value = "针对单个商品数量", required = false, paramType = "query", dataType = "double"),
            @ApiImplicitParam(name = "pdunit", value = "针对单个商品单位", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "pddilivery", value = "针对单个商品发货时间", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "pdstoreid", value = "针对单个商品仓库id", required = false, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "type", value = "单个商品购买就传0，从购物车结算就传1", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "protype", value = "针对单个商品，远期类型0=不是远期1=全款2=定金", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "shopcarids", value = "购物车id", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "isonline", value = "订单类型0=普通订单1=线上2=限时购", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "limitid", value = "活动id", required = false, paramType = "query", dataType = "int"),
    })
    public ShopCarRet loadSelectProduct(Model model, String shopcarids, Long pdids, String pdno, String uProvince, String uCity, BigDecimal pdnum, String pdunit, String pddilivery, Long pdstoreid, int type, Short protype, @RequestParam(defaultValue = "0") Short isonline, Long limitid,Long memberid) {

        ShopCarRet shopCarRet = new ShopCarRet();
        shopCarRet.setMessage("返回成功");
        shopCarRet.setResult(BasicRet.SUCCESS);
        //Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        Member member = memberService.selectById(memberid);
        //立即购买
        if (type == Quantity.STATE_0) {
            List<Map<String, Object>> maplist = new ArrayList<Map<String, Object>>();
            Map<String, Object> map = new HashMap<String, Object>();
            maplist.add(map);
            ProductInfo productInfo = shopCarService.getProductInfo(pdids);
//            ProductStore productStore = shopCarService.getProductStore(pdids, pdno,pdstoreid);

            if(productInfo == null){
                shopCarRet.setMessage("");
                shopCarRet.setResult(BasicRet.ERR);
                return  shopCarRet;
            }


            ProductStore productStore = productStoreService.getByPdidAndPdno(pdids,pdno);




            Brand brand = shopCarService.getBrandById(productInfo.getBrandid());
            List<ProductAttr> productAttrs = productAttrService.getListByPidAndPdno(pdids,pdno);
            StringBuffer sb = new StringBuffer();
            for(ProductAttr attr:productAttrs){
                sb.append(attr.getValue()).append("*");
            }
            if(productAttrs.size()>0){
                sb.deleteCharAt(sb.length()-1);
            }
            if(isonline==Quantity.STATE_0){

                if (productInfo != null && productStore != null) {

                    //包装方式
                    String packagetype = productInfo.getPackagetype();

                    BigDecimal converNum = pdnum;
                    String convertUnit = "";
                    if (AppConstant.FASTENER_PRO_TYPE.equals(productInfo.getProducttype())) {//紧固件转换单位为基础单位的数量
                        converNum = (BigDecimal) (JinshangUtils.toLowerUnit(packagetype, pdnum, pdunit)).get("num");
                        convertUnit = (String) (JinshangUtils.toLowerUnit(packagetype, pdnum, pdunit)).get("unit");
                    }


                    if(productStore.getMinplus() != null && productStore.getMinplus().compareTo(Quantity.BIG_DECIMAL_0)>0){
                        if(!this.checkBuyNum(converNum,productStore.getMinplus())){
                            shopCarRet.setMessage("购买量必须是加购量的倍数");
                            shopCarRet.setResult(BasicRet.ERR);
                            return  shopCarRet;
                        }
                    }



                    if(AppConstant.FASTENER_PRO_TYPE.equals(productInfo.getProducttype())){
                        map.put("unit",convertUnit);
                        map.put("packageStr",JinshangUtils.packageToString(packagetype,converNum,convertUnit));
                    }else {
                        map.put("unit",pdunit);
                    }

                    map.put("pdstorenum",productStore.getPdstorenum());
                    map.put("storename", productStore.getStorename());
                    map.put("productname", productInfo.getProductname());
                    map.put("material", productInfo.getMaterial());
                    map.put("cardnum", productInfo.getCardnum());
                    map.put("weight", productInfo.getWeight());
                    map.put("packagetype", productInfo.getPackagetype());
                    map.put("selfsupport",productInfo.getSelfsupport());
                    map.put("level3",productInfo.getLevel3());

                    map.put("attrjson", sb.toString());
                    if (brand != null) {
                        if (brand.getPic() != null) {
                            map.put("pic", brand.getPic());
                        }
                    }
                    map.put("brand", productInfo.getBrand());
                    map.put("pdpicture", productInfo.getPdpicture());
                    map.put("freightmode", productStore.getFreightmode());
                    map.put("memberid", productInfo.getMemberid());
                    map.put("producttype", productInfo.getProducttype());
                    map.put("saleid", productInfo.getMemberid());
                    map.put("pdid", productInfo.getId());

                    //TODO
                    map.put("pdno", productStore.getPdno());
                    map.put("pdnumber", converNum);
                    map.put("storeid", pdstoreid);
                    map.put("delivertime", pddilivery);
                    map.put("protype", protype);

                    //计算价格
                    BigDecimal salePrice = ordersService.updatePriceByNum(converNum, productStore, pddilivery, productInfo.getMemberid(), productInfo.getLevel3id(), member.getGradleid());
//                    salePrice = salePrice.setScale(2, BigDecimal.ROUND_HALF_UP);
                    map.put("price", salePrice);

                    BigDecimal paymoney = salePrice.multiply(converNum);
                    //计算远期全款和定金
                    if (protype == Quantity.STATE_0) {
                        map.put("allpay", new BigDecimal(0));
                        map.put("partpay", new BigDecimal(0));
                        map.put("yupay", new BigDecimal(0));
                    }
                    //全款
                    if (protype == Quantity.STATE_1) {
                        map.put("allpay", paymoney.multiply(allPayRate));
                        map.put("partpay", new BigDecimal(0));
                        map.put("yupay", new BigDecimal(0));
                    }
                    //定金
                    if (protype == Quantity.STATE_2) {
                        map.put("allpay", new BigDecimal(0));
                        map.put("partpay", paymoney.multiply(transactionSettingService.getTransactionSetting().getRemotepurchasingmargin().multiply(new BigDecimal(0.01))));
                        map.put("yupay", paymoney.subtract(paymoney.multiply(transactionSettingService.getTransactionSetting().getRemotepurchasingmargin().multiply(new BigDecimal(0.01)))));
                    }

                    long freightmode = productStore.getFreightmode();
                    //商品重量
                    BigDecimal weight = productStore.getWeight();
                    BigDecimal totalweight = weight.multiply(converNum);


                    //计算运费
                    map.put("totalCost",freightService.getFreight(freightmode,totalweight,uProvince,uCity));
                }
            }else if(isonline==Quantity.STATE_2) {//限时购

                LimitTimeProd prod = shopCarService.getLimitTimeProd(pdids,limitid);

                //判断活动是否开始
                Date startTime = prod.getBegintime();
                Date endTime = prod.getEndtime();
                Date now = new Date();
                if (prod.getState() == Quantity.STATE_4) {
                    if (startTime.compareTo(now) == 1) {
                        shopCarRet.setResult(BasicRet.ERR);
                        shopCarRet.setMessage("活动还未开始");
                        return shopCarRet;
                    }
                    if (endTime.compareTo(now) == -1) {
                        shopCarRet.setResult(BasicRet.ERR);
                        shopCarRet.setMessage("活动已结束");
                        return shopCarRet;
                    }
                    Long sellerId = prod.getMemberid();
                    if (sellerId == member.getId()) {
                        shopCarRet.setMessage("不能购买自己的商品");
                        shopCarRet.setResult(BasicRet.ERR);
                        return shopCarRet;
                    }
                    List<ShopCar> list = shopCarService.getLimitShopCarNum(pdids, member.getId());
                    BigDecimal num = new BigDecimal(0);
                    for (ShopCar shopCar1 : list) {
                        num = num.add(shopCar1.getPdnumber());
                    }
                    num = num.add(pdnum);
                    if (prod.getBuylimit().compareTo(num) == -1) {
                        shopCarRet.setMessage("同一账号超过购买数量");
                        shopCarRet.setResult(BasicRet.ERR);
                        return shopCarRet;
                    }
                    LimitTimeStore limitTimeStore = shopCarService.getLimitTimeStore(limitid,pdids,pdno);
                    if(limitTimeStore.getStorenum().compareTo(pdnum)==-1){
                        shopCarRet.setMessage("超过库存数量");
                        shopCarRet.setResult(BasicRet.ERR);
                        return shopCarRet;
                    }

                    map.put("storename", productStore.getStorename());
                    map.put("pdstorenum", limitTimeStore.getStorenum());
                    map.put("productname", productInfo.getProductname());
                    map.put("material", productInfo.getMaterial());
                    map.put("cardnum", productInfo.getCardnum());
                    map.put("weight", productStore.getWeight());
                    map.put("packagetype", productInfo.getPackagetype());
                    map.put("selfsupport",productInfo.getSelfsupport());


                    map.put("price", limitTimeStore.getLimitprice());

                    map.put("brand", productInfo.getBrand());
                    map.put("pdpicture", productInfo.getPdpicture());
                    map.put("freightmode", -1l);
                    map.put("memberid", productInfo.getMemberid());
                    map.put("producttype", productInfo.getProducttype());
                    map.put("saleid", productInfo.getMemberid());
                    map.put("pdid", productInfo.getId());
                    map.put("pdno", pdno);
                    map.put("pdnumber", pdnum);
                    map.put("storeid", pdstoreid);
                    map.put("delivertime", pddilivery);
                    map.put("protype", protype);
                    map.put("allpay", new BigDecimal(0));
                    map.put("partpay", new BigDecimal(0));
                    map.put("yupay", new BigDecimal(0));
                    map.put("totalCost", new BigDecimal(0));
                    map.put("level3",productInfo.getLevel3());
                    map.put("limitid",limitid);

                    map.put("attrjson", sb.toString());

                    //包装方式
                    String packagetype = productInfo.getPackagetype();

                    BigDecimal converNum = pdnum;
                    String convertUnit = "";
                    if (AppConstant.FASTENER_PRO_TYPE.equals(productInfo.getProducttype())) {//紧固件转换单位为基础单位的数量
                        converNum = (BigDecimal) (JinshangUtils.toLowerUnit(packagetype, pdnum, pdunit)).get("num");
                        convertUnit = (String) (JinshangUtils.toLowerUnit(packagetype, pdnum, pdunit)).get("unit");
                    }

                    if(AppConstant.FASTENER_PRO_TYPE.equals(productInfo.getProducttype())){
                        map.put("unit",convertUnit);
                        map.put("packageStr",JinshangUtils.packageToString(packagetype,converNum,convertUnit));
                    }else {
                        map.put("unit",pdunit);
                    }

                    if(AppConstant.FASTENER_PRO_TYPE.equals(productInfo.getProducttype())) {
                        map.put("packageStr", JinshangUtils.packageToString(productInfo.getPackagetype(), pdnum, pdunit));
                    }

                }else {
                    shopCarRet.setMessage("商品不在活动中");
                    shopCarRet.setResult(BasicRet.ERR);
                    return shopCarRet;
                }
            }
            Store store = storeMapper.selectByPrimaryKey(productStore.getStoreid());
            map.put("storeAddress",store.getProvince()+store.getCity()+store.getCitysmall()+store.getAddress());
            shopCarRet.data.list = maplist;
            return shopCarRet;

        }else {

            List<Map<String, Object>> list = shopCarService.loadSelectProduct(shopcarids, member.getId());
            //计算运费
            for (Map<String, Object> map : list) {

                ProductInfo productInfo = shopCarService.getProductInfo((Long) map.get("pdid"));
                map.put("level3",productInfo.getLevel3());

                if(map.get("isonline") != null && (int)map.get("isonline") == 2){ //限时购，取限时购库存
                    LimitTimeStore limitTimeStore = shopCarService.getLimitTimeStore((Long) map.get("limitid"),(Long) map.get("pdid"),(String) map.get("pdno"));
                    map.put("pdstorenum",limitTimeStore.getStorenum());

                    map.put("totalCost", new BigDecimal(0));  //运费
                }else{
                    //运费模板id
                    long freightmode = (long) map.get("freightmode");
                    //购物车商品数量
                    BigDecimal converNum = (BigDecimal) map.get("pdnumber");
                    //商品重量
                    BigDecimal weight = (BigDecimal) map.get("weight");
                    BigDecimal totalweight = weight.multiply(converNum);


                    //计算运费
                    map.put("totalCost",freightService.getFreight(freightmode,totalweight,uProvince,uCity));
                }

                Store store = storeMapper.selectByPrimaryKey((Long) map.get("storeid"));
                map.put("storeAddress",store.getProvince()+store.getCity()+store.getCitysmall()+store.getAddress());
            }
            shopCarRet.data.list = list;
            return shopCarRet;
        }
    }

*/
    private class ShopCarRet extends BasicRet {
        private class Data {

            List<TransactionSetting> transactionSettings;
            private BigDecimal saleprice;

            private BigDecimal bigDecimal;

            private List<InvoiceInfo> invoiceInfos;

            public List<InvoiceInfo> getInvoiceInfos() {
                return invoiceInfos;
            }

            public void setInvoiceInfos(List<InvoiceInfo> invoiceInfos) {
                this.invoiceInfos = invoiceInfos;
            }

            public BigDecimal getBigDecimal() {
                return bigDecimal;
            }

            public void setBigDecimal(BigDecimal bigDecimal) {
                this.bigDecimal = bigDecimal;
            }

            private List<Map<String, Object>> list;

            public List<Map<String, Object>> getList() {
                return list;
            }

            public void setList(List<Map<String, Object>> list) {
                this.list = list;
            }

            public BigDecimal getSaleprice() {
                return saleprice;
            }

            public void setSaleprice(BigDecimal saleprice) {
                this.saleprice = saleprice;
            }

            public List<TransactionSetting> getTransactionSettings() {
                return transactionSettings;
            }

            public void setTransactionSettings(List<TransactionSetting> transactionSettings) {
                this.transactionSettings = transactionSettings;
            }
        }

        private Data data = new Data();

        public Data getData() {
            return data;
        }

        public void setData(Data data) {
            this.data = data;
        }
    }


    /**
     * 计算运费
     *
     * @param defaultfreight   默认运费公斤
     * @param defaultcost      默认运费
     * @param perkilogramadded 每增加公斤
     * @param perkilogramcost  每增加运费
     * @param totalweight      商品总重量
     * @return 每个商品的运费
     */
    private BigDecimal getTotalCost(BigDecimal defaultfreight, BigDecimal defaultcost, BigDecimal perkilogramadded, BigDecimal perkilogramcost, BigDecimal totalweight) {

        BigDecimal totalCost = new BigDecimal(0);
        //判断重量计算运费
        //如果小于或等于默认重量
        if (totalweight.compareTo(defaultfreight) == -1 || totalweight.compareTo(defaultfreight) == 0) {
            totalCost = defaultcost;
        } else {
            //剩余的重量
            BigDecimal substractWeight = totalweight.subtract(defaultfreight);
            //倍数和余数
            BigDecimal[] results = substractWeight.divideAndRemainder(perkilogramadded);
            //如果剩下的重量小于每增加公斤数
            if (results[0].compareTo(new BigDecimal(0)) == Quantity.INT_0) {
                totalCost = defaultcost.add(perkilogramcost);
            } else {
                BigDecimal multiplycost = results[0].multiply(perkilogramcost);
                //如果没有余数
                if (results[1].compareTo(new BigDecimal(0)) == Quantity.INT_0) {
                    totalCost = multiplycost.add(defaultcost);
                } else {
                    //如果有余数
                    totalCost = multiplycost.add(perkilogramcost).add(defaultcost);
                }
            }
        }
        return totalCost;
    }




    /**
     * 从购物车中买家提交订单
     *
     * @param model
     * @param billingRecord
     * @param orders
     * @param ids
     * @return
     */
    /*
    @RequestMapping(value = "/submitOrders", method = RequestMethod.POST)
    @ApiOperation(value = "从购物车中买家提交订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "memberid", value = "会员id", required = true, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "invoiceid", value = "发票id", required = true, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "shipto", value = "收货人", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "phone", value = "收货人电话", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "province", value = "省", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "city", value = "市", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "area", value = "区", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "receivingaddress", value = "详细地址", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "isbilling", value = "是否开票0=不开票1=开票", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "billingtype", value = "开票类型0=纸质1=电子", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "ids", value = "选择的购物车id数组", required = true, paramType = "query", dataType = "array"),
            @ApiImplicitParam(name = "isonline", value = "订单类型0=普通订单1=线下2=限时购", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "deliverybill", value = "是否需要发货单，1-需要，0-不需要", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "mailornotPidArray", value = "需要自提的商品id json数组字符串，如[1,2]", required = true, paramType = "query", dataType = "string"),
    })
    public BasicRet submitOrders(
            Model model, BillingRecord billingRecord,
            Orders orders, String ids, String mailornotPidArray,
            HttpServletRequest request,Long memberid) throws Exception {

        OrderCarRet basicRet = new OrderCarRet();
        //Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        Member member = memberService.selectById(memberid);
        List<ShopCar> list = ordersService.loadSelectShopCar(ids);
        if (orders.getIsonline() == null) {
            orders.setIsonline(Quantity.STATE_0);
        }
        //删除购物车的商品
        String[] arrayIds = ids.split(",");
        List<Long> longIds = new ArrayList<Long>();
        for (String id : arrayIds) {
            longIds.add(Long.parseLong(id));
        }

        List<Long> mailornotPids = (List<Long>) JsonUtil.toObject(mailornotPidArray, List.class);

        //定义订单拆分map
        Map<String, List<ShopCar>> opMap = new TreeMap<String, List<ShopCar>>();
        List<Orders> ordersList = new ArrayList<Orders>();


        // final Map<String, List<ShopCar>> opMap1 =


        //---------------创建商品订单记录,按仓库按发货时间按远期付款类型拆分订单-------------------------------
        for (ShopCar shopCar : list) {
            long pdid = shopCar.getPdid();
            String pdno = shopCar.getPdno();
            long storeid = shopCar.getStoreid();
            BigDecimal convertNum = shopCar.getPdnumber();

            orders.setIsonline(shopCar.getIsonline());

            if (orders.getIsonline() == Quantity.STATE_0) {
                //更新库存
                ProductStore productStore = ordersService.getProductStore(pdid, pdno, storeid);
                if (productStore != null) {
                    BigDecimal storeNum = productStore.getPdstorenum();
                    if (convertNum.compareTo(storeNum) == Quantity.STATE_1) {
                        throw new RuntimeException("库存不足");
                    }


                    if (productStore.getMinplus() != null && productStore.getMinplus().compareTo(Quantity.BIG_DECIMAL_0) > 0) {
                        if (!this.checkBuyNum(convertNum, productStore.getMinplus())) {
                            throw new RuntimeException("购买量必须是加购量的倍数");
                        }
                    }


                    //减库存操作
                    productStore.setPdstorenum(productStore.getPdstorenum().subtract(convertNum));
                    ordersService.updateProductStore(productStore);
                    //发货时间
                    String deliveryTime = shopCar.getDelivertime();
                    int protype = shopCar.getProtype();
                    Short producttype = shopCar.getProducttype();
                    //根据仓库id和发货时间判断是否属于同一订单
                    String key = storeid + deliveryTime + protype + producttype + shopCar.getIsonline();
                    if (opMap.containsKey(key)) {
                        List<ShopCar> oplist = opMap.get(key);
                        oplist.add(shopCar);
                    } else {
                        List<ShopCar> optemplist = new ArrayList<ShopCar>();
                        optemplist.add(shopCar);
                        opMap.put(key, optemplist);
                    }
                } else {
//                    basicRet.setResult(BasicRet.ERR);
//                    basicRet.setMessage("没有该商品");
//                    return basicRet;
                    throw new RuntimeException("没有该商品");
                }
            }
            //限时购
            if (orders.getIsonline() == Quantity.STATE_2) {

                LimitTimeProd limitTimeProd = limitTimeProdService.getById(shopCar.getLimitid());

                //判断活动是否开始
                Date startTime = limitTimeProd.getBegintime();
                Date endTime = limitTimeProd.getEndtime();
                Date now = new Date();
                if (limitTimeProd.getState() == Quantity.STATE_4) {
                    if (startTime.compareTo(now) == 1) {
                        throw new Exception("活动还未开始");
                    }
                    if (endTime.compareTo(now) == -1) {
                        throw new Exception("活动已结束");
                    }
                    Long sellerId = limitTimeProd.getMemberid();
                    if (sellerId == member.getId()) {
                        throw new Exception("不能购买自己的商品");
                    }
                } else {
                    throw new Exception("活动未开始");
                }


                LimitTimeStore limitTimeStore = shopCarService.getLimitTimeStore(shopCar.getLimitid(), shopCar.getPdid(), shopCar.getPdno());
                //更新库存
                limitTimeStoreService.updateLimitStoreNum(limitTimeStore.getId(), shopCar.getPdnumber().multiply(new BigDecimal(-1)), shopCar.getPdnumber());
                LimitTimeStore newLimitStore = limitTimeStoreService.selectByPrimaryKey(limitTimeStore.getId());
                if (newLimitStore == null || newLimitStore.getStorenum().compareTo(new BigDecimal(0)) == -1) {
                    throw new Exception("库存不足");
                }
                limitTimeProdService.updateSalestotalnumInDB(shopCar.getLimitid(), shopCar.getPdnumber());


                //发货时间
                String deliveryTime = shopCar.getDelivertime();
                int protype = shopCar.getProtype();
                Short producttype = shopCar.getProducttype();
                //根据仓库id和发货时间判断是否属于同一订单
                String key = storeid + deliveryTime + protype + producttype + shopCar.getIsonline();
                if (opMap.containsKey(key)) {
                    List<ShopCar> oplist = opMap.get(key);
                    oplist.add(shopCar);
                } else {
                    List<ShopCar> optemplist = new ArrayList<ShopCar>();
                    optemplist.add(shopCar);
                    opMap.put(key, optemplist);
                }
            }
        }

        Short isonline = 0;
        //----------------------遍历拆分后的订单，生成不同的订单编号-------------------------------------
        if (opMap.size() > Quantity.INT_0) {
            //生成不同的订单
            for (String key : opMap.keySet()) {
                List<ShopCar> shopCarList = opMap.get(key);
                String orderNo = GenerateNo.getOrderNo();
                //订单总运费
                BigDecimal fight = BigDecimal.valueOf(0);
                //订单总价（包含运费）
                BigDecimal totalPrice = BigDecimal.valueOf(0);
                //订单总定金
                BigDecimal deposit = BigDecimal.valueOf(0);
                //订单总余款
                BigDecimal balance = BigDecimal.valueOf(0);
                //订单总全款
                BigDecimal orderAllpay = BigDecimal.valueOf(0);
                List<OrderProduct> orderProductsList = new ArrayList<OrderProduct>();
                for (ShopCar shop : shopCarList) {

                    isonline = shop.getIsonline();

                    OrderProduct op = new OrderProduct();
                    op.setOrderno(orderNo);
                    op.setPdid(shop.getPdid());
                    op.setPdno(shop.getPdno());
                    op.setAttrjson(shop.getAttrjson());
                    op.setSellerid(shop.getSaleid());
                    op.setDeliverytime(shop.getDelivertime());
                    ProductInfo productInfo = ordersService.getProductInfoByPrimeKey(shop.getPdid());

                    if (productInfo == null) {
                        throw new RuntimeException("商品id为" + shop.getPdid() + "的商品不存在");
                    }

                    if (productInfo.getPdstate() != Quantity.STATE_4) {
                        throw new RuntimeException(productInfo.getProductname() + "的商品现在处于下架状态，不可购买");
                    }

                    Brand brand = shopCarService.getBrandById(productInfo.getBrandid());
                    op.setPdname(productInfo.getProductname());
                    op.setPddesc(productInfo.getPackagetype());
                    if (productInfo.getProducttype().equals("紧固件")) {
                        op.setProducttype(Quantity.STATE_1);
                    } else {
                        op.setProducttype(Quantity.STATE_2);
                    }
                    String[] strpic = (String[]) productInfo.getPdpicture();
                    if (strpic != null) {
                        if (strpic.length > 0) {
                            op.setPdpic(strpic[0]);
                        } else {
                            op.setPdpic("");
                        }
                    } else {
                        op.setPdpic("");
                    }

                    if (brand != null) {
                        if (brand.getPic() != null) {
                            op.setPic(brand.getPic());
                        } else {
                            op.setPic("");
                        }
                    } else {
                        op.setPic("");
                    }
                    op.setPrice(shop.getPrice());
                    op.setUnit(shop.getUnit());
                    op.setNum(shop.getPdnumber());
                    op.setStoreid(shop.getStoreid());
                    op.setStorename(shop.getStorename());
                    //是否包邮
                    if (shop.getFrightmode() == Quantity.STATE_) {
                        op.setMailornot(true);
                    } else {
                        op.setMailornot(false);
                    }
                    if (StringUtils.hasText(productInfo.getStand())) {
                        op.setStandard(productInfo.getStand());
                    }
                    if (StringUtils.hasText(productInfo.getMark())) {
                        op.setMark(productInfo.getMark());
                    }
                    if (StringUtils.hasText(productInfo.getBrand())) {
                        op.setBrand(productInfo.getBrand());
                    }
                    if (StringUtils.hasText(productInfo.getMaterial())) {
                        op.setMaterial(productInfo.getMaterial());
                    }

                    if (StringUtils.hasText(productInfo.getLevel3())) {
                        op.setClassify(productInfo.getLevel3());
                        op.setClassifyid(productInfo.getLevel3id());
                    } else if (StringUtils.hasText(productInfo.getLevel2())) {
                        op.setClassify(productInfo.getLevel2());
                        op.setClassifyid(productInfo.getLevel2id());
                    } else {
                        op.setClassify(productInfo.getLevel1());
                        op.setClassifyid(productInfo.getLevel1id());
                    }


                    if (StringUtils.hasText(productInfo.getCardnum())) {
                        op.setGradeno(productInfo.getCardnum());
                    }
                    if (shop.getLimitid() != null) {
                        op.setLimitid(shop.getLimitid());
                    }
                    op.setIsmailornot((short) 0);
                    // 设置是否自提
                    if (mailornotPids != null && mailornotPids.contains(op.getPdid())) {
                        op.setIsmailornot((short) 1);
                    }
                    //设置远期支付方式
                    op.setProtype(shop.getProtype());
                    op.setAllpay(shop.getAllpay());
                    op.setPartpay(shop.getPartpay());
                    op.setYupay(shop.getYupay());
                    ProductStore productStore1 = ordersService.getProductStore(shop.getPdid(), shop.getPdno(), shop.getStoreid());
                    //计算运费
                    BigDecimal figtht = BigDecimal.valueOf(0);
                    if (orders.getIsonline() == Quantity.STATE_0) {
                        //figtht = ordersService.countSinglePdFight(productInfo, productStore1, orders.getProvince(), orders.getCity(), shop.getPdnumber());
                        figtht = freightService.getFreight(productStore1.getFreightmode(),productStore1.getWeight().multiply(shop.getPdnumber()),orders.getProvince(),orders.getCity());
                        op.setFreight(figtht);
                    }
                    if (orders.getIsonline() == Quantity.STATE_2) {
                        op.setFreight(BigDecimal.valueOf(0));
                    }

                    if (shop.getProtype() != Quantity.STATE_1) {
                        op.setActualpayment(figtht.add(shop.getPrice().multiply(shop.getPdnumber())));
                    } else {
                        op.setActualpayment(figtht.add(shop.getAllpay()));
                    }
                    fight = fight.add(figtht);
                    orderProductsList.add(op);
                    //计算订单总价
                    BigDecimal tempTotalPrice = BigDecimal.valueOf(0);
                    //不是远期全款
                    if (shop.getProtype() == Quantity.STATE_0 || shop.getProtype() == Quantity.STATE_2) {
                        tempTotalPrice = shop.getPrice().multiply(shop.getPdnumber());
                        deposit = deposit.add(shop.getPartpay());
                        balance = balance.add(shop.getYupay());
                    }
                    //远期全款
                    if (shop.getProtype() == Quantity.STATE_1) {
                        tempTotalPrice = shop.getAllpay();
                        orderAllpay = orderAllpay.add(shop.getAllpay());
                    }
                    tempTotalPrice = tempTotalPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
                    totalPrice = totalPrice.add(tempTotalPrice).add(figtht);

                    //ordersService.insertOrderProduct(op);

                    List<ProductAttr> productAttrs = productAttrService.getListByPidAndPdno(productInfo.getId(), op.getPdno());
                    OrderProductLog orderProductLog = new OrderProductLog();
                    orderProductLog.setProductinfojson(GsonUtils.toJson(productInfo));
                    orderProductLog.setProductstorejson(GsonUtils.toJson(productStore1));
                    orderProductLog.setProductattrjson(GsonUtils.toJson(productAttrs));

                    op.getExtend().put("OrderProductLog", orderProductLog);

                }

                //----------------------------创建订单---------------------------------------
                OrderProduct orderProduct = orderProductsList.get(0);
                Orders order = new Orders();
                order.setIsonline(isonline);
                order.setProvince(orders.getProvince());
                order.setCity(orders.getCity());
                order.setArea(orders.getArea());
                order.setShipto(orders.getShipto());
                order.setPhone(orders.getPhone());
                order.setReceivingaddress(orders.getReceivingaddress());
                order.setIsbilling(orders.getIsbilling());
                order.setBillingtype(orders.getBillingtype());
                order.setDeliverybill(orders.getDeliverybill());
                //添加:2018年6月8日 订单添加介绍人
                order.setWaysalesman(member.getWaysalesman());
                //查询是否有业务员
                AdminUser AdminUser = adminUserService.getAdminUserByUserid(member.getId());
                if (AdminUser != null) {
                    //添加业务员/业务员联系方式
                    Admin admin = adminService.getById(AdminUser.getAdminid());
                    if(admin !=null){
                        order.setClerkname(admin.getRealname());
                        order.setClerknamephone(admin.getMobile());
                    }
                }

                //订单总运费
                order.setFreight(fight);
                //订单总价
                totalPrice = totalPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
                order.setTotalprice(totalPrice);
                //实付款
                order.setActualpayment(totalPrice);
                order.setDeposit(deposit);
                order.setBalance(balance);
                order.setAllpay(orderAllpay);
                //买家id
                order.setMemberid(member.getId());
                order.setMembername(member.getUsername());
                Member member1 = memberService.getMemberById(orderProduct.getSellerid());
                if (member1 != null) {
                    order.setSellername(member1.getUsername());
                }
                //卖家id
                order.setSaleid(orderProduct.getSellerid());
                // 获取公司名称
                SellerCompanyInfo sellerCompanyInfo = sellerCompanyInfoService.getSellerCompanyByMemberid(orderProduct.getSellerid());
                if (sellerCompanyInfo == null) {
                    throw new RuntimeException("卖家不存在");
                }

                order.setMembercompany(sellerCompanyInfo.getCompanyname());
                order.setShopname(sellerCompanyInfo.getShopname());
                //平台代发模式
                if (sellerCompanyInfo.getDeliverymode() == Quantity.STATE_1) {
                    order.setDeliverytype(Quantity.STATE_1);
                }


                //计算远期发货时间
                Date futuretime = getFutureTime(orderProduct);
                if (futuretime != null) {
                    order.setFuturetime(futuretime);
                }
                //订单编号
                order.setOrderno(orderNo);
                order.setTransactionnumber(GenerateNo.getTransactionNo());
                //合同编号
                order.setCode(GenerateNo.getContractNo());
                //仓库id
                order.setStoreid(orderProduct.getStoreid());
                //仓库名称
                order.setStorename(orderProduct.getStorename());
                if (orderProduct.getProtype() == Quantity.STATE_0) {
                    order.setOrdertype(Quantity.STATE_0);
                }
                if (orderProduct.getProtype() == Quantity.STATE_1) {
                    order.setOrdertype(Quantity.STATE_1);
                }
                if (orderProduct.getProtype() == Quantity.STATE_2) {
                    order.setOrdertype(Quantity.STATE_2);
                }
                order.setCreatetime(new Date());

                //保存订单
                ordersService.insertOrders(order);
                for (OrderProduct orderProduct1 : orderProductsList) {
                    orderProduct1.setOrderid(order.getId());

                    ordersService.insertOrderProduct(orderProduct1);

                    OrderProductLog orderProductLog = (OrderProductLog) orderProduct1.getExtend().get("OrderProductLog");
                    orderProductLog.setOrderproductid(orderProduct1.getId());
                    orderProductLogService.add(orderProductLog);

                }
                //批量保存产品订单表
                //ordersService.insertAllOrderProduct(orderProductsList);


                ordersList.add(order);

                //操作日志
                OperateLog operateLog = new OperateLog();
                operateLog.setContent("提交订单");
                operateLog.setOpid(member.getId());
                operateLog.setOpname(member.getUsername());
                operateLog.setOptime(new Date());
                operateLog.setOptype(Quantity.STATE_0);
                operateLog.setOrderid(order.getId());
                operateLog.setOrderno(order.getOrderno());
                ordersService.saveOperatelog(operateLog);
            }
            //如果选择了开票
            if (orders.getIsbilling() != Quantity.STATE_0) {
                if (billingRecord.getInvoiceid() != null) {
                    InvoiceInfo invoiceInfo = ordersService.getInvoiceInfo(billingRecord.getInvoiceid());
                    if (invoiceInfo != null) {
                        StringBuffer sb = new StringBuffer();
                        BigDecimal billCach = BigDecimal.valueOf(0);
                        List<BillOrder> billOrders = new ArrayList<>();
//                        for (Orders orders1 : ordersList) {
//                            sb.append(orders1.getId()).append(",");
//                            billCach = billCach.add((orders1.getTotalprice().subtract(orders1.getFreight())));
//
//                            BillOrder billOrder = new BillOrder();
//                            billOrder.setOrderid(orders1.getId());
//                            billOrders.add(billOrder);
//                        }
//                        //创建开票记录
//                        billingRecord.setInvoiceheadup(invoiceInfo.getInvoiceheadup());
//                        billingRecord.setTexno(invoiceInfo.getTexno());
//                        billingRecord.setBankofaccounts(invoiceInfo.getBankofaccounts());
//                        billingRecord.setAccount(invoiceInfo.getAccount());
//                        billingRecord.setPhone(invoiceInfo.getPhone());
//                        billingRecord.setAddress(invoiceInfo.getAddress());
//                        billingRecord.setMemberid(member.getId());
//                        billingRecord.setMembername(member.getUsername());
//                        billingRecord.setRemark(invoiceInfo.getAvailable());
//                        billingRecord.setOrderno(sb.toString());
//                        billingRecord.setBillcash(billCach);
//                        billingRecord.setBillingrecordtype(orders.getBillingtype());
//                        ordersService.insertBillingRecord(billingRecord);
//
//                        for (BillOrder billOrder : billOrders) {
//                            billOrder.setBillrecordid(billingRecord.getId());
//                        }
//                        ordersService.insertAll(billOrders);


                        for (Orders orders1 : ordersList) {
                            //创建开票记录
                            billingRecord.setInvoiceheadup(invoiceInfo.getInvoiceheadup());
                            billingRecord.setTexno(invoiceInfo.getTexno());
                            billingRecord.setBankofaccounts(invoiceInfo.getBankofaccounts());
                            billingRecord.setAccount(invoiceInfo.getAccount());
                            billingRecord.setPhone(invoiceInfo.getPhone());
                            billingRecord.setAddress(invoiceInfo.getAddress());
                            billingRecord.setMemberid(member.getId());
                            billingRecord.setMembername(member.getUsername());
                            billingRecord.setRemark(invoiceInfo.getAvailable());
                            billingRecord.setOrderno(orders1.getId().toString());
                            billingRecord.setBillcash(orders1.getTotalprice());
                            billingRecord.setBillingrecordtype(orders.getBillingtype());
                            billingRecord.setReceiveaddress(invoiceInfo.getReceiveaddress());

                            //提前创建出发票信息，等该笔订单交易成功时设置为0
                            billingRecord.setState(Quantity.STATE_);
                            billingRecord.setId(null);
                            ordersService.insertBillingRecord(billingRecord);

                            BillOrder billOrder = new BillOrder();
                            billOrder.setBillrecordid(billingRecord.getId());
                            billOrder.setOrderid(orders1.getId());
                            ordersService.saveBillOrder(billOrder);
                        }
                    }
                }
            }
        }


        //删除购物车
        shopCarService.deleteAll(longIds);

        List<String> ordernoList = new ArrayList<>();
        ordersList.forEach(o -> ordernoList.add(o.getOrderno()));
        ordersService.jisuanOrdersBreakPay(ordernoList);


        //批量保存订单
        //ordersService.insertAllOrders(ordersList);
        memberLogOperator.saveMemberLog(member, null, "提交订单", "/rest/buyer/orders/submitOrders", request, memberOperateLogService);
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("提交成功");
        basicRet.data.ordersList = ordersList;
        return basicRet;
    }
*/

    private class OrderCarRet extends BasicRet {
        private class Data {
            private BuyerCenterModel buyerCenterModel;
            List<TransactionSetting> transactionSettings;
            private SellerBillRecord sellerBillRecord;
            private List<Orders> ordersList;
            private EvaPageModel evaPageModel;
            private OrderProductBack orderProductBack;

            private List<ShippingAddress> shippingAddresses;

            private BigDecimal bigDecimal;

            private Orders orders;

            private List<OrderProduct> orderProducts;

            private OrderProduct orderProduct;

            private OrderProduct orderProduct1;

            private BillingRecord billingRecord;

            private BillRecordComplex billRecordComplex;

            private Map<String, BigDecimal> map;

            private List<OperateLog> operateLogs;

            private BigDecimal integralRecord;

            private boolean selfsupport;

            private String expressurl;

            public String getExpressurl() {
                return expressurl;
            }

            public void setExpressurl(String expressurl) {
                this.expressurl = expressurl;
            }

            public boolean isSelfsupport() {
                return selfsupport;
            }

            public void setSelfsupport(boolean selfsupport) {
                this.selfsupport = selfsupport;
            }

            public OrderProduct getOrderProduct1() {
                return orderProduct1;
            }

            public void setOrderProduct1(OrderProduct orderProduct1) {
                this.orderProduct1 = orderProduct1;
            }

            public BigDecimal getIntegralRecord() {
                return integralRecord;
            }

            public void setIntegralRecord(BigDecimal integralRecord) {
                this.integralRecord = integralRecord;
            }

            public BuyerCenterModel getBuyerCenterModel() {
                return buyerCenterModel;
            }

            public void setBuyerCenterModel(BuyerCenterModel buyerCenterModel) {
                this.buyerCenterModel = buyerCenterModel;
            }

            public EvaPageModel getEvaPageModel() {
                return evaPageModel;
            }

            public void setEvaPageModel(EvaPageModel evaPageModel) {
                this.evaPageModel = evaPageModel;
            }

            public List<OperateLog> getOperateLogs() {
                return operateLogs;
            }

            public void setOperateLogs(List<OperateLog> operateLogs) {
                this.operateLogs = operateLogs;
            }

            public Map<String, BigDecimal> getMap() {
                return map;
            }

            public void setMap(Map<String, BigDecimal> map) {
                this.map = map;
            }

            public BillRecordComplex getBillRecordComplex() {
                return billRecordComplex;
            }

            public void setBillRecordComplex(BillRecordComplex billRecordComplex) {
                this.billRecordComplex = billRecordComplex;
            }

            public BillingRecord getBillingRecord() {
                return billingRecord;
            }

            public void setBillingRecord(BillingRecord billingRecord) {
                this.billingRecord = billingRecord;
            }

            public OrderProduct getOrderProduct() {
                return orderProduct;
            }

            public void setOrderProduct(OrderProduct orderProduct) {
                this.orderProduct = orderProduct;
            }

            public List<OrderProduct> getOrderProducts() {
                return orderProducts;
            }

            public void setOrderProducts(List<OrderProduct> orderProducts) {
                this.orderProducts = orderProducts;
            }

            public Orders getOrders() {
                return orders;
            }

            public void setOrders(Orders orders) {
                this.orders = orders;
            }

            public BigDecimal getBigDecimal() {
                return bigDecimal;
            }

            public void setBigDecimal(BigDecimal bigDecimal) {
                this.bigDecimal = bigDecimal;
            }

            public List<ShippingAddress> getShippingAddresses() {
                return shippingAddresses;
            }

            public void setShippingAddresses(List<ShippingAddress> shippingAddresses) {
                this.shippingAddresses = shippingAddresses;
            }

            private int orderNum;

            public int getOrderNum() {
                return orderNum;
            }

            public void setOrderNum(int orderNum) {
                this.orderNum = orderNum;
            }

            public List<Orders> getOrdersList() {
                return ordersList;
            }

            public void setOrdersList(List<Orders> ordersList) {
                this.ordersList = ordersList;
            }

            public OrderProductBack getOrderProductBack() {
                return orderProductBack;
            }

            public void setOrderProductBack(OrderProductBack orderProductBack) {
                this.orderProductBack = orderProductBack;
            }

            public SellerBillRecord getSellerBillRecord() {
                return sellerBillRecord;
            }

            public void setSellerBillRecord(SellerBillRecord sellerBillRecord) {
                this.sellerBillRecord = sellerBillRecord;
            }

            public List<TransactionSetting> getTransactionSettings() {
                return transactionSettings;
            }

            public void setTransactionSettings(List<TransactionSetting> transactionSettings) {
                this.transactionSettings = transactionSettings;
            }
        }

        private Data data = new Data();

        public Data getData() {
            return data;
        }

        public void setData(Data data) {
            this.data = data;
        }
    }

    /**
     * 计算远期发货时间
     *
     * @param orderProduct
     * @return
     */
    private Date getFutureTime(@NotNull OrderProduct orderProduct) {
        Date futuretime = null;
        //计算远期发货时间
        if (!orderProduct.getDeliverytime().equals("立即发货")) {
            Calendar now = Calendar.getInstance();
            now.setTime(new Date());
            if (orderProduct.getDeliverytime().equals("3天后发货")) {
                now.set(Calendar.DATE, now.get(Calendar.DATE) + 3);
                futuretime = now.getTime();
            }
            if (orderProduct.getDeliverytime().equals("15天后发货")) {
                now.set(Calendar.DATE, now.get(Calendar.DATE) + 15);
                futuretime = now.getTime();
            }
            if (orderProduct.getDeliverytime().equals("30天后发货")) {
                now.set(Calendar.DATE, now.get(Calendar.DATE) + 30);
                futuretime = now.getTime();
            }
            if (orderProduct.getDeliverytime().equals("60天后发货")) {
                now.set(Calendar.DATE, now.get(Calendar.DATE) + 60);
                futuretime = now.getTime();
            }
        }
        return futuretime;
    }



    @RequestMapping(value = "/getMemberInfo", method = RequestMethod.POST)
    @ApiOperation(value = "搜索用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "会员id",required = false,paramType = "query",dataType = "Long",defaultValue = "0"),
            @ApiImplicitParam(name = "username",value = "会员名",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "companyname",value = "公司名",required = false,paramType = "query",dataType = "string"),
    })
    public BasicRet getMemberInfoDefault(String username,Long id,String companyname,Model model) {
        Admin admin = (Admin) model.asMap().get(AppConstant.ADMIN_SESSION_NAME);
        BasicExtRet basicRet = new BasicExtRet();
        List<Map<String,Object>> list = memberService.getMemberInfoByUserName(username,admin.getRealname(),id,companyname);
        basicRet.setData(list);
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("获取成功");
        return basicRet;
    }


    @RequestMapping(value = "/addShippingAddress",method = RequestMethod.POST)
    @ApiOperation(value = "买家添加收货地址")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "memberid",value = "代下单会员id",required = true,paramType = "query"  ,dataType = "long"),
            @ApiImplicitParam(name = "shipto",value = "收货人",required = true,paramType = "query"  ,dataType = "string"),
            @ApiImplicitParam(name = "regionid",value = "地区代码",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "province",value = "省",required = true,paramType = "query"  ,dataType = "string"),
            @ApiImplicitParam(name = "city",value = "市",required = true,paramType = "query"  ,dataType = "string"),
            @ApiImplicitParam(name = "citysmall",value = "区",required = true,paramType = "query"  ,dataType = "string"),
            @ApiImplicitParam(name = "address",value = "地址",required = true,paramType = "query"  ,dataType = "string"),
            @ApiImplicitParam(name = "zipcode",value = "邮编",required = true,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "phone",value = "手机号码",required = true,paramType = "query"  ,dataType = "int"),
            @ApiImplicitParam(name = "isdefault",value = "是否默认使用该地址{0:不是,1:是}",required = true,paramType = "query",dataType = "int"),
    })
    public BasicRet addBuyerShippingAddress(ShippingAddress shippingAddress,Model model,long memberid) {
        BasicRet basicRet = new BasicRet();
       // Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        Member member = memberService.selectById(memberid);


        ShippingAddressExample example =  new ShippingAddressExample();
        ShippingAddressExample.Criteria criteria = example.createCriteria();
        criteria.andMemberidEqualTo(member.getId()).andTypeEqualTo(Quantity.STATE_2);
        int count =  shippingAddressService.countByExample(example);
            /*if(count>=10){
                return  new BasicRet(BasicRet.ERR,"最多可添加10条");
            }*/

        if(shippingAddress.getIsdefault() == Quantity.STATE_1){  //如果设置为默认，首先全部取消默认
            shippingAddressService.upateAllToNotDefault(member.getId(),Quantity.STATE_2);
        }else{
            ShippingAddress defaultAddress =  shippingAddressService.getDefaultShippingAddress(member.getId(),Quantity.STATE_2);
            if(defaultAddress == null){  //如果不存在默认的，将此设置为默认的
                shippingAddress.setIsdefault(Quantity.STATE_1);
            }
        }

        shippingAddress.setMemberid(member.getId());
        shippingAddress.setType(Quantity.STATE_2);
        shippingAddressService.addShippingAddress(shippingAddress);

        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("添加成功");
        return basicRet;
    }



    @PostMapping("/listShippingAddress")
    @ApiOperation("列出买家收货地址列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "memberid",value = "代下单会员id",required = true,paramType = "query"  ,dataType = "long"),
    })
    public PageRet  listBuyerShippingAddress(@RequestParam(required = true,defaultValue = "1") int pageNo,
                                             @RequestParam(required = true,defaultValue = "10")  int pageSize, Model model,long memberid){
        PageRet pageRet = new PageRet();
        //Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        Member member = memberService.selectById(memberid);
        PageInfo pageInfo = shippingAddressService.listAllShippingAddress(pageNo,pageSize,member.getId(),Quantity.STATE_2);
        pageRet.data.setPageInfo(pageInfo);
        pageRet.setResult(BasicRet.SUCCESS);
        return  pageRet;
    }


    @RequestMapping(value =  "/listFastenerProduct",method = RequestMethod.POST)
    @ApiOperation("紧固件商品分页列表查询")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "第几页",name = "pageNo",paramType = "query",dataType = "int",required = true,defaultValue = "1"),
            @ApiImplicitParam(value = "每页显示的条数",name = "pageSize",paramType = "query",dataType = "int",required = true,defaultValue = "20"),
            @ApiImplicitParam(value = "商品名",name = "productname",paramType = "query",dataType = "string",required = false),
            @ApiImplicitParam(value = "品牌",name = "brand",paramType = "query",dataType = "string",required = false),
            @ApiImplicitParam(value = "分类id",name = "levelid",paramType = "query",dataType = "int",required = false,defaultValue = "0"),
            @ApiImplicitParam(value = "是否有库存,0-全部,1-有，2-没有",name = "haveStorenum",paramType = "query",dataType = "int",required = false,defaultValue = "0"),
            @ApiImplicitParam(value = "商品状态 0=放入仓库 1=待审核(立即发布) 2=审核通过 3=未通过 4=已上架(上架) 5=下架 6=删除 7-违规下架",name = "pdstate",paramType = "query",dataType = "int",required = false,defaultValue = "-1"),
            @ApiImplicitParam(value = "材质id",name = "materialid",paramType = "query",dataType = "int",required = false,defaultValue = "-1"),
            @ApiImplicitParam(value = "牌号id",name = "cardnumid",paramType = "query",dataType = "int",required = false,defaultValue = "-1"),
            @ApiImplicitParam(value = "卖家id",name = "memberid",paramType = "query",dataType = "int",required = false,defaultValue = "0"),
            @ApiImplicitParam(value = "用户名",name = "username",paramType = "query",dataType = "string",required = false,defaultValue = ""),
            @ApiImplicitParam(value = "上架时间",name = "uptimeStart",paramType = "query",dataType = "date",required = false),
            @ApiImplicitParam(value = "上架时间",name = "uptimeEnd",paramType = "query",dataType = "date",required = false),
            @ApiImplicitParam(value = "发布时间区间",name = "createStart",paramType = "query",dataType = "date",required = false),
            @ApiImplicitParam(value = "发布时间区间",name = "createEnd",paramType = "query",dataType = "date",required = false),
            @ApiImplicitParam(value = "印记",name = "mark",paramType = "query",dataType = "string",required = false),
            @ApiImplicitParam(value = "店铺名称",name = "shopname",paramType = "query",dataType = "string",required = false),
            @ApiImplicitParam(value = "规格",name = "stand",paramType = "query",dataType = "string",required = false),
            @ApiImplicitParam(value = "是否远期",name = "futurePrice",paramType = "query",dataType = "short",required = false,defaultValue = "0")
    })
    //@PreAuthorize("hasAuthority('" + AdminAuthorityConst.VALETORDER + "')")
    public PageRet listFastenerProduct(@RequestParam(required = true,defaultValue = "1") int pageNo,
                                       @RequestParam(required = true,defaultValue = "20") int pageSize,
                                       @RequestParam(required = false) String productname,
                                       @RequestParam(required = false) String brand,
                                       @RequestParam(required = false,defaultValue = "0") long levelid,
                                       @RequestParam(required = false,defaultValue = "0") short haveStorenum,
                                       @RequestParam(required = false,defaultValue = "-1") short pdstate,
                                       @RequestParam(required = false,defaultValue = "-1") long materialid,
                                       @RequestParam(required = false,defaultValue = "-1") long cardnumid,
                                       @RequestParam(required = false,defaultValue = "0") long memberid,
                                       @RequestParam(required = false,defaultValue = "") String username,
                                       @RequestParam(required = false) Date uptimeStart,
                                       @RequestParam(required = false) Date uptimeEnd,
                                       @RequestParam(required = false) Date createStart,
                                       @RequestParam(required = false) Date createEnd,
                                       @RequestParam(required = false) String mark,
                                       @RequestParam(required = false) String shopname,
                                       @RequestParam(required = false,defaultValue = "") String stand,
                                       @RequestParam(required = false,defaultValue = "0") short futurePrice
    ){
        PageRet pageRet =  new PageRet();
        ProductInfoQuery productInfo = new ProductInfoQuery();
        ProductStore productStore = new ProductStore();


        if(StringUtils.hasText(productname)){
            productInfo.setProductname(productname);
        }

        if(StringUtils.hasText(brand)){
            productInfo.setBrand(brand);
        }

        if(StringUtils.hasText(mark)){
            productInfo.setMark(mark);
        }

        productInfo.setFuturePrice(futurePrice);


        if(levelid >0){
            Categories productCategory =  categoriesService.getCategoryLevel(levelid);
            if(productCategory != null){
                if(productCategory.getLevel()==1){
                    productInfo.setLevel1id(levelid);
                }else if(productCategory.getLevel() == 2){
                    productInfo.setLevel2id(levelid);
                }else if(productCategory.getLevel() == 3){
                    productInfo.setLevel3id(levelid);
                }
            }
        }


        if(haveStorenum == 1 || haveStorenum == 2){
            productStore.setHaveStorenum(haveStorenum);
        }


        if(StringUtils.hasText(shopname)){
            productInfo.setShopname(shopname);
        }

        productInfo.setPdstate(pdstate);
        productInfo.setMaterialid(materialid);
        productInfo.setCardnumid(cardnumid);
        productInfo.setMemberid(memberid);
        productInfo.setUsername(username);


        if(uptimeStart!= null){
            productInfo.setUptimeStart(uptimeStart);
        }

        if(uptimeEnd != null){
            productInfo.setUptimeEnd(DateUtils.addDays(uptimeEnd,1));
        }

        if(createStart != null){
            productInfo.setCreateStart(createStart);
        }

        if(createEnd != null){
            productInfo.setCreateEnd( DateUtils.addDays(createEnd,1));
        }

        if(stand != null){
            productInfo.setStand(stand);
        }

        productInfo.setProductStore(productStore);
        PageInfo pageInfo = productInfoService.listFastenerProduct(pageNo,pageSize,productInfo);


        pageRet.data.setPageInfo(pageInfo);
        pageRet.setResult(BasicRet.SUCCESS);

        return  pageRet;
    }



    @RequestMapping(value =  "/listOtherProduct",method = RequestMethod.POST)
    @ApiOperation("非紧固件商品分页列表查询")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "第几页",name = "pageNo",paramType = "query",dataType = "int",required = true,defaultValue = "1"),
            @ApiImplicitParam(value = "每页显示的条数",name = "pageSize",paramType = "query",dataType = "int",required = true,defaultValue = "20"),
            @ApiImplicitParam(value = "商品名",name = "productname",paramType = "query",dataType = "string",required = false),
            @ApiImplicitParam(value = "品牌",name = "brand",paramType = "query",dataType = "string",required = false),
            @ApiImplicitParam(value = "分类id",name = "levelid",paramType = "query",dataType = "int",required = false,defaultValue = "0"),
            @ApiImplicitParam(value = "商品状态 0=放入仓库 1=待审核(立即发布) 2=审核通过 3=未通过 4=已上架(上架) 5=下架 6=删除 7-违规下架",name = "pdstate",paramType = "query",dataType = "int",required = false,defaultValue = "-1"),
            @ApiImplicitParam(value = "卖家id",name = "memberid",paramType = "query",dataType = "int",required = false,defaultValue = "0"),
            @ApiImplicitParam(value = "用户名",name = "username",paramType = "query",dataType = "string",required = false,defaultValue = ""),
            @ApiImplicitParam(value = "上架时间",name = "uptimeStart",paramType = "query",dataType = "date",required = false),
            @ApiImplicitParam(value = "上架时间",name = "uptimeEnd",paramType = "query",dataType = "date",required = false),
            @ApiImplicitParam(value = "发布时间区间",name = "createStart",paramType = "query",dataType = "date",required = false),
            @ApiImplicitParam(value = "发布时间区间",name = "createEnd",paramType = "query",dataType = "date",required = false),
            @ApiImplicitParam(value = "店铺名称",name = "shopname",paramType = "query",dataType = "string",required = false),
            @ApiImplicitParam(value = "是否远期",name = "futurePrice",paramType = "query",dataType = "short",required = false,defaultValue = "0")
    })
    //@PreAuthorize("hasAuthority('" + AdminAuthorityConst.VALETORDER + "')")
    public  PageRet listOtherProduct(OtherProductQueryDto queryDto, int pageNo, int pageSize, Model model){
        PageRet pageRet =  new PageRet();

        if(queryDto.getLevelid() != null && queryDto.getLevelid()>0){
            Categories productCategory =  categoriesService.getCategoryLevel(queryDto.getLevelid());
            if(productCategory != null){
                if(productCategory.getLevel()==1){
                    queryDto.setLevel1id(queryDto.getLevelid());
                }else if(productCategory.getLevel() == 2){
                    queryDto.setLevel2id(queryDto.getLevelid());
                }else if(productCategory.getLevel() == 3){
                    queryDto.setLevel3id(queryDto.getLevelid());
                }
            }
        }


        if(queryDto.getUptimeEnd() != null){
            queryDto.setUptimeEnd(DateUtils.addDays(queryDto.getUptimeEnd(),1));
        }

        if(queryDto.getCreateEnd() != null){
            queryDto.setCreateEnd(DateUtils.addDays(queryDto.getCreateEnd(),1));
        }

        PageInfo pageInfo =  otherProdService.listOtherProd(queryDto,pageNo,pageSize);

        pageRet.data.setPageInfo(pageInfo);
        pageRet.setResult(BasicRet.SUCCESS);

        return  pageRet;
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

        private ProdRatePriceData data = new ProdRatePriceData();

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
            @ApiImplicitParam(value = "会员id", name = "memberid", paramType = "query", dataType = "long"),
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
            Model model,long memberid) {

       // Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        Member member = memberService.selectById(memberid);
        String store = storename;

        searchKey = searchKey.toLowerCase();

        if(member != null){
            member =  memberService.getMemberById(member.getId());
        }



        ProductListRet productListRet = new ProductListRet();

        int start = (pageNo - 1) * pageSize;

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

        int count = productSearchService.countSearchWithKeys(StringUtils.nvl(searchKey), level1, level2, level3, productname, brand, cardnum, material,surfacetreatment, attrs,selfsupport, havestore,forwardtime,store);

        List<Map> list = null;
//        List<List<Map>> resultList = null;
        Map<String, Set> resultGroupAttr = null;
        Map<String, Set> resultGroupAttr2 = null;

        List<KeyValue> keyValues = new ArrayList<>();
        if (count > 0) {
            list = productSearchService.searchWithKeys(StringUtils.nvl(searchKey), level1, level2, level3, productname, brand, cardnum, material, surfacetreatment,attrs, start, pageSize,sorttype== null ? null :JinshangUtils.fastenSortType(sorttype),selfsupport, havestore,forwardtime,store);

            if (list != null && list.size() > 0) {

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
                    Map<String, Object> threepriceMap = new HashMap<>();
                    Map<String, Object> ninetypriceMap = new HashMap<>();
                    Map<String, Object> thirtypriceMap = new HashMap<>();
                    Map<String, Object> sixtypriceMap = new HashMap<>();

                    if (map.get("prodprice") != null && ((BigDecimal)map.get("prodprice")).compareTo(Quantity.BIG_DECIMAL_0) >0 ) {
                        prodpriceMap.put("type", 0);
                        prodpriceMap.put("name", Quantity.LIJIFAHUO);
                        if(member != null) {
                            prodpriceMap.put("price", ((BigDecimal) map.get("prodprice")).multiply(memberRateSetting.getRate()).setScale(5, BigDecimal.ROUND_HALF_UP));
                        }else{
                            prodpriceMap.put("price", ((BigDecimal) map.get("prodprice")).multiply(memberRateSetting.getRate()).setScale(5, BigDecimal.ROUND_HALF_UP));
                        }
                        list1.add(prodpriceMap);
                    }


                    if (map.get("thirtyprice") != null && ((BigDecimal)map.get("thirtyprice")).compareTo(Quantity.BIG_DECIMAL_0) >0) {
                        thirtypriceMap.put("type", 30);
                        thirtypriceMap.put("name", Quantity.SANSHITIANFAHUO);
                        if(member != null) {
                            thirtypriceMap.put("price", ((BigDecimal) map.get("thirtyprice")).multiply(memberRateSetting.getRate()).setScale(5, BigDecimal.ROUND_HALF_UP));
                        }else{
                            thirtypriceMap.put("price", ((BigDecimal) map.get("thirtyprice")).multiply(memberRateSetting.getRate()).setScale(5, BigDecimal.ROUND_HALF_UP));
                        }
                        list1.add(thirtypriceMap);
                    }


                    if (map.get("sixtyprice") != null && ((BigDecimal)map.get("sixtyprice")).compareTo(Quantity.BIG_DECIMAL_0) >0) {
                        sixtypriceMap.put("type", 60);
                        sixtypriceMap.put("name", Quantity.LIUSHITIANFAHUO);
                        if(member != null) {
                            sixtypriceMap.put("price", ((BigDecimal) map.get("sixtyprice")).multiply(memberRateSetting.getRate()).setScale(5, BigDecimal.ROUND_HALF_UP));
                        }else{
                            sixtypriceMap.put("price", ((BigDecimal) map.get("sixtyprice")).multiply(memberRateSetting.getRate()).setScale(5, BigDecimal.ROUND_HALF_UP));
                        }

                        list1.add(sixtypriceMap);
                    }

                    if (map.get("ninetyprice") != null && ((BigDecimal)map.get("ninetyprice")).compareTo(Quantity.BIG_DECIMAL_0) >0) {
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

                    map.put("packageStr",JinshangUtils.packageToString((String)map.get("packagetype"),(BigDecimal)map.get("startnum"),(String) map.get("unit")));
                }
            }

//            ProductGroup sort = new ProductGroup();
//            resultList = sort.group(list);
            List<Map> groupAttr = productSearchService.fetchSearchKeys(StringUtils.nvl(searchKey), level1, level2, level3, productname, brand, cardnum, material, surfacetreatment,attrs,selfsupport, havestore,forwardtime,store);


            List<Map> groupAttr1 = new ArrayList<>();
            if(attrs == null || attrs.size()== 0) {
                groupAttr1 = productSearchService.fetchSearchAttrKeys(StringUtils.nvl(searchKey), level1, level2, level3, productname, brand, cardnum, material, surfacetreatment,attrs,selfsupport, havestore,forwardtime,store);
            }else{
                groupAttr1 = productSearchService.fetchSearchAttrKeysHashAttr(StringUtils.nvl(searchKey), level1, level2, level3, productname, brand, cardnum, material,surfacetreatment, attrs,selfsupport, havestore,forwardtime,store);
            }

            resultGroupAttr = productFrontService.groupAttr(groupAttr);

            //在选择品名后,需要返回对应品名的所有属性条件作为筛选项
            if(!StringUtils.hasText(productname)){
                resultGroupAttr2 = productFrontService.groupAttrInAttr(groupAttr1);
            }else{
                resultGroupAttr2 = productFrontService.groupAttrInAttrbyProductname(groupAttr1);
            }


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
//                    keyValue.value.sort((String::compareTo));
//                    keyValue.value.sort((String v1,String v2)->
//                        StringUtils.floatValue(v1) >= StringUtils.floatValue(v2) ? 1 : -1
//                    );
                    keyValue.value.sort((String v1,String v2)->{
                        return ProductSearchSortUtils.getSortWeigth("长度",Quantity.STATE_0,v1).compareTo(ProductSearchSortUtils.getSortWeigth("长度",Quantity.STATE_0,v2));
                    });
                }

                if(key.equals("公称直径")){
                    // keyValue.value.sort((String::compareTo));
                    keyValue.value.sort((String v1,String v2)->{
                        return ProductSearchSortUtils.getSortWeigth("公称直径",Quantity.STATE_0,v1).compareTo(ProductSearchSortUtils.getSortWeigth("公称直径",Quantity.STATE_0,v2));
                    });
                }


                if(key.equals("牙数")){
                    keyValue.value.sort((String v1,String v2)->
                            StringUtils.floatValue(v1) >= StringUtils.floatValue(v2) ? 1 : -1
                    );
                }

                //

//                if(StringUtils.hasText(level1) || StringUtils.hasText(level2) || StringUtils.hasText(level3) || StringUtils.hasText(productname)){
//                        keyValues.add(keyValue);
//                }else{
//                    if(!key.equals("level1") && !key.equals("level2") && !key.equals("level3") && !key.equals("productname")){
//                        continue;
//                    }else {
//                        keyValues.add(keyValue);
//                    }
//                }

                keyValues.add(keyValue);

            }
        }


        keyValues.sort((KeyValue kv1, KeyValue kv2)->kv1.getSort().compareTo(kv2.getSort()));
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


    @RequestMapping(value = "/deleteShopCar", method = RequestMethod.POST)
    @ApiOperation(value = "从购物车中删除单个商品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "memberid", value = "会员id", required = true, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "id", value = "主键id", required = true, paramType = "query", dataType = "long"),
    })
    public BasicRet deleteShopCar(Model model, Long id, HttpServletRequest request,long memberid) {
        BasicRet basicRet = new BasicRet();
        ShopCar shopCar = shopCarService.getShopCarByPrimeKey(id);
        if (shopCar == null) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("删除失败");
            return basicRet;
        } else {
            shopCarService.deleteShopCar(id);
            basicRet.setResult(BasicRet.SUCCESS);
            basicRet.setMessage("删除成功");
           // Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
            Member member = memberService.selectById(memberid);
            //保存用户日志
            memberLogOperator.saveMemberLog(member, null, "购物车中删除了一个商品","/rest/admin/toorders/deleteShopCar" ,request, memberOperateLogService);
            return basicRet;
        }
    }


}
