package com.github.abigail830.mybatictest.domain;

import com.github.abigail830.mybatictest.domain.model.Wish;

import java.util.List;

public interface WishInfrastructure {

    List<Wish> getAll();

    Wish getWishById(Integer id);

    void insertWish(Wish wish, Integer userId);

    void updateWishDescriptionById(String description, Integer id);

    void deleteWish(Integer id);
}
