package com.example.demo.domain;

import java.io.Serializable;

/**
 * 作者: 是小文啊
 * QQ: 1612902996
 * 博客: wenshijin.cn
 * 描述:
 * 类名: Song
 * 编辑日期: 2020/3/13
 * 编辑时间: 13:26
 *
 * @Description:
 */
public class Song implements Serializable {
    private Long id;
    private String title;
    private String album;
    private  String Songplay;

    public String getSongplay() {
        return Songplay;
    }

    public void setSongplay(String songplay) {
        Songplay = songplay;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", album='" + album + '\'' +
                ", Songplay='" + Songplay + '\'' +
                '}';
    }
}
