package com.misakiga.husky.cloud;

import com.misakiga.husky.cloud.message.MessageSource;
import com.misakiga.husky.cloud.message.NotificationMessageSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;

/**
 * 消息队列服务
 * @author MISAKIGA
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableBinding({MessageSource.class, NotificationMessageSource.class})
public class CloudMessageBootstrap {
    public static void main(String[] args) {
        SpringApplication.run(CloudMessageBootstrap.class,args);
    }
}
