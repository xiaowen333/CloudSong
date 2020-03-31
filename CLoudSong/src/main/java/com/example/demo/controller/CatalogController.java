package com.example.demo.controller;

import com.example.demo.domain.Catalog;
import com.example.demo.domain.Comment;
import com.example.demo.mapper.CatalogMapper;
import com.example.demo.mapper.SonglistMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
 * 类名: CatalogController
 * 编辑日期: 2020/3/16
 * 编辑时间: 20:24
 *
 * @Description:
 */
@Controller
public class CatalogController {
    @Autowired
    CatalogMapper catalogMapper;
    @Autowired
    SonglistMapper songlistMapper;

    @RequestMapping("/catalog")
    public ModelAndView catalogByUser(@RequestParam(defaultValue = "2")Long id, @RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = "5")int pageSize) {
        ModelAndView modelAndView = new ModelAndView();

        PageHelper.startPage(pageNo,pageSize);
        List<Catalog> byUserIdCatalog = catalogMapper.findByUserIdCatalog(id);

        PageInfo pageInfo = new PageInfo(byUserIdCatalog);
        modelAndView.addObject("catalogs",pageInfo);

        modelAndView.setViewName("admins/catalog-table");
        return modelAndView;
    }


    @RequestMapping("/updateCatalog")
    @Transactional
    public ModelAndView updateCatalog(@RequestParam(defaultValue = "")Long id) {
        ModelAndView modelAndView = new ModelAndView();
        Catalog byIdCatalog = catalogMapper.findByIdCatalog(id);

        modelAndView.addObject("catalog",byIdCatalog);
        modelAndView.setViewName("admins/update-catalog");

        return modelAndView;

    }

    @PostMapping("/updateCatalog")
    @Transactional
    public ModelAndView updateCatalogPost(@RequestParam(defaultValue = "")Long id,Catalog catalog) {
        ModelAndView modelAndView = new ModelAndView();

        Catalog byIdCatalog = catalogMapper.findByIdCatalog(id);
        byIdCatalog.setName(catalog.getName());

        catalogMapper.updateCatalog(byIdCatalog);


        modelAndView.setViewName("redirect:/catalog?id=" + byIdCatalog.getUser().getId());

        return modelAndView;
    }

    @RequestMapping("/deleteCatalog")
    @Transactional
    public ModelAndView deleteCatalog(@RequestParam(defaultValue = "")Long id) {
        ModelAndView modelAndView = new ModelAndView();


        return modelAndView;
    }

    @RequestMapping("/catalogs")
    public ModelAndView catalogs(@RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = "5")int pageSize) {
        ModelAndView modelAndView = new ModelAndView();

        PageHelper.startPage(pageNo,pageSize);
        List<Catalog> allCatalog = catalogMapper.findAllCatalog();

        PageInfo pageInfo = new PageInfo(allCatalog);
        modelAndView.addObject("catalogs",pageInfo);

        modelAndView.setViewName("admins/catalogs-table");
        return modelAndView;
    }
}
