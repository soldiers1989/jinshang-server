package project.jinshang.config;

import mizuki.project.core.restserver.interceptor.DefaultIntercep;
import mizuki.project.core.restserver.interceptor.RestAuthIntercep;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import project.jinshang.interceptor.AdminInterceptor;
import project.jinshang.interceptor.FrontInterceptor;
import project.jinshang.interceptor.MemberInterceptor;
import project.jinshang.interceptor.SessionInterceptor;

/**
 * Created by ycj on 16/4/20.
 * 配置web 拦截等
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(sessionInterceptor()).addPathPatterns("/**");

        registry.addInterceptor(defaultIntercep()).addPathPatterns("/**");


        registry.addInterceptor(frontInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/rest/callback/**");

        registry.addInterceptor(adminInterceptor())
                .addPathPatterns("/rest/admin/**").excludePathPatterns("/rest/admin/synonym/**")
                .excludePathPatterns("/rest/admin/login")
                .excludePathPatterns("rest/alipay/**").excludePathPatterns("rest/wxpay/**")
        ;

        registry.addInterceptor(memberInterceptor())
                .addPathPatterns("/rest/seller/**")
                .addPathPatterns("/rest/buyer/**")
                .excludePathPatterns("/rest/buyer/registerMember")
                .excludePathPatterns("/rest/buyer/registerMemberByMobile")
                .excludePathPatterns("/rest/seller/login")
                .excludePathPatterns("/rest/seller/subLogin")
                .excludePathPatterns("/rest/buyer/login")
                .excludePathPatterns("/rest/buyer/loginByMobile")
                .excludePathPatterns("/rest/buyer/wap/login")
                .excludePathPatterns("/rest/buyer/wap/loginByMobile")
                .excludePathPatterns("/rest/buyer/logout")
                .excludePathPatterns("/rest/buyer/wap/logout")
                .excludePathPatterns("/rest/seller/logout")
                .excludePathPatterns("/rest/forgetPassword/**")
                .excludePathPatterns("/rest/front/**")
                .excludePathPatterns("/rest/common/**")
                .excludePathPatterns("/rest/thirdpart/login/**");




        super.addInterceptors(registry);
    }

    @Bean
    public MultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(104857600);
        multipartResolver.setMaxInMemorySize(4096);
        // lazy加载，使用时才接收数据
        multipartResolver.setResolveLazily(true);
        return multipartResolver;
    }

    @Bean
    public DefaultIntercep defaultIntercep(){
        return new DefaultIntercep();
    }

    @Bean
    public RestAuthIntercep restAuthIntercep(){
        return new RestAuthIntercep();
    }



    @Bean
    public MemberInterceptor memberInterceptor(){
        return  new MemberInterceptor();
    }

    @Bean
    public AdminInterceptor adminInterceptor(){
        return  new AdminInterceptor();
    }


    @Bean
    public FrontInterceptor frontInterceptor(){
        return  new FrontInterceptor();
    }


    @Bean
    public SessionInterceptor sessionInterceptor(){
        return new SessionInterceptor();
    }


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true);
    }
}
