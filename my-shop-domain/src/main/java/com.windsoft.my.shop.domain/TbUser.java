package com.windsoft.my.shop.domain;

import com.windsoft.my.shop.commons.persistence.BaseEntity;

/**
 * @Author: Ricost
 * @Date: 2019/10/15 10:18
 */
public class TbUser extends BaseEntity {
    private String username;
    private String password;
    private String phone;
    private String email;


    private boolean isRemember;

    @Override
    public String toString() {
        return "TbUser{" +
                "id=" + getId() +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", created=" + getCreated() +
                ", updated=" + getUpdated() +
                '}';
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

    public boolean isRemember() {
        return isRemember;
    }

    public void setRemember(boolean remember) {
        isRemember = remember;
    }
}
