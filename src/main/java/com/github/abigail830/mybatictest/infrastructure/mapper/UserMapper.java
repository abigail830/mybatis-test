package com.github.abigail830.mybatictest.infrastructure.mapper;

import com.github.abigail830.mybatictest.infrastructure.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserMapper {

    List<UserEntity> getAllUsers();

    Optional<UserEntity> getUserById(Integer id);

    void insertUser(UserEntity user);

    Integer updateUser(UserEntity user);

    Integer deleteUser(Integer id);
}
