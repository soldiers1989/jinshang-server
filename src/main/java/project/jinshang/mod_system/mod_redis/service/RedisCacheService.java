package project.jinshang.mod_system.mod_redis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.common.constant.RedisCacheKey;
import project.jinshang.common.utils.RedisUtils;
import project.jinshang.mod_admin.mod_floor.bean.FloorViewDto;
import project.jinshang.mod_front.bean.ShowCateFrontView;
import project.jinshang.mod_front.service.IndexService;

import java.util.List;

/**
 * create : wyh
 * date : 2018/2/27
 */
@Service
public class RedisCacheService {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private IndexService indexService;


    /**
     * 添加首页楼层缓存
     * @return
     */
    public List addIndexFloor(){
        List list = indexService.getFloor();
        redisUtils.setList(RedisCacheKey.INDEX_FLOOR,list);
        return  list;
    }

    /**
     * 获取首页楼层缓存
     * @return
     */
    public List getIndexFloor(){
      return   redisUtils.getList(RedisCacheKey.INDEX_FLOOR,FloorViewDto.class);
    }


    /**
     * 添加展示类目缓存
     * @return
     */
    public  List<ShowCateFrontView> addShowCate(){
        List list = indexService.getShowCate();
        redisUtils.setList(RedisCacheKey.SHOW_CATEGORY,list);
        return  list;
    }

    /**
     *获取展示类目缓存
     */
    public  List<ShowCateFrontView> getShowCate(){
        return  redisUtils.getList(RedisCacheKey.SHOW_CATEGORY, ShowCateFrontView.class);
    }





    public  void remove(String key){
        redisUtils.expire(key,0);
    }
}
