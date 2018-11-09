package project.jinshang.mod_coupon;

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
import project.jinshang.common.constant.AdminAuthorityConst;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.mod_coupon.bean.YhqProject;
import project.jinshang.mod_coupon.bean.YhqTicketpacks;
import project.jinshang.mod_coupon.bean.YhqTicketset;
import project.jinshang.mod_coupon.service.YhqProjectService;
import project.jinshang.mod_coupon.service.YhqTicketPacksService;
import project.jinshang.mod_coupon.service.YhqTicketSetService;

@RestController
@RequestMapping(value = {"/rest/admin/yhqcheck"})
@Api(tags = "后台优惠券审核", description = "优惠券审核接口")
@SessionAttributes(AppConstant.ADMIN_SESSION_NAME)
public class YhqCheckAction {
    @Autowired
    private YhqTicketSetService yhqTicketSetService;

    @Autowired
    private YhqTicketPacksService yhqTicketPacksService;

    @Autowired
    private YhqProjectService yhqProjectService;

    @PostMapping(value = "/updateYhqTicketSetInfo")
    @ApiOperation(value = "优惠券配置审核")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "优惠券方案Id", required = true, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "status", value = "状态1：待审核97：禁用98：审核不通过99：审核通过", required = true, paramType = "query", dataType = "long"),
    })
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.COUPONREVIEW + "')")
    public BasicRet updateYhqTicketSetInfo(YhqTicketset yhqTicketset) {
        BasicRet basicRet = new BasicRet();
        YhqTicketset oldYhqTicketset = yhqTicketSetService.getYhqTicketSetInfoById(yhqTicketset.getId());
        if (oldYhqTicketset == null) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("不存在该优惠券配置");
        }else if(yhqTicketset.getStatus()!=98 && yhqTicketset.getStatus()!=99) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("审核状态不对，请检查是否为审核通过或者审核不通过");
        }else {
            yhqTicketSetService.updateYhqTicketSetInfo(yhqTicketset);
            basicRet.setResult(BasicRet.SUCCESS);
            basicRet.setMessage("修改成功");
        }
        return basicRet;
    }



    @PostMapping(value = "/updateYhqTicketPacksInfo")
    @ApiOperation(value = "优惠券礼包审核")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "优惠券礼包Id", required = true, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "status", value = "状态1：待审核97：禁用98：审核不通过99：审核通过", required = true, paramType = "query", dataType = "long"),
    })
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.COUPONREVIEW + "')")
    public BasicRet updateYhqTicketPacksInfo(YhqTicketpacks yhqTicketpacks) {
        BasicRet basicRet = new BasicRet();
        YhqTicketpacks oldYhqTicketpacks = yhqTicketPacksService.getYhqTicketpacksInfoById(yhqTicketpacks.getId());
        if (oldYhqTicketpacks == null) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("不存在该优惠券礼包");
        }else if(yhqTicketpacks.getStatus()!=98 && yhqTicketpacks.getStatus()!=99) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("审核状态不对，请检查是否为审核通过或者审核不通过");
        }else {
            yhqTicketPacksService.updateYhqTicketPacksInfo(yhqTicketpacks);
            basicRet.setResult(BasicRet.SUCCESS);
            basicRet.setMessage("修改成功");
        }
        return basicRet;
    }


    @PostMapping(value = "/updateYhqProjectInfo")
    @ApiOperation(value = "优惠券分发项目审核")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "优惠券分发项目Id", required = true, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "status", value = "状态1：待审核2：审核通过97：禁用98：审核不通过99：已执行", required = true, paramType = "query", dataType = "long"),
    })
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.COUPONREVIEW + "')")
    public BasicRet updateYhqProjectInfo(YhqProject yhqProject) {
        BasicRet basicRet = new BasicRet();
        YhqProject oldYhqProject = yhqProjectService.getYhqProjectInfoById(yhqProject.getId());
        if (oldYhqProject == null) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("不存在该优惠券礼包");
        }else if(yhqProject.getStatus()!=2 && yhqProject.getStatus()!=98) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("审核状态不对，请检查是否为审核通过或审核不通过");
        }else {
            yhqProjectService.updateYhqProjectInfo(yhqProject);
            basicRet.setResult(BasicRet.SUCCESS);
            basicRet.setMessage("修改成功");
        }
        return basicRet;
    }






}
