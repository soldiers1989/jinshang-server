package project.jinshang.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import project.jinshang.common.utils.DateUtils;
import project.jinshang.mod_credit.BillCreateMapper;
import project.jinshang.mod_credit.bean.BillCreate;
import project.jinshang.mod_credit.bean.BillCreateExample;
import project.jinshang.mod_member.bean.Member;
import project.jinshang.mod_member.service.MemberService;
import project.jinshang.scheduled.mapper.BillcreateTaskMapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 修复授信账单错误
 * create : wyh
 * date : 2018/8/31
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BillCreateTest {

    @Autowired
    private BillCreateMapper billCreateMapper;

    @Autowired
    private BillcreateTaskMapper billcreateTaskMapper;

    @Autowired
    private MemberService memberService;

    @Test
    public void repair(){




        List<String[]> repairList = new ArrayList<>();
        repairList.add(new String[]{"2018.03.27-2018.04.27","2018-03-28 00:00:00","2018-04-27 23:59:59.999","2018.03.28-2018.04.27"});
        repairList.add(new String[]{"2018.04.27-2018.05.27","2018-04-28 00:00:00","2018-05-27 23:59:59.999","2018.04.28-2018.05.27"});
        repairList.add(new String[]{"2018.05.27-2018.06.27","2018-05-28 00:00:00","2018-06-27 23:59:59.999","2018.05.28-2018.06.27"});
        repairList.add(new String[]{"2018.06.28-2018.07.27","2018-06-28 00:00:00","2018-07-27 23:59:59.999","2018.06.28-2018.07.27"});
        repairList.add(new String[]{"2018.07.28-2018.08.27","2018-07-28 00:00:00","2018-08-27 23:59:59.999","2018.07.28-2018.08.27"});


       for(String[] array : repairList) {

           String settlement = array[0];
           String startDate = array[1];
           String endDate = array[2];
           String repairSettment = array[3];

           BillCreateExample billCreateExample = new BillCreateExample();
           BillCreateExample.Criteria criteria = billCreateExample.createCriteria();
           criteria.andSettlementtimeEqualTo(settlement);


           String buyerinspectiontimeStart = startDate;
           String buyerinspectiontimeEnd = endDate;

           List<BillCreate> billCreateList = billCreateMapper.selectByExample(billCreateExample);
           System.out.println(billCreateList.size());
           for (BillCreate billCreate : billCreateList) {

               Member member = memberService.getMemberById(billCreate.getBuyerid());

               //查询出该账期内所有使用授信交易成功的订单
               BigDecimal payMoney = billcreateTaskMapper.getPayTotalMoney(member.getId(), DateUtils.StrToDate(buyerinspectiontimeStart), DateUtils.StrToDate(buyerinspectiontimeEnd));
               payMoney = payMoney == null ? new BigDecimal(0) : payMoney;


               //查询出该账期内所有的违约金
               //BigDecimal penaltyMoney =billcreateTaskMapper.getPenaltyTotalMoney(member.getId(),DateUtils.StrToDate(buyerinspectiontimeStart),DateUtils.StrToDate(buyerinspectiontimeEnd));
               //penaltyMoney =  penaltyMoney == null ? new BigDecimal(0) : penaltyMoney;
               BigDecimal penaltyMoney = new BigDecimal(0);

               //查询出该账期内退款的订单
               BigDecimal drawbackMoney = billcreateTaskMapper.getDrawbackMoney(member.getId(), DateUtils.StrToDate(buyerinspectiontimeStart), DateUtils.StrToDate(buyerinspectiontimeEnd));
               drawbackMoney = drawbackMoney == null ? new BigDecimal(0) : drawbackMoney;

               //已还款的订单总额（提前还款）
               BigDecimal paymentTotalMoney = billcreateTaskMapper.getepaymentTotalMoney(member.getId(), DateUtils.StrToDate(buyerinspectiontimeStart), DateUtils.StrToDate(buyerinspectiontimeEnd));
               paymentTotalMoney = paymentTotalMoney == null ? new BigDecimal(0) : paymentTotalMoney;

               //该账期内需要还款的金额
               BigDecimal money = payMoney.add(penaltyMoney).subtract(drawbackMoney);
               System.out.println("该账期要缴纳的金额：" + money + "----" + billCreate.getMoney());

               if (money.compareTo(billCreate.getMoney()) == 0) {
                   System.out.println("账单金额没问题");

                   BillCreate up = new BillCreate();
                   up.setId(billCreate.getId());
                   up.setSettlementtime(repairSettment);
                   billCreateMapper.updateByPrimaryKeySelective(up);

               } else {
                   //System.out.println("账单金额错误，请修正。。。。");
                   System.out.println("该账期要缴纳的金额：" + money + "----" + billCreate.getMoney());
                   System.out.println(billCreate.getBillno());
                   System.out.println("aaaa");

               }
           }
       }
    }

}
