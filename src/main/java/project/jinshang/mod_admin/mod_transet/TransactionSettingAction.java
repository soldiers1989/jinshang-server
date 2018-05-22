package project.jinshang.mod_admin.mod_transet;

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
import project.jinshang.common.bean.MemberLogOperator;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.mod_admin.mod_transet.bean.TransactionSetting;
import project.jinshang.mod_admin.mod_transet.bean.TransactionSettingExample;
import project.jinshang.mod_admin.mod_transet.service.TransactionSettingService;
import project.jinshang.mod_member.bean.Admin;
import project.jinshang.mod_product.service.MemberOperateLogService;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2017/11/9.
 */
@RestController
@RequestMapping("/rest/admin/transactionSetting")
@Api(tags = "后台管理-交易设置模块",description = "交易设置")
public class TransactionSettingAction {
    @Autowired
    private TransactionSettingService transactionSettingService;
    @Autowired
    private MemberOperateLogService memberOperateLogService;

    MemberLogOperator memberLogOperator = new MemberLogOperator();

    @RequestMapping(value = "/updateTransactionSetting",method = RequestMethod.POST)
    @ApiOperation(value = "更新交易设置")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "unpaidtimeout",value = "未付款超时",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "confirmreceipttimeout",value = "确认收货超时",required = true,paramType = "query"  ,dataType = "int"),
            @ApiImplicitParam(name = "orderreturnperiod",value = "订单退货期限",required = true,paramType = "query"  ,dataType = "int"),
            @ApiImplicitParam(name = "spotsalesmargin",value = "现货销售保证金",required = true,paramType = "query"  ,dataType = "double"),
            @ApiImplicitParam(name = "forwardsalesmargin",value = "被违约方获取违约金占比",required = true,paramType = "query"  ,dataType = "double"),
            @ApiImplicitParam(name = "getliquidated",value = "违约金占比",required = true,paramType = "query"  ,dataType = "double"),
            @ApiImplicitParam(name = "remotepurchasingmargin",value = "远程采购保证金",required = true,paramType = "query"  ,dataType = "double"),
            @ApiImplicitParam(name = "upperlimitamount",value = "充值金额上限",required = true,paramType = "query"  ,dataType = "double"),
            @ApiImplicitParam(name = "delivery1of10",value = "延期发货1-10天日罚金",required = true,paramType = "query"  ,dataType = "double"),
            @ApiImplicitParam(name = "delivery11of20",value = "延期发货11-20天日罚金",required = true,paramType = "query"  ,dataType = "double"),
            @ApiImplicitParam(name = "delivery21of30",value = "延期发货21-30天日罚金",required = true,paramType = "query"  ,dataType = "double"),
            @ApiImplicitParam(name = "remotedeliveryday",value = "远程交货日",required = true,paramType = "query"  ,dataType = "int"),
            @ApiImplicitParam(name = "buytimeaheadtime",value = "限时购提前购买时间",required = true,paramType = "query"  ,dataType = "int"),
            @ApiImplicitParam(name = "timedoutofpayment",value = "限时购未付款超时",required = true,paramType = "query"  ,dataType = "int"),
            @ApiImplicitParam(name = "inspectionperiod",value = "验货期",required = true,paramType = "query"  ,dataType = "int"),
            @ApiImplicitParam(name = "periodoffreezing",value = "收货后资金冻结期",required = true,paramType = "query"  ,dataType = "int"),
            @ApiImplicitParam(name = "freezetotogoodspay",value = "冻结金额到货款的天数",required = true,paramType = "query"  ,dataType = "int"),
            @ApiImplicitParam(name = "creditbreakrate",value = "授信违约金比率",required = true,paramType = "query"  ,dataType = "int"),
    })
    public BasicRet updateTransactionSetting(Model model, TransactionSetting transactionSetting, HttpServletRequest request){
        BasicRet basicRet=new BasicRet();
        TransactionSettingExample transactionSettingExample = new TransactionSettingExample();
        transactionSettingService.updateTransactionSetting(transactionSetting,transactionSettingExample);
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("交易设置修改成功");
        Admin admin = (Admin)model.asMap().get(AppConstant.ADMIN_SESSION_NAME);
        memberLogOperator.saveMemberLog(null,admin,"修改了交易设置","/rest/admin/transactionSetting/updateTransactionSetting",request,memberOperateLogService);
        return basicRet;
    }

    @RequestMapping(value = "/loadAllTransactionSetting",method = RequestMethod.POST)
    @ApiOperation(value = "加载所有交易设置")
    public TransactionSettingRet loadTransactionSetting(){

        TransactionSettingRet transactionSettingRet = new TransactionSettingRet();

        TransactionSettingExample transactionSettingExample = new TransactionSettingExample();
        TransactionSetting transactionSetting = transactionSettingService.loadTransactionSetting(transactionSettingExample);

        transactionSettingRet.data.transactionSetting = transactionSetting;
        transactionSettingRet.setResult(BasicRet.SUCCESS);
        return transactionSettingRet;
    }

    private class  TransactionSettingRet extends  BasicRet{
        private  class TransactionSettingData{
            private  TransactionSetting transactionSetting;

            public TransactionSetting getTransactionSetting() {
                return transactionSetting;
            }

            public void setTransactionSetting(TransactionSetting transactionSetting) {
                this.transactionSetting = transactionSetting;
            }
        }

        private  TransactionSettingData data = new TransactionSettingData();

        public TransactionSettingData getData() {
            return data;
        }

        public void setData(TransactionSettingData data) {
            this.data = data;
        }
    }

}
