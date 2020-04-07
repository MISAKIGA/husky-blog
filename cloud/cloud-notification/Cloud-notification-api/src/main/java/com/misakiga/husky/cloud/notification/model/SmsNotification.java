package com.misakiga.husky.cloud.notification.model;

import lombok.Data;

import java.util.Map;

/**
 * @author MISAKIGA
 */
@Data
public class SmsNotification extends Notification {

    private String phoneNumber;

    private String templateCode;

    private Map<String,Object> params;

    private String signName;
}
