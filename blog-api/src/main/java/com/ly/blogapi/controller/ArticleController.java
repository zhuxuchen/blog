package com.ly.blogapi.controller;

import com.ly.blogapi.service.ArticleService;
import com.ly.blogapi.vo.Result;
import com.ly.blogapi.vo.params.ArticleParam;
import com.ly.blogapi.vo.params.PageParams;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 首页 最热文章
     * @return com.ly.blogapi.vo.Result
     */
    @PostMapping("/hot")
    public Result hotArticle() {
        int limit = 5;
        return articleService.hotArticle(limit);
    }

    /**
     * 首页 最新文章
     * @return com.ly.blogapi.vo.Result
     */
    @PostMapping("/new")
    public Result newArticle() {
        int limit = 5;
        return articleService.newArticle(limit);
    }

    /**
     * 首页 文章归档
     * @return com.ly.blogapi.vo.Result
     */
    @PostMapping("/listArchives")
    public Result listArchives() {
        return articleService.listArchives();
    }

    /**
     * 根据id查询文章详情
     * @param articleId
     * @return com.ly.blogapi.vo.Result
     */
    @PostMapping("/view/{id}")
    public Result findArticleById(@PathVariable("id") Long articleId) {
        return articleService.findArticleById(articleId);
    }

    /**
     * 发布文章
     * @param articleParam
     * @return com.ly.blogapi.vo.Result
     */
    @PostMapping("/publish")
    public Result publish(@RequestBody ArticleParam articleParam) {
        return articleService.publish(articleParam);
    }
}
