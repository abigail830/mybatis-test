package com.github.abigail830.mybatictest.infrastructure.mapper;

import com.github.abigail830.mybatictest.infrastructure.entity.WishEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WishMapper {

    List<WishEntity> getAllWishesByUser(Integer userId);

    WishEntity getWishById(Integer id);

    void insertWish(WishEntity wishEntity);

    Integer updateWishDescription(WishEntity wishEntity);

    Integer deleteWish(Integer id);
}
