package project.jinshang.test;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import project.jinshang.common.constant.Quantity;
import project.jinshang.mod_admin.mod_statement.bean.dto.BuyerStatementDto;
import project.jinshang.mod_admin.mod_statement.service.StatementService;
import project.jinshang.mod_cash.bean.dto.BuyerCapitalAccountDto;
import project.jinshang.mod_cash.bean.dto.BuyerCapitalAccountQueryDto;
import project.jinshang.mod_cash.bean.dto.BuyerCapitalViewDto;
import project.jinshang.mod_cash.service.BuyerCapitalService;
import project.jinshang.mod_member.bean.Member;
import project.jinshang.mod_member.service.MemberService;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Test4
 *
 * @author xiazy
 * @create 2018-10-10 11:32
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class Test4 {

    @Autowired
    private MemberService memberService;
    @Autowired
    private BuyerCapitalService buyerCapitalService;
    @Autowired
    private StatementService statementService;

    ExecutorService pool = Executors.newFixedThreadPool(30);

    @Test
    public void test() throws InterruptedException {
        //查出所有用户
        int pageCount = 10;
        int pageSize = 100;
        int pageNo = 1;

        while (pageNo<=pageCount) {
            PageHelper.startPage(pageNo, pageSize);
            List<Member> memberList=memberService.getAllMember();
            PageInfo pageInfo = new PageInfo(memberList);
            pageCount = pageInfo.getPages();
            pageNo++;
            pool.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        memberList.forEach(member -> {
                            BuyerCapitalAccountQueryDto dto=new BuyerCapitalAccountQueryDto();
                            BuyerCapitalAccountDto buyerCapitalAccountDto=new BuyerCapitalAccountDto();
                            dto.setMemberid(member.getId());
                            List<BuyerCapitalAccountDto> buyerCapitalAccountDtoList=buyerCapitalService.listForAccount(dto);
                            if (buyerCapitalAccountDtoList!=null&&buyerCapitalAccountDtoList.size()>0){
                                buyerCapitalAccountDto=buyerCapitalAccountDtoList.get(buyerCapitalAccountDtoList.size()-1);
                            }else{
                                buyerCapitalAccountDto.setReceivableaccount(Quantity.BIG_DECIMAL_0);
                                buyerCapitalAccountDto.setInvoicebalance(Quantity.BIG_DECIMAL_0);
                            }
                            BuyerStatementDto buyerStatementDto=new BuyerStatementDto();
                            buyerStatementDto.setMemberid(member.getId());
                            buyerStatementDto.setCreatetime(new Date());
                            buyerStatementDto.setContractno("000000000000000000000");
                            buyerStatementDto.setType((short) 9);
    //                buyerStatementDto.setDeliveryamount(fahuoamount);
    //                buyerStatementDto.setReceiptamount(shoukuanamount);
                            buyerStatementDto.setReceivableamount(buyerCapitalAccountDto.getReceivableaccount());
    //                buyerStatementDto.setInvoiceamount(kaipiaoamount);
                            buyerStatementDto.setInvoicebalance(buyerCapitalAccountDto.getInvoicebalance());
                            buyerStatementDto.setPaytype((short) 6);
                            buyerStatementDto.setPayno(null);
                            buyerStatementDto.setRemark("系统首次统计用户上期结余");
                            statementService.insertStatementForTest(buyerStatementDto);

                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }


        pool.shutdown();
        pool.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);






    }
}
