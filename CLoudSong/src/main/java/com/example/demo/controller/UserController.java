package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.mapper.UserMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 作者: 是小文啊
 * QQ: 1612902996
 * 博客: wenshijin.cn
 * 描述:
 * 类名: UserController
 * 编辑日期: 2020/3/13
 * 编辑时间: 17:36
 *
 * @Description:
 */
@Controller
@ResponseBody
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/user")
    public ModelAndView findByIdUser(@RequestParam(defaultValue = "2")Long id){
        ModelAndView modelAndView = new ModelAndView();

        User byId = userMapper.findById(id);
        modelAndView.addObject("user",byId);


        modelAndView.setViewName("admins/features-profile");
        return  modelAndView;
    }

    @PostMapping("/user")
    public ModelAndView updateUser(User user){
        ModelAndView modelAndView = new ModelAndView();
        System.out.println(user);

        if(user.getAvatar().equals("")) {
            User byId = userMapper.findById(user.getId());
            user.setAvatar(byId.getAvatar());
        }
        else {
            String avatar = user.getAvatar();
            String avatar1="../img/avatar/"+ avatar;
            user.setAvatar(avatar1);
        }
        userMapper.updateUser(user);
        modelAndView.setViewName("redirect:/user/" + user.getId());
        return modelAndView;
    }

    @RequestMapping("/users")
    public ModelAndView findAllUser(@RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = "5")int pageSize){
        ModelAndView modelAndView = new ModelAndView();

        PageHelper.startPage(pageNo,pageSize);
        List<User> all = userMapper.findAll();

        PageInfo pageInfo = new PageInfo(all);

        modelAndView.addObject("users",pageInfo);
        modelAndView.setViewName("admins/users-table");
        return  modelAndView;
    }



}
