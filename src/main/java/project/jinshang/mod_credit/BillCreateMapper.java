package project.jinshang.mod_credit;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import project.jinshang.mod_credit.bean.BillCreate;
import project.jinshang.mod_credit.bean.BillCreateExample;
import project.jinshang.mod_credit.bean.BillCreateQueryDto;

public interface BillCreateMapper {
    int countByExample(BillCreateExample example);

    int deleteByExample(BillCreateExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BillCreate record);

    int insertSelective(BillCreate record);

    List<BillCreate> selectByExample(BillCreateExample example);

    BillCreate selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BillCreate record, @Param("example") BillCreateExample example);

    int updateByExample(@Param("record") BillCreate record, @Param("example") BillCreateExample example);

    int updateByPrimaryKeySelective(BillCreate record);

    int updateByPrimaryKey(BillCreate record);

    @Select("select * from billcreate where buyerid=#{memberid} order by createtime desc limit 1")
    BillCreate getLast(Long memberid);

    @Select("select settlementtime from billcreate where buyerid=#{memberid} order by createtime desc limit #{count} ")
    List<String> getBillCreateTimeList(@Param("memberid") Long memberid,@Param("count") Integer count);


    @Select("<script>select * from billcreate B where 1=1 " +
            "<if test=\"buyerid != null and buyerid &gt; 0 \"> and B.buyerid=#{buyerid} </if>" +
            "<if test=\"billno != null and billno !='' \"> and B.billno=#{billno} </if>" +
            "<if test=\"settlementtime != null and settlementtime !='' \"> and B.settlementtime=#{settlementtime} </if>" +
            "<if test=\"state != null and state &gt; -1 \"> and B.state=#{state} </if>" +
            "</script>")
    List<BillCreate> list(BillCreateQueryDto dto);

    /**
     * 将过期未缴清的设置为已过期状态
     */
    @Update("update billcreate set state=2 , illegaldays=date_part('day', now() - lastrepaymentday)  where  money>amountpaid and " +
            "  now()> lastrepaymentday ")
    void setExpire();


    @Update("update billcreate set illegal=true,illegalmoney=money-amountpaid where state=2 and illegal=false ")
    void  fillIllegalData();
}