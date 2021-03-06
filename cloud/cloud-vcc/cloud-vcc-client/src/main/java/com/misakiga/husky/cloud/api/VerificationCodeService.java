package com.misakiga.husky.cloud.api;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

/**
 * 验证码服务接口
 */
public interface VerificationCodeService {

    /**
     * 验证码缓存名称
     */
    String VERIFICATION_CODE_CACHE_NAME = "verificationCodeCache";
    /**
     * 验证码短信发送记录缓存名称
     */
    String VERIFICATION_CODE_SMS_SEND_CACHE_NAME = "verificationCodeSmsSendCache";

    /**
     * 验证码邮箱发送记录缓存
     */
    String VERIFICATION_CODE_EMAIL_SEND_CACHE_NAME = "verificationCodeEmailSendCache";
    /**
     * 验证码和手机号缓存
     */
    String VERIFICATION_CODE_PHONE_NUMBER_CACHE_NAME = "verificationPhoneNumberCodeCache";

    /**
     * 发送邮箱验证码
     * @param token
     * @param receiver
     * @param content content 内包含 {code} ，可以识别并在此存储自动生成的验证码，否则会把验证码加到content内容后面
     */
    void sendEmail(String token, String receiver, String content);

    /**
     * 发送短信验证码
     * @param token 验证码TOKEN
     * @param phoneNumber 手机号码
     * 短信模板配置项：husky.vc.sms.templateCode<br/>
     * 短信签名配置项：husky.vc.sms.signName<br/>
     * 验证码参数名配置项：husky.vc.sms.codeParamName<br/>
     * 可以对上述三个配置项进行配置全局的发送短信参数
     */
    void sendSms(String token, String phoneNumber);

    /**
     * 发送短信验证码
     * @param token 验证码TOKEN
     * @param templateCode 短信模板
     * @param signName 短信签名
     * @param phoneNumber 手机号码
     * @param codeParamName 短信验证码参数名
     * @param params 短信参数
     */
    void sendSms(String token, String templateCode, String signName, String phoneNumber, String codeParamName, Map<String, Object> params);

    /**
     * 渲染验证码图片
     * @param token
     * @param outputStream
     * @throws IOException
     */
    void renderImage(String token, OutputStream outputStream) throws IOException;

    /**
     * 判断验证码是否匹配，默认忽略大小写
     * @param token 验证码TOKEN
     * @param code 验证码值
     * @return
     */
    boolean validate(String token, String code, String subject);

    /**
     * 判断验证是否匹配
     * @param token 验证码TOKEN
     * @param code 验证码值
     * @param ignoreCase 忽略大小写
     * @return
     */
    boolean validate(String token, String code, String subject, boolean ignoreCase);

    /**
     * 获取验证码token
     * @param size  验证码长度
     * @param expire 过期时间
     * @param type 类型 0：数字验证码 1：混合验证码
     * @return
     * 验证码长度和过期时间采用默认配置
     * 验证码长度配置项：husky.vc.size 默认值：4<br/>
     * 验证码过期时间配置项：husky.vc.expire 默认值：180000(3分钟)
     */
    String getToken(Integer size, Long expire, String type, String subject);

    /**
     * 刷新验证码有效期
     * 验证码过期时间配置项：husky.vc.expire 默认值：180000(3分钟)
     * @param token 验证码token
     */
    void refresh(String token);

    /**
     * 刷新验证码
     * @param token 验证码token
     * @param expire 验证码过期时间
     */
    void refresh(String token, long expire);
}
