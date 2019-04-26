package com.github.abigail830.mybatictest.infrastructure;

import com.github.abigail830.mybatictest.infrastructure.entity.UserEntity;
import com.github.abigail830.mybatictest.infrastructure.mapper.UserMapper;
import com.github.abigail830.mybatictest.service.User;
import com.github.abigail830.mybatictest.service.UserInfrastructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class UserInfrastructureImpl implements UserInfrastructure {

    private UserMapper userMapper;

    @Autowired
    public UserInfrastructureImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public List<User> getAll() {
        return userMapper.getAllUsers().stream().map(UserEntity::toUser).collect(Collectors.toList());
    }

    @Override
    public User getUserById(Integer id) {
        return userMapper.getUserById(id).toUser();
    }

    @Override
    public void insertUser(User user) {
        final UserEntity userEntity = UserEntity.fromNewUser(user);
        userMapper.insertUser(userEntity);
    }

    @Override
    public void updateUser(User user) {
        final UserEntity userEntity = UserEntity.fromExistUser(user);
        userMapper.updateUser(userEntity);
    }

    @Override
    public void deleteUser(Integer id) {
        userMapper.deleteUser(id);
    }
}
