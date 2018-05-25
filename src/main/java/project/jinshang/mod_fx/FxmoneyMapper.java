package project.jinshang.mod_fx;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import project.jinshang.mod_fx.bean.Fxmoney;
import project.jinshang.mod_fx.bean.FxmoneyExample;

public interface FxmoneyMapper {
    int countByExample(FxmoneyExample example);

    int deleteByExample(FxmoneyExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Fxmoney record);

    int insertSelective(Fxmoney record);

    List<Fxmoney> selectByExample(FxmoneyExample example);

    Fxmoney selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Fxmoney record, @Param("example") FxmoneyExample example);

    int updateByExample(@Param("record") Fxmoney record, @Param("example") FxmoneyExample example);

    int updateByPrimaryKeySelective(Fxmoney record);

    int updateByPrimaryKey(Fxmoney record);
    @Select("select * from fx_money where memberid = #{cmemberid}")
    Fxmoney getByCMemberId(@Param("cmemberid") Long cmemberid);

}