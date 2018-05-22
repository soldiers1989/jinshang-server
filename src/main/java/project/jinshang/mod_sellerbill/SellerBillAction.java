package project.jinshang.mod_sellerbill;


import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import mizuki.project.core.restserver.config.BasicRet;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.jinshang.common.bean.PageRet;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.common.constant.Quantity;
import project.jinshang.common.utils.GenerateNo;
import project.jinshang.common.utils.GsonUtils;
import project.jinshang.common.utils.StringUtils;
import project.jinshang.mod_member.bean.Member;
import project.jinshang.mod_product.bean.OrderProduct;
import project.jinshang.mod_product.bean.Orders;
import project.jinshang.mod_product.bean.OrdersExample;
import project.jinshang.mod_product.service.OrdersService;
import project.jinshang.mod_sellerbill.bean.*;
import project.jinshang.mod_sellerbill.service.SellerBillOrderService;
import project.jinshang.mod_sellerbill.service.SellerBillServices;

import javax.annotation.Resource;
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



    @PostMapping("/getSellerBillList")
    @ApiOperation("开票列表")
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

        //总的开票金额 = 订单总金额-开票金额
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
