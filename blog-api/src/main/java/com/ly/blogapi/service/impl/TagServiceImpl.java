package com.ly.blogapi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ly.blogapi.entity.Tag;
import com.ly.blogapi.service.TagService;
import com.ly.blogapi.mapper.TagMapper;
import org.springframework.stereotype.Service;

/**
* @author zhuxuchen
* @description 针对表【ms_tag】的数据库操作Service实现
* @createDate 2023-03-03 15:44:13
*/
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag>
    implements TagService{

}




