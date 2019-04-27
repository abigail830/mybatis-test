package com.github.abigail830.mybatictest.infrastructure;

import com.github.abigail830.mybatictest.infrastructure.entity.UserEntity;
import com.github.abigail830.mybatictest.infrastructure.mapper.UserMapper;
import com.github.abigail830.mybatictest.service.User;
import com.github.abigail830.mybatictest.service.UserInfrastructure;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLClientInfoException;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Slf4j
public class UserInfrastructureImpl implements UserInfrastructure {

    public static final int SUCCESS = 1;
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
    public void updateUser(User user) throws SQLClientInfoException{
        final UserEntity userEntity = UserEntity.fromExistUser(user);
        final Integer result = userMapper.updateUser(userEntity);
        if(result!= SUCCESS){
            throw new SQLClientInfoException();
        }
    }

    @Override
    public void deleteUser(Integer id) throws SQLClientInfoException {

        final Integer result = userMapper.deleteUser(id);
        if(result!=SUCCESS){
            throw new SQLClientInfoException();
        }
    }
}
