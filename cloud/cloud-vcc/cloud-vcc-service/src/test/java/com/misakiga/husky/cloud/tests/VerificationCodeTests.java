package com.misakiga.husky.cloud.tests;

import com.misakiga.husky.cloud.VerificationCodeBootstrap;
import com.misakiga.husky.cloud.api.VerificationCodeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {VerificationCodeBootstrap.class})
public class VerificationCodeTests {

   // @Reference(version = "1.0.0")
    private VerificationCodeService verificationCodeService;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void testEmail(){
        String cacheName = "test";
        String key = "123";
        String value = "4567891";
        Long expire = 60000L;
        stringRedisTemplate.opsForValue().set(cacheName + ":" + key,value);
        stringRedisTemplate.expire(cacheName + ":" + key,expire, TimeUnit.MILLISECONDS);
        String token = verificationCodeService.getToken(5, 6000L, "0", "2595903671@qq.com");

        verificationCodeService.sendEmail(token,"2595903671@qq.com","验证码:{code},欢迎您!");
        boolean validate = verificationCodeService.validate(token, "987654", "2595903671@qq.com");
        System.out.println(token);
    }
}
