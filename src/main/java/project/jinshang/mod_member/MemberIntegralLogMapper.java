package project.jinshang.mod_member;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import project.jinshang.mod_member.bean.MemberIntegralLog;
import project.jinshang.mod_member.bean.MemberIntegralLogExample;

public interface MemberIntegralLogMapper {
    int countByExample(MemberIntegralLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MemberIntegralLog record);

    int insertSelective(MemberIntegralLog record);

    List<MemberIntegralLog> selectByExample(MemberIntegralLogExample example);

    MemberIntegralLog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MemberIntegralLog record, @Param("example") MemberIntegralLogExample example);

    int updateByExample(@Param("record") MemberIntegralLog record, @Param("example") MemberIntegralLogExample example);

    int updateByPrimaryKeySelective(MemberIntegralLog record);

    int updateByPrimaryKey(MemberIntegralLog record);

    @Select("select * from memberintegrallog where memberid=#{memberid}")
   List<MemberIntegralLog> selectByPrimarymemberId(MemberIntegralLog memberIntegralLog);
}