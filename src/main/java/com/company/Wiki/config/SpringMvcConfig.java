package com.company.Wiki.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {


    /**
     * 注入打印日志的拦截器
     */
//    @Resource
//    LogInterceptor logInterceptor;
//
//
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(logInterceptor)
//                .addPathPatterns("/**")//加入所有请求进行过滤
//                .excludePathPatterns("/login"); //排除/login这个请求，不过滤
//    }
}
