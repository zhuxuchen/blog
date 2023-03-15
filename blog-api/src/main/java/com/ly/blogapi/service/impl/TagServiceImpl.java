package com.ly.blogapi.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ly.blogapi.entity.Tag;
import com.ly.blogapi.mapper.TagMapper;
import com.ly.blogapi.service.TagService;
import com.ly.blogapi.vo.Result;
import com.ly.blogapi.vo.TagVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
* @author zhuxuchen
* @description 针对表【ms_tag】的数据库操作Service实现
* @createDate 2023-03-03 15:44:13
*/
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag>
    implements TagService{

    @Resource
    private TagMapper tagMapper;

    @Override
    public List<TagVo> findTagsByArticleId(Long articleId) {
        List<Tag> tags = tagMapper.findTagsByArticleId(articleId);
        return copyList(tags);
    }

    @Override
    public Result hot(int limit) {
        // 查询 根据tag_id分组 计数 从大到小排列 取前limit个
        List<Long> tagIds = tagMapper.findHotTagIds(limit);
        List<Tag> tagList = listByIds(tagIds);
        return Result.success(tagList);
    }

    @Override
    public Result findAll() {
        List<Tag> tags = lambdaQuery()
                .select(Tag::getId, Tag::getTagName)
                .list();
        return Result.success(copyList(tags));
    }

    @Override
    public Result findAllDetail() {
        List<Tag> tags = list();
        return Result.success(copyList(tags));
    }

    @Override
    public Result findDetailById(Long id) {
        Tag tag = getById(id);
        TagVo tagVo = BeanUtil.copyProperties(tag, TagVo.class);
        return Result.success(tagVo);
    }

    private List<TagVo> copyList(List<Tag> tagList) {
        List<TagVo> tagVoList = new ArrayList<>();
        for (Tag tag : tagList) {
            tagVoList.add(copy(tag));
        }
        return tagVoList;
    }

    private TagVo copy(Tag tag) {
        TagVo tagVo = new TagVo();
        BeanUtil.copyProperties(tag,tagVo);
        tagVo.setId(String.valueOf(tag.getId()));
        return tagVo;
    }
}




