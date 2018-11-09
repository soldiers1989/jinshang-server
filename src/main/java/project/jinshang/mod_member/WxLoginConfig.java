package project.jinshang.mod_member;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * create : wyh
 * date : 2018/2/24
 */

@Component
@ConfigurationProperties("mod.wechat.login")
public class WxLoginConfig {

    private  String appId;
    private  String appSecret;
    private  String accsstokenUrl;
    private  String globalAccsstokenUrl;
    private  String wxinfoUrl;

    private  String wapAppId;
    private  String wapAppSecret;

    private  String jsapiTicketUrl;

    private String wxacctokUrlTinyProg;

    private String tinyProgAppId;
    private String tinyProgSecret;


    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getAccsstokenUrl() {
        return accsstokenUrl;
    }

    public void setAccsstokenUrl(String accsstokenUrl) {
        this.accsstokenUrl = accsstokenUrl;
    }

    public String getWxinfoUrl() {
        return wxinfoUrl;
    }

    public void setWxinfoUrl(String wxinfoUrl) {
        this.wxinfoUrl = wxinfoUrl;
    }

    public String getWapAppId() {
        return wapAppId;
    }

    public void setWapAppId(String wapAppId) {
        this.wapAppId = wapAppId;
    }

    public String getWapAppSecret() {
        return wapAppSecret;
    }

    public void setWapAppSecret(String wapAppSecret) {
        this.wapAppSecret = wapAppSecret;
    }

    public String getGlobalAccsstokenUrl() {
        return globalAccsstokenUrl;
    }

    public void setGlobalAccsstokenUrl(String globalAccsstokenUrl) {
        this.globalAccsstokenUrl = globalAccsstokenUrl;
    }

    public String getJsapiTicketUrl() {
        return jsapiTicketUrl;
    }

    public void setJsapiTicketUrl(String jsapiTicketUrl) {
        this.jsapiTicketUrl = jsapiTicketUrl;
    }

    public String getWxacctokUrlTinyProg() {
        return wxacctokUrlTinyProg;
    }

    public void setWxacctokUrlTinyProg(String wxacctokUrlTinyProg) {
        this.wxacctokUrlTinyProg = wxacctokUrlTinyProg;
    }

    public String getTinyProgAppId() {
        return tinyProgAppId;
    }

    public void setTinyProgAppId(String tinyProgAppId) {
        this.tinyProgAppId = tinyProgAppId;
    }

    public String getTinyProgSecret() {
        return tinyProgSecret;
    }

    public void setTinyProgSecret(String tinyProgSecret) {
        this.tinyProgSecret = tinyProgSecret;
    }
}
