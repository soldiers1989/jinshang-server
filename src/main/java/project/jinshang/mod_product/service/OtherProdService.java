package project.jinshang.mod_product.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.common.utils.StringUtils;
import project.jinshang.mod_product.bean.ProductAttr;
import project.jinshang.mod_product.bean.StepWisePrice;
import project.jinshang.mod_product.bean.dto.OtherProdDetailViewDto;
import project.jinshang.mod_product.bean.dto.OtherProdStore;
import project.jinshang.mod_product.bean.dto.OtherProdStoreForExcel;
import project.jinshang.mod_product.bean.dto.OtherProductQueryDto;
import project.jinshang.mod_product.mapper.OtherProdMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * create : wyh
 * date : 2018/1/4
 */

@Service
public class OtherProdService {

    @Autowired
    private  ProductStoreService productStoreService;

    @Autowired
    private  ProductAttrService productAttrService;

    @Autowired
    private OtherProdMapper otherProdMapper;


    /**
     * 获取非紧固件类商品库存列表
     * @param pdid
     * @return
     */
    public List<OtherProdStore> getOtherProdStore(Long pdid){
        return  otherProdMapper.getOtherProdStore(pdid);
    }

    /**
     * 非紧固件商品列表
     * @param queryDto
     * @param pageNo
     * @param pageSize
     * @return
     */
    public PageInfo listOtherProd(OtherProductQueryDto queryDto, int pageNo, int pageSize){
        PageHelper.startPage(pageNo,pageSize);
        List<OtherProdDetailViewDto> list = otherProdMapper.listOtherProd(queryDto);
        PageInfo pageInfo =  new PageInfo(list);
        return  pageInfo;
    }


    /**
     * 卖家excel导出非紧固件商品
     * @param queryDto
     * @return
     */
    public List<Map<String,Object>> listOtherProdForSellerExportExcel(OtherProductQueryDto queryDto){

        List<Map<String,Object>> list = otherProdMapper.listOtherProdForSellerExcel(queryDto);


        //用于存放存放入excel的数据
        List<Map<String,Object>> data = new ArrayList<>();

        Gson gson = new Gson();

        for(Map<String,Object> map : list){
            List<OtherProdStoreForExcel> storeList = otherProdMapper.getOtherProdStoreForExportExcel((Long) map.get("id"));
            for(OtherProdStoreForExcel store : storeList){
                Map<String,Object> resMap = new HashMap<>();
                resMap.put("卖家公司名称",map.get("companyname"));
                resMap.put("商品名称",map.get("productname"));
                resMap.put("别名",map.get("productalias"));
                resMap.put("品牌",map.get("brand"));
                resMap.put("单位",map.get("unit"));
                resMap.put("重量(KG)",map.get("weight"));
                resMap.put("SEO标题",map.get("seotitle"));
                resMap.put("SEO关键字",map.get("seokey"));
                resMap.put("SEO描述",map.get("seovalue"));

                resMap.put("挂牌价(3天价)",store.getProdprice());
                resMap.put("起订量",store.getStartnum());
                resMap.put("30天价格",store.getThirtyprice());
                resMap.put("60天价格",store.getSixtyprice());
                resMap.put("90天价格",store.getNinetyprice());
                resMap.put("市场价",store.getMarketprice());
                resMap.put("成本价",store.getCostprice());
                resMap.put("仓库名称",store.getStorename());
                resMap.put("仓库地址",store.getStoreaddress());

                resMap.put("商品编码",store.getPdno());

                String temname = "包邮";
                if(store.getFreightmode() != -1){
                    temname =  store.getTemname();
                }
                resMap.put("运费方式",temname);

                String intervalprice = StringUtils.nvl(store.getIntervalprice());
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

                StringBuilder stand = new StringBuilder();
                List<ProductAttr> attrList =  productAttrService.getListByPidAndPdno(store.getPdid(),store.getPdno());
                for(int i=0;i<attrList.size();i++){
                    stand.append(attrList.get(i).getValue());
                    if(i !=(attrList.size()-1)){
                        stand.append("/");
                    }
                }

                resMap.put("规格",stand.toString());

                data.add(resMap);

            }
        }


        return  data;


    }





    public List<Map<String,Object>> listOtherProdForAdminExcle(OtherProductQueryDto queryDto){
        List<Map<String,Object>> list = otherProdMapper.listOtherProdForAdminExcel(queryDto);


        //用于存放存放入excel的数据
        List<Map<String,Object>> data = new ArrayList<>();

        Gson gson = new Gson();

        for(Map<String,Object> map : list){

            List<OtherProdStoreForExcel> storeList = otherProdMapper.getOtherProdStoreForExportExcel((Long) map.get("id"));
            for(OtherProdStoreForExcel store : storeList){
                Map<String,Object> resMap = new HashMap<>();
                resMap.put("卖家公司名称",map.get("companyname"));
                resMap.put("商品名称",map.get("productname"));
                resMap.put("别名",map.get("productalias"));
                resMap.put("品牌",map.get("brand"));
                resMap.put("单位",map.get("unit"));
                resMap.put("重量(KG)",map.get("weight"));
                resMap.put("SEO标题",map.get("seotitle"));
                resMap.put("SEO关键字",map.get("seokey"));
                resMap.put("SEO描述",map.get("seovalue"));

                resMap.put("挂牌价(3天价)",store.getProdprice());
                resMap.put("起订量",store.getStartnum());
                resMap.put("30天价格",store.getThirtyprice());
                resMap.put("60天价格",store.getSixtyprice());
                resMap.put("90天价格",store.getNinetyprice());
                resMap.put("市场价",store.getMarketprice());
                resMap.put("成本价",store.getCostprice());
                resMap.put("仓库名称",store.getStorename());
                resMap.put("仓库地址",store.getStoreaddress());

                resMap.put("商品编码",store.getPdno());

                String temname = "包邮";
                if(store.getFreightmode() != -1){
                    temname =  store.getTemname();
                }
                resMap.put("运费方式",temname);

                String intervalprice = StringUtils.nvl(store.getIntervalprice());
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

                StringBuilder stand = new StringBuilder();
                List<ProductAttr> attrList =  productAttrService.getListByPidAndPdno(store.getPdid(),store.getPdno());
                for(int i=0;i<attrList.size();i++){
                    stand.append(attrList.get(i).getValue());
                    if(i !=(attrList.size()-1)){
                        stand.append("/");
                    }
                }

                resMap.put("规格",stand.toString());

                data.add(resMap);

            }
        }


        return  data;
    }



}
