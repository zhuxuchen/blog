package com.ly.blogapi.service;

import com.ly.blogapi.vo.Result;
import com.ly.blogapi.vo.params.LoginParam;

/**
 * <p>
 *     用户登录服务接口
 * </p>
 *
 * @author zhuxuchen
 * @since 2023-03-05
 */
public interface LoginService {

    /**
     * 登录功能
     * @param loginParam
     * @return com.ly.blogapi.vo.Result
     */
    Result login(LoginParam loginParam);
}
