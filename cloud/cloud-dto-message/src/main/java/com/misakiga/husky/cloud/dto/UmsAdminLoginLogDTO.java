package com.misakiga.husky.cloud.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UmsAdminLoginLogDTO implements Serializable {


    private static final long serialVersionUID = -4798414052038455596L;

    private Long id;
    private Long adminId;
    private Date createTime;
    private String ip;
    private String address;

    /**
     * 浏览器登录类型
     */
    private String userAgent;

}
