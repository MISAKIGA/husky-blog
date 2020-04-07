package com.misakiga.husky.starter.sms;


import com.misakiga.husky.comm.sms.Sender.AliyunSmsSender;
import com.misakiga.husky.comm.sms.SmsSender;
import com.misakiga.husky.starter.sms.config.AliyunSmsProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({AliyunSmsProperties.class})
public class SmsAutoConfiguration {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AliyunSmsProperties aliyunSmsProperties;

    @Bean
    @ConditionalOnClass({ AliyunSmsSender.class })
    @ConditionalOnProperty(name = "husky.sms.type", havingValue = "aliyun")
    public SmsSender smsSender() {
        AliyunSmsSender sender = new AliyunSmsSender();
        BeanUtils.copyProperties(aliyunSmsProperties, sender);
        return sender;
    }
}

