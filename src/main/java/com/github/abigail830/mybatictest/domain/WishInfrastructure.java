package com.github.abigail830.mybatictest.domain;

import com.github.abigail830.mybatictest.domain.model.Wish;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Optional;

public interface WishInfrastructure {

    Optional<Wish> getWishById(Integer id);

    void updateWishDescriptionById(String description, Integer id) throws SQLIntegrityConstraintViolationException;

    void deleteWish(Integer id) throws SQLIntegrityConstraintViolationException;
}
