package com.github.abigail830.mybatictest.domain;

import com.github.abigail830.mybatictest.domain.model.User;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;


public interface UserInfrastructure {

    List<User> getAll();

    User getUserById(Integer id);

    void insertUser(User user);

    void updateUser(User user) throws SQLIntegrityConstraintViolationException;

    void deleteUser(Integer id) throws SQLIntegrityConstraintViolationException;
}
