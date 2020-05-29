package com.quan.oauth.entity;

import javax.persistence.*;

@Entity
@Table(name = "test_resource_user")
public class TestResourceUser {

    @Id
    @Column(name="user_name")
    private String userName;

    @Column(name="password")
    private String password;


    @Column(name="user_desc")
    private String userDesc;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserDesc() {
        return userDesc;
    }

    public void setUserDesc(String userDesc) {
        this.userDesc = userDesc;
    }

    @Override
    public String toString() {
        return "TestUser{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", userDesc='" + userDesc + '\'' +
                '}';
    }
}
