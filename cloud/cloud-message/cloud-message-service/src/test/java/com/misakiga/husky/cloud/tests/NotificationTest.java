package com.misakiga.husky.cloud.tests;

import com.misakiga.husky.cloud.api.MessageService;
import com.misakiga.husky.cloud.api.NotificationMessageService;
import com.misakiga.husky.cloud.message.NotificationMessageSource;
import com.misakiga.husky.cloud.notification.model.EmailNotification;
import org.apache.dubbo.config.annotation.Reference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NotificationTest {

    @Reference(version = "1.0.0")
    private NotificationMessageService messageService;

    @Test
    public void testNotification(){
        EmailNotification emailNotification = new EmailNotification();
        emailNotification.setContent("I'm email");
        emailNotification.setReceiver("2595903671@qq.com");
        emailNotification.setTitle("T'm Title");

        messageService.notificationTask("");
    }
}
