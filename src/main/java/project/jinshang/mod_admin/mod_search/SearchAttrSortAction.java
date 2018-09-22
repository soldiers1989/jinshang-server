package project.jinshang.mod_admin.mod_search;

import io.swagger.annotations.*;
import mizuki.project.core.restserver.config.BasicRet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import project.jinshang.common.bean.PageRet;
import project.jinshang.common.constant.AdminAuthorityConst;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.common.utils.ProductSearchSortUtils;
import project.jinshang.mod_product.bean.SearchAttrSort;
import project.jinshang.mod_product.bean.SearchAttrSortValue;
import project.jinshang.mod_product.bean.SearchAttrSortValueExample;
import project.jinshang.mod_product.service.SearchAttrSortService;
import project.jinshang.mod_product.service.SearchAttrSortValueService;

@RestController
@Api(tags = "后台产品搜索排序接口", description = "产品相关接口")
@RequestMapping("/rest/admin/searchattrsort")
@Transactional(rollbackFor = Exception.class)
@SessionAttributes({AppConstant.ADMIN_SESSION_NAME})
public class SearchAttrSortAction {

    @Autowired
    private SearchAttrSortService searchAttrSortService;

    @Autowired
    private SearchAttrSortValueService searchAttrSortValueService;


    @PostMapping("/addSearchSort")
    @ApiOperation("添加搜索排序")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "名称", name = "sortname", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "类型 0=紧固件，1=工业品", name = "type", required = true, dataType = "int", paramType = "query"),
    })
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.SEARCHSORTMANAGEMENT + "')")
    public BasicRet addSearchSort(@RequestParam(required = true) String sortname,@RequestParam(required = true) Short type){

        int count = searchAttrSortService.getCountBySortnamType(sortname,type);
        if(count>0){
            return new BasicRet(BasicRet.ERR,"已存在");
        }

        SearchAttrSort sort = new SearchAttrSort();
        sort.setSortname(sortname);
        sort.setType(type);
        searchAttrSortService.insertSelective(sort);

        return new BasicRet(BasicRet.SUCCESS,"添加成功");
    }


    @PostMapping("/delSearchSort")
    @ApiOperation("删除搜索排序")
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.SEARCHSORTMANAGEMENT + "')")
    public BasicRet delSearchSort(@RequestParam(required = true) Long id){
        SearchAttrSort sort = searchAttrSortService.getById(id);
        if(sort == null){
            return new BasicRet(BasicRet.ERR,"搜索排序不存在");
        }
        searchAttrSortService.delById(id);
        ProductSearchSortUtils.delete(sort.getSortname(),sort.getType());
        return new BasicRet(BasicRet.SUCCESS,"删除成功");
    }


    @PostMapping("/selectByPage")
    @ApiOperation("分页列表")
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.SEARCHSORTMANAGEMENT + "')")
    public PageRet selectByPage(@RequestParam(required = true) int pageNo,@RequestParam(defaultValue = "10") int pageSize){
        PageRet pageRet = new PageRet();
        pageRet.getData().setPageInfo(searchAttrSortService.getByPage(pageNo,pageSize));
        pageRet.setResult(BasicRet.SUCCESS);
        return pageRet;
    }


    @PostMapping("/selectValueByPage")
    @ApiOperation("搜索值分页显示列表")
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.SEARCHSORTMANAGEMENT + "')")
    public PageRet selectValueByPage(@RequestParam(required = true) Long searchAttrId,
                                     @RequestParam(required = true) int pageNo,
                                     @RequestParam(defaultValue = "10") int pageSize){
        PageRet pageRet = new PageRet();
        pageRet.getData().setPageInfo(searchAttrSortValueService.getBySearchAttrId(searchAttrId,pageNo,pageSize));
        pageRet.setResult(BasicRet.SUCCESS);
        return pageRet;
    }


    @PostMapping("/addSearchSortValue")
    @ApiOperation("添加排序值")
//    @ApiImplicitParams({
//            @ApiImplicitParam(value = "", name = "searchAttrId", required = true, dataType = "string", paramType = "query"),
//            @ApiImplicitParam(value = "类型 0=紧固件，1=工业品", name = "key", required = true, dataType = "int", paramType = "query"),
//            @ApiImplicitParam(value = "类型 0=紧固件，1=工业品", name = "weight", required = true, dataType = "int", paramType = "query"),
//    })
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.SEARCHSORTMANAGEMENT + "')")
    public BasicRet addSearchSortValue(@RequestParam(required = true) Long searchAttrId,
                                       @RequestParam(required = true) String key,
                                       @RequestParam(required = true) Integer weight){
        SearchAttrSort searchAttrSort = searchAttrSortService.getById(searchAttrId);
        if(searchAttrSort == null){
            return new BasicRet(BasicRet.ERR,"搜索排序条目不存在");
        }

        SearchAttrSortValueExample example = new SearchAttrSortValueExample();
        SearchAttrSortValueExample.Criteria criteria = example.createCriteria();
        criteria.andSearchAttrIdEqualTo(searchAttrId).andKeyEqualTo(key);

        if(searchAttrSortValueService.countByExample(example)>0){
            return new BasicRet(BasicRet.ERR,"该排序值已设置过了");
        }

        SearchAttrSortValue value = new SearchAttrSortValue();
        value.setSearchAttrId(searchAttrId);
        value.setKey(key);
        value.setWeight(weight);
        searchAttrSortValueService.insertSelective(value);

        ProductSearchSortUtils.addKeySort(searchAttrSort.getSortname(),searchAttrSort.getType(),key,weight);
        return new BasicRet(BasicRet.SUCCESS,"添加成功");
    }


    @PostMapping("/delSearchSortValue")
    @ApiOperation("删除排序值")
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.SEARCHSORTMANAGEMENT + "')")
    public BasicRet delSearchSortValue(@RequestParam(required = true) long id){
        SearchAttrSortValue value = searchAttrSortValueService.getById(id);
        if(value == null){
            return new BasicRet(BasicRet.ERR,"该搜索词不存在");
        }

        SearchAttrSort searchAttrSort = searchAttrSortService.getById(value.getSearchAttrId());
        if(searchAttrSort != null){
            ProductSearchSortUtils.deleteKeySort(searchAttrSort.getSortname(),searchAttrSort.getType(),value.getKey());
        }

        searchAttrSortValueService.deleteById(id);
        return new BasicRet(BasicRet.SUCCESS,"删除成功");
    }



}
