package com.misakiga.husky.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication(scanBasePackageClasses = ProviderAdminLoginLogBootstrap.class)
@MapperScan(basePackages = "com.misakiga.husky.provider.mapper")
public class ProviderAdminLoginLogBootstrap {
    public static void main(String[] args) {
        SpringApplication.run(ProviderAdminLoginLogBootstrap.class,args);
    }
}
