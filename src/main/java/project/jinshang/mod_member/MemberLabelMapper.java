package project.jinshang.mod_member;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import project.jinshang.mod_member.bean.MemberLabel;
import project.jinshang.mod_member.bean.MemberLabelExample;
import project.jinshang.mod_member.bean.dto.MemberLabelQueryDto;
import project.jinshang.mod_member.bean.dto.MemberLabelViewDto;
import project.jinshang.mod_member.provider.MemberLabelProvider;

public interface MemberLabelMapper {
    int countByExample(MemberLabelExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MemberLabel record);

    int insertSelective(MemberLabel record);

    List<MemberLabel> selectByExample(MemberLabelExample example);

    MemberLabel selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MemberLabel record, @Param("example") MemberLabelExample example);

    int updateByExample(@Param("record") MemberLabel record, @Param("example") MemberLabelExample example);

    int updateByPrimaryKeySelective(MemberLabel record);

    int updateByPrimaryKey(MemberLabel record);

    @Select("select * from memberlabel where labelname=#{name} order by id desc limit 1")
    MemberLabel getByLabelName(@Param("name") String name);

    @SelectProvider(type = MemberLabelProvider.class,method = "getLabelList")
    List<MemberLabelViewDto> getLabelList(@Param("queryDto") MemberLabelQueryDto queryDto);

    @SelectProvider(type = MemberLabelProvider.class,method = "getMemberLabelCount")
    int getMemberLabelCount(@Param("id") long id);
}