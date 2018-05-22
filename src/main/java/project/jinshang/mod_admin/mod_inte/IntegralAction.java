package project.jinshang.mod_admin.mod_inte;

import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import mizuki.project.core.restserver.config.BasicRet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import project.jinshang.common.bean.MemberLogOperator;
import project.jinshang.common.bean.PageRet;
import project.jinshang.common.constant.AdminAuthorityConst;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.common.constant.Quantity;
import project.jinshang.common.utils.StringUtils;
import project.jinshang.mod_admin.mod_inte.bean.IntegralQueryParam;
import project.jinshang.mod_admin.mod_inte.bean.IntegralRecord;
import project.jinshang.mod_admin.mod_inte.bean.IntegralSet;
import project.jinshang.mod_admin.mod_inte.service.IntegralService;
import project.jinshang.mod_member.bean.Admin;
import project.jinshang.mod_member.bean.Member;
import project.jinshang.mod_member.service.MemberService;
import project.jinshang.mod_product.service.MemberOperateLogService;
import project.jinshang.mod_product.service.OrdersService;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value ="/rest/admin/integralSet" )
@SessionAttributes({AppConstant.ADMIN_SESSION_NAME})
@Api(tags = "后台积分设置",description = "后台积分设置")
@Transactional(rollbackFor =Exception.class)
public class IntegralAction {

    @Autowired
    private MemberOperateLogService memberOperateLogService;

    @Autowired
    private MemberService memberService;

    MemberLogOperator memberLogOperator = new MemberLogOperator();

    private  class  IntegralRet extends BasicRet {
        private  class  Data{

            private List<IntegralSet> list;

            public List<IntegralSet> getList() {
                return list;
            }

            public void setList(List<IntegralSet> list) {
                this.list = list;
            }
        }
        private IntegralAction.IntegralRet.Data data = new IntegralAction.IntegralRet.Data();

        public Data getData() {
            return data;
        }

        public void setData(Data data) {
            this.data = data;
        }
    }


    @Autowired
    private IntegralService integralService;

    @Autowired
    private OrdersService ordersService;

    /**
     * 获取所有积分规则
     * @return
     */
    @RequestMapping(value = "/getAllIntegralSet",method = RequestMethod.POST)
    @ApiOperation(value = "获了所有积分规则,类型0=紧固件1=其它2=邀请会员3=新会员注册4=积分兑换")
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.INTEGRALRULES + "')")
    public IntegralRet getAllIntegralSet(){
        IntegralRet integralRet = new IntegralRet();
        integralRet.data.list = integralService.getAllIntegralSet();
        integralRet.setMessage("返回成功");
        integralRet.setResult(BasicRet.SUCCESS);
        return integralRet;
    }

    /**
     * 修改积分规则
     * @return
     */
    @RequestMapping(value = "/updateIntegralSet",method = RequestMethod.POST)
    @ApiOperation(value = "修改积分规则")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsonstr",value = "[{\"id\":1,\"scope\":5},{\"id\":2,\"scope\":6}]",required = true,paramType = "query",dataType = "string"),
    })
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.INTEGRALRULES + "')")
    public IntegralRet updateIntegralSet(Model model, HttpServletRequest request,String jsonstr){

        Gson gson = new Gson();
        List<IntegralSet> list = gson.fromJson(jsonstr,new TypeToken<ArrayList<IntegralSet>>() {}.getType());
        for(IntegralSet set : list){
            integralService.updateIntegralSet(set);
        }
        IntegralRet integralRet = new IntegralRet();
        integralRet.setMessage("修改成功");
        integralRet.setResult(BasicRet.SUCCESS);

        Admin admin = (Admin)model.asMap().get(AppConstant.ADMIN_SESSION_NAME);
        //保存用户日志
        memberLogOperator.saveMemberLog(null,admin,"修改积分规则",request,memberOperateLogService);
        return integralRet;
    }


    /**
     * 获取积分记录信息
     * @param pageNo
     * @param pageSize
     * @param type
     * @param memberid
     * @param startTime
     * @param endTime
     * @return
     */
    @RequestMapping(value = "/getIntegerRecordList",method = RequestMethod.POST)
    @ApiOperation(value = "获取积分记录信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo",value = "页码",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "pageSize",value = "页数",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "type",value = "传数组，积分类型0=消费1=注册2=邀请3=后台增加4=后台减少",required = false,paramType = "query",dataType = "Array"),
            @ApiImplicitParam(name = "memberid",value = "用户id",required = false,paramType = "query",dataType = "long"),
            @ApiImplicitParam(name = "startTime",value = "开始时间",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "endTime",value = "结束时间",required = false,paramType = "query",dataType = "string"),
    })
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.MEMBERINTEGRAL + "')")
    public PageRet getIntegerRecordList(int pageNo, int pageSize, Short[] type, Long memberid, Date startTime, Date endTime){
        PageRet pageRet = new PageRet();
        List<Short> list = null;
        if(type!=null){
            list = Arrays.asList(type);
        }else {
            list = new ArrayList<>();
        }
        PageInfo info = integralService.getIntegralRecord(pageNo,pageSize, list,memberid,startTime,endTime);
        pageRet.setResult(BasicRet.SUCCESS);
        pageRet.setMessage("返回成功");
        pageRet.data.setPageInfo(info);
        return pageRet;
    }



    


    /**
     * 获取用户积分信息
     * @param pageNo
     * @param pageSize
     * @param param
     * @return
     */
    @RequestMapping(value = "/getMemberIntegral",method = RequestMethod.POST)
    @ApiOperation(value = "获取用户积分信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo",value = "页码",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "pageSize",value = "页数",required = true,paramType = "query",dataType = "int"),
    })
    public PageRet getMemberIntegral(int pageNo, int pageSize, IntegralQueryParam param){
        PageRet pageRet = new PageRet();
        PageInfo info = integralService.getIntegralPageModle(param,pageNo,pageSize);
        pageRet.setResult(BasicRet.SUCCESS);
        pageRet.setMessage("返回成功");
        pageRet.data.setPageInfo(info);
        return pageRet;
    }

    @RequestMapping(value = "/updateMemberIntegral",method = RequestMethod.POST)
    @ApiOperation(value = "更新用户积分")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "memberid",value = "用户id",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "type",value = "积分类型3=后台增加4=后台减少",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "scope",value = "积分值",required = true,paramType = "query",dataType = "double"),
            @ApiImplicitParam(name = "remark",value = "备注",required = false,paramType = "query",dataType = "string"),
    })
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.MEMBERINTEGRAL + "')")
    public BasicRet updateMemberIntegral(Long memberid, Short type, BigDecimal scope,String remark){
        Member member = memberService.getMemberById(memberid);
        BasicRet basicRet = new BasicRet();
        if(member!=null){
            if(type == Quantity.STATE_3){
                member.setIntegrals(member.getIntegrals().add(scope));
                member.setAvailableintegral(member.getAvailableintegral().add(scope));
            }
            if(type ==Quantity.STATE_4){
                member.setIntegrals(member.getIntegrals().subtract(scope));
                member.setAvailableintegral(member.getAvailableintegral().subtract(scope));
            }
            IntegralRecord integralRecord = new IntegralRecord();
            integralRecord.setCreatetime(new Date());
            integralRecord.setMemberid(memberid);
            integralRecord.setMembername(member.getUsername());
            if(StringUtils.hasText(remark)){
                integralRecord.setRemark(remark);
            }
            integralRecord.setScope(scope);
            integralRecord.setType(type);
            integralService.updateMemberIntegral(member,integralRecord);
            basicRet.setMessage("修改成功");
            basicRet.setResult(BasicRet.SUCCESS);
            return basicRet;
        }else {
            basicRet.setMessage("没有该用户");
            basicRet.setResult(BasicRet.ERR);
            return basicRet;
        }

    }

       public static void main(String[] args) {

        Gson gson = new Gson();
        IntegralSet op = new IntegralSet();
        op.setId(1);
        op.setScope(new BigDecimal(5));


           IntegralSet op1 = new IntegralSet();
           op1.setId(2);
           op1.setScope(new BigDecimal(6));


        List<IntegralSet> list = new ArrayList<IntegralSet>();
        list.add(op);
        list.add(op1);

        String str = gson.toJson(list);

        System.out.println(str);

        List<IntegralSet> list2 = gson.fromJson(str,new TypeToken<ArrayList<IntegralSet>>() {}.getType());


        System.out.println(str);

    }

}
