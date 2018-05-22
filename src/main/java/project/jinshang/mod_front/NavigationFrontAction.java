package project.jinshang.mod_front;

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
import project.jinshang.mod_admin.mod_navigation.service.NavigationService;
import project.jinshang.mod_common.bean.BasicExtRet;

@RestController
@Api(tags = "前台网站导航", description = "网站导航相关接口")
@RequestMapping(value = "/rest/front/Navigation")
public class NavigationFrontAction {

    @Autowired
    private NavigationService navigationService;


    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ApiOperation("网站列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "naType", value = "导航类型", dataType = "String", paramType = "query", required = false),
    })
    public BasicExtRet list(@RequestParam(required = false) String naType) {
        BasicExtRet basicExtRet = new BasicExtRet();
        basicExtRet.setData(navigationService.getNavigationListByNaType(naType));
        basicExtRet.setResult(BasicRet.SUCCESS);
        basicExtRet.setMessage("成功");
        return basicExtRet;
    }
}
