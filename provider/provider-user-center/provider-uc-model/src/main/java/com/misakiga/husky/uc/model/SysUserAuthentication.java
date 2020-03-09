package com.misakiga.husky.uc.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 认证主体
 * @author MISAKIGA
 */
@Data
public class SysUserAuthentication implements Serializable {

    private static final long serialVersionUID = 3081524808793943440L;

    private Long id;

    private String username;

    private String password;

    private String email;

    private String phoneNumber;

    private String status;

    private String name;

    private String type;
}
