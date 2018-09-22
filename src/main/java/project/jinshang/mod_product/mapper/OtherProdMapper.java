package project.jinshang.mod_product.mapper;

import org.apache.ibatis.annotations.*;
import project.jinshang.mod_product.bean.dto.OtherProdDetailViewDto;
import project.jinshang.mod_product.bean.dto.OtherProdStore;
import project.jinshang.mod_product.bean.dto.OtherProdStoreForExcel;
import project.jinshang.mod_product.bean.dto.OtherProductQueryDto;
import project.jinshang.mod_product.provider.OtherProdProvider;

import java.util.List;
import java.util.Map;

/**
 * create : wyh
 * date : 2018/1/4
 */

@Mapper
public interface OtherProdMapper {

    @Select("select pdid,prodprice,thirtyprice,sixtyprice,ninetyprice,costprice,marketprice,pdno,pdstorenum,storeid,storename,intervalprice,startnum,stepwiseprice,freightmode,weight,minplus from productstore where pdid=#{pdid}")
    List<OtherProdStore> getOtherProdStore(@Param("pdid") Long pdid);


    @Select("select pdid,prodprice,thirtyprice,sixtyprice,ninetyprice,costprice,marketprice,pdno,pdstorenum,storeid,storename,intervalprice,startnum,stepwiseprice,freightmode,s.address as storeaddress,ST.temname from productstore pst left join store s on pst.storeid=s.id left join shippingtemplates ST on pst.freightmode=ST.id where pst.pdid=#{pdid}")
    List<OtherProdStoreForExcel> getOtherProdStoreForExportExcel(@Param("pdid") Long pdid);


    /**
     * 非紧固件商品列表
     * @param queryDto
     * @return
     */
    @SelectProvider(type = OtherProdProvider.class,method = "listOtherProd")
    List<OtherProdDetailViewDto> listOtherProd(@Param("queryDto")OtherProductQueryDto queryDto);

    /**
     * 非紧固件商品列表,商家中心商品管理专用
     * @param queryDto
     * @return
     */
    /*@SelectProvider(type = OtherProdProvider.class,method = "listOtherProductForSellerCenter")
    List<OtherProdDetailViewDto> listOtherProductForSellerCenter(@Param("queryDto")OtherProductQueryDto queryDto);*/


    @SelectProvider(type = OtherProdProvider.class,method = "listOtherProdForSellerExcel")
    List<Map<String,Object>> listOtherProdForSellerExcel(@Param("queryDto")OtherProductQueryDto queryDto);


    @SelectProvider(type = OtherProdProvider.class,method = "listOtherProdForAdminExcel")
    List<Map<String,Object>> listOtherProdForAdminExcel(@Param("queryDto")OtherProductQueryDto queryDto);

    /*@SelectProvider(type = OtherProdProvider.class,method = "listOtherProdCount")
    long getOtherProductInfoCount(@Param("queryDto")OtherProductQueryDto queryDto);*/

}
