package com.misakiga.husky.starter.sms.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author MISAKIGA
 */
@Configuration
@ConfigurationProperties(prefix = "husky.sms.aliyun")
public class AliyunSmsProperties {

    private String accessKeyId ;
    private String accessKeySecret;
    public String getAccessKeyId() {
        return accessKeyId;
    }
    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }
    public String getAccessKeySecret() {
        return accessKeySecret;
    }
    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

}
