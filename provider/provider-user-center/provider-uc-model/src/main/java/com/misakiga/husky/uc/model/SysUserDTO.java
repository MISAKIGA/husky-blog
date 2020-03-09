package com.misakiga.husky.uc.model;

import lombok.Data;

/**
 * @author MISAKIGA
 */
@Data
public class SysUserDTO {

    private Long id;
    /**
     * 登录账号
     */
    private String username;
    /**
     * 名称
     */
    private String name;
    /**
     * 电话号码
     */
    private String phoneNumber;
    /**
     * 电子邮箱
     */
    private String email;
    /**
     * 用户类型
     */
    private String type;
}
