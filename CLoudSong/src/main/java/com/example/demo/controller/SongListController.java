package com.example.demo.controller;

import com.example.demo.domain.*;
import com.example.demo.mapper.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
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
 * 类名: SongListController
 * 编辑日期: 2020/3/15
 * 编辑时间: 20:01
 *
 * @Description:
 */
@Controller
public class SongListController {
    @Autowired
    SonglistMapper songlistMapper;

    @Autowired
    FavoriteMapper favoriteMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    CatalogMapper catalogMapper;

    @Autowired
    SongMapper songMapper;

    @RequestMapping("/songlist")
    public ModelAndView songlist(@RequestParam(defaultValue = "2")Long id,@RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = "5") int pageSize){
        ModelAndView modelAndView = new ModelAndView();

        PageHelper.startPage(pageNo,pageSize);
        List<Songlist> byUserIdSonglist = songlistMapper.findByUserIdSonglist(id);
        PageInfo pageInfo = new PageInfo(byUserIdSonglist);


        modelAndView.addObject("songlists",pageInfo);
        modelAndView.addObject("userId",id);

        modelAndView.setViewName("admins/components-table");

        return  modelAndView;
    }

    @RequestMapping("/deletesonglist")
    @Transactional
    public ModelAndView deleteSonglist(@RequestParam(defaultValue = "")Long id ) {

        ModelAndView modelAndView = new ModelAndView();
        Songlist byIdSonglist = songlistMapper.findByIdSonglist(id);
        songlistMapper.deleteSonglistComment(id);
        songlistMapper.deleteSonglistFavorite(id);
        songlistMapper.deleteSonglistSong(id);

        songlistMapper.deleteSonglist(id);

        modelAndView.setViewName("redirect:/songlist?id=" + byIdSonglist.getUser().getId());

        return modelAndView;

    }

    @RequestMapping("/updateSonglist")
    @Transactional
    public ModelAndView updateSonglist(@RequestParam(defaultValue = "")Long id) {

        ModelAndView modelAndView = new ModelAndView();
        Songlist byIdSonglist = songlistMapper.findByIdSonglist(id);
        modelAndView.addObject("songlist",byIdSonglist);

        modelAndView.setViewName("admins/features-songlist");
        return modelAndView;
    }

    @PostMapping("/updateSongList")
    @Transactional
    public  ModelAndView updateSongListPost(@RequestParam(defaultValue = "")Long id,Songlist songlist){
        ModelAndView modelAndView = new ModelAndView();
        Songlist byIdSonglist = songlistMapper.findByIdSonglist(id);
        songlist.setId(id);
        Catalog byUserNameCatalog = catalogMapper.findByUserNameCatalog(songlist.getCatalog().getName());
        songlist.setCatalog(byUserNameCatalog);
        songlistMapper.updateSonglist(songlist);

        modelAndView.setViewName("redirect:/songlist?id=" + byIdSonglist.getUser().getId());
        return modelAndView;


    }

    @RequestMapping("/insertSong")
    @Transactional
    public ModelAndView insertSong(@RequestParam(defaultValue = "")Long id){

        ModelAndView modelAndView = new ModelAndView();
        Songlist byIdSonglist = songlistMapper.findByIdSonglist(id);
        modelAndView.addObject("songlist",byIdSonglist);

        modelAndView.setViewName("admins/insert-song");
        return modelAndView;
    }

    @PostMapping("/insertSong")
    @Transactional
    public ModelAndView insertSongPost(@RequestParam(defaultValue = "")Long id, Song song){
        ModelAndView modelAndView = new ModelAndView();

        songMapper.insertSong(song);
        songlistMapper.insertSonglistSong(id,song.getId());
        Songlist byIdSonglist = songlistMapper.findByIdSonglist(id);
        byIdSonglist.setSongSize(songlistMapper.songSize(id));
        songlistMapper.updateSongSize(byIdSonglist);


        modelAndView.setViewName("redirect:/updateSonglist?id=" + id);


        return modelAndView;
    }

    @RequestMapping("/deleteSong")
    @Transactional
    public ModelAndView deleteSong(@RequestParam(defaultValue = "")Long id){
        ModelAndView modelAndView = new ModelAndView();
        Long aLong = songlistMapper.selectSongListId(id);
        songlistMapper.deleteSonglistSong(id);
        songMapper.deleteSong(id);


        Songlist byIdSonglist = songlistMapper.findByIdSonglist(aLong);
        byIdSonglist.setSongSize(songlistMapper.songSize(aLong));
        songlistMapper.updateSongSize(byIdSonglist);



        modelAndView.setViewName("redirect:/updateSonglist?id=" + aLong);
        return modelAndView;
    }





    @RequestMapping("/insertSonglist")
    @Transactional
    public ModelAndView insertSonglist(@RequestParam(defaultValue = "")Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userId",id);
        modelAndView.setViewName("admins/insert-songlist");

        return modelAndView;

    }

    @PostMapping("/insertSongList")
    @Transactional
    public ModelAndView insertSonglistPost(@RequestParam(defaultValue = "")Long id,Songlist songlist){

        ModelAndView modelAndView = new ModelAndView();
        Songlist songlist1 = new Songlist();
        User byId = userMapper.findById(id);
        Catalog byUserNameCatalog = catalogMapper.findByUserNameCatalog(songlist.getCatalog().getName());

        if(byUserNameCatalog == null) {

        }else {
            songlist1.setCatalog(byUserNameCatalog);
        }
        songlist1.setUser(byId);
        songlist1.setSongListName(songlist.getSongListName());
        songlistMapper.insertSonglist(songlist1);

        System.out.println(songlist1);


        modelAndView.setViewName("redirect:/songlist?id" + id);

        return modelAndView;

    }




    @RequestMapping("/songlists")
    public ModelAndView songlists(@RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = "5") int pageSize){
        ModelAndView modelAndView = new ModelAndView();

        PageHelper.startPage(pageNo,pageSize);
        List<Songlist> allSonglist = songlistMapper.findAllSonglist();
        PageInfo pageInfo = new PageInfo(allSonglist);


        modelAndView.addObject("songlists",pageInfo);

        modelAndView.setViewName("admins/songlists-table");

        return  modelAndView;

    }

    @RequestMapping("/favoriteSonglist")
    @Transactional
    public ModelAndView favoriteSongList(@RequestParam(defaultValue = "2")Long id,@RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = "5")int pageSize) {

        ModelAndView modelAndView = new ModelAndView();


        PageHelper.startPage(pageNo,pageSize);
        List<Favorite> byUserIdFavorite = favoriteMapper.findByUserIdFavorite(id);

        PageInfo pageInfo = new PageInfo(byUserIdFavorite);
        modelAndView.addObject("songlists",pageInfo);
        modelAndView.setViewName("admins/favoriteSonglist-table");
        return modelAndView;
    }

    @RequestMapping("/updateFavoriteSonglist")
    @Transactional
    public ModelAndView updateFavoriteSongList(@RequestParam(defaultValue = "")Long id){
        ModelAndView modelAndView = new ModelAndView();
        Songlist byIdSonglist = songlistMapper.findByIdSonglist(id);
        modelAndView.addObject("songlist",byIdSonglist);

        modelAndView.setViewName("admins/favorite-songlist");

        return modelAndView;

    }
    @RequestMapping("/deleteFavoritesonglist")
    @Transactional
    public ModelAndView deleteFavoriteSongList(@RequestParam(defaultValue = "")Long id) {
        ModelAndView modelAndView = new ModelAndView();
        Favorite byIdFavorite = favoriteMapper.findByIdFavorite(id);
        songlistMapper.deleteSonglistFavorite(id);
        favoriteMapper.deleteFavorite(id);

        Integer integer = songlistMapper.favoriteSize(byIdFavorite.getSonglist().getId());
        Songlist songlist = byIdFavorite.getSonglist();
        songlist.setFavoriteSize(integer);
        songlistMapper.updateFavoriteSize(songlist);
        modelAndView.setViewName("redirect:/favoriteSonglist?id" + byIdFavorite.getUser().getId());

        return modelAndView;

    }


    @RequestMapping("/favoriteSonglists")
    @Transactional
    public ModelAndView favoriteSongList(@RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = "5")int pageSize) {

        ModelAndView modelAndView = new ModelAndView();


        PageHelper.startPage(pageNo,pageSize);
        List<Favorite> allFavorite = favoriteMapper.findAllFavorite();

        PageInfo pageInfo = new PageInfo(allFavorite);
        modelAndView.addObject("songlists",pageInfo);
        modelAndView.setViewName("admins/favoriteSonglists-table");
        return modelAndView;
    }

}
