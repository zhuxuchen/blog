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
 *     登录控制器
 * </p>
 *
 * @author zhuxuchen
 * @since 2023-03-05
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @Resource
    private LoginService loginService;

    /**
     * 登录
     * @return com.ly.blogapi.vo.Result
     */
    @PostMapping
    public Result login(@RequestBody LoginParam loginParam) {
        return loginService.login(loginParam);
    }
}
