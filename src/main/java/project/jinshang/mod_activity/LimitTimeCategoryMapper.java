package project.jinshang.mod_activity;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import project.jinshang.mod_activity.bean.LimitTimeCategory;
import project.jinshang.mod_activity.bean.LimitTimeCategoryExample;

public interface LimitTimeCategoryMapper {
    int countByExample(LimitTimeCategoryExample example);

    int deleteByExample(LimitTimeCategoryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(LimitTimeCategory record);

    int insertSelective(LimitTimeCategory record);

    List<LimitTimeCategory> selectByExample(LimitTimeCategoryExample example);

    LimitTimeCategory selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") LimitTimeCategory record, @Param("example") LimitTimeCategoryExample example);

    int updateByExample(@Param("record") LimitTimeCategory record, @Param("example") LimitTimeCategoryExample example);

    int updateByPrimaryKeySelective(LimitTimeCategory record);

    int updateByPrimaryKey(LimitTimeCategory record);

    @Select("select * from limittimecategory where name=#{name}")
    LimitTimeCategory getByName(@Param("name") String name);

    @Select("select * from limittimecategory where type=#{type}")
    List<LimitTimeCategory> getByType(@Param("type") int type);

    @Update("update limittimecategory set type = #{type} where id = #{id}")
    int updateTypeById(@Param("id") int id,@Param("type") int type);

    @Select("select distinct category as name,categoryid as id from limittimeprod where category in(select name from limittimecategory where type=0) and (state =1 or state=4)")
    List<LimitTimeCategory> selectActivity();
}