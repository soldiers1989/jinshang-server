package project.jinshang.mod_feedback;

import com.github.pagehelper.PageInfo;
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
import project.jinshang.common.bean.PageRet;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.mod_feedback.bean.Feedback;
import project.jinshang.mod_feedback.service.FeedbackService;
import project.jinshang.mod_member.bean.Member;

import java.util.Date;

@RestController
@RequestMapping(value = "/rest/admin/feedback")
@SessionAttributes({AppConstant.ADMIN_SESSION_NAME})
@Api(tags = "反馈后台管理", description = "反馈")
@Transactional(rollbackFor = Exception.class)
public class FeedbackAction {

    @Autowired
    FeedbackService feedbackService;

    @Autowired
    FeedbackTypeMapper feedbackTypeMapper;


    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ApiOperation(value = "反馈列表 分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "页数", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "一页大小", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "feedbackTypeId", value = "反馈类型id", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "dispose", value = "是否已处理 0 未处理 1 已处理", required = false, paramType = "query", dataType = "int")
    })
    public PageRet getFeedbackList(int pageNo, int pageSize, Integer feedbackTypeId, Short dispose) {
        PageRet pageRet = new PageRet();
        PageInfo pageInfo = feedbackService.getFeedbackList(pageNo, pageSize, feedbackTypeId, dispose);
        pageRet.data.setPageInfo(pageInfo);
        pageRet.setMessage("成功");
        pageRet.setResult(BasicRet.SUCCESS);
        return pageRet;
    }

    @RequestMapping(value = "/dispose", method = RequestMethod.POST)
    @ApiOperation(value = "处理反馈")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "feedbackId", value = "反馈id", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "disposeContent", value = "处理意见", required = false, paramType = "query", dataType = "string")
    })
    @ApiImplicitParam(name = "disposeContent", value = "处理意见", required = false, paramType = "query", dataType = "string")
    public BasicRet disposeFeedback(int feedbackId, String disposeContent, Model model) {
        BasicRet basicRet = new BasicRet();

        Member member = (Member) model.asMap().get(AppConstant.ADMIN_SESSION_NAME);
        if (member == null) {
            basicRet.setMessage("没有登陆信息");
            basicRet.setResult(BasicRet.ERR);
            return basicRet;
        }
        Feedback feedback = feedbackService.getFeedbackById(feedbackId);
        if (feedback == null) {
            basicRet.setMessage("不存在该反馈");
            basicRet.setResult(BasicRet.ERR);
            return basicRet;
        }
        feedback.setDispose((short) 1);
        feedback.setDisposeContent(disposeContent);
        feedback.setDisposePeopleId(member.getId());
        feedback.setUpdateTime(new Date());

        feedbackService.updateFeedback(feedback);
        basicRet.setMessage("成功");
        basicRet.setResult(BasicRet.SUCCESS);
        return basicRet;
    }
}
