package project.jinshang.mod_fx;

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
import project.jinshang.mod_fx.bean.Fxmoney;
import project.jinshang.mod_fx.service.FxMoneyService;
import project.jinshang.mod_member.bean.Member;

@RestController
@RequestMapping(value = {"/rest/fxmoney"})
@Api(tags = "佣金余额管理", description = "佣金余额管理接口")
@SessionAttributes(AppConstant.MEMBER_SESSION_NAME)
public class FxMoneyAction {

    @Autowired
    private FxMoneyService fxMoneyService;

    @ApiOperation(value = "我的佣金-当前可用佣金")
    @PostMapping("/get")
    public BasicRet getByMemberId(Model model) {
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        BasicExtRet basicRet = new BasicExtRet();
        Fxmoney fxmoney = fxMoneyService.getByMemberId(member.getId());
        if (null == fxmoney){
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("佣金余额表还不存在该买家信息，需要执行定时任务");
        }else{
            basicRet.setResult(BasicRet.SUCCESS);
            basicRet.setMessage("获取成功");
            basicRet.setData(fxmoney);
        }
        return basicRet;
    }

}
