package com.ly.blogapi.vo.params;

import lombok.Data;

/**
 * <p>
 *     文章内容参数
 * </p>
 *
 * @author zhuxuchen
 * @since 2023-03-10
 */
@Data
public class ArticleBodyParam {
    private String content;

    private String contentHtml;
}
