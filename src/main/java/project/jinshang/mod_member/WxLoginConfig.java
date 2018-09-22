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
}
