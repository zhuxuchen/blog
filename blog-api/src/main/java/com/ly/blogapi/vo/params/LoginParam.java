package com.ly.blogapi.vo.params;

import lombok.Data;

/**
 * <p>
 *     登录请求参数
 * </p>
 *
 * @author zhuxuchen
 * @since 2023-03-05
 */
@Data
public class LoginParam {

    private String account;

    private String password;

    private String nickname;
}
