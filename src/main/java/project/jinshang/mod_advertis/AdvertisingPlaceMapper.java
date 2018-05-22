package project.jinshang.mod_advertis;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import project.jinshang.mod_advertis.bean.AdvertisingPlace;
import project.jinshang.mod_advertis.bean.AdvertisingPlaceExample;

public interface AdvertisingPlaceMapper {

    int countByExample(AdvertisingPlaceExample example);


    int deleteByExample(AdvertisingPlaceExample example);


    int deleteByPrimaryKey(Long id);


    int insert(AdvertisingPlace record);


    int insertSelective(AdvertisingPlace record);


    List<AdvertisingPlace> selectByExample(AdvertisingPlaceExample example);


    AdvertisingPlace selectByPrimaryKey(Long id);


    int updateByExampleSelective(@Param("record") AdvertisingPlace record, @Param("example") AdvertisingPlaceExample example);


    int updateByExample(@Param("record") AdvertisingPlace record, @Param("example") AdvertisingPlaceExample example);


    int updateByPrimaryKeySelective(AdvertisingPlace record);


    int updateByPrimaryKey(AdvertisingPlace record);
}