package com.misakiga.husky.cloud.feign;

import com.misakiga.husky.cloud.notification.model.EmailNotification;
import com.misakiga.husky.cloud.notification.model.SmsNotification;
import com.misakiga.husky.comm.dto.ResponseResult;
import com.misakiga.husky.configuration.FeignRequestConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author MISAKIGA
 */
@FeignClient(value = "cloud-message",path = "message",configuration =  FeignRequestConfiguration.class)
public interface NotificationMessageClient {

    @PostMapping("/email/notification")
    ResponseResult<String> sendEmailNotification(@RequestBody EmailNotification emailNotification);

    @PostMapping("/sms/notification")
    ResponseResult<String> sendSmsNotification(@RequestBody SmsNotification smsNotification);
}