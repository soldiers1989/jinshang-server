package project.jinshang.mod_checkstore.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import project.jinshang.mod_product.service.ProductSearchProvider;
import project.jinshang.mod_checkstore.bean.ProductStoreCheck;
import java.util.List;
import java.util.Map;

@Mapper
public interface ProductStoreCheckSearchMapper {


    @SelectProvider(type = ProductStoreCheckSearchProvide.class,method = "countSearchWithKeys")
    int countSearchWithKeys(String query, String level1, String level2, String level3,
                            String productname, String brand, String cardnum, String material, String surfacetreatment, Map<String,Object> attrs,String memberids);

    //    @Cacheable(value = "jinshang-productsearch")
    @SelectProvider(type = ProductStoreCheckSearchProvide.class,method = "searchWithKeys")
    List<Map> searchWithKeys(String query, String level1, String level2, String level3,
                             String productname, String brand, String cardnum, String material,
                             String surfacetreatment, Map<String,Object> attrs, int start, int max, String memberids);

    //    @Cacheable(value = "jinshang-productsearch")
    @SelectProvider(type = ProductStoreCheckSearchProvide.class,method = "searchKeys")
    List<Map> searchKeys(String query,String level1,String level2,String level3,
                         String productname,String brand,String cardnum,String material, String surfacetreatment,Map<String,Object> attrs);

    //    @Cacheable(value = "jinshang-productsearch")
    @SelectProvider(type = ProductStoreCheckSearchProvide.class,method = "searchAttrKeys")
    List<Map> searchAttrKeys(String query,String level1,String level2,String level3,
                             String productname,String brand,String cardnum,String material,String surfacetreatment, Map<String,Object> attrs);

    @SelectProvider(type = ProductStoreCheckSearchProvide.class,method = "searchAttrKeysHashAttr")
    List<Map> searchAttrKeysHashAttr(String query,String level1,String level2,String level3,
                                     String productname,String brand,String cardnum,String material,String surfacetreatment, Map<String,Object> attrs);



}
