package project.jinshang.mod_admin.mod_inte;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import project.jinshang.mod_admin.mod_inte.bean.IntegralSet;
import project.jinshang.mod_admin.mod_inte.bean.IntegralSetExample;

public interface IntegralSetMapper {
    int countByExample(IntegralSetExample example);

    int deleteByExample(IntegralSetExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(IntegralSet record);

    int insertSelective(IntegralSet record);

    List<IntegralSet> selectByExample(IntegralSetExample example);

    IntegralSet selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") IntegralSet record, @Param("example") IntegralSetExample example);

    int updateByExample(@Param("record") IntegralSet record, @Param("example") IntegralSetExample example);

    int updateByPrimaryKeySelective(IntegralSet record);

    int updateByPrimaryKey(IntegralSet record);
}