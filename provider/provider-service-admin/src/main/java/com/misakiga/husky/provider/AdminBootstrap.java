package com.misakiga.husky.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author MISAKIGA
 */
@SpringBootApplication
@MapperScan(basePackages = "com.misakiga.husky.provider.mapper")
public class AdminBootstrap {

    public static void main(String[] args) {
        SpringApplication.run(AdminBootstrap.class,args);
    }
}
