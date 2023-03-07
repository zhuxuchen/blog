package com.ly.blogapi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ly.blogapi.entity.SysUser;
import com.ly.blogapi.vo.Result;

/**
* @author zhuxuchen
* @description 针对表【ms_sys_user】的数据库操作Service
* @createDate 2023-03-03 15:44:13
*/
public interface SysUserService extends IService<SysUser> {

    /**
     * 根据 token 查询用户信息
     * @param token
     * @return com.ly.blogapi.vo.Result
     */
    Result findUserByToken(String token);
}
