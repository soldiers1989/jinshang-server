package project.jinshang.mod_fx;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import project.jinshang.mod_fx.bean.Fxmoneylist;
import project.jinshang.mod_fx.bean.FxmoneylistExample;

public interface FxmoneylistMapper {
    int countByExample(FxmoneylistExample example);

    int deleteByExample(FxmoneylistExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Fxmoneylist record);

    int insertSelective(Fxmoneylist record);

    List<Fxmoneylist> selectByExample(FxmoneylistExample example);

    Fxmoneylist selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Fxmoneylist record, @Param("example") FxmoneylistExample example);

    int updateByExample(@Param("record") Fxmoneylist record, @Param("example") FxmoneylistExample example);

    int updateByPrimaryKeySelective(Fxmoneylist record);

    int updateByPrimaryKey(Fxmoneylist record);

    @Select("<script>select f.*,m.realname as realname,m.username as  username " +
            "from fx_moneyList f " +
            "left join member m on m.id = f.memberid " +
            "<where> 1=1 " +
            "<if test=\"memberid != null  \">and f.memberid = #{memberid} </if>" +
            "</where> order by f.id desc" +
            "</script>")
    List<Map<String,Object>> findCommisionList(@Param("memberid") long memberid);
}