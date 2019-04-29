package com.github.abigail830.mybatictest.domain;

import com.github.abigail830.mybatictest.domain.model.User;
import com.github.abigail830.mybatictest.domain.model.Wish;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;


public interface UserInfrastructure {

    List<User> getAll();

    Optional<User> getUserById(Integer id);

    void insertUser(User user);

    Integer updateUser(User user);

    Integer deleteUser(Integer id);

    List<Wish> getAllWishesByUser(Integer userId);

    void insertWish(Wish wish, Integer userId) throws SQLIntegrityConstraintViolationException;

    Integer deleteAllWishesForUser(Integer userId);

    boolean isSuccess(Integer updatedRowCount);
}
