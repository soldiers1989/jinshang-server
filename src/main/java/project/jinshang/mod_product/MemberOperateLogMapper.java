package project.jinshang.mod_product;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import project.jinshang.mod_product.bean.MemberOperateLog;
import project.jinshang.mod_product.bean.MemberOperateLogExample;

public interface MemberOperateLogMapper {
    int countByExample(MemberOperateLogExample example);

    int deleteByExample(MemberOperateLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MemberOperateLog record);

    int insertSelective(MemberOperateLog record);

    List<MemberOperateLog> selectByExample(MemberOperateLogExample example);

    MemberOperateLog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MemberOperateLog record, @Param("example") MemberOperateLogExample example);

    int updateByExample(@Param("record") MemberOperateLog record, @Param("example") MemberOperateLogExample example);

    int updateByPrimaryKeySelective(MemberOperateLog record);

    int updateByPrimaryKey(MemberOperateLog record);
}