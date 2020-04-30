package com.misakiga.husky.message.configure;

import com.misakiga.husky.comm.mail.MailSender;
import com.misakiga.husky.comm.sms.SmsSender;
import com.misakiga.husky.message.exchanger.EmailNotificationExchanger;
import com.misakiga.husky.message.exchanger.SmsNotificationExchanger;
import com.misakiga.husky.message.exchanger.WebSocketNotificationExchanger;
import com.misakiga.husky.starter.mail.MailAutoConfiguration;
import com.misakiga.husky.starter.mail.SmsAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author MISAKIGA
 */
@Configuration
@EnableAutoConfiguration
@AutoConfigureAfter({SmsAutoConfiguration.class, MailAutoConfiguration.class})
public class NotificationAutoConfiguration {

    @Autowired(required = false)
    private SmsSender smsSender;

    @Autowired(required = false)
    private MailSender mailSender;

    @Bean
    public SmsNotificationExchanger smsNotificationExchanger(){
        return new SmsNotificationExchanger(smsSender);
    }

    @Bean
    public EmailNotificationExchanger emailNotificationExchanger(){
        return new EmailNotificationExchanger(mailSender);
    }

    @Bean
    @ConditionalOnProperty(name="husky.notification.websocket.enable",matchIfMissing = false)
    public WebSocketNotificationExchanger webSocketNotificationExchanger(){
        return new WebSocketNotificationExchanger();

    }
}
