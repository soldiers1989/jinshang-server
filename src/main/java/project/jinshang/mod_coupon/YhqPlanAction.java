package project.jinshang.mod_coupon;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import mizuki.project.core.restserver.config.BasicRet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import project.jinshang.common.bean.PageRet;
import project.jinshang.common.constant.AdminAuthorityConst;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.mod_common.bean.BasicExtRet;
import project.jinshang.mod_coupon.bean.YhqPlan;
import project.jinshang.mod_coupon.bean.YhqPlanExample;
import project.jinshang.mod_coupon.bean.YhqTicketsetExample;
import project.jinshang.mod_coupon.service.YhqPlanService;
import project.jinshang.mod_coupon.service.YhqTicketSetService;


@RestController
@RequestMapping(value = {"/rest/admin/yhqplan"})
@Api(tags = "后台优惠券方案管理", description = "优惠券方案接口")
@SessionAttributes(AppConstant.ADMIN_SESSION_NAME)
public class YhqPlanAction {


    @Autowired
    private YhqPlanService yhqPlanService;
    @Autowired
    private YhqTicketSetService yhqTicketSetService;

    @PostMapping(value = "/insert")
    @ApiOperation(value = "新增优惠券方案")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "名称", required = true, paramType = "query", dataType = "string"),
    })
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.COUPONMANAGE + "')")
    public BasicRet insertYhqPlanInfo(YhqPlan yhqPlan) {
        BasicRet basicRet = new BasicRet();

        YhqPlanExample example = new YhqPlanExample();
        YhqPlanExample.Criteria criteria = example.createCriteria();
        criteria.andNameEqualTo(yhqPlan.getName());
        long count = yhqPlanService.countByExample(example);
        if (count > 0) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("新增失败,优惠券方案已存在");
            return basicRet;
        }

        yhqPlanService.insertYhqPlanInfo(yhqPlan);
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("新增成功");
        return basicRet;
    }



    @PostMapping(value = "/get")
    @ApiOperation(value = "获取优惠券方案详情")
    @ApiImplicitParam(name = "id", value = "优惠券方案Id", required = true, paramType = "query", dataType = "long")
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.COUPONMANAGE + "')")
    public BasicRet getYhqPlanInfoDetail(Long id) {
        BasicExtRet basicRet = new BasicExtRet();
        YhqPlan yhqPlan = yhqPlanService.getYhqPlanInfoById(id);
        if (yhqPlan == null) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("不存在该优惠券方案");
        } else {
            basicRet.setResult(BasicRet.SUCCESS);
            basicRet.setMessage("获取成功");
            basicRet.setData(yhqPlan);
        }
        return basicRet;
    }


    @PostMapping(value = "/update")
    @ApiOperation(value = "修改优惠券方案")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "优惠券方案Id", required = true, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "name", value = "名称", required = true, paramType = "query", dataType = "string"),
    })
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.COUPONMANAGE + "')")
    public BasicRet updateYhqPlanInfo(YhqPlan yhqPlan) {
        BasicRet basicRet = new BasicRet();
        YhqPlan oldYhqPlan = yhqPlanService.getYhqPlanInfoById(yhqPlan.getId());

        YhqPlanExample example = new YhqPlanExample();
        YhqPlanExample.Criteria criteria = example.createCriteria();
        criteria.andNameEqualTo(yhqPlan.getName());
        long count = yhqPlanService.countByExample(example);
        if (oldYhqPlan == null) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("不存在该优惠券");
        }else if (count > 0) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("修改失败,优惠券方案已存在");
            return basicRet;
        } else {
            yhqPlanService.updateYhqPlanInfo(yhqPlan);
            basicRet.setResult(BasicRet.SUCCESS);
            basicRet.setMessage("修改成功");
        }
        return basicRet;
    }



    @ApiOperation(value = "优惠券方案列表/搜索")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "名称", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "pageNo", value = "页码(值为-1不分页)", required = true, paramType = "query", dataType = "int", defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页数", required = false, paramType = "query", dataType = "int", defaultValue = "20"),
    })
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.COUPONMANAGE + "')")
    @PostMapping("/list")
    public PageRet getList(int pageNo, int pageSize, YhqPlan yhqPlan) {
        PageInfo pageInfo = yhqPlanService.getListByPage(pageNo, pageSize,yhqPlan);

        PageRet pageRet = new PageRet();
        pageRet.data.setPageInfo(pageInfo);
        pageRet.setMessage("返回成功");
        pageRet.setResult(BasicRet.SUCCESS);
        return pageRet;
    }






    @PostMapping(value = "/delete")
    @ApiOperation(value = "删除优惠券方案")
    @ApiImplicitParam(name = "id", value = "优惠券方案Id", required = true, paramType = "query", dataType = "long")
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.COUPONMANAGE + "')")
    public BasicRet deleteYhqPlanInfo(Long id) {
        BasicRet basicRet = new BasicRet();
        YhqPlan oldYhqPlan = yhqPlanService.getYhqPlanInfoById(id);
        if (oldYhqPlan == null) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("不存在优惠券方案");
        } else {
            //优惠券配置表
            YhqTicketsetExample yhqTicketsetExample = new YhqTicketsetExample();
            YhqTicketsetExample.Criteria criteria = yhqTicketsetExample.createCriteria();
            criteria.andPlanidEqualTo(id);
            long count = yhqTicketSetService.countByExample(yhqTicketsetExample);
            if (count > 0) {
                basicRet.setResult(BasicRet.ERR);
                basicRet.setMessage("删除失败,该优惠券方案优惠券配置表引用");
                return basicRet;
            }
            yhqPlanService.deleteYhqPlanInfoById(id);
            basicRet.setResult(BasicRet.SUCCESS);
            basicRet.setMessage("删除成功");
        }
        return basicRet;
    }










}
