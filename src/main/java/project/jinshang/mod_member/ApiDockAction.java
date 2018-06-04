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
import project.jinshang.common.utils.DateUtils;
import project.jinshang.mod_member.bean.dto.ApiRecordQueryDto;
import project.jinshang.mod_member.bean.dto.ApiRecordViewDto;
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
        if(dto.getEndCreateTime() != null) {
//            queryMap.put("tradetimeEnd", DateUtils.addDays(tradetimeEnd,1));
            dto.setEndCreateTime(DateUtils.addDays(dto.getEndCreateTime(),1));
        }
        PageInfo pageInfo=apiRecordService.apiRecordlist(pageNo,pageSize,dto);
        pageRet.data.setPageInfo(pageInfo);
        pageRet.setMessage("查询成功");
        pageRet.setResult(BasicRet.SUCCESS);
        return pageRet;
    }

    @RequestMapping(value = "/getApiRecordDetailById",method = RequestMethod.POST)
    @ApiOperation(value = "根据对接记录的id查询对接记录的详情")
    public ApiRecordRet getApiRecordDetailById(@RequestParam(required = true) Long id){
        ApiRecordRet apiRecordRet=new ApiRecordRet();
        ApiRecordViewDto apiRecordViewDto=apiRecordService.getApiRecordById(id);
        apiRecordRet.setMessage("查询成功");
        apiRecordRet.setResult(BasicRet.SUCCESS);
        apiRecordRet.data.setApiRecordViewDto(apiRecordViewDto);
        return apiRecordRet;
    }

    private class ApiRecordRet extends BasicRet{
        private class Data{
            private ApiRecordViewDto apiRecordViewDto;

            public ApiRecordViewDto getApiRecordViewDto() {
                return apiRecordViewDto;
            }

            public void setApiRecordViewDto(ApiRecordViewDto apiRecordViewDto) {
                this.apiRecordViewDto = apiRecordViewDto;
            }
        }
        private Data data=new Data();

        public Data getData() {
            return data;
        }

        public void setData(Data data) {
            this.data = data;
        }
    }
}
