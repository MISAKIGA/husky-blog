package com.misakiga.husky.business.controller;

import com.google.common.collect.Maps;
import com.misakiga.husky.business.BusinessException;
import com.misakiga.husky.business.BusinessStatus;
import com.misakiga.husky.business.dto.LoginParam;
import com.misakiga.husky.business.feign.ProfileFeign;
import com.misakiga.husky.cloud.api.MessageService;
import com.misakiga.husky.cloud.dto.UmsAdminLoginLogDTO;
import com.misakiga.husky.commons.dto.ResponseResult;
import com.misakiga.husky.commons.utils.MapperUtils;
import com.misakiga.husky.commons.utils.OkHttpClientUtil;
import com.misakiga.husky.commons.utils.UserAgentUtils;
import com.misakiga.husky.provider.api.UmsAdminService;
import com.misakiga.husky.provider.domain.UmsAdmin;
import okhttp3.Response;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

@RestController
public class LoginController {

    private static final String URL_OAUTH_TOKEN ="http://localhost:9001/oauth/token";

    @Value("${business.oauth2.grant_type}")
    public String oauth2GrantType;

    @Value("${business.oauth2.client_id}")
    public String oauth2ClientId;

    @Value("${business.oauth2.client_secret}")
    public String oauth2ClientSecret;

    @Resource(name = "userDetailsServiceBean")
    public UserDetailsService userDetailsService;

    @Resource
    public BCryptPasswordEncoder passwordEncoder;

    @Resource
    public TokenStore tokenStore;

    @Resource
    private ProfileFeign profileFeign;

    @Reference(version = "1.0.0")
    private UmsAdminService umsAdminService;

    @Reference(version = "1.0.0")
    private MessageService messageService;

    @PostMapping(value = "/user/login")
    public ResponseResult<Map<String,Object>> login(@RequestBody LoginParam loginParam, HttpServletRequest request){

        //封装返回结果
        Map<String,Object> result = Maps.newHashMap();

        //验证账号密码
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginParam.getUsername());

        if(userDetails == null || !passwordEncoder.matches(loginParam.getPassword(),userDetails.getPassword())){
            throw new BusinessException(BusinessStatus.ADMIN_PASSWORD);
        }


        //通过HTTP客户端请求登录接口
        Map<String,String> params = Maps.newHashMap();
        params.put("username",loginParam.getUsername());
        params.put("password",loginParam.getPassword());
        params.put("grant_type",oauth2GrantType);
        params.put("client_id",oauth2ClientId);
        params.put("client_secret",oauth2ClientSecret);

        try {
            //解析响应结果封装并返回
            Response response = OkHttpClientUtil.getInstance().postData(URL_OAUTH_TOKEN, params);

            String jsonString = Objects.requireNonNull(response.body()).string();
            Map<String,Object> jsonMap = MapperUtils.json2map(jsonString);
            String token = String.valueOf(jsonMap.get("access_token"));
            result.put("token",token);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //发送管理员登录日志
        sendAdminLoginLog(userDetails.getUsername(),request);
        return new ResponseResult<Map<String,Object>>(BusinessStatus.OK.getCode(), "登录成功",result);
    }

    private boolean sendAdminLoginLog(String username,HttpServletRequest request){

        UmsAdmin umsAdmin = umsAdminService.get(username);
        String ip = UserAgentUtils.getIpAddr(request);
        String address = UserAgentUtils.getIpInfo(ip).getCity();
        String userAgent = UserAgentUtils.getBrowser(request).getName();


        UmsAdminLoginLogDTO dto = new UmsAdminLoginLogDTO();
        dto.setAdminId(umsAdmin.getId());
        dto.setCreateTime(new Date());
        dto.setIp(ip);
        dto.setAddress(address);
        dto.setUserAgent(userAgent);


        return messageService.sendAdminLoginLog(dto);
    }

}
