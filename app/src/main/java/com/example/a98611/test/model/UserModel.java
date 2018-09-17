package com.example.a98611.test.model;


import com.example.a98611.test.bean.UserBean;

public interface UserModel {

    void setSid(int cid);
    UserBean load(int sid);
    void setUsername(String username);
    void setUserpwd(String userpwd);
}
