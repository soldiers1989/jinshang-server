package project.jinshang.mod_techplatform;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import project.jinshang.common.constant.AppConstant;

/**
 * 紧商网技术开放平台相应接口和调用函数
 *
 * @author xiazy
 * @create 2018-06-02 16:16
 **/
@RestController
@RequestMapping("/api")
@Api(tags = "紧商网技术开放平台相应接口和调用函数")
@Transactional(rollbackFor = Exception.class)
public class ApiTecPlatformAction {

    @RequestMapping(value = "/apiRecordList",method = RequestMethod.POST)
    @ApiOperation(value = "接口对接记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startCreateTime",value = "对接查询开始时间",required = false,paramType = "query",dataType = "date"),
            @ApiImplicitParam(name = "endCreateTime",value = "对接查询结束时间",required = false,paramType = "query",dataType = "date"),
            @ApiImplicitParam(name = "appId",value = "对接ID",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "apiCode",value = "对接接口",required = false,paramType = "query",dataType = "string"),
    })
    public String remoteInvoke(){
        return null;
    }



}
