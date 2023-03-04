package com.ly.blogapi.service.impl;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ly.blogapi.entity.Article;
import com.ly.blogapi.entity.Tag;
import com.ly.blogapi.mapper.TagMapper;
import com.ly.blogapi.service.ArticleService;
import com.ly.blogapi.mapper.ArticleMapper;
import com.ly.blogapi.service.SysUserService;
import com.ly.blogapi.service.TagService;
import com.ly.blogapi.vo.ArticleVo;
import com.ly.blogapi.vo.Result;
import com.ly.blogapi.vo.params.PageParams;
import jdk.jfr.consumer.RecordedObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



/**
* @author zhuxuchen
* @description 针对表【ms_article】的数据库操作Service实现
* @createDate 2023-03-03 15:37:45
*/
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article>
implements ArticleService{

    @Resource
    private ArticleMapper articleMapper;

    @Resource
    private TagService tagService;

    @Resource
    protected SysUserService sysUserService;

    @Override
    public Result listArticle(PageParams pageParams) {
        Page<Article> page = new Page<>(pageParams.getPage(), pageParams.getPageSize());
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Article::getWeight, Article::getCreateDate);
        Page<Article> articlePage = articleMapper.selectPage(page, queryWrapper);
        List<Article> articleList = articlePage.getRecords();
        List<ArticleVo> articleVoList = copyList(articleList);

        return Result.success(articleVoList);
    }
    private List<ArticleVo> copyList(List<Article> articleList) {
        List<ArticleVo> articleVoList = new ArrayList<>();
        for (Article article : articleList) {
            articleVoList.add(copy(article,true,true));
        }
        return articleVoList;
    }
    private ArticleVo copy(Article article, boolean isTag, boolean isAuthor) {
        ArticleVo articleVo = new ArticleVo();
        BeanUtil.copyProperties(article, articleVo);

        articleVo.setCreateDate(new DateTime(article.getCreateDate()).toString("yyyy-MM-dd HH:mm"));
        if (isTag) {
            Long articleId = article.getId();
            articleVo.setTags(tagService.findTagsByArticleId(articleId));
        }
        if (isAuthor) {
            Long authorId = article.getAuthorId();
            articleVo.setAuthor(sysUserService.getById(authorId).getNickname());
        }
        return articleVo;
    }
}
