package com.ly.blogapi.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ly.blogapi.entity.Comment;
import com.ly.blogapi.entity.SysUser;
import com.ly.blogapi.service.CommentService;
import com.ly.blogapi.mapper.CommentMapper;
import com.ly.blogapi.service.SysUserService;
import com.ly.blogapi.utils.UserHolder;
import com.ly.blogapi.vo.CommentVo;
import com.ly.blogapi.vo.Result;
import com.ly.blogapi.vo.UserVo;
import com.ly.blogapi.vo.params.CommentParam;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static cn.hutool.extra.cglib.CglibUtil.copyList;

/**
* @author zhuxuchen
* @description 针对表【ms_comment】的数据库操作Service实现
* @createDate 2023-03-09 10:47:49
*/
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
    implements CommentService{

    @Resource
    private SysUserService sysUserService;

    @Override
    public Result commentsByArticleId(Long id) {
        /*
         * 1.根据文章id查询评论列表 从comment表中查询
         * 2.根据作者的id查询作者的信息
         * 3.判断 如果level == 1 要去查询它有没有子评论
         * 4.如果有 根据评论id进行查询
         */
        List<Comment> comments = lambdaQuery()
                .eq(Comment::getArticleId, id)
                .eq(Comment::getLevel, 1)
                .list();
        List<CommentVo> commentVoList = copyList(comments);
        return Result.success(commentVoList);
    }

    @Override
    public Result comment(CommentParam commentParam) {
        //拿到当前登陆用户
        SysUser sysUser = UserHolder.get();
        Comment comment = new Comment();
        comment.setArticleId(commentParam.getArticleId());
        comment.setAuthorId(sysUser.getId());
        comment.setContent(commentParam.getContent());
        comment.setCreateDate(System.currentTimeMillis());
        Long parent = commentParam.getParent();
        if (parent == null || parent == 0) {
            comment.setLevel(String.valueOf(1));
        }else{
            comment.setLevel(String.valueOf(2));
        }
        //如果是空，parent就是0
        comment.setParentId(parent == null ? 0 : parent);
        Long toUserId = commentParam.getToUserId();
        comment.setToUid(toUserId == null ? 0 : toUserId);
        save(comment);
        return Result.success(null);
    }

    private List<CommentVo> copyList(List<Comment> comments) {
        List<CommentVo> commentVoList = new ArrayList<>();
        for (Comment comment : comments) {
            commentVoList.add(copy(comment));
        }
        return commentVoList;
    }

    private CommentVo copy(Comment comment) {
        CommentVo commentVo = new CommentVo();
        //只能拷贝类型相同的
        BeanUtil.copyProperties(comment, commentVo);
        commentVo.setId(String.valueOf(comment.getId()));
        //作者信息
        Long authorId = comment.getAuthorId();
        UserVo userVo = this.sysUserService.findUserVoById(authorId);
        commentVo.setAuthor(userVo);
        //子评论
        Integer level = Integer.valueOf(comment.getLevel());
        if (level == 1) {
            Long id = comment.getId();
            List<CommentVo> commentVoList = findCommentsByParentId(id);
            commentVo.setChildren(commentVoList);
        }
        //to user 给谁评论
        if (level >1) {
            Long toUid = comment.getToUid();
            UserVo touserVo = this.sysUserService.findUserVoById(toUid);
            commentVo.setToUser(touserVo);
        }
        return commentVo;
    }

    private List<CommentVo> findCommentsByParentId(Long id) {
        List<Comment> comments = lambdaQuery()
                .eq(Comment::getParentId, id)
                .eq(Comment::getLevel, 2)
                .list();
        return copyList(comments);
    }
}




