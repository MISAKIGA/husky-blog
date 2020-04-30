package com.misakiga.husky.cloud.notification.controller;

import com.misakiga.husky.cloud.notification.consumer.NotificationDispatcher;
import com.misakiga.husky.cloud.notification.model.EmailNotification;
import com.misakiga.husky.cloud.notification.model.SmsNotification;
import com.misakiga.husky.comm.dto.ResponseResult;
import org.springframework.cloud.commons.util.IdUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 通知中心
 * @author MISAKIGA
 */
@RestController()
@RequestMapping("notification")
public class NotificationController {

  //  @Reference(version = "1.0.0")
    private NotificationDispatcher dispatcher;

    @PostMapping("/send/sms")
    public ResponseResult<String> sendSms(@RequestBody SmsNotification smsNotification){



        Map<String,Object> map = new HashMap<>(5);
        map.put("id", IdUtils.generate());
        map.put("type","email");
        map.put("data", smsNotification);
        this.dispatcher.dispatch(smsNotification);
        return new ResponseResult<>();
    }

    @PostMapping("/send/email")
    public ResponseResult<String> sendEmail(@RequestBody EmailNotification emailNotification){

        this.dispatcher.dispatch(emailNotification);
        return new ResponseResult<>();
    }
}
