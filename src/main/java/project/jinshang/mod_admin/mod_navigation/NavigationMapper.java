package project.jinshang.mod_admin.mod_navigation;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import project.jinshang.mod_admin.mod_navigation.bean.Navigation;
import project.jinshang.mod_admin.mod_navigation.bean.NavigationExample;

public interface NavigationMapper {
    int countByExample(NavigationExample example);

    int deleteByPrimaryKey(long id);

    int insert(Navigation record);

    int insertSelective(Navigation record);

    List<Navigation> selectByExample(NavigationExample example);

    Navigation selectByPrimaryKey(long id);

    int updateByExampleSelective(@Param("record") Navigation record, @Param("example") NavigationExample example);

    int updateByExample(@Param("record") Navigation record, @Param("example") NavigationExample example);

    int updateByPrimaryKeySelective(Navigation record);

    int updateByPrimaryKey(Navigation record);

    @Select("select max(nasort) from navigation")
    int selectMaxSort();
}