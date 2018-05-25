package project.jinshang.mod_fx;


import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import mizuki.project.core.restserver.config.BasicRet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import project.jinshang.common.bean.PageRet;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.mod_fx.service.FxMoneyListService;
import project.jinshang.mod_member.service.MemberService;


@RestController
@RequestMapping(value = {"/rest/fxmoneylist"})
@Api(tags = "佣金汇总管理", description = "佣金汇总接口")
@SessionAttributes(AppConstant.MEMBER_SESSION_NAME)
public class FxMoneyListAction {

    @Autowired
    private MemberService memberService;
    @Autowired
    private FxMoneyListService fxMoneyListService;

    @PostMapping("/list")
    @ApiOperation(value = "显示会员列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "页码(值为-1不分页)", required = true, paramType = "query", dataType = "int", defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页数", required = false, paramType = "query", dataType = "int", defaultValue = "20"),
    })
    public PageRet list(int pageNo, int pageSize) {

        PageInfo pageInfo = memberService.findAllMemberList(pageNo, pageSize);
        PageRet pageRet = new PageRet();
        pageRet.data.setPageInfo(pageInfo);
        pageRet.setMessage("返回成功");
        pageRet.setResult(BasicRet.SUCCESS);
        return pageRet;
    }


    @PostMapping("/list1")
    @ApiOperation(value = "查看佣金汇总")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "id",name = "memberid",required = true,dataType = "long",paramType = "query"),
            @ApiImplicitParam(name = "pageNo", value = "页码(值为-1不分页)", required = true, paramType = "query", dataType = "int", defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页数", required = false, paramType = "query", dataType = "int", defaultValue = "20"),
    })
    public PageRet list1(long memberid,int pageNo, int pageSize) {
        PageInfo pageInfo = fxMoneyListService.findCommisionList(memberid,pageNo, pageSize);
        PageRet pageRet = new PageRet();
        pageRet.data.setPageInfo(pageInfo);
        pageRet.setMessage("返回成功");
        pageRet.setResult(BasicRet.SUCCESS);
        return pageRet;
    }


}
