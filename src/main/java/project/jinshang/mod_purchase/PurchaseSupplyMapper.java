package project.jinshang.mod_purchase;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import project.jinshang.mod_purchase.bean.PurchaseSupply;
import project.jinshang.mod_purchase.bean.PurchaseSupplyExample;

public interface PurchaseSupplyMapper {
    int countByExample(PurchaseSupplyExample example);

    int deleteByExample(PurchaseSupplyExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PurchaseSupply record);

    int insertSelective(PurchaseSupply record);

    List<PurchaseSupply> selectByExample(PurchaseSupplyExample example);

    PurchaseSupply selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PurchaseSupply record, @Param("example") PurchaseSupplyExample example);

    int updateByExample(@Param("record") PurchaseSupply record, @Param("example") PurchaseSupplyExample example);

    int updateByPrimaryKeySelective(PurchaseSupply record);

    int updateByPrimaryKey(PurchaseSupply record);
}