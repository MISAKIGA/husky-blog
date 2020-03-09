package com.misakiga.husky.message.sink;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * 接收指定消息群组
 * @author MISAKIGA
 */
public interface AdminLoginLogSink {

    @Input("admin-login-log-topic")
    SubscribableChannel adminLoginLog();
}
