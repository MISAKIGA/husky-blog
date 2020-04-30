package com.misakiga.husky.cloud.notification.model;

import lombok.Data;

/**
 * @author MISAKIGA
 */
@Data
public class EmailNotification extends Notification{
    private static final long serialVersionUID = 5655540941187637923L;
    private String receiver;

    private String title;

    private String content;

}
