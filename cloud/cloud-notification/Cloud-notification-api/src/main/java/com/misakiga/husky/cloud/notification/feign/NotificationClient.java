package com.misakiga.husky.cloud.notification.feign;

import com.misakiga.husky.cloud.notification.model.EmailNotification;
import com.misakiga.husky.cloud.notification.model.Notification;
import com.misakiga.husky.cloud.notification.model.SmsNotification;
import com.misakiga.husky.comm.dto.ResponseResult;
import com.misakiga.husky.configuration.FeignRequestConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author MISAKIGA
 */
@FeignClient(value = "cloud-notification",path = "notification",configuration = {FeignRequestConfiguration.class})
public interface NotificationClient {

    /**
     * 发送短信通知
     * @param smsNotification {@link SmsNotification}
     * @return {@link ResponseResult}
     */
    @PostMapping("/send/sms")
    ResponseResult<String> sendSms(@RequestBody SmsNotification smsNotification);

    /**
     * 发送邮箱通知
     * @param emailNotification {@link EmailNotification}
     * @return {@link ResponseResult}
     */
    @PostMapping("/send/email")
    ResponseResult<String> sendEmail(@RequestBody EmailNotification emailNotification);

}
