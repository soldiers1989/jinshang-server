package project.jinshang.mod_admin.mod_returnreason;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import mizuki.project.core.restserver.config.BasicRet;
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
import project.jinshang.mod_admin.mod_returnreason.bean.ReturnReason;
import project.jinshang.mod_admin.mod_returnreason.bean.ReturnReasonExample;
import project.jinshang.mod_admin.mod_returnreason.service.ReturnReasonService;
import project.jinshang.mod_common.bean.BasicExtRet;
import project.jinshang.mod_member.bean.Admin;
import java.util.Date;


@RestController
@RequestMapping(value = "/rest/admin/returnreason")
@SessionAttributes({AppConstant.ADMIN_SESSION_NAME})
@Api(tags = "后台退货原因和违约金比例", description = "退货原因和违约金比例接口")
public class ReturnReasonAction {
    @Autowired
    private ReturnReasonService returnReasonService;


    @PostMapping(value = "/insert")
    @ApiOperation(value = "新增退货原因")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "returnreason", value = "退货原因", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "responsibility", value = "责任方1.买方 2.卖方", required = true, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "penalty", value = "违约金比例", required = true, paramType = "query", dataType = "decimal"),
            @ApiImplicitParam(name = "remark", value = "备注", required = false, paramType = "query", dataType = "string"),
    })
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.RETURNREASONS + "')")
    public BasicRet insertReturnReasonInfo(ReturnReason returnReason, Model model) {
        BasicRet basicRet = new BasicRet();
        Admin admin = (Admin) model.asMap().get(AppConstant.ADMIN_SESSION_NAME);
        ReturnReasonExample example = new ReturnReasonExample();
        ReturnReasonExample.Criteria criteria = example.createCriteria();
        criteria.andReturnreasonEqualTo(returnReason.getReturnreason());
        long count = returnReasonService.countByExample(example);
        if (count > 0) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("新增失败,退货原因已存在");
            return basicRet;
        }
        returnReason.setAdminid(admin.getId());
        returnReason.setCreatetime(new Date());
        returnReason.setUpdatetime(new Date());
        returnReasonService.insertReturnReasonInfo(returnReason);
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("新增成功");
        return basicRet;
    }


    @PostMapping(value = "/getDetailById")
    @ApiOperation(value = "获取退货/退款原因")
    @ApiImplicitParam(name = "id", value = "退货/退款原因Id", required = true, paramType = "query", dataType = "long")
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.RETURNREASONS + "')")
    public BasicRet getDetailById(Long id) {
        BasicExtRet basicRet = new BasicExtRet();
        ReturnReason returnReason = returnReasonService.getDetailById(id);
        if (returnReason == null) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("不存在该退货原因");
        } else {
            basicRet.setResult(BasicRet.SUCCESS);
            basicRet.setMessage("获取成功");
            basicRet.setData(returnReason);
        }
        return basicRet;
    }


    @PostMapping(value = "/update")
    @ApiOperation(value = "修改退货/退款原因")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "退货/退款原因ID", required = true, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "returnreason", value = "退货/退款原因", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "responsibility", value = "责任方1.买方 2.卖方", required = false, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "penalty", value = "违约金比例", required = false, paramType = "query", dataType = "decimal"),
            @ApiImplicitParam(name = "remark", value = "备注", required = false, paramType = "query", dataType = "string"),
    })
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.RETURNREASONS + "')")
    public BasicRet updateReturnReasonInfo(ReturnReason returnReason,Model model) {
        BasicRet basicRet = new BasicRet();
        Admin admin = (Admin) model.asMap().get(AppConstant.ADMIN_SESSION_NAME);
        ReturnReason oldReturnReason = returnReasonService.getDetailById(returnReason.getId());
        if (oldReturnReason == null) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("不存在该退货/退款原因");
        } else {
            returnReason.setUpdatetime(new Date());
            returnReasonService.updateReturnReasonInfo(returnReason);
            basicRet.setResult(BasicRet.SUCCESS);
            basicRet.setMessage("修改成功");
        }
        return basicRet;
    }



    @PostMapping(value = "/delete")
    @ApiOperation(value = "删除退货/退款原因")
    @ApiImplicitParam(name = "id", value = "退货/退款原因Id", required = true, paramType = "query", dataType = "long")
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.RETURNREASONS + "')")
    public BasicRet deleteReturnReasonById(Long id) {
        BasicRet basicRet = new BasicRet();
        ReturnReason oldReturnReason = returnReasonService.getDetailById(id);
        if (oldReturnReason == null) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("不存在该退货/退款原因");
        } else {
            returnReasonService.deleteReturnReasonById(id);
            basicRet.setResult(BasicRet.SUCCESS);
            basicRet.setMessage("删除成功");
        }
        return basicRet;
    }

    @PostMapping("/list")
    @ApiOperation(value = "退货/退款原因列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "returnreason", value = "退货/退款原因", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "pageNo", value = "页码(值为-1不分页)", required = true, paramType = "query", dataType = "int", defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页数", required = false, paramType = "query", dataType = "int", defaultValue = "20"),
    })
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.RETURNREASONS + "')")
    public PageRet getList(int pageNo, int pageSize,ReturnReason returnReason) {
        PageInfo pageInfo = returnReasonService.getListByPage(pageNo, pageSize,returnReason);
        PageRet pageRet = new PageRet();
        pageRet.data.setPageInfo(pageInfo);
        pageRet.setMessage("查询成功");
        pageRet.setResult(BasicRet.SUCCESS);
        return pageRet;
    }



}
