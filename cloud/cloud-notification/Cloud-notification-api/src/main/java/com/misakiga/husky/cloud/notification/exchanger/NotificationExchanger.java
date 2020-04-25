package com.misakiga.husky.cloud.notification.exchanger;

import com.misakiga.husky.cloud.notification.model.Notification;

/**
 * @author MISAKIGA
 */
public interface NotificationExchanger {

    boolean support(Object notification);

    boolean exchange(Object notification);
}
