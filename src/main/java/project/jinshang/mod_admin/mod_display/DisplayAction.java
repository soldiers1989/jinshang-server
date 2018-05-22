package project.jinshang.mod_admin.mod_display;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import mizuki.project.core.restserver.config.BasicRet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.jinshang.common.bean.MemberLogOperator;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.mod_admin.mod_display.bean.Display;
import project.jinshang.mod_admin.mod_display.service.DisplayService;
import project.jinshang.mod_member.bean.Admin;
import project.jinshang.mod_product.service.MemberOperateLogService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/rest/admin/Display")
@SessionAttributes({AppConstant.ADMIN_SESSION_NAME})
@Api(tags = "后台展示类目", description = "展示类目相关接口")
public class DisplayAction {

    @Autowired
    private DisplayService displayService;

    @Autowired
    MemberLogOperator memberLogOperator;

    @Autowired
    MemberOperateLogService memberOperateLogService;


    @RequestMapping(value = "/addDisplay", method = RequestMethod.POST)
    @ApiOperation(value = "类目添加")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "praentid", value = "上一级ID{最大分类为0}", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "dpclass", value = "分类名称", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "dpurl", value = "url", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "dpsuperior", value = "上级分类名称{不填表示为该类的最大分类}", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "adadvert", value = "选择广告", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "isshow", value = "是否显示广告{0:不显示;1显示:}", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "dpicon", value = "分类图标", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "dpsort", value = "排序", required = true, paramType = "query", dataType = "int"),
    })
    public BasicRet addDisplay(Display display, Model model, HttpServletRequest request) {
        Admin admin = (Admin) model.asMap().get(AppConstant.ADMIN_SESSION_NAME);
        BasicRet basicRet = new BasicRet();
        displayService.addDisplay(display);
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("标签添加成功");
        memberLogOperator.saveMemberLog(null, admin, "添加展示类目:" + display.getDpclass(), request, memberOperateLogService);
        return basicRet;
    }

    @RequestMapping(value = "/deleteDisplay", method = RequestMethod.POST)
    @ApiOperation(value = "删除类目")
    public BasicRet deleteLable(@RequestParam(required = true) long id, Model model, HttpServletRequest request) {
        Admin admin = (Admin) model.asMap().get(AppConstant.ADMIN_SESSION_NAME);
        BasicRet basicRet = new BasicRet();
        int Count = displayService.selectCountByPrarentid(id);
        if (Count != 0) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("该分类下还有子类，不能删除！");
        } else {
            Display display = displayService.selectByDisPlayId(id);
            if (display == null) {
                basicRet.setResult(BasicRet.ERR);
                basicRet.setMessage("不存在改分类");
                return basicRet;
            }
            displayService.deleteDisplay(id);
            basicRet.setResult(BasicRet.SUCCESS);
            basicRet.setMessage("标签删除成功");
            memberLogOperator.saveMemberLog(null, admin, "删除展示类目:" + display.getDpclass(), request, memberOperateLogService);
        }
        return basicRet;

    }

    @RequestMapping(value = "/updateDisplay", method = RequestMethod.POST)
    @ApiOperation("修改类目")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "ID", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "praentid", value = "上一级ID{最大分类为0}", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "dpclass", value = "分类名称", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "dpurl", value = "url", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "dpsuperior", value = "上级分类名称{不填表示为该类的最大分类}", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "adadvert", value = "选着广告", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "isshow", value = "是否显示广告{0:不显示;1显示:}", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "dpicon", value = "分类图标", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "dpsort", value = "排序", required = true, paramType = "query", dataType = "int"),
    })
    public BasicRet updateDisplay(Display display, Model model, HttpServletRequest request) {
        Admin admin = (Admin) model.asMap().get(AppConstant.ADMIN_SESSION_NAME);
        BasicRet basicRet = new BasicRet();
        displayService.updateDisplay(display);
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("标签修改成功");
        memberLogOperator.saveMemberLog(null, admin, "修改展示类目:" + display.getDpclass(), request, memberOperateLogService);
        return basicRet;
    }

    @RequestMapping(value = "/listAllDisplay", method = RequestMethod.POST)
    @ApiOperation("列出所有类目列表")
    public List<Display> listDisplay() {

        return displayService.listAllDisplay();

    }
}
