package project.jinshang.mod_admin.mod_commondata;

import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import mizuki.project.core.restserver.config.BasicRet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.jinshang.common.bean.Page;
import project.jinshang.common.bean.PageRet;
import project.jinshang.common.constant.AdminAuthorityConst;
import project.jinshang.common.utils.CommonUtils;
import project.jinshang.common.utils.StringUtils;
import project.jinshang.mod_admin.mod_commondata.bean.CommonData;
import project.jinshang.mod_admin.mod_commondata.bean.CommonDataValue;
import project.jinshang.mod_admin.mod_commondata.service.CommonDataService;
import project.jinshang.mod_admin.mod_commondata.service.CommonDataValueService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * create : wyh
 * date : 2017/12/26
 */
@RestController
@RequestMapping("/rest/admin/commondata")
@Api(tags = "后台公共数据接口")
@Transactional(rollbackFor = Exception.class)
public class CommonDataAction {

    @Autowired
    private CommonDataService commonDataService;

    @Autowired
    private CommonDataValueService commonDataValueService;



    @PostMapping("/updateValue")
    @ApiOperation("修改公共数据值")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "id",name = "id",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(value = "值",name = "value",required = true,paramType = "query",dataType = "string"),
            @ApiImplicitParam(value = "序号",name = "sort",required = true,paramType = "query",dataType = "string"),
    })
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.PUBLICDATAMANAGEMENT + "')")
    public  BasicRet updateValue(@RequestParam(required = true) long id,@RequestParam(required = true) String value,
                              @RequestParam(required = true) int sort){
        BasicRet basicRet =  new BasicRet();

        CommonDataValue commonDataValue = commonDataValueService.getById(id);

        if(commonDataValue == null){
            return  new BasicRet(BasicRet.ERR,"不存在该值");
        }

        commonDataValue.setValue(value);
        commonDataValue.setSort(sort);
        commonDataValue.setId(id);
        commonDataValueService.updateByPrimaryKey(commonDataValue);
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("添加成功");
        return  basicRet;
    }




    @PostMapping("/addValue")
    @ApiOperation("添加公共数据值")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "公共数据id",name = "dataid",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(value = "值",name = "value",required = true,paramType = "query",dataType = "string"),
            @ApiImplicitParam(value = "序号",name = "sort",required = true,paramType = "query",dataType = "string"),
    })
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.PUBLICDATAMANAGEMENT + "')")
    public  BasicRet addValue(@RequestParam(required = true) long dataid,@RequestParam(required = true) String value,
                              @RequestParam(required = true) int sort){
        BasicRet basicRet =  new BasicRet();

        CommonDataValue commonDataValue = new CommonDataValue();
        commonDataValue.setValue(value);
        commonDataValue.setSort(sort);
        commonDataValue.setDataid(dataid);
        commonDataValueService.insertSelective(commonDataValue);
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("添加成功");
        return  basicRet;
    }





    @PostMapping("/deleteValue")
    @ApiOperation("删除公共数据值")
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.PUBLICDATAMANAGEMENT + "')")
    public BasicRet deleteValue(long id){
        BasicRet basicRet =  new BasicRet();
        commonDataValueService.deleteById(id);
        basicRet.setMessage("删除成功");
        basicRet.setResult(BasicRet.SUCCESS);
        return  basicRet;
    }




    @PostMapping("/detailData")
    @ApiOperation("公共数据详情")
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.PUBLICDATAMANAGEMENT + "')")
    public  CommonDataDetailRet detailData(long id){
        CommonDataDetailRet commonDataDetailRet =  new CommonDataDetailRet();

        CommonData commonData = commonDataService.getById(id);
        if(commonData == null){
            commonDataDetailRet.setMessage("公共数据不存在");
            commonDataDetailRet.setResult(BasicRet.ERR);
            return  commonDataDetailRet;
        }

        List<CommonDataValue> commonDataValues = commonDataValueService.getValueByDataid(id);

        commonDataDetailRet.commonData =  commonData;
        commonDataDetailRet.commonDataValues =  commonDataValues;
        commonDataDetailRet.setResult(BasicRet.SUCCESS);
        commonDataDetailRet.setMessage("查询成功");
        return  commonDataDetailRet;
    }



    private  class  CommonDataDetailRet extends  BasicRet{
        private  CommonData commonData;
        private  List<CommonDataValue> commonDataValues;

        public CommonData getCommonData() {
            return commonData;
        }

        public void setCommonData(CommonData commonData) {
            this.commonData = commonData;
        }

        public List<CommonDataValue> getCommonDataValues() {
            return commonDataValues;
        }

        public void setCommonDataValues(List<CommonDataValue> commonDataValues) {
            this.commonDataValues = commonDataValues;
        }
    }




    @PostMapping("/delData")
    @ApiOperation("删除公共数据")
    @ApiImplicitParams({

    })
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.PUBLICDATAMANAGEMENT + "')")
    public BasicRet delData(@RequestParam(required = true) long id){
        BasicRet basicRet =  new BasicRet();
        commonDataValueService.deleteByDataId(id);
        commonDataService.deleteById(id);
        return  new BasicRet(BasicRet.SUCCESS,"删除成功");
    }


    @PostMapping("/listData")
    @ApiOperation("公共数据列表")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "名称",name = "name",required = false,paramType = "query",dataType = "string"),
    })
    public PageRet listData(@RequestParam(required = true,defaultValue = "1") int pageNo,
                            @RequestParam(required = true,defaultValue = "20") int pageSize,
                            @RequestParam(required = false) String name){

        PageRet pageRet = new PageRet();
        PageInfo pageInfo =  commonDataService.listByPage(name,pageNo,pageSize);
        pageRet.data.setPageInfo(pageInfo);
        pageRet.setResult(BasicRet.SUCCESS);
        return  pageRet;
    }




    @PostMapping("/updateDate")
    @ApiOperation("修改公共数据")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "名称", name = "name", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(value = "备注", name = "mark", required = true, paramType = "query", dataType = "string"),
    })
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.PUBLICDATAMANAGEMENT + "')")
    public BasicRet updateData(
                            @RequestParam(required = true) long id,
                            @RequestParam(required = true) String name,
                            @RequestParam(required = true,defaultValue = "") String mark) {
        BasicRet basicRet = new BasicRet();

        CommonData commonData = commonDataService.getByName(name);
        if (commonData != null && !commonData.getId().equals(id)) {
            return new BasicRet(BasicRet.ERR, "已经存在该名称的公共数据了");
        }

        commonData = new CommonData();
        commonData.setId(id);
        commonData.setName(name);
        commonData.setMark(mark);
        commonDataService.updateByPrimaryKeySelective(commonData);

        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("修改成功");
        return  basicRet;
    }


    @PostMapping("/addData")
    @ApiOperation("添加公共数据")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "名称",name = "name",required = true,paramType = "query",dataType = "string"),
            @ApiImplicitParam(value = "备注",name = "mark",required = true,paramType = "query",dataType = "string"),
            @ApiImplicitParam(value = "数据值json [{\"value\":\"10\",\"sort\":1},{\"value\":\"20\",\"sort\":2}]",name = "valueJson",required = true,paramType = "query",dataType = "string"),
    })
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.PUBLICDATAMANAGEMENT + "')")
    public BasicRet addData(@RequestParam(required = true) String name,
                       @RequestParam(required = true) String mark,
                       @RequestParam(required = true) String valueJson){
        BasicRet basicRet = new BasicRet();

        CommonData commonData =  commonDataService.getByName(name);
        if(commonData != null){
            return  new BasicRet(BasicRet.ERR,"已经存在该名称的公共数据了");
        }

        if(!CommonUtils.isGoodJson(valueJson)){
            return  new BasicRet(BasicRet.ERR,"json数据不合法");
        }

        Gson gson = new Gson();
        List<CommonDataValue> valueList =  gson.fromJson(valueJson,new TypeToken<ArrayList<CommonDataValue>>() {}.getType());

        commonData = new CommonData();
        commonData.setMark(mark);
        commonData.setName(name);
        commonDataService.insertSelective(commonData);

        if(valueList != null && valueList.size()>0){
            for(CommonDataValue value : valueList){
                if(!StringUtils.hasText(value.getValue())) continue;
                value.setDataid(commonData.getId());
                value.setUpdatetime(new Date());

                commonDataValueService.insertSelective(value);
            }
        }

        return  new BasicRet(BasicRet.SUCCESS,"添加成功");
    }


//    public static void main(String[] args) {
//        List<CommonDataValue> list =  new ArrayList<>();
//        CommonDataValue commonDataValue = new CommonDataValue();
//        commonDataValue.setSort(1);
//        commonDataValue.setValue("10");
//        list.add(commonDataValue);
//        Gson gson =  new Gson();
//        System.out.println(gson.toJson(list));
//    }

}
