package com.ly.blogapi.service;

import com.ly.blogapi.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ly.blogapi.vo.CategoryVo;
import com.ly.blogapi.vo.Result;

/**
* @author zhuxuchen
* @description 针对表【ms_category】的数据库操作Service
* @createDate 2023-03-08 14:48:45
*/
public interface CategoryService extends IService<Category> {

    CategoryVo findCategoryByCategoryId(Integer categoryId);

    Result findAll();
}
