package com.misakiga.husky.business.tests;

import com.google.common.collect.Maps;
import com.misakiga.husky.business.OAuthBootstrap;
import com.misakiga.husky.commons.utils.MapperUtils;
import com.misakiga.husky.commons.utils.OkHttpClientUtil;
import okhttp3.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;
import java.util.Objects;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = OAuthBootstrap.class)
public class AdminLoginTest {

    @Test
    public void getLoginUser(){
        //通过HTTP客户端请求登录接口
        Map<String,String> params = Maps.newHashMap();
        params.put("username","admin");
        params.put("password","123456");
        params.put("grant_type","password");
        params.put("client_id","client");
        params.put("client_secret","secret");
        params.put("auth_type","password");

        try {
            //解析响应结果封装并返回
            Response response = OkHttpClientUtil.getInstance().postData("http://localhost:9001/oauth/token", params);

            String jsonString = Objects.requireNonNull(response.body()).string();
            Map<String,Object> jsonMap = MapperUtils.json2map(jsonString);
            String token = String.valueOf(jsonMap.get("access_token"));
            System.out.println(token);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
