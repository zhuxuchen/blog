package com.ly.blogapi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ly.blogapi.entity.Article;
import com.ly.blogapi.vo.Result;
import com.ly.blogapi.vo.params.PageParams;


/**
* @author zhuxuchen
* @description 针对表【ms_article】的数据库操作Service
* @createDate 2023-03-03 15:37:45
*/
public interface ArticleService extends IService<Article> {

    /**
     * 首页获取文章列表信息
     * @param pageParams 页面参数
     * @return com.ly.blogapi.vo.Result
     */
    Result listArticle(PageParams pageParams);

    /**
     * 首页获取最热文章
     * @param limit
     * @return com.ly.blogapi.vo.Result
     */
    Result hotArticle(int limit);

    /**
     * 首页获取最新文章
     * @param limit
     * @return com.ly.blogapi.vo.Result
     */
    Result newArticle(int limit);
}
