package com.ly.blogapi.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ly.blogapi.entity.Category;
import com.ly.blogapi.service.CategoryService;
import com.ly.blogapi.mapper.CategoryMapper;
import com.ly.blogapi.vo.CategoryVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author zhuxuchen
* @description 针对表【ms_category】的数据库操作Service实现
* @createDate 2023-03-08 14:48:45
*/
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
    implements CategoryService{

    @Resource
    private CategoryMapper categoryMapper;

    @Override
    public CategoryVo findCategoryByCategoryId(Integer categoryId) {
        Category category = categoryMapper.selectById(categoryId);
        CategoryVo categoryVo = BeanUtil.copyProperties(category, CategoryVo.class);
        return categoryVo;
    }
}




