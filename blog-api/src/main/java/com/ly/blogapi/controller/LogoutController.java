package com.ly.blogapi.controller;

import com.ly.blogapi.service.LoginService;
import com.ly.blogapi.vo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *     登出控制器
 * </p>
 *
 * @author zhuxuchen
 * @since 2023-03-07
 */
@RestController
@RequestMapping("/logout")
public class LogoutController {

    @Resource
    private LoginService loginService;

    @GetMapping
    public Result logout(@RequestHeader("Authorization") String token) {
        return loginService.logout(token);
    }
}
