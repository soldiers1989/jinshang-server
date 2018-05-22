package project.jinshang.mod_admin.mod_display;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import project.jinshang.mod_admin.mod_display.bean.Display;
import project.jinshang.mod_admin.mod_display.bean.DisplayExample;

public interface DisplayMapper {
    int countByExample(DisplayExample example);

    int deleteByPrimaryKey(long id);

    int insert(Display record);

    int insertSelective(Display record);

    List<Display> selectByExample(DisplayExample example);

    Display selectByPrimaryKey(long id);

    int updateByExampleSelective(@Param("record") Display record, @Param("example") DisplayExample example);

    int updateByExample(@Param("record") Display record, @Param("example") DisplayExample example);

    int updateByPrimaryKeySelective(Display record);

    int updateByPrimaryKey(Display record);
}