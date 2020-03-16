package com.misakiga.husky.business;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * 单点登录，oauth授权服务器
 * @author MISAKIGA
 */
@SpringBootApplication(scanBasePackageClasses = {OAuthBootstrap.class},scanBasePackages = "com.misakiga.husky.cloud.feign")
@EnableDiscoveryClient
@EnableCaching
@EnableResourceServer
@EnableFeignClients
public class OAuthBootstrap {
    public static void main(String[] args) {
        SpringApplication.run(OAuthBootstrap.class,args);
    }
}
