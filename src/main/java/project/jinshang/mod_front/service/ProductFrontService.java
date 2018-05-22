package project.jinshang.mod_front.service;

import org.springframework.stereotype.Service;
import project.jinshang.common.utils.ProductSearchUtils;
import project.jinshang.common.utils.StringUtils;

import java.util.*;

/**
 * create : wyh
 * date : 2017/11/17
 */

@Service
public class ProductFrontService {

    /**
     * 用于处理商品搜索筛选条件分组(固定属性)
     * @param list
     * @return
     */
    public Map<String,Set> groupAttr(List<Map> list ){
        if(list == null) return  null;

        Map<String,Set> resultMap = new HashMap<>();

        Set<String> keySet = new HashSet<>();

        //所有搜索的key
        for(Map map : list){
            Set<String> set = map.keySet();
            for(String key : set){
                keySet.add(key);
            }
        }


        for(String key : keySet){
            Set valueSet = new HashSet();
            for(Map map : list){
                if(map.containsKey(key) && StringUtils.hasText(map.get(key).toString())){
                    valueSet.add(map.get(key));
                }
            }
            resultMap.put(key,valueSet);
        }

        return  resultMap;
    }


    /**
     * 用于处理商品搜索筛选条件分组(不固定属性)
     * @param list
     * @return
     */
    public Map<String,Set> groupAttrInAttr(List<Map> list ){

        if(list == null) return  null;
        Map<String,Set> resultMap = new HashMap<>();

        List<Map> attrs = new ArrayList<>();

        for(Map<String,Object> map : list){
            if(map == null) continue;
            String key = ProductSearchUtils.getEnName(StringUtils.nvl(map.get("attribute")));
            if(key == null) continue;
            Map<String,Object> attrMap =  new HashMap<>();
            attrMap.put(key,map.get("value"));
            attrs.add(attrMap);
        }

        return  groupAttr(attrs);
    }


    public Map<String,Set> groupAttrInAttrbyProductname(List<Map> list ){

        if(list == null) return  null;
        Map<String,Set> resultMap = new HashMap<>();

        List<Map> attrs = new ArrayList<>();

        for(Map<String,Object> map : list){
            if(map == null) continue;
//            String key = ProductSearchUtils.getEnName(StringUtils.nvl(map.get("attribute")));
//            if(key == null) continue;
            Map<String,Object> attrMap =  new HashMap<>();
            attrMap.put(map.get("attribute").toString(),map.get("value"));
            attrs.add(attrMap);
        }

        return  groupAttr(attrs);
    }











}
