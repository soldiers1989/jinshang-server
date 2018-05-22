package project.jinshang.mod_feedback;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import mizuki.project.core.restserver.config.BasicRet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.mod_common.bean.BasicExtRet;
import project.jinshang.mod_feedback.bean.Feedback;
import project.jinshang.mod_feedback.service.FeedbackService;
import project.jinshang.mod_feedback.service.FeedbackTypeService;
import project.jinshang.mod_member.bean.Member;

import java.util.Date;

@RestController
@RequestMapping(value = "/rest/buyer/feedback")
@SessionAttributes({AppConstant.MEMBER_SESSION_NAME})
@Api(tags = "买家反馈", description = "反馈")
@Transactional(rollbackFor = Exception.class)
public class FeedBackFrontAction {


    @Autowired
    FeedbackService feedbackService;

    @Autowired
    FeedbackTypeService feedbackTypeService;


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiOperation(value = "新增反馈")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "feedbackTypeId", value = "反馈类型id", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "feedbackContent", value = "反馈内容", required = true, paramType = "query", dataType = "stirng"),
            @ApiImplicitParam(name = "phone", value = "联系电话", required = false, paramType = "query", dataType = "stirng")
    })
    public BasicRet addFeedback(int feedbackTypeId, String feedbackContent, String phone, Model model) {
        BasicRet basicRet = new BasicRet();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        Feedback feedback = new Feedback();
        feedback.setFeedbackTypeId(feedbackTypeId);
        feedback.setFeedbackContent(feedbackContent);
        feedback.setCreateTime(new Date());
        feedback.setUpdateTime(new Date());
        feedback.setPhone(phone);
        feedback.setMemberId(member.getId());
        feedbackService.addFeedback(feedback);
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("成功");
        return basicRet;
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ApiOperation(value = "获取用户的反馈列表")
    public BasicExtRet getFeedbackList(int feedbackTypeId, String feedbackContent, String phone, Model model) {
        BasicExtRet basicRet = new BasicExtRet();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        basicRet.setData(feedbackService.getFeedbackListByMemberId(member.getId()));
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("成功");
        return basicRet;
    }

    @RequestMapping(value = "/feedBackType/list", method = RequestMethod.POST)
    @ApiOperation(value = "反馈类型列表")
    public BasicExtRet getFeedbackTypeList() {
        BasicExtRet basicRet = new BasicExtRet();
        basicRet.setData(feedbackTypeService.getFeedbackList());
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("成功");
        return basicRet;
    }


}
