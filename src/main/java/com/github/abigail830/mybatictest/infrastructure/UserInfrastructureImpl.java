package com.github.abigail830.mybatictest.infrastructure;

import com.github.abigail830.mybatictest.domain.UserInfrastructure;
import com.github.abigail830.mybatictest.domain.model.User;
import com.github.abigail830.mybatictest.infrastructure.entity.UserEntity;
import com.github.abigail830.mybatictest.infrastructure.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Slf4j
public class UserInfrastructureImpl implements UserInfrastructure {

    private static final int SUCCESS = 1;
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
    public void updateUser(User user) throws SQLIntegrityConstraintViolationException {
        final UserEntity userEntity = UserEntity.fromExistUser(user);
        final Integer result = userMapper.updateUser(userEntity);
        if(result!= SUCCESS){
            throw new SQLIntegrityConstraintViolationException();
        }
    }

    @Override
    public void deleteUser(Integer id) throws SQLIntegrityConstraintViolationException {

        final Integer result = userMapper.deleteUser(id);
        if(result!=SUCCESS){
            throw new SQLIntegrityConstraintViolationException();
        }
    }
}
