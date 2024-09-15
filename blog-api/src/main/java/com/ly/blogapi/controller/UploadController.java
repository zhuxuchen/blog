package com.ly.blogapi.controller;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import com.ly.blogapi.vo.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *     图片上传控制器
 * </p>
 *
 * @author zhuxuchen
 * @since 2023-03-13
 */
@RestController
@RequestMapping("/upload")
public class UploadController {

    @PostMapping
    public Result upload(@RequestParam("image") MultipartFile file) {
        // 原始文件名称 比如：aa.png
        String originalFilename = file.getOriginalFilename();
        // 唯一的文件名称
        String fileName = UUID.randomUUID()
                + "." + StrUtil.subAfter(originalFilename, ".", false);
        return null;
    }
}
