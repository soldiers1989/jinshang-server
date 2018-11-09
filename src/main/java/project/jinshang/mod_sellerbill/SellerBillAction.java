package project.jinshang.mod_sellerbill;


import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import mizuki.project.core.restserver.config.BasicRet;
import net.sf.json.JSONObject;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.jinshang.common.bean.Page;
import project.jinshang.common.bean.PageRet;
import project.jinshang.common.constant.AdminAuthorityConst;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.common.constant.Quantity;
import project.jinshang.common.constant.SellerAuthorityConst;
import project.jinshang.common.utils.*;
import project.jinshang.mod_cash.bean.SalerCapital;
import project.jinshang.mod_cash.bean.dto.SalerCapitalQueryDto;
import project.jinshang.mod_cash.service.SalerCapitalService;
import project.jinshang.mod_member.bean.Member;
import project.jinshang.mod_member.service.MemberService;
import project.jinshang.mod_product.bean.OrderProduct;
import project.jinshang.mod_product.bean.OrderQueryParam;
import project.jinshang.mod_product.bean.Orders;
import project.jinshang.mod_product.bean.OrdersExample;
import project.jinshang.mod_product.service.OrdersService;
import project.jinshang.mod_sellerbill.bean.*;
import project.jinshang.mod_sellerbill.service.SellerBillBreakService;
import project.jinshang.mod_sellerbill.service.SellerBillOrderService;
import project.jinshang.mod_sellerbill.service.SellerBillServices;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

@RestController
@Api(tags = "卖家端开票模块",description = "卖家端开票管理，卖家向平台开票")
@Transactional(rollbackFor = Exception.class)
@SessionAttributes(value ={AppConstant.MEMBER_SESSION_NAME} )
@RequestMapping("/rest/seller/sellerbill")
public class SellerBillAction {

    @Resource
    private OrdersService ordersService;

    @Resource
    private SellerBillServices sellerBillServices;

    @Resource
    private SellerBillOrderService sellerBillOrderService;

    @Autowired
    private SalerCapitalService salerCapitalService;

    @Autowired
    private SellerBillBreakService sellerBillBreakService;

    @Autowired
    private MemberService memberService;



    @PostMapping("/getSellerBillList")
    @ApiOperation("开票列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderno", value = "订单号", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "sellercompanyname", value = "公司名称", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "sellerid", value = "卖家id", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "type", value = "这个接口只传只传type为1时候   1= 订单发票   2=违约金发票", required = false, paramType = "query", dataType = "int"),
    })
    public PageRet getSellerBillList(SellerBillQuery sellerBillQuery,
                                     @RequestParam(defaultValue = "1") Integer pageNo,
                                     @RequestParam(defaultValue = "20") Integer pageSize ,Model model){

        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        sellerBillQuery.setSellerid(member.getId());

        PageInfo pageInfo = sellerBillServices.getSellerBillByPage(sellerBillQuery,pageNo,pageSize);

        PageRet pageRet = new PageRet();
        pageRet.setMessage("查询成功");
        pageRet.setResult(BasicRet.SUCCESS);
        pageRet.data.setPageInfo(pageInfo);
        return  pageRet;
    }

    @PostMapping("/getSellerPenaltyBillList")
    @ApiOperation("违约金开票列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderno", value = "订单号", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "sellercompanyname", value = "公司名称", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "sellerid", value = "卖家id", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "type", value = "只传type为2的时候 1= 订单发票   2=违约金发票", required = false, paramType = "query", dataType = "int"),
    })
    public PageRet getSellerPenaltyBillList(SellerBillQuery sellerBillQuery,
                                     @RequestParam(defaultValue = "1") Integer pageNo,
                                     @RequestParam(defaultValue = "20") Integer pageSize ,Model model){

        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        sellerBillQuery.setSellerid(member.getId());

        PageInfo pageInfo = sellerBillServices.getSellerBillByPage1(sellerBillQuery,pageNo,pageSize);

        PageRet pageRet = new PageRet();
        pageRet.setMessage("查询成功");
        pageRet.setResult(BasicRet.SUCCESS);
        pageRet.data.setPageInfo(pageInfo);
        return  pageRet;
    }


    @PostMapping("/getSellerPenaltBillDetail")
    @ApiOperation("查看违约金详细信息")
    public  SellerPenaltyBillDetailRet getSellerPenaltBillDetail(@RequestParam(required = true) long id,Model model){
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

        SellerPenaltyBillDetailRet sellerPenaltyBillDetailRet = new SellerPenaltyBillDetailRet();

        SellerBill oldSellerBill = sellerBillServices.getById(id);
        if(oldSellerBill == null){
            sellerPenaltyBillDetailRet.setMessage("记录不存在");
            sellerPenaltyBillDetailRet.setResult(BasicRet.ERR);
            return sellerPenaltyBillDetailRet;
        }

        if(!oldSellerBill.getSellerid().equals(member.getId())){
            sellerPenaltyBillDetailRet.setMessage("该记录不属于你");
            sellerPenaltyBillDetailRet.setResult(BasicRet.ERR);
            return  sellerPenaltyBillDetailRet;
        }


       /* JSONObject object = JSONObject.fromObject(oldSellerBill.getBilljson());
        String billjson = object.getString("value");
        //返回前端的只有value
        oldSellerBill.setBilljson(billjson);*/

        List<SellerBillBreak> sellerBillBreakList = sellerBillBreakService.getBySellerbillid(oldSellerBill.getId());
        //SellerBill sellerBill1 = sellerBillServices.getById(id);

        List<SellerBillBreakView> sellerBillBreakViewList = new ArrayList<>();
        for(SellerBillBreak sellerBillBreak: sellerBillBreakList){
            SellerBillBreakView view = new SellerBillBreakView();
            BeanUtils.copyProperties(sellerBillBreak,view);
            sellerBillBreakViewList.add(view);
        }

        sellerPenaltyBillDetailRet.getData().setSellerBill(oldSellerBill);
        sellerPenaltyBillDetailRet.getData().setSellerBillBreakList(sellerBillBreakViewList);
        sellerPenaltyBillDetailRet.setResult(BasicRet.SUCCESS);
        return  sellerPenaltyBillDetailRet;
    }


    @PostMapping("/getSellerBillDetail")
    @ApiOperation("查看开票详细信息")
    public  SellerBillDetailRet getSellerBillDetail(@RequestParam(required = true) long id,Model model){
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

        SellerBillDetailRet sellerBillDetailRet = new SellerBillDetailRet();

        SellerBill sellerBill = sellerBillServices.getById(id);
        if(sellerBill == null){
            sellerBillDetailRet.setMessage("记录不存在");
            sellerBillDetailRet.setResult(BasicRet.ERR);
            return sellerBillDetailRet;
        }

        if(!sellerBill.getSellerid().equals(member.getId())){
            sellerBillDetailRet.setMessage("该记录不属于你");
            sellerBillDetailRet.setResult(BasicRet.ERR);
            return  sellerBillDetailRet;
        }


        List<SellerBillOrder> sellerBillOrderList = sellerBillOrderService.getBySellerbillid(sellerBill.getId());



        List<SellerBillOrderView> list = new ArrayList<>();

        sellerBillOrderList.forEach(sellerBillOrder -> {
            SellerBillOrderView sellerBillOrderView = new SellerBillOrderView();
            BeanUtils.copyProperties(sellerBillOrder,sellerBillOrderView);
            List<OrderProduct> orderProductList = ordersService.getOrderProductByOrderId(sellerBillOrder.getOrderid());
            sellerBillOrderView.setOrderProductList(orderProductList);
            list.add(sellerBillOrderView);
        });


        sellerBillDetailRet.getData().setSellerBill(sellerBill);
        sellerBillDetailRet.getData().setSellerBillOrderViewList(list);

        sellerBillDetailRet.setResult(BasicRet.SUCCESS);
        sellerBillDetailRet.setMessage("查询成功");


        return  sellerBillDetailRet;
    }




    @PostMapping("/getWaitOpenBillList")
    @ApiOperation("查询待开票订单")
    public PageRet getWaitOpenBillList(@RequestParam(defaultValue = "1") int pageNo,@RequestParam(defaultValue = "20") int pageSize, Model model){
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

        PageInfo pageInfo = ordersService.getWaitOpenBillListByPage(member.getId(),pageNo,pageSize);

        PageRet pageRet = new PageRet();
        pageRet.data.setPageInfo(pageInfo);
        pageRet.setResult(BasicRet.SUCCESS);
        pageRet.setMessage("查询成功");
        return  pageRet;
    }

    @PostMapping("/getWaitOpenPenaltyBillList")
    @ApiOperation("查询违约金待开票订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tradetimeStart",value = "开始时间",required = false,paramType = "query",dataType = "date"),
            @ApiImplicitParam(name = "tradetimeEnd",value = "结束时间",required = false,paramType = "query",dataType = "date"),
            @ApiImplicitParam(name = "capitaltype",value = "资金类型0=订单金额1=上架保证金2=下架保证金3=退款金额4=充值5=余额提现6=买家违约金7=卖家违约金8=余款9=全款10=定金 11=货款提现",defaultValue = "-1",required = false,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "tradeno",value = "交易编号",defaultValue = "",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "orderno",value = "订单号",defaultValue = "",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "rechargenumber",value = "充值单号",defaultValue = "",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "presentationnumber",value = "提现单号",defaultValue = "",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "paytype",value = "支付方式{0=支付宝1=微信2=银行卡3=余额4=授信}",defaultValue = "-1",required = false,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "rechargeperform",value = "平台0=微信1=支付宝2=线下平台3=授信4=银行卡",defaultValue = "-1",required = false,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "withdrawtype", value = "提现方式{1=微信2=支付宝3=银行卡}", defaultValue = "-1", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "rechargestate", value = "状态{0=待处理1=成功2=失败3=待审核4=审核通过5=审核不通过}", defaultValue = "-1", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "billtoserver", value = "卖家是否已向平台开票开违约金发票 0-未开，1-已开，-1= 老数据", defaultValue = "-1", required = false, paramType = "query", dataType = "int"),
    })
    @PreAuthorize("hasAuthority('"+ SellerAuthorityConst.MYACCOUNT+"') || hasAuthority('"+ SellerAuthorityConst.ALL+"')")
    public PageRet getWaitOpenPenaltyBillList(@RequestParam(defaultValue = "1") int pageNo,@RequestParam(defaultValue = "20") int pageSize, Model model,SalerCapitalQueryDto dto){
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

        //PageInfo pageInfo = ordersService.getWaitOpenBillListByPage(member.getId(),pageNo,pageSize);
        dto.setMemberid(member.getId());
        PageInfo pageInfo =  salerCapitalService.list(pageNo,pageSize,dto);
        PageRet pageRet = new PageRet();
        pageRet.data.setPageInfo(pageInfo);
        pageRet.setResult(BasicRet.SUCCESS);
        pageRet.setMessage("查询成功");
        return  pageRet;
    }


    @PostMapping("/addSellerBill")
    @ApiOperation("添加发票记录")
    public BasicRet addSellerBill(Long[] ordersid,Model model){
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

        Set<Long> ordersidSet = new TreeSet<>(Arrays.asList(ordersid));

        if(ordersidSet.size() != ordersid.length){
            return  new BasicRet(BasicRet.ERR,"订单id有重复");
        }

        //订单总金额
        BigDecimal totalOrders = Quantity.BIG_DECIMAL_0;

        //佣金总金额
        BigDecimal totalBraeakPay = Quantity.BIG_DECIMAL_0;

        //总的开票金额
        BigDecimal totalBill = Quantity.BIG_DECIMAL_0;

        List<Orders> ordersList = ordersService.getOrdersByIds(ordersid);

        if(ordersList.size() != ordersid.length){
            return  new BasicRet(BasicRet.ERR,"提交的订单有不存在的");
        }

        List<SellerBillOrder> sellerBillOrders = new ArrayList<>();
        for(Long orderid : ordersid){
            for(Orders orders : ordersList){
                if(orderid.equals(orders.getId())){
                    if(orders.getOrderstatus() != Quantity.STATE_5){
                        return  new BasicRet(BasicRet.ERR,orders.getOrderno()+"订单状态不是已完成状态");
                    }

                    SellerBillOrder sellerBillOrder = new SellerBillOrder();
                    sellerBillOrder.setOrdermoney(orders.getFrozepay());
                    sellerBillOrder.setOrderno(orders.getOrderno());
                    sellerBillOrder.setOrderid(orders.getId());
                    sellerBillOrder.setBuyername(orders.getMembername());
                    sellerBillOrder.setFishtime(orders.getBuyerinspectiontime());
                    sellerBillOrder.setBreakpaymoney(orders.getBrokepay());

                    totalOrders = totalOrders.add(orders.getFrozepay());
                    totalBraeakPay = totalBraeakPay.add(orders.getBrokepay());
                    sellerBillOrders.add(sellerBillOrder);
                }
            }
        }

        //总的开票金额 = 订单总金额-佣金
        totalBraeakPay = totalBraeakPay.setScale(2,BigDecimal.ROUND_HALF_UP);
        totalBill = totalOrders.subtract(totalBraeakPay);

        SellerBill sellerBill = new SellerBill();
        sellerBill.setSellerid(member.getId());
        sellerBill.setNo(GenerateNo.getSellBillNo());
        sellerBill.setAdduser(member.getRealname());
        sellerBill.setMoney(totalBill);
        sellerBill.setTotalorders(totalOrders);
        sellerBill.setTotalbrokepay(totalBraeakPay);
        sellerBill.setState(Quantity.STATE_1);
        sellerBill.setAddtime(new Date());
        sellerBill.setOrdernum(ordersid.length);
        sellerBill.setSellercompanyname(member.getSellerCompanyInfo().getCompanyname());
        sellerBill.setSellerusername(member.getUsername());


        sellerBillServices.insert(sellerBill);

        sellerBillOrders.forEach(sellerBillOrder -> sellerBillOrder.setSellerbillid(sellerBill.getId()));

        sellerBillOrderService.batchInsert(sellerBillOrders);

        //将订单billtoserver标记为1状态
        for(Orders orders : ordersList){
            OrdersExample example = new OrdersExample();
            example.createCriteria().andIdEqualTo(orders.getId()).andOrderstatusEqualTo(Quantity.STATE_5).andBilltoserverEqualTo(Quantity.STATE_0);
            Orders updateOrders = new Orders();
            updateOrders.setBilltoserver(Quantity.STATE_1);
            int count = ordersService.updateByExampleSelective(updateOrders,example);
            if(count !=1){
                throw  new RuntimeException("订单状态不合法,请重新申请");
            }
        }


        return new BasicRet(BasicRet.SUCCESS,"添加成功");
    }

    @PostMapping("/addPenaltySellerBill")
    @ApiOperation("添加违约金发票记录")
    public BasicRet addPenaltySellerBill(Long[] salercapitalids,Model model){
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

        Set<Long> salercapitalidSet = new TreeSet<>(Arrays.asList(salercapitalids));

        if(salercapitalidSet.size() != salercapitalids.length){
            return  new BasicRet(BasicRet.ERR,"订单id有重复");
        }

        List<SalerCapital> salerCapitalList = salerCapitalService.getSalerCapitalByIds(salercapitalids);

        if(salerCapitalList.size()!= salercapitalids.length){
            return  new BasicRet(BasicRet.ERR,"提交的违约金明细有不存在的");
         }
        //订单总金额 勾选的每个订单总和
        BigDecimal totalOrders = Quantity.BIG_DECIMAL_0;

        //佣金总金额
        BigDecimal totalBraeakPay = Quantity.BIG_DECIMAL_0;

        //总的违约金开票金额
        BigDecimal totalBill = Quantity.BIG_DECIMAL_0;

        SellerBill sellerBill = new SellerBill();
        sellerBill.setSellerid(member.getId());
        sellerBill.setNo(GenerateNo.getSellBillNo());
        sellerBill.setAdduser(member.getRealname());
        sellerBill.setMoney(totalBill);
        sellerBill.setTotalorders(totalOrders);
        sellerBill.setTotalbrokepay(Quantity.BIG_DECIMAL_0);
        sellerBill.setState(Quantity.STATE_1);
        sellerBill.setAddtime(new Date());
        //sellerBill.setBillcreatetime(new Date());
        sellerBill.setOrdernum(salercapitalidSet.size());
        sellerBill.setSellercompanyname(member.getSellerCompanyInfo().getCompanyname());
        sellerBill.setSellerusername(member.getUsername());
        sellerBill.setType(Quantity.STATE_2);

        sellerBillServices.insert(sellerBill);

            for (SalerCapital salerCapital : salerCapitalList) {
                //同时向sellerbillbreak表存入
                //根据买家id查询买家信息
                //Member member1 = memberService.getMemberById(salerCapital.getBuyerid());
                SellerBillBreak sellerBillBreak = new SellerBillBreak();
                sellerBillBreak.setSellerbillid(sellerBill.getId());
                sellerBillBreak.setSalercapitalid(salerCapital.getId());
                sellerBillBreak.setTradeno(salerCapital.getTradeno());
                sellerBillBreak.setBreakmoney(salerCapital.getPenalty());
                //sellerBillBreak.setBuyername(member1.getUsername());

                sellerBillBreak.setBreaktime(salerCapital.getTradetime());
                sellerBillBreakService.insertSellerBillBreak(sellerBillBreak);

                //累加订单总金额
                totalOrders = totalOrders.add(salerCapital.getOrdercapital());
                //佣金总金额
                totalBraeakPay = totalBraeakPay.add(salerCapital.getBrokerage());
                //总的违约金开票金额
                totalBill = totalBill.add(salerCapital.getPenalty());

                SellerBill updateSellerBill1 = new SellerBill();
                updateSellerBill1.setId(sellerBill.getId());
                updateSellerBill1.setTotalorders(totalOrders);
                updateSellerBill1.setMoney(totalBill);
                updateSellerBill1.setTotalbrokepay(totalBraeakPay);
                updateSellerBill1.setBreaknum(salercapitalidSet.size());
                //开票金额等于违约金总金额
                updateSellerBill1.setTotalbreak(totalBill);
                sellerBillServices.update(updateSellerBill1);


                //将billtoserver标记为1状态
                SalerCapital updateSalerCapital = new SalerCapital();
                updateSalerCapital.setId(salerCapital.getId());
                updateSalerCapital.setBilltoserver(Quantity.STATE_1);
                salerCapitalService.updateByPrimaryKeySelective(updateSalerCapital);

            }


        return new BasicRet(BasicRet.SUCCESS,"添加成功");
    }

    @RequestMapping(value = "/exportSellerBill",method = RequestMethod.GET)
    @ApiOperation(value = "导出已添加的开票记录")
    public ResponseEntity<InputStreamResource> exportSellerBill(Long[] ordersid,Model model) throws IOException {

        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        XSSFWorkbook workbook = null;
        try {
            String[] rowTitles = new String[]{"订单号","下单时间", "合同号",  "交易号", "订单类型", "订单来源", "商品名称", "规格","商品分类", "材质", "牌号", "品牌", "印记", "表面处理", "包装方式", "单位","结算单价","销售单价", "订购量", "结算金额","销售总价","运费", "订单状态","付款方式"};
            String[] sumCols = new String[]{"订购量","销售总价","结算金额"};


            String orderid = "";
            for(int i=0;i<ordersid.length;i++){
                if(ordersid.length-i==1) {
                    orderid += ordersid[i];
                }else{
                    orderid += ordersid[i]+",";
                }
            }
            List<Map<String, Object>> list = ordersService.getExcelSellerBillOrders(member.getId(),orderid);

            //workbook = ExcelUtils.poiSteColumnMergCeellExcel("订单列表",rowTitles,list,0,sumCols);
            workbook = ExcelGen.commonSellBill("开票记录订单列表", rowTitles, list, sumCols);
            if (workbook != null) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                //sheet.addMergedRegion(new CellRangeAddress(firstRow, lastRow, firstCol, lastCol));

                workbook.write(baos);
                System.out.println(baos.toByteArray().length);
                HttpHeaders headers = new HttpHeaders();
                headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
                headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", new String("开票记录订单列表.xlsx".getBytes(), "iso-8859-1")));
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

    @RequestMapping(value = "/exportSellerPenaltyBill",method = RequestMethod.GET)
    @ApiOperation(value = "导出已添加的违约金开票记录")
    public ResponseEntity<InputStreamResource> exportSellerPenaltyBill(Long[] salercapitalids,Model model) throws IOException {

        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        XSSFWorkbook workbook = null;
        try {
            String[] rowTitles = new String[]{"违约订单号","完成时间", "交易号", "违约类型", "违约金额", "付款方式"};
            String[] sumCols = new String[]{"违约金额"};

            List<Map<String, Object>> list = salerCapitalService.getExcelSellerBillid(member.getId(),salercapitalids);

            workbook = ExcelGen.common("违约金开票记录列表", rowTitles, list, sumCols);
            if (workbook != null) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                workbook.write(baos);
                System.out.println(baos.toByteArray().length);
                HttpHeaders headers = new HttpHeaders();
                headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
                headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", new String("违约金开票记录列表.xlsx".getBytes(), "iso-8859-1")));
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




    @PostMapping("/delSellBill")
    @ApiOperation("删除发票记录")
    public BasicRet delSellBill(@RequestParam(required = true) Long id,Model model){
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

        SellerBill sellerBill = sellerBillServices.getById(id);
        if(sellerBill == null){
            return  new BasicRet(BasicRet.ERR,"记录不存在");
        }

        if(!sellerBill.getSellerid().equals(member.getId())){
            return  new BasicRet(BasicRet.ERR,"该记录不属于你");
        }

        if(sellerBill.getState() != Quantity.STATE_1 && sellerBill.getState() != Quantity.STATE_2){
            return  new BasicRet(BasicRet.ERR,"订单状态不可删除");
        }


        List<SellerBillOrder> sellerBillOrderList = sellerBillOrderService.getBySellerbillid(sellerBill.getId());

        //将订单billtoserver标记为0状态
        for(SellerBillOrder sellerBillOrder : sellerBillOrderList){
            OrdersExample example = new OrdersExample();
            example.createCriteria().andIdEqualTo(sellerBillOrder.getOrderid()).andOrderstatusEqualTo(Quantity.STATE_5).andBilltoserverEqualTo(Quantity.STATE_1);
            Orders updateOrders = new Orders();
            updateOrders.setBilltoserver(Quantity.STATE_0);
            int count = ordersService.updateByExampleSelective(updateOrders,example);
            if(count !=1){
                throw  new RuntimeException("订单状态不合法,请联系网站管理员");
            }
        }


        sellerBillOrderService.deleteBySellerbillid(sellerBill.getId());

        sellerBillServices.deleteById(sellerBill.getId());

        return  new BasicRet(BasicRet.SUCCESS,"删除成功");
    }


    @PostMapping("/delSellPenaltyBill")
    @ApiOperation("删除卖家违约金发票记录")
    public BasicRet delSellPenaltyBill(@RequestParam(required = true) Long id,Model model){
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

        SellerBill sellerBill = sellerBillServices.getById(id);
        if(sellerBill == null){
            return  new BasicRet(BasicRet.ERR,"记录不存在");
        }

        if(!sellerBill.getSellerid().equals(member.getId())){
            return  new BasicRet(BasicRet.ERR,"该记录不属于你");
        }

        if(sellerBill.getState() != Quantity.STATE_1 && sellerBill.getState() != Quantity.STATE_2){
            return  new BasicRet(BasicRet.ERR,"订单状态不可删除");
        }
        List<SellerBillOrder> sellerBillOrderList = sellerBillOrderService.getBySellerbillid(sellerBill.getId());

        //将订单billtoserver标记为0状态
        /*通过salercapital id查询 卖家开违约金发票表数据
        然后通过 卖家开违约金发票表 中的sellbill的id先删除sellbill
        再回来删除 卖家开违约金发票表
        最后 更新salercapital 中的状态*/

        List<SellerBillBreak> sellerBillBreakList = sellerBillBreakService.getBySellerbillid(sellerBill.getId());
        //删除sellserbill 表中数据
        sellerBillServices.deleteById(sellerBill.getId());
       //删除卖家开违约金发票表中数据
        for (SellerBillBreak sellerBillBreak:sellerBillBreakList) {
            //再回来更新状态
            SalerCapital updateSalerCapital = new SalerCapital();
            updateSalerCapital.setId(sellerBillBreak.getSalercapitalid());
            updateSalerCapital.setBilltoserver(Quantity.STATE_0);
            salerCapitalService.updateByPrimaryKeySelective(updateSalerCapital);
            sellerBillBreakService.deleteById(sellerBillBreak.getId());
        }

        return  new BasicRet(BasicRet.SUCCESS,"删除成功");
    }


    @PostMapping("/addBillRecordInfo")
    @ApiOperation("添加开票信息")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "发票json",name = "billjson",required = true,dataType = "string",paramType = "query"),
    })
    public BasicRet addBillRecordInfo(@RequestParam(required = true) long id,
                                      @RequestParam(required = true)String billjson,
                                      @RequestParam(required = true) String billtype,
                                      @RequestParam(required = true) Date billcreatetime,Model model){

        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

        SellerBill sellerBill = sellerBillServices.getById(id);
        if(sellerBill == null){
            return  new BasicRet(BasicRet.ERR,"记录不存在");
        }

        if(!sellerBill.getSellerid().equals(member.getId())){
            return  new BasicRet(BasicRet.ERR,"该记录不属于你");
        }

        if(sellerBill.getState() == 99){
            return  new BasicRet(BasicRet.ERR,"该记录不可修改");
        }


        List<BillRecord> billRecordList = GsonUtils.toList(billjson,BillRecord.class);
        BigDecimal totalBill = Quantity.BIG_DECIMAL_0;

        for(BillRecord billRecord : billRecordList){
            totalBill = totalBill.add(billRecord.getBillnum());
        }

        if(sellerBill.getMoney().compareTo(totalBill) != 0){
            return  new BasicRet(BasicRet.ERR,"开票总金额不对，无法提交");
        }

        SellerBill updateSellerBill = new SellerBill();
        updateSellerBill.setId(sellerBill.getId());
        updateSellerBill.setBilljson(billjson);
        updateSellerBill.setBillcreatetime(billcreatetime);
        updateSellerBill.setBilltype(billtype);
        updateSellerBill.setState(Quantity.STATE_2);

        sellerBillServices.update(updateSellerBill);


        return  new BasicRet(BasicRet.SUCCESS,"修改成功");
    }


    @PostMapping("/addPenaltyBillRecordInfo")
    @ApiOperation("添加违约金开票信息")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "发票json",name = "billjson",required = false,dataType = "string",paramType = "query"),
    })
    public BasicRet addPenaltyBillRecordInfo(@RequestParam(required = true) long id,
                                      @RequestParam(required = true)String billjson,
                                      @RequestParam(required = false) String billtype,
                                      @RequestParam(required = true) Date billcreatetime,Model model){

        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

        SellerBill sellerBill = sellerBillServices.getById(id);
        if(sellerBill == null){
            return  new BasicRet(BasicRet.ERR,"记录不存在");
        }

        if(!sellerBill.getSellerid().equals(member.getId())){
            return  new BasicRet(BasicRet.ERR,"该记录不属于你");
        }

        if(sellerBill.getState() == 99){
            return  new BasicRet(BasicRet.ERR,"该记录不可修改");
        }

        SellerBill updateSellerBill = new SellerBill();
        updateSellerBill.setId(sellerBill.getId());
        updateSellerBill.setState(Quantity.STATE_2);
        updateSellerBill.setBilljson(billjson);
        updateSellerBill.setBillcreatetime(billcreatetime);
        updateSellerBill.setBilltype(billtype);
        sellerBillServices.update(updateSellerBill);
        return  new BasicRet(BasicRet.SUCCESS,"修改成功");
    }


    private  class BillRecord{
        //发票代码
        private  String billno;
        //发票号码
        private  String billserial;
        //金额
        private  BigDecimal billnum;

        public String getBillno() {
            return billno;
        }

        public void setBillno(String billno) {
            this.billno = billno;
        }

        public String getBillserial() {
            return billserial;
        }

        public void setBillserial(String billserial) {
            this.billserial = billserial;
        }

        public BigDecimal getBillnum() {
            return billnum;
        }

        public void setBillnum(BigDecimal billnum) {
            this.billnum = billnum;
        }
    }

}
