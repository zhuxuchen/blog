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

    List<Tag> findTagsByArticleId(Long articleId);
}




