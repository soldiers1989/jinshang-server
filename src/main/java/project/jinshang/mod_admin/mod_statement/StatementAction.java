package project.jinshang.mod_admin.mod_statement;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import mizuki.project.core.restserver.config.BasicRet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.jinshang.common.constant.AdminAuthorityConst;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.common.utils.DateUtils;
import project.jinshang.common.utils.ExcelGen;
import project.jinshang.common.utils.ExcelUtils;
import project.jinshang.common.utils.StringUtils;
import project.jinshang.mod_admin.mod_cash.CashManageAction;
import project.jinshang.mod_admin.mod_statement.bean.dto.BuyerStamentQueryDto;
import project.jinshang.mod_admin.mod_statement.bean.dto.BuyerStatementDto;
import project.jinshang.mod_admin.mod_statement.service.StatementService;
import project.jinshang.mod_cash.bean.dto.BuyerCapitalAccountDto;
import project.jinshang.mod_cash.bean.dto.BuyerCapitalAccountQueryDto;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * 下单客户对账单
 *
 * @author xiazy
 * @create 2018-09-13 16:17
 **/
@RestController
@RequestMapping(value = "/rest/admin/statement")
@Api(tags = "下单客户对账单")
@SessionAttributes({AppConstant.ADMIN_SESSION_NAME})
@Transactional(rollbackFor = Exception.class)
public class StatementAction {
    @Autowired
    private StatementService statementService;

    @RequestMapping(value = "/accountList",method = RequestMethod.POST)
    @ApiOperation(value = "下单客户对账单（type:类别1收款、2发货、3退货、4违约金、5退款、6充值、7提现、8开票）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "statementStartTime",value = "开始日期",required = false,paramType = "query",dataType = "date"),
            @ApiImplicitParam(name = "statementEndTime",value = "结束日期",required = false,paramType = "query",dataType = "date"),
            @ApiImplicitParam(name = "memberid",value = "会员编号",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "username",value = "会员名称",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "companyname",value = "公司名称",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "invoicename",value = "开票名称",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "realname",value = "个人姓名",required = false,paramType = "query",dataType = "string"),
    })
//    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.BUYERSTATEMENT + "')")
    public BuyerStatementRet accountList(BuyerStamentQueryDto dto,
                                                               Model model){
        BuyerStatementRet buyerStatementRet=new BuyerStatementRet();
        if (dto.getStatementEndTime()!=null){
            Calendar calendar=Calendar.getInstance();
            calendar.setTime(dto.getStatementEndTime());
            calendar.add(Calendar.DAY_OF_YEAR,1);
            dto.setStatementEndTime(calendar.getTime());
        }
        Long memberid=dto.getMemberid();
        List<BuyerStatementDto> buyerStatementDtoList=new ArrayList<>();
        if((memberid==null||memberid<0) && StringUtils.isEmpty(dto.getUsername()) && StringUtils.isEmpty(dto.getCompanyname()) && StringUtils.isEmpty(dto.getRealname())){
            buyerStatementRet.setResult(BasicRet.ERR);
            buyerStatementRet.setMessage("会员编号、会员名称、公司名称、个人姓名必须要填写一项");
            return buyerStatementRet;
        }else {
            buyerStatementDtoList=statementService.listForBuyerStatement(dto);
        }
        buyerStatementRet.setMessage("查询成功");
        buyerStatementRet.setResult(BasicRet.SUCCESS);
        buyerStatementRet.setList(buyerStatementDtoList);
        return buyerStatementRet;
    }


    @RequestMapping(value = "/excelExport/accountList",method = RequestMethod.GET)
    @ApiOperation(value = "下单客户对账单（type:类别1收款、2发货、3退货、4违约金、5退款、6充值、7提现、8开票）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "statementStartTime",value = "开始日期",required = false,paramType = "query",dataType = "date"),
            @ApiImplicitParam(name = "statementEndTime",value = "结束日期",required = false,paramType = "query",dataType = "date"),
            @ApiImplicitParam(name = "memberid",value = "会员编号",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "username",value = "会员名称",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "companyname",value = "公司名称",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "invoicename",value = "开票名称",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "realname",value = "个人姓名",required = false,paramType = "query",dataType = "string"),
    })
//    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.BUYERSTATEMENT + "')")
    public ResponseEntity<InputStreamResource> excelExportAccountList(BuyerStamentQueryDto dto,
                                                           Model model) {
        List<Map<String, Object>> resList = new ArrayList<>();
        XSSFWorkbook workbook = null;
        try {
            String[] rowTitles = new String[]{"日期", "合同编号", "类别", "发货金额", "收款金额", "应收账款", "开票金额", "发票结余", "支付方式", "支付单号", "备注"};
            Date statementStartTime=dto.getStatementStartTime();
            if (dto.getStatementEndTime()!=null){
                Calendar calendar=Calendar.getInstance();
                calendar.setTime(dto.getStatementEndTime());
                calendar.add(Calendar.DAY_OF_YEAR,1);
                dto.setStatementEndTime(calendar.getTime());
            }
            Date statementEndTime=dto.getStatementEndTime();
            resList = statementService.excelExportListForAccount(dto);
            workbook = ExcelGen.common("", rowTitles, resList, null);
            workbook = ExcelUtils.insertRows(workbook, "Sheet0", 0, (short) 1, "搜索时间：");
            workbook = ExcelUtils.updateRows(workbook, "Sheet0", 0, (short) 2, (statementStartTime!= null ? (DateUtils.format(statementStartTime, "yyyy.MM.dd")) : "") + "-" + (statementEndTime != null ? (DateUtils.format(DateUtils.addDays(statementEndTime, -1), "yyyy.MM.dd")) : ""));
            workbook = ExcelUtils.updateRows(workbook, "Sheet0", 0, (short) 5, "会员ID：");
            if (resList!=null&&resList.size()>0){
                DateUtils.addDays(statementEndTime, 1);
                List<BuyerStatementDto> list= statementService.listForBuyerStatement(dto);
                workbook = ExcelUtils.updateRows(workbook, "Sheet0", 0, (short) 6, String.valueOf(list.get(1).getMemberid()));
            }
            workbook = ExcelUtils.updateRows(workbook, "Sheet0", 0,(short) 8,"会员全称：");
            workbook = ExcelUtils.updateRows(workbook, "Sheet0", 0,(short) 9,dto.getUsername()
            );
            if (workbook != null) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                workbook.write(baos);
                //System.out.println(baos.toByteArray().length);
                HttpHeaders headers = new HttpHeaders();
                headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
                headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", new String("买家对账单.xlsx".getBytes(), "iso-8859-1")));
                headers.add("Pragma", "no-cache");
                headers.add("Expires", "0");
                String contentType = "application/vnd.ms-excel";
                return ResponseEntity.ok().headers(headers).contentType(MediaType.parseMediaType(contentType))
                        .body(new InputStreamResource(new ByteArrayInputStream(baos.toByteArray())));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (workbook != null) {
                try {
                    workbook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }



    private  class BuyerStatementRet extends BasicRet{
        private List<BuyerStatementDto> list;

        public List<BuyerStatementDto> getList() {
            return list;
        }

        public void setList(List<BuyerStatementDto> list) {
            this.list = list;
        }
    }


}
