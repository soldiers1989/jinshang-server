package project.jinshang.mod_product.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import mizuki.project.core.restserver.config.BasicRet;
import mizuki.project.core.restserver.util.JsonUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import project.jinshang.common.bean.Page;
import project.jinshang.common.utils.MD5Tools;
import project.jinshang.mod_company.bean.SellerCompanyInfo;
import project.jinshang.mod_company.bean.Store;
import project.jinshang.mod_company.service.StoreService;
import project.jinshang.mod_product.ProductStoreMapper;
import project.jinshang.mod_product.bean.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * create : wyh
 * date : 2017/11/10
 */

@Service
public class ProductStoreService {

    @Value("${shop.self-support.id}")
    private String shopSelfSupportid;

    @Autowired
    private ProductStoreMapper productStoreMapper;

    @Autowired
    private StoreService storeService;
    @Autowired
    private OrdersService ordersService;


    public void add(ProductStore productStore) {
        productStoreMapper.insert(productStore);
    }


    public void insertSelective(ProductStore productStore) {
        productStoreMapper.insertSelective(productStore);
    }

    public void deleteByProductid(long productid) {
        productStoreMapper.deleteByProductid(productid);
    }


    public List<ProductStore> getByProductid(long productid) {
        return productStoreMapper.getByProductid(productid);
    }


    public void updateById(ProductStore productStore) {
        productStoreMapper.updateByPrimaryKey(productStore);
    }


    public int countByExample(ProductStoreExample example) {
        return productStoreMapper.countByExample(example);
    }


    /**
     * 根据商品id修改库存
     *
     * @param pid
     * @param storenum
     */
    public void updatePdstorenumByPid(long pid, BigDecimal storenum) {
        productStoreMapper.updatePdstorenumByPid(pid, storenum);
    }


    public int countByMemberidAndPdno(long memberid, String pdno) {
        return productStoreMapper.countByMemberidAndPdno(memberid, pdno);
    }


    public ProductStore getByMemberidAndPdno(long memberid, String pdno) {
        return productStoreMapper.getByMemberidAndPdno(memberid, pdno);
    }

    /**
     * 根据商品id 和 商品编号获取商品库存信息
     *
     * @param pdid
     * @param pdno
     * @return
     */
    public ProductStore getByPdidAndPdno(long pdid, String pdno) {
        return productStoreMapper.getByPdidAndPdno(pdid, pdno);
    }


    /**
     * 减库存
     *
     * @param pdid
     * @param pdno
     * @param pdstorenum
     */
    public void decrStoreNumByPdidAndPdno(long pdid, String pdno, BigDecimal pdstorenum) {
        productStoreMapper.decrStoreNumByPdidAndPdno(pdid, pdno, pdstorenum);
    }

    /**
     * 加库存
     *
     * @param pdid
     * @param pdno
     * @param pdstorenum
     */
    public void addStoreNumByPdidAndPdno(long pdid, String pdno, BigDecimal pdstorenum) {
        productStoreMapper.addStoreNumByPdidAndPdno(pdid, pdno, pdstorenum);
    }


    public ProductStore selectByProductidForFastener(long pdid) {
        return productStoreMapper.selectByProductidForFastener(pdid);
    }

    /**
     * 查询某个用户是否在某个仓库下是否存在某个商品
     *
     * @param memberid
     * @param storeid
     * @param pdno
     * @return
     */
    public ProductStore getByStoreidAndPdNo(long memberid, long storeid, String pdno) {
        return productStoreMapper.getByStoreidAndPdNo(memberid, storeid, pdno);
    }


    /**
     *根据仓库编码、商品唯一特征码进行查询
     * @author xiazy
     * @date  2018/6/7 15:50
     * @param storeid 仓库编码
     * @param pdno 商品唯一特征码进行查询
     * @return project.jinshang.mod_product.bean.ProductStore
     */
    public ProductStore getByStoreidAndPdNo(Long storeid,String pdno){
        return productStoreMapper.getByStoreidAndPdNoForStockSyn(storeid,pdno);
    }


    /**
     * 更新仓库商品信息
     *
     * @param storeid
     * @param pdno
     * @param num
     * @return
     */
    public int updateNumByStoreidAndPdno(long storeid, String pdno, BigDecimal num) {
        return productStoreMapper.updateNumByStoreidAndPdno(storeid, pdno, num);
    }

//    public int updateNumByStoreidAndPdno(String pdno, BigDecimal num) {
//        List<String> shopSelfSupportIds = Arrays.asList(shopSelfSupportid.split("\\|"));
//        String s = "";
//        for (int i = 0; i < shopSelfSupportIds.size(); i++) {
//            if (i == 0 && !"6700".equals(shopSelfSupportIds.get(i))) {
//                s = s + "(" + shopSelfSupportIds.get(i);
//            } else if (!"6700".equals(shopSelfSupportIds.get(i))) {
//                s = s + "," + shopSelfSupportIds.get(i);
//            }
//        }
//        s = s + ")";
//        return productStoreMapper.updateProductStoreNum(pdno, num, s);
//    }

    public int updateNumByMemberidStoreidAndPdno(Long memberid,Long storeid,String pdno, BigDecimal num) {
        return  productStoreMapper.updateNumByMemberidStoreidAndPdno(memberid,storeid,pdno,num);
    }

//    public int updateNumByMemberidStoreidAndPdno(Long memberid,Long storeid,String pdno, BigDecimal num) {
//        return  productStoreMapper.updateNumByMemberidStoreidAndPdno(memberid,storeid,pdno,num);
//    }


    public int updateNumByStoreidAndPdno(Long memberid,String pdno, BigDecimal num) {
        return productStoreMapper.updateProductStoreNum(pdno, num, "("+String.valueOf(memberid)+")");
    }


    public int updateStorenameByStoreid(long storeid, String name) {
        return productStoreMapper.updateStorenameByStoreid(storeid, name);
    }



    public ProductStore getProductStore(long pdid, String pdno, long storeid) {
        return  productStoreMapper.getProductStore(pdid,pdno,storeid);
    }

    /**
     *库存同步(主动)
     * @author xiazy
     * @date  2018/6/7 16:29
     * @param sellerId 卖家id
     * @return mizuki.project.core.restserver.config.BasicRet
     */
    public BasicRet initiativeStockSyn(Long sellerId){
        BasicRet basicRet=new BasicRet();
        //暂时直接调用库存同步的共用方法--暂时没有其他地方直接发起主动库存同步
        basicRet=this.stockSynCom(sellerId);
        return basicRet;
    }


    /**
     *库存同步(主动)/库存同步(被动)的共方法
     * @author xiazy
     * @date  2018/6/6 17:59
     * @param sellerId 卖家的id
     * @return mizuki.project.core.restserver.config.BasicRet
     */
    public BasicRet stockSynCom(Long sellerId){
        BasicRet basicRet=new BasicRet();
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("库存同步主体方法调用成功");
        List<Store> storeList=storeService.getAllByMember(sellerId);
        Map<String,Object> retmap=ordersService.verification(sellerId);
        if (((BasicRet)retmap.get("basicRet")).getResult()!=1){
            basicRet= (BasicRet)retmap.get("basicRet");
            return basicRet;
        }
//        PageInfo pageInfo = productStoreService.getCreditRecordList(member.getId(), creditApplyRecord, pageNo, pageSize);
//        buyerCreditRet.data.pageInfo = pageInfo;
//        buyerCreditRet.setMessage("返回成功");
//        buyerCreditRet.setResult(BasicRet.SUCCESS);
//        return buyerCreditRet;
        Map<String,Object> sysParam=new HashMap<>();
        String pdnoStr="";
        String jsonParam="";
        SellerCompanyInfo sellerCompanyInfo= (SellerCompanyInfo) retmap.get("sellerCompanyInfo");
        String appId=sellerCompanyInfo.getAppid();
        String appSecret=sellerCompanyInfo.getAppsecret();
        String appUrl=sellerCompanyInfo.getAppurl();
        Long timestamp=System.currentTimeMillis();
        String notify= MD5Tools.MD5(appId+appSecret+timestamp);
        Js006 js006=new Js006();
        js006.setAppid(appId);
        js006.setNotify(notify);
        js006.setType(DockType.JS006.getType());
        js006.setExtendjson(null);
        js006.setTimestamp(String.valueOf(timestamp));
        if (storeList!=null&&storeList.size()>0){
            for (Store store:storeList) {
                Long storeId=store.getId();
                js006.setStore(String.valueOf(storeId));
                PageInfo<ProductStore> productStorePageInfo=this.selectProductStoreForSyn(0,0,storeId,sellerId,DockType.Constan.ONSHEWLF.getValue());
                int totalPageSize=productStorePageInfo.getPages();
                if (totalPageSize>0){
                    for(int i=1;i<=totalPageSize;i++){
                        PageInfo<ProductStore> currentPageInfo=this.selectProductStoreForSyn(i,DockType.Constan.PAGESIZE.getValue(),storeId,sellerId,DockType.Constan.ONSHEWLF.getValue());
                        int size=currentPageInfo.getSize();
                        //计算出商品明细JSON
                        pdnoStr="";
                        for (ProductStore productStore:currentPageInfo.getList()) {
                            pdnoStr=pdnoStr+productStore.getPdno()+",";
                        }
                        Map<String,Object> pdnoMap=new HashMap<>();
                        pdnoMap.put("pdno",pdnoStr);
                        js006.setPdjson(JsonUtil.toJson(pdnoMap));
                        Page page=new Page();
                        page.setPageNo(i);
                        page.setPageSize(size);
                        js006.setPagejson(JsonUtil.toJson(page));
                        jsonParam=JsonUtil.toJson(js006);
                        ordersService.sendToMiddleManage(appUrl,JsonUtil.toMap(jsonParam));
                    }
                }
            }
            return basicRet;
        }else{
            basicRet.setMessage("没有获取对应卖家的所属仓库");
            basicRet.setResult(BasicRet.ERR);
            return basicRet;
        }
    }

    /**
     *查询需要进行库存同步的商品
     * @author xiazy
     * @date  2018/6/7 10:59
     * @param storeid 仓库的编码
     * @param memberid 卖家的id
     * @param pdstate 商品的状态
     * @return java.util.List<project.jinshang.mod_product.bean.ProductInfo>
     */
    public PageInfo selectProductStoreForSyn(int pageNo, int pageSize, Long storeid, Long memberid, int pdstate){
        if (pageNo>0&&pageSize>0){
            PageHelper.startPage(pageNo,pageSize);
        }
        List<ProductStore> productStoreList=productStoreMapper.selectProductStoreForSyn(storeid,memberid,pdstate);
        PageInfo pageInfo=new PageInfo(productStoreList);
        return pageInfo;
    }





    public  Long getPdidByStoreidAndPdno( Long storeid, String pdno){
       return   productStoreMapper.getPdidByStoreidAndPdno(storeid,pdno);
    }

}
