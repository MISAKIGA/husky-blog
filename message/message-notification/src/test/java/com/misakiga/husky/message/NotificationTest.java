package com.misakiga.husky.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.misakiga.husky.cloud.notification.model.EmailNotification;
import com.misakiga.husky.cloud.notification.model.SmsNotification;
import com.misakiga.husky.comm.mail.MailSender;
import com.misakiga.husky.comm.mail.MailSenderParams;
import com.misakiga.husky.comm.sms.SmsParameter;
import com.misakiga.husky.comm.sms.SmsSendResult;
import com.misakiga.husky.comm.sms.SmsSender;
import com.misakiga.husky.message.consumer.NotificationDispatcherImpl;
import org.apache.dubbo.config.annotation.Reference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = NotificationConsumerBootstrap.class)
public class NotificationTest {

    @Resource
    private NotificationDispatcherImpl notificationDispatcher;


    @Test
    public void testNotification(){
        EmailNotification notification = new EmailNotification();
        notification.setTitle("Hello");
        notification.setReceiver("2595903671@qq.com");
        notification.setContent("hello!!!");
        emailNotification(notification);
        //notificationDispatcher.dispatch(notification);
                                                  
/*        SmsNotification smsNotification = new SmsNotification();
        smsNotification.setPhoneNumber("13534967540");
        smsNotification.setTemplateCode("SMS_187242199");
        Map<String,Object> map = new HashMap<>();
        map.put("code","878945");
        smsNotification.setParams(map);
       // SmsSendResult result = testSmsNotification(smsNotification);
        notificationDispatcher.dispatch(smsNotification);*/

    }

    @Autowired
    private MailSender sender;

    @Autowired
    private SmsSender smsSender;

    private SmsSendResult testSmsNotification(Object notification){
        Assert.notNull(smsSender, "短信接口没有初始化");

        SmsNotification smsNotification = (SmsNotification) notification;
        SmsParameter parameter = new SmsParameter();
        parameter.setPhoneNumbers(Arrays.asList(smsNotification.getPhoneNumber()));
        parameter.setTemplateCode(smsNotification.getTemplateCode());

        if(StringUtils.isEmpty(smsNotification.getSignName())){
            smsNotification.setSignName("husky");
        }

        Assert.notNull(smsNotification.getSignName(),"短信签名不能为空");

        parameter.setSignName(smsNotification.getSignName());
        if (smsNotification.getParams() != null) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                parameter.setParams(mapper.writeValueAsString(smsNotification.getParams()));
            } catch (JsonProcessingException e) {
                throw new RuntimeException("格式化短信参数失败");
            }
        }
        return smsSender.send(parameter);
    }

    private void emailNotification(Object notification){
        EmailNotification emailNotification = (EmailNotification)notification;
        MailSenderParams params = new MailSenderParams();
        params.setMailTo(emailNotification.getReceiver());
        params.setContent(emailNotification.getContent());
        params.setTitle(emailNotification.getTitle());

        sender.sendSimpleMail(params);
    }
}
