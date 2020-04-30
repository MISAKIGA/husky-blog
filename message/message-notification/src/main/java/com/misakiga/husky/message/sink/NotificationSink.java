package com.misakiga.husky.message.sink;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author MISAKIGA
 */
public interface NotificationSink {

    String TOPIC = "notification-task-topic";

    @Input(NotificationSink.TOPIC)
    SubscribableChannel notificationTask();
}
