package project.jinshang.mod_pay.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import project.jinshang.mod_pay.bean.PayLogs;
import project.jinshang.mod_pay.bean.PayLogsExample;

public interface PayLogsMapper {
    int countByExample(PayLogsExample example);

    int deleteByExample(PayLogsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PayLogs record);

    int insertSelective(PayLogs record);

    List<PayLogs> selectByExample(PayLogsExample example);

    PayLogs selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PayLogs record, @Param("example") PayLogsExample example);

    int updateByExample(@Param("record") PayLogs record, @Param("example") PayLogsExample example);

    int updateByPrimaryKeySelective(PayLogs record);

    int updateByPrimaryKey(PayLogs record);
}