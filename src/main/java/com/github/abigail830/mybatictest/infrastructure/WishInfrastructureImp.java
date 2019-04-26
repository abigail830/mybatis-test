package com.github.abigail830.mybatictest.infrastructure;

import com.github.abigail830.mybatictest.infrastructure.entity.WishEntity;
import com.github.abigail830.mybatictest.infrastructure.mapper.WishMapper;
import com.github.abigail830.mybatictest.service.Wish;
import com.github.abigail830.mybatictest.service.WishInfrastructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class WishInfrastructureImp implements WishInfrastructure {

    private WishMapper wishMapper;

    @Autowired
    public WishInfrastructureImp(WishMapper wishMapper) {
        this.wishMapper = wishMapper;
    }

    @Override
    public List<Wish> getAll() {
        return wishMapper.getAllWishes().stream().map(WishEntity::toWish).collect(Collectors.toList());
    }

    @Override
    public Wish getWishById(Integer id) {
        return wishMapper.getWishById(id).toWish();
    }

    @Override
    public void insertWish(Wish wish, Integer userId) {
        final WishEntity wishEntity = WishEntity.fromNewWish(wish, userId);
        wishMapper.insertWish(wishEntity);
    }

    @Override
    public void updateWishDescriptionById(String description, Integer id) {
        final WishEntity wishEntity = WishEntity.fromExistWish(description, id);
        wishMapper.updateWishDescription(wishEntity);
    }

    @Override
    public void deleteWish(Integer id) {
        wishMapper.deleteWish(id);
    }
}
