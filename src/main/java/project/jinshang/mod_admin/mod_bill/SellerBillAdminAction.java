package project.jinshang.mod_admin.mod_bill;


import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import mizuki.project.core.restserver.config.BasicRet;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.jinshang.common.bean.MemberLogOperator;
import project.jinshang.common.bean.PageRet;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.common.constant.Quantity;
import project.jinshang.mod_member.bean.Admin;
import project.jinshang.mod_member.bean.Member;
import project.jinshang.mod_member.service.MemberService;
import project.jinshang.mod_product.bean.OrderProduct;
import project.jinshang.mod_product.service.MemberOperateLogService;
import project.jinshang.mod_product.service.OrdersService;
import project.jinshang.mod_sellerbill.bean.*;
import project.jinshang.mod_sellerbill.service.SellerBillBreakService;
import project.jinshang.mod_sellerbill.service.SellerBillOrderService;
import project.jinshang.mod_sellerbill.service.SellerBillServices;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@Api(tags = "后台-卖家端开票管理模块",description = "对卖家开票审核、确认")
@Transactional(rollbackFor = Exception.class)
@SessionAttributes(value ={AppConstant.ADMIN_SESSION_NAME} )
@RequestMapping("/rest/admin/sellerbill")
public class SellerBillAdminAction {

    @Autowired
    private SellerBillServices sellerBillServices;

    @Autowired
    private SellerBillOrderService sellerBillOrderService;

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private MemberLogOperator memberLogOperator;

    @Autowired
    private  MemberOperateLogService memberOperateLogService;

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
            @ApiImplicitParam(name = "type", value = "1= 订单发票   2=违约金发票", required = false, paramType = "query", dataType = "int"),
    })
    public PageRet getSellerBillList(SellerBillQuery sellerBillQuery,
                                     @RequestParam(defaultValue = "1") Integer pageNo,
                                     @RequestParam(defaultValue = "20") Integer pageSize , Model model){
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

        Admin admin = (Admin) model.asMap().get(AppConstant.ADMIN_SESSION_NAME);
        sellerBillQuery.setSellerid(admin.getId());

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
        Admin admin = (Admin) model.asMap().get(AppConstant.ADMIN_SESSION_NAME);

        SellerPenaltyBillDetailRet sellerPenaltyBillDetailRet = new SellerPenaltyBillDetailRet();

        SellerBill oldSellerBill = sellerBillServices.getById(id);
        if(oldSellerBill == null){
            sellerPenaltyBillDetailRet.setMessage("记录不存在");
            sellerPenaltyBillDetailRet.setResult(BasicRet.ERR);
            return sellerPenaltyBillDetailRet;
        }

        List<SellerBillBreak> sellerBillBreakList = sellerBillBreakService.getBySellerbillid(oldSellerBill.getId());


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
    public SellerBillDetailRet getSellerBillDetail(@RequestParam(required = true) long id, Model model){

        SellerBillDetailRet sellerBillDetailRet = new SellerBillDetailRet();

        SellerBill sellerBill = sellerBillServices.getById(id);
        if(sellerBill == null){
            sellerBillDetailRet.setMessage("记录不存在");
            sellerBillDetailRet.setResult(BasicRet.ERR);
            return sellerBillDetailRet;
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



    @PostMapping("/checkSellerBill")
    @ApiOperation("财务确认收票")
    @ApiImplicitParams({
           // @ApiImplicitParam(name = "state",value = "状态 2=运输中，99=已收到票",required = true,paramType = "query",dataType = "int")
    })
    public BasicRet checkSellerBill(@RequestParam(required = true) long id,Model model, HttpServletRequest request){

        Admin admin = (Admin) model.asMap().get(AppConstant.ADMIN_SESSION_NAME);


        SellerBill sellerBill = sellerBillServices.getById(id);
        if(sellerBill == null){
            return new BasicRet(BasicRet.ERR,"记录不存在");
        }

        if(sellerBill.getState() != Quantity.STATE_2){
            return  new BasicRet(BasicRet.ERR,"卖家还未邮寄发票，不可确认");
        }


        SellerBill updateSellerBill = new SellerBill();
        updateSellerBill.setId(sellerBill.getId());
        updateSellerBill.setState((short)99);
        sellerBillServices.update(updateSellerBill);

        memberLogOperator.saveMemberLog(null,admin,admin.getUsername()+"确认发票记录："+sellerBill.getId(),request,memberOperateLogService);

        //当为违约金发票的时候
        if(sellerBill.getType() == Quantity.STATE_2){
            //冻结余额减去违约金 贷款余额加上违约金
            Member member = memberService.getMemberById(sellerBill.getSellerid());
            Member updateMember = new Member();
            updateMember.setId(member.getId());
            updateMember.setSellerfreezebanlance(member.getSellerfreezebanlance().subtract(sellerBill.getTotalbreak()));
            updateMember.setGoodsbanlance(member.getGoodsbanlance().add(sellerBill.getTotalbreak()));
            memberService.updateByPrimaryKeySelective(updateMember);
        }

        return new BasicRet(BasicRet.SUCCESS,"操作成功");
    }



}
