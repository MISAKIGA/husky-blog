package com.misakiga.husky.cloud.notification.tests;

import com.misakiga.husky.cloud.notification.NotificationBootstrap;
import com.misakiga.husky.cloud.notification.consumer.NotificationDispatcher;
import com.misakiga.husky.cloud.notification.model.EmailNotification;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = NotificationBootstrap.class)
public class NotificationTest {

    @Autowired
    private NotificationDispatcher dispatcher;

    @Test
    public void testNotification(){
        EmailNotification notification = new EmailNotification();
        notification.setTitle("Hello");
        notification.setReceiver("2595903671@qq.com");
        notification.setContent("hello!!!");
        dispatcher.dispatch(notification);
    }
}
