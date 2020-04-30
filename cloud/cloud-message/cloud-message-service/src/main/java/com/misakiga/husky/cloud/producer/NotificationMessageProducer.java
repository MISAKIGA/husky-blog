package com.misakiga.husky.cloud.producer;

import com.misakiga.husky.cloud.api.NotificationMessageService;
import com.misakiga.husky.cloud.message.NotificationMessageSource;
import com.misakiga.husky.cloud.notification.model.EmailNotification;
import com.misakiga.husky.cloud.notification.model.Notification;
import org.apache.dubbo.config.annotation.Service;
import org.apache.rocketmq.common.message.MessageConst;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeTypeUtils;

import javax.annotation.Resource;

/**
 * 使用消息队列发送通知
 */
@Component
@Service(version = "1.0.0")
public class NotificationMessageProducer implements NotificationMessageService {

    @Resource
    private NotificationMessageSource source;

    /**
     * 发送通知
     * @param notificationJson
     * {@link Notification}
     * @return
     */
    @Override
    public boolean notificationTask(String notificationJson) {
        return source.notificationTask().send(MessageBuilder
                .withPayload(notificationJson)
                .setHeader(MessageConst.PROPERTY_TAGS, NotificationMessageSource.NOTIFICATION_TOPIC)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build());
    }
}
