package project.jinshang.mod_transaction;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import project.jinshang.mod_transaction.bean.TransactionLog;
import project.jinshang.mod_transaction.bean.TransactionLogExample;

public interface TransactionLogMapper {
    int countByExample(TransactionLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TransactionLog record);

    int insertSelective(TransactionLog record);

    List<TransactionLog> selectByExample(TransactionLogExample example);

    TransactionLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TransactionLog record, @Param("example") TransactionLogExample example);

    int updateByExample(@Param("record") TransactionLog record, @Param("example") TransactionLogExample example);

    int updateByPrimaryKeySelective(TransactionLog record);

    int updateByPrimaryKey(TransactionLog record);
}