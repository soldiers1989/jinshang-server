package project.jinshang.mod_member;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import project.jinshang.mod_member.bean.AdminGroup;
import project.jinshang.mod_member.bean.AdminGroupExample;

public interface AdminGroupMapper {
    int countByExample(AdminGroupExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AdminGroup record);

    int insertSelective(AdminGroup record);

    List<AdminGroup> selectByExample(AdminGroupExample example);

    AdminGroup selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AdminGroup record, @Param("example") AdminGroupExample example);

    int updateByExample(@Param("record") AdminGroup record, @Param("example") AdminGroupExample example);

    int updateByPrimaryKeySelective(AdminGroup record);

    int updateByPrimaryKey(AdminGroup record);
}