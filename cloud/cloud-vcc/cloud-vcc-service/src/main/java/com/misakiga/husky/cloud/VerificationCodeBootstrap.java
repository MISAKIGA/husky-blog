package com.misakiga.husky.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * 验证码服务器
 * @author MISAKIGA
 */
@EnableCaching
@SpringBootApplication
public class VerificationCodeBootstrap {
    public static void main(String[] args) {
        SpringApplication.run(VerificationCodeBootstrap.class,args);
    }
}
