package project.jinshang.scheduled;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import project.jinshang.common.constant.DistributeTaskConst;
import project.jinshang.common.constant.Quantity;
import project.jinshang.common.constant.TradeConstant;
import project.jinshang.common.utils.CommonUtils;
import project.jinshang.common.utils.DateUtils;
import project.jinshang.common.utils.GenerateNo;
import project.jinshang.mod_cash.service.BuyerCapitalService;
import project.jinshang.mod_credit.bean.BillCreate;
import project.jinshang.mod_credit.service.BillCreateService;
import project.jinshang.mod_member.bean.Member;
import project.jinshang.mod_member.bean.MemberExample;
import project.jinshang.mod_member.service.MemberService;
import project.jinshang.scheduled.Bean.DistributeTaskLog;
import project.jinshang.scheduled.mapper.BillcreateTaskMapper;
import project.jinshang.scheduled.mapper.DistributeTaskMapper;
import project.jinshang.scheduled.service.DistributeTaskLogService;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 授信账单
 */



@Component
@Transactional(rollbackFor = Exception.class)
public class BillcreateTask {

    @Autowired
    private  MemberService memberService;

    @Autowired
    private BillCreateService billCreateService;


    @Autowired
    private BillcreateTaskMapper billcreateTaskMapper;


    @Autowired
    private BuyerCapitalService buyerCapitalService;

    @Autowired
    private DistributeTaskMapper distributeTaskMapper;
    @Autowired
    private DistributeTaskLogService distributeTaskLogService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 生成授信账单
     */

//    @Scheduled(cron="*/10 * * * * *")

    //@Scheduled(cron="0 51 17 * * ?") //每天3点执行
    @Scheduled(cron="0 0 3 28 * ?")  //每月28号3:00生成账单
    public  void  genetateCreditBill(){

        if(distributeTaskMapper.start(DistributeTaskConst.BILL_GENETATE_CREDITBILL) != 1){
            return;
        }

        DistributeTaskLog taskLog  = new DistributeTaskLog();
        taskLog.setHostip(CommonUtils.getServerIP());
        taskLog.setHostname(CommonUtils.getServerHost());
        taskLog.setTaskcode(DistributeTaskConst.BILL_GENETATE_CREDITBILL);

        try {
            String todays =  DateUtils.format(new Date(),"yyyy-MM-dd");

            //查询出所有授信用户
            MemberExample memberExample =  new MemberExample();
            MemberExample.Criteria criteria = memberExample.createCriteria();
            criteria.andCreditlimitGreaterThan(new BigDecimal(0));

            List<Member> memberLists = memberService.selectByExample(memberExample);

        /*
        //上线前打开
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH,28);
        Date buyerinspectiontimeEndDate = calendar.getTime();
        calendar.add(Calendar.MONTH,-1);
        Date buyerinspectiontimeStartDate =  calendar.getTime();

           //测试
//        Calendar calendar = Calendar.getInstance();
//        //calendar.add(Calendar.DAY_OF_MONTH,-1);
//        Date buyerinspectiontimeEndDate = calendar.getTime();
//        Date buyerinspectiontimeStartDate =  calendar.getTime();


        String buyerinspectiontimeStart = DateUtils.format(buyerinspectiontimeStartDate,"yyyy-MM-dd")+" 23:59:59";
        String buyerinspectiontimeEnd =  DateUtils.format(buyerinspectiontimeEndDate,"yyyy-MM-dd")+" 00:00:00";
*/


            //上线前打开
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DAY_OF_MONTH,27);
            Date buyerinspectiontimeEndDate = calendar.getTime();
            calendar.add(Calendar.MONTH,-1);
            calendar.add(Calendar.DATE,1);
            Date buyerinspectiontimeStartDate =  calendar.getTime();

            String buyerinspectiontimeStart = DateUtils.format(buyerinspectiontimeStartDate,"yyyy-MM-dd")+" 00:00:00";
            String buyerinspectiontimeEnd =  DateUtils.format(buyerinspectiontimeEndDate,"yyyy-MM-dd")+" 23:59:59.999";


            for(Member member : memberLists){
                BillCreate billCreate = new BillCreate();
                billCreate.setBuyerid(member.getId());


                Calendar cal = Calendar.getInstance();
    //            测试
                //cal.add(Calendar.DATE,1);

    //            正式使用
                cal.add(Calendar.MONTH,1);
                cal.set(Calendar.DAY_OF_MONTH,15);

                billCreate.setLastrepaymentday(DateUtils.StrToDate(DateUtils.format(cal.getTime(),"yyyy-MM-dd")+" 23:59:59","yyyy-MM-dd HH:mm:ss")); //统一每月15号还款
                billCreate.setBillno(GenerateNo.getSettlementNo());
                billCreate.setSettlementtime(DateUtils.format(buyerinspectiontimeStartDate,"yyyy.MM.dd")+"-"+DateUtils.format(buyerinspectiontimeEndDate,"yyyy.MM.dd"));

                //查询出该账期内所有使用授信交易成功的订单
                BigDecimal payMoney = billcreateTaskMapper.getPayTotalMoney(member.getId(),DateUtils.StrToDate(buyerinspectiontimeStart),DateUtils.StrToDate(buyerinspectiontimeEnd));
                payMoney =  payMoney == null ? new BigDecimal(0) : payMoney;


                //查询出该账期内所有的违约金
                //BigDecimal penaltyMoney =billcreateTaskMapper.getPenaltyTotalMoney(member.getId(),DateUtils.StrToDate(buyerinspectiontimeStart),DateUtils.StrToDate(buyerinspectiontimeEnd));
                //penaltyMoney =  penaltyMoney == null ? new BigDecimal(0) : penaltyMoney;
                BigDecimal penaltyMoney = new BigDecimal(0);

                //查询出该账期内退款的订单
                BigDecimal drawbackMoney = billcreateTaskMapper.getDrawbackMoney(member.getId(),DateUtils.StrToDate(buyerinspectiontimeStart),DateUtils.StrToDate(buyerinspectiontimeEnd));
                drawbackMoney = drawbackMoney == null ? new BigDecimal(0) : drawbackMoney;

                //已还款的订单总额（提前还款）
                BigDecimal paymentTotalMoney = billcreateTaskMapper.getepaymentTotalMoney(member.getId(),DateUtils.StrToDate(buyerinspectiontimeStart),DateUtils.StrToDate(buyerinspectiontimeEnd));
                paymentTotalMoney =  paymentTotalMoney == null ? new BigDecimal(0) : paymentTotalMoney;

                //该账期内需要还款的金额
                billCreate.setMoney(payMoney.add(penaltyMoney).subtract(drawbackMoney));
                billCreate.setAmountpaid(paymentTotalMoney);


                List<Map<String,Object>> ids  = billcreateTaskMapper.getCreditCapitalIds(member.getId(),DateUtils.StrToDate(buyerinspectiontimeStart),DateUtils.StrToDate(buyerinspectiontimeEnd));
                StringBuilder id_sb =  new StringBuilder();
                for(Map<String,Object> map : ids){
                    id_sb.append(map.get("id").toString()).append(",");
                }

                billCreate.setState((short) ((billCreate.getMoney().compareTo(billCreate.getAmountpaid())== 0)  ? 1 : 0));
                billCreate.setCreatetime(new Date());
                billCreate.setRecords(ids.size());

                billCreateService.insertSelective(billCreate);


                long bill_id =  billCreate.getId();

                if(ids.size()>0){
                    buyerCapitalService.updateBillcreateid(bill_id,id_sb.toString().substring(0,id_sb.length()-1));
                }


                //授信账单生成完毕
            }
        } catch (Exception e) {
            logger.error("生成授信账单",e);
            taskLog.setState(Quantity.STATE_2);
            taskLog.setError(e.toString());
        } finally {
            distributeTaskMapper.end(DistributeTaskConst.BILL_GENETATE_CREDITBILL);
            taskLog.setState(Quantity.STATE_1);
        }

        distributeTaskLogService.insert(taskLog);
    }

    //每天1点10分执行
    @Scheduled(cron="10 1 * * * ?")  //授信账单状态
    public  void setBillCreateState(){

        if(distributeTaskMapper.start(DistributeTaskConst.BILL_CHECK_CREDITBILL_OVERDUE) != 1){
            return;
        }

        DistributeTaskLog taskLog  = new DistributeTaskLog();
        taskLog.setHostip(CommonUtils.getServerIP());
        taskLog.setHostname(CommonUtils.getServerHost());
        taskLog.setTaskcode(DistributeTaskConst.BILL_CHECK_CREDITBILL_OVERDUE);

        try {
            //将过期未还款的设置为逾期状态
            billCreateService.setExpire();

            //将违约的授信账单设置为违约账单，并且填充违约金额
            billCreateService.fillIllegalData();
        } catch (Exception e) {
            logger.error("检测授信账单逾期",e);
            taskLog.setState(Quantity.STATE_2);
            taskLog.setError(e.toString());
        } finally {
            distributeTaskMapper.end(DistributeTaskConst.BILL_CHECK_CREDITBILL_OVERDUE);
            taskLog.setState(Quantity.STATE_1);
        }

        distributeTaskLogService.insert(taskLog);
    }





//    public static void main(String[] args) {
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(Calendar.DAY_OF_MONTH,27);
//        Date buyerinspectiontimeEndDate = calendar.getTime();
//        calendar.add(Calendar.MONTH,-1);
//        Date buyerinspectiontimeStartDate =  calendar.getTime();
//
//        String buyerinspectiontimeStart = DateUtils.format(buyerinspectiontimeStartDate,"yyyy-MM-dd")+" 00:00:00";
//        String buyerinspectiontimeEnd =  DateUtils.format(buyerinspectiontimeEndDate,"yyyy-MM-dd")+" 23:59:59";
//
//        System.out.println(buyerinspectiontimeStart);
//        System.out.println(buyerinspectiontimeEnd);
//    }


}
