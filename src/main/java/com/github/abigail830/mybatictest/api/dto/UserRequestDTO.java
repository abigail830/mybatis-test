package com.github.abigail830.mybatictest.api.dto;

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

}
