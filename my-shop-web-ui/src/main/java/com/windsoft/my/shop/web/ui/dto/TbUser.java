package com.windsoft.my.shop.web.ui.dto;

import java.io.Serializable;

/**
 * @ClassName TbUser
 * @Description
 * @Author RicostTempest
 * @Date 2019/11/22 22:05
 * @Version V1.0
 **/

public class TbUser implements Serializable {
    private Long id;
    private String username;
    private String password;
    public String phone;
    private String email;

    @Override
    public String toString() {
        return "TbUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
