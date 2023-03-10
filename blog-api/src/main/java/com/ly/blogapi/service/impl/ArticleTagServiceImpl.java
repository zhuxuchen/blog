package com.ly.blogapi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ly.blogapi.entity.ArticleTag;
import com.ly.blogapi.mapper.ArticleTagMapper;
import com.ly.blogapi.service.ArticleTagService;
import org.springframework.stereotype.Service;

/**
* @author zhuxuchen
* @description 针对表【ms_article_tag】的数据库操作Service实现
* @createDate 2023-03-10 14:24:31
*/
@Service
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagMapper, ArticleTag>
    implements ArticleTagService{

}




