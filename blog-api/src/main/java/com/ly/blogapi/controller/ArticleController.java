package com.ly.blogapi.controller;

import com.ly.blogapi.service.ArticleService;
import com.ly.blogapi.vo.params.PageParams;
import com.ly.blogapi.vo.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *     文章相关操作的控制器
 * </p>
 *
 * @author zhuxuchen
 * @since 2023-03-03
 */
@RestController
@RequestMapping("/articles")
public class ArticleController {

    @Resource
    private ArticleService articleService;

    /**
     * 首页 文章列表
     * @param pageParams 页面参数
     * @return com.ly.blogapi.vo.Result
     */
    @PostMapping
    public Result listArticle(@RequestBody PageParams pageParams) {
        return articleService.listArticle(pageParams);
    }
}
