package com.ly.blogapi.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ly.blogapi.entity.SysUser;
import com.ly.blogapi.mapper.SysUserMapper;
import com.ly.blogapi.service.LoginService;
import com.ly.blogapi.service.SysUserService;
import com.ly.blogapi.vo.ErrorCode;
import com.ly.blogapi.vo.LoginUserVo;
import com.ly.blogapi.vo.Result;
import com.ly.blogapi.vo.UserVo;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author zhuxuchen
* @description 针对表【ms_sys_user】的数据库操作Service实现
* @createDate 2023-03-03 15:44:13
*/
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser>
    implements SysUserService{

    @Resource
    @Lazy
    private LoginService loginService;

    @Override
    public Result findUserByToken(String token) {
        /*
         * 1、token合法性校验：是否为空，解析是否成功，在redis中是否存在
         * 2、如果校验失败，返回错误
         * 3、如果成功，返回对应结果 LoginUserVo
         */
        SysUser sysUser = loginService.checkToken(token);
        if (sysUser == null) {
            return Result.fail(ErrorCode.TOKEN_ERROR.getCode(), ErrorCode.NO_LOGIN.getMsg());
        }
        LoginUserVo loginUserVo = BeanUtil.copyProperties(sysUser, LoginUserVo.class);
        return Result.success(loginUserVo);
    }

    @Override
    public UserVo findUserVoById(Long authorId) {
        //根据id查询
        //为防止sysUser为空增加一个判断
        SysUser sysUser = getById(authorId);
        if (sysUser == null){
            sysUser = new SysUser();
            sysUser.setId(1L);
            sysUser.setAvatar("/static/img/logo.b3a48c0.png");
            sysUser.setNickname("码神之路");
        }
        UserVo userVo = new UserVo();
        BeanUtil.copyProperties(sysUser, userVo);
        userVo.setId(String.valueOf(sysUser.getId()));
        return userVo;
    }
}




