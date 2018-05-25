package project.jinshang.mod_fx;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import project.jinshang.mod_fx.bean.Fxstation;
import project.jinshang.mod_fx.bean.FxstationExample;


public interface FxstationMapper {
    int countByExample(FxstationExample example);

    int deleteByExample(FxstationExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Fxstation record);

    int insertSelective(Fxstation record);

    List<Fxstation> selectByExample(FxstationExample example);

    Fxstation selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Fxstation record, @Param("example") FxstationExample example);

    int updateByExample(@Param("record") Fxstation record, @Param("example") FxstationExample example);

    int updateByPrimaryKeySelective(Fxstation record);

    int updateByPrimaryKey(Fxstation record);
    @Select("select * from fx_station ")
    Fxstation getStationInfo();
}