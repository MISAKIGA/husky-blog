package com.misakiga.husky.interceptor;


import feign.Request;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.Charset;
import java.util.Enumeration;

/**
 * @author MISAKIGA
 */
public class FeignRequestInterceptor implements RequestInterceptor {

    /**
     * 获取将带有令牌的请求信息
     * 封装请求头，请求体
     * @param requestTemplate
     */
    @Override
    public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();

        //遍历请求头
        Enumeration<String> headerNames = request.getHeaderNames();
        if (headerNames != null) {
            while (headerNames.hasMoreElements()) {
                String name = headerNames.nextElement();
                String value = request.getHeader(name);
                requestTemplate.header(name,value);
            }
        }

        //遍历请求体
        Enumeration<String> parameterNames = request.getParameterNames();
        StringBuilder body = new StringBuilder();
        if (parameterNames != null) {
            while (parameterNames.hasMoreElements()) {
                String name = parameterNames.nextElement();
                String value = request.getParameter(name);

                //将令牌放入Header
                if("access_token".equals(name)){
                    requestTemplate.header("authorization","Bearer" + value);
                }

                body.append(name).append("=").append(value).append("&");
            }
        }

        if (body.length() > 0) {
            body.deleteCharAt(body.length() - 1);
            requestTemplate.body(Request.Body.bodyTemplate("", Charset.defaultCharset()));
        }
    }
}
