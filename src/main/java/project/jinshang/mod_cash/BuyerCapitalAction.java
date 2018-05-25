package project.jinshang.mod_cash;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import mizuki.project.core.restserver.config.BasicRet;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.jinshang.common.bean.PageRet;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.common.utils.DateUtils;
import project.jinshang.common.utils.ExcelGen;
import project.jinshang.mod_cash.bean.BuyerCapital;
import project.jinshang.mod_cash.bean.dto.BuyerCapitalQueryDto;
import project.jinshang.mod_cash.service.BuyerCapitalService;
import project.jinshang.mod_member.bean.Member;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/rest/buyer")
@Api(tags = "买家我的账户资金明细", description = "买家资金明细查询（充值消费提现授信）")
@SessionAttributes(AppConstant.MEMBER_SESSION_NAME)
public class BuyerCapitalAction {

    @Autowired
    private BuyerCapitalService buyerCapitalService;


    @RequestMapping(value = "/cash/list", method = RequestMethod.POST)
    @ApiOperation(value = "买家资金明细查询（capitaltype:类别0=消费1=充值2=退款3=提现4=授信5=授信还款）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tradetimeStart", value = "开始时间", required = false, paramType = "query", dataType = "date"),
            @ApiImplicitParam(name = "tradetimeEnd", value = "结束时间", required = false, paramType = "query", dataType = "date"),
            @ApiImplicitParam(name = "capitaltype", value = "类别{0=消费1=充值2=退款3=提现4=授信5=授信还款}", defaultValue = "-1", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "tradeno", value = "交易编号", defaultValue = "", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "orderno", value = "订单号", defaultValue = "", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "presentationnumber", value = "提现单号", defaultValue = "", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "paytype", value = "支付方式{0=支付宝1=微信2=银行卡3=余额4=授信}", defaultValue = "-1", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "rechargeperform", value = "充值方式{0=微信1=支付宝2=线下平台}", defaultValue = "-1", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "withdrawtype", value = "提现方式{1=微信2=支付宝3=银行卡}", defaultValue = "-1", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "rechargestate", value = "状态{0=待处理1=成功2=失败3=待审核4=审核通过5=审核不通过}", defaultValue = "-1", required = false, paramType = "query", dataType = "int"),
    })
    public PageRet list(BuyerCapitalQueryDto dto,
                        @RequestParam(required = true, defaultValue = "1") int pageNo,
                        @RequestParam(required = true, defaultValue = "20") int pageSize,
                        Model model) {
        PageRet pageRet = new PageRet();

        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

        dto.setMemberid(member.getId());

        PageInfo pageInfo = buyerCapitalService.list(pageNo, pageSize, dto);

        pageRet.setMessage("查询成功");
        pageRet.setResult(BasicRet.SUCCESS);
        pageRet.data.setPageInfo(pageInfo);
        return pageRet;

    }


    @RequestMapping(value = "/exportExcel/cash/list/test",method = RequestMethod.GET)
    @ApiOperation(value = "买家资金明细导出excel（capitaltype:类别0=消费1=充值2=退款3=提现4=授信5=授信还款）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tradetimeStart", value = "开始时间", required = false, paramType = "query", dataType = "date"),
            @ApiImplicitParam(name = "tradetimeEnd", value = "结束时间", required = false, paramType = "query", dataType = "date"),
            @ApiImplicitParam(name = "capitaltype", value = "类别{0=消费1=充值2=退款3=提现4=授信5=授信还款}", defaultValue = "-1", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "tradeno", value = "交易编号", defaultValue = "", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "orderno", value = "订单号", defaultValue = "", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "presentationnumber", value = "提现单号", defaultValue = "", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "paytype", value = "支付方式{0=支付宝1=微信2=银行卡3=余额4=授信}", defaultValue = "-1", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "rechargeperform", value = "充值方式{0=微信1=支付宝2=线下平台}", defaultValue = "-1", required = false, paramType = "query", dataType = "int"),
    })
    public ResponseEntity<InputStreamResource> list(BuyerCapitalQueryDto dto, Model model){
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        dto.setMemberid(member.getId());

        List<Map<String,Object>> resList =  buyerCapitalService.listConsumeForAdminExportExcel(dto);

        XSSFWorkbook workbook = null;
        String name = "买家导出账号明细";
        String[] rowTitles =  new String[]{"时间","买家编号","公司名称","会员名称","类型","金额","支付方式","单号","状态"};
        /*
        String name = "";
        String[] rowTitles = null;
        switch (capitaltype){
            case 0:
                // name = "买家导出账号明细";
                // rowTitles =  new String[]{"时间","交易号","订单号","类型","订单金额","支付方式"};
                name = "买家导出消费明细";
                rowTitles =  new String[]{"时间","交易号","订单号","类型","订单金额","支付方式"};
                break;
            case 1:
                name = "买家导出充值明细";
                rowTitles =  new String[]{"时间","充值单号","订单金额","方式","状态"};
                break;
            case 2:
                name = "买家导出退款明细";
                rowTitles =  new String[]{"时间","交易号","订单号","类型","订单金额","支付方式"};
                break;
            case 3:
                name = "买家导出提现明细";
                rowTitles =  new String[]{"时间","充值单号","订单金额","方式","状态"};
                break;
            case 4:
                name = "买家导出授信明细";
                rowTitles =  new String[]{"时间","交易号","订单号","订单金额","类型","方式"};
                break;
            case 5:
                name = "买家导出授信明细";
                rowTitles =  new String[]{"时间","交易号","订单号","订单金额","类型","方式"};
                break;
        }*/

        try {
            workbook = ExcelGen.common(name,rowTitles,resList,null);
            if(workbook!=null){
                ByteArrayOutputStream baos=new ByteArrayOutputStream();
                workbook.write(baos);
                System.out.println(baos.toByteArray().length);
                HttpHeaders headers = new HttpHeaders();
                headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
                headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", new String((name+".xlsx").getBytes(),"iso-8859-1")));
                headers.add("Pragma", "no-cache");
                headers.add("Expires", "0");
                String contentType = "application/vnd.ms-excel";
                return ResponseEntity.ok()
                        .headers(headers).contentType(MediaType.parseMediaType(contentType))
                        .body(new InputStreamResource(new ByteArrayInputStream(baos.toByteArray())));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(workbook != null){
                try {
                    workbook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @RequestMapping(value = "/cash/detail", method = RequestMethod.POST)
    @ApiOperation(value = "买家资金详细信息")
    public BuyerCapitalRet detail(@RequestParam(required = true) long id, Model model) {
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        BuyerCapitalRet buyerCapitalRet = new BuyerCapitalRet();

        BuyerCapital buyerCapital = buyerCapitalService.getById(id);
        if (buyerCapital == null) {
            buyerCapitalRet.setMessage("资金明细不存在");
            buyerCapitalRet.setResult(BasicRet.ERR);
            return buyerCapitalRet;
        }

        if (!buyerCapital.getMemberid().equals(member.getId())) {
            buyerCapitalRet.setMessage("资金明细不属于你");
            buyerCapitalRet.setResult(BasicRet.ERR);
            return buyerCapitalRet;
        }

        buyerCapitalRet.data = buyerCapital;
        buyerCapitalRet.setResult(BasicRet.SUCCESS);
        return buyerCapitalRet;
    }



    @PostMapping("/breakContract/listLogs")
    @ApiOperation("违约金明细")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "违约类型 6=买家违约10=卖家违约",name = "flag",required = true,paramType = "query",dataType = "int",defaultValue = "1"),
            @ApiImplicitParam(value = "卖家名称",name = "sellername",required = false,paramType = "query",dataType = "string",defaultValue = ""),
            @ApiImplicitParam(value = "买家名称",name = "buyername",required = false,paramType = "query",dataType = "string",defaultValue = ""),
            @ApiImplicitParam(value = "交易号",name = "tradeno",required = false,paramType = "query",dataType = "string",defaultValue = ""),
            @ApiImplicitParam(value = "金额",name = "capital",required = false,paramType = "query",dataType = "double",defaultValue = ""),
            @ApiImplicitParam(value = "开始日期",name = "tradetimeStart",required = false,paramType = "query",dataType = "date",defaultValue = ""),
            @ApiImplicitParam(value = "结束日期",name = "tradetimeEnd",required = false,paramType = "query",dataType = "date",defaultValue = ""),
    })
    public PageRet breakContractListLogs(@RequestParam(required = true) short flag,
                                         @RequestParam(required = false,defaultValue = "") String sellername,
                                         @RequestParam(required = false,defaultValue = "") String buyername,
                                         @RequestParam(required = false,defaultValue = "") String tradeno,
                                         @RequestParam(required = false) BigDecimal capital,
                                         @RequestParam(required = false) Date tradetimeStart,
                                         @RequestParam(required = false) Date tradetimeEnd,
                                         @RequestParam(required = true,defaultValue = "1") int pageNo,
                                         @RequestParam(required = true,defaultValue = "20") int pageSize,Model model){
        PageRet pageRet =  new PageRet();

        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

        Map<String,Object> queryMap =  new HashMap<>();
        queryMap.put("memberid",member.getId());
        queryMap.put("flag",flag);
        queryMap.put("sellername",sellername);
        queryMap.put("buyername",buyername);
        queryMap.put("tradeno",tradeno);
        queryMap.put("capital",capital);
        queryMap.put("tradetimeStart",tradetimeStart);
        if(tradetimeEnd != null) {
            queryMap.put("tradetimeEnd", DateUtils.addDays(tradetimeEnd,1));
        }

        PageInfo pageInfo = buyerCapitalService.breakContractListLogs(queryMap,pageNo,pageSize);

        pageRet.data.setPageInfo(pageInfo);
        pageRet.setResult(BasicRet.SUCCESS);
        return  pageRet;
    }



    @GetMapping("/exportExcel/breakContract/listLogs")
    @ApiOperation("后台导出违约金明细")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "违约类型 6=买家违约10=卖家违约",name = "flag",required = true,paramType = "query",dataType = "int",defaultValue = "1"),
            @ApiImplicitParam(value = "卖家名称",name = "sellername",required = false,paramType = "query",dataType = "string",defaultValue = ""),
            @ApiImplicitParam(value = "买家名称",name = "buyername",required = false,paramType = "query",dataType = "string",defaultValue = ""),
            @ApiImplicitParam(value = "交易号",name = "tradeno",required = false,paramType = "query",dataType = "string",defaultValue = ""),
            @ApiImplicitParam(value = "金额",name = "capital",required = false,paramType = "query",dataType = "double",defaultValue = ""),
            @ApiImplicitParam(value = "开始日期",name = "tradetimeStart",required = false,paramType = "query",dataType = "date",defaultValue = ""),
            @ApiImplicitParam(value = "结束日期",name = "tradetimeEnd",required = false,paramType = "query",dataType = "date",defaultValue = ""),
    })
    public ResponseEntity<InputStreamResource> breakContractListLogsForExcel(@RequestParam(required = true) short flag,
                                                                             @RequestParam(required = false,defaultValue = "") String sellername,
                                                                             @RequestParam(required = false,defaultValue = "") String buyername,
                                                                             @RequestParam(required = false,defaultValue = "") String tradeno,
                                                                             @RequestParam(required = false) BigDecimal  capital,
                                                                             @RequestParam(required = false) Date tradetimeStart,
                                                                             @RequestParam(required = false) Date tradetimeEnd,Model model
    ){
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        Map<String,Object> queryMap =  new HashMap<>();
        queryMap.put("memberid",member.getId());
        queryMap.put("flag",flag);
        queryMap.put("sellername",sellername);
        queryMap.put("buyername",buyername);
        queryMap.put("tradeno",tradeno);
        queryMap.put("capital",capital);
        queryMap.put("tradetimeStart",tradetimeStart);
        if(tradetimeEnd != null) {
            queryMap.put("tradetimeEnd", DateUtils.addDays(tradetimeEnd,1));
        }

        List<Map<String,Object>> resList  = buyerCapitalService.breakContractListLogsForBuyerExcel(queryMap);

        String name =  "";
        if(flag == 6){
            name =  "买家违约列表";
        }else if(flag == 10){
            name =  "卖家违约列表";
        }

        String[] rowTitles = new String[]{"时间","客户","卖方","交易号","类型","合同金额","违约事由","违约金额"};

        XSSFWorkbook workbook = null;
        try {
            workbook = ExcelGen.common(name,rowTitles,resList,null);
            if(workbook!=null){
                ByteArrayOutputStream baos=new ByteArrayOutputStream();
                workbook.write(baos);
                System.out.println(baos.toByteArray().length);
                HttpHeaders headers = new HttpHeaders();
                headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
                headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", new String((name+".xlsx").getBytes(),"iso-8859-1")));
                headers.add("Pragma", "no-cache");
                headers.add("Expires", "0");
                String contentType = "application/vnd.ms-excel";
                return ResponseEntity.ok()
                        .headers(headers).contentType(MediaType.parseMediaType(contentType))
                        .body(new InputStreamResource(new ByteArrayInputStream(baos.toByteArray())));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(workbook != null){
                try {
                    workbook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }




        return  null;
    }



    private class BuyerCapitalRet extends BasicRet {
        private BuyerCapital data;

        public BuyerCapital getData() {
            return data;
        }

        public void setData(BuyerCapital data) {
            this.data = data;
        }
    }

}
