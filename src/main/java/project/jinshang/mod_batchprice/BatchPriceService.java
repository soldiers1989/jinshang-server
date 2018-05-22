package project.jinshang.mod_batchprice;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.mod_batchprice.bean.ProductQueryParam;
import project.jinshang.mod_product.ProductInfoMapper;
import project.jinshang.mod_product.ProductStoreMapper;
import project.jinshang.mod_product.bean.ProductInfo;
import project.jinshang.mod_product.bean.ProductStore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BatchPriceService {

    @Autowired
    private ProductInfoMapper productInfoMapper;

    @Autowired
    private ProductStoreMapper productStoreMapper;




    public void updateProductInfo(ProductInfo info){
        productInfoMapper.updateByPrimaryKeySelective(info);
    }



    /**
     * 获取产品类型
     * @param ids
     * @return
     */
    public List<String> getProductInfoByIds(String ids,Long sellerid){
        return productInfoMapper.getProductInfoByIds(ids,sellerid);
    }

    /**
     * 获取仓库产品
     * @param pdids
     * @return
     */
    public List<ProductStore> getProductStoreByPdids(@Param("pdids") String pdids){
        return productStoreMapper.getProductStoreByPdids(pdids);
    }

    /**
     * 获取商品信息
     * @param pdids
     * @return
     */
    List<ProductInfo> getProductInfoList(@Param("pdids") String pdids){
        return productStoreMapper.getProductInfoList(pdids);
    }

    /**
     * 导出exel
     * @param productQueryParam
     * @return
     */
    List<Map<String,Object>> getExcelProduct(ProductQueryParam productQueryParam){
        List<Map<String,Object>> list= productStoreMapper.getExcelProduct(productQueryParam);
        List<Map<String,Object>> list2 = new ArrayList<>();
        for(Map<String,Object> map:list){
            Map<String,Object> maptemp = new HashMap<>();
            maptemp.put("商品库存id",map.get("id"));
            maptemp.put("商品id",map.get("pdid"));
            maptemp.put("紧固件编码",map.get("pdno"));
            maptemp.put("品名",map.get("productname"));
            maptemp.put("规格",map.get("stand"));
            maptemp.put("商品价格",map.get("prodprice"));
            maptemp.put("30天发货价格",map.get("thirtyprice"));
            maptemp.put("60天发货价格",map.get("sixtyprice"));
            maptemp.put("90天发货价格",map.get("ninetyprice"));
            maptemp.put("仓库名称",map.get("storename"));
            list2.add(maptemp);
        }
        return list2;
    }




}
