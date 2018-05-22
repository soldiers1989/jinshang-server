package project.jinshang.common.utils;

import project.jinshang.mod_member.bean.SellerProductCategory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * create : wyh
 * date : 2018/3/9
 */
public class SellerCategoryUtils {


    /**
     * 获取子类
     * @param list
     * @param pid
     * @return
     */
    public static List<SellerProductCategory> getChildsManyGroup(List<SellerProductCategory> list, long pid){
        List<SellerProductCategory> arr = new ArrayList<SellerProductCategory>();
        for(SellerProductCategory location : list){
            if(pid == location.getParentid()){
                if(location.getBrokeragerate() == null || location.getBrokeragerate().compareTo(new BigDecimal(-1)) == 0){
                    BigDecimal brokeragerate =  getBrokeragerate(list,location.getCategoryid());
                    location.setBrokeragerate(brokeragerate);
                }

                location.setList(getChildsManyGroup(list, location.getCategoryid()));
                arr.add(location);
            }
        }
        return arr;
    }





    /**
     * 获取佣金比率
     * @param list
     * @param id
     * @return
     */
    public static BigDecimal getBrokeragerate(List<SellerProductCategory> list , long id){
        for(SellerProductCategory cate : list){
            if(cate.getCategoryid() == id){
                if(cate.getBrokeragerate().compareTo(new BigDecimal(-1)) == 0){  //-1 沿用上级
                    return getBrokeragerate(list,cate.getParentid());
                }else{
                    return  cate.getBrokeragerate();
                }
            }
        }
        return  new BigDecimal(0);
    }



}
