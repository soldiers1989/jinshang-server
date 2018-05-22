package project.jinshang.mod_admin.mod_paymode;

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
import project.jinshang.mod_admin.mod_paymode.bean.PayMode;
import project.jinshang.mod_admin.mod_paymode.bean.PayModeExample;
import project.jinshang.mod_admin.mod_paymode.service.PayModeService;
import project.jinshang.mod_member.bean.Admin;
import project.jinshang.mod_product.service.MemberOperateLogService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Administrator on 2017/11/9.
 */
@RestController
@RequestMapping("/rest/admin/paymode")
@Api(tags = "后台管理-支付方式模块",description = "支付方式设置")
public class PayModeAction {
    @Autowired
    private PayModeService payModeService;

    @Autowired
    private MemberOperateLogService memberOperateLogService;

    MemberLogOperator memberLogOperator = new MemberLogOperator();

    @RequestMapping(value = "/updatePayMode",method = RequestMethod.POST)
    @ApiOperation(value = "更新支付方式")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "id",required = true,paramType = "query",dataType = "long"),
            @ApiImplicitParam(name = "paytype",value = "支付方式名称:支付宝，支付宝APP支付等",required = false,paramType = "query"  ,dataType = "int"),
            @ApiImplicitParam(name = "identityid",value = "合作者身份ID",required = false,paramType = "query"  ,dataType = "string"),
            @ApiImplicitParam(name = "saftycode",value = "交易安全检验码",required = false,paramType = "query"  ,dataType = "string"),
            @ApiImplicitParam(name = "alipayaccount",value = "收款支付宝账户",required = false,paramType = "query"  ,dataType = "string"),
            @ApiImplicitParam(name = "saleralipayaccount",value = "商户支付宝账号",required = false,paramType = "query"  ,dataType = "string"),
            @ApiImplicitParam(name = "privatekey",value = "privatekey",required = false,paramType = "query"  ,dataType = "string"),
            @ApiImplicitParam(name = "publickey",value = "publickey",required = false,paramType = "query"  ,dataType = "string"),
            @ApiImplicitParam(name = "weixinappid",value = "weixinappid",required = false,paramType = "query"  ,dataType = "string"),
            @ApiImplicitParam(name = "weixinappsecret",value = "weixinappsecret",required = false,paramType = "query"  ,dataType = "string"),
            @ApiImplicitParam(name = "weixinkey",value = "weixinkey",required = false,paramType = "query"  ,dataType = "string"),
            @ApiImplicitParam(name = "weixinmchid",value = "weixinmchid",required = false,paramType = "query"  ,dataType = "string"),
            @ApiImplicitParam(name = "certificate",value = "商户证书",required = false,paramType = "query"  ,dataType = "string"),
            @ApiImplicitParam(name = "open",value = "是否开启0=开启1=关闭",required = false,paramType = "query"  ,dataType = "int"),
    })
    public BasicRet updatePayMode(Model model,PayMode payMode, HttpServletRequest request){
        BasicRet basicRet=new BasicRet();
        payModeService.updatePayMode(payMode);
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("支付方式修改成功");
        Admin admin = (Admin) model.asMap().get(AppConstant.ADMIN_SESSION_NAME);
        memberLogOperator.saveMemberLog(null,admin,"更新支付方式","/rest/admin/paymode/updatePayMode",request,memberOperateLogService);
        return basicRet;
    }

    @RequestMapping(value = "/loadAllPayMode",method = RequestMethod.POST)
    @ApiOperation(value = "加载所有支付方式")
    public List<PayMode> loadAllPayMode(){
        PayModeExample example =  new PayModeExample();
        List<PayMode> list = payModeService.loadAllPayMode(example);
        return list;
    }
}
