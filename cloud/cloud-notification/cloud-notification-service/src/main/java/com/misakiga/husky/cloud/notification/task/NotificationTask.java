package com.misakiga.husky.cloud.notification.task;

import com.misakiga.husky.cloud.notification.exchanger.NotificationExchanger;

import java.util.concurrent.Callable;

/**
 * @author MISAKIGA
 */
public class NotificationTask implements Callable<Boolean> {

    private NotificationExchanger notificationExchanger;
    private Object notification;

    public NotificationTask(NotificationExchanger notificationExchanger, Object notification) {
        this.notificationExchanger = notificationExchanger;
        this.notification = notification;
    }

    @Override
    public Boolean call() throws Exception {
        return notificationExchanger.exchange(notification);
    }
}
