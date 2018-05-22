package project.jinshang.mod_credit;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import project.jinshang.mod_admin.mod_creditapplyrecord.bean.AccountDetailQuery;
import project.jinshang.mod_admin.mod_creditapplyrecord.provider.CreditapplyrecordProvider;
import project.jinshang.mod_credit.bean.CreditApplyRecord;
import project.jinshang.mod_credit.bean.CreditApplyRecordExample;

public interface CreditApplyRecordMapper {
    int countByExample(CreditApplyRecordExample example);

    int deleteByExample(CreditApplyRecordExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CreditApplyRecord record);

    int insertSelective(CreditApplyRecord record);

    List<CreditApplyRecord> selectByExample(CreditApplyRecordExample example);

    CreditApplyRecord selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CreditApplyRecord record, @Param("example") CreditApplyRecordExample example);

    int updateByExample(@Param("record") CreditApplyRecord record, @Param("example") CreditApplyRecordExample example);

    int updateByPrimaryKeySelective(CreditApplyRecord record);

    int updateByPrimaryKey(CreditApplyRecord record);

    @SelectProvider(type = CreditapplyrecordProvider.class,method = "listForAdmin")
    List<CreditApplyRecord> listForAdmin(Map<String,Object> map);

    @SelectProvider(type = CreditapplyrecordProvider.class,method = "listForAdminExcel")
    List<CreditApplyRecord> listForAdminExcel(Map<String,Object> map);



    @SelectProvider(type = CreditapplyrecordProvider.class,method = "listCreditUser")
    List<Map<String,Object>> listCreditUser(Map<String,Object> map);


    /**
     * 后台授信会员导出Excel
     * @param map
     * @return
     */
    @SelectProvider(type = CreditapplyrecordProvider.class,method = "listCreditUserForAdminExportExcle")
    List<Map<String,Object>> listCreditUserForAdminExportExcle(Map<String,Object> map);



    @SelectProvider(type = CreditapplyrecordProvider.class,method = "listUserBillCreate")
    List<Map<String,Object>> listUserBillCreate(@Param("memberid") long memberid);


    @SelectProvider(type = CreditapplyrecordProvider.class,method = "creditUser")
    Map<String,Object> creditUser(@Param("memberid") long memberid);


    @Select("select settlementtime from billcreate  group by settlementtime order by settlementtime desc ")
    List<String> getGroupSettlementtime();



    @Select("select count(id) from billcreate where settlementtime=#{settlement}")
    int  getUserMeberBySettlement(@Param("settlement") String settlement);

    @Select("select sum(money) from billcreate where settlementtime=#{settlement}")
    BigDecimal getOutMoneyBySettlement(@Param("settlement") String settlement);


    @Select("select sum(amountpaid) from billcreate where settlementtime=#{settlement}")
    BigDecimal getInMoneyBySettlement(@Param("settlement") String settlement);


    @Select("select count(id) from billcreate where settlementtime=#{settlement} and state=#{state} ")
    int getCountBySettlementAndState(@Param("settlement") String settlement,@Param("state") Short state);


    @Select("<script>select B.*,M.username,BC.companyname from billcreate B left join member M on " +
            " B.buyerid=M.id left join buyercompanyinfo BC on B.buyerid=BC.memberid where 1=1 " +
            "<if test=\"query.memberid &gt; 0\"> and M.id=#{query.memberid} </if>"+
            "<if test=\"query.membername != null and query.membername != '' \"> and M.realname  like '%${query.membername}' </if>"+
            "<if test=\"query.companyname != null and query.companyname != '' \"> and BC.companyname  like '%${query.companyname}' </if>"+
            "<if test=\"query.settlement != null and query.settlement != ''\"> and B.settlementtime=#{query.settlement} </if>" +
            "<if test=\"query.billno != null and query.billno != ''\"> and B.billno like '%${query.billno}%' </if>" +
            "</script>")
    List<Map> getAccountDetaiByPage(@Param("query")AccountDetailQuery query);


    /**
     * 根据状态查询授信个数
     * @param state
     * @return
     */
    @Select("select count(id) from creditapplyrecord where state=#{state}")
    int getCountByStates(@Param("state") Short state);

}