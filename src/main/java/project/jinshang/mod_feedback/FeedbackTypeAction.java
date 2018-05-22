package project.jinshang.mod_feedback;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import mizuki.project.core.restserver.config.BasicRet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.mod_common.bean.BasicExtRet;
import project.jinshang.mod_feedback.bean.FeedbackType;
import project.jinshang.mod_feedback.service.FeedbackTypeService;

import java.util.Date;

@RestController
@RequestMapping(value = "/rest/admin/feedbackType")
@SessionAttributes({AppConstant.ADMIN_SESSION_NAME})
@Api(tags = "反馈类型后台管理", description = "反馈类型")
@Transactional(rollbackFor = Exception.class)
public class FeedbackTypeAction {

    @Autowired
    FeedbackTypeService feedbackTypeService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiOperation(value = "新增反馈类型")
    @ApiImplicitParam(name = "feedbackName", value = "反馈标签名", required = true, paramType = "query", dataType = "string")
    public BasicRet addFeedbackType(String feedbackName) {
        BasicRet basicRet = new BasicRet();
        if (feedbackName.length() > 20) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("字数不能超过20个");
            return basicRet;
        }
        feedbackTypeService.addFeedbackType(feedbackName);
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("成功");
        return basicRet;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ApiOperation(value = "修改反馈类型")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "反馈类型id", required = true, paramType = "query", dataType = "id"),
            @ApiImplicitParam(name = "feedbackName", value = "反馈标签名", required = true, paramType = "query", dataType = "string")
    })
    public BasicRet editFeedbackType(int id, String feedbackName) {
        BasicRet basicRet = new BasicRet();
        if (feedbackName.length() > 20) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("字数不能超过20个");
            return basicRet;
        }
        FeedbackType feedbackType = feedbackTypeService.getFeedbackTypeById(id);
        if (feedbackType == null) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("不存在该类型");
            return basicRet;
        }
        feedbackType.setTypeName(feedbackName);
        feedbackType.setUpdateTime(new Date());
        feedbackTypeService.editFeedbackType(feedbackType);
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("成功");
        return basicRet;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ApiOperation(value = "删除反馈类型")
    @ApiImplicitParam(name = "id", value = "反馈类型id", required = true, paramType = "query", dataType = "int")
    public BasicRet deleteFeedbackType(int id) {
        BasicRet basicRet = new BasicRet();
        FeedbackType feedbackType = feedbackTypeService.getFeedbackTypeById(id);
        if (feedbackType == null) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("不存在该类型");
            return basicRet;
        }
        if (feedbackTypeService.getFeedbackListSizeByFeedbackTypeId(id) != 0) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("该类型下存在反馈不能删除");
            return basicRet;
        }
        feedbackTypeService.deleteFeedbackType(id);
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("成功");
        return basicRet;
    }

    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    @ApiOperation(value = "反馈类型详情")
    @ApiImplicitParam(name = "id", value = "反馈类型id", required = true, paramType = "query", dataType = "int")
    public BasicExtRet getFeedbackType(int id) {
        BasicExtRet basicRet = new BasicExtRet();
        FeedbackType feedbackType = feedbackTypeService.getFeedbackTypeById(id);
        if (feedbackType == null) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("不存在该类型");
            return basicRet;
        }
        basicRet.setData(feedbackType);
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("成功");
        return basicRet;
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ApiOperation(value = "反馈类型列表")
    public BasicExtRet getFeedbackTypeList() {
        BasicExtRet basicRet = new BasicExtRet();
        basicRet.setData(feedbackTypeService.getFeedbackList());
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("成功");
        return basicRet;
    }
}
