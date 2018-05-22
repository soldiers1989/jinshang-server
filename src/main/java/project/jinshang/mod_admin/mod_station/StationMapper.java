package project.jinshang.mod_admin.mod_station;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import project.jinshang.mod_admin.mod_station.bean.Station;
import project.jinshang.mod_admin.mod_station.bean.StationExample;

public interface StationMapper {
    int countByExample(StationExample example);

    int deleteByPrimaryKey(long id);

    int insert(Station record);

    int insertSelective(Station record);

    List<Station> selectByExample(StationExample example);

    Station selectByPrimaryKey(long id);

    int updateByExampleSelective(@Param("record") Station record, @Param("example") StationExample example);

    int updateByExample(@Param("record") Station record, @Param("example") StationExample example);

    int updateByPrimaryKeySelective(Station record);

    int updateByPrimaryKey(Station record);


    @Select("select * from station order by id desc limit 1")
    Station get();

    @Update("update station" +
            "    set stname = #{station.stname,jdbcType=VARCHAR}," +
            "      sttitle = #{station.sttitle,jdbcType=VARCHAR}," +
            "      stkeyword = #{station.stkeyword,jdbcType=VARCHAR}," +
            "      stdescribe = #{station.stdescribe,jdbcType=VARCHAR}," +
            "      stlogo = #{station.stlogo,jdbcType=VARCHAR}," +
            "      aftertime = #{station.aftertime,jdbcType=INTEGER}")
    int update(@Param("station") Station station);
}