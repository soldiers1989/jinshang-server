package project.jinshang.mod_admin.mod_top;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import project.jinshang.mod_admin.mod_top.bean.TopActivityProduct;
import project.jinshang.mod_admin.mod_top.bean.TopActivityProductExample;

public interface TopActivityProductMapper {
    int countByExample(TopActivityProductExample example);

    int deleteByExample(TopActivityProductExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TopActivityProduct record);

    int insertSelective(TopActivityProduct record);

    List<TopActivityProduct> selectByExample(TopActivityProductExample example);

    TopActivityProduct selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TopActivityProduct record, @Param("example") TopActivityProductExample example);

    int updateByExample(@Param("record") TopActivityProduct record, @Param("example") TopActivityProductExample example);

    int updateByPrimaryKeySelective(TopActivityProduct record);

    int updateByPrimaryKey(TopActivityProduct record);
}