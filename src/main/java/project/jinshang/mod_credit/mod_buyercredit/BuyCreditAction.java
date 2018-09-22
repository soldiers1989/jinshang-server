package project.jinshang.mod_credit.mod_buyercredit;

import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import mizuki.project.core.restserver.config.BasicRet;
import mizuki.project.core.restserver.util.JsonUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.jinshang.common.bean.MemberLogOperator;
import project.jinshang.common.bean.PageRet;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.common.constant.Quantity;
import project.jinshang.common.utils.DateUtils;
import project.jinshang.common.utils.GenerateNo;
import project.jinshang.mod_cash.bean.BuyerCapital;
import project.jinshang.mod_cash.bean.BuyerCapitalExample;
import project.jinshang.mod_cash.service.BuyerCapitalService;
import project.jinshang.mod_common.bean.BasicExtRet;
import project.jinshang.mod_credit.bean.BillCreate;
import project.jinshang.mod_credit.bean.BillCreateQueryDto;
import project.jinshang.mod_credit.bean.CreditApplyRecord;
import project.jinshang.mod_credit.bean.CreditApplyRecordExample;
import project.jinshang.mod_credit.service.BillCreateService;
import project.jinshang.mod_credit.service.BuyerCreditService;
import project.jinshang.mod_member.bean.Member;
import project.jinshang.mod_member.service.MemberService;
import project.jinshang.mod_product.service.MemberOperateLogService;
import project.jinshang.mod_product.service.OrdersService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

/**
 * 买家授信管理
 * Created by Administrator on 2017/11/22.
 */
@RestController
@RequestMapping("/rest/buyer/orders")
@SessionAttributes({AppConstant.MEMBER_SESSION_NAME})
@Api(tags = "买家-授信模块", description = "授信相关")
@Transactional(rollbackFor = Exception.class)
public class BuyCreditAction {

    @Autowired
    BuyerCreditService buyerCreditService;

    @Autowired
    private BillCreateService billCreateService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private OrdersService ordersService;


    @Autowired
    private BuyerCapitalService buyerCapitalService;

    @Autowired
    private MemberOperateLogService memberOperateLogService;

    @Autowired
    private ApplicationContext context;

    MemberLogOperator memberLogOperator = new MemberLogOperator();

    @RequestMapping(value = "/insertCreditRecord", method = RequestMethod.POST)
    @ApiOperation(value = "买家申请授信")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "limitmoney", value = "申请额度", required = true, paramType = "query", dataType = "double"),
            @ApiImplicitParam(name = "company", value = "申请单位", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "address", value = "单位地址", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "contract", value = "联系人", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "phone", value = "联系电话", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "description", value = "备注", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "license", value = "营业执照", required = true, paramType = "query", dataType = "string"),
    })
    public BasicRet insertCreditRecord(Model model, CreditApplyRecord creditApplyRecord, HttpServletRequest request) {
        BuyerCreditRet basicRet = new BuyerCreditRet();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

        member = memberService.getMemberById(member.getId());

        //判断已用额度
        if (member.getUsedlimit() != null && member.getUsedlimit().compareTo(new BigDecimal(0)) > 0) {
            basicRet.setMessage("之前授信账单未还完，不可申请");
            basicRet.setResult(BasicRet.ERR);
            return basicRet;
        }

        //判断是否已经有在审核中的授信，如果有的话不可申请
        CreditApplyRecordExample creditApplyRecordExample = new CreditApplyRecordExample();
        CreditApplyRecordExample.Criteria criteria = creditApplyRecordExample.createCriteria();
        criteria.andMemberidEqualTo(member.getId()).andStateIn(Arrays.asList(new Short[]{0, 1, 2, 3}));
        int count = buyerCreditService.countByExample(creditApplyRecordExample);
        if (count > 0) {
            basicRet.setMessage("存在正在审核中的授信记录，不可重复申请");
            basicRet.setResult(BasicRet.ERR);
            return basicRet;
        }

        Member updateMember = new Member();
        updateMember.setId(member.getId());
        updateMember.setCreditlimit(new BigDecimal(0));
        updateMember.setAvailablelimit(new BigDecimal(0));
        memberService.updateByPrimaryKeySelective(updateMember);

        creditApplyRecord.setMemberid(member.getId());
        creditApplyRecord.setCreatetime(new Date());
        buyerCreditService.insertCreditRecord(creditApplyRecord);
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("申请成功");
        basicRet.data.creditApplyRecord = creditApplyRecord;


        memberLogOperator.saveMemberLog(member, null, "申请授信", "/rest/buyer/orders/insertCreditRecord", request, memberOperateLogService);
        return basicRet;
    }

    private class BuyerCreditRet extends BasicRet {
        private class Data {
            private CreditApplyRecord creditApplyRecord;

            private PageInfo pageInfo;

            private Member member;

            private List<BillCreate> list;

            private List<BuyerCapital> list2;

            private BigDecimal sumMoney;

            public BigDecimal getSumMoney() {
                return sumMoney;
            }

            public void setSumMoney(BigDecimal sumMoney) {
                this.sumMoney = sumMoney;
            }

            public CreditApplyRecord getCreditApplyRecord() {
                return creditApplyRecord;
            }

            public void setCreditApplyRecord(CreditApplyRecord creditApplyRecord) {
                this.creditApplyRecord = creditApplyRecord;
            }

            public PageInfo getPageInfo() {
                return pageInfo;
            }

            public void setPageInfo(PageInfo pageInfo) {
                this.pageInfo = pageInfo;
            }

            public Member getMember() {
                return member;
            }

            public void setMember(Member member) {
                this.member = member;
            }

            public List<BillCreate> getList() {
                return list;
            }

            public void setList(List<BillCreate> list) {
                this.list = list;
            }

            public List<BuyerCapital> getList2() {
                return list2;
            }

            public void setList2(List<BuyerCapital> list2) {
                this.list2 = list2;
            }

        }

        private BuyCreditAction.BuyerCreditRet.Data data = new BuyCreditAction.BuyerCreditRet.Data();

        public Data getData() {
            return data;
        }

        public void setData(Data data) {
            this.data = data;
        }
    }

    @RequestMapping(value = "/getCreditRecordList", method = RequestMethod.POST)
    @ApiOperation(value = "获取买家申请授信记录列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "申请编号", required = false, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "contract", value = "联系人", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "phone", value = "联系电话", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "state", value = "状态", required = false, paramType = "query", dataType = "int", defaultValue = "-1"),
    })
    public BasicRet getCreditRecordList(Model model, CreditApplyRecord creditApplyRecord, @RequestParam(required = true, defaultValue = "1") int pageNo,
                                        @RequestParam(required = true, defaultValue = "10") int pageSize) {
        BuyerCreditRet buyerCreditRet = new BuyerCreditRet();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        PageInfo pageInfo = buyerCreditService.getCreditRecordList(member.getId(), creditApplyRecord, pageNo, pageSize);
        buyerCreditRet.data.pageInfo = pageInfo;
        buyerCreditRet.setMessage("返回成功");
        buyerCreditRet.setResult(BasicRet.SUCCESS);
        return buyerCreditRet;
    }

    @RequestMapping(value = "/getSingleCreditRecord", method = RequestMethod.POST)
    @ApiOperation(value = "获取单个授信记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "申请编号", required = false, paramType = "query", dataType = "long"),
    })
    public BasicRet getSingleCreditRecord(Long id) {
        BuyerCreditRet buyerCreditRet = new BuyerCreditRet();
        CreditApplyRecord creditApplyRecord = buyerCreditService.getCreditApplyRecord(id);
        buyerCreditRet.setResult(BasicRet.SUCCESS);
        buyerCreditRet.setMessage("获取成功");
        buyerCreditRet.data.creditApplyRecord = creditApplyRecord;
        return buyerCreditRet;
    }


    @RequestMapping(value = "/updateCreditRecord", method = RequestMethod.POST)
    @ApiOperation(value = "买家撤消授信")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "记录id", required = true, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "state", value = "撤消状态传6", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "cancelreason", value = "撤消原因", required = false, paramType = "query", dataType = "string"),
    })
    public BasicRet updateCreditRecord(Model model, CreditApplyRecord creditApplyRecord, HttpServletRequest request) {
        BasicRet basicRet = new BasicRet();
        buyerCreditService.updateCreditRecord(creditApplyRecord);
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("修改成功");
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        memberLogOperator.saveMemberLog(member, null, "撤消授信申请记录，记录id：" + creditApplyRecord.getId(), "/rest/buyer/orders/updateCreditRecord", request, memberOperateLogService);
        return basicRet;
    }

    @RequestMapping(value = "/getMemberCredit", method = RequestMethod.POST)
    @ApiOperation(value = "获取买家授信信息")
    public BuyerCreditRet getMemberCredit(Model model) {
        BuyerCreditRet buyerCreditRet = new BuyerCreditRet();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        member = memberService.getMemberById(member.getId());
        buyerCreditRet.data.member = member;
        buyerCreditRet.setMessage("返回成功");
        buyerCreditRet.setResult(BasicRet.SUCCESS);
        return buyerCreditRet;
    }


    @RequestMapping(value = "/getBillCreateList", method = RequestMethod.POST)
    @ApiOperation(value = "获取买家历史账单列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "billno", value = "结算单号", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "state", value = "状态0=未缴清1=已缴清2=已逾期", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "settlementtime", value = "结算期", required = false, paramType = "query", dataType = "string"),
    })
    public PageRet getBillCreateList(@RequestParam(required = true, defaultValue = "1") int pageNo,
                                     @RequestParam(required = true, defaultValue = "10") int pageSize,
                                     BillCreateQueryDto queryDto,
                                     Model model) {
        PageRet pageRet = new PageRet();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        queryDto.setBuyerid(member.getId());
        PageInfo pageInfo = buyerCreditService.getBillCreateList(queryDto, pageNo, pageSize);
        pageRet.setMessage("返回成功");
        pageRet.setResult(BasicRet.SUCCESS);
        pageRet.data.setPageInfo(pageInfo);
        return pageRet;
    }


    @PostMapping("/getBillCreateTimeList")
    @ApiOperation("结算期时间列表")
    public BillCreatTimeListRet getBillCreateTimeList(Model model) {
        BillCreatTimeListRet billCreatTimeListRet = new BillCreatTimeListRet();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

        List<String> list = billCreateService.getBillCreateTimeList(member.getId(), 12);
        billCreatTimeListRet.CreatTimeList = list;

        billCreatTimeListRet.setResult(BasicRet.SUCCESS);

        return billCreatTimeListRet;
    }


    private class BillCreatTimeListRet extends BasicRet {
        private List<String> CreatTimeList;

        public List<String> getList() {
            return CreatTimeList;
        }

        public void setList(List<String> list) {
            this.CreatTimeList = list;
        }
    }


    /*
    @RequestMapping(value = "/getBuyerCapitalList",method = RequestMethod.POST)
    @ApiOperation(value = "获取账单详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ordernos",value = "订单编号数组",required = true,paramType = "query"  ,dataType = "array"),
    })
    public BuyerCreditRet getBuyerCapitalList(String ordernos){
        BuyerCreditRet buyerCreditRet = new BuyerCreditRet();
        List<BuyerCapital> list = buyerCreditService.getBuyerCapitalList(ordernos);
        buyerCreditRet.setMessage("返回成功");
        buyerCreditRet.setResult(BasicRet.SUCCESS);
        buyerCreditRet.data.list2 = list;
        return buyerCreditRet;
    }




    @RequestMapping(value = "/getNotOutCreditSum",method = RequestMethod.POST)
    @ApiOperation(value = "获取未出账单总金额")
    public BuyerCreditRet getNotOutCreditSum(Model model){
        BuyerCreditRet buyerCreditRet = new BuyerCreditRet();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        BigDecimal sumMoney = buyerCreditService.getNotOutCreditSum(member.getId());
        buyerCreditRet.setMessage("获取成功");
        buyerCreditRet.setResult(BasicRet.SUCCESS);
        buyerCreditRet.data.sumMoney = sumMoney;
        return  buyerCreditRet;
    }
*/


    @RequestMapping(value = "/getNotOutCreditSum", method = RequestMethod.POST)
    @ApiOperation(value = "获取未出账单已用金额")
    //已用金额 = 消费总金额 - 退款总金额 - 已还款总金额
    public BuyerCreditRet getNotOutCreditSum(Model model) {
        BuyerCreditRet buyerCreditRet = new BuyerCreditRet();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

        member = memberService.getMemberById(member.getId());

        BigDecimal sumMoney = buyerCreditService.getNotOutCreditSum(member);
        buyerCreditRet.setMessage("获取成功");
        buyerCreditRet.setResult(BasicRet.SUCCESS);
        buyerCreditRet.data.sumMoney = sumMoney;
        return buyerCreditRet;
    }


    @RequestMapping(value = "/getNotOutBuyerCapitalList", method = RequestMethod.POST)
    @ApiOperation(value = "获取未出账单列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "页码", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "页数", required = true, paramType = "query", dataType = "int"),
    })
    public PageRet getNotOutBuyerCapitalList(Model model, int pageNo, int pageSize) {
        PageRet buyerCreditRet = new PageRet();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        PageInfo pageInfo = buyerCreditService.getNotOutBuyerCapitalList(member.getId(), pageNo, pageSize);
        buyerCreditRet.setMessage("返回成功");
        buyerCreditRet.setResult(BasicRet.SUCCESS);
        buyerCreditRet.data.setPageInfo(pageInfo);
        return buyerCreditRet;
    }


    @RequestMapping(value = "/advanceRepayMoney", method = RequestMethod.POST)
    @ApiOperation(value = "授信提前还款")
    public BasicRet advanceRepayMoney(Model model) {
        BasicRet basicRet = new BasicRet();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        member = memberService.getMemberById(member.getId());

        if (member.getCreditlimit() == null || member.getCreditlimit().compareTo(new BigDecimal(0)) == 0) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("用户未开通授信");
            return basicRet;
        }

        BigDecimal totalMoney = buyerCreditService.getNotOutCreditSum(member);
        if (member.getBalance().compareTo(totalMoney) == -1) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("买家账户余额不足");
            return basicRet;
        }

        //创建交易流水
        BuyerCapital buyerCapital = new BuyerCapital();
        //交易号
        buyerCapital.setTradeno(GenerateNo.getTransactionNo());
        //交易类型，授信提前还款
        buyerCapital.setCapitaltype(Quantity.STATE_11);
        //还款金额
        buyerCapital.setCapital(totalMoney);
        //支付方式
        buyerCapital.setPaytype(Quantity.STATE_3);
        //会员id
        buyerCapital.setMemberid(member.getId());

        buyerCreditService.saveBuyerCapital(buyerCapital);

        //修改member表中修改还款的金额
        memberService.creditRepayment(member.getId(), totalMoney);


        basicRet.setMessage("还款成功");
        basicRet.setResult(BasicRet.SUCCESS);
        return basicRet;
    }


    @RequestMapping(value = "/sendRepayMoney", method = RequestMethod.POST)
    @ApiOperation(value = "授信还款")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "账单id", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "type", value = "还款类型 0=全额还款,1=部分还款", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "money", value = "部分还款金额", required = true, paramType = "query", dataType = "int"),
    })
    public BasicRet sendRepayMoney(int id,@RequestParam(required = true,defaultValue = "0") short type,BigDecimal money, Model model) {
        BasicRet basicRet = new BasicRet();
        Member memberSession = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

        if(type != 0 && type != 1){
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("请选择还款类型");
            return basicRet;
        }

        if(type == 1 && (money == null || money.compareTo(Quantity.BIG_DECIMAL_0)==-1)){
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("还款金额不合法");
            return basicRet;
        }

        Member member = memberService.getMemberById(memberSession.getId());
        //查询账单信息
        BillCreate billCreate = billCreateService.getById(id);
        if (billCreate == null) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("账单未查询到");
            return basicRet;
        }

        if (!billCreate.getBuyerid().equals(member.getId())) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("该账单不属于你");
            return basicRet;
        }

        if (billCreate.getState() == Quantity.STATE_1) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("该账单已缴清，不可重复还款");
            return basicRet;
        }

        if(type == 0) { //全额还款
            money = billCreate.getMoney().subtract(billCreate.getAmountpaid());
        }

        BigDecimal debt = billCreate.getMoney().subtract(billCreate.getAmountpaid()); //欠款

        if(debt.compareTo(money) == -1){
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("该账单最多再还"+debt+"元就可以了");
            return basicRet;
        }

        if (member.getBalance().compareTo(money) == -1) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("买家账号余额不足");
            return basicRet;
        }

        //创建交易流水
        BuyerCapital buyerCapital = new BuyerCapital();
        //交易号
        buyerCapital.setTradeno(GenerateNo.getTransactionNo());
        //交易类型，授信还款
        buyerCapital.setCapitaltype(Quantity.STATE_5);
        //还款金额
        buyerCapital.setCapital(money);
        //支付方式
        buyerCapital.setPaytype(Quantity.STATE_3);
        //会员id
        buyerCapital.setMemberid(member.getId());

        //授信账单id
        buyerCapital.setBillcreateid(new Long(billCreate.getId()));

        buyerCreditService.saveBuyerCapital(buyerCapital);

        //修改member表中修改还款的金额
        memberService.creditRepayment(member.getId(), money);


        //修改账单为已缴清状态
        BillCreate updateBillCreate = new BillCreate();
        updateBillCreate.setId(id);

        if(debt.compareTo(money) == 0) {
            updateBillCreate.setState(Quantity.STATE_1);  //欠款与还款金额一致，设置为已缴清状态
        }

        updateBillCreate.setAmountpaid(billCreate.getAmountpaid().add(money));

        //如果账单为逾期未缴状态，设置逾期未缴纳天数
        if (billCreate.getIllegal()) {
            updateBillCreate.setIllegaldays(DateUtils.diffDays(new Date(), billCreate.getLastrepaymentday()));
        }

        billCreateService.updateByPrimaryKeySelective(updateBillCreate);


        //将订单中授信还款状态更改为已还款
        //ordersService.updateOrdersIsbackcredit(member.getId(),billCreate.getOrdersid(),Quantity.STATE_1);


        //更新session信息
        memberSession.setBalance(member.getBalance().subtract(money));
        memberSession.setUsedlimit(member.getUsedlimit().subtract(money));
        memberSession.setCreditlimit(member.getCreditlimit());
        memberSession.setAvailablelimit(member.getAvailablelimit().add(money));

        model.addAttribute(AppConstant.MEMBER_SESSION_NAME, memberSession);


        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("还款成功");
        return basicRet;
    }


    @PostMapping("/getLastBillCreate")
    @ApiOperation("获取上月账单详情")
    public LastBillCreateRet getLastBillCreate(Model model) {
        LastBillCreateRet lastBillCreateRet = new LastBillCreateRet();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        BillCreate billCreate = billCreateService.getLast(member.getId());

        List<BuyerCapital> buyerCapitalList = null;
        if (billCreate != null) {
            buyerCapitalList = buyerCapitalService.listByBillcreateid(billCreate.getId());
        }

        lastBillCreateRet.billCreate = billCreate;
        lastBillCreateRet.buyerCapitalList = buyerCapitalList;
        lastBillCreateRet.setResult(BasicRet.SUCCESS);
        return lastBillCreateRet;
    }


    @PostMapping("/getBillCapital")
    @ApiOperation("获取账单明细")
    public BillCapitalRet getBillCapital(Long billcreateid, Model model) {

        BillCapitalRet billCapitalRet = new BillCapitalRet();

        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

        BillCreate billCreate = billCreateService.getById(billcreateid.intValue());
        if (billCreate == null || !billCreate.getBuyerid().equals(member.getId())) {
            billCapitalRet.setResult(BasicRet.ERR);
            billCapitalRet.setMessage("账单不存在");
            return billCapitalRet;
        }

        BuyerCapitalExample example = new BuyerCapitalExample();
        example.setOrderByClause("id desc");
        BuyerCapitalExample.Criteria criteria = example.createCriteria();
        criteria.andBillcreateidEqualTo(billcreateid).andMemberidEqualTo(member.getId());

        billCapitalRet.data.orderList = buyerCapitalService.selectByExample(example);
        billCapitalRet.data.billCreate = billCreate;

        billCapitalRet.setResult(BasicRet.SUCCESS);
        return billCapitalRet;
    }


    private class BillCapitalRet extends BasicRet {
//        private  List<BuyerCapital> data;

        private class BillCapitalData {
            private List<BuyerCapital> orderList;
            private BillCreate billCreate;

            public List<BuyerCapital> getOrderList() {
                return orderList;
            }

            public void setOrderList(List<BuyerCapital> orderList) {
                this.orderList = orderList;
            }

            public BillCreate getBillCreate() {
                return billCreate;
            }

            public void setBillCreate(BillCreate billCreate) {
                this.billCreate = billCreate;
            }
        }

        private BillCapitalData data = new BillCapitalData();

        public BillCapitalData getData() {
            return data;
        }

        public void setData(BillCapitalData data) {
            this.data = data;
        }
    }


    private class LastBillCreateRet extends BasicRet {
        private BillCreate billCreate;
        private List<BuyerCapital> buyerCapitalList;

        public BillCreate getBillCreate() {
            return billCreate;
        }

        public void setBillCreate(BillCreate billCreate) {
            this.billCreate = billCreate;
        }

        public List<BuyerCapital> getBuyerCapitalList() {
            return buyerCapitalList;
        }

        public void setBuyerCapitalList(List<BuyerCapital> buyerCapitalList) {
            this.buyerCapitalList = buyerCapitalList;
        }
    }

    @PostMapping("/app/credit/detail")
    @ApiOperation("app获取授信状态")
    public BasicExtRet getAppCreditDetail(Model model) {
        BasicExtRet basicExtRet = new BasicExtRet();
        basicExtRet.setResult(BasicRet.SUCCESS);
        basicExtRet.setMessage("成功");
        Map<String, String> map = new HashMap<>();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        member = memberService.getMemberById(member.getId());
        map.put("creditState", member.getCreditstate() + "");
        if (member.getCreditstate() != 1) {
            basicExtRet.setData(map);
            return basicExtRet;
        }
        if (member.getAvailablelimit() == null) {
            map.put("availableLimit", "0.00");
        } else {
            map.put("availableLimit", member.getAvailablelimit().toPlainString());
        }
        if (member.getUsedlimit() == null) {
            map.put("usedLimit", "0.00");
        } else {
            map.put("usedLimit", member.getUsedlimit().toPlainString());
        }
        BillCreate billCreate = billCreateService.getLast(member.getId());
        BigDecimal sumMoney = buyerCreditService.getNotOutCreditSum(member);
        map.put("lastBill", new Gson().toJson(billCreate));
        if (sumMoney == null) {
            map.put("notOutBillAmount", "0.00");
        } else {
            map.put("notOutBillAmount", sumMoney.toPlainString());
        }
        basicExtRet.setData(map);
        return basicExtRet;
    }


        /**
         * 合同下载
         */
        @RequestMapping(value = "/downloadModelContract", method = RequestMethod.GET)
        @ApiOperation(value = "合同下载")
        public ResponseEntity<InputStreamResource> downloadModelContract(
                @RequestParam("fileName") String fileName
        ) throws IOException {
            try {
                Resource resource = context.getResource("classpath:download/" + fileName);
                HttpHeaders headers = new HttpHeaders();
                headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
                fileName = new String(fileName.getBytes("utf-8"), "iso-8859-1"); //解决中文乱码问题
                headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", fileName));
                headers.add("Pragma", "no-cache");
                headers.add("Expires", "0");
                return ResponseEntity
                        .ok()
                        .headers(headers)
                        .contentType(MediaType.APPLICATION_OCTET_STREAM)
                        .body(new InputStreamResource(resource.getInputStream()));
            } catch (Exception e) {
                return null;
            }
        }

}
