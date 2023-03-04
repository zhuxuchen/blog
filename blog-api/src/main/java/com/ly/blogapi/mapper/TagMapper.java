package com.ly.blogapi.mapper;

import com.ly.blogapi.entity.Tag;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author zhuxuchen
* @description 针对表【ms_tag】的数据库操作Mapper
* @createDate 2023-03-03 15:44:13
* @Entity com.ly.blogapi.entity.Tag
*/
public interface TagMapper extends BaseMapper<Tag> {

    /**
     * 根据文章id查询标签
     * @param articleId 文章id
     * @return java.util.List<com.ly.blogapi.entity.Tag>
     */
    List<Tag> findTagsByArticleId(Long articleId);

    /**
     * 查询最热的标签 前n条
     * @param limit 查询前几条
     * @return java.util.List<java.lang.Long>
     */
    List<Long> findHotTagIds(int limit);
}




