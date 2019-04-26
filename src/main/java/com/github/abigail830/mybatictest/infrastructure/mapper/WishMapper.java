package com.github.abigail830.mybatictest.infrastructure.mapper;

import com.github.abigail830.mybatictest.infrastructure.entity.WishEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WishMapper {

    List<WishEntity> getAllWishes();

    WishEntity getWishById(Integer id);

//    WishEntity getWishByUserId(Integer userId);

    void insertWish(WishEntity wishEntity);

    void updateWishDescription(WishEntity wishEntity);

    void deleteWish(Integer id);
}
