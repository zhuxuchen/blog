package com.ly.blogapi.controller;

import com.ly.blogapi.service.CommentService;
import com.ly.blogapi.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 *     评论控制器
 * </p>
 *
 * @author zhuxuchen
 * @since 2023-03-09
 */
@RestController
@RequestMapping("/comments")
public class CommentsController {

    @Resource
    private CommentService commentService;

    @GetMapping("/article/{id}")
    public Result comments(@PathVariable("id") Long id) {
        return commentService.commentsByArticleId(id);
    }
}
