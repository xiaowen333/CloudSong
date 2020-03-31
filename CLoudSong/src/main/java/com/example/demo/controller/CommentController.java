package com.example.demo.controller;

import com.example.demo.domain.Comment;
import com.example.demo.mapper.CommentMapper;
import com.example.demo.mapper.SonglistMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 作者: 是小文啊
 * QQ: 1612902996
 * 博客: wenshijin.cn
 * 描述:
 * 类名: CommentController
 * 编辑日期: 2020/3/16
 * 编辑时间: 20:26
 *
 * @Description:
 */
@Controller
public class CommentController {
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    SonglistMapper songlistMapper;

    @RequestMapping("/comment")
    public ModelAndView CommentById(@RequestParam(defaultValue = "2")Long id,@RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = "5")int pageSize) {
        ModelAndView modelAndView = new ModelAndView();

        PageHelper.startPage(pageNo,pageSize);
        List<Comment> byUserIdComment = commentMapper.findByUserIdComment(id);

        PageInfo pageInfo = new PageInfo(byUserIdComment);
        modelAndView.addObject("comments",pageInfo);

        modelAndView.setViewName("admins/comment-table");
        return modelAndView;
    }

    @RequestMapping("/updateComment")
    @Transactional
    public ModelAndView updateComment(@RequestParam(defaultValue = "")Long id) {
        ModelAndView modelAndView = new ModelAndView();

        Comment byIdComment = commentMapper.findByIdComment(id);
        modelAndView.addObject("comment",byIdComment);
        modelAndView.setViewName("admins/update-comment");

        return modelAndView;

    }

    @PostMapping("/updateComment")
    @Transactional
    public ModelAndView updateCommentPost(@RequestParam(defaultValue = "")Long id,Comment comment) {
        ModelAndView modelAndView = new ModelAndView();

        Comment byIdComment = commentMapper.findByIdComment(id);
        byIdComment.setContent(comment.getContent());
        commentMapper.updateComment(byIdComment);


        modelAndView.setViewName("redirect:/comment?id=" + byIdComment.getUser().getId());

        return modelAndView;
    }

    @RequestMapping("/deleteComment")
    @Transactional
    public ModelAndView deleteComment(@RequestParam(defaultValue = "")Long id) {
        ModelAndView modelAndView = new ModelAndView();
        Comment byIdComment = commentMapper.findByIdComment(id);
        songlistMapper.deleteSonglistComment(id);
        commentMapper.deleteComment(id);

        modelAndView.setViewName("redirect:/comment?id=" +byIdComment.getUser().getId());

        return modelAndView;

    }



    @RequestMapping("/comments")
    public ModelAndView Comments(@RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = "5")int pageSize) {
        ModelAndView modelAndView = new ModelAndView();

        PageHelper.startPage(pageNo,pageSize);
        List<Comment> allComment = commentMapper.findAllComment();

        PageInfo pageInfo = new PageInfo(allComment);
        modelAndView.addObject("comments",pageInfo);

        modelAndView.setViewName("admins/comments-table");
        return modelAndView;
    }

}
