package com.ly.blogapi.vo;

import lombok.Data;

/**
 * <p>
 *     登录用户视图类
 * </p>
 *
 * @author zhuxuchen
 * @since 2023-03-07
 */
@Data
public class LoginUserVo {

    private Long id;

    private String account;

    private String nickName;

    private String avatar;
}
