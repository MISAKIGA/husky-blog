package com.misakiga.husky.message.exchanger;

import com.misakiga.husky.cloud.notification.exchanger.NotificationExchanger;
import com.misakiga.husky.cloud.notification.model.EmailNotification;
import com.misakiga.husky.comm.mail.MailSender;
import com.misakiga.husky.comm.mail.MailSenderParams;

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
    public boolean exchange(Object notification) {
        EmailNotification emailNotification = (EmailNotification)notification;
        MailSenderParams params = new MailSenderParams();
        params.setMailTo(emailNotification.getReceiver());
        params.setContent(emailNotification.getContent());
        params.setTitle(emailNotification.getTitle());

        mailSender.sendSimpleMail(params);
        return true;
    }
}
