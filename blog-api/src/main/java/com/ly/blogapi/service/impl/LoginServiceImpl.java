package com.ly.blogapi.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.json.JSONUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.ly.blogapi.entity.SysUser;
import com.ly.blogapi.service.LoginService;
import com.ly.blogapi.service.SysUserService;
import com.ly.blogapi.utils.JWTUtils;
import com.ly.blogapi.vo.ErrorCode;
import com.ly.blogapi.vo.Result;
import com.ly.blogapi.vo.params.LoginParam;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *     用户登录实现类
 * </p>
 *
 * @author zhuxuchen
 * @since 2023-03-05
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private SysUserService sysUserService;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    private static final String slat = "mszlu!@#";

    /**
     * 1、检查参数是否合法 <br>
     * 2、根据用户名和密码去user表中查询 是否存在 <br>
     * 3、如果不存在，登录失败 <br>
     * 4、如果存在，使用jwt生成token并返回给前端 <br>
     * 5、token放入Redis中，token:user 信息 设置过期时间
     * (登录认证的时候，先认证token字符串是否合法，再去Redis判断是否存在)
     *
     * @param loginParam 登录参数
     * @return com.ly.blogapi.vo.Result
     */
    @Override
    public Result login(LoginParam loginParam) {
        String account = loginParam.getAccount();
        String password = loginParam.getPassword();
        if (StringUtils.isBlank(account) || StringUtils.isBlank(password)) {
            return Result.fail(ErrorCode.PARAMS_ERROR.getCode(), ErrorCode.PARAMS_ERROR.getMsg());
        }

        password = DigestUtil.md5Hex(password + slat);
        SysUser sysUser = sysUserService.lambdaQuery()
                .select(SysUser::getAccount,SysUser::getId,SysUser::getAvatar,SysUser::getNickname)
                .eq(SysUser::getAccount, account)
                .eq(SysUser::getPassword, password)
                .one();
        if (ObjectUtil.isNull(sysUser)) {
            return Result.fail(ErrorCode.ACCOUNT_PWD_NOT_EXIST.getCode(),
                    ErrorCode.ACCOUNT_PWD_NOT_EXIST.getMsg());
        }

        String token = JWTUtils.createToken(sysUser.getId());
        stringRedisTemplate.opsForValue()
                .set("TOKEN_" + token, JSONUtil.toJsonStr(sysUser), 1, TimeUnit.DAYS);
        return Result.success(token);
    }

    @Override
    public SysUser checkToken(String token) {
        if (StrUtil.isBlank(token)) {
            return null;
        }
        Map<String, Object> stringObjectMap = JWTUtils.checkToken(token);
        if (stringObjectMap == null) {
            return null;
        }
        String userJson = stringRedisTemplate.opsForValue().get("TOKEN_" + token);
        if (StrUtil.isBlank(userJson)) {
            return null;
        }
        SysUser sysUser = JSON.parseObject(userJson, SysUser.class);
        return sysUser;
    }

    @Override
    public Result logout(String token) {
        stringRedisTemplate.delete("TOKEN_" + token);
        return Result.success(null);
    }
}
