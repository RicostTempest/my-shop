package com.windsoft.my.shop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.windsoft.my.shop.commons.persistence.BaseEntity;
import com.windsoft.my.shop.commons.utils.RegexpUtils;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;

/**
 * @Author: Ricost
 * @Date: 2019/10/15 10:18
 */
public class TbUser extends BaseEntity {
    @Length(min = 6, max = 20, message = "用户名长度必须介于 6 和 20 之间")
    private String username;
    @Length(min = 6, max = 20, message = "密码长度必须介于 6 和 20 之间")
    private String password;
    @Pattern(regexp = RegexpUtils.PHONE, message = "手机号格式不正确")
    private String phone;
    @Pattern(regexp = RegexpUtils.EMAIL, message = "邮箱格式不正确")
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

    @JsonIgnore   //标注为不进行json数据化
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
