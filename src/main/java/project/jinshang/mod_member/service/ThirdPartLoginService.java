package project.jinshang.mod_member.service;



import com.google.gson.Gson;
import org.apache.commons.httpclient.HttpClient;

import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.mod_member.WxLoginConfig;
import project.jinshang.mod_member.bean.WeixinInfo;
import project.jinshang.mod_member.bean.WxAccessToken;

import java.io.IOException;
import java.io.InputStream;

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

}
