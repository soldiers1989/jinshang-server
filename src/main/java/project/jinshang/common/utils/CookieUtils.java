package project.jinshang.common.utils;


import javax.servlet.http.Cookie;

/**
 * create : wyh
 * date : 2018/1/2
 */
public class CookieUtils {

    public static boolean exisCookie(Cookie[] cookies,String name){
       boolean exis =  false;
       if(cookies != null && cookies.length>0){
           for(Cookie cookie : cookies){
               if(cookie.getName().equals(name)){
                   return  true;
               }
           }
       }
       return  false;
    }


    public static String getValue(Cookie[] cookies,String name){
        if(cookies != null && cookies.length>0){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals(name)){
                    return  cookie.getValue();
                }
            }
        }
        return  null;
    }





}
