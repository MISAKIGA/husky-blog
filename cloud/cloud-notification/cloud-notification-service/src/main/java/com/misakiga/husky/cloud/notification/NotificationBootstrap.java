package com.misakiga.husky.cloud.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
/**
 * 通知中心
 * @author MISAKIGA
 */
@SpringBootApplication
@EnableDiscoveryClient
public class NotificationBootstrap {
    public static void main(String[] args) {
        SpringApplication.run(NotificationBootstrap.class,args);
    }
}
