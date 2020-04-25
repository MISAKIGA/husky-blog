package com.misakiga.husky.comm.business;

/**
 * @author MISAKIGA
 */
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 4886352475592846841L;private Integer code;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public BusinessException() {

    }

    public BusinessException(BusinessStatus status) {
        this.code = status.getCode();
    }
}
