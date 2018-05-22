package project.jinshang.mod_admin.mod_pdlabel;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import mizuki.project.core.restserver.config.BasicRet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.jinshang.common.bean.PageRet;
import project.jinshang.mod_admin.mod_pdlabel.bean.Pdlabel;
import project.jinshang.mod_admin.mod_pdlabel.service.PdlabelService;


import java.util.List;

@RestController
@Api(tags = "后台标签管理",description = "标签管理相关接口")
@RequestMapping(value = "/rest/admin/Pdlable")
public class PdlabelAction {
    @Autowired
    private PdlabelService pdlabelService;

    @RequestMapping(value = "/addPdlable",method = RequestMethod.POST)
    @ApiOperation(value = "添加标签")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "labelname",value = "商品栏位名称",dataType = "String",paramType = "query",required = true),
            @ApiImplicitParam(name = "recommend",value = "推荐商品栏目描述",dataType = "String",paramType = "query",required = true),
            @ApiImplicitParam(name = "isuse",value = "是否启用{0:不启用,1:启用}",dataType = "int",paramType = "query",required = true),
    })
    public BasicRet addPdlable(Pdlabel pdlabel){
        BasicRet basicRet=new BasicRet();
        pdlabelService.addPdlabel(pdlabel);
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("添加成功");
        return  basicRet;
    }

    @RequestMapping(value = "/deletePdlable",method = RequestMethod.POST)
    @ApiOperation("删除标签")
    public  BasicRet deletePdlable(@RequestParam(required = true) long id){
        BasicRet basicRet=new BasicRet();
        pdlabelService.deletePdlable(id);
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("删除成功");
        return basicRet;
    }


    @RequestMapping(value = "/updatePdlable",method = RequestMethod.POST)
    @ApiOperation("修改标签")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "ID",dataType = "int",paramType = "query",required = true),
            @ApiImplicitParam(name = "labelname",value = "商品栏位名称",dataType = "String",paramType = "query",required = true),
            @ApiImplicitParam(name = "recommend",value = "推荐商品栏目描述",dataType = "String",paramType = "query",required = true),
            @ApiImplicitParam(name = "isuse",value = "是否启用{0:不启用,1:启用}",dataType = "int",paramType = "query",required = true),
    })
    public  BasicRet updatePdlable(Pdlabel pdlabel){
        BasicRet basicRet= new BasicRet();
        pdlabelService.updatePdlable(pdlabel);
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("修改成功");
        return basicRet;
    }
    @RequestMapping(value = "/listPdlable",method = RequestMethod.POST)
    @ApiOperation("标签列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "labelname",value = "商品栏位名称",dataType = "string",paramType = "query",required = false),
    })
    public PageRet listPdlabele(@RequestParam(required = true,defaultValue = "1") int pageNo,
                                   @RequestParam(required = false,defaultValue = "20") int pageSize,
                                   @RequestParam(required = false,defaultValue = "") String labelname){

        PageRet pageRet =new PageRet();

        Pdlabel pdlabel = new Pdlabel();
        pdlabel.setLabelname(labelname);
        PageInfo pageInfo = pdlabelService.list(pageNo,pageSize,pdlabel);

        pageRet.data.setPageInfo(pageInfo);
        pageRet.setResult(BasicRet.SUCCESS);
        return  pageRet;
    }




}
