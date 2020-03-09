package com.misakiga.husky.business.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author MISAKIGA
 */
@Data
public class LoginParam implements Serializable {

    private static final long serialVersionUID = 1758889378119943485L;
    private String username;
    private String password;
}
