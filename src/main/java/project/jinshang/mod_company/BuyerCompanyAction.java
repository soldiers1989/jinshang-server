package project.jinshang.mod_company;

import io.swagger.annotations.*;
import mizuki.project.core.restserver.config.BasicRet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.mod_common.bean.BasicExtRet;
import project.jinshang.mod_company.bean.BuyerCompanyInfo;
import project.jinshang.mod_company.service.BuyerCompanyService;
import project.jinshang.mod_invoice.bean.InvoiceInfo;
import project.jinshang.mod_invoice.service.InvoiceService;
import project.jinshang.mod_member.bean.Member;
import project.jinshang.mod_member.service.MemberService;

import java.util.Date;

/**
 * create : wyh
 * date : 2017/11/4
 */

@RestController
@Api(tags = "买家公司信息", description = "买家公司信息相关接口")
@RequestMapping("/rest/buyer/")
@SessionAttributes({AppConstant.MEMBER_SESSION_NAME})
public class BuyerCompanyAction {

    @Autowired
    private BuyerCompanyService buyerCompanyService;

    @Autowired
    private MemberService memberService;

    @Autowired
    InvoiceService invoiceService;


    @RequestMapping(value = "/buyerCompanyInfo", method = {RequestMethod.POST,RequestMethod.GET})
    @ApiOperation(value = "获取买家公司信息")
    public BuyerCompanyRet buyerCompanyInfo(Model model) {
        BuyerCompanyRet ret = new BuyerCompanyRet();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        BuyerCompanyInfo info = buyerCompanyService.getBuyerCompanyInfoByMemberId(member.getId());
        if (info == null) {
            ret.setMessage("公司信息不存在");
            ret.setResult(BasicRet.ERR);
            return ret;
        }

        ret.data = info;
        ret.setResult(BasicRet.SUCCESS);
        return ret;
    }


    @RequestMapping(value = "/applyForCompany", method = RequestMethod.POST)
    @ApiOperation(value = "个人帐号申请成为公司帐号")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "memberid", value = "买家ID", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "shortname", value = "单位简称", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "companyname", value = "单位全称", required = true, paramType = "query", dataType = "string"),
//            @ApiImplicitParam(name = "companyno", value = "单位编号", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "province", value = "省", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "city", value = "市", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "citysmall", value = "区", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "address", value = "详细地址", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "legalperson", value = "法人代表", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "mobile", value = "联系手机", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "worktelephone", value = "单位电话", required = true, paramType = "query", dataType = "string"),
//            @ApiImplicitParam(name = "postaladdress", value = "通讯地址", required = true, paramType = "query", dataType = "string"),
//            @ApiImplicitParam(name = "zipcode", value = "邮编", required = false, paramType = "query", dataType = "string"),


            @ApiImplicitParam(name = "bankdeposit", value = "开户行", required = true, paramType = "query", dataType = "string"), //新加
            @ApiImplicitParam(name = "bankuser", value = "开户人", required = true, paramType = "query", dataType = "string"),//新加


            @ApiImplicitParam(name = "bankname", value = "开户银行", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "bankaccount", value = "银行帐号", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "taxregistrationcertificate", value = "纳税号", required = true, paramType = "query", dataType = "string"),

//            @ApiImplicitParam(name = "invoicetype", value = "发票类型", required = false, paramType = "query", dataType = "string"),

            @ApiImplicitParam(name = "methodsettingaccount", value = "结算方式", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "businesslicencenumberphoto", value = "营业执照", required = true, paramType = "query", dataType = "string"),
    })
    public BasicRet applyForCompany(BuyerCompanyInfo info, Model model) {
        BasicRet basicRet = new BasicRet();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        Long memberId = member.getId();

        BuyerCompanyInfo buyerCompanyInfo = buyerCompanyService.getBuyerCompanyInfoByMemberId(memberId);
        if (buyerCompanyInfo != null) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("之前已经升级为公司帐号了");
            return basicRet;
        }

        buyerCompanyService.applyForCompany(info);
        basicRet.setMessage("升级为公司帐号");
        basicRet.setResult(BasicRet.SUCCESS);
        return basicRet;
    }


    @RequestMapping(value = "/updateForCompany", method = RequestMethod.POST)
    @ApiOperation(value = "更改买家公司信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "province", value = "省", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "city", value = "市", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "citysmall", value = "区", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "address", value = "详细地址", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "mobile", value = "联系手机", required = true, paramType = "query", dataType = "string", readOnly = true),
            @ApiImplicitParam(name = "worktelephone", value = "单位电话", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "legalperson", value = "法人", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "methodsettingaccount", value = "结算方式", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "shortname", value = "单位简称", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "businesslicencenumberphoto", value = "营业执照", required = false, paramType = "query", dataType = "string"),
    })
    public BasicRet updateForCompany(String province,
                                     String city,
                                     String citysmall,
                                     String address,
                                     String shortname,
                                     @RequestParam(required = false, defaultValue = "") String mobile,
                                     @RequestParam(required = false, defaultValue = "") String legalperson,
                                     @RequestParam(required = false, defaultValue = "") String worktelephone,
                                     @RequestParam(required = false, defaultValue = "") String methodsettingaccount,
                                     @RequestParam(required = true,defaultValue = "") String businesslicencenumberphoto,
                                     Model model) {
        BasicRet basicRet = new BasicRet();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);


        BuyerCompanyInfo buyerCompanyInfo = buyerCompanyService.getBuyerCompanyInfoByMemberId(member.getId());
        if (buyerCompanyInfo == null) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("没有查询到公司信息");
            return basicRet;
        }

        if (member == null) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("没有查询到对应memberid的帐号信息");
            return basicRet;
        }

        buyerCompanyInfo.setProvince(province);
        buyerCompanyInfo.setCity(city);
        buyerCompanyInfo.setCitysmall(citysmall);
        buyerCompanyInfo.setAddress(address);
        buyerCompanyInfo.setMobile(mobile);
        buyerCompanyInfo.setWorktelephone(worktelephone);
        buyerCompanyInfo.setLegalperson(legalperson);
        buyerCompanyInfo.setMethodsettingaccount(methodsettingaccount);
        buyerCompanyInfo.setShortname(shortname);
        buyerCompanyInfo.setBusinesslicencenumberphoto(businesslicencenumberphoto);

        buyerCompanyInfo.setUpdatedate(new Date());
        buyerCompanyService.updateByPrimaryKeySelective(buyerCompanyInfo);

        //完善公司信息后，买家发票新增公司信息相关发票信息
        InvoiceInfo invoiceInfo = new InvoiceInfo();
        invoiceInfo.setDefaultbill((short) 1);
        invoiceInfo.setMemberid(member.getId());
        invoiceInfo.setCreatedate(new Date());
        invoiceInfo.setUpdatedate(new Date());
        invoiceInfo.setAvailable((short) 0);
        invoiceInfo.setInvoiceheadup(buyerCompanyInfo.getCompanyname());
        invoiceInfo.setBankofaccounts(buyerCompanyInfo.getBankname());
        invoiceInfo.setTexno(buyerCompanyInfo.getTaxregistrationcertificate());
        invoiceInfo.setAccount(buyerCompanyInfo.getBankaccount());
        invoiceInfo.setAddress(buyerCompanyInfo.getProvince()+""+buyerCompanyInfo.getCity()+""+buyerCompanyInfo.getCitysmall()+""+buyerCompanyInfo.getAddress());
        invoiceInfo.setReceiveaddress(buyerCompanyInfo.getProvince()+""+buyerCompanyInfo.getCity()+""+buyerCompanyInfo.getCitysmall()+""+buyerCompanyInfo.getAddress());
        invoiceInfo.setLinkman(buyerCompanyInfo.getBankuser());
        invoiceInfo.setPhone(mobile);
        invoiceService.updateInvoiceInfoByMemberid(member.getId());
        /*if(member.getCompany()){
            invoiceService.deleteInvoiceInfoByMemberid(member.getId());
        }*/
        invoiceService.addInvoiceInfo(invoiceInfo);

        basicRet.setMessage("修改成功");
        basicRet.setResult(BasicRet.SUCCESS);

        return basicRet;
    }


    private class BuyerCompanyRet extends BasicRet {
        private BuyerCompanyInfo data;

        public BuyerCompanyInfo getData() {
            return data;
        }

        public void setData(BuyerCompanyInfo data) {
            this.data = data;
        }
    }


    @RequestMapping(value = "/company/updateBasisInfo", method = RequestMethod.POST)
    @ApiOperation(value = "app修改公司的基础信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "shortname", value = "单位简称", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "companyname", value = "单位全称", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "province", value = "省", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "city", value = "市", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "citysmall", value = "区", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "address", value = "详细地址", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "worktelephone", value = "单位电话", required = true, paramType = "query", dataType = "string"),
    })
    public BasicExtRet updateCompanyBasisInfo(String shortname,
                                              String companyname,
                                              String province,
                                              String city,
                                              String citysmall,
                                              String address,
                                              String worktelephone,
                                              Model model) {
        BasicExtRet basicRet = new BasicExtRet();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

        if (member == null) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("没有查询到对应memberid的帐号信息");
            return basicRet;
        }
        BuyerCompanyInfo buyerCompanyInfo = buyerCompanyService.getBuyerCompanyInfoByMemberId(member.getId());
        if (buyerCompanyInfo == null) {
            buyerCompanyInfo = new BuyerCompanyInfo();
            buyerCompanyInfo.setMemberid(member.getId());
            buyerCompanyInfo.setShortname(shortname);
            buyerCompanyInfo.setCompanyname(companyname);
            buyerCompanyInfo.setProvince(province);
            buyerCompanyInfo.setCity(city);
            buyerCompanyInfo.setCitysmall(citysmall);
            buyerCompanyInfo.setAddress(address);
            buyerCompanyInfo.setWorktelephone(worktelephone);

            buyerCompanyInfo.setCreatedate(new Date());
            buyerCompanyInfo.setUpdatedate(new Date());
            buyerCompanyService.addBuyerCompanyInfo(buyerCompanyInfo);
            buyerCompanyInfo = buyerCompanyService.getBuyerCompanyInfoByMemberId(member.getId());
        } else {
            buyerCompanyInfo.setShortname(shortname);
            buyerCompanyInfo.setCompanyname(companyname);
            buyerCompanyInfo.setProvince(province);
            buyerCompanyInfo.setCity(city);
            buyerCompanyInfo.setCitysmall(citysmall);
            buyerCompanyInfo.setAddress(address);
            buyerCompanyInfo.setWorktelephone(worktelephone);

            buyerCompanyInfo.setUpdatedate(new Date());
            buyerCompanyService.updateByPrimaryKeySelective(buyerCompanyInfo);
        }

        basicRet.setData(buyerCompanyInfo);
        basicRet.setMessage("修改成功");
        basicRet.setResult(BasicRet.SUCCESS);
        return basicRet;
    }

    @RequestMapping(value = "/company/updateVipInfo", method = RequestMethod.POST)
    @ApiOperation(value = "app修改公司的高级信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bankname", value = "开户银行", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "bankaccount", value = "银行帐号", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "legalperson", value = "法人代表", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "mobile", value = "联系手机", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "taxregistrationcertificate", value = "纳税号", required = true, paramType = "query", dataType = "string"),
    })
    public BasicExtRet updateCompanyVipInfo(String bankname,
                                            String bankaccount,
                                            String legalperson,
                                            String mobile,
                                            String taxregistrationcertificate,
                                            Model model) {
        BasicExtRet basicRet = new BasicExtRet();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

        if (member == null) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("没有查询到对应memberid的帐号信息");
            return basicRet;
        }
        BuyerCompanyInfo buyerCompanyInfo = buyerCompanyService.getBuyerCompanyInfoByMemberId(member.getId());
        if (buyerCompanyInfo == null) {
            buyerCompanyInfo = new BuyerCompanyInfo();
            buyerCompanyInfo.setMemberid(member.getId());
            buyerCompanyInfo.setShortname("");
            buyerCompanyInfo.setCompanyname("");
            buyerCompanyInfo.setAddress("");
            buyerCompanyInfo.setCreatedate(new Date());
            buyerCompanyInfo.setUpdatedate(new Date());

            buyerCompanyInfo.setBankname(bankname);
            buyerCompanyInfo.setBankaccount(bankaccount);
            buyerCompanyInfo.setLegalperson(legalperson);
            buyerCompanyInfo.setMobile(mobile);
            buyerCompanyInfo.setTaxregistrationcertificate(taxregistrationcertificate);
            buyerCompanyService.addBuyerCompanyInfo(buyerCompanyInfo);
            buyerCompanyInfo = buyerCompanyService.getBuyerCompanyInfoByMemberId(member.getId());

        } else {
            buyerCompanyInfo.setBankname(bankname);
            buyerCompanyInfo.setBankaccount(bankaccount);
            buyerCompanyInfo.setLegalperson(legalperson);
            buyerCompanyInfo.setMobile(mobile);
            buyerCompanyInfo.setTaxregistrationcertificate(taxregistrationcertificate);

            buyerCompanyInfo.setUpdatedate(new Date());
            buyerCompanyService.updateByPrimaryKeySelective(buyerCompanyInfo);
        }
        basicRet.setData(buyerCompanyInfo);
        basicRet.setMessage("修改成功");
        basicRet.setResult(BasicRet.SUCCESS);
        return basicRet;
    }

    @RequestMapping(value = "/company/updateLicense", method = RequestMethod.POST)
    @ApiOperation(value = "app修改公司的执照")
    @ApiImplicitParam(name = "businesslicencenumberphoto", value = "营业执照", required = true, paramType = "query", dataType = "string")
    public BasicExtRet updateCompanyLicense(String businesslicencenumberphoto,
                                            Model model) {
        BasicExtRet basicRet = new BasicExtRet();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

        if (member == null) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("没有查询到对应memberid的帐号信息");
            return basicRet;
        }
        BuyerCompanyInfo buyerCompanyInfo = buyerCompanyService.getBuyerCompanyInfoByMemberId(member.getId());
        if (buyerCompanyInfo == null) {
            buyerCompanyInfo = new BuyerCompanyInfo();
            buyerCompanyInfo.setMemberid(member.getId());
            buyerCompanyInfo.setShortname("");
            buyerCompanyInfo.setCompanyname("");
            buyerCompanyInfo.setAddress("");
            buyerCompanyInfo.setCreatedate(new Date());
            buyerCompanyInfo.setUpdatedate(new Date());

            buyerCompanyInfo.setBusinesslicencenumberphoto(businesslicencenumberphoto);
            buyerCompanyService.addBuyerCompanyInfo(buyerCompanyInfo);
            buyerCompanyInfo = buyerCompanyService.getBuyerCompanyInfoByMemberId(member.getId());
        } else {
            buyerCompanyInfo.setBusinesslicencenumberphoto(businesslicencenumberphoto);
            buyerCompanyInfo.setUpdatedate(new Date());
            buyerCompanyService.updateByPrimaryKeySelective(buyerCompanyInfo);
        }
        basicRet.setData(buyerCompanyInfo);
        basicRet.setMessage("修改成功");
        basicRet.setResult(BasicRet.SUCCESS);
        return basicRet;
    }
}
