package com.qf.config;

import com.qf.interceptor.MyInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class MyWebConfig implements WebMvcConfigurer {
    @Autowired
    MyInterceptor myInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor( myInterceptor ).addPathPatterns( "/**" ).excludePathPatterns( "/user/**" ).excludePathPatterns ( "/pay/**" );
    }
}
