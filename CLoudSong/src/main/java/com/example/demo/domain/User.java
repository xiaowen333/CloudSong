package com.example.demo.domain;

import java.io.Serializable;
import java.util.List;

/**
 * 作者: 是小文啊
 * QQ: 1612902996
 * 博客: wenshijin.cn
 * 描述:
 * 类名: User
 * 编辑日期: 2020/3/12
 * 编辑时间: 22:33
 *
 * @Description:
 */
public class User implements Serializable {
    private Long id;
    private String avatar;
    private String email;
    private String password;
    private String username;

    private List<Authority> authoritys;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Authority> getAuthoritys() {
        return authoritys;
    }

    public void setAuthoritys(List<Authority> authoritys) {
        this.authoritys = authoritys;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", avatar='" + avatar + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", authorities=" + authoritys +
                '}';
    }
}
