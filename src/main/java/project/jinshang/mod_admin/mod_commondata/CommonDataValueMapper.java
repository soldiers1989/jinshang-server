package project.jinshang.mod_admin.mod_commondata;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import project.jinshang.mod_admin.mod_commondata.bean.CommonDataValue;
import project.jinshang.mod_admin.mod_commondata.bean.CommonDataValueExample;

public interface CommonDataValueMapper {
    int countByExample(CommonDataValueExample example);

    @CacheEvict(value = "jinshang-commonDataValue",allEntries = true)
    int deleteByExample(CommonDataValueExample example);

    @CacheEvict(value = "jinshang-commonDataValue",allEntries = true)
    int deleteByPrimaryKey(Long id);

    @CacheEvict(value = "jinshang-commonDataValue",allEntries = true)
    int insert(CommonDataValue record);

    @CacheEvict(value = "jinshang-commonDataValue",allEntries = true)
    int insertSelective(CommonDataValue record);


    List<CommonDataValue> selectByExample(CommonDataValueExample example);

    CommonDataValue selectByPrimaryKey(Long id);

    @CacheEvict(value = "jinshang-commonDataValue",allEntries = true)
    int updateByExampleSelective(@Param("record") CommonDataValue record, @Param("example") CommonDataValueExample example);

    @CacheEvict(value = "jinshang-commonDataValue",allEntries = true)
    int updateByExample(@Param("record") CommonDataValue record, @Param("example") CommonDataValueExample example);

    @CacheEvict(value = "jinshang-commonDataValue",allEntries = true)
    int updateByPrimaryKeySelective(CommonDataValue record);

    @CacheEvict(value = "jinshang-commonDataValue",allEntries = true)
    int updateByPrimaryKey(CommonDataValue record);


    @Select("select * from commondatavalue  where dataid=#{dataid} and value=#{value} order by id desc limit 1")
    CommonDataValue getByValueAndDataId(@Param("value") String value,@Param("dataid") long dataid);

    @Cacheable(value = "jinshang-commonDataValue",key = "'getcommonDataValue-name:'+#p0")
    @Select("select value from commondata D left join commondatavalue V on D.id=V.dataid where D.name=#{name} order by V.sort asc ")
    List<String> getcommonDataValue(@Param("name") String name);
}