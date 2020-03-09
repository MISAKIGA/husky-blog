package com.misakiga.husky.business.controller;

import com.misakiga.husky.business.BusinessStatus;
import com.misakiga.husky.business.dto.LoginParam;
import com.misakiga.husky.commons.dto.ResponseResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 成员登录
 * @author MISAKIGA
 */
@RestController(value = "member")
public class MemberControlller {

    @PostMapping("/login")
    public ResponseResult<Void> memberLogin(@RequestBody LoginParam loginParam, HttpServletRequest request){



        return new ResponseResult<Void>(BusinessStatus.OK.getCode(),"登录成功");
    }

}
