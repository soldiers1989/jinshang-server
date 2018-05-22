package project.jinshang.mod_product;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.*;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import project.jinshang.mod_product.bean.ProductInfo;
import project.jinshang.mod_product.bean.ProductInfoExample;
import project.jinshang.mod_product.bean.ProductInfoQuery;
import project.jinshang.mod_product.bean.Products;
import project.jinshang.mod_product.bean.dto.ProdUnitRateViewDto;
import project.jinshang.mod_product.service.ProductInfoProvider;

import javax.persistence.FetchType;
import javax.persistence.OneToOne;

public interface ProductInfoMapper {
    int countByExample(ProductInfoExample example);

    @CacheEvict(value = "jinshang-productinfo",allEntries = true)
    int deleteByExample(ProductInfoExample example);

    @CacheEvict(value = "jinshang-productinfo",key = "'jinshang-productinfo.selectByPrimaryKey:' + #p0")
    int deleteByPrimaryKey(Long id);

    int insert(ProductInfo record);

    int insertSelective(ProductInfo record);

    List<ProductInfo> selectByExample(ProductInfoExample example);

    @Cacheable(value = "jinshang-productinfo",key = "'jinshang-productinfo.selectByPrimaryKey:' + #p0")
    ProductInfo selectByPrimaryKey(Long id);

    @CacheEvict(value = "jinshang-productinfo",allEntries = true)
    int updateByExampleSelective(@Param("record") ProductInfo record, @Param("example") ProductInfoExample example);

    @CacheEvict(value = {"jinshang-productinfo"},allEntries = true)
    int updateByExample(@Param("record") ProductInfo record, @Param("example") ProductInfoExample example);

    @CacheEvict(value = "jinshang-productinfo",key = "'jinshang-productinfo.selectByPrimaryKey:' + #p0.id")
    int updateByPrimaryKeySelective(ProductInfo record);

    @CacheEvict(value = "jinshang-productinfo",key = "'jinshang-productinfo.selectByPrimaryKey:' + #p0.id")
    int updateByPrimaryKey(ProductInfo record);


    @Select("select * from productinfo where memberid=#{memberid} and pdstate=4 and id=#{id}")
    ProductInfo getGroundingById(@Param("memberid") long memberid,@Param("id") long id);


    @Select("<script>select P.*,S.startnum,S.prodprice,S.threeprice,S.ninetyprice,S.thirtyprice,S.sixtyprice,S.freightmode,S.pdstorenum,S.costprice,S.marketprice,M.username,SC.companyname,SC.shopname " +
            "from ProductInfo P join productStore S on P.id=S.pdid join member M on M.id=P.memberid  join sellercompanyinfo SC on SC.memberid=P.memberid " +
            "<where> P.producttype='紧固件' and p.pdstate != 6 " +
            "<if test=\"info.memberid != null and info.memberid != 0\"> and p.memberid=#{info.memberid} </if>" +
            "<if test=\"info.username != null and info.username != ''\"> and M.username like '%${info.username}%' </if>" +
            "<if test=\"info.brand != null\"> and p.brand like '%${info.brand}%' </if>" +
            "<if test=\"info.mark != null\"> and p.mark=#{info.mark} </if>" +
            "<if test=\"info.shopname != null\"> and SC.shopname like '%${info.shopname}%' </if>" +
            "<if test=\"info.productStore.haveStorenum == 1\"> and s.pdstorenum &gt; s.startnum </if>" +
            "<if test=\"info.productStore.haveStorenum == 2\"> and s.pdstorenum &lt; s.startnum </if>" +
            "<if test=\"info.level1id !=null and info.level1id &gt; 0\"> and p.level1id=#{info.level1id} </if>" +
            "<if test=\"info.level2id !=null and info.level2id &gt; 0\"> and p.level2id=#{info.level2id} </if>" +
            "<if test=\"info.level3id !=null and info.level3id &gt; 0\"> and p.level3id=#{info.level3id} </if>" +
            "<if test=\"info.pdstate != -1\"> and p.pdstate=#{info.pdstate} </if>" +
            "<if test=\"info.cardnumid &gt; 0\"> and p.cardnumid=#{info.cardnumid} </if>"+
            "<if test=\"info.materialid &gt; 0 \"> and p.materialid=#{info.materialid} </if>"+
            "<if test=\"info.uptimeStart != null \"> and p.uptime &gt; #{info.uptimeStart} </if>"+
            "<if test=\"info.uptimeEnd != null \"> and p.uptime &lt; #{info.uptimeEnd} </if>"+
            "<if test=\"info.createStart != null \"> and p.createtime &gt; #{info.createStart} </if>"+
            "<if test=\"info.createEnd != null \"> and p.createtime &lt; #{info.createEnd} </if>"+
            "<if test=\"info.stand != null and info.stand !='' \"> and p.stand  like  '%${info.stand}%' </if>"+
            "<if test=\"info.productname != null\"> and p.productname like '%${info.productname}%' </if>" +
            "</where>  order by P.id desc " +
            "</script>")
    List<Map<String,Object>> fastenerList(@Param("info") ProductInfoQuery info);


    /**
     * 列表查询，后台用于导出excel
     * @param info
     * @return
     */
    @Select("<script>select P.*,S.startnum,S.prodprice,S.threeprice,S.ninetyprice,S.thirtyprice,S.sixtyprice,S.freightmode,S.pdstorenum,S.costprice,S.pdno," +
            "S.marketprice,S.intervalprice,M.username,SC.companyname,SC.shopname,SE.name as storename,SE.address as storeaddress,ST.temname " +
            "from ProductInfo P join productStore S on P.id=S.pdid join member M on M.id=P.memberid  join sellercompanyinfo SC on SC.memberid=P.memberid  left join store SE on S.storeid=SE.id left join shippingtemplates ST on ST.id=S.freightmode " +
            "<where> P.producttype='紧固件' and p.pdstate != 6 " +
            "<if test=\"info.memberid != null and info.memberid != 0\">and p.memberid=#{info.memberid} </if>" +
            "<if test=\"info.username != null and info.username != ''\">and M.username like '%${info.username}%' </if>" +
            "<if test=\"info.brand != null\"> and p.brand like '%${info.brand}%' </if>" +
            "<if test=\"info.mark != null\"> and p.mark=#{info.mark} </if>" +
            "<if test=\"info.shopname != null\"> and SC.shopname like '%${info.shopname}%' </if>" +
            "<if test=\"info.productStore.haveStorenum == 1\"> and s.pdstorenum &gt; 0 </if>" +
            "<if test=\"info.productStore.haveStorenum == 2\"> and s.pdstorenum = 0 </if>" +
            "<if test=\"info.level1id !=null and info.level1id &gt; 0\"> and p.level1id=#{info.level1id} </if>" +
            "<if test=\"info.level2id !=null and info.level2id &gt; 0\"> and p.level2id=#{info.level2id} </if>" +
            "<if test=\"info.level3id !=null and info.level3id &gt; 0\"> and p.level3id=#{info.level3id} </if>" +
            "<if test=\"info.pdstate != -1\"> and p.pdstate=#{info.pdstate} </if>" +
            "<if test=\"info.cardnumid &gt; 0\"> and p.cardnumid=#{info.cardnumid} </if>"+
            "<if test=\"info.materialid &gt; 0 \"> and p.materialid=#{info.materialid} </if>"+
            "<if test=\"info.uptimeStart != null \"> and p.uptime &gt; #{info.uptimeStart} </if>"+
            "<if test=\"info.uptimeEnd != null \"> and p.uptime &lt; #{info.uptimeEnd} </if>"+
            "<if test=\"info.createStart != null \"> and p.createtime &gt; #{info.createStart} </if>"+
            "<if test=\"info.createEnd != null \"> and p.createtime &lt; #{info.createEnd} </if>"+
            "<if test=\"info.productname != null\">and p.productname like '%${info.productname}%' </if>" +
            "<if test=\"info.stand != null and info.stand !='' \"> and p.stand  like  '%${info.stand}%' </if>"+
            "</where>  order by P.id desc " +
            "</script>")
    List<Map<String,Object>> fastenerListForExcelByAdmin(@Param("info") ProductInfoQuery info);




    /**
     * 列表查询，卖家用于导出excel
     * @param info
     * @return
     */
    @Select("<script>select P.*,S.startnum,S.prodprice,S.threeprice,S.ninetyprice,S.thirtyprice,S.sixtyprice," +
            "S.freightmode,S.pdstorenum,S.costprice,S.storename,M.username,S.pdno from ProductInfo P join productStore S on P.id=S.pdid join member M on M.id=P.memberid " +
            "<where> P.producttype='紧固件' and p.pdstate != 6 " +
            "<if test=\"info.memberid != null and info.memberid != 0\">and p.memberid=#{info.memberid} </if>" +
            "<if test=\"info.username != null and info.username != ''\">and M.username like '%${info.username}%' </if>" +
            "<if test=\"info.productname != null\">and p.productname like '%${info.productname}%' </if>" +
            "<if test=\"info.brand != null\"> and p.brand=#{info.brand} </if>" +
            "<if test=\"info.mark != null\"> and p.mark=#{info.mark} </if>" +
            "<if test=\"info.productStore.haveStorenum == 1\"> and s.pdstorenum &gt; 0 </if>" +
            "<if test=\"info.productStore.haveStorenum == 2\"> and s.pdstorenum = 0 </if>" +
            "<if test=\"info.level1id !=null and info.level1id &gt; 0\"> and p.level1id=#{info.level1id} </if>" +
            "<if test=\"info.level2id !=null and info.level2id &gt; 0\"> and p.level2id=#{info.level2id} </if>" +
            "<if test=\"info.level3id !=null and info.level3id &gt; 0\"> and p.level3id=#{info.level3id} </if>" +
            "<if test=\"info.pdstate != -1\"> and p.pdstate=#{info.pdstate} </if>" +
            "<if test=\"info.cardnumid &gt; 0\"> and p.cardnumid=#{info.cardnumid} </if>"+
            "<if test=\"info.materialid &gt; 0 \"> and p.materialid=#{info.materialid} </if>"+
            "<if test=\"info.uptimeStart != null \"> and p.uptime &gt; #{info.uptimeStart} </if>"+
            "<if test=\"info.uptimeEnd != null \"> and p.uptime &lt; #{info.uptimeEnd} </if>"+
            "<if test=\"info.createStart != null \"> and p.createtime &gt; #{info.createStart} </if>"+
            "<if test=\"info.createEnd != null \"> and p.createtime &lt; #{info.createEnd} </if>"+
            "</where>  order by P.id desc " +
            "</script>")
    List<Map<String,Object>> fastenerListForExcelBuySeller(@Param("info") ProductInfoQuery info);




    /**
     * 根据id查询商品和库存信息(只可用于紧固件)
     * @param
     * @return
     */
//    @Select("select * from productinfo where id=#{id}")
//    @Results({
//            @Result(id = true,column = "id",property = "id"),
//            @Result(column = "id",property = "productStore", one=@One(
//                    select="project.jinshang.mod_product.ProductStoreMapper.selectByProductid"))
//    })
//    ProductInfo getProductInfoWithStore(@Param("id") long id);


    @SelectProvider(type = ProductInfoProvider.class,method = "ProductInfoWithRate")
    List<Map> getProductInfoWithRate(@Param("productname") String productname,
                                     @Param("brand") String brand,
                                     @Param("level1id") int level1id,
                                     @Param("level2id") int level2id,
                                     @Param("level3id") int level3id,
                                     @Param("material") String material,
                                     @Param("cardnum") String cardnum,
                                     @Param("mark") String mark);


    @Select("select  p.id,p.productname,p.subtitle,p.brand,p.unit,p.pdpicture,p.salesnum,pst.prodprice,pst.pdno from productinfo p ,productstore pst where p.id=pst.pdid and p.memberid=#{memberid} and pst.pdno=#{pdno} and p.pdstate=4 order by pst.id desc limit 1")
    Map<String,Object> getShopSelfProdByPdno(@Param("pdno") String pdno,@Param("memberid") Long memberid);


    @Cacheable(value = "jinshang-productinfo",key = "'jinshang-productinfo.selectViewCookieByPId:' + #p0")
    @Select("SELECT pi.id,ps.storename,ps.pdstorenum,ps.startnum,pi.productname,\n" +
            "ps.prodprice,pi.material,pi.cardnum,pi.brand,pi.packagetype,pi.tag,pi.pdpicture,pi.producttype,pi.stand,br.pic,pi.selfsupport,pi.level3 \n" +
            "FROM productinfo pi \n" +
            "LEFT JOIN productstore ps on pi.id=ps.pdid\n" +
            "LEFT JOIN brand br on pi.brandid=br.id \n" +
            "where pi.id=#{pId} limit 1")
    Map<String, Object> selectViewCookieByPId(@Param("pId") Long pId);


    @Select("select id,unit,storeunit,unitrate from productinfo where id=#{id }")
    ProdUnitRateViewDto getProdUnitRate(@Param("id") long id);

    @Select("select producttype from productinfo where memberid=#{sellerid} and id in (${ids})")
    List<String> getProductInfoByIds(@Param("ids") String ids,@Param("sellerid") Long sellerid);



    @Select("<script>" +
            "select  p.*,pst.prodprice,pst.pdno,pst.storename from productinfo p ,productstore pst,member m where p.id=pst.pdid and p.memberid=m.id " +
            "<if test=\"username !=null and username!= ''\"> and m.username=#{username} </if>" +
            "<if test=\"pdno !=null and pdno!= ''\">and pst.pdno=#{pdno} </if>" +
            "<if test=\"store !=null and store!= ''\">and pst.storename=#{store} </if>" +
            "<if test=\"brand !=null and brand!= ''\">and p.brand=#{brand} </if>" +
            "<if test=\"stand !=null and stand!= ''\">and p.stand=#{stand} </if>" +
            "<if test=\"productname !=null and productname!= ''\">and p.productname=#{productname} </if>" +
            "and p.pdstate=4 order by pst.id desc" +
            "</script>")
    List<Map<String,Object>> getFloorProducts(@Param("pdno") String pdno,@Param("username") String username,
                                             @Param("brand") String brand,@Param("productname") String productname,
                                             @Param("stand") String stand,
                                        @Param("store") String store
                                        );



    @Select("select p.id,p.memberid,p.level1id,p.level2id,p.level3id,ps.prodprice,ps.thirtyprice,ps.sixtyprice,ps.ninetyprice,m.membersettingstate " +
            "from productinfo p,productstore ps,member m where " +
            "p.id=ps.pdid and p.memberid=m.id and p.id in (${pidsStr})")
    List<Map<String,Object>> getProdRatePriceByPids(@Param("pidsStr") String pidsStr);


    @CacheEvict(value = "jinshang-productinfo",allEntries = true)
    @Update("update productinfo set pdpicture=#{pdpicture,typeHandler=project.jinshang.typeHandler.ArrayTypeHandler}," +
            "pddrawing=#{pddrawing,typeHandler=project.jinshang.typeHandler.ArrayTypeHandler} where productsno=#{productno}")
    int updateImgByProductsno(Products products);
}