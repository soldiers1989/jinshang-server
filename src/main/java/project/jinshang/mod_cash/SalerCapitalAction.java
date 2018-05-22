package project.jinshang.mod_cash;

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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.jinshang.common.bean.PageRet;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.common.constant.SellerAuthorityConst;
import project.jinshang.common.utils.ExcelGen;
import project.jinshang.mod_cash.bean.SalerCapital;
import project.jinshang.mod_cash.bean.dto.SalerCapitalQueryDto;
import project.jinshang.mod_cash.service.SalerCapitalService;
import project.jinshang.mod_member.bean.Member;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * create : wyh
 * date : 2017/12/11
 */

@RestController
@RequestMapping(value = "/rest/seller")
@Api(tags = "卖家我的账户资金明细",description = "买家资金明细查询")
@SessionAttributes(AppConstant.MEMBER_SESSION_NAME)
public class SalerCapitalAction {

    @Autowired
    private SalerCapitalService salerCapitalService;

    @RequestMapping(value = "/cash/list",method = RequestMethod.POST)
    @ApiOperation(value = "卖家资金明细查询（capitaltype:类别0=消费1=充值2=退款3=提现4=授信5=授信还款）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tradetimeStart",value = "开始时间",required = false,paramType = "query",dataType = "date"),
            @ApiImplicitParam(name = "tradetimeEnd",value = "结束时间",required = false,paramType = "query",dataType = "date"),
            @ApiImplicitParam(name = "capitaltype",value = "资金类型0=订单金额1=上架保证金2=下架保证金3=退款金额4=充值5=提现6=违约金",defaultValue = "-1",required = false,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "tradeno",value = "交易编号",defaultValue = "",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "orderno",value = "订单号",defaultValue = "",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "rechargenumber",value = "充值单号",defaultValue = "",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "presentationnumber",value = "提现单号",defaultValue = "",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "paytype",value = "支付方式{0=支付宝1=微信2=银行卡3=余额4=授信}",defaultValue = "-1",required = false,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "rechargeperform",value = "平台0=微信1=支付宝2=线下平台3=授信4=银行卡",defaultValue = "-1",required = false,paramType = "query",dataType = "int"),
    })
    @PreAuthorize("hasAuthority('"+ SellerAuthorityConst.MYACCOUNT+"') || hasAuthority('"+ SellerAuthorityConst.ALL+"')")
    public PageRet list(SalerCapitalQueryDto  dto,
                        @RequestParam(required = true,defaultValue = "1") int pageNo,
                        @RequestParam(required = true,defaultValue = "20") int pageSize,
                        Model model){
        PageRet pageRet = new PageRet();

        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

        dto.setMemberid(member.getId());

        PageInfo pageInfo =  salerCapitalService.list(pageNo,pageSize,dto);

        pageRet.setMessage("查询成功");
        pageRet.setResult(BasicRet.SUCCESS);
        pageRet.data.setPageInfo(pageInfo);
        return pageRet;
    }




    @RequestMapping(value = "/exportExcel/cash/list",method = RequestMethod.GET)
    @ApiOperation(value = "卖家资金明细导出excel（capitaltype:类别0=消费1=充值2=退款3=提现4=授信5=授信还款）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tradetimeStart",value = "开始时间",required = false,paramType = "query",dataType = "date"),
            @ApiImplicitParam(name = "tradetimeEnd",value = "结束时间",required = false,paramType = "query",dataType = "date"),
            @ApiImplicitParam(name = "capitaltype",value = "资金类型0=订单金额1=上架保证金2=下架保证金3=退款金额4=充值5=提现6=违约金",defaultValue = "-1",required = false,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "tradeno",value = "交易编号",defaultValue = "",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "orderno",value = "订单号",defaultValue = "",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "rechargenumber",value = "充值单号",defaultValue = "",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "presentationnumber",value = "提现单号",defaultValue = "",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "paytype",value = "支付方式{0=支付宝1=微信2=银行卡3=余额4=授信}",defaultValue = "-1",required = false,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "rechargeperform",value = "平台0=微信1=支付宝2=线下平台3=授信4=银行卡",defaultValue = "-1",required = false,paramType = "query",dataType = "int"),
    })
    @PreAuthorize("hasAuthority('"+ SellerAuthorityConst.MYACCOUNT+"') || hasAuthority('"+ SellerAuthorityConst.ALL+"')")
    public ResponseEntity<InputStreamResource> list(SalerCapitalQueryDto  dto,Model model){
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        dto.setMemberid(member.getId());

        List<Map<String,Object>> resList =  salerCapitalService.listForSellerExportExcel(dto);

        XSSFWorkbook workbook = null;
        String name = "卖家导出资金明细";

        String[] rowTitles =  new String[]{"时间","类型","订单号","订单金额","保证金","违约金","退款","状态"};

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











    @RequestMapping(value = "/cash/detail",method = RequestMethod.POST)
    @ApiOperation(value = "卖家资金详细信息")
    @PreAuthorize("hasAuthority('"+ SellerAuthorityConst.MYACCOUNT+"') || hasAuthority('"+ SellerAuthorityConst.ALL+"')")
    public SalerCapitalRet detail(@RequestParam(required = true) long id,Model model){
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        SalerCapitalRet salerCapitalRet = new SalerCapitalRet();

        SalerCapital salerCapital =  salerCapitalService.getById(id);
        if(salerCapital == null){
            salerCapitalRet.setMessage("资金明细不存在");
            salerCapitalRet.setResult(BasicRet.ERR);
            return  salerCapitalRet;
        }

        if(!salerCapital.getMemberid().equals(member.getId())){
            salerCapitalRet.setMessage("资金明细不属于你");
            salerCapitalRet.setResult(BasicRet.ERR);
            return  salerCapitalRet;
        }

        salerCapitalRet.data = salerCapital;
        salerCapitalRet.setResult(BasicRet.SUCCESS);
        return  salerCapitalRet;
    }


    private  class  SalerCapitalRet extends  BasicRet{
        private SalerCapital data;

        public SalerCapital getData() {
            return data;
        }

        public void setData(SalerCapital data) {
            this.data = data;
        }
    }

}
