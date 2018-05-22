package project.jinshang.mod_product;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import project.jinshang.mod_product.bean.BillType;
import project.jinshang.mod_product.bean.BillTypeExample;

public interface BillTypeMapper {
    int countByExample(BillTypeExample example);

    int deleteByExample(BillTypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BillType record);

    int insertSelective(BillType record);

    List<BillType> selectByExample(BillTypeExample example);

    BillType selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BillType record, @Param("example") BillTypeExample example);

    int updateByExample(@Param("record") BillType record, @Param("example") BillTypeExample example);

    int updateByPrimaryKeySelective(BillType record);

    int updateByPrimaryKey(BillType record);
}