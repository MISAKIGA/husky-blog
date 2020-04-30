package com.misakiga.husky.cloud.sink;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author MISAKIGA
 */
public interface NotificationSink {

    String TOPIC = "notification-task-topic";

   // @Input(TOPIC)
    SubscribableChannel notificationTask();
}
