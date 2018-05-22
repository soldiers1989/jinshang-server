package project.jinshang.mod_purchase;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import project.jinshang.mod_purchase.bean.PurchaseStoreRecord;
import project.jinshang.mod_purchase.bean.PurchaseStoreRecordExample;

public interface PurchaseStoreRecordMapper {
    int countByExample(PurchaseStoreRecordExample example);

    int deleteByExample(PurchaseStoreRecordExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PurchaseStoreRecord record);

    int insertSelective(PurchaseStoreRecord record);

    List<PurchaseStoreRecord> selectByExample(PurchaseStoreRecordExample example);

    PurchaseStoreRecord selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PurchaseStoreRecord record, @Param("example") PurchaseStoreRecordExample example);

    int updateByExample(@Param("record") PurchaseStoreRecord record, @Param("example") PurchaseStoreRecordExample example);

    int updateByPrimaryKeySelective(PurchaseStoreRecord record);

    int updateByPrimaryKey(PurchaseStoreRecord record);
}