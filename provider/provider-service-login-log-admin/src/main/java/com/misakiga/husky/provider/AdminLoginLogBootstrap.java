package com.misakiga.husky.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication(scanBasePackageClasses = AdminLoginLogBootstrap.class)
@MapperScan(basePackages = "com.misakiga.husky.provider.mapper")
public class AdminLoginLogBootstrap {
    public static void main(String[] args) {
        SpringApplication.run(AdminLoginLogBootstrap.class,args);
    }
}
