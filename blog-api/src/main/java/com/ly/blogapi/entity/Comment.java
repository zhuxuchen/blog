package com.ly.blogapi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 
 * @TableName ms_comment
 */
@TableName(value ="ms_comment")
@Data
public class Comment implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    private String content;

    /**
     * 
     */
    private Long createDate;

    /**
     * 
     */
    private Long articleId;

    /**
     * 
     */
    private Long authorId;

    /**
     * 
     */
    private Long parentId;

    /**
     * 
     */
    private Long toUid;

    /**
     * 
     */
    private String level;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}