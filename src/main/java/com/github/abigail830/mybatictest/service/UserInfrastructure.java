package com.github.abigail830.mybatictest.service;

import org.springframework.stereotype.Repository;

import java.util.List;


public interface UserInfrastructure {

    List<User> getAll();

    User getUserById(Integer id);

    void insertUser(User user);

    void updateUser(User user);

    void deleteUser(Integer id);
}
