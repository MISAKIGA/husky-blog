package com.misakiga.husky.cloud.notification;

import com.misakiga.husky.cloud.notification.sink.NotificationSink;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

/**
 * 通知中心
 * @author MISAKIGA
 */
@SpringBootApplication
@EnableBinding({NotificationSink.class})
public class NotificationBootstrap {
    public static void main(String[] args) {
        SpringApplication.run(NotificationBootstrap.class,args);
    }
}
