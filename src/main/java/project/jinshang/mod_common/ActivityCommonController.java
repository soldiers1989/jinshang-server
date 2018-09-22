package project.jinshang.mod_common;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import mizuki.project.core.restserver.config.BasicRet;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.jinshang.mod_activity.bean.LimitTimeCategory;
import project.jinshang.mod_activity.bean.LimitTimeSetting;
import project.jinshang.mod_activity.service.LimitTimeCategoryService;
import project.jinshang.mod_activity.service.LimitTimeSettingService;

import javax.annotation.Resource;
import java.util.List;

/**
 * create : wyh
 * date : 2018/1/10
 */

@RestController
@RequestMapping("/rest/common/activity")
@Api(tags = "活动通用接口")
public class ActivityCommonController {

    @Resource
    private LimitTimeCategoryService limitTimeCategoryService;

    @Resource
    private LimitTimeSettingService limitTimeSettingService;


    @PostMapping("/get/limitSetting")
    @ApiOperation("获取活动参数设置")
    public  LimitSettingRet getLimitSetting(){
        LimitSettingRet settingRet = new LimitSettingRet();
        LimitTimeSetting setting = limitTimeSettingService.getLimitTimeSetting();
        settingRet.setting = setting;
        settingRet.setResult(BasicRet.SUCCESS);
        return  settingRet;
    }

    private  class  LimitSettingRet extends  BasicRet{
        private  LimitTimeSetting setting;

        public LimitTimeSetting getSetting() {
            return setting;
        }

        public void setSetting(LimitTimeSetting setting) {
            this.setting = setting;
        }
    }




    @PostMapping("/listCategory")
    @ApiOperation("分类列表")
    public CategoryListRet listCategory(){
        CategoryListRet listRet = new CategoryListRet();
        listRet.setList(limitTimeCategoryService.selectAll());
        listRet.setResult(BasicRet.SUCCESS);
        return  listRet;
    }

    private  class  CategoryListRet extends  BasicRet{
        private List<LimitTimeCategory> list;

        public List<LimitTimeCategory> getList() {
            return list;
        }

        public void setList(List<LimitTimeCategory> list) {
            this.list = list;
        }
    }


}
