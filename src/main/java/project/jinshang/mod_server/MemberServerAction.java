package project.jinshang.mod_server;

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
import project.jinshang.common.bean.MemberLogOperator;
import project.jinshang.common.bean.PageRet;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.mod_member.bean.Admin;
import project.jinshang.mod_member.bean.Member;
import project.jinshang.mod_product.service.MemberOperateLogService;
import project.jinshang.mod_server.bean.*;
import project.jinshang.mod_server.service.MemberServerService;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/rest/admin/server")
@SessionAttributes({AppConstant.ADMIN_SESSION_NAME})
@Api(tags = "后台服务商模块", description = "后台服务商模块")
@Transactional(rollbackFor = Exception.class)
public class MemberServerAction {

    MemberLogOperator memberLogOperator = new MemberLogOperator();

    @Autowired
    private MemberOperateLogService memberOperateLogService;


    @Autowired
    private MemberServerService memberServerService;


    private class MemberRet extends BasicRet {

        private class Data {

            private ServerPageModel serverPageModel;

            private BigDecimal totalSum;

            private ServerSet serverSet;

            private List<ServerSet> list;

            public List<ServerSet> getList() {
                return list;
            }

            public void setList(List<ServerSet> list) {
                this.list = list;
            }

            public ServerSet getServerSet() {
                return serverSet;
            }

            public void setServerSet(ServerSet serverSet) {
                this.serverSet = serverSet;
            }

            public BigDecimal getTotalSum() {
                return totalSum;
            }

            public void setTotalSum(BigDecimal totalSum) {
                this.totalSum = totalSum;
            }

            public ServerPageModel getServerPageModel() {
                return serverPageModel;
            }

            public void setServerPageModel(ServerPageModel serverPageModel) {
                this.serverPageModel = serverPageModel;
            }
        }

        private Data data = new Data();

        public Data getData() {
            return data;
        }

        public void setData(Data data) {
            this.data = data;
        }
    }

    @RequestMapping(value = "/updateMemberToServer", method = RequestMethod.POST)
    @ApiOperation(value = "改变是否为服务商")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = true, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "service", value = "是否是服务商", required = true, paramType = "query", dataType = "boolean"),
    })
    public MemberRet updateMemberToServer(Model model, Long id, Boolean services, HttpServletRequest request) {
        Admin admin = (Admin) model.asMap().get(AppConstant.ADMIN_SESSION_NAME);

        MemberRet memberRet = new MemberRet();
        Member member = memberServerService.getMemberById(id);
        member.setServices(services);
        memberServerService.updateMember(member);
        memberRet.setMessage("修改成功");
        memberRet.setResult(BasicRet.SUCCESS);

        memberLogOperator.saveMemberLog(null, admin, "设置是否为服务商" + services, "/rest/admin/server/updateMemberToServer", request, memberOperateLogService);

        return memberRet;
    }


    @RequestMapping(value = "/getMemberServerList", method = RequestMethod.POST)
    @ApiOperation(value = "获取服务商列表")
    public PageRet getMemberServerList(ServerQueryParam param) {
        PageRet pageRet = new PageRet();
        PageInfo pageInfo = memberServerService.getMemberServerList(param);
        pageRet.data.setPageInfo(pageInfo);
        pageRet.setResult(BasicRet.SUCCESS);
        pageRet.setMessage("返回成功");

        return pageRet;
    }


    @RequestMapping(value = "/getSingleMemberInfo", method = RequestMethod.POST)
    @ApiOperation(value = "获取单个服务商信息")
    public MemberRet getSingleMemberInfo(Long id) {
        MemberRet memberRet = new MemberRet();
        ServerPageModel serverPageModel = memberServerService.getSingleMemberById(id);
        memberRet.data.serverPageModel = serverPageModel;
        memberRet.setMessage("返回成功");
        memberRet.setResult(BasicRet.SUCCESS);
        return memberRet;
    }

    @RequestMapping(value = "/getSingleServerSet", method = RequestMethod.POST)
    @ApiOperation(value = "获取单个服务商设置")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "服务商设置id", required = true, paramType = "query", dataType = "long"),
    })
    public MemberRet getSingleServerSet(Long id) {
        MemberRet memberRet = new MemberRet();
        ServerSet serverSet = memberServerService.getServerSer(id);
        memberRet.data.serverSet = serverSet;
        memberRet.setMessage("返回成功");
        memberRet.setResult(BasicRet.SUCCESS);
        return memberRet;
    }


    @RequestMapping(value = "/saveServerSet", method = RequestMethod.POST)
    @ApiOperation(value = "保存服务费设置")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "memberid", value = "用户id", required = true, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "servername", value = "公司名称", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "province", value = "省", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "city", value = "市", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "area", value = "区", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "starttime", value = "开始时间", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "endtime", value = "结束时间", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "rate", value = "服务费占比", required = true, paramType = "query", dataType = "double"),
    })
    public BasicRet saveServerSet(Model model, ServerSet serverSet, HttpServletRequest request) {
        Admin member = (Admin) model.asMap().get(AppConstant.ADMIN_SESSION_NAME);
        BasicRet basicRet = new BasicRet();
        ServerSet serset = memberServerService.getSingelServerSetByArea(serverSet.getArea(), serverSet.getCity(), serverSet.getProvince(), serverSet.getMemberid());
        if (serset != null) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("同一用户地区不能重复");
        } else {
            memberServerService.saveServerSet(serverSet);
            basicRet.setResult(BasicRet.SUCCESS);
            basicRet.setMessage("保存成功");
            memberLogOperator.saveMemberLog(null, member, "新增用户id为" + serverSet.getMemberid() + "服务费设置", "/rest/admin/server/saveServerSet", request, memberOperateLogService);

        }
        return basicRet;
    }

    @RequestMapping(value = "/updateServerSet", method = RequestMethod.POST)
    @ApiOperation(value = "修改服务费设置")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "服务商设置id", required = true, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "starttime", value = "开始时间", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "endtime", value = "结束时间", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "rate", value = "服务费占比", required = false, paramType = "query", dataType = "double"),
    })
    public BasicRet updateServerSet(Model model, ServerSet serverSet, HttpServletRequest request) {
        Admin member = (Admin) model.asMap().get(AppConstant.ADMIN_SESSION_NAME);

        BasicRet basicRet = new BasicRet();
        memberServerService.updateServerSet(serverSet);
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("修改成功");

        memberLogOperator.saveMemberLog(null, member, "修改id为" + serverSet.getId() + "服务费设置", "/rest/admin/server/updateServerSet", request, memberOperateLogService);

        return basicRet;
    }


    @RequestMapping(value = "/deleteServerSet", method = RequestMethod.POST)
    @ApiOperation(value = "删除服务费设置")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "服务商设置id", required = true, paramType = "query", dataType = "long"),
    })
    public BasicRet deleteServerSet(Model model, Long id, HttpServletRequest request) {

        Admin member = (Admin) model.asMap().get(AppConstant.ADMIN_SESSION_NAME);

        BasicRet basicRet = new BasicRet();
        memberServerService.deleteServerSet(id);
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("删除成功");

        memberLogOperator.saveMemberLog(null, member, "删除用户" + id + "服务费设置", "/rest/admin/server/deleteServerSet", request, memberOperateLogService);

        return basicRet;
    }


    @RequestMapping(value = "/getServerPayList", method = RequestMethod.POST)
    @ApiOperation(value = "获取服务商结算列表")
    public PageRet getServerPayList(ServerPayQueryParam param) {
        PageRet memberRet = new PageRet();
        PageInfo pageInfo = memberServerService.getServerPayList(param);
        memberRet.data.setPageInfo(pageInfo);
        memberRet.setMessage("返回成功");
        memberRet.setResult(BasicRet.SUCCESS);
        return memberRet;
    }

    @RequestMapping(value = "/getTotalSum", method = RequestMethod.POST)
    @ApiOperation(value = "获取服务商结算总额")
    public MemberRet getTotalSum(ServerPayQueryParam param) {
        MemberRet memberRet = new MemberRet();
        BigDecimal totalSum = memberServerService.getTotalSum(param);
        memberRet.data.totalSum = totalSum;
        memberRet.setResult(BasicRet.SUCCESS);
        memberRet.setMessage("返回成功");
        return memberRet;
    }


    @RequestMapping(value = "/getSettlePayDetailList", method = RequestMethod.POST)
    @ApiOperation(value = "获取结算详情列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "结算设置id", required = true, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "startTime", value = "开始时间", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "pageNo", value = "页数", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "pageSize", value = "一页多少", required = true, paramType = "query", dataType = "string"),
    })
    public PageRet getSettlePayDetailList(SettleQueryParam param) {
        PageRet memberRet = new PageRet();
        PageInfo pageInfo = memberServerService.getServerOrderModelList(param);
        memberRet.data.setPageInfo(pageInfo);
        memberRet.setMessage("返回成功");
        memberRet.setResult(BasicRet.SUCCESS);
        return memberRet;
    }

    @RequestMapping(value = "/getSettleTotalSum", method = RequestMethod.POST)
    @ApiOperation(value = "获取结算详情总额")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "结算设置id", required = true, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "startTime", value = "开始时间", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", required = false, paramType = "query", dataType = "string"),
    })
    public MemberRet getSettleTotalSum(SettleQueryParam param) {
        MemberRet memberRet = new MemberRet();
        BigDecimal totalPay = memberServerService.getTotalPay(param);
        memberRet.data.totalSum = totalPay;
        memberRet.setResult(BasicRet.SUCCESS);
        memberRet.setMessage("返回成功");
        return memberRet;
    }

    @RequestMapping(value = "/getServerSetList", method = RequestMethod.POST)
    @ApiOperation(value = "获取服务商下拉列表")
    public MemberRet getServerSetList() {
        MemberRet memberRet = new MemberRet();
        List<ServerSet> list = memberServerService.getServerSetList();
        memberRet.data.list = list;
        memberRet.setResult(BasicRet.SUCCESS);
        memberRet.setMessage("返回成功");
        return memberRet;
    }
}
