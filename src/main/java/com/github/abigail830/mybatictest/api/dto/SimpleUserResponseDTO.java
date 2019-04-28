package com.github.abigail830.mybatictest.api.dto;

import com.github.abigail830.mybatictest.domain.model.User;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SimpleUserResponseDTO {

    int id;
    String userName;
    String gender;
    String avatarUrl;

    public static SimpleUserResponseDTO fromUser(User user){
        return new SimpleUserResponseDTO(user.getId(), user.getUserName(), user.getGender(), user.getAvatarUrl());
    }
}
