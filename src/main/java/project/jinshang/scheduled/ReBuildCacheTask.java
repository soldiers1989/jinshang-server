package project.jinshang.scheduled;

import com.hankcs.hanlp.dictionary.CustomDictionary;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import project.jinshang.mod_admin.mod_synonym.Synonym;
import project.jinshang.mod_admin.mod_synonym.SynonymMapper;
import project.jinshang.mod_system.mod_redis.service.RedisCacheService;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * create : wyh
 * date : 2018/2/27
 */

@Component
public class ReBuildCacheTask implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private RedisCacheService redisCacheService;

    @Autowired
    private SynonymMapper synonymMapper;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if(event.getApplicationContext().getParent() == null){//root application context 没有parent，他就是老大.
            //需要执行的逻辑代码，当spring容器初始化完成后就会执行该方法。
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    System.out.println("更新首页缓存");
                    redisCacheService.addIndexFloor();


                    System.out.println("更新展示类目缓存");
                    redisCacheService.addShowCate();
                }
            };

            Timer timer = new Timer();
            timer.schedule(task,0,600000);
        }
    }
}
