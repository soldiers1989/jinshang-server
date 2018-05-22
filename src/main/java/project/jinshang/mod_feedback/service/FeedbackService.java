package project.jinshang.mod_feedback.service;

import com.alibaba.druid.sql.PagerUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.jinshang.mod_feedback.FeedbackMapper;
import project.jinshang.mod_feedback.bean.Feedback;
import project.jinshang.mod_feedback.bean.FeedbackExample;

import java.util.List;

@Service
public class FeedbackService {

    @Autowired
    FeedbackMapper feedbackMapper;

    public PageInfo getFeedbackList(int pageNo, int pageSize, Integer feedbackTypeId, Short dispose) {
        PageHelper.startPage(pageNo, pageSize);
        FeedbackExample example = new FeedbackExample();
        FeedbackExample.Criteria criteria = example.createCriteria();
        if (feedbackTypeId != null) {
            criteria.andFeedbackTypeIdEqualTo(feedbackTypeId);
        }
        if (dispose != null) {
            criteria.andDisposeEqualTo(dispose);
        }
        PageInfo pageInfo = new PageInfo(feedbackMapper.selectByExample(example));
        return pageInfo;
    }

    public Feedback getFeedbackById(int feedbackId) {
        return feedbackMapper.selectByPrimaryKey(feedbackId);
    }

    public void updateFeedback(Feedback feedback) {
        feedbackMapper.updateByPrimaryKeySelective(feedback);
    }

    public void addFeedback(Feedback feedback) {
        feedbackMapper.insertSelective(feedback);
    }

    public List<Feedback> getFeedbackListByMemberId(Long memberId) {
        FeedbackExample example = new FeedbackExample();
        FeedbackExample.Criteria criteria = example.createCriteria();
        criteria.andMemberIdEqualTo(memberId);
        return feedbackMapper.selectByExample(example);
    }
}
