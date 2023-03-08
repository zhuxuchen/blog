package com.ly.blogapi.handler;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.ly.blogapi.entity.SysUser;
import com.ly.blogapi.service.LoginService;
import com.ly.blogapi.vo.ErrorCode;
import com.ly.blogapi.vo.Result;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *     登录拦截器
 * </p>
 *
 * @author zhuxuchen
 * @since 2023-03-08
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Resource
    private LoginService loginService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 在执行Controller方法之前执行
        /*
         * 1、需要判断请求的接口路径是否为HandlerMethod(Controller方法)
         * 2、判断token是否为空 如果为空 未登录
         * 3、如果token不为空 登录验证 loginService checkToken
         * 4、如果验证成功 放行
         */
        if ( !(handler instanceof HandlerMethod)) {
            return true;
        }
        String token = request.getHeader("Authorization");
        if (StrUtil.isBlank(token)) {
            Result result = Result.fail(ErrorCode.NO_LOGIN.getCode(), "未登录");
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(JSONUtil.toJsonStr(result));
            return false;
        }
        SysUser sysUser = loginService.checkToken(token);
        if (sysUser == null) {
            Result result = Result.fail(ErrorCode.NO_LOGIN.getCode(), "未登录");
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(JSONUtil.toJsonStr(result));
            return false;
        }
        // 登录验证成功 放行
        return true;
    }
}
