package project.jinshang.scheduled.util;

import org.apache.commons.collections.MapUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * create : wyh
 * date : 2018/1/30
 */
public class ProductViewStatistics {

    private  ProductViewStatistics(){}

    private  static Map<Long,Integer> collection1 = new HashMap<>();
    private  static Map<Long,Integer> collection2 = new HashMap<>();
    private  static  Short used = 1;

    /**
     * 添加访问次数
     * @param id
     */
    public static void addCount(Long id){
        Map<Long,Integer> map = getCollection();
        if(map == null){
            throw  new RuntimeException("容器不存在,无法统计数据");
        }

        if(map.containsKey(id)){
            map.put(id,map.get(id)+1);
        }else{
            map.put(id,1);
        }

    }


    private  static Map<Long,Integer>  getCollection(){

        if(used == 1){
            return  collection1;
        }else if(used == 2){
            return  collection2;
        }
        return  null;
    }

    private  static  synchronized void switchCollection(){
        if(used == 1){
            used =2;
        }else if(used==2){
            used =  1;
        }
    }


    public synchronized   static  Map<Long,Integer> getandclear(){
        Map<Long,Integer> map = new HashMap<>();

        Map<Long,Integer> collection = getCollection();
        map.putAll(collection);
        switchCollection();
        collection.clear();
        return  map;
    }


}
