package com.example.entity;

import java.io.Serializable;

public class User implements Serializable{
    private String username;
    private String passwd;
    private String fullname;
    private  String phone;

    public User(){
    }
    public User(String username, String password, String fullname,String phone) {
        this.username = username;
        this.passwd = password;
        this.fullname = fullname;
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return passwd;
    }

    public void setPassword(String password) {
        this.passwd = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
