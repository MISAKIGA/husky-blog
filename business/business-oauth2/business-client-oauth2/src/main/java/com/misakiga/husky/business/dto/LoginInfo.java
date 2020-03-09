package com.misakiga.husky.business.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 登录信息
 * @author MISAKIGA
 */
@Data
public class LoginInfo implements Serializable {

    private static final long serialVersionUID = 9079371246303024723L;
    private String name;
    private String avatar;
}
