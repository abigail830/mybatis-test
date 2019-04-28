package com.github.abigail830.mybatictest.domain;

import com.github.abigail830.mybatictest.domain.model.Wish;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public interface WishInfrastructure {

    List<Wish> getAllWishesByUser(Integer userId);

    Wish getWishById(Integer id);

    void insertWish(Wish wish, Integer userId);

    void updateWishDescriptionById(String description, Integer id) throws SQLIntegrityConstraintViolationException;

    void deleteWish(Integer id) throws SQLIntegrityConstraintViolationException;
}
