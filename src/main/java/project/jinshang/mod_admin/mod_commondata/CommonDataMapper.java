package project.jinshang.mod_admin.mod_commondata;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.cache.annotation.CacheEvict;
import project.jinshang.mod_admin.mod_commondata.bean.CommonData;
import project.jinshang.mod_admin.mod_commondata.bean.CommonDataExample;

public interface CommonDataMapper {
    int countByExample(CommonDataExample example);

    @CacheEvict(value = "jinshang-commonDataValue",allEntries = true)
    int deleteByExample(CommonDataExample example);

    @CacheEvict(value = "jinshang-commonDataValue",allEntries = true)
    int deleteByPrimaryKey(Long id);

    @CacheEvict(value = "jinshang-commonDataValue",allEntries = true)
    int insert(CommonData record);

    @CacheEvict(value = "jinshang-commonDataValue",allEntries = true)
    int insertSelective(CommonData record);

    List<CommonData> selectByExample(CommonDataExample example);

    CommonData selectByPrimaryKey(Long id);

    @CacheEvict(value = "jinshang-commonDataValue",allEntries = true)
    int updateByExampleSelective(@Param("record") CommonData record, @Param("example") CommonDataExample example);

    @CacheEvict(value = "jinshang-commonDataValue",allEntries = true)
    int updateByExample(@Param("record") CommonData record, @Param("example") CommonDataExample example);

    @CacheEvict(value = "jinshang-commonDataValue",allEntries = true)
    int updateByPrimaryKeySelective(CommonData record);

    @CacheEvict(value = "jinshang-commonDataValue",allEntries = true)
    int updateByPrimaryKey(CommonData record);


    @Select("select * from commondata where name=#{name} order by id desc limit 1")
    CommonData getByName(@Param("name") String name);
}