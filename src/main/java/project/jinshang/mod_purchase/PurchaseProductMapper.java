package project.jinshang.mod_purchase;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import project.jinshang.mod_purchase.bean.PurchaseProduct;
import project.jinshang.mod_purchase.bean.PurchaseProductExample;

public interface PurchaseProductMapper {
    int countByExample(PurchaseProductExample example);

    int deleteByExample(PurchaseProductExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PurchaseProduct record);

    int insertSelective(PurchaseProduct record);

    List<PurchaseProduct> selectByExample(PurchaseProductExample example);

    PurchaseProduct selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PurchaseProduct record, @Param("example") PurchaseProductExample example);

    int updateByExample(@Param("record") PurchaseProduct record, @Param("example") PurchaseProductExample example);

    int updateByPrimaryKeySelective(PurchaseProduct record);

    int updateByPrimaryKey(PurchaseProduct record);
}