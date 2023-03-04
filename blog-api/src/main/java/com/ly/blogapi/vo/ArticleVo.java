package com.ly.blogapi.vo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.List;

/**
 * <p>
 *     文章视图类
 * </p>
 *
 * @author zhuxuchen
 * @since 2023-03-03
 */
@Data
public class ArticleVo {
    private String id;

    private String title;

    private String summary;

    private Integer commentCounts;

    private Integer viewCounts;

    private Integer weight;
    /**
     * 创建时间
     */
    private String createDate;

    private String author;

    private ArticleBodyVo body;

    private List<TagVo> tags;

    private CategoryVo category;
}
