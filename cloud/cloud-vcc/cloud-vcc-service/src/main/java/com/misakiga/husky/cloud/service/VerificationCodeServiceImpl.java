package com.misakiga.husky.cloud.service;

import com.misakiga.husky.cloud.api.VerificationCodeService;
import com.misakiga.husky.cloud.cache.VerificationCodeCache;
import com.misakiga.husky.cloud.feign.NotificationMessageClient;
import com.misakiga.husky.cloud.notification.feign.NotificationClient;
import com.misakiga.husky.cloud.notification.model.EmailNotification;
import com.misakiga.husky.cloud.notification.model.SmsNotification;
import com.misakiga.husky.cloud.utils.VerificationCodeUtils;
import com.misakiga.husky.commons.utils.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 验证码生成器
 * @author MISAKIGA
 */
@Service
@EnableCaching
@CacheConfig(cacheNames = {VerificationCodeService.VERIFICATION_CODE_CACHE_NAME,
                           VerificationCodeService.VERIFICATION_CODE_SMS_SEND_CACHE_NAME,
                           VerificationCodeService.VERIFICATION_CODE_EMAIL_SEND_CACHE_NAME})
public class VerificationCodeServiceImpl implements VerificationCodeService {

    @Resource
    private VerificationCodeCache verificationCodeCache;

    @Resource
    private NotificationMessageClient notificationMessageClient;
   // private NotificationClient notificationClient;

    /**
     * 默认验证码长度
     */
    @Value("${husky.vc.size:4}")
    private Integer verificationCodeSize;

    /**
     * 发送验证码短信模板
     */
    @Value("${husky.vc.sms.template-code:}")
    private String templateCode;

    /**
     * 验证码参数名称
     */
    @Value("${husky.vc.sms.code-param-name:}")
    private String codeParamName;

    /**
     * 验证码参数名称
     */
    @Value("${husky.vc.sms.sign-name:}")
    private String signName;

    /**
     * 默认验证码失效时间
     */
    @Value("${husky.vc.expire:300000}")
    private Long verificationCodeExpireTime;

    /**
     * 短信验证码发送间隔,默认为1分钟
     */
    @Value("${husky.vc.sms.interval:60000}")
    private Long smsSendInterval;

    /**
     * 数字验证码
     */
    private static final String NUMBER_VERIFICATION_CODE = "0";

    /**
     * 数字英文混合验证码
     */
    private static final String MIXED_VERIFICATION_CODE = "1";

    /**
     * 发送邮箱验证码
     * @param token
     * @param receiver
     * @param content content 内包含 {code} ，可以识别并在此存储自动生成的验证码，否则会把验证码加到content内容后面
     */
    @Override
    public void sendEmail(String token, String receiver, String content){

        token = receiver + "@" + token;
        //验证是否已经过期
        boolean isExpired = this.verificationCodeCache.isExpire(VERIFICATION_CODE_CACHE_NAME,token);
        if (isExpired) {
            this.evictCache(token);
        }
        Assert.isTrue(!isExpired, "验证码已过期");

        //判断邮箱验证码的发送间隔
        validateSendInterval(token,VERIFICATION_CODE_EMAIL_SEND_CACHE_NAME);

        EmailNotification emailNotification = new EmailNotification();
        emailNotification.setReceiver(receiver);
        emailNotification.setTitle("验证码");
        String value = this.getCode(token);
        if(StringUtils.containsIgnoreCase(content,"{code}")){
            HashMap<Object, Object> map = new HashMap<>();
            map.put("code",value);
            content = StringUtils.format(content,map);
        }else{
            content = StringUtils.addSuffixIfNot(content,value);
        }
        emailNotification.setContent(content);
        notificationMessageClient.sendEmailNotification(emailNotification);

        setSendTimeStamp(token,VERIFICATION_CODE_EMAIL_SEND_CACHE_NAME);
    }

    /**
     * 设置短信发送的时间戳
     * @param token
     */
    private void setSendTimeStamp(String token,String sendCacheName){
        this.verificationCodeCache.set(sendCacheName,token,String.valueOf(System.currentTimeMillis()),smsSendInterval);
    }
    /**
     * 验证短信发送的间隔
     * @param token
     */
    private void validateSendInterval(String token,String sendCacheName){
        String timeStamp = this.verificationCodeCache.get(sendCacheName,token);
        Assert.isTrue(StringUtils.isEmpty(timeStamp),"短信发送间隔时间太短，请稍后再试");
    }

    @Override
    public void sendSms(String token, String phoneNumber) {
        this.sendSms(token, this.templateCode, this.signName, phoneNumber, this.codeParamName, null);
    }

    @Override
    public void sendSms(String token, String templateCode, String signName, String phoneNumber, String codeParamName, Map<String, Object> params) {
        Assert.isTrue(StringUtils.isNotEmpty(templateCode), "短信模板没有配置");
        Assert.isTrue(StringUtils.isNotEmpty(signName), "短信模板没有没有配置");
        Assert.isTrue(StringUtils.isNotEmpty(codeParamName), "验证码参数不能为空");
        if (params == null) {
            params = new HashMap<>();
        }

        token = phoneNumber + "@" + token;

        //验证是否已经过期
        boolean isExpired = this.verificationCodeCache.isExpire(VERIFICATION_CODE_CACHE_NAME,token);
        if (isExpired) {
            this.evictCache(token);
        }
        Assert.isTrue(!isExpired, "验证码已过期");

        //判断短信验证码的发送间隔
        validateSendInterval(token,VERIFICATION_CODE_SMS_SEND_CACHE_NAME);

        //通过通知中心发送通知
        SmsNotification smsNotification = new SmsNotification();
        smsNotification.setPhoneNumber(phoneNumber);
        smsNotification.setSignName(signName);
        smsNotification.setTemplateCode(templateCode);
        String value = this.getCode(token);
        params.put(codeParamName, value);
        smsNotification.setParams(params);
        notificationMessageClient.sendSmsNotification(smsNotification);
        setSendTimeStamp(token,VERIFICATION_CODE_SMS_SEND_CACHE_NAME);
    }

    @Override
    public String getToken(Integer size, Long expire, String type,String subject){
        if(size == null) {
            size = this.verificationCodeSize;
        }
        if(expire == null){
            expire = this.verificationCodeExpireTime;
        }
        String code = "";
        if(NUMBER_VERIFICATION_CODE.equals(type)){
            code = VerificationCodeUtils.generateNumberVerifyCode(size);
        }else{
            code = VerificationCodeUtils.generateVerifyCode(size);
        }
        String token = UUID.randomUUID().toString().replaceAll("-","");
        String realToken = token;
        if(StringUtils.isNotEmpty(subject)){
            realToken = subject + "@" + token;
        }
        this.putCache(realToken, code,expire);
        return token;
    }


    @Override
    public void refresh(String token){
        this.refresh(token,verificationCodeExpireTime);
    }

    @Override
    public void refresh(String token,long expire){
        String code = this.getCode(token);
        this.updateCache(token,code,expire);
    }


    @Override
    public void renderImage(String token, OutputStream outputStream) throws IOException {
        // 生成随机字串
        String value = this.getCode(token);
        // 生成图片
        int w = 100, h = 30;
        VerificationCodeUtils.outputImage(w, h, outputStream, value);
    }

    @Override
    public boolean validate(String token, String code,String subject) {
        return this.validate(token, code, subject,true);
    }

    @Override
    public boolean validate(String token, String code, String subject,boolean ignoreCase) {
        if(StringUtils.isEmpty(code)){
            return false;
        }
        if(StringUtils.isNotEmpty(subject)){
            token = subject + "@" + token;
        }
        String value = this.verificationCodeCache.get(VERIFICATION_CODE_CACHE_NAME,token);

        if(StringUtils.isEmpty(value)){
            return false;
        }

        return ignoreCase ? code.equalsIgnoreCase(value) : code.equals(value);
    }

    public String getCode(String token){
        return this.verificationCodeCache.get(VERIFICATION_CODE_CACHE_NAME,token);
    }

    /**
     * 放入缓存
     * @param token
     * @param code
     */
    private void putCache(String token,String code,long expire){
        verificationCodeCache.set(VERIFICATION_CODE_CACHE_NAME,token,code,expire);
    }

    /**
     * 删除缓存
     * @param token
     */
    private void evictCache(String token){
        verificationCodeCache.remove(VERIFICATION_CODE_CACHE_NAME,token);
    }

    /**
     * 更新缓存
     * @param token
     * @param code
     * @param expire
     */
    private void updateCache(String token,String code,long expire){
        this.evictCache(token);
        verificationCodeCache.set(VERIFICATION_CODE_CACHE_NAME,token,code,expire);
    }
}
