package com.misakiga.husky.cloud.service;

import com.misakiga.husky.cloud.api.VerificationCodeService;
import org.springframework.cache.annotation.CacheConfig;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

/**
 * @author MISAKIGA
 */
@CacheConfig(cacheNames = {VerificationCodeService.VERIFICATION_CODE_CACHE_NAME,
    VerificationCodeService.VERIFICATION_CODE_SMS_SEND_CACHE_NAME})
public class VerificationCodeServiceImpl implements VerificationCodeService {
    @Override
    public void sendSms(String token, String phoneNumber) {

    }

    @Override
    public void sendSms(String token, String templateCode, String signName, String phoneNumber, String codeParamName, Map<String, Object> params) {

    }

    @Override
    public void renderImage(String token, OutputStream outputStream) throws IOException {

    }

    @Override
    public boolean validate(String token, String code, String phoneNumber) {
        return false;
    }

    @Override
    public boolean validate(String token, String code, String phoneNumber, boolean ignoreCase) {
        return false;
    }

    @Override
    public String getToken(Integer size, Long expire, String type, String subject) {
        return null;
    }

    @Override
    public void refresh(String token) {

    }

    @Override
    public void refresh(String token, long expire) {

    }
}
