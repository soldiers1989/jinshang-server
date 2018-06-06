package project.jinshang.common.utils;

import mizuki.project.core.restserver.config.BasicRet;
import mizuki.project.core.restserver.util.JsonUtil;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * create : wyh
 * date : 2017/10/31
 */
public class HttpClientUtils {

    public  static String get(String url) throws IOException {
        return  get(url,"utf-8");
    }


    public  static String get(String url,String charset) throws IOException {
        HttpClient httpClient = new HttpClient();
        GetMethod getMethod = new GetMethod(url);

        try {
            int status=httpClient.executeMethod(getMethod);
            if(status >=200 && status<=300){
                InputStream is = getMethod.getResponseBodyAsStream();
                String result = IOUtils.toString(is, charset);
                return  result;
            }
        } catch (Exception e) {
            throw e;
        }
        return  null;
    }


    public  static String post(String url, NameValuePair[] pairs) throws IOException{
        return  post(url,pairs,"utf-8","utf-8");
    }

    public  static String post(String url, NameValuePair[] pairs, String charset,String serverCharset) throws IOException {
        HttpClient httpClient = new HttpClient();
        PostMethod postMethod = new PostMethod(url);
        postMethod.addParameters(pairs);
        postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,serverCharset);

        try {
            int status=httpClient.executeMethod(postMethod);
            if(status >=200 && status<=300){
                InputStream is = postMethod.getResponseBodyAsStream();
                String result = IOUtils.toString(is, charset);
                return  result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }

    private static CloseableHttpClient httpclient= HttpClients.createDefault();

    public static Map<String,Object> post(String url, Map<String,String> params){
        HttpPost post=new HttpPost(url);
        List<org.apache.http.NameValuePair> pairs = new ArrayList<>();
        Map<String,Object> res=null;
        try {
            params.keySet().forEach((key) -> pairs.add(new BasicNameValuePair(key,params.get(key))));
            post.setEntity(new UrlEncodedFormEntity(pairs, "utf-8"));
            post.setHeader("Content-type", "application/x-www-form-urlencoded");
            CloseableHttpResponse response = httpclient.execute(post);
            HttpEntity entity = response.getEntity();
            res = JsonUtil.toMap(EntityUtils.toString(entity));
            EntityUtils.consume(entity);
            response.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(res==null) res = new BasicRet(-100,"post调用报错").map();
        return res;
    }
}
