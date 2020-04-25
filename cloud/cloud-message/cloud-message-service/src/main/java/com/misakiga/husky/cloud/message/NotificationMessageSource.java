package com.misakiga.husky.cloud.message;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface NotificationMessageSource {

    String NOTIFICATION_TOPIC = "notification-task-topic";

    @Output(NOTIFICATION_TOPIC)
    MessageChannel notificationTask();
}
