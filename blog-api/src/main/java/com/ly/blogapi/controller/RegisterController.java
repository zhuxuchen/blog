package com.ly.blogapi.controller;

import com.ly.blogapi.service.LoginService;
import com.ly.blogapi.vo.Result;
import com.ly.blogapi.vo.params.LoginParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *     注册控制器
 * </p>
 *
 * @author zhuxuchen
 * @since 2023-03-07
 */
@RestController
@RequestMapping("/register")
public class RegisterController {

    @Resource
    private LoginService loginService;

    @PostMapping
    public Result register(@RequestBody LoginParam loginParam) {
        return loginService.register(loginParam);
    }
}
