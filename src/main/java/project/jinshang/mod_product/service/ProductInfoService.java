package project.jinshang.mod_product.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.common.utils.ErrorMes;
import project.jinshang.common.utils.StringUtils;
import project.jinshang.mod_product.ProductInfoMapper;
import project.jinshang.mod_product.bean.*;
import project.jinshang.mod_product.bean.dto.ProdUnitRateViewDto;

import java.math.BigDecimal;
import java.util.*;

/**
 * create : wyh
 * date : 2017/11/10
 */
@Service
public class ProductInfoService {

    @Autowired
    private ProductInfoMapper productInfoMapper;

    @Autowired
    private ProductStoreService productStoreService;

    public long add(ProductInfo productInfo) {
        return productInfoMapper.insert(productInfo);
    }

    public void insertSelective(ProductInfo productInfo) {
        productInfoMapper.insertSelective(productInfo);
    }


    public ProductInfo getById(long id) {
        return productInfoMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取某个用户的上架的商品
     *
     * @param memberid
     * @param id
     * @return
     */
    public ProductInfo getGroundingById(long memberid, long id) {
        return productInfoMapper.getGroundingById(memberid, id);
    }


    public Map<String, Object> getProductInfoById(long id) {
        return productInfoMapper.selectViewCookieByPId(id);
    }


    public void deleteById(long id) {
        productInfoMapper.deleteByPrimaryKey(id);
    }


    /**
     * 根据条件搜索产品信息(紧固件)
     *
     * @param pageNo
     * @param pageSize
     * @param productInfo
     * @return
     */
    public PageInfo listFastenerProduct(int pageNo, int pageSize, ProductInfoQuery productInfo) {
        PageHelper.startPage(pageNo, pageSize);
        List<Map<String, Object>> list = productInfoMapper.fastenerList(productInfo);

        //判断最高价格
        for (Map<String, Object> map : list) {
            List<BigDecimal> priceList = new ArrayList<>();
            priceList.add((BigDecimal) map.get("prodprice") == null ? new BigDecimal(0) : (BigDecimal) map.get("prodprice"));
            priceList.add((BigDecimal) map.get("threeprice") == null ? new BigDecimal(0) : (BigDecimal) map.get("threeprice"));
            priceList.add((BigDecimal) map.get("ninetyprice") == null ? new BigDecimal(0) : (BigDecimal) map.get("ninetyprice"));
            priceList.add((BigDecimal) map.get("thirtyprice") == null ? new BigDecimal(0) : (BigDecimal) map.get("thirtyprice"));
            priceList.add((BigDecimal) map.get("sixtyprice") == null ? new BigDecimal(0) : (BigDecimal) map.get("sixtyprice"));

            map.put("highprice", Collections.max(priceList));

        }


        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    /**
     * 多规格筛选
     * @param productInfo
     * @return
     */
    public List MultiSpecification(ProductInfoQuery productInfo){

        List<Map<String, Object>> list = productInfoMapper.MultiSpecification(productInfo);
        List<String> key = new ArrayList<String>();
        for(Map<String,Object> m:list){
            key.add(m.get("level3").toString());
        }
        key = removeDuplicate(key);

        List allData = new ArrayList();
        List listData = new ArrayList();
        for(int i=0;i<key.size();i++){
            Map<String,List> map = new HashMap<String,List>();
            List keys = new ArrayList();
            List value= new ArrayList();
            for(int j=0;j<list.size();j++){
                if(key.get(i).equals(list.get(j).get("level3").toString())){
                    if(list.get(j).get("stand")!=null) {
                        String stand = list.get(j).get("stand").toString().replace(" ","").split("\\*")[0];
                        value.add(stand);
                    }
                }
            }
            keys.add(key.get(i));
            value = removeDuplicate(value);
            map.put("name",keys);
            map.put("value",value);

            listData.add(map);
        }
        allData.add(listData);

        return allData;
    }

    //list去重
    public  List removeDuplicate(List list){
        for  ( int  i  =   0 ; i  <  list.size()  -   1 ; i ++ )  {
            for  ( int  j  =  list.size()  -   1 ; j  >  i; j -- )  {
                if  (list.get(j).equals(list.get(i)))  {
                    list.remove(j);
                }
            }
        }
        return list;
    }

    /**
     * 根据条件搜索产品信息(紧固件) 后台Excel导出
     * @param productInfo
     * @return
     */
    public List<Map<String,Object>> listFastenerProductForExcelByAdmin(ProductInfoQuery productInfo) {
        List<Map<String, Object>> list = productInfoMapper.fastenerListForExcelByAdmin(productInfo);

        //用于存放存放入excel的数据
        List<Map<String,Object>> data = new ArrayList<>();

        Gson gson = new Gson();


        for(Map<String,Object> map : list){
            Map<String,Object> resMap = new HashMap<>();

            resMap.put("卖家公司名称",map.get("companyname"));
            resMap.put("商品名称",map.get("productname"));
            resMap.put("别名",map.get("productalias"));
            resMap.put("第三级分类名",map.get("level3"));
            resMap.put("材质",map.get("material"));
            resMap.put("牌号",map.get("cardnum"));
            resMap.put("规格",map.get("stand"));
            resMap.put("品牌",map.get("brand"));
            resMap.put("印记",map.get("mark"));
            resMap.put("表面处理",map.get("surfacetreatment"));
            resMap.put("包装方式",map.get("packagetype"));
            resMap.put("单位",map.get("unit"));
            resMap.put("重量(KG)",map.get("weight"));
            resMap.put("挂牌价",map.get("prodprice"));
            resMap.put("起订量",map.get("startnum"));
            resMap.put("30天价格",map.get("thirtyprice"));
            resMap.put("60天价格",map.get("sixtyprice"));
            resMap.put("90天价格",map.get("ninetyprice"));
            resMap.put("市场价",map.get("marketprice"));
            resMap.put("成本价",map.get("costprice"));
            resMap.put("仓库名称",map.get("storename"));
            resMap.put("仓库地址",map.get("storeaddress"));

            String temname = "包邮";
            if(map.get("freightmode")!=null && !"".equals(map.get("freightmode")) &&(long)map.get("freightmode") != -1){
                temname =  (String) map.get("temname");
            }
            resMap.put("运费方式",temname);
            resMap.put("SEO标题",map.get("seotitle"));
            resMap.put("SEO关键字",map.get("seokey"));
            resMap.put("SEO描述",map.get("seovalue"));
            resMap.put("商品货号",map.get("pdno"));


            String intervalprice = StringUtils.nvl(map.get("intervalprice"));
            List<StepWisePrice> stepWisePrices = gson.fromJson(intervalprice,new TypeToken<List<StepWisePrice>>() {
            }.getType());

            if(stepWisePrices != null && stepWisePrices.size()==3){
                StepWisePrice st1 =  stepWisePrices.get(0);
                StepWisePrice st2 =  stepWisePrices.get(1);
                StepWisePrice st3 =  stepWisePrices.get(2);
                resMap.put("订货区间一",st1.getStart()+"-"+st1.getEnd());
                resMap.put("折扣一",st1.getRate()+"%");
                resMap.put("订货区间二",st2.getStart()+"-"+st2.getEnd());
                resMap.put("折扣二",st2.getRate()+"%");
                resMap.put("订货区间三",st2.getStart()+"以上");
                resMap.put("折扣三",st3.getRate()+"%");
            }else{
                resMap.put("订货区间一","");
                resMap.put("折扣一","");
                resMap.put("订货区间二","");
                resMap.put("折扣二","");
                resMap.put("订货区间三","");
                resMap.put("折扣三","");
            }
            data.add(resMap);
        }
        return  data;
    }

    /**
     * 根据条件搜索产品信息总记录数
     * @param productInfo
     * @return
     */
    public Long getProductInfoCount(ProductInfoQuery productInfo) {
        return productInfoMapper.getProductInfoCount(productInfo);
    }


    /**
     * 根据条件搜索产品信息(紧固件) 卖家Excel导出
     * @param productInfo
     * @return
     */
    public List<Map<String,Object>> listFastenerProductForExcelBySeller(ProductInfoQuery productInfo) {

        List<Map<String, Object>> list = productInfoMapper.fastenerListForExcelBuySeller(productInfo);

        //判断最高价格
//        for (Map<String, Object> map : list) {
//            List<BigDecimal> priceList = new ArrayList<>();
//            priceList.add((BigDecimal) map.get("prodprice") == null ? new BigDecimal(0) : (BigDecimal) map.get("prodprice"));
//            priceList.add((BigDecimal) map.get("threeprice") == null ? new BigDecimal(0) : (BigDecimal) map.get("threeprice"));
//            priceList.add((BigDecimal) map.get("ninetyprice") == null ? new BigDecimal(0) : (BigDecimal) map.get("ninetyprice"));
//            priceList.add((BigDecimal) map.get("thirtyprice") == null ? new BigDecimal(0) : (BigDecimal) map.get("thirtyprice"));
//            priceList.add((BigDecimal) map.get("sixtyprice") == null ? new BigDecimal(0) : (BigDecimal) map.get("sixtyprice"));
//            map.put("highprice", Collections.max(priceList));
//
//        }


        return  list;
    }



//


    public void updateById(ProductInfo productInfo) {
        productInfoMapper.updateByPrimaryKey(productInfo);
    }


    public void updateByPrimaryKeySelective(ProductInfo productInfo) {
        productInfoMapper.updateByPrimaryKeySelective(productInfo);
    }


    public List<ProductInfo> listProductByExample(ProductInfoExample example) {
        return productInfoMapper.selectByExample(example);
    }

    public PageInfo getListProduct(ProductInfo productInfo,int pageNo, int pageSize){
        PageHelper.startPage(pageNo, pageSize);
        List<ProductInfo> list = productInfoMapper.getListProduct(productInfo);
        return new PageInfo(list);
    }


    public PageInfo getByPage(ProductInfoExample example, int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        List<ProductInfo> list = listProductByExample(example);
        return new PageInfo(list);
    }


    public int countByExample(ProductInfoExample example) {
        return productInfoMapper.countByExample(example);
    }

    /**
     * 查询商品信息和商品库存信息
     *
     * @param id
     * @return
     */
    public ProductInfo getProductInfoWithStore(long id) {
        ProductInfo productInfo = productInfoMapper.selectByPrimaryKey(id);

        if (productInfo == null) {
            return null;
        }
        ProductStore productStore = productStoreService.selectByProductidForFastener(id);
        productInfo.setProductStore(productStore);
        return productInfo;
    }


    public PageInfo getProductInfoWithRate(
            int pageNo, int pageSize,
            String productname,
            String brand,
            int level1id,
            int level2id,
            int level3id,
            String material,
            String cardnum,
            String mark) {
        PageHelper.startPage(pageNo, pageSize);
        List<Map> list = productInfoMapper.getProductInfoWithRate(productname, brand, level1id, level2id, level3id, material, cardnum, mark);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }


    /**
     * 用于校验产品发布的一些关键字段
     *
     * @param productInfo
     * @return
     */
    public ErrorMes checkProductInfo(ProductInfo productInfo, ProductStore productStore) {
        ErrorMes errorMes = new ErrorMes();

        if (productInfo.getLevel1id() <= 0 ||
                productInfo.getLevel2id() <= 0 ||
                productInfo.getLevel3id() <= 0 ||
                !StringUtils.hasText(productInfo.getLevel1()) ||
                !StringUtils.hasText(productInfo.getLevel2()) ||
                !StringUtils.hasText(productInfo.getLevel3())) {
            errorMes.addError("level", "分类选择不正确");
        }

        if (productInfo.getMaterialid() == null || productInfo.getMaterialid() <= 0 || !StringUtils.hasText(productInfo.getMaterial())) {
            errorMes.addError("materialid", "请选择材质");
        }


        if (productInfo.getCardnumid() == null || productInfo.getCardnumid() <= 0 || !StringUtils.hasText(productInfo.getCardnum())) {
            errorMes.addError("cardnumid", "请选择牌号");
        }


        if (productInfo.getBrandid() == null || productInfo.getBrandid() <= 0 || !StringUtils.hasText(productInfo.getBrand())) {
            errorMes.addError("brandid", "请选择品牌");

        }


        if (!StringUtils.hasText(productInfo.getProducttype())) {
            errorMes.addError("producttype", "请选择产品类型");
        }

        if (!StringUtils.hasText(productStore.getStoreunit())) {
            errorMes.addError("storeunit", "请填写计量单位");
        }

        if (productInfo.getWeight() == null) {
            errorMes.addError("weight", "请填写重量");
        }


//        if(productStore.getStepwiseprice() == true && (productStore.getThreeprice()==null &&
//            productStore.getSevenprice()==null && productStore.getThirtyprice()==null && productStore.getSixtyprice()==null)){
//            errorMes.addError("stepwiseprice","请填写阶梯价格");
//        }else{
//
//        }


        if (productStore.getProdprice() == null) {
            errorMes.addError("prodprice", "请填写商品价格");
        }


        if (productStore.getMarketprice() == null) {
            errorMes.addError("marketprice", "请填写市场价");
        }

        if (productStore.getPdstorenum().compareTo(new BigDecimal(0)) == -1) {
            errorMes.addError("pdstorenum", "请填写商品库存");
        }

//        String[] pdPic = (String[]) productInfo.getPdpicture();
//        if(pdPic== null || pdPic.length==0){
//            errorMes.addError("pdpicture","请上传产品图片");
//        }


        if (productStore.getStoreid() <= 0
                || !StringUtils.hasText(productStore.getStorename())
                ) {
            errorMes.addError("storeid", "请选择仓库");
        }

//        if (productStore.getFreightmode() == null || productStore.getFreightmode() < -1) {
//            errorMes.addError("freightmode", "运费模版选择不正确");
//        }

        if (productStore.getDiscountratio() == null) {
            errorMes.addError("Discountratio", "请填写折扣比例");
        }


        return errorMes;
    }


    /**
     * 根据商品编号获取自营店产品
     *
     * @param pdno
     * @param memberid
     * @return
     */
    public Map<String, Object> getShopSelfProdByPdno(String pdno, Long memberid) {
        return productInfoMapper.getShopSelfProdByPdno(pdno, memberid);
    }


    public ProdUnitRateViewDto getProdUnitRate(long id) {
        return productInfoMapper.getProdUnitRate(id);
    }



    public List<Map<String,Object>> getFloorProducts(String pdno, String username,
                                                     String brand, String productname,
                                                     String stand,String store){
        return  productInfoMapper.getFloorProducts(pdno,username,brand,productname,stand,store);
    }



    public  List<Map<String,Object>> getProdRatePriceByPids(Long[] pids){

        if(pids == null || pids.length ==0){
            return new ArrayList<>();
        }

        StringBuilder temp = new StringBuilder();

        for(int i=0;i<pids.length;i++){
            temp.append(pids[i]);
            if(i != pids.length-1){
                temp.append(",");
            }
        }

        return  productInfoMapper.getProdRatePriceByPids(temp.toString());

    }


    /**
     *
     * @param products
     * @return
     */
    public int updateImgByProductsno(Products products){
        return  productInfoMapper.updateImgByProductsno(products);
    }


    /**
     * 紧固件产品库修改重量后，商品信息也要修改
     * @param weight
     * @param productid
     * @return
     */
    public  int updateWeightByProductsid( BigDecimal weight,  Long productid){
        return productInfoMapper.updateWeightByProductsid(weight,productid);
    }


//    public List<ProductInfo> getProductInfoByProductId(long productid) {
//        return productInfoMapper.getProductInfoByProductId(productid);
//    }
}
