package com.ly.blogapi.controller;

import com.ly.blogapi.service.CategoryService;
import com.ly.blogapi.vo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *     分类控制器
 * </p>
 *
 * @author zhuxuchen
 * @since 2023-03-10
 */
@RestController
@RequestMapping("/categorys")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    @GetMapping
    public Result categories() {
        return categoryService.findAll();
    }

    @GetMapping("/detail")
    public Result categoryDetail() {
        return categoryService.findAllDetail();
    }
}
