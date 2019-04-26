package com.github.abigail830.mybatictest.service;

import lombok.Getter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
public class User {

    int id;
    String userName;
    String gender;
    String avatarUrl;

    List<Wish> wishList;

    public User(int id, String userName, String gender, String avatarUrl) {
        this.id = id;
        this.userName = userName;
        this.gender = gender;
        this.avatarUrl = avatarUrl;
    }
}
