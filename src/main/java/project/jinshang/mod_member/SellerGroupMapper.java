package project.jinshang.mod_member;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import project.jinshang.mod_member.bean.SellerGroup;
import project.jinshang.mod_member.bean.SellerGroupExample;

public interface SellerGroupMapper {
    int countByExample(SellerGroupExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SellerGroup record);

    int insertSelective(SellerGroup record);

    List<SellerGroup> selectByExample(SellerGroupExample example);

    SellerGroup selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SellerGroup record, @Param("example") SellerGroupExample example);

    int updateByExample(@Param("record") SellerGroup record, @Param("example") SellerGroupExample example);

    int updateByPrimaryKeySelective(SellerGroup record);

    int updateByPrimaryKey(SellerGroup record);

    @Select("select * from sellergroup where memberid=#{memberid}")
    List<SellerGroup> listsellergroup(SellerGroup record);
}