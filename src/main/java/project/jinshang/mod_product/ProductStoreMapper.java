package project.jinshang.mod_product;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;
import project.jinshang.common.utils.DateUtils;
import org.springframework.cache.annotation.Cacheable;
import project.jinshang.common.utils.StringUtils;
import project.jinshang.mod_batchprice.bean.ProductQueryParam;
import project.jinshang.mod_product.bean.ProductInfo;
import project.jinshang.mod_product.bean.ProductInfoQuery;
import project.jinshang.mod_product.bean.ProductStore;
import project.jinshang.mod_product.bean.ProductStoreExample;
import project.jinshang.mod_product.bean.dto.OtherProductQueryDto;
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
    List<Map<String, Object>> getExcelProduct(ProductInfoQuery productInfo);

    public class excelProductProvider {

        private final String TBL_PRODUCT_INFO = "productinfo info on ps.pdid=info.id";
        private final String TBL_PRODUCT_STORE = "productstore ps";

        public String queryProductByParam(ProductInfoQuery productInfo) {
            StringBuffer sb = new StringBuffer();
            sb.append("ps.id,ps.pdid,ps.pdno,info.productname,info.stand,ps.prodprice,ps.thirtyprice,ps.sixtyprice,ps.ninetyprice,ps.storename");
            SQL sql = new SQL().SELECT(sb.toString()).FROM(TBL_PRODUCT_STORE);
            sql.LEFT_OUTER_JOIN(TBL_PRODUCT_INFO);
            sql.WHERE("info.memberid=#{memberid}");
            sql.WHERE("info.pdstate=5");
            sql.WHERE("info.producttype='紧固件'");
            if (StringUtils.hasText(productInfo.getProductname())) {
                String pdName = "%" + productInfo.getProductname() + "%";
                productInfo.setProductname(pdName);
                sql.WHERE("info.productname LIKE #{productname}");
            }

            if (StringUtils.hasText(productInfo.getStand())) {
                String stand = "%" + productInfo.getStand() + "%";
                productInfo.setStand(stand);
                sql.WHERE("info.stand LIKE #{stand}");
            }

            if (StringUtils.hasText(productInfo.getCardnum())) {
                sql.WHERE("info.cardnum=#{cardnum}");
            }
            if (StringUtils.hasText(productInfo.getMaterial())) {
                sql.WHERE("info.material=#{material}");
            }

            if (productInfo.getLevel1id() != null) {
                sql.WHERE("info.level1id=#{level1id}");
            }
            if (productInfo.getLevel2id() != null) {
                sql.WHERE("info.level2id=#{level2id}");
            }
            if (productInfo.getLevel3id() != null) {
                sql.WHERE("info.level3id=#{level3id}");
            }
            if(StringUtils.hasText(productInfo.getPdno())){
                String pdNo = "%"+productInfo.getPdno()+"%";
                productInfo.setPdno(pdNo);
                sql.WHERE("ps.pdno like #{pdno}");
            }
            if(StringUtils.hasText(productInfo.getBrand())){
                sql.WHERE("info.brand = #{brand}");
            }
            if(productInfo.getMaterialid()>0){
                sql.WHERE("info.materialid=#{materialid}");
            }
            if(productInfo.getCardnumid()>0){
                sql.WHERE("info.cardnumid=#{cardnumid}");
            }
            if(productInfo.getPdids()!=null){
                String pdids = org.apache.commons.lang.StringUtils.join(productInfo.getPdids().toArray(), ",");
                sql.WHERE("info.id in ("+pdids+")");
            }
            if(productInfo.getUptimeStart()!=null){
                sql.WHERE("info.uptime &lt; #{uptimeEnd}");
            }
            if (productInfo.getUptimeEnd() != null) {
                productInfo.setUptimeEnd(DateUtils.addDays(productInfo.getUptimeEnd(), 1));
                sql.WHERE("info.uptime &lt; #{uptimeEnd}");
            }
            if(productInfo.getDowntimeStart()!=null){
                sql.WHERE("info.downtime &gt; #{downtimeStart}");
            }
            if(productInfo.getDowntimeEnd()!=null){
                productInfo.setDowntimeEnd(DateUtils.addDays(productInfo.getDowntimeEnd(),1));
                sql.WHERE("info.downtime &lt; #{downtimeEnd}");
            }
            if(productInfo.getUpdatetimeStart()!=null){
                sql.WHERE("info.updatetime &gt; #{updatetimeStart}");
            }
            if(productInfo.getUpdatetimeEnd()!=null){
                productInfo.setUpdatetimeEnd(DateUtils.addDays(productInfo.getUpdatetimeEnd(),1));
                sql.WHERE("info.updatetime &lt; #{updatetimeEnd}");
            }

            return sql.toString();
        }
    }

    @SelectProvider(type = excelOtherProductProvider.class, method = "queryProductByParam")
    List<Map<String, Object>> getExcelOtherProduct(OtherProductQueryDto queryDto);

    public class excelOtherProductProvider {

        private final String TBL_PRODUCT_INFO = "productinfo info on ps.pdid=info.id";
        private final String TBL_PRODUCT_STORE = "productstore ps";

        public String queryProductByParam(OtherProductQueryDto queryDto) {
            StringBuffer sb = new StringBuffer();
            sb.append("ps.id,ps.pdid,ps.pdno,info.productname,info.stand,ps.prodprice,ps.thirtyprice,ps.sixtyprice,ps.ninetyprice,ps.storename");
            SQL sql = new SQL().SELECT(sb.toString()).FROM(TBL_PRODUCT_STORE);
            sql.LEFT_OUTER_JOIN(TBL_PRODUCT_INFO);
            sql.WHERE("info.memberid=#{memberid}");
            sql.WHERE("info.pdstate=5");
            sql.WHERE("info.producttype !='紧固件'");

            if(StringUtils.hasText(queryDto.getBrand())){
                sql.WHERE("info.brand = #{brand} ");
            }
            if (StringUtils.hasText(queryDto.getProductname())) {
                String pdName = "%" + queryDto.getProductname() + "%";
                queryDto.setProductname(pdName);
                sql.WHERE("info.productname LIKE #{productName}");
            }

            if (StringUtils.hasText(queryDto.getStand())) {
                String stand = "%" + queryDto.getStand() + "%";
                queryDto.setStand(stand);
                sql.WHERE("info.stand LIKE #{stand}");
            }

            if (StringUtils.hasText(queryDto.getCardnum())) {
                sql.WHERE("info.cardnum=#{cardnum}");
            }
            if (StringUtils.hasText(queryDto.getMaterial())) {
                sql.WHERE("info.material=#{material}");
            }

            if (queryDto.getLevel1id() != null) {
                sql.WHERE("info.level1id=#{level1id}");
            }
            if (queryDto.getLevel2id() != null) {
                sql.WHERE("info.level2id=#{level2id}");
            }
            if (queryDto.getLevel3id() != null) {
                sql.WHERE("info.level3id=#{level3id}");
            }
            if(queryDto.getCreateStart()!=null){
                sql.WHERE("info.createtime >= #{createStart} ");
            }
            if(queryDto.getCreateEnd()!=null){
                sql.WHERE("info.createtime < #{createEnd}");
            }
            if(queryDto.getUptimeStart() != null){
                sql.WHERE("info.uptime >= #{uptimeStart} ");
            }
            if(queryDto.getUptimeEnd() != null){
                sql.WHERE("info.uptime < #{uptimeEnd} ");
            }

            if(queryDto.getDowntimeStart() != null){
                sql.WHERE("info.downtime >= #{downtimeStart} ");
            }
            if(queryDto.getDowntimeEnd() != null){
                sql.WHERE("info.downtime < #{downtimeEnd} ");
            }

            if(queryDto.getUpdatetimeStart() != null){
                sql.WHERE("info.updatetime >= #{updatetimeStart} ");
            }
            if(queryDto.getUpdatetimeEnd() != null){
                sql.WHERE("info.updatetime < #{updatetimeEnd} ");
            }
            if(queryDto.getPdids() != null && queryDto.getPdids() != ""){
                sql.WHERE("info.id in ( "+ queryDto.getPdids() +" ) ");
            }
            if(StringUtils.hasText(queryDto.getPdno())){
                sql.WHERE("ps.pdno = #{pdno}");
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


    @Select("select pst.* from productstore pst  where pst.storeid=#{storeid} and pst.pdno=#{pdno}")
    ProductStore getByStoreidAndPdNoForStockSyn(@Param("storeid") long storeid,@Param("pdno") String pdno);


    @Update("update productstore set pdstorenum=#{num} where id in (select pst.id from productinfo pi,productstore pst " +
            "where pi.id=pst.pdid and pi.memberid=#{memberid} and pst.storeid=#{storeid} and pi.pdstate<>6 and pst.pdno=#{pdno})")
    int updateNumByMemberidStoreidAndPdno(@Param("memberid") Long memberid,@Param("storeid") Long storeid,@Param("pdno") String pdno, @Param("num") BigDecimal num);


    @Select("select * from productstore where pdid=#{pdid} and pdno=#{pdno} and storeid=#{storeid} order by id desc limit 1")
    ProductStore getProductStore(@Param("pdid") Long pdid,@Param("pdno") String pdno,@Param("storeid") Long storeid);



    /**
     *根据卖家id,仓库编码,上架商品等条件查询出商品进行库存同步
     * @author xiazy
     * @date  2018/6/7 10:52
     * @param storeid 库存编码
     * @param memberid 卖家id
     * @param pdstate 商品的状态
     * @return java.util.List<project.jinshang.mod_product.bean.ProductInfo>
     */
    @Select("SELECT pt.* \n" +
            "\t FROM productstore pt \n" +
            "\t LEFT JOIN productinfo pi ON pi.id = pt.pdid WHERE \n" +
            "\t pt.storeid = #{storeid} \n" +
            "\t AND pi.memberid = #{memberid} \n" +
            "\t AND pi.pdstate = #{pdstate} \n" +
            "\t ORDER BY pt.id")
    List<ProductStore> selectProductStoreForSyn(@Param("storeid") Long storeid, @Param("memberid") Long memberid, @Param("pdstate") int pdstate);


    @Update("update productstore set intervalprice = #{jsonList} where id = #{psid}")
    void updateProductStore(@Param("psid") long psid,@Param("jsonList") String jsonList);


    @Cacheable(value = "ProductStore",key = "'getPdidByStoreidAndPdno:'+#p0+'#'+#p1")
    @Select("select pdid from productstore where storeid=#{storeid} and pdno=#{pdno}")
    Long getPdidByStoreidAndPdno(@Param("storeid") Long storeid,@Param("pdno") String pdno);
}