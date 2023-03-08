package com.ly.blogapi.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ly.blogapi.entity.ArticleBody;
import com.ly.blogapi.service.ArticleBodyService;
import com.ly.blogapi.mapper.ArticleBodyMapper;
import com.ly.blogapi.vo.ArticleBodyVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author zhuxuchen
* @description 针对表【ms_article_body】的数据库操作Service实现
* @createDate 2023-03-08 14:48:45
*/
@Service
public class ArticleBodyServiceImpl extends ServiceImpl<ArticleBodyMapper, ArticleBody>
    implements ArticleBodyService{

    @Resource
    private ArticleBodyMapper articleBodyMapper;

    @Override
    public ArticleBodyVo findArticleBodyByArticleBodyId(Long articleBodyId) {
        ArticleBody articleBody = articleBodyMapper.selectById(articleBodyId);
        ArticleBodyVo articleBodyVo = BeanUtil.copyProperties(articleBody, ArticleBodyVo.class);
        return articleBodyVo;
    }
}




