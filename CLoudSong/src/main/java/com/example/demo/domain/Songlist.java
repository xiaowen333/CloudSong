package com.example.demo.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * 作者: 是小文啊
 * QQ: 1612902996
 * 博客: wenshijin.cn
 * 描述:
 * 类名: Songlist
 * 编辑日期: 2020/3/13
 * 编辑时间: 12:30
 *
 * @Description:
 */
public class Songlist implements Serializable {
    private Long id;
    private String songListName;
    private Integer commentSize = 0;
    private Integer favoriteSize = 0;
    private Integer readSize = 0;
    private Integer songSize = 0;
    private Timestamp createTime;



    private User user;
    private Catalog catalog;
    private List<Comment> comments;
    private List<Favorite> favorites;
    private List<Song> songs;


    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSongListName() {
        return songListName;
    }

    public void setSongListName(String songListName) {
        this.songListName = songListName;
    }

    public Integer getCommentSize() {
        return commentSize;
    }

    public void setCommentSize(Integer commentSize) {
        this.commentSize = commentSize;
    }

    public Integer getFavoriteSize() {
        return favoriteSize;
    }

    public void setFavoriteSize(Integer favoriteSize) {
        this.favoriteSize = favoriteSize;
    }

    public Integer getReadSize() {
        return readSize;
    }

    public void setReadSize(Integer readSize) {
        this.readSize = readSize;
    }

    public Integer getSongSize() {
        return songSize;
    }

    public void setSongSize(Integer songSize) {
        this.songSize = songSize;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Favorite> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<Favorite> favorites) {
        this.favorites = favorites;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    @Override
    public String toString() {
        return "Songlist{" +
                "id=" + id +
                ", songListName='" + songListName + '\'' +
                ", commentSize=" + commentSize +
                ", favoriteSize=" + favoriteSize +
                ", readSize=" + readSize +
                ", songSize=" + songSize +
                ", createTime=" + createTime +
                ", user=" + user +
                ", catalog=" + catalog +
                ", comments=" + comments +
                ", favorites=" + favorites +
                ", songs=" + songs +
                '}';
    }
}
