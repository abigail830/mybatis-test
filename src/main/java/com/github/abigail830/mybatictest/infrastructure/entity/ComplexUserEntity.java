package com.github.abigail830.mybatictest.infrastructure.entity;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ComplexUserEntity {

    Integer id;
    String userName;
    String gender;
    String avatarUrl;
    Integer wishId;
    Timestamp createTime;
    String description;

}
