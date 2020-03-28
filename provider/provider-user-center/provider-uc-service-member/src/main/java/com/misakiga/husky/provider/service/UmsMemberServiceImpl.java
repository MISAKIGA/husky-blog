package com.misakiga.husky.provider.service;

import com.misakiga.husky.provider.domain.UmsMember;
import com.misakiga.husky.uc.model.SysUserAuthentication;
import com.misakiga.husky.uc.model.SysUserDTO;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.BeanUtils;

import javax.annotation.Resource;
import com.misakiga.husky.provider.mapper.UmsMemberMapper;
import com.misakiga.husky.provider.api.UmsMemberService;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author MISAKIGA
 */
@Service(version = "1.0.0")
public class UmsMemberServiceImpl implements UmsMemberService{

    @Resource
    private UmsMemberMapper umsMemberMapper;

    @Override
    public void update(SysUserDTO sysUserDTO) {

    }

    @Override
    public List<UmsMember> list() {
        return null;
    }

    @Override
    public void doResetPassword(Long id) {

    }

    @Override
    public UmsMember doDisable(Long id) {
        return null;
    }

    @Override
    public UmsMember doEnable(Long id) {
        return null;
    }

    @Override
    public UmsMember doLock(Long id) {
        return null;
    }

    @Override
    public UmsMember doUnlock(Long id) {
        return null;
    }

    @Override
    public void updatePassword(Long id, String oldPassword, String newPassword) {

    }

    @Override
    public SysUserAuthentication findUserByUsername(String username) {
        return null;
    }

    @Override
    public SysUserAuthentication findUserById(Long id) {
        return null;
    }

    @Override
    public SysUserAuthentication findUserByPhoneNumber(String phoneNumber) {

        Example example = new Example(UmsMember.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("phone",phoneNumber);
        UmsMember umsMember = this.umsMemberMapper.selectOneByExample(example);

        SysUserAuthentication sysUserAuthentication = new SysUserAuthentication();

        BeanUtils.copyProperties(umsMember,sysUserAuthentication);

        return sysUserAuthentication;
    }

    @Override
    public boolean deleteSysUser(String username) {
        return false;
    }

    @Override
    public void insert(SysUserDTO sysUserDTO) {

    }
}
