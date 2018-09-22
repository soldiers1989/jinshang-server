package project.jinshang.mod_sellerbill.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import project.jinshang.mod_sellerbill.bean.SellerBill;
import project.jinshang.mod_sellerbill.bean.SellerBillExample;
import project.jinshang.mod_sellerbill.bean.SellerBillQuery;
import project.jinshang.mod_sellerbill.provider.SellerBillProvider;

public interface SellerBillMapper {
    int countByExample(SellerBillExample example);

    int deleteByExample(SellerBillExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SellerBill record);

    int insertSelective(SellerBill record);

    List<SellerBill> selectByExample(SellerBillExample example);

    SellerBill selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SellerBill record, @Param("example") SellerBillExample example);

    int updateByExample(@Param("record") SellerBill record, @Param("example") SellerBillExample example);

    int updateByPrimaryKeySelective(SellerBill record);

    int updateByPrimaryKey(SellerBill record);

    @SelectProvider(type = SellerBillProvider.class,method = "getSellerBill")
    List<SellerBill> getSellerBill(SellerBillQuery query);

    @SelectProvider(type = SellerBillProvider.class,method = "getSellerBill1")
    List<SellerBill> getSellerBill1(SellerBillQuery query);

    @Select("select * from sellerBill where id = #{id}")
    List<SellerBill> getSellerBillById(@Param("id") long id);
}