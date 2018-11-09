package project.jinshang.scheduled;

import com.hankcs.hanlp.dictionary.CustomDictionary;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import project.jinshang.common.constant.DistributeTaskConst;
import project.jinshang.common.constant.Quantity;
import project.jinshang.common.utils.CommonUtils;
import project.jinshang.mod_admin.mod_synonym.Synonym;
import project.jinshang.mod_admin.mod_synonym.SynonymMapper;
import project.jinshang.mod_system.mod_redis.service.RedisCacheService;
import project.jinshang.scheduled.Bean.DistributeTaskLog;
import project.jinshang.scheduled.mapper.DistributeTaskMapper;
import project.jinshang.scheduled.service.DistributeTaskLogService;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * create : wyh
 * date : 2018/2/27
 */

@Component
public class ReBuildCacheTask{ //implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private RedisCacheService redisCacheService;

    @Autowired
    private SynonymMapper synonymMapper;

    @Autowired
    private DistributeTaskMapper distributeTaskMapper;
    @Autowired
    private DistributeTaskLogService distributeTaskLogService;

//    @Override
//    public void onApplicationEvent(ContextRefreshedEvent event) {
//
//        System.out.println(1);
//        System.out.println(event.getApplicationContext().getParent());
//
//        if(event.getApplicationContext().getParent() == null){//root application context 没有parent，他就是老大.
//            //需要执行的逻辑代码，当spring容器初始化完成后就会执行该方法。
//            TimerTask task = new TimerTask() {
//                @Override
//                public void run() {
//                    System.out.println("更新首页缓存");
//                    redisCacheService.addIndexFloor();
//
//
//                    System.out.println("更新展示类目缓存");
//                    redisCacheService.addShowCate();
//
//
//
//                }
//            };
//
//            Timer timer = new Timer();
//            timer.schedule(task,0,60);
//        }
//    }


//    @Scheduled(cron="0 0/5 * * * ?")
//    public void onApplicationEvent(){
//        System.out.println("更新首页缓存");
//        redisCacheService.addIndexFloor();
//
//        System.out.println("更新展示类目缓存");
//        redisCacheService.addShowCate();
//    }

    @Scheduled(cron="0 0/5 * * * ?")
    public void addIndexFloor(){
        distributeTaskMapper.start(DistributeTaskConst.INDEX_INDEX_FLOOR);

        DistributeTaskLog taskLog  = new DistributeTaskLog();
        taskLog.setHostip(CommonUtils.getServerIP());
        taskLog.setHostname(CommonUtils.getServerHost());
        taskLog.setTaskcode(DistributeTaskConst.INDEX_INDEX_FLOOR);

        try {
            System.out.println("更新首页缓存");
            redisCacheService.addIndexFloor();
        } catch (Exception e) {
            e.printStackTrace();
            taskLog.setState(Quantity.STATE_2);
            taskLog.setError(e.toString());
        } finally {
            distributeTaskMapper.end(DistributeTaskConst.INDEX_INDEX_FLOOR);
            taskLog.setState(Quantity.STATE_1);
        }

        distributeTaskLogService.insert(taskLog);

    }

    @Scheduled(cron="0 0/5 * * * ?")
    public void addShowCate(){
        distributeTaskMapper.start(DistributeTaskConst.INDEX_SHOW_CATE);

        DistributeTaskLog taskLog  = new DistributeTaskLog();
        taskLog.setHostip(CommonUtils.getServerIP());
        taskLog.setHostname(CommonUtils.getServerHost());
        taskLog.setTaskcode(DistributeTaskConst.INDEX_INDEX_FLOOR);

        try {
            System.out.println("更新展示类目缓存");
            redisCacheService.addShowCate();
        } catch (Exception e) {
            e.printStackTrace();
            taskLog.setState(Quantity.STATE_2);
            taskLog.setError(e.toString());
        } finally {
            distributeTaskMapper.end(DistributeTaskConst.INDEX_SHOW_CATE);
            taskLog.setState(Quantity.STATE_1);
        }

        distributeTaskLogService.insert(taskLog);
    }





}
