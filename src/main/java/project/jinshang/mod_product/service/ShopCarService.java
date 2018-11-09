package project.jinshang.mod_product.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import mizuki.project.core.restserver.config.BasicRet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.common.bean.MemberLogOperator;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.common.constant.Quantity;
import project.jinshang.common.utils.JinshangUtils;
import project.jinshang.mod_activity.LimitTimeProdMapper;
import project.jinshang.mod_activity.LimitTimeStoreMapper;
import project.jinshang.mod_activity.bean.LimitTimeProd;
import project.jinshang.mod_activity.bean.LimitTimeProdExample;
import project.jinshang.mod_activity.bean.LimitTimeStore;
import project.jinshang.mod_activity.bean.LimitTimeStoreExample;
import project.jinshang.mod_activity.service.LimitTimeProdService;
import project.jinshang.mod_admin.mod_transet.service.TransactionSettingService;
import project.jinshang.mod_invoice.InvoiceInfoMapper;
import project.jinshang.mod_invoice.bean.InvoiceInfo;
import project.jinshang.mod_invoice.bean.InvoiceInfoExample;
import project.jinshang.mod_member.bean.Member;
import project.jinshang.mod_member.bean.MemberRateSetting;
import project.jinshang.mod_product.*;
import project.jinshang.mod_product.bean.*;
import project.jinshang.mod_shippingaddress.ShippingAddressMapper;
import project.jinshang.mod_shippingaddress.bean.ShippingAddress;
import project.jinshang.mod_shippingaddress.bean.ShippingAddressExample;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/14.
 */
@Service
public class ShopCarService {

    @Autowired
    private ShopCarMapper shopCarMapper;

    @Autowired
    private ProductInfoMapper productInfoMapper;

    @Autowired
    private ProductStoreMapper productStoreMapper;

    @Autowired
    private AreaCostMapper areaCostMapper;

    @Autowired
    private ShippingTemplatesMapper shippingTemplatesMapper;

    @Autowired
    private ShippingAddressMapper shippingAddressMapper;

    @Autowired
    private InvoiceInfoMapper invoiceInfoMapper;

    @Autowired
    private BrandMapper brandMapper;

    @Autowired
    private LimitTimeProdMapper limitTimeProdMapper;

    @Autowired
    private LimitTimeStoreMapper limitTimeStoreMapper;

    /**
     * 修改限时购商品
     * @param limitTimeProd
     */
    public void updateLimitTimeProd(LimitTimeProd limitTimeProd){
        limitTimeProdMapper.updateByPrimaryKeySelective(limitTimeProd);
    }

    /**
     * 修改限时购库存
     * @param limitTimeStore
     */
    public void updateLimitTimeStore(LimitTimeStore limitTimeStore){
        limitTimeStoreMapper.updateByPrimaryKeySelective(limitTimeStore);
    }

    /**
     * 获取限时购仓库
     * @param limitId
     * @param pdid
     * @param pdno
     * @return
     */
    public LimitTimeStore getLimitTimeStore(Long limitId,Long pdid,String pdno){
        LimitTimeStoreExample limitTimeStoreExample = new LimitTimeStoreExample();
        limitTimeStoreExample.createCriteria().andLimittimeidEqualTo(limitId).andPdidEqualTo(pdid).andPdnoEqualTo(pdno);
        List<LimitTimeStore> list = limitTimeStoreMapper.selectByExample(limitTimeStoreExample);
        if(list.size()>0){
            return list.get(0);
        }else {
            return null;
        }
    }

    /**
     * 获取限时购仓库
     * @param storeid
     * @return
     */
    public LimitTimeStore getLimitTimeStore(Long storeid){
        return limitTimeStoreMapper.selectByPrimaryKey(storeid);
    }


    /**
     * 根据用户id和产品id查询购物车的数量
     * @param pdid
     * @param memberid
     * @return
     */
    public List<ShopCar> getLimitShopCarNum(Long pdid,Long memberid){
        ShopCarExample shopCarExample = new ShopCarExample();
        shopCarExample.createCriteria().andPdidEqualTo(pdid).andMemberidEqualTo(memberid).andIsonlineEqualTo(Quantity.STATE_2);
        return shopCarMapper.selectByExample(shopCarExample);
    }

    /**
     * 根据用户id和产品id查询购物车的数量
     * @param pdid
     * @param memberid
     * @return
     */
    public ShopCar getLimitShopCarNum(Long pdid,Long memberid,String pdno){
        ShopCarExample shopCarExample = new ShopCarExample();
        shopCarExample.createCriteria().andPdidEqualTo(pdid).andMemberidEqualTo(memberid).andIsonlineEqualTo(Quantity.STATE_2).andPdnoEqualTo(pdno);
        List<ShopCar> list = shopCarMapper.selectByExample(shopCarExample);
        if(list.size()>0){
            return list.get(0);
        }else {
            return  null;
        }
    }

    /**
     * 获取限时购商品
     * @param pdid
     * @return
     */
    public LimitTimeProd getLimitTimeProd(Long pdid,Long limitid){
        LimitTimeProdExample limitTimeProdExample = new LimitTimeProdExample();
        limitTimeProdExample.createCriteria().andIdEqualTo(limitid).andPdidEqualTo(pdid);
        List<LimitTimeProd> list = limitTimeProdMapper.selectByExample(limitTimeProdExample);
        if(list.size()>0){
            return list.get(0);
        }else {
            return null;
        }
    }


    public Brand getBrandById(long id){
        return brandMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取用户开票信息
     * @param memberid
     * @return
     */
    public List<InvoiceInfo> getInvoiceInfoList(long memberid){
        InvoiceInfoExample invoiceInfoExample = new InvoiceInfoExample();
        invoiceInfoExample.createCriteria().andMemberidEqualTo(memberid);
        return invoiceInfoMapper.selectByExample(invoiceInfoExample);
    }

    /**
     *  根据用户id和商品id查询购物车
     * @param pdid
     * @param memberid
     * @param deliverTime
     * @return
     */
    public ShopCar getMemberShopCar(long pdid,long memberid,String deliverTime,String pdno,Short protype){
        ShopCarExample shopCarExample = new ShopCarExample();
        shopCarExample.createCriteria().andPdidEqualTo(pdid).andMemberidEqualTo(memberid).andDelivertimeEqualTo(deliverTime).andPdnoEqualTo(pdno).andIsonlineEqualTo(Quantity.STATE_0).andProtypeEqualTo(protype);
        List<ShopCar> list = shopCarMapper.selectByExample(shopCarExample);
        if(list.size()>0){
            return list.get(0);
        }else{
            return null;
        }
    }

    /**
     * 获取用户收货地址
     * @param memberid
     * @return
     */
    public ShippingAddress getShippingAddress(long memberid){
        ShippingAddressExample shippingAddressExample = new ShippingAddressExample();
        shippingAddressExample.createCriteria().andMemberidEqualTo(memberid);
        shippingAddressExample.createCriteria().andIsdefaultEqualTo(Quantity.STATE_0);
        List<ShippingAddress> list = shippingAddressMapper.selectByExample(shippingAddressExample);
        if(list.size()>Quantity.INT_0){
            return list.get(0);
        }else {
            return null;
        }
    }

    /**
     * 获取运费模板
     * @param id 模板id
     * @return
     */
    public ShippingTemplates getShippingTemp(long id){
        return shippingTemplatesMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取地区运费模板
     * @param id 模板id
     * @return
     */
//    public AreaCost getAreaCost(long id){
//        AreaCostExample areaCostExample  = new AreaCostExample();
//        areaCostExample.createCriteria().andTemidEqualTo(id);
//        List<AreaCost> list = areaCostMapper.selectByExample(areaCostExample);
//        if(list.size()>Quantity.INT_0){
//            return list.get(0);
//        }else{
//            return null;
//        }
//    }
//    public List<AreaCost> getAreaCost(long id){
//        AreaCostExample areaCostExample  = new AreaCostExample();
//        areaCostExample.createCriteria().andTemidEqualTo(id);
//        List<AreaCost> list = areaCostMapper.selectByExample(areaCostExample);
//        return  list;
//    }


    /**
     * 根据id查询商品信息
     * @param id
     * @return
     */
    public ProductInfo getProductInfo(Long id){
        ProductInfo info = productInfoMapper.selectByPrimaryKey(id);
        return info;
    }

    /**
     * 根据商品id和仓库id获取ProductStore
     * @param pdid
     * @param storeid
     * @return
     */
    public ProductStore getProductStore(Long pdid,String pdno,Long storeid){
//        ProductStoreExample productStoreExample = new ProductStoreExample();
//        productStoreExample.createCriteria().andPdidEqualTo(pdid).andStoreidEqualTo(storeid).andPdnoEqualTo(pdno);
//        List<ProductStore> psList = productStoreMapper.selectByExample(productStoreExample);
//        if(psList.size()>Quantity.INT_0){
//            return psList.get(0);
//        }
//        return null;

        return  productStoreMapper.getProductStore(pdid,pdno,storeid);
    }


    /**
     * 根据商品id和商品pdno获取ProductStore
     * @param pdid
     * @param pdno
     * @return
     */
//    public ProductStore getProductStore(Long pdid,String pdno){
//        ProductStoreExample productStoreExample = new ProductStoreExample();
//        productStoreExample.createCriteria().andPdidEqualTo(pdid).andPdnoEqualTo(pdno);
//        List<ProductStore> psList = productStoreMapper.selectByExample(productStoreExample);
//        if(psList.size()>Quantity.INT_0){
//            return psList.get(0);
//        }
//        return null;
//    }

    /**
     * 新增商品到购物车
     * @param shopCar
     */
    public void insertShopCar(ShopCar shopCar){
        shopCarMapper.insertSelective(shopCar);
    }

    /**
     * 更新商品
     * @param shopCar
     */
    public void updateShopCar(ShopCar shopCar){
        shopCarMapper.updateByPrimaryKeySelective(shopCar);
    }

    /**
     * 加载购物车商品
     */
    public PageInfo loadAllShipCar(Long memberid,int pageNo ,int pageSize){
        PageHelper.startPage(pageNo,pageSize);
        List<Map<String,Object>> list = shopCarMapper.loadShopCar(memberid);
        for(Map<String,Object> map : list){
            if(map.get("packagetype") != null){
                map.put("packages",  JinshangUtils.toCovertPacking(map.get("packagetype").toString()));
                map.put("packageStr",JinshangUtils.packageToString((String) map.get("packagetype"),((BigDecimal)map.get("pdnumber")),(String) map.get("unit")));
//                ProductStore productStore = getProductStore((Long)map.get("pdid"), (String) map.get("pdno"));
//                if (productStore != null) {
//                    //库存不足
//                    if ((productStore.getPdstorenum().compareTo((BigDecimal)map.get("pdnumber"))) == Quantity.STATE_) {
//                        map.put("pdnumber",productStore.getPdstorenum());
//                    }
//                }
            }


            if(map.get("isonline")!= null && (int)map.get("isonline") == 2){  //限时购，取限时购库存
                LimitTimeStore limitTimeStore = this.getLimitTimeStore((Long) map.get("limitid"),(Long) map.get("pdid"),(String) map.get("pdno"));
                map.put("pdstorenum",limitTimeStore.getStorenum());
            }


        }
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    public List<Map<String,Object>> loadSelectProduct(String shopcarids,Long memberid){
        List<Map<String,Object>>  list = shopCarMapper.loadSelectProduct(shopcarids,memberid);
        return list;
    }

    /**
     * 根据id获取购物车
     * @param id
     * @return
     */
    public ShopCar getShopCarByPrimeKey(long id){
        return shopCarMapper.selectByPrimaryKey(id);
    }

    /**
     * 删除单个商品
     * @param id
     */
    public void deleteShopCar(Long id){
        shopCarMapper.deleteByPrimaryKey(id);
    }

    /**
     * 批量删除商品
     * @param ids
     */
    public int deleteAll(List<Long> ids){
       return shopCarMapper.deleteAll(ids);
    }
}
