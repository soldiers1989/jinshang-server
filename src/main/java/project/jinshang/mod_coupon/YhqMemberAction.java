package project.jinshang.mod_coupon;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import mizuki.project.core.restserver.config.BasicRet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.mod_common.bean.BasicExtRet;
import project.jinshang.mod_coupon.bean.YhqTicket;
import project.jinshang.mod_coupon.service.YhqTicketService;
import project.jinshang.mod_member.bean.Member;

import java.util.List;

@RestController
@RequestMapping(value = {"/rest/buyer/yhqmember"})
@Api(tags = "买家我的优惠券管理", description = "买家我的优惠券接口")
@SessionAttributes(AppConstant.MEMBER_SESSION_NAME)
public class YhqMemberAction {


    @Autowired
    private YhqTicketService yhqTicketService;

    @PostMapping(value = "/notuseticket")
    @ApiOperation(value = "未使用的优惠券")
    public BasicRet getNotUseYhqTicket(Model model) {
        BasicExtRet basicRet = new BasicExtRet();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        List<YhqTicket> list = yhqTicketService.getNotUseYhqTicket(member.getId());
        if (list.size() == 0) {
            basicRet.setResult(BasicRet.SUCCESS);
            basicRet.setMessage("该用户没有未使用的优惠券");
        } else {
            basicRet.setResult(BasicRet.SUCCESS);
            basicRet.setMessage("获取成功");
            basicRet.setData(list);
        }
        return basicRet;
    }


    @PostMapping(value = "/useticket")
    @ApiOperation(value = "使用过优惠券")
    public BasicRet getUseYhqTicket(Model model) {
        BasicExtRet basicRet = new BasicExtRet();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        List<YhqTicket> list = yhqTicketService.getUseYhqTicket(member.getId());
        if (list.size() == 0) {
            basicRet.setResult(BasicRet.SUCCESS);
            basicRet.setMessage("该用户没有使用过优惠券");
        } else {
            basicRet.setResult(BasicRet.SUCCESS);
            basicRet.setMessage("获取成功");
            basicRet.setData(list);
        }
        return basicRet;
    }


    @PostMapping(value = "/expireticket")
    @ApiOperation(value = "已过期优惠券")
    public BasicRet getExpireTicket(Model model) {
        BasicExtRet basicRet = new BasicExtRet();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        List<YhqTicket> list = yhqTicketService.getExpiretYhqTicket(member.getId());
        if (list.size() == 0) {
            basicRet.setResult(BasicRet.SUCCESS);
            basicRet.setMessage("该用户没有已过期优惠券");
        } else {
            basicRet.setResult(BasicRet.SUCCESS);
            basicRet.setMessage("获取成功");
            basicRet.setData(list);
        }
        return basicRet;
    }
}

