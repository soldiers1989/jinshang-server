package project.jinshang.mod_member.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import project.jinshang.mod_member.bean.ApiRecord;
import project.jinshang.mod_member.bean.ApiRecordExample;

public interface ApiRecordMapper {
    int countByExample(ApiRecordExample example);

    int deleteByExample(ApiRecordExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ApiRecord record);

    int insertSelective(ApiRecord record);

    List<ApiRecord> selectByExample(ApiRecordExample example);

    ApiRecord selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ApiRecord record, @Param("example") ApiRecordExample example);

    int updateByExample(@Param("record") ApiRecord record, @Param("example") ApiRecordExample example);

    int updateByPrimaryKeySelective(ApiRecord record);

    int updateByPrimaryKey(ApiRecord record);
}