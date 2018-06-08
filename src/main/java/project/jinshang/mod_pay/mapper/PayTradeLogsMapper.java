package project.jinshang.mod_pay.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import project.jinshang.mod_pay.bean.PayTradeLogs;
import project.jinshang.mod_pay.bean.PayTradeLogsExample;

public interface PayTradeLogsMapper {
    int countByExample(PayTradeLogsExample example);

    int deleteByExample(PayTradeLogsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PayTradeLogs record);

    int insertSelective(PayTradeLogs record);

    List<PayTradeLogs> selectByExample(PayTradeLogsExample example);

    PayTradeLogs selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PayTradeLogs record, @Param("example") PayTradeLogsExample example);

    int updateByExample(@Param("record") PayTradeLogs record, @Param("example") PayTradeLogsExample example);

    int updateByPrimaryKeySelective(PayTradeLogs record);

    int updateByPrimaryKey(PayTradeLogs record);

    @Insert("<script>insert into paytradelogs(outtradeno,orderno,orderid,createtime,ordertype,paystates,paytype) values " +
            "<foreach collection=\"list\" index=\"index\" item=\"item\" separator=\",\" >" +
            "(#{item.outtradeno},#{item.orderno},#{item.orderid},#{item.createtime},#{item.ordertype},#{item.paystates},#{item.paytype})" +
            "</foreach></script>")
    int batchInsert(@Param("list") List<PayTradeLogs> list);
}