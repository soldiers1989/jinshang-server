package project.jinshang.mod_cash;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import mizuki.project.core.restserver.config.BasicRet;
import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.jinshang.common.bean.MemberLogOperator;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.common.constant.Quantity;
import project.jinshang.common.utils.Base64Utils;
import project.jinshang.common.utils.CommonUtils;
import project.jinshang.common.utils.GenerateNo;
import project.jinshang.mod_cash.bean.BuyerCapital;
import project.jinshang.mod_cash.bean.SalerCapital;
import project.jinshang.mod_cash.service.BuyerCapitalService;
import project.jinshang.mod_cash.service.SalerCapitalService;
import project.jinshang.mod_member.bean.Member;
import project.jinshang.mod_member.service.MemberService;
import project.jinshang.mod_product.service.MemberOperateLogService;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Date;

/**
 * create : wyh
 * date : 2017/11/20
 */



@RestController
//@RequestMapping(value = {"/rest/buyer","/rest/seller"})
@Api(tags = "买家卖家充值提现接口")
@SessionAttributes({AppConstant.MEMBER_SESSION_NAME})
@Transactional(rollbackFor = Exception.class)
public class ChargeMoneyAction {

    @Autowired
    private BuyerCapitalService buyerCapitalService;

    @Autowired
    private SalerCapitalService salerCapitalService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberLogOperator memberLogOperator;

    @Autowired
    private MemberOperateLogService memberOperateLogService;


    @PostMapping("/rest/buyer/recharge/detail")
    @ApiOperation("买家根据充值单号获取充值信息")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "充值单号",name = "rechargenumber",required = true,dataType = "int",paramType = "query"),
    })
    public BuyerCapitalRet buyerRechargeDatail(String rechargenumber){
        BuyerCapitalRet buyerCapitalRet = new BuyerCapitalRet();

        BuyerCapital buyerCapital = buyerCapitalService.getBuyerCapitalByRechargenumber(rechargenumber);
        if(buyerCapital == null){
            buyerCapitalRet.setMessage("充值记录不存在");
            buyerCapitalRet.setResult(BasicRet.ERR);
            return  buyerCapitalRet;
        }

        buyerCapitalRet.setResult(BasicRet.SUCCESS);
        buyerCapitalRet.data = buyerCapital;

        return  buyerCapitalRet;
    }




    @PostMapping("/rest/seller/recharge/detail")
    @ApiOperation("卖家根据充值单号获取充值信息")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "充值单号",name = "rechargenumber",required = true,dataType = "int",paramType = "query"),
    })
    public SalerCapitalRet salerRechargeDatail(String rechargenumber){
        SalerCapitalRet salerCapitalRet = new SalerCapitalRet();

        SalerCapital salerCapital = salerCapitalService.getSalerCapitalByRechargenumber(rechargenumber);

        if(salerCapitalRet == null){
            salerCapitalRet.setMessage("充值记录不存在");
            salerCapitalRet.setResult(BasicRet.ERR);
            return  salerCapitalRet;
        }
        salerCapitalRet.setResult(BasicRet.SUCCESS);
        salerCapitalRet.data = salerCapital;

        return  salerCapitalRet;
    }




    @RequestMapping(value = "/rest/buyer/chargeMoney/buyerCharge",method = RequestMethod.POST)
    @ApiOperation("买家充值")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "充值平台2=线下平台3=银行卡  注意：如果是支付宝或微信请填写-1",name = "rechargeperform",required = true,dataType = "int",paramType = "query"),
            @ApiImplicitParam(value = "金额",name = "capital",required = true,dataType = "double",paramType = "query"),
    })
    public ChargeRet buyerCharge(@RequestParam(required = true) short rechargeperform,
                                 @RequestParam(required = true)BigDecimal capital,
                                 Model model, HttpServletRequest request){
        ChargeRet chargeRet = new ChargeRet();

        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

        BuyerCapital buyerCapital = new BuyerCapital();
        buyerCapital.setOrderno("");
        buyerCapital.setTradeno("");
        buyerCapital.setCapitaltype((short)1);
        buyerCapital.setCapital(capital);
        buyerCapital.setPaytype((short)-1);
        buyerCapital.setMemberid(member.getId());
        buyerCapital.setTradetime(new Date());
        buyerCapital.setRemark("充值");
        buyerCapital.setRechargenumber(GenerateNo.getRechargeNo());
        buyerCapital.setRechargeperform(rechargeperform);
        buyerCapital.setRechargestate(Quantity.STATE_0);
        //buyerCapital.setInvoiceamount(new BigDecimal(0));


        buyerCapitalService.insertSelective(buyerCapital);

        chargeRet.setMessage("");
        chargeRet.setResult(BasicRet.SUCCESS);
        chargeRet.data.chargeNo = buyerCapital.getRechargenumber();

        //保存日志
        memberLogOperator.saveMemberLog(member,null,"买家帐号充值："+buyerCapital.getCapital(),request,memberOperateLogService);

        return  chargeRet;
    }






    @RequestMapping(value = "/rest/seller/chargeMoney/sellerCharge",method = RequestMethod.POST)
    @ApiOperation("卖家充值")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "充值平台0=微信1=支付宝2=线下平台4=银行卡 注意：如果是支付宝或微信请填写-1",name = "rechargeperform",required = true,dataType = "int",paramType = "query"),
            @ApiImplicitParam(value = "金额",name = "capital",required = true,dataType = "double",paramType = "query"),
    })
    public ChargeRet sellerCharge(@RequestParam(required = true) short rechargeperform,
                                 @RequestParam(required = true)BigDecimal capital,
                                 Model model,HttpServletRequest request){
        ChargeRet chargeRet = new ChargeRet();

        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

        SalerCapital salerCapital = new SalerCapital();
        salerCapital.setMemberid(member.getId());
        salerCapital.setOrderno("");
        salerCapital.setTradeno("");
        salerCapital.setTradetime(new Date());
        salerCapital.setOrdercapital(capital);
        salerCapital.setRemark("卖家充值");
        salerCapital.setCapitaltype(Quantity.STATE_4);
        salerCapital.setRechargenumber(GenerateNo.getRechargeNo());
        salerCapital.setRechargestate(Quantity.STATE_0);
        salerCapital.setRechargeperform(rechargeperform);

        salerCapitalService.insertSelective(salerCapital);

        chargeRet.setMessage("");
        chargeRet.setResult(BasicRet.SUCCESS);
        chargeRet.data.chargeNo = salerCapital.getRechargenumber();


        //保存日志
        memberLogOperator.saveMemberLog(member,null,"卖家充值："+salerCapital.getOrdercapital(),request,memberOperateLogService);

        return  chargeRet;
    }





    @RequestMapping(value = "/rest/seller/sellerWithdrawCas",method = RequestMethod.POST)
    @ApiOperation("卖家提现申请")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "提现种类 5=余額提现，11=货款提现",name = "capitaltype",required = true,dataType = "int",paramType = "query"),
            @ApiImplicitParam(value = "提现金额",name = "money",required = true,dataType = "double",paramType = "query"),
            @ApiImplicitParam(value = "提现方式1=微信2=支付宝3=银行卡",name = "withdrawtype",required = true,dataType = "int",paramType = "query"),
            @ApiImplicitParam(value = "帐号",name = "account",required = true,dataType = "string",paramType = "query"),
            @ApiImplicitParam(value = "支付密码",name = "payPassword",required = true,dataType = "string",paramType = "query"),
            @ApiImplicitParam(value = "昵称",name = "alias",required = false,dataType = "string",paramType = "query"),
            @ApiImplicitParam(value = "开户银行全称",name = "bankname",required = false,dataType = "string",paramType = "query"),
            @ApiImplicitParam(value = "开户银行",name = "banknamealias",required = false,dataType = "string",paramType = "query"),
            @ApiImplicitParam(value = "银行开户名",name = "bankaccountname",required = false,dataType = "string",paramType = "query"),
            @ApiImplicitParam(value = "用户备注",name = "membermark",required = false,dataType = "string",paramType = "query"),
    })
    public BasicRet  sellerWithdrawCas(@RequestParam(required = true) Short capitaltype,
                                       @RequestParam(required = true) BigDecimal money,
                                       @RequestParam(required = true) short withdrawtype,
                                       @RequestParam(required =  true) String account,
                                       @RequestParam(required = true) String payPassword,
                                       @RequestParam(required =  false,defaultValue = "") String alias,
                                       @RequestParam(required =  false,defaultValue = "") String bankname,
                                       @RequestParam(required = false,defaultValue = "") String banknamealias,
                                       @RequestParam(required = false,defaultValue = "") String bankaccountname,
                                       @RequestParam(required = false,defaultValue = "") String membermark,
                                       Model model, HttpServletRequest request){
        BasicRet basicRet = new BasicRet();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

        payPassword = Base64Utils.decode(payPassword);

        if(capitaltype != Quantity.STATE_5 && capitaltype != Quantity.STATE_11){
            return  new BasicRet(BasicRet.ERR,"提现种类不合法");
        }

        member =  memberService.getMemberById(member.getId());

        BigDecimal balance =  member.getSellerbanlance() == null ? new BigDecimal(0) : member.getSellerbanlance() ;
        BigDecimal goodsbalance =  member.getGoodsbanlance() == null ? new BigDecimal(0) : member.getGoodsbanlance();

        if(capitaltype == Quantity.STATE_5 && balance.compareTo(money) == -1 ){
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("余额不足");
            return  basicRet;
        }else if(capitaltype == Quantity.STATE_11 && goodsbalance.compareTo(money) == -1){
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("货款余额不足");
            return  basicRet;
        }

        if(!member.getPaypassword().equals(CommonUtils.genMd5Password(payPassword,member.getPaypasswordsalt()))){
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("支付密码不正确");
            return  basicRet;
        }

        SalerCapital salerCapital = new  SalerCapital();
        salerCapital.setMemberid(member.getId());
        salerCapital.setTradetime(new Date());
        salerCapital.setOrdercapital(money);
        salerCapital.setRemark(capitaltype == Quantity.STATE_5 ?  "卖家余额提现" : "卖家货款提现");
        salerCapital.setCapitaltype(capitaltype);
        salerCapital.setPresentationnumber(GenerateNo.getWithdrawalsNoNo());
        salerCapital.setRechargestate(Quantity.STATE_3);
        salerCapital.setAccount(account);
        salerCapital.setAlias(alias);
        salerCapital.setBankname(bankname);
        salerCapital.setBanknamealias(banknamealias);
        salerCapital.setBankaccountname(bankaccountname);
        salerCapital.setWithdrawtype(withdrawtype);
        salerCapital.setMembermark(membermark);

        salerCapitalService.insertSelective(salerCapital);


        if(capitaltype == Quantity.STATE_5 ){  //卖家余额提现
            memberService.updateSellerMemberBalanceInDb(member.getId(),money.multiply(new BigDecimal(-1)),new BigDecimal(0));
        }else if(capitaltype == Quantity.STATE_11 ){  //卖家货款提现
            memberService.updateSellerMemberGoodsBalanceInDb(member.getId(),money.multiply(new BigDecimal(-1)));
        }


        member =  memberService.getMemberById(member.getId());
        if(capitaltype == Quantity.STATE_5 && member.getSellerbanlance().compareTo(new BigDecimal(0)) <0){  //卖家余额提现
            throw new RuntimeException("卖家余额不足");
        }else if(capitaltype == Quantity.STATE_11 && member.getGoodsbanlance().compareTo(new BigDecimal(0)) <0 ){  //卖家货款提现
            throw new RuntimeException("卖家货款不足");
        }


        //保存日志
        memberLogOperator.saveMemberLog(member,null,salerCapital.getRemark()+salerCapital.getOrdercapital(),request,memberOperateLogService);

        basicRet.setMessage("提交成功，等待审核");
        basicRet.setResult(BasicRet.SUCCESS);

        return  basicRet;
    }





    @RequestMapping(value = "/rest/buyer/buyerWithdrawCas",method = RequestMethod.POST)
    @ApiOperation("买家提现申请")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "提现金额",name = "money",required = true,dataType = "double",paramType = "query"),
            @ApiImplicitParam(value = "提现方式1=微信2=支付宝3=银行卡",name = "withdrawtype",required = true,dataType = "int",paramType = "query"),
            @ApiImplicitParam(value = "帐号",name = "account",required = true,dataType = "string",paramType = "query"),
            @ApiImplicitParam(value = "支付密码",name = "payPassword",required = true,dataType = "string",paramType = "query"),
            @ApiImplicitParam(value = "昵称",name = "alias",required = false,dataType = "string",paramType = "query"),
            @ApiImplicitParam(value = "开户银行全称",name = "bankname",required = false,dataType = "string",paramType = "query"),
            @ApiImplicitParam(value = "开户银行",name = "banknamealias",required = false,dataType = "string",paramType = "query"),
            @ApiImplicitParam(value = "银行开户名",name = "bankaccountname",required = false,dataType = "string",paramType = "query"),
            @ApiImplicitParam(value = "用户备注",name = "membermark",required = false,dataType = "string",paramType = "query"),
    })
    public BasicRet  buyerWithdrawCas(@RequestParam(required = true) BigDecimal money,
                                       @RequestParam(required = true) short withdrawtype,
                                       @RequestParam(required =  true) String account,
                                       @RequestParam(required = true) String payPassword,
                                       @RequestParam(required =  false,defaultValue = "") String alias,
                                       @RequestParam(required =  false,defaultValue = "") String bankname,
                                       @RequestParam(required = false,defaultValue = "") String banknamealias,
                                       @RequestParam(required = false,defaultValue = "") String bankaccountname,
                                       @RequestParam(required = false,defaultValue = "") String membermark,
                                       Model model,HttpServletRequest request){
        BasicRet basicRet = new BasicRet();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

        payPassword = Base64Utils.decode(payPassword);

        member =  memberService.getMemberById(member.getId());
        BigDecimal balance =  member.getBalance();
        if(balance.compareTo(money) == -1 ){
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("余额不足");
            return  basicRet;
        }

        if(!member.getPaypassword().equals(CommonUtils.genMd5Password(payPassword,member.getPaypasswordsalt()))){
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("支付密码不正确");
            return  basicRet;
        }

        BuyerCapital buyerCapital = new BuyerCapital();
        buyerCapital.setMemberid(member.getId());
        buyerCapital.setTradetime(new Date());
        buyerCapital.setCapital(money);
        buyerCapital.setRemark("买家余额提现");
        buyerCapital.setCapitaltype(Quantity.STATE_3);
        buyerCapital.setPresentationnumber(GenerateNo.getWithdrawalsNoNo());
        buyerCapital.setRechargestate(Quantity.STATE_3);
        buyerCapital.setAccount(account);
        buyerCapital.setAlias(alias);
        buyerCapital.setBankname(bankname);
        buyerCapital.setBanknamealias(banknamealias);
        buyerCapital.setBankaccountname(bankaccountname);
        buyerCapital.setWithdrawtype(withdrawtype);
        buyerCapital.setMembermark(membermark);

        buyerCapitalService.insertSelective(buyerCapital);

        //扣除余额
        memberService.updateBuyerMemberBalanceInDb(member.getId(),money.multiply(new BigDecimal(-1)));


        member =  memberService.getMemberById(member.getId());
        if(member.getBalance().compareTo(new BigDecimal(0)) <0){
            throw  new RuntimeException("帐号余额不足");
        }


        //保存日志
        memberLogOperator.saveMemberLog(member,null,"买家帐号申请提现："+buyerCapital.getCapital(),request,memberOperateLogService);

        basicRet.setMessage("提交成功，等待审核");
        basicRet.setResult(BasicRet.SUCCESS);

        return  basicRet;
    }



    private  class  SalerCapitalRet extends  BasicRet{
        private  SalerCapital data;

        public SalerCapital getData() {
            return data;
        }

        public void setData(SalerCapital data) {
            this.data = data;
        }
    }


    private  class  BuyerCapitalRet extends  BasicRet{
        private  BuyerCapital data;

        public BuyerCapital getData() {
            return data;
        }

        public void setData(BuyerCapital data) {
            this.data = data;
        }
    }


    private  class ChargeRet extends  BasicRet{
        private  class  Data{
            private  String chargeNo;

            public String getChargeNo() {
                return chargeNo;
            }

            public void setChargeNo(String chargeNo) {
                this.chargeNo = chargeNo;
            }
        }


        private  Data data =  new Data();

        public Data getData() {
            return data;
        }
    }




}
