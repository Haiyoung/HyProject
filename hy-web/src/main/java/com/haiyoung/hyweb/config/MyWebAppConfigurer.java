package com.haiyoung.hyweb.config;

import com.haiyoung.hyweb.web.interceptor.AuthenticateInterceptor;
import com.haiyoung.hyweb.web.interceptor.MyInterceptor01;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MyWebAppConfigurer extends WebMvcConfigurerAdapter{

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
        //registry.addInterceptor(new MyInterceptor01()).addPathPatterns("/**");
        registry.addInterceptor(new AuthenticateInterceptor())
                                                .addPathPatterns("/**")
                                                .excludePathPatterns("/resources/**")
                                                .excludePathPatterns("/page/login")
                                                .excludePathPatterns("/api/authenticate")
                                                /*.excludePathPatterns("/websocket")*/;
        super.addInterceptors(registry);
    }
}
