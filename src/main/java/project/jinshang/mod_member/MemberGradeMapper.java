package project.jinshang.mod_member;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import project.jinshang.mod_member.bean.MemberGrade;
import project.jinshang.mod_member.bean.MemberGradeExample;

public interface MemberGradeMapper {
    int countByExample(MemberGradeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MemberGrade record);

    int insertSelective(MemberGrade record);

    List<MemberGrade> selectByExample(MemberGradeExample example);

    MemberGrade selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MemberGrade record, @Param("example") MemberGradeExample example);

    int updateByExample(@Param("record") MemberGrade record, @Param("example") MemberGradeExample example);

    int updateByPrimaryKeySelective(MemberGrade record);

    int updateByPrimaryKey(MemberGrade record);


}