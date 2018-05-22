package project.jinshang.common.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * create : wyh
 * date : 2017/11/15
 */
public class ProductGroup {

    private  List list1 = null;
    private  List list2 = null;
    private  int use = 1;

    private  List resultList = new ArrayList();

    private void  switchList(){
        switch (use){
            case 1:
                if(list1 == null) list1 = new ArrayList();
                if(list1.size()>0){
                    resultList.add(list1);
                }
                list2 = new ArrayList();
                use = 2;
                break;
            case 2:
                if(list2 == null) list2 = new ArrayList();
                if(list2.size()>0) {
                    resultList.add(list2);
                }
                list1 = new ArrayList();
                use = 1;
                break;
        }
    }



    private  void  add(Map map){

        if(use == 1){
            if(list1 == null){
                list1 = new ArrayList();
            }
            list1.add(map);
        }

        if(use ==2){
            if(list2 == null){
                list2 = new ArrayList();
            }
            list2.add(map);
        }


    }


    public  List group(List<Map> list){

        for(int i=0;i<list.size();i++){

            Map map = list.get(i);
            if(map.get("rank").equals((long)1)){
                switchList();
            }
            add(map);

            if(i == list.size()-1){
                switchList();
            }
        }
        return  resultList;
    }

}
