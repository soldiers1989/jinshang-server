package project.jinshang.mod_bankaccount;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import mizuki.project.core.restserver.config.BasicRet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.jinshang.common.bean.PageRet;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.common.constant.Quantity;
import project.jinshang.common.constant.SellerAuthorityConst;
import project.jinshang.mod_bankaccount.bean.BankAccount;
import project.jinshang.mod_bankaccount.bean.BankAccountExample;
import project.jinshang.mod_bankaccount.service.BankAccountService;
import project.jinshang.mod_member.bean.Member;

import java.util.List;


@RestController
@Api(tags = "买家、卖家银行卡管理", description = "买家、卖家银行卡相关接口")
@SessionAttributes({AppConstant.MEMBER_SESSION_NAME})
public class BankAccountMemberAction {
    @Autowired
    private BankAccountService bankAccountService;

    @RequestMapping(value = "/rest/seller/BankAccount/addBankAccount", method = RequestMethod.POST)
    @ApiOperation(value = "卖家添加银行卡")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bankaccountname", value = "开户银行", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "bankaccountnumber", value = "开户帐户", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "bankname", value = "开户行", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "bankusername", value = "开户名", required = true, paramType = "query", dataType = "string"),
            //  @ApiImplicitParam(name = "taxregistrationcertificate",value = "税号",required = true,paramType = "query"  ,dataType = "string"),
            @ApiImplicitParam(name = "isdefault", value = "是否默认使用银行卡", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "idCard", value = "该银行卡身份证号", required = true, paramType = "query", dataType = "String"),
    })
    @PreAuthorize("hasAuthority('"+ SellerAuthorityConst.BANKACCOUNT+"') || hasAuthority('"+ SellerAuthorityConst.ALL+"')")
    public BasicRet addSellerBankAccount(BankAccount bankAccount, Model model) {
        BasicRet basicRet = new BasicRet();

        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

        BankAccountExample example = new BankAccountExample();
        BankAccountExample.Criteria criteria = example.createCriteria();

        criteria.andMemberidEqualTo(member.getId()).andBatypeEqualTo(Quantity.STATE_1);
        int count = bankAccountService.countByExample(example);
        if (count >= 10) {
            return new BasicRet(BasicRet.SUCCESS, "最多可添加10条");
        }


        example.clear();
        BankAccountExample.Criteria criteria1 = example.createCriteria();
        criteria1.andBankaccountnameEqualTo(bankAccount.getBankaccountnumber());
        criteria1.andBatypeEqualTo(Quantity.STATE_1);
        criteria1.andMemberidEqualTo(member.getId());
        count = bankAccountService.countByExample(example);

        if (count > 0) {
            basicRet.setMessage("已经存在该银行卡了，不可重复添加");
            basicRet.setResult(BasicRet.ERR);
            return basicRet;
        }

        example.clear();
        BankAccountExample.Criteria criteria2 = example.createCriteria();
        criteria2.andBatypeEqualTo(Quantity.STATE_1);
        criteria2.andMemberidEqualTo(member.getId());
        count = bankAccountService.countByExample(example);

        if (count == 0) {
            bankAccount.setIsdefault(Quantity.STATE_1);
        }


        if (bankAccount.getIsdefault() == Quantity.STATE_1) {
            bankAccountService.updateIsdefault(Quantity.STATE_0, Quantity.STATE_1, member.getId());
        }

        bankAccount.setMemberid(member.getId());
        bankAccount.setBatype(Quantity.STATE_1);
        bankAccountService.addBankAccount(bankAccount);

        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("添加成功");


        return basicRet;
    }


    @RequestMapping(value = "/rest/buyer/BankAccount/addBankAccount", method = RequestMethod.POST)
    @ApiOperation(value = "买家添加银行卡")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bankaccountname", value = "开户银行", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "bankaccountnumber", value = "开户帐户", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "bankname", value = "开户行", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "bankusername", value = "开户名", required = true, paramType = "query", dataType = "string"),
            // @ApiImplicitParam(name = "taxregistrationcertificate",value = "税号",required = true,paramType = "query"  ,dataType = "string"),
            @ApiImplicitParam(name = "isdefault", value = "是否默认使用银行卡", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "idCard", value = "该银行卡身份证号", required = true, paramType = "query", dataType = "String"),
    })
    public BasicRet addBuyerBankAccount(BankAccount bankAccount, Model model) {
        BasicRet basicRet = new BasicRet();

        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

        BankAccountExample example = new BankAccountExample();
        BankAccountExample.Criteria criteria = example.createCriteria();

        criteria.andMemberidEqualTo(member.getId()).andBatypeEqualTo(Quantity.STATE_2);
        int count = bankAccountService.countByExample(example);
        if (count >= 10) {
            return new BasicRet(BasicRet.SUCCESS, "最多可添加10条");
        }

        example.clear();
        BankAccountExample.Criteria criteria1 = example.createCriteria();
        criteria1.andBankaccountnameEqualTo(bankAccount.getBankaccountnumber());
        criteria1.andBatypeEqualTo(Quantity.STATE_2);
        criteria1.andMemberidEqualTo(member.getId());
        count = bankAccountService.countByExample(example);

        if (count > 0) {
            basicRet.setMessage("已经存在该银行卡了，不可重复添加");
            basicRet.setResult(BasicRet.ERR);
            return basicRet;
        }


        example.clear();
        BankAccountExample.Criteria criteria2 = example.createCriteria();
        criteria2.andBatypeEqualTo(Quantity.STATE_2);
        criteria2.andMemberidEqualTo(member.getId());
        count = bankAccountService.countByExample(example);

        if (count == 0) {
            bankAccount.setIsdefault(Quantity.STATE_1);
        }


        if (bankAccount.getIsdefault() == Quantity.STATE_1) {
            bankAccountService.updateIsdefault(Quantity.STATE_0, Quantity.STATE_2, member.getId());
        }

        bankAccount.setMemberid(member.getId());
        bankAccount.setBatype(Quantity.STATE_2);
        bankAccountService.addBankAccount(bankAccount);

        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("添加成功");

        return basicRet;
    }


    @RequestMapping(value = {"/rest/seller/BankAccount/deleteBankAccount", "/rest/buyer/BankAccount/deleteBankAccount"}, method = RequestMethod.POST)
    @ApiOperation(value = "删除银行卡")
    public BasicRet deleteBankAccount(@RequestParam(required = true) long id, Model model) {
        BasicRet basicRet = new BasicRet();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        BankAccount bankAccount = bankAccountService.getById(id);

        if (bankAccount == null) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("没有找到该银行卡信息");
        } else if (!member.getId().equals(bankAccount.getMemberid())) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("不是您创建的银行卡，不可删除");
        } else if (bankAccount != null && bankAccount.getIsdefault() == 1) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("默认银行卡不可删除");
        } else {
            bankAccountService.deleteBankAccountById(id);
            basicRet.setResult(BasicRet.SUCCESS);
            basicRet.setMessage("删除成功");
        }
        return basicRet;

    }


    @RequestMapping(value = "/rest/seller/BankAccount/updateBankAccount", method = RequestMethod.POST)
    @ApiOperation("卖家修改银行卡信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "bankaccountname", value = "开户银行", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "bankaccountnumber", value = "开户帐户", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "bankname", value = "开户行", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "bankusername", value = "开户名", required = true, paramType = "query", dataType = "string"),
            // @ApiImplicitParam(name = "taxregistrationcertificate",value = "税号",required = true,paramType = "query"  ,dataType = "string"),
            @ApiImplicitParam(name = "isdefault", value = "是否默认使用银行卡", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "idCard", value = "该银行卡身份证号", required = true, paramType = "query", dataType = "String"),

    })
    @PreAuthorize("hasAuthority('"+ SellerAuthorityConst.BANKACCOUNT+"') || hasAuthority('"+ SellerAuthorityConst.ALL+"')")
    public BasicRet updateSellerBankAccount(BankAccount bankAccount, Model model) {
        BasicRet basicRet = new BasicRet();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

        BankAccount dbBankAccount = bankAccountService.getById(bankAccount.getId());
        if (dbBankAccount == null) {
            basicRet.setMessage("要修改的银行卡不存在");
            basicRet.setResult(BasicRet.ERR);
            return basicRet;
        }


        if (!dbBankAccount.getMemberid().equals(member.getId())) {
            basicRet.setMessage("要修改的银行卡不属于你");
            basicRet.setResult(BasicRet.ERR);
            return basicRet;
        }

        if (dbBankAccount.getIsdefault() == Quantity.STATE_1 && bankAccount.getIsdefault() == Quantity.STATE_0) {
            basicRet.setMessage("必须有一个默认的银行卡");
            basicRet.setResult(BasicRet.ERR);
            return basicRet;
        }


        if (dbBankAccount.getBatype() != Quantity.STATE_1) {
            basicRet.setMessage("该银行卡不属于卖家银行卡");
            basicRet.setResult(BasicRet.ERR);
            return basicRet;
        }

        if (bankAccount.getIsdefault() == Quantity.STATE_1) {
            BankAccount defaultBankAccount = bankAccountService.selectDefaultBankCard(dbBankAccount.getMemberid(), Quantity.STATE_2);
            defaultBankAccount.setIsdefault(Quantity.STATE_0);
            bankAccountService.updateByPrimaryKeySelective(defaultBankAccount);
        }

        bankAccount.setMemberid(member.getId());

        bankAccountService.updateByPrimaryKeySelective(bankAccount);


        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("修改成功");

        return basicRet;
    }


    @RequestMapping(value = "/rest/buyer/BankAccount/updateBankAccount", method = RequestMethod.POST)
    @ApiOperation("买家修改银行卡信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "bankaccountname", value = "开户银行", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "bankaccountnumber", value = "开户帐户", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "bankname", value = "开户行", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "bankusername", value = "开户名", required = true, paramType = "query", dataType = "string"),
            // @ApiImplicitParam(name = "taxregistrationcertificate",value = "税号",required = true,paramType = "query"  ,dataType = "string"),
            @ApiImplicitParam(name = "isdefault", value = "是否默认使用银行卡", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "idCard", value = "该银行卡身份证号", required = true, paramType = "query", dataType = "String"),

    })
    public BasicRet updateBuyerBankAccount(BankAccount bankAccount, Model model) {
        BasicRet basicRet = new BasicRet();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

        BankAccount dbBankAccount = bankAccountService.getById(bankAccount.getId());
        if (dbBankAccount == null) {
            basicRet.setMessage("要修改的银行卡不存在");
            basicRet.setResult(BasicRet.ERR);
            return basicRet;
        }


        if (!dbBankAccount.getMemberid().equals(member.getId())) {
            basicRet.setMessage("要修改的银行卡不属于你");
            basicRet.setResult(BasicRet.ERR);
            return basicRet;
        }

        if (dbBankAccount.getIsdefault() == Quantity.STATE_1 && bankAccount.getIsdefault() == Quantity.STATE_0) {
            basicRet.setMessage("必须有一个默认的银行卡");
            basicRet.setResult(BasicRet.ERR);
            return basicRet;
        }


        if (dbBankAccount.getBatype() != Quantity.STATE_2) {
            basicRet.setMessage("该银行卡不属于买家银行卡");
            basicRet.setResult(BasicRet.ERR);
            return basicRet;
        }

        if (bankAccount.getIsdefault() == Quantity.STATE_1) {
            BankAccount defaultBankAccount = bankAccountService.selectDefaultBankCard(dbBankAccount.getMemberid(), Quantity.STATE_2);
            defaultBankAccount.setIsdefault(Quantity.STATE_0);
            bankAccountService.updateByPrimaryKeySelective(defaultBankAccount);
        }

        bankAccount.setMemberid(member.getId());

        bankAccountService.updateByPrimaryKeySelective(bankAccount);


        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("修改成功");

        return basicRet;
    }


    @RequestMapping(value = {"/rest/seller/BankAccount/listAllBankAccount"}, method = RequestMethod.POST)
    @ApiOperation("列出买家、卖家所有银行卡列表")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "类型：1-卖家，2-买家", name = "batype", paramType = "query", dataType = "int", required = true),
    })
    @PreAuthorize("hasAuthority('"+ SellerAuthorityConst.BANKACCOUNT+"') || hasAuthority('"+ SellerAuthorityConst.ALL+"')")
    public PageRet listSellerBankAccount(
            @RequestParam(required = true, defaultValue = "1") int pageNo,
            @RequestParam(required = true, defaultValue = "10") int pageSize, Model model) {
        PageRet pageRet = new PageRet();
        short batype =  1;
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        PageInfo pageInfo = bankAccountService.getBankAccount(member.getId(), batype, pageNo, pageSize);
        pageRet.setResult(BasicRet.SUCCESS);
        pageRet.data.setPageInfo(pageInfo);
        return pageRet;
    }



    @RequestMapping(value = {"/rest/buyer/BankAccount/listAllBankAccount"}, method = RequestMethod.POST)
    @ApiOperation("列出买家所有银行卡列表")
    public PageRet listBuyerBankAccount(
            @RequestParam(required = true, defaultValue = "1") int pageNo,
            @RequestParam(required = true, defaultValue = "10") int pageSize, Model model) {
        PageRet pageRet = new PageRet();
        short batype =  2;
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        PageInfo pageInfo = bankAccountService.getBankAccount(member.getId(), batype, pageNo, pageSize);
        pageRet.setResult(BasicRet.SUCCESS);
        pageRet.data.setPageInfo(pageInfo);
        return pageRet;
    }



    @RequestMapping(value = {"/rest/seller/BankAccount/get", "/rest/buyer/BankAccount/get"}, method = RequestMethod.POST)
    @ApiOperation("获取银行卡信息")
    public BankAccountRet get(@RequestParam(required = true) long id, Model model) {
        BankAccountRet bankAccountRet = new BankAccountRet();

        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);


        BankAccount bankAccount = bankAccountService.getById(id);

        if (bankAccount == null) {
            bankAccountRet.setMessage("未查询到数据");
            bankAccountRet.setResult(BasicRet.ERR);
            return bankAccountRet;
        }

        if (!bankAccount.getMemberid().equals(member.getId())) {
            bankAccountRet.setMessage("该信息不属于你");
            bankAccountRet.setResult(BasicRet.ERR);
            return bankAccountRet;
        }

        bankAccountRet.data.bankAccount = bankAccount;
        bankAccountRet.setResult(BasicRet.SUCCESS);
        bankAccountRet.setMessage("查询成功");
        return bankAccountRet;
    }


    @RequestMapping(value = {"/rest/seller/BankAccount/setDefault", "/rest/buyer/BankAccount/setDefault"}, method = RequestMethod.POST)
    @ApiOperation("设置为默认银行卡")
    public BasicRet setDefault(@RequestParam(required = true) long id,
                               Model model) {
        BasicRet basicRet = new BasicRet();

        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);


        BankAccount bankAccount = bankAccountService.getById(id);
        if (bankAccount == null) {
            basicRet.setMessage("银行卡信息不存在");
            basicRet.setResult(BasicRet.ERR);
            return basicRet;
        }


        if (!bankAccount.getMemberid().equals(member.getId())) {
            basicRet.setMessage("银行卡不属于你");
            basicRet.setResult(BasicRet.ERR);
            return basicRet;
        }


        //先全部取消默认
        bankAccountService.updateIsdefault(Quantity.STATE_0, bankAccount.getBatype(), member.getId());

        BankAccount updateBank = new BankAccount();
        updateBank.setIsdefault(Quantity.STATE_1);
        updateBank.setId(id);
        bankAccountService.updateByPrimaryKeySelective(updateBank);

        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("修改成功");

        return basicRet;
    }


    private class BankAccountRet extends BasicRet {
        private class BankAccountData {
            private BankAccount bankAccount;

            public BankAccount getBankAccount() {
                return bankAccount;
            }

            public void setBankAccount(BankAccount bankAccount) {
                this.bankAccount = bankAccount;
            }
        }

        private BankAccountData data = new BankAccountData();

        public BankAccountData getData() {
            return data;
        }

        public void setData(BankAccountData data) {
            this.data = data;
        }
    }

}
