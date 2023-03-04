package com.ly.blogapi.vo;

import lombok.Data;

/**
 * <p></p>
 *
 * @author zhuxuchen
 * @since 2023-03-03
 */
@Data
public class CategoryVo {

    //id，图标路径，图标名称
    private String id;

    private String avatar;

    private String categoryName;

    private String description;

}
