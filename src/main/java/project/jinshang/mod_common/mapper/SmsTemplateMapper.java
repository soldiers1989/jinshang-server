package project.jinshang.mod_common.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import project.jinshang.mod_common.bean.SmsTemplate;
import project.jinshang.mod_common.bean.SmsTemplateExample;

public interface SmsTemplateMapper {
    int countByExample(SmsTemplateExample example);

    int deleteByExample(SmsTemplateExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SmsTemplate record);

    int insertSelective(SmsTemplate record);

    List<SmsTemplate> selectByExample(SmsTemplateExample example);

    SmsTemplate selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SmsTemplate record, @Param("example") SmsTemplateExample example);

    int updateByExample(@Param("record") SmsTemplate record, @Param("example") SmsTemplateExample example);

    int updateByPrimaryKeySelective(SmsTemplate record);

    int updateByPrimaryKey(SmsTemplate record);

    @Select("select * from smstemplate where sendcode=#{sendcode} limit 1")
    SmsTemplate getBySendCode(@Param("sendcode") String sendcode);
}