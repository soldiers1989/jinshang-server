package project.jinshang.common.utils;

import java.io.Serializable;
import java.util.*;

/**
 * create : wyh
 * date : 2017/10/27
 */
public class ErrorMes implements Serializable{

    public List<String[]> errorList = new ArrayList<>();

    public void addError(String k,String v){
        if(errorList != null){
            for(String[] sArr : errorList){
                if(k.equals(sArr[0])){
                    errorList.remove(sArr);
                }
            }
        }else{
            errorList = new ArrayList<>();
        }

        String[] s = new String[2];
        s[0] =  k;
        s[1] = v;
        errorList.add(s);
    }


    public int getSize() {
        if (errorList == null) {
            return 0;
        }
        return errorList.size();
    }

    public  void  clean(){
        if(errorList != null){
            errorList.clear();
        }
    }


    public  String getAllErrStr(){
        if(errorList != null ){
            StringBuilder sb = new StringBuilder();
            for(int i=0;i<errorList.size();i++){
                sb.append(errorList.get(i)[1]);

                if(i != (getSize()-1)){
                    sb.append("|");
                }
            }

            return  sb.toString();
        }

        return  "";
    }


    public  String getItemValue(String key){
        if(errorList != null ){
            for(String[] sArr : errorList){
                if(key.equals(sArr[0])){
                    return  sArr[1];
                }
            }
        }

        return  "";
    }




    public List<String[]> getErrorList() {
        return errorList;
    }




}
