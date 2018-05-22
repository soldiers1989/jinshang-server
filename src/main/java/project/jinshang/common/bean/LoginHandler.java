package project.jinshang.common.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import project.jinshang.common.invoke.MessageHandler;

public class LoginHandler implements MessageHandler{
    private static final Logger log = LoggerFactory.getLogger(LoginHandler.class);
    @Override
    public void dealMessage() {
//        log.info("登录成功！");
        System.out.println("登录成功！");
    }

}
