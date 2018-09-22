package project.jinshang.mod_product;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import project.jinshang.mod_product.bean.AdminOperateLog;
import project.jinshang.mod_product.bean.AdminOperateLogExample;

public interface AdminOperateLogMapper {
    int countByExample(AdminOperateLogExample example);

    int deleteByExample(AdminOperateLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AdminOperateLog record);

    int insertSelective(AdminOperateLog record);

    List<AdminOperateLog> selectByExample(AdminOperateLogExample example);

    AdminOperateLog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AdminOperateLog record, @Param("example") AdminOperateLogExample example);

    int updateByExample(@Param("record") AdminOperateLog record, @Param("example") AdminOperateLogExample example);

    int updateByPrimaryKeySelective(AdminOperateLog record);

    int updateByPrimaryKey(AdminOperateLog record);
}