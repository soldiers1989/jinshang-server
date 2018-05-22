package project.jinshang.mod_admin.mod_top;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import project.jinshang.mod_admin.mod_top.bean.ActivityType;
import project.jinshang.mod_admin.mod_top.bean.ActivityTypeExample;

public interface ActivityTypeMapper {
    int countByExample(ActivityTypeExample example);

    int deleteByExample(ActivityTypeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ActivityType record);

    int insertSelective(ActivityType record);

    List<ActivityType> selectByExample(ActivityTypeExample example);

    ActivityType selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ActivityType record, @Param("example") ActivityTypeExample example);

    int updateByExample(@Param("record") ActivityType record, @Param("example") ActivityTypeExample example);

    int updateByPrimaryKeySelective(ActivityType record);

    int updateByPrimaryKey(ActivityType record);
}