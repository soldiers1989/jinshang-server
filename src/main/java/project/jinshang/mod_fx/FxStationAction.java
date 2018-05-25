package project.jinshang.mod_fx;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import mizuki.project.core.restserver.config.BasicRet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.mod_common.bean.BasicExtRet;
import project.jinshang.mod_fx.bean.Fxstation;
import project.jinshang.mod_fx.service.FxStationService;



@RestController
@RequestMapping(value = {"/rest/fxstation"})
@Api(tags = "三级分销设置管理", description = "三级分销设置接口")
@SessionAttributes(AppConstant.MEMBER_SESSION_NAME)
public class FxStationAction {

    @Autowired
    private FxStationService fxStationService;

    @PostMapping(value = "/update")
    @ApiOperation(value = "修改三级分销基础信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "ID", required = true, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "cycle", value = "佣金返现周期", required = true, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "ratio2", value = "二级返佣金比例", required = true, paramType = "query", dataType = "decimal"),
            @ApiImplicitParam(name = "disable", value = "状态", required = true, paramType = "query", dataType = "boolean"),
    })
    public BasicRet updateStationInfo(Fxstation fxstation) {
        BasicRet basicRet = new BasicRet();
        Fxstation oldfxstation = fxStationService.getStationInfoById(fxstation.getId());
        if (oldfxstation == null) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("不存在该三级分销基础信息");
        } else {
            fxStationService.updateStationInfo(fxstation);
            basicRet.setResult(BasicRet.SUCCESS);
            basicRet.setMessage("修改成功");
        }
        return basicRet;
    }


    @PostMapping("/get")
    @ApiOperation(value = "获取三级分销基础详情")
    public BasicRet getStationInfo() {
        BasicExtRet basicRet = new BasicExtRet();
        Fxstation fxstation = fxStationService.getStationInfo();
        if (fxstation == null) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("不存在该三级分销基础");
        } else {
            basicRet.setResult(BasicRet.SUCCESS);
            basicRet.setMessage("获取成功");
            basicRet.setData(fxstation);
        }
        return basicRet;
    }
}
