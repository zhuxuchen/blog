package com.ly.blogapi.service;

import com.ly.blogapi.entity.Tag;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ly.blogapi.vo.Result;
import com.ly.blogapi.vo.TagVo;

import java.util.List;

/**
* @author zhuxuchen
* @description 针对表【ms_tag】的数据库操作Service
* @createDate 2023-03-03 15:44:13
*/
public interface TagService extends IService<Tag> {

    /**
     * 根据文章id查询标签
     * @param articleId 文章id
     * @return java.util.List<com.ly.blogapi.vo.TagVo>
     */
    List<TagVo> findTagsByArticleId(Long articleId);

    /**
     * 最热标签
     * @param limit
     * @return com.ly.blogapi.vo.Result
     */
    Result hot(int limit);
}
