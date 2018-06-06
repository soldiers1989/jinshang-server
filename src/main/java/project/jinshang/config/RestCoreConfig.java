package project.jinshang.config;

import mizuki.project.core.restserver.ErrorController;
import mizuki.project.core.restserver.config.BasicConfig;
import mizuki.project.core.restserver.config.security.SecurityConfig;
import mizuki.project.core.restserver.mod_user.UserModConfig;
import mizuki.project.core.restserver.modules.oss_ali.AliOssModConfig;
import mizuki.project.core.restserver.modules.session.SpringSessionConfig;
import mizuki.project.core.restserver.modules.sms.SmsModConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import project.jinshang.mod_product.service.ProductSearchService;
import project.jinshang.mod_product.service.ProductSearchServiceImpl;
import project.jinshang.mod_product.service.ProductSearchServiceImplEs;

/**
 * Created by ycj on 2017/3/12.
 * for restServiceCore
 */
@Configuration
// 因为是有@Bean的@Configuration 采用扫描方式
@Import({
        BasicConfig.class,
        DataSourcePgsql1Conf.class,
//        SpringSessionConfig.class,
        SecurityConfig.class,
        SmsModConfig.class,
        UserModConfig.class,
        AliOssModConfig.class,
        ErrorController.class,
        KaptchaConfig.class
})
// for mybatis-spring
@MapperScan({
        "project.jinshang",
        "mizuki.project.core.restserver"
})
public class RestCoreConfig {

    @Bean
    public ProductSearchService productSearchService(){
        return new ProductSearchServiceImpl();
    }

}
