package project.jinshang.mod_sellerbill.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import project.jinshang.mod_sellerbill.bean.SellerBillOrder;
import project.jinshang.mod_sellerbill.bean.SellerBillOrderExample;

public interface SellerBillOrderMapper {
    int countByExample(SellerBillOrderExample example);

    int deleteByExample(SellerBillOrderExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SellerBillOrder record);

    int insertSelective(SellerBillOrder record);

    List<SellerBillOrder> selectByExample(SellerBillOrderExample example);

    SellerBillOrder selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SellerBillOrder record, @Param("example") SellerBillOrderExample example);

    int updateByExample(@Param("record") SellerBillOrder record, @Param("example") SellerBillOrderExample example);

    int updateByPrimaryKeySelective(SellerBillOrder record);

    int updateByPrimaryKey(SellerBillOrder record);

    @Insert("<script>insert into sellerbillorder(sellerbillid,orderid,orderno,ordermoney,buyername,fishtime,breakpaymoney) values" +
            "<foreach collection=\"list\" item=\"item\" index=\"index\" separator=\",\">  \n" +
            "    (#{item.sellerbillid},#{item.orderid},#{item.orderno},#{item.ordermoney},#{item.buyername},#{item.fishtime},#{item.breakpaymoney})" +
            "</foreach> </script>")
    int batchInsert(@Param("list") List<SellerBillOrder> list);
}