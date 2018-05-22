package project.jinshang.mod_purchase;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import project.jinshang.mod_purchase.bean.PurchaseCapital;
import project.jinshang.mod_purchase.bean.PurchaseCapitalExample;

public interface PurchaseCapitalMapper {
    int countByExample(PurchaseCapitalExample example);

    int deleteByExample(PurchaseCapitalExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PurchaseCapital record);

    int insertSelective(PurchaseCapital record);

    List<PurchaseCapital> selectByExample(PurchaseCapitalExample example);

    PurchaseCapital selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PurchaseCapital record, @Param("example") PurchaseCapitalExample example);

    int updateByExample(@Param("record") PurchaseCapital record, @Param("example") PurchaseCapitalExample example);

    int updateByPrimaryKeySelective(PurchaseCapital record);

    int updateByPrimaryKey(PurchaseCapital record);
}