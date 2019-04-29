package com.github.abigail830.mybatictest.infrastructure;

import com.github.abigail830.mybatictest.domain.UserInfrastructure;
import com.github.abigail830.mybatictest.domain.model.User;
import com.github.abigail830.mybatictest.domain.model.Wish;
import com.github.abigail830.mybatictest.infrastructure.entity.UserEntity;
import com.github.abigail830.mybatictest.infrastructure.entity.WishEntity;
import com.github.abigail830.mybatictest.infrastructure.mapper.UserMapper;
import com.github.abigail830.mybatictest.infrastructure.mapper.WishMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@Slf4j
public class UserInfrastructureImpl implements UserInfrastructure {

    private static final int ONE_ROW = 1;
    private UserMapper userMapper;
    private WishMapper wishMapper;

    @Autowired
    public UserInfrastructureImpl(UserMapper userMapper, WishMapper wishMapper) {
        this.userMapper = userMapper;
        this.wishMapper = wishMapper;
    }

    @Override
    public List<User> getAll() {
        return userMapper.getAllUsers().stream().map(UserEntity::toUser).collect(Collectors.toList());
    }

    @Override
    public Optional<User> getUserById(Integer id) {
        final Optional<UserEntity> userById = userMapper.getUserById(id);
        return userById.map(UserEntity::toUser);
    }

    @Override
    public void insertUser(User user) {
        final UserEntity userEntity = UserEntity.fromNewUser(user);
        userMapper.insertUser(userEntity);
    }

    @Override
    public Integer updateUser(User user) {
        final UserEntity userEntity = UserEntity.fromExistUser(user);
        return userMapper.updateUser(userEntity);

    }

    @Override
    public Integer deleteUser(Integer id) {
        deleteAllWishesForUser(id);
        return deleteUserFromUserTblOnly(userMapper.deleteUser(id));
    }

    private Integer deleteUserFromUserTblOnly(Integer id) {
        return userMapper.deleteUser(id);

    }

    @Override
    public List<Wish> getAllWishesByUser(Integer userId) {
        return wishMapper.getAllWishesByUser(userId).stream().map(WishEntity::toWish).collect(Collectors.toList());
    }

    @Override
    public void insertWish(Wish wish, Integer userId) throws SQLIntegrityConstraintViolationException {
        final WishEntity wishEntity = WishEntity.fromNewWish(wish, userId);
        final Integer result = wishMapper.insertWish(wishEntity);
        if (result != ONE_ROW) {
            throw new SQLIntegrityConstraintViolationException();
        }
    }

    @Override
    public Integer deleteAllWishesForUser(Integer userId) {
        return wishMapper.deleteWishForUser(userId);

    }

    @Override
    public boolean isSuccess(Integer updatedRowCount) {
        return updatedRowCount >= ONE_ROW;
    }
}
