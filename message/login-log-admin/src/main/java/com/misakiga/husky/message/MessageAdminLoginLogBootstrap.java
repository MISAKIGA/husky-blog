package com.misakiga.husky.message;

import com.misakiga.husky.message.sink.AdminLoginLogSink;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

/**
 * 消息接收，处理
 * @author MISAKIGA
 */
@SpringBootApplication
@EnableBinding({AdminLoginLogSink.class})
public class MessageAdminLoginLogBootstrap {
    public static void main(String[] args) {
        SpringApplication.run(MessageAdminLoginLogBootstrap.class,args);
    }
}
