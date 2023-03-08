package com.ly.blogapi.utils;

import com.ly.blogapi.entity.SysUser;

/**
 * <p>
 *     本地线程存储用户信息
 * </p>
 *
 * @author zhuxuchen
 * @since 2023-03-08
 */
public class UserHolder {

    private UserHolder(){}

    public static final ThreadLocal<SysUser> tl = new ThreadLocal<>();

    public static void put(SysUser sysUser) {
        tl.set(sysUser);
    }

    public static SysUser get() {
        return tl.get();
    }

    public static void remove() {
        tl.remove();
    }
}
