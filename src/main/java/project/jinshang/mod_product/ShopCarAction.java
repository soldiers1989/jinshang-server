package project.jinshang.mod_product;

import com.github.pagehelper.PageInfo;
import com.google.common.primitives.Longs;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import mizuki.project.core.restserver.config.BasicRet;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.jinshang.common.bean.MemberLogOperator;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.common.constant.Quantity;
import project.jinshang.common.constant.TradeConstant;
import project.jinshang.common.utils.JinshangUtils;
import project.jinshang.common.utils.StringUtils;
import project.jinshang.mod_activity.bean.LimitTimeProd;
import project.jinshang.mod_activity.bean.LimitTimeStore;
import project.jinshang.mod_activity.service.LimitTimeProdService;
import project.jinshang.mod_activity.service.LimitTimeStoreService;
import project.jinshang.mod_admin.mod_transet.bean.TransactionSetting;
import project.jinshang.mod_admin.mod_transet.service.TransactionSettingService;
import project.jinshang.mod_company.StoreMapper;
import project.jinshang.mod_company.bean.Store;
import project.jinshang.mod_invoice.bean.InvoiceInfo;
import project.jinshang.mod_member.bean.Favorite;
import project.jinshang.mod_member.bean.Member;
import project.jinshang.mod_member.bean.MemberRateSetting;
import project.jinshang.mod_member.service.FavoriteService;
import project.jinshang.mod_member.service.MemberRateSettingService;
import project.jinshang.mod_product.bean.*;
import project.jinshang.mod_product.service.*;
import project.jinshang.mod_shippingaddress.bean.ShippingAddress;
import project.jinshang.mod_shippingaddress.service.ShippingAddressService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.awt.geom.Area;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by Administrator on 2017/11/9.
 */
@RestController
@RequestMapping("/rest/buyer/shopcar")
@SessionAttributes({AppConstant.MEMBER_SESSION_NAME})
@Api(tags = "买家-购物车模块", description = "购物车相关")
@Transactional(rollbackFor = Exception.class)
public class ShopCarAction {
    @Autowired
    ShopCarService shopCarService;
    @Autowired
    private OrdersService ordersService;

    @Autowired
    private MemberRateSettingService memberRateSettingService;
    @Autowired
    private AreaCostService areaCostService;
    @Autowired
    private TransactionSettingService transactionSettingService;
    @Autowired
    private MemberOperateLogService memberOperateLogService;

    @Autowired
    private ProductAttrService productAttrService;

    @Autowired
    private LimitTimeStoreService limitTimeStoreService;
    @Autowired
    private StoreMapper storeMapper;
    @Autowired
    private FreightService freightService;


    @Autowired
    private OrderProductServices orderProductServices;


    @Autowired
    private LimitTimeProdService limitTimeProdService;

    @Autowired
    private ProductStoreService productStoreService;
    @Autowired
    private ShippingAddressService shippingAddressService;
    @Autowired
    private FavoriteService favoriteService;


    MemberLogOperator memberLogOperator = new MemberLogOperator();

    @RequestMapping(value = "/updateShopCar", method = RequestMethod.POST)
    @ApiOperation(value = "当变更数量和单位和远期付款方式时候用")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "购物车id", required = false, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "pdid", value = "针对详情页商品id", required = false, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "storeid", value = "针对详情页仓库id", required = false, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "diliveryTime", value = "针对详情页发货时间", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "pdnumber", value = "商品数量", required = true, paramType = "query", dataType = "float"),
            @ApiImplicitParam(name = "unit", value = "单位：箱或盒或千", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "protype", value = "远期类型0=不是远期1=全款2=定金", required = true, paramType = "query", dataType = "int"),
    })
    public ShopCarRet updateShopCar(Model model, Long id, BigDecimal pdnumber, String unit, long pdid, long storeid, String diliveryTime, short protype,String pdno) {

        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
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
                    BigDecimal appPap = saleprice.multiply(convertNum);
                    shopCar.setProtype(shopCar.getProtype());
                    if (shopCar.getProtype() != Quantity.STATE_0) {
                        if (shopCar.getProtype() == Quantity.STATE_1) {
                            shopCar.setPrice(shopCar.getPrice().multiply(TradeConstant.allPayRate));
                            //全款
                            shopCar.setAllpay(appPap.multiply(TradeConstant.allPayRate));
                        } else {
                            //定金
                            shopCar.setPartpay(appPap.multiply(transactionSettingService.getTransactionSetting().getRemotepurchasingmargin().multiply(new BigDecimal(0.01))));
                            //余款
                            shopCar.setYupay(appPap.subtract(shopCar.getPartpay()));
                        }
                    }
                    //全款
//                    shopCar.setAllpay(saleprice.multiply(convertNum).multiply(TradeConstant.allPayRate));
                    //定金
//                    shopCar.setPartpay(saleprice.multiply(convertNum).multiply(transactionSettingService.getTransactionSetting().getRemotepurchasingmargin().multiply(new BigDecimal(0.01))));
                    //余款
//                    shopCar.setYupay(saleprice.multiply(convertNum).multiply((new BigDecimal(1).subtract(transactionSettingService.getTransactionSetting().getRemotepurchasingmargin().multiply(new BigDecimal(0.01))))));
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




    @RequestMapping(value = "/loadAllShopCar", method = RequestMethod.POST)
    @ApiOperation(value = "加载用户购物车信息")
    public ShopCarListRet loadAllShopCar(Model model, @RequestParam(defaultValue = "1") int pageNo,@RequestParam(defaultValue = "20") int pageSize) {
        ShopCarListRet shopCarListRet = new ShopCarListRet();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        PageInfo pageInfo = shopCarService.loadAllShipCar(member.getId(), pageNo, pageSize);

        shopCarListRet.data.pageInfo = pageInfo;
        shopCarListRet.setMessage("返回成功");
        shopCarListRet.setResult(BasicRet.SUCCESS);
        return shopCarListRet;
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


    @RequestMapping(value = "/loadSelectProduct", method = RequestMethod.POST)
    @ApiOperation(value = "订单确认页面商品列表")
    public ShopCarRet loadSelectProduct(Model model, String shopcarids, Long pdids, String pdno,String uProvince, String uCity, BigDecimal pdnum, String pdunit, String pddilivery, Long pdstoreid, int type, Short protype,@RequestParam(defaultValue = "0") Short isonline,Long limitid){
        ShopCarRet shopCarRet = new ShopCarRet();
        shopCarRet.setMessage("APP升级中，请到电脑或微信端下单");
        shopCarRet.setResult(BasicRet.ERR);
        return shopCarRet;
    }

/*
    @RequestMapping(value = "/loadSelectProduct", method = RequestMethod.POST)
    @ApiOperation(value = "订单确认页面商品列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pdids", value = "如果单个商品购买就传一个id", paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "pdno", value = "如果单个商品购买就传一个pdno", paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "uProvince", value = "收货地址省", paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "uCity", value = "收货地址市", paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "pdnum", value = "针对单个商品数量", paramType = "query", dataType = "double"),
            @ApiImplicitParam(name = "pdunit", value = "针对单个商品单位", paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "pddilivery", value = "针对单个商品发货时间", paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "pdstoreid", value = "针对单个商品仓库id", paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "type", value = "单个商品购买就传0，从购物车结算就传1", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "protype", value = "针对单个商品，远期类型0=不是远期1=全款2=定金", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "shopcarids", value = "购物车id", paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "isonline", value = "订单类型0=普通订单1=线上2=限时购", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "limitid", value = "活动id", paramType = "query", dataType = "int"),
    })
    public ShopCarRet loadSelectProduct(Model model, String shopcarids, Long pdids, String pdno,String uProvince, String uCity, BigDecimal pdnum, String pdunit, String pddilivery, Long pdstoreid, int type, Short protype,@RequestParam(defaultValue = "0") Short isonline,Long limitid) {

        ShopCarRet shopCarRet = new ShopCarRet();
        shopCarRet.setMessage("返回成功");
        shopCarRet.setResult(BasicRet.SUCCESS);
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

        if(!StringUtils.hasText(uProvince)){
            ShippingAddress shippingAddress = shippingAddressService.getDefaultShippingAddress(member.getId(),Quantity.STATE_2);
            if(shippingAddress != null) {
                uProvince = shippingAddress.getProvince();
                uCity = shippingAddress.getCity();
            }
        }

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
                        if(!ordersService.checkBuyNum(converNum,productStore.getMinplus())){
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
                        map.put("allpay", paymoney.multiply(TradeConstant.allPayRate));
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


    @RequestMapping(value = "/countSinglePdFight", method = RequestMethod.POST)
    @ApiOperation(value = "计算单个商品运费")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pdid", value = "商品id", required = true, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "storeid", value = "仓库id", required = true, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "uprovince", value = "收货地址省", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "ucity", value = "收货地址市", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "pdnum", value = "商品数量", required = true, paramType = "query", dataType = "double"),
            @ApiImplicitParam(name = "unit", value = "单位", required = true, paramType = "query", dataType = "string"),
    })
    public ShopCarRet countSinglePdFight(long pdid, long storeid, String uprovince, String ucity, BigDecimal pdnum, String unit,String pdno) {

        ShopCarRet shopCarRet = new ShopCarRet();
        shopCarRet.setMessage("返回成功");
        shopCarRet.setResult(BasicRet.SUCCESS);
        ProductStore productStore = shopCarService.getProductStore(pdid,pdno, storeid);
        ProductInfo productInfo = shopCarService.getProductInfo(pdid);
        /*
        修改记录-李嘉成
        修改前代码
        BigDecimal convertNum = (BigDecimal) (JinshangUtils.toLowerUnit(productInfo.getPackagetype(), pdnum, unit)).get("num");
        */
        //TODO
        /*
        修改后代码
        */
        BigDecimal convertNum = pdnum;
        if (AppConstant.FASTENER_PRO_TYPE.equals(productInfo.getProducttype())) {
            convertNum = (BigDecimal) (JinshangUtils.toLowerUnit(productInfo.getPackagetype(), pdnum, unit)).get("num");
        }
        /*
        修改后代码
        */
        BigDecimal weight = productStore.getWeight();
        long freightmode = productStore.getFreightmode();

        BigDecimal totalweight = weight.multiply(convertNum);

        //计算运费
        shopCarRet.data.bigDecimal =    freightService.getFreight(freightmode,totalweight,uprovince,ucity);

        /*
        if (freightmode != Quantity.STATE_) {
            //获取商品运费模板
            ShippingTemplates shippingTemplates = shopCarService.getShippingTemp(freightmode);
            //默认运费公斤
            BigDecimal defaultfreight = shippingTemplates.getDefaultfreight();
            //默认运费
            BigDecimal defaultcost = shippingTemplates.getDefaultcost();
            //每增加公斤
            BigDecimal perkilogramadded = shippingTemplates.getPerkilogramadded();
            //每增加运费
            BigDecimal perkilogramcost = shippingTemplates.getPerkilogramcost();
            //商品总重量
            BigDecimal totalweight = weight.multiply(convertNum);
            //匹配地区运费
            AreaCost areaCost = shopCarService.getAreaCost(freightmode);

            if (uprovince == null || ucity == null) {
                if (shippingTemplates != null) {
                    //计算默认运费
                    shopCarRet.data.bigDecimal = getTotalCost(defaultfreight, defaultcost, perkilogramadded, perkilogramcost, totalweight);
                    return shopCarRet;
                }
            } else {
                //如果有地区运费模板，就用地区运费模板，否则用默认运费模板
                if (areaCost != null) {
                    String province = areaCost.getProvince();
                    String city = areaCost.getCity();
                    //判断省市是否匹配
                    if (province.contains(uprovince) && city.contains(ucity)) {
                        BigDecimal udefaultfreight = areaCost.getDefaultfreight();
                        //默认运费
                        BigDecimal udefaultcost = areaCost.getDefaultcost();
                        //每增加公斤
                        BigDecimal uperkilogramadded = areaCost.getPerkilogramadded();
                        //每增加运费
                        BigDecimal uperkilogramcost = areaCost.getPerkilogramcost();
                        //计算地区运费
                        shopCarRet.data.bigDecimal = getTotalCost(udefaultfreight, udefaultcost, uperkilogramadded, uperkilogramcost, totalweight);
                        return shopCarRet;

                    } else {
                        //计算默认运费
                        shopCarRet.data.bigDecimal = getTotalCost(defaultfreight, defaultcost, perkilogramadded, perkilogramcost, totalweight);
                        return shopCarRet;
                    }

                } else {
                    //计算默认运费
                    shopCarRet.data.bigDecimal = getTotalCost(defaultfreight, defaultcost, perkilogramadded, perkilogramcost, totalweight);
                    return shopCarRet;
                }
            }
        } else {
            shopCarRet.data.bigDecimal = new BigDecimal(0);
            return shopCarRet;
        }
       shopCarRet.data.bigDecimal = new BigDecimal(0);*/
        return shopCarRet;
    }




    @RequestMapping(value = "/insertShopCar", method = RequestMethod.POST)
    @ApiOperation(value = "新增商品到购物车")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pdid", value = "商品id", required = true, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "pdno", value = "商品编号", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "limitid", value = "限时购id", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "pdnumber", value = "商品数量", required = true, paramType = "query", dataType = "double"),
            @ApiImplicitParam(name = "storeid", value = "仓库id", required = false, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "delivertime", value = "发货时间", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "unit", value = "单位", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "protype", value = "远期类型0=不是远期1=全款2=定金", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "isonline", value = "订单类型标识0=线上1=线下2=限时购", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "source", value = "订单来源 1=PC,2=微信，3=安卓，4=IOS", required = false, paramType = "query", dataType = "int"),
    })
    public BasicRet insertShopCar(ShopCar shopCar, Model model, HttpServletRequest request,Long limitid,Short source) throws Exception {
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

        if(source == null){
            return new BasicRet(BasicRet.ERR,"APP升级中，请到电脑或微信端下单");
        }

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
            if (sellerId.compareTo(member.getId())==0) {
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
                if(shopCar1!=null){
                    convertNum =convertNum.add(shopCar1.getPdnumber());
                }
                //判断库存
                productStore = shopCarService.getProductStore(shopCar.getPdid(), shopCar.getPdno(),shopCar.getStoreid());


                if(productStore != null && productStore.getMinplus()!=null && productStore.getMinplus().compareTo(Quantity.BIG_DECIMAL_0)>0){

                    if(!ordersService.checkBuyNum(convertNum,productStore.getMinplus())){
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
                        shopCar.setAllpay(allpap.multiply(TradeConstant.allPayRate));
                        salePrice = salePrice.multiply(TradeConstant.allPayRate);
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
                        if (shopCar1.getProtype() != Quantity.STATE_0) {
                            if (shopCar1.getProtype() == Quantity.STATE_1) {
                                //全款
                                shopCar1.setAllpay(appPap.multiply(TradeConstant.allPayRate));
                            } else {
                                //定金
                                shopCar1.setPartpay(appPap.multiply(transactionSettingService.getTransactionSetting().getRemotepurchasingmargin().multiply(new BigDecimal(0.01))));
                                //余款
                                shopCar1.setYupay(appPap.subtract(shopCar1.getPartpay()));
                            }
                        }
                        //全款
//                        shopCar1.setAllpay(appPap.multiply(TradeConstant.allPayRate));
                        //定金
//                        shopCar1.setPartpay(appPap.multiply(transactionSettingService.getTransactionSetting().getRemotepurchasingmargin().multiply(new BigDecimal(0.01))));
                        //余款
//                        shopCar1.setYupay(appPap.subtract(shopCar1.getPartpay()));
                    }
                    shopCarService.updateShopCar(shopCar1);
                }
                basicRet.setResult(BasicRet.SUCCESS);
                basicRet.setMessage("添加成功");
                //保存用户日志
                memberLogOperator.saveMemberLog(member, null, "新增商品到购物车", "/rest/buyer/shopcar/insertShopCar",request, memberOperateLogService);
                return basicRet;
            } else {
                basicRet.setResult(BasicRet.ERR);
                basicRet.setMessage("找不到此商品");
                return basicRet;
            }
        }

        if(shopCar.getIsonline()==Quantity.STATE_2){  //限时购
            if(Arrays.asList("207,208,209,210".split(",")).contains(StringUtils.nvl(shopCar.getLimitid()))){
                if(!"8".equals(member.getRegistertypelabel()) || !"9".equals(member.getRegisterchannellabel())){
                    return new BasicRet(BasicRet.ERR,"该商品您不可购买");
                }
            }

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

    @RequestMapping(value = "/batchInsertShopCar", method = RequestMethod.POST)
    @ApiOperation(value = "批量添加购物车")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "shopCarList", value = "商品json数组：[{\"saleid\":1,\"pdid\":7,\"pdno\":\"2222\",\"pdnumber\":10,\"storeid\":3,\"delivertime\":\"立即发货\",\"unit\":\"千\",\"protype\":0,\"producttype\":1},{\"saleid\":2,\"pdid\":7,\"pdno\":\"3333\",\"pdnumber\":5,\"storeid\":3,\"delivertime\":\"30天发货\",\"unit\":\"盒\",\"protype\":1，\"producttype\":2}]", required = true, paramType = "query", dataType = "string"),
    })
    public BasicRet batchInsertShopCar(Model model, String shopCarList, HttpServletRequest request) {
        BasicRet basicRet = new BasicRet();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        Gson gson = new Gson();
        List<ShopCar> list = gson.fromJson(shopCarList, new TypeToken<ArrayList<ShopCar>>() {
        }.getType());
        StringBuffer sb = new StringBuffer();
        //验证
        for (ShopCar shopCar : list) {
            ProductInfo info = shopCarService.getProductInfo(shopCar.getPdid());
            shopCar.setSaleid(info.getMemberid());

            sb.setLength(0);
            //判断买家卖家是否是同一账号
            Long sellerId = shopCar.getSaleid();
            if (member.getId() == sellerId) {
                basicRet.setResult(BasicRet.ERR);
                basicRet.setMessage("不能购买自己的商品");
                return basicRet;
            }

            //判断商品是否下架
            ProductStore productStore = null;
            if (info != null) {
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
                } else {
                    //判断库存是否为0
                    productStore = shopCarService.getProductStore(shopCar.getPdid(),shopCar.getPdno(), shopCar.getStoreid());
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

                }


                //计算阶梯价格
                BigDecimal salePrice = ordersService.updatePriceByNum(convertNum, productStore, shopCar.getDelivertime(), info.getMemberid(), info.getLevel3id(), member.getGradleid());
                //远期定金和全款计算
                BigDecimal allpap = salePrice.multiply(convertNum);
                //计算远期定金，全款和余额
                if (shopCar.getProtype() != Quantity.STATE_0) {
                    //全款
                    shopCar.setAllpay(allpap.multiply(TradeConstant.allPayRate));
                    //定金
                    shopCar.setPartpay(allpap.multiply(transactionSettingService.getTransactionSetting().getRemotepurchasingmargin().multiply(new BigDecimal(0.01))));
                    //余款
                    shopCar.setYupay(allpap.subtract(shopCar.getPartpay()));
                }
                //判断购物车里是否有该商品
                ShopCar shopCar1 = shopCarService.getMemberShopCar(shopCar.getPdid(), member.getId(), shopCar.getDelivertime(),shopCar.getPdno(),shopCar.getProtype());
                List<ProductAttr> productAttrs = productAttrService.getListByPidAndPdno(shopCar.getPdid(),shopCar.getPdno());
                if (shopCar1 == null) {
                    shopCar.setMemberid(member.getId());
                    shopCar.setPrice(salePrice);
                    shopCar.setUnit(convertUnit);
                    shopCar.setPdnumber(convertNum);
                    shopCar.setFrightmode(productStore.getFreightmode());
                    for(ProductAttr attr:productAttrs){
                        sb.append(attr.getValue()).append("*");
                    }
                    if(productAttrs.size()>0){
                        sb.deleteCharAt(sb.length()-1);
                    }
                    shopCar.setAttrjson(sb.toString());
                    shopCarService.insertShopCar(shopCar);
                } else {
                    shopCar1.setPdnumber(shopCar1.getPdnumber().add(convertNum));
                    shopCar1.setUnit(convertUnit);
                    shopCar1.setPrice(salePrice);
                    shopCar1.setFrightmode(productStore.getFreightmode());
                    if (shopCar.getProtype() != Quantity.STATE_0) {
                        BigDecimal appPap = salePrice.multiply(shopCar1.getPdnumber());
                        shopCar1.setProtype(shopCar.getProtype());
                        //全款
                        if (shopCar.getProtype() == Quantity.STATE_1) {
                            shopCar1.setAllpay(appPap.multiply(TradeConstant.allPayRate));
                        } else {
                            //定金
                            shopCar1.setPartpay(appPap.multiply(transactionSettingService.getTransactionSetting().getRemotepurchasingmargin().multiply(new BigDecimal(0.01))));
                            //余款
                            shopCar1.setYupay(appPap.subtract(shopCar1.getPartpay()));
                        }
                    }
                    shopCarService.updateShopCar(shopCar1);
                }
            } else {
                basicRet.setResult(BasicRet.ERR);
                basicRet.setMessage("找不到此商品");
                return basicRet;
            }
        }
        //保存用户日志
        memberLogOperator.saveMemberLog(member, null, "批量新增商品到购物车", "/rest/buyer/shopcar/batchInsertShopCar",request, memberOperateLogService);
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("添加成功");
        return basicRet;
    }

    @RequestMapping(value = "/deleteShopCar", method = RequestMethod.POST)
    @ApiOperation(value = "从购物车中删除单个商品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id", required = true, paramType = "query", dataType = "long"),
    })
    public BasicRet deleteShopCar(Model model, Long id, HttpServletRequest request) {
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
            Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
            //保存用户日志
            memberLogOperator.saveMemberLog(member, null, "购物车中删除了一个商品","/rest/buyer/shopcar/deleteShopCar" ,request, memberOperateLogService);
            return basicRet;
        }
    }

    @RequestMapping(value = "/deleteAllShopCar", method = RequestMethod.POST)
    @ApiOperation(value = "从购物车中删除多个商品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "购物车的id集合", required = true, paramType = "query", dataType = "Array"),
    })
    public BasicRet deleteShopCar(Model model, Long[] ids, HttpServletRequest request) {
        BasicRet basicRet = new BasicRet();
        shopCarService.deleteAll(Arrays.asList(ids));
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("删除成功");
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        //保存用户日志
        memberLogOperator.saveMemberLog(member, null, "购物车中删除了多个商品","/rest/buyer/shopcar/deleteAllShopCar", request, memberOperateLogService);
        return basicRet;
    }

    @RequestMapping(value = "/getInvoiceInfo", method = RequestMethod.POST)
    @ApiOperation(value = "获取用户开票信息")
    public ShopCarRet getInvoiceInfo(Model model) {
        ShopCarRet shopCarRet = new ShopCarRet();
        shopCarRet.setMessage("返回成功");
        shopCarRet.setResult(BasicRet.SUCCESS);
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        shopCarRet.data.invoiceInfos = shopCarService.getInvoiceInfoList(member.getId());
        return shopCarRet;
    }

    @PostMapping(value = "/moveToFavorite")
    public  BasicRet moveToFavorite(Model model,long[] shopcarids){
        BasicRet basicRet=new BasicRet();
        basicRet.setMessage("收藏成功");
        basicRet.setResult(BasicRet.SUCCESS);
        Member member= (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        List<Long> list = Longs.asList(shopcarids);
        for (long shopcarid:list){
            Long pid=shopCarService.getShopCarByPrimeKey(shopcarid).getPdid();
            if (favoriteService.getGoodsFavoriteByMemberId(member.getId(), pid)) {
//                basicRet.setResult(BasicRet.SUCCESS);
//                basicRet.setMessage("添加成功");
//                return basicRet;
            }else {
                Favorite favorite = new Favorite();
                favorite.setMemberid(member.getId());
                favorite.setPid(pid);
                favorite.setCreatetime(new Date());

                favoriteService.add(favorite);
            }

            shopCarService.deleteShopCar(shopcarid);
        }
        return basicRet;
    }



}
