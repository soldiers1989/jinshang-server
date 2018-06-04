package project.jinshang.mod_admin.mod_showcategory;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.swagger.annotations.*;
import mizuki.project.core.restserver.config.BasicRet;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.jinshang.common.bean.MemberLogOperator;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.common.utils.CommonUtils;
import project.jinshang.common.utils.ProductCategoryUtils;
import project.jinshang.common.utils.StringUtils;
import project.jinshang.mod_admin.mod_floor.bean.FloorViewDto;
import project.jinshang.mod_admin.mod_showcategory.bean.ShowCate;
import project.jinshang.mod_admin.mod_showcategory.bean.ShowCatedetail;
import project.jinshang.mod_admin.mod_showcategory.bean.dto.ShowCateView;
import project.jinshang.mod_admin.mod_showcategory.bean.dto.ShowCategory;
import project.jinshang.mod_admin.mod_showcategory.service.ShowCategoryDetailService;
import project.jinshang.mod_admin.mod_showcategory.service.ShowCategoryService;
import project.jinshang.mod_front.bean.ShowCateFrontView;
import project.jinshang.mod_member.bean.Admin;
import project.jinshang.mod_product.bean.Categories;
import project.jinshang.mod_product.service.CategoriesService;
import project.jinshang.mod_product.service.MemberOperateLogService;
import project.jinshang.mod_system.mod_redis.service.RedisCacheService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * create : wyh
 * date : 2018/2/1
 */

@RestController
@RequestMapping("/rest/admin/showcategory")
@SessionAttributes({AppConstant.ADMIN_SESSION_NAME})
@Transactional(rollbackFor = Exception.class)
@Api(tags = "后台展示类目管理（设置首页展示类目）")
public class ShowCategoryAdminAction {

    @Autowired
    private ShowCategoryService showCategoryService;

    @Autowired
    private ShowCategoryDetailService showCategoryDetailService;

    @Autowired
    private CategoriesService categoriesService;

    @Autowired
    MemberLogOperator memberLogOperator;

    @Autowired
    MemberOperateLogService memberOperateLogService;

    @Autowired
    private RedisCacheService redisCacheService;


    @PostMapping("/addcate")
    @ApiOperation("添加展示类目")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "类型", name = "type", required = true, paramType = "query"),
            @ApiImplicitParam(value = "主分类名", name = "maincategory", required = true, paramType = "query"),
            @ApiImplicitParam(value = "排序", name = "sort", required = true, paramType = "query"),
            @ApiImplicitParam(value = "json 例：[{\"level2category\":\"六角螺母\",\"level2id\":\"169\",\"material\":\"不锈钢\",\"categoryids\":\"190,189,191\"},{\"level2category\":\"外六角螺母\",\"level2id\":\"168\",\"material\":\"不锈钢\",\"categoryids\":\"186,187,188\"}]", name = "cateJson", required = true, paramType = "query"),
    })
    public BasicRet addcate(@RequestParam(required = true) String type,
                            @RequestParam(required = true) String maincategory,
                            @RequestParam(required = true) int sort,
                            @RequestParam(required = true) String cateJson,
                            Model model, HttpServletRequest request) {
        Admin admin = (Admin) model.asMap().get(AppConstant.ADMIN_SESSION_NAME);
        if (!"紧固件".equals(type) && !"生活类".equals(type) && !"工业品".equals(type)) {
            return new BasicRet(BasicRet.ERR, "类型错误");
        }

        ShowCate showCate = showCategoryService.getByMainCategory(maincategory);
        if (showCate != null) {
            return new BasicRet(BasicRet.ERR, "主分类名不可重复");
        }


        if (!CommonUtils.isGoodJson(cateJson)) {
            return new BasicRet(BasicRet.ERR, "json格式不正确");
        }
        Gson gson = new Gson();
        List<ShowCatedetail> detailList = gson.fromJson(cateJson, new TypeToken<List<ShowCatedetail>>() {
        }.getType());
        if (detailList == null || detailList.size() == 0) {
            return new BasicRet(BasicRet.ERR, "json数据不可为空");
        }

        for (ShowCatedetail detail : detailList) {
            if ("紧固件".equals(type)) {
                if (detail.getLevel2id() == null || !StringUtils.hasText(detail.getLevel2category()) || !StringUtils.hasText(detail.getMaterial())) {
                    return new BasicRet(BasicRet.ERR, "紧固件分类，二级分类和材质必须选择");
                }
            } else {
                if (!StringUtils.hasText(detail.getLevel2category()) || detail.getLevel2id() == null) {
                    return new BasicRet(BasicRet.ERR, "生活类和工业品分类，二级分类必须填写");
                }
            }


            if (!StringUtils.hasText(detail.getCategoryids())) {
                return new BasicRet(BasicRet.ERR, "末级分类不可为空");
            }
        }

        showCate = new ShowCate();
        showCate.setMaincategory(maincategory);
        showCate.setSort(sort);
        showCate.setType(type);
        showCategoryService.addCate(showCate);

        for (ShowCatedetail detail : detailList) {
            detail.setShowid(showCate.getId());
            showCategoryDetailService.insertSelective(detail);
        }
        memberLogOperator.saveMemberLog(null, admin, "添加展示类目:" + type, request, memberOperateLogService);
        //List<ShowCateFrontView> list = redisCacheService.addShowCate();
        //List<FloorViewDto> dtoList = redisCacheService.addIndexFloor();
        return new BasicRet(BasicRet.SUCCESS, "添加成功");
    }


    @PostMapping("/updateCate")
    @ApiOperation("添加展示类目")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "类型", name = "type", required = true, paramType = "query"),
            @ApiImplicitParam(value = "主分类名", name = "maincategory", required = true, paramType = "query"),
            @ApiImplicitParam(value = "排序", name = "sort", required = true, paramType = "query"),
            @ApiImplicitParam(value = "json 例：[{\"level2category\":\"垫圈\",\"level2id\":\"169\",\"material\":\"不锈钢\",\"categoryids\":\"190,189,191\"},{\"level2category\":\"螺母\",\"level2id\":\"168\",\"material\":\"不锈钢\",\"categoryids\":\"186,187,188\"}]", name = "cateJson", required = true, paramType = "query"),
    })
    public BasicRet updateCate(@RequestParam(required = true) long id,
                               @RequestParam(required = true) String type,
                               @RequestParam(required = true) String maincategory,
                               @RequestParam(required = true) int sort,
                               @RequestParam(required = true) String cateJson,
                               Model model, HttpServletRequest request) {
        Admin admin = (Admin) model.asMap().get(AppConstant.ADMIN_SESSION_NAME);
        if (!"紧固件".equals(type) && !"生活类".equals(type) && !"工业品".equals(type)) {
            return new BasicRet(BasicRet.ERR, "类型错误");
        }

        ShowCate showCate = showCategoryService.getByMainCategory(maincategory);
        if (showCate != null && !showCate.getId().equals(id)) {
            return new BasicRet(BasicRet.ERR, "主分类名不可重复");
        }

        showCate = showCategoryService.getById(id);
        if (showCate == null) {
            return new BasicRet(BasicRet.ERR, "要修改的分类不存在");
        }


        if (!CommonUtils.isGoodJson(cateJson)) {
            return new BasicRet(BasicRet.ERR, "json格式不正确");
        }
        Gson gson = new Gson();
        List<ShowCatedetail> detailList = gson.fromJson(cateJson, new TypeToken<List<ShowCatedetail>>() {
        }.getType());
        if (detailList == null || detailList.size() == 0) {
            return new BasicRet(BasicRet.ERR, "json数据不可为空");
        }

        for (ShowCatedetail detail : detailList) {
            if ("紧固件".equals(type)) {
                if (detail.getLevel2id() == null || !StringUtils.hasText(detail.getLevel2category()) || !StringUtils.hasText(detail.getMaterial())) {
                    return new BasicRet(BasicRet.ERR, "紧固件分类，二级分类和材质必须选择");
                }
            } else {
                if (detail.getLevel2id() == null || !StringUtils.hasText(detail.getLevel2category())) {
                    return new BasicRet(BasicRet.ERR, "其他分类，二级分类必须填写");
                }
            }

            if (!StringUtils.hasText(detail.getCategoryids())) {
                return new BasicRet(BasicRet.ERR, "末级分类不可为空");
            }
            detail.setCategoryids(detail.getCategoryids().replaceAll("，", ","));
        }

        showCate.setMaincategory(maincategory);
        showCate.setSort(sort);
        showCate.setType(type);
        showCategoryService.updateByPrimaryKey(showCate);


        showCategoryDetailService.delByShowId(showCate.getId());

        for (ShowCatedetail detail : detailList) {
            detail.setShowid(showCate.getId());
            showCategoryDetailService.insertSelective(detail);
        }
        memberLogOperator.saveMemberLog(null, admin, "修改展示类目:" + type, request, memberOperateLogService);
        //List<ShowCateFrontView> list = redisCacheService.addShowCate();
        //List<FloorViewDto> dtoList = redisCacheService.addIndexFloor();
        return new BasicRet(BasicRet.SUCCESS, "添加成功");
    }


    @PostMapping("/delCate")
    @ApiOperation("删除展示类目")
    public BasicRet delCate(long id, Model model, HttpServletRequest request) {
        Admin admin = (Admin) model.asMap().get(AppConstant.ADMIN_SESSION_NAME);
        ShowCate showCate = showCategoryService.getById(id);
        if (showCate == null) {
            return new BasicRet(BasicRet.ERR, "没有该展示类目");
        }
        showCategoryService.delCate(id);
        showCategoryDetailService.delByShowId(id);
        memberLogOperator.saveMemberLog(null, admin, "删除展示类目:" + showCate.getType(), request, memberOperateLogService);
        //List<ShowCateFrontView> list = redisCacheService.addShowCate();
        //List<FloorViewDto> dtoList = redisCacheService.addIndexFloor();
        return new BasicRet(BasicRet.SUCCESS, "删除成功");
    }


    @PostMapping("/detailCate")
    @ApiOperation("分类详情")
    public ShowDetailRet detailCate(long id) {
        ShowDetailRet showDetailRet = new ShowDetailRet();

        ShowCate showCate = showCategoryService.getById(id);
        if (showCate == null) {
            showDetailRet.setResult(BasicRet.ERR);
            showDetailRet.setMessage("该类目不存在");
            return showDetailRet;
        }

        List<ShowCatedetail> showCatedetailList = showCategoryDetailService.getByShowId(id);
        List<ShowCateView> list = new ArrayList<>();


        for (ShowCatedetail showCatedetail : showCatedetailList) {
            ShowCateView showCateView = new ShowCateView();
            BeanUtils.copyProperties(showCatedetail, showCateView);


            if (showCatedetail.getLevel2id() != null) {
                Categories level2Cate = categoriesService.getById(showCatedetail.getLevel2id());
                Categories level1Cate = null;

                if(level2Cate != null && level2Cate.getParentid() != null){
                    level1Cate = categoriesService.getById(level2Cate.getParentid());
                }

                if (level1Cate != null) {
                    showCateView.setLevel1id(level1Cate.getId());
                }
            }

            String[] cateidArr = showCatedetail.getCategoryids().split(",");

            List<ShowCategory> cateList = new ArrayList<>();
            for (String cateid : cateidArr) {
                if (!StringUtils.hasText(cateid)) {
                    continue;
                }

                Categories categories = categoriesService.getById(StringUtils.intValue(cateid));
                if(categories != null) {
                    ShowCategory showCategory = new ShowCategory();
                    BeanUtils.copyProperties(categories, showCategory);
                    cateList.add(showCategory);
                }
            }

            showCateView.setCateList(cateList);

            list.add(showCateView);
        }


        showDetailRet.setResult(BasicRet.SUCCESS);
        showDetailRet.data.showCate = showCate;
        showDetailRet.data.list = list;


        return showDetailRet;
    }


    @PostMapping("/listcate")
    @ApiOperation("列表显示展示类目")
    public ListcateRet listcate() {
        ListcateRet listcateRet = new ListcateRet();
        List<ShowCate> list = showCategoryService.listAll();

        listcateRet.data.list = list;
        listcateRet.setResult(BasicRet.SUCCESS);
        return listcateRet;
    }


    /**
     * 获取材质集合
     *
     * @param showCatedetailList
     * @return
     */
    private List<String> getMaterialList(List<ShowCatedetail> showCatedetailList) {
        List<String> materialList = new ArrayList<>();

        for (ShowCatedetail showCatedetail : showCatedetailList) {
            if (!materialList.contains(showCatedetail.getMaterial())) {
                materialList.add(showCatedetail.getMaterial());
            }
        }
        return materialList;
    }


    private class ShowDetailRet extends BasicRet {
        private class ShowDetailData {
            private ShowCate showCate;
            private List<ShowCateView> list;

            public ShowCate getShowCate() {
                return showCate;
            }

            public void setShowCate(ShowCate showCate) {
                this.showCate = showCate;
            }

            public List<ShowCateView> getList() {
                return list;
            }

            public void setList(List<ShowCateView> list) {
                this.list = list;
            }
        }

        private ShowDetailData data = new ShowDetailData();

        public ShowDetailData getData() {
            return data;
        }

        public void setData(ShowDetailData data) {
            this.data = data;
        }
    }


    private class ListcateRet extends BasicRet {
        private class ListcateData {
            private List<ShowCate> list;

            public List<ShowCate> getList() {
                return list;
            }

            public void setList(List<ShowCate> list) {
                this.list = list;
            }
        }

        ListcateData data = new ListcateData();

        public ListcateData getData() {
            return data;
        }

        public void setData(ListcateData data) {
            this.data = data;
        }
    }
}
