package com.ly.blogapi.vo.params;

import lombok.Data;

/**
 * <p>
 *     评论参数
 * </p>
 *
 * @author zhuxuchen
 * @since 2023-03-09
 */
@Data
public class CommentParam {
    private Long articleId;

    private String content;

    private Long parent;

    private Long toUserId;
}
