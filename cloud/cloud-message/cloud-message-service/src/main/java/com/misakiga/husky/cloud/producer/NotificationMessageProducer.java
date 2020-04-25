package com.misakiga.husky.cloud.producer;

import com.misakiga.husky.cloud.api.NotificationMessageService;
import com.misakiga.husky.cloud.message.NotificationMessageSource;
import com.misakiga.husky.cloud.notification.model.Notification;
import org.apache.dubbo.config.annotation.Service;
import org.apache.rocketmq.common.message.MessageConst;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.util.MimeTypeUtils;

import javax.annotation.Resource;

@Service(version = "1.0.0")
public class NotificationMessageProducer implements NotificationMessageService {

    @Resource
    private NotificationMessageSource source;

    /**
     * 发送通知
     * @param notification {@link Notification}
     * @return 发送是否成功
     */
    @Override
    public boolean notificationTask(Notification notification) {
        return source.notificationTask().send(MessageBuilder
                .withPayload(notification)
                .setHeader(MessageConst.PROPERTY_TAGS, NotificationMessageSource.NOTIFICATION_TOPIC)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build());
    }
}
