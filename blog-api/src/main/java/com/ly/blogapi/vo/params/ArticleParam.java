package com.ly.blogapi.vo.params;

import com.ly.blogapi.vo.CategoryVo;
import com.ly.blogapi.vo.TagVo;
import lombok.Data;

import java.util.List;

/**
 * <p>
 *     发布文章参数
 * </p>
 *
 * @author zhuxuchen
 * @since 2023-03-10
 */
@Data
public class ArticleParam {
    private Long id;

    private ArticleBodyParam body;

    private CategoryVo category;

    private String summary;

    private List<TagVo> tags;

    private String title;
}
