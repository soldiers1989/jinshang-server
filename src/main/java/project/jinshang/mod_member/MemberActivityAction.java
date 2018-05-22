package project.jinshang.mod_member;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import mizuki.project.core.restserver.config.BasicRet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import project.jinshang.mod_member.bean.Member;
import project.jinshang.mod_member.bean.MemberActivity;
import project.jinshang.mod_member.service.MemberActivityService;

import java.util.Date;

/**
 * Created by Administrator on 2018/5/10.
 */

@RestController
@RequestMapping("/rest/memberactivity")
@Api(tags = "会员活动",description = "会员参加活动")
public class MemberActivityAction {

    @Autowired
    private MemberActivityService mas;

    @RequestMapping(value = "/joinActivity", method = RequestMethod.POST)
    @ApiOperation(value = "参加活动")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mobile", value = "手机号码", required = true, paramType = "query", dataType = "string")
    })
    public BasicRet joinActivity(String mobile){
        BasicRet basicRet = new BasicRet();
        Member member = mas.getMemberByMobile(mobile);
        if(member==null){
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("该号码未注册，请先注册账号");
        }else {
            MemberActivity oldMemberActivity = mas.getMemberActivityByMobile(mobile);
            if(oldMemberActivity!=null) {

                basicRet.setResult(BasicRet.ERR);
                basicRet.setMessage("该号码已参与过此活动，不能重复参与");
            }else{
                member.setBuyerCompanyInfo(mas.getMemberCompanyByMemberid(member.getId()));
                MemberActivity memberActivity = new MemberActivity();
                memberActivity.setMobile(member.getMobile());
                memberActivity.setJointime(new Date());
                memberActivity.setMemberid(member.getId());
                memberActivity.setUsername(member.getUsername());
                if(member.getBuyerCompanyInfo()!=null) {
                    memberActivity.setCompanyname(member.getBuyerCompanyInfo().getCompanyname());
                }
            int result = mas.addmemberActivity(memberActivity);
            if (result > 0) {
                basicRet.setResult(BasicRet.SUCCESS);
                basicRet.setMessage("活动参与成功");
            } else {
                basicRet.setResult(BasicRet.ERR);
                basicRet.setMessage("活动参与失败");
            }
        }
        }
        return basicRet;
    }
}
