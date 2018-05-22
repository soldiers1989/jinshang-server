package project.jinshang.mod_purchase;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import mizuki.project.core.restserver.config.BasicRet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.mod_member.bean.Member;
import project.jinshang.mod_purchase.bean.PurchaseBill;
import project.jinshang.mod_purchase.bean.PurchaseSupply;
import project.jinshang.mod_purchase.service.PurchaseService;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value ="/rest/seller/purchase" )
@SessionAttributes({AppConstant.MEMBER_SESSION_NAME})
@Api(tags = "卖家采购模块",description = "卖家采购模块")
@Transactional(rollbackFor =Exception.class)
public class PurchaseAction {

    @Autowired
    private PurchaseService purchaseService;

    private class PurchaseRet extends BasicRet{
        private class Data{
            private PurchaseBill purchaseBill;
            private List<PurchaseSupply> list;
            private PurchaseSupply purchaseSupply;

            public List<PurchaseSupply> getList() {
                return list;
            }

            public void setList(List<PurchaseSupply> list) {
                this.list = list;
            }

            public PurchaseBill getPurchaseBill() {
                return purchaseBill;
            }

            public void setPurchaseBill(PurchaseBill purchaseBill) {
                this.purchaseBill = purchaseBill;
            }
        }

        PurchaseAction.PurchaseRet.Data data = new PurchaseAction.PurchaseRet.Data();

        public Data getData() {
            return data;
        }

        public void setData(Data data) {
            this.data = data;
        }
    }


    /**
     * 保存开票
     * @return
     */
    @RequestMapping(value = "/savePurchaseBill",method = RequestMethod.POST)
    @ApiOperation(value = "保存开票")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "supplyid",value = "",required = true,paramType = "query",dataType = "long"),
            @ApiImplicitParam(name = "supplier",value = "",required = true,paramType = "query",dataType = "long"),
            @ApiImplicitParam(name = "billstart",value = "",required = true,paramType = "query",dataType = "long"),
            @ApiImplicitParam(name = "billend",value = "",required = true,paramType = "query",dataType = "long"),
            @ApiImplicitParam(name = "spay",value = "",required = true,paramType = "query",dataType = "long"),
            @ApiImplicitParam(name = "pay",value = "",required = true,paramType = "query",dataType = "long"),
            @ApiImplicitParam(name = "billno",value = "",required = true,paramType = "query",dataType = "long"),
            @ApiImplicitParam(name = "express",value = "",required = true,paramType = "query",dataType = "long"),
            @ApiImplicitParam(name = "expressno",value = "",required = true,paramType = "query",dataType = "long"),
    })
    public PurchaseRet savePurchaseBill(Model model,PurchaseBill purchaseBill){
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        PurchaseRet purchaseRet = new PurchaseRet();
        purchaseBill.setYpay(purchaseBill.getSpay().subtract(purchaseBill.getPay()));
        purchaseBill.setMemberid(member.getId());
        purchaseBill.setCreatetime(new Date());
        purchaseService.savePurchaseBill(purchaseBill);
        purchaseRet.data.purchaseBill = purchaseBill;
        purchaseRet.setMessage("保存成功");
        purchaseRet.setResult(BasicRet.SUCCESS);
        return purchaseRet;
    }


    /**
     * 获取供应商列表
     * @return
     */
    @RequestMapping(value = "/getPurchaseSupply",method = RequestMethod.POST)
    @ApiOperation(value = "获取供应商")
    public PurchaseRet getPurchaseSupply(Model model,PurchaseBill purchaseBill){
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        PurchaseRet purchaseRet = new PurchaseRet();
        List<PurchaseSupply> list = purchaseService.getPurchaseSupplyByMemberId(member.getId());
        purchaseRet.data.list = list;
        purchaseRet.setMessage("获取成功");
        purchaseRet.setResult(BasicRet.SUCCESS);
        return purchaseRet;
    }

    /**
     * 获取开票列表
     * @return
     */
    @RequestMapping(value = "/getPurchaseBillList",method = RequestMethod.POST)
    @ApiOperation(value = "获取开票列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "supplyid",value = "",required = true,paramType = "query",dataType = "long"),
            @ApiImplicitParam(name = "supplier",value = "",required = true,paramType = "query",dataType = "long"),
            @ApiImplicitParam(name = "billstart",value = "",required = true,paramType = "query",dataType = "long"),
            @ApiImplicitParam(name = "billend",value = "",required = true,paramType = "query",dataType = "long"),
            @ApiImplicitParam(name = "spay",value = "",required = true,paramType = "query",dataType = "long"),
            @ApiImplicitParam(name = "pay",value = "",required = true,paramType = "query",dataType = "long"),
            @ApiImplicitParam(name = "billno",value = "",required = true,paramType = "query",dataType = "long"),
            @ApiImplicitParam(name = "express",value = "",required = true,paramType = "query",dataType = "long"),
            @ApiImplicitParam(name = "expressno",value = "",required = true,paramType = "query",dataType = "long"),
    })
    public PurchaseRet getPurchaseBillList(Model model,PurchaseBill purchaseBill){
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        PurchaseRet purchaseRet = new PurchaseRet();
        purchaseBill.setYpay(purchaseBill.getSpay().subtract(purchaseBill.getPay()));
        purchaseBill.setMemberid(member.getId());
        purchaseBill.setCreatetime(new Date());
        purchaseService.savePurchaseBill(purchaseBill);
        purchaseRet.data.purchaseBill = purchaseBill;
        purchaseRet.setMessage("保存成功");
        purchaseRet.setResult(BasicRet.SUCCESS);
        return purchaseRet;
    }


    /**
     * 保存供应商
     * @return
     */
    @RequestMapping(value = "/savePurchaseSupply",method = RequestMethod.POST)
    @ApiOperation(value = "保存开票")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username",value = "供应商名称",required = true,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "area",value = "省市区",required = true,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "address",value = "详细地址",required = true,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "supplyname",value = "供应商全称",required = true,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "legal",value = "法人",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "transport",value = "运输方式",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "mobilephone",value = "手机",required = true,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "phone",value = "电话",required = true,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "code",value = "邮编",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "bank",value = "开户银行",required = true,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "bankno",value = "银行账号",required = true,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "texno",value = "纳税号",required = true,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "billtype",value = "发票类型",required = true,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "settletype",value = "结算方式",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "currency",value = "币种",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "settletime",value = "账期结算日",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "business",value = "业务员",required = true,paramType = "query",dataType = "string"),
    })
    public PurchaseRet savePurchaseSupply(Model model,PurchaseSupply purchaseSupply){
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        PurchaseRet purchaseRet = new PurchaseRet();
        purchaseSupply.setMemberid(member.getId());
        purchaseRet.data.purchaseSupply = purchaseSupply;
        purchaseRet.setMessage("保存成功");
        purchaseRet.setResult(BasicRet.SUCCESS);
        return purchaseRet;
    }
}
