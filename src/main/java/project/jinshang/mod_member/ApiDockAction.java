package project.jinshang.mod_member;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import mizuki.project.core.restserver.config.BasicRet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.jinshang.common.bean.PageRet;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.mod_member.bean.dto.ApiRecordQueryDto;
import project.jinshang.mod_member.service.ApiRecordService;


/**
 * Api对接Action
 *
 * @author xiazy
 * @create 2018-05-25 11:47
 **/
@RestController
@RequestMapping("/rest/admin/tecplatform")
@Api(tags = "紧商网技术平台")
@SessionAttributes({AppConstant.ADMIN_SESSION_NAME})
@Transactional(rollbackFor = Exception.class)
public class ApiDockAction{


    @Autowired
    public ApiRecordService apiRecordService;
    @RequestMapping(value = "/apiRecordList",method = RequestMethod.POST)
    @ApiOperation(value = "接口对接记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startCreateTime",value = "对接查询开始时间",required = false,paramType = "query",dataType = "date"),
            @ApiImplicitParam(name = "endCreateTime",value = "对接查询结束时间",required = false,paramType = "query",dataType = "date"),
            @ApiImplicitParam(name = "appId",value = "对接ID",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "apiCode",value = "对接接口",required = false,paramType = "query",dataType = "string"),
    })
    public PageRet list(ApiRecordQueryDto dto,
                        @RequestParam(required = true, defaultValue = "1") int pageNo,
                        @RequestParam(required = true, defaultValue = "20") int pageSize,
                        Model model) {
        PageRet pageRet=new PageRet();
        PageInfo pageInfo=apiRecordService.apiRecordlist(pageNo,pageSize,dto);
        pageRet.data.setPageInfo(pageInfo);
        pageRet.setMessage("查询成功");
        pageRet.setResult(BasicRet.SUCCESS);
        return pageRet;
    }
}
