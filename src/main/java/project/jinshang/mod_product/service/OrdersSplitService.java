package project.jinshang.mod_product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.common.constant.Quantity;
import project.jinshang.common.constant.ShippingTemplateMethod;
import project.jinshang.common.utils.JinshangUtils;
import project.jinshang.common.utils.StringUtils;
import project.jinshang.mod_activity.bean.LimitTimeProd;
import project.jinshang.mod_activity.bean.LimitTimeStore;
import project.jinshang.mod_activity.service.LimitTimeProdService;
import project.jinshang.mod_activity.service.LimitTimeStoreService;
import project.jinshang.mod_product.bean.*;
import project.jinshang.mod_product.bean.dto.ShopCarProdView;
import project.jinshang.mod_product.bean.dto.ShopCarView;
import project.jinshang.mod_product.bean.dto.SplitOrderQuery;

import java.math.BigDecimal;
import java.util.*;

/**
 * create : wyh
 * date : 2018/7/16
 */

@Service
public class OrdersSplitService {

    @Autowired
    private ProductInfoService productInfoService;
    @Autowired
    private ProductStoreService productStoreService;
    @Autowired
    private LimitTimeProdService limitTimeProdService;
    @Autowired
    private LimitTimeStoreService limitTimeStoreService;
    @Autowired
    private BrandService brandService;
    @Autowired
    private FreightService freightService;
    @Autowired
    private ShippingTemplateGroupService shippingTemplateGroupService;

    /**
     * 购物车订单划分订单
     * @param shopCarList
     * @return
     */
    public List<List<ShopCar>> splitOrders(List<ShopCar> shopCarList){
        //分单规则：
        //  1.远期订单 一个商品一个订单
        //  2.限时购订单 同一个卖家，同一个仓库的商品 一个订单
        //  3.普通订单  统一个卖家，同一个仓库，同一个运费集合  一个订单

        List<List<ShopCar>> splitShopCarList  = new ArrayList<>();

        Map<String,List<ShopCar>> commonShopCarMap = new LinkedHashMap<>();

        List<ShopCarView> resultList = new ArrayList<>();

        for(ShopCar shopCar : shopCarList){
            if(shopCar.getIsonline() == Quantity.STATE_0){ //普通订单

                ProductInfo productInfo = productInfoService.getById(shopCar.getPdid());
                ProductStore productStore = productStoreService.getByPdidAndPdno(shopCar.getPdid(),shopCar.getPdno());
                shopCar.setProductInfo(productInfo);
                shopCar.setProductStore(productStore);

                if(shopCar.getProtype() != 0){ //远期订单
                    List<ShopCar> list = new ArrayList<>();
                    list.add(shopCar);
                    splitShopCarList.add(list);
                }else{
                    String key = productInfo.getMemberid()+"-"+productStore.getStoreid()+"-"+productInfo.getShippingtemplatesgroup();
                    List<ShopCar> list = null;
                    if(commonShopCarMap.containsKey(key)){
                        list = commonShopCarMap.get(key);
                        list.add(shopCar);
                    }else{
                        list = new ArrayList<>();
                        list.add(shopCar);
                        commonShopCarMap.put(key,list);
                    }
                }
            }else if(shopCar.getIsonline() == Quantity.STATE_2){  //限时购订单
                LimitTimeProd limitTimeProd = limitTimeProdService.getById(shopCar.getLimitid());
                ProductInfo productInfo = productInfoService.getById(limitTimeProd.getPdid());
                LimitTimeStore limitTimeStore = limitTimeStoreService.getLimitTimeStore(shopCar.getLimitid(),shopCar.getPdid(),shopCar.getPdno());
                ProductStore productStore = productStoreService.getByPdidAndPdno(shopCar.getPdid(),shopCar.getPdno());

                shopCar.setProductInfo(productInfo);
                shopCar.setProductStore(productStore);
                shopCar.setLimitTimeProd(limitTimeProd);
                shopCar.setLimitTimeStore(limitTimeStore);

                String key = limitTimeProd.getMemberid()+"-"+productStore.getStoreid();
                List<ShopCar> list = null;
                if(commonShopCarMap.containsKey(key)){
                    list = commonShopCarMap.get(key);
                    list.add(shopCar);
                }else{
                    list = new ArrayList<>();
                    list.add(shopCar);
                    commonShopCarMap.put(key,list);
                }
            }
        }

        Set<String> commonShopCarKeySet =  commonShopCarMap.keySet();
        for(String commonShopCarKey : commonShopCarKeySet){
            splitShopCarList.add(commonShopCarMap.get(commonShopCarKey));
        }

        return  splitShopCarList;
    }


    /**
     * 统计单个订单的总价格、总重量
     * @param splitShopCarList
     */
    public void enOrdersStep1(List<ShopCarView> splitShopCarList){
        for (ShopCarView shopCarView : splitShopCarList) {
            //订单总重量
            BigDecimal totalWeight = Quantity.BIG_DECIMAL_0;
            //订单商品总价格
            BigDecimal productTotalPrice = Quantity.BIG_DECIMAL_0;
            //订单商品订金总价
            BigDecimal productPartTotalPrice = Quantity.BIG_DECIMAL_0;
            //订单商品余款总价
            BigDecimal productYuTotalPrice = Quantity.BIG_DECIMAL_0;

            for (ShopCarProdView prodView : shopCarView.getShopCarProdViewList()) {
                totalWeight = totalWeight.add(prodView.getWeight().multiply(prodView.getPdnumber()));
                productTotalPrice = productTotalPrice.add(prodView.getAllpay());
                productPartTotalPrice = productPartTotalPrice.add(prodView.getPartpay());
                productYuTotalPrice = productYuTotalPrice.add(prodView.getYupay());
            }

            shopCarView.setTotalWeight(totalWeight);
            shopCarView.setProductTotalPrice(productTotalPrice);
            shopCarView.setProductPartTotalPrice(productPartTotalPrice);
            shopCarView.setProductYuTotalPrice(productYuTotalPrice);
        }
    }



    /**
     * 统计订单商品总重量、计算订单运费
     * @return
     */
    public void getOrderFreight (List<ShopCarView> splitShopCarList,String province,String city) throws Exception {

        //计算运费
        for (ShopCarView shopCarView : splitShopCarList) {
            ShopCarProdView shopCarProdView = shopCarView.getShopCarProdViewList().get(0);

            if (shopCarProdView.getIsonline() == Quantity.STATE_2 || shopCarView.getShippingTemplateGroupId() ==-1) { //限时购 全部包邮
                shopCarView.setDefaultmethod("包邮");
                shopCarView.setFreight(Quantity.BIG_DECIMAL_0);
                List<String> shippintMethod = new ArrayList<>();
                shippintMethod.add("包邮");
                shopCarView.setSupportShippingMethod(shippintMethod);
            } else {
                ShippingTemplateGroup shippingTemplateGroup = shippingTemplateGroupService.getShippingTemplateGroup(shopCarView.getShippingTemplateGroupId());

                List<String> shippintMethod = new ArrayList<>();
                if (shippingTemplateGroup.getSelflifting()) {
                    shippintMethod.add("自提");
                    shopCarView.setDefaultmethod("自提");
                    shopCarView.setShippingTemplateId(ShippingTemplateMethod.Selflifting);
                }

                if (shippingTemplateGroup.getSfarrivepay()) {
                    shippintMethod.add("顺丰到付");
                    shopCarView.setDefaultmethod("顺丰到付");
                    shopCarView.setShippingTemplateId(ShippingTemplateMethod.Sfarrivepay);
                }

                if (shippingTemplateGroup.getExpreselflifting()) {
                    shippintMethod.add("物流自提");
                    shopCarView.setDefaultmethod("物流自提");
                    shopCarView.setShippingTemplateId(shippingTemplateGroup.getExpreselftemp());
                }

                if (shippingTemplateGroup.getExprehousehoid()) {
                    shippintMethod.add("物流到户");
                    shopCarView.setDefaultmethod("物流到户");
                    shopCarView.setShippingTemplateId(shippingTemplateGroup.getExprehousetemp());
                }

                if (shippingTemplateGroup.getExpresspay()) {
                    shippintMethod.add("快递");
                    shopCarView.setDefaultmethod("快递");
                    shopCarView.setShippingTemplateId(shippingTemplateGroup.getExpretemp());
                }

                shopCarView.setSupportShippingMethod(shippintMethod);
                if (shopCarView.getShippingTemplateId() > 0) {  //使用运费模板
                    if(StringUtils.hasText(province)) {
                        Map<String, Object> freightMap = freightService.getFreight(shopCarView.getShippingTemplateId(), shopCarView.getProductTotalPrice(), shopCarView.getTotalWeight(), province, city);
                        //计算运费
                        BigDecimal freight = (BigDecimal) freightMap.get("freight");
                        shopCarView.setFreight(freight.setScale(2, BigDecimal.ROUND_HALF_UP));
                    }else{
                        shopCarView.setFreight(Quantity.BIG_DECIMAL_0);
                    }
                }
            }
        }
    }



    public ShopCarProdView shopCarToView(ShopCar shopCar){
        ShopCarProdView view = new ShopCarProdView();


        ProductInfo productInfo = shopCar.getProductInfo();
        ProductStore productStore = shopCar.getProductStore();
        LimitTimeProd limitTimeProd = shopCar.getLimitTimeProd();
        LimitTimeStore limitTimeStore = shopCar.getLimitTimeStore();

        view.setShopcarid(shopCar.getId());
        view.setPdid(productInfo.getId());
        view.setLevel1(productInfo.getLevel1());
        view.setLevel2(productInfo.getLevel2());
        view.setLevel3(productInfo.getLevel3());
        view.setProductname(productInfo.getProductname());
        String[] strpic = (String[]) productInfo.getPdpicture();
        if (strpic != null) {
            if (strpic.length > 0) {
                view.setPdpicture(strpic[0]);
            } else {
                view.setPdpicture("");
            }
        } else {
            view.setPdpicture("");
        }

        view.setBrand(productInfo.getBrand());
        Brand brand = brandService.getById(productInfo.getBrandid());
        if (brand != null) {
            if (brand.getPic() != null) {
                view.setBrandpic(brand.getPic());
            } else {
                view.setBrandpic("");
            }
        } else {
            view.setBrandpic("");
        }

        view.setStand(shopCar.getAttrjson());

        BigDecimal converNum = shopCar.getPdnumber();
        String convertUnit = "";
        if (AppConstant.FASTENER_PRO_TYPE.equals(productInfo.getProducttype())) {//紧固件转换单位为基础单位的数量
            Map<String,Object> map =  JinshangUtils.toLowerUnit(productInfo.getPackagetype(), shopCar.getPdnumber(), productInfo.getUnit());
            converNum = (BigDecimal) map.get("num");
            convertUnit = (String) map.get("unit");
        }

        if(AppConstant.FASTENER_PRO_TYPE.equals(productInfo.getProducttype())){
            view.setUnit(convertUnit);
            view.setPdnumber(converNum);
            view.setPackageStr(JinshangUtils.packageToString(productInfo.getPackagetype(),converNum,convertUnit));
        }else{
            view.setUnit(productInfo.getUnit());
            view.setPdnumber(shopCar.getPdnumber());
        }

        if(shopCar.getIsonline() == Quantity.STATE_2){
            view.setLimitid(limitTimeProd.getId());
        }

        if(shopCar.getIsonline() == Quantity.STATE_2){
            view.setStorenum(limitTimeStore.getStorenum());
        }else{
            view.setStorenum(productStore.getPdstorenum());
        }

        view.setStorename(productStore.getStorename());
        view.setWeight(productStore.getWeight());
        view.setMark(productInfo.getMark());
        view.setSelfsupport(productInfo.getSelfsupport());
        view.setCardnum(productInfo.getCardnum());
        view.setMaterial(productInfo.getMaterial());
        view.setDelivertime(shopCar.getDelivertime());
        view.setProtype(shopCar.getProtype());
        view.setIsonline(shopCar.getIsonline());

        // TODO  单价  (此次不应该从购物车表取，应该从商品库存表取出数据重新计算)
        view.setPrice(shopCar.getPrice());
        view.setTotalWeight(view.getWeight().multiply(view.getPdnumber()));

        //不管是什么交易模式，allpay 代表商品总价
        view.setAllpay(shopCar.getPrice().multiply(view.getPdnumber()).setScale(2,BigDecimal.ROUND_HALF_UP));
        view.setPartpay(shopCar.getPartpay());
        view.setYupay(shopCar.getYupay());

        return view;
    }



    public Map<String,Object> getDefaultFreightMethod(ShippingTemplateGroup shippingTemplateGroup){
        Map<String,Object> map = new HashMap<>();

        if (shippingTemplateGroup.getExpresspay()) {
            map.put("defaultmethod","快递");
            map.put("shippingTemplateId",shippingTemplateGroup.getExpretemp());
            return map;
        }


        if (shippingTemplateGroup.getExprehousehoid()) {
            map.put("defaultmethod","物流到户");
            map.put("shippingTemplateId",shippingTemplateGroup.getExprehousetemp());
            return map;
        }

        if (shippingTemplateGroup.getExpreselflifting()) {
            map.put("defaultmethod","物流自提");
            map.put("shippingTemplateId",shippingTemplateGroup.getExpreselftemp());
            return map;
        }



        if (shippingTemplateGroup.getSfarrivepay()) {
            map.put("defaultmethod","顺丰到付");
            map.put("shippingTemplateId",ShippingTemplateMethod.Sfarrivepay);
            return map;

        }

        if (shippingTemplateGroup.getSelflifting()) {
            map.put("defaultmethod","自提");
            map.put("shippingTemplateId",ShippingTemplateMethod.Selflifting);
            return map;
        }

        return map;
    }



}
