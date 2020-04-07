package com.misakiga.husky.cloud.notification.model;

import lombok.Data;

/**
 * @author MISAKIGA
 */
@Data
public class EmailNotification extends Notification{

    private String receiver;

    private String title;

    private String content;

}
