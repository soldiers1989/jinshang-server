package project.jinshang.mod_coupon;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import mizuki.project.core.restserver.config.BasicRet;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import project.jinshang.common.bean.PageRet;
import project.jinshang.common.constant.AdminAuthorityConst;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.common.utils.GenerateNo;
import project.jinshang.mod_common.bean.BasicExtRet;
import project.jinshang.mod_coupon.bean.*;
import project.jinshang.mod_coupon.service.YhqGetPacksService;
import project.jinshang.mod_coupon.service.YhqTicketPacksService;
import project.jinshang.mod_coupon.service.YhqTicketService;
import project.jinshang.mod_member.bean.Admin;
import project.jinshang.mod_member.bean.Member;
import project.jinshang.mod_member.bean.MemberExample;
import project.jinshang.mod_member.service.MemberService;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = {"/rest/admin/yhqticketpacks"})
@Api(tags = "后台优惠券礼包管理", description = "优惠券礼包接口")
@SessionAttributes(AppConstant.ADMIN_SESSION_NAME)
public class YhqTicketPacksAction {
    @Autowired
    private YhqTicketPacksService yhqTicketPacksService;
    @Autowired
    private YhqGetPacksService yhqGetPacksService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private YhqTicketService yhqTicketService;

    @ApiOperation(value = "优惠券礼包列表/搜索")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "名称", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "status", value = "状态1：待审核97：禁用98：审核不通过99：审核通过", required = false, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "pageNo", value = "页码(值为-1不分页)", required = true, paramType = "query", dataType = "int", defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页数", required = false, paramType = "query", dataType = "int", defaultValue = "20"),
    })
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.COUPONPACKAGEMANAGE + "')")
    @PostMapping("/list")
    public PageRet getList(int pageNo, int pageSize, YhqTicketpacks yhqTicketpacks) {
        PageInfo pageInfo = yhqTicketPacksService.getListByPage(pageNo, pageSize,yhqTicketpacks);

        PageRet pageRet = new PageRet();
        pageRet.data.setPageInfo(pageInfo);
        pageRet.setMessage("返回成功");
        pageRet.setResult(BasicRet.SUCCESS);
        return pageRet;
    }


    @PostMapping(value = "/insert")
    @ApiOperation(value = "优惠券礼包生成")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "名称", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "ticketlist", value = "发放方案", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "packscount", value = "限定发放数量", required = true, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "surpluscount", value = "剩余数量", required = true, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "starttime", value = "发放开始时间", required = true, paramType = "query", dataType = "date"),
            @ApiImplicitParam(name = "endtime", value = "发放截止时间", required = true, paramType = "query", dataType = "date"),
    })
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.COUPONPACKAGEMANAGE + "')")
    public BasicRet insertYhqTicketpacksInfo(YhqTicketpacks yhqTicketpacks, Model model) {
        BasicRet basicRet = new BasicRet();
        Admin admin = (Admin) model.asMap().get(AppConstant.ADMIN_SESSION_NAME);
        yhqTicketpacks.setNo(GenerateNo.getYNo());
        yhqTicketpacks.setStatus(1l);
        yhqTicketpacks.setAuditingid(0l);
        yhqTicketpacks.setUsersid(admin.getId());
        yhqTicketpacks.setCreatetime(new Date());
        yhqTicketPacksService.insertYhqTicketpacksInfo(yhqTicketpacks);
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("新增成功");
        return basicRet;
    }


    @PostMapping(value = "/update")
    @ApiOperation(value = "优惠券礼包生成")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "name", value = "名称", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "ticketlist", value = "发放方案", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "packscount", value = "限定发放数量", required = false, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "surpluscount", value = "剩余数量", required = false, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "starttime", value = "发放开始时间", required = false, paramType = "query", dataType = "date"),
            @ApiImplicitParam(name = "endtime", value = "发放截止时间", required = false, paramType = "query", dataType = "date"),
    })
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.COUPONPACKAGEMANAGE + "')")
    public BasicRet updateYhqTicketpacksInfo(YhqTicketpacks yhqTicketpacks, Model model) {
        BasicRet basicRet = new BasicRet();
        Admin admin = (Admin) model.asMap().get(AppConstant.ADMIN_SESSION_NAME);
        YhqTicketpacks newYhqTicketpacks = new YhqTicketpacks();
        newYhqTicketpacks.setId(yhqTicketpacks.getId());
        newYhqTicketpacks.setStatus(1l);
        newYhqTicketpacks.setUsersid(admin.getId());
        newYhqTicketpacks.setName(yhqTicketpacks.getName());
        newYhqTicketpacks.setTicketlist(yhqTicketpacks.getTicketlist());
        newYhqTicketpacks.setPackscount(yhqTicketpacks.getPackscount());
        newYhqTicketpacks.setSurpluscount(yhqTicketpacks.getSurpluscount());
        newYhqTicketpacks.setStarttime(yhqTicketpacks.getStarttime());
        newYhqTicketpacks.setEndtime(yhqTicketpacks.getEndtime());
        newYhqTicketpacks.setCreatetime(new Date());
        yhqTicketPacksService.updateYhqTicketPacksInfo(yhqTicketpacks);
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("更新成功");
        return basicRet;
    }



    @PostMapping(value = "/get")
    @ApiOperation(value = "获取优惠券礼包详情")
    @ApiImplicitParam(name = "id", value = "优惠券礼包Id", required = true, paramType = "query", dataType = "long")
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.COUPONPACKAGEMANAGE + "')")
    public BasicRet getYhqTicketpacksInfoDetail(Long id) {
        BasicExtRet basicRet = new BasicExtRet();
        YhqTicketpacks yhqTicketpacks = yhqTicketPacksService.getYhqTicketpacksInfoById(id);

        if (yhqTicketpacks == null) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("不存在该优惠券礼包");
        } else {
            List<Map<String,Object>> list = yhqTicketPacksService.selectById(id,yhqTicketpacks);
            basicRet.setResult(BasicRet.SUCCESS);
            basicRet.setMessage("获取成功");
            basicRet.setData(yhqTicketpacks);
        }
        return basicRet;
    }


    @PostMapping(value = "/delete")
    @ApiOperation(value = "删除优惠券礼包")
    @ApiImplicitParam(name = "id", value = "优惠券礼包Id", required = true, paramType = "query", dataType = "long")
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.COUPONPACKAGEMANAGE + "')")
    public BasicRet deleteYhqTicketpacksInfo(Long id) {
        BasicRet basicRet = new BasicRet();
        YhqTicketpacks oldYhqTicketpacks = yhqTicketPacksService.getYhqTicketpacksInfoById(id);
        if (oldYhqTicketpacks == null) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("不存在优惠券礼包");
        } else {
            //审核了不能删除
            YhqTicketpacksExample yhqTicketpacksExample = new YhqTicketpacksExample();
            YhqTicketpacksExample.Criteria criteria = yhqTicketpacksExample.createCriteria();
            criteria.andIdEqualTo(id);
            criteria.andStatusEqualTo(99l);
            long count = yhqTicketPacksService.countByExample(yhqTicketpacksExample);
            if (count > 0) {
                basicRet.setResult(BasicRet.ERR);
                basicRet.setMessage("已审核通过的优惠券礼包无法删除！");
                return basicRet;
            }

            yhqTicketPacksService.deleteYhqTicketpacksInfoById(id);
            basicRet.setResult(BasicRet.SUCCESS);
            basicRet.setMessage("删除成功");
        }
        return basicRet;
    }








}
