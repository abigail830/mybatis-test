package com.github.abigail830.mybatictest.infrastructure.entity;

import com.github.abigail830.mybatictest.service.User;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    Integer id;
    String userName;
    String gender;
    String avatarUrl;

    public UserEntity(String userName, String gender, String avatarUrl) {
        this.userName = userName;
        this.gender = gender;
        this.avatarUrl = avatarUrl;
    }

    public User toUser(){
        return new User(id, userName, gender, avatarUrl);
    }

    public static UserEntity fromNewUser(User user){
        return new UserEntity(user.getUserName(), user.getGender(), user.getAvatarUrl());
    }

    public static UserEntity fromExistUser(User user){
        return new UserEntity(user.getId(), user.getUserName(), user.getGender(), user.getAvatarUrl());
    }

}
