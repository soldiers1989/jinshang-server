package project.jinshang.mod_cash;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import project.jinshang.mod_admin.mod_cash.dto.AdvanceCapitalQueryDto;
import project.jinshang.mod_admin.mod_cash.dto.AdvanceCapitalSellerQueryDto;
import project.jinshang.mod_cash.bean.BuyerCapital;
import project.jinshang.mod_cash.bean.SalerCapital;
import project.jinshang.mod_cash.bean.SalerCapitalExample;
import project.jinshang.mod_cash.bean.dto.SalerCapitalAdminExcel;
import project.jinshang.mod_cash.bean.dto.SalerCapitalQueryDto;
import project.jinshang.mod_cash.bean.dto.SalerCapitalSellerExportExcel;
import project.jinshang.mod_cash.bean.dto.SalerCapitalViewDto;
import project.jinshang.mod_cash.provider.BuyerCapitalProvider;
import project.jinshang.mod_cash.provider.SalerCapitalProvider;

public interface SalerCapitalMapper {
    int countByExample(SalerCapitalExample example);

    int deleteByExample(SalerCapitalExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SalerCapital record);

    int insertSelective(SalerCapital record);

    List<SalerCapital> selectByExample(SalerCapitalExample example);

    SalerCapital selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SalerCapital record, @Param("example") SalerCapitalExample example);

    int updateByExample(@Param("record") SalerCapital record, @Param("example") SalerCapitalExample example);

    int updateByPrimaryKeySelective(SalerCapital record);

    int updateByPrimaryKey(SalerCapital record);

    /**
     * 卖家资金明细查询
     * @param salerCapital
     * @return
     */
    @Select("<script>SELECT S.*,M.username,M.realname,C.companyname,C.shopname FROM salercapital S left join member M on S.memberid=M.id " +
            "left join sellercompanyinfo C on S.memberid=C.memberid" +
            "<where> 1=1" +
            "<if test=\"memberid != null and memberid != 0\"> and S.memberid=#{memberid} </if>" +
            "<if test=\"username != null and username !=''\"> and M.username like '%${username}%' </if>"+
            "<if test=\"realname != null and realname !=''\"> and M.realname =#{realname}</if>"+
            "<if test=\"companyname != null and companyname !='' \"> and C.companyname like  '%${companyname}%' </if>"+
            "<if test=\"shopname != null and shopname !='' \"> and C.shopname like  '%${shopname}%' </if>"+
            "<if test=\"tradetimeStart != null \"> and tradetime>= #{tradetimeStart} </if>"+
            "<if test=\"tradetimeEnd != null \"> and tradetime &lt; #{tradetimeEnd} </if>"+
            "<if test=\"rechargeperform != null and rechargeperform != -1 \"> and S.rechargeperform =#{rechargeperform} </if>"+
            "<if test=\"rechargestate != null and rechargestate != -1\"> and S.rechargestate =#{rechargestate} </if>"+
            "<if test=\"capitaltype == 0\"> and S.capitaltype in (0,8,9,10) </if>"+
          //  "<if test=\"capitaltype == -2\"> and S.capitaltype in (5,11) </if>"+
            "<if test=\"capitaltype != null and capitaltype &gt; 0\"> and S.capitaltype=#{capitaltype} </if>"+

            "<if test=\"presentationnumber != null and presentationnumber !='' \"> and S.presentationnumber like '%${presentationnumber}' </if>"+
            "<if test=\"rechargenumber != null and rechargenumber !='' \"> and S.rechargenumber like '%${rechargenumber}%' </if>"+
            "<if test=\"tradeno != null and tradeno !='' \"> and S.tradeno like '%${tradeno}%' </if>"+
            "<if test=\"orderno != null and orderno !='' \"> and S.orderno like '%${orderno}%' </if>"+
            "<if test=\"capitalStart != null \">and ordercapital &gt;= #{capitalStart} </if>"+
            "<if test=\"capitalEnd != null \">and ordercapital &lt;=#{capitalEnd} </if>"+
            "</where>  order by S.id desc " +
            "</script>")
    List<SalerCapital> selectSalerCapitalByConsume(SalerCapitalQueryDto salerCapital);


    /**
     * 后台卖家资金明细导出 for excel
     * @param salerCapital
     * @return
     */
    @Select("<script>select OS.createtime,OS.code,OS.orderno,OS.transactionnumber,M.realname,M.username,C.companyname,OS.shopname,OS.sellername,S.capitaltype,OS.isonline,S.ordercapital as capital,\n" +
            "OS.isbilling,OS.orderstatus,OS.shipto,OS.province,OS.city,OS.area,OS.receivingaddress,OS.paytype,OS.logisticscompany,OS.couriernumber,S.operation,\n" +
            "OS.transactionid,OS.uuid,S.memberid\n" +
            "from salercapital S \n" +
            "left join sellercompanyinfo C on S.memberid=C.memberid \n" +
            "left join orders OS on S.orderno=OS.orderno \n" +
            "left join member M on C.memberid=M.id" +
            "<where> 1=1" +
            "<if test=\"memberid != null and memberid != 0\"> and S.memberid=#{memberid} </if>" +
            "<if test=\"username != null and username !=''\"> and M.username like '%${username}%' </if>"+
            "<if test=\"realname != null and realname !=''\"> and M.realname =#{realname}</if>"+
            "<if test=\"companyname != null and companyname !='' \"> and C.companyname like  '%${companyname}%' </if>"+
            "<if test=\"shopname != null and shopname !='' \"> and C.shopname like  '%${shopname}%' </if>"+
            "<if test=\"tradetimeStart != null \"> and tradetime>= #{tradetimeStart} </if>"+
            "<if test=\"tradetimeEnd != null \"> and tradetime &lt; #{tradetimeEnd} </if>"+
            "<if test=\"rechargeperform != null and rechargeperform != -1 \"> and S.rechargeperform =#{rechargeperform} </if>"+
            "<if test=\"rechargestate != null and rechargestate != -1\"> and S.rechargestate =#{rechargestate} </if>"+
            "<if test=\"capitaltype == 0\"> and S.capitaltype in (0,8,9,10) </if>"+
            //  "<if test=\"capitaltype == -2\"> and S.capitaltype in (5,11) </if>"+
            "<if test=\"capitaltype != null and capitaltype &gt; 0\"> and S.capitaltype=#{capitaltype} </if>"+

            "<if test=\"presentationnumber != null and presentationnumber !='' \"> and S.presentationnumber like '%${presentationnumber}' </if>"+
            "<if test=\"rechargenumber != null and rechargenumber !='' \"> and S.rechargenumber like '%${rechargenumber}%' </if>"+
            "<if test=\"tradeno != null and tradeno !='' \"> and S.tradeno like '%${tradeno}%' </if>"+
            "<if test=\"orderno != null and orderno !='' \"> and S.orderno like '%${orderno}%' </if>"+
            "<if test=\"capitalStart != null \">and ordercapital &gt;= #{capitalStart} </if>"+
            "<if test=\"capitalEnd != null \">and ordercapital &lt;=#{capitalEnd} </if>"+
            "</where>  order by S.id desc " +
            "</script>")
    List<Map<String,Object>> selectSalerCapitalByConsumeForAdminExcel(SalerCapitalQueryDto salerCapital);





    @Select("select * from salercapital where rechargenumber=#{rechargenumber} order by id desc limit 1")
    SalerCapital getSalerCapitalByRechargenumber(@Param("rechargenumber") String rechargenumber);



    @Select("<script>select M.id,M.username,M.realname,C.companyname,M.sellerbanlance,M.sellerfreezebanlance,M.goodsbanlance,G.gradename,C.shopname" +
//            ",(select sum(ordercapital)" +
//            " from salercapital where capitaltype=1 and rechargestate=1 and memberid=M.id ) as totalcapital" +
            " from member M join  sellercompanyinfo C on M.id=C.memberid left join shopgrade G on C.shopgradeid=G.id where 1=1" +
            "<if test=\"memberid != null and  memberid != 0\"> and M.id=#{memberid} </if>" +
            "<if test=\"username != null and username !=''\"> and M.username like '%${username}%' </if>" +
            "<if test=\"realname != null and realname !=''\"> and M.realname like '%${realname}%' </if>" +
            "<if test=\"companyname != null and companyname !=''\"> and C.companyname like '%${companyname}%' </if>" +
            "<if test=\"shopname != null and shopname !=''\"> and C.shopname like '%${shopname}%' </if>" +
            "</script>")
    List<Map<String,Object>> advanceCapitalList(AdvanceCapitalSellerQueryDto queryDto);


    @SelectProvider(type = SalerCapitalProvider.class,method = "list")
    List<SalerCapitalViewDto> list(@Param("dto")SalerCapitalQueryDto dto);




    @SelectProvider(type = SalerCapitalProvider.class,method = "listForSellerExportExcel")
    List<SalerCapitalSellerExportExcel> listForSellerExportExcel(@Param("dto")SalerCapitalQueryDto dto);



    /**
     * 批量插入
     * @param list
     */
    @InsertProvider(type = SalerCapitalProvider.class, method = "insertAll")
    void insertAll(List<SalerCapital> list);

    /**
     * 批量插入 同时插入billtoserver
     * @param list
     */
    @InsertProvider(type = SalerCapitalProvider.class, method = "insertAllNew")
    void insertAllNew(List<SalerCapital> list);

    /**
     *
     * @param salerid
     * @return
     */
 /*   @Select("select id,orderno,membername,frozepay,brokepay,buyerinspectiontime as overtime from orders where saleid=#{saleid} " +
            "and orderstatus=5 and billtoserver=0 order by buyerinspectiontime asc ")*/
    @Select("select sc.id,sc.memberid,sc.tradeno,sc.orderno,sc.tradetime,sc.penalty,sc.buyerid,billtoserver from salercapital sc where sc.type = 2 ")
    List<Map<String,Object>> getWaitPenaltyOpenBillList(Long salerid);

    @Select("<script>select sc.* from salercapital sc  where sc.id in <foreach collection=\"ids\" item=\"item\" index=\"index\" \n" +
            "open=\"(\" separator=\",\" close=\")\">#{item}</foreach></script>")
    List<SalerCapital> getSalerCapitalByIds(@Param("ids")Long[] ids);


    @Select("<script>select sc.id,sc.memberid,sc.tradeno,sc.orderno,sc.tradetime,sc.ordercapital,sc.bail,sc.penalty,sc.capitaltype,sc.rechargeperform,sc.rechargeperform,sc.brokerage,sc.buyerid,sc.billtoserver,o.paytype  from salercapital sc left join orders o on sc.orderno = o.orderno where  sc.billtoserver = 0 and sc.capitaltype = 6 and sc.id in <foreach collection=\"ids\" item=\"item\" index=\"index\" \n" +
            "open=\"(\" separator=\",\" close=\")\">#{item}</foreach> </script>")
    List<Map<String,Object>> getExcelSellerBillid(@Param("sellerid") Long sellerid,@Param("ids") Long[] ids);

}