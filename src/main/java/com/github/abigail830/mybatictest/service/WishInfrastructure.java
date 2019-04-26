package com.github.abigail830.mybatictest.service;

import java.util.List;

public interface WishInfrastructure {

    List<Wish> getAll();

    Wish getWishById(Integer id);

    void insertWish(Wish wish, Integer userId);

    void updateWishDescriptionById(String description, Integer id);

    void deleteWish(Integer id);
}
