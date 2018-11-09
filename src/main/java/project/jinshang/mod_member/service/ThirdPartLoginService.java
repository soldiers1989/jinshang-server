package project.jinshang.mod_member.service;



import com.google.gson.Gson;
import org.apache.commons.httpclient.HttpClient;

import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.stereotype.Service;
import project.jinshang.common.utils.RedisUtils;
import project.jinshang.common.utils.StringUtils;
import project.jinshang.mod_member.WxLoginConfig;
import project.jinshang.mod_member.bean.GlobalAccessToken;
import project.jinshang.mod_member.bean.JsapiTicket;
import project.jinshang.mod_member.bean.WeixinInfo;
import project.jinshang.mod_member.bean.WxAccessToken;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

/**
 * create : wyh
 * date : 2018/2/24
 */

@Service
public class ThirdPartLoginService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private HttpClient httpClient;

    @Autowired
    private WxLoginConfig wxLoginConfig;

    @Autowired
    private  Gson gson;
    @Autowired
    private RedisUtils redisUtils;

    public   static  final  String GLOBAL_ACCESS_TOKEN = "globalaccesstoken";
    public   static  final  String GLOBAL_JSAPITICKET  = "globaljsapiticket";
    public   static  final  String Zero                = "0";

    public ThirdPartLoginService() {
    }

    /**
     * 获取微信token
     * @param code
     * @return
     */
    public WxAccessToken getWxAccssToken(String code,String type){
        int status = 0;
        String url = wxLoginConfig.getAccsstokenUrl().replace("APPID",wxLoginConfig.getAppId())
                .replace("SECRET",wxLoginConfig.getAppSecret()).replace("CODE",code);

        if("wap".equalsIgnoreCase(type)){
            url = wxLoginConfig.getAccsstokenUrl().replace("APPID",wxLoginConfig.getWapAppId())
                    .replace("SECRET",wxLoginConfig.getWapAppSecret()).replace("CODE",code);
        }


        GetMethod getMethod =  new GetMethod(url);
        try {
            status=httpClient.executeMethod(getMethod);
            if(status >=200 && status<=300){
                String result = getMethod.getResponseBodyAsString();

                logger.error("微信获取token:"+result);


                if(result != null && !result.equals("")){

                    WxAccessToken at=	gson.fromJson(result, WxAccessToken.class);
                    //logger.error(at.toString());

                    return at;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return  null;
    }

    /**
     * 获取微信token
     * @param code
     * @return
     */
    public WxAccessToken getWxAccTokForTinyProg(String code){
        int status = 0;
        String url = wxLoginConfig.getWxacctokUrlTinyProg().replace("APPID",wxLoginConfig.getTinyProgAppId())
                .replace("SECRET",wxLoginConfig.getTinyProgSecret()).replace("JSCODE",code);


        GetMethod getMethod =  new GetMethod(url);
        try {
            status=httpClient.executeMethod(getMethod);
            if(status >=200 && status<=300){
                String result = getMethod.getResponseBodyAsString();

                logger.error("微信获取token:"+result);


                if(result != null && !result.equals("")){

                    WxAccessToken at=	gson.fromJson(result, WxAccessToken.class);
                    //logger.error(at.toString());

                    return at;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return  null;
    }



    /**
     * 获取微信用户信息
     * @param openid
     * @param access_token
     * @return
     */
    public WeixinInfo getWeixinInfo(String openid, String access_token) {

        int status = 0;
        HttpClient client = new HttpClient();
        String url = wxLoginConfig.getWxinfoUrl().replace("ACCESS_TOKEN",access_token).replace("OPENID",openid);
        GetMethod getMethod = new GetMethod(url);

        try {
            status=client.executeMethod(getMethod);
            if(status >=200 && status<=300){
                InputStream is = getMethod.getResponseBodyAsStream();

                String result = IOUtils.toString(is, "utf-8");
                WeixinInfo weixinInfo = gson.fromJson(result,WeixinInfo.class);

                return weixinInfo;
            }else{
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    /**
     *获取全局jsapi_ticket并缓存
     * @author xiazy
     * @date  2018/6/16 16:50
     * @param
     * @return project.jinshang.mod_member.bean.JsapiTicket
     */
    public JsapiTicket getJsApiTicket(){
        JsapiTicket globaljsapiTicket=new JsapiTicket();
        String jsapiticket=redisUtils.get(GLOBAL_JSAPITICKET);
        if (StringUtils.hasText(jsapiticket)){
            globaljsapiTicket.setTicket(jsapiticket);
        }else{
            GlobalAccessToken gst=this.getAccessToken();
            globaljsapiTicket=this.jsapiticket(gst.getAccess_token());
        }
        return globaljsapiTicket;
    }
    /**
     *获取accessToken并缓存下来
     * @author xiazy
     * @date  2018/6/16 16:07
     * @param
     * @return java.lang.String
     */
    public GlobalAccessToken getAccessToken(){
        GlobalAccessToken globalAccessToken=new GlobalAccessToken();
        String accesstoken=redisUtils.get(GLOBAL_ACCESS_TOKEN);
        if (StringUtils.hasText(accesstoken)){
            globalAccessToken.setAccess_token(accesstoken);
            return globalAccessToken;
        }else{
            globalAccessToken=this.accessToken();
        }
        return globalAccessToken;

    }

    /**
     *调用微信的官方文档，进行accessToken的获取，并缓存到Redis
     * @author xiazy
     * @date  2018/6/16 16:13
     * @param
     * @return java.lang.String
     */
    public GlobalAccessToken accessToken(){
        int status = 0;
        HttpClient client = new HttpClient();
        String url = wxLoginConfig.getGlobalAccsstokenUrl().replace("APPID",wxLoginConfig.getWapAppId())
                .replace("APPSECRET",wxLoginConfig.getWapAppSecret());
        GetMethod getMethod = new GetMethod(url);
        try {
            status=httpClient.executeMethod(getMethod);
            if(status >=200 && status<=300){
                String result = getMethod.getResponseBodyAsString();

                logger.error("微信获取全局accesstoken:"+result);


                if(result != null && !result.equals("")){

                    GlobalAccessToken gst=	gson.fromJson(result, GlobalAccessToken.class);
                    //logger.error(at.toString());
                    if(Zero.equals(gst.getErrcode())){
                        long       expires_in  =  Long.valueOf(gst.getExpires_in());
                        String   access_token  =  gst.getAccess_token();
                        Expiration expiration  =  Expiration.seconds(expires_in);
                        redisUtils.set(GLOBAL_ACCESS_TOKEN,access_token,expiration);
                    }
                    return gst;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return  null;
    }


    /**
     *调用微信的官方文档，进行jsapiticket的获取，并缓存到Redis
     * @author xiazy
     * @date  2018/6/16 17:00
     * @param globalaccesstoken
     * @return project.jinshang.mod_member.bean.JsapiTicket
     */
    public JsapiTicket jsapiticket(String globalaccesstoken){
        int status=0;
        HttpClient clien=new HttpClient();
        String url=wxLoginConfig.getJsapiTicketUrl().replace("ACCESS_TOKEN",globalaccesstoken);
        GetMethod getMethod=new GetMethod(url);
        try {
            status=httpClient.executeMethod(getMethod);
            if(status >=200 && status<=300){
                String result = getMethod.getResponseBodyAsString();

                logger.error("获取微信JS接口的临时票据:"+result);


                if(result != null && !result.equals("")){

                    JsapiTicket jat =	gson.fromJson(result, JsapiTicket.class);
                    //logger.error(at.toString());
                    if(Zero.equals(jat.getErrcode())){
                        long       expires_in  =  Long.valueOf(jat.getExpires_in());
                        String   jsapi_ticket  =  jat.getTicket();
                        Expiration expiration  =  Expiration.seconds(expires_in);
                        redisUtils.set(GLOBAL_JSAPITICKET,jsapi_ticket,expiration);
                    }
                    return jat;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return  null;
    }

}
