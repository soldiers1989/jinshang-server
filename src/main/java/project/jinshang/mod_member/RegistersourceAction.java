package project.jinshang.mod_member;


import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import mizuki.project.core.restserver.config.BasicRet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import project.jinshang.common.bean.PageRet;
import project.jinshang.common.constant.AdminAuthorityConst;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.mod_common.bean.BasicExtRet;
import project.jinshang.mod_fx.bean.Fxstation;
import project.jinshang.mod_member.bean.Registersource;
import project.jinshang.mod_member.bean.RegistersourceExample;
import project.jinshang.mod_member.service.RegistersourceService;

@RestController
@RequestMapping("/rest/admin/registersource")
@SessionAttributes({AppConstant.ADMIN_SESSION_NAME})
@Api(tags = "后台注册来源",description = "后台注册来源相关接口")
@Transactional(rollbackFor = Exception.class)
public class RegistersourceAction {

    @Autowired
    private RegistersourceService registersourceService;


    @PostMapping(value = "/list")
    @ApiOperation(value = "注册信息来源列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "lable", value = "标识", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "labelname", value = "标识名称", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "type", value = "类型：1.注册来源 2.注册类型 3.注册渠道", required = false, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "pageNo", value = "页码(值为-1不分页)", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "页数", required = false, paramType = "query", dataType = "int", defaultValue = "20"),
    })
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.REGISTRATIONMODEL + "')")
    public PageRet getList(int pageNo, int pageSize, Registersource registersource) {
        PageRet pageRet = new PageRet();
        PageInfo pageInfo = registersourceService.selectAll(pageNo,pageSize,registersource);
        pageRet.data.setPageInfo(pageInfo);
        pageRet.setResult(BasicRet.SUCCESS);
        pageRet.setMessage("获取成功");
        return pageRet;
    }

    @PostMapping(value = "/list1")
    @ApiOperation(value = "注册信息来源列表/不需要权限的")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "lable", value = "标识", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "labelname", value = "标识名称", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "type", value = "类型：1.注册来源 2.注册类型 3.注册渠道", required = false, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "pageNo", value = "页码(值为-1不分页)", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "页数", required = false, paramType = "query", dataType = "int", defaultValue = "20"),
    })
    public PageRet getList1(int pageNo, int pageSize, Registersource registersource) {
        PageRet pageRet = new PageRet();
        PageInfo pageInfo = registersourceService.selectAll(pageNo,pageSize,registersource);
        pageRet.data.setPageInfo(pageInfo);
        pageRet.setResult(BasicRet.SUCCESS);
        pageRet.setMessage("获取成功");
        return pageRet;
    }


    @PostMapping(value = "/registerlist")
    @ApiOperation(value = "注册来源列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "页码(值为-1不分页)", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "页数", required = false, paramType = "query", dataType = "int", defaultValue = "20"),
    })
    public PageRet getRegisterList(int pageNo, int pageSize) {
        PageRet pageRet = new PageRet();
        Registersource registersource = new Registersource();
        //1.注册来源
        Short type = 1;
        registersource.setType(type);
        PageInfo pageInfo = registersourceService.selectAll(pageNo,pageSize,registersource);
        pageRet.data.setPageInfo(pageInfo);
        pageRet.setResult(BasicRet.SUCCESS);
        pageRet.setMessage("获取成功");
        return pageRet;
    }


    @PostMapping(value = "/insert")
    @ApiOperation(value = "新增注册")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "label", value = "标识", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "labelname", value = "标识名称", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "type", value = "类型：1.注册来源 2.注册类型 3.注册渠道", required = true, paramType = "query", dataType = "string"),
    })
    public BasicRet insertRegistersourceInfo(Registersource registersource) {
        BasicRet basicRet = new BasicRet();

        RegistersourceExample registersourceExample = new RegistersourceExample();
        RegistersourceExample.Criteria criteria = registersourceExample.createCriteria();
        criteria.andTypeEqualTo(registersource.getType()).andLabelEqualTo(registersource.getLabel());
        long count = registersourceService.countByExample(registersourceExample);
        if (count > 0) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("新增失败,标识已存在且为同一个标识类型");
            return basicRet;
        }

        RegistersourceExample registersourceExample1 = new RegistersourceExample();
        RegistersourceExample.Criteria criteria1 = registersourceExample1.createCriteria();
        criteria1.andTypeEqualTo(registersource.getType()).andLabelnameEqualTo(registersource.getLabelname());
        long count1 = registersourceService.countByExample(registersourceExample1);
        if (count1 > 0) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("新增失败,标识名称已存在且为同一个标识类型");
            return basicRet;
        }


        RegistersourceExample registersourceExample2 = new RegistersourceExample();
        RegistersourceExample.Criteria criteria2 = registersourceExample2.createCriteria();
        criteria2.andTypeEqualTo(registersource.getType()).andLabelnameEqualTo(registersource.getLabelname()).andLabelEqualTo(registersource.getLabel());
        long count2 = registersourceService.countByExample(registersourceExample2);
        if (count2 > 0) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("新增失败,标识名称已存在且为同一个标识类型同一个标识");
            return basicRet;
        }


        registersourceService.insertRegistersourceInfo(registersource);
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("新增成功");
        return basicRet;
    }


    @PostMapping(value = "/get")
    @ApiOperation(value = "获取注册来源信息详情")
    @ApiImplicitParam(name = "id", value = "注册来源信息id", required = true, paramType = "query", dataType = "Long")
    public BasicRet get(long id) {
        BasicExtRet basicRet = new BasicExtRet();
        Registersource registersource = registersourceService.selectById(id);
        if (registersource == null) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("不存在该注册来源信息");
            return basicRet;
        }
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("获取成功");
        basicRet.setData(registersource);
        return basicRet;
    }


    @PostMapping(value = "/update")
    @ApiOperation(value = "修改注册来源信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "注册来源信息ID", required = true, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "lable", value = "标识", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "labelname", value = "标识名称", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "type", value = "类型：1.注册来源 2.注册类型 3.注册渠道", required = false, paramType = "query", dataType = "long"),
    })
    public BasicRet updateRegistersourceInfo(Registersource registersource) {
        BasicRet basicRet = new BasicRet();
        Registersource oldRegistersource = registersourceService.selectById(registersource.getId());
        if (oldRegistersource == null) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("不存在该注册来源信息");
        } else {
            registersourceService.updateRegistersourceInfo(registersource);
            basicRet.setResult(BasicRet.SUCCESS);
            basicRet.setMessage("修改成功");
        }
        return basicRet;
    }











}
