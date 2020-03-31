package com.example.demo.controller;

import com.example.demo.domain.Authority;
import com.example.demo.domain.Catalog;
import com.example.demo.domain.User;
import com.example.demo.mapper.AuthorityMapper;
import com.example.demo.mapper.CatalogMapper;
import com.example.demo.mapper.CommentMapper;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: 是小文啊
 * QQ: 1612902996
 * 博客: wenshijin.cn
 * 描述:
 * 类名: MainController
 * 编辑日期: 2020/3/10
 * 编辑时间: 22:33
 *
 * @Description:
 */
@Controller
public class MainController {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AuthorityMapper authorityMapper;
    @Autowired
    private CatalogMapper catalogMapper;
    @Autowired
    private CommentMapper commentMapper;
    @RequestMapping("/")
    @Transactional
    public String index() {
        //用户查找
       // List<User> all = userMapper.findAll();
       // User qqq = userMapper.findByName("qqq");
//        List<Authority> authorities = new ArrayList<>();
//       Authority authority = new Authority();
//       authority.setName("111");
//       authorities.add(authority);
//        User user = new User();
//        user.setEmail("111");
//        user.setPassword("111");
//        user.setUsername("111");
//        user.setAuthoritys(authorities);
//
////        添加用户
//        userMapper.insertUser(user);
//        authorityMapper.insertAuthority(authority);
//        userMapper.insertUserAuthority(user.getId(),authority.getId());

//        //删除用户
//        User qqq = userMapper.findByName("qqq");
//        userMapper.deleteUserAuthority(qqq.getId());
//
//        List<Authority> authoritys = qqq.getAuthoritys();
//        for (Authority a :
//                authoritys) {
//            Long id = a.getId();
//            authorityMapper.deleteAuthorityID(id);
//        }
//        userMapper.deleteUser(qqq.getId());

//        //更新user
//        User user = userMapper.findByName("www");
//        user.setEmail("qqq");
//        user.setAvator("qqq");
//        userMapper.updateUser(user);

//        //查看catalog
//        List<Catalog> allCatalog = catalogMapper.findAllCatalog();
//        System.out.println(allCatalog.toString());

//        //插入catalog
//        User qqq = userMapper.findByName("www");
//        System.out.println(qqq.toString());
//        Catalog catalog = new Catalog();
//        catalog.setName("111");
//        catalog.setUser(qqq);
//        catalogMapper.insertCatalog(catalog);
//
//        List<Catalog> allCatalog = catalogMapper.findAllCatalog();
//        System.out.println(allCatalog.toString());
       // System.out.println(commentMapper.findAllComment().toString());
        return "errors-404";
    }
}
