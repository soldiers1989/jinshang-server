package project.jinshang.mod_product;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import project.jinshang.mod_product.bean.SellerBillRecord;
import project.jinshang.mod_product.bean.SellerBillRecordExample;

public interface SellerBillRecordMapper {
    int countByExample(SellerBillRecordExample example);

    int deleteByExample(SellerBillRecordExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SellerBillRecord record);

    int insertSelective(SellerBillRecord record);

    List<SellerBillRecord> selectByExample(SellerBillRecordExample example);

    SellerBillRecord selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SellerBillRecord record, @Param("example") SellerBillRecordExample example);

    int updateByExample(@Param("record") SellerBillRecord record, @Param("example") SellerBillRecordExample example);

    int updateByPrimaryKeySelective(SellerBillRecord record);

    int updateByPrimaryKey(SellerBillRecord record);
}