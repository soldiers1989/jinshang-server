package project.jinshang.common.invoke;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 登出
 *
 * @author xiazy
 * @create 2018-05-17 9:07
 **/
public class LogoutHandler implements MessageHandler{
    private final  static Logger log= LoggerFactory.getLogger(LogoutHandler.class);
    @Override
    public void dealMessage() {
        System.out.println("登出成功!");
        log.info("this is log!");
    }
}
