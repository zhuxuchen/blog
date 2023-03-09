package com.ly.blogapi.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.List;

/**
 * <p>
 *     评论视图类
 * </p>
 *
 * @author zhuxuchen
 * @since 2023-03-09
 */
@Data
public class CommentVo {
    //防止前端 精度损失 把id转为string
    @JsonSerialize(using = ToStringSerializer.class)
    private String id;

    private UserVo author;

    private String content;

    private List<CommentVo> children;

    private String createDate;

    private Integer level;

    private UserVo toUser;
}
