package com.ly.blogapi.controller;

import com.ly.blogapi.service.SysUserService;
import com.ly.blogapi.vo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *     用户控制器
 * </p>
 *
 * @author zhuxuchen
 * @since 2023-03-07
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Resource
    private SysUserService sysUserService;

    @GetMapping("/currentUser")
    public Result currentUser(@RequestHeader ("Authorization") String token) {
        return sysUserService.findUserByToken(token);
    }
}
