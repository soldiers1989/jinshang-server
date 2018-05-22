package project.jinshang.mod_feedback.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.mod_feedback.FeedbackMapper;
import project.jinshang.mod_feedback.FeedbackTypeMapper;
import project.jinshang.mod_feedback.bean.FeedbackExample;
import project.jinshang.mod_feedback.bean.FeedbackType;

import java.util.Date;
import java.util.List;

@Service
public class FeedbackTypeService {

    @Autowired
    FeedbackTypeMapper feedbackTypeMapper;

    @Autowired
    FeedbackMapper feedbackMapper;


    public void addFeedbackType(String feedbackTypeName) {
        FeedbackType feedbackType = new FeedbackType();
        feedbackType.setTypeName(feedbackTypeName);
        feedbackType.setCreateTime(new Date());
        feedbackType.setUpdateTime(new Date());
        feedbackTypeMapper.insertSelective(feedbackType);
    }

    public FeedbackType getFeedbackTypeById(int id) {
        return feedbackTypeMapper.selectByPrimaryKey(id);
    }

    public void editFeedbackType(FeedbackType feedbackType) {
        feedbackTypeMapper.updateByPrimaryKeySelective(feedbackType);
    }

    public int getFeedbackListSizeByFeedbackTypeId(int feedbackId) {
        FeedbackExample example = new FeedbackExample();
        FeedbackExample.Criteria criteria = example.createCriteria();
        criteria.andFeedbackTypeIdEqualTo(feedbackId);
        return feedbackMapper.countByExample(example);
    }

    public void deleteFeedbackType(int id){
        feedbackTypeMapper.deleteByPrimaryKey(id);
    }

    public List<FeedbackType> getFeedbackList(){
        return feedbackTypeMapper.selectByExample(null);
    }
}
