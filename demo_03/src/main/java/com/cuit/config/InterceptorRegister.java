package com.cuit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * 注册拦截器
 */
@Configuration
public class InterceptorRegister implements WebMvcConfigurer {
    /**
     * 把定义的拦截器类注册为Bean
     */
    @Bean
    public HandlerInterceptor getInterceptor() {
        return new ResidentUserInterceptor();
    }

    /**
     * 添加拦截器，并配置拦截地址
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> pathPatterns = new ArrayList<>();
        // /login 路劲不拦截
        pathPatterns.add("/login");
        registry.addInterceptor(getInterceptor()).excludePathPatterns(pathPatterns);
    }
}
