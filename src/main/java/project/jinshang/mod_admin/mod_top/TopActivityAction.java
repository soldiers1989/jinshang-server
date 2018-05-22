package project.jinshang.mod_admin.mod_top;

import com.github.pagehelper.PageInfo;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
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
import project.jinshang.mod_admin.mod_top.bean.ActivityType;
import project.jinshang.mod_admin.mod_top.bean.ProductModel;
import project.jinshang.mod_admin.mod_top.bean.TopActivity;
import project.jinshang.mod_admin.mod_top.bean.TopActivityProduct;
import project.jinshang.mod_admin.mod_top.service.TopActivityService;
import project.jinshang.mod_member.bean.Admin;
import project.jinshang.mod_product.service.MemberOperateLogService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/rest/admin/top")
@SessionAttributes({AppConstant.ADMIN_SESSION_NAME})
@Api(tags = "后台顶部活动模块", description = "后台顶部活动模块")
@Transactional(rollbackFor = Exception.class)
public class TopActivityAction {


    @Autowired
    private TopActivityService topActivityService;

    @Autowired
    private MemberOperateLogService memberOperateLogService;

    MemberLogOperator memberLogOperator = new MemberLogOperator();

    private class TopActivityRet extends BasicRet {

        private class Data {

            private List<ActivityType> activityTypes;

            private List<ProductModel> productModels;

            private List<TopActivity> topActivities;


            public List<TopActivity> getTopActivities() {
                return topActivities;
            }

            public void setTopActivities(List<TopActivity> topActivities) {
                this.topActivities = topActivities;
            }

            public List<ProductModel> getProductModels() {
                return productModels;
            }

            public void setProductModels(List<ProductModel> productModels) {
                this.productModels = productModels;
            }

            public List<ActivityType> getActivityTypes() {
                return activityTypes;
            }

            public void setActivityTypes(List<ActivityType> activityTypes) {
                this.activityTypes = activityTypes;
            }
        }

        private Data data = new Data();

        public Data getData() {
            return data;
        }

        public void setData(Data data) {
            this.data = data;
        }
    }


    @RequestMapping(value = "/saveTopActivity", method = RequestMethod.POST)
    @ApiOperation(value = "保存顶部活动")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "商品栏位名称", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "description", value = "推荐商品栏位描述", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "isavailable", value = "是否启用1=启用2=停用", required = true, paramType = "query", dataType = "int"),
    })
    public BasicRet saveTopActivity(Model model, HttpServletRequest request, TopActivity topActivity) {
        BasicRet basicRet = new BasicRet();
        topActivity.setCreatetime(new Date());
        topActivityService.saveTopActivity(topActivity);
        basicRet.setMessage("保存成功");
        basicRet.setResult(BasicRet.SUCCESS);
        Admin admin = (Admin) model.asMap().get(AppConstant.ADMIN_SESSION_NAME);
        //保存用户日志
        memberLogOperator.saveMemberLog(null, admin, "保存顶部活动", "/rest/admin/top/saveTopActivity", request, memberOperateLogService);
        return basicRet;
    }


    @RequestMapping(value = "/updateTopActivity", method = RequestMethod.POST)
    @ApiOperation(value = "修改顶部活动")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "name", value = "商品栏位名称", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "description", value = "推荐商品栏位描述", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "isavailable", value = "是否启用1=启用2=停用", required = true, paramType = "query", dataType = "int"),
    })
    public BasicRet updateTopActivity(Model model, HttpServletRequest request, TopActivity topActivity) {
        BasicRet basicRet = new BasicRet();
        topActivityService.updateTopActivity(topActivity);
        basicRet.setMessage("修改成功");
        basicRet.setResult(BasicRet.SUCCESS);
        Admin admin = (Admin) model.asMap().get(AppConstant.ADMIN_SESSION_NAME);
        //保存用户日志
        memberLogOperator.saveMemberLog(null, admin, "修改顶部活动", "/rest/admin/top/updateTopActivity", request, memberOperateLogService);
        return basicRet;
    }

    @RequestMapping(value = "/deleteTopActivity", method = RequestMethod.POST)
    @ApiOperation(value = "删除顶部活动")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "query", dataType = "long"),
    })
    public BasicRet deleteTopActivity(Model model, HttpServletRequest request, Long id) {
        BasicRet basicRet = new BasicRet();
        topActivityService.deleteTopActivity(id);
        topActivityService.deleteByActivityId(id);
        basicRet.setMessage("删除成功");
        basicRet.setResult(BasicRet.SUCCESS);
        Admin admin = (Admin) model.asMap().get(AppConstant.ADMIN_SESSION_NAME);
        //保存用户日志
        memberLogOperator.saveMemberLog(null, admin, "删除顶部活动", "/rest/admin/top/deleteTopActivity", request, memberOperateLogService);
        return basicRet;
    }

    @RequestMapping(value = "/getTopActivityList", method = RequestMethod.POST)
    @ApiOperation(value = "获取顶部活动列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "页码", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "页数", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "description", value = "描述", required = false, paramType = "query", dataType = "long"),
    })
    public PageRet getTopActivityList(Model model, HttpServletRequest request, int pageNo, int pageSize, String description) {
        PageRet basicRet = new PageRet();
        PageInfo pageInfo = topActivityService.getTopActivityList(pageNo, pageSize, description);
        basicRet.data.setPageInfo(pageInfo);
        basicRet.setMessage("返回成功");
        basicRet.setResult(BasicRet.SUCCESS);
        return basicRet;
    }


    @RequestMapping(value = "/getActivityType", method = RequestMethod.POST)
    @ApiOperation(value = "加载活动类型")
    public TopActivityRet getActivityType(Model model, HttpServletRequest request) {
        TopActivityRet topActivityRet = new TopActivityRet();
        List<ActivityType> list = topActivityService.getActivityType();
        topActivityRet.data.activityTypes = list;
        topActivityRet.setMessage("返回成功");
        topActivityRet.setResult(BasicRet.SUCCESS);
        return topActivityRet;
    }


    @RequestMapping(value = "/getLimitProduct", method = RequestMethod.POST)
    @ApiOperation(value = "获取限时购商品列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "activitytype", value = "活动类型1=购时购", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "topid", value = "顶部活动id", required = true, paramType = "query", dataType = "long"),
    })
    public TopActivityRet getLimitProduct(Model model, HttpServletRequest request, Short activitytype, Long topid) {
        TopActivityRet basicRet = new TopActivityRet();
        List<ProductModel> list = topActivityService.getLimitProduct(activitytype, topid);
        basicRet.data.productModels = list;
        basicRet.setMessage("返回成功");
        basicRet.setResult(BasicRet.SUCCESS);
        return basicRet;
    }

    @RequestMapping(value = "/getProductInfoByMemberId", method = RequestMethod.POST)
    @ApiOperation(value = "根据用户id获取从限时购中获取商品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "页码", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "页数", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "memberid", value = "用户id", required = true, paramType = "query", dataType = "long"),
    })
    public PageRet getProductInfoByMemberId(Model model, HttpServletRequest request, int pageNo, int pageSize, Long memberid) {
        PageRet basicRet = new PageRet();
        PageInfo pageInfo = topActivityService.getProductInfoByMemberId(pageNo, pageSize, memberid);
        basicRet.data.setPageInfo(pageInfo);
        basicRet.setMessage("返回成功");
        basicRet.setResult(BasicRet.SUCCESS);
        return basicRet;
    }


    @RequestMapping(value = "/getActivityMember", method = RequestMethod.POST)
    @ApiOperation(value = "获取活动商家")
    public TopActivityRet getActivityMember(Model model, HttpServletRequest request) {
        TopActivityRet basicRet = new TopActivityRet();
        List<ProductModel> list = topActivityService.getActivityMember();
        basicRet.data.productModels = list;
        basicRet.setMessage("返回成功");
        basicRet.setResult(BasicRet.SUCCESS);
        return basicRet;
    }

    @RequestMapping(value = "/batchSaveTopActivityProduct", method = RequestMethod.POST)
    @ApiOperation(value = "批量保存商品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsonStr", value = "[{\"activityid\":1,\"topid\":2,\"activitytype\":1,\"pdid\":1,\"pdno\":\"YA3-A\"},{\"activityid\":4,\"topid\":2,\"activitytype\":1,\"pdid\":2,\"pdno\":\"YA3-B\"}]", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "activitytype", value = "活动类型1=购时购", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "topid", value = "顶部活动id", required = true, paramType = "query", dataType = "long"),
    })
    public BasicRet batchSaveTopActivityProduct(Model model, HttpServletRequest request, String jsonStr, Short activitytype, Long topid) {
        BasicRet basicRet = new BasicRet();
        Gson gson = new Gson();
        List<TopActivityProduct> list = gson.fromJson(jsonStr, new TypeToken<ArrayList<TopActivityProduct>>() {
        }.getType());

        //已经存在的
        List<ProductModel> productModels = topActivityService.getLimitProduct(activitytype, topid);
        if ((list.size() + productModels.size()) > 10) {
            basicRet.setMessage("推荐总商品数量不能超过10个");
            basicRet.setResult(BasicRet.SUCCESS);
            return basicRet;
        }


        //判断该商品是否已经添加到推荐活动商品中了
        for (TopActivityProduct tap : list) {
            if (topActivityService.exisCountTopActivityProduct(tap) > 0) {
                return new BasicRet(BasicRet.ERR, "商品id:" + tap.getPdid() + "的商品已经存在");
            }
        }
        ;


        topActivityService.batchInsertTopActivityProduct(list);
        basicRet.setMessage("保存成功");
        basicRet.setResult(BasicRet.SUCCESS);

        Admin admin = (Admin) model.asMap().get(AppConstant.ADMIN_SESSION_NAME);
        //保存用户日志
        memberLogOperator.saveMemberLog(null, admin, "批量保存顶部活动商品", "/rest/admin/top/batchSaveTopActivityProduct", request, memberOperateLogService);
        return basicRet;
    }

    @RequestMapping(value = "/batchdeleteTopActivityProduct", method = RequestMethod.POST)
    @ApiOperation(value = "批量删除顶部活动商品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "tapid集合", required = true, paramType = "query", dataType = "Array"),
    })
    public BasicRet batchdeleteTopActivityProduct(Model model, Long[] ids, HttpServletRequest request) {
        BasicRet basicRet = new BasicRet();
        topActivityService.deleteAll(Arrays.asList(ids));
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("删除成功");
        Admin admin = (Admin) model.asMap().get(AppConstant.ADMIN_SESSION_NAME);
        memberLogOperator.saveMemberLog(null, admin, "删除顶部活动商品", "/rest/admin/top/batchdeleteTopActivityProduct", request, memberOperateLogService);
        return basicRet;
    }

    @RequestMapping(value = "/deleteTopActivityProduct", method = RequestMethod.POST)
    @ApiOperation(value = "删除顶部活动商品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "tapid", required = true, paramType = "query", dataType = "long"),
    })
    public BasicRet deleteTopActivityProduct(Model model, Long id, HttpServletRequest request) {
        BasicRet basicRet = new BasicRet();
        topActivityService.deleteTopActivityProduct(id);
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("删除成功");
        Admin admin = (Admin) model.asMap().get(AppConstant.ADMIN_SESSION_NAME);
        memberLogOperator.saveMemberLog(null, admin, "删除顶部活动商品", "/rest/admin/top/deleteTopActivityProduct", request, memberOperateLogService);
        return basicRet;
    }

    @RequestMapping(value = "/saveActivityType", method = RequestMethod.POST)
    @ApiOperation(value = "保存活动类型")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "活动名称", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "type", value = "类型1=限时购", required = true, paramType = "query", dataType = "int"),
    })
    public BasicRet saveActivityType(Model model, HttpServletRequest request, ActivityType activityType) {
        BasicRet basicRet = new BasicRet();
        topActivityService.saveActivityType(activityType);
        basicRet.setMessage("保存成功");
        basicRet.setResult(BasicRet.SUCCESS);
        Admin admin = (Admin) model.asMap().get(AppConstant.ADMIN_SESSION_NAME);
        //保存用户日志
        memberLogOperator.saveMemberLog(null, admin, "保存活动类型", "/rest/admin/top/saveActivityType", request, memberOperateLogService);
        return basicRet;
    }


    @RequestMapping(value = "/upateActivityType", method = RequestMethod.POST)
    @ApiOperation(value = "修改活动类型")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "name", value = "活动名称", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "type", value = "类型1=限时购", required = true, paramType = "query", dataType = "int"),
    })
    public BasicRet upateActivityType(Model model, HttpServletRequest request, ActivityType activityType) {
        BasicRet basicRet = new BasicRet();
        topActivityService.updateActivityType(activityType);
        basicRet.setMessage("修改成功");
        basicRet.setResult(BasicRet.SUCCESS);
        Admin admin = (Admin) model.asMap().get(AppConstant.ADMIN_SESSION_NAME);
        //保存用户日志
        memberLogOperator.saveMemberLog(null, admin, "保改活动类型", "/rest/admin/top/upateActivityType", request, memberOperateLogService);
        return basicRet;
    }

    @RequestMapping(value = "/deleteActivityType", method = RequestMethod.POST)
    @ApiOperation(value = "删除活动类型")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "query", dataType = "long"),
    })
    public BasicRet deleteActivityType(Model model, HttpServletRequest request, Long id) {
        BasicRet basicRet = new BasicRet();
        topActivityService.deleteActivityType(id);
        basicRet.setMessage("删除成功");
        basicRet.setResult(BasicRet.SUCCESS);
        Admin admin = (Admin) model.asMap().get(AppConstant.ADMIN_SESSION_NAME);
        //保存用户日志
        memberLogOperator.saveMemberLog(null, admin, "删除活动类型", "/rest/admin/top/deleteActivityType", request, memberOperateLogService);
        return basicRet;
    }

     /*public static void main(String[] args) {

        Gson gson = new Gson();
        TopActivityProduct op = new TopActivityProduct();
        op.setActivityid(1l);
        op.setTopid(2l);
        op.setActivitytype(Quantity.STATE_1);

        TopActivityProduct op1 = new TopActivityProduct();
         op1.setActivityid(4l);
         op1.setTopid(2l);
         op1.setActivitytype(Quantity.STATE_1);

         List<TopActivityProduct> list = new ArrayList<TopActivityProduct>();
        list.add(op);
        list.add(op1);

        String str = gson.toJson(list);

        System.out.println(str);

       List<TopActivityProduct> list2 = gson.fromJson(str,new TypeToken<ArrayList<TopActivityProduct>>() {}.getType());


        System.out.println(str);

    }*/
}
