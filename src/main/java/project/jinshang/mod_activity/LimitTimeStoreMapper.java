package project.jinshang.mod_activity;

import java.math.BigDecimal;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import project.jinshang.mod_activity.bean.LimitTimeStore;
import project.jinshang.mod_activity.bean.LimitTimeStoreExample;

public interface LimitTimeStoreMapper {
    int countByExample(LimitTimeStoreExample example);

    int deleteByExample(LimitTimeStoreExample example);

    int deleteByPrimaryKey(Long id);

    int insert(LimitTimeStore record);

    int insertSelective(LimitTimeStore record);

    List<LimitTimeStore> selectByExample(LimitTimeStoreExample example);

    LimitTimeStore selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") LimitTimeStore record, @Param("example") LimitTimeStoreExample example);

    int updateByExample(@Param("record") LimitTimeStore record, @Param("example") LimitTimeStoreExample example);

    int updateByPrimaryKeySelective(LimitTimeStore record);

    int updateByPrimaryKey(LimitTimeStore record);


    @Update("update limittimestore set storenum=storenum+#{storenum},salesnum=salesnum+#{salesnum} where id=#{id}")
    int updateLimitStoreNum(@Param("id") long id, @Param("storenum") BigDecimal storenum, @Param("salesnum") BigDecimal salesnum);
}