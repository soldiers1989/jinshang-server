package project.jinshang.mod_feedback;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import project.jinshang.mod_feedback.bean.FeedbackType;
import project.jinshang.mod_feedback.bean.FeedbackTypeExample;

public interface FeedbackTypeMapper {
    int countByExample(FeedbackTypeExample example);

    int deleteByExample(FeedbackTypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FeedbackType record);

    int insertSelective(FeedbackType record);

    List<FeedbackType> selectByExample(FeedbackTypeExample example);

    FeedbackType selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FeedbackType record, @Param("example") FeedbackTypeExample example);

    int updateByExample(@Param("record") FeedbackType record, @Param("example") FeedbackTypeExample example);

    int updateByPrimaryKeySelective(FeedbackType record);

    int updateByPrimaryKey(FeedbackType record);
}