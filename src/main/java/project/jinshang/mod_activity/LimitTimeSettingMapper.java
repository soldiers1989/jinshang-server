package project.jinshang.mod_activity;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import project.jinshang.mod_activity.bean.LimitTimeSetting;
import project.jinshang.mod_activity.bean.LimitTimeSettingExample;

public interface LimitTimeSettingMapper {
    int countByExample(LimitTimeSettingExample example);

    int deleteByExample(LimitTimeSettingExample example);

    int insert(LimitTimeSetting record);

    int insertSelective(LimitTimeSetting record);

    List<LimitTimeSetting> selectByExample(LimitTimeSettingExample example);

    int updateByExampleSelective(@Param("record") LimitTimeSetting record, @Param("example") LimitTimeSettingExample example);

    int updateByExample(@Param("record") LimitTimeSetting record, @Param("example") LimitTimeSettingExample example);

    @Update("update limittimesetting set preheattime=#{record.preheattime},canbuy=#{record.preheattime}")
    int update(@Param("record") LimitTimeSetting setting);
}