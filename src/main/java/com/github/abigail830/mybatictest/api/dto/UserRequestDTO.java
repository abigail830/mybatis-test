package com.github.abigail830.mybatictest.api.dto;

import com.github.abigail830.mybatictest.service.User;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {

    String userName;
    String avatarUrl;
    String gender;

    public User toUser(Integer id){
        return new User(id, userName, gender, avatarUrl);
    }

    public User toUser(){
        return new User(userName, gender, avatarUrl);
    }

}
