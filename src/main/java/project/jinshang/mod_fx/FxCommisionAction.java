package project.jinshang.mod_fx;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import mizuki.project.core.restserver.config.BasicRet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import project.jinshang.common.bean.PageRet;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.mod_common.bean.BasicExtRet;
import project.jinshang.mod_fx.bean.Fxcommision;
import project.jinshang.mod_fx.dto.FxcommisionDto;
import project.jinshang.mod_fx.service.FxCommisionService;
import project.jinshang.mod_member.bean.Member;
import project.jinshang.mod_member.service.MemberService;

import java.util.Date;


@RestController
@RequestMapping(value = {"/rest/fxcommision"})
@Api(tags = "佣金记录管理", description = "佣金记录管理接口")
@SessionAttributes(AppConstant.MEMBER_SESSION_NAME)
public class FxCommisionAction {

    @Autowired
    private FxCommisionService fxCommisionService;

    @Autowired
    private MemberService memberService;


    @ApiOperation(value = "我的佣金-累计返佣/我的佣金-待到账佣金")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "progressnum", value = "progressNum进度状态（默认99为累计返佣，如果不传默认0为待到帐佣金）", required = true, paramType = "query", dataType = "long",defaultValue = "99"),
    })
    @PostMapping("/get")
    public BasicRet getCountCommisionPriceBycMemberId(Long progressnum,Model model) {
        BasicExtRet basicRet = new BasicExtRet();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        FxcommisionDto fxcommision = null;
        if(99 == progressnum) {
            //累计返佣
            System.out.println("hello");
            fxcommision = fxCommisionService.getCountCommisionPriceBycMemberId(member.getId());
            System.out.println("hello1");
        }else{
            //待到账佣金
            fxcommision = fxCommisionService.getWaitCommisionPriceBycMemberId(member.getId());
            System.out.println("test");
        }
        if (fxcommision == null){
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("佣金记录表还不存在该买家，需要执行定时任务");
        }else{
            basicRet.setResult(BasicRet.SUCCESS);
            basicRet.setMessage("获取成功");
            basicRet.setData(fxcommision);
        }
        return basicRet;
    }

    @ApiOperation(value = "我的佣金-佣金记录列表/我的佣金-搜索列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startime", value = "到账开始时间", required = false, paramType = "query", dataType = "date"),
            @ApiImplicitParam(name = "endtime", value = "到账结束时间", required = false, paramType = "query", dataType = "date"),
            @ApiImplicitParam(name = "progressnum", value = "进度状态 1：订单进行中2：佣金核算中97：订单关闭98：异常操作99：已完成", required = false, paramType = "query", dataType = "int", defaultValue = "-1"),
            @ApiImplicitParam(name = "pageNo", value = "页码(值为-1不分页)", required = true, paramType = "query", dataType = "int", defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页数", required = false, paramType = "query", dataType = "int", defaultValue = "20"),
    })
    @PostMapping("/list")
    public PageRet getList(Date startime, Date endtime, Fxcommision fxcommision, int pageNo, int pageSize, Model model) {
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        PageInfo pageInfo = fxCommisionService.getListByPage(startime,endtime, fxcommision,member.getId(), pageNo, pageSize);
        PageRet pageRet = new PageRet();
        pageRet.data.setPageInfo(pageInfo);
        pageRet.setMessage("返回成功");
        pageRet.setResult(BasicRet.SUCCESS);
        return pageRet;
    }

    @ApiOperation(value = "后台-佣金记录列表/后台-佣金记录搜索列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderno", value = "订单单号", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "cmemberid", value = "返佣人", required = false, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "memberid", value = "买家", required = false, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "startime", value = "到账开始时间", required = false, paramType = "query", dataType = "date"),
            @ApiImplicitParam(name = "endtime", value = "到账结束时间", required = false, paramType = "query", dataType = "date"),
            @ApiImplicitParam(name = "progressnum", value = "进度状态 1：订单进行中2：佣金核算中97：订单关闭98：异常操作99：已完成", required = false, paramType = "query", dataType = "int", defaultValue = "-1"),
            @ApiImplicitParam(name = "pageNo", value = "页码(值为-1不分页)", required = true, paramType = "query", dataType = "int", defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页数", required = false, paramType = "query", dataType = "int", defaultValue = "20"),
    })
    @PostMapping("/list2")
    public PageRet getList2(Date startime, Date endtime, Fxcommision fxcommision, int pageNo, int pageSize) {
        PageInfo pageInfo = fxCommisionService.getListByPage1(startime,endtime, fxcommision,null,pageNo, pageSize);

        PageRet pageRet = new PageRet();
        pageRet.data.setPageInfo(pageInfo);
        pageRet.setMessage("返回成功");
        pageRet.setResult(BasicRet.SUCCESS);
        return pageRet;
    }


    @PostMapping("/list1")
    @ApiOperation(value = "我邀请的用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "页码(值为-1不分页)", required = true, paramType = "query", dataType = "int", defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页数", required = false, paramType = "query", dataType = "int", defaultValue = "20"),

    })
    public PageRet list(int pageNo, int pageSize,Model model) {
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        PageInfo pageInfo = memberService.findInviteList(pageNo, pageSize,member.getId());
        PageRet pageRet = new PageRet();
        pageRet.data.setPageInfo(pageInfo);
        pageRet.setMessage("返回成功");
        pageRet.setResult(BasicRet.SUCCESS);
        return pageRet;
    }


    @PostMapping("/count")
    @ApiOperation(value = "我邀请的用户-邀请数量")
    public BasicRet getList(Model model) {
        BasicExtRet basicRet = new BasicExtRet();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);


        long count = memberService.countAllInvite(member.getId());
        basicRet.setData(count);
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("获取成功");
        return basicRet;
    }


    @ApiOperation(value = "后台-累计返佣/后台-待到账佣金")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "progressnum", value = "progressNum进度状态（默认99为累计返佣，如果0为待到帐佣金）", required = true, paramType = "query", dataType = "long",defaultValue = "99"),
    })
    @PostMapping("/get1")
    public BasicRet getCountCommisionPrice(Long progressnum) {
        BasicExtRet basicRet = new BasicExtRet();
        FxcommisionDto fxcommision = null;
        System.out.println("progressNum~~~~~~~~~~~~~"+progressnum);
        if(99 == progressnum) {
            //累计返佣
            fxcommision = fxCommisionService.getAllCountCommisionPrice();
        }else{
            //待到账佣金
            fxcommision = fxCommisionService.getAllWaitCommisionPrice();
        }
            basicRet.setResult(BasicRet.SUCCESS);
            basicRet.setMessage("获取成功");
            basicRet.setData(fxcommision);

        return basicRet;
    }









}
