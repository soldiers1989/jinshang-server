package project.jinshang.mod_purchase;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import project.jinshang.mod_purchase.bean.PurchaseBill;
import project.jinshang.mod_purchase.bean.PurchaseBillExample;

public interface PurchaseBillMapper {
    int countByExample(PurchaseBillExample example);

    int deleteByExample(PurchaseBillExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PurchaseBill record);

    int insertSelective(PurchaseBill record);

    List<PurchaseBill> selectByExample(PurchaseBillExample example);

    PurchaseBill selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PurchaseBill record, @Param("example") PurchaseBillExample example);

    int updateByExample(@Param("record") PurchaseBill record, @Param("example") PurchaseBillExample example);

    int updateByPrimaryKeySelective(PurchaseBill record);

    int updateByPrimaryKey(PurchaseBill record);
}