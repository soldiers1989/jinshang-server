package project.jinshang.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import project.jinshang.common.utils.JinShangSms;

import java.util.HashMap;
import java.util.Map;

/**
 * create : wyh
 * date : 2018/11/5
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestSms {

    @Autowired
    private JinShangSms jinShangSms;

    @Test
    public void test(){
        Map<String,String> map = new HashMap<>();
        map.put("code","123");
        jinShangSms.send("18663868251","SMS_150173096",map);
    }
}
