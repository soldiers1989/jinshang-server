package project.jinshang.mod_shippingaddress;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import project.jinshang.mod_shippingaddress.bean.ShippingAddress;
import project.jinshang.mod_shippingaddress.bean.ShippingAddressExample;

public interface ShippingAddressMapper {
    int countByExample(ShippingAddressExample example);

    int deleteByPrimaryKey(long id);

    int insert(ShippingAddress record);

    int insertSelective(ShippingAddress record);

    List<ShippingAddress> selectByExample(ShippingAddressExample example);

    ShippingAddress selectByPrimaryKey(long id);

    int updateByExampleSelective(@Param("record") ShippingAddress record, @Param("example") ShippingAddressExample example);

    int updateByExample(@Param("record") ShippingAddress record, @Param("example") ShippingAddressExample example);

    int updateByPrimaryKeySelective(ShippingAddress record);

    int updateByPrimaryKey(ShippingAddress record);

    @Select("SELECT * FROM shippingaddress where id = #{id} and type=#{type} ")
    ShippingAddress selectByidAndType(@Param("id")long id,@Param("type") short type);

    @Select("select * from shippingaddress where memberid=#{memberid} and type=#{type} and isdefault=1 limit 1")
    ShippingAddress getDefaultShippingAddress(@Param("memberid") long memberid,@Param("type") short type);

    @Update("update shippingaddress set isdefault=0 where memberid=#{memberid} and type=#{type}")
    void upateAllToNotDefault(@Param("memberid") long memberid, @Param("type") short type);

}