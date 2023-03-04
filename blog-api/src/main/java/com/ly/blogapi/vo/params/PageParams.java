package com.ly.blogapi.vo.params;

import lombok.Data;

/**
 * <p>
 *     页面参数
 * </p>
 *
 * @author zhuxuchen
 * @since 2023-03-03
 */
@Data
public class PageParams {
    private int page = 1;
    private int pageSize = 10;
}
