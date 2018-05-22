package project.jinshang.mod_pay.mod_wxpay;

import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("mod.wechat.pay")
public class WxPayConfiguration {
    private String appId;
    private String mchId;
    private String mchKey;
    private String keyPath;

    @Bean
    @ConditionalOnMissingBean
    public WxPayConfig config() {
        WxPayConfig payConfig = new WxPayConfig();
        payConfig.setMchId(mchId);
        payConfig.setMchKey(mchKey);
        payConfig.setAppId(appId);
        payConfig.setKeyPath(keyPath);
        return payConfig;
    }

    @Bean
    //@ConditionalOnMissingBean
    public WxPayService wxPayService(WxPayConfig payConfig) {
        WxPayService wxPayService = new WxPayServiceImpl();
        wxPayService.setConfig(payConfig);
        return wxPayService;
    }

    public String getMchId() {
        return mchId;
    }

    public WxPayConfiguration setMchId(String mchId) {
        this.mchId = mchId;
        return this;
    }

    public String getMchKey() {
        return mchKey;
    }

    public WxPayConfiguration setMchKey(String mchKey) {
        this.mchKey = mchKey;
        return this;
    }

    public String getAppId() {
        return appId;
    }

    public WxPayConfiguration setAppId(String appId) {
        this.appId = appId;
        return this;
    }

    public String getKeyPath() {
        return keyPath;
    }

    public WxPayConfiguration setKeyPath(String keyPath) {
        this.keyPath = keyPath;
        return this;
    }
}
