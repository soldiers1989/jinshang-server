package project.jinshang.mod_cash;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.*;
import project.jinshang.mod_admin.mod_cash.dto.AdvanceCapitalQueryDto;
import project.jinshang.mod_cash.bean.BuyerCapital;
import project.jinshang.mod_cash.bean.BuyerCapitalExample;
import project.jinshang.mod_cash.bean.dto.BuyerCapitalAccountQueryDto;
import project.jinshang.mod_cash.bean.dto.BuyerCapitalAdminExcel;
import project.jinshang.mod_cash.bean.dto.BuyerCapitalQueryDto;
import project.jinshang.mod_cash.bean.dto.BuyerCapitalViewDto;
import project.jinshang.mod_cash.provider.BuyerCapitalProvider;
import project.jinshang.mod_product.BillingRecordMapper;
import project.jinshang.mod_product.bean.BillingRecord;

public interface BuyerCapitalMapper {
    int countByExample(BuyerCapitalExample example);

    int deleteByExample(BuyerCapitalExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BuyerCapital record);

    int insertSelective(BuyerCapital record);

    List<BuyerCapital> selectByExample(BuyerCapitalExample example);

    BuyerCapital selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BuyerCapital record, @Param("example") BuyerCapitalExample example);

    int updateByExample(@Param("record") BuyerCapital record, @Param("example") BuyerCapitalExample example);

    int updateByPrimaryKeySelective(BuyerCapital record);

    int updateByPrimaryKey(BuyerCapital record);

    /**
     * 批量插入
     * @param list
     */
    @InsertProvider(type = BuyerCapitalProvider.class, method = "insertAll")
    void insertAll(List<BuyerCapital> list);



    /**
     * 获取未出账单总金额
     * @param memberid
     * @return
     */
    @Select("select sum(capital) as totalcapital from buyercapital where memberid=#{memberid} and paytype=4 and outbillstate=0  ") //and capitaltype in (0,2,6)
    BigDecimal getTotalNotOutBillMoney(@Param("memberid") long memberid);

    /**
     * 授信获取资金明细
     * @param ordernos
     * @return
     */
    @Select("select tradetime,capitaltype,orderno,transactionobj,capital from buyercapital where orderno in (${ordernos})")
    List<BuyerCapital> getBuyerCapitalList(@Param("ordernos") String ordernos);

    /**
     * 授信获取未出账单明细
     * @param memberid
     * @return
     */
    @Select("select tradetime,orderno,capital,capitaltype from buyercapital where memberid=#{memberid} and paytype=4 and outbillstate=0  order by tradetime desc") //and capitaltype in (0,2,6)
    List<BuyerCapital> getNotOutBuyerCapitalList(@Param("memberid") long memberid);

    /**
     * 授信获取未出账单明细
     * @param memberid
     * @return
     */
    @Select("select capital,capitaltype from buyercapital where memberid=#{memberid} and paytype=4 and outbillstate=0 and capitaltype in (0,2) ")
    List<BuyerCapital> getNotOutBuyerCapitalListSum(@Param("memberid") long memberid);

    /**
     * 买家资金明细查询
     * @param buyerCatpital
     * @return
     */
    @Select("<script>SELECT C.*,M.username,M.realname,B.companyname FROM buyercapital C left join member M on C.memberid=M.id left join buyercompanyinfo B on C.memberid=B.memberid " +
            "<where> 1=1" +
            "<if test=\"memberid != null and memberid !=0 \"> and C.memberid=#{memberid} </if>" +
            "<if test=\"username != null and username !='' \"> and M.username like '%${username}%' </if>"+
            "<if test=\"realname != null and realname !='' \"> and M.realname =#{realname} </if>"+
            "<if test=\"companyname != null and companyname !='' \"> and B.companyname = #{companyname} </if>"+
            "<if test=\"tradetimeStart != null \"> and tradetime>= #{tradetimeStart} </if>"+
            "<if test=\"tradetimeEnd != null \"> and tradetime &lt; #{tradetimeEnd} </if>"+
            "<if test=\"paytype != -1 \"> and paytype =#{paytype} </if>"+
            "<if test=\"rechargestate != -1 \"> and rechargestate =#{rechargestate} </if>"+
            "<if test=\"rechargeperform != -1 \"> and rechargeperform =#{rechargeperform} </if>"+
            "<if test=\"capitaltype ==0 \"> and capitaltype in (0,7,8,9) </if>"+
            "<if test=\"capitaltype &gt; 0 \"> and capitaltype = #{capitaltype} </if>"+
            "<if test=\"capitalStart != null \"> and capital &gt;= #{capitalStart} </if>"+
            "<if test=\"capitalEnd != null \"> and capital &lt;=#{capitalEnd} </if>"+
            "<if test=\"presentationnumber != null and presentationnumber !='' \"> and C.presentationnumber like '%${presentationnumber}%' </if>"+
            "<if test=\"rechargenumber != null and rechargenumber !='' \"> and C.rechargenumber like '%${rechargenumber}%' </if>"+
            "<if test=\"tradeno != null and tradeno !='' \"> and C.tradeno like '%${tradeno}%' </if>"+
            "<if test=\"orderno != null and orderno !='' \"> and C.orderno like '%${orderno}%' </if>"+
            "</where>  order by id desc " +
            "</script>")
    List<BuyerCapital> selectBuyerCapitalByConsume(BuyerCapitalQueryDto buyerCatpital);


    /**
     * 后台买家资金明细 导出Excel
     * @param buyerCatpital
     * @return
     */
    @Select("<script>SELECT C.*,M.username,B.companyname FROM buyercapital C left join member M on C.memberid=M.id left join buyercompanyinfo B on C.memberid=B.memberid " +
            "<where> 1=1" +
            "<if test=\"memberid != null and memberid !=0 \"> and C.memberid=#{memberid} </if>" +
            "<if test=\"username != null and username !='' \"> and M.username like '%${username}%' </if>"+
            "<if test=\"realname != null and realname !='' \"> and M.realname =#{realname} </if>"+
            "<if test=\"companyname != null and companyname !='' \"> and B.companyname = #{companyname} </if>"+
            "<if test=\"tradetimeStart != null \"> and tradetime>= #{tradetimeStart} </if>"+
            "<if test=\"tradetimeEnd != null \"> and tradetime &lt; #{tradetimeEnd} </if>"+
            "<if test=\"paytype != -1 \"> and paytype =#{paytype} </if>"+
            "<if test=\"rechargestate != -1 \"> and rechargestate =#{rechargestate} </if>"+
            "<if test=\"rechargeperform != -1 \"> and rechargeperform =#{rechargeperform} </if>"+
            "<if test=\"capitaltype ==0 \"> and capitaltype in (0,7,8,9) </if>"+
            "<if test=\"capitaltype &gt; 0 \"> and capitaltype = #{capitaltype} </if>"+
            "<if test=\"capitalStart != null \"> and capital &gt;= #{capitalStart} </if>"+
            "<if test=\"capitalEnd != null \"> and capital &lt;=#{capitalEnd} </if>"+
            "<if test=\"presentationnumber != null and presentationnumber !='' \"> and C.presentationnumber like '%${presentationnumber}%' </if>"+
            "<if test=\"rechargenumber != null and rechargenumber !='' \"> and C.rechargenumber like '%${rechargenumber}%' </if>"+
            "<if test=\"tradeno != null and tradeno !='' \"> and C.tradeno like '%${tradeno}%' </if>"+
            "<if test=\"orderno != null and orderno !='' \"> and C.orderno like '%${orderno}%' </if>"+
            "</where>  order by id desc " +
            "</script>")
    List<BuyerCapitalAdminExcel> selectBuyerCapitalByConsumeForAdminExcel(BuyerCapitalQueryDto buyerCatpital);










    @Select("select * from buyercapital where rechargenumber=#{rechagernumber} order by id desc limit 1")
    BuyerCapital getBuyerCapitalByRechargenumber(@Param("rechagernumber") String rechagernumber);



    @Select("<script>select M.id,M.username,M.realname,C.companyname,M.balance,G.gradename" +
//            ",(select sum(capital)" +
//            "    from buyercapital where capitaltype=1 and rechargestate=1 and memberid=M.id ) as totalcapital" +
            "    from member M left JOIN buyercompanyinfo C ON M.id=C.memberid left join membergrade G on M.gradleid=G.id where M.parentid=0 " +
            "<if test=\"memberid != null and memberid != 0\"> and M.id=#{memberid} </if>" +
            "<if test=\"username != null and username !=''\"> and (M.username like '%${username}%' or M.realname like '%${username}%' or C.companyname like '%${username}%')</if>" +
            "</script>")
    List<Map<String,Object>> advanceCapitalList( AdvanceCapitalQueryDto dto);


    /**
     * 资金明细列表查询
     * @param dto
     * @return
     */
    @SelectProvider(type = BuyerCapitalProvider.class,method = "list")
    List<BuyerCapitalViewDto> list(@Param("dto")BuyerCapitalQueryDto dto);

    /**
     * 对账单所用资金列表查询
     * @param dto
     * @return
     */
    @SelectProvider(type= BuyerCapitalProvider.class,method = "listForAccount")
    List<BuyerCapitalViewDto> listForAccount(@Param("dto")BuyerCapitalAccountQueryDto dto);

    /**
     * 资金明细查询导出
     * @param dto
     * @return
     */
    @SelectProvider(type = BuyerCapitalProvider.class,method = "listForPurchaserExportExcel")
    List<Map<String,Object>> listForPurchaserExportExcel(@Param("dto")BuyerCapitalQueryDto dto);

    @Update("update buyercapital set billcreateid=#{billcreateid},outbillstate=1 where id in (${ids})")
    void  updateBillcreateid(@Param("billcreateid") long billcreateid,@Param("ids") String ids);

    @Update("update buyercapital set state=#{state} where memberid=#{memberid} and id in (${ids})")
    void updateCapitalIsbackcredit(@Param("memberid") Long memberid,@Param("ids") String ids,@Param("state") Short state);


    @Select("select sum(capital) from buyercapital where memberid=#{memberid} and capitaltype=#{capitaltype} and paytype=#{paytype} and outbillstate=#{outbillstate} and tradetime>=#{starttime} and tradetime<=#{endtime}  ")
    BigDecimal getTotal(@Param("memberid") long memberid, @Param("capitaltype") Short capitaltype, @Param("paytype") Short paytype,
                        @Param("outbillstate") Short outbillstate, @Param("starttime")Date starttime,@Param("endtime") Date endtime);


    @SelectProvider(type = BuyerCapitalProvider.class,method = "breakContractListLogs")
    List<Map> breakContractListLogs(Map<String,Object> queryMap);


    /**
     * 后台导出违约金列表
     * @param queryMap
     * @return
     */
    @SelectProvider(type = BuyerCapitalProvider.class,method = "breakContractListLogsForAdminExcel")
    List<Map<String,Object>> breakContractListLogsForAdminExcel(Map<String,Object> queryMap);

    /**
     * 买家导出违约金列表
     * @param queryMap
     * @return
     */
    @SelectProvider(type = BuyerCapitalProvider.class,method = "breakContractListLogsForBuyerExcel")
    List<Map<String,Object>> breakContractListLogsForBuyerExcel(Map<String,Object> queryMap);

    /**
     * 后台资金明细导出Excel中需要导出商品详细信息的查询
     * @param code
     * @return
     */
    @Select("select pdname,op.standard,op.material,op.gradeno,op.brand,op.mark,p.surfacetreatment,packagetype,op.unit,op.price,op.num from \n" +
            "orderproduct op left join products p on op.pdid=p.id where orderno = #{orderno}")
    List<Map<String,Object>> getOrderProducts(@Param("orderno") String orderno);

    /**
     * 卖家应收账款财务管理excel导出
     * @param dto
     * @return
     */
    @SelectProvider(type = BuyerCapitalProvider.class,method = "ExcelExportUserCapitalManagement")
    List<Map<String,Object>> ExcelExportUserCapitalManagement(@Param("dto")BuyerCapitalQueryDto dto);

    /**
     * 卖家应收账款财务管理excel导出 查询商品列表
     * @param orderno
     * @return
     */
    @Select("select level1,producttype from productinfo where id in(select pdid from orderproduct where orderno=#{orderno})")
    List<Map<String,Object>> getProductList(@Param("orderno") String orderno);

    @Select("select DISTINCT memberid,invoiceheadup,texno,bankofaccounts,account,address,phone from billingrecord where memberid=#{memberid,jdbcType=BIGINT}")
    Map<String,Object> getBillingRecordByMemberid(@Param("memberid") Long memberid);
}