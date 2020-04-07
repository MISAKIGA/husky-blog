package com.misakiga.husky.cloud.message;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * 自定义Binding
 * @author MISAKIGA
 */
public interface MessageSource {

    @Output("admin-login-log-topic")
    MessageChannel adminLoginLog();

    @Output("notification-task-topic")
    MessageChannel notificationTask();
}
