package project.jinshang.mod_fx;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import project.jinshang.mod_fx.bean.Fxcirculation;
import project.jinshang.mod_fx.bean.FxcirculationExample;


public interface FxcirculationMapper {
    int countByExample(FxcirculationExample example);

    int deleteByExample(FxcirculationExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Fxcirculation record);

    int insertSelective(Fxcirculation record);

    List<Fxcirculation> selectByExample(FxcirculationExample example);

    Fxcirculation selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Fxcirculation record, @Param("example") FxcirculationExample example);

    int updateByExample(@Param("record") Fxcirculation record, @Param("example") FxcirculationExample example);

    int updateByPrimaryKeySelective(Fxcirculation record);

    int updateByPrimaryKey(Fxcirculation record);
}