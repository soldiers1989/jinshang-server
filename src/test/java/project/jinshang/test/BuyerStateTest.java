package project.jinshang.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import project.jinshang.mod_admin.mod_statement.bean.BuyerStatement;
import project.jinshang.mod_admin.mod_statement.service.StatementService;

import java.math.BigDecimal;
import java.util.Date;

/**
 * BuyerStateTest
 *
 * @author xiazy
 * @create 2018-09-25 9:03
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class BuyerStateTest {
    @Autowired
    private StatementService statementService;
    @Test
    public void buyerStateTest(){
        BuyerStatement buyerStatement=new BuyerStatement();
        buyerStatement.setBillrecoid((long) 123456);
        buyerStatement.setPayno("123456789");
        buyerStatement.setRemark("来自xiazy的对账单"+"\r\n"+"this is a statement");
        buyerStatement.setMemberid((long) 107);
        buyerStatement.setContractno("D201809251111");
        buyerStatement.setType((short) 1);
        buyerStatement.setDeliveryamount(new BigDecimal("30"));
        buyerStatement.setReceiptamount(new BigDecimal("40"));
        buyerStatement.setInvoiceamount(new BigDecimal("10"));
        buyerStatement.setPaytype((short) 2);
        buyerStatement.setCreatetime(new Date());
        statementService.insertStatement(buyerStatement);
    }
}
