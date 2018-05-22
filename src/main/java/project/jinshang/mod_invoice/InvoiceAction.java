package project.jinshang.mod_invoice;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import mizuki.project.core.restserver.config.BasicRet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.mod_common.bean.BasicExtRet;
import project.jinshang.mod_invoice.bean.InvoiceInfo;
import project.jinshang.mod_invoice.service.InvoiceService;
import project.jinshang.mod_member.bean.Member;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/rest/buyer/invoiceInfo")
@SessionAttributes({AppConstant.MEMBER_SESSION_NAME})
@Api(tags = "发票信息模块", description = "发票信息")
@Transactional(rollbackFor = Exception.class)
public class InvoiceAction {

    @Autowired
    InvoiceService invoiceService;


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiOperation(value = "新增发票信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "invoiceheadup", value = "发票开头", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "bankofaccounts", value = "开户行", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "texno", value = "税号", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "account", value = "账号", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "address", value = "发票地址", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "receiveaddress", value = "收票地址", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "linkman", value = "联系人", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "phone", value = "电话", required = true, paramType = "query", dataType = "string"),
    })
    public BasicRet addInvoiceInfo(InvoiceInfo invoiceInfo,
                                   Model model) {
        BasicRet basicRet = new BasicRet();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        List<InvoiceInfo> invoiceInfos = invoiceService.getInvoiceInfoListByMemberId(member.getId());
        if (invoiceInfos == null || invoiceInfos.size() == 0) {
            invoiceInfo.setDefaultbill((short) 1);
        } else {
            invoiceInfo.setDefaultbill((short) 0);
        }

        if ((invoiceInfos != null || invoiceInfos.size() != 0) && member.getCompany()) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("公司账户新增失败");
        }else {

            invoiceInfo.setMemberid(member.getId());
            invoiceInfo.setCreatedate(new Date());
            invoiceInfo.setUpdatedate(new Date());
            invoiceInfo.setAvailable((short) 0);//
            invoiceService.addInvoiceInfo(invoiceInfo);
            basicRet.setResult(BasicRet.SUCCESS);
            basicRet.setMessage("新增成功");
        }
        return basicRet;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ApiOperation(value = "删除发票信息")
    @ApiImplicitParam(name = "invoiceId", value = "发票信息id", required = true, paramType = "query", dataType = "int")
    public BasicRet deleteInvoiceInfo(@RequestParam(value = "invoiceId") Long invoiceId) {
        BasicRet basicRet = new BasicRet();
        InvoiceInfo invoiceInfo = invoiceService.getInvoiceInfoById(invoiceId);
        if (invoiceInfo == null) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("不存在该发票信息");
        } else if (invoiceInfo.getDefaultbill() == 1) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("默认发票不可删除");
        } else {
            invoiceService.deleteInvoiceInfoById(invoiceId);
            basicRet.setResult(BasicRet.SUCCESS);
            basicRet.setMessage("删除成功");
        }
        return basicRet;
    }


    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ApiOperation(value = "修改发票信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "发票信息id", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "invoiceheadup", value = "发票开头", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "bankofaccounts", value = "开户行", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "texno", value = "税号", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "account", value = "账号", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "address", value = "发票地址", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "receiveaddress", value = "收票地址", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "linkman", value = "联系人", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "phone", value = "电话", required = true, paramType = "query", dataType = "string"),
    })
    public BasicRet editInvoiceInfo(InvoiceInfo invoiceInfo) {
        BasicRet basicRet = new BasicRet();
        InvoiceInfo invoiceInfoFromSql = invoiceService.getInvoiceInfoById(invoiceInfo.getId());
        if (invoiceInfoFromSql == null) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("不存在该发票信息");
        } else {
            invoiceInfo.setUpdatedate(new Date());
            invoiceService.editInvoiceInfo(invoiceInfo);
            basicRet.setResult(BasicRet.SUCCESS);
            basicRet.setMessage("修改成功");
        }
        return basicRet;
    }

    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    @ApiOperation(value = "发票信息详情")
    @ApiImplicitParam(name = "invoiceId", value = "发票信息id", required = true, paramType = "query", dataType = "int")
    public BasicRet getInvoiceInfoDetail(@RequestParam(value = "invoiceId") Long invoiceId) {
        BasicExtRet basicRet = new BasicExtRet();
        InvoiceInfo invoiceInfo = invoiceService.getInvoiceInfoById(invoiceId);
        if (invoiceInfo == null) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("不存在该发票信息");
        } else {
            basicRet.setResult(BasicRet.SUCCESS);
            basicRet.setMessage("获取成功");
            basicRet.setData(invoiceInfo);
        }
        return basicRet;
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ApiOperation(value = "发票信息列表")
    public BasicRet getInvoiceInfoList(Model model) {
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        BasicExtRet basicRet = new BasicExtRet();
        List<InvoiceInfo> invoiceInfoList = invoiceService.getInvoiceInfoListByMemberId(member.getId());
        basicRet.setData(invoiceInfoList);
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("获取成功");
        return basicRet;
    }

    @RequestMapping(value = "/default/detail", method = RequestMethod.POST)
    @ApiOperation(value = "获取默认发票信息")
    public BasicRet getInvoiceInfoDefault(Model model) {
        BasicExtRet basicRet = new BasicExtRet();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        InvoiceInfo invoiceInfo = invoiceService.getDefaultInvoiceInfoByMemberId(member.getId());
        basicRet.setData(invoiceInfo);
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("获取成功");
        return basicRet;
    }

    @RequestMapping(value = "/change/default", method = RequestMethod.POST)
    @ApiOperation(value = "修改默认发票信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "newInvoiceId", value = "新的默认发票信息id", required = true, paramType = "query", dataType = "int"),
    })

    public BasicRet changeInvoiceInfoDefault(@RequestParam(value = "newInvoiceId") Long newInvoiceId,
                                             Model model) {
        BasicRet basicRet = new BasicRet();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        InvoiceInfo oldInvoiceInfo = invoiceService.getDefaultInvoiceInfoByMemberId(member.getId());

        if (!oldInvoiceInfo.getId().equals(newInvoiceId)) {
            oldInvoiceInfo.setDefaultbill((short) 0);

            InvoiceInfo newInvoiceInfo = new InvoiceInfo();
            newInvoiceInfo.setId(newInvoiceId);
            newInvoiceInfo.setDefaultbill((short) 1);

            invoiceService.editInvoiceInfo(oldInvoiceInfo);
            invoiceService.editInvoiceInfo(newInvoiceInfo);
        }
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("修改成功");
        return basicRet;
    }

    @RequestMapping(value = "/app/add", method = RequestMethod.POST)
    @ApiOperation(value = "app新增发票信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "invoiceheadup", value = "发票开头", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "bankofaccounts", value = "开户行", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "texno", value = "税号", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "account", value = "账号", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "address", value = "发票地址", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "receiveaddress", value = "收票地址", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "linkman", value = "联系人", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "phone", value = "电话", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "defaultbill", value = "是否是默认信息 ， 0：不是  1：是", required = true, paramType = "query", dataType = "int"),
    })
    public BasicRet appAddInvoiceInfo(InvoiceInfo invoiceInfo,
                                      Model model) {
        BasicRet basicRet = new BasicRet();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

        invoiceInfo.setCreatedate(new Date());
        invoiceInfo.setUpdatedate(new Date());
        invoiceInfo.setMemberid(member.getId());
        invoiceInfo.setAvailable((short) 0);//

        List<InvoiceInfo> invoiceInfos = invoiceService.getInvoiceInfoListByMemberId(member.getId());

        if (invoiceInfos.size() == 0) {
            invoiceInfo.setDefaultbill((short) 1);
        } else {
            if (invoiceInfo.getDefaultbill() == 1) {
                InvoiceInfo defaultInvoiceInfo = invoiceService.getDefaultInvoiceInfoByMemberId(member.getId());
                defaultInvoiceInfo.setDefaultbill((short) 0);
                defaultInvoiceInfo.setUpdatedate(new Date());
                invoiceService.editInvoiceInfo(defaultInvoiceInfo);
            }
        }
        invoiceService.addInvoiceInfo(invoiceInfo);
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("新增成功");
        return basicRet;
    }

    @RequestMapping(value = "/app/edit", method = RequestMethod.POST)
    @ApiOperation(value = "app修改发票信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "发票信息id", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "invoiceheadup", value = "发票开头", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "bankofaccounts", value = "开户行", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "texno", value = "税号", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "account", value = "账号", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "address", value = "收票地址", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "receiveaddress", value = "收票地址", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "linkman", value = "联系人", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "phone", value = "电话", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "defaultbill", value = "是否是默认信息 ， 0：不是  1：是", required = true, paramType = "query", dataType = "int"),
    })
    public BasicRet appEditInvoiceInfo(InvoiceInfo invoiceInfo, Model model) {
        BasicRet basicRet = new BasicRet();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        InvoiceInfo invoiceInfoFromSql = invoiceService.getInvoiceInfoById(invoiceInfo.getId());
        if (invoiceInfoFromSql == null) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("不存在该发票信息");
        } else if (invoiceInfoFromSql.getDefaultbill() == 1) {
            if (invoiceInfo.getDefaultbill() == 0) {
                basicRet.setResult(BasicRet.ERR);
                basicRet.setMessage("必须有一个默认的发票");
            } else {
                invoiceInfo.setUpdatedate(new Date());
                invoiceService.editInvoiceInfo(invoiceInfo);
            }
        } else {
            if (invoiceInfo.getDefaultbill() == 1) {
                InvoiceInfo defaultInvoice = invoiceService.getDefaultInvoiceInfoByMemberId(member.getId());
                defaultInvoice.setDefaultbill((short) 0);
                defaultInvoice.setUpdatedate(new Date());
                invoiceService.editInvoiceInfo(defaultInvoice);
            }
            invoiceInfo.setUpdatedate(new Date());
            invoiceService.editInvoiceInfo(invoiceInfo);
            basicRet.setResult(BasicRet.SUCCESS);
            basicRet.setMessage("修改成功");
        }
        return basicRet;
    }


}
