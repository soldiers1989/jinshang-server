package project.jinshang.mod_product;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.security.access.method.P;
import project.jinshang.common.utils.StringUtils;
import project.jinshang.mod_batchprice.bean.ProductQueryParam;
import project.jinshang.mod_product.bean.ProductInfo;
import project.jinshang.mod_product.bean.ProductStore;
import project.jinshang.mod_product.bean.ProductStoreExample;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface ProductStoreMapper {
    int countByExample(ProductStoreExample example);

    int deleteByExample(ProductStoreExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ProductStore record);

    int insertSelective(ProductStore record);

    List<ProductStore> selectByExample(ProductStoreExample example);

    ProductStore selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ProductStore record, @Param("example") ProductStoreExample example);

    int updateByExample(@Param("record") ProductStore record, @Param("example") ProductStoreExample example);

    int updateByPrimaryKeySelective(ProductStore record);

    int updateByPrimaryKey(ProductStore record);

    @Delete("delete from productstore where pdid=#{productid}")
    int deleteByProductid(@Param("productid") long productid);


    @Select("select * from productstore where pdid=#{pdid}")
    List<ProductStore> getByProductid(@Param("pdid") long pdid);


    @Select("select * from productstore where pdid=#{pdid} limit 1")
    ProductStore selectByProductidForFastener(@Param("pdid") long pdid);

    @Update("update productstore set pdstorenum=#{storenum} where pdid=#{pid}")
    int updatePdstorenumByPid(@Param("pid") long pid, @Param("storenum") BigDecimal storenum);

    @Select("select count(p.id) from productinfo p,productstore s where p.id=s.pdid and p.memberid=#{memberid} and s.pdno=#{pdno} and p.pdstate<> 6 ")
    int countByMemberidAndPdno(@Param("memberid") long memberid, @Param("pdno") String pdno);

    @Select("select s.* from productinfo p,productstore s where p.id=s.pdid and p.memberid=#{memberid} and s.pdno=#{pdno} and p.pdstate<>6 limit 1")
    ProductStore getByMemberidAndPdno(@Param("memberid") long memberid, @Param("pdno") String pdno);

    @Select("select s.* from productstore s where s.pdid=#{pdid} and s.pdno=#{pdno} limit 1")
    ProductStore getByPdidAndPdno(@Param("pdid") long pdid, @Param("pdno") String pdno);


    @Update("update productstore set pdstorenum = pdstorenum-#{pdstorenum} where pdid=#{pdid} and pdno=#{pdno}")
    int decrStoreNumByPdidAndPdno(@Param("pdid") long pdid, @Param("pdno") String pdno, @Param("pdstorenum") BigDecimal pdstorenum);

    @Update("update productstore set pdstorenum = pdstorenum + #{pdstorenum} where pdid=#{pdid} and pdno=#{pdno}")
    int addStoreNumByPdidAndPdno(@Param("pdid") long pdid, @Param("pdno") String pdno, @Param("pdstorenum") BigDecimal pdstorenum);


    @Select("select pst.* from productstore pst,productinfo p where pst.pdid=p.id and p.pdstate <> 6 and p.memberid=#{memberid} and  pst.storeid=#{storeid} and pst.pdno=#{pdno} order by pst.id limit 1")
    ProductStore getByStoreidAndPdNo(@Param("memberid") long memberid, @Param("storeid") long storeid, @Param("pdno") String pdno);


    @Select("select id,prodprice,threeprice,thirtyprice,sixtyprice,ninetyprice,marketprice from productstore where pdid in (${pdids})")
    List<ProductStore> getProductStoreByPdids(@Param("pdids") String pdids);

    @Select("select id from productinfo where id in (${pdids})")
    List<ProductInfo> getProductInfoList(@Param("pdids") String pdids);


    @Update("update productstore set pdstorenum=#{num} where storeid=#{storeid} and pdno=#{pdno}")
    int updateNumByStoreidAndPdno(@Param("storeid") long storeid, @Param("pdno") String pdno, @Param("num") BigDecimal num);


    @SelectProvider(type = excelProductProvider.class, method = "queryProductByParam")
    List<Map<String, Object>> getExcelProduct(ProductQueryParam productQueryParam);

    public class excelProductProvider {

        private final String TBL_PRODUCT_INFO = "productinfo info on ps.pdid=info.id";
        private final String TBL_PRODUCT_STORE = "productstore ps";

        public String queryProductByParam(ProductQueryParam productQueryParam) {
            StringBuffer sb = new StringBuffer();
            sb.append("ps.id,ps.pdid,ps.pdno,info.productname,info.stand,ps.prodprice,ps.thirtyprice,ps.sixtyprice,ps.ninetyprice,ps.storename");
            SQL sql = new SQL().SELECT(sb.toString()).FROM(TBL_PRODUCT_STORE);
            sql.LEFT_OUTER_JOIN(TBL_PRODUCT_INFO);
            sql.WHERE("info.memberid=#{sellerId}");
            sql.WHERE("info.pdstate=5");
            sql.WHERE("info.producttype='紧固件'");
            if (StringUtils.hasText(productQueryParam.getProductName())) {
                String pdName = "%" + productQueryParam.getProductName() + "%";
                productQueryParam.setProductName(pdName);
                sql.WHERE("info.productname LIKE #{productName}");
            }

            if (StringUtils.hasText(productQueryParam.getStand())) {
                String stand = "%" + productQueryParam.getStand() + "%";
                productQueryParam.setStand(stand);
                sql.WHERE("info.stand LIKE #{stand}");
            }

            if (StringUtils.hasText(productQueryParam.getCardnum())) {
                sql.WHERE("info.cardnum=#{cardnum}");
            }
            if (StringUtils.hasText(productQueryParam.getMaterial())) {
                sql.WHERE("info.material=#{material}");
            }

            if (productQueryParam.getLevel1() != null) {
                sql.WHERE("info.level1id=#{level1}");
            }
            if (productQueryParam.getLevel2() != null) {
                sql.WHERE("info.level2id=#{level2}");
            }
            if (productQueryParam.getLevel3() != null) {
                sql.WHERE("info.level3id=#{level3}");
            }

            return sql.toString();
        }
    }


    @Update("update productstore set storename=#{name} where storeid=#{storeid}")
    int updateStorenameByStoreid(@Param("storeid") long storeid, @Param("name") String name);

    @Update("update productstore \n" +
            "set pdstorenum = #{num} \n" +
            "where \n" +
            "pdno = #{sku}\n" +
            "and storeid in (select id from store where memberid in ${memberIds})" +
            "and pdid in (\n" +
            "select p.id from productstore pst,productinfo p  where p.id=pst.pdid and  pst.pdno=#{sku}  and p.producttype='紧固件'\n" +
            ")")
    int updateProductStoreNum(@Param("sku") String sku,
                              @Param("num") BigDecimal num,
                              @Param("memberIds") String memberIds);



    @Select("select * from productstore where pdid=#{pdid} and pdno=#{pdno} and storeid=#{storeid} order by id desc limit 1")
    ProductStore getProductStore(@Param("pdid") Long pdid,@Param("pdno") String pdno,@Param("storeid") Long storeid);

}