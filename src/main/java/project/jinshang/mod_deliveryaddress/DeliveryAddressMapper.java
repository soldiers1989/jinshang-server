package project.jinshang.mod_deliveryaddress;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import project.jinshang.mod_deliveryaddress.bean.DeliveryAddress;
import project.jinshang.mod_deliveryaddress.bean.DeliveryAddressExample;

public interface DeliveryAddressMapper {
    int countByExample(DeliveryAddressExample example);

    int deleteByPrimaryKey(long id);

    int insert(DeliveryAddress record);

    int insertSelective(DeliveryAddress record);

    List<DeliveryAddress> selectByExample(DeliveryAddressExample example);

    DeliveryAddress selectByPrimaryKey(long id);

    int updateByExampleSelective(@Param("record") DeliveryAddress record, @Param("example") DeliveryAddressExample example);

    int updateByExample(@Param("record") DeliveryAddress record, @Param("example") DeliveryAddressExample example);

    int updateByPrimaryKeySelective(DeliveryAddress record);

    int updateByPrimaryKey(DeliveryAddress record);

    @Update("update deliveryaddress set isdefault = 0 WHERE  memberid = #{memberid}")
    int updatedefultAddressBymemberid(@Param("memberid")long memberid);

    @Select("SELECT memberid FROM deliveryaddress where id = #{id}")
    DeliveryAddress selectMemberidByid(long id);

    @Select("select * from deliveryaddress where memberid=#{memberid} and isdefault=1 limit 1 ")
    DeliveryAddress getDefaultDeliveryAddress(@Param("memberid") long memberid);

    @Update("update   deliveryaddress set isdefault=#{isdefault} where id=#{id} ")
    int updateIsdefault(@Param("id") long id,@Param("isdefault") short isdefault);


}