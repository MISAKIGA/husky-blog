package com.misakiga.husky.business.integration.authenticator.sms;

import com.misakiga.husky.business.feign.UmsMemberClient;
import com.misakiga.husky.business.integration.IntegrationAuthentication;
import com.misakiga.husky.business.integration.authenticator.AbstractPreparableIntegrationAuthenticator;
import com.misakiga.husky.business.integration.authenticator.sms.event.SmsAuthenticateBeforeEvent;
import com.misakiga.husky.business.integration.authenticator.sms.event.SmsAuthenticateSuccessEvent;
import com.misakiga.husky.uc.model.SysUserAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

/**
 * 短信验证码集成认证
 *
 * @author MISAKIGA
 */
public class SmsIntegrationAuthenticator extends AbstractPreparableIntegrationAuthenticator implements ApplicationEventPublisherAware {

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private UmsMemberClient umsMemberClient;

    private ApplicationEventPublisher applicationEventPublisher;

    private final static String SMS_AUTH_TYPE = "sms";

    @Override
    public SysUserAuthentication authenticate(IntegrationAuthentication integrationAuthentication) {

        //获取密码，实际值是验证码
        String password = integrationAuthentication.getAuthParameter("password");

        //获取用户名，实际值手机号
        String username = integrationAuthentication.getUsername();

        //发布事件，可以监听事件进行自动注册用户
        this.applicationEventPublisher.publishEvent(new SmsAuthenticateBeforeEvent(integrationAuthentication));

        SysUserAuthentication sysUserAuthentication = umsMemberClient.findUserByPhoneNumber(username);

        if(sysUserAuthentication != null){
            //将密码设置成验证码
            sysUserAuthentication.setPassword(passwordEncoder.encode(password));
            //发布事件，可以监听事件进行
            this.applicationEventPublisher.publishEvent(new SmsAuthenticateSuccessEvent(integrationAuthentication));

        }
        return sysUserAuthentication;
    }

    @Override
    public void prepare(IntegrationAuthentication integrationAuthentication) {

        String smsToken = integrationAuthentication.getAuthParameter("sms_token");
        String smsCode = integrationAuthentication.getAuthParameter("password");
        String username = integrationAuthentication.getAuthParameter("username");

    }

    @Override
    public boolean support(IntegrationAuthentication integrationAuthentication) {
        return SMS_AUTH_TYPE.equals(integrationAuthentication.getAuthType());
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
}
