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
import project.jinshang.common.exception.MyException;
import project.jinshang.common.utils.GenerateNo;
import project.jinshang.mod_common.bean.BasicExtRet;
import project.jinshang.mod_coupon.bean.YhqProject;
import project.jinshang.mod_coupon.bean.YhqTicket;
import project.jinshang.mod_coupon.bean.YhqTicketExample;
import project.jinshang.mod_coupon.bean.YhqTicketset;
import project.jinshang.mod_coupon.service.YhqProjectService;
import project.jinshang.mod_coupon.service.YhqTicketService;
import project.jinshang.mod_member.bean.Admin;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value = {"/rest/admin/yhqticket"})
@Api(tags = "后台优惠券生成", description = "优惠券生成")
@SessionAttributes(AppConstant.ADMIN_SESSION_NAME)
public class YhqTicketAction {

    @Autowired
    private YhqTicketService yhqTicketService;
    @Autowired
    private YhqProjectService yhqProjectService;


    @PostMapping(value = "/insert")
    @ApiOperation(value = "优惠券生成")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "名称", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "type", value = "类型", required = true, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "planid", value = "方案ID", required = true, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "ticketsetid", value = "优惠券配置ID", required = true, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "about", value = "简介", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "rule", value = "优惠券规则", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "categorieslist", value = "适用分类列表", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "storelist", value = "适用仓库列表", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "productstorelist", value = "适用商品列表", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "membergradelist", value = "适用买家等级列表", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "starttime", value = "发放开始时间", required = true, paramType = "query", dataType = "date"),
            @ApiImplicitParam(name = "endtime", value = "发放截止时间", required = true, paramType = "query", dataType = "date"),
            @ApiImplicitParam(name = "validityrule", value = "有效规则", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "validitystarttime", value = "有效开始时间", required = true, paramType = "query", dataType = "date"),
            @ApiImplicitParam(name = "validityendtime", value = "有效截止时间", required = true, paramType = "query", dataType = "date"),
            @ApiImplicitParam(name = "ticketcount", value = "发放数量", required = true, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "createtype", value = "发放类型", required = true, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "rule", value = "优惠规则", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "validityrule", value = "有效规则", required = true, paramType = "query", dataType = "string"),

    })
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.COUPONLIST + "')")
    public BasicRet insertYhqTicketInfo(YhqTicket yhqTicket, Model model) {
       BasicRet basicRet = new BasicRet();
        BigDecimal zero = new BigDecimal(0);
        Admin admin = (Admin) model.asMap().get(AppConstant.ADMIN_SESSION_NAME);
        YhqTicketExample yhqTicketExample = new YhqTicketExample();
        YhqTicketExample.Criteria criteria = yhqTicketExample.createCriteria();
        criteria.andNameEqualTo(yhqTicket.getName());
        long count = yhqTicketService.countByExample(yhqTicketExample);
        if (count > 0) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("新增失败,名称已存在");
            return basicRet;
        }


        yhqTicket.setNo(GenerateNo.getYNo());
        yhqTicket.setProjectid(0l);
        yhqTicket.setCapital(zero);
        yhqTicket.setAvailable(zero);
        yhqTicket.setStatus(1l);
        yhqTicket.setMemberid(0l);
//        yhqTicket.setOrdersid(0l);
        yhqTicket.setUsersid(admin.getId());
        yhqTicket.setCreatetime(new Date());
        yhqTicketService.insertYhqTicketInfo(yhqTicket);
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("新增成功");
        return basicRet;
    }


    @ApiOperation(value = "优惠券管理/搜索")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "名称", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "type", value = "类型", required = false, paramType = "query", dataType = "long", defaultValue = "0"),
            @ApiImplicitParam(name = "planid", value = "优惠券方案ID", required = false, paramType = "query", dataType = "long", defaultValue = "0"),
            @ApiImplicitParam(name = "ticketsetid", value = "优惠券配置ID", required = false, paramType = "query", dataType = "long", defaultValue = "0"),
            @ApiImplicitParam(name = "status", value = "状态", required = false, paramType = "query", dataType = "string" , defaultValue = "0"),
            @ApiImplicitParam(name = "pageNo", value = "页码(值为-1不分页)", required = true, paramType = "query", dataType = "int", defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页数", required = false, paramType = "query", dataType = "int", defaultValue = "20"),
    })
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.COUPONLIST + "')")
    @PostMapping("/list")
    public PageRet getList(int pageNo, int pageSize, YhqTicket yhqTicket) {
        PageInfo pageInfo = yhqTicketService.getListByPage(pageNo, pageSize,yhqTicket);

        PageRet pageRet = new PageRet();
        pageRet.data.setPageInfo(pageInfo);
        pageRet.setMessage("返回成功");
        pageRet.setResult(BasicRet.SUCCESS);
        return pageRet;
    }




    @PostMapping(value = "/get")
    @ApiOperation(value = "获取优惠券")
    @ApiImplicitParam(name = "id", value = "优惠券Id", required = true, paramType = "query", dataType = "long")
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.COUPONLIST + "')")
    public BasicRet getYhqTicketSetInfoDetail(Long id) {
        BasicExtRet basicRet = new BasicExtRet();
        //用list主要是因为会分单所以一个优惠券会有多个订单使用
        List<Map<String,Object>> param = yhqTicketService.getListYhqTicketInfoById(id);
        if (param == null) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("不存在该优惠券");
        } else {
            basicRet.setResult(BasicRet.SUCCESS);
            basicRet.setMessage("获取成功");
            basicRet.setData(param);
        }
        return basicRet;
    }


    @PostMapping(value = "/update")
    @ApiOperation(value = "修改优惠券状态/禁用 恢复")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "优惠券Id", required = true, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "status", value = "状态 禁用98 恢复2 已领取", required = true, paramType = "query", dataType = "long"),
    })
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.COUPONLIST + "')")
    public BasicRet updateYhqTicketInfo(Long id,Long status) {
        BasicRet basicRet = new BasicRet();
        YhqTicket oldYhqTicket = yhqTicketService.getYhqTicketInfoById(id);
        if (oldYhqTicket == null) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("不存在该优惠券");
        } else {
            YhqTicket yhqTicket = new YhqTicket();
            yhqTicket.setId(id);
            yhqTicket.setStatus(status);
            yhqTicketService.updateYhqTicketInfo(yhqTicket);
            basicRet.setResult(BasicRet.SUCCESS);
            basicRet.setMessage("修改成功");
        }
        return basicRet;
    }


    @ApiOperation(value = "优惠券分发记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "名称", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "type", value = "类型", required = false, paramType = "query", dataType = "long", defaultValue = "0"),
            @ApiImplicitParam(name = "planid", value = "优惠券方案ID", required = false, paramType = "query", dataType = "long", defaultValue = "0"),
            @ApiImplicitParam(name = "ticketsetid", value = "优惠券配置ID", required = false, paramType = "query", dataType = "long", defaultValue = "0"),
            @ApiImplicitParam(name = "projectid", value = "优惠券分发方案", required = true, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "status", value = "状态", required = false, paramType = "query", dataType = "string" , defaultValue = "0"),
            @ApiImplicitParam(name = "pageNo", value = "页码(值为-1不分页)", required = true, paramType = "query", dataType = "int", defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页数", required = false, paramType = "query", dataType = "int", defaultValue = "20"),
    })
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.COUPONLIST + "')")
    @PostMapping("/list1")
    public PageRet getList1(int pageNo, int pageSize, YhqTicket yhqTicket) {
        PageInfo pageInfo = yhqTicketService.getListByPage1(pageNo, pageSize,yhqTicket);
        PageRet pageRet = new PageRet();
        pageRet.data.setPageInfo(pageInfo);
        pageRet.setMessage("返回成功");
        pageRet.setResult(BasicRet.SUCCESS);
        return pageRet;
    }


 /*   @PostMapping(value = "/executeticket")
    @ApiOperation(value = "优惠券执行分发")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "优惠券分发项目id", required = true, paramType = "query", dataType = "long"),
    })*/
   /* public BasicRet executeticket(YhqProject yhqProject, Model model) {
        BasicRet basicRet = new BasicRet();
        YhqProject yhqProject1 = yhqProjectService.getYhqProjectInfoById(yhqProject.getId());
        //同时将选择的优惠券配置 写入到优惠券表
        yhqTicketService.insertYhqTicketForExecute(new YhqTicket(),yhqProject1);
        //System.out.println("新用户优惠券发放完成");
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("优惠券发放完成");
        return basicRet;
    }*/


    @PostMapping(value = "/executeticket")
    @ApiOperation(value = "优惠券执行分发")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "优惠券分发项目id", required = true, paramType = "query", dataType = "long"),
    })
    public BasicRet executeticket1(YhqProject yhqProject, Model model) throws MyException {
        BasicRet basicRet = new BasicRet();
        YhqProject oldYhqProject = yhqProjectService.getYhqProjectInfoById(yhqProject.getId());
        if(oldYhqProject == null){
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("不存在该优惠券分发项目");
        }
        //同时将选择的优惠券配置 写入到优惠券表
        yhqTicketService.insertYhqTicketForExecute1(oldYhqProject);
        //全部分发完还要将分发状态改成99已执行
        YhqProject newYhqProject = new YhqProject();
        newYhqProject.setId(yhqProject.getId());
        newYhqProject.setStatus(99l);
        yhqProjectService.updateYhqProjectInfo(newYhqProject);
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("优惠券发放完成");
        return basicRet;
    }




}
