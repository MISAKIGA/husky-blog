package com.misakiga.husky.cloud.controller;
import com.misakiga.husky.cloud.api.VerificationCodeService;
import com.misakiga.husky.cloud.feign.NotificationMessageClient;
import com.misakiga.husky.cloud.notification.model.EmailNotification;
import com.misakiga.husky.comm.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author MISAKIGA
 */
@RestController
@RequestMapping("vcc")
public class VerificationCodeController extends BaseController {

    @Autowired
    private VerificationCodeService vcService;

    @Resource
    private NotificationMessageClient notificationMessageClient;

    @GetMapping("/sms/{phoneNumber}")
    public void sendSmsNotification(@PathVariable String phone){

    }

    @GetMapping(value = "/email/{receiver}")
    public void sendEmailNotification(@PathVariable String receiver){
  /*      EmailNotification emailNotification = new EmailNotification();
        emailNotification.setTitle("GIAO");
        emailNotification.setContent("giao");
        emailNotification.setReceiver(receiver);*/

        String token = vcService.getToken(6, 60000L, "email", "2595903671@qq.com");
        vcService.sendEmail(token,receiver,"嘿嘿嘿,验证码：{code} 。。。");
        //notificationMessageClient.sendEmailNotification(emailNotification);
    }
}
