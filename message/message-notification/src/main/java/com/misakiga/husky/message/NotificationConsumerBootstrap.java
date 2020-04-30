package com.misakiga.husky.message;

import com.misakiga.husky.message.sink.NotificationSink;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;

/**
 * 通知中心
 * @author MISAKIGA
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableBinding({NotificationSink.class})
public class NotificationConsumerBootstrap {
    public static void main(String[] args) {
        SpringApplication.run(NotificationConsumerBootstrap.class,args);
    }
}
