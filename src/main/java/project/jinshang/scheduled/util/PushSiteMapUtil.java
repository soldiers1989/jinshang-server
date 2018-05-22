package project.jinshang.scheduled.util;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

public class PushSiteMapUtil {

    /**
     * 推送 Stiemap 到百度
     * @param url
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static Map push(List<String> url){
        String urlPath = "http://data.zz.baidu.com/urls?site=www.jinshang9.com&token=MHJnjmC6RT9KqLwd";
        try {
            String re = postHttp(urlPath, url);
            System.out.println(re);
            Map map = new Gson().fromJson(re, Map.class);
            return map;
        } catch (Exception e) {

        }

        return  null;
    }

    /**
     * 百度链接实时推送
     * @param url
     * @param params
     * @return
     */
    private static String postHttp(String url,List<String> params){
        if(null == url || null == params || params.isEmpty()){
            return null;
        }
        String result = "";
        PrintWriter out = null;
        BufferedReader in = null;
        try {
            URLConnection conn=new URL(url).openConnection();// 建立URL之间的连接
            conn.setRequestProperty("Host","data.zz.baidu.com");// 设置通用的请求属性
            conn.setRequestProperty("User-Agent", "curl/7.12.1");
            conn.setRequestProperty("Content-Length", "83");
            conn.setRequestProperty("Content-Type", "text/plain");
            conn.setDoInput(true);// 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            out = new PrintWriter(conn.getOutputStream());// 获取conn对应的输出流
            String param = "";// 发送请求参数
            for(String s : params){
                param += s + "\n";
            }
            out.print(param.trim());
            out.flush();
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while((line=in.readLine())!= null){
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            try{
                if(out != null)
                    out.close();
                if(in!= null)
                    in.close();
            }catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }



}
