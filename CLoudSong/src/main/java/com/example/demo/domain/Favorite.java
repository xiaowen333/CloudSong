package com.example.demo.domain;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 作者: 是小文啊
 * QQ: 1612902996
 * 博客: wenshijin.cn
 * 描述:
 * 类名: Favorite
 * 编辑日期: 2020/3/13
 * 编辑时间: 13:29
 *
 * @Description:
 */
public class Favorite implements Serializable {
    private Long id;
    private User user;
    private Timestamp createTime;
    private Songlist songlist;

    public Songlist getSonglist() {
        return songlist;
    }

    public void setSonglist(Songlist songlist) {
        this.songlist = songlist;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Favorite{" +
                "id=" + id +
                ", user=" + user +
                ", createTime=" + createTime +
                '}';
    }
}
