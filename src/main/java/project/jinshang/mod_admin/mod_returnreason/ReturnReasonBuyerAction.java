package project.jinshang.mod_admin.mod_returnreason;

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
import project.jinshang.mod_admin.mod_returnreason.bean.ReturnReason;
import project.jinshang.mod_admin.mod_returnreason.service.ReturnReasonService;

@RestController
@RequestMapping(value = "/rest/buyer/returnreason")
@SessionAttributes({AppConstant.MEMBER_SESSION_NAME})
@Api(tags = "买家退货原因", description = "买家退货原因接口")
public class ReturnReasonBuyerAction {


    @Autowired
    private ReturnReasonService returnReasonService;
    @PostMapping("/list")
    @ApiOperation(value = "退货/退款原因列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "returnreason", value = "退货/退款原因", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "pageNo", value = "页码(值为-1不分页)", required = false, paramType = "query", dataType = "int", defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页数", required = false, paramType = "query", dataType = "int", defaultValue = "20"),
    })
    public PageRet getList(int pageNo, int pageSize, ReturnReason returnReason) {
        PageInfo pageInfo = returnReasonService.getListByPage1(pageNo, pageSize,returnReason);
        PageRet pageRet = new PageRet();
        pageRet.data.setPageInfo(pageInfo);
        pageRet.setMessage("查询成功");
        pageRet.setResult(BasicRet.SUCCESS);
        return pageRet;
    }


}
