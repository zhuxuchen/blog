package com.ly.blogapi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ly.blogapi.entity.SysUser;
import com.ly.blogapi.service.SysUserService;
import com.ly.blogapi.mapper.SysUserMapper;
import org.springframework.stereotype.Service;

/**
* @author zhuxuchen
* @description 针对表【ms_sys_user】的数据库操作Service实现
* @createDate 2023-03-03 15:44:13
*/
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser>
    implements SysUserService{

}




