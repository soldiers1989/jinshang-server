package project.jinshang.mod_cash;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import mizuki.project.core.restserver.config.BasicRet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import project.jinshang.common.constant.Quantity;
import project.jinshang.mod_cash.bean.BuyerCapital;
import project.jinshang.mod_cash.bean.SalerCapital;
import project.jinshang.mod_cash.service.BuyerCapitalService;
import project.jinshang.mod_cash.service.SalerCapitalService;
import project.jinshang.mod_member.bean.Member;
import project.jinshang.mod_member.service.MemberService;

import java.math.BigDecimal;

/**
 * create : wyh
 * date : 2017/11/20
 */


@RestController
@RequestMapping("/rest/chargeMoneyback")
@Api(tags = "买家卖家充值回调接口")
@Transactional(rollbackFor = Exception.class)
public class ChargeMoneyBackAction {

    @Autowired
    private BuyerCapitalService buyerCapitalService;

    @Autowired
    private SalerCapitalService salerCapitalService;


    @Autowired
    private MemberService memberService;


    @RequestMapping(value = "/back/buyerCharge",method = RequestMethod.POST)
    @ApiOperation("买家充值回调")
    public BasicRet buyerChargeBack(String orderNo){
        BasicRet basicRet = new BasicRet();

        BuyerCapital buyerCapital = buyerCapitalService.getBuyerCapitalByRechargenumber(orderNo);

        if(buyerCapital == null){
            basicRet.setMessage("系统出现错误");
            basicRet.setResult(BasicRet.ERR);
            return  basicRet;
        }


        Member member = memberService.getMemberById(buyerCapital.getMemberid());

        if(member ==null){
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("要充值的帐号不存在");
            return  basicRet;
        }

        //BigDecimal balance =  member.getBalance() == null ? buyerCapital.getCapital() : member.getBalance().add(buyerCapital.getCapital());
        //memberService.updateBuyerMemberBalance(member.getId(),balance);
        memberService.updateBuyerMemberBalanceInDb(member.getId(),buyerCapital.getCapital());


        BuyerCapital updateBuyerCapital = new BuyerCapital();
        updateBuyerCapital.setId(buyerCapital.getId());
        updateBuyerCapital.setRechargestate(Quantity.STATE_1);
        buyerCapitalService.updateByPrimaryKeySelective(updateBuyerCapital);


        basicRet.setMessage("充值成功");
        basicRet.setResult(BasicRet.SUCCESS);

        return  basicRet;
    }




    @RequestMapping(value = "/back/sellerCharge",method = RequestMethod.POST)
    @ApiOperation("卖家充值回调")
    public BasicRet sellerChargeBack(String orderNo){
        BasicRet basicRet = new BasicRet();
        SalerCapital salerCapital =  salerCapitalService.getSalerCapitalByRechargenumber(orderNo);


        if(salerCapital == null){
            basicRet.setMessage("系统出现错误");
            basicRet.setResult(BasicRet.ERR);
            return  basicRet;
        }


        Member member = memberService.getMemberById(salerCapital.getMemberid());

        if(member ==null){
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("要充值的帐号不存在");
            return  basicRet;
        }

        //BigDecimal sellerbalance =  member.getSellerbanlance() == null ? salerCapital.getOrdercapital() : member.getSellerbanlance().add(salerCapital.getOrdercapital());
        //memberService.updateSellerMemberBalance(member.getId(),sellerbalance,member.getSellerfreezebanlance());
        memberService.updateSellerMemberBalanceInDb(member.getId(),salerCapital.getOrdercapital(),Quantity.BIG_DECIMAL_0);




        SalerCapital updateSalerCapital =  new SalerCapital();
        updateSalerCapital.setId(salerCapital.getId());
        updateSalerCapital.setRechargestate(Quantity.STATE_1);
        salerCapitalService.updateByPrimaryKeySelective(updateSalerCapital);

        basicRet.setMessage("充值成功");
        basicRet.setResult(BasicRet.SUCCESS);
        return  basicRet;
    }









}
