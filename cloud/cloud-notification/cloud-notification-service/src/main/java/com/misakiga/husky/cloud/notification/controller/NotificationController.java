package com.misakiga.husky.cloud.notification.controller;

import com.misakiga.husky.cloud.notification.consumer.NotificationDispatcher;
import com.misakiga.husky.cloud.notification.model.EmailNotification;
import com.misakiga.husky.cloud.notification.model.SmsNotification;
import com.misakiga.husky.comm.dto.ResponseResult;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 通知中心
 * @author MISAKIGA
 */
@RestController()
@RequestMapping("notification")
public class NotificationController {

    @Reference(version = "1.0.0")
    private NotificationDispatcher dispatcher;

    @PostMapping("/send/sms")
    public ResponseResult<String> sendSms(SmsNotification smsNotification){

        this.dispatcher.dispatch(smsNotification);
        return new ResponseResult<>();
    }

    @PostMapping("/send/email")
    public ResponseResult<String> sendEmail(EmailNotification emailNotification){

        this.dispatcher.dispatch(emailNotification);
        return new ResponseResult<>();
    }
}
