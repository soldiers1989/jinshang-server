package project.jinshang.test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import project.jinshang.common.exception.CashException;
import project.jinshang.scheduled.ProdShipTempTask;

/**
 * create : wyh
 * date : 2018/8/1
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class Test {

    @Autowired
    private ProdShipTempTask prodShipTempTask;

    @org.junit.Test
    public void  t1() throws CashException {
        prodShipTempTask.fixProdShipTemp();

    }

}
