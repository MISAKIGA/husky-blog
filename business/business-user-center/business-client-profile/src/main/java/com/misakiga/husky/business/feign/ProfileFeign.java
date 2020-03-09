package com.misakiga.husky.business.feign;

import com.misakiga.husky.business.dto.params.IconParam;
import com.misakiga.husky.business.dto.params.PasswordParam;
import com.misakiga.husky.business.dto.params.ProfileParam;
import com.misakiga.husky.configuration.FeignRequestConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author MISAKIGA
 */
@FeignClient(value = "business-profile", path = "profile",configuration = FeignRequestConfiguration.class)
public interface ProfileFeign {

    /**
     * 获取个人信息
     * @param username
     * @return
     */
    @GetMapping(value = "info/{username}")
    String info(@PathVariable String username);

    /**
     * 更新个人信息
     * @param profileParamd
     * @return
     */
    @PostMapping(value = "update")
    String update(@RequestBody ProfileParam profileParamd);

    /**
     * 修改密码
     * @param passwordParam
     * @return
     */
    @PostMapping(value = "modify/password")
    String modifyPassword(@RequestBody PasswordParam passwordParam);

    /**
     * 修改头像
     * @param iconParam {@link IconParam}
     * @return
     */
    @PostMapping(value = "modify/password")
    String modifyIcon(@RequestBody IconParam iconParam);
}
