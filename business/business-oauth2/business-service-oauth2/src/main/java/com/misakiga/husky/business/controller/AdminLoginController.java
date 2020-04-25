package com.misakiga.husky.business.controller;

import com.google.common.collect.Maps;
import com.misakiga.husky.business.dto.LoginInfo;
import com.misakiga.husky.business.dto.LoginParam;
import com.misakiga.husky.business.feign.ProfileFeign;
import com.misakiga.husky.business.feign.SysAuthorizeClient;
import com.misakiga.husky.cloud.api.MessageService;
import com.misakiga.husky.cloud.dto.UmsAdminLoginLogDTO;
import com.misakiga.husky.comm.base.BaseController;
import com.misakiga.husky.comm.business.BusinessException;
import com.misakiga.husky.comm.business.BusinessStatus;
import com.misakiga.husky.comm.dto.ResponseResult;
import com.misakiga.husky.commons.utils.MapperUtils;
import com.misakiga.husky.commons.utils.OkHttpClientUtil;
import com.misakiga.husky.commons.utils.UserAgentUtils;
import com.misakiga.husky.provider.api.UmsAdminService;
import com.misakiga.husky.provider.domain.UmsAdmin;
import okhttp3.Response;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;
import java.util.Objects;


/**
 * 管理员登录
 * @author MISAKIGA
 */
@RestController()
@RequestMapping("admin")
public class AdminLoginController extends BaseController {

    private static final String URL_OAUTH_TOKEN ="http://localhost:9001/oauth/token";

    @Value("${business.oauth2.grant_type}")
    public String oauth2GrantType;

    @Value("${business.oauth2.client_id}")
    public String oauth2ClientId;

    @Value("${business.oauth2.client_secret}")
    public String oauth2ClientSecret;

    @Resource(name = "userDetailsServiceBean")
    public UserDetailsService userDetailsService;

    public final String authType = "password";

    @Resource
    public BCryptPasswordEncoder passwordEncoder;

    @Resource
    public TokenStore tokenStore;

    @Resource
    private ProfileFeign profileFeign;

    @Resource
    private SysAuthorizeClient sysAuthorizeClient;

    @Reference(version = "1.0.0")
    private UmsAdminService umsAdminService;

    @Reference(version = "1.0.0")
    private MessageService messageService;

    @PostMapping(value = "/login")
    public ResponseResult<Map<String,Object>> login(@RequestBody LoginParam loginParam, HttpServletRequest request){

        //封装返回结果
        Map<String,Object> result = Maps.newHashMap();

        //验证账号密码
       // UserDetails userDetails = userDetailsService.loadUserByUsername(loginParam.getUsername());

        //通过HTTP客户端请求登录接口
        Map<String,String> params = Maps.newHashMap();
        params.put("username",loginParam.getUsername());
        params.put("password",loginParam.getPassword());
        params.put("grant_type",oauth2GrantType);
        params.put("client_id",oauth2ClientId);
        params.put("client_secret",oauth2ClientSecret);
        params.put("auth_type",authType);

        String jsonString = null;

        try {
            //解析响应结果封装并返回
            Response response = OkHttpClientUtil.getInstance().postData(URL_OAUTH_TOKEN, params);

            jsonString = Objects.requireNonNull(response.body()).string();
            Map<String,Object> jsonMap = MapperUtils.json2map(jsonString);
            String token = String.valueOf(jsonMap.get("access_" +
                    "token"));
            String error = String.valueOf(jsonMap.get("error"));

            if(!StringUtils.isEmpty(error) && "null".equals(token)){

                result.put("error",jsonString);
                throw new BusinessException(BusinessStatus.ADMIN_PASSWORD);
            }

            result.put("token",token);
        } catch (Exception e) {
            e.printStackTrace();
            return this.failure("登录失败",result);
        }

        //发送管理员登录日志
        sendAdminLoginLog(loginParam.getUsername(),request);
        return this.success("登录成功",result);
    }

    @GetMapping(value = "/info")
    public ResponseResult<LoginInfo> info() throws Exception {

        //获取用户认证信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String jsonString = profileFeign.info(authentication.getName());


        UmsAdmin umsAdmin = MapperUtils.json2pojoByTree(jsonString, "data", UmsAdmin.class);

        //熔断之后的结果
        if(umsAdmin.getUsername() == null){
            return MapperUtils.json2pojo(jsonString, ResponseResult.class);
        }

        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setName(umsAdmin.getUsername());
        loginInfo.setAvatar(umsAdmin.getIcon());

        return this.success("获取用户信息",loginInfo);
    }


    @DeleteMapping(value = "/logout")
    public ResponseResult<LoginInfo> logout(HttpServletRequest request){
        String token = request.getParameter("access_token");
        OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(token);
        tokenStore.removeAccessToken(oAuth2AccessToken);
        return this.success("用户注销");
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
