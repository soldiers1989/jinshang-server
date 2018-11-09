package project.jinshang.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import project.jinshang.common.exception.CashException;
import project.jinshang.common.exception.MyException;
import project.jinshang.mod_pay.service.TradeService;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PayTest {

    @Autowired
    private TradeService tradeService;

    @Test
    public void test01() throws CashException, MyException {
        //tradeService.notify("order-1000000651232299","bank","");
        tradeService.test("D201811090935208613","order-1000000003611132","","0.07","bank","2018-11-09 09:35:00");
        //tradeService.test("D201806061738552426","order-1000000884208488","4200000129201806065012277178","66","wxpay","2018-06-06 17:39:17");
//        tradeService.test("D201808091708408274","","","850.33","wxpay","2018-08-09 17:09:00");
//        tradeService.test("D201808091635345759","","","212.76","wxpay","2018-08-09 17:09:00");

//        tradeService.testForCharge("CZ201810131414459807","","","500","alipay","2018-10-13 14:40:00");
    }
}
