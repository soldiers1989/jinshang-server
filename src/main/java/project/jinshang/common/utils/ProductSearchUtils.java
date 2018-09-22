package project.jinshang.common.utils;

import io.swagger.models.auth.In;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * create : wyh
 * date : 2017/12/28
 */
public class ProductSearchUtils {
    private static final Map<String,String> searchNameMap =  new HashMap<>();
    private  static  final  Map<String,Integer> searchComporeMap = new HashMap<>();
    //private  static  final  Map<String,Integer> bigComporeMap = new HashMap<>();

    static {
        searchNameMap.put("cardnum","牌号");
        searchNameMap.put("material","材质");
        searchNameMap.put("productname","品名");
        searchNameMap.put("level1","一级分类");
        searchNameMap.put("level2","二级分类");
        searchNameMap.put("level3","三级分类");
        searchNameMap.put("brand","品牌");
        searchNameMap.put("surfacetreatment","表面处理");
        searchNameMap.put("storename","仓库");
        searchNameMap.put("长度","长度");
        searchNameMap.put("公称直径","公称直径");

//        searchNameMap.put("牙距","牙距");
//        searchNameMap.put("tdiameter","直径");


        //大类、分类、标准、品名、材质、牌号、公称直径、牙距、长度、外径、厚度、性能等级、表面处理、品牌、仓库。
        int compare =  1;
        searchComporeMap.put("level1",compare++);
        searchComporeMap.put("level2",compare++);
        searchComporeMap.put("level3",compare++);
        searchComporeMap.put("productname",compare++);
        searchComporeMap.put("material",compare++);
        searchComporeMap.put("cardnum",compare++);
        searchComporeMap.put("公称直径",compare++);
        searchComporeMap.put("尺寸",compare++);
        searchComporeMap.put("牙距",compare++);
        searchComporeMap.put("牙数",compare++);
        searchComporeMap.put("长度",compare++);
        searchComporeMap.put("外径",compare++);
        searchComporeMap.put("厚度",compare++);
        searchComporeMap.put("性能等级",compare++);

        searchComporeMap.put("surfacetreatment",compare++);
        searchComporeMap.put("brand",compare++);
        searchComporeMap.put("storename",compare++);



//        int  compare2 = 1;
//        bigComporeMap.put("螺栓",compare2++);
//        bigComporeMap.put("螺钉",compare2++);
//        bigComporeMap.put("螺母",compare2++);
//        bigComporeMap.put("垫圈、挡圈",compare2++);
//        bigComporeMap.put("铆钉",compare2++);
//        bigComporeMap.put("螺柱、牙条",compare2++);
//        bigComporeMap.put("开口销",compare2++);
//        bigComporeMap.put("膨胀系列",compare2++);


    }


    public  static String getName(String key ){
        if(searchNameMap.containsKey(key)){
            return  searchNameMap.get(key);
        }
        return  key;
    }


    public  static Integer getSort(String key ){
        if(searchComporeMap.containsKey(key)){
            return  searchComporeMap.get(key);
        }

        return  9999;
    }



//    public static  Integer getBigSort(String key){
//        Integer compre = 999;
//        if(bigComporeMap.containsKey(key)){
//           compre =   bigComporeMap.get(key);
//        }
//        return  compre;
//    }


    public  static  String getEnName(String cnName){
        Set<String> keySet =  searchNameMap.keySet();
        for(String key : keySet){
            if(searchNameMap.get(key).equals(cnName)){
                return  key;
            }
        }
        return  null;
    }





}
