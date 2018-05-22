package project.jinshang.mod_product.service;

import org.apache.ibatis.annotations.*;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProductSearchMapper {

    @Insert({"insert into productSearch(productid,sindex) values(#{param1},'${sindex}'::tsvector)"})
    void saveIndex(long productid, @Param("sindex") String sindex);

    @Select("select productid,sindex from productSearch where productid=#{param1}")
    Map<String,Object> findById(long id);

    @Select("update productSearch set sindex='${sindex}'::tsvector where productid=#{param1}")
    void updateIndex(long productid, @Param("sindex")String sindex);

    @Delete("delete from productSearch where productid=#{param1}")
    void  deleteIndex(long productid);


    /**
     * todo: demo
     * 分组分页
     * start 分页开始 （0 起始）
     * max 分页个数
     */
    @SelectProvider(type = ProductSearchProvider.class,method = "search")
    List<Map> search(@Param("query")String query, int start, int max);

//    @Cacheable(value = "jinshang-productsearch")
    @SelectProvider(type = ProductSearchProvider.class,method = "searchKeys")
    List<Map> searchKeys(String query,String level1,String level2,String level3,
                         String productname,String brand,String cardnum,String material, String surfacetreatment,Map<String,Object> attrs,Integer selfsupport,
                         Integer havestore,Integer forwardtime,String store);


    @SelectProvider(type = ProductSearchProvider.class,method = "otherProdSearchKeys")
    List<Map> otherProdSearchKeys(String query,String level1,String level2,String level3,
                         String productname,String brand, Map<String,Object> attrs,int type);


//    @Cacheable(value = "jinshang-productsearch")
    @SelectProvider(type = ProductSearchProvider.class,method = "searchAttrKeys")
    List<Map> searchAttrKeys(String query,String level1,String level2,String level3,
                         String productname,String brand,String cardnum,String material,String surfacetreatment,
                             Map<String,Object> attrs,Integer selfsupport,Integer havestore,Integer forwardtime,String store);


    @SelectProvider(type = ProductSearchProvider.class,method = "searchAttrKeysHashAttr")
    List<Map> searchAttrKeysHashAttr(String query,String level1,String level2,String level3,
                             String productname,String brand,String cardnum,String material,String surfacetreatment,
                                     Map<String,Object> attrs,Integer selfsupport,Integer havestore,Integer forwardtime,String store);



    @SelectProvider(type = ProductSearchProvider.class,method = "otherProdSearchAttrKeys")
    List<Map> otherProdsearchAttrKeys(String query,String level1,String level2,String level3,
                             String productname,String brand, Map<String,Object> attrs,int type);


//    @Cacheable(value = "jinshang-productsearch")
    @SelectProvider(type = ProductSearchProvider.class,method = "searchWithKeys")
    List<Map> searchWithKeys(String query,String level1,String level2,String level3,
                             String productname,String brand,String cardnum,String material,String surfacetreatment, Map<String,Object> attrs,int start, int max,
                             String sorttype,Integer selfsupport,Integer havestore,Integer forwardtime,String store);



    @SelectProvider(type = ProductSearchProvider.class,method = "otherProdSearchWithKeys")
    List<Map> otherProdSearchWithKeys(String query,String level1,String level2,String level3,
                             String productname,String brand, Map<String,Object> attrs,int start,int count,String sorttype,int type);





//    @Cacheable(value = "jinshang-productsearch")
    @SelectProvider(type = ProductSearchProvider.class,method = "countSearchWithKeys")
    int countSearchWithKeys(String query,String level1,String level2,String level3,
                            String productname,String brand,String cardnum,String material,String surfacetreatment, Map<String,Object> attrs,Integer selfsupport,Integer havestore,Integer forwardtime,String store);




    @SelectProvider(type = ProductSearchProvider.class,method = "otherProdCountSearchWithKeys")
    int otherProdCountSearchWithKeys(String query,String level1,String level2,String level3,
                            String productname,String brand, Map<String,Object> attrs,int type);


}
