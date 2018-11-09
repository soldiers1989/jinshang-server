package project.jinshang.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import project.jinshang.mod_cash.BuyerCapitalMapper;
import project.jinshang.mod_cash.bean.dto.BuyerCapitalViewDto;

import java.util.List;

/**
 * Test3
 *
 * @author xiazy
 * @create 2018-09-27 13:54
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class Test3 {
    @Autowired
    private BuyerCapitalMapper buyerCapitalMapper;

    @Test
    public void setBuyerCapitalMapper() {

        List<BuyerCapitalViewDto> list=buyerCapitalMapper.queryBuyerCapitalByOrderNo("D201804021758026584");
        System.out.println(list.get(0).getInvoiceheadup());
    }
}
