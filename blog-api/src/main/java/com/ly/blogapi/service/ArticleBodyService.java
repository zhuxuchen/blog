package com.ly.blogapi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ly.blogapi.entity.ArticleBody;
import com.ly.blogapi.vo.ArticleBodyVo;

/**
* @author zhuxuchen
* @description 针对表【ms_article_body】的数据库操作Service
* @createDate 2023-03-08 14:48:45
*/
public interface ArticleBodyService extends IService<ArticleBody> {

    ArticleBodyVo findArticleBodyByArticleBodyId(Long articleBodyId);
}
