package com.ly.blogapi.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 
 * @TableName ms_article_tag
 */
@TableName(value ="ms_article_tag")
@Data
public class ArticleTag implements Serializable {
    /**
     * 
     */

    private Long id;

    /**
     * 
     */
    private Long articleId;

    /**
     * 
     */
    private Long tagId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}