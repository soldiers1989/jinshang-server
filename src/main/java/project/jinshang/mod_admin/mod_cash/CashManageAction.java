package project.jinshang.mod_admin.mod_cash;

import com.github.pagehelper.PageInfo;
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
import project.jinshang.common.bean.PageRet;
import project.jinshang.common.constant.AdminAuthorityConst;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.common.constant.BuyerCapitalConst;
import project.jinshang.common.constant.Quantity;
import project.jinshang.common.utils.DateUtils;
import project.jinshang.common.utils.ExcelGen;
import project.jinshang.common.utils.GenerateNo;
import project.jinshang.common.utils.StringUtils;
import project.jinshang.mod_admin.mod_cash.dto.AdvanceCapitalQueryDto;
import project.jinshang.mod_admin.mod_cash.dto.AdvanceCapitalSellerQueryDto;
import project.jinshang.mod_cash.BuyerCapitalAction;
import project.jinshang.mod_cash.bean.BuyerCapital;
import project.jinshang.mod_cash.bean.SalerCapital;
import project.jinshang.mod_cash.bean.dto.*;
import project.jinshang.mod_cash.service.BuyerCapitalService;
import project.jinshang.mod_cash.service.SalerCapitalService;
import project.jinshang.mod_member.bean.Admin;
import project.jinshang.mod_member.bean.Member;
import project.jinshang.mod_member.service.MemberService;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

/**
 * create : wyh
 * date : 2017/11/21
 */


@RestController
@RequestMapping("/rest/admin/cash")
@Api(tags = "后台资金充值、提现")
@SessionAttributes({AppConstant.ADMIN_SESSION_NAME})
@Transactional(rollbackFor = Exception.class)
public class CashManageAction {

    @Autowired
    private MemberService memberService;

    @Autowired
    private BuyerCapitalService buyerCapitalService;

    @Autowired
    private SalerCapitalService salerCapitalService;


    @RequestMapping(value = "/sellerList",method = RequestMethod.POST)
    @ApiOperation(value = "买家资金明细查询（类别0=消费1=充值2=退款3=提现4=授信5=授信还款6=违约金7=远期定金8=远期余款9=远期全款）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tradetimeStart",value = "开始时间",required = false,paramType = "query",dataType = "date"),
            @ApiImplicitParam(name = "tradetimeEnd",value = "结束时间",required = false,paramType = "query",dataType = "date"),
            @ApiImplicitParam(name = "capitaltype",value = "资金类型0=订单金额1=上架保证金2=下架保证金3=退款金额4=充值5=提现6=违约金",defaultValue = "-1",required = false,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "tradeno",value = "交易编号",defaultValue = "",required = false,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "paytype",value = "支付方式{0=支付宝1=微信2=银行卡3=余额4=授信}",defaultValue = "-1",required = false,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "rechargeperform",value = "平台0=微信1=支付宝2=线下平台3=授信4=银行卡",defaultValue = "-1",required = false,paramType = "query",dataType = "int"),

            @ApiImplicitParam(value = "状态0=待处理1=成功2=失败3=待审核4=审核通过5=审核不通过",name = "rechargestate",required = false,paramType = "query",dataType = "int",defaultValue = "-1"),
            @ApiImplicitParam(value = "用户名",name = "username",required = false,paramType = "query",dataType = "string",defaultValue = ""),
            @ApiImplicitParam(value = "公司名",name = "companyname",required = false,paramType = "query",dataType = "string",defaultValue = ""),
            @ApiImplicitParam(value = "店铺名称",name = "shopname",required = false,paramType = "query",dataType = "string",defaultValue = ""),
            @ApiImplicitParam(value = "提现单号",name = "presentationnumber",required = false,paramType = "query",dataType = "string",defaultValue = ""),
            @ApiImplicitParam(value = "充值单号",name = "rechargenumber",required = false,paramType = "query",dataType = "string",defaultValue = ""),
            @ApiImplicitParam(value = "交易时间",name = "tradetimeStart",required = false,paramType = "query",dataType = "date"),
            @ApiImplicitParam(value = "交易时间",name = "tradetimeEnd",required = false,paramType = "query",dataType = "date"),
            @ApiImplicitParam(value = "交易金额区间",name = "capitalStart",required = false,paramType = "query",dataType = "double"),
            @ApiImplicitParam(value = "交易金额区间",name = "capitalEnd",required = false,paramType = "query",dataType = "double"),

    })
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.FINANCIALDETAILS + "')")
    public PageRet sellerList(SalerCapitalQueryDto dto,
                        @RequestParam(required = true,defaultValue = "1") int pageNo,
                        @RequestParam(required = true,defaultValue = "20") int pageSize,
                        Model model){
        PageRet pageRet = new PageRet();
        PageInfo pageInfo =  salerCapitalService.list(pageNo,pageSize,dto);

        pageRet.setMessage("查询成功");
        pageRet.setResult(BasicRet.SUCCESS);
        pageRet.data.setPageInfo(pageInfo);
        return pageRet;

    }





    @RequestMapping(value = "/buyerList",method = RequestMethod.POST)
    @ApiOperation(value = "买家资金明细查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tradetimeStart",value = "开始时间",required = false,paramType = "query",dataType = "date"),
            @ApiImplicitParam(name = "tradetimeEnd",value = "结束时间",required = false,paramType = "query",dataType = "date"),
            @ApiImplicitParam(name = "capitaltype",value = "类别{0=消费1=充值2=退款3=提现4=授信5=授信还款}",defaultValue = "-1",required = false,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "tradeno",value = "交易编号",defaultValue = "",required = false,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "paytype",value = "支付方式{0=支付宝1=微信2=银行卡3=余额4=授信}",defaultValue = "-1",required = false,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "rechargeperform",value = "充值方式{0=微信1=支付宝2=线下平台}",defaultValue = "-1",required = false,paramType = "query",dataType = "int"),
            @ApiImplicitParam(value = "状态0=待处理1=成功2=失败3=待审核4=审核通过5=审核不通过",name = "rechargestate",required = false,paramType = "query",dataType = "int",defaultValue = "-1"),
            @ApiImplicitParam(value = "用户名",name = "username",required = false,paramType = "query",dataType = "string",defaultValue = ""),
            @ApiImplicitParam(value = "公司名",name = "companyname",required = false,paramType = "query",dataType = "string",defaultValue = ""),
            @ApiImplicitParam(value = "店铺名称",name = "shopname",required = false,paramType = "query",dataType = "string",defaultValue = ""),
            @ApiImplicitParam(value = "提现单号",name = "presentationnumber",required = false,paramType = "query",dataType = "string",defaultValue = ""),
            @ApiImplicitParam(value = "充值单号",name = "rechargenumber",required = false,paramType = "query",dataType = "string",defaultValue = ""),
            @ApiImplicitParam(value = "交易时间",name = "tradetimeStart",required = false,paramType = "query",dataType = "date"),
            @ApiImplicitParam(value = "交易时间",name = "tradetimeEnd",required = false,paramType = "query",dataType = "date"),
            @ApiImplicitParam(value = "交易金额区间",name = "capitalStart",required = false,paramType = "query",dataType = "double"),
            @ApiImplicitParam(value = "交易金额区间",name = "capitalEnd",required = false,paramType = "query",dataType = "double"),
    })
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.FINANCIALDETAILS + "')")
    public PageRet buyerList(BuyerCapitalQueryDto dto,
                        @RequestParam(required = true,defaultValue = "1") int pageNo,
                        @RequestParam(required = true,defaultValue = "20") int pageSize,
                        Model model){
        PageRet pageRet = new PageRet();

        PageInfo pageInfo =  buyerCapitalService.listConsume(dto,pageNo,pageSize);

        pageRet.setMessage("查询成功");
        pageRet.setResult(BasicRet.SUCCESS);
        pageRet.data.setPageInfo(pageInfo);
        return pageRet;

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
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.FINANCIALDETAILS + "')")
    public PageRet breakContractListLogs(@RequestParam(required = true) short flag,
                                         @RequestParam(required = false,defaultValue = "") String sellername,
                                         @RequestParam(required = false,defaultValue = "") String buyername,
                                         @RequestParam(required = false,defaultValue = "") String tradeno,
                                         @RequestParam(required = false) BigDecimal  capital,
                                         @RequestParam(required = false) Date tradetimeStart,
                                         @RequestParam(required = false) Date tradetimeEnd,
                                         @RequestParam(required = true,defaultValue = "1") int pageNo,
                                         @RequestParam(required = true,defaultValue = "20") int pageSize){
        PageRet pageRet =  new PageRet();

        Map<String,Object> queryMap =  new HashMap<>();
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
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.FINANCIALDETAILS + "')")
    public ResponseEntity<InputStreamResource> breakContractListLogsForExcel(@RequestParam(required = true) short flag,
                                         @RequestParam(required = false,defaultValue = "") String sellername,
                                         @RequestParam(required = false,defaultValue = "") String buyername,
                                         @RequestParam(required = false,defaultValue = "") String tradeno,
                                         @RequestParam(required = false) BigDecimal  capital,
                                         @RequestParam(required = false) Date tradetimeStart,
                                         @RequestParam(required = false) Date tradetimeEnd
                                         ){

        Map<String,Object> queryMap =  new HashMap<>();
        queryMap.put("flag",flag);
        queryMap.put("sellername",sellername);
        queryMap.put("buyername",buyername);
        queryMap.put("tradeno",tradeno);
        queryMap.put("capital",capital);
        queryMap.put("tradetimeStart",tradetimeStart);
        if(tradetimeEnd != null) {
            queryMap.put("tradetimeEnd", DateUtils.addDays(tradetimeEnd,1));
        }

        List<Map<String,Object>> resList  = buyerCapitalService.breakContractListLogsForAdminExcel(queryMap);

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











    @RequestMapping(value = "/userCapital/listLogs",method = RequestMethod.POST)
    @ApiOperation("买家，卖家资金流水明细")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "买家卖家类型[1=买家，2=卖家]",name = "searchType",required = true,paramType = "query",dataType = "int",defaultValue = "1"),
            @ApiImplicitParam(value = "类别 买家[类别0=消费1=充值2=退款3=提现4=授信5=授信还款6=违约金7=远期定金8=远期余款9=远期全款] 卖家[资金类型0=订单金额1=上架保证金2=下架保证金3=退款金额4=充值5=提现6=买家违约金7=卖家违约金8=余款9=全款10=定金]",name = "capitaltype",required = false,paramType = "query",dataType = "int",defaultValue = "-1"),
            @ApiImplicitParam(value = "状态0=待处理1=成功2=失败3=待审核4=审核通过5=审核不通过",name = "rechargestate",required = false,paramType = "query",dataType = "int",defaultValue = "-1"),
            @ApiImplicitParam(value = "充值平台0=微信1=支付宝2=线下平台3=银行卡",name = "rechargeperform",required = false,paramType = "query",dataType = "int",defaultValue = "-1"),
            @ApiImplicitParam(value = "支付方式0=支付宝1=微信2=银行卡3=余额4=授信",name = "paytype",required = false,paramType = "query",dataType = "int",defaultValue = "-1"),
            @ApiImplicitParam(value = "用户名id",name = "memberid",required = false,paramType = "query",dataType = "int"),
            @ApiImplicitParam(value = "用户名",name = "username",required = false,paramType = "query",dataType = "string",defaultValue = ""),
            @ApiImplicitParam(value = "会员姓名",name = "realname",required = false,paramType = "query",dataType = "string",defaultValue = ""),
            @ApiImplicitParam(value = "公司名",name = "companyname",required = false,paramType = "query",dataType = "string",defaultValue = ""),
            @ApiImplicitParam(value = "店铺名称",name = "shopname",required = false,paramType = "query",dataType = "string",defaultValue = ""),
            @ApiImplicitParam(value = "提现单号",name = "presentationnumber",required = false,paramType = "query",dataType = "string",defaultValue = ""),
            @ApiImplicitParam(value = "充值单号",name = "rechargenumber",required = false,paramType = "query",dataType = "string",defaultValue = ""),
            @ApiImplicitParam(value = "交易单号",name = "tradeno",required = false,paramType = "query",dataType = "string",defaultValue = ""),
            @ApiImplicitParam(value = "交易时间",name = "tradetimeStart",required = false,paramType = "query",dataType = "date"),
            @ApiImplicitParam(value = "交易时间",name = "tradetimeEnd",required = false,paramType = "query",dataType = "date"),
            @ApiImplicitParam(value = "交易金额区间",name = "capitalStart",required = false,paramType = "query",dataType = "double"),
            @ApiImplicitParam(value = "交易金额区间",name = "capitalEnd",required = false,paramType = "query",dataType = "double"),
            @ApiImplicitParam(value = "订单号",name = "orderno",required = false,paramType = "query",dataType = "string",defaultValue = ""),
            @ApiImplicitParam(value = "卖家提现方式 0=全部 1=余额提现，2=货款提现",name = "withdrawtype",required = false,paramType = "query",dataType = "string",defaultValue = ""),
    })
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.FINANCIALDETAILS + "')")
    public  CashRet userCapitalListLogs(@RequestParam(required =  true,defaultValue = "1") short searchType,
                                        @RequestParam(required =  true,defaultValue = "1") int pageNo,
                                        @RequestParam(required =  true,defaultValue = "10") int pageSize,
                                        @RequestParam(required = false,defaultValue = "0") long memberid,
                                        @RequestParam(required = false,defaultValue = "") String username,
                                        @RequestParam(required = false,defaultValue = "") String realname,
                                        @RequestParam(required = false,defaultValue = "") String companyname,
                                        @RequestParam(required = false,defaultValue = "") String shopname,
                                        @RequestParam(required = false,defaultValue = "") String rechargenumber,
                                        @RequestParam(required = false,defaultValue = "") String presentationnumber,
                                        @RequestParam(required =  false,defaultValue = "-1") short capitaltype,
                                        @RequestParam(required =  false,defaultValue = "-1") short rechargestate,
                                        @RequestParam(required = false) Date tradetimeStart,
                                        @RequestParam(required =  false) Date tradetimeEnd,
                                        @RequestParam(required = false) BigDecimal capitalStart,
                                        @RequestParam(required = false) BigDecimal capitalEnd,
                                        @RequestParam(required = false,defaultValue = "") String tradeno,
                                        @RequestParam(required = false,defaultValue = "-1") short rechargeperform,
                                        @RequestParam(required = false,defaultValue = "-1") short paytype,
                                        @RequestParam(required = false,defaultValue = "") String orderno,
                                        @RequestParam(required =  false,defaultValue = "0") short withdrawtype
                                        ){
        CashRet cashRet = new CashRet();

        if(searchType != 1 && searchType != 2){
            cashRet.setResult(BasicRet.ERR);
            cashRet.setMessage("买家卖家类型错误");
            return  cashRet;
        }

        PageInfo pageInfo =null;
        if(searchType == 1){

            BuyerCapitalQueryDto queryDto =new BuyerCapitalQueryDto();

            if(memberid >0){
                queryDto.setMemberid(memberid);
            }


            queryDto.setCapitaltype(capitaltype);
            queryDto.setPaytype(paytype);

            queryDto.setRechargestate(rechargestate);

            queryDto.setRechargeperform(rechargeperform);


            if(StringUtils.hasText(username)){
                queryDto.setUsername(username);
            }

            if(StringUtils.hasText(companyname)){
                queryDto.setCompanyname(companyname);
            }

            if(StringUtils.hasText(realname)){
                queryDto.setRealname(realname);
            }


            if(StringUtils.hasText(rechargenumber)){
                queryDto.setRechargenumber(rechargenumber);
            }

            if(StringUtils.hasText(presentationnumber)){
                queryDto.setPresentationnumber(presentationnumber);
            }


            if(tradetimeStart != null){
                queryDto.setTradetimeStart(tradetimeStart);
            }

            if(tradetimeEnd != null){
                queryDto.setTradetimeEnd(DateUtils.addDays(tradetimeEnd,1));
            }

            if(capitalStart != null){
                queryDto.setCapitalStart(capitalStart);
            }

            if(capitalEnd != null){
                queryDto.setCapitalEnd(capitalEnd);
            }

            if(StringUtils.hasText(shopname)){
                queryDto.setShopname(shopname);
            }

            if(StringUtils.hasText(tradeno)){
                queryDto.setTradeno(tradeno);
            }

            if(StringUtils.hasText(orderno)){
                queryDto.setOrderno(orderno);
            }

//            pageInfo = buyerCapitalService.listConsume(memberid,starttime,endtime,capitaltype,(short)-1,rechargeperform,pageNo,pageSize);
            pageInfo = buyerCapitalService.listConsume(queryDto,pageNo,pageSize);
        }else{



            SalerCapitalQueryDto queryDto =new SalerCapitalQueryDto();

            if(memberid >0){
                queryDto.setMemberid(memberid);
            }




            queryDto.setRechargestate(rechargestate);

            queryDto.setRechargeperform(rechargeperform);

            queryDto.setTradeno(tradeno);

            if(StringUtils.hasText(username)){
                queryDto.setUsername(username);
            }

            if(StringUtils.hasText(companyname)){
                queryDto.setCompanyname(companyname);
            }


            if(StringUtils.hasText(rechargenumber)){
                queryDto.setRechargenumber(rechargenumber);
            }

            if(StringUtils.hasText(presentationnumber)){
                queryDto.setPresentationnumber(presentationnumber);
            }


            if(tradetimeStart != null){
                queryDto.setTradetimeStart(tradetimeStart);
            }

            if(tradetimeEnd != null){
                queryDto.setTradetimeEnd(DateUtils.addDays(tradetimeEnd,1));
            }

            if(capitalStart != null){
                queryDto.setCapitalStart(capitalStart);
            }

            if(capitalEnd != null){
                queryDto.setCapitalEnd(capitalEnd);
            }

            if(StringUtils.hasText(shopname)){
                queryDto.setShopname(shopname);
            }


            if(StringUtils.hasText(realname)){
                queryDto.setRealname(realname);
            }


            queryDto.setCapitaltype(capitaltype);
//           if(capitaltype == Quantity.STATE_5) {  //如果是卖家提现
//               if(withdrawtype == Quantity.STATE_0){
//                   capitaltype =  -2;  //余额 和货款
//               }else if(withdrawtype == Quantity.STATE_1){
//                   capitaltype  = 5; //余额提现
//               }else if(withdrawtype == Quantity.STATE_2){
//                   capitaltype =  11; //货款提现
//               }
//
//               queryDto.setCapitaltype(capitaltype);
//           }


            pageInfo = salerCapitalService.listCash(queryDto,pageNo,pageSize);
        }

        cashRet.data.pageInfo = pageInfo;
        cashRet.setResult(BasicRet.SUCCESS);
        cashRet.setMessage("查询成功");

        return  cashRet;
    }



    @RequestMapping(value = "/userCapitalList/buyer",method = RequestMethod.POST)
    @ApiOperation("资金管理-买家-买家明细-列表")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "用户id",name = "memberid",required = false,paramType = "query",dataType = "int"),
           /* @ApiImplicitParam(value = "会员名称",name = "realname",required = false,paramType = "query",dataType = "string"),*/
            @ApiImplicitParam(value = "会员用户名",name = "username",required = false,paramType = "query",dataType = "string"),
            /*@ApiImplicitParam(value = "公司名称",name = "companyname",required = false,paramType = "query",dataType = "string"),*/
    })
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.FINANCIALDETAILS + "')")
    public  CashRet BuyerUserCapitalList(
            @RequestParam(required =  true,defaultValue = "1") int pageNo,
            @RequestParam(required =  true,defaultValue = "10") int pageSize,
            AdvanceCapitalQueryDto queryDto){
        CashRet cashRet = new CashRet();
        PageInfo pageInfo =  null;
        pageInfo = buyerCapitalService.advanceCapitalList(pageNo,pageSize,queryDto);
        cashRet.data.pageInfo = pageInfo;
        cashRet.setMessage("查询成功");
        cashRet.setResult(BasicRet.SUCCESS);
        return  cashRet;
    }




    @RequestMapping(value = "/userCapitalList/seller",method = RequestMethod.POST)
    @ApiOperation("资金管理-卖家-店铺明细-列表")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "用户id",name = "memberid",required = false,paramType = "query",dataType = "int"),
            @ApiImplicitParam(value = "会员名称",name = "realname",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(value = "会员用户名",name = "username",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(value = "商家公司名称",name = "companyname",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(value = "店铺名称",name = "shopname",required = false,paramType = "query",dataType = "string"),
    })
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.FINANCIALDETAILS + "')")
    public  CashRet sellerUserCapitalList(
            @RequestParam(required =  true,defaultValue = "1") int pageNo,
            @RequestParam(required =  true,defaultValue = "10") int pageSize,
            AdvanceCapitalSellerQueryDto queryDto){
        CashRet cashRet = new CashRet();
        PageInfo pageInfo =  null;
        pageInfo = salerCapitalService.advanceCapitalList(pageNo,pageSize,queryDto);
        cashRet.data.pageInfo = pageInfo;
        cashRet.setMessage("查询成功");
        cashRet.setResult(BasicRet.SUCCESS);
        return  cashRet;
    }






    @RequestMapping(value = "/excelExport/userCapital/listLogs",method = RequestMethod.GET)
    @ApiOperation("Excel导出-买家，卖家资金流水明细")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "买家卖家类型[1=买家，2=卖家]",name = "searchType",required = true,paramType = "query",dataType = "int",defaultValue = "1"),
            @ApiImplicitParam(value = "类别 买家[类别0=消费1=充值2=退款3=提现4=授信5=授信还款6=违约金7=远期定金8=远期余款9=远期全款] 卖家[资金类型0=订单金额1=上架保证金2=下架保证金3=退款金额4=充值5=余额提现6=买家违约金7=卖家违约金8=余款9=全款10=定金 11=货款提现]",name = "capitaltype",required = false,paramType = "query",dataType = "int",defaultValue = "-1"),
            @ApiImplicitParam(value = "状态0=待处理1=成功2=失败3=待审核4=审核通过5=审核不通过",name = "rechargestate",required = false,paramType = "query",dataType = "int",defaultValue = "-1"),
            @ApiImplicitParam(value = "充值平台0=微信1=支付宝2=线下平台3=银行卡",name = "rechargeperform",required = false,paramType = "query",dataType = "int",defaultValue = "-1"),
            @ApiImplicitParam(value = "支付方式0=支付宝1=微信2=银行卡3=余额4=授信",name = "paytype",required = false,paramType = "query",dataType = "int",defaultValue = "-1"),
            @ApiImplicitParam(value = "用户名",name = "username",required = false,paramType = "query",dataType = "string",defaultValue = ""),
            @ApiImplicitParam(value = "会员姓名",name = "realname",required = false,paramType = "query",dataType = "string",defaultValue = ""),
            @ApiImplicitParam(value = "公司名",name = "companyname",required = false,paramType = "query",dataType = "string",defaultValue = ""),
            @ApiImplicitParam(value = "店铺名称",name = "shopname",required = false,paramType = "query",dataType = "string",defaultValue = ""),
            @ApiImplicitParam(value = "提现单号",name = "presentationnumber",required = false,paramType = "query",dataType = "string",defaultValue = ""),
            @ApiImplicitParam(value = "充值单号",name = "rechargenumber",required = false,paramType = "query",dataType = "string",defaultValue = ""),
            @ApiImplicitParam(value = "交易时间",name = "tradetimeStart",required = false,paramType = "query",dataType = "date"),
            @ApiImplicitParam(value = "交易时间",name = "tradetimeEnd",required = false,paramType = "query",dataType = "date"),
            @ApiImplicitParam(value = "交易金额区间",name = "capitalStart",required = false,paramType = "query",dataType = "double"),
            @ApiImplicitParam(value = "交易金额区间",name = "capitalEnd",required = false,paramType = "query",dataType = "double"),
            @ApiImplicitParam(value = "订单号",name = "orderno",required = false,paramType = "query",dataType = "string",defaultValue = ""),
            @ApiImplicitParam(value = "卖家提现方式 0=全部 1=余额提现，2=货款提现",name = "withdrawtype",required = false,paramType = "query",dataType = "string",defaultValue = ""),
    })
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.FINANCIALDETAILS + "')")
    public ResponseEntity<InputStreamResource> ExcelExportUserCapitalListLogs(@RequestParam(required =  true,defaultValue = "1") short searchType,
                                                                              @RequestParam(required =  true,defaultValue = "1") int pageNo,
                                                                              @RequestParam(required =  true,defaultValue = "10") int pageSize,
                                                                              @RequestParam(required = false,defaultValue = "0") long memberid,
                                                                              @RequestParam(required = false,defaultValue = "") String username,
                                                                              @RequestParam(required = false,defaultValue = "") String realname,
                                                                              @RequestParam(required = false,defaultValue = "") String companyname,
                                                                              @RequestParam(required = false,defaultValue = "") String shopname,
                                                                              @RequestParam(required = false,defaultValue = "") String rechargenumber,
                                                                              @RequestParam(required = false,defaultValue = "") String presentationnumber,
                                                                              @RequestParam(required =  false,defaultValue = "-1") short capitaltype,
                                                                              @RequestParam(required =  false,defaultValue = "-1") short rechargestate,
                                                                              @RequestParam(required = false) Date tradetimeStart,
                                                                              @RequestParam(required =  false) Date tradetimeEnd,
                                                                              @RequestParam(required = false) BigDecimal capitalStart,
                                                                              @RequestParam(required = false) BigDecimal capitalEnd,
                                                                              @RequestParam(required = false,defaultValue = "-1") short rechargeperform,
                                                                              @RequestParam(required = false,defaultValue = "-1") short paytype,
                                                                              @RequestParam(required = false,defaultValue = "") String orderno,
                                                                              @RequestParam(required =  false,defaultValue = "0") short withdrawtype

    ) {

        if (searchType != 1 && searchType != 2) {
            return null;
        }

        List<Map<String, Object>> resList = new ArrayList<>();

        String[] rowTitles = null;
        String[] sumCols = null;
        String name = "";

        if (searchType == 1) {

            name = "买家资金明细";
            rowTitles = new String[]{"下单时间", "合同号", "订单号", "交易号", "买家", "卖家", "订单类型", "订单来源", "商品名称", "规格", "材质", "牌号", "品牌", "印记", "表面处理", "包装方式", "单位", "单价", "订购量", "货款金额", "开票抬头", "税号", "开户行", "开户账号", "开票地址", "电话", "是否开票", "订单状态", "项目", "收件人", "收货地址", "付款方式", "物流公司", "快递单号", "业务员", "第三方支付号", "业务单号"};

            BuyerCapitalQueryDto queryDto = new BuyerCapitalQueryDto();

            if (memberid > 0) {
                queryDto.setMemberid(memberid);
            }

            queryDto.setCapitaltype(capitaltype);
            queryDto.setPaytype(paytype);
            queryDto.setRechargestate(rechargestate);
            queryDto.setRechargeperform(rechargeperform);

            if (StringUtils.hasText(username)) {
                queryDto.setUsername(username);
            }

            if (StringUtils.hasText(companyname)) {
                queryDto.setCompanyname(companyname);
            }


            if (StringUtils.hasText(rechargenumber)) {
                queryDto.setRechargenumber(rechargenumber);
            }

            if (StringUtils.hasText(presentationnumber)) {
                queryDto.setPresentationnumber(presentationnumber);
            }


            if (tradetimeStart != null) {
                queryDto.setTradetimeStart(tradetimeStart);
            }

            if (tradetimeEnd != null) {
                queryDto.setTradetimeEnd(DateUtils.addDays(tradetimeEnd, 1));
            }

            if (capitalStart != null) {
                queryDto.setCapitalStart(capitalStart);
            }

            if (capitalEnd != null) {
                queryDto.setCapitalEnd(capitalEnd);
            }

            if (StringUtils.hasText(shopname)) {
                queryDto.setShopname(shopname);
            }

            if (StringUtils.hasText(realname)) {
                queryDto.setRealname(realname);
            }

            resList = buyerCapitalService.listConsumeForAdminExportExcel(queryDto);
        } else {

            name = "卖家资金明细";


            SalerCapitalQueryDto queryDto =new SalerCapitalQueryDto();

        if (memberid > 0) {
            queryDto.setMemberid(memberid);
        }


        queryDto.setCapitaltype(capitaltype);


        queryDto.setRechargestate(rechargestate);

        queryDto.setRechargeperform(rechargeperform);


        if (StringUtils.hasText(username)) {
            queryDto.setUsername(username);
        }

        if (StringUtils.hasText(companyname)) {
            queryDto.setCompanyname(companyname);
        }


        if (StringUtils.hasText(rechargenumber)) {
            queryDto.setRechargenumber(rechargenumber);
        }

        if (StringUtils.hasText(presentationnumber)) {
            queryDto.setPresentationnumber(presentationnumber);
        }


        if (tradetimeStart != null) {
            queryDto.setTradetimeStart(tradetimeStart);
        }

        if (tradetimeEnd != null) {
            queryDto.setTradetimeEnd(DateUtils.addDays(tradetimeEnd, 1));
        }

        if (capitalStart != null) {
            queryDto.setCapitalStart(capitalStart);
        }

        if (capitalEnd != null) {
            queryDto.setCapitalEnd(capitalEnd);
        }

        if (StringUtils.hasText(shopname)) {
            queryDto.setShopname(shopname);
        }

        if (StringUtils.hasText(realname)) {
            queryDto.setRealname(realname);
        }

        if (StringUtils.hasText(orderno)) {
            queryDto.setOrderno(orderno);
        }

        queryDto.setCapitaltype(capitaltype);
//            if(capitaltype == Quantity.STATE_5) {  //如果是卖家提现
//                if(withdrawtype == Quantity.STATE_0){
//                    capitaltype =  -2;  //余额 和货款
//                }else if(withdrawtype == Quantity.STATE_1){
//                    capitaltype  = 5; //余额提现
//                }else if(withdrawtype == Quantity.STATE_2){
//                    capitaltype =  11; //货款提现
//                }
//
//                queryDto.setCapitaltype(capitaltype);
//            }

            resList =  salerCapitalService.listCashForAdminExcel(queryDto);
            rowTitles = new String[]{"下单时间", "合同号", "订单号", "交易号", "买家", "卖家", "订单类型", "订单来源", "商品名称", "规格", "材质", "牌号", "品牌", "印记", "表面处理", "包装方式", "单位", "单价", "订购量", "货款金额", "开票抬头", "税号", "开户行", "开户账号", "开票地址", "电话", "是否开票", "订单状态", "项目", "收件人", "收货地址", "付款方式", "物流公司", "快递单号", "业务员", "第三方支付号", "业务单号"};

       /* Map<String, List<Map<String, Object>>> maps = new HashMap<String, List<Map<String, Object>>>();
        maps.put(name,resList);*/

    }
        sumCols = new String[]{"货款金额"};
        XSSFWorkbook workbook = null;
        try {
            //workbook = Test.createExcel(rowTitles, maps, new int[]{0,1,2,3,4,5,6,7});
            workbook = ExcelGen.common(name,rowTitles,resList,sumCols);
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

    @RequestMapping(value = "/excelExport/userCapitalManagement",method = RequestMethod.GET)
    @ApiOperation("Excel导出-买家，卖家应收账款财务管理")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "买家卖家类型[1=买家，2=卖家]",name = "searchType",required = true,paramType = "query",dataType = "int",defaultValue = "1"),
            @ApiImplicitParam(value = "类别 买家[类别0=消费1=充值2=退款3=提现4=授信5=授信还款6=违约金7=远期定金8=远期余款9=远期全款] 卖家[资金类型0=订单金额1=上架保证金2=下架保证金3=退款金额4=充值5=余额提现6=买家违约金7=卖家违约金8=余款9=全款10=定金 11=货款提现]",name = "capitaltype",required = false,paramType = "query",dataType = "int",defaultValue = "-1"),
            @ApiImplicitParam(value = "状态0=待处理1=成功2=失败3=待审核4=审核通过5=审核不通过",name = "rechargestate",required = false,paramType = "query",dataType = "int",defaultValue = "-1"),
            @ApiImplicitParam(value = "充值平台0=微信1=支付宝2=线下平台3=银行卡",name = "rechargeperform",required = false,paramType = "query",dataType = "int",defaultValue = "-1"),
            @ApiImplicitParam(value = "支付方式0=支付宝1=微信2=银行卡3=余额4=授信",name = "paytype",required = false,paramType = "query",dataType = "int",defaultValue = "-1"),
            @ApiImplicitParam(value = "用户名",name = "username",required = false,paramType = "query",dataType = "string",defaultValue = ""),
            @ApiImplicitParam(value = "会员姓名",name = "realname",required = false,paramType = "query",dataType = "string",defaultValue = ""),
            @ApiImplicitParam(value = "公司名",name = "companyname",required = false,paramType = "query",dataType = "string",defaultValue = ""),
            @ApiImplicitParam(value = "店铺名称",name = "shopname",required = false,paramType = "query",dataType = "string",defaultValue = ""),
            @ApiImplicitParam(value = "提现单号",name = "presentationnumber",required = false,paramType = "query",dataType = "string",defaultValue = ""),
            @ApiImplicitParam(value = "充值单号",name = "rechargenumber",required = false,paramType = "query",dataType = "string",defaultValue = ""),
            @ApiImplicitParam(value = "交易时间",name = "tradetimeStart",required = false,paramType = "query",dataType = "date"),
            @ApiImplicitParam(value = "交易时间",name = "tradetimeEnd",required = false,paramType = "query",dataType = "date"),
            @ApiImplicitParam(value = "交易金额区间",name = "capitalStart",required = false,paramType = "query",dataType = "double"),
            @ApiImplicitParam(value = "交易金额区间",name = "capitalEnd",required = false,paramType = "query",dataType = "double"),
            @ApiImplicitParam(value = "订单号",name = "orderno",required = false,paramType = "query",dataType = "string",defaultValue = ""),
            @ApiImplicitParam(value = "卖家提现方式 0=全部 1=余额提现，2=货款提现",name = "withdrawtype",required = false,paramType = "query",dataType = "string",defaultValue = ""),
    })
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.FINANCIALDETAILS + "')")
    public ResponseEntity<InputStreamResource> ExcelExportUserCapitalManagement(@RequestParam(required =  true,defaultValue = "1") short searchType,
                                                                              @RequestParam(required =  true,defaultValue = "1") int pageNo,
                                                                              @RequestParam(required =  true,defaultValue = "10") int pageSize,
                                                                              @RequestParam(required = false,defaultValue = "0") long memberid,
                                                                              @RequestParam(required = false,defaultValue = "") String username,
                                                                              @RequestParam(required = false,defaultValue = "") String realname,
                                                                              @RequestParam(required = false,defaultValue = "") String companyname,
                                                                              @RequestParam(required = false,defaultValue = "") String shopname,
                                                                              @RequestParam(required = false,defaultValue = "") String rechargenumber,
                                                                              @RequestParam(required = false,defaultValue = "") String presentationnumber,
                                                                              @RequestParam(required =  false,defaultValue = "-1") short capitaltype,
                                                                              @RequestParam(required =  false,defaultValue = "-1") short rechargestate,
                                                                              @RequestParam(required = false) Date tradetimeStart,
                                                                              @RequestParam(required =  false) Date tradetimeEnd,
                                                                              @RequestParam(required = false) BigDecimal capitalStart,
                                                                              @RequestParam(required = false) BigDecimal capitalEnd,
                                                                              @RequestParam(required = false,defaultValue = "-1") short rechargeperform,
                                                                              @RequestParam(required = false,defaultValue = "-1") short paytype,
                                                                              @RequestParam(required = false,defaultValue = "") String orderno,
                                                                              @RequestParam(required =  false,defaultValue = "0") short withdrawtype

    ){

        if(searchType != 1 && searchType != 2) {
            return  null;
        }

        List<Map<String,Object>> resList = new ArrayList<>();

        String[] rowTitles = null;
        String name = "";

        if(searchType == 1){

            name="买家应收账款财务管理";

        }else{

            name="卖家应收账款财务管理";
        }

        BuyerCapitalQueryDto queryDto =new BuyerCapitalQueryDto();

        if(memberid >0){
            queryDto.setMemberid(memberid);
        }

        queryDto.setCapitaltype(capitaltype);

        queryDto.setRechargestate(rechargestate);

        queryDto.setRechargeperform(rechargeperform);

        if(StringUtils.hasText(username)){
            queryDto.setUsername(username);
        }

        if(StringUtils.hasText(companyname)){
            queryDto.setCompanyname(companyname);
        }


        if(StringUtils.hasText(rechargenumber)){
            queryDto.setRechargenumber(rechargenumber);
        }

        if(StringUtils.hasText(presentationnumber)){
            queryDto.setPresentationnumber(presentationnumber);
        }

        if(tradetimeStart != null){
            queryDto.setTradetimeStart(tradetimeStart);
        }

        if(tradetimeEnd != null){
            queryDto.setTradetimeEnd(DateUtils.addDays(tradetimeEnd,1));
        }

        if(capitalStart != null){
            queryDto.setCapitalStart(capitalStart);
        }

        if(capitalEnd != null){
            queryDto.setCapitalEnd(capitalEnd);
        }

        if(StringUtils.hasText(shopname)){
            queryDto.setShopname(shopname);
        }

        if(StringUtils.hasText(realname)){
            queryDto.setRealname(realname);
        }

        if(StringUtils.hasText(orderno)){
            queryDto.setOrderno(orderno);
        }

        queryDto.setCapitaltype(capitaltype);

        resList =  buyerCapitalService.ExcelExportUserCapitalManagement(queryDto);
        rowTitles =  new String[]{"汇入行","交易时间","凭证号","汇款人名称","金额","款项归属","备注","卖家","订单号","类别"};
        String[] sumCols = new String[]{"金额"};
        /*Map<String, List<Map<String, Object>>> maps = new HashMap<String, List<Map<String, Object>>>();
        maps.put(name,resList);*/


        XSSFWorkbook workbook = null;
        try {
            //workbook = Test.createExcel(rowTitles, maps, new int[]{0,1,2,3,4,5,6,7});
            workbook = ExcelGen.common(name,rowTitles,resList,sumCols);
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


    @RequestMapping(value = "/accountList",method = RequestMethod.POST)
    @ApiOperation(value = "买家对账单查询（capitaltype:类别0=消费2=退款4=授信6=违约金10=卖家违约金）记录中的最后一条数据为对账单结算数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tradetimeStart",value = "开始日期",required = false,paramType = "query",dataType = "date"),
            @ApiImplicitParam(name = "tradetimeEnd",value = "结束日期",required = false,paramType = "query",dataType = "date"),
            @ApiImplicitParam(name = "memberid",value = "会员编号",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "companyname",value = "公司名称",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "invoicename",value = "开票名称",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "realname",value = "会员姓名",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "mobile",value = "手机号码",required = false,paramType = "query",dataType = "string")
    })
    public BuyerCapitalAccountRet accountList(BuyerCapitalAccountQueryDto dto,
                                                                 Model model){
        BuyerCapitalAccountRet buyerCapitalAccountRet=new BuyerCapitalAccountRet();
        List<BuyerCapitalAccountDto> buyerCapitalAccountDtos=new ArrayList<BuyerCapitalAccountDto>();

        //核定用户，具体暂做分析
        List<BuyerCapitalViewDto> buyerCapitalViewDtos=buyerCapitalService.listForAccount(dto);
        if(buyerCapitalViewDtos!=null && buyerCapitalViewDtos.size()>0){
            final BigDecimal[] sumDeliveryAmount = {new BigDecimal(0.00)};
            final BigDecimal[] sumReceiptAmount = {new BigDecimal(0.00)};
            final BigDecimal[] sumOtherAmount = {new BigDecimal(0.00)};
            BigDecimal sumReceivableAccount =new BigDecimal(0.00);
            BigDecimal sumInvoiceBalance    =new BigDecimal(0.00);
            sumDeliveryAmount[0].setScale(2,BigDecimal.ROUND_HALF_UP);
            sumReceiptAmount[0].setScale(2,BigDecimal.ROUND_HALF_UP);
            sumOtherAmount[0].setScale(2,BigDecimal.ROUND_HALF_UP);
            sumReceivableAccount.setScale(2,BigDecimal.ROUND_HALF_UP);
            sumInvoiceBalance.setScale(2,BigDecimal.ROUND_HALF_UP);
            for (int i = 0; i < buyerCapitalViewDtos.size(); i++) {
                BuyerCapitalViewDto buyerCapitalViewDto=buyerCapitalViewDtos.get(i);
                short payType=buyerCapitalViewDto.getPaytype();
                short capitalType=buyerCapitalViewDto.getCapitaltype();
                BuyerCapitalAccountDto buyerCapitalAccountDto=null;
                //根据capitalType进行分组计算
                switch  (capitalType){
                    case BuyerCapitalConst.CAPITALTYPE_CONSUM:
                    {
                        if (payType == BuyerCapitalConst.PAYMETHOD_ALIPAY || payType == BuyerCapitalConst.PAYMETHOD_WEIXIN || payType == BuyerCapitalConst.PAYMETHOD_BANKCARD) {
                            //当记录为网络支付时，先添加一条收款记录，【应收账款】减钱
                            buyerCapitalAccountDto = this.transferViewDto2AccountDto(buyerCapitalViewDto);
                            buyerCapitalAccountDto.setDeliveryamount(null);
                            buyerCapitalAccountDto.setOtheramount(null);
                            buyerCapitalAccountDto.setCapitaltypename("收款");
                            sumReceivableAccount=sumReceivableAccount.subtract(buyerCapitalAccountDto.getReceiptamount());
                            buyerCapitalAccountDto.setReceivableaccount(sumReceivableAccount);
                            buyerCapitalAccountDto.setInvoicebalance(sumInvoiceBalance);
                            buyerCapitalAccountDto.setRemark((buyerCapitalViewDto.getInvoiceheadup()==null?"":buyerCapitalViewDto.getInvoiceheadup())+"$"+buyerCapitalViewDto.getMemberid());
                            //添加到对应的结果集合中去
                            buyerCapitalAccountDtos.add(buyerCapitalAccountDto);
                            //然后再添加一条发货记录，【应收账款】加钱
                            buyerCapitalAccountDto = this.transferViewDto2AccountDto(buyerCapitalViewDto);
                            buyerCapitalAccountDto.setReceiptamount(null);
                            buyerCapitalAccountDto.setOtheramount(null);
                            buyerCapitalAccountDto.setCapitaltypename("发货");
                            //填写对应的发货单编号
                            buyerCapitalAccountDto.setOddnumber(buyerCapitalViewDto.getDeliveryno());
                            sumReceivableAccount=sumReceivableAccount.add(buyerCapitalAccountDto.getDeliveryamount());
                            buyerCapitalAccountDto.setReceivableaccount(sumReceivableAccount);
                            //当发货=加法，发票结余进行累加，其他情况不处理
                            sumInvoiceBalance=sumInvoiceBalance.add(buyerCapitalAccountDto.getDeliveryamount());
                            buyerCapitalAccountDto.setInvoicebalance(sumInvoiceBalance);
                            buyerCapitalAccountDto.setRemark((buyerCapitalViewDto.getInvoiceheadup()==null?"":buyerCapitalViewDto.getInvoiceheadup())+"$"+buyerCapitalViewDto.getMemberid());
                            //添加到对应的结果集合中去
                            buyerCapitalAccountDtos.add(buyerCapitalAccountDto);
                        } else if (payType == BuyerCapitalConst.PAYMETHOD_BALANCE || payType == BuyerCapitalConst.PAYMETHOD_CREDIT) {
                            //这两种情况下，均是新增一条发货记录，同时累加发票结余
                            buyerCapitalAccountDto = this.transferViewDto2AccountDto(buyerCapitalViewDto);
                            buyerCapitalAccountDto.setReceiptamount(null);
                            buyerCapitalAccountDto.setOtheramount(null);
                            buyerCapitalAccountDto.setCapitaltypename("发货");
                            //填写对应的发货单编号
                            buyerCapitalAccountDto.setOddnumber(buyerCapitalViewDto.getDeliveryno());
                            sumReceivableAccount=sumReceivableAccount.add(buyerCapitalAccountDto.getDeliveryamount());
                            buyerCapitalAccountDto.setReceivableaccount(sumReceivableAccount);
                            sumInvoiceBalance=sumInvoiceBalance.add(buyerCapitalAccountDto.getDeliveryamount());
                            buyerCapitalAccountDto.setInvoicebalance(sumInvoiceBalance);
                            buyerCapitalAccountDto.setRemark((buyerCapitalViewDto.getInvoiceheadup()==null?"":buyerCapitalViewDto.getInvoiceheadup())+"$"+buyerCapitalViewDto.getMemberid());
                            buyerCapitalAccountDtos.add(buyerCapitalAccountDto);
                        }
                        break;
                    }
                    case BuyerCapitalConst.CAPITALTYPE_RECHARGE:
                    {
                        buyerCapitalAccountDto = this.transferViewDto2AccountDto(buyerCapitalViewDto);
                        //此时的单号获取的为充值平台的单号
                        buyerCapitalAccountDto.setOddnumber(buyerCapitalViewDto.getRechargenumber());
                        buyerCapitalAccountDto.setDeliveryamount(null);
                        buyerCapitalAccountDto.setOtheramount(null);
                        buyerCapitalAccountDto.setCapitaltypename("充值");
                        sumReceivableAccount=sumReceivableAccount.subtract(buyerCapitalAccountDto.getReceiptamount());
                        buyerCapitalAccountDto.setReceivableaccount(sumReceivableAccount);
                        buyerCapitalAccountDto.setInvoicebalance(sumInvoiceBalance);
                        //添加到对应的结果集合中去
                        buyerCapitalAccountDtos.add(buyerCapitalAccountDto);
                        break;
                    }
                    //买家违约，金额归为其他，进行相加
                    case BuyerCapitalConst.CAPITALTYPE_PENALTY:
                    {
                        buyerCapitalAccountDto=this.transferViewDto2AccountDto(buyerCapitalViewDto);
                        buyerCapitalAccountDto.setDeliveryamount(null);
                        buyerCapitalAccountDto.setReceiptamount(null);
                        buyerCapitalAccountDto.setCapitaltypename("违约金");
                        sumReceivableAccount=sumReceivableAccount.add(buyerCapitalAccountDto.getOtheramount());
                        buyerCapitalAccountDto.setReceivableaccount(sumReceivableAccount);
                        buyerCapitalAccountDto.setInvoicebalance(sumInvoiceBalance);
                        buyerCapitalAccountDto.setPaytype((short)5);
                        //capitalType类型为违约时，显示remark字段的内容
                        buyerCapitalAccountDto.setRemark("买家违约");
                        buyerCapitalAccountDtos.add(buyerCapitalAccountDto);
                        break;
                    }
                    //卖家违约，金额归为其他，金额变为负数，进行相加
                    case BuyerCapitalConst.CAPITALTYPE_PENALTY_SELLER:
                    {
                        buyerCapitalAccountDto=this.transferViewDto2AccountDto(buyerCapitalViewDto);
                        buyerCapitalAccountDto.setDeliveryamount(null);
                        buyerCapitalAccountDto.setReceiptamount(null);
                        buyerCapitalAccountDto.setCapitaltypename("违约金");
                        buyerCapitalAccountDto.setOtheramount(buyerCapitalAccountDto.getOtheramount().multiply(new BigDecimal(-1)));
                        sumReceivableAccount=sumReceivableAccount.add(buyerCapitalAccountDto.getOtheramount());
                        buyerCapitalAccountDto.setReceivableaccount(sumReceivableAccount);
                        buyerCapitalAccountDto.setInvoicebalance(sumInvoiceBalance);
                        //违约金没有支付方式
                        buyerCapitalAccountDto.setPaytype((short)5);
                        //capitalType类型为违约时，显示remark字段的内容
                        buyerCapitalAccountDto.setRemark("卖家违约");
                        buyerCapitalAccountDtos.add(buyerCapitalAccountDto);
                        break;
                    }
                    //退款、提现，收款金额变成负数，进行相减
                    case BuyerCapitalConst.CAPITALTYPE_REFUND:
                    case BuyerCapitalConst.CAPITALTYPE_WITHDRAWAL:
                    {
                        buyerCapitalAccountDto=this.transferViewDto2AccountDto(buyerCapitalViewDto);
                        buyerCapitalAccountDto.setDeliveryamount(null);
                        buyerCapitalAccountDto.setOtheramount(null);
                        buyerCapitalAccountDto.setCapitaltypename(capitalType==BuyerCapitalConst.CAPITALTYPE_REFUND?"退款":"提现");
                        buyerCapitalAccountDto.setReceiptamount(buyerCapitalAccountDto.getReceiptamount().multiply(new BigDecimal(-1)));
                        sumReceivableAccount= sumReceivableAccount.subtract(buyerCapitalAccountDto.getReceiptamount());
                        buyerCapitalAccountDto.setReceivableaccount(sumReceivableAccount);
                        buyerCapitalAccountDto.setInvoicebalance(sumInvoiceBalance);
                        buyerCapitalAccountDtos.add(buyerCapitalAccountDto);
                        break;
                    }
                    default:
                }

            }
            //计算发货金额当期结算、收款金额当期结算、其他当期结算
            buyerCapitalAccountDtos.stream().forEach(buyerCapitalAccountDto -> {
                        sumDeliveryAmount[0] = buyerCapitalAccountDto.getDeliveryamount() == null ? sumDeliveryAmount[0] : sumDeliveryAmount[0].add(buyerCapitalAccountDto.getDeliveryamount());
                        sumReceiptAmount[0] = buyerCapitalAccountDto.getReceiptamount() == null ? sumReceiptAmount[0] : sumReceiptAmount[0].add(buyerCapitalAccountDto.getReceiptamount());
                        sumOtherAmount[0] = buyerCapitalAccountDto.getOtheramount() == null ? sumOtherAmount[0] : sumOtherAmount[0].add(buyerCapitalAccountDto.getOtheramount());
                    }
            );
            sumReceivableAccount=buyerCapitalAccountDtos.get(buyerCapitalAccountDtos.size()-1).getReceivableaccount();
            sumInvoiceBalance=buyerCapitalAccountDtos.get(buyerCapitalAccountDtos.size()-1).getInvoicebalance();
            //当期结算的金额统计数据,最后一个Dto
            BuyerCapitalAccountDto buyerCapitalAccountDto1=new BuyerCapitalAccountDto();
            buyerCapitalAccountDto1.setDeliveryamount(sumDeliveryAmount[0]);
            buyerCapitalAccountDto1.setReceiptamount(sumReceiptAmount[0]);
            buyerCapitalAccountDto1.setOtheramount(sumOtherAmount[0]);
            buyerCapitalAccountDto1.setReceivableaccount(sumReceivableAccount);
            buyerCapitalAccountDto1.setInvoicebalance(sumInvoiceBalance);
            buyerCapitalAccountDtos.add(buyerCapitalAccountDto1);
        }
        buyerCapitalAccountRet.setMessage("查询成功");
        buyerCapitalAccountRet.setResult(BasicRet.SUCCESS);
        buyerCapitalAccountRet.setList(buyerCapitalAccountDtos);
        return buyerCapitalAccountRet;
    }


    /**
     * 进行BuyerCapitalViewDto至BuyerCapitalAccountDto之间的转换
     * @param dto
     * @return
     */
    private BuyerCapitalAccountDto transferViewDto2AccountDto(BuyerCapitalViewDto dto){
        BuyerCapitalAccountDto buyerCapitalAccountDto=new BuyerCapitalAccountDto();
        buyerCapitalAccountDto.setTradetime(dto.getTradetime());
        buyerCapitalAccountDto.setOddnumber(dto.getOrderno());
        buyerCapitalAccountDto.setCapitaltype(dto.getCapitaltype());
        buyerCapitalAccountDto.setReceiptamount(dto.getCapital());
        buyerCapitalAccountDto.setDeliveryamount(dto.getCapital());
        buyerCapitalAccountDto.setOtheramount(dto.getCapital());
        buyerCapitalAccountDto.setPaytype(dto.getPaytype()==null?0:dto.getPaytype());
        buyerCapitalAccountDto.setPayno(dto.getTransactionid());
        buyerCapitalAccountDto.setRemark("操作人: "+(dto.getOperation()==null?"":dto.getOperation())+"$"+"审核人: "+(dto.getVerify()==null?"":dto.getVerify())+"$"+dto.getMemberid());
        return buyerCapitalAccountDto;
    }

    @RequestMapping(value = "/advanceCapitalList/buyer",method = RequestMethod.POST)
    @ApiOperation("预付款管理-买家-预付款充值-列表")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "用户id",name = "memberid",required = false,paramType = "query",dataType = "int"),
            @ApiImplicitParam(value = "会员名称",name = "realname",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(value = "会员用户名",name = "username",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(value = "公司名称",name = "companyname",required = false,paramType = "query",dataType = "string"),
    })
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.ADVANCEPAYMENTMANAGEMENT + "')")
    public  CashRet BuyerAdvanceCapitalList(
            @RequestParam(required =  true,defaultValue = "1") int pageNo,
            @RequestParam(required =  true,defaultValue = "10") int pageSize,
            AdvanceCapitalQueryDto queryDto){
        CashRet cashRet = new CashRet();
        PageInfo pageInfo =  null;
        pageInfo = buyerCapitalService.advanceCapitalList(pageNo,pageSize,queryDto);
        cashRet.data.pageInfo = pageInfo;
        cashRet.setMessage("查询成功");
        cashRet.setResult(BasicRet.SUCCESS);
        return  cashRet;
    }




    @RequestMapping(value = "/advanceCapitalList/seller",method = RequestMethod.POST)
    @ApiOperation("预付款管理-卖家-预付款充值-列表")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "用户id",name = "memberid",required = false,paramType = "query",dataType = "int"),
            @ApiImplicitParam(value = "会员名称",name = "realname",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(value = "会员用户名",name = "username",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(value = "商家公司名称",name = "companyname",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(value = "店铺名称",name = "shopname",required = false,paramType = "query",dataType = "string"),
    })
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.ADVANCEPAYMENTMANAGEMENT + "')")
    public  CashRet sellerAdvanceCapitalList(
                                            @RequestParam(required =  true,defaultValue = "1") int pageNo,
                                            @RequestParam(required =  true,defaultValue = "10") int pageSize,
                                            AdvanceCapitalSellerQueryDto queryDto){
        CashRet cashRet = new CashRet();
        PageInfo pageInfo =  null;
        pageInfo = salerCapitalService.advanceCapitalList(pageNo,pageSize,queryDto);
        cashRet.data.pageInfo = pageInfo;
        cashRet.setMessage("查询成功");
        cashRet.setResult(BasicRet.SUCCESS);
        return  cashRet;
    }





    @RequestMapping(value = "/addAdvanceCapital",method = RequestMethod.POST)
    @ApiOperation("预付款充值")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "充值类型[1=买家帐号，2=卖家帐号]",name = "memberType",required = true,dataType = "int",paramType = "query"),
            @ApiImplicitParam(value = "充值金额",name = "money",required = true,paramType = "query",dataType = "double"),
            @ApiImplicitParam(value = "要充值的用户id",name = "memberid",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(value = "备注",name = "mark",required = true,paramType = "query",dataType = "string"),
    })
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.ADVANCEPAYMENTMANAGEMENT + "')")
    public BasicRet addAdvance(@RequestParam(required = true) short memberType,
                               @RequestParam(required = true) BigDecimal money,
                               @RequestParam(required = true) long memberid,
                               @RequestParam(required = false,defaultValue = "") String mark, Model model){

        BasicRet basicRet =  new BasicRet();
        Admin admin = (Admin) model.asMap().get(AppConstant.ADMIN_SESSION_NAME);

        if(memberType != 1 && memberType != 2){
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("充值类型错误");
            return  basicRet;
        }

        Member member = memberService.getMemberById(memberid);
        if(member == null){
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("要充值的用户不存在");
            return  basicRet;
        }


        if(memberType == 1){ //
            BuyerCapital buyerCapital = new BuyerCapital();
            buyerCapital.setCapitaltype(Quantity.STATE_1);
            buyerCapital.setCapital(money);
            buyerCapital.setMemberid(memberid);
            buyerCapital.setTradetime(new Date());
            buyerCapital.setRemark(StringUtils.hasText(mark) ? mark : "买家线下充值" );
            buyerCapital.setRechargenumber(GenerateNo.getRechargeNo());
            buyerCapital.setRechargeperform(Quantity.STATE_2);
            buyerCapital.setRechargestate(Quantity.STATE_3);
            buyerCapital.setOperation(admin.getUsername());
            buyerCapitalService.insertSelective(buyerCapital);
        }else{
            SalerCapital salerCapital =  new SalerCapital();
            salerCapital.setMemberid(memberid);
            salerCapital.setTradetime(new Date());
            salerCapital.setOrdercapital(money);
            salerCapital.setRemark(StringUtils.hasText(mark) ? mark : "买家线下充值");
            salerCapital.setCapitaltype(Quantity.STATE_4);
            salerCapital.setRechargenumber(GenerateNo.getRechargeNo());
            salerCapital.setRechargeperform(Quantity.STATE_2);
            salerCapital.setRechargestate(Quantity.STATE_3);
            salerCapital.setOperation(admin.getUsername());
            salerCapitalService.insertSelective(salerCapital);
        }


        basicRet.setMessage("添加成功，等待财务确认");
        basicRet.setResult(BasicRet.SUCCESS);
        return  basicRet;
    }




    @RequestMapping(value = "/advanceCapitalValidate",method = RequestMethod.POST)
    @ApiOperation("财务对预付款充值审核")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "充值类型[1=买家帐号，2=卖家帐号]",name = "memberType",required = true,dataType = "int",paramType = "query",defaultValue = "1"),
            @ApiImplicitParam(value = "状态{4=审核通过5=审核不通过}",name = "rechargestate",required = true,dataType = "int",paramType = "query"),
            @ApiImplicitParam(value = "备注",name = "mark",required = false,dataType = "string",paramType = "query"),
    })
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.ADVANCEPAYMENTMANAGEMENT + "')")
    public  BasicRet advanceCapitalValidate(
            @RequestParam(required = true) long id,
            @RequestParam(required = true,defaultValue = "1") short memberType,
            @RequestParam(required = true) short rechargestate,
            @RequestParam(required = false,defaultValue = "") String mark,
            Model model
    ){
        BasicRet basicRet = new BasicRet();

        long memberid =  0;

        Admin admin = (Admin) model.asMap().get(AppConstant.ADMIN_SESSION_NAME);

        if(memberType != 1 && memberType != 2){
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("充值类型错误");
            return  basicRet;
        }


        if(rechargestate != 4 && rechargestate != 5){
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("状态错误");
            return  basicRet;
        }

        if(memberType ==1){ //买家
            BuyerCapital buyerCapital = buyerCapitalService.getById(id);
            if(buyerCapital == null){
                basicRet.setResult(BasicRet.ERR);
                basicRet.setMessage("未查询到充值信息");
                return  basicRet;
            }

            if(buyerCapital.getCapitaltype() != Quantity.STATE_1 || buyerCapital.getRechargeperform() != Quantity.STATE_2 || buyerCapital.getRechargestate() != Quantity.STATE_3){
                basicRet.setResult(BasicRet.ERR);
                basicRet.setMessage("充值订单信息不正确，请联系网站管理员");
                return basicRet;
            }

            Member member = memberService.getMemberById(buyerCapital.getMemberid());
            if (member == null) {
                basicRet.setResult(BasicRet.ERR);
                basicRet.setMessage("未查询到要充值的用户信息");
                return basicRet;
            }

            if(rechargestate == 4) {
                //更改用户帐号金额
                //BigDecimal balance = member.getBalance() == null ? buyerCapital.getCapital() : member.getBalance().add(buyerCapital.getCapital());
                //memberService.updateBuyerMemberBalance(member.getId(), balance);
                memberService.updateBuyerMemberBalanceInDb(member.getId(),buyerCapital.getCapital());
            }

            BuyerCapital updateBuyerCapital = new BuyerCapital();
            updateBuyerCapital.setId(buyerCapital.getId());
            updateBuyerCapital.setRechargestate(rechargestate == 4 ? (short)1 : rechargestate);
            updateBuyerCapital.setVerify(admin.getUsername());
            updateBuyerCapital.setSuccesstime(new Date());

            if(StringUtils.hasText(mark)){
                updateBuyerCapital.setRemark(buyerCapital.getRemark()+"--"+mark);
            }
            buyerCapitalService.updateByPrimaryKeySelective(updateBuyerCapital);

        }else{ //卖家

            SalerCapital salerCapital =  salerCapitalService.getById(id);

            if(salerCapital == null){
                basicRet.setResult(BasicRet.ERR);
                basicRet.setMessage("未查询到充值信息");
                return  basicRet;
            }


            Member member = memberService.getMemberById(salerCapital.getMemberid());
            if (member == null) {
                basicRet.setResult(BasicRet.ERR);
                basicRet.setMessage("未查询到要充值的用户信息");
                return basicRet;
            }


            if(rechargestate == 4) {
                //更改用户帐号金额
                //BigDecimal balance = member.getSellerbanlance() == null ? salerCapital.getOrdercapital() : member.getSellerbanlance().add(salerCapital.getOrdercapital());
                //memberService.updateSellerMemberBalance(member.getId(),balance,member.getSellerfreezebanlance());
                memberService.updateSellerMemberBalanceInDb(member.getId(),salerCapital.getOrdercapital(),new BigDecimal(0));
            }

            SalerCapital updatesalerCapital = new SalerCapital();
            updatesalerCapital.setId(salerCapital.getId());
            updatesalerCapital.setRechargestate(rechargestate == 4 ? (short)1 : rechargestate);
            updatesalerCapital.setVerify(admin.getUsername());
            updatesalerCapital.setSuccesstime(new Date());

            if(StringUtils.hasText(mark)){
                updatesalerCapital.setRemark(salerCapital.getRemark()+"--"+mark);
            }
            salerCapitalService.updateByPrimaryKeySelective(updatesalerCapital);

        }


        basicRet.setMessage("审核成功");
        basicRet.setResult(BasicRet.SUCCESS);
        return  basicRet;
    }



    @RequestMapping(value = "/advanceCapitalCancel",method = RequestMethod.POST)
    @ApiOperation(value = "预付款充值撤销",notes = "撤销后订单转变为失败状态")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "充值类型[1=买家帐号，2=卖家帐号]",name = "memberType",required = true,dataType = "int",paramType = "query",defaultValue = "1"),
    })
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.ADVANCEPAYMENTMANAGEMENT + "')")
    public  BasicRet advanceCapitalCancel(
            @RequestParam(required = true) long id,
            @RequestParam(required = true,defaultValue = "1") short memberType,
            @RequestParam(required =  false) String remark,
            Model model
    ){
        BasicRet basicRet = new BasicRet();
        Admin admin = (Admin) model.asMap().get(AppConstant.ADMIN_SESSION_NAME);

        if(memberType ==1){ //买家
            BuyerCapital buyerCapital = buyerCapitalService.getById(id);
            if(buyerCapital == null){
                basicRet.setResult(BasicRet.ERR);
                basicRet.setMessage("未查询到充值信息");
                return  basicRet;
            }

            if(buyerCapital.getCapitaltype() != Quantity.STATE_1 ||
                    buyerCapital.getRechargeperform() != Quantity.STATE_2 ||
                    buyerCapital.getRechargestate() != Quantity.STATE_1){
                basicRet.setResult(BasicRet.ERR);
                basicRet.setMessage("充值订单信息不正确，请联系网站管理员");
                return basicRet;
            }


            Member member = memberService.getMemberById(buyerCapital.getMemberid());
            if (member == null) {
                basicRet.setResult(BasicRet.ERR);
                basicRet.setMessage("未查询到要充值的用户信息");
                return basicRet;
            }

            if(member.getBalance().compareTo(buyerCapital.getCapital()) == -1){
                basicRet.setResult(BasicRet.ERR);
                basicRet.setMessage("用户帐号金额不足，不可撤销");
                return  basicRet;
            }


            //memberService.updateBuyerMemberBalance(member.getId(),member.getBalance().subtract(buyerCapital.getCapital()));
            memberService.updateBuyerMemberBalanceInDb(member.getId(),buyerCapital.getCapital().multiply(new BigDecimal(-1)));

            BuyerCapital updateBuyerCapital = new BuyerCapital();
            updateBuyerCapital.setId(buyerCapital.getId());
            updateBuyerCapital.setRechargestate(Quantity.STATE_2);
            updateBuyerCapital.setVerify(admin.getUsername());
            if(StringUtils.hasText(remark)) {
                updateBuyerCapital.setRemark(buyerCapital.getRemark()+"--"+remark);
            }

            buyerCapitalService.updateByPrimaryKeySelective(updateBuyerCapital);

        }else{ //卖家

            SalerCapital salerCapital =  salerCapitalService.getById(id);

            if(salerCapital == null){
                basicRet.setResult(BasicRet.ERR);
                basicRet.setMessage("未查询到充值信息");
                return  basicRet;
            }


            Member member = memberService.getMemberById(salerCapital.getMemberid());
            if (member == null) {
                basicRet.setResult(BasicRet.ERR);
                basicRet.setMessage("未查询到要充值的用户信息");
                return basicRet;
            }



            if(salerCapital.getCapitaltype() != Quantity.STATE_1 ||
                salerCapital.getRechargeperform() != Quantity.STATE_2 ||
                salerCapital.getRechargestate() != Quantity.STATE_1){
                basicRet.setResult(BasicRet.ERR);
                basicRet.setMessage("充值订单信息不正确，请联系网站管理员");
                return basicRet;
            }

            if(member.getSellerbanlance().compareTo(salerCapital.getOrdercapital()) == -1){
                basicRet.setResult(BasicRet.ERR);
                basicRet.setMessage("用户帐号金额不足，不可撤销");
                return  basicRet;
            }


            //memberService.updateSellerMemberBalance(member.getId(),member.getSellerbanlance().subtract(salerCapital.getOrdercapital()),member.getSellerfreezebanlance());
            memberService.updateSellerMemberBalanceInDb(member.getId(),salerCapital.getOrdercapital().multiply(Quantity.BIG_DECIMAL_MINUS_1),Quantity.BIG_DECIMAL_0);

            SalerCapital updatesalerCapital = new SalerCapital();
            updatesalerCapital.setId(salerCapital.getId());
            updatesalerCapital.setRechargestate(Quantity.STATE_2);
            updatesalerCapital.setVerify(admin.getUsername());
            if(StringUtils.hasText(remark)){
                updatesalerCapital.setRemark(salerCapital.getRemark()+"--"+remark);
            }
            salerCapitalService.updateByPrimaryKeySelective(updatesalerCapital);
        }

        basicRet.setMessage("操作成功");
        basicRet.setResult(BasicRet.SUCCESS);
        return  basicRet;
    }





    //审核通过rechargestate 状态转变为4,不通过为5
    @RequestMapping(value = "/seller/checkWithdrawCas",method = RequestMethod.POST)
    @ApiOperation("对卖家提现申请进行审核")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "提现记录id",name = "id",required = true,dataType = "int",paramType = "query"),
            @ApiImplicitParam(value = "状态4=审核通过5=审核不通过",name = "rechargestate",required = true,dataType = "int",paramType = "query"),
            @ApiImplicitParam(value = "备注",name = "remark",required = false,dataType = "int",paramType = "query"),
    })
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.RECHARGEREVIEW + "')")
    public  BasicRet checkWithdrawCasSaler(@RequestParam(required = true) long id,
                                      @RequestParam(required = true ) short rechargestate,
                                      @RequestParam(required =  false,defaultValue = "") String remark,
                                      Model model){
        BasicRet basicRet =  new BasicRet();

        Admin admin = (Admin) model.asMap().get(AppConstant.ADMIN_SESSION_NAME);

        if(rechargestate != 4 && rechargestate != 5){
            basicRet.setMessage("审核状态值非法");
            basicRet.setResult(BasicRet.ERR);
            return  basicRet;
        }

        SalerCapital salerCapital =  salerCapitalService.getById(id);

        if(salerCapital == null){
            basicRet.setMessage("未查询到该提现记录");
            basicRet.setResult(BasicRet.ERR);
            return  basicRet;
        }

        if(salerCapital.getRechargestate() != 3 && salerCapital.getCapitaltype() != 5){
            basicRet.setMessage("提现记录状态不正确");
            basicRet.setResult(BasicRet.ERR);
            return  basicRet;
        }


        if(salerCapital.getCapitaltype() != 5 && salerCapital.getCapitaltype() != 11){
            return  new BasicRet(BasicRet.ERR,"该记录不是提现申请");
        }


        Member member =  memberService.getMemberById(salerCapital.getMemberid());
        if(member == null){
            basicRet.setMessage("要提现的用户不存在");
            basicRet.setResult(BasicRet.ERR);
            return  basicRet;
        }

       SalerCapital updateSalerCapital = new SalerCapital();
//       if(rechargestate == 4 && salerCapital.getCapitaltype() == 5) {  //卖家余额提现
//           BigDecimal balance = member.getSellerbanlance() == null ? new BigDecimal(0) : member.getSellerbanlance();
//           if (balance.compareTo(salerCapital.getOrdercapital()) == -1) {
//               basicRet.setMessage("帐号余额小于提现金额，不可审核通过");
//               basicRet.setResult(BasicRet.ERR);
//               return basicRet;
//           }
//       }else if(rechargestate == 4 && salerCapital.getCapitaltype() == 11){ //卖家货款提现
//           BigDecimal balance = member.getGoodsbanlance() == null ? new BigDecimal(0) : member.getGoodsbanlance();
//           if (balance.compareTo(salerCapital.getOrdercapital()) == -1) {
//               basicRet.setMessage("帐号货款余额小于提现金额，不可审核通过");
//               basicRet.setResult(BasicRet.ERR);
//               return basicRet;
//           }
//       }


        if(rechargestate == 5 && salerCapital.getCapitaltype() == 5) {  //卖家余额提现 审核不通过，资金返回卖家余额
           memberService.updateSellerMemberBalanceInDb(salerCapital.getMemberid(),salerCapital.getOrdercapital(),new BigDecimal(0));
        }else if(rechargestate == 5 && salerCapital.getCapitaltype() == 11){ //卖家货款提现 审核不通过
            memberService.updateSellerMemberGoodsBalanceInDb(salerCapital.getMemberid(),salerCapital.getOrdercapital());
        }


        updateSalerCapital.setId(salerCapital.getId());
        updateSalerCapital.setRechargestate(rechargestate);
        updateSalerCapital.setValidatemark(remark);
        updateSalerCapital.setVerify(admin.getUsername());
        updateSalerCapital.setVerify(admin.getUsername());
        updateSalerCapital.setOperatetime(new Date());

       salerCapitalService.updateByPrimaryKeySelective(updateSalerCapital);

       basicRet.setResult(BasicRet.SUCCESS);
       basicRet.setMessage("审核成功");
       return  basicRet;
    }






    //审核通过rechargestate 状态转变为4,不通过为5
    @RequestMapping(value = "/buyer/checkWithdrawCas",method = RequestMethod.POST)
    @ApiOperation("对买家提现申请进行审核")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "提现记录id",name = "id",required = true,dataType = "int",paramType = "query"),
            @ApiImplicitParam(value = "状态4=审核通过5=审核不通过",name = "rechargestate",required = true,dataType = "int",paramType = "query"),
            @ApiImplicitParam(value = "备注",name = "remark",required = false,dataType = "int",paramType = "query"),
    })
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.RECHARGEREVIEW + "')")
    public  BasicRet checkWithdrawCasBuyer(@RequestParam(required = true) long id,
                                      @RequestParam(required = true ) short rechargestate,
                                      @RequestParam(required =  false,defaultValue = "") String remark,
                                      Model model){
        BasicRet basicRet =  new BasicRet();

        Admin admin = (Admin) model.asMap().get(AppConstant.ADMIN_SESSION_NAME);

        if(rechargestate != 4 && rechargestate != 5){
            basicRet.setMessage("审核状态值非法");
            basicRet.setResult(BasicRet.ERR);
            return  basicRet;
        }


        BuyerCapital buyerCapital =  buyerCapitalService.getById(id);

        if(buyerCapital == null){
            basicRet.setMessage("未查询到该提现记录");
            basicRet.setResult(BasicRet.ERR);
            return  basicRet;
        }

        if(buyerCapital.getRechargestate() != 3 && buyerCapital.getCapitaltype() != 5 ){
            basicRet.setMessage("提现记录状态不正确");
            basicRet.setResult(BasicRet.ERR);
            return  basicRet;
        }


        Member member =  memberService.getMemberById(buyerCapital.getMemberid());
        if(member == null){
            basicRet.setMessage("要提现的用户不存在");
            basicRet.setResult(BasicRet.ERR);
            return  basicRet;
        }

//        if(rechargestate == 4) {
//            BigDecimal balance = member.getBalance();
//            if (balance.compareTo(buyerCapital.getCapital()) == -1) {
//                basicRet.setMessage("帐号余额小于提现金额，不可审核通过");
//                basicRet.setResult(BasicRet.ERR);
//                return basicRet;
//            }
//        }

        if(rechargestate == 5){  //审核不通过，提现金额返回买家余额
            memberService.updateBuyerMemberBalanceInDb(buyerCapital.getMemberid(),buyerCapital.getCapital());
        }



        BuyerCapital updatebuyerCapital = new BuyerCapital();
        updatebuyerCapital.setId(buyerCapital.getId());
        updatebuyerCapital.setRechargestate(rechargestate);
        updatebuyerCapital.setValidatemark(remark);
        updatebuyerCapital.setVerify(admin.getUsername());
        updatebuyerCapital.setOperatetime(new Date());

        buyerCapitalService.updateByPrimaryKeySelective(updatebuyerCapital);


        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("审核成功");
        return  basicRet;
    }




    @RequestMapping(value = "/buyer/withdrawCasRemittance",method = RequestMethod.POST)
    @ApiOperation("买家提现财务打款确认")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "提现记录id",name = "id",required = true,dataType = "int",paramType = "query"),
            @ApiImplicitParam(value = "状态 1=成功 2=失败",name = "rechargestate",required = true,dataType = "int",paramType = "query"),
            @ApiImplicitParam(value = "备注",name = "remark",required = false,dataType = "int",paramType = "query"),
    })
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.RECHARGEREVIEW + "')")
    public  BasicRet withdrawCasRemittanceBuyer(@RequestParam(required =  true) long id,
                                                @RequestParam(required =  true) short rechargestate,
                                                @RequestParam(required = false,defaultValue = "") String remark,
                                                Model model){

        BasicRet basicRet = new BasicRet();

        Admin admin = (Admin) model.asMap().get(AppConstant.ADMIN_SESSION_NAME);

        if(rechargestate != 1 && rechargestate != 2){
            basicRet.setMessage("状态值非法");
            basicRet.setResult(BasicRet.ERR);
            return  basicRet;
        }


        BuyerCapital buyerCapital =  buyerCapitalService.getById(id);
        if(buyerCapital == null){
            basicRet.setMessage("未查询到该提现记录");
            basicRet.setResult(BasicRet.ERR);
            return  basicRet;
        }

        if(buyerCapital.getRechargestate() != 4 ){
            basicRet.setMessage("提现记录状态不正确");
            basicRet.setResult(BasicRet.ERR);
            return  basicRet;
        }


        Member member =  memberService.getMemberById(buyerCapital.getMemberid());
        if(member == null){
            basicRet.setMessage("要提现的用户不存在");
            basicRet.setResult(BasicRet.ERR);
            return  basicRet;
        }

        BuyerCapital updateBuyerCapital = new BuyerCapital();
        if(rechargestate == 1) {
//            BigDecimal balance = member.getBalance();
//            if (balance.compareTo(buyerCapital.getCapital()) == -1) {
//                basicRet.setMessage("帐号余额小于提现金额");
//                basicRet.setResult(BasicRet.ERR);
//                return basicRet;
//            }
//
//            //帐号余额减少
//            memberService.updateBuyerMemberBalance(member.getId(),balance.subtract(buyerCapital.getCapital()));

            updateBuyerCapital.setSuccesstime(new Date());
        }else if(rechargestate ==2){  //失败，余额返回买家余额
            memberService.updateBuyerMemberBalanceInDb(buyerCapital.getMemberid(),buyerCapital.getCapital());
        }



        updateBuyerCapital.setId(buyerCapital.getId());
        updateBuyerCapital.setOperation(admin.getUsername());
        if(StringUtils.hasText(remark)) {
            updateBuyerCapital.setRemark(remark);
        }
        updateBuyerCapital.setRechargestate(rechargestate);


        buyerCapitalService.updateByPrimaryKeySelective(updateBuyerCapital);



        basicRet.setMessage("设置成功");
        basicRet.setResult(BasicRet.SUCCESS);

        return  basicRet;
    }



    @RequestMapping(value = "/seller/withdrawCasRemittance",method = RequestMethod.POST)
    @ApiOperation("卖家提现财务打款确认")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "提现记录id",name = "id",required = true,dataType = "int",paramType = "query"),
            @ApiImplicitParam(value = "状态 1=成功 2=失败",name = "rechargestate",required = true,dataType = "int",paramType = "query"),
            @ApiImplicitParam(value = "备注",name = "remark",required = false,dataType = "int",paramType = "query"),
    })
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.RECHARGEREVIEW + "')")
    public  BasicRet withdrawCasRemittanceSeller(@RequestParam(required =  true) long id,
                                                @RequestParam(required =  true) short rechargestate,
                                                @RequestParam(required = false,defaultValue = "") String remark,
                                                Model model){

        BasicRet basicRet = new BasicRet();

        Admin admin = (Admin) model.asMap().get(AppConstant.ADMIN_SESSION_NAME);

        if(rechargestate != 1 && rechargestate != 2){
            basicRet.setMessage("状态值非法");
            basicRet.setResult(BasicRet.ERR);
            return  basicRet;
        }


         SalerCapital salerCapital =  salerCapitalService.getById(id);
        if(salerCapital == null){
            basicRet.setMessage("未查询到该提现记录");
            basicRet.setResult(BasicRet.ERR);
            return  basicRet;
        }

        if(salerCapital.getRechargestate() != 4 ){
            basicRet.setMessage("提现记录状态不正确");
            basicRet.setResult(BasicRet.ERR);
            return  basicRet;
        }

        Member member =  memberService.getMemberById(salerCapital.getMemberid());
        if(member == null){
            basicRet.setMessage("要提现的用户不存在");
            basicRet.setResult(BasicRet.ERR);
            return  basicRet;
        }

        SalerCapital updateSalerCapital = new SalerCapital();
        if(rechargestate == 1) { //卖家余额提现
            updateSalerCapital.setSuccesstime(new Date());
        }else if(rechargestate == 2){ //卖家货款提现
            if(salerCapital.getCapitaltype() == 5){ //余额提现失败，返回卖家余额
                memberService.updateSellerMemberBalanceInDb(salerCapital.getMemberid(),salerCapital.getOrdercapital(),new BigDecimal(0));
            }else if(salerCapital.getCapitaltype() == 11){
                memberService.updateSellerMemberGoodsBalanceInDb(salerCapital.getMemberid(),salerCapital.getOrdercapital());
            }
        }

        updateSalerCapital.setId(salerCapital.getId());
        updateSalerCapital.setOperation(admin.getUsername());
        if(StringUtils.hasText(remark)) {
            updateSalerCapital.setRemark(remark);
        }
        updateSalerCapital.setRechargestate(rechargestate);
        salerCapitalService.updateByPrimaryKeySelective(updateSalerCapital);


        basicRet.setMessage("设置成功");
        basicRet.setResult(BasicRet.SUCCESS);

        return  basicRet;
    }




    @RequestMapping(value = "/seller/detail",method = RequestMethod.POST)
    @ApiOperation("卖家资金详情")
    public BasicRet sellerDetail(@RequestParam(required = true) long id){
        SellerCashRet sellerCashRet =  new SellerCashRet();

        SalerCapital salerCapital =  salerCapitalService.getById(id);
        if(salerCapital == null){
            sellerCashRet.setMessage("未查询到信息");
            sellerCashRet.setResult(BasicRet.ERR);
            return  sellerCashRet;
        }

        sellerCashRet.data.salerCapital = salerCapital;
        sellerCashRet.setResult(BasicRet.SUCCESS);
        return  sellerCashRet;

    }



    @RequestMapping(value = "/buyer/detail",method = RequestMethod.POST)
    @ApiOperation("买家资金详情")
    public BasicRet buyerDetail(@RequestParam(required = true) long id){
        BuyerCashRet buyerCashRet = new BuyerCashRet();

        BuyerCapital buyerCapital =  buyerCapitalService.getById(id);
        if(buyerCapital == null){
            buyerCashRet.setMessage("未查询到信息");
            buyerCashRet.setResult(BasicRet.ERR);
            return  buyerCashRet;
        }

        buyerCashRet.data.buyerCapital = buyerCapital;
        buyerCashRet.setResult(BasicRet.SUCCESS);
        return  buyerCashRet;
    }






    private  class  SellerCashRet extends  BasicRet{
        private  class SellerCashData{
            private  SalerCapital salerCapital;

            public SalerCapital getSalerCapital() {
                return salerCapital;
            }

            public void setSalerCapital(SalerCapital salerCapital) {
                this.salerCapital = salerCapital;
            }
        }

        private  SellerCashData data = new SellerCashData();

        public SellerCashData getData() {
            return data;
        }

        public void setData(SellerCashData data) {
            this.data = data;
        }
    }



    private  class  BuyerCashRet extends  BasicRet{
        private  class BuyerCashData{
            private  BuyerCapital buyerCapital;

            public BuyerCapital getBuyerCapital() {
                return buyerCapital;
            }

            public void setBuyerCapital(BuyerCapital buyerCapital) {
                this.buyerCapital = buyerCapital;
            }
        }

        private  BuyerCashData data = new BuyerCashData();

        public BuyerCashData getData() {
            return data;
        }

        public void setData(BuyerCashData data) {
            this.data = data;
        }
    }



    private  class  CashRet extends  BasicRet{
        private  class  CashRetData{
            private PageInfo pageInfo;

            public PageInfo getPageInfo() {
                return pageInfo;
            }

            public void setPageInfo(PageInfo pageInfo) {
                this.pageInfo = pageInfo;
            }
        }

        private  CashRetData data = new CashRetData();

        public CashRetData getData() {
            return data;
        }

        public void setData(CashRetData data) {
            this.data = data;
        }
    }

    private static class BuyerCapitalAccountRet extends BasicRet{
        private List<BuyerCapitalAccountDto> list;

        public List<BuyerCapitalAccountDto> getList() {
            return list;
        }

        public void setList(List<BuyerCapitalAccountDto> list) {
            this.list = list;
        }
    }

}
