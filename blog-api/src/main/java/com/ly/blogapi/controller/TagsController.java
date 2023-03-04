package com.ly.blogapi.controller;

import com.ly.blogapi.service.TagService;
import com.ly.blogapi.vo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *     标签控制器
 * </p>
 *
 * @author zhuxuchen
 * @since 2023-03-04
 */
@RestController
@RequestMapping("/tags")
public class TagsController {

    @Resource
    private TagService tagService;

    /**
     * 最热标签
     * @return com.ly.blogapi.vo.Result
     */
    @GetMapping("/hot")
    public Result hot() {
        int limit = 6;
        return tagService.hot(limit);
    }
}
