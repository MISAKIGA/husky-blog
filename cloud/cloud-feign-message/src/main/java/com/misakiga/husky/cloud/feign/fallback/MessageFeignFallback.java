package com.misakiga.husky.cloud.feign.fallback;

import com.misakiga.husky.cloud.feign.MessageFeign;
import org.springframework.stereotype.Component;

/**
 * 消息队列熔断
 * @author MISAKIGA
 */
@Component
public class MessageFeignFallback implements MessageFeign {

    private static final String BREAKING_MESSAGE = "请求失败，请检查您的网络";

}
