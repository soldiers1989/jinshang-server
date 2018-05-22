package project.jinshang.mod_member;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import mizuki.project.core.restserver.config.BasicRet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import project.jinshang.common.bean.PageRet;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.common.constant.Quantity;
import project.jinshang.mod_admin.mod_inte.bean.IntegralRecordExample;
import project.jinshang.mod_admin.mod_inte.service.IntegralRecordService;
import project.jinshang.mod_admin.mod_inte.service.IntegralService;
import project.jinshang.mod_member.bean.Member;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value ="/rest/buyer/integral" )
@SessionAttributes({AppConstant.MEMBER_SESSION_NAME})
@Api(tags = "买家积分信息",description = "买家积分信息")
@Transactional(rollbackFor =Exception.class)
public class BuyerIntegralAction {


    @Autowired
    private IntegralService integralService;

    @Autowired
    private IntegralRecordService integralRecordService;

    /**
     * 获取积分记录信息
     * @param pageNo
     * @param pageSize
     * @param type
     * @return
     */
    @RequestMapping(value = "/getIntegerRecordList",method = RequestMethod.POST)
    @ApiOperation(value = "获取积分记录信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo",value = "页码",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "pageSize",value = "页数",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "type",value = "传数组，积分类型0=消费1=注册2=邀请3=后台增加4=后台减少",required = false,paramType = "query",dataType = "Array"),
    })
    public PageRet getIntegerRecordList(Model model,int pageNo, int pageSize, Short[] type){
        PageRet pageRet = new PageRet();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        List<Short> list = null;
        if(type!=null){
            list = Arrays.asList(type);
        }else {
            list = new ArrayList<>();
        }
        PageInfo info = integralService.getIntegralRecord(pageNo,pageSize, list,member.getId(),null,null);
        pageRet.setResult(BasicRet.SUCCESS);
        pageRet.setMessage("返回成功");
        pageRet.data.setPageInfo(info);
        return pageRet;
    }


    @RequestMapping(value = "/getInviteMemberCount",method = RequestMethod.POST)
    @ApiOperation(value = "获取邀请注册会员的个数")
    public InviteMemberCountRet getInviteMemberCount(Model model){
        InviteMemberCountRet inviteMemberCountRet = new InviteMemberCountRet();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

        IntegralRecordExample example = new IntegralRecordExample();
        IntegralRecordExample.Criteria criteria = example.createCriteria();
        criteria.andMemberidEqualTo(member.getId()).andTypeEqualTo(Quantity.STATE_2);

        int count = integralRecordService.countByExample(example);
        inviteMemberCountRet.data.inviteMemberCount = count;
        inviteMemberCountRet.setResult(BasicRet.SUCCESS);
        return  inviteMemberCountRet;
    }


    private  class  InviteMemberCountRet extends  BasicRet{
        private  class InviteMemberCountData{
            private  int inviteMemberCount;

            public int getInviteMemberCount() {
                return inviteMemberCount;
            }

            public void setInviteMemberCount(int inviteMemberCount) {
                this.inviteMemberCount = inviteMemberCount;
            }
        }

        private InviteMemberCountData data = new InviteMemberCountData();

        public InviteMemberCountData getData() {
            return data;
        }

        public void setData(InviteMemberCountData data) {
            this.data = data;
        }
    }

}
