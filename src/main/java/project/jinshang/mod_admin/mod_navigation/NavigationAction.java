package project.jinshang.mod_admin.mod_navigation;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import mizuki.project.core.restserver.config.BasicRet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.jinshang.common.bean.MemberLogOperator;
import project.jinshang.common.bean.PageRet;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.mod_admin.mod_navigation.service.NavigationService;
import project.jinshang.mod_admin.mod_navigation.bean.Navigation;
import project.jinshang.mod_common.bean.BasicExtRet;
import project.jinshang.mod_member.bean.Admin;
import project.jinshang.mod_member.bean.Member;
import project.jinshang.mod_product.service.MemberOperateLogService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@Api(tags = "后台网站导航", description = "网站导航相关接口")
@RequestMapping(value = "/rest/admin/Navigation")
@SessionAttributes({AppConstant.ADMIN_SESSION_NAME})
@Transactional(rollbackFor = Exception.class)
public class NavigationAction {

    @Autowired
    private NavigationService navigationService;

    @Autowired
    MemberLogOperator memberLogOperator;

    @Autowired
    MemberOperateLogService memberOperateLogService;

    @RequestMapping(value = "/add/Navigation", method = RequestMethod.POST)
    @ApiOperation("添加网站")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "natype", value = "导航类型", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "natitle", value = "标题", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "nalink", value = "链接", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "nalocation", value = "所在位置", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "naterrace", value = "平台标识", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "naicon", value = "图标", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "isnew", value = "是否新窗口打开 0：否 1：是", dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "nasort", value = "排序", dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "isShow", value = "前台是否显示0：不显示 1：显示", dataType = "int", paramType = "query", required = true),
    })
    public BasicRet addNavigation(Navigation navigation, Model model, HttpServletRequest request) {
        Admin admin = (Admin) model.asMap().get(AppConstant.ADMIN_SESSION_NAME);
        BasicRet basicRet = new BasicRet();
        List<Navigation> navigationList = navigationService.getListByNaSort(navigation.getNasort());
        if (navigationList.size() != 0) {
            Navigation defaultNavigation = navigationList.get(0);
            defaultNavigation.setNasort(navigationService.selectMaxSort() + 1);
            navigationService.updateNavigation(defaultNavigation);
        }
        navigationService.addNavigation(navigation);
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("添加成功");
        memberLogOperator.saveMemberLog(null, admin, "新增网站导航：" + navigation.getNatitle(), "/add/Navigation", request, memberOperateLogService);
        return basicRet;
    }

    @RequestMapping(value = "/deleteNavigation", method = RequestMethod.POST)
    @ApiOperation("删除标签")
    public BasicRet deleteNavigation(@RequestParam(required = true) long id, Model model, HttpServletRequest request) {
        Admin admin = (Admin) model.asMap().get(AppConstant.ADMIN_SESSION_NAME);
        BasicRet basicRet = new BasicRet();
        Navigation navigation = navigationService.selectNavigationById(id);
        if (navigation == null) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("不存在该标签");
            return basicRet;
        }
        navigationService.deleteNavigation(id);
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("删除成功");
        memberLogOperator.saveMemberLog(null, admin, "删除网站导航：" + navigation.getNatitle(), "/deleteNavigation", request, memberOperateLogService);
        return basicRet;
    }

    @RequestMapping(value = "/updateNavigation", method = RequestMethod.POST)
    @ApiOperation("编辑网站")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "ID", dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "natype", value = "导航类型", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "natitle", value = "标题", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "nalink", value = "链接", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "nalocation", value = "所在位置", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "naterrace", value = "平台标识", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "naicon", value = "图标", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "isnew", value = "是否新窗口打开", dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "nasort", value = "排序", dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "isShow", value = "前台是否显示0：不显示 1：显示", dataType = "int", paramType = "query", required = true),
    })
    public BasicRet updateNavigation(Navigation navigation, Model model, HttpServletRequest request) {
        Admin admin = (Admin) model.asMap().get(AppConstant.ADMIN_SESSION_NAME);
        BasicRet basicRet = new BasicRet();
        Navigation dbNavigation = navigationService.selectNavigationById(navigation.getId());
        if (dbNavigation.getNasort().longValue() != navigation.getNasort().longValue()) {
            List<Navigation> navigationList = navigationService.getListByNaSort(navigation.getNasort());
            if (navigationList.size() != 0) {
                Navigation defaultNavigation = navigationList.get(0);
                defaultNavigation.setNasort(dbNavigation.getNasort());
                navigationService.updateNavigation(defaultNavigation);
            }
        }
        navigationService.updateNavigation(navigation);
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("修改成功");
        memberLogOperator.saveMemberLog(null, admin, "修改网站导航：" + navigation.getNatitle(), "/addArticle", request, memberOperateLogService);
        return basicRet;
    }

    @RequestMapping(value = "/listAllNavigation", method = RequestMethod.POST)
    @ApiOperation("所有网站列表")
    public List<Navigation> listAllPdlabele() {
        return navigationService.listAllNavigation();
    }


    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ApiOperation("网站列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "第几页", dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "pageSize", value = "一页几个数据", dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "natype", value = "导航类型", dataType = "String", paramType = "query", required = false),
    })
    public PageRet list(@RequestParam int pageNo,
                        @RequestParam int pageSize,
                        @RequestParam(required = false) String natype) {
        PageRet pageRet = new PageRet();
        pageRet.data.setPageInfo(navigationService.getNavigationList(pageNo, pageSize, natype));
        pageRet.setResult(BasicRet.SUCCESS);
        pageRet.setMessage("成功");
        return pageRet;
    }

    @RequestMapping(value = "/maxSort", method = RequestMethod.POST)
    @ApiOperation("获取最大的序列号")
    public BasicExtRet getMaxSort() {
        BasicExtRet basicExtRet = new BasicExtRet();
        basicExtRet.setData(navigationService.selectMaxSort());
        basicExtRet.setResult(BasicRet.SUCCESS);
        basicExtRet.setMessage("成功");
        return basicExtRet;
    }
}
