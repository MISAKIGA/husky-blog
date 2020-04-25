package com.misakiga.husky.cloud.message;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * 自定义Binding
 * @author MISAKIGA
 */
public interface MessageSource {

    String LOGIN_LOG_TOPIC = "admin-login-log-topic";

    @Output(LOGIN_LOG_TOPIC)
    MessageChannel adminLoginLog();
}
