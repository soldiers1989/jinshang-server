package project.jinshang.mod_member;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import project.jinshang.mod_member.bean.MemberRateSetting;
import project.jinshang.mod_member.bean.MemberRateSettingExample;

public interface MemberRateSettingMapper {
    int countByExample(MemberRateSettingExample example);

    int deleteByExample(MemberRateSettingExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MemberRateSetting record);

    int insertSelective(MemberRateSetting record);

    List<MemberRateSetting> selectByExample(MemberRateSettingExample example);

    MemberRateSetting selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MemberRateSetting record, @Param("example") MemberRateSettingExample example);

    int updateByExample(@Param("record") MemberRateSetting record, @Param("example") MemberRateSettingExample example);

    int updateByPrimaryKeySelective(MemberRateSetting record);

    int updateByPrimaryKey(MemberRateSetting record);


    @Select("select * from memberratesetting where memberid=#{memberid} and membergradeid=#{gradeid} and levelid=#{levelid} order by id desc limit 1")
    MemberRateSetting getByMemberid_levelid_gradeid(@Param("memberid") long memberid,@Param("levelid") long levelid, @Param("gradeid") long gradeid);
}