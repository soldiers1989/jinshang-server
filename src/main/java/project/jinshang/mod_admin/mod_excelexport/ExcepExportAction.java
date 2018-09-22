package project.jinshang.mod_admin.mod_excelexport;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import mizuki.project.core.restserver.config.BasicRet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import project.jinshang.common.constant.AdminAuthorityConst;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.common.utils.ExcelGen;
import project.jinshang.common.utils.StringUtils;
import project.jinshang.mod_admin.mod_inte.bean.IntegralQueryParam;
import project.jinshang.mod_admin.mod_inte.service.IntegralService;
import project.jinshang.mod_company.service.AdminShopService;
import project.jinshang.mod_product.bean.BackQueryParam;
import project.jinshang.mod_product.bean.BillQueryParam;
import project.jinshang.mod_product.bean.OrderQueryParam;
import project.jinshang.mod_product.bean.SellerBillRecordQueryParam;
import project.jinshang.mod_product.service.OrdersService;
import project.jinshang.mod_server.bean.ServerPayQueryParam;
import project.jinshang.mod_server.bean.SettleQueryParam;
import project.jinshang.mod_server.service.MemberServerService;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

@RestController
@RequestMapping("/rest/admin/excelexport")
@SessionAttributes({AppConstant.ADMIN_SESSION_NAME})
@Api(tags = "后台导出excel模块",description = "后台导出excel模块")
public class ExcepExportAction {

    @Autowired
    private OrdersService ordersService;
    @Autowired
    private IntegralService integralService;

    @Autowired
    private MemberServerService memberServerService;

    @Autowired
    private AdminShopService adminShopService;

    public static Map<String,Long> map = new HashMap();

    private static Map<String,Object> time = new HashMap<String,Object>();

    @RequestMapping(value = "/getStateForExcel",method = RequestMethod.POST)
    @ApiOperation(value = "判断是否正在导出excel")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "导出数据的类型:orders/product/otherproduct/seller/memberinfo", name = "type", required = false, paramType = "query", dataType = "string")
    })
    public BasicRet getStateForExcel(@RequestParam(required = false) String type){
        BasicRet basicRet = new BasicRet();

        if(!StringUtils.hasText(type)){
            basicRet.setMessage("no type");
            basicRet.setResult(BasicRet.ERR);
            return basicRet;
        }else {
            if (!map.containsKey(type)) {
                basicRet.setMessage("success");
                basicRet.setResult(BasicRet.SUCCESS);
                return basicRet;
            } else {
                long time1 = System.currentTimeMillis();
                if(!time.containsKey("time")) {
                    time.put("time", time1);
                    time.put("type",type);
                }
                if(time1-Long.parseLong(time.get("time").toString())>120000 && type.equals(time.get("type").toString())){
                    time.remove("time");
                    time.remove("type");
                    map.remove(type);
                    basicRet.setMessage("success");
                    basicRet.setResult(BasicRet.SUCCESS);
                    return basicRet;
                }
                basicRet.setMessage("has download task");
                basicRet.setResult(BasicRet.ERR);
                return basicRet;
            }
        }

    }


    @RequestMapping(value = "/exportOrdersExcel",method = RequestMethod.GET)
    @ApiOperation(value = "导出订单列表")
    @PreAuthorize("hasAnyAuthority('"+AdminAuthorityConst.ORDERMANAGEMENTEXCEL+"')")
    public ResponseEntity<InputStreamResource> exportOrdersExcel(OrderQueryParam param) throws IOException {

        long time=System.currentTimeMillis();
        map.put("orders",time);

        if(param.getStand()!=null){
            param.setStand(param.getStand().toUpperCase());
        }
        XSSFWorkbook workbook = null;
        try {
            String[] rowTitles = new String[]{"订单号","下单时间", "合同号",  "交易号", "买家", "卖家", "订单类型", "订单来源", "商品名称", "规格","商品分类", "材质", "牌号", "品牌", "印记", "表面处理", "包装方式", "单位", "单价", "订购量", "货款金额", "开票抬头", "税号", "开户行", "开户账号", "开票地址", "电话", "是否开票", "订单状态", "项目","收件人","收货地址","付款方式","物流公司","快递单号","业务员","第三方支付单号","业务单号"};
            String[] sumCols = new String[]{"货款金额","订购量"};

            List<Map<String, Object>> list = ordersService.getExcelOrders(param);

            //workbook = ExcelUtils.poiSteColumnMergCeellExcel("订单列表",rowTitles,list,0,sumCols);
            workbook = ExcelGen.common("订单列表", rowTitles, list, sumCols);
            if (workbook != null) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                workbook.write(baos);
                System.out.println(baos.toByteArray().length);
                HttpHeaders headers = new HttpHeaders();
                headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
                headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", new String("订单列表.xlsx".getBytes(), "iso-8859-1")));
                headers.add("Pragma", "no-cache");
                headers.add("Expires", "0");
                String contentType = "application/vnd.ms-excel";
                return ResponseEntity.ok().headers(headers).contentType(MediaType.parseMediaType(contentType))
                        .body(new InputStreamResource(new ByteArrayInputStream(baos.toByteArray())));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            map.remove("orders");
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

    @RequestMapping(value = "/exportBillRecordExcel",method = RequestMethod.GET)
    @ApiOperation(value = "导出开票列表")
    public ResponseEntity<InputStreamResource> exportBillRecordExcel(HttpServletResponse response, BillQueryParam billQueryParam){

        XSSFWorkbook workbook = null;
        try {
            String[] rowTitles = new String[]{"开票日期","会员编码","会员名称","订单号","订单金额","开票金额","开票类型","发票类型","发票抬头","税号","发票号","物流公司","运输单号","开票状态"};

            List<Map<String,Object>> list = ordersService.getBillRecordList(billQueryParam);
            workbook = ExcelGen.common("开票列表", rowTitles, list, null);
            if (workbook != null) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                workbook.write(baos);
                System.out.println(baos.toByteArray().length);
                HttpHeaders headers = new HttpHeaders();
                headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
                headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", new String("开票列表.xlsx".getBytes(), "iso-8859-1")));
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

    @RequestMapping(value = "/exportProductBackExcel",method = RequestMethod.GET)
    @ApiOperation(value = "导出退货列表")
    public ResponseEntity<InputStreamResource> exportProductBackExcel(HttpServletResponse response, BackQueryParam backQueryParam){
        XSSFWorkbook workbook = null;
        try {
            String[] rowTitles = new String[]{"下单时间","合同号","订单号","交易号","买家","卖家","退货数量","退货金额","退货单号","订单类型","订单来源","商品名称","规格","材质","牌号","品牌","印记","表面处理","包装方式","单位","单价","订购量","货款金额"};
            List<Map<String,Object>> list = ordersService.getOrderProductBackExcelList(backQueryParam);
            workbook = ExcelGen.common("退货列表", rowTitles, list, null);
            if (workbook != null) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                workbook.write(baos);
                System.out.println(baos.toByteArray().length);
                HttpHeaders headers = new HttpHeaders();
                headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
                headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", new String("退货列表.xlsx".getBytes(), "iso-8859-1")));
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


    @RequestMapping(value = "/exportIntegralExcel",method = RequestMethod.GET)
    @ApiOperation(value = "导出积分列表")
    public ResponseEntity<InputStreamResource> exportIntegralExcel(HttpServletResponse response, IntegralQueryParam param){

        XSSFWorkbook workbook = null;
        try {
            String[] rowTitles = new String[]{"会员编号","会员名","可用积分","会员等级","历史积分","会员注册时间"};
            List<Map<String,Object>> list = integralService.getIntegralExcel(param);
            workbook = ExcelGen.common("积分列表", rowTitles, list, null);
            if (workbook != null) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                workbook.write(baos);
                System.out.println(baos.toByteArray().length);
                HttpHeaders headers = new HttpHeaders();
                headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
                headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", new String("积分列表.xlsx".getBytes(), "iso-8859-1")));
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

    @RequestMapping(value = "/exportSellerBillExcel",method = RequestMethod.GET)
    @ApiOperation(value = "导出商家开票列表")
    public ResponseEntity<InputStreamResource> exportSellerBillExcel(HttpServletResponse response, SellerBillRecordQueryParam param){

        XSSFWorkbook workbook = null;
        try {
            String[] rowTitles = new String[]{"开票日期","商家编号","店铺名称","开票金额","物流公司","快递单号","发票类型","发票号","状态"};
            List<Map<String,Object>> list = ordersService.getSellerBillRecordExcel(param);
            workbook = ExcelGen.common("商家开票列表", rowTitles, list, null);
            if (workbook != null) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                workbook.write(baos);
                System.out.println(baos.toByteArray().length);
                HttpHeaders headers = new HttpHeaders();
                headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
                headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", new String("商家开票列表.xlsx".getBytes(), "iso-8859-1")));
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

    @RequestMapping(value = "/getExcelExportServerPay",method = RequestMethod.GET)
    @ApiOperation(value = "服务商结算excel导出")
    public ResponseEntity<InputStreamResource> getExcelExportServerPay(HttpServletResponse response, ServerPayQueryParam param){

        XSSFWorkbook workbook = null;
        try {
            String[] rowTitles = new String[]{"服务商","地区","省市","服务费占比%","服务费"};
            List<Map<String,Object>> list = memberServerService.getExcelExportServerPay(param);
            workbook = ExcelGen.common("服务商服务费列表", rowTitles, list, null);
            if (workbook != null) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                workbook.write(baos);
                System.out.println(baos.toByteArray().length);
                HttpHeaders headers = new HttpHeaders();
                headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
                headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", new String("服务商服务费列表.xlsx".getBytes(), "iso-8859-1")));
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


    @RequestMapping(value = "/getExcelExportServerOrder",method = RequestMethod.GET)
    @ApiOperation(value = "结算详情excel导出")
    public ResponseEntity<InputStreamResource> getExcelExportServerOrder(HttpServletResponse response, SettleQueryParam param){

        XSSFWorkbook workbook = null;
        try {
            String[] rowTitles = new String[]{"日期","地区","买家","会员号","完成时间","服务费","订单号","订单金额","商品金额","商品","服务分类","服务费利率","服务费占比"};
            List<Map<String,Object>> list = memberServerService.getExcelExportServerOrder(param);
            workbook = ExcelGen.common("服务商结算详情列表", rowTitles, list, null);
            if (workbook != null) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                workbook.write(baos);
                System.out.println(baos.toByteArray().length);
                HttpHeaders headers = new HttpHeaders();
                headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
                headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", new String("服务商结算详情列表.xlsx".getBytes(), "iso-8859-1")));
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


    @GetMapping(value = "/listshop")
    @ApiOperation(value = "Excel导出商家信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "companyname",value = "店铺名",dataType = "string",required = false,paramType = "query"),
            @ApiImplicitParam(name = "username",value = "店主账号",dataType = "string",required = false,paramType = "query"),
            @ApiImplicitParam(name = "shopgradeid",value = "店铺等级",dataType = "int",required = false,paramType = "query"),
            @ApiImplicitParam(name = "validate",value = "审核状态 0-未审核，1-通过，2-未通过，3-删除",dataType = "int",defaultValue = "-1",required = false,paramType = "query"),
    })
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.BUSINESSMANAGEMENTEXCEL + "')")
    public ResponseEntity<InputStreamResource> exportExcellistshop(@RequestParam(required = false,defaultValue ="" )String companyname,
                                                                   @RequestParam(required = false,defaultValue ="" )String username,
                                                                   @RequestParam(required = false,defaultValue ="0" ) int shopgradeid,
                                                                   @RequestParam(required = false,defaultValue = "-1") int validate, HttpServletResponse res) throws UnsupportedEncodingException {

        long time=System.currentTimeMillis();
        map.put("seller",time);

        List<Map<String,Object>> resList = adminShopService.listShopForAdminExcel(companyname, username, shopgradeid,validate);

        String[] rowTitles = new String[]{"商家编号","店铺名称","产品类型","商家账号","商家所在地","详细地址","商家等级",
                "创店时间","状态","公司名称","公司电话","联系电话","员工数","注册资金",
                "营业执照号","执照有效期","法定经营范围","银行账号","开户银行","支付宝账号","微信号","纳税登记号","纳税人识别号"
        };


        XSSFWorkbook workbook = null;
        try {
            workbook = ExcelGen.common("商家信息列表",rowTitles,resList,null);
            if(workbook!=null){
                ByteArrayOutputStream baos=new   ByteArrayOutputStream();
                workbook.write(baos);
                //System.out.println(baos.toByteArray().length);
                HttpHeaders headers = new HttpHeaders();
                headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
                headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", new String("商家信息列表.xlsx".getBytes(),"iso-8859-1")));

                // headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", new String("商家信息列表.xlsx".getBytes("utf-8"),"ISO8859-1")));

                headers.add("Pragma", "no-cache");
                headers.add("Expires", "0");
                String contentType = "application/vnd.ms-excel";
                //time.clear();
                return ResponseEntity.ok()
                        .headers(headers).contentType(MediaType.parseMediaType(contentType))
                        .body(new InputStreamResource(new ByteArrayInputStream(baos.toByteArray())));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            map.remove("seller");
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


}
