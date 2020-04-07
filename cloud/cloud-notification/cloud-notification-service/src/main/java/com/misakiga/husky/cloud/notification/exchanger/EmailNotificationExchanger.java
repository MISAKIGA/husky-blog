package com.misakiga.husky.cloud.notification.exchanger;

import com.misakiga.husky.cloud.notification.model.EmailNotification;
import com.misakiga.husky.cloud.notification.model.Notification;
import com.misakiga.husky.comm.mail.MailSender;

/**
 * @author MISAKIGA
 */
public class EmailNotificationExchanger implements NotificationExchanger {

    private MailSender mailSender;

    public EmailNotificationExchanger(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public boolean support(Object notification) {
        return notification.getClass().equals(EmailNotification.class);
    }

    @Override
    public boolean exchange(Notification notification) {
        return false;
    }
}
