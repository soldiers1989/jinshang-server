package project.jinshang.scheduled.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * create : wyh
 * date : 2017/12/19
 */

@Mapper
public interface BillcreateTaskMapper {

    /**
     * 查询出该账期内所有使用授信交易成功的订单
     * @param
     * @return
     */
    @Select("select sum(capital) from buyercapital bc where bc.memberid=#{memberid} and bc.capitaltype in (0,7,8,9) and bc.paytype=4  " +
            "and tradetime>=#{buyerinspectiontimeStart} and tradetime<=#{buyerinspectiontimeEnd}")
    public BigDecimal getPayTotalMoney(@Param("memberid") long memberid,
                                       @Param("buyerinspectiontimeStart") Date buyerinspectiontimeStart,
                                       @Param("buyerinspectiontimeEnd") Date buyerinspectiontimeEnd);


    @Select("select sum(capital) from buyercapital bc where bc.memberid=#{memberid} and bc.capitaltype=6 and bc.paytype=4  " +
            "and tradetime>=#{buyerinspectiontimeStart} and tradetime<=#{buyerinspectiontimeEnd}")
    public  BigDecimal getPenaltyTotalMoney(@Param("memberid") long memberid,
                                            @Param("buyerinspectiontimeStart") Date buyerinspectiontimeStart,
                                            @Param("buyerinspectiontimeEnd") Date buyerinspectiontimeEnd);


    /**
     * 查询出该账期内所有用户授信交易成功的订单
     * @param buyerinspectiontimeStart
     * @param buyerinspectiontimeEnd
     * @return
     */
    @Select("select sum(capital) from buyercapital bc where bc.capitaltype=0 and bc.paytype=4  and tradetime>=#{buyerinspectiontimeStart} and tradetime<=#{buyerinspectiontimeEnd}")
    public BigDecimal getAllUserPayTotalMoney( @Param("buyerinspectiontimeStart") Date buyerinspectiontimeStart, @Param("buyerinspectiontimeEnd") Date buyerinspectiontimeEnd);


    /**
     * 查询出该账期内使用的授信用户的个数
     * @param buyerinspectiontimeStart
     * @param buyerinspectiontimeEnd
     * @return
     */
    @Select("select count( DISTINCT  memberid) from buyercapital bc where bc.capitaltype=0 and bc.paytype=4  and tradetime>=#{buyerinspectiontimeStart} and tradetime<=#{buyerinspectiontimeEnd} ")
    public int getUsedMemberCount(@Param("buyerinspectiontimeStart") Date buyerinspectiontimeStart,@Param("buyerinspectiontimeEnd") Date buyerinspectiontimeEnd);



    /**
     * 查询出已经提前还款的订单总额
     * @param memberid
     * @param buyerinspectiontimeStart
     * @param buyerinspectiontimeEnd
     * @return
     */
    @Select("select sum(capital) from buyercapital bc where bc.memberid=#{memberid} and bc.capitaltype=11 and bc.paytype=3  and tradetime>=#{buyerinspectiontimeStart} and tradetime<=#{buyerinspectiontimeEnd}")
    public BigDecimal getepaymentTotalMoney(@Param("memberid") long memberid,@Param("buyerinspectiontimeStart") Date buyerinspectiontimeStart,@Param("buyerinspectiontimeEnd") Date buyerinspectiontimeEnd);






    /**
     * 查询该用户出该账期内退款的订单
     * @param memberid
     * @param buyerinspectiontimeStart
     * @param buyerinspectiontimeEnd
     * @return
     */
    @Select("select sum(capital) from buyercapital bc where bc.memberid=#{memberid} " +
            " and bc.capitaltype=2 and bc.paytype=4 " +
            " and tradetime>=#{buyerinspectiontimeStart} and tradetime<=#{buyerinspectiontimeEnd}")
    public  BigDecimal getDrawbackMoney(@Param("memberid") long memberid,@Param("buyerinspectiontimeStart") Date buyerinspectiontimeStart,@Param("buyerinspectiontimeEnd") Date buyerinspectiontimeEnd);


    /**
     * 查询所有用户出该账期内退款的订单
     * @param buyerinspectiontimeStart
     * @param buyerinspectiontimeEnd
     * @return
     */
    @Select("select sum(capital) from buyercapital bc where  " +
            " bc.capitaltype=2 and bc.paytype=4 " +
            " and tradetime>=#{buyerinspectiontimeStart} and tradetime<=#{buyerinspectiontimeEnd}")
    public  BigDecimal getAllUserDrawbackMoney(@Param("buyerinspectiontimeStart") Date buyerinspectiontimeStart,@Param("buyerinspectiontimeEnd") Date buyerinspectiontimeEnd);




    /**
     * 获取该账期内的所有资金信息
     * @param memberid
     * @param buyerinspectiontimeStart
     * @param buyerinspectiontimeEnd
     * @return
     */
    @Select("select id from buyercapital bc  where bc.memberid=#{memberid} and tradetime>=#{buyerinspectiontimeStart} and tradetime<=#{buyerinspectiontimeEnd}  and " +
            "( (bc.capitaltype=0 and bc.paytype=4) or (bc.capitaltype=5 and bc.paytype=4 ) or (bc.capitaltype=2 and bc.paytype=4) or (bc.capitaltype=6 and bc.paytype=4))")
    public List<Map<String,Object>> getCreditCapitalIds(@Param("memberid") long memberid, @Param("buyerinspectiontimeStart") Date buyerinspectiontimeStart, @Param("buyerinspectiontimeEnd") Date buyerinspectiontimeEnd);

}
