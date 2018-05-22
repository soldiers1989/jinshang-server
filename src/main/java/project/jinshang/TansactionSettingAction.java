package project.jinshang;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import mizuki.project.core.restserver.config.BasicRet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import project.jinshang.mod_admin.mod_transet.bean.TransactionSetting;
import project.jinshang.mod_admin.mod_transet.bean.TransactionSettingExample;
import project.jinshang.mod_admin.mod_transet.service.TransactionSettingService;

@RestController
@RequestMapping("/rest/transaction")
@Api(tags = "交易设置通用模块", description = "获取交易设置")
public class TansactionSettingAction {

    @Autowired
    private TransactionSettingService transactionSettingService;

    private class TansactionSettingRet extends BasicRet {
        private class Data {

            TransactionSetting transactionSettings;


            public TransactionSetting getTransactionSettings() {
                return transactionSettings;
            }

            public void setTransactionSettings(TransactionSetting transactionSettings) {
                this.transactionSettings = transactionSettings;
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


    @RequestMapping(value = "/loadAllTransactionSetting", method = RequestMethod.POST)
    @ApiOperation(value = "加载所有交易设置")
    public TansactionSettingRet loadTransactionSetting() {
        TansactionSettingRet orderCarRet = new TansactionSettingRet();
        TransactionSettingExample transactionSettingExample = new TransactionSettingExample();
        TransactionSetting transactionSetting = transactionSettingService.loadTransactionSetting(transactionSettingExample);
        orderCarRet.setMessage("返回成功");
        orderCarRet.setResult(BasicRet.SUCCESS);
        orderCarRet.data.transactionSettings = transactionSetting;
        return orderCarRet;
    }
}
