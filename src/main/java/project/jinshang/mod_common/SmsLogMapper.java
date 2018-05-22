package project.jinshang.mod_common;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import project.jinshang.mod_common.bean.SmsLog;
import project.jinshang.mod_common.bean.SmsLogExample;

public interface SmsLogMapper {
    int countByExample(SmsLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SmsLog record);

    int insertSelective(SmsLog record);

    List<SmsLog> selectByExample(SmsLogExample example);

    SmsLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SmsLog record, @Param("example") SmsLogExample example);

    int updateByExample(@Param("record") SmsLog record, @Param("example") SmsLogExample example);

    int updateByPrimaryKeySelective(SmsLog record);

    int updateByPrimaryKey(SmsLog record);

    @Select("select * from smslog where mobile=#{mobile} and type=#{type} order by id desc limit 1")
    SmsLog getLastLog(@Param("mobile") String mobile,@Param("type") String type);
}