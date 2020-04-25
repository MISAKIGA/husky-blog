package com.misakiga.husky.comm.sms.Sender;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.misakiga.husky.comm.sms.SmsParameter;
import com.misakiga.husky.comm.sms.SmsSendResult;
import com.misakiga.husky.comm.sms.SmsSender;
import com.misakiga.husky.commons.utils.MapperUtils;
import com.misakiga.husky.commons.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * 阿里云短信发送器
 * @author MISAKIGA
 */
public class AliyunSmsSender implements SmsSender {

    private String accessKeyId;

    private String accessKeySecret;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public AliyunSmsSender(){
        logger.info("初始化阿里云接口:" + this);
    }

    @Override
    public SmsSendResult send(SmsParameter parameter) {
        // 短信API产品域名
        final String regionId = "cn-hangzhou";
        // 短信API产品名称
        final String domain = "dysmsapi.aliyuncs.com";

        final String version = "2017-05-25";

        final String action = "SendSms";

        DefaultProfile profile = DefaultProfile.getProfile(regionId,accessKeyId,accessKeySecret);
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain(domain);
        request.setSysVersion(version);
        request.setSysAction(action);
        request.putQueryParameter("RegionId",regionId);
        // 必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
        request.putQueryParameter("PhoneNumbers", StringUtils.join(parameter.getPhoneNumbers(),","));
        request.putQueryParameter("SignName", parameter.getSignName());
        request.putQueryParameter("TemplateCode", parameter.getTemplateCode());
        request.putQueryParameter("TemplateParam", parameter.getParams());


        SmsSendResult smsSendResult = new SmsSendResult();
        CommonResponse response;
        String responseData = null;
        Map<String,Object> responseResult = null;
        try {

             response = client.getCommonResponse(request);
             responseData = response.getData();

             Assert.notNull(responseData,"短信提供商服务器没有响应信息");

             responseResult = MapperUtils.json2map(responseData);
             smsSendResult.setData(responseResult);

        }
        catch (Exception e) {

            Map<String,Object> map = new HashMap<>(2);
            map.put("Message",e.getMessage());

            map.put("Code","SEND_SMS_FAILURE");
            smsSendResult.setData(map);

            throw new RuntimeException("发送短信发生错误：" + e);
        }

        if(response.getData() == null || !smsSendResult.isSuccess()){

            logger.error("发送短信失败:" + smsSendResult.getCode());
        }

        return smsSendResult;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }
}
