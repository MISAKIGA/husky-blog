package com.misakiga.husky.business.feign.fallback;
import com.misakiga.husky.business.BusinessStatus;
import com.misakiga.husky.business.dto.UmsAdminDTO;
import com.misakiga.husky.business.dto.params.IconParam;
import com.misakiga.husky.business.dto.params.PasswordParam;
import com.misakiga.husky.business.dto.params.ProfileParam;
import com.misakiga.husky.business.feign.ProfileFeign;
import com.misakiga.husky.commons.dto.ResponseResult;
import com.misakiga.husky.commons.utils.MapperUtils;
import org.springframework.stereotype.Component;

/**
 * 个人信息服务熔点器
 * @author MISAKIGA
 */
@Component
public class ProfileFeignFallback implements ProfileFeign {

    public static final String BREAKING_MESSAGE = "您的网络有问题，请检查";

    @Override
    public String findUserByUsername(String username) {
        UmsAdminDTO dto = new UmsAdminDTO();
        dto.setEmail("service@funtl.com");

        try {
            return MapperUtils.obj2json(new ResponseResult<UmsAdminDTO>(BusinessStatus.BREAKING.getCode(), BREAKING_MESSAGE));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String info(String username) {
        UmsAdminDTO dto = new UmsAdminDTO();
        dto.setEmail("service@funtl.com");

        try {
            return MapperUtils.obj2json(new ResponseResult<UmsAdminDTO>(BusinessStatus.BREAKING.getCode(), BREAKING_MESSAGE));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String update(ProfileParam profileParamd) {
        return null;
    }

    @Override
    public String modifyPassword(PasswordParam passwordParam) {
        return null;
    }

    @Override
    public String modifyIcon(IconParam iconParam) {
        return null;
    }

}

