package com.github.abigail830.mybatictest.infrastructure.entity;

import com.github.abigail830.mybatictest.domain.model.Wish;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class WishEntity {

    Integer id;
    Integer userId;
    Timestamp createTime;
    String description;

    public WishEntity(Integer userId, Timestamp createTime, String description) {
        this.userId = userId;
        this.createTime = createTime;
        this.description = description;
    }

    public WishEntity(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public Wish toWish(){
        return new Wish(id, createTime, description);
    }

    public static WishEntity fromNewWish(Wish wish, Integer userId){
        return new WishEntity(userId, wish.getCreateTime(), wish.getDescription());
    }

    public static WishEntity fromExistWish(String description, Integer id){
        return new WishEntity(id, description);
    }

}
