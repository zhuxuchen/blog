package com.ly.blogapi.service.impl;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ly.blogapi.entity.Article;
import com.ly.blogapi.entity.ArticleBody;
import com.ly.blogapi.entity.ArticleTag;
import com.ly.blogapi.entity.SysUser;
import com.ly.blogapi.mapper.ArticleMapper;
import com.ly.blogapi.service.*;
import com.ly.blogapi.utils.UserHolder;
import com.ly.blogapi.vo.*;
import com.ly.blogapi.vo.params.ArticleParam;
import com.ly.blogapi.vo.params.PageParams;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
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

    @Resource
    private ArticleBodyService articleBodyService;

    @Resource
    private CategoryService categoryService;

    @Resource
    private ThreadService threadService;

    @Resource
    private ArticleTagService articleTagService;

    @Override
    public Result listArticle(PageParams pageParams) {
        Page<Article> page = new Page<>(pageParams.getPage(), pageParams.getPageSize());
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();

        if (pageParams.getCategoryId() != null) {
            queryWrapper.eq(Article::getCategoryId, pageParams.getCategoryId());
        }
        queryWrapper.orderByDesc(Article::getWeight, Article::getCreateDate);
        Page<Article> articlePage = articleMapper.selectPage(page, queryWrapper);
        List<Article> articleList = articlePage.getRecords();
        List<ArticleVo> articleVoList = copyList(articleList, true, true);

        return Result.success(articleVoList);
    }

    @Override
    public Result hotArticle(int limit) {
        // select id,title from article order by view_counts desc limit 5
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .select(Article::getId,Article::getTitle)
                .orderByDesc(Article::getViewCounts)
                .last("limit " + limit);
        List<Article> articles = articleMapper.selectList(queryWrapper);
        return Result.success(copyList(articles, false, false));
    }

    @Override
    public Result newArticle(int limit) {
        // select id,title from article order by create_date desc limit 5
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .select(Article::getId,Article::getTitle)
                .orderByDesc(Article::getCreateDate)
                .last("limit " + limit);
        List<Article> articles = articleMapper.selectList(queryWrapper);
        return Result.success(copyList(articles, false, false));
    }

    @Override
    public Result listArchives() {
        List<Archives> archivesList = articleMapper.listArchives();
        return Result.success(archivesList);
    }

    @Override
    public Result findArticleById(Long articleId) {
        /*
         * 1、根据id查询文章信息
         * 2、根据bodyId和categoryId去做关联查询
         */
        Article article = articleMapper.selectById(articleId);
        ArticleVo articleVo = copy(article, true, true);

        Long articleBodyId = article.getBodyId();
        ArticleBodyVo articleBody =  articleBodyService.findArticleBodyByArticleBodyId(articleBodyId);
        articleVo.setBody(articleBody);
        Integer categoryId = article.getCategoryId();
        CategoryVo categoryVo = categoryService.findCategoryByCategoryId(categoryId);
        articleVo.setCategory(categoryVo);

        // 把更新阅读数交到线程池中去执行
        threadService.updateArticleViewCount(articleMapper, article);
        return Result.success(articleVo);
    }

    @Override
    @Transactional
    public Result publish(ArticleParam articleParam) {
        SysUser sysUser = UserHolder.get();

        Article article = new Article();
        article.setAuthorId(sysUser.getId());
        article.setWeight(0);
        article.setViewCounts(0);
        article.setTitle(articleParam.getTitle());
        article.setSummary(articleParam.getSummary());
        article.setCommentCounts(0);
        article.setCreateDate(System.currentTimeMillis());
        article.setCategoryId(Integer.valueOf(articleParam.getCategory().getId()));
        // 插入之后会生成文章id
        save(article);

        List<TagVo> tags = articleParam.getTags();
        if (tags != null) {
            List<ArticleTag> articleTags = new ArrayList<>();
            for (TagVo tag : tags) {
                Long articleId = article.getId();
                ArticleTag articleTag = new ArticleTag();
                articleTag.setArticleId(articleId);
                articleTag.setTagId(Long.valueOf(tag.getId()));
                articleTags.add(articleTag);
            }
            articleTagService.saveBatch(articleTags);
        }

        ArticleBody articleBody = new ArticleBody();
        articleBody.setArticleId(article.getId());
        articleBody.setContent(articleParam.getBody().getContent());
        articleBody.setContentHtml(articleParam.getBody().getContentHtml());
        articleBodyService.save(articleBody);

        article.setBodyId(articleBody.getId());
        updateById(article);
        HashMap<String, String> map = new HashMap<>();
        map.put("id", article.getId().toString());
        return Result.success(map);
    }

    private List<ArticleVo> copyList(List<Article> articleList, boolean isTag, boolean isAuthor) {
        List<ArticleVo> articleVoList = new ArrayList<>();
        for (Article article : articleList) {
            articleVoList.add(copy(article,isTag,isAuthor));
        }
        return articleVoList;
    }
    private ArticleVo copy(Article article, boolean isTag, boolean isAuthor) {
        ArticleVo articleVo = new ArticleVo();
        BeanUtil.copyProperties(article, articleVo);
        Long createDate = article.getCreateDate();
        if (createDate != null) {
            articleVo.setCreateDate(new DateTime(createDate).toString("yyyy-MM-dd HH:mm"));
        }
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
