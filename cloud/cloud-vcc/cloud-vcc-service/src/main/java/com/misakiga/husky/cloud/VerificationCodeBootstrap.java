package com.misakiga.husky.cloud;

import com.misakiga.husky.cloud.sink.NotificationSink;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;


/**
 * 验证码服务器
 * @author MISAKIGA
 */
@SpringBootApplication(scanBasePackageClasses = VerificationCodeBootstrap.class,scanBasePackages = "com.misakiga.husky.cloud.feign")
@EnableDiscoveryClient
@EnableCaching
@EnableFeignClients
public class VerificationCodeBootstrap {
    public static void main(String[] args) {
        SpringApplication.run(VerificationCodeBootstrap.class,args);
    }
}
