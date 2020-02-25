package com.misakiga.husky.configuration;

import com.misakiga.husky.interceptor.FeignRequestInterceptor;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author MISAKIGA
 */
@Configuration
public class FeignRequestConfiguration {

    @Bean
    public RequestInterceptor requestInterceptor(){
        //将自定义的拦截器注入到Bean中
        return new FeignRequestInterceptor();
    }
}
