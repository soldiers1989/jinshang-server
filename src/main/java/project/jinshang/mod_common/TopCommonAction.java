package project.jinshang.mod_common;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import mizuki.project.core.restserver.config.BasicRet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import project.jinshang.common.constant.Quantity;
import project.jinshang.mod_activity.bean.LimitTimeProd;
import project.jinshang.mod_activity.service.LimitTimeProdService;
import project.jinshang.mod_admin.mod_top.bean.ProductModel;
import project.jinshang.mod_admin.mod_top.bean.TopActivity;
import project.jinshang.mod_admin.mod_top.service.TopActivityService;
import project.jinshang.mod_admin.mod_transet.bean.TransactionSetting;
import project.jinshang.mod_admin.mod_transet.service.TransactionSettingService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/rest/top")
@Api(tags = "通用后台顶部活动模块",description = "通用后台顶部活动模块")
public class TopCommonAction {

    private class TopCommonActivityRet extends BasicRet{

        private class Data{

            private Long systemtime;

            private List<ProductModel> productModels;

            private  List<TopActivity> topActivities;

            public Long getSystemtime() {
                return systemtime;
            }

            public void setSystemtime(Long systemtime) {
                this.systemtime = systemtime;
            }

            public List<ProductModel> getProductModels() {
                return productModels;
            }

            public void setProductModels(List<ProductModel> productModels) {
                this.productModels = productModels;
            }

            public List<TopActivity> getTopActivities() {
                return topActivities;
            }

            public void setTopActivities(List<TopActivity> topActivities) {
                this.topActivities = topActivities;
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

    @Autowired
    private TopActivityService topActivityService;
    @Autowired
    private LimitTimeProdService limitTimeProdService;

    @Autowired
    private TransactionSettingService transactionSettingService;

    @RequestMapping(value = "/getLimitProduct",method = RequestMethod.POST)
    @ApiOperation(value = "获取限时购商品列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "activitytype", value = "活动类型1=购时购", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "topid", value = "顶部活动id", required = true, paramType = "query", dataType = "long"),
    })
    public TopCommonActivityRet getLimitProduct(Model model, HttpServletRequest request, Short activitytype, Long topid){
        TopCommonActivityRet basicRet = new TopCommonActivityRet();

        TransactionSetting transactionSetting = transactionSettingService.getTransactionSetting();

        int befoBuytime = 0;
        if(transactionSetting != null){
            befoBuytime = transactionSetting.getBuytimeaheadtime().intValue();
        }


        List<ProductModel> list= topActivityService.getLimitProduct(activitytype,topid,befoBuytime);

//        for(ProductModel productModel:list){
//            Long limitid = productModel.getLimitid();
//            if(limitid!=null){
//                long nowTime = System.currentTimeMillis();
//                LimitTimeProd limitTimeProd = topActivityService.getLimitTimeProdByID(limitid);
//                if(limitTimeProd!=null){
//                    if(limitTimeProd.getBegintime().getTime() < nowTime &&
//                            limitTimeProd.getEndtime().getTime()> nowTime &&
//                            limitTimeProd.getState()== Quantity.STATE_1){
//                        //修改活动为进行中状态
//                        LimitTimeProd updateLimitTimeProd =  new LimitTimeProd();
//                        updateLimitTimeProd.setId(limitid);
//                        updateLimitTimeProd.setState(Quantity.STATE_4);
//                        limitTimeProdService.updateByPrimaryKeySelective(updateLimitTimeProd);
//                        productModel.setState(Quantity.STATE_4);
//                    }
//
//                    if(limitTimeProd.getEndtime().getTime() < nowTime && limitTimeProd.getState()==Quantity.STATE_4){
//                        //修改活动状态为活动结束状态
//                        LimitTimeProd updateLimitTimeProd =  new LimitTimeProd();
//                        updateLimitTimeProd.setId(limitid);
//                        updateLimitTimeProd.setState(Quantity.STATE_5);
//                        limitTimeProdService.updateByPrimaryKeySelective(updateLimitTimeProd);
//                        productModel.setState(Quantity.STATE_5);
//                    }
//                }
//
//            }
//        }


        basicRet.data.productModels = list;
        basicRet.setMessage("返回成功");
        basicRet.setResult(BasicRet.SUCCESS);
        return basicRet;
    }


    @RequestMapping(value = "/getTopActivityList",method = RequestMethod.POST)
    @ApiOperation(value = "获取顶部活动列表")
    public TopCommonActivityRet getTopActivityList(Model model, HttpServletRequest request){
        TopCommonActivityRet basicRet = new TopCommonActivityRet();
        basicRet.data.topActivities = topActivityService.getTopActivityList();
        basicRet.setMessage("返回成功");
        basicRet.setResult(BasicRet.SUCCESS);
        return basicRet;
    }

    @RequestMapping(value = "/getSystemTime", method = RequestMethod.POST)
    @ApiOperation(value = "获取系统时间")
    public TopCommonActivityRet getSystemTime() {
        TopCommonActivityRet basicRet = new TopCommonActivityRet();
        basicRet.data.systemtime=System.currentTimeMillis();
        basicRet.setMessage("返回成功");
        basicRet.setResult(BasicRet.SUCCESS);
        return basicRet;
    }

}
