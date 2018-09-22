package project.jinshang.mod_sellerbill.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import project.jinshang.mod_sellerbill.bean.SellerBillBreak;
import project.jinshang.mod_sellerbill.bean.SellerBillBreakExample;

public interface SellerBillBreakMapper {
    int countByExample(SellerBillBreakExample example);

    int deleteByExample(SellerBillBreakExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SellerBillBreak record);

    int insertSelective(SellerBillBreak record);

    List<SellerBillBreak> selectByExample(SellerBillBreakExample example);

    SellerBillBreak selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SellerBillBreak record, @Param("example") SellerBillBreakExample example);

    int updateByExample(@Param("record") SellerBillBreak record, @Param("example") SellerBillBreakExample example);

    int updateByPrimaryKeySelective(SellerBillBreak record);

    int updateByPrimaryKey(SellerBillBreak record);
}