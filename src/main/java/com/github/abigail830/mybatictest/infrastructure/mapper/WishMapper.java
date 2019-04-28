package com.github.abigail830.mybatictest.infrastructure.mapper;

import com.github.abigail830.mybatictest.infrastructure.entity.WishEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface WishMapper {

    List<WishEntity> getAllWishesByUser(Integer userId);

    Optional<WishEntity> getWishById(Integer id);

    Integer insertWish(WishEntity wishEntity);

    Integer updateWishDescription(WishEntity wishEntity);

    Integer deleteWish(Integer id);
}
