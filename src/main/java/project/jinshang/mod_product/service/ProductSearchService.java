package project.jinshang.mod_product.service;

import project.jinshang.mod_product.bean.ProductInfo;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface ProductSearchService {
    void saveIndex(ProductInfo productInfo);
    void delIndex(long productId);

    Map<String,Object>  search(String search_str,
                               String level1, String level2, String level3,
                               String productname, String brand, String cardnum, String material, String surfacetreatment, Map<String, Object> attrs,
                               int start, int max, String sorttype, Integer selfsupport, Integer havestore, Integer forwardtime, String store, Integer type, String productType);

    /**
     * 提取分类关键字
     */
    List<Map> fetchSearchKeys(String search_str, String level1, String level2, String level3,
                              String productname, String brand, String cardnum, String material, String surfacetreatment, Map<String, Object> attrs,
                              Integer selfsupport, Integer havestore, Integer forwardtime, String store);

    List<Map> otherProdFetchSearchKeys(String search_str,
                                       String level1, String level2, String level3,
                                       String productname, String brand, Map<String, Object> attrs, int type);



    List<Map> fetchSearchAttrKeys(String search_str,
                                  String level1, String level2, String level3,
                                  String productname, String brand, String cardnum, String material, String surfacetreatment,
                                  Map<String, Object> attrs, Integer selfsupport, Integer havestore, Integer forwardtime, String store);

    List<Map> fetchSearchAttrKeysHashAttr(String search_str,
                                          String level1, String level2, String level3,
                                          String productname, String brand, String cardnum, String material, String surfacetreatment, Map<String, Object> attrs,
                                          Integer selfsupport, Integer havestore, Integer forwardtime, String store);
    List<Map> otherProdFetchSearchAttrKeys(String search_str,
                                           String level1, String level2, String level3,
                                           String productname, String brand, Map<String, Object> attrs, int type);
    /**
     * 搜索中 再分类显示结果
     * 分组分页
     * start 分页开始 （0 起始）
     * max 分页个数 -1则为全部
     */
    List<Map> searchWithKeys(
            String search_str,
            String level1, String level2, String level3,
            String productname, String brand, String cardnum, String material, String surfacetreatment, Map<String, Object> attrs,
            int start, int max, String sorttype, Integer selfsupport, Integer havestore, Integer forwardtime, String store
    );

    List<Map> otherProdSearchWithKeys(
            String search_str,
            String level1, String level2, String level3,
            String productname, String brand, Map<String, Object> attrs, int pageNo, int pageSize, String sorttype, int type

    );

    /**
     * 总数
     */
    int countSearchWithKeys(String search_str,
                            String level1, String level2, String level3,
                            String productname, String brand, String cardnum, String material, String surfacetreatment, Map<String, Object> attrs, Integer selfsupport, Integer havestore, Integer forwardtime, String store);

    /**
     * 总数
     */
    int otherProdCountSearchWithKeys(String search_str,
                                     String level1, String level2, String level3,
                                     String productname, String brand, Map<String, Object> attrs, int type);

    void rebuildIndex() throws InterruptedException;

    /**
     * 根据仓库id 和
     * @param storeid
     * @param pdno
     * @return
     */
    List<Map<String,Object>> searchByStoreidAndPdno(Long storeid,String pdno);


    void bulkUpdateIndex(List<Map<String, Object>> data) throws IOException;

}
