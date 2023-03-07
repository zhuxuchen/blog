package com.ly.blogapi.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.json.JSONUtil;
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
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    @Transactional
    public Result register(LoginParam loginParam) {
        /*
         * 1、判断参数是否合法
         * 2、判断账户是否存在 存在则返回账户已经注册
         * 3、不存在 注册账户
         * 4、生成token
         * 5、存入Redis 并返回
         * 6、注意加上事务 一旦中间的任何过程出现问题 注册的用户需要回滚
         */
        String account = loginParam.getAccount();
        String password = loginParam.getPassword();
        String nickname = loginParam.getNickname();
        if (StrUtil.isBlank(account) || StrUtil.isBlank(password) || StrUtil.isBlank(nickname)) {
            return Result.fail(ErrorCode.PARAMS_ERROR.getCode(), ErrorCode.PARAMS_ERROR.getMsg());
        }
        SysUser sysUser = sysUserService.query().eq("account", account).one();
        if (sysUser != null) {
            return Result.fail(ErrorCode.ACCOUNT_EXIST.getCode(),"账户已经被注册了");
        }
        sysUser = new SysUser();
        sysUser.setNickname(nickname);
        sysUser.setAccount(account);
        sysUser.setPassword(DigestUtil.md5Hex(password + slat));
        sysUser.setCreateDate(System.currentTimeMillis());
        sysUser.setLastLogin(System.currentTimeMillis());
        sysUser.setAvatar("/static/img/logo.b3a48c0.png");
        sysUser.setAdmin(false); //1 为true
        sysUser.setDeleted(false); // 0 为false
        sysUserService.save(sysUser);

        String token = JWTUtils.createToken(sysUser.getId());
        stringRedisTemplate.opsForValue()
                .set("TOKEN_" + token, JSONUtil.toJsonStr(sysUser), 1, TimeUnit.DAYS);
        return Result.success(token);
    }
}
