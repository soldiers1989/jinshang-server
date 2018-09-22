package project.jinshang.common.utils;

import io.swagger.models.auth.In;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import project.jinshang.mod_product.bean.SearchAttrSort;
import project.jinshang.mod_product.bean.SearchAttrSortValue;
import project.jinshang.mod_product.bean.SearchAttrSortValueExample;
import project.jinshang.mod_product.mapper.SearchAttrSortMapper;
import project.jinshang.mod_product.mapper.SearchAttrSortValueMapper;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProductSearchSortUtils {

    @Autowired
    private SearchAttrSortMapper searchAttrSortMapper;
    @Autowired
    private SearchAttrSortValueMapper searchAttrSortValueMapper;



    private static Map<String,Map<String,Integer>> sortMap = new HashMap<>();

    private static Integer defautWeigth = 99999;

    @PostConstruct
    public void init(){
        List<SearchAttrSort> list = searchAttrSortMapper.selectByExample(null);
        list.forEach(searchAttrSort -> {
            SearchAttrSortValueExample example = new SearchAttrSortValueExample();
            SearchAttrSortValueExample.Criteria criteria = example.createCriteria();
            criteria.andSearchAttrIdEqualTo(searchAttrSort.getId());
            List<SearchAttrSortValue> values = searchAttrSortValueMapper.selectByExample(example);
            Map<String,Integer> map = new HashMap<>();
            values.forEach(searchAttrSortValue -> {
                map.put(searchAttrSortValue.getKey(),searchAttrSortValue.getWeight());
            });
            sortMap.put(searchAttrSort.getSortname()+"-"+searchAttrSort.getType(),map);
        });
    }


    public static Integer getSortWeigth(String sortname,Short type,String key){
        if(sortMap.containsKey(sortname+"-"+type)){
            Map<String,Integer> map = sortMap.get(sortname+"-"+type);
            return map.getOrDefault(key,defautWeigth);
        }
        return defautWeigth;
    }


    public static  void addKeySort(String sortname,Short type,String key,Integer weight){
        if(!sortMap.containsKey(sortname+"-"+type)){
            sortMap.put(sortname+"-"+type,new HashMap<>());
        }
        Map<String,Integer> map = sortMap.get(sortname+"-"+type);
        map.put(key,weight);
        sortMap.put(sortname+"-"+type,map);
    }

    public static void deleteKeySort(String sortname,Short type,String key){
        if(sortMap.containsKey(sortname+"-"+type)){
            Map<String,Integer> map = sortMap.get(sortname+"-"+type);
            map.remove(key);
        }
    }

    /**
     *
     * @param sortname
     * @param type
     */
    public static void delete(String sortname,Short type){
        sortMap.remove(sortname+"-"+type);
    }


}
