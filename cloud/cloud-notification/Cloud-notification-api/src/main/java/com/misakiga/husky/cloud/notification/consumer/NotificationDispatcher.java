package com.misakiga.husky.cloud.notification.consumer;

import com.misakiga.husky.cloud.notification.model.Notification;

/**
 * @author MISAKIGA
 */
public interface NotificationDispatcher {

    /**
     * 执行通知操作
     * @param notification 通知 {@link com.misakiga.husky.cloud.notification.model.SmsNotification} {@link com.misakiga.husky.cloud.notification.model.EmailNotification}
     */
    void dispatch(String notification);
}
