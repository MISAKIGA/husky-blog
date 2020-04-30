package com.misakiga.husky.cloud.notification.model;

import lombok.Data;

import java.util.Map;

/**
 * @author MISAKIGA
 */
@Data
public class SmsNotification extends Notification {
    private static final long serialVersionUID = 5655540941187637923L;
    private String phoneNumber;

    private String templateCode;

    private Map<String,Object> params;

    private String signName;
}
