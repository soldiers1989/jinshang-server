package project.jinshang.mod_company;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import project.jinshang.mod_company.bean.SellerCompanyInfo;
import project.jinshang.mod_company.bean.SellerCompanyInfoExample;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface SellerCompanyInfoMapper {
    int countByExample(SellerCompanyInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SellerCompanyInfo record);

    int insertSelective(SellerCompanyInfo record);

    List<SellerCompanyInfo> selectByExample(SellerCompanyInfoExample example);

    SellerCompanyInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SellerCompanyInfo record, @Param("example") SellerCompanyInfoExample example);

    int updateByExample(@Param("record") SellerCompanyInfo record, @Param("example") SellerCompanyInfoExample example);

    int updateByPrimaryKeySelective(SellerCompanyInfo record);

    int updateByPrimaryKey(SellerCompanyInfo record);

    @Select("<script>SELECT sc.id,sc.memberid,sc.companyname,mm.username,sc.address,sp.gradename,sc.createdate,sc.shopperiod,sc.shopstate,sc.isrecommend,sc.validate,sc.deliverymode " +
            "FROM sellercompanyinfo sc  " +
            "LEFT JOIN MEMBER mm ON sc.memberid = mm.id " +
            "LEFT JOIN shopgrade sp ON sc.shopgradeid=sp.id " +
            "LEFT JOIN orders od on mm.id=od.saleid "+
            "<where> 1=1" +
            "<if test=\"validate != -1\"> and sc.validate=#{validate} </if>"+
            "<if test=\"companyname != null and companyname != '' \">and sc.companyname like '%${companyname}%' </if>" +
            "<if test=\"username != null and username != '' \">and mm.username like '%${username}%' </if>"+
            "<if test=\"shopgradeid != 0 \">and sc.shopgradeid =#{shopgradeid}</if>"+
            "</where> " +
            " GROUP BY sc.ID," +
            "sc.companyname," +
            "mm.username," +
            "sc.address," +
            "sp.gradename," +
            "sc.createdate," +
            "sc.shopperiod," +
            "sc.shopstate," +
            "sc.isrecommend," +
            "sc.validate "+
            " order by sc.id desc "+
            "</script>")
    List<Map> selectShop(Map<String, Object> map);




    @Select("<script>SELECT sc.id,sc.companyname,mm.username,sc.province,sc.city,sc.citysmall,sc.address,sp.gradename,sc.createdate,sc.companytel," +
            "sc.linkmantel,sc.regfound,sc.businesslicencenumber,sc.businesscategory,sc.businesslicencestart,sc.businesslicenceend,sc.businessscope,sc.bankaccount,sc.bankname," +
            "sc.alipayno,sc.wxno,sc.taxregistrationcertificate,sc.taxRegistrationno,sc.employeenum," +
            "sc.shopperiod,sc.shopstate,sc.isrecommend,sc.validate " +
            "FROM sellercompanyinfo sc  " +
            "LEFT JOIN MEMBER mm ON sc.memberid = mm.id " +
            "LEFT JOIN shopgrade sp ON sc.shopgradeid=sp.id" +
            "<where> 1=1" +
            "<if test=\"validate != -1\"> and sc.validate=#{validate} </if>"+
            "<if test=\"companyname != null and companyname != '' \">and sc.companyname like '%${companyname}%' </if>" +
            "<if test=\"username != null and username != '' \">and mm.username like '%${username}%' </if>"+
            "<if test=\"shopgradeid != 0 \">and sc.shopgradeid =#{shopgradeid}</if>"+
            "</where> " +
            " order by sc.id desc "+
            "</script>")
    List<Map<String,Object>> selectShopForAdminExcel(Map<String, Object> map);


    @Select("select * from sellercompanyinfo where companyname=#{companyname} limit 1")
    SellerCompanyInfo getByCompanyName(@Param("companyname") String companyname);

    @Select("select sum(brokepay) from orders where saleid=#{sellerid}")
    BigDecimal getSellerSumBokerBySellerId(@Param("sellerid") Long sellerid);


    @Select("select * from sellercompanyinfo where memberid=#{memberid} order by id desc limit 1")
    SellerCompanyInfo getSellerCompanyByMemberid(@Param("memberid") long memberid);
}