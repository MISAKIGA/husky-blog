package com.misakiga.husky.cloud.notification.exchanger;

/**
 * @author MISAKIGA
 */
public interface NotificationExchanger {

    boolean support(Object notification);

    boolean exchange(Object notification);
}
